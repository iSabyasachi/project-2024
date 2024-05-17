package org.example.domain.office;

public class Printer {
    Printer(){
        System.out.println("Reset printer ...");
    }

    public static void printNoReset(){
        System.out.println("Printing (no reset) ..." + Printer.class.hashCode());
    }

    public void printReset(){
        System.out.println("Printing (with reset) ..."+ Printer.class.hashCode());
    }

    public static void main(String[] args) {
        // Instance method
        Runnable p1 = new Printer()::printReset;
        Runnable p2 = () -> new Printer().printReset();
        /*Printer constructor is called every time lambda(p2.run) is called,
        * whereas, Printer constructor is called single time at the time of declaration.*/
        System.out.println("p1:");p1.run();
        System.out.println("p1:");p1.run();
        System.out.println("p2:");p2.run();
        System.out.println("p2:");p2.run();
        System.out.println("p1:");p1.run();
        System.out.println("p2:");p2.run();

        //Static method
        System.out.println("------------");
        Runnable p3 = Printer::printNoReset;
        Runnable p4 = () -> Printer.printNoReset();
        System.out.println("p3:");p3.run();
        System.out.println("p3:");p3.run();
        System.out.println("p4:");p4.run();
        System.out.println("p4:");p4.run();
        System.out.println("p3:");p3.run();
        System.out.println("p4:");p4.run();
    }
}
