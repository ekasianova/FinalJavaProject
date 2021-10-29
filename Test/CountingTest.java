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
        Assert.assertEquals("Days assertion", 0, nextDays.restDays);
        Assert.assertEquals("Hours assertion", 2, nextDays.restHours);
        Assert.assertEquals("Final date assertion", DateCreation.createDate(22, 2, 2021, 14).getTime(),
                nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void startDateAfterCurrentDay() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 16);
        Date begDate = DateCreation.createDate(23, 2, 2021, 10);
        int timeTillEnd = 10;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(1, nextDays.restDays);
        Assert.assertEquals(20, nextDays.restHours);
        Assert.assertEquals(DateCreation.createDate(24, 2, 2021, 12).getTime(), nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void startDaySameAsCurrent() {
        Date curDate = DateCreation.createDate(22, 2, 2021, 10);
        Date begDate = DateCreation.createDate(22, 2, 2021, 10);
        int timeTillEnd = 10;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(1, nextDays.restDays);
        Assert.assertEquals(2, nextDays.restHours);
        Assert.assertEquals(DateCreation.createDate(23, 2, 2021, 12).getTime(), nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void startDayBeforeCurrentAndWeekends() {
        Date curDate = DateCreation.createDate(20, 6, 2021, 10);
        Date begDate = DateCreation.createDate(16, 6, 2021, 10);
        int timeTillEnd = 18;
        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(0, nextDays.restDays);
        Assert.assertEquals(2, nextDays.restHours);
        Date expectedDate = DateCreation.createDate(20, 6, 2021, 12);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void timeTillEndEqualZero() {
        Date curDate = DateCreation.createDate(25, 9, 2021, 10);
        Date begDate = DateCreation.createDate(25, 9, 2021, 10);
        int timeTillEnd = 0;

        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertTrue(nextDays.isFinished);
        Assert.assertEquals(0, nextDays.restDays);
        Assert.assertEquals(0, nextDays.restHours);
        Date expectedDate = DateCreation.createDate(25, 9, 2021, 10);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void timeTillEndEqualWorkingDay() {
        Date curDate = DateCreation.createDate(25, 9, 2021, 10);
        Date begDate = DateCreation.createDate(25, 9, 2021, 10);
        int timeTillEnd = 8;

        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(0, nextDays.restDays);
        Assert.assertEquals(8, nextDays.restHours);
        Date expectedDate = DateCreation.createDate(25, 9, 2021, 18);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void timeTillEndInMiddle() {
        Date curDate = DateCreation.createDate(25, 9, 2021, 10);
        Date begDate = DateCreation.createDate(25, 9, 2021, 10);
        int timeTillEnd = 4;

        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(0, nextDays.restDays);
        Assert.assertEquals(4, nextDays.restHours);
        Date expectedDate = DateCreation.createDate(25, 9, 2021, 14);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void timeTillEndMoreThanWorkingDay() {
        Date curDate = DateCreation.createDate(25, 9, 2021, 10);
        Date begDate = DateCreation.createDate(25, 9, 2021, 10);
        int timeTillEnd = 12;

        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(1, nextDays.restDays);
        Assert.assertEquals(4, nextDays.restHours);
        Date expectedDate = DateCreation.createDate(26, 9, 2021, 14);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDate.getTime());
    }

    @Test
    public void timeTillEndAfterWeekends() {
        Date curDate = DateCreation.createDate(25, 9, 2021, 10);
        Date begDate = DateCreation.createDate(25, 9, 2021, 10);
        int timeTillEnd = 5 * 8 + 4;

        CountingRestDays nextDays = new CountingRestDays(begDate, curDate, timeTillEnd);
        Assert.assertFalse(nextDays.isFinished);
        Assert.assertEquals(7, nextDays.restDays);
        Assert.assertEquals(4, nextDays.restHours);
        Date expectedDate = DateCreation.createDate(1, 10, 2021, 14);
        Assert.assertEquals(expectedDate.getTime(), nextDays.lastStudyingDate.getTime());

    }


}
