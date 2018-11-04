package quartz.job;

import models.Citizen;
import org.quartz.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class WriterJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        {
            //Job values Map
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();

            //read values
            ArrayList<Citizen> citizens = (ArrayList) dataMap.get("citizensList");
            String city = "";
            try {
                String newLineSeparator = ":\n";
                FileOutputStream fos = new FileOutputStream(new File(".\\odp.txt"), false);
                citizens.sort(Comparator.comparing(Citizen::getCity));
                if(citizens.size() != 0)
                    city = citizens.get(0).getCity();
                fos.write((city + newLineSeparator).getBytes());
                for (Citizen citizen : citizens) {
                    String temp = "\t " + citizen.getFullData() + "\n";
                    if(!city.equals(citizen.getCity())) {
                        city = citizen.getCity();
                        fos.write((city + newLineSeparator).getBytes());
                    }
                    fos.write(temp.getBytes());
                }
                fos.flush();
                fos.close();
            } catch (Exception ex) {
                System.out.println("ERR " + ex.getMessage());
            }

        }
    }
}
