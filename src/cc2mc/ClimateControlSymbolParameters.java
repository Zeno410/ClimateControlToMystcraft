
package cc2mc;

import climateControl.utils.Mutable;
/**
 *
 * @author Zeno410
 */
public class ClimateControlSymbolParameters {

    public static final String no = "no";
    public static final String increase = "increase";
    public static final String decrease = "decrease";

    private final boolean negated;
    private final boolean decreased;


    public ClimateControlSymbolParameters(AgeControllerAdapter controller) {
        ModifierFacade noFound = controller.popModifier(no);
        negated  = (noFound != null);
        ModifierFacade decreaseFound = controller.popModifier(decrease);
        decreased = (decreaseFound != null);
        ModifierInstaller.logger.info("parameters " + negated +  " "+ decreased);
    }

    private ClimateControlSymbolParameters(boolean negated, boolean decreased) {
        this.decreased = decreased;
        this.negated= negated;
    }

    public boolean decreased() {return decreased;}
    public boolean negated() {return negated;}

    public ClimateControlSymbolParameters reversed() {
        return new ClimateControlSymbolParameters(!negated,!decreased);
    }

    public int changedNoNegate(int toChange) {
        if (decreased) {
            int result = toChange*2/3;
            if (result < 1) return result;
            if (result == toChange) return toChange-1;
            return result;
        } else {
            int result = toChange*3/2;
            if (result == toChange) return toChange+1;
            return result;
        }
    }

    public void changedNoNegate(Mutable<Integer> toChange) {
        toChange.set(changedNoNegate(toChange.value()));
    }

    public void changedOrNegated(Mutable<Integer> toChange) {
        toChange.set(changedOrNegated(toChange.value()));
    }
    
     public double changedNoNegate(double toChange) {
        if (decreased) {
            double result = toChange*2.0/3.0;
            if (result < 1) return result;
            if (result == toChange) return toChange-1;
            return result;
        } else {
            double result = toChange*3.0/2.0;
            if (result == toChange) return toChange+1;
            return result;
        }
    }

    public int changedOrNegated(int toChange) {
        if (negated) return 0;
        return changedNoNegate(toChange);
    }
}
