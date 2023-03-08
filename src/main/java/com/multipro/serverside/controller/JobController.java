package com.multipro.serverside.controller;

import com.multipro.serverside.client.JobClient;
import com.multipro.serverside.dto.JobDto;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/serverside")
@RequiredArgsConstructor
public class JobController {

    private final JobClient jobClient;

    @GetMapping("/jobs")
    public List<JobDto> getAllJob(@RequestHeader("token") String token){
        return jobClient.getData(token);
    }

    @GetMapping("/jobs/{id}")
    public JobDto getAllJob(@RequestHeader("token") String token, @PathVariable UUID id){
        return jobClient.getDataById(token, id);
    }

    @GetMapping("/jobs/download")
    public void downloadCsv2(HttpServletResponse response) throws IOException{
        jobClient.download(response);
    }
}
