package com.cql.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class SkyView extends TextView {
    
    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();
    private Paint paint3 = new Paint();
    
    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int translate;

    public SkyView(Context context) {
        super(context);
    }

    public SkyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public SkyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    public SkyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(measureWidthHeight(widthMeasureSpec), measureWidthHeight(heightMeasureSpec));
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
        paint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        paint1.setStyle(Paint.Style.FILL);
        
        paint2.setColor(Color.YELLOW);
        paint2.setStyle(Paint.Style.FILL);
        
        paint3.setColor(Color.BLACK);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(5);
        paint3.setAntiAlias(true);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        if(mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, 
                        new int[]{Color.BLUE,0xffffff,Color.BLUE}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        drawMethod1(canvas);
        super.onDraw(canvas);
        canvas.restore();
        
//        drawMethod2(canvas);
    }
    
    private void drawMethod1(Canvas canvas){
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint1);
        canvas.drawRect(20, 20, getMeasuredWidth()-20, getMeasuredHeight()-20, paint2);
        canvas.save();
        canvas.translate(20, 0);
        
        //此处画的线 会向右偏移20个像素，因为canvas平移之后没有调 restore() 方法进行画布还原
        canvas.drawLine(0, 0, 20, 300, paint3);
    }
    
    private void drawMethod2(Canvas canvas){
        if(mGradientMatrix != null){
            translate += mViewWidth/5;
            if(translate > 2 * mViewWidth){
                translate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(translate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(200);
            
        }
    }
    
    private int measureWidthHeight(int measureSpec){
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = 200;   //单位是px
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(result, size);
            }
        }
        return result;
    }
    
}
