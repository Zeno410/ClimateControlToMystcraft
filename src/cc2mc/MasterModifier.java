
package cc2mc;

import climateControl.api.ClimateControlSettings;
import climateControl.api.DimensionalSettingsModifier;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Unload;

/**
 *
 * @author Zeno410
 */
public class MasterModifier extends DimensionalSettingsModifier {

    public static Logger logger = new Zeno410Logger("MasterModifier").logger();

    HashMap<Integer,ArrayList<ClimateControlModifierParameterizer.Parameterized>> dimensionalModifiers=
            new HashMap<Integer,ArrayList<ClimateControlModifierParameterizer.Parameterized>>();
            
    public void set(int dimension,ArrayList<ClimateControlModifierParameterizer.Parameterized> modifiers) {
        logger.info("dimension "+ dimension+ " modifier count "+modifiers.size());

        dimensionalModifiers.put(dimension, modifiers);
    }

    public boolean signalCCActive(int dimension){
        // signal CC to modify this dimension if we're doing something
        ArrayList<ClimateControlModifierParameterizer.Parameterized> targets = dimensionalModifiers.get(dimension);
        return targets != null;
    }
    
    @Override
    public void modify(int dimension, ClimateControlSettings settings) {
        ArrayList<ClimateControlModifierParameterizer.Parameterized> targets = dimensionalModifiers.get(dimension);
        if (targets != null) {
            for (ClimateControlModifierParameterizer.Parameterized target: targets) {
                target.change(settings);
                logger.info("snowy "+settings.snowyIncidence.value());
                logger.info("cool "+settings.coolIncidence.value());
                logger.info("warm "+settings.warmIncidence.value());
            }
        }
    }

    @Override
    public void onWorldLoad(Load arg0) {
        // no action needed;
    }

    @Override
    public void unloadWorld(Unload arg0) {
        // may need to do something
    }

    @Override
    public void serverStarted(FMLServerStartedEvent arg0) {
        // no action needed;
    }

    @Override
    public void serverStopped(FMLServerStoppedEvent arg0) {
        // clear modifiers; don't apply to another world
        dimensionalModifiers=new HashMap<Integer,ArrayList<ClimateControlSettingsModifier.Parameterized>>();
    }

}
