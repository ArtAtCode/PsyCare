package com.art.code.psycare;

public class Song {

    private String name;
    private String singer;
    private String url;
    private int pic;

    public Song(String name,String singer,String url, int pic){
        this.name = name;
        this.singer = singer;
        this.url = url;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
