package com.merpar.contentanalyzer.controller.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContentDetails {

    @JsonProperty("firstPost")
    LocalDateTime firstPostDate;

    @JsonProperty("lastPost")
    LocalDateTime lastPostDate;

    @JsonProperty("totalPosts")
    int totalPostsCount;

    @JsonProperty("totalAcceptedPosts")
    int totalAcceptedPostsCount;

    @JsonProperty("avgScore")
    int averageScore;

    @JsonProperty("maxScore")
    int maximumScore;

    @JsonProperty("minScore")
    int minimumScore;

    @JsonProperty("avgViewCount")
    int averageViewCount;

    @JsonProperty("avgAnswerCount")
    int averageAnswerCount;

    @JsonProperty("avgCommentCount")
    int averageCommentCount;
}
