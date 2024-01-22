package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractAmbientSoundDashboard;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.repository.ambientsound.AmbientSoundRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentAmbientSoundViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4755a;
    public ContractAmbientSoundDashboard contractAmbientSoundDashboard;
    public LifecycleOwner mLifecycleOwner;

    /* loaded from: classes3.dex */
    public static final class a extends Lambda implements Function1<EntityDailyAmbientSoundData, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(EntityDailyAmbientSoundData entityDailyAmbientSoundData) {
            invoke2(entityDailyAmbientSoundData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(EntityDailyAmbientSoundData entityDailyAmbientSoundData) {
            FragmentAmbientSoundViewModel.this.getContractAmbientSoundDashboard$app_prodRelease().updateDailyLevelData(entityDailyAmbientSoundData);
        }
    }

    /* loaded from: classes3.dex */
    public static final class b extends Lambda implements Function1<List<? extends EntityHourlyAmbientSoundData>, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends EntityHourlyAmbientSoundData> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<? extends EntityHourlyAmbientSoundData> list) {
            List emptyList;
            boolean z;
            if (!AppUtils.isEmpty(list)) {
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<Entry> arrayList2 = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    arrayList.add(PayUtils.getAmPmHourValue(String.valueOf(i)));
                    arrayList2.add(new Entry(i, 0.0f));
                }
                for (EntityHourlyAmbientSoundData entityHourlyAmbientSoundData : list) {
                    String startTime = entityHourlyAmbientSoundData.getStartTime();
                    Intrinsics.checkNotNullExpressionValue(startTime, "hourlyAmbientSoundData.startTime");
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
                            arrayList2.set(parseInt, new Entry(parseInt, (float) entityHourlyAmbientSoundData.getAvgAmbientSound()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                FragmentAmbientSoundViewModel.this.getContractAmbientSoundDashboard$app_prodRelease().updateHourlyLevelData(arrayList2, arrayList);
                return;
            }
            FragmentAmbientSoundViewModel.this.getContractAmbientSoundDashboard$app_prodRelease().updateHourlyLevelData(null, null);
        }
    }

    public FragmentAmbientSoundViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4755a = context;
    }

    public static final void c(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void d(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @NotNull
    public final Context getContext() {
        return this.f4755a;
    }

    @NotNull
    public final ContractAmbientSoundDashboard getContractAmbientSoundDashboard$app_prodRelease() {
        ContractAmbientSoundDashboard contractAmbientSoundDashboard = this.contractAmbientSoundDashboard;
        if (contractAmbientSoundDashboard != null) {
            return contractAmbientSoundDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractAmbientSoundDashboard");
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
        LiveData<EntityDailyAmbientSoundData> dailyDataWithoutFlowValidator;
        Intrinsics.checkNotNullParameter(date, "date");
        if (getContractAmbientSoundDashboard$app_prodRelease().isSyncInProgress()) {
            dailyDataWithoutFlowValidator = AmbientSoundRepository.Companion.getInstance(this.f4755a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4755a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator = AmbientSoundRepository.Companion.getInstance(this.f4755a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4755a).getBleApi().getMacAddress());
        }
        LifecycleOwner mLifecycleOwner = getMLifecycleOwner();
        final a aVar = new a();
        dailyDataWithoutFlowValidator.observe(mLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.b
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentAmbientSoundViewModel.c(Function1.this, obj);
            }
        });
    }

    public final void loadHourlyAmbientSoundData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityHourlyAmbientSoundData>> hourlyData = AmbientSoundRepository.Companion.getInstance(this.f4755a).getHourlyData(date, BleApiManager.getInstance(this.f4755a).getBleApi().getMacAddress());
        LifecycleOwner mLifecycleOwner = getMLifecycleOwner();
        final b bVar = new b();
        hourlyData.observe(mLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.a
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentAmbientSoundViewModel.d(Function1.this, obj);
            }
        });
    }

    public final void setContractAmbientSoundDashboard$app_prodRelease(@NotNull ContractAmbientSoundDashboard contractAmbientSoundDashboard) {
        Intrinsics.checkNotNullParameter(contractAmbientSoundDashboard, "<set-?>");
        this.contractAmbientSoundDashboard = contractAmbientSoundDashboard;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
