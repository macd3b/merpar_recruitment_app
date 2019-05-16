package com.merpar.contentanalyzer.service;

import com.merpar.contentanalyzer.Fixtures;
import com.merpar.contentanalyzer.controller.model.response.ContentDetails;
import com.merpar.contentanalyzer.exception.ContentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ContentServiceTest {

    private final Fixtures fixtures = new Fixtures();

    @Mock
    private ContentReceiver xmlContentReceiver;

    @InjectMocks
    private ContentService xmlContentService;

    @Test
    public void shouldReturnContentDetails() {
        //given
        given(xmlContentReceiver.getPosts(fixtures.getXmlFileUrl())).willReturn(fixtures.getPosts());

        //when
        final ContentDetails contentDetails = xmlContentService.analyse(fixtures.getXmlFileUrl());

        //then
        assertThat(contentDetails).isEqualTo(fixtures.getContentDetails());
    }

    @Test
    public void shouldReturnEmptyPostsContentDetails() {
        //given
        given(xmlContentReceiver.getPosts(fixtures.getXmlFileUrl())).willReturn(fixtures.getEmptyPosts());

        //when
        final ContentDetails contentDetails = xmlContentService.analyse(fixtures.getXmlFileUrl());

        //then
        assertThat(contentDetails).isEqualTo(fixtures.getEmptyPostsContentDetails());
    }

    @Test
    public void shouldThrowContentException() {

        assertThatThrownBy(
                //when
                () -> xmlContentService.analyse(fixtures.getXmlFileUrl())
                //then
        ).isInstanceOf(ContentException.class);
    }
}