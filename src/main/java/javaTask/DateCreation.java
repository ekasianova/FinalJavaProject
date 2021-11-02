package javaTask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCreation {
    public static Date createDate(int day, int month, int year, int hour){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date parseDateFromCommandLine(String[] commandLineArgs) throws ParseException {
        int dateTokens = 4;
        StringBuilder buildStringDate = new StringBuilder();
        for (int i = 0; i < commandLineArgs.length && i < dateTokens; i++) {
            buildStringDate.append(commandLineArgs[i]);
            buildStringDate.append(" ");
        }
        DateFormat format = new SimpleDateFormat("d MMMM yyyy HH.mm");
        format.setLenient(false);
        Date currentDate = format.parse(buildStringDate.toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date createStartDateForStudent(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("d MMMM yyyy - E");
        format.setLenient(false);
        Calendar cal = Calendar.getInstance();
        cal.setTime(format.parse(date));
        cal.set(Calendar.HOUR, 10);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime();
    }


}
