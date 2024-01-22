package com.coveiot.android.leonardo.dashboard.health.listeners;

import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ContractUpdateHrBpHealthTextListener {
    void onFitnessHeightWeightUpdate(boolean z);

    void updateDailyLevelBpData(@Nullable EntityDailyBp entityDailyBp);

    void updateDailyLevelHrData(@Nullable EntityDailyHeartRateData entityDailyHeartRateData);
}
