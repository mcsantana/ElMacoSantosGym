package Test;

import Java.ContentWriter;
import Java.GymDemo;
import Java.FileReader;
import Java.Customer;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by Mimi Santana
 * Date: 2020-10-13
 * Time: 22:09
 * Project: GymDemo
 * Copyright: MIT
 */
public class TestGym {

    String newFilePath = "src/Test/TestFileOut";
    String filePath = "src/Test/TestFile";
    Customer c1 = new Customer();
    Customer c2 = new Customer();


    @Test
    public void getDataFromFileTest() {

        FileReader fr = new FileReader();

        List<Customer> customerList = fr.getDataFromFile(filePath);

        c1 = customerList.get(0);
        c2 = customerList.get(1);

        assertTrue(c1.getName().equals("Mimi Santana"));
        assertFalse(c2.getPersonID().equals("5"));

    }

    @Test
    public void isValidMemberTest() {

        Customer k1 = new Customer("123456", "Kalle Anka", LocalDate.of(2018,10,16));
        Customer k2 = new Customer("654321", "Gordon Freeman",LocalDate.of(2020,7,6));
        LocalDate today = LocalDate.of(2020,10,16); // berättar för metoden vad dagens datum är.

        Boolean isMember = GymDemo.IsValidMember(k1,today);
        assertFalse(isMember);

        Boolean isMember2 = GymDemo.IsValidMember(k2,today);
        assertTrue(isMember2);

    }

    @Test
    public void writeDataToFileTest() {
        ContentWriter cw = new ContentWriter();

        Customer k2 = new Customer("199004079900", "Mimi Santana", LocalDate.of(2020, 4, 7));
        cw.writeDataToFile(newFilePath, k2,true); // true så att inte utskriften körs i writeDataToFile.

        try (Scanner sc = new Scanner(new File(newFilePath)).useDelimiter(",|\n")) {

            assertTrue(sc.next().equals("Kund: Mimi Santana"));
            assertFalse(sc.next().equals(null));
            BufferedWriter clearContentInFile = new BufferedWriter(new PrintWriter(new File(newFilePath)));
            clearContentInFile.write(""); // skriver över filen med blanksteg, så att filen inte växer

        } catch (FileNotFoundException e) {
            System.out.println("Testfilen kan inte hittas :-(");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
