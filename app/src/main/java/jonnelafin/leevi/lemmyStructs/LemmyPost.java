package jonnelafin.leevi.lemmyStructs;

import com.google.gson.Gson;

public class LemmyPost extends LemmyPublication {


    public String community_name;
    public String community_nsfw;

    public static LemmyPost fromJson(String s) {
        return new Gson().fromJson(s, LemmyPost.class);
    }
}
