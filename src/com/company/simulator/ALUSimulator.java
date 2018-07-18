package com.company.simulator;

import com.company.Helper;
import com.company.model.gates.Wire;
import com.company.model.general.ALU;

import java.util.ArrayList;
import java.util.Scanner;

public class ALUSimulator {
    private static ArrayList<Wire> controls = Helper.initWires(Helper.ALU_CONTROL_NUM);
    private static ArrayList<Wire> A = Helper.initWires(Helper.BITS_32);
    private static ArrayList<Wire> B = Helper.initWires(Helper.BITS_32);
    private static Wire carryIn = new Wire();
    private static ALU alu = new ALU(controls, carryIn, A, B);
    private static Scanner sc = new Scanner(System.in);
    private static String options[] = new String[]{"A", "B", "NOT A", "NOT B", "A + B", "A + B + 1", "A + 1", "B + 1", "B - A", "B - 1", "-A", "A AND B", "A OR B", "0", "1", "-1"};

    public static void simulateALU(){
        System.out.println("Welcome to ALU Simulator 2018");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            displayOptions();
            System.out.println();
            System.out.print("Choose the function you want to perform: ");
            int choice = Helper.getValidChoice(0, options.length - 1);
            switch(choice){
                case 0:
                    Helper.intToWires(5, controls);
                    carryIn.setState(false);
                    break;
                case 1:
                    Helper.intToWires(9, controls);
                    carryIn.setState(false);
                    break;
                case 2:
                    Helper.intToWires(21, controls);
                    carryIn.setState(false);
                    break;
                case 3:
                    Helper.intToWires(14, controls);
                    carryIn.setState(false);
                    break;
                case 4:
                    Helper.intToWires(15, controls);
                    carryIn.setState(false);
                    break;
                case 5:
                    Helper.intToWires(15, controls);
                    carryIn.setState(true);
                    break;
                case 6:
                    Helper.intToWires(7, controls);
                    carryIn.setState(true);
                    break;
                case 7:
                    Helper.intToWires(11, controls);
                    carryIn.setState(true);
                    break;
                case 8:
                    Helper.intToWires(31, controls);
                    carryIn.setState(true);
                    break;
                case 9:
                    Helper.intToWires(27, controls);
                    carryIn.setState(false);
                    break;
                case 10:
                    Helper.intToWires(23, controls);
                    carryIn.setState(true);
                    break;
                case 11:
                    Helper.intToWires(12, controls);
                    carryIn.setState(false);
                    break;
                case 12:
                    Helper.intToWires(13, controls);
                    carryIn.setState(false);
                    break;
                case 13:
                    Helper.intToWires(1, controls);
                    carryIn.setState(false);
                    break;
                case 14:
                    Helper.intToWires(3, controls);
                    carryIn.setState(true);
                    break;
                case 15:
                    Helper.intToWires(19, controls);
                    carryIn.setState(false);
                    break;
            }
            runALU();
            System.out.print("0 to go back or any number to choose another function: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void displayOptions(){
        for(int i = 0; i < options.length; i++){
            System.out.println(i + ": " + options[i]);
        }
    }

    private static void runALU() {
        while(true){
            System.out.println();
            System.out.print("Enter A ");
            Helper.intToWires(sc.nextInt(), A);
            System.out.print("Enter B: ");
            Helper.intToWires(sc.nextInt(), B);
            System.out.print("Before: ");
            Helper.printWireValuesBinary(A);
            System.out.print("        ");
            Helper.printWireValuesBinary(B);
            System.out.println();
            System.out.print("After : ");
            Helper.printWireValuesBinary(alu.getOut());
            System.out.println("Your result: " + Helper.wiresToInt(alu.getOut()));
            System.out.println();
            System.out.print("0 to go back or any number to try again: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }
}
