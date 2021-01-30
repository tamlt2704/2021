package lpbook.solver;

import lpsolve.LpSolve;

public enum Sense {
    EQUALITY(LpSolve.EQ), LESS_THAN_OR_EQUAL(LpSolve.LE), GREATER_THAN_OR_EQUAL(LpSolve.GE);
    private final int value;

    Sense(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
