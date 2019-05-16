package com.merpar.contentanalyzer.controller;

import javax.validation.Valid;

import com.merpar.contentanalyzer.controller.model.request.ContentAnalyzeRequest;
import com.merpar.contentanalyzer.controller.model.response.ContentDetails;
import com.merpar.contentanalyzer.service.ContentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    private final ContentService xmlContentService;

    public ContentController(final ContentService xmlContentService) {
        this.xmlContentService = xmlContentService;
    }

    @PostMapping("/analyze")
    public ContentDetails analyze(final @Valid @RequestBody ContentAnalyzeRequest request) {
        return xmlContentService.analyse(request.getUrl());
    }
}
