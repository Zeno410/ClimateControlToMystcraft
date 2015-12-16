
package cc2mc;

import climateControl.api.BiomeSettings;
import climateControl.api.ClimateControlSettings;
import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
public class ClimateControlBiomeFrequencyModifier extends ClimateControlModifierParameterizer{

    @Override
    public Parameterized parameterized(AgeControllerAdapter controller) {
        BiomeGenBase biome = controller.popBiome();
        return new Parameterized(new BiomeTweaker(biome), new ClimateControlSymbolParameters(controller));
    }

    private class BiomeTweaker extends ClimateControlSettingsModifier {
        private final BiomeGenBase biome;
        BiomeTweaker(BiomeGenBase biome){
            this.biome = biome;
        }

        public void change(ClimateControlSettings toTweak, ClimateControlSymbolParameters parameters) {
            if (biome == null) return;
            for (BiomeSettings tweaked: toTweak.biomeSettings()) {
                for (BiomeSettings.Element element: tweaked.elements()){
                    // check to see it it's a target biome
                    BiomeGenBase biomeToChange = BiomeGenBase.getBiome(element.biomeID().value());
                    // skip if null for some reason
                    if (biomeToChange == null) continue;
                    // only change target biome;
                    ModifierInstaller.logger.info(biomeToChange.biomeID + " "+biome.biomeID);
                    if (biomeToChange.biomeID != biome.biomeID) continue;
                    parameters.changedOrNegated(element.biomeIncidences());
                    ModifierInstaller.logger.info(biomeToChange.biomeName + " "+element.biomeIncidences());

                }
            }
        }

    }
}
