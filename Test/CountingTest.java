import javaTask.CountingRestDays;
import javaTask.DateCreation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;



public class CountingTest {

    @Test
    public void startDateBeforeCurrentDay() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 16);
        Date begDate = DateCreation.createDate(22, 2, 2021, 10);
        int timeTillEnd = 4;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertTrue("Are courses finished", nextDays.isFinished);
        Assert.assertEquals("Days assertion", 0, nextDays.days);
        Assert.assertEquals("Hours assertion", 2, nextDays.hours);
        Assert.assertEquals("Final date assertion", DateCreation.createDate(22, 2, 2021, 14).getTime(),
                nextDays.lastStudyingDay.getTime());
    }

    @Test
    public void startDateAfterCurrentDay() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 16);
        Date begDate = DateCreation.createDate(23, 2, 2021, 10);
        int timeTillEnd = 10;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(1, nextDays.days);
        Assert.assertEquals(20, nextDays.hours);
        Assert.assertEquals(DateCreation.createDate(24, 2, 2021, 12).getTime(), nextDays.lastStudyingDay.getTime());
    }

    @Test
    public void startDaySameAsCurrent() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 10);
        Date begDate = DateCreation.createDate(22, 2, 2021, 10);
        int timeTillEnd = 10;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(1, nextDays.days);
        Assert.assertEquals(2, nextDays.hours);
        Assert.assertEquals(DateCreation.createDate(23, 2, 2021, 12).getTime(), nextDays.lastStudyingDay.getTime());
    }

    @Test
    public void startDayBeforeCurrentAndWeekends() {
        Date curDate = DateCreation.createDate(20, 6, 2021, 10);
        Date begDate = DateCreation.createDate(16, 6, 2021, 10);
        int timeTillEnd = 18;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(0, nextDays.days);
        Assert.assertEquals(2, nextDays.hours);
        Date expectedDate = DateCreation.createDate(20, 6, 2021, 12);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDay.getTime());
    }


}
