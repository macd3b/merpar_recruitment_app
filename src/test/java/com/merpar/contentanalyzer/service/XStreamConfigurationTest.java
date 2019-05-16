package com.merpar.contentanalyzer.service;

import java.net.URL;
import java.nio.file.Files;

import com.merpar.contentanalyzer.configuration.ContentAnalyzerConfiguration;
import com.merpar.contentanalyzer.model.Posts;
import com.thoughtworks.xstream.XStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockserver.model.XmlBody.xml;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ContentAnalyzerConfiguration.class)
public class XStreamConfigurationTest {

    private static final String XML_FILE_NAME = "test.xml";
    @Rule
    public final MockServerRule mockServerRule = new MockServerRule(this);
    @Autowired
    private XStream postsXStream;
    private MockServerClient mockServerClient;

    @Test
    public void shouldReturnMockServerPosts() throws Exception {

        //given
        final String xmlString = new String(
                Files.readAllBytes(
                        new ClassPathResource(XML_FILE_NAME)
                                .getFile()
                                .toPath()
                )
        );
        mockServerClient.when(HttpRequest.request()
                .withMethod("GET")
                .withPath(String.format("/%s", XML_FILE_NAME)))
                .respond(HttpResponse.response().withBody(xml(xmlString)));


        //when
        final Object result = postsXStream.fromXML(
                new URL(
                        String.format("http://localhost:%s/%s", mockServerRule.getPort(), XML_FILE_NAME)
                )
        );

        //when
        assertThat(result).isInstanceOf(Posts.class);
        assertThat(((Posts) result).getRows().size()).isEqualTo(80);
    }
}