package com.progress.progressapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: HelloWordController
 * date: 2023/8/29 16:34
 *
 * @author: wangxiaoning
 */
@RestController
@RequestMapping("/sys")
public class HelloWordController {

    /**
     * description: 心跳检测
     * @param
     * @return
     */
    @GetMapping("/heartbeat")
    public String heartbeat(){
        return "SUCCESS";
    }
}
