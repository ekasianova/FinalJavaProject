package javaTask;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static javaTask.DateCreation.parseDateFromCommandLine;

public class Main {
    public static void main(String[] args) {
        Date launchDate;
        int dateTokens = 4;
        if(args.length == 0) {
            System.out.println("Wrong number of arguments in command line: must start with a date in the format" +
                    " \"d MMMM yyyy HH.mm\" + 0 or nothing for short report other for long report");
            return;
        }

        try {
            launchDate = parseDateFromCommandLine(args);
        } catch (ParseException e) {
            System.out.println("Command line must start with a date in the format \"d MMMM yyyy HH.mm\" ");
            e.printStackTrace();
            return;
        }

        try {
            FileParser fileParser = new FileParser(System.getProperty("user.dir") + "/src/data/Students");
            List<Student> students = fileParser.parse();
            Report report = new Report(launchDate);
            if(args.length > dateTokens) {
                try {
                    if (Integer.parseInt(args[dateTokens]) == 0) {
                        report.printShortReport(students);
                    }
                    else {
                        report.printLongReport(students);
                    }
                } catch (NumberFormatException e) {
                    report.printLongReport(students);
                }
            }
            else {
                report.printShortReport(students);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
