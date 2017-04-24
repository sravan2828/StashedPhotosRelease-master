package com.stashcity.www.stashedphotos.model;

/**
 * Created by sravan on 16/10/16.
 */

public class Image {
    public int getAlbumId() {
        return AlbumId;
    }

    public void setAlbumId(int albumId) {
        AlbumId = albumId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "AlbumId=" + AlbumId +
                ", Likes=" + Likes +
                ", Views=" + Views +
                ", photograpId=" + photograpId +
                ", photographerId=" + photographerId +
                ", name='" + name + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    public int getPhotograpId() {
        return photograpId;
    }

    public void setPhotograpId(int photograpId) {
        this.photograpId = photograpId;
    }

    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    int AlbumId,Likes,Views,photograpId,photographerId;
    String name,thumbnailUrl,url;
}
