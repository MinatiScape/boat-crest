package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.leonardo.dashboard.health.comaparators.AmbientSoundHistoryComparator;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFragmentAmbientSoundHistory;
import com.coveiot.android.leonardo.dashboard.health.model.AmbientSoundLevelData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.MonthlyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.WeeklyAmbientSoundData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.repository.ambientsound.AmbientSoundRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.Entry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentAmbientSoundHistoryViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4754a;
    public ContractFragmentAmbientSoundHistory contractFragmentAmbientSoundHistory;
    public LifecycleOwner mLifecycleOwner;

    public FragmentAmbientSoundHistoryViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4754a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4754a;
    }

    @NotNull
    public final ContractFragmentAmbientSoundHistory getContractFragmentAmbientSoundHistory$app_prodRelease() {
        ContractFragmentAmbientSoundHistory contractFragmentAmbientSoundHistory = this.contractFragmentAmbientSoundHistory;
        if (contractFragmentAmbientSoundHistory != null) {
            return contractFragmentAmbientSoundHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractFragmentAmbientSoundHistory");
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
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4754a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<EntityDailyAmbientSoundData>> dayWiseList = AmbientSoundRepository.Companion.getInstance(this.f4754a).getDayWiseList(calendar, calendar2, connectedDeviceMacAddress);
        if (dayWiseList != null) {
            dayWiseList.observe(getMLifecycleOwner(), new Observer<List<? extends EntityDailyAmbientSoundData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentAmbientSoundHistoryViewModel$selectDayView$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List<? extends EntityDailyAmbientSoundData> list) {
                    if (AppUtils.isEmpty(list)) {
                        return;
                    }
                    ArrayList<Entry> arrayList = new ArrayList<>();
                    ArrayList<String> arrayList2 = new ArrayList<>();
                    if (list != null) {
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            EntityDailyAmbientSoundData entityDailyAmbientSoundData = list.get(i);
                            AmbientSoundLevelData type = new AmbientSoundLevelData(entityDailyAmbientSoundData.getMinAmbientSound(), entityDailyAmbientSoundData.getMaxAmbientSound(), (int) entityDailyAmbientSoundData.getAvgAmbientSound(), entityDailyAmbientSoundData.getDate()).setType(AppConstants.DAY.getValue());
                            Entry entry = new Entry(i, (float) entityDailyAmbientSoundData.getAvgAmbientSound());
                            entry.setData(type);
                            arrayList.add(entry);
                            arrayList2.add(PayUtils.getDayMonthFormatDate(entityDailyAmbientSoundData.getDate()));
                        }
                        FragmentAmbientSoundHistoryViewModel.this.getContractFragmentAmbientSoundHistory$app_prodRelease().onLineChartDataLoaded(arrayList, arrayList2);
                    }
                }
            });
        }
    }

    public final void selectMonthView() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4754a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<MonthlyAmbientSoundData>> monthWiseList = AmbientSoundRepository.Companion.getInstance(this.f4754a).getMonthWiseList(calendar, calendar2, connectedDeviceMacAddress);
        if (monthWiseList != null) {
            monthWiseList.observe(getMLifecycleOwner(), new Observer<List<? extends MonthlyAmbientSoundData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentAmbientSoundHistoryViewModel$selectMonthView$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List<? extends MonthlyAmbientSoundData> list) {
                    if (!AppUtils.isEmpty(list)) {
                        ArrayList<Entry> arrayList = new ArrayList<>();
                        ArrayList<String> arrayList2 = new ArrayList<>();
                        if (list != null) {
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                MonthlyAmbientSoundData monthlyAmbientSoundData = list.get(i);
                                int i2 = monthlyAmbientSoundData.minAmbientSound;
                                int i3 = monthlyAmbientSoundData.maxAmbientSound;
                                int i4 = monthlyAmbientSoundData.avgAmbientSound;
                                AmbientSoundLevelData type = new AmbientSoundLevelData(i2, i3, i4, monthlyAmbientSoundData.year + Soundex.SILENT_MARKER + monthlyAmbientSoundData.getMonth()).setType(AppConstants.MONTH.getValue());
                                Entry entry = new Entry((float) i, (float) monthlyAmbientSoundData.avgAmbientSound);
                                entry.setData(type);
                                arrayList.add(entry);
                                SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("MM yyyy");
                                SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("MMM yyyy");
                                try {
                                    arrayList2.add(simpleDateFormat2.format(simpleDateFormat.parse(monthlyAmbientSoundData.getMonth() + ' ' + monthlyAmbientSoundData.year)));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            FragmentAmbientSoundHistoryViewModel.this.getContractFragmentAmbientSoundHistory$app_prodRelease().onLineChartDataLoaded(arrayList, arrayList2);
                            return;
                        }
                        return;
                    }
                    FragmentAmbientSoundHistoryViewModel.this.getContractFragmentAmbientSoundHistory$app_prodRelease().onLineChartDataLoaded(null, null);
                }
            });
        }
    }

    public final void selectWeekView() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4754a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<WeeklyAmbientSoundData>> weekWiseList = AmbientSoundRepository.Companion.getInstance(this.f4754a).getWeekWiseList(calendar, calendar2, connectedDeviceMacAddress);
        if (weekWiseList != null) {
            weekWiseList.observe(getMLifecycleOwner(), new Observer<List<? extends WeeklyAmbientSoundData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentAmbientSoundHistoryViewModel$selectWeekView$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List<? extends WeeklyAmbientSoundData> list) {
                    if (!AppUtils.isEmpty(list)) {
                        Collections.sort(list, new AmbientSoundHistoryComparator());
                        ArrayList<Entry> arrayList = new ArrayList<>();
                        ArrayList arrayList2 = new ArrayList();
                        if (list != null) {
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                WeeklyAmbientSoundData weeklyAmbientSoundData = list.get(i);
                                AmbientSoundLevelData type = new AmbientSoundLevelData(weeklyAmbientSoundData.minAmbientSound, weeklyAmbientSoundData.maxAmbientSound, weeklyAmbientSoundData.avgAmbientSound, weeklyAmbientSoundData.getWeek()).setType(AppConstants.WEEK.getValue());
                                String str = weeklyAmbientSoundData.year;
                                Intrinsics.checkNotNullExpressionValue(str, "weeklyHrData.year");
                                type.setYear(Integer.parseInt(str));
                                Entry entry = new Entry(i, weeklyAmbientSoundData.avgAmbientSound);
                                entry.setData(type);
                                arrayList.add(entry);
                                arrayList2.add('W' + weeklyAmbientSoundData.getWeek());
                            }
                            FragmentAmbientSoundHistoryViewModel.this.getContractFragmentAmbientSoundHistory$app_prodRelease().onLineChartDataLoaded(arrayList, arrayList2);
                            return;
                        }
                        return;
                    }
                    FragmentAmbientSoundHistoryViewModel.this.getContractFragmentAmbientSoundHistory$app_prodRelease().onLineChartDataLoaded(null, null);
                }
            });
        }
    }

    public final void setContractFragmentAmbientSoundHistory$app_prodRelease(@NotNull ContractFragmentAmbientSoundHistory contractFragmentAmbientSoundHistory) {
        Intrinsics.checkNotNullParameter(contractFragmentAmbientSoundHistory, "<set-?>");
        this.contractFragmentAmbientSoundHistory = contractFragmentAmbientSoundHistory;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
