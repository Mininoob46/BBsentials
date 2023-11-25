package de.hype.bbsentials.client.common.mclibraries;

import de.hype.bbsentials.shared.constants.Islands;

import java.io.File;
import java.util.List;

public interface Utils {
    Islands getCurrentIsland();

    int getPlayerCount();

    String getServerId();

    boolean isOnMegaServer();

    boolean isOnMiniServer();

    int getMaximumPlayerCount();

    long getLobbyTime();

    default int getLobbyDay() {
        return (int) (getLobbyTime() / 24000);
    }

    List<String> getPlayers();

    boolean isWindowFocused();

    File getConfigPath();

    String getUsername();

    String getMCUUID();

    void playsound(String eventName);

    int getPotTime();

    String mojangAuth(String serverId);

    // Leechers was originally inveneted by Calva but redone by me without access to the code, I made it since Calvas mod was private at that date
    List<String> getSplashLeechingPlayers();

}