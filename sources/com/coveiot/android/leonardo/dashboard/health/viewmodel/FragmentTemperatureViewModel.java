package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractTemperatureDashboard;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.temperature.TemperatureRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.Entry;
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
public final class FragmentTemperatureViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4771a;
    public ContractTemperatureDashboard contractTemperatureDashboard;
    public LifecycleOwner mLifecycleOwner;

    public FragmentTemperatureViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4771a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4771a;
    }

    @NotNull
    public final ContractTemperatureDashboard getContractTemperatureDashboard$app_prodRelease() {
        ContractTemperatureDashboard contractTemperatureDashboard = this.contractTemperatureDashboard;
        if (contractTemperatureDashboard != null) {
            return contractTemperatureDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractTemperatureDashboard");
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

    public final void loadDailyData(@NotNull Calendar date) {
        LiveData<DailyTemperature> dailyDataWithoutFlowValidator;
        Intrinsics.checkNotNullParameter(date, "date");
        if (getContractTemperatureDashboard$app_prodRelease().isSyncInProgress()) {
            dailyDataWithoutFlowValidator = TemperatureRepository.Companion.getInstance(this.f4771a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4771a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator = TemperatureRepository.Companion.getInstance(this.f4771a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4771a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator.observe(getMLifecycleOwner(), new Observer<DailyTemperature>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentTemperatureViewModel$loadDailyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable @org.jetbrains.annotations.Nullable DailyTemperature dailyTemperature) {
                FragmentTemperatureViewModel.this.getContractTemperatureDashboard$app_prodRelease().updateDailyLevelData(dailyTemperature);
            }
        });
    }

    public final void loadHourlyTemperatureData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        TemperatureRepository.Companion.getInstance(this.f4771a).getHourlyData(date, BleApiManager.getInstance(this.f4771a).getBleApi().getMacAddress()).observe(getMLifecycleOwner(), new Observer<List<? extends HourlyTemperature>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentTemperatureViewModel$loadHourlyTemperatureData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable @NotNull List<? extends HourlyTemperature> hourlyHrList) {
                List emptyList;
                boolean z;
                Intrinsics.checkNotNullParameter(hourlyHrList, "hourlyHrList");
                if (!AppUtils.isEmpty(hourlyHrList)) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList<Entry> arrayList2 = new ArrayList<>();
                    for (int i = 0; i < 24; i++) {
                        arrayList.add(PayUtils.getAmPmHourValue(String.valueOf(i)));
                        arrayList2.add(new Entry(i, 0.0f));
                    }
                    for (HourlyTemperature hourlyTemperature : hourlyHrList) {
                        String startTime = hourlyTemperature.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "hourlyHeartRateData.startTime");
                        List<String> split = new Regex(":").split(startTime, 0);
                        boolean z2 = true;
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
                                Context context = FragmentTemperatureViewModel.this.getContext();
                                Intrinsics.checkNotNull(context);
                                Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(context).isTemperatureUnitInFahrenheit();
                                Intrinsics.checkNotNullExpressionValue(isTemperatureUnitInFahrenheit, "getInstance(context!!).i…mperatureUnitInFahrenheit");
                                if (isTemperatureUnitInFahrenheit.booleanValue()) {
                                    float temperatureInFahrenheit = (float) PayUtils.getTemperatureInFahrenheit(hourlyTemperature.temperature_high);
                                    if (temperatureInFahrenheit != 32.0f) {
                                        z2 = false;
                                    }
                                    if (z2) {
                                        temperatureInFahrenheit = 0.0f;
                                    }
                                    arrayList2.set(parseInt, new Entry(parseInt, temperatureInFahrenheit));
                                } else {
                                    arrayList2.set(parseInt, new Entry(parseInt, (float) hourlyTemperature.temperature_high));
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    FragmentTemperatureViewModel.this.getContractTemperatureDashboard$app_prodRelease().updateHourlyLevelData(arrayList2, arrayList);
                    return;
                }
                FragmentTemperatureViewModel.this.getContractTemperatureDashboard$app_prodRelease().updateHourlyLevelData(null, null);
            }
        });
    }

    public final void setContractTemperatureDashboard$app_prodRelease(@NotNull ContractTemperatureDashboard contractTemperatureDashboard) {
        Intrinsics.checkNotNullParameter(contractTemperatureDashboard, "<set-?>");
        this.contractTemperatureDashboard = contractTemperatureDashboard;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
