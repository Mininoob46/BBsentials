package de.hype.bbsentials.shared.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface Collections extends MinionResourceItem {
    Set<Collections> values = values();

    static Set<Collections> values() {
        Set<Collections> collections = new HashSet<>();
        collections.addAll(Arrays.stream(Farming.values()).toList());
        collections.addAll(Arrays.stream(Foraging.values()).toList());
        collections.addAll(Arrays.stream(Mining.values()).toList());
        collections.addAll(Arrays.stream(Fishing.values()).toList());
        collections.addAll(Arrays.stream(Combat.values()).toList());
        return collections;
    }

    static String getCodeReference(String goalName) {
        goalName = goalName.replace("_", " ");
        for (Collections value : values()) {
            if (value.getDisplayName().equalsIgnoreCase(goalName)) {
                return value.getClass().getName().replaceAll("\\$[0-9]+", "").replace("$", ".").replace("de.hype.bbsentials.shared.constants.", "") + "." + value.toString();
            }
        }
        return null;
    }

    String getId();

    int getTierCount();

    int getTierWithCollection(int amount);

    int getCollectionForTier(int tier);

    default Integer getCompactorLevel() {
        return 1;
    }

    @Override
    default String getDisplayName() {
        return this.toString().replace("_", " ");
    }

    default Integer getSuperCompactorLevel() {
        return 160;
    }

    String getMinionID();

    enum Farming implements Collections {
        Cocoa_Beans("INK_SACK:3", "COCOA_GENERATOR_1", 75, 200, 500, 2000, 5000, 10000, 20000, 50000, 100000),
        Carrot("CARROT_ITEM", "CARROT_GENERATOR_1", 100, 250, 500, 1750, 5000, 10000, 25000, 50000, 100000),
        Cactus("CACTUS", "CACTUS_GENERATOR_1", 100, 250, 500, 1000, 2500, 5000, 10000, 25000, 50000),
        Raw_Chicken("RAW_CHICKEN", "CHICKEN_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000),
        Sugar_Cane("SUGAR_CANE", "SUGAR_CANE_GENERATOR_1", 100, 250, 500, 1000, 2000, 5000, 10000, 20000, 50000),
        Pumpkin("PUMPKIN", "PUMPKIN_GENERATOR_1", 40, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000, 250000),
        Wheat("WHEAT", "WHEAT_GENERATOR_1", 50, 100, 250, 500, 1000, 2500, 10000, 15000, 25000, 50000, 100000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }

            @Override
            public Integer getSuperCompactorLevel() {
                return 1296;
            }
        },
        Seeds("SEEDS", "SEEDS_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 25000),
        Mushroom("MUSHROOM_COLLECTION", "MUSHROOM_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Raw_Rabbit("RABBIT", "RABBIT_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000),
        Nether_Wart("NETHER_STALK", "NETHER_WARTS_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 75000, 100000, 250000),
        Mutton("MUTTON", "SHEEP_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000),
        Melon("MELON", "MELON_GENERATOR_1", 250, 500, 1250, 5000, 15000, 25000, 50000, 100000, 250000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Potato("POTATO_ITEM", "POTATO_GENERATOR_1", 100, 200, 500, 1750, 5000, 10000, 25000, 50000, 100000),
        Leather("LEATHER", "COW_GENERATOR_1", 50, 100, 250, 500, 1000, 2500, 5000, 10000, 25000, 50000, 100000) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 576;
            }
        },
        Raw_Porkchop("PORK", "PIG_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000),
        Feather("FEATHER", "CHICKEN_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000),

        ;
        public final String id;
        public final String minionID;
        public final int[] tiers;

        Farming(String id, String minionID, int... tiers) {
            this.id = id;
            this.minionID = minionID;
            this.tiers = tiers;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getTierCount() {
            return tiers.length;
        }

        @Override
        public int getTierWithCollection(int amount) {
            for (int i = this.tiers.length - 1; i >= 0; i--) {
                if (tiers[i] >= amount) return i;
            }
            return 0;
        }

        @Override
        public int getCollectionForTier(int tier) {
            return tiers[Math.min(tier, tiers.length - 1)];
        }

        @Override
        public String getMinionID() {
            return minionID;
        }
    }

    enum Mining implements Collections {
        Lapis_Lazuli("INK_SACK:4", "LAPIS_GENERATOR_1", 250, 500, 1000, 2000, 10000, 25000, 50000, 100000, 150000, 250000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Redstone("REDSTONE", "REDSTONE_GENERATOR_1", 100, 250, 750, 1500, 3000, 5000, 10000, 25000, 50000, 200000, 400000, 600000, 800000, 1000000, 1200000, 1400000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Umber("UMBER", null, 1000, 2500, 10000, 25000, 100000, 250000, 500000, 750000, 1000000),
        Coal("COAL", "COAL_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Mycelium("MYCEL", "MYCELIUM_GENERATOR_1", 50, 500, 750, 1000, 2500, 10000, 15000, 25000, 50000, 100000),
        End_Stone("ENDER_STONE", "ENDER_STONE_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 15000, 25000, 50000),
        Nether_Quartz("QUARTZ", "QUARTZ_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000) {
            @Override
            public Integer getCompactorLevel() {
                return 4;
            }
        },
        Sand("SAND", "SAND_GENERATOR_1", 50, 100, 250, 500, 1000, 2500, 5000),
        Iron_Ingot("IRON_INGOT", "IRON_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000, 200000, 400000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Gemstone("GEMSTONE_COLLECTION", null, 100, 250, 1000, 2500, 5000, 25000, 100000, 250000, 500000, 1000000, 2000000) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 80;
            }
        },
        Tungsten("TUNGSTEN", null, 1000, 2500, 10000, 25000, 100000, 250000, 500000, 750000, 1000000),
        Obsidian("OBSIDIAN", "OBSIDIAN_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000),
        Diamond("DIAMOND", "DIAMOND_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Cobblestone("COBBLESTONE", "COBBLESTONE_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 40000, 70000),
        Glowstone_Dust("GLOWSTONE_DUST", "GLOWSTONE_GENERATOR_1", 50, 100, 1000, 2500, 5000, 10000, 25000) {
            @Override
            public Integer getCompactorLevel() {
                return 4;
            }
        },
        Gold_Ingot("GOLD_INGOT", "GOLD_GENERATOR_1", 50, 100, 250, 500, 1000, 2500, 5000, 10000, 25000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Gravel("GRAVEL", "GRAVEL_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 15000, 50000),
        Hard_Stone("HARD_STONE", "HARD_STONE_GENERATOR_1", 50, 1000, 5000, 50000, 150000, 300000, 1000000) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 576;
            }
        },
        Mithril("MITHRIL_ORE", "MITHRIL_GENERATOR_1", 50, 250, 1000, 2500, 5000, 10000, 250000, 500000, 1000000),
        Emerald("EMERALD", "EMERALD_GENERATOR_1", 50, 100, 250, 1000, 5000, 15000, 30000, 50000, 100000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Red_Sand("SAND:1", "RED_SAND_GENERATOR_1", 50, 500, 2500, 10000, 15000, 25000, 50000, 100000),
        Ice("ICE", "ICE_GENERATOR_1", 50, 100, 250, 500, 1000, 5000, 10000, 50000, 100000, 250000, 500000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Glacite("GLACITE", null, 1000, 2500, 10000, 25000, 100000, 250000, 500000, 750000, 1000000),
        Sulphur("SULPHUR_ORE", null, 200, 1000, 2500, 5000, 10000, 15000, 25000, 50000, 100000),
        Netherrack("NETHERRACK", null, 50, 250, 500, 1000, 5000),

        ;
        public final String id;
        public final String minionID;
        public final int[] tiers;

        Mining(String id, String minionID, int... tiers) {
            this.id = id;
            this.minionID = minionID;
            this.tiers = tiers;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getTierCount() {
            return tiers.length;
        }

        @Override
        public int getTierWithCollection(int amount) {
            for (int i = this.tiers.length - 1; i >= 0; i--) {
                if (tiers[i] >= amount) return i;
            }
            return 0;
        }

        @Override
        public int getCollectionForTier(int tier) {
            return tiers[Math.min(tier, tiers.length - 1)];
        }

        @Override
        public String getMinionID() {
            return minionID;
        }
    }

    enum Combat implements Collections {
        Ender_Pearl("ENDER_PEARL", "ENDERMAN_GENERATOR_1", 50, 250, 1000, 2500, 5000, 10000, 15000, 25000, 50000) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 20;
            }
        },
        Chili_Pepper("CHILI_PEPPER", null, 10, 25, 75, 250, 1000, 2500, 5000, 10000, 20000),
        Slimeball("SLIME_BALL", "SLIME_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000) {
            @Override
            public Integer getCompactorLevel() {
                return 9;
            }
        },
        Magma_Cream("MAGMA_CREAM", "MAGMA_CUBE_GENERATOR_1", 50, 250, 1000, 2500, 5000, 10000, 25000, 50000),
        Ghast_Tear("GHAST_TEAR", "GHAST_GENERATOR_1", 20, 250, 1000, 2500, 5000, 10000, 25000) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 5;
            }
        },
        Gunpowder("SULPHUR", "CREEPER_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000),
        Rotten_Flesh("ROTTEN_FLESH", "ZOMBIE_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000),
        Spider_Eye("SPIDER_EYE", "SPIDER_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000),
        Bone("BONE", "SKELETON_GENERATOR_1", 50, 100, 250, 500, 1000, 5000, 10000, 25000, 50000, 150000),
        Blaze_Rod("BLAZE_ROD", "BLAZE_GENERATOR_1", 50, 250, 1000, 2500, 5000, 10000, 25000, 50000),
        String("STRING", "TARANTULA_GENERATOR_1", 50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000),

        ;
        public final String id;
        public final String minionID;
        public final int[] tiers;

        Combat(String id, String minionID, int... tiers) {
            this.id = id;
            this.minionID = minionID;
            this.tiers = tiers;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getTierCount() {
            return tiers.length;
        }

        @Override
        public int getTierWithCollection(int amount) {
            for (int i = this.tiers.length - 1; i >= 0; i--) {
                if (tiers[i] >= amount) return i;
            }
            return 0;
        }

        @Override
        public int getCollectionForTier(int tier) {
            return tiers[Math.min(tier, tiers.length - 1)];
        }

        @Override
        public String getMinionID() {
            return minionID;
        }
    }

    enum Foraging implements Collections {
        Acacia_Wood("LOG_2", "ACACIA_GENERATOR_1", 50, 100, 250, 500, 1000, 2000, 5000, 10000, 25000),
        Spruce_Wood("LOG:1", "SPRUCE_GENERATOR_1", 50, 100, 250, 1000, 2000, 5000, 10000, 25000, 50000),
        Jungle_Wood("LOG:3", "JUNGLE_GENERATOR_1", 50, 100, 250, 500, 1000, 2000, 5000, 10000, 25000),
        Birch_Wood("LOG:2", "BIRCH_GENERATOR_1", 50, 100, 250, 500, 1000, 2000, 5000, 10000, 25000, 50000),
        Oak_Wood("LOG", "OAK_GENERATOR_1", 50, 100, 250, 500, 1000, 2000, 5000, 10000, 30000),
        Dark_Oak_Wood("LOG_2:1", "DARK_OAK_GENERATOR_1", 50, 100, 250, 1000, 2000, 5000, 10000, 15000, 25000),

        ;
        public final String id;
        public final String minionID;
        public final int[] tiers;

        Foraging(String id, String minionID, int... tiers) {
            this.id = id;
            this.minionID = minionID;
            this.tiers = tiers;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getTierCount() {
            return tiers.length;
        }

        @Override
        public int getTierWithCollection(int amount) {
            for (int i = this.tiers.length - 1; i >= 0; i--) {
                if (tiers[i] >= amount) return i;
            }
            return 0;
        }

        @Override
        public int getCollectionForTier(int tier) {
            return tiers[Math.min(tier, tiers.length - 1)];
        }

        @Override
        public String getMinionID() {
            return minionID;
        }
    }

    enum Fishing implements Collections {
        Lily_Pad("WATER_LILY", null, 10, 50, 100, 200, 500, 1500, 3000, 6000, 10000),
        Prismarine_Shard("PRISMARINE_SHARD", "FISHING_GENERATOR_1", 10, 25, 50, 100, 200, 400, 800) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 80;
            }
        },
        Ink_Sac("INK_SACK", null, 20, 40, 100, 200, 400, 800, 1500, 2500, 4000),
        Raw_Fish("RAW_FISH", "FISHING_GENERATOR_1", 20, 50, 100, 250, 500, 1000, 2500, 15000, 30000, 45000, 60000),
        Clownfish("RAW_FISH:2", "FISHING_GENERATOR_1", 10, 25, 50, 100, 200, 400, 800, 1600, 4000),
        Raw_Salmon("RAW_FISH:1", "FISHING_GENERATOR_1", 20, 50, 100, 250, 500, 1000, 2500, 5000, 10000),
        Magmafish("MAGMA_FISH", null, 20, 100, 500, 1000, 5000, 15000, 30000, 50000, 75000, 100000, 250000, 500000),
        Prismarine_Crystals("PRISMARINE_CRYSTALS", "FISHING_GENERATOR_1", 10, 25, 50, 100, 200, 400, 800) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 80;
            }
        },
        Clay("CLAY_BALL", "CLAY_GENERATOR_1", 50, 100, 250, 1000, 1500, 2500) {
            @Override
            public Integer getCompactorLevel() {
                return 4;
            }
        },
        Sponge("SPONGE", "FISHING_GENERATOR_1", 20, 40, 100, 200, 400, 800, 1500, 2500, 4000) {
            @Override
            public Integer getSuperCompactorLevel() {
                return 40;
            }
        },

        ;
        public final String id;
        public final String minionID;
        public final int[] tiers;

        Fishing(String id, String minionID, int... tiers) {
            this.id = id;
            this.minionID = minionID;
            this.tiers = tiers;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getTierCount() {
            return tiers.length;
        }

        @Override
        public int getTierWithCollection(int amount) {
            for (int i = this.tiers.length - 1; i >= 0; i--) {
                if (tiers[i] >= amount) return i;
            }
            return 0;
        }

        @Override
        public int getCollectionForTier(int tier) {
            return tiers[Math.min(tier, tiers.length - 1)];
        }

        @Override
        public String getMinionID() {
            return minionID;
        }
    }

    enum Rift implements Collections {
        Wilted_Berberis("WILTED_BERBERIS", 20, 60, 140, 400),
        Living_Metal_Heart("METAL_HEART", 1, 20, 60, 100),
        Caducous_Stem("CADUCOUS_STEM", 20, 60, 150, 500),
        Agaricus_Cap("AGARICUS_CAP", 20, 60, 100, 200),
        Hemovibe("HEMOVIBE", 50, 250, 1000, 5000, 15000, 30000, 80000, 150000, 400000),
        Half_Eaten_Carrot("HALF_EATEN_CARROT", 1, 400, 1000, 3500),

        ;
        public final String id;
        public final int[] tiers;

        Rift(String id, int... tiers) {
            this.id = id;
            this.tiers = tiers;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getTierCount() {
            return tiers.length;
        }

        @Override
        public int getTierWithCollection(int amount) {
            for (int i = this.tiers.length - 1; i >= 0; i--) {
                if (tiers[i] >= amount) return i;
            }
            return 0;
        }

        @Override
        public int getCollectionForTier(int tier) {
            return tiers[Math.min(tier, tiers.length - 1)];
        }

        @Override
        public Integer getSuperCompactorLevel() {
            return 1;
        }

        @Override
        public String getMinionID() {
            return null;
        }
    }


}
