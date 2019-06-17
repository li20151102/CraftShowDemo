package com.tyj.craftshow.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author create by kyle_2019 on 2019/6/14 15:32
 * @package com.tyj.craftshow.activity
 * @fileName CountNumView
 */
public class CountNumView extends View {

    public CountNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        canvas.drawCircle(20,20,150,p);
    }
}
