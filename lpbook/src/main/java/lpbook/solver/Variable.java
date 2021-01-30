package lpbook.solver;

public class Variable {
    int variableIndex;
    String variableName;
    double value;
    VariableType variableType;
    float lowerBound;
    float upperBound;
    double objCoefficient;

    public Variable(int variableIndex, String variableName, VariableType variableType, float lowerBound, float upperBound, double objCoefficient) {
        this.variableIndex = variableIndex;
        this.variableName = variableName;
        this.variableType = variableType;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.objCoefficient = objCoefficient;
    }

    public int getVariableIndex() {
        return variableIndex;
    }

    public void setVariableIndex(int variableIndex) {
        this.variableIndex = variableIndex;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    public float getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(float lowerBound) {
        this.lowerBound = lowerBound;
    }

    public float getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(float upperBound) {
        this.upperBound = upperBound;
    }

    public double getObjCoefficient() {
        return objCoefficient;
    }

    public void setObjCoefficient(double objCoefficient) {
        this.objCoefficient = objCoefficient;
    }
}
