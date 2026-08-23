package com.example.logowith;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class HashtagLogoView extends View {

    private final Paint lightBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint darkBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final float strokeWidth = 85f;
    private final float tiltDegrees = 6f;

    public HashtagLogoView(Context context) {
        super(context);
        init();
    }

    public HashtagLogoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HashtagLogoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        lightBlue.setColor(0xFF2B7FFF);
        lightBlue.setStrokeWidth(strokeWidth);
        lightBlue.setStrokeCap(Paint.Cap.ROUND);
        lightBlue.setStyle(Paint.Style.STROKE);

        darkBlue.setColor(0xFF0842A0);
        darkBlue.setStrokeWidth(strokeWidth);
        darkBlue.setStrokeCap(Paint.Cap.ROUND);
        darkBlue.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float lenV = 310f;
        float lenH = 340f;

        canvas.save();
        canvas.rotate(tiltDegrees, cx, cy);

        // Left vertical bar — Light Blue
        Path leftV = new Path();
        leftV.moveTo(cx - 115f, cy - lenV/2);
        leftV.lineTo(cx - 115f, cy + lenV/2);
        canvas.drawPath(leftV, lightBlue);

        // Right vertical bar — Dark Blue
        Path rightV = new Path();
        rightV.moveTo(cx + 115f, cy - lenV/2);
        rightV.lineTo(cx + 115f, cy + lenV/2 + 35f);
        canvas.drawPath(rightV, darkBlue);

        // Top horizontal bar — split color
        Path topH = new Path();
        topH.moveTo(cx - lenH/2, cy - 75f);
        topH.lineTo(cx + lenH/2 + 45f, cy - 55f);

        canvas.save();
        canvas.clipRect(new RectF(0, 0, cx, getHeight()));
        canvas.drawPath(topH, lightBlue);
        canvas.restore();

        canvas.save();
        canvas.clipRect(new RectF(cx, 0, getWidth(), getHeight()));
        canvas.drawPath(topH, darkBlue);
        canvas.restore();

        // Bottom horizontal bar — split color
        Path botH = new Path();
        botH.moveTo(cx - lenH/2 - 25f, cy + 95f);
        botH.lineTo(cx + lenH/2, cy + 75f);

        canvas.save();
        canvas.clipRect(new RectF(0, 0, cx, getHeight()));
        canvas.drawPath(botH, lightBlue);
        canvas.restore();

        canvas.save();
        canvas.clipRect(new RectF(cx, 0, getWidth(), getHeight()));
        canvas.drawPath(botH, darkBlue);
        canvas.restore();

        canvas.restore();
    }

    @Override
    protected void onMeasure(int wSpec, int hSpec) {
        int sz = Math.min(View.MeasureSpec.getSize(wSpec), View.MeasureSpec.getSize(hSpec));
        setMeasuredDimension(sz, sz);
    }
}