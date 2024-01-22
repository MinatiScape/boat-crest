package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
/* loaded from: classes3.dex */
public class CustomMarkerView extends MarkerView {
    public TextView h;
    public CandleEntry i;

    public CustomMarkerView(Context context, int i) {
        super(context, i);
        TextView textView = (TextView) findViewById(R.id.tvContent);
        this.h = textView;
        textView.setBackgroundColor(context.getColor(R.color.color_979797));
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void draw(Canvas canvas, float f, float f2) {
        super.draw(canvas, f, f2);
        getOffsetForDrawingAtPoint(f, f2);
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -(this.i.getClose() + 25.0f + this.i.getOpen()));
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    @SuppressLint({"SetTextI18n"})
    public void refreshContent(Entry entry, Highlight highlight) {
        if (entry instanceof CandleEntry) {
            CandleEntry candleEntry = (CandleEntry) entry;
            this.i = candleEntry;
            if (candleEntry.getHigh() != 0.0f) {
                this.h.setVisibility(0);
                TextView textView = this.h;
                textView.setText("Min: " + ((int) this.i.getOpen()) + " | Max:" + ((int) this.i.getClose()) + "");
                return;
            }
            this.h.setVisibility(8);
            this.h.setText(getResources().getString(R.string.no_data));
        }
    }
}
