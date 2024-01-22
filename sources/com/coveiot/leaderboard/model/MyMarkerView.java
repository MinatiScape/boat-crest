package com.coveiot.leaderboard.model;

import android.content.Context;
import android.widget.TextView;
import com.coveiot.leaderboard.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes9.dex */
public class MyMarkerView extends MarkerView {
    private TextView tvContent;

    public MyMarkerView(Context context, int i) {
        super(context, i);
        this.tvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), (-getHeight()) / 2);
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void refreshContent(Entry entry, Highlight highlight) {
        if (entry instanceof CandleEntry) {
            TextView textView = this.tvContent;
            textView.setText("" + Utils.formatNumber(((CandleEntry) entry).getHigh(), 0, true));
        } else {
            TextView textView2 = this.tvContent;
            textView2.setText("" + Utils.formatNumber(entry.getY(), 0, true));
        }
        super.refreshContent(entry, highlight);
    }
}
