package com.merpar.contentanalyzer.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Posts {
    List<Row> rows;
}
