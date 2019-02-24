package am.aca.twitter.text.analysis.mappers;

import am.aca.twitter.text.analysis.dtos.TweetDto;
import am.aca.twitter.text.analysis.entities.Tweet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TweetMapper {

    public TweetDto getTweetDto(Tweet tweet) {
        TweetDto tweetDto = new TweetDto();
        tweetDto.setId(tweet.getId());
        tweetDto.setAvatarUrl(tweet.getAvatarUrl());
        tweetDto.setCreatedAt(tweet.getCreatedAt());
        tweetDto.setTweetText(tweet.getTweetText());
        tweetDto.setUserName(tweet.getUserName());
        tweetDto.setUserScreenName(tweet.getUserScreenName());
        tweetDto.setLang(tweet.getLang());
        return tweetDto;
    }

    public List<TweetDto> getTweetDtoList(List<Tweet> tweets) {

        return tweets.stream()
                .map(this::getTweetDto)
                .collect(Collectors.toList());

    }
}
