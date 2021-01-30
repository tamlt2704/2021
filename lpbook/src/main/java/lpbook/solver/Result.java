package lpbook.solver;

import lpbook.utils.AsciiTable;

import java.util.List;

public class Result {
    double objectiveValue;
    List<Variable> variableList;

    public Result(double objectiveValue, List<Variable> variableList) {
        this.objectiveValue = objectiveValue;
        this.variableList = variableList;
    }

    public void render() {
        System.out.println("value of objective function: " + objectiveValue);
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.setMaxColumnWidth(45);
        asciiTable.getColumns().add(new AsciiTable.Column("attribute"));
        asciiTable.getColumns().add(new AsciiTable.Column("value"));

        AsciiTable.Row row = new AsciiTable.Row();
        asciiTable.getData().add(row);
        row.addValues("objective value", String.valueOf(objectiveValue));

        for (Variable variable : variableList) {
            row = new AsciiTable.Row();
            asciiTable.getData().add(row);
            row.addValues(variable.getVariableName(), String.valueOf(variable.getValue()));
        }

        asciiTable.calculateColumnWidth();
        asciiTable.render();
    }
}
