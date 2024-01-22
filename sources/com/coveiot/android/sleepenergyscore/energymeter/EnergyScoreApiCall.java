package com.coveiot.android.sleepenergyscore.energymeter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.FitnessComputedDataApiCall;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.EnergyMeterHistoryData;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.SleepEnergyScoreEventDataHome;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.coveaccess.energyscore.EnergyScoreApiReq;
import com.coveiot.coveaccess.energyscore.EnergyScoreApiRes;
import com.coveiot.coveaccess.energyscore.model.ActivityData;
import com.coveiot.coveaccess.energyscore.model.EnergyScoreData;
import com.coveiot.coveaccess.energyscore.model.FitnessData;
import com.coveiot.coveaccess.energyscore.model.SleepSummary;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.CoveEventBusManager;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyScoreApiCall {
    @NotNull
    public static final EnergyScoreApiCall INSTANCE = new EnergyScoreApiCall();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Handler f5696a = new Handler(Looper.getMainLooper());

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreApiCall", f = "EnergyScoreApiCall.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {441}, m = "getActivityData", n = {"walkdata", "context", "fitActivitySessionList", "fitnessDataList", "sleepSummary", "sleepSummaryData", "activityData"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
    /* loaded from: classes6.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public Object L$6;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EnergyScoreApiCall.this.getActivityData(null, null, this);
        }
    }

    public static final void c(String pageType, Context context) {
        Intrinsics.checkNotNullParameter(pageType, "$pageType");
        Intrinsics.checkNotNullParameter(context, "$context");
        if (pageType.equals(context.getResources().getString(R.string.energy_meter))) {
            CoveEventBusManager.getInstance().getEventBus().post(new EnergyScoreEventData());
        } else if (pageType.equals(context.getResources().getString(R.string.energy_meter_history))) {
            EnergyMeterHistoryData energyMeterHistoryData = new EnergyMeterHistoryData();
            energyMeterHistoryData.setType(context.getResources().getString(R.string.load_graph));
            CoveEventBusManager.getInstance().getEventBus().post(energyMeterHistoryData);
        } else {
            CoveEventBusManager.getInstance().getEventBus().post(new SleepEnergyScoreEventDataHome());
        }
    }

    public final FitnessData b(String str) {
        int i;
        FitnessData fitnessData = new FitnessData();
        fitnessData.activityType = ActivityType.WALK.name();
        fitnessData.activityBaseUnit = ActivityBaseUnit.STEPS.name();
        fitnessData.calories = BleConst.GetDeviceTime;
        fitnessData.date = str;
        fitnessData.createdDate = str;
        fitnessData.meters = BleConst.GetDeviceTime;
        fitnessData.value = BleConst.GetDeviceTime;
        ArrayList arrayList = new ArrayList();
        if (Intrinsics.areEqual(str, RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"))) {
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
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
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
        FitnessData.TimeLog timeLog = new FitnessData.TimeLog();
        timeLog.logs = arrayList;
        fitnessData.timeLog = timeLog;
        return fitnessData;
    }

    public final void copyToFile(@NotNull EnergyScoreApiReq energyScoreApiReq, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(energyScoreApiReq, "energyScoreApiReq");
        Intrinsics.checkNotNullParameter(context, "context");
        String str = new Gson().toJson(energyScoreApiReq).toString();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(context.getFilesDir(), "energyscore")));
        bufferedWriter.write(str);
        bufferedWriter.close();
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v8, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v9, types: [T, com.coveiot.coveaccess.energyscore.model.EnergyScoreData] */
    public final void energyScoreBatchApiCall(@NotNull Calendar startDate, @NotNull Calendar endDate, @NotNull Context context, @NotNull String type) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        if (UserDataManager.getInstance(context).isEnableSleepEnergyScoreFeature(context)) {
            RepositoryUtils.formatDate(endDate.getTime(), "yyyy-MM-dd");
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = RepositoryUtils.formatDate(startDate.getTime(), "yyyy-MM-dd");
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = new EnergyScoreData();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = new ArrayList();
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EnergyScoreApiCall$energyScoreBatchApiCall$1(context, objectRef, startDate, endDate, objectRef3, objectRef2, type, null), 2, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01a7  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getActivityData(@org.jetbrains.annotations.NotNull com.coveiot.covedb.walk.entities.DailyWalkData r18, @org.jetbrains.annotations.NotNull android.content.Context r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.coveiot.coveaccess.energyscore.model.ActivityData> r20) {
        /*
            Method dump skipped, instructions count: 435
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreApiCall.getActivityData(com.coveiot.covedb.walk.entities.DailyWalkData, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final ActivityData getActivityDataWithZero(@NotNull String currentDate, @NotNull Context mContext) {
        Integer sleepScore;
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        ArrayList arrayList = new ArrayList();
        SleepSummary sleepSummary = new SleepSummary();
        SleepSummary.Summary summary = new SleepSummary.Summary();
        ActivityData activityData = new ActivityData();
        SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
        SleepScoreData sleepScoreData = companion.getInstance(mContext).getSleepScoreData(currentDate, BleApiManager.getInstance(mContext).getBleApi().getMacAddress());
        if (sleepScoreData == null || (sleepScore = sleepScoreData.getSleepScore()) == null || sleepScore.intValue() <= 0) {
            return null;
        }
        Integer sleepScore2 = companion.getInstance(mContext).getSleepScore(currentDate, BleApiManager.getInstance(mContext).getBleApi().getMacAddress());
        Intrinsics.checkNotNull(sleepScore2);
        summary.sleepScore = sleepScore2.intValue();
        sleepSummary.summary = summary;
        activityData.sleepSummary = sleepSummary;
        arrayList.add(INSTANCE.b(currentDate));
        activityData.date = currentDate;
        activityData.fitnessData = arrayList;
        return activityData;
    }

    @NotNull
    public final List<Date> getListOfNotSyncDates(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Long lastSyncedDate = EnergyScoreRepository.Companion.getInstance(context).getLastSyncedDate(bleApi.getMacAddress());
        int maxDaysOfStepsDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand();
        if (lastSyncedDate != null && lastSyncedDate.longValue() != 0) {
            String dateFromTimeStamp = WorkoutUtils.INSTANCE.getDateFromTimeStamp(lastSyncedDate, "yyyy-MM-dd");
            if (RepositoryUtils.findDateDifference(dateFromTimeStamp) <= maxDaysOfStepsDataOnBand) {
                maxDaysOfStepsDataOnBand = RepositoryUtils.findDateDifference(dateFromTimeStamp);
            }
        }
        LogsHelper.d("maxdays*** ", String.valueOf(maxDaysOfStepsDataOnBand));
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfStepsDataOnBand);
        Date time = calendar.getTime();
        Date time2 = Calendar.getInstance().getTime();
        String formatDate = RepositoryUtils.formatDate(time, "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(startDateEM, \"yyyy-MM-dd\")");
        String formatDate2 = RepositoryUtils.formatDate(time2, "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(endDateEM, \"yyyy-MM-dd\")");
        return Utils.getDatesBetween(formatDate, formatDate2);
    }

    @NotNull
    public final Handler getMainHandler() {
        return f5696a;
    }

    public final void saveDataInDb(@NotNull EnergyScoreApiRes energyScoreApiRes, @NotNull Context context, boolean z, @NotNull String str) {
        final String str2;
        int i;
        final Context context2;
        ArrayList<EnergyData> arrayList;
        EnergyScoreDbData energyScoreDbData;
        EnergyData energyData;
        EnergyData energyData2;
        List<EnergyScoreApiRes.Data.ContributingFactors.Dock.Session> sessions;
        List<EnergyScoreApiRes.Data.ContributingFactors.Dock.Session> list;
        ArrayList<EnergyData> arrayList2;
        EnergyScoreDbData energyScoreDbData2;
        EnergyData energyData3;
        ArrayList<QuestionAnswerData> arrayList3;
        Context context3 = context;
        String pageType = str;
        Intrinsics.checkNotNullParameter(energyScoreApiRes, "energyScoreApiRes");
        Intrinsics.checkNotNullParameter(context3, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        List<EnergyScoreApiRes.Data> data = energyScoreApiRes.getData();
        new ArrayList();
        int size = data.size();
        int i2 = 0;
        while (i2 < size) {
            if (m.equals(data.get(i2).getStatus(), context.getResources().getString(R.string.error_caps), true)) {
                str2 = pageType;
                i = size;
                context2 = context3;
            } else {
                ArrayList<EnergyData> arrayList4 = new ArrayList<>();
                EnergyScoreDbData energyScoreDbData3 = new EnergyScoreDbData();
                EnergyData energyData4 = new EnergyData();
                EnergyData.ContributingFactors contributingFactors = new EnergyData.ContributingFactors(new EnergyData());
                EnergyData.ContributingFactors.Dock dock = new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData()));
                EnergyData.ContributingFactors.Replenish replenish = new EnergyData.ContributingFactors.Replenish(new EnergyData.ContributingFactors(new EnergyData()));
                ArrayList arrayList5 = new ArrayList();
                energyScoreDbData3.message = energyScoreApiRes.getMessage();
                energyScoreDbData3.status = energyScoreApiRes.getStatus();
                if (Intrinsics.areEqual(data.get(i2).getDate(), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"))) {
                    String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                    Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                    EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(context3).getEnergyScoreData(formatDate, BleApiManager.getInstance(context).getBleApi().getMacAddress());
                    if (energyScoreData != null && (arrayList3 = energyScoreData.feedbackList) != null && arrayList3.size() > 0) {
                        energyScoreDbData3.feedbackList = energyScoreData.feedbackList;
                        energyScoreDbData3.questionnaireId = energyScoreData.questionnaireId;
                    }
                }
                if (data.get(i2).getContributingFactors() != null) {
                    if (data.get(i2).getContributingFactors().getDock() != null) {
                        if (data.get(i2).getContributingFactors().getDock().getNonSessionCalorieContribution() != null) {
                            dock.setNonSessionCalorieContribution(data.get(i2).getContributingFactors().getDock().getNonSessionCalorieContribution());
                        }
                        if (data.get(i2).getContributingFactors().getDock().getSessions() == null || (sessions = data.get(i2).getContributingFactors().getDock().getSessions()) == null || sessions.size() <= 0) {
                            i = size;
                            arrayList = arrayList4;
                            energyScoreDbData = energyScoreDbData3;
                            energyData2 = energyData4;
                        } else {
                            int size2 = sessions.size();
                            int i3 = 0;
                            while (i3 < size2) {
                                int i4 = size;
                                int i5 = size2;
                                EnergyData.ContributingFactors.Dock.Session session = new EnergyData.ContributingFactors.Dock.Session(new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData())));
                                ArrayList arrayList6 = new ArrayList();
                                session.setContribution(sessions.get(i3).getContribution());
                                session.setSessionType(sessions.get(i3).getSessionType());
                                session.setTotalDuration(sessions.get(i3).getTotalDuration());
                                List<EnergyScoreApiRes.Data.ContributingFactors.Dock.Session.Log> logs = sessions.get(i3).getLogs();
                                if (logs == null || logs.size() <= 0) {
                                    list = sessions;
                                    arrayList2 = arrayList4;
                                    energyScoreDbData2 = energyScoreDbData3;
                                    energyData3 = energyData4;
                                } else {
                                    int size3 = logs.size();
                                    list = sessions;
                                    int i6 = 0;
                                    while (i6 < size3) {
                                        int i7 = size3;
                                        EnergyData.ContributingFactors.Dock.Session.Log log = new EnergyData.ContributingFactors.Dock.Session.Log(new EnergyData.ContributingFactors.Dock.Session(new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData()))));
                                        log.setActivityDuration(logs.get(i6).getActivityDuration());
                                        log.setSessionEndDate(logs.get(i6).getSessionEndDate());
                                        log.setSessionStartDate(logs.get(i6).getSessionStartDate());
                                        arrayList6.add(log);
                                        i6++;
                                        size3 = i7;
                                        energyScoreDbData3 = energyScoreDbData3;
                                        arrayList4 = arrayList4;
                                        energyData4 = energyData4;
                                    }
                                    arrayList2 = arrayList4;
                                    energyScoreDbData2 = energyScoreDbData3;
                                    energyData3 = energyData4;
                                    session.setLogs(arrayList6);
                                }
                                arrayList5.add(session);
                                i3++;
                                size = i4;
                                size2 = i5;
                                sessions = list;
                                energyScoreDbData3 = energyScoreDbData2;
                                arrayList4 = arrayList2;
                                energyData4 = energyData3;
                            }
                            i = size;
                            arrayList = arrayList4;
                            energyScoreDbData = energyScoreDbData3;
                            energyData2 = energyData4;
                            dock.setSessions(arrayList5);
                        }
                        contributingFactors.setDock(dock);
                    } else {
                        i = size;
                        arrayList = arrayList4;
                        energyScoreDbData = energyScoreDbData3;
                        energyData2 = energyData4;
                    }
                    if (data.get(i2).getContributingFactors().getReplenish() != null) {
                        if (data.get(i2).getContributingFactors().getReplenish().getSleepScoreContribution() != null) {
                            replenish.setSleepScoreContribution(data.get(i2).getContributingFactors().getReplenish().getSleepScoreContribution());
                        }
                        contributingFactors.setReplenish(replenish);
                    }
                    energyData = energyData2;
                    energyData.setContributingFactors(contributingFactors);
                } else {
                    i = size;
                    arrayList = arrayList4;
                    energyScoreDbData = energyScoreDbData3;
                    energyData = energyData4;
                }
                energyData.setStatus(data.get(i2).getStatus());
                energyData.setDate(data.get(i2).getDate());
                energyData.setComputedDate(data.get(i2).getComputedDate());
                energyData.setAlgoIdentifier(data.get(i2).getAlgoIdentifier());
                if (data.get(i2).getCurrentEnergyLevel() != null) {
                    energyData.setCurrentEnergyLevel(data.get(i2).getCurrentEnergyLevel());
                }
                if (data.get(i2).getInitialEnergyLevel() != null) {
                    energyData.setInitialEnergyLevel(data.get(i2).getInitialEnergyLevel());
                }
                if (data.get(i2).getDockPoint() != null) {
                    energyData.setDockPoint(data.get(i2).getDockPoint());
                }
                if (data.get(i2).getHourlyEnergyLevel() != null) {
                    energyData.setHourlyEnergyLevel(data.get(i2).getHourlyEnergyLevel());
                }
                if (data.get(i2).getReplenishPoints() != null) {
                    energyData.setReplenishPoints(data.get(i2).getReplenishPoints());
                }
                ArrayList<EnergyData> arrayList7 = arrayList;
                arrayList7.add(energyData);
                String date = data.get(i2).getDate();
                Intrinsics.checkNotNullExpressionValue(date, "energyDataList[i].date");
                EnergyScoreDbData energyScoreDbData4 = energyScoreDbData;
                energyScoreDbData4.setMDate(date);
                energyScoreDbData4.isSyncedWithServer = 0;
                if (!z) {
                    energyScoreDbData4.lastSyncedDate = Long.valueOf(System.currentTimeMillis());
                }
                energyScoreDbData4.data = arrayList7;
                energyScoreDbData4.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
                EnergyScoreRepository.Companion companion = EnergyScoreRepository.Companion;
                context2 = context;
                companion.getInstance(context2).insert(energyScoreDbData4);
                Long insert = companion.getInstance(context2).insert(energyScoreDbData4);
                Integer valueOf = insert != null ? Integer.valueOf((int) insert.longValue()) : null;
                if ((valueOf != null && valueOf.intValue() == -1) || i2 + 1 != data.size()) {
                    str2 = str;
                } else {
                    str2 = str;
                    f5696a.post(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.energymeter.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            EnergyScoreApiCall.c(str2, context2);
                        }
                    });
                }
            }
            i2++;
            context3 = context2;
            pageType = str2;
            size = i;
        }
        Context context4 = context3;
        if (z) {
            FitnessComputedDataApiCall.INSTANCE.sendSleepEnergyScoreDataToServer(context4);
        }
    }
}
