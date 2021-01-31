package lpbook.entities;

import lpbook.solver.Variable;
import java.util.Map;

public class RawMaterial {
    public enum Component {
        C("carbon"), CU("Copper"), MN("Manganese");
        private final String name;

        Component(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    String name;
    Map<Component, Double> chemicalComponent;
    float availability;
    double cost;

    Variable variable;

    public RawMaterial(String name, Map<Component, Double> chemicalComponent, float availability, double cost) {
        this.name = name;
        this.chemicalComponent = chemicalComponent;
        this.availability = availability;
        this.cost = cost;
    }

    public Map<Component, Double> getChemicalComponent() {
        return chemicalComponent;
    }

    public void setChemicalComponent(Map<Component, Double> chemicalComponent) {
        this.chemicalComponent = chemicalComponent;
    }

    public float getAvailability() {
        return availability;
    }

    public void setAvailability(float availability) {
        this.availability = availability;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setChemicalComponent(Component component, double percentage) {
        this.chemicalComponent.put(component, percentage);
    }


    public String getName() {
        return name;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }
}
