package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.leonardo.dashboard.health.model.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.BarEntry;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1", f = "FragmentSleepHistoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Calendar $fromDate;
    public final /* synthetic */ Calendar $toDate;
    public int label;
    public final /* synthetic */ FragmentSleepHistoryViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1(FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel, Calendar calendar, Calendar calendar2, Continuation<? super FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentSleepHistoryViewModel;
        this.$fromDate = calendar;
        this.$toDate = calendar2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1(this.this$0, this.$fromDate, this.$toDate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        SleepDataModel sleepDataModel;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.getMLastQueriedSleepData().clear();
            this.this$0.getMLastQueriedTotalSleepHourDayWise().clear();
            this.this$0.getBarEntries().clear();
            this.this$0.getBarLabels().clear();
            this.this$0.setLatestDateKey(null);
            this.this$0.setTotalSleepHour(0);
            List<DailySleepData> dailySleepDataBetweenDates = SleepDBRead.getInstance(this.this$0.getContext()).getDailySleepDataBetweenDates(this.this$0.getSimpleDateFormat().format(this.$fromDate.getTime()), this.this$0.getSimpleDateFormat().format(this.$toDate.getTime()), BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
            if (!(dailySleepDataBetweenDates == null || dailySleepDataBetweenDates.isEmpty())) {
                List<DailySleepData> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) new HashSet(dailySleepDataBetweenDates));
                if (mutableList.size() > 1) {
                    h.sortWith(mutableList, new Comparator() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1$invokeSuspend$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return f.compareValues(((DailySleepData) t).getDate(), ((DailySleepData) t2).getDate());
                        }
                    });
                }
                int i = 0;
                int i2 = 0;
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
                        HashMap<String, List<SleepDataModelForLastNight>> mLastQueriedSleepData = this.this$0.getMLastQueriedSleepData();
                        String date = dailySleepData.getDate();
                        Intrinsics.checkNotNullExpressionValue(date, "dailySleepData.date");
                        mLastQueriedSleepData.put(date, CollectionsKt___CollectionsKt.toMutableList((Collection) lastNignthSleepDataWithOutLiveData));
                        HashMap<String, Integer> mLastQueriedTotalSleepHourDayWise = this.this$0.getMLastQueriedTotalSleepHourDayWise();
                        String date2 = dailySleepData.getDate();
                        Intrinsics.checkNotNullExpressionValue(date2, "dailySleepData.date");
                        mLastQueriedTotalSleepHourDayWise.put(date2, Boxing.boxInt(sleepDataModel.getCountTotalSleep()));
                        BarEntry barEntry = new BarEntry(i2, sleepDataModel.getCountTotalSleep() / 60.0f);
                        barEntry.setData(new SleepData(String.valueOf(sleepDataModel.getCountOfDeepSleepMinutes()), String.valueOf(sleepDataModel.getCountOfLightSleepMinutes()), String.valueOf(sleepDataModel.getCountOfAwakeMinutes()), String.valueOf(sleepDataModel.getCountOfREMMinutes()), dailySleepData.getDate()));
                        this.this$0.getBarEntries().add(barEntry);
                        this.this$0.getBarLabels().add(this.this$0.getSimpleDateFormat1().format(cal.getTime()));
                        this.this$0.setLatestDateKey(dailySleepData.getDate());
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.this$0;
                        fragmentSleepHistoryViewModel.setTotalSleepHour(fragmentSleepHistoryViewModel.getTotalSleepHour() + sleepDataModel.getCountTotalSleep());
                        i2++;
                    }
                }
                this.this$0.getTotalSleepData().postValue(Boxing.boxInt(this.this$0.getTotalSleepHour()));
                if (i > 0) {
                    this.this$0.getAvgSleepData().postValue(Boxing.boxInt(this.this$0.getTotalSleepHour() / i));
                } else {
                    this.this$0.getAvgSleepData().postValue(Boxing.boxInt(0));
                }
            } else {
                this.this$0.getAvgSleepData().postValue(Boxing.boxInt(0));
                this.this$0.getTotalSleepData().postValue(Boxing.boxInt(0));
            }
            this.this$0.getSleepRangeQueryLiveData().postValue(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
