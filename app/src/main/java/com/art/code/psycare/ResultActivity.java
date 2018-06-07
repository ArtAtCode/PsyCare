package com.art.code.psycare;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.art.code.psycare.Compent.ScoreView;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    ScoreView scoreView ;

    private Button backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    protected void initViews(){
        scoreView = findViewById(R.id.scoreview);
        resultText = findViewById(R.id.result_text);
        backToHome = findViewById(R.id.backtohome_evaluationresult);

        resultText.setText(Html.fromHtml("<font color=#47A9D5>非常愉悦(*σ´∀`)σ</font>"));

        scoreView.setMaxValue(91);
    }
}
