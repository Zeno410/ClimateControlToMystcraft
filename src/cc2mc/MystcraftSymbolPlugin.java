
package cc2mc;

/**
 *
 * @author Zeno410
 */
abstract public class MystcraftSymbolPlugin {
    
    abstract public void registerLogic(AgeControllerAdapter controller, long seed);

    abstract public String identifier() ;
}
