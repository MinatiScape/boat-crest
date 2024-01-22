package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicSpo2;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.repository.periodicspo2.PeriodicSpo2Repository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.BarEntry;
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
public final class FragmentPeriodicSpo2ViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4759a;
    public ContractPeriodicSpo2 contractPeriodicSpo2;
    public LifecycleOwner mLifecycleOwner;

    public FragmentPeriodicSpo2ViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4759a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4759a;
    }

    @NotNull
    public final ContractPeriodicSpo2 getContractPeriodicSpo2$app_prodRelease() {
        ContractPeriodicSpo2 contractPeriodicSpo2 = this.contractPeriodicSpo2;
        if (contractPeriodicSpo2 != null) {
            return contractPeriodicSpo2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractPeriodicSpo2");
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
        Intrinsics.checkNotNullParameter(date, "date");
    }

    public final void loadHourlyPeriodicSpo2Data(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        PeriodicSpo2Repository.Companion.getInstance(this.f4759a).getHourlyData(date, BleApiManager.getInstance(this.f4759a).getBleApi().getMacAddress()).observe(getMLifecycleOwner(), new Observer<List<? extends EntityHourlySpo2>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentPeriodicSpo2ViewModel$loadHourlyPeriodicSpo2Data$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable @NotNull List<? extends EntityHourlySpo2> hourlySpo2List) {
                List emptyList;
                int i;
                boolean z;
                Intrinsics.checkNotNullParameter(hourlySpo2List, "hourlySpo2List");
                if (!AppUtils.isEmpty(hourlySpo2List)) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList<BarEntry> arrayList2 = new ArrayList<>();
                    for (int i2 = 0; i2 < 24; i2++) {
                        arrayList.add(PayUtils.getAmPmHourValue(String.valueOf(i2)));
                        arrayList2.add(new BarEntry(i2, 0.0f));
                    }
                    for (EntityHourlySpo2 entityHourlySpo2 : hourlySpo2List) {
                        String startTime = entityHourlySpo2.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "hourlySpo2Data.startTime");
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
                                int size = entityHourlySpo2.codevalue.size() - 1;
                                if (size >= 0) {
                                    while (true) {
                                        int i3 = size - 1;
                                        Integer num = entityHourlySpo2.codevalue.get(size);
                                        Intrinsics.checkNotNullExpressionValue(num, "hourlySpo2Data.codevalue[i]");
                                        if (num.intValue() > 0) {
                                            Integer num2 = entityHourlySpo2.codevalue.get(size);
                                            Intrinsics.checkNotNullExpressionValue(num2, "hourlySpo2Data.codevalue[i]");
                                            i = num2.intValue();
                                            break;
                                        } else if (i3 < 0) {
                                            break;
                                        } else {
                                            size = i3;
                                        }
                                    }
                                    arrayList2.set(parseInt, new BarEntry(parseInt, i));
                                }
                                i = 0;
                                arrayList2.set(parseInt, new BarEntry(parseInt, i));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    FragmentPeriodicSpo2ViewModel.this.getContractPeriodicSpo2$app_prodRelease().updateHourlyLevelData(arrayList2, arrayList);
                    return;
                }
                FragmentPeriodicSpo2ViewModel.this.getContractPeriodicSpo2$app_prodRelease().updateHourlyLevelData(null, null);
            }
        });
    }

    public final void setContractPeriodicSpo2$app_prodRelease(@NotNull ContractPeriodicSpo2 contractPeriodicSpo2) {
        Intrinsics.checkNotNullParameter(contractPeriodicSpo2, "<set-?>");
        this.contractPeriodicSpo2 = contractPeriodicSpo2;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
