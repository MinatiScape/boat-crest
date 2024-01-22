package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractSP02Dashboard;
import com.coveiot.android.leonardo.dashboard.health.spo2.SPO2Level;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.Entry;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSpo2ViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4765a;
    public ContractSP02Dashboard contractSP02Dashboard;
    public LifecycleOwner mLifecycleOwner;

    public FragmentSpo2ViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4765a = context;
    }

    public final Drawable a(float f) {
        if (f == 2.0f) {
            return this.f4765a.getDrawable(R.drawable.circle_normal_spo2);
        }
        if (f == 1.0f) {
            return this.f4765a.getDrawable(R.drawable.circle_very_low_spo2);
        }
        if (f == 0.0f) {
            return this.f4765a.getDrawable(R.drawable.circle_very_low_spo2);
        }
        return this.f4765a.getDrawable(R.color.colorPrimary);
    }

    public final float b(float f) {
        if (f == 2.0f) {
            return 75.0f;
        }
        if (f == 1.0f) {
            return 25.0f;
        }
        if (f == 0.0f) {
            return 0.0f;
        }
        return f;
    }

    @NotNull
    public final Context getContext() {
        return this.f4765a;
    }

    @NotNull
    public final ContractSP02Dashboard getContractSP02Dashboard$app_prodRelease() {
        ContractSP02Dashboard contractSP02Dashboard = this.contractSP02Dashboard;
        if (contractSP02Dashboard != null) {
            return contractSP02Dashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractSP02Dashboard");
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

    @Nullable
    public final CharSequence getSpo2Level(float f) {
        if (f == 2.0f) {
            return SPO2Level.NORMAL.name();
        }
        if (f == 1.0f) {
            return SPO2Level.LOW.name();
        }
        if (f == 0.0f) {
            return m.replace$default(SPO2Level.VERY_LOW.name(), "_", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null);
        }
        return String.valueOf(f);
    }

    public final void loadHourlySP02Data(@NotNull final Calendar calendar, final boolean z) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        ManualDataRepository.Companion companion = ManualDataRepository.Companion;
        String formatDate = AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(calendar.time, \"yyyy-MM-dd\")");
        LiveData<List<EntityManualData>> lastMeasuredSpo2HourlyDataForDate = companion.getInstance(this.f4765a).getLastMeasuredSpo2HourlyDataForDate(formatDate, BleApiManager.getInstance(this.f4765a).getBleApi().getMacAddress(), null, z ? 1 : 0);
        if (!z) {
            String formatDate2 = AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(calendar.time, \"yyyy-MM-dd\")");
            lastMeasuredSpo2HourlyDataForDate = companion.getInstance(this.f4765a).getSpo2ata(formatDate2, BleApiManager.getInstance(this.f4765a).getBleApi().getMacAddress(), null, z ? 1 : 0);
        }
        if (lastMeasuredSpo2HourlyDataForDate != null) {
            lastMeasuredSpo2HourlyDataForDate.observe(getMLifecycleOwner(), new Observer<List<? extends EntityManualData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSpo2ViewModel$loadHourlySP02Data$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@androidx.annotation.Nullable @Nullable List<? extends EntityManualData> list) {
                    Entry entry;
                    Drawable a2;
                    Drawable a3;
                    EntityManualData entityManualData;
                    EntityManualData entityManualData2;
                    EntityManualData entityManualData3;
                    EntityManualData entityManualData4;
                    if (!AppUtils.isEmpty(list)) {
                        Long l = null;
                        if (!z) {
                            Float valueOf = (list == null || (entityManualData4 = list.get(0)) == null) ? null : Float.valueOf((float) entityManualData4.getSpo2());
                            Intrinsics.checkNotNull(valueOf);
                            float floatValue = valueOf.floatValue();
                            if (list != null && (entityManualData3 = list.get(0)) != null) {
                                l = Long.valueOf(entityManualData3.getTimeStamp());
                            }
                            Intrinsics.checkNotNull(list);
                            for (EntityManualData entityManualData5 : list) {
                                long timeStamp = entityManualData5.getTimeStamp();
                                Intrinsics.checkNotNull(l);
                                if (timeStamp > l.longValue()) {
                                    floatValue = (float) entityManualData5.getSpo2();
                                    l = Long.valueOf(entityManualData5.getTimeStamp());
                                }
                            }
                            this.getContractSP02Dashboard$app_prodRelease().updateHourlySPO2Data(CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSpo2ViewModel$loadHourlySP02Data$1$onChanged$$inlined$compareByDescending$1
                                @Override // java.util.Comparator
                                public final int compare(T t, T t2) {
                                    return f.compareValues(Long.valueOf(((EntityManualData) t2).getTimeStamp()), Long.valueOf(((EntityManualData) t).getTimeStamp()));
                                }
                            }), String.valueOf(floatValue), l, calendar);
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        ArrayList<Entry> arrayList2 = new ArrayList<>();
                        for (int i = 0; i < 24; i++) {
                            arrayList.add(PayUtils.getAmPmHourValue(String.valueOf(i)));
                            arrayList2.add(new Entry(i, 0.0f));
                        }
                        Float valueOf2 = (list == null || (entityManualData2 = list.get(0)) == null) ? null : Float.valueOf((float) entityManualData2.getSpo2());
                        Intrinsics.checkNotNull(valueOf2);
                        float floatValue2 = valueOf2.floatValue();
                        if (list != null && (entityManualData = list.get(0)) != null) {
                            l = Long.valueOf(entityManualData.getTimeStamp());
                        }
                        Intrinsics.checkNotNull(list);
                        Long l2 = l;
                        for (EntityManualData entityManualData6 : list) {
                            if (entityManualData6.getTimeStamp() != 0) {
                                try {
                                    Calendar calendar2 = Calendar.getInstance();
                                    calendar2.setTimeInMillis(entityManualData6.getTimeStamp());
                                    int i2 = calendar2.get(11);
                                    float spo2 = (float) entityManualData6.getSpo2();
                                    long timeStamp2 = entityManualData6.getTimeStamp();
                                    Intrinsics.checkNotNull(l2);
                                    if (timeStamp2 > l2.longValue()) {
                                        floatValue2 = (float) entityManualData6.getSpo2();
                                        l2 = Long.valueOf(entityManualData6.getTimeStamp());
                                    }
                                    if (entityManualData6.isLevelInterpretation()) {
                                        spo2 = this.b(spo2);
                                    }
                                    if (z) {
                                        a3 = this.a((float) entityManualData6.getSpo2());
                                        entry = new Entry(i2, spo2, a3);
                                    } else {
                                        a2 = this.a((float) entityManualData6.getSpo2());
                                        entry = new Entry(i2, spo2, a2);
                                    }
                                    entry.setData(entityManualData6);
                                    arrayList2.set(i2, entry);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        this.getContractSP02Dashboard$app_prodRelease().updateHourlyLevelData(arrayList2, arrayList, String.valueOf(this.getSpo2Level(floatValue2)), l2, calendar);
                        return;
                    }
                    this.getContractSP02Dashboard$app_prodRelease().updateHourlyLevelData(null, null, null, null, calendar);
                }
            });
        }
    }

    public final void setContractSP02Dashboard$app_prodRelease(@NotNull ContractSP02Dashboard contractSP02Dashboard) {
        Intrinsics.checkNotNullParameter(contractSP02Dashboard, "<set-?>");
        this.contractSP02Dashboard = contractSP02Dashboard;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
