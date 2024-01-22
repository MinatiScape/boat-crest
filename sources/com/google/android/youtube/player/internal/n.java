package com.google.android.youtube.player.internal;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
/* loaded from: classes10.dex */
public final class n extends FrameLayout {
    public final ProgressBar h;
    public final TextView i;

    public n(Context context) {
        super(context, null, z.c(context));
        m mVar = new m(context);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        ProgressBar progressBar = new ProgressBar(context);
        this.h = progressBar;
        progressBar.setVisibility(8);
        addView(progressBar, new FrameLayout.LayoutParams(-2, -2, 17));
        int i = (int) ((context.getResources().getDisplayMetrics().density * 10.0f) + 0.5f);
        TextView textView = new TextView(context);
        this.i = textView;
        textView.setTextAppearance(context, 16973894);
        textView.setTextColor(-1);
        textView.setVisibility(8);
        textView.setPadding(i, i, i, i);
        textView.setGravity(17);
        textView.setText(mVar.f10501a);
        addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
    }

    public final void a() {
        this.h.setVisibility(8);
        this.i.setVisibility(8);
    }

    public final void b() {
        this.h.setVisibility(0);
        this.i.setVisibility(8);
    }

    public final void c() {
        this.h.setVisibility(8);
        this.i.setVisibility(0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        float f;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (mode == 1073741824 || (mode == Integer.MIN_VALUE && mode2 == 0)) {
                size2 = (int) (size / 1.777f);
            } else {
                if (mode2 == 1073741824 || (mode2 == Integer.MIN_VALUE && mode == 0)) {
                    f = size2;
                } else if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
                    f = size2;
                    float f2 = size / 1.777f;
                    if (f >= f2) {
                        size2 = (int) f2;
                    }
                } else {
                    size = 0;
                    size2 = 0;
                }
                size = (int) (f * 1.777f);
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(FrameLayout.resolveSize(size, i), 1073741824), View.MeasureSpec.makeMeasureSpec(FrameLayout.resolveSize(size2, i2), 1073741824));
    }
}
