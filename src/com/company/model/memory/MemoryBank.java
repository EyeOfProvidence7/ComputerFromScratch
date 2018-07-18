package com.company.model.memory;

import com.company.Helper;
import com.company.model.combinatorial.Decoder;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.MGate;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class MemoryBank {
    public static final int SIZE_LIMIT = 10;
    private ArrayList<Wire> out;

    //control values: 0: CS, 1: RD, 2: OE
    public MemoryBank(ArrayList<Wire> dataIn, ArrayList<Wire> address, ArrayList<Wire> controls){
        try{
            checkArraySize(address);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            System.exit(0);
        }
        this.out = new ArrayList<>();
        Decoder d = new Decoder(address);
        ArrayList<Wire> wordSelectLines = d.getOutWires();
        MGate outputEnable = new MGate(controls, GateType.AND);
        Gate NOT = new Gate(controls.get(1), new Wire(), GateType.NOT, Helper.setID());
        Gate writeEnableAND = new Gate(controls.get(0), NOT.getOut(), GateType.AND, Helper.setID());
        ArrayList<Wire> writeGateOuts = getWriteGateOuts(address, wordSelectLines, writeEnableAND);
        buildMemoryGrid(dataIn, address, wordSelectLines, outputEnable, writeGateOuts);
    }

    private void buildMemoryGrid(ArrayList<Wire> dataIn, ArrayList<Wire> address, ArrayList<Wire> wordSelectLines, MGate outputEnable, ArrayList<Wire> writeGateOuts) {
        for(int i = 0; i < dataIn.size(); i++){
            ArrayList<Wire> ORin = new ArrayList<>();
            for(int j = 0; j < Helper.powerOfTwo(address.size()); j++){
                ClockedDLatch dLatch = new ClockedDLatch(dataIn.get(i), writeGateOuts.get(j));
                Gate outputGate = new Gate(wordSelectLines.get(j), dLatch.getQ(), GateType.AND, Helper.setID());
                ORin.add(outputGate.getOut());
            }
            MGate OR = new MGate(ORin, GateType.OR);
            Gate outputEnableGate = new Gate(OR.getOut(), outputEnable.getOut(), GateType.AND, Helper.setID());
            out.add(outputEnableGate.getOut());
        }
    }

    private ArrayList<Wire> getWriteGateOuts(ArrayList<Wire> address, ArrayList<Wire> wordSelectLines, Gate writeEnableAND) {
        ArrayList<Wire> writeGateOuts = new ArrayList<>();
        for(int i = 0; i < Helper.powerOfTwo(address.size()); i++){
            Gate writeGate = new Gate(writeEnableAND.getOut(), wordSelectLines.get(i), GateType.AND, Helper.setID());
            writeGateOuts.add(writeGate.getOut());
        }
        return writeGateOuts;
    }

    private void checkArraySize( ArrayList<Wire> A) throws IllegalArgumentException {
        if (A.size() > SIZE_LIMIT){
            throw new IllegalArgumentException("Wrong array sizes for inputs to Register");
        }
    }

    public ArrayList<Wire> getOut() {
        return out;
    }
}
