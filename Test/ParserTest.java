import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.zip.DataFormatException;

public class ParserTest {

    @Test(expected = DataFormatException.class)
    public void testEmptyNameWithOneStudent() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser("/Users/ekasianova/IdeaProjects/FinalJavaProject/Test/testEmptyName");
        List<Student> students = parser.Parse();
    }

    @Test(expected = DataFormatException.class)
    public void testEmptyDate() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser("/Users/ekasianova/IdeaProjects/FinalJavaProject/Test/testEmptyDate");
        List<Student> students = parser.Parse();
    }

    @Test(expected = DataFormatException.class)
    public void testEmptyCurriculum() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser("/Users/ekasianova/IdeaProjects/FinalJavaProject/Test/testEmptyCurriculum");
        List<Student> students = parser.Parse();
    }

    @Test(expected = DataFormatException.class)
    public void testEmptyCourses() throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser("/Users/ekasianova/IdeaProjects/FinalJavaProject/Test/testEmptyCourses");
        List<Student> students = parser.Parse();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyFile()throws DataFormatException, IOException, ParseException {
        FileParser parser = new FileParser("/Users/ekasianova/IdeaProjects/FinalJavaProject/Test/emptyFile");
        List<Student> students = parser.Parse();
    }


}
