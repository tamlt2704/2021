package lpbook.problems;

import lpbook.entities.BurglarItem;
import lpbook.solver.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BurglarProblem extends AbstractLpModel {
    private int nbItems;
    private int weightMax;
    private double[] values;
    private double[] weights;
    List<BurglarItem> itemList = new ArrayList<>();

    public BurglarProblem(String modelName) {
        super("BurglarProblem");
    }

    @Override
    public void initialise() {
        setNbItems(8);
        setWeightMax(102);
        values = new double[nbItems];
        weights = new double[nbItems];
        setValues(15, 100, 90, 60, 40, 15, 10, 1);
        setWeights( 2, 20, 20, 30, 40, 30, 60, 10);
        for (int i = 0; i < values.length; i++) {
            itemList.add(new BurglarItem(values[i], weights[i]));
        }
    }

    @Override
    public void populateVariables() {
        for (BurglarItem burglarItem : itemList) {
            int itemIdex = itemList.indexOf(burglarItem);
            Variable variable = variableFactory.createVariable(
                    String.format("take(%d)", itemIdex),
                    VariableType.BINARY,
                    0,
                    1,
                    burglarItem.getValue()
                    );
            burglarItem.setVariable(variable);
        }
    }

    @Override
    public void populateConstraints() {
        //! Weight restriction
        List<Column> cols = itemList.stream().map(item -> new Column(item.getVariable(), item.getWeight())).collect(Collectors.toList());
        Row weightConstraint = new Row("Weight constraint", cols, Sense.LESS_THAN_OR_EQUAL, weightMax);
        constraints.add(weightConstraint);
    }

    public void setNbItems(int nbItems) {
        this.nbItems = nbItems;
    }

    public void setWeightMax(int weightMax) {
        this.weightMax = weightMax;
    }

    public void setValues(double... itemvalues) {
        if (itemvalues.length != getNbItems()) {
            throw new RuntimeException(String.format("Number of input '%s' is not equal to number of item '%d'", itemvalues.length, nbItems));
        }
        for (int i = 0; i < itemvalues.length; i++) {
            values[i] = itemvalues[i];
        }
    }

    public void setWeights(double... itemWeights) {
        if (itemWeights.length != getNbItems()) {
            throw new RuntimeException(String.format("Number of input '%s' is not equal to number of item '%d'", itemWeights.length, nbItems));
        }
        for (int i = 0; i < itemWeights.length; i++) {
            weights[i] = itemWeights[i];
        }
    }

    public int getNbItems() {
        return nbItems;
    }

    public int getWeightMax() {
        return weightMax;
    }

    public double[] getValues() {
        return values;
    }

    public double[] getWeights() {
        return weights;
    }

    public void runDemo() {
        initialise();
        populateVariables();
        populateConstraints();
        createModel();
        getResults().render();
    }
}
