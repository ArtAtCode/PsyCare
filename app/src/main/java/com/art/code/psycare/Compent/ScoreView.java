package com.art.code.psycare.Compent;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.art.code.psycare.R;

public class ScoreView extends View {

    private final int DEFAULT_PADDING = dp2Px(5);

    private final int DEFAULT_WIDTH = dp2Px(200);   //默认宽度为200dp

    private final int DEFAULT_HEIGHT = dp2Px(200);  //默认高度为200dp

    private int viewWidth;  //View宽度

    private int viewHeight;   //View高度

    private int halfView;  //view宽度或高度的一半

    private int radius;   //绘制圆形区域的半径

    private Paint bgPaint;

    private Paint arrowPaint;  //指示块画笔

    private Paint arcPaint;  //圆弧画笔

    private Paint arcReachPaint;  //圆弧画笔

    private Paint reachProgressPaint;  //已达刻度

    private Paint scoreTextPaint;   //绘制分数文本

    private Paint descTextPaint;   //绘制描述文本

    private float degree = 3f;  //相邻刻度之间夹角大小为3度,角度制，不是弧度制

    private float lineLength;

    private int lineColor;

    private int curValue;   //动画进行的当前值

    private ValueAnimator valueAnimator;

    private boolean isAnimEnd = false;

    public ScoreView(Context context) {
        this(context, null);
    }

    public ScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainAttrs(context, attrs);
        init();
    }

    private void obtainAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScoreView);
        lineLength = typedArray.getDimension(R.styleable.ScoreView_lineLength, dp2Px(10));
        lineColor = typedArray.getColor(R.styleable.ScoreView_lineColor, R.color.deepskyblue);
        typedArray.recycle();
    }

    private void init() {
        arrowPaint = createPaint(Color.WHITE, 0, Paint.Style.FILL, 0);
        arcPaint = createPaint(lineColor, dp2Px(1), Paint.Style.STROKE, 0);
        bgPaint = createPaint(lineColor, dp2Px(1), Paint.Style.FILL, 0);
        reachProgressPaint = createPaint(R.color.deepskyblue,dp2Px(1), Paint.Style.FILL, 0);//lmj
        arcReachPaint = createPaint(R.color.darkcyan, dp2Px(1), Paint.Style.STROKE, 0);//lmj
        scoreTextPaint = createPaint(R.color.deepskyblue, 0, Paint.Style.STROKE, dp2Px(26));//lmj
        descTextPaint = createPaint(R.color.deepskyblue, 0, Paint.Style.STROKE, dp2Px(16));//lmj
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureSize(widthMeasureSpec, DEFAULT_WIDTH), measureSize(heightMeasureSpec, DEFAULT_HEIGHT));
    }

    private int measureSize(int measureSpec, int defaultSize) {
        int measureSize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            measureSize = size;
        } else {
            if (mode == MeasureSpec.AT_MOST) {
                measureSize = Math.min(defaultSize, size);
            }
        }
        return measureSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        halfView = Math.min(viewWidth, viewHeight) / 2;   //宽度或高度中最小值的一半，即圆形的位置
        radius = (Math.min(viewWidth, viewHeight) - 2 * DEFAULT_PADDING) / 2;  //半径是宽高除去默认内边距的
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArcBackground(canvas);
        drawArcProgress(canvas);
        drawScoreText(canvas);
        if (isAnimEnd) {
            drawDescText(canvas);
        }
    }

    private void drawScoreText(Canvas canvas) {
        canvas.save();
        canvas.translate(halfView, halfView);
        String scoreText = curValue + "分";
        float textLength = scoreTextPaint.measureText(scoreText);
        canvas.drawText(scoreText, -textLength / 2, 0, scoreTextPaint);
        canvas.restore();
    }

    /**
     * 绘制文本
     *
     * @param canvas
     */
    private void drawDescText(Canvas canvas) {
        canvas.save();
        canvas.translate(halfView, halfView);
        String desc = "";
        if (curValue >= 90) {
            desc = "愉悦";
        } else {
            desc = "一般";
        }
        float descLength = descTextPaint.measureText(desc);
        canvas.drawText(desc, -descLength / 2, dp2Px(30), descTextPaint);
        canvas.restore();
        isAnimEnd = false; // isAnimEnd置为false,防止再次点击start时，就显示动画结束时才能显示的内容
    }

    /**
     * 绘制进度
     *
     * @param canvas
     */
    private void drawArcProgress(Canvas canvas) {
        canvas.save();
        canvas.translate(halfView, halfView);
        //绘制圆弧
        RectF rectF = new RectF(dp2Px(5) - radius, dp2Px(5) - radius, radius - dp2Px(5), radius - dp2Px(5));
        canvas.drawArc(rectF, 120, curValue * degree, false, arcReachPaint);

        //绘制指示方块，方块是从0开始移动某一个位置的
        canvas.rotate(30 + degree * curValue);
        Path path = new Path();
        path.moveTo(dp2Px(5), radius);
        path.lineTo(dp2Px(5), radius - dp2Px(10));
        path.lineTo(0, radius - dp2Px(15));
        path.lineTo(-dp2Px(5), radius - dp2Px(10));
        path.lineTo(-dp2Px(5), radius);
        path.close();
        canvas.drawPath(path, arrowPaint);

        //绘制已经达到的刻度
        canvas.restore();
        canvas.save();
        canvas.translate(halfView, halfView);
        canvas.rotate(30);
        for (int i = 0; i < curValue; i++) {
            canvas.drawLine(0, radius - dp2Px(15), 0,
                    radius - dp2Px(15) - lineLength,
                    reachProgressPaint);
            canvas.rotate(degree);
        }
        canvas.restore();
    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    private void drawArcBackground(Canvas canvas) {
        canvas.save();
        canvas.translate(halfView, halfView);
        //绘制圆弧
        RectF rectF = new RectF(dp2Px(5) - radius, dp2Px(5) - radius, radius - dp2Px(5), radius - dp2Px(5));
        canvas.drawArc(rectF, 120, 300, false, arcPaint);
        //绘制刻度线
        canvas.rotate(30);
        for (int i = 0; i < 100; i++) {
            canvas.drawLine(0, radius - dp2Px(15), 0,
                    radius - dp2Px(15) - lineLength,
                    bgPaint);
            canvas.rotate(degree);
        }
        canvas.restore();
    }

    /**
     * 创建画笔
     *
     * @param color
     * @param strokeWidth
     * @param style
     * @param textSize
     * @return
     */
    private Paint createPaint(int color, int strokeWidth, Paint.Style style, float textSize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextSize(textSize);
        return paint;
    }

    /**
     * dp转换成px
     *
     * @param dpValue
     * @return
     */
    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, getResources().getDisplayMetrics());
    }


    /**
     * 对外提供的接口，用于设置进度的最大值
     *
     * @param value
     */
    public void setMaxValue(int value) {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, value);
        }
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(30 * value);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curValue = (int) animation.getAnimatedValue();
                Log.i("debug", "curValue=" + curValue);
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimEnd = true;  //标记动画结束
                Log.i("debug", "onAnimationEnd");
                Log.i("debug", "onAnimationEnd curValue = " + curValue);
                invalidate();
            }
        });
        valueAnimator.start();
    }
}