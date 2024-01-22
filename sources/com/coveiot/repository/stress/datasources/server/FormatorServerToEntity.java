package com.coveiot.repository.stress.datasources.server;

import com.coveiot.coveaccess.model.server.CodecBean;
import com.coveiot.coveaccess.model.server.LogsBean;
import com.coveiot.coveaccess.model.server.NormalBean;
import com.coveiot.coveaccess.model.server.PeriodicStressData;
import com.coveiot.coveaccess.model.server.RelaxBean;
import com.coveiot.coveaccess.model.server.TimeLogBean;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
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
        public final PeriodicStressData getActivityDataModel(@NotNull DailyStress dailyStress, @NotNull List<? extends HourlyStress> stressHourlyData) {
            Intrinsics.checkNotNullParameter(dailyStress, "dailyStress");
            Intrinsics.checkNotNullParameter(stressHourlyData, "stressHourlyData");
            PeriodicStressData periodicStressData = new PeriodicStressData();
            periodicStressData.setDate(dailyStress.mDate);
            periodicStressData.setType("STRESS");
            periodicStressData.setBaseUnit("UNITLESS");
            periodicStressData.setMax(Integer.valueOf(dailyStress.stress_high));
            periodicStressData.setMin(Integer.valueOf(dailyStress.stress_low));
            periodicStressData.setAvg(Integer.valueOf((int) dailyStress.stress_avg));
            periodicStressData.setBaseline(Integer.valueOf((int) dailyStress.baselinne));
            periodicStressData.setReadiness(Integer.valueOf((int) dailyStress.readiness));
            TimeLogBean timeLogBean = new TimeLogBean();
            ArrayList arrayList = new ArrayList();
            for (HourlyStress hourlyStress : stressHourlyData) {
                LogsBean logsBean = new LogsBean();
                logsBean.setStartTime(hourlyStress.getStartTime());
                logsBean.setEndTime(hourlyStress.getEndTime());
                logsBean.setMax(Integer.valueOf(hourlyStress.stress_high));
                logsBean.setMin(Integer.valueOf(hourlyStress.stress_low));
                logsBean.setAvg(Integer.valueOf((int) hourlyStress.stress_avg));
                logsBean.setCodedValues(hourlyStress.getCodevalue());
                arrayList.add(logsBean);
            }
            timeLogBean.setLogs(arrayList);
            periodicStressData.setTimeLog(timeLogBean);
            CodecBean codecBean = new CodecBean();
            RelaxBean relaxBean = new RelaxBean();
            relaxBean.setGte(0);
            relaxBean.setLte(24);
            codecBean.setRelax(relaxBean);
            NormalBean normalBean = new NormalBean();
            normalBean.setLte(50);
            normalBean.setGt(24);
            codecBean.setNormal(normalBean);
            NormalBean normalBean2 = new NormalBean();
            normalBean2.setLte(75);
            normalBean2.setGt(50);
            codecBean.setModerate(normalBean2);
            NormalBean normalBean3 = new NormalBean();
            normalBean3.setLte(100);
            normalBean3.setGt(75);
            codecBean.setOverstress(normalBean3);
            periodicStressData.setCodec(codecBean);
            return periodicStressData;
        }

        @NotNull
        public final DailyStress getDailyStressData(@NotNull PeriodicStressData periodicStressData) {
            Intrinsics.checkNotNullParameter(periodicStressData, "periodicStressData");
            DailyStress dailyStress = new DailyStress();
            dailyStress.mDate = periodicStressData.getDate();
            Integer max = periodicStressData.getMax();
            Intrinsics.checkNotNullExpressionValue(max, "periodicStressData.max");
            dailyStress.stress_high = max.intValue();
            Integer min = periodicStressData.getMin();
            Intrinsics.checkNotNullExpressionValue(min, "periodicStressData.min");
            dailyStress.stress_low = min.intValue();
            dailyStress.stress_avg = periodicStressData.getAvg().intValue();
            if (periodicStressData.getBaseline() != null) {
                dailyStress.baselinne = periodicStressData.getBaseline().intValue();
            }
            if (periodicStressData.getFeedback() != null && !AppUtils.isEmpty(periodicStressData.getFeedback().getQuestionsAndAnswers())) {
                ArrayList arrayList = new ArrayList();
                for (PeriodicStressData.Feedback.QuestionsAndAnswers questionsAndAnswers : periodicStressData.getFeedback().getQuestionsAndAnswers()) {
                    SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer = new SaveFeedbackQuestionarieRequest.QuestionsAndAnswer();
                    questionsAndAnswer.setQuestionId(questionsAndAnswers.getQuestionId());
                    questionsAndAnswer.setUserInput(questionsAndAnswers.getUserInput());
                    questionsAndAnswer.setAnswerIds(questionsAndAnswers.getAnswerIds());
                    arrayList.add(new Gson().toJson(questionsAndAnswer));
                }
                dailyStress.AnsweredQuestions_FeedBack = arrayList;
            }
            if (periodicStressData.getReadiness() != null) {
                dailyStress.readiness = periodicStressData.getReadiness().intValue();
            }
            dailyStress.is_sync_server = true;
            return dailyStress;
        }

        @NotNull
        public final List<HourlyStress> getHourlyStressData(@NotNull PeriodicStressData periodicStressData) {
            Intrinsics.checkNotNullParameter(periodicStressData, "periodicStressData");
            ArrayList arrayList = new ArrayList();
            if (periodicStressData.getTimeLog() != null && !RepositoryUtils.isEmpty(periodicStressData.getTimeLog().getLogs())) {
                for (LogsBean logsBean : periodicStressData.getTimeLog().getLogs()) {
                    HourlyStress hourlyStress = new HourlyStress();
                    hourlyStress.setStartTime(logsBean.getStartTime());
                    hourlyStress.setEndTime(logsBean.getEndTime());
                    Integer max = logsBean.getMax();
                    Intrinsics.checkNotNullExpressionValue(max, "activityLog.max");
                    hourlyStress.stress_high = max.intValue();
                    Integer min = logsBean.getMin();
                    Intrinsics.checkNotNullExpressionValue(min, "activityLog.min");
                    hourlyStress.stress_low = min.intValue();
                    hourlyStress.stress_avg = logsBean.getAvg().intValue();
                    hourlyStress.mDate = periodicStressData.getDate();
                    hourlyStress.setCodevalue((ArrayList) logsBean.getCodedValues());
                    arrayList.add(hourlyStress);
                }
            }
            return arrayList;
        }
    }
}
