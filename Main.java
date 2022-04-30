package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println((char) 27 + "[36mThis program will help Уou find and record a valid IP address. Enter something: " + (char)27 + "[0m");
        String ip = in.nextLine();
        boolean IpFind;
        int quantity = 0;                                                                //счётчик

        Pattern pattern = Pattern.compile("\\b(((25[0-5]\\.)|(2[0-4]\\d\\.)|(1[0-9]{2}\\.)|([1-9][0-9]\\.)|([0-9]\\.)){3})((25[0-5])|(2[0-4]\\d)|(1[0-9]{2})|([1-9][0-9])|([0-9]))\\b");
        Matcher match = pattern.matcher(ip);
        try(FileWriter fw = new FileWriter("ip.txt")){
            do {
                IpFind = match.find();
                if (IpFind) {
                    fw.write(match.group().replace(" ", "") + "\n"); //получает коллекцию групп, соответствующих регулярному выражению
                    System.out.println((char) 27 + "[33mIP address recorded. " + (char)27 + "[0m");
                    fw.flush();                                                          //записывает то, что уже передал с помощью метода write()
                    quantity++;
                }
                if(!IpFind & quantity == 0) {                                             //проверка истинности и совпадений
                    System.out.println((char) 27 + "[31mNo valid IP address found. " + (char)27 + "[0m");
                    fw.write("There is no valid IP address here.");
                }
            }
            while (IpFind);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
