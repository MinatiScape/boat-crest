package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractStressDashboard;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentStressViewModelNew extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4770a;
    public ContractStressDashboard contractStressDashboard;
    public LifecycleOwner mLifecycleOwner;

    public FragmentStressViewModelNew(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4770a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4770a;
    }

    @NotNull
    public final ContractStressDashboard getContractStressDashboard$app_prodRelease() {
        ContractStressDashboard contractStressDashboard = this.contractStressDashboard;
        if (contractStressDashboard != null) {
            return contractStressDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractStressDashboard");
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

    public final void loadHourlyStressData(@NotNull final Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        String formatDate = AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(calendar.time, \"yyyy-MM-dd\")");
        LiveData<List<EntityManualData>> stressData = ManualDataRepository.Companion.getInstance(this.f4770a).getStressData(formatDate, BleApiManager.getInstance(this.f4770a).getBleApi().getMacAddress(), null);
        if (stressData != null) {
            stressData.observe(getMLifecycleOwner(), new Observer<List<? extends EntityManualData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressViewModelNew$loadHourlyStressData$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable @org.jetbrains.annotations.Nullable List<? extends EntityManualData> list) {
                    if (!AppUtils.isEmpty(list)) {
                        ArrayList arrayList = new ArrayList();
                        Intrinsics.checkNotNull(list);
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            arrayList.add(i, Integer.valueOf(list.get(i).getStress()));
                        }
                        FragmentStressViewModelNew.this.getContractStressDashboard$app_prodRelease().updateHourlyStressData(list, String.valueOf(BleApiUtils.INSTANCE.getAverageValue(arrayList)), null, calendar);
                        return;
                    }
                    FragmentStressViewModelNew.this.getContractStressDashboard$app_prodRelease().updateHourlyStressData(null, null, null, calendar);
                }
            });
        }
    }

    public final void setContractStressDashboard$app_prodRelease(@NotNull ContractStressDashboard contractStressDashboard) {
        Intrinsics.checkNotNullParameter(contractStressDashboard, "<set-?>");
        this.contractStressDashboard = contractStressDashboard;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
