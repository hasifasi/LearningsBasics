package Excercises;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

	private static List<String> medicinesList = new ArrayList<String>();
	private static List<String> sugarList = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		// SuperMarket(getCustmerName());

		// onlineStore(getCustmerName());
		
		retailStore(getCustmerName());

	}

	public static ArrayList<HashMap> calculate() throws IOException {

		medicinesList.add("Motrin");
		medicinesList.add("Tylenol");
		medicinesList.add("Syringe");

		sugarList.add("Chocolates");
		sugarList.add("Biscuits");
		sugarList.add("Juice");
		sugarList.add("Pastries");

		ArrayList<HashMap> finalBill = new ArrayList<HashMap>();
		HashMap<String, Double> finalPrice = new HashMap<String, Double>();
		HashMap<String, Double> calcTax = new HashMap<String, Double>();
		int quantity = 0;
		String product = null;
		boolean isImported = false;
		// boolean isMedicine=true;
		double price = 0;
		double calculatedTax;
		Tax t = new Tax();
		InputProductList ipl = new InputProductList();

		ArrayList<String> inputdata = ipl.whichFormat();
		if (inputdata.size() != 0) {
			for (int i = 0; i < inputdata.size(); i++) {
				String[] s = inputdata.get(i).toString().split("/");
				quantity = Integer.parseInt(s[0]);
				product = s[1];
				isImported = Boolean.parseBoolean(s[2]);
				price = Double.parseDouble(s[3]);
				// Tax t= new Tax();

				double productCost = quantity * price;
				boolean isMedicine = medicinesList.contains(product) ? true : false;

				calculatedTax = t.calculateTotalTax(isMedicine, isImported, productCost);
//				System.out.println("calculatedTax: " + calculatedTax);
//				System.out.println("productCost:" + productCost);

				double totalProductCost = productCost + calculatedTax;

//				System.out.println("TotalProductCost : " + totalProductCost);
//				System.out.println("------------------------------------");

				calcTax.put(product, calculatedTax);
				finalPrice.put(product, Math.round(totalProductCost * 100.0) / 100.0);
			}
		} else {
			System.err.println("No products Entered");
		}

		finalBill.add(finalPrice);
		finalBill.add(calcTax);

		return finalBill;
	}

	public static void SuperMarket(String customerName) throws IOException {
		ArrayList<HashMap> alFinalBill = calculate();

		HashMap<String, Double> productPrice = alFinalBill.get(0);
		HashMap<String, Double> taxPrice = alFinalBill.get(1);

		System.out.println("------------------------------------");
		for (Map.Entry<String, Double> entry : productPrice.entrySet()) {
			String product = entry.getKey();
			Double productValue = entry.getValue();

			if (taxPrice.containsKey(product)) {
				Double taxValue = taxPrice.get(product);
				System.out.println(customerName + " bought " + product + " of worth " + productValue + " with "
						+ taxValue + " tax.");
			} else {
				System.out.println("Tax information not found for " + product);
			}

		}
	}

	public static void onlineStore(String customerName) throws IOException {
		ArrayList<HashMap> alFinalBill = calculate();

		HashMap<String, Double> productPrice = alFinalBill.get(0);
		HashMap<String, Double> taxPrice = alFinalBill.get(1);

		printHeader(productPrice,customerName);

		for (Map.Entry<String, Double> entry : productPrice.entrySet()) {
			String product = entry.getKey();
			Double productValue = entry.getValue();

			if (medicinesList.contains(product) && sugarList.contains(product)) {
				if (taxPrice.containsKey(product)) {
					Double taxValue = taxPrice.get(product);
					System.out.println(" The Total cost of the " + product + " is " + productValue + " with " + taxValue
							+ " tax.");
				}
			} else if (sugarList.contains(product)) {
				if (taxPrice.containsKey(product)) {
					Double taxValue = taxPrice.get(product);
					System.out.println(" The Total cost of the " + product + " is " + productValue + " with " + taxValue
							+ " tax.");
				}
			} else if (medicinesList.contains(product)) {
				if (taxPrice.containsKey(product)) {
					Double taxValue = taxPrice.get(product);
					System.out.println(" The Total cost of the " + product + " is " + productValue + " with " + taxValue
							+ " tax.");
				}
			} else {
				if (taxPrice.containsKey(product)) {
					Double taxValue = taxPrice.get(product);
					System.out.println(" The Total cost of the " + product + " is " + productValue + " with " + taxValue
							+ " tax.");
				}
			}
		}
	}
	
	public static void retailStore(String customerName) throws IOException {
		ArrayList<HashMap> alFinalBill = calculate();

		HashMap<String, Double> productPrice = alFinalBill.get(0);
		HashMap<String, Double> taxPrice = alFinalBill.get(1);

		System.out.println("Customer Name: "+customerName);
		for (Map.Entry<String, Double> entry : productPrice.entrySet()) {
			String product = entry.getKey();
			Double productValue = entry.getValue();

			
				if (taxPrice.containsKey(product)) {
					Double taxValue = taxPrice.get(product);
					System.out.println(" The Total cost of the " + product + " is " + productValue + " with " + taxValue
							+ " tax.");
				}
			
			}
		}
	


	private static void printHeader(HashMap<String, Double> productSet,String customerName) {

		boolean isFromSugar = false;
		boolean isFromMed = false;

		Set<String> keys = productSet.keySet();

		for (String product : keys) {
			if (sugarList.contains(product)) {
				isFromSugar = true;
			}
			if (medicinesList.contains(product)) {
				isFromMed = true;
			}
		}

		if (isFromSugar && isFromMed) {
			System.out.println("Mr."+customerName+" Welcome to Amazon Store");
			System.out.println("Bill will be paid at Cash on Delivery");
			System.out.println("Please check Medicine Expiration before buying");
			System.out.println("Warning: Excessive sugar consumption! Please consider reducing your intake.");
		} else if (isFromSugar) {
			System.out.println("Mr."+customerName+" Welcome to Amazon Store");
			System.out.println("Bill will be paid at Cash on Delivery");
			System.out.println("Warning: Excessive sugar consumption! Please consider reducing your intake.");
		} else if (isFromMed) {
			System.out.println("Mr."+customerName+" Welcome to Amazon Store");
			System.out.println("Bill will be paid at Cash on Delivery");
			System.out.println("Please check Medicine Expiration before buying");
		} else {
			System.out.println("Mr."+customerName+" Welcome to Amazon Store");
			System.out.println("Bill will be paid at Cash on Delivery");

		}
	}

	public static String getCustmerName() {

		Scanner scn = new Scanner(System.in);
		System.out.println(" Please enter Customer Name");
		String customerName = scn.nextLine();
		return customerName;
	}

}
