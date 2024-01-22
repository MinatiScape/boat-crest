package com.coveiot.android.respiratoryrate.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;
import com.coveiot.android.respiratoryrate.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes6.dex */
public class CustomMarkerViewRespiratoryRate extends MarkerView {
    public TextView h;
    public Context i;

    public CustomMarkerViewRespiratoryRate(Context context, int i) {
        super(context, i);
        this.i = context;
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
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    @SuppressLint({"SetTextI18n"})
    public void refreshContent(Entry entry, Highlight highlight) {
        if (entry instanceof Entry) {
            if (entry.getY() > 1.0f) {
                this.h.setVisibility(0);
                float y = entry.getY();
                TextView textView = this.h;
                textView.setText(String.valueOf((int) y) + HexStringBuilder.DEFAULT_SEPARATOR + this.i.getString(R.string.brpm));
                return;
            }
            this.h.setVisibility(8);
            this.h.setText("No data");
        }
    }
}
