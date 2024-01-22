package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.heartrate.datasources.db.read.HeartRateDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.CandleEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentHeartRateViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4758a;
    public ContractHeartRateDashboard contractHeartRateDashboard;
    public LifecycleOwner mLifecycleOwner;

    public FragmentHeartRateViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4758a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4758a;
    }

    @NotNull
    public final ContractHeartRateDashboard getContractHeartRateDashboard$app_prodRelease() {
        ContractHeartRateDashboard contractHeartRateDashboard = this.contractHeartRateDashboard;
        if (contractHeartRateDashboard != null) {
            return contractHeartRateDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractHeartRateDashboard");
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

    public final void loadBpHourlyData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        HeartRateRepository.Companion.getInstance(this.f4758a).getHourlyData(date, BleApiManager.getInstance(this.f4758a).getBleApi().getMacAddress()).observe(getMLifecycleOwner(), new Observer<List<? extends EntityHourlyHeartRateData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateViewModel$loadBpHourlyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@NotNull List<? extends EntityHourlyHeartRateData> hourlyHrList) {
                List emptyList;
                boolean z;
                Intrinsics.checkNotNullParameter(hourlyHrList, "hourlyHrList");
                if (!AppUtils.isEmpty(hourlyHrList)) {
                    ArrayList<CandleEntry> arrayList = new ArrayList<>();
                    ArrayList<String> arrayList2 = new ArrayList<>();
                    for (int i = 0; i < 24; i++) {
                        arrayList.add(new CandleEntry(i, 0.0f, 0.0f, 0.0f, 0.0f));
                        arrayList2.add(PayUtils.getAmPmHourValue(String.valueOf(i)));
                    }
                    for (EntityHourlyHeartRateData entityHourlyHeartRateData : hourlyHrList) {
                        String startTime = entityHourlyHeartRateData.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "hourlyHeartRateData.startTime");
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
                                arrayList.set(parseInt, new CandleEntry(parseInt, entityHourlyHeartRateData.getAvgHeartRate(), entityHourlyHeartRateData.getMaxHeartRate(), entityHourlyHeartRateData.getMinHeartRate(), entityHourlyHeartRateData.getMaxHeartRate()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    FragmentHeartRateViewModel.this.getContractHeartRateDashboard$app_prodRelease().updateHourlyLevelData(arrayList, arrayList2);
                    return;
                }
                FragmentHeartRateViewModel.this.getContractHeartRateDashboard$app_prodRelease().updateHourlyLevelData(null, null);
            }
        });
    }

    public final void loadDailyData(@NotNull Calendar date) {
        LiveData<EntityDailyHeartRateData> dailyDataWithoutFlowValidator;
        Intrinsics.checkNotNullParameter(date, "date");
        if (getContractHeartRateDashboard$app_prodRelease().isSyncInProgress()) {
            dailyDataWithoutFlowValidator = HeartRateRepository.Companion.getInstance(this.f4758a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4758a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator = HeartRateRepository.Companion.getInstance(this.f4758a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4758a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator.observe(getMLifecycleOwner(), new Observer<EntityDailyHeartRateData>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateViewModel$loadDailyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable @org.jetbrains.annotations.Nullable EntityDailyHeartRateData entityDailyHeartRateData) {
                FragmentHeartRateViewModel.this.getContractHeartRateDashboard$app_prodRelease().updateDailyLevelData(entityDailyHeartRateData);
            }
        });
    }

    public final void selectWeeklyView(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        EntityDailyHeartRateData entityDailyHeartRateData;
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        List<EntityDailyHeartRateData> rangeHRData = HeartRateDBRead.getInstance(this.f4758a).getDailyHeartRateDataByStartAndEndDate(BleApiManager.getInstance(this.f4758a).getBleApi().getMacAddress(), RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"));
        if (!AppUtils.isEmpty(rangeHRData)) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd/MM");
            ArrayList<CandleEntry> arrayList = new ArrayList<>();
            ArrayList<String> arrayList2 = new ArrayList<>();
            int size = rangeHRData.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new CandleEntry(i, rangeHRData.get(i) != null ? entityDailyHeartRateData.getAvgHeartRate() : 0.0f, rangeHRData.get(i).getMaxHeartRate(), rangeHRData.get(i).getMinHeartRate(), rangeHRData.get(i).getMaxHeartRate()));
                try {
                    arrayList2.add(simpleDateFormat2.format(simpleDateFormat.parse(rangeHRData.get(i).getDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            getContractHeartRateDashboard$app_prodRelease().updateHourlyLevelData(arrayList, arrayList2);
            ContractHeartRateDashboard contractHeartRateDashboard$app_prodRelease = getContractHeartRateDashboard$app_prodRelease();
            Intrinsics.checkNotNullExpressionValue(rangeHRData, "rangeHRData");
            contractHeartRateDashboard$app_prodRelease.updateRangeLevelData(rangeHRData);
            return;
        }
        getContractHeartRateDashboard$app_prodRelease().updateHourlyLevelData(null, null);
        getContractHeartRateDashboard$app_prodRelease().updateRangeLevelData(CollectionsKt__CollectionsKt.emptyList());
    }

    public final void setContractHeartRateDashboard$app_prodRelease(@NotNull ContractHeartRateDashboard contractHeartRateDashboard) {
        Intrinsics.checkNotNullParameter(contractHeartRateDashboard, "<set-?>");
        this.contractHeartRateDashboard = contractHeartRateDashboard;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
