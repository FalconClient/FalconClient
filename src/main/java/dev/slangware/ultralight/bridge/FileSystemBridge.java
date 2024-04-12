// UltraLight Reborn - https://github.com/Janrupf/ultralight-java-reborn/tree/main/examples/glfw
package dev.slangware.ultralight.bridge;

import dev.slangware.ultralight.UltraManager;
import net.janrupf.ujr.api.UltralightResources;
import net.janrupf.ujr.api.filesystem.UltralightFilesystem;
import net.janrupf.ujr.api.util.NioUltralightBuffer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileSystemBridge implements UltralightFilesystem {
    public static final String RESOURCE_PREFIX = "$built-in/resources/";


    /**
     * Checks if a file exists.
     *
     * @param path The path of the file to check
     * @return True if the file exists, false otherwise
     */
    @Override
    public boolean fileExists(String path) {
        UltraManager.getLogger().debug("Checking if {} exists", path);

        if (path.startsWith(RESOURCE_PREFIX)) {
            // Built in resource, attempt look up
            String resourcePath = path.substring(RESOURCE_PREFIX.length());
            boolean resourceExists = UltralightResources.getResource(resourcePath) != null;

            // If the resource does not exist, log a warning
            if (!resourceExists) {
                UltraManager.getLogger().warn("Resource {} does not exist", resourcePath);
            }

            return resourceExists;
        }

        return false;
    }


    /**
     * Retrieves the mime type of file given its path.
     *
     * @param path The path to the file.
     * @return The mime type of the file.
     */
    @Override
    public String getFileMimeType(String path) {
        // Log the path for debugging purposes
        UltraManager.getLogger().debug("Retrieving mime type of {}", path);
        // Use the URLConnection class to guess the mime type of the file
        return URLConnection.guessContentTypeFromName(new File(path).getName());
    }

    /**
     * Retrieves the charset of the given file path.
     *
     * @param path The path of the file.
     * @return The charset of the file.
     */
    @Override
    public String getFileCharset(String path) {
        // Log the retrieval of charset for the given file path
        UltraManager.getLogger().debug("Retrieving charset of {}", path);

        // This method will only be called for text files anyway, so it is safe to assume
        // that the file is utf-8 encoded. If your application has more complex needs,
        // you should use a more sophisticated method to determine the charset.
        return "utf-8";
    }


    /**
     * Opens a file for reading.
     *
     * @param path The path of the file to open.
     * @return A NioUltralightBuffer containing the contents of the file.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public NioUltralightBuffer openFile(String path) throws IOException {
        if (path.startsWith(RESOURCE_PREFIX)) {
            // Built-in resource, attempt to look up
            String resourcePath = path.substring(RESOURCE_PREFIX.length());
            URI resource = UltralightResources.getResource(resourcePath);

            // If the resource does not exist, log and throw an I/O exception
            if (resource == null) {
                // This should never happen, as we already checked for the existence of the resource
                // in the fileExists method, but we still check here to be safe
                throw new IOException("Resource " + resourcePath + " does not exist");
            }

            // Open the resource
            URLConnection connection = resource.toURL().openConnection();
            int length = connection.getContentLength();

            if (length != -1) {
                // If the length is known, allocate a buffer with the correct size upfront.
                // We also allocate a direct byte buffer, which will avoid copies later
                ByteBuffer buffer = ByteBuffer.allocateDirect(length);
                try (ReadableByteChannel channel = Channels.newChannel(connection.getInputStream())) {
                    channel.read(buffer);
                }

                // We are done, the resource is in memory
                return new NioUltralightBuffer((ByteBuffer) buffer.flip());
            }

            // Size is not known, so we will have to take the slower path of a buffer
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (InputStream in = connection.getInputStream()) {
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) > 0) {
                    out.write(buffer, 0, read);
                }
            }

            // Wrap the array in a buffer
            //
            // The native code later will have to copy this again as the buffer is not a direct buffer.
            // However, Ultralight Java Reborn handles this detail internally for you.
            return new NioUltralightBuffer(ByteBuffer.wrap(out.toByteArray()));
        }

        // Opening failed.
        //
        // It is fine to return null here, which will effectively be treated the same as an
        // IOException and simply result in the opening failing.
        return null;
    }
}
