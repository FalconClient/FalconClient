package ir.albino.client.event.impl;

import ir.albino.client.event.type.CancellableEvent;
import lombok.AllArgsConstructor;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.config.Weather;

@AllArgsConstructor
public class WeatherChangeEvent extends CancellableEvent {
    public Weather weather;
}
