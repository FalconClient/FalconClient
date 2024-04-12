package dev.slangware.ultralight;

import dev.slangware.ultralight.listener.UILoadListener;
import dev.slangware.ultralight.util.UltralightKeyMapper;
import net.janrupf.ujr.api.*;
import net.janrupf.ujr.api.event.*;
import net.janrupf.ujr.api.javascript.JavaScriptException;
import net.janrupf.ujr.api.math.IntRect;
import net.janrupf.ujr.api.surface.UltralightSurface;
import net.janrupf.ujr.api.util.UltralightBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL12.GL_UNSIGNED_INT_8_8_8_8_REV;


public class ViewController extends  UltralightView{
    private final UltralightRenderer renderer;
    private int glTexture;
    private double scale;


    public ViewController(UltralightRenderer renderer, UltralightView view) {
        super(view.getImplementation());

        this.renderer = renderer;
        this.glTexture = -1;

        this.setLoadListener(new UILoadListener());
    }


    /**
     * Updates the function.
     */
    public void update() {
        this.renderer.update();
        if (!this.needsPaint()) return;
        this.renderer.render();
    }

    /**
     * Renders the texture on the screen. some codes from <a href="https://github.com/DaveH355/ultralight-forge-1.8.9">ultralight-forge-1.8.9</a>
     */
    public void render() {
        if (glTexture == -1) createGLTexture();

        UltralightSurface surface = this.surface();

        int width = (int) this.width();
        int height = (int) this.height();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GlStateManager.bindTexture(glTexture);

        IntRect dirtyBounds = surface.dirtyBounds();

        if (dirtyBounds.isValid()) {
            try (UltralightBuffer buffer = surface.lockPixels()) {
                ByteBuffer byteBuffer = buffer.asByteBuffer();

                GL11.glPixelStorei(GL11.GL_UNPACK_ROW_LENGTH, (int) surface.rowBytes() / 4);

                if (dirtyBounds.width() == width && dirtyBounds.height() == height) {
                    GL11.glTexImage2D(GL11.GL_TEXTURE_2D,
                            0,
                            GL11.GL_RGBA8,
                            width,
                            height,
                            0,
                            GL12.GL_BGRA,
                            GL_UNSIGNED_INT_8_8_8_8_REV,
                            byteBuffer);
                    GL11.glPixelStorei(GL11.GL_UNPACK_ROW_LENGTH, 0);
                } else {
                    int x = dirtyBounds.x();
                    int y = dirtyBounds.y();
                    int dirtyWidth = dirtyBounds.width();
                    int dirtyHeight = dirtyBounds.height();
                    int startOffset = (int) ((y * surface.rowBytes()) + x * 4);

                    GL11.glTexSubImage2D(
                            GL11.GL_TEXTURE_2D,
                            0,
                            x, y, dirtyWidth, dirtyHeight,
                            GL12.GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
                            (ByteBuffer) byteBuffer.position(startOffset));

                }

                GL11.glPixelStorei(GL11.GL_UNPACK_ROW_LENGTH, 0);
            }

            surface.clearDirtyBounds();
        }

        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_TRANSFORM_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, -1, 1);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glColor4f(1, 1, 1, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer bufferBuilder = tessellator.getWorldRenderer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(0, 0, 0).tex(0, 0).endVertex();
        bufferBuilder.pos(0, height, 0).tex(0, 1).endVertex();
        bufferBuilder.pos(width, height, 0).tex(1, 1).endVertex();
        bufferBuilder.pos(width, 0, 0).tex(1, 0).endVertex();
        tessellator.draw();

        GlStateManager.bindTexture(0);

        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glPopAttrib();
    }

    /**
     * Creates a GL texture.
     */
    private void createGLTexture() {
        // Enable 2D texture mode
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        // Generate a new texture
        this.glTexture = GL11.glGenTextures();

        // Bind the texture to the current context
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.glTexture);

