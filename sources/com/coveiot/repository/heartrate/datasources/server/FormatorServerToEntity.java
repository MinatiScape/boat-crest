package com.coveiot.repository.heartrate.datasources.server;

import com.coveiot.coveaccess.heartrate.HeartRateData;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
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
        public final HeartRateData getActivityDataModel(@NotNull EntityDailyHeartRateData heartRate, @NotNull List<? extends EntityHourlyHeartRateData> heartRateHourlyData) {
            Intrinsics.checkNotNullParameter(heartRate, "heartRate");
            Intrinsics.checkNotNullParameter(heartRateHourlyData, "heartRateHourlyData");
            HeartRateData heartRateData = new HeartRateData();
            heartRateData.setDate(heartRate.getDate());
            heartRateData.setType(EcgStyleReportUtil.UserInfoKey.HR);
            heartRateData.setBaseUnit("BPM");
            heartRateData.setMax(heartRate.getMaxHeartRate());
            heartRateData.setMin(heartRate.getMinHeartRate());
            heartRateData.setRest(heartRate.getAvgHeartRate());
            HeartRateData.TimeLogBean timeLogBean = new HeartRateData.TimeLogBean();
            ArrayList arrayList = new ArrayList();
            for (EntityHourlyHeartRateData entityHourlyHeartRateData : heartRateHourlyData) {
                HeartRateData.TimeLogBean.LogsBean logsBean = new HeartRateData.TimeLogBean.LogsBean();
                logsBean.setStartTime(entityHourlyHeartRateData.getStartTime());
                logsBean.setEndTime(entityHourlyHeartRateData.getEndTime());
                logsBean.setMax(entityHourlyHeartRateData.getMaxHeartRate());
                logsBean.setMin(entityHourlyHeartRateData.getMinHeartRate());
                logsBean.setCodedValues(entityHourlyHeartRateData.getCodedValues());
                arrayList.add(logsBean);
            }
            timeLogBean.setLogs(arrayList);
            heartRateData.setTimeLog(timeLogBean);
            return heartRateData;
        }

        @NotNull
        public final EntityDailyHeartRateData getDailyHeartRateData(@NotNull HeartRateData heartRateData) {
            Intrinsics.checkNotNullParameter(heartRateData, "heartRateData");
            EntityDailyHeartRateData entityDailyHeartRateData = new EntityDailyHeartRateData();
            entityDailyHeartRateData.setDate(heartRateData.getDate());
            entityDailyHeartRateData.setMaxHeartRate(heartRateData.getMax());
            entityDailyHeartRateData.setMinHeartRate(heartRateData.getMin());
            entityDailyHeartRateData.setAvgHeartRate(heartRateData.getRest());
            entityDailyHeartRateData.setIsSyncServer(true);
            entityDailyHeartRateData.setRestHeartRate(heartRateData.getRest());
            return entityDailyHeartRateData;
        }

        @NotNull
        public final List<EntityHourlyHeartRateData> getHourlyHeartRateData(@NotNull HeartRateData heartRateData) {
            Intrinsics.checkNotNullParameter(heartRateData, "heartRateData");
            ArrayList arrayList = new ArrayList();
            if (heartRateData.getTimeLog() != null && !RepositoryUtils.isEmpty(heartRateData.getTimeLog().getLogs())) {
                for (HeartRateData.TimeLogBean.LogsBean logsBean : heartRateData.getTimeLog().getLogs()) {
                    EntityHourlyHeartRateData entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                    entityHourlyHeartRateData.setStartTime(logsBean.getStartTime());
                    entityHourlyHeartRateData.setEndTime(logsBean.getEndTime());
                    entityHourlyHeartRateData.setMaxHeartRate(logsBean.getMax());
                    entityHourlyHeartRateData.setMinHeartRate(logsBean.getMin());
                    entityHourlyHeartRateData.setDate(heartRateData.getDate());
                    if (!RepositoryUtils.isEmpty(logsBean.getCodedValues())) {
                        int i = 0;
                        for (Integer element : logsBean.getCodedValues()) {
                            Intrinsics.checkNotNullExpressionValue(element, "element");
                            i += element.intValue();
                        }
                        entityHourlyHeartRateData.setAvgHeartRate(i / logsBean.getCodedValues().size());
                        entityHourlyHeartRateData.setCodedValues((ArrayList) logsBean.getCodedValues());
                    }
                    arrayList.add(entityHourlyHeartRateData);
                }
            }
            return arrayList;
        }
    }
}
