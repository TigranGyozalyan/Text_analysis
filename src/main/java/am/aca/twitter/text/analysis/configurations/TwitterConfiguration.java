package am.aca.twitter.text.analysis.configurations;

import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterConfiguration {
    private final String CONSUMER_KEY = "xNXX43bchvxaFQns5zHjrImPI";
    private final String CONSUMER_SECRET = "1aiCLwxU6dfvzxU52Ca1BBNO2ZmxbzLvNDGRbe1UUFXmGe01cv";
    private final String ACCESS_TOKEN = "1092318687162482688-F6BzAChEZQqArM0zrNz3fQfLeae6wm";
    private final String ACCESS_SECRET = "CxdwzCtUyZRjtii2X0kXvSaLdj1PE7IAFxmq9PLPteHKB";

    private Configuration getConfigurationInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_SECRET);
        return cb.build();
    }

    public Twitter getTwitter() {
        return new TwitterFactory(getConfigurationInstance()).getInstance();
    }


}
