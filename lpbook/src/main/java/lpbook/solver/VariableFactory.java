package lpbook.solver;

import java.util.ArrayList;
import java.util.List;

public class VariableFactory {
    int variableIndex = 1;
    private List<Variable> variableList = new ArrayList<>();

    public Variable createVariable(String variableName, VariableType variableType, float lowerBound, float upperBound, double objCoefficient) {
        Variable variable = new Variable(variableIndex++, variableName,  variableType, lowerBound, upperBound,  objCoefficient);
        variableList.add(variable);
        return variable;
    }

    public List<Variable> getVariableList() {
        return variableList;
    }
}
