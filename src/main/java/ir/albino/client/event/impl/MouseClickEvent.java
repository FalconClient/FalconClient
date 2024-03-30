package ir.albino.client.event.impl;

import ir.albino.client.event.type.CancellableEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MouseClickEvent extends CancellableEvent {
    public int x;
    public int y;
    public int key;
}
