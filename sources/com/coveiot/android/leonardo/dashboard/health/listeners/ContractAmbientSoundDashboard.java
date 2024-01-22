package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractAmbientSoundDashboard {
    boolean isSyncInProgress();

    void updateDailyLevelData(@Nullable EntityDailyAmbientSoundData entityDailyAmbientSoundData);

    void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2);
}
