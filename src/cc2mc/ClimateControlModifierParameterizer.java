
package cc2mc;


import climateControl.api.ClimateControlSettings;

/**
 *
 * @author Zeno410
 */
abstract public class ClimateControlModifierParameterizer {

    public class Parameterized {
        private final ClimateControlSettingsModifier modifier;
        private final ClimateControlSymbolParameters parameters;
        public Parameterized(ClimateControlSettingsModifier modifier,
                ClimateControlSymbolParameters parameters) {
            this.modifier = modifier;
            this.parameters = parameters;
        }

        public void change(ClimateControlSettings toChange) {
            modifier.change(toChange, parameters);
        }
    }

    abstract public Parameterized parameterized(AgeControllerAdapter controller);

}