package com.coveiot.repository.hrv.datasource.server;

import com.coveiot.coveaccess.hrvdata.HRVDataNew;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.repository.RepositoryUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class FormatorServerToEntity {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final HRVDataNew getActivityDataModel(@NotNull DailyHRV dailyHRV, @NotNull List<? extends HourlyHRV> hrvHourlyData) {
            Intrinsics.checkNotNullParameter(dailyHRV, "dailyHRV");
            Intrinsics.checkNotNullParameter(hrvHourlyData, "hrvHourlyData");
            HRVDataNew hRVDataNew = new HRVDataNew();
            hRVDataNew.setDate(dailyHRV.mDate);
            hRVDataNew.setType("HRV");
            hRVDataNew.setBaseUnit("MILLISECONDS");
            hRVDataNew.setMax(Double.valueOf(dailyHRV.hrv_high));
            hRVDataNew.setMin(Double.valueOf(dailyHRV.hrv_low));
            hRVDataNew.setAvg(Double.valueOf(dailyHRV.hrv_avg));
            hRVDataNew.setBaseline(Double.valueOf(dailyHRV.baselinne));
            hRVDataNew.setReadiness(Double.valueOf(dailyHRV.readiness));
            HRVDataNew.TimeLog timeLog = new HRVDataNew.TimeLog();
            ArrayList arrayList = new ArrayList();
            for (HourlyHRV hourlyHRV : hrvHourlyData) {
                HRVDataNew.TimeLog.Logs logs = new HRVDataNew.TimeLog.Logs();
                logs.setStartTime(hourlyHRV.getStartTime());
                logs.setEndTime(hourlyHRV.getEndTime());
                logs.setMax(Double.valueOf(hourlyHRV.hrv_high));
                logs.setMin(Double.valueOf(hourlyHRV.hrv_low));
                logs.setAvg(Double.valueOf(hourlyHRV.hrv_avg));
                logs.setCodedValues(hourlyHRV.getCodevalue());
                arrayList.add(logs);
            }
            timeLog.setLogs(arrayList);
            hRVDataNew.setTimeLog(timeLog);
            return hRVDataNew;
        }

        @NotNull
        public final DailyHRV getDailyStressData(@NotNull HRVDataNew hrvDataNew) {
            Intrinsics.checkNotNullParameter(hrvDataNew, "hrvDataNew");
            DailyHRV dailyHRV = new DailyHRV();
            dailyHRV.mDate = hrvDataNew.getDate();
            Double max = hrvDataNew.getMax();
            Intrinsics.checkNotNullExpressionValue(max, "hrvDataNew.max");
            dailyHRV.hrv_high = max.doubleValue();
            Double min = hrvDataNew.getMin();
            Intrinsics.checkNotNullExpressionValue(min, "hrvDataNew.min");
            dailyHRV.hrv_low = min.doubleValue();
            dailyHRV.hrv_avg = hrvDataNew.getAvg().doubleValue();
            dailyHRV.baselinne = hrvDataNew.getBaseline().doubleValue();
            dailyHRV.readiness = hrvDataNew.getReadiness().doubleValue();
            dailyHRV.is_sync_server = true;
            return dailyHRV;
        }

        @NotNull
        public final List<HourlyHRV> getHourlyStressData(@NotNull HRVDataNew hrvDataNew) {
            Intrinsics.checkNotNullParameter(hrvDataNew, "hrvDataNew");
            ArrayList arrayList = new ArrayList();
            if (hrvDataNew.getTimeLog() != null && !RepositoryUtils.isEmpty(hrvDataNew.getTimeLog().getLogs())) {
                for (HRVDataNew.TimeLog.Logs logs : hrvDataNew.getTimeLog().getLogs()) {
                    HourlyHRV hourlyHRV = new HourlyHRV();
                    hourlyHRV.setStartTime(logs.getStartTime());
                    hourlyHRV.setEndTime(logs.getEndTime());
                    Double max = logs.getMax();
                    Intrinsics.checkNotNullExpressionValue(max, "activityLog.max");
                    hourlyHRV.hrv_high = max.doubleValue();
                    Double min = logs.getMin();
                    Intrinsics.checkNotNullExpressionValue(min, "activityLog.min");
                    hourlyHRV.hrv_low = min.doubleValue();
                    hourlyHRV.hrv_avg = logs.getAvg().doubleValue();
                    hourlyHRV.mDate = hrvDataNew.getDate();
                    hourlyHRV.setCodevalue((ArrayList) logs.getCodedValues());
                    arrayList.add(hourlyHRV);
                }
            }
            return arrayList;
        }
    }
}
