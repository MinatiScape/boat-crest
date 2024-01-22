package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class RadarData extends ChartData<IRadarDataSet> {

    /* renamed from: a  reason: collision with root package name */
    public List<String> f7942a;

    public RadarData() {
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.data.ChartData
    public Entry getEntryForHighlight(Highlight highlight) {
        return getDataSetByIndex(highlight.getDataSetIndex()).getEntryForIndex((int) highlight.getX());
    }

    public List<String> getLabels() {
        return this.f7942a;
    }

    public void setLabels(List<String> list) {
        this.f7942a = list;
    }

    public RadarData(List<IRadarDataSet> list) {
        super(list);
    }

    public void setLabels(String... strArr) {
        this.f7942a = Arrays.asList(strArr);
    }

    public RadarData(IRadarDataSet... iRadarDataSetArr) {
        super(iRadarDataSetArr);
    }
}
