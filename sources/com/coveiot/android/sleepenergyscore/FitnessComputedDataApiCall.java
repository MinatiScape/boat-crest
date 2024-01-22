package com.coveiot.android.sleepenergyscore;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesscomputeddataapi.FitnessComputedDataApiManager;
import com.coveiot.coveaccess.fitnesscomputeddataapi.GetFitnessComputedDataResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FitnessComputedDataApiCall {
    @NotNull
    public static final FitnessComputedDataApiCall INSTANCE = new FitnessComputedDataApiCall();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Handler f5691a = new Handler(Looper.getMainLooper());
    @NotNull
    public static String b = "yyyy-MM-dd";
    @NotNull
    public static String c = "yyyy-MM-dd hh:mm a";

    /* JADX WARN: Removed duplicated region for block: B:56:0x01c4  */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void callSaveApi(@org.jetbrains.annotations.NotNull android.content.Context r34, @org.jetbrains.annotations.NotNull final java.lang.String r35, @org.jetbrains.annotations.Nullable final com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest r36) {
        /*
            Method dump skipped, instructions count: 1623
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.FitnessComputedDataApiCall.callSaveApi(android.content.Context, java.lang.String, com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest):void");
    }

    @NotNull
    public final String getDateFormat() {
        return b;
    }

    @NotNull
    public final String getDateFormat1() {
        return c;
    }

    public final void getFitnessComputedDataFromServer(@NotNull String date, @NotNull final Context context) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(context, "context");
        FitnessComputedDataApiManager.getFitnessComputedDataFromServer(date, new CoveApiListener<GetFitnessComputedDataResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.sleepenergyscore.FitnessComputedDataApiCall$getFitnessComputedDataFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFitnessComputedDataResponse getFitnessComputedDataResponse) {
                Intrinsics.checkNotNullParameter(getFitnessComputedDataResponse, "getFitnessComputedDataResponse");
                LogsHelper.d("getfitnesscomputedapi call*** ", new Gson().toJson(getFitnessComputedDataResponse));
                FitnessComputedDataApiCall.INSTANCE.saveDataInDb(getFitnessComputedDataResponse, context);
            }
        });
    }

    @NotNull
    public final Handler getMainHandler() {
        return f5691a;
    }

    public final boolean isFitnessComputedApiCallRequired(@NotNull Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        if (m.equals("prod", "qa", true)) {
            if (UserDataManager.getInstance(mContext).getFitnessComputedApiCallLastUpdatedDate() == null) {
                return true;
            }
            String fitnessComputedApiCallLastUpdatedDate = UserDataManager.getInstance(mContext).getFitnessComputedApiCallLastUpdatedDate();
            Intrinsics.checkNotNullExpressionValue(fitnessComputedApiCallLastUpdatedDate, "getInstance(mContext).ge…dApiCallLastUpdatedDate()");
            Date date = new Date(Long.parseLong(fitnessComputedApiCallLastUpdatedDate));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(c, Locale.ENGLISH);
            String tempDt = simpleDateFormat.format(date);
            String currentDt = simpleDateFormat.format(new Date());
            Intrinsics.checkNotNullExpressionValue(tempDt, "tempDt");
            Intrinsics.checkNotNullExpressionValue(currentDt, "currentDt");
            int timeDifferenceInHours = Utils.getTimeDifferenceInHours(tempDt, currentDt, c);
            System.out.println((Object) ("hoursDiff: " + timeDifferenceInHours));
            if (timeDifferenceInHours >= 1) {
                return true;
            }
        } else if (UserDataManager.getInstance(mContext).getFitnessComputedApiCallLastUpdatedDate() == null) {
            return true;
        } else {
            String fitnessComputedApiCallLastUpdatedDate2 = UserDataManager.getInstance(mContext).getFitnessComputedApiCallLastUpdatedDate();
            Intrinsics.checkNotNullExpressionValue(fitnessComputedApiCallLastUpdatedDate2, "getInstance(mContext).ge…dApiCallLastUpdatedDate()");
            String format = new SimpleDateFormat(b, Locale.ENGLISH).format(new Date(Long.parseLong(fitnessComputedApiCallLastUpdatedDate2)));
            Intrinsics.checkNotNull(format);
            Date parseDate = AppUtils.parseDate(format, b);
            String currentDate = Utils.getCurrentDate();
            Intrinsics.checkNotNull(currentDate);
            if (AppUtils.findDateDifference(parseDate, AppUtils.parseDate(currentDate, b)) >= Integer.parseInt(Constants.FITNESS_COMPUTED_API_CALL_INTERVAL.getValue())) {
                return true;
            }
        }
        return false;
    }

    public final void saveDataInDb(@NotNull GetFitnessComputedDataResponse getFitnessComputedDataResponse, @NotNull Context context) {
        List<GetFitnessComputedDataResponse.Data.FitnessDataComputed> fitnessDataComputed;
        int i;
        int size;
        int size2;
        Intrinsics.checkNotNullParameter(getFitnessComputedDataResponse, "getFitnessComputedDataResponse");
        Intrinsics.checkNotNullParameter(context, "context");
        if (getFitnessComputedDataResponse.getData() == null || (fitnessDataComputed = getFitnessComputedDataResponse.getData().getFitnessDataComputed()) == null || fitnessDataComputed.size() <= 0) {
            return;
        }
        int size3 = fitnessDataComputed.size();
        int i2 = 0;
        while (i2 < size3) {
            if (fitnessDataComputed.get(i2).getEnergy() == null || fitnessDataComputed.get(i2).getEnergy().getLevel() == null || fitnessDataComputed.get(i2).getEnergy().getLevel().getRationale() == null) {
                i = size3;
            } else {
                ArrayList<EnergyData> arrayList = new ArrayList<>();
                EnergyScoreDbData energyScoreDbData = new EnergyScoreDbData();
                EnergyData energyData = new EnergyData();
                EnergyData.ContributingFactors contributingFactors = new EnergyData.ContributingFactors(new EnergyData());
                EnergyData.ContributingFactors.Dock dock = new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData()));
                ArrayList arrayList2 = new ArrayList();
                energyScoreDbData.message = getFitnessComputedDataResponse.getMessage();
                energyScoreDbData.status = getFitnessComputedDataResponse.getStatus();
                if (fitnessDataComputed.get(i2).getEnergy().getLevel().getRationale().getDrains() != null) {
                    List<GetFitnessComputedDataResponse.Data.FitnessDataComputed.Energy.Level.Rationale.Drain> drains = fitnessDataComputed.get(i2).getEnergy().getLevel().getRationale().getDrains();
                    if (drains == null || drains.size() <= 0) {
                        i = size3;
                    } else {
                        int size4 = drains.size();
                        int i3 = 0;
                        while (i3 < size4) {
                            int i4 = size3;
                            int i5 = size4;
                            EnergyData.ContributingFactors.Dock.Session session = new EnergyData.ContributingFactors.Dock.Session(new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData())));
                            session.setContribution(Double.valueOf(drains.get(i3).getValue().intValue()));
                            if (m.equals(drains.get(i3).getType(), context.getResources().getString(R.string.physical_activity_type), true)) {
                                if (drains.get(i3).getActivityId() != null && drains.get(i3).getCategoryId() != null) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(drains.get(i3).getActivityId().intValue());
                                    sb.append(':');
                                    sb.append(drains.get(i3).getCategoryId().intValue());
                                    session.setSessionType(sb.toString());
                                } else {
                                    String type = drains.get(i3).getType();
                                    Intrinsics.checkNotNullExpressionValue(type, "drainDataList[j].type");
                                    String lowerCase = type.toLowerCase();
                                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                                    session.setSessionType(m.capitalize(lowerCase));
                                }
                            } else {
                                String type2 = drains.get(i3).getType();
                                Intrinsics.checkNotNullExpressionValue(type2, "drainDataList[j].type");
                                String lowerCase2 = type2.toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                                session.setSessionType(m.capitalize(lowerCase2));
                            }
                            if (drains.get(i3).getActivityDuration() != null) {
                                Object activityDuration = drains.get(i3).getActivityDuration();
                                Intrinsics.checkNotNull(activityDuration, "null cannot be cast to non-null type kotlin.Double");
                                session.setTotalDuration(Integer.valueOf((int) ((Double) activityDuration).doubleValue()));
                            }
                            arrayList2.add(session);
                            i3++;
                            size3 = i4;
                            size4 = i5;
                        }
                        i = size3;
                        dock.setSessions(arrayList2);
                    }
                    contributingFactors.setDock(dock);
                } else {
                    i = size3;
                }
                if (fitnessDataComputed.get(i2).getEnergy().getLevel().getRationale().getGains() != null) {
                    List<GetFitnessComputedDataResponse.Data.FitnessDataComputed.Energy.Level.Rationale.Gain> gains = fitnessDataComputed.get(i2).getEnergy().getLevel().getRationale().getGains();
                    if (gains.size() > 0) {
                        int size5 = gains.size();
                        for (int i6 = 0; i6 < size5; i6++) {
                            EnergyData.ContributingFactors.Replenish replenish = new EnergyData.ContributingFactors.Replenish(new EnergyData.ContributingFactors(new EnergyData()));
                            replenish.setSleepScoreContribution(Double.valueOf(gains.get(i6).getValue().intValue()));
                            contributingFactors.setReplenish(replenish);
                        }
                    }
                }
                energyData.setContributingFactors(contributingFactors);
                energyData.setDate(fitnessDataComputed.get(i2).getDate());
                if (fitnessDataComputed.get(i2).getEnergy().getLevel() != null) {
                    String computedDate = fitnessDataComputed.get(i2).getEnergy().getLevel().getComputedDate();
                    Intrinsics.checkNotNullExpressionValue(computedDate, "energyDataList[i].energy.level.computedDate");
                    energyData.setComputedDate(m.replace$default(computedDate, ".000Z", "Z", false, 4, (Object) null));
                    energyData.setAlgoIdentifier(fitnessDataComputed.get(i2).getEnergy().getLevel().getSource().getIdentifier());
                }
                if (fitnessDataComputed.get(i2).getEnergy().getLevel().getValues() != null) {
                    List<Integer> values = fitnessDataComputed.get(i2).getEnergy().getLevel().getValues();
                    if (Intrinsics.areEqual(fitnessDataComputed.get(i2).getDate(), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), b))) {
                        String format = new SimpleDateFormat("HH", Locale.ENGLISH).format(new Date());
                        Intrinsics.checkNotNullExpressionValue(format, "format.format(Date())");
                        size = Integer.parseInt(format);
                        size2 = size;
                    } else {
                        size = values.size() - 1;
                        size2 = values.size();
                    }
                    energyData.setCurrentEnergyLevel(values.get(size));
                    LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<>();
                    for (int i7 = 0; i7 < size2; i7++) {
                        if (i7 == 0) {
                            linkedHashMap.put(BleConst.GetTotalActivityData, Double.valueOf(values.get(i7).intValue()));
                        } else {
                            linkedHashMap.put(String.valueOf(i7), Double.valueOf(values.get(i7).intValue()));
                        }
                    }
                    energyData.setHourlyEnergyLevel(linkedHashMap);
                }
                arrayList.add(energyData);
                if (Intrinsics.areEqual(fitnessDataComputed.get(i2).getDate(), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), b)) && fitnessDataComputed.get(i2).getEnergy().getFeedback() != null && fitnessDataComputed.get(i2).getEnergy().getFeedback().getQuestionsAndAnswers() != null) {
                    ArrayList<QuestionAnswerData> arrayList3 = new ArrayList<>();
                    int size6 = fitnessDataComputed.get(i2).getEnergy().getFeedback().getQuestionsAndAnswers().size();
                    for (int i8 = 0; i8 < size6; i8++) {
                        QuestionAnswerData questionAnswerData = new QuestionAnswerData();
                        questionAnswerData.setQuestionId(fitnessDataComputed.get(i2).getEnergy().getFeedback().getQuestionsAndAnswers().get(i8).getQuestionId());
                        questionAnswerData.setAnswerIds(fitnessDataComputed.get(i2).getEnergy().getFeedback().getQuestionsAndAnswers().get(i8).getAnswerIds());
                        questionAnswerData.setUserInput(fitnessDataComputed.get(i2).getEnergy().getFeedback().getQuestionsAndAnswers().get(i8).getUserInput());
                        arrayList3.add(questionAnswerData);
                    }
                    energyScoreDbData.feedbackList = arrayList3;
                    energyScoreDbData.questionnaireId = fitnessDataComputed.get(i2).getEnergy().getFeedback().getQuestionnaireId();
                }
                String date = fitnessDataComputed.get(i2).getDate();
                Intrinsics.checkNotNullExpressionValue(date, "energyDataList[i].date");
                energyScoreDbData.setMDate(date);
                energyScoreDbData.isSyncedWithServer = 1;
                energyScoreDbData.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
                energyScoreDbData.data = arrayList;
                EnergyScoreRepository.Companion.getInstance(context).insert(energyScoreDbData);
            }
            if (fitnessDataComputed.get(i2).getSleep() != null && fitnessDataComputed.get(i2).getSleep().getQuality() != null && fitnessDataComputed.get(i2).getSleep().getQuality().getRationale() != null) {
                SleepScoreData sleepScoreData = new SleepScoreData();
                String date2 = fitnessDataComputed.get(i2).getDate();
                Intrinsics.checkNotNullExpressionValue(date2, "energyDataList[i].date");
                sleepScoreData.setDate(date2);
                sleepScoreData.setSleepScore(fitnessDataComputed.get(i2).getSleep().getQuality().getValue());
                String computedDate2 = fitnessDataComputed.get(i2).getSleep().getQuality().getComputedDate();
                Intrinsics.checkNotNullExpressionValue(computedDate2, "energyDataList[i].sleep.quality.computedDate");
                sleepScoreData.setComputedDate(m.replace$default(computedDate2, ".000Z", "Z", false, 4, (Object) null));
                sleepScoreData.setAlgoIdentifier(fitnessDataComputed.get(i2).getSleep().getQuality().getSource().getIdentifier());
                if (Intrinsics.areEqual(fitnessDataComputed.get(i2).getDate(), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), b)) && fitnessDataComputed.get(i2).getSleep().getFeedback() != null && fitnessDataComputed.get(i2).getSleep().getFeedback().getQuestionsAndAnswers() != null) {
                    ArrayList<QuestionAnswerSleepData> arrayList4 = new ArrayList<>();
                    int size7 = fitnessDataComputed.get(i2).getSleep().getFeedback().getQuestionsAndAnswers().size();
                    for (int i9 = 0; i9 < size7; i9++) {
                        QuestionAnswerSleepData questionAnswerSleepData = new QuestionAnswerSleepData();
                        questionAnswerSleepData.setQuestionId(fitnessDataComputed.get(i2).getSleep().getFeedback().getQuestionsAndAnswers().get(i9).getQuestionId());
                        questionAnswerSleepData.setAnswerIds(fitnessDataComputed.get(i2).getSleep().getFeedback().getQuestionsAndAnswers().get(i9).getAnswerIds());
                        questionAnswerSleepData.setUserInput(fitnessDataComputed.get(i2).getSleep().getFeedback().getQuestionsAndAnswers().get(i9).getUserInput());
                        arrayList4.add(questionAnswerSleepData);
                    }
                    sleepScoreData.setFeedbackList(arrayList4);
                    sleepScoreData.setQuestionnaireId(fitnessDataComputed.get(i2).getSleep().getFeedback().getQuestionnaireId());
                }
                sleepScoreData.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
                sleepScoreData.setWascoCount(fitnessDataComputed.get(i2).getSleep().getQuality().getRationale().getWasoCount());
                sleepScoreData.setTimeToDeepSleep(fitnessDataComputed.get(i2).getSleep().getQuality().getRationale().getTimeToDeepSleep());
                sleepScoreData.setTotalSleep(String.valueOf(fitnessDataComputed.get(i2).getSleep().getQuality().getRationale().getSleepDuration()));
                sleepScoreData.setSleepConsistency(fitnessDataComputed.get(i2).getSleep().getQuality().getRationale().getConsistency());
                Integer sleepScore = sleepScoreData.getSleepScore();
                if (sleepScore == null || sleepScore.intValue() != -1) {
                    SleepScoreRepository.Companion.getInstance(context).insert(sleepScoreData);
                }
            }
            i2++;
            size3 = i;
        }
    }

    public final void saveFeedbackToServer(@NotNull Context context, @Nullable SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest, @NotNull String pageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        FeedbackApiManager.saveFeedbackData(saveFeedbackQuestionarieRequest, new FitnessComputedDataApiCall$saveFeedbackToServer$1(pageType, saveFeedbackQuestionarieRequest, context));
    }

    public final void sendSleepEnergyScoreDataToServer(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        callSaveApi(context, Constants.FITNESS_COMPUTATION.getValue(), null);
    }

    public final void setDateFormat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        b = str;
    }

    public final void setDateFormat1(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        c = str;
    }
}
