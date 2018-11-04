package quartz.scheduler;

import models.WmiSchedule;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartz.job.RemainingJob;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class RemainingTimeScheduler {

    public static void remainingTime(int remaining, boolean type) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our JobWithMap class
            JobDetail job = JobBuilder.newJob(RemainingJob.class)
                    .withIdentity("remainingJob", "group2") // name "myJob", group "group1"
                    .usingJobData("type", type)
                    .usingJobData("remainingTime", remaining)
                    .build();

            // Trigger the job to run now, and then repeat every 1 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger2", "group2")
                    .startNow()
                    .withSchedule(cronSchedule("0 * 0/1 ? * * *"))
                    .build();


            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // and start it off
            scheduler.start();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
