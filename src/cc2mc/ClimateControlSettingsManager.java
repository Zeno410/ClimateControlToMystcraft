
package cc2mc;

import climateControl.api.DimensionalSettingsRegistry;
import java.util.ArrayList;

/**
 *
 * @author Zeno410
 */
public class ClimateControlSettingsManager {
    private ArrayList<ClimateControlModifierParameterizer.Parameterized> modifiers =
            new ArrayList<ClimateControlModifierParameterizer.Parameterized>();
    private final MasterModifier masterModifier = new MasterModifier();

    public void register() {
        DimensionalSettingsRegistry.instance.add("cc2mc", masterModifier);
    }

    public void add(ClimateControlSettingsModifier.Parameterized toAdd) {
        modifiers.add(toAdd);
    }

    public void set(int dimension) {
        // note this also clears the modifier list
        masterModifier.set(dimension, modifiers);
        modifiers = new ArrayList<ClimateControlModifierParameterizer.Parameterized>();;
    }

}
