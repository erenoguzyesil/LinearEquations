package Examples;

import Equation.*;
import Equation.EquationParts.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SolveEquationByUserInput {
    static final Character[] alphabet =
            { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                    's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static Character[] hasLetter(String string) {
        for (char letter : alphabet) {
            if (string.contains(letter + "")) {
                return new Character[] { letter };
            }
        }

        return new Character[] {};
    }

    public static boolean isOnlyLetter(String string) {
        for (char letter : alphabet) {
            if ((letter + "").equals(string)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter a linear equation: ");
        String equationInput = s.nextLine();

        System.out.println("Which variable to solve for?");
        String variable = s.nextLine().trim();

        s.close();

        ArrayList<String> stringTerms = new ArrayList<>();

        String scannedString = "";
        for (int i = 0; i < equationInput.length(); i++) {
            char character = equationInput.charAt(i);

            if (character == ' ') continue;

            if (character == '+' || character == '-') {
                stringTerms.add(scannedString);
                scannedString = character + "";
            } else if (character == '=') {
                stringTerms.add(scannedString);
                stringTerms.add("=");
                scannedString = "";
            } else if (i == equationInput.length() - 1) {
                scannedString += character;
                stringTerms.add(scannedString);
            } else {
                scannedString += character;
            }
        }

        ArrayList<Coefficient>[] coefficients = new ArrayList[] { new ArrayList<>(), new ArrayList<>() };

        double[] constants = { 0, 0 };

        int currentSideIndex = 0;

        for (String term : stringTerms) {
            if (term.equals("=")) {
                currentSideIndex += 1;
                continue;
            }

            if (hasLetter(term).length > 0) {
                String letter;
                double coefficientValue;

                if (isOnlyLetter(term)) {
                    letter = term;
                    coefficientValue = 1;
                } else {
                    letter = hasLetter(term)[0] + "";
                    coefficientValue = Integer.parseInt(term.replace(letter, ""));
                }

                coefficients[currentSideIndex].add(
                        new Coefficient(
                                coefficientValue, new Variable(letter)
                        )
                );
            }


            if (hasLetter(term).length == 0) {
                double constant = Integer.parseInt(term);

                constants[currentSideIndex] += constant;
            }
        }

        EquationSide side1 = new EquationSide(
                coefficients[0].toArray(new Coefficient[coefficients[0].size()]),
                constants[0]
        );

        EquationSide side2 = new EquationSide(
                coefficients[1].toArray(new Coefficient[coefficients[1].size()]),
                constants[1]
        );

        Equation equation = new Equation(side1, side2);

        System.out.println(equation.solveFor(new Variable(variable)).simpleForm());
    }
}