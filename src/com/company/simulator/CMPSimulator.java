package com.company.simulator;

import com.company.Helper;
import com.company.model.combinatorial.Comparator;
import com.company.model.gates.Wire;

import java.util.ArrayList;
import java.util.Scanner;

public class CMPSimulator {
    private static ArrayList<Wire> A = Helper.initWires(Helper.BITS_32);
    private static ArrayList<Wire> B = Helper.initWires(Helper.BITS_32);
    private static Comparator c = new Comparator(A, B);
    private static Scanner sc = new Scanner(System.in);

    public static void simulateCMP(){
        System.out.println("Welcome to Can The Machine Spot the Difference 2018! (Comparator simulator)");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            System.out.print("Enter the first number to check for equality: ");
            Helper.intToWires(sc.nextInt(), A);
            System.out.print("Enter the second number to check for equality: ");
            Helper.intToWires(sc.nextInt(), B);
            System.out.println("Your result: " + c.getOut().getState());
            System.out.println();
            System.out.print("0 to quit or any number to try again: ");
            if(sc.nextInt() == 0){
                break;
            }

        }
    }
}
