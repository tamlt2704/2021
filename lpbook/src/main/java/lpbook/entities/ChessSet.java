package lpbook.entities;

import lpbook.solver.Variable;

public class ChessSet {
    String name;
    float timeToProduce;
    float kgWoodRequired;
    float profit;
    Variable variable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTimeToProduce() {
        return timeToProduce;
    }

    public void setTimeToProduce(float timeToProduce) {
        this.timeToProduce = timeToProduce;
    }

    public float getKgWoodRequired() {
        return kgWoodRequired;
    }

    public void setKgWoodRequired(float kgWoodRequired) {
        this.kgWoodRequired = kgWoodRequired;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }
}
