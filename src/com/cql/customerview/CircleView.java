package com.cql.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    
    private Paint circlePaint = new Paint();
    
    private Paint linePaint = new Paint();
    
    private Paint textPaint = new Paint();
    
    private Paint pointPaint = new Paint();
    
    private Paint hLinePaint = new Paint();
    
    private int width;
    
    private int height;

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context) {
        super(context);
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
        circlePaint.setColor(Color.GRAY);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setAntiAlias(true);
        
        linePaint = circlePaint;
        
        hLinePaint.setColor(Color.GRAY);
        hLinePaint.setStyle(Paint.Style.STROKE);
        hLinePaint.setStrokeWidth(7);
        hLinePaint.setAntiAlias(true);
        
        textPaint.setColor(Color.GRAY);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(4);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(40);
        
        pointPaint.setColor(Color.GRAY);
        pointPaint.setStyle(Paint.Style.FILL);
        //设置抗锯齿
        pointPaint.setAntiAlias(true);
        //设置防抖动
        pointPaint.setDither(true);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        
        canvas.drawCircle(width/2, height/2, width/2, circlePaint);
        canvas.drawCircle(width/2, height/2, 10, pointPaint);
        drawTimeLine(canvas);
    }
    
    private void drawTimeLine(Canvas canvas){
        canvas.save();
        int lineLength = 0;
        
        for(int i = 0;i < 24; i+=1){
            if(i == 0 || i == 6 || i == 12 || i == 18){
                lineLength = 60;
            }else{
                lineLength = 30;
            }
            canvas.drawLine(width/2, (height-width)/2, width/2, (height-width)/2+lineLength, linePaint);
            canvas.drawText(String.valueOf(i), width/2-textPaint.measureText(String.valueOf(i))/2, (height-width)/2+lineLength+50, textPaint);
            
            if(i == 5){
                canvas.drawLine(width/2, height/2-150, width/2, height/2, hLinePaint);
            }else if(i == 20){
                canvas.drawLine(width/2, height/2-300, width/2, height/2, linePaint);
            }
            
            canvas.rotate(15, width/2, height/2);
        }
        canvas.restore();
    }

}
