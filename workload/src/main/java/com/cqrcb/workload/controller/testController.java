package com.cqrcb.workload.controller;

import com.cqrcb.workload.common.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/staff-work/test")
@RestController
@Slf4j
public class testController {
    @GetMapping("/visit")
    public RestResponse<String> testController() {
        return RestResponse.success("visit success");
    }
}
