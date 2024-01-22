package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractFragmentSleepHistory {
    void displaySelectedDateRange(@Nullable String str);

    void onDataLoaded(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2);
}
