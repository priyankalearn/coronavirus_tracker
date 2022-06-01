package io.priyanka.coronavirustrac.services;

import io.priyanka.coronavirustrac.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
   private static String VIRUS_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports_us/01-01-2021.csv";

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    private List<LocationStats> allStats = new ArrayList<>();

   @PostConstruct
   @Scheduled(cron = "* * 1 * * *")
   public void fetchVirusData() throws IOException, InterruptedException {
       List<LocationStats> newStats = new ArrayList<>();
       HttpClient client =HttpClient.newHttpClient();
       HttpRequest request=HttpRequest.newBuilder()
               .uri(URI.create(VIRUS_DATA_URL))
               .build();
       HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
       StringReader csvBodyreader= new StringReader(httpResponse.body());
       Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyreader);
       for (CSVRecord record : records) {
           LocationStats locationStat= new LocationStats();
           locationStat.setProvince_State(record.get("Province_State"));
           locationStat.setCountry_Region(record.get("Country_Region"));
           locationStat.setConfirmed(Integer.parseInt(record.get("Confirmed")));
           newStats.add(locationStat);

       }
       this.allStats=newStats;
    }
}
