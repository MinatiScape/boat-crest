package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.model.HRVData;
import com.coveiot.android.dashboard2.util.HRVDataHelper;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.repository.hrv.datasource.HRVRepository;
import java.util.Calendar;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class HRVDataViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4278a;
    @NotNull
    public final MutableLiveData<HRVData> b;

    public HRVDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4278a = context;
        this.b = new MutableLiveData<>();
        new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HRVDataViewModel.b(HRVDataViewModel.this, (HourlyHRV) obj);
            }
        };
    }

    public static final void b(HRVDataViewModel this$0, HourlyHRV hourlyHRV) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (hourlyHRV != null && hourlyHRV.hrv_avg > 0.0d) {
            Pair<Double, Calendar> hrvBy = HRVDataHelper.INSTANCE.getHrvBy(hourlyHRV);
            HRVData hRVData = new HRVData(0.0d, 0L, 3, null);
            hRVData.setValue(hrvBy.getFirst().doubleValue());
            hRVData.setTimestamp(hrvBy.getSecond().getTimeInMillis());
            this$0.b.postValue(hRVData);
            return;
        }
        this$0.b.postValue(new HRVData(0.0d, 0L, 3, null));
    }

    @NotNull
    public final Context getContext() {
        return this.f4278a;
    }

    @NotNull
    public final MutableLiveData<HRVData> getHRVLiveData() {
        return this.b;
    }

    public final void getLastReadHrv() {
        HourlyHRV latestRecordHourly = HRVRepository.Companion.getInstance(this.f4278a).getLatestRecordHourly(BleApiManager.getInstance(this.f4278a).getBleApi().getMacAddress());
        if (latestRecordHourly != null && latestRecordHourly.hrv_avg > 0.0d) {
            Pair<Double, Calendar> hrvBy = HRVDataHelper.INSTANCE.getHrvBy(latestRecordHourly);
            HRVData hRVData = new HRVData(0.0d, 0L, 3, null);
            hRVData.setValue(hrvBy.getFirst().doubleValue());
            hRVData.setTimestamp(hrvBy.getSecond().getTimeInMillis());
            this.b.postValue(hRVData);
            return;
        }
        this.b.postValue(new HRVData(0.0d, 0L, 3, null));
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
    }
}
