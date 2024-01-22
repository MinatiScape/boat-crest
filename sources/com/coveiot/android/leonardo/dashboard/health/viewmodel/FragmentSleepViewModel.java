package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.QuestionsOptionData;
import com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSleepViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4763a;
    @NotNull
    public final MutableLiveData<ArrayList<Integer>> b;
    @NotNull
    public final String c;
    public ContractSleepDashboard contractSleepDashboard;
    @NotNull
    public MutableLiveData<Integer> d;
    @NotNull
    public MutableLiveData<Integer> e;
    public LifecycleOwner mLifecycleOwner;
    public ContractFeedBackQuestionsList questionsList;

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepViewModel$getDailySleepData$1", f = "FragmentSleepViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes3.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $date;
        public final /* synthetic */ boolean $isCurrentDay;
        public int label;
        public final /* synthetic */ FragmentSleepViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Calendar calendar, FragmentSleepViewModel fragmentSleepViewModel, boolean z, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$date = calendar;
            this.this$0 = fragmentSleepViewModel;
            this.$isCurrentDay = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$date, this.this$0, this.$isCurrentDay, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Calendar calendar = this.$date;
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.set(13, 0);
                List<SleepDataModelForLastNight> lastNignthSleepDataWithOutLiveData = SleepRepository.Companion.getInstance(this.this$0.getContext()).getLastNignthSleepDataWithOutLiveData(this.$date, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
                if (lastNignthSleepDataWithOutLiveData != null) {
                    SleepDataModel sleepDataModel = SleepDataHelper.getSleepDataModel(this.this$0.getContext(), lastNignthSleepDataWithOutLiveData);
                    if (this.$isCurrentDay) {
                        this.this$0.getCurrentDaySleepData().postValue(Boxing.boxInt(sleepDataModel != null ? sleepDataModel.getCountTotalSleep() : 0));
                    } else {
                        this.this$0.getPreviousDaySleepData().postValue(Boxing.boxInt(sleepDataModel != null ? sleepDataModel.getCountTotalSleep() : 0));
                    }
                } else if (this.$isCurrentDay) {
                    this.this$0.getCurrentDaySleepData().postValue(Boxing.boxInt(0));
                } else {
                    this.this$0.getPreviousDaySleepData().postValue(Boxing.boxInt(0));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public FragmentSleepViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4763a = context;
        this.b = new MutableLiveData<>();
        this.c = "FragmentSleepViewModel";
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f4763a;
    }

    @NotNull
    public final ContractSleepDashboard getContractSleepDashboard$app_prodRelease() {
        ContractSleepDashboard contractSleepDashboard = this.contractSleepDashboard;
        if (contractSleepDashboard != null) {
            return contractSleepDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractSleepDashboard");
        return null;
    }

    @NotNull
    public final MutableLiveData<Integer> getCurrentDaySleepData() {
        return this.d;
    }

    public final void getDailySleepData(@NotNull Calendar date, boolean z) {
        Intrinsics.checkNotNullParameter(date, "date");
        e.e(ViewModelKt.getViewModelScope(this), null, null, new a(date, this, z, null), 3, null);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void getFeedbackQuestionnarieList() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        FeedbackApiManager.getFeedbackQuestionnaireList("SLEEP_COMPUTATION", new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepViewModel$getFeedbackQuestionnarieList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                String str;
                Intrinsics.checkNotNullParameter(object, "object");
                str = FragmentSleepViewModel.this.c;
                Log.d(str, "feedbacklisterror: " + object.getMsg());
                FragmentSleepViewModel.this.getQuestionsList$app_prodRelease().onReceiveQuestionList(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFeedbackListResponse getFeedbackListResponse) {
                String str;
                ArrayList<QuestionAnswerSleepData> feedbackList;
                Intrinsics.checkNotNullParameter(getFeedbackListResponse, "getFeedbackListResponse");
                str = FragmentSleepViewModel.this.c;
                Log.d(str, "FeedbacklistsleepResponse: " + new Gson().toJson(getFeedbackListResponse));
                String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                SleepScoreData sleepScoreData = SleepScoreRepository.Companion.getInstance(FragmentSleepViewModel.this.getContext()).getSleepScoreData(formatDate, BleApiManager.getInstance(FragmentSleepViewModel.this.getContext()).getBleApi().getMacAddress());
                ArrayList arrayList = new ArrayList();
                if (sleepScoreData != null && (feedbackList = sleepScoreData.getFeedbackList()) != null && feedbackList.size() > 0) {
                    int size = feedbackList.size();
                    for (int i = 0; i < size; i++) {
                        String questionId = feedbackList.get(i).getQuestionId();
                        Intrinsics.checkNotNull(questionId);
                        arrayList.add(questionId);
                    }
                }
                int size2 = getFeedbackListResponse.getData().getQuestionnaire().size();
                for (int i2 = 0; i2 < size2; i2++) {
                    FeedbackQuetionnarieModel feedbackQuetionnarieModel = new FeedbackQuetionnarieModel();
                    feedbackQuetionnarieModel.setCreatedDate(getFeedbackListResponse.getData().getCreatedDate());
                    feedbackQuetionnarieModel.setSubject(getFeedbackListResponse.getData().getSubject());
                    feedbackQuetionnarieModel.setQuestionnaireId(getFeedbackListResponse.getData().getQuestionnaireId());
                    feedbackQuetionnarieModel.setQuestionId(getFeedbackListResponse.getData().getQuestionnaire().get(i2).getQuestionId());
                    feedbackQuetionnarieModel.setText(getFeedbackListResponse.getData().getQuestionnaire().get(i2).getType());
                    feedbackQuetionnarieModel.setText(getFeedbackListResponse.getData().getQuestionnaire().get(i2).getQuestion().getText());
                    FragmentSleepViewModel fragmentSleepViewModel = FragmentSleepViewModel.this;
                    List<GetFeedbackListResponse.Data.Questionnaire.Question.Option> options = getFeedbackListResponse.getData().getQuestionnaire().get(i2).getQuestion().getOptions();
                    Intrinsics.checkNotNullExpressionValue(options, "getFeedbackListResponse.…naire[i].question.options");
                    feedbackQuetionnarieModel.setOptions(fragmentSleepViewModel.getOptionList(options));
                    objectRef.element.add(feedbackQuetionnarieModel);
                    if (arrayList.size() > 0 && arrayList.contains(feedbackQuetionnarieModel.getQuestionId())) {
                        objectRef.element.remove(feedbackQuetionnarieModel);
                    }
                }
                ArrayList<FeedbackQuetionnarieModel> arrayList2 = objectRef.element;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    FragmentSleepViewModel.this.getQuestionsList$app_prodRelease().onReceiveQuestionList(objectRef.element);
                } else {
                    FragmentSleepViewModel.this.getQuestionsList$app_prodRelease().onReceiveQuestionList(null);
                }
            }
        });
    }

    @NotNull
    public final LifecycleOwner getMLifecycleOwner() {
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
        if (lifecycleOwner != null) {
            return lifecycleOwner;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLifecycleOwner");
        return null;
    }

    @NotNull
    public final ArrayList<QuestionsOptionData> getOptionList(@NotNull List<GetFeedbackListResponse.Data.Questionnaire.Question.Option> options) {
        Intrinsics.checkNotNullParameter(options, "options");
        ArrayList<QuestionsOptionData> arrayList = new ArrayList<>();
        int size = options.size();
        for (int i = 0; i < size; i++) {
            QuestionsOptionData questionsOptionData = new QuestionsOptionData();
            questionsOptionData.setActiveIconUrl(options.get(i).getActiveIconUrl());
            questionsOptionData.setIconUrl(options.get(i).getIconUrl());
            questionsOptionData.setInactiveIconUrl(options.get(i).getInactiveIconUrl());
            questionsOptionData.setOptionId(options.get(i).getOptionId());
            questionsOptionData.setText(options.get(i).getText());
            arrayList.add(questionsOptionData);
        }
        return arrayList;
    }

    @NotNull
    public final MutableLiveData<Integer> getPreviousDaySleepData() {
        return this.e;
    }

    @NotNull
    public final ContractFeedBackQuestionsList getQuestionsList$app_prodRelease() {
        ContractFeedBackQuestionsList contractFeedBackQuestionsList = this.questionsList;
        if (contractFeedBackQuestionsList != null) {
            return contractFeedBackQuestionsList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("questionsList");
        return null;
    }

    @NotNull
    public final MutableLiveData<ArrayList<Integer>> getSleepHourList() {
        return this.b;
    }

    public final void load24Hours() {
        if (this.b.getValue() == null) {
            this.b.setValue(new ArrayList<>());
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            ArrayList<Integer> value = this.b.getValue();
            Intrinsics.checkNotNull(value);
            value.add(Integer.valueOf(i));
            if (i2 > 24) {
                MutableLiveData<ArrayList<Integer>> mutableLiveData = this.b;
                mutableLiveData.postValue(mutableLiveData.getValue());
                return;
            }
            i = i2;
        }
    }

    public final void loadDailyData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar.getInstance().setTime(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public final void loadHourlyData(@NotNull Calendar date) {
        LiveData<List<SleepDataModelForLastNight>> hourlyDataWithoutFlowValidator;
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(date.getTime());
            Intrinsics.checkNotNullExpressionValue(simpleDateFormat.format(calendar.getTime()), "simpleDateFormat.format(calendar.time)");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (getContractSleepDashboard$app_prodRelease().isSyncInProgress()) {
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            hourlyDataWithoutFlowValidator = SleepRepository.Companion.getInstance(this.f4763a).getHourlyDataWithoutFlowValidator(calendar, BleApiManager.getInstance(this.f4763a).getBleApi().getMacAddress());
        } else {
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            hourlyDataWithoutFlowValidator = SleepRepository.Companion.getInstance(this.f4763a).getHourlyDataWithoutFlowValidator(calendar, BleApiManager.getInstance(this.f4763a).getBleApi().getMacAddress());
        }
        hourlyDataWithoutFlowValidator.observe(getMLifecycleOwner(), new Observer<List<? extends SleepDataModelForLastNight>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepViewModel$loadHourlyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@javax.annotation.Nullable @NotNull List<? extends SleepDataModelForLastNight> hourlyWalkDataList) {
                Intrinsics.checkNotNullParameter(hourlyWalkDataList, "hourlyWalkDataList");
                if (!AppUtils.isEmpty(hourlyWalkDataList)) {
                    FragmentSleepViewModel.this.getContractSleepDashboard$app_prodRelease().updateHourlyLevelData(hourlyWalkDataList);
                } else {
                    FragmentSleepViewModel.this.getContractSleepDashboard$app_prodRelease().updateHourlyLevelData(null);
                }
            }
        });
    }

    @NotNull
    public final String loadSelectedDAteRange(@NotNull SleepData sleepData) {
        Intrinsics.checkNotNullParameter(sleepData, "sleepData");
        String str = "";
        if (m.equals(sleepData.getType(), "Day", false)) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            try {
                String format = simpleDateFormat2.format(simpleDateFormat.parse(sleepData.getDwmValue()));
                Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…sleepData.getDwmValue()))");
                str = format;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (m.equals(simpleDateFormat.format(calendar.getTime()), sleepData.getDwmValue(), true)) {
                str = this.f4763a.getResources().getString(R.string.last_night);
                Intrinsics.checkNotNullExpressionValue(str, "context.resources.getString(R.string.last_night)");
            }
            calendar.add(5, -1);
            if (m.equals(simpleDateFormat.format(calendar.getTime()), sleepData.getDwmValue(), true)) {
                String string = this.f4763a.getResources().getString(R.string.yesterday);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.yesterday)");
                return string;
            }
            return str;
        }
        return "";
    }

    public final void setContractSleepDashboard$app_prodRelease(@NotNull ContractSleepDashboard contractSleepDashboard) {
        Intrinsics.checkNotNullParameter(contractSleepDashboard, "<set-?>");
        this.contractSleepDashboard = contractSleepDashboard;
    }

    public final void setCurrentDaySleepData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }

    public final void setPreviousDaySleepData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setQuestionsList$app_prodRelease(@NotNull ContractFeedBackQuestionsList contractFeedBackQuestionsList) {
        Intrinsics.checkNotNullParameter(contractFeedBackQuestionsList, "<set-?>");
        this.questionsList = contractFeedBackQuestionsList;
    }
}
