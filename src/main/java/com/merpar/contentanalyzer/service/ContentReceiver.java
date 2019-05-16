package com.merpar.contentanalyzer.service;

import java.net.URL;

import com.merpar.contentanalyzer.model.Posts;
import com.thoughtworks.xstream.XStream;
import org.springframework.stereotype.Component;

@Component
public class ContentReceiver {

    private final XStream xStream;

    public ContentReceiver(XStream xStream) {
        this.xStream = xStream;
    }

    Posts getPosts(final URL url) {
        return (Posts) xStream.fromXML(url);
    }
}
