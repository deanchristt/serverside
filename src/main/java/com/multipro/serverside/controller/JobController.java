package com.multipro.serverside.controller;

import com.multipro.serverside.client.JobClient;
import com.multipro.serverside.dto.JobDto;
import com.multipro.serverside.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobClient jobClient;

    private final AuthenticationService authenticationService;

    @GetMapping
    public HashMap<String, ArrayList<JobDto>> getAllJob(@RequestHeader("token") String token){
        authenticationService.validateToken(token);
        return jobClient.getData();
    }

    @GetMapping("/{id}")
    public JobDto getAllJob(@RequestHeader("token") String token, @PathVariable UUID id){
        authenticationService.validateToken(token);
        return jobClient.getDataById(token, id);
    }

    @GetMapping("/download")
    public void downloadCsv2(@RequestHeader("token") String token, HttpServletResponse response) throws IOException{
        authenticationService.validateToken(token);
        jobClient.download(response);
    }
}
