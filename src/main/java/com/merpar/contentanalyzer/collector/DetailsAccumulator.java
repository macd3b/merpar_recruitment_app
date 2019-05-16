package com.merpar.contentanalyzer.collector;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class DetailsAccumulator {

    LocalDateTime firstPostDate;
    LocalDateTime lastPostDate;
    int totalPosts;
    int totalAcceptedPosts;
    int scoreSum;
    int maxScore;
    int minScore;
    int viewCountSum;
    int answerCountSum;
    int commentCountSum;

    void totalPostsIncrement() {
        this.totalPosts++;
    }

    void totalAcceptedIncrement() {
        this.totalAcceptedPosts++;
    }

    void addScoreSum(int value) {
        this.scoreSum += value;
    }

    void addViewCount(int value) {
        this.viewCountSum += value;
    }

    void addCommentCount(int value) {
        this.commentCountSum += value;
    }

    void addAnswerCount(int value) {
        this.answerCountSum += value;
    }
}

