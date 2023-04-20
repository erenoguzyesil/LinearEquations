package Equation.EquationParts;

public class Coefficient {
    private double coefficientValue;
    private final Variable variable;

    public Coefficient(double coefficientValue, Variable variable) {
        this.coefficientValue = coefficientValue;
        this.variable = variable;
    }

    public double getCoefficientValue() {
        return coefficientValue;
    }

    public void setCoefficientValue(int coefficientValue) {
        this.coefficientValue = coefficientValue;
    }

    public Variable getVariable() {
        return variable;
    }

    public boolean hasVariable(Variable variable) {
        return this.variable.equals(variable);
    }

    public Coefficient add(Coefficient other) {
        return new Coefficient(
                coefficientValue + other.getCoefficientValue(),
                new Variable(variable.getName())
        );
    }

    public Coefficient multiply(double constant) {
        return new Coefficient(
                coefficientValue * constant,
                new Variable(variable.getName())
        );
    }

    public Coefficient negate() {
        return multiply(-1);
    }

    public double computeValue() throws Exception {
        if (variable.getValue() == 0) {
            throw new Exception("`this.variable` has no set value.");
        }

        return coefficientValue * variable.getValue();
    }

    @Override
    public String toString() {
        return "(Coefficient: " + coefficientValue + variable.getName() + ")";
    }

    public String simpleForm() {
        String sign = coefficientValue + "";

        if (coefficientValue == 1) sign = "";
        if (coefficientValue == -1) sign = "-";

        return sign + variable.getName();
    }
}
