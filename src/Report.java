import java.text.SimpleDateFormat;
import java.util.*;

public class Report {
    public Date currentDate;

    Report(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        currentDate = cal.getTime();
    }

    public void shortReport(List<Student> students){
        Iterator<Student> studentsIterator = students.iterator();
        while (studentsIterator.hasNext()){
            Student currentStudent = studentsIterator.next();
            CountingRestDays restDays = new CountingRestDays(currentStudent.startDate, currentDate, currentStudent.timeForAllCourses);

            if(restDays.isFinished)
                System.out.println(String.format("%s (%s) - Training completed. %d days %d hours have passed since the end.",
                        currentStudent.name, currentStudent.curriculum, restDays.days, restDays.hours));
            else
                System.out.println(String.format("%s (%s) - Training is not finished. %d days %d hours are left until the the end.",
                        currentStudent.name, currentStudent.curriculum, restDays.days, restDays.hours));
        }
    }
    public void longReport(List<Student> students){
        Iterator<Student> studentsIterator = students.iterator();
        while (studentsIterator.hasNext()){
            Student currentStudent = studentsIterator.next();
            CountingRestDays restDays = new CountingRestDays(currentStudent.startDate, currentDate, currentStudent.timeForAllCourses);
            System.out.println("Name " + currentStudent.name);
            System.out.println("Working time from 10 to 18 each working day");
            System.out.println("Program name " + currentStudent.curriculum);
            System.out.println(String.format("Program duration %d working hours", currentStudent.timeForAllCourses));
            int i = 1;
            for (String name: currentStudent.courses.keySet()) {
                System.out.println(String.format("%d. %s  %d", i, name, currentStudent.courses.get(name) ));
                i++;
            }
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy, HH.mm");
            String formattedDate = format.format(currentStudent.startDate);
            System.out.println(String.format("Start date " + formattedDate));
            formattedDate = format.format(restDays.lastStudyingDay);
            System.out.println(String.format("Last date " + formattedDate));


            if(restDays.isFinished)
                System.out.println(String.format("%d days %d hours have passed since the end.",
                        restDays.days, restDays.hours));
            else
                System.out.println(String.format("%d days %d hours are left until the the end.",
                         restDays.days, restDays.hours));

        }
    }


}
