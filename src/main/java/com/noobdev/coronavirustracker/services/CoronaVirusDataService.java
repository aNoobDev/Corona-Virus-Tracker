package com.noobdev.coronavirustracker.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.noobdev.coronavirustracker.models.LocationStats;

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

    private static String VIRUS_DATA_URL = "https://api.covid19india.org/csv/latest/state_wise.csv";
    public String total;
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        for (CSVRecord record : records) {
    
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("State"));
            if(record.get("State").equals("Total"))
            {
            	total=record.get("Confirmed");
            }
            locationStat.setConfirmed(record.get("Confirmed"));
            locationStat.setRecovered(record.get("Recovered"));
            locationStat.setDeaths(record.get("Deaths"));
            locationStat.setActive(record.get("Active"));
            locationStat.setDate(record.get("Last_Updated_Time"));
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }

}
