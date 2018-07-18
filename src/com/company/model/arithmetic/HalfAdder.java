package com.company.model.arithmetic;

import com.company.Helper;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.Wire;

public class HalfAdder {
    private Wire sum;
    private Wire carry;

    public HalfAdder(Wire A, Wire B){
        Gate g1 = new Gate(A, B, GateType.XOR, Helper.setID());
        Gate g2 = new Gate(A, B, GateType.AND, Helper.setID());
        sum = g1.getOut();
        carry = g2.getOut();
    }

    public Wire getSum() {
        return sum;
    }

    public Wire getCarry() {
        return carry;
    }
}
