package com.company.model.combinatorial;

import com.company.Helper;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.MGate;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class Decoder {
    private ArrayList<Wire> outWires;

    public Decoder(ArrayList<Wire> controls){
        constructDecoder(controls);
    }

    private void constructDecoder(ArrayList<Wire> controls){
        ArrayList<Wire> controlComplements = Helper.getWireComplements(controls);
        getAndGateOutputs(controls, controlComplements);
    }

    private void getAndGateOutputs(ArrayList<Wire> controls, ArrayList<Wire> controlComplement) {
        outWires = new ArrayList<>();
        if(controls.size() == 1){
            Gate NOT = new Gate(controls.get(0), new Wire(), GateType.NOT, Helper.setID());
            outWires.add(NOT.getOut());
            outWires.add(controls.get(0));
        } else {
            for (int i = 0; i < Helper.powerOfTwo(controls.size()); i++){
                ArrayList<Wire> wires = Helper.getMultiplexerDecoderANDGateInputWires(controls, controlComplement, i);
                MGate and = new MGate(wires, GateType.AND);
                outWires.add(and.getOut());
            }
        }
    }

    public ArrayList<Wire> getOutWires() {
        return outWires;
    }
}
