package dev.slangware.ultralight.bridge;

import dev.slangware.ultralight.UltraManager;
import net.janrupf.ujr.api.clipboard.UltralightClipboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class LwjglClipboardBridge implements UltralightClipboard {
    private final Clipboard clipboard;

    public LwjglClipboardBridge() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }


    /**
     * Clears the contents of the clipboard.
     */
    @Override
    public void clear() {
        // Create a StringSelection object with an empty string
        StringSelection stringSelection = new StringSelection("");
        // Set the clipboard contents to the empty string selection
        this.clipboard.setContents(stringSelection, null);
    }

    /**
     * Reads the plain text data from the clipboard.
     *
     * @return the plain text data from the clipboard, or an empty string if no data is available.
     */
    @Override
    public String readPlainText() {
        try {
            // Check if string flavor data is available in the clipboard
            if (this.clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                // Retrieve and return the plain text data from the clipboard
                return (String) this.clipboard.getData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            UltraManager.getLogger().throwing(e);
        }
        // Return an empty string if no data is available
        return "";
    }

    /**
     * Writes the given text to the clipboard as plain text.
     *
     * @param text The text to be written to the clipboard.
     */
    @Override
    public void writePlainText(String text) {
        // Create a StringSelection object with the given text
        StringSelection stringSelection = new StringSelection(text);

        // Set the contents of the clipboard to the StringSelection object
        this.clipboard.setContents(stringSelection, null);
    }
}
