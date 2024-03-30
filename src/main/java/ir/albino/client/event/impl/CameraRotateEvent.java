package ir.albino.client.event.impl;

import ir.albino.client.event.type.CancellableEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CameraRotateEvent extends CancellableEvent {
    public Reason reason;
    public enum Reason {
        DAMAGE,
        PLAYER_CONTROL
    }

}
