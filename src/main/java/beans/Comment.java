package beans;

public class Comment {
    private int id;
    private String content;
    private String login;
    private int postId;


    public Comment(String content, String login, int postId) {
        this.id = 0;
        this.content = content;
        this.login = login;
        this.postId = postId;
    }

    public Comment(int id, String content, String login, int postId) {
        this.id = id;
        this.content = content;
        this.login = login;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }


    public String getContent() {
        return content;
    }


    public String getLogin() {
        return login;
    }


    public int getPostId() {
        return postId;
    }

}
