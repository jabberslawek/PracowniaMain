import helpers.CitizenHelper;
import models.Citizen;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import quartz.job.WriterJob;
import quartz.scheduler.WriteScheduler;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

class MainLogic {

    private final Scanner in = new Scanner(System.in);
    private List<Citizen> citizens = new ArrayList();

    private int checkCitizen(String citizenData, List<Citizen> citizens) throws Exception {
        String pesel = citizenData.substring(citizenData.lastIndexOf(" ") + 1, 11);
        for(Citizen citizen : citizens){
            if(citizen.getPesel().equals(pesel)) {
                return citizens.indexOf(citizen);
            }
        }
        return -1;
    }

    void citizenAdd() throws Exception {
        String citizenCity, citizenData;
        WriteScheduler.writeSchedulerRun(citizens);
        //noinspection InfiniteLoopStatement
        for(;;) {
            System.out.print("Podaj miasto : ");
            citizenCity = in.next();
            in.nextLine();
            System.out.print("Podaj imie, nazwisko, pesel : ");
            citizenData = in.nextLine();
            int index = checkCitizen(citizenData, citizens);
            if(index != -1) {
                citizens.get(index).setFullData(citizenData);
                citizens.get(index).setCity(citizenCity);
            } else {
                Citizen citizen = new Citizen(citizenCity, citizenData);
                if(citizen.getPesel() != null) {
                    citizens.add(citizen);
                }
            }
        }
    }

}
