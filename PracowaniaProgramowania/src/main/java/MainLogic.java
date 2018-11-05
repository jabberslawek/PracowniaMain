import helpers.WmiScheduleHelper;
import models.Citizen;
import quartz.scheduler.RemainingTimeScheduler;
import quartz.scheduler.WriteScheduler;

import java.util.*;

class MainLogic {

    private final Scanner in = new Scanner(System.in);
    private List<Citizen> citizens = new ArrayList();
    private WmiScheduleHelper wmiHelper = new WmiScheduleHelper();

    private int checkCitizen(String citizenData, List<Citizen> citizens) {
        String pesel = citizenData.substring(citizenData.lastIndexOf(" ") + 1);
        for(Citizen citizen : citizens){
            if(citizen.getPesel().equals(pesel)) {
                return citizens.indexOf(citizen);
            }
        }
        return -1;
    }

    void citizenAdd() throws Exception {
        String citizenCity, citizenData;
        int index = -1, time = wmiHelper.checkActivity(new Date());
        WriteScheduler.writeSchedulerRun(citizens);
        RemainingTimeScheduler.remainingTime(time, wmiHelper.isType());
        //noinspection InfiniteLoopStatement
        for(;;) {
            System.out.print("Podaj miasto : ");
            citizenCity = in.next();
            in.nextLine();
            System.out.print("Podaj imie, nazwisko, pesel : ");
            citizenData = in.nextLine();
            if(citizens.size() != 0)
                index = checkCitizen(citizenData, citizens);
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
