package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.leonardo.dashboard.vitals.model.SleepInsightsModel;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.utils.utility.AppUtils;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1", f = "FragmentSleepHistoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Calendar $fromDate;
    public final /* synthetic */ boolean $isCurrentRange;
    public final /* synthetic */ Calendar $toDate;
    public int label;
    public final /* synthetic */ FragmentSleepHistoryViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1(FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel, Calendar calendar, Calendar calendar2, boolean z, Continuation<? super FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentSleepHistoryViewModel;
        this.$fromDate = calendar;
        this.$toDate = calendar2;
        this.$isCurrentRange = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1(this.this$0, this.$fromDate, this.$toDate, this.$isCurrentRange, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        SleepDataModel sleepDataModel;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<DailySleepData> dailySleepDataBetweenDates = SleepDBRead.getInstance(this.this$0.getContext()).getDailySleepDataBetweenDates(this.this$0.getSimpleDateFormat().format(this.$fromDate.getTime()), this.this$0.getSimpleDateFormat().format(this.$toDate.getTime()), BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
            if (!(dailySleepDataBetweenDates == null || dailySleepDataBetweenDates.isEmpty())) {
                List<DailySleepData> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) new HashSet(dailySleepDataBetweenDates));
                if (mutableList.size() > 1) {
                    h.sortWith(mutableList, new Comparator() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1$invokeSuspend$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return f.compareValues(((DailySleepData) t).getDate(), ((DailySleepData) t2).getDate());
                        }
                    });
                }
                this.this$0.setTotalSleepTime(0);
                this.this$0.setTotalAwakeTime(0);
                this.this$0.setTotalDeepTime(0);
                int i = 0;
                for (DailySleepData dailySleepData : mutableList) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(11, 0);
                    cal.set(12, 0);
                    cal.set(13, 0);
                    cal.setTime(AppUtils.parseDate(dailySleepData.getDate(), this.this$0.getDateFormat()));
                    Intrinsics.checkNotNullExpressionValue(cal, "cal");
                    List<SleepDataModelForLastNight> lastNignthSleepDataWithOutLiveData = SleepRepository.Companion.getInstance(this.this$0.getContext()).getLastNignthSleepDataWithOutLiveData(cal, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
                    if (lastNignthSleepDataWithOutLiveData != null && (sleepDataModel = SleepDataHelper.getSleepDataModel(this.this$0.getContext(), lastNignthSleepDataWithOutLiveData)) != null && sleepDataModel.getCountTotalSleep() > 0) {
                        i++;
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.this$0;
                        fragmentSleepHistoryViewModel.setTotalSleepTime(fragmentSleepHistoryViewModel.getTotalSleepTime() + sleepDataModel.getCountTotalSleep());
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel2 = this.this$0;
                        fragmentSleepHistoryViewModel2.setTotalAwakeTime(fragmentSleepHistoryViewModel2.getTotalAwakeTime() + sleepDataModel.getCountOfAwakeMinutes());
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel3 = this.this$0;
                        fragmentSleepHistoryViewModel3.setTotalDeepTime(fragmentSleepHistoryViewModel3.getTotalDeepTime() + sleepDataModel.getCountOfDeepSleepMinutes());
                    }
                }
                if (i > 0) {
                    int totalSleepTime = this.this$0.getTotalSleepTime();
                    int totalAwakeTime = this.this$0.getTotalAwakeTime() / i;
                    int totalDeepTime = this.this$0.getTotalDeepTime() / i;
                    if (this.$isCurrentRange) {
                        MutableLiveData<SleepInsightsModel> currentSleepInsightsData = this.this$0.getCurrentSleepInsightsData();
                        if (currentSleepInsightsData != null) {
                            currentSleepInsightsData.postValue(new SleepInsightsModel(totalSleepTime, totalAwakeTime, totalDeepTime));
                        }
                    } else {
                        MutableLiveData<SleepInsightsModel> previousSleepInsightsData = this.this$0.getPreviousSleepInsightsData();
                        if (previousSleepInsightsData != null) {
                            previousSleepInsightsData.postValue(new SleepInsightsModel(totalSleepTime, totalAwakeTime, totalDeepTime));
                        }
                    }
                } else if (this.$isCurrentRange) {
                    MutableLiveData<SleepInsightsModel> currentSleepInsightsData2 = this.this$0.getCurrentSleepInsightsData();
                    if (currentSleepInsightsData2 != null) {
                        currentSleepInsightsData2.postValue(new SleepInsightsModel(0, 0, 0));
                    }
                } else {
                    MutableLiveData<SleepInsightsModel> previousSleepInsightsData2 = this.this$0.getPreviousSleepInsightsData();
                    if (previousSleepInsightsData2 != null) {
                        previousSleepInsightsData2.postValue(new SleepInsightsModel(0, 0, 0));
                    }
                }
            } else if (this.$isCurrentRange) {
                MutableLiveData<SleepInsightsModel> currentSleepInsightsData3 = this.this$0.getCurrentSleepInsightsData();
                if (currentSleepInsightsData3 != null) {
                    currentSleepInsightsData3.postValue(new SleepInsightsModel(0, 0, 0));
                }
            } else {
                MutableLiveData<SleepInsightsModel> previousSleepInsightsData3 = this.this$0.getPreviousSleepInsightsData();
                if (previousSleepInsightsData3 != null) {
                    previousSleepInsightsData3.postValue(new SleepInsightsModel(0, 0, 0));
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
