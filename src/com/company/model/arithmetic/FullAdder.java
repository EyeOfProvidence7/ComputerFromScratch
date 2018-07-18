package com.company.model.arithmetic;

import com.company.Helper;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.Wire;

public class FullAdder {
    private Wire sum;
    private Wire carryOut;

    public FullAdder(Wire A, Wire B, Wire carryIn){
        HalfAdder ha1 = new HalfAdder(A, B);
        HalfAdder ha2 = new HalfAdder(ha1.getSum(), carryIn);
        Gate or = new Gate(ha1.getCarry(), ha2.getCarry(), GateType.OR, Helper.setID());
        sum = ha2.getSum();
        carryOut = or.getOut();
    }

    public Wire getSum() {
        return sum;
    }

    public Wire getCarryOut() {
        return carryOut;
    }
}
