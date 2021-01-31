package lpbook.problems;

import lpbook.entities.RawMaterial;
import lpbook.solver.*;

import java.util.*;
import java.util.stream.Collectors;

import static lpbook.entities.RawMaterial.Component.C;
import static lpbook.entities.RawMaterial.Component.CU;
import static lpbook.entities.RawMaterial.Component.MN;

public class ProductionOfAlloysProblem extends AbstractLpModel {
    float orderQuantity;
    Map<RawMaterial.Component, Double> minimumPercentage = new HashMap<>();
    Map<RawMaterial.Component, Double> maximumPercentage = new HashMap<>();
    List<RawMaterial> materials = new ArrayList<>();


    public ProductionOfAlloysProblem(String modelName) {
        super(modelName);
    }

    @Override
    public void initialise() {
        orderQuantity = 500;
        minimumPercentage.put(C, 2.0);
        minimumPercentage.put(CU, 0.4);
        minimumPercentage.put(MN, 1.2);

        maximumPercentage.put(C, 3.0);
        maximumPercentage.put(CU, 0.6);
        maximumPercentage.put(MN, 1.65);

        RawMaterial ironAlloy1 = new RawMaterial("ironAlloy 1", new HashMap<>(), 400, 200);
        ironAlloy1.setChemicalComponent(C, 2.5);
        ironAlloy1.setChemicalComponent(CU, 0);
        ironAlloy1.setChemicalComponent(MN, 1.3);

        RawMaterial ironAlloy2 = new RawMaterial("ironAlloy 2", new HashMap<>(), 300, 250);
        ironAlloy2.setChemicalComponent(C, 3);
        ironAlloy2.setChemicalComponent(CU, 0);
        ironAlloy2.setChemicalComponent(MN, 0.8);

        RawMaterial ironAlloy3 = new RawMaterial("ironAlloy 3", new HashMap<>(), 600, 150);
        ironAlloy3.setChemicalComponent(C, 0);
        ironAlloy3.setChemicalComponent(CU, 0.3);
        ironAlloy3.setChemicalComponent(MN, 0);

        RawMaterial copperAlloy1 = new RawMaterial("copper alloy 1", new HashMap<>(), 500, 220);
        copperAlloy1.setChemicalComponent(C, 0);
        copperAlloy1.setChemicalComponent(CU, 90);
        copperAlloy1.setChemicalComponent(MN, 0);

        RawMaterial copperAlloy2 = new RawMaterial("copper alloy 2", new HashMap<>(), 200, 240);
        copperAlloy2.setChemicalComponent(C, 0);
        copperAlloy2.setChemicalComponent(CU, 96);
        copperAlloy2.setChemicalComponent(MN, 4);

        RawMaterial aluminumAlloy1 = new RawMaterial("aluminum alloy 1", new HashMap<>(), 300, 200);
        aluminumAlloy1.setChemicalComponent(C, 0);
        aluminumAlloy1.setChemicalComponent(CU, 0.4);
        aluminumAlloy1.setChemicalComponent(MN, 1.2);

        RawMaterial aluminumAlloy2 = new RawMaterial("aluminum alloy 2", new HashMap<>(), 250, 165);
        aluminumAlloy2.setChemicalComponent(C, 0);
        aluminumAlloy2.setChemicalComponent(CU, 0.6);
        aluminumAlloy2.setChemicalComponent(MN, 0);

        materials.add(ironAlloy1);
        materials.add(ironAlloy2);
        materials.add(ironAlloy3);
        materials.add(copperAlloy1);
        materials.add(copperAlloy2);
        materials.add(aluminumAlloy1);
        materials.add(aluminumAlloy2);
    }

    @Override
    public void populateVariables() {
        for (RawMaterial material : materials) {
            Variable variable = variableFactory.createVariable(
                    String.format("use_%s", material.getName()),
                    VariableType.SEMI_CONTINUOUS,
                    0,
                    MAX_INT,
                    material.getCost()
            );
            material.setVariable(variable);
        }
    }

    @Override
    public void populateConstraints() {
        // produce
        Variable produce = variableFactory.createVariable("produce", VariableType.SEMI_CONTINUOUS, 0, MAX_INT, 0);
        List<Column> cols = materials.stream().map(m -> new Column(m.getVariable(), 1)).collect(Collectors.toList());
        cols.add(new Column(produce, -1));
        Row produceConstraint = new Row("Produce Constraint", cols, Sense.EQUALITY, 0);
        constraints.add(produceConstraint);

        // guarantee min and max percentage of every chemical element
        for (RawMaterial.Component component : Arrays.asList(C, CU, MN)) {
            List<Column> pminCols = new ArrayList<>();
            List<Column> pmaxCols = new ArrayList<>();
            for (RawMaterial material : materials) {
                pminCols.add(new Column(material.getVariable(), material.getChemicalComponent().get(component)));
                pmaxCols.add(new Column(material.getVariable(), material.getChemicalComponent().get(component)));
            }
            pminCols.add(new Column(produce, -1*minimumPercentage.get(component)));
            Row pminConstraint = new Row(String.format("min percentage (%s) constraint", component), pminCols, Sense.GREATER_THAN_OR_EQUAL, 0);
            constraints.add(pminConstraint);

            pmaxCols.add(new Column(produce, -1*maximumPercentage.get(component)));
            Row pMaxConstraint = new Row(String.format("max percentage (%s) constraint", component), pmaxCols, Sense.LESS_THAN_OR_EQUAL, 0);
            constraints.add(pMaxConstraint);
        }

        // material availability
        for (RawMaterial material : materials) {
            List<Column> col = new ArrayList<>();
            col.add(new Column(material.getVariable(), 1));
            Row mAvailabilityConstraint = new Row(String.format("availability %s", material.getName()), col, Sense.LESS_THAN_OR_EQUAL, material.getAvailability());
            constraints.add(mAvailabilityConstraint);
        }

        //demand
        List<Column> col = new ArrayList<>();
        col.add(new Column(produce, 1.0));
        Row demandConstraint = new Row("Demand", col, Sense.GREATER_THAN_OR_EQUAL, orderQuantity);
        constraints.add(demandConstraint);
    }

    public void runDemo() {
        initialise();
        populateVariables();
        populateConstraints();
        objectiveFunction = ObjectiveFunction.MINIMISE;
        createModel();
        getResults().render();
    }
}
