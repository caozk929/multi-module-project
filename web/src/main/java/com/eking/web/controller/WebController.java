package com.eking.web.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caozk929
 */
@Data
@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Web Controller for APP!";
    }
}