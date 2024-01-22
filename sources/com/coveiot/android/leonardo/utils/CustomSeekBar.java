package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.coveiot.android.onr.ui.uiutils.ProgressItem;
import java.util.ArrayList;
/* loaded from: classes5.dex */
public class CustomSeekBar extends AppCompatSeekBar {
    public ArrayList<ProgressItem> i;

    public CustomSeekBar(Context context) {
        super(context);
        this.i = new ArrayList<>();
    }

    public void initData(ArrayList<ProgressItem> arrayList) {
        this.i = arrayList;
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        if (this.i.size() > 0) {
            int width = getWidth();
            int height = getHeight();
            int thumbOffset = getThumbOffset();
            int i = 0;
            int i2 = 0;
            while (i < this.i.size()) {
                ProgressItem progressItem = this.i.get(i);
                Paint paint = new Paint();
                paint.setColor(getResources().getColor(progressItem.getColor()));
                int progressItemPercentage = ((int) ((progressItem.getProgressItemPercentage() * width) / 100.0f)) + i2;
                if (i == this.i.size() - 1 && progressItemPercentage != width) {
                    progressItemPercentage = width;
                }
                Rect rect = new Rect();
                int i3 = thumbOffset / 2;
                rect.set(i2, i3, progressItemPercentage, height - i3);
                canvas.drawRect(rect, paint);
                i++;
                i2 = progressItemPercentage;
            }
            super.onDraw(canvas);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public CustomSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
