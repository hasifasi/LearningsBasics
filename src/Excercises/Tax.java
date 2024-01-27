package Excercises;

public class Tax {

	public double calculateSalesTax(double price) {
		double salesTax;
		return salesTax = price * .1;
	}

	public double calculateImportedTax(double price) {
		double importedTax;

		return importedTax = price * .05;

	}

	public double calculateTotalTax(boolean medicine, boolean imported, double price) {
		double totalTax;
		if (medicine == true && imported == true) {
			totalTax = calculateSalesTax(price) + calculateImportedTax(price)
					- ((calculateSalesTax(price) + calculateImportedTax(price)) * 0.01);
		} else if (medicine == true) {
			totalTax = calculateSalesTax(price) - ((calculateSalesTax(price) * 0.01));
		} else if (imported == true) {
			totalTax = calculateSalesTax(price) + calculateImportedTax(price);
		} else {
			totalTax = calculateSalesTax(price);
		}
		//System.out.println("Tax: " + totalTax);
		double RtotalTax = taxRounded(totalTax);
		//System.out.println("Rounded:" + RtotalTax);

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
		//System.out.println("Rounded Tax: $" + roundedPrice);
		return roundedPrice;
	}
}
