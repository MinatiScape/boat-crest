package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.repository.sleep.SleepRepository;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SleepDataViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4284a;

    public SleepDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4284a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4284a;
    }

    @Nullable
    public final LiveData<List<SleepDataModelForLastNight>> getLastNightSleepLiveData() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        return SleepRepository.Companion.getInstance(this.f4284a).getHourlyDataWithoutFlowValidator(calendar, BleApiManager.getInstance(this.f4284a).getBleApi().getMacAddress());
    }
}
