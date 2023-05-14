package com.hdfk7.module.controller.exception;

import com.hdfk7.proto.base.result.Result;
import com.hdfk7.proto.base.result.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "404")
@RestController
@RequestMapping("/error")
public class GlobalErrorController implements ErrorController {
    @RequestMapping
    @Operation(summary = "404处理")
    public Result<?> error() {
        String msg = "No handler found";
        return ResultCode.SYS_ERROR.bindResult().bindMsg(msg);
    }
}
