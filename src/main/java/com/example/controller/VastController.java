package com.example.controller;

import com.example.service.VastService;
import com.example.utils.JsonExporter;
import com.example.model.VastModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/vast")
public class VastController {

    @Autowired
    private VastService vastService;

    @GetMapping("/file")
    public VastModel parseVastFromFile(@RequestParam String filePath) throws Exception {
        VastModel vastModel = vastService.parseFromFile(filePath);
        return vastModel;
    }

    @GetMapping("/url")
    public String parseVastFromUrl(@RequestParam String url) throws Exception {
        VastModel vastModel = vastService.parseFromUrl(url);
        return JsonExporter.exportToJson(vastModel);
    }
}
