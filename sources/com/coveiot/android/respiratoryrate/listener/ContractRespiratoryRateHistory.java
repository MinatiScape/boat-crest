package com.coveiot.android.respiratoryrate.listener;

import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface ContractRespiratoryRateHistory {
    void onLineChartDataLoaded(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2, @NotNull String str);
}
