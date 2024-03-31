package ir.albino.client.features.modules.impl.miscellaneous;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.CameraRotateEvent;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;

@ModuleInfo(module = "NoHurtCam", description = "Disables the camera shaking when damaged", version = "1.0", category = ModuleInfo.Category.MISCELLANEOUS, draggable = true)
public class NoHurtCamModule extends Module {
    @Listener
    public void onRender2D(Render2DEvent e) {
        mc.fontRendererObj.drawStringWithShadow("NoHurtCam", x, y, theme.color);
    }

    @Listener
    public void onCameraRotate(CameraRotateEvent e) {
        if (e.reason == CameraRotateEvent.Reason.DAMAGE) e.setCancelled(true);
    }
}
