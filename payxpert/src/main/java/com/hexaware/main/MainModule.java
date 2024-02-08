package com.hexaware.main;

import java.util.Scanner;
import com.hexaware.controller.*;

public class MainModule {

    final static String PASSWORD = "AdminPass";
    // ANSI color codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws Exception {
        IControllerservice view = new ControllerImpl();

        System.out.println("Welcome! Please enter the password:");
        Scanner sc = new Scanner(System.in);
        String passwd = sc.next();

        if (passwd.equals(PASSWORD)) {
            String ch;
            do {
                view.mainMenu();

                System.out.println(ANSI_GREEN + "Press Y to continue or N to exit" + ANSI_RESET);
                ch = sc.next();
            } while ("Y".equalsIgnoreCase(ch));
        } else {
            System.out.println(ANSI_RED + "Invalid password" + ANSI_RESET);
        }
        sc.close();
    }
}
