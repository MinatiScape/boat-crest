package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.feedback.ContractGenericFeedBackQuestionsList;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicStressDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.DataBean;
import com.coveiot.android.leonardo.dashboard.health.model.StressPercentage;
import com.coveiot.android.leonardo.dashboard.model.StressInsightModel;
import com.coveiot.android.leonardo.utils.FeedBackPageType;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.QuestionsOptionData;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.stress.StressRepository;
import com.coveiot.repository.stress.datasources.db.read.StressDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.s;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentStressPeriodicViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4768a;
    @NotNull
    public final String b;
    public boolean c;
    public ContractGenericFeedBackQuestionsList contractGenericFeedBackQuestionsList;
    public ContractPeriodicStressDashboard contractPeriodicStressDashboard;
    @NotNull
    public MutableLiveData<StressInsightModel> d;
    public LifecycleOwner mLifecycleOwner;

    public FragmentStressPeriodicViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4768a = context;
        String simpleName = FragmentStressPeriodicViewModel.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        this.b = simpleName;
        this.d = new MutableLiveData<>();
    }

    public final int c(List<Integer> list) {
        int i = 0;
        for (Integer num : list) {
            if (num.intValue() > 0) {
                i++;
            }
        }
        return i;
    }

    public final StressPercentage d(List<? extends HourlyStress> list) {
        StressPercentage stressPercentage = new StressPercentage();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int i = 0;
        for (HourlyStress hourlyStress : list) {
            List<Integer> codevalue = hourlyStress.getCodevalue();
            Intrinsics.checkNotNullExpressionValue(codevalue, "data.codevalue");
            i += c(codevalue);
            List<Integer> codevalue2 = hourlyStress.getCodevalue();
            Intrinsics.checkNotNullExpressionValue(codevalue2, "data.codevalue");
            e(codevalue2, hashMap);
        }
        if (i > 0) {
            ArrayList arrayList = new ArrayList();
            Set<String> keySet = hashMap.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "stressStateCountHHashMap.keys");
            for (String key : CollectionsKt___CollectionsKt.toList(keySet)) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                arrayList.add(new DataBean(PayUtils.getStressIndexExcludingZero(key), (((Number) s.getValue(hashMap, key)).floatValue() / i) * 100.0f, key));
            }
            if (arrayList.size() > 0) {
                Collections.sort(arrayList, new StressModelComparator());
                Object obj = arrayList.get(0);
                Intrinsics.checkNotNullExpressionValue(obj, "dataBeanList[0]");
                stressPercentage.setList(CollectionsKt__CollectionsKt.arrayListOf((DataBean) obj));
            }
        }
        return stressPercentage;
    }

    public final void e(List<Integer> list, HashMap<String, Integer> hashMap) {
        for (Integer num : list) {
            String stressRangeExcludingZero = PayUtils.getStressRangeExcludingZero(num.intValue(), this.f4768a);
            if (stressRangeExcludingZero != null) {
                if (hashMap.containsKey(stressRangeExcludingZero)) {
                    Integer num2 = hashMap.get(stressRangeExcludingZero);
                    Intrinsics.checkNotNull(num2);
                    hashMap.put(stressRangeExcludingZero, Integer.valueOf(num2.intValue() + 1));
                } else {
                    hashMap.put(stressRangeExcludingZero, 1);
                }
            }
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4768a;
    }

    @NotNull
    public final ContractGenericFeedBackQuestionsList getContractGenericFeedBackQuestionsList$app_prodRelease() {
        ContractGenericFeedBackQuestionsList contractGenericFeedBackQuestionsList = this.contractGenericFeedBackQuestionsList;
        if (contractGenericFeedBackQuestionsList != null) {
            return contractGenericFeedBackQuestionsList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractGenericFeedBackQuestionsList");
        return null;
    }

    @NotNull
    public final ContractPeriodicStressDashboard getContractPeriodicStressDashboard$app_prodRelease() {
        ContractPeriodicStressDashboard contractPeriodicStressDashboard = this.contractPeriodicStressDashboard;
        if (contractPeriodicStressDashboard != null) {
            return contractPeriodicStressDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractPeriodicStressDashboard");
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void getFeedbackQuestionnarieList() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        FeedbackApiManager.getFeedbackQuestionnaireList(FeedBackPageType.Companion.getSTRESS_DAILY_DATA(), new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressPeriodicViewModel$getFeedbackQuestionnarieList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                String str;
                Intrinsics.checkNotNullParameter(object, "object");
                str = FragmentStressPeriodicViewModel.this.b;
                Log.d(str, "feedbacklisterror: " + object.getMsg());
                FragmentStressPeriodicViewModel.this.getContractGenericFeedBackQuestionsList$app_prodRelease().onReceiveQuestionList(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFeedbackListResponse getFeedbackListResponse) {
                String str;
                List<String> list;
                Intrinsics.checkNotNullParameter(getFeedbackListResponse, "getFeedbackListResponse");
                str = FragmentStressPeriodicViewModel.this.b;
                Log.d(str, "FeedbacklistsleepResponse: " + new Gson().toJson(getFeedbackListResponse));
                DailyStress dailyStressData = StressDBRead.getInstance(FragmentStressPeriodicViewModel.this.getContext()).getDailyStressData(AppUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"), BleApiManager.getInstance(FragmentStressPeriodicViewModel.this.getContext()).getBleApi().getMacAddress());
                ArrayList arrayList = new ArrayList();
                if (dailyStressData != null && (list = dailyStressData.AnsweredQuestions_FeedBack) != null) {
                    Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.intValue() > 0) {
                        int size = dailyStressData.AnsweredQuestions_FeedBack.size();
                        for (int i = 0; i < size; i++) {
                            SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer = (SaveFeedbackQuestionarieRequest.QuestionsAndAnswer) new Gson().fromJson(dailyStressData.AnsweredQuestions_FeedBack.get(i), (Class<Object>) SaveFeedbackQuestionarieRequest.QuestionsAndAnswer.class);
                            if (questionsAndAnswer != null) {
                                arrayList.add(questionsAndAnswer.getQuestionId());
                            }
                        }
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
                    FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = FragmentStressPeriodicViewModel.this;
                    List<GetFeedbackListResponse.Data.Questionnaire.Question.Option> options = getFeedbackListResponse.getData().getQuestionnaire().get(i2).getQuestion().getOptions();
                    Intrinsics.checkNotNullExpressionValue(options, "getFeedbackListResponse.…naire[i].question.options");
                    feedbackQuetionnarieModel.setOptions(fragmentStressPeriodicViewModel.getOptionList(options));
                    objectRef.element.add(feedbackQuetionnarieModel);
                    if (arrayList.size() > 0 && arrayList.contains(feedbackQuetionnarieModel.getQuestionId())) {
                        objectRef.element.remove(feedbackQuetionnarieModel);
                    }
                }
                ArrayList<FeedbackQuetionnarieModel> arrayList2 = objectRef.element;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    FragmentStressPeriodicViewModel.this.getContractGenericFeedBackQuestionsList$app_prodRelease().onReceiveQuestionList(objectRef.element);
                } else {
                    FragmentStressPeriodicViewModel.this.getContractGenericFeedBackQuestionsList$app_prodRelease().onReceiveQuestionList(null);
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
    public final MutableLiveData<StressInsightModel> getStressInsightData() {
        return this.d;
    }

    public final boolean isWeekSelected() {
        return this.c;
    }

    public final void loadDailyData(@NotNull Calendar date) {
        LiveData<DailyStress> dailyDataWithoutFlowValidator;
        LiveData<DailyStress> dailyDataWithoutFlowValidator2;
        Intrinsics.checkNotNullParameter(date, "date");
        final Ref.IntRef intRef = new Ref.IntRef();
        if (getContractPeriodicStressDashboard$app_prodRelease().isSyncInProgress()) {
            dailyDataWithoutFlowValidator = StressRepository.Companion.getInstance(this.f4768a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4768a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator = StressRepository.Companion.getInstance(this.f4768a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4768a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator.observe(getMLifecycleOwner(), new Observer<DailyStress>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressPeriodicViewModel$loadDailyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable @org.jetbrains.annotations.Nullable DailyStress dailyStress) {
                if ((dailyStress != null ? Double.valueOf(dailyStress.stress_avg) : null) != null) {
                    Ref.IntRef intRef2 = Ref.IntRef.this;
                    Double valueOf = dailyStress != null ? Double.valueOf(dailyStress.stress_avg) : null;
                    Intrinsics.checkNotNull(valueOf);
                    intRef2.element = (int) valueOf.doubleValue();
                }
                this.getContractPeriodicStressDashboard$app_prodRelease().updateDailyLevelData(dailyStress);
            }
        });
        Calendar previousDate = ThemesUtils.INSTANCE.getPreviousDate(date, -1);
        if (getContractPeriodicStressDashboard$app_prodRelease().isSyncInProgress()) {
            dailyDataWithoutFlowValidator2 = StressRepository.Companion.getInstance(this.f4768a).getDailyDataWithoutFlowValidator(previousDate, BleApiManager.getInstance(this.f4768a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator2 = StressRepository.Companion.getInstance(this.f4768a).getDailyDataWithoutFlowValidator(previousDate, BleApiManager.getInstance(this.f4768a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator2.observe(getMLifecycleOwner(), new FragmentStressPeriodicViewModel$loadDailyData$2(intRef, this));
    }

    public final void loadHourlyStressData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        StressRepository.Companion.getInstance(this.f4768a).getHourlyData(date, BleApiManager.getInstance(this.f4768a).getBleApi().getMacAddress()).observe(getMLifecycleOwner(), new Observer<List<? extends HourlyStress>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressPeriodicViewModel$loadHourlyStressData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@NotNull List<? extends HourlyStress> hourlyDataList) {
                StressPercentage d;
                List emptyList;
                boolean z;
                Intrinsics.checkNotNullParameter(hourlyDataList, "hourlyDataList");
                if (!AppUtils.isEmpty(hourlyDataList)) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList<BarEntry> arrayList2 = new ArrayList<>();
                    for (int i = 0; i < 24; i++) {
                        arrayList.add(PayUtils.getAmPmHourValue(String.valueOf(i)));
                        arrayList2.add(new BarEntry(i, 0.0f));
                    }
                    boolean z2 = false;
                    for (HourlyStress hourlyStress : hourlyDataList) {
                        String startTime = hourlyStress.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "hourlyStressD.startTime");
                        List<String> split = new Regex(":").split(startTime, 0);
                        if (!split.isEmpty()) {
                            ListIterator<String> listIterator = split.listIterator(split.size());
                            while (listIterator.hasPrevious()) {
                                if (listIterator.previous().length() == 0) {
                                    z = true;
                                    continue;
                                } else {
                                    z = false;
                                    continue;
                                }
                                if (!z) {
                                    emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                                    break;
                                }
                            }
                        }
                        emptyList = CollectionsKt__CollectionsKt.emptyList();
                        String[] strArr = (String[]) emptyList.toArray(new String[0]);
                        if (!(strArr.length == 0)) {
                            try {
                                int parseInt = Integer.parseInt(strArr[0]);
                                float f = (float) hourlyStress.stress_avg;
                                if (f > 0.0f) {
                                    z2 = true;
                                }
                                arrayList2.set(parseInt, new BarEntry(parseInt, f));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (z2) {
                        FragmentStressPeriodicViewModel.this.getContractPeriodicStressDashboard$app_prodRelease().updateHourlyLevelData(arrayList2, arrayList);
                        ContractPeriodicStressDashboard contractPeriodicStressDashboard$app_prodRelease = FragmentStressPeriodicViewModel.this.getContractPeriodicStressDashboard$app_prodRelease();
                        d = FragmentStressPeriodicViewModel.this.d(hourlyDataList);
                        contractPeriodicStressDashboard$app_prodRelease.updateStressPercentage(d);
                        return;
                    }
                    FragmentStressPeriodicViewModel.this.getContractPeriodicStressDashboard$app_prodRelease().updateHourlyLevelData(null, null);
                    FragmentStressPeriodicViewModel.this.getContractPeriodicStressDashboard$app_prodRelease().updateStressPercentage(null);
                    return;
                }
                FragmentStressPeriodicViewModel.this.getContractPeriodicStressDashboard$app_prodRelease().updateHourlyLevelData(null, null);
                FragmentStressPeriodicViewModel.this.getContractPeriodicStressDashboard$app_prodRelease().updateStressPercentage(null);
            }
        });
    }

    public final void selectWeeklyView(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String formatDate = RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd");
        String formatDate2 = RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        String formatDate3 = RepositoryUtils.formatDate(themesUtils.getPreviousDate(fromDate, this.c ? -6 : -30).getTime(), "yyyy-MM-dd");
        String formatDate4 = RepositoryUtils.formatDate(themesUtils.getPreviousDate(toDate, this.c ? -6 : -30).getTime(), "yyyy-MM-dd");
        List<DailyStress> rangeStressData = StressDBRead.getInstance(this.f4768a).getDailyStressDataByStartAndEndDate(BleApiManager.getInstance(this.f4768a).getBleApi().getMacAddress(), formatDate, formatDate2);
        List<DailyStress> dailyStressDataByStartAndEndDate = StressDBRead.getInstance(this.f4768a).getDailyStressDataByStartAndEndDate(BleApiManager.getInstance(this.f4768a).getBleApi().getMacAddress(), formatDate3, formatDate4);
        if (!AppUtils.isEmpty(rangeStressData)) {
            Iterator<DailyStress> it = rangeStressData.iterator();
            while (it.hasNext()) {
                DailyStress next = it.next();
                if ((next != null ? Double.valueOf(next.stress_avg) : null) != null) {
                    double d = next.stress_avg;
                    if (d > 0.0d) {
                        arrayList.add(Integer.valueOf(kotlin.math.c.roundToInt(d)));
                    }
                }
            }
            i = BleApiUtils.INSTANCE.getAverageValue(arrayList);
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd/MM");
            ArrayList<String> arrayList3 = new ArrayList<>();
            ArrayList<BarEntry> arrayList4 = new ArrayList<>();
            int size = rangeStressData.size();
            for (int i3 = 0; i3 < size; i3++) {
                float f = i3;
                DailyStress dailyStress = rangeStressData.get(i3);
                arrayList4.add(new BarEntry(f, dailyStress != null ? (float) dailyStress.stress_avg : 0.0f));
                try {
                    arrayList3.add(simpleDateFormat2.format(simpleDateFormat.parse(rangeStressData.get(i3).mDate)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            getContractPeriodicStressDashboard$app_prodRelease().updateHourlyLevelData(arrayList4, arrayList3);
            ContractPeriodicStressDashboard contractPeriodicStressDashboard$app_prodRelease = getContractPeriodicStressDashboard$app_prodRelease();
            Intrinsics.checkNotNullExpressionValue(rangeStressData, "rangeStressData");
            contractPeriodicStressDashboard$app_prodRelease.updateRangeLevelData(rangeStressData);
        } else {
            getContractPeriodicStressDashboard$app_prodRelease().updateHourlyLevelData(null, null);
            getContractPeriodicStressDashboard$app_prodRelease().updateRangeLevelData(CollectionsKt__CollectionsKt.emptyList());
            i = 0;
        }
        if (AppUtils.isEmpty(dailyStressDataByStartAndEndDate)) {
            i2 = 0;
        } else {
            Iterator<DailyStress> it2 = dailyStressDataByStartAndEndDate.iterator();
            while (it2.hasNext()) {
                DailyStress next2 = it2.next();
                if ((next2 != null ? Double.valueOf(next2.stress_avg) : null) != null) {
                    double d2 = next2.stress_avg;
                    if (d2 > 0.0d) {
                        arrayList2.add(Integer.valueOf(kotlin.math.c.roundToInt(d2)));
                    }
                }
            }
            i2 = BleApiUtils.INSTANCE.getAverageValue(arrayList2);
        }
        StressInsightModel stressInsightModel = new StressInsightModel(null, false, 3, null);
        stressInsightModel.setStressDifference(Integer.valueOf(Math.abs(i - i2)));
        stressInsightModel.setStressIncreased(i > i2);
        this.d.postValue(stressInsightModel);
    }

    public final void setContractGenericFeedBackQuestionsList$app_prodRelease(@NotNull ContractGenericFeedBackQuestionsList contractGenericFeedBackQuestionsList) {
        Intrinsics.checkNotNullParameter(contractGenericFeedBackQuestionsList, "<set-?>");
        this.contractGenericFeedBackQuestionsList = contractGenericFeedBackQuestionsList;
    }

    public final void setContractPeriodicStressDashboard$app_prodRelease(@NotNull ContractPeriodicStressDashboard contractPeriodicStressDashboard) {
        Intrinsics.checkNotNullParameter(contractPeriodicStressDashboard, "<set-?>");
        this.contractPeriodicStressDashboard = contractPeriodicStressDashboard;
    }

    public final void setIsWeekSelected(boolean z) {
        this.c = z;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }

    public final void setStressInsightData(@NotNull MutableLiveData<StressInsightModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setWeekSelected(boolean z) {
        this.c = z;
    }
}
