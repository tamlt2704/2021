package lpbook.solver;

import lpbook.utils.AsciiTable;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractLpModel {
    protected String modelName;
    public static final int MAX_INT = Integer.MAX_VALUE;
    protected LpSolve lpSolve;
    protected List<Row> constraints = new ArrayList<>();
    protected VariableFactory variableFactory = new VariableFactory();
    protected ObjectiveFunction objectiveFunction = ObjectiveFunction.MAXIMISE;

    protected double objectiveVal;

    public AbstractLpModel(String modelName) {
        this.modelName = modelName;
    }

    public abstract void initialise();
    public abstract void populateVariables();
    public abstract void populateConstraints();

    protected void addConstraint(int rowIndex, Row constraint) {
        try {
            lpSolve.setAddRowmode(true);

            List<Column> columns = constraint.getColumns();
            int[] varIndex = new int[columns.size()];
            double[] coefficient = new double[columns.size()];

            for (int i = 0; i < columns.size(); i++) {
                Column col = columns.get(i);
                varIndex[i] = col.getVariable().variableIndex;
                coefficient[i] = col.getCoefficient();
            }
            lpSolve.addConstraintex(columns.size(), coefficient, varIndex, constraint.getSense().getValue(), constraint.getRHS());
            lpSolve.setRowName(rowIndex, constraint.getConstraintName());
            lpSolve.setAddRowmode(false);
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }

    protected void configureObjectiveFunction(int[] colIndex, double[] coefficient, ObjectiveFunction objectiveFunction) {
        try {
            lpSolve.setObjFnex(colIndex.length, coefficient, colIndex);
            switch (objectiveFunction) {
                case MAXIMISE:
                    lpSolve.setMaxim();
                    break;
                case MINIMISE:
                    lpSolve.setMinim();
                    break;
                default:
                    throw new RuntimeException(String.format("Objective function '%s' is not supported!", objectiveFunction.toString()));
            }
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }

    public void createModel() {
        try {
            // init model object
            int nbVariables = variableFactory.getVariableList().size();
            lpSolve = LpSolve.makeLp(0, nbVariables);

            // add constraints
            for (int i = 0; i < constraints.size(); i++) {
                addConstraint(i + 1, constraints.get(i));
            }

            // configure objective function
            int[] varIndex = new int[nbVariables];
            double[] coefficient = new double[nbVariables];
            for (int i = 0; i < variableFactory.getVariableList().size(); i++) {
                Variable variable = variableFactory.getVariableList().get(i);
                varIndex[i] = variable.getVariableIndex();
                coefficient[i] = variable.getObjCoefficient();
            }
            configureObjectiveFunction(varIndex, coefficient, objectiveFunction);

            // set Var name and type
            configureVariables();
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }

    protected void configureVariables() {
        try {
            for (int i = 0; i < variableFactory.getVariableList().size(); i++) {
                Variable variable = variableFactory.getVariableList().get(i);
                switch (variable.getVariableType()) {
                    case INTEGER:
                        lpSolve.setInt(variable.getVariableIndex(), true);
                        break;
                    case SEMI_CONTINUOUS:
                        lpSolve.setSemicont(variable.getVariableIndex(), true);
                        break;
                    case BINARY:
                        lpSolve.setBinary(variable.getVariableIndex(), true);
                        break;
                    case FREE:
                        lpSolve.setUnbounded(variable.getVariableIndex());
                        break;
                    default:
                        throw new RuntimeException(String.format("Variable type '%s' is not supported!", variable.getVariableType()));
                }
                lpSolve.setColName(variable.getVariableIndex(), variable.variableName);
            }
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }

    protected Result getResults() {
        try {
            // write model to lpsolver format, can be run in lpsolve IDE or for debug purposes
            lpSolve.writeLp(modelName);
            lpSolve.solve();
            objectiveVal = lpSolve.getObjective();

            for (int i = 0; i < lpSolve.getPtrVariables().length; i++) {
                for (Variable variable : variableFactory.getVariableList()) {
                    if (variable.getVariableIndex() == i) {
                        variable.setValue(lpSolve.getPtrVariables()[i]);
                    }
                }
            }
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
        return new Result(objectiveVal, variableFactory.getVariableList());
    }
}
