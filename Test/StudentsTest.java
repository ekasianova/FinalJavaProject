import javaTask.DateCreation;
import javaTask.Student;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.zip.DataFormatException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

public class StudentsTest {

    Student testStudent = new Student();


    @Test(expected = DataFormatException.class)
    public void oneNameWithOnlyNumbersTest() throws DataFormatException {
        testStudent.setName("123 567");
    }

    @Test(expected = DataFormatException.class)
    public void nameWithNumberInTheBeginningTest() throws DataFormatException {
        testStudent.setName("1Jane Eyre");
    }

    @Test(expected = DataFormatException.class)
    public void nameWithNumberInTheEndTest() throws DataFormatException {
        testStudent.setName("Jane Eyre1");
    }

    @Test(expected = DataFormatException.class)
    public void nameWithNumberInTheMiddleTest() throws DataFormatException {
        testStudent.setName("J1ane Eyre");
    }

    @Test
    public void correctName() throws DataFormatException {
        testStudent.setName("Jane Eyre");
        assertThat("Jane Eyre", equalTo(testStudent.name));
    }

    // Curriculum same as name

    @Test
    public void correctCurriculum() throws DataFormatException {
        testStudent.setCurriculum("QA Java Class");
        assertThat("QA Java Class", equalTo(testStudent.curriculum));
    }

    @Test
    public void correctDataSet() throws ParseException {
        testStudent.setStartDate("1 June 2020 - Monday");
        Assert.assertEquals(DateCreation.createDate(1, 5, 2020, 10).getTime(),
                testStudent.startDate.getTime());
    }

    @Test(expected = ParseException.class)
    public void wrongDataSetPattern() throws ParseException {
        testStudent.setStartDate("1 bla bla bla June 2020 - Monday");
    }

    @Test
    public void correctDataSetWithAppropriateSpaces() throws ParseException {
        testStudent.setStartDate(" 1 June  2020 - Monday ");
        Assert.assertEquals(DateCreation.createDate(1, 5, 2020, 10).getTime(),
                testStudent.startDate.getTime());
    }

    @Test(expected = ParseException.class)
    public void correctDataSetWithNotAppropriateSpaces() throws ParseException {
        testStudent.setStartDate(" 1     June  2020    -    Monday ");
    }

    @Test(expected = ParseException.class)
    public void notExistentDateDayWrong() throws ParseException {
        testStudent.setStartDate("31 February 2020 - Monday");
    }

    @Test(expected = ParseException.class)
    public void notExistentDateWeekDayWrong() throws ParseException {
        testStudent.setStartDate("1 February 2020 - Tuesday");
    }

    @Test
    public void addCorrectCourse() throws DataFormatException {
        testStudent.addCourse("Java", 10);

        assertThat(testStudent.courses, hasEntry("Java", 10));
        assertThat(testStudent.timeForAllCourses, equalTo(10));
    }

    @Test
    public void checkTimeForAllCourses() throws DataFormatException {
        testStudent.addCourse("Java", 10);
        testStudent.addCourse("QA Theory", 10);

        assertThat(testStudent.courses, hasEntry("QA Theory", 10));
        assertThat(testStudent.timeForAllCourses, equalTo(20));
    }

}
