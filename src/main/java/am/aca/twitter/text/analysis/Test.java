package am.aca.twitter.text.analysis;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class Test {

//
//    ooauth.consumerKey=xNXX43bchvxaFQns5zHjrImPI
//    oauth.consumerSecret=1aiCLwxU6dfvzxU52Ca1BBNO2ZmxbzLvNDGRbe1UUFXmGe01cv
//    oauth.accessToken=1092318687162482688-F6BzAChEZQqArM0zrNz3fQfLeae6wm
//    oauth.accessTokenSecret=CxdwzCtUyZRjtii2X0kXvSaLdj1PE7IAFxmq9PLPteHKB

    public static void main(String[] args) throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("xNXX43bchvxaFQns5zHjrImPI")
                .setOAuthConsumerSecret("1aiCLwxU6dfvzxU52Ca1BBNO2ZmxbzLvNDGRbe1UUFXmGe01cv")
                .setOAuthAccessToken("1092318687162482688-F6BzAChEZQqArM0zrNz3fQfLeae6wm")
                .setOAuthAccessTokenSecret("CxdwzCtUyZRjtii2X0kXvSaLdj1PE7IAFxmq9PLPteHKB");
        cb.setJSONStoreEnabled(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query("test");
        QueryResult result = twitter.search(query);
        List<String> tweetTexts = new ArrayList<String>();
        List<Status> tweets = result.getTweets();
        for (int i = 0; i < 10; i++) {
            Status status = tweets.get(i);


            System.out.println("============================");
            tweetTexts.add(status.getText());
            System.out.println(status.getText());
            System.out.println("============================");
            String json = DataObjectFactory.getRawJSON(tweets.get(i));
            System.out.println(json);

        }
    }
}
