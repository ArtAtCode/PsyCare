package com.art.code.psycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    private List<Song> songList;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        back = findViewById(R.id.back_song);

        songList= new ArrayList<>();
        initSongs();
        MusicAdapter adapter = new MusicAdapter(songList, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_music);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initSongs(){
        Song song0 = new Song("Fight Song","Rachel Platten","http://music.163.com/song/29803675", R.mipmap.fight_song);
        songList.add(song0);
        Song song1 = new Song("她说","林俊杰","http://music.163.com/song/108242", R.mipmap.she_says);
        songList.add(song1);
        Song song2 = new Song("烟火里的尘埃","华晨宇","http://music.163.com/song/29004400", R.mipmap.dirt);
        songList.add(song2);
        Song song3 = new Song("有何不可","许嵩","http://music.163.com/song/167876", R.mipmap.xusong);
        songList.add(song3);
        Song song4 = new Song("红豆","王菲","http://music.163.com/song/299757", R.mipmap.hongdou);
        songList.add(song4);
        Song song5 = new Song("听","张杰","http://music.163.com/song/29357693", R.mipmap.ting);
        songList.add(song5);
        Song song6 = new Song("丑八怪","薛之谦","http://music.163.com/song/27808044", R.mipmap.choubaguai);
        songList.add(song6);
        Song song7 = new Song("无字碑","张靓颖","http://music.163.com/song/29802253", R.mipmap.wuzibei);
        songList.add(song7);
        Song song8 = new Song("如果有来生","谭维维","http://music.163.com/song/293948", R.mipmap.ruguoyoulaisheng);
        songList.add(song8);
        Song song9 = new Song("成都","赵雷","http://music.163.com/song/436514312", R.mipmap.chengdu);
        songList.add(song9);
    }
}
