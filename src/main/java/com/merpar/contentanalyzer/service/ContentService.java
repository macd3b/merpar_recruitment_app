package com.merpar.contentanalyzer.service;

import java.net.URL;
import java.util.Optional;

import com.merpar.contentanalyzer.collector.DetailResponseCollector;
import com.merpar.contentanalyzer.controller.model.response.ContentDetails;
import com.merpar.contentanalyzer.exception.ContentException;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private final ContentReceiver xmlContentCollector;

    public ContentService(final ContentReceiver xmlContentCollector) {
        this.xmlContentCollector = xmlContentCollector;
    }

    public ContentDetails analyse(final URL url) {
        return Optional.ofNullable(xmlContentCollector.getPosts(url))
                .map(posts -> posts
                        .getRows()
                        .stream()
                        .collect(new DetailResponseCollector()))
                .orElseThrow(ContentException::new);
    }
}
