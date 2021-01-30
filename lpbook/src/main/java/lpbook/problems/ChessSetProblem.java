package lpbook.problems;

import lpbook.entities.ChessSet;
import lpbook.solver.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessSetProblem extends AbstractLpModel {
    List<ChessSet> chessSets = new ArrayList<>();
    float machineHoursAvailable = 160;
    float boxwoodAvailable = 200;

    public ChessSetProblem(String modelName) {
        super(modelName);
    }


    @Override
    public void initialise() {
        ChessSet small = new ChessSet();
        small.setName("xs");
        small.setTimeToProduce(3);
        small.setKgWoodRequired(1);
        small.setProfit(5);

        ChessSet large = new ChessSet();
        large.setName("ls");
        large.setTimeToProduce(2);
        large.setKgWoodRequired(3);
        large.setProfit(20);

        chessSets.add(small);
        chessSets.add(large);
    }

    @Override
    public void populateVariables() {
        chessSets.forEach(s -> {
            Variable variable = variableFactory.createVariable(s.getName(), VariableType.INTEGER, 0, MAX_INT, s.getProfit());
            s.setVariable(variable);
        });
    }

    @Override
    public void populateConstraints() {
        // kg of box wood
        List<Column> cols = chessSets.stream().map(s -> new Column(s.getVariable(), s.getKgWoodRequired())).collect(Collectors.toList());
        Row boxWoodLimitedConstraint = new Row("box wood limited", cols, Sense.LESS_THAN_OR_EQUAL, boxwoodAvailable);

        cols = chessSets.stream().map(s -> new Column(s.getVariable(), s.getTimeToProduce())).collect(Collectors.toList());
        Row machineLimitedConstraint = new Row("Machine hour limited", cols, Sense.LESS_THAN_OR_EQUAL, machineHoursAvailable);

        constraints.add(boxWoodLimitedConstraint);
        constraints.add(machineLimitedConstraint);
    }

    public void runDemo() {
        initialise();
        populateVariables();
        populateConstraints();
        createModel();
        getResults();
    }
}
