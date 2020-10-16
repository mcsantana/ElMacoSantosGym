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

public class GymDemo {
    ContentWriter cw = new ContentWriter();
    List<Customer> customerList;
    String filePath = "src/Java/customers.txt";
    String newFilePath = "src/Java/LoyaltyProgram";

    public GymDemo() {

        FileReader fr = new FileReader();
        customerList = fr.getDataFromFile(filePath);
        String input = JOptionPane.showInputDialog("Ange namn eller personummer för den medlem du söker");
        printMember(input);

    }

    public static void main(String[] args) {
        new GymDemo();


    }

    public static Boolean IsValidMember(Customer customer, LocalDate today) {

        return customer.getLastPayment().plusYears(1).isAfter(today); // tar datumet från filen, lägger på ett år, jämför om det är senare än idag
    }

    public Customer SearchCustomerList(String userInput) {

        for (Customer i : customerList) {
            if (i.getName().equalsIgnoreCase(userInput) || i.getPersonID().equalsIgnoreCase(userInput)) {
                return i; // returnerar i om userInput matchar listan
            }
        }
        return null; // returnerar null om customer inte hittas
    }

    public void printMember(String userInput) {
        if (userInput == null || userInput.trim().equalsIgnoreCase("exit")) {
            JOptionPane.showMessageDialog(null, "Programmet avslutas");
            System.exit(0); // programmet avslutas om input kan tolkas som exit eller null tex anv trycker cancel.
        }
        Customer customer = SearchCustomerList(userInput.trim());
        if (customer == null) { // om customer är null
            if (userInput.isBlank()) { // om userInput är blankt
                JOptionPane.showMessageDialog(null, "Din input kunde inte tolkas");
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Varning!\n" + userInput + " finns inte i registret för nuvarande \neller tidgare medlemmar",
                    "OBEHÖRIG MEDLEM", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        } else {
            if (IsValidMember(customer, LocalDate.now())) {
                JOptionPane.showMessageDialog(null, customer.getName() + " Är medlem.\n" +
                        "Årsavgiften för medlemskapet betalades: " + customer.getLastPayment());
                cw.writeDataToFile(newFilePath, customer,false);


            } else JOptionPane.showMessageDialog(null, customer.getName() + " har ett ogiltigt medlemskap." +
                    "\nmedlemskapet gick ut: " + customer.getLastPayment().plusYears(1));

        }

    }

}
