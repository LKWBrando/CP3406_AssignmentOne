package example.assignmentone.cp3406.converter;

import org.junit.Test;
import static example.assignmentone.cp3406.converter.ConversionMethods.convertActual;
import static example.assignmentone.cp3406.converter.ConversionMethods.convertUSD;
import static example.assignmentone.cp3406.converter.ConversionMethods.roundConvertedValue;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    //Testing various combinations of currency conversions, with different input values
    @Test
    public void conversion_isCorrect() throws Exception {
        double userInput; //used to store user input for calculations
        double userInputUsd; //used in the conversion calculations
        double convertedResult = 0.0; //used in the conversion calculations
        String inputCurrencyType; //User input for initial currency type
        String convertedCurrencyType; //User input for converted currency type

        //Case 1: 100 usd to sgd, result should be 135 sgd, rounding off to whole number
        inputCurrencyType = "usd";
        convertedCurrencyType = "sgd";
        userInput = 100;
        userInputUsd = convertUSD(inputCurrencyType,userInput);
        convertedResult = convertActual(convertedCurrencyType, userInputUsd);
        convertedResult = roundConvertedValue(convertedResult, 0);
        assertTrue(convertedResult == 135.0);

        //Case 2: 156 eur to myr, result should be 749 myr, rounding off to whole number
        inputCurrencyType = "eur";
        convertedCurrencyType = "myr";
        userInput = 156;
        userInputUsd = convertUSD(inputCurrencyType,userInput);
        convertedResult = convertActual(convertedCurrencyType, userInputUsd);
        convertedResult = roundConvertedValue(convertedResult, 0);
        assertTrue(convertedResult == 749.0);

        //Case 3: 12569 idr to jpy, result should be 105 jpy, rounding off to whole number
        inputCurrencyType = "idr";
        convertedCurrencyType = "jpy";
        userInput = 12569;
        userInputUsd = convertUSD(inputCurrencyType,userInput);
        convertedResult = convertActual(convertedCurrencyType, userInputUsd);
        convertedResult = roundConvertedValue(convertedResult, 0);
        assertTrue(convertedResult == 105.0);

        //Case 4: 100 gbp to aud, result should be 178.22 aud, rounding off to two decimal places
        inputCurrencyType = "gbp";
        convertedCurrencyType = "aud";
        userInput = 100;
        userInputUsd = convertUSD(inputCurrencyType,userInput);
        convertedResult = convertActual(convertedCurrencyType, userInputUsd);
        convertedResult = roundConvertedValue(convertedResult, 2);
        assertTrue(convertedResult == 178.22);

        //Case 5: 61 hkd to thb, result should be 258.68 thb, rounding off to two decimal places
        inputCurrencyType = "hkd";
        convertedCurrencyType = "thb";
        userInput = 61;
        userInputUsd = convertUSD(inputCurrencyType,userInput);
        convertedResult = convertActual(convertedCurrencyType, userInputUsd);
        convertedResult = roundConvertedValue(convertedResult, 2);
        assertTrue(convertedResult == 258.68);

        //Case 6: 456 nzd to inr, result should be 20259.67 inr, rounding off to two decimal places
        inputCurrencyType = "nzd";
        convertedCurrencyType = "inr";
        userInput = 456;
        userInputUsd = convertUSD(inputCurrencyType,userInput);
        convertedResult = convertActual(convertedCurrencyType, userInputUsd);
        convertedResult = roundConvertedValue(convertedResult, 2);
        assertTrue(convertedResult == 20259.67);
    }
}