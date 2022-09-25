package com.massal.twittertime.twittertime.controller;

import com.massal.twittertime.twittertime.model.TweetHour;
import com.massal.twittertime.twittertime.model.User;
import com.massal.twittertime.twittertime.service.TweetService;
import com.massal.twittertime.twittertime.service.impl.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.massal.twittertime.twittertime.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.InterruptedNamingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Controller
public class UserController {



    public UserService userService;

    public TweetService tweetService;

    @Autowired
    public UserController(UserService userservice,TweetService tweetService){
        this.tweetService = tweetService;
        this.userService = userservice;

    }

    @GetMapping(value = "/")
    public String getpage() {
        return "page1.html";
    }

    @PostMapping(value = "api/save")
    public  String saveUser(@ModelAttribute User user,final RedirectAttributes redirectAttributes) throws URISyntaxException, IOException, ParseException, InterruptedException {
        TweetHour tweethour = new TweetHour();
        try{
            io.github.redouane59.twitter.dto.user.User user1 = tweetService.getUserFromUserName(user.getTwitter_username());
            System.out.println(user1);
        }catch (Exception e){
            return "redirect:/erreur_pseudo";
        }
        tweethour.setHour(tweetService.gettime(tweetService.gettweets(user.getTwitter_username())));
        tweetService.saveTweetHour(tweethour);
        userService.saveUser(user);
        return "redirect:/graph";
    }

    @GetMapping("/erreur_pseudo")
    public String getPieChart() {
        return "erreur_pseudo";
    }


    @GetMapping("/graph")
    public String getPieChart( Model model) {
        Map<Integer,Integer> moyenne = new HashMap<Integer,Integer>();
        for(int x=0;x<24;x++){
                moyenne.put(x,0);
        }
        for(int i=0;i<this.tweetService.getTweetHour().size()-1;i++){
            for(int y=0;y<24;y++) {
               int hour = this.tweetService.getTweetHour().get(i).getHour().get(y);
               int value = hour+moyenne.get(y);
               moyenne.put(y,value);

            }
        }
        for(Map.Entry<Integer, Integer> entry : moyenne.entrySet()){
            entry.setValue(entry.getValue()/this.tweetService.getTweetHour().size());
        }
        model.addAttribute("chartData",this.tweetService.getTweetHour().get(this.tweetService.getTweetHour().size()-1).getHour());
        model.addAttribute("moyenneData", moyenne);
        return "google-charts";
    }


}
