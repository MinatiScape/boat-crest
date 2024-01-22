package com.coveiot.android.sleepenergyscore.energymeter.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData;
import com.coveiot.android.sleepenergyscore.energymeter.listener.ContractEnergyMeterDashBoard;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.QuestionsOptionData;
import com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.c;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentEnergyMeterViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5730a;
    @NotNull
    public MutableLiveData<Integer> b;
    @NotNull
    public final String c;
    public ContractEnergyMeterDashBoard contractEnergyMeterDashBoard;
    @NotNull
    public String d;
    public LifecycleOwner mLifecycleOwner;
    public ContractFeedBackQuestionsList questionsList;

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModel$loadEnergyData$1", f = "FragmentEnergyMeterViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $selectedDate;
        private /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$selectedDate = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.$selectedDate, continuation);
            aVar.L$0 = obj;
            return aVar;
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
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                String selectedDate = this.$selectedDate;
                Intrinsics.checkNotNullExpressionValue(selectedDate, "selectedDate");
                EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(FragmentEnergyMeterViewModel.this.getContext()).getEnergyScoreData(selectedDate, BleApiManager.getInstance(FragmentEnergyMeterViewModel.this.getContext()).getBleApi().getMacAddress());
                Unit unit = null;
                if (energyScoreData != null) {
                    FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel = FragmentEnergyMeterViewModel.this;
                    String selectedDate2 = this.$selectedDate;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    LogsHelper.d("energyRes*** ", new Gson().toJson(energyScoreData));
                    ArrayList<EnergyData> arrayList3 = energyScoreData.data;
                    if (arrayList3 != null) {
                        if (!(!arrayList3.isEmpty()) || m.equals(arrayList3.get(0).getStatus(), fragmentEnergyMeterViewModel.getContext().getResources().getString(R.string.error_caps), true)) {
                            fragmentEnergyMeterViewModel.h();
                        } else {
                            fragmentEnergyMeterViewModel.getEnergyScoreLiveData().postValue(arrayList3.get(0).getCurrentEnergyLevel());
                            for (int i = 11; i < 25; i++) {
                                arrayList.add(new Entry(i, -1.0f));
                                arrayList2.add(Utils.getAmPmHourValue(String.valueOf(i)));
                                linkedHashMap.put(String.valueOf(i), Boxing.boxInt(-1));
                            }
                            Intrinsics.checkNotNullExpressionValue(selectedDate2, "selectedDate");
                            fragmentEnergyMeterViewModel.a(arrayList, arrayList2, linkedHashMap, selectedDate2, CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList3));
                        }
                        unit = Unit.INSTANCE;
                    }
                }
                if (unit == null) {
                    FragmentEnergyMeterViewModel.this.h();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public FragmentEnergyMeterViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5730a = context;
        this.b = new MutableLiveData<>();
        this.c = "FragmentEnergyMeterViewModel";
        this.d = "yyyy-MM-dd";
    }

    public final void a(ArrayList<Entry> arrayList, ArrayList<String> arrayList2, LinkedHashMap<String, Integer> linkedHashMap, String str, List<EnergyData> list) {
        int i;
        int i2;
        Unit unit;
        DailyWalkData walkDataForSelectedDate;
        List<EnergyData> list2 = list;
        int i3 = 0;
        for (int size = list.size(); i3 < size; size = i) {
            TreeMap<String, Integer> treeMap = new TreeMap<>();
            TreeMap<String, Integer> treeMap2 = new TreeMap<>();
            TreeMap<String, Integer> treeMap3 = new TreeMap<>();
            EnergyData.ContributingFactors contributingFactors = list2.get(i3).getContributingFactors();
            if (contributingFactors != null) {
                ArrayList<EnergyData.ContributingFactors.Dock.Session> arrayList3 = new ArrayList<>();
                EnergyData.ContributingFactors.Replenish replenish = contributingFactors.getReplenish();
                if (replenish != null) {
                    Double sleepScoreContribution = replenish.getSleepScoreContribution();
                    i2 = sleepScoreContribution != null ? (int) sleepScoreContribution.doubleValue() : 0;
                } else {
                    i2 = 0;
                }
                EnergyData.ContributingFactors.Dock dock = contributingFactors.getDock();
                if (dock != null) {
                    Double nonSessionCalorieContribution = dock.getNonSessionCalorieContribution();
                    double doubleValue = nonSessionCalorieContribution != null ? nonSessionCalorieContribution.doubleValue() : 0.0d;
                    List<EnergyData.ContributingFactors.Dock.Session> sessions = dock.getSessions();
                    if (sessions != null) {
                        if (sessions.size() > 0) {
                            Intrinsics.checkNotNull(sessions, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData.ContributingFactors.Dock.Session>");
                            arrayList3.addAll((ArrayList) sessions);
                            ArrayList arrayList4 = new ArrayList();
                            int size2 = arrayList3.size();
                            for (int i4 = 0; i4 < size2; i4++) {
                                String sessionType = arrayList3.get(i4).getSessionType();
                                if (sessionType != null) {
                                    arrayList4.add(sessionType);
                                }
                            }
                            Resources resources = this.f5730a.getResources();
                            int i5 = R.string.steps;
                            if (arrayList4.contains(resources.getString(i5)) || ((int) doubleValue) <= 0 || (walkDataForSelectedDate = getWalkDataForSelectedDate(str)) == null || walkDataForSelectedDate.getValue() <= 0) {
                                i = size;
                            } else {
                                i = size;
                                EnergyData.ContributingFactors.Dock.Session session = new EnergyData.ContributingFactors.Dock.Session(new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData())));
                                session.setContribution(Double.valueOf(doubleValue));
                                session.setTotalDuration(Integer.valueOf(walkDataForSelectedDate.getValue()));
                                session.setSessionType(this.f5730a.getResources().getString(i5));
                                arrayList3.add(arrayList3.size(), session);
                            }
                            if (arrayList3.size() > 1) {
                                h.sortWith(arrayList3, new Comparator() { // from class: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModel$calculatEMContribution$lambda$9$lambda$8$lambda$6$$inlined$sortByDescending$1
                                    @Override // java.util.Comparator
                                    public final int compare(T t, T t2) {
                                        return f.compareValues(((EnergyData.ContributingFactors.Dock.Session) t2).getContribution(), ((EnergyData.ContributingFactors.Dock.Session) t).getContribution());
                                    }
                                });
                            }
                        } else {
                            i = size;
                            if (((int) doubleValue) > 0) {
                                arrayList3.add(arrayList3.size(), b(str, doubleValue));
                            }
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        i = size;
                        unit = null;
                    }
                    if (unit == null && ((int) doubleValue) > 0) {
                        arrayList3.add(arrayList3.size(), b(str, doubleValue));
                    }
                } else {
                    i = size;
                }
                if (arrayList3.size() > 0) {
                    c(arrayList3, treeMap, treeMap2, treeMap3, i2);
                }
                list2 = list;
            } else {
                i = size;
                list2 = list;
                i2 = 0;
            }
            d(list2.get(i3), linkedHashMap, arrayList, arrayList2, treeMap, treeMap2, treeMap3, i2);
            i3++;
        }
    }

    public final EnergyData.ContributingFactors.Dock.Session b(String str, double d) {
        Unit unit;
        EnergyData.ContributingFactors.Dock.Session session = new EnergyData.ContributingFactors.Dock.Session(new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData())));
        DailyWalkData walkDataForSelectedDate = getWalkDataForSelectedDate(str);
        if (walkDataForSelectedDate != null) {
            if (walkDataForSelectedDate.getValue() > 0) {
                session.setContribution(Double.valueOf(d));
                session.setTotalDuration(Integer.valueOf(walkDataForSelectedDate.getValue()));
                session.setSessionType(this.f5730a.getResources().getString(R.string.steps));
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            ProfileData userDetails = SessionManager.getInstance(this.f5730a).getUserDetails();
            String weight = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
            float parseFloat = Float.parseFloat(weight);
            String height = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
            float calculateBmi = Utils.calculateBmi(parseFloat, Float.parseFloat(height));
            session.setContribution(Double.valueOf(d));
            session.setTotalDuration(Integer.valueOf((int) calculateBmi));
            session.setSessionType(this.f5730a.getResources().getString(R.string.bmi_based_calorie));
        }
        return session;
    }

    public final void c(ArrayList<EnergyData.ContributingFactors.Dock.Session> arrayList, TreeMap<String, Integer> treeMap, TreeMap<String, Integer> treeMap2, TreeMap<String, Integer> treeMap3, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            EnergyData.ContributingFactors.Dock.Session session = arrayList.get(i2);
            Intrinsics.checkNotNullExpressionValue(session, "sessionDataList[s]");
            EnergyData.ContributingFactors.Dock.Session session2 = session;
            if (session2.getSessionType() != null && session2.getContribution() != null && session2.getTotalDuration() != null) {
                String sessionType = session2.getSessionType();
                Intrinsics.checkNotNull(sessionType);
                if (arrayList.size() <= 4) {
                    Double contribution = session2.getContribution();
                    Intrinsics.checkNotNull(contribution);
                    treeMap2.put(sessionType, Integer.valueOf((int) contribution.doubleValue()));
                } else if (i > 0) {
                    if (i2 > 3) {
                        Double contribution2 = session2.getContribution();
                        Intrinsics.checkNotNull(contribution2);
                        treeMap3.put(sessionType, Integer.valueOf((int) contribution2.doubleValue()));
                    } else {
                        Double contribution3 = session2.getContribution();
                        Intrinsics.checkNotNull(contribution3);
                        treeMap2.put(sessionType, Integer.valueOf((int) contribution3.doubleValue()));
                    }
                } else if (i2 > 4) {
                    Double contribution4 = session2.getContribution();
                    Intrinsics.checkNotNull(contribution4);
                    treeMap3.put(sessionType, Integer.valueOf((int) contribution4.doubleValue()));
                } else {
                    Double contribution5 = session2.getContribution();
                    Intrinsics.checkNotNull(contribution5);
                    treeMap2.put(sessionType, Integer.valueOf((int) contribution5.doubleValue()));
                }
                if (!Intrinsics.areEqual(sessionType, this.f5730a.getResources().getString(R.string.steps)) && !Intrinsics.areEqual(sessionType, this.f5730a.getResources().getString(R.string.bmi_based_calorie))) {
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    Integer totalDuration = session2.getTotalDuration();
                    Intrinsics.checkNotNull(totalDuration);
                    treeMap.put(sessionType, Integer.valueOf((int) timeUnit.toMinutes(totalDuration.intValue())));
                } else {
                    Integer totalDuration2 = session2.getTotalDuration();
                    Intrinsics.checkNotNull(totalDuration2);
                    treeMap.put(sessionType, totalDuration2);
                }
            }
        }
    }

    public final void d(EnergyData energyData, LinkedHashMap<String, Integer> linkedHashMap, ArrayList<Entry> arrayList, ArrayList<String> arrayList2, TreeMap<String, Integer> treeMap, TreeMap<String, Integer> treeMap2, TreeMap<String, Integer> treeMap3, int i) {
        Integer num;
        Integer currentEnergyLevel = energyData.getCurrentEnergyLevel();
        Intrinsics.checkNotNull(currentEnergyLevel);
        if (currentEnergyLevel.intValue() <= 0) {
            LinkedHashMap<String, Double> hourlyEnergyLevel = energyData.getHourlyEnergyLevel();
            Set<Map.Entry<String, Double>> entrySet = hourlyEnergyLevel != null ? hourlyEnergyLevel.entrySet() : null;
            Intrinsics.checkNotNull(entrySet);
            if (entrySet.size() <= 0) {
                getContractEnergyMeterDashBoard$sleepenergyscore_prodRelease().updateEnergyMeterData(null, null, null, null, null, i);
                return;
            }
        }
        LinkedHashMap<String, Double> hourlyEnergyLevel2 = energyData.getHourlyEnergyLevel();
        Set<Map.Entry<String, Double>> entrySet2 = hourlyEnergyLevel2 != null ? hourlyEnergyLevel2.entrySet() : null;
        Intrinsics.checkNotNull(entrySet2);
        for (Map.Entry<String, Double> entry : entrySet2) {
            Intrinsics.checkNotNullExpressionValue(entry, "energyData.hourlyEnergyLevel?.entries!!");
            String key = entry.getKey();
            Double value = entry.getValue();
            Intrinsics.checkNotNullExpressionValue(key, "key");
            if (Integer.parseInt(key) >= 11) {
                Intrinsics.checkNotNullExpressionValue(value, "value");
                linkedHashMap.put(key, Integer.valueOf(c.roundToInt(value.doubleValue())));
            }
        }
        int i2 = 0;
        Set<Map.Entry<String, Integer>> entrySet3 = linkedHashMap != null ? linkedHashMap.entrySet() : null;
        Intrinsics.checkNotNull(entrySet3);
        for (Map.Entry<String, Integer> entry2 : entrySet3) {
            Intrinsics.checkNotNullExpressionValue(entry2, "mapOfHourlyEM?.entries!!");
            String key2 = entry2.getKey();
            entry2.getValue();
            Intrinsics.checkNotNullExpressionValue(key2, "key");
            if (Integer.parseInt(key2) >= 11) {
                float f = i2;
                Float valueOf = linkedHashMap.get(key2) != null ? Float.valueOf(num.intValue()) : null;
                Intrinsics.checkNotNull(valueOf);
                arrayList.set(i2, new Entry(f, valueOf.floatValue()));
                i2++;
            }
        }
        getContractEnergyMeterDashBoard$sleepenergyscore_prodRelease().updateEnergyMeterData(arrayList, arrayList2, treeMap, treeMap2, treeMap3, i);
    }

    @NotNull
    public final Context getContext() {
        return this.f5730a;
    }

    @NotNull
    public final ContractEnergyMeterDashBoard getContractEnergyMeterDashBoard$sleepenergyscore_prodRelease() {
        ContractEnergyMeterDashBoard contractEnergyMeterDashBoard = this.contractEnergyMeterDashBoard;
        if (contractEnergyMeterDashBoard != null) {
            return contractEnergyMeterDashBoard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractEnergyMeterDashBoard");
        return null;
    }

    @NotNull
    public final String getDateFormat() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Integer> getEnergyScoreLiveData() {
        return this.b;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void getFeedbackQuestionnarieList() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        FeedbackApiManager.getFeedbackQuestionnaireList("ENERGY_COMPUTATION", new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModel$getFeedbackQuestionnarieList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                String str;
                Intrinsics.checkNotNullParameter(object, "object");
                str = FragmentEnergyMeterViewModel.this.c;
                Log.d(str, "feedbacklisterror: " + object.getMsg());
                FragmentEnergyMeterViewModel.this.getQuestionsList$sleepenergyscore_prodRelease().onReceiveQuestionList(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFeedbackListResponse getFeedbackListResponse) {
                String str;
                ArrayList<QuestionAnswerData> arrayList;
                Intrinsics.checkNotNullParameter(getFeedbackListResponse, "getFeedbackListResponse");
                str = FragmentEnergyMeterViewModel.this.c;
                Log.d(str, "FeedbacklistResponse: " + new Gson().toJson(getFeedbackListResponse));
                String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), FragmentEnergyMeterViewModel.this.getDateFormat());
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(FragmentEnergyMeterViewModel.this.getContext()).getEnergyScoreData(formatDate, BleApiManager.getInstance(FragmentEnergyMeterViewModel.this.getContext()).getBleApi().getMacAddress());
                ArrayList arrayList2 = new ArrayList();
                if (energyScoreData != null && (arrayList = energyScoreData.feedbackList) != null && arrayList.size() > 0) {
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        String questionId = arrayList.get(i).getQuestionId();
                        Intrinsics.checkNotNull(questionId);
                        arrayList2.add(questionId);
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
                    FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel = FragmentEnergyMeterViewModel.this;
                    List<GetFeedbackListResponse.Data.Questionnaire.Question.Option> options = getFeedbackListResponse.getData().getQuestionnaire().get(i2).getQuestion().getOptions();
                    Intrinsics.checkNotNullExpressionValue(options, "getFeedbackListResponse.…naire[i].question.options");
                    feedbackQuetionnarieModel.setOptions(fragmentEnergyMeterViewModel.getOptionList(options));
                    objectRef.element.add(feedbackQuetionnarieModel);
                    if (arrayList2.size() > 0 && arrayList2.contains(feedbackQuetionnarieModel.getQuestionId())) {
                        objectRef.element.remove(feedbackQuetionnarieModel);
                    }
                }
                ArrayList<FeedbackQuetionnarieModel> arrayList3 = objectRef.element;
                if (arrayList3 != null && arrayList3.size() > 0) {
                    FragmentEnergyMeterViewModel.this.getQuestionsList$sleepenergyscore_prodRelease().onReceiveQuestionList(objectRef.element);
                } else {
                    FragmentEnergyMeterViewModel.this.getQuestionsList$sleepenergyscore_prodRelease().onReceiveQuestionList(null);
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
    public final ContractFeedBackQuestionsList getQuestionsList$sleepenergyscore_prodRelease() {
        ContractFeedBackQuestionsList contractFeedBackQuestionsList = this.questionsList;
        if (contractFeedBackQuestionsList != null) {
            return contractFeedBackQuestionsList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("questionsList");
        return null;
    }

    @Nullable
    public final DailyWalkData getWalkDataForSelectedDate(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return WalkDBRead.getInstance(this.f5730a).getDailyWalkDataWithDate(date, BleApiManager.getInstance(this.f5730a).getBleApi().getMacAddress());
    }

    public final void h() {
        this.b.postValue(0);
        getContractEnergyMeterDashBoard$sleepenergyscore_prodRelease().updateEnergyMeterData(null, null, null, null, null, 0);
    }

    public final void loadEnergyData(@NotNull Calendar calendar) {
        Integer sleepScore;
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        String selectedDate = RepositoryUtils.formatDate(calendar.getTime(), this.d);
        Intrinsics.checkNotNullExpressionValue(selectedDate, "selectedDate");
        DailyWalkData walkDataForSelectedDate = getWalkDataForSelectedDate(selectedDate);
        SleepScoreData sleepScoreData = SleepScoreRepository.Companion.getInstance(this.f5730a).getSleepScoreData(selectedDate, BleApiManager.getInstance(this.f5730a).getBleApi().getMacAddress());
        if ((walkDataForSelectedDate != null && walkDataForSelectedDate.getValue() > 0) || (sleepScoreData != null && ((sleepScore = sleepScoreData.getSleepScore()) == null || sleepScore.intValue() != -1))) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(selectedDate, null), 2, null);
        } else {
            h();
        }
    }

    public final void setContractEnergyMeterDashBoard$sleepenergyscore_prodRelease(@NotNull ContractEnergyMeterDashBoard contractEnergyMeterDashBoard) {
        Intrinsics.checkNotNullParameter(contractEnergyMeterDashBoard, "<set-?>");
        this.contractEnergyMeterDashBoard = contractEnergyMeterDashBoard;
    }

    public final void setDateFormat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setEnergyScoreLiveData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }

    public final void setQuestionsList$sleepenergyscore_prodRelease(@NotNull ContractFeedBackQuestionsList contractFeedBackQuestionsList) {
        Intrinsics.checkNotNullParameter(contractFeedBackQuestionsList, "<set-?>");
        this.questionsList = contractFeedBackQuestionsList;
    }
}
