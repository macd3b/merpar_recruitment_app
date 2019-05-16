package com.merpar.contentanalyzer.configuration;

import com.merpar.contentanalyzer.model.Posts;
import com.merpar.contentanalyzer.model.Row;
import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentAnalyzerConfiguration {

    private static final String POSTS_ELEMENT = "posts";
    private static final String ROW_ELEMENT = "row";
    private static final String ROWS_COLLECTION = "rows";
    private static final String SCORE_ATTRIBUTE_NAME = "score";
    private static final String SCORE_ALIAS = "Score";
    private static final String VIEW_COUNT_ALIAS = "ViewCount";
    private static final String VIEW_COUNT_ATTRIBUTE_NAME = "viewCount";
    private static final String COMMENT_COUNT_ALIAS = "CommentCount";
    private static final String COMMENT_COUNT_ATTRIBUTE_NAME = "commentCount";
    private static final String ANSWER_COUNT_ALIAS = "AnswerCount";
    private static final String ANSWER_COUNT_ATTRIBUTE_NAME = "answerCount";
    private static final String CREATION_DATE_ATTRIBUTE_NAME = "creationDate";
    private static final String CREATION_DATE_ALIAS = "CreationDate";
    private static final String ACCEPTED_ANSWER_ID_ATTRIBUTE_NAME = "acceptedAnswerId";
    private static final String ACCEPTED_ANSWER_ID_ALIAS = "AcceptedAnswerId";

    @Bean
    public XStream postsXStream() {
        final Class<?>[] classes = new Class[]{Row.class, Posts.class};
        final XStream xStream = new XStream();

        xStream.alias(POSTS_ELEMENT, Posts.class);
        xStream.alias(ROW_ELEMENT, Row.class);
        xStream.aliasAttribute(Row.class, SCORE_ATTRIBUTE_NAME, SCORE_ALIAS);
        xStream.aliasAttribute(Row.class, VIEW_COUNT_ATTRIBUTE_NAME, VIEW_COUNT_ALIAS);
        xStream.aliasAttribute(Row.class, COMMENT_COUNT_ATTRIBUTE_NAME, COMMENT_COUNT_ALIAS);
        xStream.aliasAttribute(Row.class, ANSWER_COUNT_ATTRIBUTE_NAME, ANSWER_COUNT_ALIAS);
        xStream.aliasAttribute(Row.class, CREATION_DATE_ATTRIBUTE_NAME, CREATION_DATE_ALIAS);
        xStream.aliasAttribute(Row.class, ACCEPTED_ANSWER_ID_ATTRIBUTE_NAME, ACCEPTED_ANSWER_ID_ALIAS);
        xStream.addImplicitCollection(Posts.class, ROWS_COLLECTION);

        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(classes);

        return xStream;
    }
}
