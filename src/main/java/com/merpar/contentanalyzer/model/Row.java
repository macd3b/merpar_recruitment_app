package com.merpar.contentanalyzer.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class Row {

    LocalDateTime creationDate;
    int score;
    int viewCount;
    int commentCount;
    int answerCount;
    String acceptedAnswerId;

    public Row(LocalDateTime creationDate, int score, String acceptedAnswerId) {
        this.creationDate = creationDate;
        this.score = score;
        this.acceptedAnswerId = acceptedAnswerId;
        this.viewCount = 0;
        this.commentCount = 0;
        this.answerCount = 0;
    }

    public boolean isAccepted() {
        return acceptedAnswerId != null;
    }

}
