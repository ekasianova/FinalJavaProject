package javaTask;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report {
    private Date currentDate;

    Report(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void printShortReport(List<Student> students) {
        for (Student currentStudent : students) {
            CountingRestDays restDays = new CountingRestDays(currentStudent.getStartDate(), currentDate,
                    currentStudent.getTimeForAllCourses());
            if (restDays.isFinished()) {
                System.out.printf("%s (%s) - Training completed. %d days %d hours have passed since the end.%n",
                        currentStudent.getName(), currentStudent.getCurriculum(), restDays.getRestDays(), restDays.getRestHours());
            }
            else {
                System.out.printf("%s (%s) - Training is not finished. %d days %d hours are left until the the end.%n",
                        currentStudent.getName(), currentStudent.getCurriculum(), restDays.getRestDays(), restDays.getRestHours());
            }
        }
    }

    public void printLongReport(List<Student> students) {
        for (Student currentStudent : students) {
            CountingRestDays restDays = new CountingRestDays(currentStudent.getStartDate(), currentDate, currentStudent.getTimeForAllCourses());
            System.out.println("Name " + currentStudent.getName());
            System.out.println("Working time from 10 to 18 each working day");
            System.out.println("Program name " + currentStudent.getCurriculum());
            System.out.printf("Program duration %d working hours%n", currentStudent.getTimeForAllCourses());
            int i = 1;
            for (String name : currentStudent.getCourses().keySet()) {
                System.out.printf("%d. %s  %d%n", i, name, currentStudent.getCourses().get(name));
                i++;
            }
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy, HH.mm");
            String formattedDate = format.format(currentStudent.getStartDate());
            System.out.println("Start date " + formattedDate);
            formattedDate = format.format(restDays.getLastStudyingDate());
            System.out.println("Last date " + formattedDate);


            if (restDays.isFinished()) {
                System.out.printf("%d days %d hours have passed since the end.%n",
                        restDays.getRestDays(), restDays.getRestHours());
            }
            else {
                System.out.printf("%d days %d hours are left until the the end.%n",
                        restDays.getRestDays(), restDays.getRestHours());
            }

        }
    }


}
