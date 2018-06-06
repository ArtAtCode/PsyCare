package com.art.code.psycare;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.art.code.psycare.Compent.ScoreView;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    ScoreView scoreView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();

    }
    protected void initViews(){
        scoreView = findViewById(R.id.scoreview);
        resultText = findViewById(R.id.result_text);

        resultText.setText(Html.fromHtml("<font color=red>非常愉悦(*σ´∀`)σ</font>"));

        scoreView.setMaxValue(89);
    }
}
