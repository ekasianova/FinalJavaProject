import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CountingTest {

    @Test
    public void testBeginningBeforeCurrentDay() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 16);
        Date begDate = DateCreation.createDate(22, 2, 2021, 10);
        int timeTillEnd = 4;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertEquals("Are courses finished",true, nextDays.isFinished);
        Assert.assertEquals("Days assertion", 0, nextDays.days);
        Assert.assertEquals("Hours assertion", 2, nextDays.hours);
        Assert.assertEquals("Final date assertion", DateCreation.createDate( 22, 2, 2021, 14).getTime(), nextDays.lastStudyingDay.getTime());
        //Assert.assertEquals(createDate(22, 2, 2021, 14), nextDays.lastStudyingDay);
    }

    @Test
    public void testBeginningAfterCurrentDay() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 16);
        Date begDate = DateCreation.createDate(23, 2, 2021, 10);
        int timeTillEnd = 10;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertEquals(false, nextDays.isFinished);
        Assert.assertEquals(1, nextDays.days);
        Assert.assertEquals(20, nextDays.hours);
        Assert.assertEquals(DateCreation.createDate(24, 2, 2021, 12).getTime(), nextDays.lastStudyingDay.getTime());
    }

    @Test
    public void testBeginningNow() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 10);
        Date begDate = DateCreation.createDate(22, 2, 2021, 10);
        int timeTillEnd = 10;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertEquals(false, nextDays.isFinished);
        Assert.assertEquals(1, nextDays.days);
        Assert.assertEquals(2, nextDays.hours);
        Assert.assertEquals(DateCreation.createDate(23, 2, 2021, 12).getTime(), nextDays.lastStudyingDay.getTime());
    }

    @Test
    public void testCountWeekends() {
        Date curDate = DateCreation.createDate(20, 6, 2021, 10);
        Date begDate = DateCreation.createDate(16, 6, 2021, 10);
        int timeTillEnd = 18;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertEquals(false, nextDays.isFinished);
        Assert.assertEquals(0, nextDays.days);
        Assert.assertEquals(2, nextDays.hours);
        Date expectedDate = DateCreation.createDate(20, 6, 2021, 12);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDay.getTime());
    }


}
