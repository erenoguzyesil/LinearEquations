# Linear Equations

A simple implementation of **linear equations** and their various concepts from the **algebra** part of mathematics and its usage examples, including:

- Linear equations
- Parts of an equation
    - Variables
    - Coefficients
    - Constants
- Operations on an equation (addition, multiplication especially)

## Usage
### `Variable` class

> Represents a variable in an equation, often used with a `Coefficient` object.

#### Create a new `Variable` object:

```java
new Variable(
    String variableName
    double value // optional
);
```

Each variable has a unique `hashCode()` value based on their **`variableName`** property.

```java
Variable v1 = new Variable("x");
Variable v2 = new Variable("x", 10);
Variable v3 = new Variable("cool-variable", 10);

v1.equals(v2);                      // true
v1.hashCode() == v2.hashCode();     // true
v1.getName().equals(v2.getName());  // true

v2.equals(v3);                      // false
v1.hashCode() == v3.hashCode();     // false
v2.getName().equals(v3.getName());  // false
```
This functionality allows `Variable` objects to be used as accessible keys in a `HashMap` object.

### `Coefficient` class

> Represents a term in an equation with a coefficient carrying its variable.

#### Create a new `Coefficient` object:

```java
new Coefficient(
    double coefficientValue,
    Variable variable
);
```

Addition is possible with other `Coefficient` objects **which have the same `variable` property**, and multiplication is achievable with **`double` values only.**

```java
Variable v1 = new Variable("x");

Coefficient c1 = new Coefficient(-2, v1);
Coefficient c2 = new Coefficient(10, v1);

c1.add(c2);     // new Coefficient(8, new Variable("x"))  ||  8x mathematically
c1.multiply(3); // new Coefficient(-6, new Variable("x")) || -6x mathematically
```

If the `Variable` object property of a `Coefficient` object has its `value` property set to any `double` other than `0`, then the `computeValue()` method can calculate the numerical value of the coefficient term mathematically:

```java
Variable v1 = new Variable("z", 20); // variable has the value of 20
Coefficient c1 = new Coefficient(5, v1); // 5z mathematically

c1.computeValue() // 100 || for z = 20, 5z = 100 mathematically

// If `v1.value` was 0, `c1.computeValue()` would have thrown an exception.
```

### `EquationSide` class

> Represents a single side of an equation, often used with an `Equation` object. 
> It can contain multiple coefficients, variables, and constants.
> Like terms and constants will be added together.

#### Create a new `EquationSide` object:
```java
new EquationSide(
    Coefficient[] coefficients,             
    double[] constants  or  double constant 
);
```
- You can leave out `coefficients` argument if you have no terms with coefficients and only constant(s).
- You can leave out `constants` or `constant` argument if you have no constants and only term(s) with coefficient(s).
- You can replace `double[] constants` with `double constant` if you have only one constant (Multiple constants will still be added together.)

Addition, subtraction, multiplication (only with `double` values), and division (achieved by multiplication by reciprocal) operations can be done.
Each method for operations like these modify the object itself.

```java
Variable v1 = new Variable("x");
Variable v2 = new Variable("y");

Coefficient c1 = new Coefficient(5, v1); // 5x
Coefficient c2 = new Coefficient(9, v2); // 9y
Coefficient c3 = new Coefficient(3, v2); // 3y
        
EquationSide side = new EquationSide(
        new Coefficient[] { c1, c2, c3 },
        new double[] { 20, -10 }
);

// Like terms `9y` and `3y` are added together, resulting in `12y`.
        
side.toString(); // 5.0x + 12.0y + 10.0

side.add(new Coefficient(2, v1)).toString(); // `5.0x + 2.0x` becomes `7.0x`
                                             //=> 7.0x + 12.0y + 10.0

side.subtract(new Coefficient(12, v2)); // `12.0y - 12.0y` becomes `0.0y` — the `12.0y` term is removed.
                                        //=> 7.0x + 10.0

side.multiply(5).toString(); //=> 35.0x + 50.0
```

### `Equation` class
> Represents an equation with two sides (using `EquationSide`)

#### Create a new `Equation` object:
```java
new Equation(
    EquationSide side1,
    EquationSide side2
);
```

Addition, subtraction, multiplication, and division can be performed on an object with each having its own method.

```java
Variable v1 = new Variable("x");
Variable v2 = new Variable("y");

Coefficient c1 = new Coefficient(2, v1); // 2x
Coefficient c2 = new Coefficient(6, v2); // 6y
Coefficient c3 = new Coefficient(2, v2); // 2y

EquationSide side1 = new EquationSide(
        new Coefficient[] { c1, c2 }, 5
); // 2x + 6y + 5

EquationSide side2 = new EquationSide(
        new Coefficient[] { c3 }, -10
); // 2y - 10

Equation equation = new Equation(side1, side2);

equation.add(5); // To keep the equation in balance, the operation is done on both sides.
// side1 => 2x + 6y + 10
// side2 => 2y - 5
```
Use the **`solveFor(Variable variable)`** method to solve an equation.

```java
equation.solveFor(v1).toString() //=> (1.0x + 0.0 = -2.0y + -7.5)
```

### `simpleForm()` method

> You can call this method on `Coefficient, EquationSide, and Equation` objects. 
> It converts the object to `String` in a **readable** way and returns `String`.

```java
Coefficient c1 = new Coefficient(-1, new Variable("x"));

c1.toString()   // (Coefficient: -1.0x)
c1.simpleForm() // -x

equation.toString() // Equation: (1.0x + 0.0 = -2.0y + -7.5)
equation.simpleForm() // x = -2.0y + -7.5
```

## Limitations

- Terms that include multiple variables are currently not achievable. `e.g. 4xy, -6zt`
- Non-linear equations are not achievable. `e.g 5x^2 = 125`