        // Set texture parameters
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);

        // Unbind the texture
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        // Disable 2D texture mode
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    /**
     * Handles the mouse click event.
     *
     * @param x           the x-coordinate of the mouse click
     * @param y           the y-coordinate of the mouse click
     * @param mouseButton the button that was clicked (0 for left, 1 for right, 2 for middle)
     * @param buttonDown  indicates whether the button was down or up
     */
    public void onMouseClick(int x, int y, int mouseButton, boolean buttonDown) {
        // Initialize variables
        UltralightMouseEventBuilder builder;
        UlMouseButton button;

        // Map mouseButton value to UlMouseButton enum
        switch (mouseButton) {
            case 0:
                button = UlMouseButton.LEFT;
                break;
            case 1:
                button = UlMouseButton.RIGHT;
                break;
            case 2:
                button = UlMouseButton.MIDDLE;
                break;
            default:
                button = UlMouseButton.NONE;
                break;
        }

        // Determine mouse event type based on buttonDown value
        if (buttonDown) {
            builder = UltralightMouseEventBuilder.down(button);
        } else {
            builder = UltralightMouseEventBuilder.up(button);
        }

        // Get the scale factor for the current resolution
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = scaledResolution.getScaleFactor();

        // Fire the mouse event with scaled coordinates
        this.fireMouseEvent(builder
                .x(x * scaleFactor)
                .y(y * scaleFactor)
                .build());
    }

    /**
     * Handles the mouse move event.
     *
     * @param x the x coordinate of the mouse pointer
     * @param y the y coordinate of the mouse pointer
     */
    public void onMouseMove(int x, int y) {
        // Get the scaled resolution of the Minecraft window
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        // Get the scale factor of the Minecraft window
        int scaleFactor = scaledResolution.getScaleFactor();

        // Create a mouse moved event with the scaled coordinates
        UlMouseEvent event = UltralightMouseEventBuilder.moved()
                .x(x * scaleFactor)
                .y(y * scaleFactor)
                .button(UlMouseButton.NONE)
                .build();

        // Fire the mouse event to the view
        this.fireMouseEvent(event);
    }

    /**
     * Handles the scrolling of the mouse.
     *
     * @param w the amount of scrolling to be done
     */
    public void onScrollMouse(int w) {
        // Check if the left control key is pressed
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            // Calculate the new device scale
            double newDeviceScale = Math.min(Math.max(scale + (double) w / 715, 1), 10);
            // Set the new device scale
            this.setDeviceScale(newDeviceScale);
            return;
        }

        // Create a scroll event with the specified deltaY
        UlScrollEvent scrollEvent = new UltralightScrollEventBuilder(UlScrollEventType.BY_PIXEL)
                .deltaY(w)
                .deltaY(w)
                .build();

        // Fire the scroll event
        this.fireScrollEvent(scrollEvent);
    }


    /**
     * Handles the key down event.
     *
     * @param c   the character representing the key pressed
     * @param key the virtual key code of the key pressed
     */
    public void onKeyDown(char c, int key) {
        UltralightKeyEventBuilder builder;

        // Check if the character is a letter, digit, or special character
        if (Character.isLetterOrDigit(c) || isSpecialCharacter(c)) {
            String text = new String(Character.toChars(c));
            builder = UltralightKeyEventBuilder.character()
                    .unmodifiedText(text)
                    .text(text);
        } else if (Keyboard.getEventKeyState()) {
            builder = UltralightKeyEventBuilder.down();
        } else {
            builder = UltralightKeyEventBuilder.up();
        }

        // Set the properties of the key event builder
        builder.nativeKeyCode(c)
                .autoRepeat(Keyboard.isRepeatEvent())
                .virtualKeyCode(UltralightKeyMapper.lwjglKeyToUltralight(key))
                .keyIdentifier(UlKeyEvent.keyIdentifierFromVirtualKeyCode(builder.virtualKeyCode))
                .modifiers(UltralightKeyMapper.lwjglModifiersToUltralight());

        // Fire the key event
        this.fireKeyEvent(builder.build());

        // Manually synthesize enter and tab
        if (builder.type == UlKeyEventType.DOWN && (key == Keyboard.KEY_RETURN || key == Keyboard.KEY_TAB)) {
            this.fireKeyEvent(UltralightKeyEventBuilder.character()
                    .unmodifiedText(key == Keyboard.KEY_RETURN ? "\n" : "\t")
                    .text(key == Keyboard.KEY_RETURN ? "\n" : "\t"));
        }

        // Manually synthesize reload
        if (builder.type == UlKeyEventType.DOWN && (key == Keyboard.KEY_R && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) && !Keyboard.isRepeatEvent()) {
            UltraManager.getLogger().debug("Reloading page");
            this.reload();
        }
    }

    /**
     * Checks if the character is a special character.
     *
     * @param c the character to check
     * @return true if the character is a special character, false otherwise
     */
    private boolean isSpecialCharacter(char c) {
        return c == '-' || c == '*' || c == '`' || c == '/' || c == '+' ||
                c == ' ' || c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^' ||
                c == '&' || c == ')' || c == '(' || c == '_' || c == '=' || c == '{' || c == '}' ||
                c == '[' || c == ']' || c == ':' || c == ';' || c == '\'' || c == '"' || c == '\\' ||
                c == '|' || c == '<' || c == '>' || c == '?' || c == '؟' || (c >= 'À' && c <= 'ÿ') ||
                c == '»' || c == '«' || c == 'ـ' || c == '~' || c == 'ً' || c == 'ٌ' || c == 'ٍ' ||
                c == '،' || c == '؛' || c == ',' || c == 'ّ' || c == 'ۀ' || c == 'آ' || c == 'َ' ||
                c == 'ُ' || c == 'ِ' || c == 'ة' || c == 'ي' || c == 'ژ' || c == 'إ' || c == 'أ' ||
                c == 'ء' || c == 'ؤ';
    }

    /**
     * Destroys the object by stopping the view and unfocusing it.
     */

    public void destroy() {
        this.stop();
        this.unfocus();
    }

    /**
     * Sets the device scale for the view.
     *
     * @param scale the scale value to set
     */
    public void setDeviceScale(double scale) {
        this.scale = scale;
//        this.setDeviceScale(scale);
        try {
            String transformOrigin = "left top";
            String transform = "scale(" + this.scale + ")";

            this.evaluateScript("document.body.style.transformOrigin = \"" + transformOrigin + "\"");
            this.evaluateScript("document.body.style.transform = \"" + transform + "\"");
        } catch (JavaScriptException e) {
            UltraManager.getLogger().throwing(e);
        }
    }


}