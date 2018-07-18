package com.company.model.general;

import com.company.Helper;
import com.company.model.arithmetic.FullAdder;
import com.company.model.combinatorial.Decoder;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.MGate;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class ALUSlice {
    private Wire out;
    private Wire carryOut;

    //controls: 0 = F0, 1 = F1, 2 = ENA, 3 = ENB, 4 = INVA
    public ALUSlice(ArrayList<Wire> controls, Wire carryIn, Wire A, Wire B){
        //Decoder setup
        ArrayList<Wire> decInput = new ArrayList<>();
        decInput.add(controls.get(0));
        decInput.add(controls.get(1));
        Decoder dec = new Decoder(decInput);
        ArrayList<Wire> decoderOut = dec.getOutWires();

        //Logical unit input setup
        Gate andA = new Gate(A, controls.get(2), GateType.AND, Helper.setID());
        Gate andB = new Gate(B, controls.get(3), GateType.AND, Helper.setID());
        Gate xor = new Gate(controls.get(4), andA.getOut(), GateType.XOR, Helper.setID());

        //logical unit and adder setup
        Gate andAB = new Gate(xor.getOut(), andB.getOut(), GateType.AND, Helper.setID());
        Gate orAB = new Gate(xor.getOut(), andB.getOut(), GateType.OR, Helper.setID());
        Gate notB = new Gate(andB.getOut(), new Wire(), GateType.NOT, Helper.setID());
        FullAdder fa = new FullAdder(andB.getOut(), xor.getOut(), carryIn);
        ArrayList<Gate> andSelectGates = new ArrayList<>();
        andSelectGates.add(new Gate(andAB.getOut(), decoderOut.get(0), GateType.AND, Helper.setID()));
        andSelectGates.add(new Gate(orAB.getOut(), decoderOut.get(1), GateType.AND, Helper.setID()));
        andSelectGates.add(new Gate(notB.getOut(), decoderOut.get(2), GateType.AND, Helper.setID()));
        andSelectGates.add(new Gate(fa.getSum(), decoderOut.get(3), GateType.AND, Helper.setID()));
        Gate andCarryOut = new Gate(fa.getCarryOut(), decoderOut.get(3), GateType.AND, Helper.setID());

        //outputSetup
        ArrayList<Wire> orInputs = new ArrayList<>();
        andSelectGates.forEach(gate -> orInputs.add(gate.getOut()));
        MGate orOut = new MGate(orInputs, GateType.OR);
        this.out = orOut.getOut();
        this.carryOut = andCarryOut.getOut();
    }

    public Wire getOut() {
        return out;
    }

    public Wire getCarryOut() {
        return carryOut;
    }
}
