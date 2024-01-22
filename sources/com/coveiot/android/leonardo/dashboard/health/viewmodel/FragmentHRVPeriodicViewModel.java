package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicHRVDashboard;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.RrHrHelperKt;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.hrv.datasource.HRVRepository;
import com.coveiot.repository.hrv.datasource.db.read.HRVDBRead;
import com.coveiot.repository.stress.StressRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentHRVPeriodicViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4756a;
    public ContractPeriodicHRVDashboard contractPeriodicHRVDashboard;
    public LifecycleOwner mLifecycleOwner;

    public FragmentHRVPeriodicViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4756a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4756a;
    }

    @NotNull
    public final ContractPeriodicHRVDashboard getContractPeriodicHRVDashboard$app_prodRelease() {
        ContractPeriodicHRVDashboard contractPeriodicHRVDashboard = this.contractPeriodicHRVDashboard;
        if (contractPeriodicHRVDashboard != null) {
            return contractPeriodicHRVDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractPeriodicHRVDashboard");
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

    @NotNull
    public final Pair<String, String> getReadinessDynamicText(double d) {
        boolean z = true;
        if (1.0d <= d && d <= 3.0d) {
            return new Pair<>(RrHrHelperKt.f5433a, "Go easy on your body today");
        }
        if (4.0d <= d && d <= 7.0d) {
            return new Pair<>("Medium", "Balance your activities, pace it out well");
        }
        if (8.0d > d || d > 10.0d) {
            z = false;
        }
        if (z) {
            return new Pair<>("Optimal", "Well rested to take the day's challenges!");
        }
        return new Pair<>(RrHrHelperKt.f5433a, "Go easy on your body today");
    }

    public final void loadDailyData(@NotNull Calendar date) {
        LiveData<DailyHRV> dailyDataWithoutFlowValidator;
        Intrinsics.checkNotNullParameter(date, "date");
        if (getContractPeriodicHRVDashboard$app_prodRelease().isSyncInProgress()) {
            dailyDataWithoutFlowValidator = HRVRepository.Companion.getInstance(this.f4756a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4756a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator = HRVRepository.Companion.getInstance(this.f4756a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4756a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator.observe(getMLifecycleOwner(), new Observer<DailyHRV>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHRVPeriodicViewModel$loadDailyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable @org.jetbrains.annotations.Nullable DailyHRV dailyHRV) {
                FragmentHRVPeriodicViewModel.this.getContractPeriodicHRVDashboard$app_prodRelease().updateDailyLevelData(dailyHRV);
            }
        });
    }

    public final void loadDailyStressData(@NotNull Calendar date) {
        LiveData<DailyStress> dailyDataWithoutFlowValidator;
        Intrinsics.checkNotNullParameter(date, "date");
        if (getContractPeriodicHRVDashboard$app_prodRelease().isSyncInProgress()) {
            dailyDataWithoutFlowValidator = StressRepository.Companion.getInstance(this.f4756a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4756a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator = StressRepository.Companion.getInstance(this.f4756a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4756a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator.observe(getMLifecycleOwner(), new Observer<DailyStress>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHRVPeriodicViewModel$loadDailyStressData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable @org.jetbrains.annotations.Nullable DailyStress dailyStress) {
                FragmentHRVPeriodicViewModel.this.getContractPeriodicHRVDashboard$app_prodRelease().updateDailyLevelStressData(dailyStress);
            }
        });
    }

    public final void loadHourlyHRVData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        HRVRepository.Companion.getInstance(this.f4756a).getHourlyData(date, BleApiManager.getInstance(this.f4756a).getBleApi().getMacAddress()).observe(getMLifecycleOwner(), new Observer<List<? extends HourlyHRV>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHRVPeriodicViewModel$loadHourlyHRVData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@NotNull List<? extends HourlyHRV> hourlyDataList) {
                List emptyList;
                boolean z;
                Intrinsics.checkNotNullParameter(hourlyDataList, "hourlyDataList");
                if (!AppUtils.isEmpty(hourlyDataList)) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList<Entry> arrayList2 = new ArrayList<>();
                    for (int i = 0; i < 24; i++) {
                        arrayList.add(PayUtils.getAmPmHourValue(String.valueOf(i)));
                        arrayList2.add(new Entry(i, 0.0f));
                    }
                    boolean z2 = false;
                    for (HourlyHRV hourlyHRV : hourlyDataList) {
                        String startTime = hourlyHRV.getStartTime();
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
                                double d = hourlyHRV.hrv_avg;
                                if (d > 0.0d) {
                                    z2 = true;
                                }
                                arrayList2.set(parseInt, new Entry(parseInt, (float) PayUtils.INSTANCE.calculationFormulaHRV(d)));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (z2) {
                        FragmentHRVPeriodicViewModel.this.getContractPeriodicHRVDashboard$app_prodRelease().updateHourlyLevelData(arrayList2, arrayList);
                        return;
                    } else {
                        FragmentHRVPeriodicViewModel.this.getContractPeriodicHRVDashboard$app_prodRelease().updateHourlyLevelData(null, null);
                        return;
                    }
                }
                FragmentHRVPeriodicViewModel.this.getContractPeriodicHRVDashboard$app_prodRelease().updateHourlyLevelData(null, null);
            }
        });
    }

    public final void selectRangeView(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        String formatDate = RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd");
        String formatDate2 = RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd/MM");
        List<DailyHRV> hRVDataByStartAndEndDate = HRVDBRead.getInstance(this.f4756a).getHRVDataByStartAndEndDate(BleApiManager.getInstance(this.f4756a).getBleApi().getMacAddress(), formatDate, formatDate2);
        if (!AppUtils.isEmpty(hRVDataByStartAndEndDate)) {
            ArrayList<Entry> arrayList = new ArrayList<>();
            ArrayList<Entry> arrayList2 = new ArrayList<>();
            ArrayList<String> arrayList3 = new ArrayList<>();
            if (hRVDataByStartAndEndDate != null) {
                int size = hRVDataByStartAndEndDate.size();
                int i = 0;
                double d = 0.0d;
                int i2 = 0;
                double d2 = 0.0d;
                while (i2 < size) {
                    double d3 = hRVDataByStartAndEndDate.get(i2).hrv_avg;
                    if (d3 > d) {
                        double d4 = d2 + d3;
                        float f = i;
                        PayUtils payUtils = PayUtils.INSTANCE;
                        Entry entry = new Entry(f, (float) payUtils.calculationFormulaHRV(hRVDataByStartAndEndDate.get(i2).hrv_avg));
                        arrayList.add(entry);
                        Entry entry2 = new Entry(f, (float) payUtils.calculationFormulaHRV(hRVDataByStartAndEndDate.get(i2).baselinne));
                        entry.setData(hRVDataByStartAndEndDate.get(i2));
                        int i3 = i + 1;
                        arrayList2.add(entry2);
                        try {
                            arrayList3.add(simpleDateFormat2.format(simpleDateFormat.parse(hRVDataByStartAndEndDate.get(i2).mDate)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        i = i3;
                        d2 = d4;
                    }
                    i2++;
                    d = 0.0d;
                }
                getContractPeriodicHRVDashboard$app_prodRelease().onRangeBarChartLoaded(arrayList, arrayList2, arrayList3, d2 / arrayList.size());
                return;
            }
            return;
        }
        getContractPeriodicHRVDashboard$app_prodRelease().onRangeBarChartLoaded(null, null, null, 0.0d);
    }

    public final void setContractPeriodicHRVDashboard$app_prodRelease(@NotNull ContractPeriodicHRVDashboard contractPeriodicHRVDashboard) {
        Intrinsics.checkNotNullParameter(contractPeriodicHRVDashboard, "<set-?>");
        this.contractPeriodicHRVDashboard = contractPeriodicHRVDashboard;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
