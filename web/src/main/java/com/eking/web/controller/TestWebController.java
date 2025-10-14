package com.eking.web.controller;

import com.eking.common.dto.HelloDto;
import com.eking.common.exception.CustomException;
import com.eking.common.mvc.BaseResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/hello")
    public BaseResp<String> hello(@RequestBody HelloDto helloDto) {
        if (helloDto.getName().equals("1")) {
            throw new CustomException("自定义异常");
        }
        return BaseResp.success("Hello from Web Controller for APP!");
    }

}