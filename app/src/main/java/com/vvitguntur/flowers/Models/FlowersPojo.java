package com.vvitguntur.flowers.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
@Entity
public class FlowersPojo implements Serializable{


    @NonNull

    @PrimaryKey
    String flowerid;
    String largeImage,tags,pageurl,views,comments,previewurl,likes;

   public FlowersPojo(String largeImage, String tags, String pageurl, String views,  String comments, String previewurl, String likes,String flowerid) {
      this.largeImage = largeImage;
      this.tags = tags;
      this.pageurl = pageurl;
      this.views = views;
      this.comments = comments;
      this.previewurl = previewurl;
      this.likes = likes;
      this.flowerid = flowerid;
   }
    @NonNull
    public String getFlowerid() {
        return flowerid;
    }
    public String getLargeImage() {
        return largeImage;
    }

    public String getTags() {
        return tags;
    }

    public String getPageurl() {
        return pageurl;
    }

    public String getViews() {
        return views;
    }

    public String getComments() {
        return comments;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public String getLikes() {
        return likes;
    }
}
