package com.company.model.combinatorial;

import com.company.Helper;
import com.company.model.gates.GateType;
import com.company.model.gates.MGate;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class Multiplexer {
    private Wire out;

    public Multiplexer(ArrayList<Wire> inputs, ArrayList<Wire> controls){
        try {
            checkArraySizes(inputs, controls);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        constructMultiplexer(inputs, controls);
    }

    private void checkArraySizes(ArrayList<Wire> inputs, ArrayList<Wire> controls){
        if (inputs.size() !=  Helper.powerOfTwo(controls.size())){
            throw new IllegalArgumentException("The input size does not correspond to amount of control lines");
        }
    }

    private void constructMultiplexer(ArrayList<Wire> inputs, ArrayList<Wire> controls){
        ArrayList<Wire> controlComplement = Helper.getWireComplements(controls);
        ArrayList<Wire> andOutputs = new ArrayList<>();
        getAndGateOutputs(inputs, controls, controlComplement, andOutputs);
        MGate or = new MGate(andOutputs, GateType.OR);
        out = or.getOut();
    }

    private void getAndGateOutputs(ArrayList<Wire> inputs, ArrayList<Wire> controls, ArrayList<Wire> controlComplement, ArrayList<Wire> andOutputs) {
        for (int i = 0; i < inputs.size(); i++){
            ArrayList<Wire> wires = new ArrayList<>();
            wires.add(inputs.get(i));
            ArrayList<Wire> controlWires = Helper.getMultiplexerDecoderANDGateInputWires(controls, controlComplement, i);
            controlWires.forEach(wire -> wires.add(wire));
            MGate and = new MGate(wires, GateType.AND);
            andOutputs.add(and.getOut());
        }
    }

    public Wire getOut() {
        return out;
    }
}
