import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.DataFormatException;

public class Student {
    String name;
    String curriculum;
    Date startDate;
    Map<String, Integer> courses = new HashMap<>();
    int timeForAllCourses = 0;

    public Student() {
    }

    public void setName(String name) throws DataFormatException {
        if(areWordsNotCorrect(name))
            throw new DataFormatException("Wrong student name format");
        this.name = name;
    }

    public void setCurriculum(String curriculum) throws DataFormatException {
        if(areWordsNotCorrect(curriculum))
            throw new DataFormatException("Wrong curriculum format");
        this.curriculum = curriculum;
    }

    public void setStartDate(String date) throws ParseException{
        DateFormat format = new SimpleDateFormat("d MMMM yyyy - E");
        format.setLenient(false);
        startDate = format.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.HOUR, 10);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        startDate = cal.getTime();
    }

    public void addCourse(String name, Integer duration) throws DataFormatException {
        if(areWordsNotCorrect(name))
            throw new DataFormatException("Wrong course name");
        courses.put(name, duration);
        timeForAllCourses += duration;
    }

    private boolean areWordsNotCorrect(String names){
        String[] words = names.split(" ");
        for(String s: words)
            if(!s.matches("^[a-zA-Z]*$"))
                return true;
        return false;
    }

}
