package com.coveiot.repository.datasync;

import android.content.Context;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.hrv.datasource.HRVRepository;
import com.coveiot.repository.periodicspo2.PeriodicSpo2Repository;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.stress.StressRepository;
import com.coveiot.repository.temperature.TemperatureRepository;
import com.coveiot.repository.walk.WalkRepository;
import com.coveiot.utils.utility.AppUtils;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class LastMeasuredDataHelper {
    @Nullable
    public final String getLastMeasuredHRVData(@NotNull Context context, @NotNull String serialNo) {
        HourlyHRV hourlyHRV;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        try {
            hourlyHRV = HRVRepository.Companion.getInstance(context).getLatestRecordHourly(serialNo);
        } catch (Exception e) {
            e.printStackTrace();
            hourlyHRV = null;
        }
        if (hourlyHRV != null) {
            int size = 60 / hourlyHRV.getCodevalue().size();
            int size2 = hourlyHRV.getCodevalue().size();
            int i = 0;
            double d = 0.0d;
            for (int i2 = 0; i2 < size2; i2++) {
                Double d2 = hourlyHRV.getCodevalue().get(i2);
                Intrinsics.checkNotNullExpressionValue(d2, "hourlyHRV.codevalue.get(i)");
                if (d2.doubleValue() > 0.0d) {
                    Double d3 = hourlyHRV.getCodevalue().get(i2);
                    Intrinsics.checkNotNullExpressionValue(d3, "hourlyHRV.codevalue.get(i)");
                    d = d3.doubleValue();
                    i = i2;
                }
            }
            Date parseDate = AppUtils.parseDate(hourlyHRV.mDate + ' ' + hourlyHRV.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i);
            if (d > 0.0d) {
                StringBuilder sb = new StringBuilder();
                sb.append(d);
                sb.append('_');
                sb.append(calendar.getTimeInMillis());
                return sb.toString();
            }
        }
        return null;
    }

    @Nullable
    public final String getLastMeasuredHeartRateData(@NotNull Context context, @NotNull String serialNo) {
        EntityHourlyHeartRateData entityHourlyHeartRateData;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        try {
            entityHourlyHeartRateData = HeartRateRepository.Companion.getInstance(context).getLatestRecordHourly(serialNo);
        } catch (Exception e) {
            e.printStackTrace();
            entityHourlyHeartRateData = null;
        }
        if (entityHourlyHeartRateData != null) {
            int size = 60 / entityHourlyHeartRateData.getCodedValues().size();
            int size2 = entityHourlyHeartRateData.getCodedValues().size();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                Integer num = entityHourlyHeartRateData.getCodedValues().get(i3);
                Intrinsics.checkNotNullExpressionValue(num, "hourlyHeartRate.codedValues.get(i)");
                if (num.intValue() > 0) {
                    Integer num2 = entityHourlyHeartRateData.getCodedValues().get(i3);
                    Intrinsics.checkNotNullExpressionValue(num2, "hourlyHeartRate.codedValues.get(i)");
                    i = num2.intValue();
                    i2 = i3;
                }
            }
            Date parseDate = AppUtils.parseDate(entityHourlyHeartRateData.getDate() + ' ' + entityHourlyHeartRateData.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i2);
            if (i > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('_');
                sb.append(calendar.getTimeInMillis());
                return sb.toString();
            }
        }
        return null;
    }

    @Nullable
    public final String getLastMeasuredPeriodicSPO2Data(@NotNull Context context, @NotNull String serialNo) {
        EntityHourlySpo2 entityHourlySpo2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        try {
            entityHourlySpo2 = PeriodicSpo2Repository.Companion.getInstance(context).getLatestRecordHourly(serialNo);
        } catch (Exception e) {
            e.printStackTrace();
            entityHourlySpo2 = null;
        }
        if (entityHourlySpo2 != null) {
            int size = 60 / entityHourlySpo2.codevalue.size();
            int size2 = entityHourlySpo2.codevalue.size();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                Integer num = entityHourlySpo2.codevalue.get(i3);
                Intrinsics.checkNotNullExpressionValue(num, "hourlySpo2.codevalue.get(i)");
                if (num.intValue() > 0) {
                    Integer num2 = entityHourlySpo2.codevalue.get(i3);
                    Intrinsics.checkNotNullExpressionValue(num2, "hourlySpo2.codevalue.get(i)");
                    i = num2.intValue();
                    i2 = i3;
                }
            }
            Date parseDate = AppUtils.parseDate(entityHourlySpo2.mDate + ' ' + entityHourlySpo2.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i2);
            if (i > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('_');
                sb.append(calendar.getTimeInMillis());
                return sb.toString();
            }
        }
        return null;
    }

    @Nullable
    public final String getLastMeasuredPeriodicSleepData(@NotNull Context context, @NotNull String serialNo) {
        HourlySleepData hourlySleepData;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        try {
            hourlySleepData = SleepRepository.Companion.getInstance(context).getLatestRecordHourly(serialNo);
        } catch (Exception e) {
            e.printStackTrace();
            hourlySleepData = null;
        }
        if (hourlySleepData != null) {
            int size = 60 / hourlySleepData.getCodevalue().size();
            int size2 = hourlySleepData.getCodevalue().size();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                Integer num = hourlySleepData.getCodevalue().get(i3);
                Intrinsics.checkNotNullExpressionValue(num, "hourlySleepData.codevalue.get(i)");
                if (num.intValue() > 0) {
                    Integer num2 = hourlySleepData.getCodevalue().get(i3);
                    Intrinsics.checkNotNullExpressionValue(num2, "hourlySleepData.codevalue.get(i)");
                    i2 = num2.intValue();
                    i = i3;
                }
            }
            Date parseDate = AppUtils.parseDate(hourlySleepData.getDate() + ' ' + hourlySleepData.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i);
            StringBuilder sb = new StringBuilder();
            sb.append(i2);
            sb.append('_');
            sb.append(calendar.getTimeInMillis());
            return sb.toString();
        }
        return null;
    }

    @Nullable
    public final String getLastMeasuredStepsData(@NotNull Context context, @NotNull String serialNo) {
        HourlyWalkData hourlyWalkData;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        try {
            hourlyWalkData = WalkRepository.Companion.getInstance(context).getLatestRecordHourly(serialNo);
        } catch (Exception e) {
            e.printStackTrace();
            hourlyWalkData = null;
        }
        if (hourlyWalkData != null) {
            int size = 60 / hourlyWalkData.getCodevalue().size();
            int size2 = hourlyWalkData.getCodevalue().size();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                Integer num = hourlyWalkData.getCodevalue().get(i3);
                Intrinsics.checkNotNullExpressionValue(num, "hourlyWalkData.codevalue.get(i)");
                if (num.intValue() > 0) {
                    Integer num2 = hourlyWalkData.getCodevalue().get(i3);
                    Intrinsics.checkNotNullExpressionValue(num2, "hourlyWalkData.codevalue.get(i)");
                    i = num2.intValue();
                    i2 = i3;
                }
            }
            Date parseDate = AppUtils.parseDate(hourlyWalkData.mDate + ' ' + hourlyWalkData.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i2);
            if (i > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('_');
                sb.append(calendar.getTimeInMillis());
                return sb.toString();
            }
        }
        return null;
    }

    @Nullable
    public final String getLastMeasuredStressData(@NotNull Context context, @NotNull String serialNo) {
        HourlyStress hourlyStress;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        try {
            hourlyStress = StressRepository.Companion.getInstance(context).getLatestRecordHourly(serialNo);
        } catch (Exception e) {
            e.printStackTrace();
            hourlyStress = null;
        }
        if (hourlyStress != null) {
            int size = 60 / hourlyStress.getCodevalue().size();
            int size2 = hourlyStress.getCodevalue().size();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                Integer num = hourlyStress.getCodevalue().get(i3);
                Intrinsics.checkNotNullExpressionValue(num, "hourlyStress.codevalue.get(i)");
                if (num.intValue() > 0) {
                    Integer num2 = hourlyStress.getCodevalue().get(i3);
                    Intrinsics.checkNotNullExpressionValue(num2, "hourlyStress.codevalue.get(i)");
                    i = num2.intValue();
                    i2 = i3;
                }
            }
            Date parseDate = AppUtils.parseDate(hourlyStress.mDate + ' ' + hourlyStress.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i2);
            if (i > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('_');
                sb.append(calendar.getTimeInMillis());
                return sb.toString();
            }
        }
        return null;
    }

    @Nullable
    public final String getLastMeasuredTemperatureData(@NotNull Context context, @NotNull String serialNo) {
        HourlyTemperature hourlyTemperature;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        try {
            hourlyTemperature = TemperatureRepository.Companion.getInstance(context).getLatestRecordHourly(serialNo);
        } catch (Exception e) {
            e.printStackTrace();
            hourlyTemperature = null;
        }
        if (hourlyTemperature != null) {
            int size = 60 / hourlyTemperature.getCodevalue().size();
            int size2 = hourlyTemperature.getCodevalue().size();
            int i = 0;
            double d = 0.0d;
            for (int i2 = 0; i2 < size2; i2++) {
                Double d2 = hourlyTemperature.getCodevalue().get(i2);
                Intrinsics.checkNotNullExpressionValue(d2, "hourlyTemperature.codevalue.get(i)");
                if (d2.doubleValue() > 0.0d) {
                    Double d3 = hourlyTemperature.getCodevalue().get(i2);
                    Intrinsics.checkNotNullExpressionValue(d3, "hourlyTemperature.codevalue.get(i)");
                    d = d3.doubleValue();
                    i = i2;
                }
            }
            Date parseDate = AppUtils.parseDate(hourlyTemperature.mDate + ' ' + hourlyTemperature.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i);
            if (d > 0.0d) {
                StringBuilder sb = new StringBuilder();
                sb.append(d);
                sb.append('_');
                sb.append(calendar.getTimeInMillis());
                return sb.toString();
            }
        }
        return null;
    }
}
