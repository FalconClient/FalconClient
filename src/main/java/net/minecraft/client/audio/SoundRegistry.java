package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;

public class SoundRegistry extends RegistrySimple<ResourceLocation, SoundEventAccessorComposite>
{
    private Map<ResourceLocation, SoundEventAccessorComposite> soundRegistry;

    protected Map<ResourceLocation, SoundEventAccessorComposite> createUnderlyingMap()
    {
        this.soundRegistry = Maps.<ResourceLocation, SoundEventAccessorComposite>newHashMap();
        return this.soundRegistry;
    }

    public void registerSound(SoundEventAccessorComposite composite)
    {
        this.putObject(composite.getSoundEventLocation(), composite);
    }

    /**
     * Reset the underlying sound map (Called on resource manager reload)
     */
    public void clearMap()
    {
        this.soundRegistry.clear();
    }
}
