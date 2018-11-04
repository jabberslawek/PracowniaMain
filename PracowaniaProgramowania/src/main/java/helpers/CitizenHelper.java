package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CitizenHelper {

    public static String cutPesel(String allData) {
        String pesel = "";
        for (int i = 0; i < allData.length(); i++) {
            if (allData.charAt(i) >= 48 && allData.charAt(i) <= 57) {
                if(allData.length() - i != 11) {
                    System.out.println("Pesel za krotki");
                    break;
                }
                pesel = allData.substring(i, i + 11);
                boolean isValidRegex = validateRegex(allData);
                boolean isValidPesel = validatePesel(pesel);
                if (isValidRegex && isValidPesel && pesel.length() == 11)
                    return pesel;
                if (!isValidRegex){
                    System.out.println("Nie poprawny wpis (Imie Nazwisko PESEL)");
                    break;
                }
                System.out.println("Nie prawdidlowy pesel dla wpisu : " + allData);
                break;
            }
        }
        return null;
    }

    public static boolean validatePesel(String pesel) {
        String[] peselArr = pesel.split("");
        int[] multiplicator = {9, 7, 3, 1};
        int peselChecksum = 0;
        for (int i = 0; i < peselArr.length - 1; i++) {
            peselChecksum = peselChecksum + multiplicator[i % 4] * Integer.parseInt(peselArr[i]);
        }
        return (peselChecksum % 10 == Integer.parseInt(peselArr[10]));
    }

    private static boolean validateRegex(String allData){
        Pattern p = Pattern.compile("[a-zA-Z]*\\s+[a-zA-Z]*\\s+\\d{11}");
        Matcher matcher = p.matcher(allData);
        return matcher.matches();
    }

}

