package project2COSC455;

import java.io.*;
import java.util.Scanner;

public class LexicalAnalyzer {
	static Scanner input = new Scanner(System.in); //scanner to be used in program
	static String[] letter = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	static String[] digit = {"0","1","2","3","4","5","6","7","8","9"};
	static Scanner sc;
	static String[] in;
	public static void main(String[] args) {
		//Scanner sc;
		String fileName;
		File file;
		//String[] in;
		int count = -1;
		int lineCount = 0;
		
		try {
			System.out.print("Enter the file name for the program: ");
			fileName = input.next();
			file = new File(fileName);
			sc = new Scanner(file);
			
			System.out.println();
			
			while(sc.hasNextLine()) {
				lineCount++;
				String s = sc.nextLine();
				if(s.contains("//"))
					s = s.split("//")[0];
			
				//for(char c: s.toCharArray()) {
					//System.out.println(c);
				//}
				
				if(!s.contains("program"))
					s = s.replaceFirst("\\s+", "");
				
				in = s.split("(?=[;.()])|(?<=[;.()])|\\s+");
				
				if(in.length == 1 && !in[0].matches("\\w+")) {
					s = sc.nextLine();
					in = s.split("(?=[;.()])|(?<=[;.()])|\\s+");
				}
				
				if(in[0].equals("program")) {
					in = s.split("(?=[:;.()])|(?<=[:;.()])|\\s+");
				}
				System.out.println("Line: " + s);
		
				
				for(int i = 0; i < in.length; i++) {
					count++;
					System.out.println(position(count, lineCount));
					System.out.println("KIND: " + kind(in[i]));
					System.out.println(value(in[i]));
					System.out.println("Next: " + getNext(i));
					System.out.println();
				}
				//next();

			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//next method
	public static void next() {
		for(int i = 0; i <= in.length; i++) {
			if(i == in.length) {
				if(sc.hasNextLine()) {
					sc.nextLine();
				}
			} else{
				getNext(i);
			}
		}
	}
	
	public static String getNext(int i) {
		if(i < in.length - 1)
			return in[i + 1];
		return "can't read next symbol";
	}
	public static String position(int count, int line) {
		return "POSITION: " + count + " : " + line;
	}
	
	public static String value(String token) {
		String v = token;
		String[] syntax = {"or", "and", "not", "false", "true", "end", "bool", "int", "program", ";", ":=", "if", "then", 
                "else", "fi", "while", "do", "od", "print", "_", "=", "<", "+", "-", "*", "/", "(", ")",";", ":=", "!=", ":"};
		
		for(int i = 0; i < syntax.length; i++) {
			if(syntax[i].equals(token)) 
				v = "null";
		}
		
		return "VALUE: " + v;
	}
	
	public static String kind(String token) {
		return checkTokenKind(token);
	}
	
	public static String checkTokenKind(String token) {
		String[] syntax = {"or", "and", "not", "false", "true", "end", "bool", "int", "program", ";", ":=", "if", "then", 
                "else", "fi", "while", "do", "od", "print", "_", "=", "<", "+", "-", "*", "/", "(", ")",";", ":=", "!=", ":"};
		
		for(int i = 0; i < syntax.length; i++) {
			if(token.equals(syntax[i]))
				return syntax[i];
			if(token.equals("end"))
				return "end-of-text";
		}
		if(token.matches("-?\\d+(\\.\\d+)?")){
			return "NUM";
		}
		
		return "ID";
	}
	
	public static boolean checkNumber(String token) {
		return token.matches("-?\\d+(\\.\\d+)?");
	}

}
