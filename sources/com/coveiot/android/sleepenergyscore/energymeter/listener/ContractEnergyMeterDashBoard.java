package com.coveiot.android.sleepenergyscore.energymeter.listener;

import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.TreeMap;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface ContractEnergyMeterDashBoard {
    void updateEnergyMeterData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2, @Nullable TreeMap<String, Integer> treeMap, @Nullable TreeMap<String, Integer> treeMap2, @Nullable TreeMap<String, Integer> treeMap3, int i);
}
