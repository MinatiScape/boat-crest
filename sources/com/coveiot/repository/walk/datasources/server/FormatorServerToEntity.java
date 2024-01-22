package com.coveiot.repository.walk.datasources.server;

import com.coveiot.coveaccess.energyscore.model.FitnessData;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.ActivityDataModel;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.repository.RepositoryUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
        public final ActivityDataModel getActivityData(@NotNull DailyWalkData dailyWalkData, @NotNull List<? extends HourlyWalkData> hourlyWalkDataList) {
            Intrinsics.checkNotNullParameter(dailyWalkData, "dailyWalkData");
            Intrinsics.checkNotNullParameter(hourlyWalkDataList, "hourlyWalkDataList");
            new ArrayList();
            ActivityDataModel activityDataModel = new ActivityDataModel();
            activityDataModel.setActivityType(ActivityType.WALK);
            activityDataModel.setActivityBaseUnit(ActivityBaseUnit.STEPS);
            activityDataModel.setCalories(String.valueOf(dailyWalkData.getCalories()));
            activityDataModel.setDate(dailyWalkData.getmDate());
            activityDataModel.setCreatedDate(dailyWalkData.getmDate());
            activityDataModel.setMeters(String.valueOf(dailyWalkData.getMeters()));
            activityDataModel.setValue(String.valueOf(dailyWalkData.getValue()));
            ArrayList arrayList = new ArrayList();
            for (HourlyWalkData hourlyWalkData : hourlyWalkDataList) {
                ActivityDataModel.TimeLogBean.LogsBean logsBean = new ActivityDataModel.TimeLogBean.LogsBean();
                logsBean.setStartTime(hourlyWalkData.getStartTime());
                logsBean.setEndTime(hourlyWalkData.getEndTime());
                logsBean.setIntervalValue(String.valueOf(hourlyWalkData.getIntervelValue()));
                logsBean.setIntervalCalories(String.valueOf(hourlyWalkData.getCalories()));
                ActivityDataModel.TimeLogBean.LogsBean.OtherDataBean otherDataBean = new ActivityDataModel.TimeLogBean.LogsBean.OtherDataBean();
                otherDataBean.setCodedValues(hourlyWalkData.getCodevalue());
                logsBean.setOtherData(otherDataBean);
                arrayList.add(logsBean);
            }
            activityDataModel.setTimeLog(new ActivityDataModel.TimeLogBean(arrayList));
            return activityDataModel;
        }

        @NotNull
        public final FitnessData getActivityDataForEnergyScore(@NotNull DailyWalkData dailyWalkData, @NotNull List<? extends HourlyWalkData> hourlyWalkDataList) {
            int i;
            Intrinsics.checkNotNullParameter(dailyWalkData, "dailyWalkData");
            Intrinsics.checkNotNullParameter(hourlyWalkDataList, "hourlyWalkDataList");
            FitnessData fitnessData = new FitnessData();
            fitnessData.activityType = ActivityType.WALK.name();
            fitnessData.activityBaseUnit = ActivityBaseUnit.STEPS.name();
            fitnessData.calories = String.valueOf(dailyWalkData.getCalories());
            fitnessData.date = dailyWalkData.getmDate();
            fitnessData.createdDate = dailyWalkData.getmDate();
            fitnessData.meters = String.valueOf(dailyWalkData.getMeters());
            fitnessData.value = String.valueOf(dailyWalkData.getValue());
            ArrayList arrayList = new ArrayList();
            if (Intrinsics.areEqual(dailyWalkData.mDate, RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"))) {
                String format = new SimpleDateFormat("HH", Locale.ENGLISH).format(new Date());
                Intrinsics.checkNotNullExpressionValue(format, "format.format(Date())");
                i = Integer.parseInt(format);
            } else {
                i = 23;
            }
            if (i >= 0) {
                int i2 = 0;
                while (true) {
                    FitnessData.TimeLog.Log log = new FitnessData.TimeLog.Log();
                    int i3 = i2 + 1;
                    int i4 = i3 == 24 ? 0 : i3;
                    StringBuilder sb = new StringBuilder();
                    Locale locale = Locale.ENGLISH;
                    String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    sb.append(format2);
                    sb.append(":00:00");
                    String sb2 = sb.toString();
                    StringBuilder sb3 = new StringBuilder();
                    String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                    sb3.append(format3);
                    sb3.append(":00:00");
                    String sb4 = sb3.toString();
                    System.out.println((Object) ("starthour " + sb2 + " endhr " + sb4));
                    log.startTime = sb2;
                    log.endTime = sb4;
                    log.intervalCalories = 0;
                    arrayList.add(log);
                    if (i2 == i) {
                        break;
                    }
                    i2 = i3;
                }
            }
            for (HourlyWalkData hourlyWalkData : hourlyWalkDataList) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    FitnessData.TimeLog.Log log2 = (FitnessData.TimeLog.Log) it.next();
                    if (Intrinsics.areEqual(hourlyWalkData.getEndTime(), log2.endTime) && Intrinsics.areEqual(hourlyWalkData.getStartTime(), log2.startTime)) {
                        log2.intervalCalories = hourlyWalkData.getCalories();
                    }
                }
            }
            FitnessData.TimeLog timeLog = new FitnessData.TimeLog();
            timeLog.logs = arrayList;
            fitnessData.timeLog = timeLog;
            return fitnessData;
        }

        @NotNull
        public final DailyWalkData getDailyWalkDataFrom(@NotNull ActivityDataModel activityData) {
            Intrinsics.checkNotNullParameter(activityData, "activityData");
            DailyWalkData dailyWalkData = new DailyWalkData();
            dailyWalkData.mDate = activityData.getDate();
            if (!RepositoryUtils.isEmpty(activityData.getMeters())) {
                String meters = activityData.getMeters();
                Intrinsics.checkNotNullExpressionValue(meters, "activityData.meters");
                dailyWalkData.setMeters(Integer.parseInt(meters));
            }
            if (!RepositoryUtils.isEmpty(activityData.getCalories())) {
                String calories = activityData.getCalories();
                Intrinsics.checkNotNullExpressionValue(calories, "activityData.calories");
                dailyWalkData.setCalories(Double.parseDouble(calories));
            }
            if (!RepositoryUtils.isEmpty(activityData.getValue())) {
                String value = activityData.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "activityData.value");
                dailyWalkData.setValue(Integer.parseInt(value));
            }
            dailyWalkData.is_sync_server = true;
            return dailyWalkData;
        }

        @NotNull
        public final List<HourlyWalkData> getHourlyWalkWalkData(@NotNull ActivityDataModel activityData) {
            Intrinsics.checkNotNullParameter(activityData, "activityData");
            ArrayList arrayList = new ArrayList();
            if (activityData.getTimeLog() != null && !RepositoryUtils.isEmpty(activityData.getTimeLog().getLogs())) {
                for (ActivityDataModel.TimeLogBean.LogsBean logsBean : activityData.getTimeLog().getLogs()) {
                    HourlyWalkData hourlyWalkData = new HourlyWalkData();
                    hourlyWalkData.setStartTime(logsBean.getStartTime());
                    hourlyWalkData.setEndTime(logsBean.getEndTime());
                    String intervalValue = logsBean.getIntervalValue();
                    Intrinsics.checkNotNullExpressionValue(intervalValue, "activityLog.intervalValue");
                    hourlyWalkData.setIntervelValue(Integer.parseInt(intervalValue));
                    String intervalCalories = logsBean.getIntervalCalories();
                    Intrinsics.checkNotNullExpressionValue(intervalCalories, "activityLog.intervalCalories");
                    hourlyWalkData.setCalories(Integer.parseInt(intervalCalories));
                    hourlyWalkData.mDate = activityData.getDate();
                    if (logsBean.getOtherData() != null && !RepositoryUtils.isEmpty(logsBean.getOtherData().getCodedValues())) {
                        hourlyWalkData.setCodevalue((ArrayList) logsBean.getOtherData().getCodedValues());
                    }
                    arrayList.add(hourlyWalkData);
                }
            }
            return arrayList;
        }
    }
}
