package com.konovalov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.print.attribute.standard.RequestingUserName;

public class Group implements Army {
	private Student[] array = new Student[10];

	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student[] getArray() {
		return array;
	}

	public void add(Student student) throws MyException {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = student;
				return;
			}
		}

		throw new MyException();
	}

	public void addNewStudent() {
		Student newStudent = new Student();

		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = newStudent;
				break;
			}
		}
		Scanner sc = new Scanner(System.in);
		Scanner scc = new Scanner(System.in);
		try {
			System.out.println("Input Surname student");
			newStudent.setSurname(sc.nextLine());
			System.out.println("Input Name  student");
			newStudent.setName(sc.nextLine());
			System.out.println("Input year of birth");
			newStudent.setYearOfBirth(sc.nextInt());
			System.out.println("Input Sex  student, if 1 Man, if 0 Girl");
			boolean a = true;
			int b = sc.nextInt();
			if (b != 1 && b != 0) {
				throw new IllegalArgumentException();
			}
			if (b == 1) {
				a = true;
			}
			if (b == 0) {
				a = false;
			}
			newStudent.setSex(a);

			System.out.println("Input faculty  student");
			newStudent.setFaculty(scc.nextLine());
			System.out.println("Input rank student");
			newStudent.setRank(scc.nextInt());

		} catch (IllegalArgumentException e) {
			System.out.println("Error, negative number Sex");
			;

		}
	}

	public void delete(Student student) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == student) {
				array[i] = null;
			}
		}
	}

	public Student search(String surname) {
		Student result = null;
		for (int i = 0; i < 10; i++) {
			if (array[i] != null && array[i].getSurname().equals(surname)) {
				result = array[i];

			}
			result = array[i];

		}
		return result;
	}

	public void sort() {
		int[] checkArray = checkSort();
		Student temp = null;

		if (checkArray[0] == 0) {
			Arrays.sort(array, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: a.getSurname().compareTo(b.getSurname()));
			if (checkArray[1] == 1) {
				for (int i = 0; i < array.length / 2; i++) {
					temp = array[i];
					array[i] = array[array.length - i - 1];
					array[array.length - i - 1] = temp;
				}

			}

		}
		if (checkArray[0] == 1) {
			Arrays.sort(array, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: a.getName().compareTo(b.getName()));
			if (checkArray[1] == 1) {

				for (int i = 0; i < array.length / 2; i++) {
					temp = array[i];
					array[i] = array[array.length - i - 1];
					array[array.length - i - 1] = temp;
				}
			}
		}
		if (checkArray[0] == 2 && checkArray[1] == 0) {
			Arrays.sort(array, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: a.getYearOfBirth() - b.getYearOfBirth());
		}
		if (checkArray[0] == 2 && checkArray[1] == 1) {
			Arrays.sort(array, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: -1 * a.getYearOfBirth() - b.getYearOfBirth());
		}
	}

	public int[] checkSort() {
		Scanner sc = new Scanner(System.in);
		Scanner scc = new Scanner(System.in);
		int[] result = new int[] { 0, 0 };
		System.out.println("Input number is parametr sorting, default number 0");
		System.out.println("is 0 if sorting Surname ");
		System.out.println("is 1 if sorting Name ");
		System.out.println("is 2 if sorting year of birth ");
		try {
			result[0] = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error, input negative number");
		}
		System.out.println("Input number is from to, default number 0 ");
		System.out.println("is `0` if sort from smallest to largest or `1` vice versa");
		try {
			result[1] = scc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error, input negative number");
		}
		return result;
	}

	public static int checkYear() {
		Date date = new Date();
		SimpleDateFormat stf = new SimpleDateFormat("yyyy");
		String res = stf.format(date);
		int result = 0;
		result = Integer.valueOf(res);
		return result;
	}

	public Student[] getArrStudentArmy() {

		int m = 0;
		int n = 0;
		try {
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					m++;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Null point arrays student");
		}
		Student[] arr = new Student[m];
		try {
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					arr[n] = array[i];
					n++;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Null point arrays student");
		}
		return arr;
	}

	public void fileSave() {
		File file = FileOperation.createFile();
		try (PrintWriter pw=new PrintWriter(file)){
			for (int i = 0; i < array.length; i++) {
				if (array[i]!=null) {					
					pw.println(array[i].toStringNew());
				}
			}
			
		} catch (FileNotFoundException | NullPointerException e) {
			System.out.println("Error, dont save Group in file");
		}
		
	}
	public void newGroupStudent() {				
		File file= FileOperation.loadFile();		
		String temp="";
				try (BufferedReader br=new BufferedReader(new FileReader(file))){
					for (int i=0; (temp = br.readLine()) != null;i++) {
						array[i]=newStudentFromFile(temp);
					}			
		} catch (IOException|ArrayIndexOutOfBoundsException e) {
			System.out.println("Error, dont write is File");		
		}		
		
	}
	public Student newStudentFromFile(String text) {
		String []textArray=text.split(" ");
		Student newStudent = new Student();
		int tempInt=0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = newStudent;
				break;
			}
		}
		
		try {			
			newStudent.setSurname(textArray[0]);			
			newStudent.setName(textArray[1]);
			if (isNumeric(textArray[2])==true) {
				tempInt=Integer.parseInt(textArray[2]);
				newStudent.setYearOfBirth(tempInt);
			}		
			newStudent.setSex(sexTextToBool(textArray[3]));
			newStudent.setFaculty(textArray[4]);
			if (isNumeric(textArray[5])==true) {
				tempInt=Integer.parseInt(textArray[5]);
				newStudent.setRank(tempInt);
			}	
		} catch (IllegalArgumentException e) {
			System.out.println("Error, negative number Sex");			
		}
		
		return newStudent;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    int i = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException e)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static boolean sexTextToBool(String sex) {
		try{	if (sex.equals("male")) {
			return true;
		} else if(sex.equals("female")) {		
		return false;
		}
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Error,format. defaut sex is male ");
		}
			return true;
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				sb.append(array[i]);
				sb.append(System.lineSeparator());
			}
		}

		return sb.toString();
	}

}
