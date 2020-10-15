package Java;

import javax.swing.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Mimi Santana
 * Date: 2020-10-14
 * Time: 11:15
 * Project: GymDemo
 * Copyright: MIT
 */

public class Main {
    List<Customer> customerList = new ArrayList<>();
    String filePath = "/Users/mimisantana/Desktop/Javautveckling/GymDemo/src/Java/customers.txt";
    String newFilePath = "src/Java/LoyaltyProgram";

    public Main() {

        FileReader fr = new FileReader();
        customerList = fr.getDataFromFile(filePath);
        String input = JOptionPane.showInputDialog("Ange namn eller personummer för medlem du söker");
        printMember(input);


    }

    public static void main(String[] args) {

        Main test = new Main();

    }

    public static Boolean isValidMember(Customer customer, boolean test) {
        Boolean memberTest = test;
        Boolean isMember = false;
        if (!memberTest) isMember = customer.getLastPayment().plusYears(1).isAfter(LocalDate.now());
        if (memberTest) isMember = customer.getLastPayment().plusYears(1).isAfter(LocalDate.parse("2020-10-10"));
        return isMember;
    }

    public Customer checkMemberRegister(String userInput) {
        for (Customer i : customerList) {
            if (i.getName().equalsIgnoreCase(userInput) || i.getPersonID().equalsIgnoreCase(userInput)) {
                return i;
            }
        }
        return null;
    }

    public void printMember(String userInput) {
        if (userInput == null) {
            JOptionPane.showMessageDialog(null, "Programmet avslutas");
            System.exit(0);
        }
        Customer customer = checkMemberRegister(userInput.trim());
        if (customer == null) {
            if (userInput.isBlank()) {
                JOptionPane.showMessageDialog(null, "Din input kunde inte tolkas");
            }
            JOptionPane.showMessageDialog(null, "Varning!\n" + userInput + " finns inte i registret för nuvarande \neller tidgare medlemmar",
                    "OBEHÖRIG MEDLEM", JOptionPane.WARNING_MESSAGE);
        } else {
            if (isValidMember(customer, false) == true) {
                JOptionPane.showMessageDialog(null, customer.getName() + " Är medlem.\n" +
                        "Årsavgiften för medlemskapet betalades: " + customer.getLastPayment());
                ContentWriter cw = new ContentWriter();
                cw.writeDataToFile(newFilePath, customer);

            } else JOptionPane.showMessageDialog(null, customer.getName() + " har ett ogiltigt medlemskap." +
                    "\nmedlemskapet gick ut: " + customer.getLastPayment().plusYears(1));

        }

    }

}
