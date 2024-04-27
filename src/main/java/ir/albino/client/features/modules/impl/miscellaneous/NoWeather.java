package ir.albino.client.features.modules.impl.miscellaneous;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.event.impl.WeatherChangeEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import net.optifine.config.Weather;

@ModuleInfo(module = "NoWeather", draggable = true, description = "Disables weather for client side", category = ModuleInfo.Category.MISCELLANEOUS, version = "1.0")
public class NoWeather extends Module {
    @Listener
    public void onRender2D(Render2DEvent e) {
        mc.fontRendererObj.drawStringWithShadow("No Weather", x, y, theme.color);
    }

    @Listener
    public void onWeatherChange(WeatherChangeEvent e) {
        System.out.println(e.weather);
        if (e.weather != Weather.CLEAR) e.setCancelled(true);
    }
}
