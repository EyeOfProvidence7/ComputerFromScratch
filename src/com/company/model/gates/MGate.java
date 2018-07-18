package com.company.model.gates;

import com.company.Helper;

import java.util.ArrayList;

public class MGate {
    private Wire out;
    private GateType type;


    public MGate(ArrayList<Wire> inputs, GateType type){
        try {
            setGateType(type);
            checkInputSize(inputs);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        setupGates(inputs);
    }

    public void setGateType(GateType type) throws IllegalArgumentException {
        if(type == GateType.NOT){
            throw new IllegalArgumentException("Invalid gate type for an MGate");
        }
        this.type = type;
    }

    private void checkInputSize(ArrayList<Wire> inputs){
        if (inputs.size() >  Helper.powerOfTwo(22)){
            throw new IllegalArgumentException("Inputs too many to make this MGate. Consider using an LMGate.");
        }
    }

    private void setupGates(ArrayList<Wire> inputs) throws IllegalArgumentException{
        if(inputs.size() < 2){
            throw new IllegalArgumentException("Invalid array size for MGate");
        }
        ArrayList<Gate> gates = new ArrayList<>();
        boolean useNotGate = false;
        if(type == GateType.NAND || type == GateType.NOR || type == GateType.NXOR){
            useNotGate = true;
        }
        switch(type){
            case NAND:
                type = GateType.AND;
                break;
            case NOR:
                type = GateType.OR;
                break;
            case NXOR:
                type = GateType.XOR;
                break;
            default:
        }
        gates.add(new Gate(inputs.get(0), inputs.get(1), type, Helper.setID()));
        for(int i = 2; i < inputs.size() ; i++){
            gates.add(new Gate(gates.get(i - 2).getOut(), inputs.get(i), type, Helper.setID()));
        }
        Wire lastGateOuput = gates.get(gates.size()-1).getOut();
        if(useNotGate){
            Gate not = new Gate(lastGateOuput, new Wire(), GateType.NOT, Helper.setID());
            out = not.getOut();
        } else {
            out = lastGateOuput;
        }
    }

    public Wire getOut() {
        return out;
    }
}
