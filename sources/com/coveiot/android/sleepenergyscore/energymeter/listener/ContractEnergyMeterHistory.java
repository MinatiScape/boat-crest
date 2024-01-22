package com.coveiot.android.sleepenergyscore.energymeter.listener;

import com.github.mikephil.charting.data.CandleEntry;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface ContractEnergyMeterHistory {
    void onCandleChartDataLoaded(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2);
}
