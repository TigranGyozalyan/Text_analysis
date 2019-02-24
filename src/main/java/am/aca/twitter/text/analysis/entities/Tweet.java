package am.aca.twitter.text.analysis.entities;

public class Tweet {

    private String tweetText;
    private String avatarUrl;
    private String userName;
    private String userScreenName;
    private String createdAt;
    private long id;
    private String lang;

    public Tweet(long id, String lang, String tweetText, String avatarUrl, String userName, String userScreenName, String createdAt) {
        this.id = id;
        this.lang = lang;
        this.tweetText = tweetText;
        this.avatarUrl = avatarUrl;
        this.userName = userName;
        this.userScreenName = userScreenName;
        this.createdAt = createdAt;
    }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
