
package cc2mc;

import climateControl.api.ClimateControlSettings;

/**
 *
 * @author Zeno410
 */
abstract public class ClimateControlSettingsModifier extends ClimateControlModifierParameterizer {

    public Parameterized parameterized(AgeControllerAdapter controller) {
        return new Parameterized(this, new ClimateControlSymbolParameters(controller));
    }

    abstract public void change(ClimateControlSettings toChange, ClimateControlSymbolParameters parameters);
}
