package Excercises;

import java.util.ArrayList;
import java.util.Scanner;

public class Tax {

	public double calculateSalesTax(double price, boolean isMed, boolean isSug,int choice) {

		double salesTax;

        if (choice == 2) {
            if (isMed) {
                salesTax = price * 0.13;
            } else if (isSug) {
                salesTax = price * 0.12;
            } else {
                salesTax = price * 0.11;
            }
        } else if (choice == 1) {
            salesTax = price * 0.10;
        } else {
            System.out.println("Invalid choice. Please enter either 1 or 2.");
            salesTax = -1;
        }

        return salesTax;
    }
	
	
	public double calculateImportedTax(double price) {
		double importedTax;

		return importedTax = price * .05;

	}

	public double calculateTotalTax(boolean medicine, boolean isSugar, boolean imported, double price, int typ) {
		double totalTax;
		if (medicine == true && imported == true) {
			totalTax = calculateSalesTax(price, medicine, isSugar,typ) + calculateImportedTax(price)
					- ((calculateSalesTax(price, medicine, isSugar,typ) + calculateImportedTax(price)) * 0.01);
		} else if (medicine == true) {
			totalTax = calculateSalesTax(price, medicine, isSugar,typ)
					- ((calculateSalesTax(price, medicine, isSugar,typ) * 0.01));
		} else if (imported == true) {
			totalTax = calculateSalesTax(price, medicine, isSugar,typ) + calculateImportedTax(price);
		} else {
			totalTax = calculateSalesTax(price, medicine, isSugar,typ);
		}
		// System.out.println("Tax: " + totalTax);
		double RtotalTax = taxRounded(totalTax);
		// System.out.println("Rounded:" + RtotalTax);

		return RtotalTax;

	}

	public double taxRounded(double originalPrice) {
		// double originalPrice = 1.9290150000000001;

		// Step 1: Convert to cents
		int cents = (int) Math.round(originalPrice * 100);

		// Step 2: Round to the nearest 5 cents
		int roundedCents = (cents + 4) / 5 * 5;

		// Step 3: Convert back to dollars
		double roundedPrice = roundedCents / 100.0;

		// System.out.println("Original Price: $" + originalPrice);
		// System.out.println("Rounded Tax: $" + roundedPrice);
		return roundedPrice;
	}
}
