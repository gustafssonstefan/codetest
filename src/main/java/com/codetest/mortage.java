package com.codetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;

public class mortage {
  
      public static float pow(float baseNum, int powNum) {
        float result = 1;
        for(int i = 0; i < powNum; i++) {
            result = result * baseNum;
        }
        return result;
    }
  
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
float annualInterest = Float.parseFloat(ary[2]) / 100;           //Get the value in percentage              
int numberOfPayments = Integer.parseInt(ary[3]) * MONTHS_YEAR;   //Amount of payment in total
float monthlyInterest =  annualInterest / MONTHS_YEAR;  
double monthlyPayment = loan * ((monthlyInterest * (pow(1 + monthlyInterest, numberOfPayments))) / ((pow(1 + monthlyInterest, numberOfPayments)) -1));

DecimalFormat formatter = new DecimalFormat("###,###.00");  
System.out.println("Name: " + name.replace("\"", "") + " ||" + " Fixed monthly payment: " + formatter.format(monthlyPayment) + " €");

}
reader.close();
} catch (Exception e) {
System.err.println("Error: Target File Cannot Be Read");
}
    }
}
