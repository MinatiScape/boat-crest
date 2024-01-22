package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.List;
/* loaded from: classes9.dex */
public class BarData extends BarLineScatterCandleBubbleData<IBarDataSet> {

    /* renamed from: a  reason: collision with root package name */
    public float f7939a;

    public BarData() {
        this.f7939a = 0.85f;
    }

    public float getBarWidth() {
        return this.f7939a;
    }

    public float getGroupWidth(float f, float f2) {
        return (this.mDataSets.size() * (this.f7939a + f2)) + f;
    }

    public void groupBars(float f, float f2, float f3) {
        BarEntry barEntry;
        if (this.mDataSets.size() > 1) {
            int entryCount = ((IBarDataSet) getMaxEntryCountSet()).getEntryCount();
            float f4 = f2 / 2.0f;
            float f5 = f3 / 2.0f;
            float f6 = this.f7939a / 2.0f;
            float groupWidth = getGroupWidth(f2, f3);
            for (int i = 0; i < entryCount; i++) {
                float f7 = f + f4;
                for (T t : this.mDataSets) {
                    float f8 = f7 + f5 + f6;
                    if (i < t.getEntryCount() && (barEntry = (BarEntry) t.getEntryForIndex(i)) != null) {
                        barEntry.setX(f8);
                    }
                    f7 = f8 + f6 + f5;
                }
                float f9 = f7 + f4;
                float f10 = groupWidth - (f9 - f);
                if (f10 > 0.0f || f10 < 0.0f) {
                    f9 += f10;
                }
                f = f9;
            }
            notifyDataChanged();
            return;
        }
        throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
    }

    public void setBarWidth(float f) {
        this.f7939a = f;
    }

    public BarData(IBarDataSet... iBarDataSetArr) {
        super(iBarDataSetArr);
        this.f7939a = 0.85f;
    }

    public BarData(List<IBarDataSet> list) {
        super(list);
        this.f7939a = 0.85f;
    }
}
