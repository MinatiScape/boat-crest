package com.coveiot.android.theme.compundview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.theme.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CustomMarkerViewPieChart extends MarkerView {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final TextView h;
    @NotNull
    public final TextView i;

    public CustomMarkerViewPieChart(@Nullable Context context, int i) {
        super(context, i);
        View findViewById = findViewById(R.id.tvName);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        this.h = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.tvValue);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.i = (TextView) findViewById2;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void draw(@NotNull Canvas canvas, float f, float f2) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.draw(canvas, f, f2);
        getOffsetForDrawingAtPoint(f, f2);
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    @NotNull
    public MPPointF getOffset() {
        return new MPPointF(-getWidth(), -getHeight());
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    @SuppressLint({"SetTextI18n"})
    public void refreshContent(@NotNull Entry e, @NotNull Highlight highlight) {
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(highlight, "highlight");
        if (e instanceof PieEntry) {
            PieEntry pieEntry = (PieEntry) e;
            this.h.setText(pieEntry.getLabel());
            if (pieEntry.getData() != null) {
                TextView textView = this.i;
                textView.setText(pieEntry.getData() + HexStringBuilder.COMMENT_BEGIN_CHAR + ((int) pieEntry.getValue()) + "%)");
                return;
            }
            this.i.setText(String.valueOf((int) pieEntry.getValue()));
        }
    }
}
