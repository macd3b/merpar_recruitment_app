package com.merpar.contentanalyzer;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import com.merpar.contentanalyzer.controller.model.request.ContentAnalyzeRequest;
import com.merpar.contentanalyzer.controller.model.response.ContentDetails;
import com.merpar.contentanalyzer.model.Posts;
import com.merpar.contentanalyzer.model.Row;
import lombok.Value;

import static java.time.Month.JANUARY;

@Value
public class Fixtures {

    String analyzeUrl = "/analyze";
    String XML_FILE_PATH = "http://test.xmlfile.xml";
    URL xmlFileUrl = prepareUrl();
    LocalDateTime localDateTime20150101 = LocalDateTime.of(2015, JANUARY, 1, 0, 15);
    Row row1 = new Row(localDateTime20150101, 0, null);

    LocalDateTime localDateTime20150113 = LocalDateTime.of(2015, JANUARY, 13, 12, 15);
    Row row2 = new Row(localDateTime20150113, 4, null);

    LocalDateTime localDateTime2015016 = LocalDateTime.of(2015, JANUARY, 6, 13, 45);
    Row row3 = new Row(localDateTime2015016, 8, "1");

    Posts posts = new Posts(Arrays.asList(row1, row2, row3));
    Posts emptyPosts = new Posts(Collections.emptyList());

    ContentAnalyzeRequest xmlContentAnalyzeRequest = new ContentAnalyzeRequest(xmlFileUrl);

    String notFoundErrorResponse = "{\n" +
            "  \"message\": \"Resource not found\"\n" +
            "}";


    String unprocessableEntityErrorResponse = "{\n" +
            "  \"message\": \"Resource fetching error\"\n" +
            "}";

    String responseBody = "{\n" +
            "  \"firstPost\": \"2015-01-01T00:15:00\",\n" +
            "  \"lastPost\": \"2015-01-13T12:15:00\",\n" +
            "  \"totalPosts\": 3,\n" +
            "  \"totalAcceptedPosts\": 1,\n" +
            "  \"avgScore\": 4,\n" +
            "  \"maxScore\": 8,\n" +
            "  \"minScore\": 0,\n" +
            "  \"avgViewCount\": 0,\n" +
            "  \"avgAnswerCount\": 0,\n" +
            "  \"avgCommentCount\": 0\n" +
            "}";

    ContentDetails contentDetails = ContentDetails.builder()
            .firstPostDate(localDateTime20150101)
            .lastPostDate(localDateTime20150113)
            .totalAcceptedPostsCount(1)
            .totalPostsCount(3)
            .averageScore(4)
            .maximumScore(8)
            .build();

    ContentDetails emptyPostsContentDetails = ContentDetails.builder().build();

    private URL prepareUrl() {
        try {
            return new URL(XML_FILE_PATH);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
