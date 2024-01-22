package com.coveiot.repository.temperature.datasources.server;

import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.coveiot.coveaccess.temperature.model.TemperatureData;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
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
        public final TemperatureData getActivityDataModel(@NotNull DailyTemperature temperature, @NotNull List<? extends HourlyTemperature> temperatureHourlyData) {
            Intrinsics.checkNotNullParameter(temperature, "temperature");
            Intrinsics.checkNotNullParameter(temperatureHourlyData, "temperatureHourlyData");
            TemperatureData temperatureData = new TemperatureData();
            temperatureData.setDate(temperature.mDate);
            temperatureData.setType("BODY_TEMPERATURE");
            temperatureData.setBaseUnit(WatchfaceConstants.CELSIUS);
            temperatureData.setMax(Double.valueOf(temperature.temperature_high));
            temperatureData.setMin(Double.valueOf(temperature.temperature_low));
            temperatureData.setAvg(Double.valueOf(temperature.temperature_avg));
            TemperatureData.TimeLog timeLog = new TemperatureData.TimeLog();
            ArrayList arrayList = new ArrayList();
            for (HourlyTemperature hourlyTemperature : temperatureHourlyData) {
                TemperatureData.TimeLog.Log log = new TemperatureData.TimeLog.Log();
                log.setStartTime(hourlyTemperature.getStartTime());
                log.setEndTime(hourlyTemperature.getEndTime());
                log.setMax(Double.valueOf(hourlyTemperature.temperature_high));
                log.setMin(Double.valueOf(hourlyTemperature.temperature_low));
                log.setAvg(Double.valueOf(hourlyTemperature.temperature_avg));
                log.setCodedValues(hourlyTemperature.getCodevalue());
                arrayList.add(log);
            }
            timeLog.setLogs(arrayList);
            temperatureData.setTimeLog(timeLog);
            return temperatureData;
        }

        @NotNull
        public final DailyTemperature getDailyTemperatureData(@NotNull TemperatureData temperatureData) {
            Intrinsics.checkNotNullParameter(temperatureData, "temperatureData");
            DailyTemperature dailyTemperature = new DailyTemperature();
            dailyTemperature.mDate = temperatureData.getDate();
            Double max = temperatureData.getMax();
            Intrinsics.checkNotNullExpressionValue(max, "temperatureData.max");
            dailyTemperature.temperature_high = max.doubleValue();
            Double min = temperatureData.getMin();
            Intrinsics.checkNotNullExpressionValue(min, "temperatureData.min");
            dailyTemperature.temperature_low = min.doubleValue();
            Double avg = temperatureData.getAvg();
            Intrinsics.checkNotNullExpressionValue(avg, "temperatureData.avg");
            dailyTemperature.temperature_avg = avg.doubleValue();
            dailyTemperature.is_sync_server = true;
            return dailyTemperature;
        }

        @NotNull
        public final List<HourlyTemperature> getHourlyTemperatureData(@NotNull TemperatureData temperatureData) {
            Intrinsics.checkNotNullParameter(temperatureData, "temperatureData");
            ArrayList arrayList = new ArrayList();
            if (temperatureData.getTimeLog() != null && !RepositoryUtils.isEmpty(temperatureData.getTimeLog().getLogs())) {
                for (TemperatureData.TimeLog.Log log : temperatureData.getTimeLog().getLogs()) {
                    HourlyTemperature hourlyTemperature = new HourlyTemperature();
                    hourlyTemperature.setStartTime(log.getStartTime());
                    hourlyTemperature.setEndTime(log.getEndTime());
                    hourlyTemperature.temperature_high = log.getMax().doubleValue();
                    hourlyTemperature.temperature_low = log.getMin().doubleValue();
                    hourlyTemperature.temperature_avg = log.getAvg().doubleValue();
                    hourlyTemperature.mDate = temperatureData.getDate();
                    hourlyTemperature.setCodevalue((ArrayList) log.getCodedValues());
                    arrayList.add(hourlyTemperature);
                }
            }
            return arrayList;
        }
    }
}
