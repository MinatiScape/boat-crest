package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.leonardo.dashboard.health.comaparators.HRHistoryWeekComparator;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFragmentHeartRateHistory;
import com.coveiot.android.leonardo.dashboard.health.model.HeartRateData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.model.MonthlyHeartRateData;
import com.coveiot.covedb.heartrate.model.WeeklyHeartRateData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.CandleEntry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentHeartRateHistoryViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4757a;
    public ContractFragmentHeartRateHistory contractFragmentHeartRateHistory;
    public LifecycleOwner mLifecycleOwner;

    public FragmentHeartRateHistoryViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4757a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4757a;
    }

    @NotNull
    public final ContractFragmentHeartRateHistory getContractFragmentHeartRateHistory$app_prodRelease() {
        ContractFragmentHeartRateHistory contractFragmentHeartRateHistory = this.contractFragmentHeartRateHistory;
        if (contractFragmentHeartRateHistory != null) {
            return contractFragmentHeartRateHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractFragmentHeartRateHistory");
        return null;
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

    public final void selectDayView() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4757a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<EntityDailyHeartRateData>> dayWiseList = HeartRateRepository.Companion.getInstance(this.f4757a).getDayWiseList(calendar, calendar2, connectedDeviceMacAddress);
        if (dayWiseList != null) {
            dayWiseList.observe(getMLifecycleOwner(), new Observer<List<? extends EntityDailyHeartRateData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateHistoryViewModel$selectDayView$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List<? extends EntityDailyHeartRateData> list) {
                    if (AppUtils.isEmpty(list)) {
                        return;
                    }
                    ArrayList<CandleEntry> arrayList = new ArrayList<>();
                    ArrayList<String> arrayList2 = new ArrayList<>();
                    if (list != null) {
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            EntityDailyHeartRateData entityDailyHeartRateData = list.get(i);
                            HeartRateData type = new HeartRateData(entityDailyHeartRateData.getMinHeartRate(), entityDailyHeartRateData.getMaxHeartRate(), entityDailyHeartRateData.getAvgHeartRate(), entityDailyHeartRateData.getDate()).setType(AppConstants.DAY.getValue());
                            CandleEntry candleEntry = new CandleEntry(i, entityDailyHeartRateData.getAvgHeartRate(), entityDailyHeartRateData.getMaxHeartRate(), entityDailyHeartRateData.getMinHeartRate(), entityDailyHeartRateData.getMaxHeartRate());
                            candleEntry.setData(type);
                            arrayList.add(candleEntry);
                            arrayList2.add(PayUtils.getDayMonthFormatDate(entityDailyHeartRateData.getDate()));
                        }
                        FragmentHeartRateHistoryViewModel.this.getContractFragmentHeartRateHistory$app_prodRelease().onCandleChartDataLoaded(arrayList, arrayList2);
                    }
                }
            });
        }
    }

    public final void selectMonthView() {
        Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4757a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<MonthlyHeartRateData>> monthWiseList = HeartRateRepository.Companion.getInstance(this.f4757a).getMonthWiseList(calendar, calendar2, connectedDeviceMacAddress);
        if (monthWiseList != null) {
            monthWiseList.observe(getMLifecycleOwner(), new Observer<List<? extends MonthlyHeartRateData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateHistoryViewModel$selectMonthView$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List<? extends MonthlyHeartRateData> list) {
                    if (AppUtils.isEmpty(list)) {
                        FragmentHeartRateHistoryViewModel.this.getContractFragmentHeartRateHistory$app_prodRelease().onCandleChartDataLoaded(null, null);
                    } else if (list != null) {
                        List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) list);
                        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
                        if (mutableList.size() > 1) {
                            h.sortWith(mutableList, new Comparator() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateHistoryViewModel$selectMonthView$1$onChanged$$inlined$sortBy$1
                                @Override // java.util.Comparator
                                public final int compare(T t, T t2) {
                                    MonthlyHeartRateData monthlyHeartRateData = (MonthlyHeartRateData) t;
                                    SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                                    Date parse = simpleDateFormat2.parse(monthlyHeartRateData.getMonth() + ' ' + monthlyHeartRateData.year);
                                    MonthlyHeartRateData monthlyHeartRateData2 = (MonthlyHeartRateData) t2;
                                    SimpleDateFormat simpleDateFormat3 = simpleDateFormat;
                                    return f.compareValues(parse, simpleDateFormat3.parse(monthlyHeartRateData2.getMonth() + ' ' + monthlyHeartRateData2.year));
                                }
                            });
                        }
                        ArrayList<CandleEntry> arrayList = new ArrayList<>();
                        ArrayList<String> arrayList2 = new ArrayList<>();
                        int size = mutableList.size();
                        for (int i = 0; i < size; i++) {
                            MonthlyHeartRateData monthlyHeartRateData = (MonthlyHeartRateData) mutableList.get(i);
                            int minHeartRate = monthlyHeartRateData.getMinHeartRate();
                            int maxHeartRate = monthlyHeartRateData.getMaxHeartRate();
                            int avgHeartRate = monthlyHeartRateData.getAvgHeartRate();
                            HeartRateData type = new HeartRateData(minHeartRate, maxHeartRate, avgHeartRate, monthlyHeartRateData.year + Soundex.SILENT_MARKER + monthlyHeartRateData.getMonth()).setType(AppConstants.MONTH.getValue());
                            CandleEntry candleEntry = new CandleEntry((float) i, (float) monthlyHeartRateData.getAvgHeartRate(), (float) monthlyHeartRateData.getMaxHeartRate(), (float) monthlyHeartRateData.getMinHeartRate(), (float) monthlyHeartRateData.getMaxHeartRate());
                            candleEntry.setData(type);
                            arrayList.add(candleEntry);
                            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("MM yyyy");
                            SimpleDateFormat simpleDateFormat3 = AppUtils.getSimpleDateFormat("MMM yyyy");
                            try {
                                arrayList2.add(simpleDateFormat3.format(simpleDateFormat2.parse(monthlyHeartRateData.getMonth() + ' ' + monthlyHeartRateData.year)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        FragmentHeartRateHistoryViewModel.this.getContractFragmentHeartRateHistory$app_prodRelease().onCandleChartDataLoaded(arrayList, arrayList2);
                    }
                }
            });
        }
    }

    public final void selectWeekView() {
        Calendar.getInstance().add(2, -3);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4757a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<WeeklyHeartRateData>> weekWiseList = HeartRateRepository.Companion.getInstance(this.f4757a).getWeekWiseList(calendar, calendar2, connectedDeviceMacAddress);
        if (weekWiseList != null) {
            weekWiseList.observe(getMLifecycleOwner(), new Observer<List<? extends WeeklyHeartRateData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateHistoryViewModel$selectWeekView$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List<? extends WeeklyHeartRateData> list) {
                    if (!AppUtils.isEmpty(list)) {
                        Collections.sort(list, new HRHistoryWeekComparator());
                        ArrayList<CandleEntry> arrayList = new ArrayList<>();
                        ArrayList arrayList2 = new ArrayList();
                        if (list != null) {
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                WeeklyHeartRateData weeklyHeartRateData = list.get(i);
                                HeartRateData type = new HeartRateData(weeklyHeartRateData.minHeartRate, weeklyHeartRateData.maxHeartRate, weeklyHeartRateData.getAvgHeartRate(), weeklyHeartRateData.getWeek()).setType(AppConstants.WEEK.getValue());
                                String str = weeklyHeartRateData.year;
                                Intrinsics.checkNotNullExpressionValue(str, "weeklyHrData.year");
                                type.setYear(Integer.parseInt(str));
                                float avgHeartRate = weeklyHeartRateData.getAvgHeartRate();
                                int i2 = weeklyHeartRateData.maxHeartRate;
                                CandleEntry candleEntry = new CandleEntry(i, avgHeartRate, i2, weeklyHeartRateData.minHeartRate, i2);
                                candleEntry.setData(type);
                                arrayList.add(candleEntry);
                                arrayList2.add('W' + weeklyHeartRateData.getWeek());
                            }
                            FragmentHeartRateHistoryViewModel.this.getContractFragmentHeartRateHistory$app_prodRelease().onCandleChartDataLoaded(arrayList, arrayList2);
                            return;
                        }
                        return;
                    }
                    FragmentHeartRateHistoryViewModel.this.getContractFragmentHeartRateHistory$app_prodRelease().onCandleChartDataLoaded(null, null);
                }
            });
        }
    }

    public final void setContractFragmentHeartRateHistory$app_prodRelease(@NotNull ContractFragmentHeartRateHistory contractFragmentHeartRateHistory) {
        Intrinsics.checkNotNullParameter(contractFragmentHeartRateHistory, "<set-?>");
        this.contractFragmentHeartRateHistory = contractFragmentHeartRateHistory;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
