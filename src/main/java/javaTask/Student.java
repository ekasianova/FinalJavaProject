package javaTask;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.DataFormatException;

import static javaTask.DateCreation.createStartDateForStudent;


public class Student {
    private String name;
    private String curriculum;
    private Date startDate;
    private Map<String, Integer> courses = new HashMap<>();
    private int timeForAllCourses = 0;

    public Student() {
    }

    public Student(String name, String curriculum, String date, Map<String, Integer> courses,
                   int timeForAllCourses) throws DataFormatException, ParseException {
        setName(name);
        setCurriculum(curriculum);
        setStartDate(date);
        this.courses = courses;
        this.timeForAllCourses = timeForAllCourses;
    }

    public void setName(String name) throws DataFormatException {
        if (areWordsNotCorrect(name)) {
            throw new DataFormatException("Wrong student name format: could contains only letters");
        }
        this.name = name;
    }

    public void setCurriculum(String curriculum) throws DataFormatException {
        if (areWordsNotCorrect(curriculum)) {
            throw new DataFormatException("Wrong curriculum format: could contains only letters");
        }
        this.curriculum = curriculum;
    }

    public void setStartDate(String date) throws ParseException {
        startDate = createStartDateForStudent(date);
    }

    public void addCourse(String name, Integer duration) throws DataFormatException {
        if (areWordsNotCorrect(name)) {
            throw new DataFormatException("Wrong course name: could contains only letters");
        }
        courses.put(name, duration);
        timeForAllCourses += duration;
    }

    private boolean areWordsNotCorrect(String names) {
        String[] words = names.split(" ");
        for (String s : words) {
            if (!s.matches("^[a-zA-Z]*$")) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Map<String, Integer> getCourses() {
        return courses;
    }

    public int getTimeForAllCourses() {
        return timeForAllCourses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if(!(obj instanceof Student)) {
            return false;
        }

        Student student = (Student) obj;

        return (this.name.equals(student.name) &&
                this.curriculum.equals(student.curriculum) &&
                this.startDate.equals(student.startDate) &&
                this.timeForAllCourses == student.timeForAllCourses &&
                this.courses.equals(student.courses));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, curriculum, startDate, courses, timeForAllCourses);
    }
}
