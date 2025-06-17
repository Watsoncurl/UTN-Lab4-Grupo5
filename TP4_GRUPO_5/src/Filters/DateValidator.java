package Filters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
    public static boolean isValidDate(String input) {
        try {
        	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
             sdf.setLenient(false);
             Date inputDate = sdf.parse(input);

             Date currentDate = new Date();

             if (inputDate.after(currentDate)) {
                 return false;
             }

             return true;
        } catch (Exception e) {
            return false;
        }
    }
}
