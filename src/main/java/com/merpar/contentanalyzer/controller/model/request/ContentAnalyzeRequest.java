package com.merpar.contentanalyzer.controller.model.request;

import javax.validation.constraints.NotNull;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public final class ContentAnalyzeRequest {

    @NotNull(message = "File url can not be null")
    URL url;

    @JsonCreator
    public ContentAnalyzeRequest(URL url) {
        this.url = url;
    }
}
