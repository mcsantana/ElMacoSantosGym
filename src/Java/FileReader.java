package Java;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * created by Mimi Santana
 * Date: 2020-10-14
 * Time: 10:52
 * Project: GymDemo
 * Copyright: MIT
 */
public class FileReader {


    public List<Customer> getDataFromFile(String path) {
        List<Customer> listOfCustomers = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(path)).useDelimiter(",|\n")) {

            String id = "";
            String name = "";
            LocalDate lastPayment;
            while (sc.hasNext()) {
                id = sc.next().trim();
                name = sc.next().trim();
                lastPayment = LocalDate.parse(sc.next());
                listOfCustomers.add(new Customer(id, name, lastPayment));

            }


        } catch (FileNotFoundException e) {
            System.out.println("Filen gick inte att läsa :-( " + e.getMessage());
        } catch (NoSuchElementException e) {
        }

        return listOfCustomers;
    }
}
