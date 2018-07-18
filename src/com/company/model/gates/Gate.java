package com.company.model.gates;

public class Gate {
    private Wire input1;
    private Wire input2;
    private Wire out;
    private GateType type;
    private int id;

    public Gate(Wire input1, Wire input2, GateType type, int id) {
        this(type, id);
        connectInputs(input1, input2);
    }

    public void connectInputs(Wire input1, Wire input2) {
        this.input1 = input1;
        this.input2 = input2;
        input1.addToDriving(this);
        input2.addToDriving(this);
        act();
    }

    public Gate(GateType type, int id) {
        this.id = id;
        this.out = new Wire();
        this.type = type;
    }

    public void act() {
        boolean A = input1.getState();
        boolean B = input2.getState();
        boolean result;
        switch (type) {
            case AND:
                result = A && B;
                break;
            case OR:
                result = A || B;
                break;
            case NAND:
                result = !(A && B);
                break;
            case NOR:
                result = !(A || B);
                break;
            case XOR:
                result = (A && !B) || (!A && B);
                break;
            case NXOR:
                result = (A && B) || (!A && !B);
                break;
            case NOT:
                result = !A;
                break;
            default:
                result = false;
        }
        if (result != out.getState()){
            out.setState(result);
        }
    }

    public Wire getOut(){
        return out;
    }

    public int getId() {
        return id;
    }
}

