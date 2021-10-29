package javaTask;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CountingRestDays {
    public Date beginningDate;
    public Date currentDate;
    public Date lastStudyingDate;
    public boolean isFinished = false;
    public long restDays;
    public long restHours;
    public int timeOfAllCourses;
    public final static int DAY_HOURS = 24;
    public final static int WORKING_HOURS = 8;

    public CountingRestDays(Date beginningDate, Date currentDate, int timeTillEnd) {
        this.beginningDate = beginningDate;
        this.currentDate = currentDate;
        this.timeOfAllCourses = timeTillEnd;
        this.lastStudyingDate = getFinalDate();
        counting();
    }

    private void counting() {
        long diffInMillis;
        isFinished = lastStudyingDate.before(currentDate) || lastStudyingDate.equals(currentDate);
        diffInMillis = Math.abs(currentDate.getTime() - lastStudyingDate.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        restDays = diff / DAY_HOURS;
        restHours = diff % DAY_HOURS;
    }

    public Date getFinalDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginningDate);
        int restTime = timeOfAllCourses;
        int beginningOfWorkingDay = 10;
        while (restTime > 0) {
            {
                if ((Calendar.SATURDAY != calendar.get(Calendar.DAY_OF_WEEK))
                        && (Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK))) {
                    if (restTime <= WORKING_HOURS) {
                       // int time = calendar.get(Calendar.HOUR_OF_DAY) + restTime;
                        calendar.set(Calendar.HOUR_OF_DAY, beginningOfWorkingDay + restTime);
                        break;
                    }
                    restTime -= WORKING_HOURS;
                }
                calendar.add(Calendar.DATE, 1);
            }
        }
        return calendar.getTime();
    }
}
