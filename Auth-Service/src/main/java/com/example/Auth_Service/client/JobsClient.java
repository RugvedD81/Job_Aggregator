package com.example.Auth_Service.client;

import com.example.Auth_Service.entity.Jobs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CRAWLER-SERVICE1")
public interface JobsClient {

    @GetMapping("/jobs/all")
    List<Jobs> getAllJobs();

    @GetMapping("/jobs/search")
    List<Jobs> getByResponse(@RequestParam("keyword") String keyword);
}
