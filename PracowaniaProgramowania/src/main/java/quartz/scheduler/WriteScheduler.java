package quartz.scheduler;

import models.Citizen;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartz.job.WriterJob;

import java.util.Date;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class WriteScheduler {

   public static void writeSchedulerRun(List<Citizen> citizens) {
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our JobWithMap class
            JobDetail job = JobBuilder.newJob(WriterJob.class)
                    .withIdentity("writeJob", "group1") // name "myJob", group "group1"
                    .build();

            job.getJobDataMap().put("citizensList", citizens);
            Date date = new Date();
            if(!(date.getSeconds() == 0))
                date.setMinutes(date.getMinutes() + 1);

            // Trigger the job to run now, and then repeat every 1 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startAt(date)
                    .withSchedule(cronSchedule("0/30 * * * * ? *"))
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
