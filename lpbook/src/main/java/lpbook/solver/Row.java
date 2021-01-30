package lpbook.solver;

import java.util.List;

public class Row {
    private String constraintName;
    private List<Column> columns;
    private float RHS;
    private Sense sense;

    public Row(String constraintName, List<Column> columns, Sense sense, float RHS) {
        this.constraintName = constraintName;
        this.columns = columns;
        this.RHS = RHS;
        this.sense = sense;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public float getRHS() {
        return RHS;
    }

    public Sense getSense() {
        return sense;
    }
}
