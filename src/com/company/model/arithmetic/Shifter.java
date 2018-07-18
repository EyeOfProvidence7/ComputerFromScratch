package com.company.model.arithmetic;

import com.company.Helper;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class Shifter {
    private ArrayList<Wire> outWires;

    public Shifter(ArrayList<Wire> inputs, Wire control){
        try {
            checkSize(inputs);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        outWires = new ArrayList<>();
        ArrayList<Wire> andOutputs = new ArrayList<>();
        Gate not = new Gate(control, new Wire(), GateType.NOT, Helper.setID());
        Wire controlComplement = not.getOut();
        setupANDGates(inputs, control, andOutputs, controlComplement);
        setupOutWires(andOutputs);
    }

    private void setupOutWires(ArrayList<Wire> andOutputs) {
        outWires.add(andOutputs.get(1));
        for(int i = 0; i < andOutputs.size() - 3; i+=2){
            Gate or = new Gate(andOutputs.get(i), andOutputs.get(i+3), GateType.OR, Helper.setID());
            outWires.add(or.getOut());
        }
        outWires.add(andOutputs.get(andOutputs.size() - 2));
    }

    private void setupANDGates(ArrayList<Wire> inputs, Wire control, ArrayList<Wire> andOutputs, Wire controlComplement) {
        Gate firstAnd = new Gate(inputs.get(0), control, GateType.AND, Helper.setID());
        andOutputs.add(firstAnd.getOut());
        for(int i = 1; i < inputs.size()-1; i++){
            Gate leftAnd = new Gate(controlComplement, inputs.get(i), GateType.AND, Helper.setID());
            Gate rightAnd = new Gate(inputs.get(i), control, GateType.AND, Helper.setID());
            andOutputs.add(leftAnd.getOut());
            andOutputs.add(rightAnd.getOut());
        }
        Gate lastAnd = new Gate(controlComplement, inputs.get(inputs.size() - 1), GateType.AND, Helper.setID());
        andOutputs.add(lastAnd.getOut());
    }

    private void checkSize(ArrayList<Wire> inputs){
        if (inputs.size() <  3){
            throw new IllegalArgumentException("Too few inputs for Shifter");
        }
    }

    public ArrayList<Wire> getOutWires() {
        return outWires;
    }
}
