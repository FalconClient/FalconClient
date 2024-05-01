package ir.albino.client.features.discord;

public interface DiscordState {
    default String getState() {
        return "In " + getClass().getSimpleName().replaceAll("Gui", "");
    }
}
