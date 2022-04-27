package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("This program will help Уou find and record a valid IP address. Enter something:");

        String ip = in.nextLine();
        boolean IpFind;
        int matches = 0;                                                                 //счётчик

        Pattern pattern = Pattern.compile("\\b(((25[0-5]\\.)|(2[0-4]\\d\\.)|(1[0-9]{2}\\.)|([1-9][0-9]\\.)|([0-9]\\.)){3})((25[0-5])|(2[0-4]\\d)|(1[0-9]{2})|([1-9][0-9])|([0-9]))\\b");
        Matcher match = pattern.matcher(ip);
        try( FileWriter fw = new FileWriter("ip.txt")){
            do {
                IpFind = match.find();
                if (IpFind) {
                    fw.write(match.group().replace(" ", "") + "\n"); //получает коллекцию групп, соответствующих регулярному выражению
                    System.out.println("IP address recorded.");
                    fw.flush();                                                          //записывает то, что уже передал с помощью метода write()
                    matches++;
                }
                if(!IpFind & matches == 0) {                                             //проверка истинности и совпадений
                    System.out.println("No valid IP address found.");
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