package com.eking.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caozk929
 */
@Tag(name = "Web开放测试接口", description = "Web开放测试接口描述")
@Data
@RestController
@RequestMapping("/web")
public class TestWebController {

    @Operation(summary = "hello测试接口", description = "hello测试接口描述")
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Web Controller for APP!";
    }

}