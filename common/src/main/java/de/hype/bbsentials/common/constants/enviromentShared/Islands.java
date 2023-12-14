package de.hype.bbsentials.common.constants.enviromentShared;

import de.hype.bbsentials.common.constants.BBDisplayNameProvider;

/**
 * List of all Islands
 * {@link #CRYSTAL_HOLLOWS}
 * {@link #CRIMSON_ISLE}
 * {@link #DEEP_CAVERNS}
 * {@link #DUNGEON}
 * {@link #DUNGEON_HUB}
 * {@link #DWARVEN_MINES}
 * {@link #GOLD_MINE}
 * {@link #HUB}
 * {@link #KUUDRA}
 * {@link #PRIVATE_ISLAND}
 * {@link #SPIDERS_DEN}
 * {@link #THE_END}
 * {@link #THE_FARMING_ISLANDS}
 * {@link #JERRYS_WORKSHOP}
 * {@link #THE_RIFT}
 */
public enum Islands implements BBDisplayNameProvider {
    CRYSTAL_HOLLOWS("crystal_hollows", "Crystal Hollows"),
    CRIMSON_ISLE("crimson_isle", "Crimson Isle"),
    DEEP_CAVERNS("mining_2", "Deep Caverns"),
    DUNGEON("dungeon", "Dungeon"),
    DUNGEON_HUB("dungeon_hub", "Dungeon Hub"),
    DWARVEN_MINES("mining_3", "Dwarven Mines"),
    GOLD_MINE("mining_1", "Gold Mine"),
    HUB("hub", "Hub"),
    KUUDRA("kuudra", "Kuudra"),
    PRIVATE_ISLAND("dynamic", "Private Islands"),
    SPIDERS_DEN("combat_1", "Spider's Den"),
    THE_END("combat_3", "The End"),
    THE_FARMING_ISLANDS("farming_1", "The Farming Islands"),
    JERRYS_WORKSHOP("winter", "Jerry's Workshop"),
    THE_RIFT("rift", "The Rift");


    private final String internalName;
    private final String displayName;

    Islands(String internalName, String displayName) {
        this.internalName = internalName;
        this.displayName = displayName;
    }

    public String getInternalName() {
        return internalName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
