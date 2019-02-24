package am.aca.twitter.text.analysis.controllers;

import am.aca.twitter.text.analysis.configurations.TextAnalysisConfiguration;
import am.aca.twitter.text.analysis.configurations.TwitterConfiguration;
import am.aca.twitter.text.analysis.dtos.ResponseDto;
import am.aca.twitter.text.analysis.dtos.TweetDto;
import am.aca.twitter.text.analysis.entities.Tweet;
import am.aca.twitter.text.analysis.entities.documents.Document;
import am.aca.twitter.text.analysis.entities.documents.Documents;
import am.aca.twitter.text.analysis.mappers.TweetMapper;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class SearchController {


    private final TwitterConfiguration twitterConfiguration;
    private final TweetMapper tweetMapper;
    private final TextAnalysisConfiguration textAnalysisConfiguration;

    public SearchController(TwitterConfiguration twitterConfiguration, TweetMapper tweetMapper, TextAnalysisConfiguration textAnalysisConfiguration) {
        this.twitterConfiguration = twitterConfiguration;
        this.tweetMapper = tweetMapper;
        this.textAnalysisConfiguration = textAnalysisConfiguration;
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
            for (Status status : tweets) {
                String imageUrl = status.getUser().getProfileImageURL();
                String text = status.getText();
                String creationDate = status.getCreatedAt().toString();
                String userScreenName = status.getUser().getScreenName();
                String userName = status.getUser().getName();
                String lang = status.getLang();
                long id = status.getId();
                if (!status.isRetweeted())
                    tweetList.add(new Tweet(id, lang, text, imageUrl, userName, userScreenName, creationDate));
            }
            return ResponseEntity.ok(tweetMapper.getTweetDtoList(tweetList));
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/sentiment",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResponseDto> getSentiment(@RequestBody List<TweetDto> tweetDtos) {

        Documents documents = new Documents();
        for (TweetDto tweetDto : tweetDtos) {
            String id = String.valueOf(tweetDto.getId());
            String lang = tweetDto.getLang();
            String text = tweetDto.getTweetText();
            Document document = new Document(id, lang, text);
            documents.add(document);
        }

        HttpsURLConnection connection = textAnalysisConfiguration.getConnection();
        Gson g = new Gson();
        String text = g.toJson(documents);
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(connection.getInputStream()))) {
            byte[] encoded_text = text.getBytes("UTF-8");
            wr.write(encoded_text, 0, encoded_text.length);
            wr.flush();
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            String result = response.toString();
            ResponseDto responseDto = g.fromJson(result, ResponseDto.class);
            return ResponseEntity.ok(responseDto);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
