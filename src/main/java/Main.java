import nsu.oop.marketplace.inet.Inet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Type any number:");
        Scanner in = new Scanner(System.in);
        Inet object = new Inet(in.nextInt());
        System.out.println("Your number is " + object.getA());
    }
}
