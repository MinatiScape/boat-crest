package com.coveiot.repository.periodicspo2.server;

import com.coveiot.coveaccess.spo2.Spo2Data;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
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
        public final Spo2Data getActivityDataModel(@NotNull DailyPeriodicSpo2 per, @NotNull List<? extends EntityHourlySpo2> periodicSpo2HourlyData) {
            Intrinsics.checkNotNullParameter(per, "per");
            Intrinsics.checkNotNullParameter(periodicSpo2HourlyData, "periodicSpo2HourlyData");
            Spo2Data spo2Data = new Spo2Data();
            spo2Data.setDate(per.mDate);
            spo2Data.setType("SPO2");
            spo2Data.setBaseUnit("PERCENTAGE");
            spo2Data.setMax(per.spo2_high);
            spo2Data.setMin(per.spo2_low);
            spo2Data.setAvg(Double.valueOf(per.spo2_avg));
            Spo2Data.TimeLog timeLog = new Spo2Data.TimeLog();
            ArrayList arrayList = new ArrayList();
            for (EntityHourlySpo2 entityHourlySpo2 : periodicSpo2HourlyData) {
                Spo2Data.TimeLog.Log log = new Spo2Data.TimeLog.Log();
                log.setStartTime(entityHourlySpo2.getStartTime());
                log.setEndTime(entityHourlySpo2.getEndTime());
                log.setMax(entityHourlySpo2.spo2_high);
                log.setMin(entityHourlySpo2.spo2_low);
                log.setAvg(Double.valueOf(entityHourlySpo2.spo2_avg));
                log.setCodedValues(entityHourlySpo2.codevalue);
                arrayList.add(log);
            }
            timeLog.setLogs(arrayList);
            spo2Data.setTimeLog(timeLog);
            return spo2Data;
        }

        @NotNull
        public final DailyPeriodicSpo2 getDailyPeriodicSpo2Data(@NotNull Spo2Data periodicSpo2Data) {
            Intrinsics.checkNotNullParameter(periodicSpo2Data, "periodicSpo2Data");
            DailyPeriodicSpo2 dailyPeriodicSpo2 = new DailyPeriodicSpo2();
            dailyPeriodicSpo2.mDate = periodicSpo2Data.getDate();
            dailyPeriodicSpo2.spo2_high = periodicSpo2Data.getMax();
            dailyPeriodicSpo2.spo2_low = periodicSpo2Data.getMin();
            Double avg = periodicSpo2Data.getAvg();
            Intrinsics.checkNotNullExpressionValue(avg, "periodicSpo2Data.avg");
            dailyPeriodicSpo2.spo2_avg = avg.doubleValue();
            dailyPeriodicSpo2.is_sync_server = true;
            return dailyPeriodicSpo2;
        }

        @NotNull
        public final List<EntityHourlySpo2> getHourlyPeriodicSpo2Data(@NotNull Spo2Data periodicSpo2Data) {
            Intrinsics.checkNotNullParameter(periodicSpo2Data, "periodicSpo2Data");
            ArrayList arrayList = new ArrayList();
            if (periodicSpo2Data.getTimeLog() != null && !RepositoryUtils.isEmpty(periodicSpo2Data.getTimeLog().getLogs())) {
                for (Spo2Data.TimeLog.Log log : periodicSpo2Data.getTimeLog().getLogs()) {
                    EntityHourlySpo2 entityHourlySpo2 = new EntityHourlySpo2();
                    entityHourlySpo2.setStartTime(log.getStartTime());
                    entityHourlySpo2.setEndTime(log.getEndTime());
                    entityHourlySpo2.spo2_high = log.getMax();
                    entityHourlySpo2.spo2_low = log.getMin();
                    entityHourlySpo2.spo2_avg = log.getAvg().doubleValue();
                    entityHourlySpo2.mDate = periodicSpo2Data.getDate();
                    entityHourlySpo2.codevalue = (ArrayList) log.getCodedValues();
                    arrayList.add(entityHourlySpo2);
                }
            }
            return arrayList;
        }
    }
}
