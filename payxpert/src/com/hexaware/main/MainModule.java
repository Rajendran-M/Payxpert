package com.hexaware.main;

import java.util.Scanner;
import com.hexaware.controller.*;

public class MainModule {

	final static String PASSWORD = "AdminPass";

	public static void main(String[] args) throws Exception {
		IControllerservice view = new ControllerImpl();

		System.out.println("welcome user enter password");
		Scanner sc = new Scanner(System.in);
		String passwd = sc.next();

		if (passwd.equals(PASSWORD)) {
			String ch;
			do {
				view.mainMenu();

				System.out.println("press Y to continue or n to exit ");
				ch = sc.next();
			} while ("Y".equals(ch) || "y".equals(ch));

		} else {
			System.out.println("invalid password");
		}
		sc.close();

	}

}
