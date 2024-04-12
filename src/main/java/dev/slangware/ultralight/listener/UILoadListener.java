package dev.slangware.ultralight.listener;


import dev.slangware.ultralight.UltraManager;
import net.janrupf.ujr.api.UltralightView;
import net.janrupf.ujr.api.listener.UltralightLoadListener;

public class UILoadListener implements UltralightLoadListener {
    /**
     * Returns the name of a frame based on the frame ID, whether it is the main frame, and the URL.
     *
     * @param frameId   the ID of the frame
     * @param mainframe indicates whether the frame is the main frame
     * @param url       the URL of the frame
     * @return the formatted name of the frame
     */

    private String frameName(long frameId, boolean mainframe, String url) {
        String frameType = mainframe ? "MainFrame" : "Frame";
        return String.format("[%s %d (%s)]: ", frameType, frameId, url);
    }

    /**
     * A description of the entire Java function.
     *
     * @param view        description of parameter
     * @param frameId     description of parameter
     * @param isMainFrame description of parameter
     * @param url         description of parameter
     */
    @Override
    public void onBeginLoading(UltralightView view, long frameId, boolean isMainFrame, String url) {
        UltraManager.getLogger().info(frameName(frameId, isMainFrame, url) + "The view is about to load");
    }

    /**
     * A description of the onFinishLoading method.
     *
     * @param view        the UltralightView object
     * @param frameId     the ID of the frame
     * @param isMainFrame indicates if the frame is the main frame
     * @param url         the URL of the loaded page
     */
    @Override
    public void onFinishLoading(UltralightView view, long frameId, boolean isMainFrame, String url) {
        UltraManager.getLogger().info(frameName(frameId, isMainFrame, url) + "The view finished loading");
    }


    /**
     * Called when loading fails.
     *
     * @param view        the UltralightView instance
     * @param frameId     the ID of the frame being loaded
     * @param isMainFrame indicates if the frame being loaded is the main frame
     * @param url         the URL being loaded
     * @param description the error description
     * @param errorDomain the error domain
     * @param errorCode   the error code
     */
    @Override
    public void onFailLoading(UltralightView view, long frameId, boolean isMainFrame, String url, String description, String errorDomain, int errorCode) {
        String errorMessage = String.format("%s Failed to load %s, %d(%s)", frameName(frameId, isMainFrame, url), errorDomain, errorCode, description);
        UltraManager.getLogger().error(errorMessage);

    }

    /**
     * A description of the entire Java function.
     *
     * @param view the UltralightView object
     */
    @Override
    public void onUpdateHistory(UltralightView view) {
        UltraManager.getLogger().info("The view has updated the history");

    }


    /**
     * A description of the entire Java function.
     *
     * @param view        description of parameter
     * @param frameId     description of parameter
     * @param isMainFrame description of parameter
     * @param url         description of parameter
     */
    @Override
    public void onWindowObjectReady(UltralightView view, long frameId, boolean isMainFrame, String url) {

    }

    /**
     * A description of the entire Java function.
     *
     * @param view        description of parameter
     * @param frameId     description of parameter
     * @param isMainFrame description of parameter
     * @param url         description of parameter
     */
    @Override
    public void onDOMReady(UltralightView view, long frameId, boolean isMainFrame, String url) {

    }
}