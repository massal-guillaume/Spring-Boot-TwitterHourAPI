package com.massal.twittertime.twittertime.repo;

import com.massal.twittertime.twittertime.model.TweetHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetHourRepository extends JpaRepository<TweetHour, Long> {
}
