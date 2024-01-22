package com.coveiot.android.sleepenergyscore.sleepscore;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.EnergyMeterHistoryData;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.SleepScoreEventData;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiManager;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiReq;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiRes;
import com.coveiot.coveaccess.sleepscore.model.SleepData;
import com.coveiot.coveaccess.sleepscore.model.SleepHistory;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.SleepSummaryData;
import com.coveiot.repository.datasync.SleepSummaryHelper;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.repository.sleep.datasources.server.FormatorServerToEntity;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SleepScoreApiCall {
    @NotNull
    public static final SleepScoreApiCall INSTANCE = new SleepScoreApiCall();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f5738a = "SleepAcoreApiCall";
    @NotNull
    public static final Handler b = new Handler(Looper.getMainLooper());
    @NotNull
    public static String c = "yyyy-MM-dd";

    public static final void c(Ref.ObjectRef insertedDb, int i, SleepScoreApiRes sleepScoreApiRes, Calendar startCal, Calendar endCal, String type, Context context) {
        Intrinsics.checkNotNullParameter(insertedDb, "$insertedDb");
        Intrinsics.checkNotNullParameter(sleepScoreApiRes, "$sleepScoreApiRes");
        Intrinsics.checkNotNullParameter(startCal, "$startCal");
        Intrinsics.checkNotNullParameter(endCal, "$endCal");
        Intrinsics.checkNotNullParameter(type, "$type");
        Intrinsics.checkNotNullParameter(context, "$context");
        Integer num = (Integer) insertedDb.element;
        if ((num != null && num.intValue() == -1) || i + 1 != sleepScoreApiRes.getData().size()) {
            return;
        }
        INSTANCE.proceedNext(startCal, endCal, type, context);
    }

    public static final void d(int i, SleepScoreApiRes sleepScoreApiRes, Calendar startCal, Calendar endCal, String type, Context context) {
        Intrinsics.checkNotNullParameter(sleepScoreApiRes, "$sleepScoreApiRes");
        Intrinsics.checkNotNullParameter(startCal, "$startCal");
        Intrinsics.checkNotNullParameter(endCal, "$endCal");
        Intrinsics.checkNotNullParameter(type, "$type");
        Intrinsics.checkNotNullParameter(context, "$context");
        if (i + 1 == sleepScoreApiRes.getData().size()) {
            INSTANCE.proceedNext(startCal, endCal, type, context);
        }
    }

    public final void copyToFile(@NotNull SleepScoreApiReq sleepScoreApiReq, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(sleepScoreApiReq, "sleepScoreApiReq");
        Intrinsics.checkNotNullParameter(context, "context");
        if (m.equals("prod", "qa", true)) {
            String str = new Gson().toJson(sleepScoreApiReq).toString();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(context.getFilesDir(), "sleepscore")));
            bufferedWriter.write(str);
            bufferedWriter.close();
        }
    }

    @NotNull
    public final String getDateFormat() {
        return c;
    }

    @NotNull
    public final SleepData getFormattedDailySleepData(@NotNull Context context, @NotNull DailySleepData dailySleepData) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(AppUtils.parseDate(dailySleepData.getDate(), c));
        List<HourlySleepData> totaleHourlyData = SleepDBRead.getInstance(context).getTotaleHourlyData(dailySleepData.getDate(), BleApiManager.getInstance(context).getBleApi().getMacAddress());
        SleepSummaryHelper sleepSummaryHelper = SleepSummaryHelper.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        SleepSummaryData sleepScoreSummary = sleepSummaryHelper.getSleepScoreSummary(context, calendar);
        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
        Intrinsics.checkNotNull(totaleHourlyData);
        return companion.getSleepDataHelper(dailySleepData, totaleHourlyData, sleepScoreSummary, context);
    }

    @NotNull
    public final List<Date> getListOfNotSyncDates(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Long lastSyncedDate = SleepScoreRepository.Companion.getInstance(context).getLastSyncedDate(bleApi.getMacAddress());
        int maxDaysOfStepsDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand();
        if (lastSyncedDate != null && lastSyncedDate.longValue() != 0) {
            String dateFromTimeStamp = WorkoutUtils.INSTANCE.getDateFromTimeStamp(lastSyncedDate, c);
            if (RepositoryUtils.findDateDifference(dateFromTimeStamp) <= maxDaysOfStepsDataOnBand) {
                maxDaysOfStepsDataOnBand = RepositoryUtils.findDateDifference(dateFromTimeStamp);
            }
        }
        LogsHelper.d("maxdays*** ", String.valueOf(maxDaysOfStepsDataOnBand));
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfStepsDataOnBand);
        Date time = calendar.getTime();
        Date time2 = Calendar.getInstance().getTime();
        String formatDate = RepositoryUtils.formatDate(time, c);
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(startDateEM, dateFormat)");
        String formatDate2 = RepositoryUtils.formatDate(time2, c);
        Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(\n            … dateFormat\n            )");
        return Utils.getDatesBetween(formatDate, formatDate2);
    }

    @NotNull
    public final Handler getMainHandler() {
        return b;
    }

    public final void getSleepScoreBatchApiCall(@NotNull final Calendar startCal, @NotNull final Calendar endCal, @NotNull final Context context, @NotNull final String type) {
        Calendar calender;
        Intrinsics.checkNotNullParameter(startCal, "startCal");
        Intrinsics.checkNotNullParameter(endCal, "endCal");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        if (UserDataManager.getInstance(context).isEnableSleepEnergyScoreFeature(context)) {
            SleepScoreApiReq sleepScoreApiReq = new SleepScoreApiReq();
            List<Date> listOfNotSyncDates = getListOfNotSyncDates(context);
            if (listOfNotSyncDates.size() > 5) {
                Date parse = new SimpleDateFormat(c).parse(RepositoryUtils.formatDate(listOfNotSyncDates.get(0), c));
                Intrinsics.checkNotNullExpressionValue(parse, "formatter.parse(Reposito…otSynced[0], dateFormat))");
                calender = Calendar.getInstance();
                calender.setTime(parse);
                Intrinsics.checkNotNullExpressionValue(calender, "calender");
            } else {
                calender = startCal;
            }
            Object clone = calender.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(6, -1);
            String formatDate = RepositoryUtils.formatDate(endCal.getTime(), c);
            String formatDate2 = RepositoryUtils.formatDate(calendar.getTime(), c);
            String str = f5738a;
            LogsHelper.d(str, "sleepDatastarttime*** " + formatDate2);
            LogsHelper.d(str, "sleepDataendtime*** " + formatDate);
            List<DailySleepData> dailySleepDataBetweenDates = SleepDBRead.getInstance(context).getDailySleepDataBetweenDates(formatDate2, formatDate, BleApiManager.getInstance(context).getBleApi().getMacAddress());
            HashSet hashSet = new HashSet(dailySleepDataBetweenDates);
            dailySleepDataBetweenDates.clear();
            dailySleepDataBetweenDates.addAll(hashSet);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (dailySleepDataBetweenDates.size() > 0) {
                Calendar calendar2 = Calendar.getInstance();
                Intrinsics.checkNotNull(calendar2);
                String formatDate3 = RepositoryUtils.formatDate(calendar2.getTime(), c);
                for (DailySleepData dailySleepData : dailySleepDataBetweenDates) {
                    arrayList2.add(dailySleepData.getDate());
                }
                boolean contains = arrayList2.contains(formatDate3);
                for (DailySleepData dailySleepData2 : dailySleepDataBetweenDates) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(AppUtils.parseDate(dailySleepData2.getDate(), c));
                    Intrinsics.checkNotNull(calendar3);
                    String formatDate4 = RepositoryUtils.formatDate(calendar3.getTime(), c);
                    Intrinsics.checkNotNullExpressionValue(formatDate4, "formatDate(\n            …                        )");
                    SleepScoreData sleepScoreData = SleepScoreRepository.Companion.getInstance(context).getSleepScoreData(formatDate4, BleApiManager.getInstance(context).getBleApi().getMacAddress());
                    if (contains) {
                        Intrinsics.checkNotNullExpressionValue(dailySleepData2, "dailySleepData");
                        arrayList.add(getFormattedDailySleepData(context, dailySleepData2));
                    } else if (sleepScoreData == null) {
                        Intrinsics.checkNotNullExpressionValue(dailySleepData2, "dailySleepData");
                        arrayList.add(getFormattedDailySleepData(context, dailySleepData2));
                    }
                }
                String str2 = f5738a;
                LogsHelper.d(str2, "sleepDataBeansList size*** " + arrayList.size());
                if (arrayList.size() > 0) {
                    Date time = calendar.getTime();
                    Intrinsics.checkNotNullExpressionValue(time, "startDateCal.time");
                    ArrayList<SleepHistory> sleepHistoryArray = Utils.getSleepHistoryArray(time, context, c);
                    if (sleepHistoryArray != null && sleepHistoryArray.size() > 0) {
                        sleepScoreApiReq.setSleepHistory(sleepHistoryArray);
                    }
                    sleepScoreApiReq.setSleepData(arrayList);
                    sleepScoreApiReq.setUserInfo(Utils.getUserInfo(context));
                    copyToFile(sleepScoreApiReq, context);
                    SleepScoreApiManager.sendDataForSleepQualityCal(sleepScoreApiReq, new CoveApiListener<SleepScoreApiRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sleepenergyscore.sleepscore.SleepScoreApiCall$getSleepScoreBatchApiCall$2
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                            SleepScoreApiCall.INSTANCE.proceedNext(startCal, endCal, type, context);
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@NotNull SleepScoreApiRes sleepScoreApiRes) {
                            Intrinsics.checkNotNullParameter(sleepScoreApiRes, "sleepScoreApiRes");
                            LogsHelper.d("sleepRes*** ", new Gson().toJson(sleepScoreApiRes));
                            SleepScoreApiCall.INSTANCE.saveSleepScoreDataInDB(sleepScoreApiRes, startCal, endCal, context, type);
                        }
                    });
                    return;
                }
                proceedNext(startCal, endCal, type, context);
                return;
            }
            proceedNext(startCal, endCal, type, context);
        }
    }

    public final void proceedNext(@NotNull Calendar startCal, @NotNull Calendar endCal, @NotNull String type, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(startCal, "startCal");
        Intrinsics.checkNotNullParameter(endCal, "endCal");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.sleep))) {
            CoveEventBusManager.getInstance().getEventBus().post(new SleepScoreEventData());
        } else if (Intrinsics.areEqual(type, context.getResources().getString(R.string.energy_meter_history))) {
            EnergyMeterHistoryData energyMeterHistoryData = new EnergyMeterHistoryData();
            energyMeterHistoryData.setType(context.getResources().getString(R.string.sleep_score));
            CoveEventBusManager.getInstance().getEventBus().post(energyMeterHistoryData);
        } else {
            EnergyScoreApiCall.INSTANCE.energyScoreBatchApiCall(startCal, endCal, context, type);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void saveSleepScoreDataInDB(@NotNull final SleepScoreApiRes sleepScoreApiRes, @NotNull final Calendar startCal, @NotNull final Calendar endCal, @NotNull final Context context, @NotNull final String type) {
        ArrayList<QuestionAnswerSleepData> feedbackList;
        Context context2 = context;
        Intrinsics.checkNotNullParameter(sleepScoreApiRes, "sleepScoreApiRes");
        Intrinsics.checkNotNullParameter(startCal, "startCal");
        Intrinsics.checkNotNullParameter(endCal, "endCal");
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        int size = sleepScoreApiRes.getData().size();
        int i = 0;
        while (i < size) {
            SleepScoreData sleepScoreData = new SleepScoreData();
            SleepScoreApiRes.Data data = sleepScoreApiRes.getData().get(i);
            String str = f5738a;
            LogsHelper.d(str, "sleepKEy ** " + data.getDate());
            LogsHelper.d(str, "sleepvalue ** " + data.getSleepQuality());
            String date = data.getDate();
            Intrinsics.checkNotNullExpressionValue(date, "sleepData.date");
            sleepScoreData.setDate(date);
            sleepScoreData.setSleepScore(data.getSleepQuality());
            sleepScoreData.setComputedDate(data.getComputedDate());
            sleepScoreData.setAlgoIdentifier(data.getAlgoIdentifier());
            sleepScoreData.setLastSyncedDate(Long.valueOf(System.currentTimeMillis()));
            if (Intrinsics.areEqual(data.getDate(), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), c))) {
                String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), c);
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                SleepScoreData sleepScoreData2 = SleepScoreRepository.Companion.getInstance(context2).getSleepScoreData(formatDate, BleApiManager.getInstance(context).getBleApi().getMacAddress());
                if (sleepScoreData2 != null && (feedbackList = sleepScoreData2.getFeedbackList()) != null && feedbackList.size() > 0) {
                    sleepScoreData.setFeedbackList(sleepScoreData2.getFeedbackList());
                    sleepScoreData.setQuestionnaireId(sleepScoreData2.getQuestionnaireId());
                }
            }
            sleepScoreData.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            if (data.getContributingFactors() != null) {
                sleepScoreData.setWascoCount(data.getContributingFactors().getWaso());
                sleepScoreData.setTimeToDeepSleep(data.getContributingFactors().getTimeToDeepSleep());
                sleepScoreData.setTotalSleep(String.valueOf(data.getContributingFactors().getSleepDuration()));
                sleepScoreData.setSleepConsistency(data.getContributingFactors().getSleepConsistency());
            }
            Integer sleepQuality = data.getSleepQuality();
            if (sleepQuality == null || sleepQuality.intValue() != -1) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                Long insert = SleepScoreRepository.Companion.getInstance(context2).insert(sleepScoreData);
                objectRef.element = insert != null ? Integer.valueOf((int) insert.longValue()) : 0;
                final int i2 = i;
                b.post(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.sleepscore.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        SleepScoreApiCall.c(Ref.ObjectRef.this, i2, sleepScoreApiRes, startCal, endCal, type, context);
                    }
                });
            } else {
                final int i3 = i;
                b.post(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.sleepscore.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        SleepScoreApiCall.d(i3, sleepScoreApiRes, startCal, endCal, type, context);
                    }
                });
            }
            i++;
            context2 = context;
        }
    }

    public final void setDateFormat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        c = str;
    }
}
