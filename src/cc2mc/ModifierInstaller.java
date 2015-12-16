
package cc2mc;

import climateControl.api.BiomeSettings;
import climateControl.api.ClimateControlSettings;
import net.minecraftforge.common.BiomeDictionary;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
abstract public class ModifierInstaller {
    public static Logger logger = new Zeno410Logger("ModifierInstaller").logger();
    abstract public void registerSymbol(MystcraftSymbolPlugin symbol, int rank, String [] words);
    final ClimateControlSettingsManager manager = new ClimateControlSettingsManager();
    
    public void install() {

        // master controller

        registerSymbol(new ClimateControlActivationSymbol("ClimateControl",manager),1,
                new String[] { "Terrain", "Entropy", "Tradition", "Encourage" });

        //modifiers
        registerSymbol(new ClimateControlModifier("Increase",ClimateControlSymbolParameters.increase),
                1, new String[] { "Terrain", "Entropy", "Tradition", "Encourage" });
        registerSymbol(new ClimateControlModifier("Decrease",ClimateControlSymbolParameters.decrease),
                1, new String[] { "Terrain", "Entropy", "Tradition", "Sustain" });
        registerSymbol(new ClimateControlModifier("Not",ClimateControlSymbolParameters.no),
                1, new String[] { "Terrain", "Entropy", "Tradition", "Sustain" });
        registerSymbol(new ClimateControlSymbol("BiomeFrequency",manager,new ClimateControlBiomeFrequencyModifier()),
                1, new String[] { "Terrain", "Mutual", "Encourage", "Exist" });

        // detailed controllers
        registerSymbol(new ClimateControlSymbol("ComplexSubBiomes",manager,new ComplexSubBiomes()), 1, new String[] { "Terrain", "Mutual", "Encourage", "Exist" });
        registerSymbol(new ClimateControlSymbol("LargeClimates",manager,largeClimates()), 1, new String[] { "Terrain", "Mutual", "Encourage", "Exist" });
        registerSymbol(new ClimateControlSymbol("MediumClimates",manager,mediumClimates()), 1, new String[] { "Terrain", "Mutual", "Sustain", "Exist" });
        registerSymbol(new ClimateControlSymbol("SmallClimates",manager,smallClimates()), 1, new String[] { "Terrain", "Mutual", "Inhibit", "Exist" });
        registerSymbol(new ClimateControlSymbol("NoClimates",manager,noClimates()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("RiverChasms",manager,riverChasms()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("BiomeSize",manager,biomeSize()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("RiverFrequency",manager,riverFrequency()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Hot",manager,hot()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Snowy",manager,snowy()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Mild",manager,mild()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Warm",manager,warm()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Cool",manager,cool()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });

        registerSymbol(new ClimateControlSymbol("Land",manager,land()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Island",manager,island()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Continent",manager,continent()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });

        registerSymbol(new ClimateControlSymbol("Forested",manager,forested()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Hilly",manager,hilly()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Mountainous",manager,mountainous()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Level",manager,level()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Swampy",manager,swampy()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Arid",manager,arid()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Rainy",manager,rainy()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Wasted",manager,wasted()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });
        registerSymbol(new ClimateControlSymbol("Magical",manager,magical()), 1, new String[] { "Terrain", "Entropy", "Tradition", "Exist" });

        manager.register();
    }

    public ClimateControlSettingsModifier largeClimates() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                toChange.halfSize.set(false);
                toChange.quarterSize.set(false);
                toChange.randomBiomes.set(false);
            }

        };
    }

    public ClimateControlSettingsModifier mediumClimates() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                toChange.halfSize.set(true);
                toChange.quarterSize.set(false);
                toChange.randomBiomes.set(false);
            }

        };
    }

    public ClimateControlSettingsModifier smallClimates() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                toChange.halfSize.set(false);
                toChange.quarterSize.set(true);
                toChange.randomBiomes.set(false);
            }

        };
    }

    public ClimateControlSettingsModifier noClimates() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                toChange.randomBiomes.set(true);
            }

        };
    }

    public ClimateControlSettingsModifier riverChasms() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                toChange.maxRiverChasm.set(parameters.changedNoNegate(toChange.maxRiverChasm.value()));
            }
        };
    }

    public ClimateControlSettingsModifier biomeSize() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                if (parameters.decreased()) {
                    if (toChange.biomeSize.value()>2){
                        toChange.biomeSize.set(toChange.biomeSize.value()-1);
                    }
                } else {
                    if (toChange.biomeSize.value()<8){
                        toChange.biomeSize.set(toChange.biomeSize.value()+1);
                    }

                }
            }
        };
    }

    public ClimateControlSettingsModifier riverFrequency() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                int riverFrequency = 100-toChange.percentageRiverReduction.value();
                riverFrequency = parameters.changedOrNegated(riverFrequency);
                toChange.percentageRiverReduction.set(100-riverFrequency);
                logger.info("river reduction"+toChange.percentageRiverReduction.value());
            }
        };
    }

    public ClimateControlSettingsModifier hot() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.hotIncidence);
            }
        };
    }

    public ClimateControlSettingsModifier snowy() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.snowyIncidence);
            }
        };
    }

    public ClimateControlSettingsModifier warm() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.warmIncidence);
            }
        };
    }

    public ClimateControlSettingsModifier mild() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.warmIncidence);
                parameters.changedOrNegated(toChange.coolIncidence);
            }
        };
    }

    public ClimateControlSettingsModifier cool() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.coolIncidence);
            }
        };
    }

    public ClimateControlSettingsModifier land() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.largeContinentFrequency);
                parameters.changedOrNegated(toChange.mediumContinentFrequency);
                parameters.changedOrNegated(toChange.smallContinentFrequency);
                parameters.changedOrNegated(toChange.largeIslandFrequency);
                parameters.changedOrNegated(toChange.mediumIslandFrequency);
            }
        };
    }

    public ClimateControlSettingsModifier landSize() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                throw new RuntimeException();
            }
        };
    }

    public ClimateControlSettingsModifier island() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.largeIslandFrequency);
                parameters.changedOrNegated(toChange.mediumIslandFrequency);
            }
        };
    }

    public ClimateControlSettingsModifier continent() {
        return new ClimateControlSettingsModifier() {
            @Override
            public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
                parameters.changedOrNegated(toChange.largeContinentFrequency);
                parameters.changedOrNegated(toChange.mediumContinentFrequency);
                parameters.changedOrNegated(toChange.smallContinentFrequency);
            }
        };
    }

    public ClimateControlSettingsModifier forested() {
        return new BiomeTweaker(BiomeDictionary.Type.FOREST);
    }
    public ClimateControlSettingsModifier level() {
        List<BiomeDictionary.Type> excluded = Arrays.asList(
                BiomeDictionary.Type.HILLS,BiomeDictionary.Type.MOUNTAIN,BiomeDictionary.Type.WATER);
        return new UnBiomeTweaker(excluded);
    }

    public ClimateControlSettingsModifier hilly() {
        return new BiomeTweaker(BiomeDictionary.Type.HILLS);
    }

    public ClimateControlSettingsModifier mountainous() {
        return new BiomeTweaker(BiomeDictionary.Type.MOUNTAIN);
    }

    public ClimateControlSettingsModifier magical() {
        return new BiomeTweaker(BiomeDictionary.Type.MAGICAL);
    }

    public ClimateControlSettingsModifier swampy() {
        return new BiomeTweaker(BiomeDictionary.Type.SWAMP);
    }

    public ClimateControlSettingsModifier wasted() {
        return new BiomeTweaker(BiomeDictionary.Type.WASTELAND);
    }

    public ClimateControlSettingsModifier rainy() {
        return new RainyBiome();
    }

    public ClimateControlSettingsModifier arid() {
        return new AridBiome();
    }

    private class BiomeTweaker extends ClimateControlSettingsModifier {
        private final BiomeDictionary.Type type;
        BiomeTweaker(BiomeDictionary.Type type){
            this.type = type;
        }

        public void change(ClimateControlSettings toTweak, ClimateControlSymbolParameters parameters) {
            for (BiomeSettings tweaked: toTweak.biomeSettings()) {
                for (BiomeSettings.Element element: tweaked.elements()){
                    // check to see it it's a target biome
                    BiomeGenBase biome = BiomeGenBase.getBiome(element.biomeID().value());
                    if (!BiomeDictionary.isBiomeOfType(biome, type)) continue;
                    parameters.changedOrNegated(element.biomeIncidences());

                }
            }
        }

    }
    
    private class RainyBiome extends ClimateControlSettingsModifier {

        public void change(ClimateControlSettings toTweak, ClimateControlSymbolParameters parameters) {
            for (BiomeSettings tweaked: toTweak.biomeSettings()) {
                for (BiomeSettings.Element element: tweaked.elements()){
                    // check to see it it's a target biome
                    BiomeGenBase biome = BiomeGenBase.getBiome(element.biomeID().value());
                    logger.info(biome.biomeName + " rainfall "+biome.rainfall);
                    if (biome.getFloatRainfall()<= 0.5F) continue;
                    parameters.changedOrNegated(element.biomeIncidences());
                    logger.info(biome.biomeName + " incidence "+element.biomeIncidences().value());
                }
            }
        }

    }
    
    private class AridBiome extends ClimateControlSettingsModifier {

        public void change(ClimateControlSettings toTweak, ClimateControlSymbolParameters parameters) {
            for (BiomeSettings tweaked: toTweak.biomeSettings()) {
                for (BiomeSettings.Element element: tweaked.elements()){
                    // check to see it it's a target biome
                    BiomeGenBase biome = BiomeGenBase.getBiome(element.biomeID().value());
                    if (biome.getFloatRainfall()> 0.5F) continue;
                    parameters.changedOrNegated(element.biomeIncidences());
                }
            }
        }

    }

    private class UnBiomeTweaker extends ClimateControlSettingsModifier {
        private final List<BiomeDictionary.Type> excluded;
        UnBiomeTweaker(List<BiomeDictionary.Type> excluded){
            this.excluded = excluded;
        }

        public void change(ClimateControlSettings toTweak, ClimateControlSymbolParameters parameters) {
            for (BiomeSettings tweaked: toTweak.biomeSettings()) {
                for (BiomeSettings.Element element: tweaked.elements()){
                    // check to see it it's a target biome
                    BiomeGenBase biome = BiomeGenBase.getBiome(element.biomeID().value());
                    for (BiomeDictionary.Type type: excluded)
                        if (BiomeDictionary.isBiomeOfType(biome, type)) continue;
                    parameters.changedOrNegated(element.biomeIncidences());
                }
            }
        }

    }

    private class ComplexSubBiomes extends ClimateControlSettingsModifier{

        @Override
        public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters) {
             toChange.noBoPSubBiomes.set(parameters.negated());
        }

    }
}
