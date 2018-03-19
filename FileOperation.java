package com.konovalov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperation {

	public static File createFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input name  File is save");
		String nameFile = sc.nextLine();
		File file = new File(nameFile);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Error, dont create");
		}
		return file;
	}

	public static File loadFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input name File is open");
		String nameFile = sc.nextLine();
		File file = new File(nameFile);
		return file;
	}

}
