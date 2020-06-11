package jonnelafin.leevi.lemmyStructs;

import com.google.gson.Gson;

import java.util.Date;

public class LemmyPublication {
    public int ID;
    public String body;
    public int creator_id;
    public int community_id;
    public String published;
    public String updated;
    public boolean deleted;
    public boolean nsfw;
    public boolean stickied;
    public String creator_name;
    public String creator_avatar;
//    public String community_name;
//    public String community_nsfw;
    public int number_of_comments;
    public int score;
    public int upvotes;
    public int downvotes;
    public int hot_rank;



    public static LemmyPublication fromJson(String s) {
        return new Gson().fromJson(s, LemmyPublication.class);
    }
    public String toString() {
        return new Gson().toJson(this);
    }


}
