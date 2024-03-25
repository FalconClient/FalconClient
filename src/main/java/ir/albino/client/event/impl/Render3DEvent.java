package ir.albino.client.event.impl;


import ir.albino.client.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Render3DEvent extends Event {
    private float partialTicks;
}