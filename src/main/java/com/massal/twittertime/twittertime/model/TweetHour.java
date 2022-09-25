package com.massal.twittertime.twittertime.model;

import javax.naming.InterruptedNamingException;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class TweetHour {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    private Map<Integer, Integer> hour;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Integer,Integer> getHour() {
        return hour;
    }

    public void setHour( Map<Integer, Integer> hour) {
        this.hour = hour;
    }


}
