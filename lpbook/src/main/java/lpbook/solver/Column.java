package lpbook.solver;


public class Column {
    Variable variable;
    double coefficient;

    public Column(Variable variable, double coefficient) {
        this.variable = variable;
        this.coefficient = coefficient;
    }

    public Variable getVariable() {
        return variable;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
