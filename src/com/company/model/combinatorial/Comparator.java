package com.company.model.combinatorial;

import com.company.Helper;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.MGate;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class Comparator {
    private Wire out;

    public Comparator(ArrayList<Wire> A, ArrayList<Wire> B){
        try {
            checkEqualSizes(A, B);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        constructComparator(A, B);
    }

    private void checkEqualSizes(ArrayList<Wire> A, ArrayList<Wire> B) throws IllegalArgumentException {
        if (A.size() != B.size()){
            throw new IllegalArgumentException("Unequal Argument Sizes!");
        }
    }

    private void constructComparator(ArrayList<Wire> A, ArrayList<Wire> B){
        ArrayList<Wire> wires = new ArrayList<>();
        for(int i = 0; i < A.size(); i++){
            Gate xor = new Gate(A.get(i), B.get(i), GateType.XOR, Helper.setID());
            wires.add(xor.getOut());
        }
        MGate nor = new MGate(wires, GateType.NOR);
        out = nor.getOut();
    }

    public Wire getOut() {
        return out;
    }
}
