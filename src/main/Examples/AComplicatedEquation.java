package main.Examples;

import main.Equation.*;
import main.Equation.EquationParts.*;

public class AComplicatedEquation {
    public static void main(String[] args) {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Variable z = new Variable("z");
        Variable t = new Variable("t");

        Coefficient xc1 = new Coefficient(7, x);
        Coefficient xc2 = new Coefficient(16, x);

        Coefficient yc1 = new Coefficient(-9, y);
        Coefficient yc2 = new Coefficient(4, y);

        Coefficient zc1 = new Coefficient(-5, z);
        Coefficient zc2 = new Coefficient(10, z);

        Coefficient tc1 = new Coefficient(-13, t);
        Coefficient tc2 = new Coefficient(92, t);

        EquationSide side1 = new EquationSide(new Coefficient[] { xc1, yc1, zc1, zc2 }, 65);
        EquationSide side2 = new EquationSide(new Coefficient[] { yc2, tc1, tc2, xc2 }, 5);

        Equation equation = new Equation(side1, side2);

        System.out.println(equation);
        System.out.println(equation.solveFor(new Variable("x")));
    }
}
