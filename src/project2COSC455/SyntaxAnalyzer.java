package project2COSC455;

import java.io.File;
import java.util.Scanner;

public class SyntaxAnalyzer {

	static Scanner input = new Scanner(System.in); //scanner to be used in program
	static String[] letter = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	static String[] digit = {"0","1","2","3","4","5","6","7","8","9"};
	static String csym;
	public static void main(String[] args) {
		Scanner sc;
		String fileName;
		File file;
		String[] in;
		
		
		try {
			System.out.print("Enter the file name for the program: ");
			fileName = input.next();
			file = new File(fileName);
			sc = new Scanner(file);
			
			System.out.println();
			
			while(sc.hasNextLine()) {
				String s = sc.nextLine();
				if(s.contains("//")) //removing all comments from being processed
					s = s.split("//")[0];
			
				
				if(!s.contains("program")) //remove all whitespace after program
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
				System.out.println();
				
				for(int i = 0; i < in.length; i++) {
					csym = in[i];
			
					System.out.println("KIND: " + LexicalAnalyzer.kind(in[i]));
					System.out.println("VALUE: " + LexicalAnalyzer.value(in[i]));
					System.out.println("Current Symbol: " + csym());
					System.out.println();
				}

			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		

	
	//current symbol
	public static String csym() {
		return csym;
	}
	
	//match method
	public static void match(String sym) {
		if(csym().equals(sym) || LexicalAnalyzer.checkTokenKind(csym()).equals(sym)) {
			System.out.println("equal");
		} else {
			System.out.println("Seeing " + csym() + " but expected " + sym);
		}
	}
	
	//program matching syntax
	public static void program() {
		match("program");
		match("ID");
		match(":");
		body();
		match("end");	
	}
	
	public static void body() {
		if(csym().equals("int") || csym().equals("bool")) {
			declarations();
		} 
		statements();
	}
	
	public static void declarations() {
		// TODO Auto-generated method stub
		
	}

	public static void statements() {
		// TODO Auto-generated method stub
		
	}

	//AssignmentStatement
	public static void assignmentStatement() {
		
	}
	
	//ConditionalStatement
	public static void conditionalStatement() {
		
	}
	
	//PrintStatement
	public static void printStatement() {
		
	}
	
	//IterativeStatement
	public static void iterativeStatement() {
		
	}
	
	//Term
	public static void term() {
		
	}
	
	//SimpleExpression
	public static void simpleExpression() {
		    
	}


}
