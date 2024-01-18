package de.hype.bbsentials.client.common.objects;

import de.hype.bbsentials.client.common.api.Formatting;
import de.hype.bbsentials.client.common.client.BBsentials;
import de.hype.bbsentials.client.common.client.objects.ServerSwitchTask;
import de.hype.bbsentials.client.common.mclibraries.EnvironmentCore;
import de.hype.bbsentials.shared.objects.Position;
import de.hype.bbsentials.shared.objects.WaypointData;

import java.util.HashMap;
import java.util.Map;

public class Waypoints extends WaypointData {
    public static int counter = 0;
    public static Map<Integer, Waypoints> waypoints = new HashMap<>();
    int removeRunnableId;
    int waypointId;

    public Waypoints(Position pos, String jsonTextToRender, int renderDistance, boolean visible, boolean deleteOnServerSwap, String textureNameSpace, String texturePath) {
        super(pos, jsonTextToRender, renderDistance, visible, deleteOnServerSwap, textureNameSpace, texturePath);
        ServerSwitchTask.onServerLeaveTask(() -> {
            if (this.deleteOnServerSwap)
                this.removeFromPool();
        });
        waypoints.put(waypointId, this);
    }

    public Waypoints removeFromPool() {
        BBsentials.onServerLeave.remove(removeRunnableId);
        return waypoints.remove(waypointId);
    }

    public String getMinimalInfoString() {
        String unformatedName;
        try {
            unformatedName = EnvironmentCore.utils.getStringFromTextJson(jsonToRenderText);
        } catch (Exception e) {
            unformatedName = Formatting.RED + "Invalid Json Name";
        }
        return "ID: " + getWaypointId() + " | Name: " + unformatedName + "§r | Coords: " + position.toString();
    }

    public int getWaypointId() {
        return waypointId;
    }

    public String getFullInfoString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        try {
            builder.append(EnvironmentCore.utils.getStringFromTextJson(jsonToRenderText) + "§r\n");
        } catch (Exception e) {
            builder.append(Formatting.RED + "Invalid Json Name§r\n");
        }
        builder.append("Coords: " + position.toString() + "\n");
        builder.append("Visible: " + visible + "\n");
        builder.append("Deleted on Server Swap: " + deleteOnServerSwap + "\n");
        builder.append("Maximum Render Distance: " + renderDistance + "\n");
        String customTexture = textureNameSpace + ":" + texturePath;
        if (!customTexture.equals("null:null")) builder.append("Custom Texture: " + customTexture);
        return builder.toString();
    }
}
