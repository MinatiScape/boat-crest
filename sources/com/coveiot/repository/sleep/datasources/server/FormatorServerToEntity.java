package com.coveiot.repository.sleep.datasources.server;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.SleepHelper;
import com.coveiot.android.sleepalgorithm.filtering.Type;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepData;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepHour;
import com.coveiot.android.sleepalgorithm.filtering.model.SleepExpression;
import com.coveiot.android.sleepalgorithm.filtering.model.SleepType;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.ActivityDataModel;
import com.coveiot.coveaccess.model.server.SleepDataBean;
import com.coveiot.coveaccess.sleepscore.model.SleepData;
import com.coveiot.coveaccess.sleepscore.model.SleepHistory;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.SleepSummaryData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

        public final List<HourlySleepData> a(String str) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < 24) {
                HourlySleepData hourlySleepData = new HourlySleepData();
                StringBuilder sb = new StringBuilder();
                Locale locale = Locale.ENGLISH;
                String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(":00:00");
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                int i2 = i + 1;
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb3.append(format2);
                sb3.append(":00:00");
                String sb4 = sb3.toString();
                if (i == 23) {
                    sb4 = "00:00:00";
                }
                hourlySleepData.setStartTime(sb2);
                hourlySleepData.setEndTime(sb4);
                hourlySleepData.setIntervalValue(0);
                hourlySleepData.setDeepSleep(0);
                hourlySleepData.setLigthSleep(0);
                hourlySleepData.setAwake(60);
                hourlySleepData.setDate(str);
                hourlySleepData.setCodevalue(getEmptySleepHourCodedValuesList());
                arrayList.add(hourlySleepData);
                i = i2;
            }
            return arrayList;
        }

        @NotNull
        public final ActivityDataModel getActivityData(@NotNull DailySleepData dailySleepData, @NotNull List<? extends HourlySleepData> hourlySleepData) {
            Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
            Intrinsics.checkNotNullParameter(hourlySleepData, "hourlySleepData");
            ActivityDataModel activityDataModel = new ActivityDataModel();
            activityDataModel.setActivityType(ActivityType.SLEEP);
            activityDataModel.setActivityBaseUnit(ActivityBaseUnit.MINUTES);
            activityDataModel.setDate(dailySleepData.getDate());
            int value = dailySleepData.getValue();
            if (value == 0) {
                value = dailySleepData.getDeepSleep() + dailySleepData.getLightSleep();
            }
            activityDataModel.setValue(String.valueOf(value));
            ArrayList arrayList = new ArrayList();
            for (HourlySleepData hourlySleepData2 : hourlySleepData) {
                ActivityDataModel.TimeLogBean.LogsBean logsBean = new ActivityDataModel.TimeLogBean.LogsBean();
                logsBean.setStartTime(hourlySleepData2.getStartTime());
                logsBean.setEndTime(hourlySleepData2.getEndTime());
                logsBean.setIntervalValue(String.valueOf(hourlySleepData2.getIntervalValue()));
                ActivityDataModel.TimeLogBean.LogsBean.OtherDataBean otherDataBean = new ActivityDataModel.TimeLogBean.LogsBean.OtherDataBean();
                otherDataBean.setCodedValues(hourlySleepData2.getCodevalue());
                otherDataBean.setDeepSleep(String.valueOf(hourlySleepData2.getDeepSleep()));
                otherDataBean.setLightSleep(String.valueOf(hourlySleepData2.getLigthSleep()));
                otherDataBean.setAwake(String.valueOf(hourlySleepData2.getAwake()));
                logsBean.setOtherData(otherDataBean);
                arrayList.add(logsBean);
            }
            activityDataModel.setTimeLog(new ActivityDataModel.TimeLogBean(arrayList));
            return activityDataModel;
        }

        @NotNull
        public final ArrayList<Integer> getEmptySleepHourCodedValuesList() {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < 60; i++) {
                arrayList.add(-1);
            }
            return arrayList;
        }

        @NotNull
        public final List<HourlySleepData> getHourlySleepData(@NotNull ActivityDataModel activityData) {
            Intrinsics.checkNotNullParameter(activityData, "activityData");
            ArrayList arrayList = new ArrayList();
            if (activityData.getTimeLog() != null && !RepositoryUtils.isEmpty(activityData.getTimeLog().getLogs())) {
                for (ActivityDataModel.TimeLogBean.LogsBean logsBean : activityData.getTimeLog().getLogs()) {
                    HourlySleepData hourlySleepData = new HourlySleepData();
                    hourlySleepData.setStartTime(logsBean.getStartTime());
                    hourlySleepData.setEndTime(logsBean.getEndTime());
                    String intervalValue = logsBean.getIntervalValue();
                    Intrinsics.checkNotNullExpressionValue(intervalValue, "activityLog.intervalValue");
                    hourlySleepData.setIntervalValue(Integer.parseInt(intervalValue));
                    String deepSleep = logsBean.getOtherData().getDeepSleep();
                    Intrinsics.checkNotNullExpressionValue(deepSleep, "activityLog.otherData.deepSleep");
                    hourlySleepData.setDeepSleep(Integer.parseInt(deepSleep));
                    String lightSleep = logsBean.getOtherData().getLightSleep();
                    Intrinsics.checkNotNullExpressionValue(lightSleep, "activityLog.otherData.lightSleep");
                    hourlySleepData.setLigthSleep(Integer.parseInt(lightSleep));
                    String intervalValue2 = logsBean.getIntervalValue();
                    Intrinsics.checkNotNullExpressionValue(intervalValue2, "activityLog.intervalValue");
                    hourlySleepData.setAwake(60 - Integer.parseInt(intervalValue2));
                    hourlySleepData.setDate(activityData.getDate());
                    if (logsBean.getOtherData() != null && !RepositoryUtils.isEmpty(logsBean.getOtherData().getCodedValues())) {
                        hourlySleepData.setCodevalue((ArrayList) logsBean.getOtherData().getCodedValues());
                    }
                    arrayList.add(hourlySleepData);
                }
                return arrayList;
            }
            String date = activityData.getDate();
            Intrinsics.checkNotNullExpressionValue(date, "activityData.date");
            return a(date);
        }

        @NotNull
        public final SleepDataBean getSleepDataBean(@NotNull DailySleepData dailySleepData, @NotNull List<? extends HourlySleepData> hourlySleepData, @Nullable SleepSummaryData sleepSummaryData, @NotNull Context context) {
            FilteredSleepHour filteredSleepHour;
            int i;
            SleepDataModel sleepDataModel;
            Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
            Intrinsics.checkNotNullParameter(hourlySleepData, "hourlySleepData");
            Intrinsics.checkNotNullParameter(context, "context");
            Type a2 = a(context);
            SleepDataBean sleepDataBean = new SleepDataBean();
            sleepDataBean.setType(ActivityType.SLEEP.name());
            sleepDataBean.setBaseUnit(ActivityBaseUnit.MINUTES.name());
            sleepDataBean.setDate(dailySleepData.getDate());
            sleepDataBean.setSleepScore(dailySleepData.getSleepScore());
            sleepDataBean.setMinHr(dailySleepData.getMinHr());
            sleepDataBean.setMaxHr(dailySleepData.getMaxHr());
            sleepDataBean.setMaxStress(dailySleepData.getMaxStress());
            sleepDataBean.setMinStress(dailySleepData.getMinStress());
            sleepDataBean.setMaxAmbientSound(dailySleepData.getMaxAmbientSound());
            sleepDataBean.setMinAmbientSound(dailySleepData.getMinAmbientSound());
            sleepDataBean.setBreathQuality(dailySleepData.getBreathQuality());
            int value = dailySleepData.getValue();
            if (value == 0) {
                if (((sleepSummaryData == null || (sleepDataModel = sleepSummaryData.getSleepDataModel()) == null) ? null : Integer.valueOf(sleepDataModel.getCountOfREMMinutes())) != null) {
                    SleepDataModel sleepDataModel2 = sleepSummaryData.getSleepDataModel();
                    Integer valueOf = sleepDataModel2 != null ? Integer.valueOf(sleepDataModel2.getCountOfREMMinutes()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    i = valueOf.intValue();
                } else {
                    i = 0;
                }
                value = i + dailySleepData.getDeepSleep() + dailySleepData.getLightSleep();
            }
            sleepDataBean.setValue(value);
            if (sleepSummaryData != null) {
                SleepDataBean.SummaryBean summaryBean = new SleepDataBean.SummaryBean();
                summaryBean.setSleepStartTime(sleepSummaryData.getStartTime());
                summaryBean.setSleepEndTime(sleepSummaryData.getEndTime());
                summaryBean.setAwakeDurations(sleepSummaryData.getAwakeList());
                summaryBean.setDeepSleepDurations(sleepSummaryData.getDeepSleepList());
                summaryBean.setLightSleepDurations(sleepSummaryData.getLightSleepList());
                summaryBean.setRemSleepDurations(sleepSummaryData.getRemSleepList());
                sleepDataBean.setSummary(summaryBean);
            }
            ArrayList arrayList = new ArrayList();
            for (HourlySleepData hourlySleepData2 : hourlySleepData) {
                SleepDataBean.TimeLogBean.LogsBean logsBean = new SleepDataBean.TimeLogBean.LogsBean();
                logsBean.setStartTime(hourlySleepData2.getStartTime());
                String endTime = hourlySleepData2.getEndTime();
                if (endTime.equals("24:00:00")) {
                    endTime = "00:00:00";
                }
                logsBean.setEndTime(endTime);
                logsBean.setCodedValues(hourlySleepData2.getCodevalue());
                logsBean.setRefinedValues(SleepHelper.getRefinedValuesList((ArrayList) hourlySleepData2.getCodevalue(), a2));
                logsBean.setDeepSleep(hourlySleepData2.getDeepSleep());
                logsBean.setLightSleep(hourlySleepData2.getLigthSleep());
                logsBean.setAwake(hourlySleepData2.getAwake());
                if (sleepSummaryData != null && sleepSummaryData.getFilteredSleepData() != null) {
                    FilteredSleepData filteredSleepData = sleepSummaryData.getFilteredSleepData();
                    if (filteredSleepData != null) {
                        String startTime = hourlySleepData2.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "hourlySleepData.startTime");
                        filteredSleepHour = filteredSleepData.getSleepHour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
                    } else {
                        filteredSleepHour = null;
                    }
                    if (filteredSleepHour != null) {
                        logsBean.setRemSleep(filteredSleepHour.getCountREM());
                    }
                }
                arrayList.add(logsBean);
            }
            SleepDataBean.TimeLogBean timeLogBean = new SleepDataBean.TimeLogBean();
            timeLogBean.setLogs(arrayList);
            if (value > 0) {
                sleepDataBean.setTimeLog(timeLogBean);
            }
            SleepDataBean.Codec codec = new SleepDataBean.Codec();
            SleepExpression deepSleepCodec = SleepHelper.getSleepExpression(a2, SleepType.DEEP_SLEEP);
            SleepExpression lightSleepCodec = SleepHelper.getSleepExpression(a2, SleepType.LIGHT_SLEEP);
            SleepExpression awakeSleepCodec = SleepHelper.getSleepExpression(a2, SleepType.AWAKE);
            SleepExpression remSleepCodec = SleepHelper.getSleepExpression(a2, SleepType.REM);
            Intrinsics.checkNotNullExpressionValue(deepSleepCodec, "deepSleepCodec");
            codec.setDeepSleep(a(deepSleepCodec));
            Intrinsics.checkNotNullExpressionValue(lightSleepCodec, "lightSleepCodec");
            codec.setLightSleep(a(lightSleepCodec));
            Intrinsics.checkNotNullExpressionValue(awakeSleepCodec, "awakeSleepCodec");
            codec.setAwake(a(awakeSleepCodec));
            Intrinsics.checkNotNullExpressionValue(remSleepCodec, "remSleepCodec");
            codec.setRemSleep(a(remSleepCodec));
            sleepDataBean.setCodec(codec);
            return sleepDataBean;
        }

        @NotNull
        public final DailySleepData getSleepDataFrom(@NotNull ActivityDataModel activityData) {
            Intrinsics.checkNotNullParameter(activityData, "activityData");
            DailySleepData dailySleepData = new DailySleepData();
            dailySleepData.setDate(activityData.getDate());
            if (!RepositoryUtils.isEmpty(activityData.getDeepSleep())) {
                String deepSleep = activityData.getDeepSleep();
                Intrinsics.checkNotNullExpressionValue(deepSleep, "activityData.deepSleep");
                dailySleepData.setDeepSleep(Integer.parseInt(deepSleep));
            }
            if (!RepositoryUtils.isEmpty(activityData.getLightSleep())) {
                String lightSleep = activityData.getLightSleep();
                Intrinsics.checkNotNullExpressionValue(lightSleep, "activityData.lightSleep");
                dailySleepData.setLightSleep(Integer.parseInt(lightSleep));
            }
            if (!RepositoryUtils.isEmpty(activityData.getValue())) {
                String value = activityData.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "activityData.value");
                dailySleepData.setValue(Integer.parseInt(value));
            }
            dailySleepData.setAwakeTime(1440 - dailySleepData.getValue());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(RepositoryUtils.parseDate(activityData.getDate(), "yyyy-MM-dd"));
            if (RepositoryUtils.findDateDifference(calendar, Calendar.getInstance()) > 1) {
                dailySleepData.setSyncedToSevrer(true);
            }
            return dailySleepData;
        }

        @NotNull
        public final SleepData getSleepDataHelper(@NotNull DailySleepData dailySleepData, @NotNull List<? extends HourlySleepData> hourlySleepData, @Nullable SleepSummaryData sleepSummaryData, @NotNull Context context) {
            Unit unit;
            FilteredSleepHour filteredSleepHour;
            Unit unit2;
            Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
            Intrinsics.checkNotNullParameter(hourlySleepData, "hourlySleepData");
            Intrinsics.checkNotNullParameter(context, "context");
            Type a2 = a(context);
            SleepData sleepData = new SleepData();
            ArrayList arrayList = new ArrayList();
            sleepData.setType(ActivityType.SLEEP.name());
            sleepData.setBaseUnit(ActivityBaseUnit.MINUTES.name());
            sleepData.setDate(dailySleepData.getDate());
            if (sleepSummaryData != null) {
                SleepDataModel sleepDataModel = sleepSummaryData.getSleepDataModel();
                if (sleepDataModel != null) {
                    Intrinsics.checkNotNullExpressionValue(sleepDataModel, "sleepDataModel");
                    sleepData.setValue(Integer.valueOf(sleepDataModel.getCountTotalSleep()));
                    unit2 = Unit.INSTANCE;
                } else {
                    unit2 = null;
                }
                if (unit2 == null) {
                    Companion companion = FormatorServerToEntity.Companion;
                    sleepData.setValue(0);
                }
                SleepData.Summary summary = new SleepData.Summary();
                summary.setSleepStartTime(sleepSummaryData.getStartTime());
                summary.setSleepEndTime(sleepSummaryData.getEndTime());
                summary.setAwakeDurations(sleepSummaryData.getAwakeList());
                summary.setDeepSleepDurations(sleepSummaryData.getDeepSleepList());
                summary.setLightSleepDurations(sleepSummaryData.getLightSleepList());
                summary.setRemSleepDurations(sleepSummaryData.getRemSleepList());
                sleepData.setSummary(summary);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                sleepData.setValue(0);
            }
            for (HourlySleepData hourlySleepData2 : hourlySleepData) {
                SleepData.TimeLog.Log log = new SleepData.TimeLog.Log();
                log.setStartTime(hourlySleepData2.getStartTime());
                String endTime = hourlySleepData2.getEndTime();
                if (endTime.equals("24:00:00")) {
                    endTime = "00:00:00";
                }
                log.setEndTime(endTime);
                log.setRefinedValues(SleepHelper.getRefinedValuesList((ArrayList) hourlySleepData2.getCodevalue(), a2));
                log.setDeepSleep(Integer.valueOf(hourlySleepData2.getDeepSleep()));
                log.setLightSleep(Integer.valueOf(hourlySleepData2.getLigthSleep()));
                log.setAwake(Integer.valueOf(hourlySleepData2.getAwake()));
                if (sleepSummaryData != null && sleepSummaryData.getFilteredSleepData() != null) {
                    FilteredSleepData filteredSleepData = sleepSummaryData.getFilteredSleepData();
                    if (filteredSleepData != null) {
                        String startTime = hourlySleepData2.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "hourlySleepData.startTime");
                        filteredSleepHour = filteredSleepData.getSleepHour(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0)));
                    } else {
                        filteredSleepHour = null;
                    }
                    if (filteredSleepHour != null) {
                        log.setRemSleep(Integer.valueOf(filteredSleepHour.getCountREM()));
                    }
                }
                arrayList.add(log);
            }
            SleepData.TimeLog timeLog = new SleepData.TimeLog();
            timeLog.setLogs(arrayList);
            sleepData.setTimeLog(timeLog);
            return sleepData;
        }

        @NotNull
        public final SleepHistory getSleepHistoryItemHelper(@NotNull DailySleepData dailySleepData, @Nullable SleepSummaryData sleepSummaryData) {
            SleepDataModel sleepDataModel;
            Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
            SleepHistory sleepHistory = new SleepHistory();
            sleepHistory.setType(ActivityType.SLEEP.name());
            sleepHistory.setBaseUnit(ActivityBaseUnit.MINUTES.name());
            sleepHistory.setDate(dailySleepData.getDate());
            int value = dailySleepData.getValue();
            if (value == 0) {
                int i = 0;
                if (((sleepSummaryData == null || (sleepDataModel = sleepSummaryData.getSleepDataModel()) == null) ? null : Integer.valueOf(sleepDataModel.getCountOfREMMinutes())) != null) {
                    SleepDataModel sleepDataModel2 = sleepSummaryData.getSleepDataModel();
                    Integer valueOf = sleepDataModel2 != null ? Integer.valueOf(sleepDataModel2.getCountOfREMMinutes()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    i = valueOf.intValue();
                }
                value = i + dailySleepData.getDeepSleep() + dailySleepData.getLightSleep();
            }
            sleepHistory.setValue(Integer.valueOf(value));
            if (sleepSummaryData != null) {
                SleepHistory.Summary summary = new SleepHistory.Summary();
                summary.setSleepStartTime(sleepSummaryData.getStartTime());
                summary.setSleepEndTime(sleepSummaryData.getEndTime());
                summary.setAwakeDurations(sleepSummaryData.getAwakeList());
                summary.setDeepSleepDurations(sleepSummaryData.getDeepSleepList());
                summary.setLightSleepDurations(sleepSummaryData.getLightSleepList());
                summary.setRemSleepDurations(sleepSummaryData.getRemSleepList());
                sleepHistory.setSummary(summary);
            }
            return sleepHistory;
        }

        @NotNull
        public final DailySleepData getSleepDataFrom(@NotNull SleepDataBean activityData) {
            Intrinsics.checkNotNullParameter(activityData, "activityData");
            DailySleepData dailySleepData = new DailySleepData();
            dailySleepData.setDate(activityData.getDate());
            dailySleepData.setValue(activityData.getValue());
            activityData.getSleepScore();
            dailySleepData.setSleepScore(activityData.getSleepScore());
            activityData.getMinHr();
            dailySleepData.setMinHr(activityData.getMinHr());
            activityData.getMaxHr();
            dailySleepData.setMaxHr(activityData.getMaxHr());
            activityData.getMaxStress();
            dailySleepData.setMaxStress(activityData.getMaxStress());
            activityData.getMinStress();
            dailySleepData.setMinStress(activityData.getMinStress());
            dailySleepData.getMinHr();
            if (dailySleepData.getMinHr() > 0) {
                dailySleepData.getMaxHr();
                if (dailySleepData.getMaxHr() > 0) {
                    dailySleepData.getMinStress();
                    if (dailySleepData.getMinStress() > 0) {
                        dailySleepData.getMaxStress();
                        if (dailySleepData.getMaxStress() > 0) {
                            dailySleepData.setIsMinMaxUpdated(1);
                            activityData.getMaxAmbientSound();
                            dailySleepData.setMaxAmbientSound(activityData.getMaxAmbientSound());
                            activityData.getMinAmbientSound();
                            dailySleepData.setMinAmbientSound(activityData.getMinAmbientSound());
                            activityData.getBreathQuality();
                            dailySleepData.setBreathQuality(activityData.getBreathQuality());
                            dailySleepData.setAwakeTime(1440 - dailySleepData.getValue());
                            dailySleepData.setSyncedToSevrer(true);
                            return dailySleepData;
                        }
                    }
                }
            }
            dailySleepData.setIsMinMaxUpdated(0);
            activityData.getMaxAmbientSound();
            dailySleepData.setMaxAmbientSound(activityData.getMaxAmbientSound());
            activityData.getMinAmbientSound();
            dailySleepData.setMinAmbientSound(activityData.getMinAmbientSound());
            activityData.getBreathQuality();
            dailySleepData.setBreathQuality(activityData.getBreathQuality());
            dailySleepData.setAwakeTime(1440 - dailySleepData.getValue());
            dailySleepData.setSyncedToSevrer(true);
            return dailySleepData;
        }

        @NotNull
        public final List<HourlySleepData> getHourlySleepData(@NotNull SleepDataBean activityData) {
            Intrinsics.checkNotNullParameter(activityData, "activityData");
            ArrayList arrayList = new ArrayList();
            if (activityData.getTimeLog() != null && !RepositoryUtils.isEmpty(activityData.getTimeLog().getLogs())) {
                for (SleepDataBean.TimeLogBean.LogsBean logsBean : activityData.getTimeLog().getLogs()) {
                    HourlySleepData hourlySleepData = new HourlySleepData();
                    hourlySleepData.setStartTime(logsBean.getStartTime());
                    hourlySleepData.setEndTime(logsBean.getEndTime());
                    hourlySleepData.setIntervalValue(logsBean.getDeepSleep() + logsBean.getLightSleep());
                    hourlySleepData.setDeepSleep(logsBean.getDeepSleep());
                    hourlySleepData.setLigthSleep(logsBean.getLightSleep());
                    hourlySleepData.setAwake(60 - hourlySleepData.getIntervalValue());
                    hourlySleepData.setDate(activityData.getDate());
                    if (!RepositoryUtils.isEmpty(logsBean.getCodedValues())) {
                        hourlySleepData.setCodevalue((ArrayList) logsBean.getCodedValues());
                    }
                    arrayList.add(hourlySleepData);
                }
                return arrayList;
            }
            String date = activityData.getDate();
            Intrinsics.checkNotNullExpressionValue(date, "activityData.date");
            return a(date);
        }

        public final Type a(Context context) {
            if (RepositoryUtils.isJStyleDevice(context)) {
                if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
                    return Type.JStyleWithREM;
                }
                return Type.JStyle;
            } else if (RepositoryUtils.isMatrixDevice(context)) {
                return Type.Matrix;
            } else {
                if (RepositoryUtils.isMoyangDevice(context)) {
                    return Type.Moyang;
                }
                if (RepositoryUtils.isSmaDevice(context)) {
                    return Type.SMA;
                }
                if (RepositoryUtils.isKaHaDeviceREM(context)) {
                    return Type.KaHaWithREM;
                }
                if (RepositoryUtils.isIDODevice(context)) {
                    return Type.IDO;
                }
                if (RepositoryUtils.isTouchELXDevice(context)) {
                    return Type.TouchELX;
                }
                if (RepositoryUtils.isEastApexDevice(context)) {
                    return Type.EastApex;
                }
                return Type.KaHa;
            }
        }

        public final SleepDataBean.Codec.Expression a(SleepExpression sleepExpression) {
            SleepDataBean.Codec.Expression expression = new SleepDataBean.Codec.Expression();
            expression.setEq(sleepExpression.getEq());
            expression.setGte(sleepExpression.getGte());
            expression.setLte(sleepExpression.getLte());
            return expression;
        }
    }
}
