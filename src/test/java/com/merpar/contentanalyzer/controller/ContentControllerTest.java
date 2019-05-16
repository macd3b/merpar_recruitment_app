package com.merpar.contentanalyzer.controller;

import com.merpar.contentanalyzer.Fixtures;
import com.merpar.contentanalyzer.controller.model.request.ContentAnalyzeRequest;
import com.merpar.contentanalyzer.exception.ContentException;
import com.merpar.contentanalyzer.service.ContentService;
import com.thoughtworks.xstream.XStreamException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest
public class ContentControllerTest {

    private final Fixtures fixtures = new Fixtures();

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private ContentService xmlContentService;

    @Test
    public void shouldReturnXmlContent() {
        //given
        given(xmlContentService.analyse(fixtures.getXmlFileUrl()))
                .willReturn(fixtures.getContentDetails());
        //when
        webTestClient.post().uri(fixtures.getAnalyzeUrl())
                .body(Mono.just(fixtures.getXmlContentAnalyzeRequest()), ContentAnalyzeRequest.class)
                .exchange()
                //then
                .expectStatus().isOk()
                .expectBody().json(fixtures.getResponseBody());
    }

    @Test
    public void shouldReturnNotFoundError() {
        //given
        given(xmlContentService.analyse(fixtures.getXmlFileUrl())).willThrow(XStreamException.class);

        //when
        webTestClient.post().uri(fixtures.getAnalyzeUrl())
                .body(Mono.just(fixtures.getXmlContentAnalyzeRequest()), ContentAnalyzeRequest.class)
                .exchange()
                //then
                .expectStatus().isNotFound()
                .expectBody().json(fixtures.getNotFoundErrorResponse());
    }

    @Test
    public void shouldReturnUnprocessableEntityError() {
        //given
        given(xmlContentService.analyse(fixtures.getXmlFileUrl())).willThrow(ContentException.class);

        //when
        webTestClient.post().uri(fixtures.getAnalyzeUrl())
                .body(Mono.just(fixtures.getXmlContentAnalyzeRequest()), ContentAnalyzeRequest.class)
                .exchange()
                //then
                .expectStatus().is4xxClientError()
                .expectBody().json(fixtures.getUnprocessableEntityErrorResponse());
    }
}