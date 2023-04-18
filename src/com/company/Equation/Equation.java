package com.company.Equation;

import com.company.Equation.EquationParts.*;

import java.util.ArrayList;

public class Equation {
    private EquationSide side1;
    private EquationSide side2;
    private EquationSide[] sides;

    public Equation(EquationSide side1, EquationSide side2) {
        this.side1 = side1;
        this.side2 = side2;
        this.sides = new EquationSide[]{side1, side2};
    }

    public EquationSide getLeftHandSide() {
        return side1;
    }

    public EquationSide getRightHandSide() {
        return side2;
    }

    public void add(double constant) {
        side1.add(constant);
        side2.add(constant);
    }

    public void add(Coefficient coefficient) {
        side1.add(coefficient);
        side2.add(coefficient);
    }

    public void subtract(double constant) {
        add(-constant);
    }

    public void subtract(Coefficient coefficient) {
        side1.subtract(coefficient);
        side2.subtract(coefficient);
    }

    public void multiply(double constant) {
        for (int sideNumber = 0; sideNumber < 2; sideNumber++) {
            sides[sideNumber].multiply(constant);
        }
    }

    public void divide(double constant) {
        multiply(1 / constant);
    }

    public Equation solveFor(Variable variableToSolveFor) {
        ArrayList<Coefficient> coefficients = new ArrayList<>();
        double constant = 0;

        for (int sideNumber = 0; sideNumber <= 1; sideNumber++) {
            int multiplier = 1;
            if (sideNumber == 1) multiplier = -1;

            for (Coefficient coefficient : sides[sideNumber].getCoefficients().values()) {
                coefficients.add(coefficient.multiply(multiplier));
            }

            constant += sides[sideNumber].getConstant() * multiplier * -1;
        }

        Coefficient[] coefficientsAsArray = new Coefficient[coefficients.size()];
        coefficients.toArray(coefficientsAsArray);

        EquationSide coefficientSide = new EquationSide(coefficientsAsArray, 0);
        EquationSide constantSide = new EquationSide(constant);

        Equation equation = new Equation(coefficientSide, constantSide);
        ArrayList<Coefficient> coefficientsToSubtract = new ArrayList<>();

        boolean onlyOneCoefficient = coefficientSide.coefficientLength() == 1;

        while (!onlyOneCoefficient && coefficientsToSubtract.size() != coefficientSide.coefficientLength() - 1) {
            for (Coefficient coefficient : coefficientSide.getCoefficients().values()) {
                if (coefficient.hasVariable(variableToSolveFor)) continue;

                coefficientsToSubtract.add(coefficient);
            }
        }

        for (Coefficient coefficient : coefficientsToSubtract) {
            equation.subtract(coefficient);
        }

        equation.divide(coefficientSide.getCoefficientByVariable(variableToSolveFor).getCoefficientValue());
        return equation;
    }

    @Override
    public String toString() {
        return "Equation: " + side1 + " = " + side2;
    }

    public String simpleForm() {
        return side1.simpleForm() + " = " + side2.simpleForm();
    }
}
