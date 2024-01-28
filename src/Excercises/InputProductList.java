package Excercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputProductList {

	public static ArrayList<String> readFile(String folderPath) throws IOException {
		ArrayList<String> lists = new ArrayList<>();
		// FileReader F= new
		// FileReader(System.getProperty("user.dir")+File.separator+"input.txt");

		//String folderPath = "C:\\Mohammed\\Project\\developWS\\Learnings\\input.txt";
		FileReader F = new FileReader(folderPath);

		BufferedReader bf = new BufferedReader(F);
		String line;
		while ((line = bf.readLine()) != null) {
			lists.add(line);
		}

		return lists;
	}

	public static ArrayList<String> whichFormat() throws IOException {

		Scanner scanner = new Scanner(System.in);
		ArrayList<String> userInput = new ArrayList<String>();
		String path;
		
		System.out.println("Choose an option:");
		System.out.println("1. Upload");
		System.out.println("2. Enter something else");

		
		int choice = scanner.nextInt();
		scanner.nextLine(); 

		
		switch (choice) {
		case 1:
			System.out.print("Enter file path to upload: ");
			path=scanner.nextLine();
			
			userInput = readFile(path);
			
			break;
		case 2:
			System.out.print("Enter Product details.Type 'End' to finish ");

			while (true) {
				 String inputLine = scanner.nextLine();

				if (inputLine.equalsIgnoreCase("END")) {
					System.out.println("Thanks for the Product details");
					break;
				} else {
					userInput.add(inputLine);
					System.out.println(userInput.size()+" item added");
				}

			}

			break;
		default:
			System.out.println("Invalid choice. Please choose 1 or 2.");
			break;
		}

		scanner.close();

		return userInput;
	}

	

}
