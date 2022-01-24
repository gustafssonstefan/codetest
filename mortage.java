package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class mortage {
  
    private static final int MONTHS_YEAR = 12;
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("https://raw.githubusercontent.com/gustafssonstefan/codetest/main/prospects.txt");
        BufferedReader reader = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
    
        String inputLine = "";
        String splitBy = ","; 

        boolean firstRow = true;      

        try   
{  
//parsing a CSV file into BufferedReader class constructor  
//while ((inputLine = reader.readLine()) != null)   //returns a Boolean value  
for (int i = 0; i < 5; i++) {  
    
    inputLine = reader.readLine(); 
    
    if(firstRow) { //should skip first row
        firstRow = false;
        continue;
    }
     
String[] ary = inputLine.split(splitBy);    // use comma as separator  

String name = ary[0];
float loan = Float.parseFloat(ary[1]);
float AnnualInterest = Float.parseFloat(ary[2]) / 100;           //Get the value in percentage              
int numberOfPayments = Integer.parseInt(ary[3]) * MONTHS_YEAR;   //Amount of payment in total
float MonthlyInterest =  AnnualInterest / MONTHS_YEAR;  
double monthlyPayment = loan * ((MonthlyInterest * (Math.pow(1 + MonthlyInterest, numberOfPayments))) / ((Math.pow(1 + MonthlyInterest, numberOfPayments)) -1));

NumberFormat n = NumberFormat.getCurrencyInstance(Locale.ITALIAN);
System.out.println("Name: " + name + " ||" + " Fixed monthly payment: " + n.format(monthlyPayment));

}
reader.close();
} catch (Exception e) {
System.err.println("Error: Target File Cannot Be Read");
}
    }
}