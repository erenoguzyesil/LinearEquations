package Equation;

import Equation.EquationParts.*;
import java.util.HashMap;

public class EquationSide {
    private final HashMap<Variable, Coefficient> coefficients = new HashMap<>();
    private double constant = 0;

    /**
     *
     * <p>Initializes a new side of an <code>Equation</code>. All like terms will be added together.</p>
     * <p>Example:</b> <u>5x + 4x + 20 - 10</u>:</p>
     * <pre>
     * {@code
     * Variable x = new Variable("x")
     * Coefficient c1 = new Coefficient(5, x);
     * Coefficient c2 = new Coefficient(4, x);
     *
     * new EquationSide(new Coefficient[] { c1, c2 }, new double[] { 20, -10 });
     * }</pre>
     * @param coefficients <code>Coefficient</code> values given in an <code>Array</code>
     * @param constants <code>double</code> values given in an <code>Array</code> as constants
     */
    public EquationSide(Coefficient[] coefficients, double[] constants) {
        for (Coefficient coefficient : coefficients) {
            Variable variable = coefficient.getVariable();

            boolean variableAlreadyExists = this.coefficients.containsKey(variable);

            // Check if a coefficient with the same variable is already added (adding like terms)
            if (variableAlreadyExists) {
                Coefficient lastCoefficientValue = this.coefficients.get(variable);
                this.coefficients.put(variable, lastCoefficientValue.add(coefficient));
            } else {
                this.coefficients.put(variable, coefficient);
            }
        }

        // Adding constants all together
        for (double constant : constants) {
            this.constant = this.constant + constant;
        }
    }

    public EquationSide(Coefficient[] coefficients, double constant) {
        this(coefficients, new double[] { constant });
    }

    public EquationSide(Coefficient[] coefficients) {
        this(coefficients, new double[] { 0 });
    }

    public EquationSide(double constant) {
        this(new Coefficient[] { }, constant);
    }

    public EquationSide(double[] constants) {
        this(new Coefficient[] { }, constants);
    }

    public HashMap<Variable, Coefficient> getCoefficients() {
        return coefficients;
    }

    public double getConstant() {
        return constant;
    }

    public void add(double constant) {
        this.constant += constant;
    }

    public void add(Coefficient coefficient) {
        Variable variable = coefficient.getVariable();

        if (coefficients.containsKey(variable)) {
            coefficients.put(variable, coefficients.get(variable).add(coefficient));
        } else {
            coefficients.put(variable, coefficient);
        }
    }

    public void subtract(Coefficient coefficient) {
        Variable variable = coefficient.getVariable();

        boolean keyExists = coefficients.containsKey(variable);
        boolean sameCoefficientExists =
                keyExists && coefficients.get(variable).getCoefficientValue() == coefficient.getCoefficientValue();

        if (sameCoefficientExists) {
            coefficients.remove(variable);
        } else {
            add(coefficient.negate());
        }
    }

    public void multiply(double constant) {
        for (Variable variable : coefficients.keySet()) {
            Coefficient coefficient = coefficients.get(variable);
            coefficients.put(variable, coefficient.multiply(constant));
        }

        this.constant *= constant;
    }

    public boolean hasCoefficient() {
        return coefficients.size() != 0;
    }

    public boolean hasConstant() {
        return constant != 0;
    }

    public int coefficientLength() {
        return coefficients.size();
    }

    public Coefficient getCoefficientByVariable(Variable variable) {
        return coefficients.get(variable);
    }

    @Override
    public String toString() {
        String result = "(EquationSide: ";

        for (Coefficient coefficient : coefficients.values()) {
            result += coefficient.getCoefficientValue() + coefficient.getVariable().getName() + " + ";
        }

        result += constant + ")";

        return result;
    }

    public String simpleForm() {
        String result = "";

        int index = 0;
        for (Coefficient coefficient : coefficients.values()) {
            if (index == coefficientLength() - 1 && constant == 0) {
                result += coefficient.simpleForm();
            } else {
                result += coefficient.simpleForm() + " + ";
            }
        }

        if (constant != 0)
            result += constant;

        return result;
    }
}
