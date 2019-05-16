package com.merpar.contentanalyzer.service;

import com.merpar.contentanalyzer.Fixtures;
import com.merpar.contentanalyzer.model.Posts;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ContentReceiverTest {

    private final Fixtures fixtures = new Fixtures();

    @Mock
    private XStream xStream;

    @InjectMocks
    private ContentReceiver xmlContentReceiver;

    @Test
    public void shouldReturnPosts() {
        //given
        given(xStream.fromXML(fixtures.getXmlFileUrl())).willReturn(fixtures.getPosts());

        //when
        final Posts posts = xmlContentReceiver.getPosts(fixtures.getXmlFileUrl());

        //then
        assertThat(posts).isEqualTo(fixtures.getPosts());
    }

}