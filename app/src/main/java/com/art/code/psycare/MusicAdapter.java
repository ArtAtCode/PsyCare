package com.art.code.psycare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private List<Song> mSongList;

    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View musicView;
        TextView musicInfo;
        ImageView songPic;

        public ViewHolder(View view){
            super(view);
            musicView = view;
            musicInfo = view.findViewById(R.id.music_info);
            songPic = view.findViewById(R.id.song_picture);
        }
    }

    public MusicAdapter(List<Song> songList, Activity activity){
        mSongList = songList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Song song = mSongList.get(position);

                Intent intent = new Intent(activity, PlayMusicActivity.class);
                intent.putExtra("URL",song.getUrl());
                activity.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Song song = mSongList.get(position);
        holder.musicInfo.setText(song.getName() + " - " + song.getSinger());
        holder.songPic.setImageResource(song.getPic());
    }

    @Override
    public int getItemCount(){
        return mSongList.size();
    }
}
