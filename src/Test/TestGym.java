package Test;

import Java.ContentWriter;
import Java.Main;

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
    Customer k1 = new Customer();
    Customer k2 = new Customer();



    @Test
    public void getDataFromFileTest() {
        FileReader fr = new FileReader();
        List<Customer> customerList = fr.getDataFromFile(filePath);
        k1 = customerList.get(0);
        k2 = customerList.get(1);
        assertTrue(k1.getName().equals("Mimi Santana"));
        assertFalse(k2.getPersonID().equals("5"));

    }

    @Test
    public void isValidMemberTest() {
        Customer k1 = new Customer("123456", "Kalle Anka", LocalDate.of(2018,4,12));
        Customer k2 = new Customer("654321","Gordon Freeman",LocalDate.of(2020,7,12));
        Boolean isMember = Main.isValidMember(k1,true);
        assertFalse(isMember);
        Boolean isMember2 = Main.isValidMember(k2,true);
        assertTrue(isMember2);

    }

    @Test
    public void writeDataToFileTest() {
        ContentWriter cw = new ContentWriter();
        cw.test=true; // så att inte utskriften körs.

        Customer k2 = new Customer("199004079900", "Mimi Santana", LocalDate.of(2020, 4, 7));

        cw.writeDataToFile(newFilePath, k2);
        try (Scanner sc = new Scanner(new File(newFilePath)).useDelimiter(",|\n")) {
           assertTrue(sc.next().equals("Kund: Mimi Santana"));
            BufferedWriter clear = new BufferedWriter(new PrintWriter(new File(newFilePath)));
            clear.write(""); // skriver över filen med blanksteg
        }
        catch (FileNotFoundException e){
            System.out.println("Testfilen kan inte hittas :-(");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
