package javaTask;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report {
    public Date currentDate;

    Report(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void printShortReport(List<Student> students) {
        Iterator<Student> studentsIterator = students.iterator();
        while (studentsIterator.hasNext()) {
            Student currentStudent = studentsIterator.next();
            CountingRestDays restDays = new CountingRestDays(currentStudent.startDate, currentDate, currentStudent.timeForAllCourses);

            if (restDays.isFinished)
                System.out.printf("%s (%s) - Training completed. %d days %d hours have passed since the end.%n",
                        currentStudent.name, currentStudent.curriculum, restDays.restDays, restDays.restHours);
            else
                System.out.printf("%s (%s) - Training is not finished. %d days %d hours are left until the the end.%n",
                        currentStudent.name, currentStudent.curriculum, restDays.restDays, restDays.restHours);
        }
    }

    public void printLongReport(List<Student> students) {
        for (Student currentStudent : students) {
            CountingRestDays restDays = new CountingRestDays(currentStudent.startDate, currentDate, currentStudent.timeForAllCourses);
            System.out.println("Name " + currentStudent.name);
            System.out.println("Working time from 10 to 18 each working day");
            System.out.println("Program name " + currentStudent.curriculum);
            System.out.printf("Program duration %d working hours%n", currentStudent.timeForAllCourses);
            int i = 1;
            for (String name : currentStudent.courses.keySet()) {
                System.out.printf("%d. %s  %d%n", i, name, currentStudent.courses.get(name));
                i++;
            }
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy, HH.mm");
            String formattedDate = format.format(currentStudent.startDate);
            System.out.println("Start date " + formattedDate);
            formattedDate = format.format(restDays.lastStudyingDate);
            System.out.println("Last date " + formattedDate);


            if (restDays.isFinished)
                System.out.printf("%d days %d hours have passed since the end.%n",
                        restDays.restDays, restDays.restHours);
            else
                System.out.printf("%d days %d hours are left until the the end.%n",
                        restDays.restDays, restDays.restHours);

        }
    }


}
