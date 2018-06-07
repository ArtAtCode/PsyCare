package com.art.code.psycare;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.art.code.psycare.Utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ArrayList<String> list_path_imgs = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();
    private TextView startTestButton ;
    private FloatingActionButton startTest;
    Banner banner;
    private OnFragmentInteractionListener mListener;
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        startTestButton =view.findViewById(R.id.startTest_text);
        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ReadSentenceActivity.class);
                startActivity(intent);
            }
        });
        startTest = view.findViewById(R.id.startTest_home);
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ReadSentenceActivity.class);
                startActivity(intent);
            }
        });
        initBanner(view);
        banner.start();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //设置banner的图片文字来源
    public void initBanner(View view){
        banner = (Banner)view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        list_path_imgs.add("http://a.bbkz.net/forum/gallery/images/309498/large/1_fotto1_1189.JPG");
        list_path_imgs.add("http://www.qqkj.cn/files/beyondpic/2012-6/8/126811182513706.jpg");
        list_path_imgs.add("http://www.qqkj.cn/files/beyondpic/2012-6/8/126811182538135.jpg");
        list_path_imgs.add("http://images.ccoo.cn/ablum/20111222/20111222223457581.jpg");
        list_title.add("心情不好？戳进来");
        list_title.add("90后情绪多变？");
        list_title.add("你不知道的危险疾病");
        list_title.add("你与开心只差一步之遥");
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        banner.setImages(list_path_imgs);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //在此设置点击banner的响应事件
            }
        });
        banner.setBannerTitles(list_title);

    }
}
