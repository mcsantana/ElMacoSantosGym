package Java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * created by Mimi Santana
 * Date: 2020-10-13
 * Time: 22:10
 * Project: GymDemo
 * Copyright: MIT
 */
public class Customer {

    private String personID;
    private String name;
    private LocalDate lastPayment;

    public Customer(String personID, String name, LocalDate lastPayment) {
        this.personID = personID;
        this.name = name;
        this.lastPayment = lastPayment;
    }

    public Customer() {

    }

    public String getPersonID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }


}
