package Java;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * created by Mimi Santana
 * Date: 2020-10-15
 * Time: 09:39
 * Project: GymDemo
 * Copyright: MIT
 */
public class ContentWriter {

    public boolean test;

    public void writeDataToFile(String newFilePath, Customer customer) {
        if (customer == null || newFilePath == null)
            throw new IllegalArgumentException("Ogiltig input");
        LocalDate datum = LocalDate.now();
        try (BufferedWriter contentWriter = new BufferedWriter(new FileWriter((newFilePath), true))) {
            contentWriter.write("Kund: " + customer.getName() + "\nPersonnummer: " + customer.getPersonID() +
                    "\nDatum för besök: " + datum + "\n");
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("ops! kunde inte läsa till filen");
        }
        if (!test)
            JOptionPane.showMessageDialog(null, "Besökslogg har förts på " + customer.getName() + " till PT-lojalitetsprogrammets fil");
    }
}
