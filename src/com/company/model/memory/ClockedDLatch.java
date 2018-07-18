package com.company.model.memory;

import com.company.Helper;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.Wire;

import java.util.Random;

public class ClockedDLatch {
    private Wire Q;
    private Wire NotQ;

    public ClockedDLatch(Wire D, Wire clock){
        Gate ANDS = new Gate(D, clock, GateType.AND, Helper.setID());
        Gate N = new Gate(D, new Wire(), GateType.NOT, Helper.setID());
        Gate ANDR = new Gate(clock, N.getOut(), GateType.AND, Helper.setID());
        Gate NORS = new Gate(GateType.NOR, Helper.setID());
        Gate NORR = new Gate(GateType.NOR, Helper.setID());
        NotQ = NORS.getOut();
        Q = NORR.getOut();
        NORS.connectInputs(ANDS.getOut(), Q);
        NORR.connectInputs(NotQ, ANDR.getOut());
    }

    public Wire getQ() {
        return Q;
    }
}
