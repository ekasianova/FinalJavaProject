import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CountingRestDays {
    Date beginningDate;
    Date currentDate;
    Date lastStudyingDay;
    boolean isFinished = false;
    long days = 0;
    long hours = 0;
    int timeOfAllCourses;

    public CountingRestDays(Date beginningDate, Date currentDate, int timeTillEnd){
        this.beginningDate = beginningDate;
        this.currentDate = currentDate;
        this.timeOfAllCourses = timeTillEnd;
        this.lastStudyingDay = getFinalDate();
        counting();
    }

    private void counting(){

        long diffInMillis;
        isFinished = (lastStudyingDay.before(currentDate)) ?  true :  false;
        diffInMillis = Math.abs(currentDate.getTime() - lastStudyingDay.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        days = diff / 24;
        hours = diff % 24;

    }

    public Date getFinalDate(){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(beginningDate);
        int restTime = timeOfAllCourses;
        while (restTime > 0){
            {
                if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                        &&(Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                    if(restTime <= 8){
                        int time = cal1.get(Calendar.HOUR_OF_DAY) + restTime;
                        cal1.set(Calendar.HOUR_OF_DAY, time);
                        break;
                    }
                    restTime -= 8;
                }
                cal1.add(Calendar.DATE,1);
            }
        }
        return cal1.getTime();
    }
}
