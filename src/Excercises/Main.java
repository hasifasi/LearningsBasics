package Excercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		
		HashMap<String,Double> finalResult=calculate();
		System.out.println("------------------------------------");
		for(Map.Entry<String,Double> e:finalResult.entrySet()) {
			System.out.println("Item: " + e.getKey() + ", Price: " + e.getValue());
			
		}
		
		
	}

	public static HashMap<String,Double> calculate() throws IOException {
		HashMap<String,Double> finalPrice= new HashMap<String,Double>();
		int quantity = 0;
		String product = null;
		boolean isImported = false;
		//boolean isMedicine=true;
		double price = 0;
		double calculatedTax;
		Tax t= new Tax();
		
		ArrayList<Object> inputdata= readFile();
		for(int i=0;i<inputdata.size();i++) {
			String[] s= inputdata.get(i).toString().split("/");
			quantity= Integer.parseInt(s[0]);
			product=s[1];
			isImported=Boolean.parseBoolean(s[2]);
			price=Double.parseDouble(s[3]);
			//Tax t= new Tax();
			
			double productCost=  quantity*price;
			boolean isMedicine=isitMedicine(product);
			 calculatedTax=t.calculateTotalTax(isMedicine, isImported, productCost);
			System.out.println("calculatedTax: "+calculatedTax);
			System.out.println("productCost:"+productCost);
			double totalProductCost= productCost + calculatedTax;
			System.out.println("TotalProductCost : "+totalProductCost);
			System.out.println("------------------------------------");
			finalPrice.put(product,Math.round(totalProductCost * 100.0) / 100.0);
		}
		
		return finalPrice;
	}


	public static boolean isitMedicine(String product) {
		
		boolean isMedicine=false;
		
		if(product.toLowerCase().contains("Tablet".toLowerCase())) {
			isMedicine=true;
		}
		
		//System.out.println("isMedicine :"+isMedicine);
		return isMedicine;
		
	}
	
	public static ArrayList<Object> readFile() throws IOException {
		ArrayList<Object> lists= new ArrayList<>();
		FileReader F= new FileReader(System.getProperty("user.dir")+File.separator+"input.txt");
		
		
		BufferedReader bf= new BufferedReader(F);
		String line;
		while((line=bf.readLine())!=null) {
			lists.add(line);
		}
		
		return lists;
	}
	
	
	
}
