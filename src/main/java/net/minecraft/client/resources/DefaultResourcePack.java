package net.minecraft.client.resources;

import com.google.common.collect.ImmutableSet;
import lombok.SneakyThrows;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import net.optifine.reflect.ReflectorForge;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;
import java.util.Set;

public class DefaultResourcePack implements IResourcePack
{
    public static final Set<String> defaultResourceDomains = ImmutableSet.of("minecraft", "realms");
    private final Map<String, File> mapAssets;

    public DefaultResourcePack(final Map<String, File> mapAssetsIn)
    {
        this.mapAssets = mapAssetsIn;
    }

    public InputStream getInputStream(final ResourceLocation location) throws IOException
    {
        final InputStream inputstream = this.getResourceStream(location);
        final InputStream inputstream1 = this.getInputStreamAssets(location);

        return inputstream != null ? inputstream : inputstream1;
    }

    public InputStream getInputStreamAssets(final ResourceLocation location) throws IOException {
        final File file1 = this.mapAssets.get(location.toString());
        return file1 != null && file1.isFile() ? new FileInputStream(file1) : null;
    }

    private InputStream getResourceStream(final ResourceLocation location)
    {
        final String s = "/assets/" + location.getResourceDomain() + "/" + location.getResourcePath();
        final InputStream inputstream = ReflectorForge.getOptiFineResourceStream(s);
        return inputstream != null ? inputstream : DefaultResourcePack.class.getResourceAsStream(s);
    }

    public boolean resourceExists(final ResourceLocation location)
    {
        return this.getResourceStream(location) != null || this.mapAssets.containsKey(location.toString());
    }

    public Set<String> getResourceDomains()
    {
        return defaultResourceDomains;
    }

    @SneakyThrows
    public <T extends IMetadataSection> T getPackMetadata(final IMetadataSerializer metadataSerializer, final String metadataSectionName) {
        final InputStream inputstream = new FileInputStream(this.mapAssets.get("pack.mcmeta"));
        return AbstractResourcePack.readMetadata(metadataSerializer, inputstream, metadataSectionName);
    }

    public BufferedImage getPackImage() throws IOException
    {
        return TextureUtil.readBufferedImage(DefaultResourcePack.class.getResourceAsStream("/" + (new ResourceLocation("pack.png")).getResourcePath()));
    }

    public String getPackName()
    {
        return "Default";
    }
}
