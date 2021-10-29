import javaTask.FileParser;
import javaTask.Student;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    private static String userDirectory = System.getProperty("user.dir");

    @Test(expected = DataFormatException.class)
    public void testEmptyNameWithOneStudent() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser( userDirectory + "/Test/testData/testEmptyName");
        parser.Parse();
    }

    @Test(expected = DataFormatException.class)
    public void testEmptyDate() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser(userDirectory  + "/Test/testData/testEmptyDate");
        parser.Parse();
    }

    @Test(expected = DataFormatException.class)
    public void testEmptyCurriculum() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser(userDirectory + "/Test/testData/testEmptyCurriculum");
        parser.Parse();
    }

    @Test(expected = DataFormatException.class)
    public void testEmptyCourses() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser(userDirectory + "/Test/testData/testEmptyCourses");
        parser.Parse();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyFile()throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser(userDirectory + "/Test/testData/emptyFile");
        parser.Parse();
    }

    @Test(expected = DataFormatException.class)
    public void testNotAcceptedLine() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser(userDirectory + "/Test/testData/strangeLine");
        parser.Parse();
    }

    @Test
    public void parseOneStudent() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser(userDirectory + "/Test/testData/oneStudent");
        List<Student> students = parser.Parse();
        Map<String, Integer> courses = new HashMap<>();
        courses.put("Java", 16);
        courses.put("JDBC", 24);
        courses.put("Spring", 16);
        int timeForCourses = 56;
        Student expectedStudent = new Student("Ivanov Ivan", "Java Developer",
                "1 June 2020 - Monday", courses, timeForCourses);

        assertEquals(students.size(), 1);
        assertEquals(expectedStudent, students.get(0));
    }

    @Test
    public void parseTwoStudents() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser(userDirectory + "/Test/testData/twoStudents");
        List<Student> students = parser.Parse();
        Map<String, Integer> coursesFirstStudent = new HashMap<>();
        coursesFirstStudent.put("Java", 16);
        coursesFirstStudent.put("JDBC", 24);
        coursesFirstStudent.put("Spring", 16);
        int timeForFirstStudent = 56;
        Student expectedFirstStudent = new Student("Ivanov Ivan", "Java Developer",
                "1 June 2020 - Monday", coursesFirstStudent, timeForFirstStudent);

        Map<String, Integer> coursesSecondStudent = new HashMap<>();
        coursesSecondStudent.put("Test design", 10);
        coursesSecondStudent.put("Page Object", 16);
        coursesSecondStudent.put("Selenium", 16);
        int timeForSecondStudent = 42;
        Student expectedSecondStudent = new Student("Sidorov Ivan", "AQE",
                "1 June 2020 - Monday", coursesSecondStudent, timeForSecondStudent);


        assertEquals(students.size(), 2);
        assertEquals(expectedFirstStudent, students.get(0));
        assertEquals(expectedSecondStudent, students.get(1));
    }



}
