package com.coveiot.android.sleepenergyscore.energymeter.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
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
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterFactorType;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterInsightModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterLineChartModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterPieChartBean;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterPieChartModel;
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
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentEnergyMeterViewModelNew extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5732a;
    @NotNull
    public MutableLiveData<Integer> b;
    @NotNull
    public MutableLiveData<Integer> c;
    @NotNull
    public final MutableLiveData<EnergyMeterLineChartModel> d;
    @NotNull
    public final MutableLiveData<EnergyMeterPieChartModel> e;
    public boolean f;
    @NotNull
    public MutableLiveData<EnergyMeterInsightModel> g;
    @NotNull
    public final String h;
    @NotNull
    public String i;
    public LifecycleOwner mLifecycleOwner;
    public ContractFeedBackQuestionsList questionsList;

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew$loadEnergyData$1", f = "FragmentEnergyMeterViewModelNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $calendar;
        public final /* synthetic */ Ref.IntRef $currentEnergy;
        public final /* synthetic */ String $selectedDate;
        private /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Ref.IntRef intRef, Calendar calendar, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$selectedDate = str;
            this.$currentEnergy = intRef;
            this.$calendar = calendar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.$selectedDate, this.$currentEnergy, this.$calendar, continuation);
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
                EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(FragmentEnergyMeterViewModelNew.this.getContext()).getEnergyScoreData(selectedDate, BleApiManager.getInstance(FragmentEnergyMeterViewModelNew.this.getContext()).getBleApi().getMacAddress());
                Unit unit = null;
                if (energyScoreData != null) {
                    FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = FragmentEnergyMeterViewModelNew.this;
                    Ref.IntRef intRef = this.$currentEnergy;
                    Calendar calendar = this.$calendar;
                    String selectedDate2 = this.$selectedDate;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    LogsHelper.d("energyRes*** ", new Gson().toJson(energyScoreData));
                    ArrayList<EnergyData> arrayList3 = energyScoreData.data;
                    if (arrayList3 != null) {
                        if ((!arrayList3.isEmpty()) && !m.equals(arrayList3.get(0).getStatus(), fragmentEnergyMeterViewModelNew.getContext().getResources().getString(R.string.error_caps), true)) {
                            fragmentEnergyMeterViewModelNew.getEnergyScoreLiveData().postValue(arrayList3.get(0).getCurrentEnergyLevel());
                            fragmentEnergyMeterViewModelNew.getLastScoreLiveData().postValue(arrayList3.get(0).getInitialEnergyLevel());
                            Integer currentEnergyLevel = arrayList3.get(0).getCurrentEnergyLevel();
                            Intrinsics.checkNotNull(currentEnergyLevel);
                            intRef.element = currentEnergyLevel.intValue();
                            Log.d("energyCheck", "currentEnergy: " + intRef.element);
                            for (int i = 11; i < 25; i++) {
                                arrayList.add(new Entry(i, -1.0f));
                                arrayList2.add(Utils.getAmPmHourValue(String.valueOf(i)));
                                linkedHashMap.put(String.valueOf(i), Boxing.boxInt(-1));
                            }
                            Intrinsics.checkNotNullExpressionValue(selectedDate2, "selectedDate");
                            fragmentEnergyMeterViewModelNew.b(calendar, arrayList, arrayList2, linkedHashMap, selectedDate2, CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList3));
                        } else {
                            intRef.element = 0;
                            fragmentEnergyMeterViewModelNew.i();
                        }
                        unit = Unit.INSTANCE;
                    }
                }
                if (unit == null) {
                    Ref.IntRef intRef2 = this.$currentEnergy;
                    FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew2 = FragmentEnergyMeterViewModelNew.this;
                    intRef2.element = 0;
                    fragmentEnergyMeterViewModelNew2.i();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew$loadEnergyData$2", f = "FragmentEnergyMeterViewModelNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $previousDate;
        public final /* synthetic */ Ref.IntRef $previousEnergy;
        private /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Ref.IntRef intRef, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$previousDate = str;
            this.$previousEnergy = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            b bVar = new b(this.$previousDate, this.$previousEnergy, continuation);
            bVar.L$0 = obj;
            return bVar;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x00af, code lost:
            if (r7 == null) goto L18;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
            /*
                r6 = this;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r0 = r6.label
                if (r0 != 0) goto Lba
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
                com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository$Companion r7 = com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository.Companion
                com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew r0 = com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew.this
                android.content.Context r0 = r0.getContext()
                java.lang.Object r7 = r7.getInstance(r0)
                com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository r7 = (com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository) r7
                java.lang.String r0 = r6.$previousDate
                java.lang.String r1 = "previousDate"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew r1 = com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew.this
                android.content.Context r1 = r1.getContext()
                com.coveiot.android.bleabstract.api.BleApiManager r1 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r1)
                com.coveiot.android.bleabstract.api.BleApi r1 = r1.getBleApi()
                java.lang.String r1 = r1.getMacAddress()
                com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData r7 = r7.getEnergyScoreData(r0, r1)
                r0 = 0
                if (r7 == 0) goto Lb1
                com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew r1 = com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew.this
                kotlin.jvm.internal.Ref$IntRef r2 = r6.$previousEnergy
                com.google.gson.Gson r3 = new com.google.gson.Gson
                r3.<init>()
                java.lang.String r3 = r3.toJson(r7)
                java.lang.String r4 = "energyRes*** "
                com.coveiot.sdk.ble.helper.LogsHelper.d(r4, r3)
                java.util.ArrayList<com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData> r7 = r7.data
                if (r7 == 0) goto Lae
                boolean r3 = r7.isEmpty()
                r4 = 1
                r3 = r3 ^ r4
                if (r3 == 0) goto La9
                java.lang.Object r3 = r7.get(r0)
                com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData r3 = (com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData) r3
                java.lang.String r3 = r3.getStatus()
                android.content.Context r1 = r1.getContext()
                android.content.res.Resources r1 = r1.getResources()
                int r5 = com.coveiot.android.sleepenergyscore.R.string.error_caps
                java.lang.String r1 = r1.getString(r5)
                boolean r1 = kotlin.text.m.equals(r3, r1, r4)
                if (r1 != 0) goto La9
                java.lang.Object r7 = r7.get(r0)
                com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData r7 = (com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData) r7
                java.lang.Integer r7 = r7.getCurrentEnergyLevel()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                int r7 = r7.intValue()
                r2.element = r7
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r1 = "previousEnergy: "
                r7.append(r1)
                int r1 = r2.element
                r7.append(r1)
                java.lang.String r7 = r7.toString()
                java.lang.String r1 = "energyCheck"
                int r7 = android.util.Log.d(r1, r7)
                java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
                goto Laf
            La9:
                r2.element = r0
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                goto Laf
            Lae:
                r7 = 0
            Laf:
                if (r7 != 0) goto Lb7
            Lb1:
                kotlin.jvm.internal.Ref$IntRef r7 = r6.$previousEnergy
                r7.element = r0
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
            Lb7:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            Lba:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public FragmentEnergyMeterViewModelNew(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5732a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = "FragmentEnergyMeterViewModelNew";
        this.i = "yyyy-MM-dd";
    }

    public static final void f(Ref.IntRef currentEnergy, Ref.IntRef previousEnergy, FragmentEnergyMeterViewModelNew this$0) {
        Intrinsics.checkNotNullParameter(currentEnergy, "$currentEnergy");
        Intrinsics.checkNotNullParameter(previousEnergy, "$previousEnergy");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EnergyMeterInsightModel energyMeterInsightModel = new EnergyMeterInsightModel(0, false);
        energyMeterInsightModel.setEnergyDifference(Math.abs(currentEnergy.element - previousEnergy.element));
        energyMeterInsightModel.setEnergyIncreased(currentEnergy.element > previousEnergy.element);
        Log.d("energyCheck", "loadEnergyData: currentEnergy: " + currentEnergy.element + " || previousEnergy: " + previousEnergy.element + " = " + (currentEnergy.element - previousEnergy.element));
        this$0.g.postValue(energyMeterInsightModel);
    }

    public final void b(Calendar calendar, ArrayList<Entry> arrayList, ArrayList<String> arrayList2, LinkedHashMap<String, Integer> linkedHashMap, String str, List<EnergyData> list) {
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
                            Resources resources = this.f5732a.getResources();
                            int i5 = R.string.steps;
                            if (arrayList4.contains(resources.getString(i5)) || ((int) doubleValue) <= 0 || (walkDataForSelectedDate = getWalkDataForSelectedDate(str)) == null || walkDataForSelectedDate.getValue() <= 0) {
                                i = size;
                            } else {
                                i = size;
                                EnergyData.ContributingFactors.Dock.Session session = new EnergyData.ContributingFactors.Dock.Session(new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData())));
                                session.setContribution(Double.valueOf(doubleValue));
                                session.setTotalDuration(Integer.valueOf(walkDataForSelectedDate.getValue()));
                                session.setSessionType(this.f5732a.getResources().getString(i5));
                                arrayList3.add(arrayList3.size(), session);
                            }
                            if (arrayList3.size() > 1) {
                                h.sortWith(arrayList3, new Comparator() { // from class: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew$calculatEMContribution$lambda$10$lambda$9$lambda$7$$inlined$sortByDescending$1
                                    @Override // java.util.Comparator
                                    public final int compare(T t, T t2) {
                                        return f.compareValues(((EnergyData.ContributingFactors.Dock.Session) t2).getContribution(), ((EnergyData.ContributingFactors.Dock.Session) t).getContribution());
                                    }
                                });
                            }
                        } else {
                            i = size;
                            if (((int) doubleValue) > 0) {
                                arrayList3.add(arrayList3.size(), c(str, doubleValue));
                            }
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        i = size;
                        unit = null;
                    }
                    if (unit == null && ((int) doubleValue) > 0) {
                        arrayList3.add(arrayList3.size(), c(str, doubleValue));
                    }
                } else {
                    i = size;
                }
                if (arrayList3.size() > 0) {
                    e(arrayList3, treeMap, treeMap2, treeMap3, i2);
                }
                list2 = list;
            } else {
                i = size;
                list2 = list;
                i2 = 0;
            }
            h(calendar, list2.get(i3), linkedHashMap, arrayList, arrayList2, treeMap, treeMap2, treeMap3, i2);
            i3++;
        }
    }

    public final EnergyData.ContributingFactors.Dock.Session c(String str, double d) {
        Unit unit;
        EnergyData.ContributingFactors.Dock.Session session = new EnergyData.ContributingFactors.Dock.Session(new EnergyData.ContributingFactors.Dock(new EnergyData.ContributingFactors(new EnergyData())));
        DailyWalkData walkDataForSelectedDate = getWalkDataForSelectedDate(str);
        if (walkDataForSelectedDate != null) {
            if (walkDataForSelectedDate.getValue() > 0) {
                session.setContribution(Double.valueOf(d));
                session.setTotalDuration(Integer.valueOf(walkDataForSelectedDate.getValue()));
                session.setSessionType(this.f5732a.getResources().getString(R.string.steps));
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            ProfileData userDetails = SessionManager.getInstance(this.f5732a).getUserDetails();
            String weight = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
            float parseFloat = Float.parseFloat(weight);
            String height = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
            float calculateBmi = Utils.calculateBmi(parseFloat, Float.parseFloat(height));
            session.setContribution(Double.valueOf(d));
            session.setTotalDuration(Integer.valueOf((int) calculateBmi));
            session.setSessionType(this.f5732a.getResources().getString(R.string.bmi_based_calorie));
        }
        return session;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x012f, code lost:
        if (r22.isEmpty() == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0132, code lost:
        r8 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterPieChartModel d(java.util.Calendar r19, java.util.TreeMap<java.lang.String, java.lang.Integer> r20, java.util.TreeMap<java.lang.String, java.lang.Integer> r21, java.util.TreeMap<java.lang.String, java.lang.Integer> r22, int r23) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew.d(java.util.Calendar, java.util.TreeMap, java.util.TreeMap, java.util.TreeMap, int):com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterPieChartModel");
    }

    public final void e(ArrayList<EnergyData.ContributingFactors.Dock.Session> arrayList, TreeMap<String, Integer> treeMap, TreeMap<String, Integer> treeMap2, TreeMap<String, Integer> treeMap3, int i) {
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
                if (!Intrinsics.areEqual(sessionType, this.f5732a.getResources().getString(R.string.steps)) && !Intrinsics.areEqual(sessionType, this.f5732a.getResources().getString(R.string.bmi_based_calorie))) {
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

    public final void g(String str, int i, EnergyMeterPieChartBean energyMeterPieChartBean) {
        if (!Intrinsics.areEqual(str, this.f5732a.getResources().getString(R.string.steps)) && !Intrinsics.areEqual(str, this.f5732a.getResources().getString(R.string.bmi_based_calorie))) {
            energyMeterPieChartBean.setValue(i + ' ' + this.f5732a.getString(R.string.min));
        } else {
            energyMeterPieChartBean.setValue(String.valueOf(i));
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) ":", false, 2, (Object) null)) {
            List split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
            energyMeterPieChartBean.setName(Utils.getActivityModeNames(this.f5732a, Integer.parseInt((String) split$default.get(1)), Integer.parseInt((String) split$default.get(0))));
            return;
        }
        energyMeterPieChartBean.setName(str);
    }

    @NotNull
    public final Context getContext() {
        return this.f5732a;
    }

    @NotNull
    public final String getDateFormat() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<EnergyMeterInsightModel> getEnergyInsightData() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<EnergyMeterLineChartModel> getEnergyMeterLineChartLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<EnergyMeterPieChartModel> getEnergyMeterPieChartLiveData() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<Integer> getEnergyScoreLiveData() {
        return this.b;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void getFeedbackQuestionnarieList() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        FeedbackApiManager.getFeedbackQuestionnaireList("ENERGY_COMPUTATION", new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew$getFeedbackQuestionnarieList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                String str;
                Intrinsics.checkNotNullParameter(object, "object");
                str = FragmentEnergyMeterViewModelNew.this.h;
                Log.d(str, "feedbacklisterror: " + object.getMsg());
                FragmentEnergyMeterViewModelNew.this.getQuestionsList$sleepenergyscore_prodRelease().onReceiveQuestionList(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFeedbackListResponse getFeedbackListResponse) {
                String str;
                ArrayList<QuestionAnswerData> arrayList;
                Intrinsics.checkNotNullParameter(getFeedbackListResponse, "getFeedbackListResponse");
                str = FragmentEnergyMeterViewModelNew.this.h;
                Log.d(str, "FeedbacklistResponse: " + new Gson().toJson(getFeedbackListResponse));
                String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), FragmentEnergyMeterViewModelNew.this.getDateFormat());
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(FragmentEnergyMeterViewModelNew.this.getContext()).getEnergyScoreData(formatDate, BleApiManager.getInstance(FragmentEnergyMeterViewModelNew.this.getContext()).getBleApi().getMacAddress());
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
                    FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = FragmentEnergyMeterViewModelNew.this;
                    List<GetFeedbackListResponse.Data.Questionnaire.Question.Option> options = getFeedbackListResponse.getData().getQuestionnaire().get(i2).getQuestion().getOptions();
                    Intrinsics.checkNotNullExpressionValue(options, "getFeedbackListResponse.…naire[i].question.options");
                    feedbackQuetionnarieModel.setOptions(fragmentEnergyMeterViewModelNew.getOptionList(options));
                    objectRef.element.add(feedbackQuetionnarieModel);
                    if (arrayList2.size() > 0 && arrayList2.contains(feedbackQuetionnarieModel.getQuestionId())) {
                        objectRef.element.remove(feedbackQuetionnarieModel);
                    }
                }
                ArrayList<FeedbackQuetionnarieModel> arrayList3 = objectRef.element;
                if (arrayList3 != null && arrayList3.size() > 0) {
                    FragmentEnergyMeterViewModelNew.this.getQuestionsList$sleepenergyscore_prodRelease().onReceiveQuestionList(objectRef.element);
                } else {
                    FragmentEnergyMeterViewModelNew.this.getQuestionsList$sleepenergyscore_prodRelease().onReceiveQuestionList(null);
                }
            }
        });
    }

    @NotNull
    public final MutableLiveData<Integer> getLastScoreLiveData() {
        return this.c;
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
    public final Calendar getPreviousDate(@NotNull Calendar date, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        Object clone = date.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.add(5, i);
        return calendar;
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
        return WalkDBRead.getInstance(this.f5732a).getDailyWalkDataWithDate(date, BleApiManager.getInstance(this.f5732a).getBleApi().getMacAddress());
    }

    public final void h(Calendar calendar, EnergyData energyData, LinkedHashMap<String, Integer> linkedHashMap, ArrayList<Entry> arrayList, ArrayList<String> arrayList2, TreeMap<String, Integer> treeMap, TreeMap<String, Integer> treeMap2, TreeMap<String, Integer> treeMap3, int i) {
        Integer num;
        Integer currentEnergyLevel = energyData.getCurrentEnergyLevel();
        Intrinsics.checkNotNull(currentEnergyLevel);
        if (currentEnergyLevel.intValue() <= 0) {
            LinkedHashMap<String, Double> hourlyEnergyLevel = energyData.getHourlyEnergyLevel();
            Set<Map.Entry<String, Double>> entrySet = hourlyEnergyLevel != null ? hourlyEnergyLevel.entrySet() : null;
            Intrinsics.checkNotNull(entrySet);
            if (entrySet.size() <= 0) {
                this.d.postValue(new EnergyMeterLineChartModel(null, null, 0, 0, 12, null));
                new EnergyMeterPieChartBean(EnergyMeterFactorType.GAIN, this.f5732a.getString(R.string.energy_score), i, null, 8, null);
                this.e.postValue(new EnergyMeterPieChartModel(null, 1, null));
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
        this.d.postValue(new EnergyMeterLineChartModel(arrayList, arrayList2, 0, 0, 12, null));
        MutableLiveData<EnergyMeterPieChartModel> mutableLiveData = this.e;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(d(calendar, treeMap, treeMap2, treeMap3, i));
        }
    }

    public final void i() {
        this.b.postValue(0);
        this.c.postValue(0);
        this.d.postValue(new EnergyMeterLineChartModel(null, null, 0, 0, 15, null));
        this.e.postValue(new EnergyMeterPieChartModel(null, 1, null));
    }

    public final boolean isWeekSelected() {
        return this.f;
    }

    public final void loadEnergyData(@NotNull Calendar calendar) {
        Integer sleepScore;
        Integer sleepScore2;
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        final Ref.IntRef intRef = new Ref.IntRef();
        String selectedDate = RepositoryUtils.formatDate(calendar.getTime(), this.i);
        Intrinsics.checkNotNullExpressionValue(selectedDate, "selectedDate");
        DailyWalkData walkDataForSelectedDate = getWalkDataForSelectedDate(selectedDate);
        SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
        SleepScoreData sleepScoreData = companion.getInstance(this.f5732a).getSleepScoreData(selectedDate, BleApiManager.getInstance(this.f5732a).getBleApi().getMacAddress());
        if ((walkDataForSelectedDate != null && walkDataForSelectedDate.getValue() > 0) || (sleepScoreData != null && ((sleepScore = sleepScoreData.getSleepScore()) == null || sleepScore.intValue() != -1))) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(selectedDate, intRef, calendar, null), 2, null);
        } else {
            intRef.element = 0;
            i();
        }
        final Ref.IntRef intRef2 = new Ref.IntRef();
        String previousDate = RepositoryUtils.formatDate(getPreviousDate(calendar, -1).getTime(), this.i);
        Intrinsics.checkNotNullExpressionValue(previousDate, "previousDate");
        DailyWalkData walkDataForSelectedDate2 = getWalkDataForSelectedDate(previousDate);
        SleepScoreData sleepScoreData2 = companion.getInstance(this.f5732a).getSleepScoreData(previousDate, BleApiManager.getInstance(this.f5732a).getBleApi().getMacAddress());
        if ((walkDataForSelectedDate2 != null && walkDataForSelectedDate2.getValue() > 0) || (sleepScoreData2 != null && ((sleepScore2 = sleepScoreData2.getSleepScore()) == null || sleepScore2.intValue() != -1))) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new b(previousDate, intRef2, null), 2, null);
        } else {
            intRef2.element = 0;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.a
            @Override // java.lang.Runnable
            public final void run() {
                FragmentEnergyMeterViewModelNew.f(Ref.IntRef.this, intRef2, this);
            }
        }, 500L);
    }

    public final void setDateFormat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.i = str;
    }

    public final void setEnergyInsightData(@NotNull MutableLiveData<EnergyMeterInsightModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setEnergyScoreLiveData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setIsWeekSelected(boolean z) {
        this.f = z;
    }

    public final void setLastScoreLiveData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }

    public final void setQuestionsList$sleepenergyscore_prodRelease(@NotNull ContractFeedBackQuestionsList contractFeedBackQuestionsList) {
        Intrinsics.checkNotNullParameter(contractFeedBackQuestionsList, "<set-?>");
        this.questionsList = contractFeedBackQuestionsList;
    }

    public final void setWeekSelected(boolean z) {
        this.f = z;
    }
}
