package com.art.code.psycare;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,HomeFragment.OnFragmentInteractionListener,CureFragment.OnFragmentInteractionListener,SettingFragment.OnFragmentInteractionListener{

    private CureFragment cureFragment;
    private HomeFragment homeFragment;
    private SettingFragment settingFragment;
    private RelativeLayout firstBottomLayout;
    private RelativeLayout secondBottomLayout;
    private RelativeLayout thirdBottomLayout;
    private ImageView firstBottomImage;
    private ImageView secondBottomImage;
    private ImageView thirdBottomImage;
    private TextView firstBottomText;
    private TextView secondBottomText;
    private TextView thirdBottomText;
    private int white = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;
    private FragmentManager fragmentManager;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();
        setChoiceItem(0);
    }
    private void initView(){

        firstBottomImage = (ImageView) findViewById(R.id.first_bottom_image);
        secondBottomImage = (ImageView) findViewById(R.id.second_bottom_image);
        thirdBottomImage = (ImageView) findViewById(R.id.fourth_bottom_image);

        firstBottomText = (TextView) findViewById(R.id.first_bottom_text);
        secondBottomText = (TextView) findViewById(R.id.second_bottom_text);
        thirdBottomText = (TextView) findViewById(R.id.fourth_bottom_text);

        firstBottomLayout = (RelativeLayout) findViewById(R.id.first_bottom_layout);
        secondBottomLayout = (RelativeLayout) findViewById(R.id.second_bottom_layout);
        thirdBottomLayout = (RelativeLayout) findViewById(R.id.fourth_bottom_layout);
        firstBottomLayout.setOnClickListener(MainActivity.this);
        secondBottomLayout.setOnClickListener(MainActivity.this);
        thirdBottomLayout.setOnClickListener(MainActivity.this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.first_bottom_layout:
                setChoiceItem(0);
                break;
            case R.id.second_bottom_layout:
                setChoiceItem(1);
                break;
            case R.id.fourth_bottom_layout:
                setChoiceItem(2);
                break;
            default:
                break;
        }
    }
    private void setChoiceItem(int index){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChoice();
        hideFragments(fragmentTransaction);
        switch(index){
            case 0:
                firstBottomText.setTextColor(dark);
                firstBottomLayout.setBackgroundColor(gray);
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.centerFrag_layout,homeFragment);
                }else{
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                secondBottomText.setTextColor(dark);
                secondBottomLayout.setBackgroundColor(gray);
                if(cureFragment == null){
                    cureFragment = new CureFragment();
                    fragmentTransaction.add(R.id.centerFrag_layout,cureFragment);
                }else{
                    fragmentTransaction.show(cureFragment);
                }
                break;
            case 2:
                thirdBottomText.setTextColor(dark);
                thirdBottomLayout.setBackgroundColor(gray);
                if(settingFragment == null){
                    settingFragment = new SettingFragment();
                    fragmentTransaction.add(R.id.centerFrag_layout,settingFragment);
                }else{
                    fragmentTransaction.show(settingFragment);
                }
                break;

        }
        fragmentTransaction.commit();
    }

    private void clearChoice(){
        firstBottomText.setTextColor(gray);
        firstBottomLayout.setBackgroundColor(white);
        secondBottomText.setTextColor(gray);
        secondBottomLayout.setBackgroundColor(white);
        thirdBottomText.setTextColor(gray);
        thirdBottomLayout.setBackgroundColor(white);
    }
    private void hideFragments(FragmentTransaction fragmentTransaction){
        if(cureFragment != null){
            fragmentTransaction.hide(cureFragment);
        }
        if(homeFragment != null){
            fragmentTransaction.hide(homeFragment);
        }
        if(settingFragment != null){
            fragmentTransaction.hide(settingFragment);
        }

    }
}
