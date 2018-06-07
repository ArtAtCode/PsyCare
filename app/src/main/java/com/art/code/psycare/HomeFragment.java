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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private ArrayList<String> list_path_imgs = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();
    private FloatingActionButton startTest;
    private List<Integer> scores;
    private List<String> xVals;
    private ArrayList<Entry> vals;
    Banner banner;
    private OnFragmentInteractionListener mListener;
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
        scores = new ArrayList<>();
        xVals = new ArrayList<>();
        vals = new ArrayList<>();
        initItems();
        drawLineChart(view);
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

    private void initItems() {
        scores.add(96);
        xVals.add("6-1 9:03");
        scores.add(87);
        xVals.add("6-1 14:26");
        scores.add(93);
        xVals.add("6-1 20:17");
        scores.add(85);
        xVals.add("6-2 10:21");
        scores.add(89);
        xVals.add("6-2 16:11");
        scores.add(92);
        xVals.add("6-2 19:07");

        for(int i = 0; i < scores.size(); ++i) {
            vals.add(new Entry(i, scores.get(i)));
        }
    }

    private void drawLineChart(View view) {
        LineChart lineChart = view.findViewById(R.id.linechart_home);
        LineDataSet a = new LineDataSet(vals,"分数");
        a.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineData lineData = new LineData(a);
        lineData.setDrawValues(true);
        lineChart.setData(lineData);
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xVals.get((int)value);
            }
        };
        XAxis xl = lineChart.getXAxis();
        xl.setValueFormatter(formatter);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        xl.setGranularity(1.0f);
        lineChart.getAxisRight().setEnabled(false);//隐藏y轴右边边线
        lineChart.setHighlightPerTapEnabled(false);//取消高亮
        lineChart.setHighlightPerDragEnabled(false);//取消高亮
        lineChart.setScaleXEnabled(false);
        lineChart.setVisibleXRangeMaximum(6);
        lineChart.setVisibleXRangeMinimum(6);
        lineChart.animateY(1000);
        lineChart.getDescription().setText("评估时间");

        lineChart.invalidate();
    }
}
