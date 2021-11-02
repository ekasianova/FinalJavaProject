package javaTask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class FileParser {
    private String filepath;

    public FileParser(String filepath) {
        this.filepath = filepath;
    }

    public List<Student> parse() throws IOException, ParseException, DataFormatException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        List<Student> students = new ArrayList<>();
        Student newStudent = null;  // ?null
        String line;
        while ((line = br.readLine()) != null) {
            String[] splitStr = line.split("\\s+");
            switch (splitStr[0]){
                case "STUDENT:":
                    newStudent = new Student();
                    newStudent.setName(line.split(":")[1].trim());
                    students.add(newStudent);
                    break;
                case "CURRICULUM:":
                    isStudentExist(newStudent);
                    newStudent.setCurriculum(line.split(": ")[1]);
                    break;
                case "START_DATE:":
                    isStudentExist(newStudent);
                    newStudent.setStartDate(line.split(":")[1].trim());
                case "COURSE":
                    continue;
                default:
                    if(parseNumber(splitStr[0])) {
                        isStudentExist(newStudent);
                        String[] courseSplit = line.replaceAll("\\s+", " ").split("\\.");
                        Integer duration = Integer.parseInt(courseSplit[2].trim());
                        newStudent.addCourse(courseSplit[1].trim(), duration);
                    }
                    else {
                        if(!splitStr[0].matches("^[\\s-]*$"))
                            throw new DataFormatException("Not informative lines could contain blank spaces or '-' symbol");
                    }
            }
        }
        checkList(students);
        return students;
    }

    private void checkList(List<Student> students) throws DataFormatException {
        if (students.isEmpty()) {
            throw new IllegalStateException("No students found after parsing file");
        }
        for (Student currentStudent: students) {
            checkFields(currentStudent);
        }

    }

    private void isStudentExist(Student student) throws DataFormatException {
        if(student == null) {
            throw new DataFormatException("Wrong file formatting");
        }
    }

    private void checkFields(Student student) throws DataFormatException {
        if (student.getName() == null || student.getCourses().isEmpty() || student.getCurriculum() == null
                || student.getStartDate() == null) {
            throw new DataFormatException("Some fields are empty after file parsing");
        }

    }

    private boolean parseNumber(String str) {
        try {
            String s = str.replaceAll("\\.", "");
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
