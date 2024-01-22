package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.os.Handler;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.health.model.DailyStepsInsightsModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.repository.walk.WalkRepository;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$loadHourlyDataForInsights$1", f = "FragmentStepsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class FragmentStepsViewModel$loadHourlyDataForInsights$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref.IntRef $codedValueLength;
    public final /* synthetic */ Ref.IntRef $currentDaySteps;
    public final /* synthetic */ Calendar $date;
    public final /* synthetic */ Ref.BooleanRef $isResultFetched;
    public final /* synthetic */ Ref.IntRef $previousDaySteps;
    public int label;
    public final /* synthetic */ FragmentStepsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentStepsViewModel$loadHourlyDataForInsights$1(FragmentStepsViewModel fragmentStepsViewModel, Calendar calendar, Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.BooleanRef booleanRef, Continuation<? super FragmentStepsViewModel$loadHourlyDataForInsights$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentStepsViewModel;
        this.$date = calendar;
        this.$codedValueLength = intRef;
        this.$currentDaySteps = intRef2;
        this.$previousDaySteps = intRef3;
        this.$isResultFetched = booleanRef;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(Ref.BooleanRef booleanRef, FragmentStepsViewModel fragmentStepsViewModel, Ref.IntRef intRef, Ref.IntRef intRef2) {
        if (booleanRef.element) {
            fragmentStepsViewModel.getStepsGoalInsights().postValue(new DailyStepsInsightsModel(intRef.element, intRef2.element));
        } else {
            fragmentStepsViewModel.getStepsGoalInsights().postValue(new DailyStepsInsightsModel(0, 0));
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentStepsViewModel$loadHourlyDataForInsights$1(this.this$0, this.$date, this.$codedValueLength, this.$currentDaySteps, this.$previousDaySteps, this.$isResultFetched, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentStepsViewModel$loadHourlyDataForInsights$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            WalkRepository.Companion companion = WalkRepository.Companion;
            LiveData<List<HourlyWalkData>> hourlyData = companion.getInstance(this.this$0.getContext()).getHourlyData(this.$date, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
            LifecycleOwner mLifecycleOwner = this.this$0.getMLifecycleOwner();
            final FragmentStepsViewModel fragmentStepsViewModel = this.this$0;
            final Ref.IntRef intRef = this.$codedValueLength;
            final Ref.IntRef intRef2 = this.$currentDaySteps;
            hourlyData.observe(mLifecycleOwner, new Observer<List<? extends HourlyWalkData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$loadHourlyDataForInsights$1.1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@NotNull List<? extends HourlyWalkData> mhourlyWalkDataList) {
                    FitnessGoal fitnessGoalStepsData;
                    Intrinsics.checkNotNullParameter(mhourlyWalkDataList, "mhourlyWalkDataList");
                    new ArrayList();
                    List<HourlyWalkData> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) mhourlyWalkDataList);
                    if (!AppUtils.isEmpty(mutableList)) {
                        UserDataManager userDataManager = UserDataManager.getInstance(FragmentStepsViewModel.this.getContext());
                        if (((userDataManager == null || (fitnessGoalStepsData = userDataManager.getFitnessGoalStepsData()) == null) ? null : fitnessGoalStepsData.target) == null) {
                            FragmentStepsViewModel.this.a(mutableList, 10000, false);
                        } else {
                            FragmentStepsViewModel fragmentStepsViewModel2 = FragmentStepsViewModel.this;
                            Integer num = UserDataManager.getInstance(fragmentStepsViewModel2.getContext()).getFitnessGoalStepsData().target;
                            Intrinsics.checkNotNullExpressionValue(num, "getInstance(context).fitnessGoalStepsData.target");
                            fragmentStepsViewModel2.a(mutableList, num.intValue(), false);
                        }
                        Collections.sort(mutableList, new FragmentStepsViewModel.HourlyComparator());
                        for (HourlyWalkData hourlyWalkData : mutableList) {
                            if (hourlyWalkData.getStartTime().equals(FragmentStepsViewModel.this.getPreviousStartHour()) && hourlyWalkData.getEndTime().equals(FragmentStepsViewModel.this.getPreviousEndHour())) {
                                ArrayList<Integer> codevalue = hourlyWalkData.getCodevalue();
                                if (!(codevalue == null || codevalue.isEmpty())) {
                                    intRef.element = (int) (PayUtils.INSTANCE.getCurrentTimeInMinutes(FragmentStepsViewModel.this.getContext()) / (60 / hourlyWalkData.getCodevalue().size()));
                                }
                                int i = intRef.element;
                                for (int i2 = 0; i2 < i; i2++) {
                                    Ref.IntRef intRef3 = intRef2;
                                    int i3 = intRef3.element;
                                    Integer num2 = hourlyWalkData.getCodevalue().get(i2);
                                    Intrinsics.checkNotNullExpressionValue(num2, "i.codevalue[j]");
                                    intRef3.element = i3 + num2.intValue();
                                }
                                return;
                            }
                            intRef2.element += hourlyWalkData.getIntervelValue();
                        }
                        return;
                    }
                    intRef2.element = 0;
                }
            });
            Object clone = this.$date.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(6, -1);
            LiveData<List<HourlyWalkData>> hourlyData2 = companion.getInstance(this.this$0.getContext()).getHourlyData(calendar, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
            LifecycleOwner mLifecycleOwner2 = this.this$0.getMLifecycleOwner();
            final FragmentStepsViewModel fragmentStepsViewModel2 = this.this$0;
            final Ref.IntRef intRef3 = this.$codedValueLength;
            final Ref.IntRef intRef4 = this.$previousDaySteps;
            final Ref.BooleanRef booleanRef = this.$isResultFetched;
            hourlyData2.observe(mLifecycleOwner2, new Observer<List<? extends HourlyWalkData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$loadHourlyDataForInsights$1.2
                @Override // androidx.lifecycle.Observer
                public void onChanged(@NotNull List<? extends HourlyWalkData> mhourlyWalkDataList) {
                    FitnessGoal fitnessGoalStepsData;
                    Intrinsics.checkNotNullParameter(mhourlyWalkDataList, "mhourlyWalkDataList");
                    new ArrayList();
                    List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) mhourlyWalkDataList);
                    if (!AppUtils.isEmpty(mutableList)) {
                        UserDataManager userDataManager = UserDataManager.getInstance(FragmentStepsViewModel.this.getContext());
                        if (((userDataManager == null || (fitnessGoalStepsData = userDataManager.getFitnessGoalStepsData()) == null) ? null : fitnessGoalStepsData.target) == null) {
                            FragmentStepsViewModel.this.a(mutableList, 10000, false);
                        } else {
                            FragmentStepsViewModel fragmentStepsViewModel3 = FragmentStepsViewModel.this;
                            Integer num = UserDataManager.getInstance(fragmentStepsViewModel3.getContext()).getFitnessGoalStepsData().target;
                            Intrinsics.checkNotNullExpressionValue(num, "getInstance(context).fitnessGoalStepsData.target");
                            fragmentStepsViewModel3.a(mutableList, num.intValue(), false);
                        }
                        Collections.sort(mutableList, new FragmentStepsViewModel.HourlyComparator());
                        Iterator it = mutableList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            HourlyWalkData hourlyWalkData = (HourlyWalkData) it.next();
                            if (hourlyWalkData.getStartTime().equals(FragmentStepsViewModel.this.getPreviousStartHour()) && hourlyWalkData.getEndTime().equals(FragmentStepsViewModel.this.getPreviousEndHour())) {
                                ArrayList<Integer> codevalue = hourlyWalkData.getCodevalue();
                                if (!(codevalue == null || codevalue.isEmpty())) {
                                    intRef3.element = (int) (PayUtils.INSTANCE.getCurrentTimeInMinutes(FragmentStepsViewModel.this.getContext()) / (60 / hourlyWalkData.getCodevalue().size()));
                                }
                                int i = intRef3.element;
                                for (int i2 = 0; i2 < i; i2++) {
                                    Ref.IntRef intRef5 = intRef4;
                                    int i3 = intRef5.element;
                                    Integer num2 = hourlyWalkData.getCodevalue().get(i2);
                                    Intrinsics.checkNotNullExpressionValue(num2, "i.codevalue[j]");
                                    intRef5.element = i3 + num2.intValue();
                                }
                            } else {
                                intRef4.element += hourlyWalkData.getIntervelValue();
                            }
                        }
                        booleanRef.element = true;
                        return;
                    }
                    intRef4.element = 0;
                    booleanRef.element = true;
                }
            });
            Handler handler = new Handler();
            final Ref.BooleanRef booleanRef2 = this.$isResultFetched;
            final FragmentStepsViewModel fragmentStepsViewModel3 = this.this$0;
            final Ref.IntRef intRef5 = this.$currentDaySteps;
            final Ref.IntRef intRef6 = this.$previousDaySteps;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.c
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentStepsViewModel$loadHourlyDataForInsights$1.invokeSuspend$lambda$0(Ref.BooleanRef.this, fragmentStepsViewModel3, intRef5, intRef6);
                }
            }, 1000L);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
