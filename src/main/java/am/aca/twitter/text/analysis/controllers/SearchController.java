package am.aca.twitter.text.analysis.controllers;

import am.aca.twitter.text.analysis.configurations.TwitterConfiguration;
import am.aca.twitter.text.analysis.dtos.TweetDto;
import am.aca.twitter.text.analysis.entities.Tweet;
import am.aca.twitter.text.analysis.mappers.TweetMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class SearchController {


    private final TwitterConfiguration twitterConfiguration;
    private final TweetMapper tweetMapper;
    public SearchController(TwitterConfiguration twitterConfiguration, TweetMapper tweetMapper) {
        this.twitterConfiguration = twitterConfiguration;
        this.tweetMapper = tweetMapper;
    }


    @GetMapping
    public ModelAndView mainPage() {
        return new ModelAndView("index");
    }

    @GetMapping("/search")
    public ResponseEntity<List<TweetDto>> getTweets(@RequestParam("keyword") String keyWord) {
        Twitter twitter = twitterConfiguration.getTwitter();

        Query query = new Query(keyWord);
        try {
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            List<Tweet> tweetList = new ArrayList<Tweet>();
            for(Status status : tweets) {
                String imageUrl = status.getUser().getMiniProfileImageURL();
                String text = status.getText();
                String creationDate = status.getCreatedAt().toString();
                String userScreenName = status.getUser().getScreenName();
                String userName = status.getUser().getName();
                tweetList.add(new Tweet(text,imageUrl,userName,userScreenName,creationDate));
            }

            return new ResponseEntity<>(tweetMapper.getTweetDtoList(tweetList), HttpStatus.OK);

        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }




}
