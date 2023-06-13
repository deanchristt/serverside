package com.multipro.serverside.client;

import com.multipro.serverside.dto.JobDto;
import com.multipro.serverside.service.AuthenticationService;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobClient {

    private final RestTemplate restTemplate;

    private final AuthenticationService authenticationService;

    @Value("${jobs.url}")
    private String jobsUrl;

    @Value("${jobs.url.by.id}")
    private String jobsUrlById;


    public HashMap<String, ArrayList<JobDto>> getData(String token) {
        HashMap<String, ArrayList<JobDto>> map = new HashMap<>();
        JobDto[] data = restTemplate.getForObject(jobsUrl, JobDto[].class);

        for (JobDto d : data) {
            String location = d.getLocation();
            ArrayList<JobDto> list = map.get(location);
            if (list == null) {
                list = new ArrayList<>();
                map.put(location, list);
            }
            list.add(d);
        }

        return map;
    }

    public JobDto getDataById(String token, UUID id) {
        authenticationService.validateToken(token);
        return restTemplate.getForObject(jobsUrlById, JobDto.class, id);
    }

    public void download(HttpServletResponse response) throws IOException {
        String filename = "data.csv";
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        CSVWriter writer = new CSVWriter(response.getWriter());

        JobDto[] jobDtos = restTemplate.getForObject(jobsUrl, JobDto[].class);
        for (JobDto data : jobDtos) {
            String[] line = {data.getId().toString(), data.getType(), data.getUrl(), data.getCreated_at(),
                    data.getCompany(), data.getCompany_url(), data.getLocation(), data.getTitle(), data.getDescription(),
                    data.getHow_to_apply(), data.getCompany_logo()};
            writer.writeNext(line);
        }

        writer.flush();
        writer.close();
    }
}
