package com.coveiot.android.leonardo.dashboard.vitals.utils;

import android.content.Context;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.List;
/* loaded from: classes4.dex */
public class MyBarDataset extends BarDataSet {
    public List<Integer> m;
    public Context n;

    public MyBarDataset(List<BarEntry> list, String str, List<Integer> list2, Context context) {
        super(list, str);
        this.m = list2;
        this.n = context;
    }

    @Override // com.github.mikephil.charting.data.BaseDataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getColor(int i) {
        return PayUtils.getStressRangeColor(this.m.get(i).intValue(), this.n);
    }
}
