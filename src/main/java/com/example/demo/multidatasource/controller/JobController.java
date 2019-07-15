package com.example.demo.multidatasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.multidatasource.domain.ScheduleJob;
import com.example.demo.multidatasource.exception.ServiceException;
import com.example.demo.multidatasource.service.impl.JobService;

@RestController
@RequestMapping(value = "/job")
public class JobController {
	@Autowired
    private JobService jobService;

    @GetMapping
    public Object getAllJob() {
        return jobService.getAllJob();
    }

    @GetMapping("/{id}")
    public Object getJob(@PathVariable("id") Long jobId) throws ServiceException {
        return jobService.select(jobId);
    }

    @PutMapping("/update/{id}")
    public Object updateJob(@PathVariable("id") Long jobId, @RequestBody ScheduleJob newScheduleJob) throws ServiceException {
        return jobService.update(jobId, newScheduleJob);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteJob(@PathVariable("id") Long jobId) throws ServiceException {
        return jobService.delete(jobId);
    }

    @PostMapping("/save")
    public Object saveJob(@RequestBody ScheduleJob newScheduleJob) throws ServiceException {
        return jobService.add(newScheduleJob);
    }


    @GetMapping("/run/{id}")
    public Object runJob(@PathVariable("id") Long jobId) throws ServiceException {
        return jobService.run(jobId);
    }


    @GetMapping("/pause/{id}")
    public Object pauseJob(@PathVariable("id") Long jobId) throws ServiceException {
        return jobService.pause(jobId);
    }

    @GetMapping("/resume/{id}")
    public Object resumeJob(@PathVariable("id") Long jobId) throws ServiceException {
        return jobService.resume(jobId);
    }
}
