package com.massal.twittertime.twittertime.service;

import com.massal.twittertime.twittertime.model.TweetHour;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.dto.tweet.TweetV2;
import io.github.redouane59.twitter.dto.user.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TweetService {

    List<TweetV2.TweetData> gettweets(String username) throws URISyntaxException, IOException, InterruptedException, ParseException;

    Map<Integer, Integer> gettime(List<TweetV2.TweetData> tweetList);

    void saveTweetHour(TweetHour tweetHour);
    List<TweetHour> getTweetHour();

    User getUserFromUserName(String string);
}
