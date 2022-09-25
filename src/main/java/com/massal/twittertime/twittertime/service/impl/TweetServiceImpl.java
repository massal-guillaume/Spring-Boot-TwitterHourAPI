package com.massal.twittertime.twittertime.service.impl;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.massal.twittertime.twittertime.model.TweetHour;
import com.massal.twittertime.twittertime.repo.TweetHourRepository;
import com.massal.twittertime.twittertime.service.TweetService;
import io.github.redouane59.twitter.ITwitterClientV2;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.others.BearerToken;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.dto.tweet.TweetV2;
import io.github.redouane59.twitter.dto.user.User;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class TweetServiceImpl implements TweetService {


    private  TweetHourRepository tweetHourRepository;
    private static TwitterClient twitterClient;


    @Autowired
    public TweetServiceImpl (TweetHourRepository tweetHourrepository) {
        this.tweetHourRepository = tweetHourrepository;
        this.twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken()
                .accessTokenSecret()
                .apiKey()
                .apiSecretKey()
                .build());
    }

    public User getUserFromUserName(String string){
        User user = twitterClient.getUserFromUserName(string);
        return user;
    }


    //Fonction qui recupere les tweet des 7 derniers jours de l'@
    @Override
    public  List<TweetV2.TweetData> gettweets(String username) throws URISyntaxException, IOException, InterruptedException {
        User user   = twitterClient.getUserFromUserName(username);
        TweetList tweet1 = twitterClient.searchTweets("(from:"+username+") -is:retweet");
        List<TweetV2.TweetData> listetweet = tweet1.getData();
        return listetweet;
    }

    @Override
    public  Map<Integer, Integer> gettime(List<TweetV2.TweetData> tweetList) {
        Map<Integer, Integer> mapHour = new HashMap<Integer, Integer>();
        for(int i=0;i<24;i++){
            mapHour.put(i,0);
        }
        for( TweetV2.TweetData element : tweetList ) {
           int hour =  element.getCreatedAt().plusHours(2).getHour();
           mapHour.replace(hour,mapHour.get(hour)+1);
        }
        return mapHour;
    }

    @Override
    public void saveTweetHour(TweetHour tweetHour) {
        tweetHourRepository.save(tweetHour);
    }

    @Override
    public List<TweetHour> getTweetHour() {
        return tweetHourRepository.findAll();
    }




}
