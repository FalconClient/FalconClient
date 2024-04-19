package dev.slangware.ultralight;

import dev.slangware.ultralight.annotations.HTMLRoute;
import dev.slangware.ultralight.bridge.FileSystemBridge;
import dev.slangware.ultralight.bridge.LoggerBridge;
import dev.slangware.ultralight.bridge.LwjglClipboardBridge;
import lombok.Getter;
import lombok.Synchronized;
import net.janrupf.ujr.api.*;
import net.janrupf.ujr.api.config.UlViewConfig;
import net.janrupf.ujr.core.UltralightJavaReborn;
import net.janrupf.ujr.core.platform.PlatformEnvironment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import spark.Spark;

import java.io.Closeable;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class UltraManager implements Closeable {
    @Getter
    private static final UltraManager instance = new UltraManager();
    private final List<HtmlScreen> screens = Collections.synchronizedList(new ArrayList<>());
    @Getter
    private static final Logger logger = LogManager.getLogger("UltraManager");
    public int SERVER_PORT;
    private UltralightJavaReborn ultralight;
    @Getter
    private ViewController viewController;


    /**
     * Adds a new HtmlScreen to the screens list and registers its annotated routes with Spark.
     *
     * @param screen the HtmlScreen to be added
     */
    @Synchronized("screens")
    public void addScreen(HtmlScreen screen) {
        if (screens.contains(screen)) return;
        screens.add(screen);

        for (Method m : screen.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(HTMLRoute.class)) continue;

            m.setAccessible(true);

            HTMLRoute annotation = m.getAnnotation(HTMLRoute.class);

            try {
                switch (annotation.method()) {
                    case "GET":
                        Spark.get(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "POST":
                        Spark.post(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "PUT":
                        Spark.put(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "DELETE":
                        Spark.delete(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "PATCH":
                        Spark.patch(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "OPTIONS":
                        Spark.options(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "HEAD":
                        Spark.head(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "TRACE":
                        Spark.trace(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    case "CONNECT":
                        Spark.connect(annotation.path(), (request, response) -> m.invoke(screen, request, response));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + annotation.method());
                }
            } catch (Exception e) {
                logger.throwing(e);
            }
        }
    }


    /**
     * Removes the given HtmlScreen from the synchronized list of screens.
     * Also, unmaps any HTMLRoute paths associated with the HtmlScreen.
     *
     * @param htmlScreen the HtmlScreen to be removed
     */
    @Synchronized("screens")
    public void removeScreen(HtmlScreen htmlScreen) {
        screens.remove(htmlScreen);

        for (Method m : htmlScreen.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(HTMLRoute.class)) continue;

            m.setAccessible(true);

            HTMLRoute route = m.getAnnotation(HTMLRoute.class);

            try {
                Spark.unmap(route.path());
            } catch (Exception e) {
                logger.throwing(e);
            }
        }

    }


    /**
     * Initialize the function.
     */
    public void init() {
        this.init("/templates");
    }

    /**
     * Initializes the function by loading the platform environment, activating the Ultralight renderer, setting up the platform configuration, and starting an HTTP server.
     */
    public void init(String templateFiles) {
        Keyboard.enableRepeatEvents(true);

        PlatformEnvironment environment = PlatformEnvironment.load();
        logger.info("Platform environment has been loaded!");

        ultralight = new UltralightJavaReborn(environment);
        ultralight.activate();

        UltralightPlatform platform = UltralightPlatform.instance();
        platform.usePlatformFontLoader();
        platform.setFilesystem(new FileSystemBridge());
        platform.setClipboard(new LwjglClipboardBridge());
        platform.setLogger(new LoggerBridge());

        String cachePath = System.getProperty("java.io.tmpdir") + File.separator + "ultralight";

        logger.info("Ultralight Cache Path: " + cachePath);

        platform.setConfig(
                new UltralightConfigBuilder()
                        .cachePath(cachePath)
                        .resourcePathPrefix(FileSystemBridge.RESOURCE_PREFIX)
                        .build());

        UlViewConfig config = new UltralightViewConfigBuilder()
                .transparent(true)
                .enableImages(true)
                .enableJavascript(true)
                .build();

        UltralightRenderer renderer = UltralightRenderer.getOrCreate();

        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());

        UltralightView view = renderer.createView(resolution.getScaledWidth(), resolution.getScaledHeight(), config);
        viewController = new ViewController(renderer, view);

        logger.info("Ultralight Initialized!");

        try {
            SERVER_PORT = 2001;

            logger.info("Starting http server port " + SERVER_PORT + "...");
            Spark.port(SERVER_PORT);
            Spark.trustForwardHeaders();
            Spark.staticFileLocation(templateFiles);
            Spark.init();
            Spark.awaitInitialization();
            logger.info("HTTP Server started!");
        } catch (Exception e) {
            logger.throwing(e);
        }
    }

    /**
     * Reloads the screens in the application.
     */
    public void reload() {
        for (HtmlScreen screen : screens) {
            screen.reload();
        }
    }

    /**
     * Closes the function.
     */
    @Override
    public void close() {
        ultralight.close();
        Spark.stop();
        Spark.awaitStop();
    }
}
