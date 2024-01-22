package com.coveiot.android.leonardo.utils;

import android.graphics.DashPathEffect;
import androidx.core.internal.view.SupportMenu;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class EcgGraphUtil {
    @NotNull
    public static final EcgGraphUtil INSTANCE = new EcgGraphUtil();

    public static /* synthetic */ void populateLineChart$default(EcgGraphUtil ecgGraphUtil, LineChart lineChart, List list, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        ecgGraphUtil.populateLineChart(lineChart, list, i);
    }

    public final void populateLineChart(@NotNull LineChart chart, @NotNull List<? extends Entry> entries, int i) {
        Intrinsics.checkNotNullParameter(chart, "chart");
        Intrinsics.checkNotNullParameter(entries, "entries");
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.disableDashedLine();
        lineDataSet.setColor(SupportMenu.CATEGORY_MASK);
        lineDataSet.setCircleColor(0);
        lineDataSet.setLineWidth(1.1f);
        lineDataSet.setCircleRadius(1.0f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(0.0f);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setFormLineWidth(1.0f);
        lineDataSet.setFillColor(0);
        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
        lineDataSet.setFormSize(15.0f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineDataSet);
        LineData lineData = new LineData(arrayList);
        chart.setData(lineData);
        chart.invalidate();
        chart.notifyDataSetChanged();
        chart.setVisibleXRangeMaximum(2000.0f);
        chart.getXAxis().setEnabled(false);
        chart.getDescription().setText("");
        chart.getLegend().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        if (i == 0) {
            chart.moveViewToX(lineData.getEntryCount() + 0.0f);
        } else {
            chart.moveViewToX(lineData.getEntryCount() * i);
        }
    }
}
