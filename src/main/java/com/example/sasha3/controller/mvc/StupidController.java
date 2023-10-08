package com.example.sasha3.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StupidController {
    @GetMapping("/history")
    public String history() {
        return "/stupid/history";
    }

    @GetMapping("/filosof")
    public String filosof() {
        return "/stupid/filosof";
    }

    @GetMapping("/social")
    public String social() {
        return "/stupid/social";
    }

    @GetMapping("/imagination")
    public String imagination() {
        return "/stupid/imagination";
    }

    @GetMapping("/traditional")
    public String traditional() {
        return "/stupid/traditional";
    }

    @GetMapping("/auto")
    public String auto() {
        return "/stupid/auto";
    }

    @GetMapping("/economic")
    public String economic() {
        return "/stupid/economic";
    }

    @GetMapping("/now")
    public String now() {
        return "/stupid/now";
    }
}
