package javaTask;

import java.text.ParseException;
import java.util.*;
import java.util.zip.DataFormatException;
import static javaTask.DateCreation.createStartDateForStudent;


public class Student {
    public String name;
    public String curriculum;
    public Date startDate;
    public Map<String, Integer> courses = new HashMap<>();
    public int timeForAllCourses = 0;

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
        if (areWordsNotCorrect(name))
            throw new DataFormatException("Wrong student name format");
        this.name = name;
    }

    public void setCurriculum(String curriculum) throws DataFormatException {
        if (areWordsNotCorrect(curriculum))
            throw new DataFormatException("Wrong curriculum format");
        this.curriculum = curriculum;
    }

    public void setStartDate(String date) throws ParseException {
        startDate = createStartDateForStudent(date);
    }

    public void addCourse(String name, Integer duration) throws DataFormatException {
        if (areWordsNotCorrect(name))
            throw new DataFormatException("Wrong course name");
        courses.put(name, duration);
        timeForAllCourses += duration;
    }

    private boolean areWordsNotCorrect(String names) {
        String[] words = names.split(" ");
        for (String s : words)
            if (!s.matches("^[a-zA-Z]*$"))
                return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if(!(obj instanceof Student))
            return false;

        Student student = (Student) obj;

        return (this.name.equals(student.name) &&
                this.curriculum.equals(student.curriculum) &&
                this.startDate.equals(student.startDate) &&
                this.timeForAllCourses == student.timeForAllCourses &&
                this.courses.equals(student.courses));
    }
}
