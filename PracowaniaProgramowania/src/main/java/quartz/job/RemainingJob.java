package quartz.job;

import org.quartz.*;

@PersistJobDataAfterExecution
public class RemainingJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();

        //Job values Map
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        //read values
        int time = dataMap.getInt("remainingTime");
        boolean type = dataMap.getBoolean("type");
//        if (dataMap.containsKey("counter")) {
//            counter = dataMap.getInt("counter");
//        }
        if(type)
            System.out.println("\nDo konca przerwy pozostalo : " + time + " minut");
        else
            System.out.println("\nDo konca zajec pozostalo : " + time + " minut");

        time--;
        //increment and save counter
        dataMap.put("remainingTime", time);
    }
}
