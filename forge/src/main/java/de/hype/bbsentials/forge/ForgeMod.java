package de.hype.bbsentials.forge;

import de.hype.bbsentials.client.common.client.BBsentials;
import de.hype.bbsentials.client.common.mclibraries.EnvironmentCore;
import de.hype.bbsentials.client.common.mclibraries.TextUtils;
import de.hype.bbsentials.forge.client.MoulConfig;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod(modid = "bbsentials", useMetadata = true)
public class ForgeMod {
    static boolean alreadyInialised = false;
    static BBsentials sentials;
    public static MoulConfig config = new MoulConfig();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        printLocation();
        EnvironmentCore core = EnvironmentCore.forge(new Utils(), new MCEvents(), new ForgeChat(), new Commands(), new Options(), new DebugThread(), new TextUtils() {
            @Override
            public String getContentFromJson(String json) {
                return IChatComponent.Serializer.jsonToComponent(json).getUnformattedText();
            }

            @Override
            public String getJsonFromContent(String content) {
                return IChatComponent.Serializer.componentToJson(new IChatComponent("sdd"));
            }
        });
        MinecraftForge.EVENT_BUS.register(this);
        sentials = new BBsentials();
        BBsentials.init();
    }
    public void printLocation() {
//        try {
//            // Get the URL of the JAR file containing the class
//            URL jarUrl = this.getClass().getProtectionDomain().getCodeSource().getLocation();
//
//            // Convert the URL to a URI
//            File jarFile = new File(jarUrl.toURI());
//
//            // Get the absolute path of the JAR file
//            String jarPath = jarFile.getAbsolutePath();
//
////            throw new RuntimeException(jarPath);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
    }
    @SubscribeEvent
    public void onClientConnected(PlayerEvent.PlayerRespawnEvent event) {
        BBsentials.onServerJoin();
    }

}

