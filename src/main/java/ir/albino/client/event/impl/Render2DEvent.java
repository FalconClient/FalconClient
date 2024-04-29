package ir.albino.client.event.impl;


import ir.albino.client.event.Event;
import lombok.AllArgsConstructor;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

@AllArgsConstructor
public class Render2DEvent extends Event {
    public ScaledResolution resolution;

    public Gui screen;
}