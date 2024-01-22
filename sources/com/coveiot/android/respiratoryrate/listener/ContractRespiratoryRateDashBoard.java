package com.coveiot.android.respiratoryrate.listener;

import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.github.mikephil.charting.data.CandleEntry;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface ContractRespiratoryRateDashBoard {
    void onCandleChartDataLoaded(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2);

    void updateDailyLevelData(@Nullable DailyRespiratoryRateEntity dailyRespiratoryRateEntity);

    void updateRangeLevelData(@NotNull List<RespiratoryRateListBean> list);
}
