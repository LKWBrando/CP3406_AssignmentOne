/*ConversionMethods is used to contain all the methods regarding the calculation of the currency conversion methods, and future mathematical methods*/

package example.assignmentone.cp3406.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConversionMethods {

    /*Part one of converting user input into selected currency type
    Method convertUSD converts the user's input of selected currency type, stored in string variable currencyType,
    into a base unit of United States Dollar (USD) and stores it in the global variable userInputUsd*/
    public static double convertUSD(String currencyType, double userInput){
        double userInputUsd = 0.0;
        switch(currencyType){
            case "usd":
                userInputUsd = userInput;
                break;
            case "gbp":
                userInputUsd = userInput * 1.34;
                break;
            case "sgd":
                userInputUsd = userInput * 0.74;
                break;
            case "thb":
                userInputUsd = userInput * 0.031;
                break;
            case "myr":
                userInputUsd = userInput * 0.25;
                break;
            case "jpy":
                userInputUsd = userInput * 0.0088;
                break;
            case "eur":
                userInputUsd = userInput * 1.18;
                break;
            case "aud":
                userInputUsd = userInput * 0.75;
                break;
            case "idr":
                userInputUsd = userInput * 0.000074;
                break;
            case "nzd":
                userInputUsd = userInput * 0.69;
                break;
            case "inr":
                userInputUsd = userInput * 0.016;
                break;
            case "hkd":
                userInputUsd = userInput * 0.13;
                break;
            case "":
                break;
        }
        return userInputUsd;
    }

    /*Part two of converting user input into selected currency type.
    Method convertActual takes in the value of userInputUsd and calculates the result of the conversion based on what user has selected, stored in string variable currencyType*/
    public static double convertActual (String currencyType, double userInputUsd){
        double convertedResult = 0.0;
        switch(currencyType){
            case "usd":
                convertedResult = userInputUsd;
                break;
            case "gbp":
                convertedResult = userInputUsd * 0.75;
                break;
            case "sgd":
                convertedResult = userInputUsd * 1.35;
                break;
            case "thb":
                convertedResult = userInputUsd * 32.62;
                break;
            case "myr":
                convertedResult = userInputUsd * 4.07;
                break;
            case "jpy":
                convertedResult = userInputUsd * 113.35;
                break;
            case "eur":
                convertedResult = userInputUsd * 0.85;
                break;
            case "aud":
                convertedResult = userInputUsd * 1.33;
                break;
            case "idr":
                convertedResult = userInputUsd * 13549;
                break;
            case "nzd":
                convertedResult = userInputUsd * 1.45;
                break;
            case "inr":
                convertedResult = userInputUsd * 64.39;
                break;
            case "hkd":
                convertedResult = userInputUsd * 7.81;
                break;
            case "":
                break;
        }
        return convertedResult;
    }

    /*Method to round double number to two decimal places.
    Taken from https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places*/
   public static Double roundConvertedValue(Double convertedResult, int decimalPlace){
        if (decimalPlace < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(convertedResult);
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
