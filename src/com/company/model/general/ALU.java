package com.company.model.general;

import com.company.Helper;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class ALU {
    private ArrayList<Wire> out;

    public ALU(ArrayList<Wire> controls, Wire carryIn, ArrayList<Wire> A, ArrayList<Wire> B){
        try{
            checkArraySize(controls, A, B);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        out = new ArrayList<>();
        constructALU(controls, carryIn, A, B);
    }

    private void checkArraySize(ArrayList<Wire> controls, ArrayList<Wire> A, ArrayList<Wire> B) throws IllegalArgumentException {
        if ((A.size() != Helper.BITS_32) || B.size() != Helper.BITS_32 || controls.size() != 5){
            throw new IllegalArgumentException("Wrong array sizes for inputs to ALU");
        }
    }

    private void constructALU(ArrayList<Wire> controls, Wire carryIn, ArrayList<Wire> A, ArrayList<Wire> B){
        for (int i = 0; i < Helper.BITS_32; i++){
            ALUSlice alu = new ALUSlice(controls, carryIn, A.get(i), B.get(i));
            out.add(alu.getOut());
            carryIn = alu.getCarryOut();
        }
    }

    public ArrayList<Wire> getOut() {
        return out;
    }
}
