package ir.albino.client.features.modules.impl.miscellaneous;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.CameraRotateEvent;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import ir.albino.client.features.modules.settings.ModuleSetting;
import ir.albino.client.features.modules.settings.Setting;

import java.util.ArrayList;
import java.util.List;

@ModuleInfo(module = "NoHurtCam", description = "Disables the camera shaking when damaged", version = "1.0", category = ModuleInfo.Category.MISCELLANEOUS, draggable = true)
public class NoHurtCam extends Module {
    private List<CameraRotateEvent.Reason> reasonList = new ArrayList<>();

    @Listener
    public void onRender2D(Render2DEvent e) {
        mc.fontRendererObj.drawStringWithShadow("NoHurtCam", x, y, theme.color);
    }

    @Setting
    public ModuleSetting<List<CameraRotateEvent.Reason>> reasons = new ModuleSetting<>("CameraMovement reasons ", this::getReasons, this::setReasons);

    private void setReasons(List<CameraRotateEvent.Reason> reasons) {
        this.reasonList = reasons;
    }

    @Listener
    public void onCameraRotate(CameraRotateEvent e) {
        e.setCancelled(true);

    }

    public List<CameraRotateEvent.Reason> getReasons() {
        return reasonList;
    }
}
