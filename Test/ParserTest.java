import javaTask.FileParser;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import java.util.zip.DataFormatException;

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


}
