package com.merpar.contentanalyzer.collector;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.merpar.contentanalyzer.controller.model.response.ContentDetails;
import com.merpar.contentanalyzer.model.Row;

import static java.lang.Math.round;

public class DetailResponseCollector implements Collector<Row, DetailsAccumulator, ContentDetails> {

    private static int calculateAvg(int elementsSum, float total) {
        return (total != 0)
                ? round(elementsSum / total)
                : 0;
    }

    @Override
    public Supplier<DetailsAccumulator> supplier() {
        return DetailsAccumulator::new;
    }

    @Override
    public BiConsumer<DetailsAccumulator, Row> accumulator() {
        return (detailsAccumulator, row) -> {
            if (detailsAccumulator.getFirstPostDate() == null) {
                detailsAccumulator.setFirstPostDate(row.getCreationDate());
            } else if (row.getCreationDate().isBefore(detailsAccumulator.getFirstPostDate())) {
                detailsAccumulator.setFirstPostDate(row.getCreationDate());
            }

            if (detailsAccumulator.getLastPostDate() == null) {
                detailsAccumulator.setLastPostDate(row.getCreationDate());
            } else if (row.getCreationDate().isAfter(detailsAccumulator.getLastPostDate())) {
                detailsAccumulator.setLastPostDate(row.getCreationDate());
            }

            detailsAccumulator.totalPostsIncrement();

            if (row.isAccepted()) {
                detailsAccumulator.totalAcceptedIncrement();
            }

            if (row.getScore() > detailsAccumulator.getMaxScore()) {
                detailsAccumulator.setMaxScore(row.getScore());
            }

            if (row.getScore() < detailsAccumulator.getMinScore()) {
                detailsAccumulator.setMinScore(row.getScore());
            }

            detailsAccumulator.addScoreSum(row.getScore());
            detailsAccumulator.addViewCount(row.getViewCount());
            detailsAccumulator.addAnswerCount(row.getAnswerCount());
            detailsAccumulator.addCommentCount(row.getCommentCount());
        };
    }

    @Override
    public BinaryOperator<DetailsAccumulator> combiner() {
        return (left, right) -> {
            throw new UnsupportedOperationException("Collector not available in parallel processing");
        };
    }

    @Override
    public Function<DetailsAccumulator, ContentDetails> finisher() {
        return detailsAccumulator ->
                ContentDetails.builder()
                        .firstPostDate(detailsAccumulator.getFirstPostDate())
                        .lastPostDate(detailsAccumulator.getLastPostDate())
                        .totalPostsCount(detailsAccumulator.getTotalPosts())
                        .totalAcceptedPostsCount(detailsAccumulator.getTotalAcceptedPosts())
                        .maximumScore(detailsAccumulator.getMaxScore())
                        .minimumScore(detailsAccumulator.getMinScore())
                        .averageScore(calculateAvg(detailsAccumulator.getScoreSum(), detailsAccumulator.getTotalPosts()))
                        .averageViewCount(calculateAvg(detailsAccumulator.getViewCountSum(), detailsAccumulator.getTotalPosts()))
                        .averageAnswerCount(calculateAvg(detailsAccumulator.getAnswerCountSum(), detailsAccumulator.getTotalPosts()))
                        .averageCommentCount(calculateAvg(detailsAccumulator.getCommentCountSum(), detailsAccumulator.getTotalPosts()))
                        .build();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}


