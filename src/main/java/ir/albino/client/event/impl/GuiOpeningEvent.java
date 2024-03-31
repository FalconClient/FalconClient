package ir.albino.client.event.impl;

import ir.albino.client.event.type.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;

@Getter
@Setter
@AllArgsConstructor
public class GuiOpeningEvent extends CancellableEvent {
    private GuiScreen gui;
}
