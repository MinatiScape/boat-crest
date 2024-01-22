package com.coveiot.android.respiratoryrate.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.CandleEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5682a;
    public ContractRespiratoryRateDashBoard contractRespiratoryRateDashboard;
    public LifecycleOwner mLifecycleOwner;

    /* loaded from: classes6.dex */
    public static final class a extends Lambda implements Function1<DailyRespiratoryRateEntity, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
            invoke2(dailyRespiratoryRateEntity);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
            RespiratoryRateViewModel.this.getContractRespiratoryRateDashboard().updateDailyLevelData(dailyRespiratoryRateEntity);
        }
    }

    public RespiratoryRateViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5682a = context;
    }

    public static final void b(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @NotNull
    public final Context getContext() {
        return this.f5682a;
    }

    @NotNull
    public final ContractRespiratoryRateDashBoard getContractRespiratoryRateDashboard() {
        ContractRespiratoryRateDashBoard contractRespiratoryRateDashBoard = this.contractRespiratoryRateDashboard;
        if (contractRespiratoryRateDashBoard != null) {
            return contractRespiratoryRateDashBoard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractRespiratoryRateDashboard");
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
    public final ArrayList<Float> getOrderedAccuraciesAsPerGraph(@NotNull ArrayList<Float> accuracies) {
        Intrinsics.checkNotNullParameter(accuracies, "accuracies");
        ArrayList<Float> arrayList = new ArrayList<>();
        arrayList.addAll(accuracies.subList(21, accuracies.size()));
        arrayList.addAll(accuracies.subList(0, 10));
        return arrayList;
    }

    @NotNull
    public final ArrayList<Integer> getOrderedValuesAsPerGraph(@NotNull ArrayList<Integer> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.addAll(values.subList(21, values.size()));
        arrayList.addAll(values.subList(0, 10));
        return arrayList;
    }

    public final void loadDailyData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        String formatDate = AppUtils.formatDate(date.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(date.time, \"yyyy-MM-dd\")");
        LiveData<DailyRespiratoryRateEntity> respiratoryRateLiveData = RespiratoryRateRepository.Companion.getInstance(this.f5682a).getRespiratoryRateLiveData(formatDate, BleApiManager.getInstance(this.f5682a).getBleApi().getMacAddress());
        if (respiratoryRateLiveData != null) {
            LifecycleOwner mLifecycleOwner = getMLifecycleOwner();
            final a aVar = new a();
            respiratoryRateLiveData.observe(mLifecycleOwner, new Observer() { // from class: com.coveiot.android.respiratoryrate.viewmodel.d
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    RespiratoryRateViewModel.b(Function1.this, obj);
                }
            });
        }
    }

    public final void selectDateRangeView(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Integer min;
        Integer min2;
        Integer max;
        Integer max2;
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        String startDate = RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd");
        String endDate = RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f5682a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNull(connectedDeviceMacAddress);
        List<RespiratoryRateListBean> dailyRespiratoryRateRangeBetweenDates = RespiratoryRateRepository.Companion.getInstance(this.f5682a).getDailyRespiratoryRateRangeBetweenDates(startDate, endDate, connectedDeviceMacAddress);
        if (!AppUtils.isEmpty(dailyRespiratoryRateRangeBetweenDates)) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd/MM");
            ArrayList<CandleEntry> arrayList = new ArrayList<>();
            ArrayList<String> arrayList2 = new ArrayList<>();
            Intrinsics.checkNotNull(dailyRespiratoryRateRangeBetweenDates);
            int size = dailyRespiratoryRateRangeBetweenDates.size();
            for (int i = 0; i < size; i++) {
                float f = i;
                RespiratoryRateListBean respiratoryRateListBean = dailyRespiratoryRateRangeBetweenDates.get(i);
                arrayList.add(new CandleEntry(f, (respiratoryRateListBean == null || (max2 = respiratoryRateListBean.getMax()) == null) ? 0.0f : max2.intValue(), dailyRespiratoryRateRangeBetweenDates.get(i).getMin() != null ? min.intValue() : 0.0f, dailyRespiratoryRateRangeBetweenDates.get(i).getMin() != null ? min2.intValue() : 0.0f, dailyRespiratoryRateRangeBetweenDates.get(i).getMax() != null ? max.intValue() : 0.0f));
                try {
                    arrayList2.add(simpleDateFormat2.format(simpleDateFormat.parse(dailyRespiratoryRateRangeBetweenDates.get(i).getDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            getContractRespiratoryRateDashboard().onCandleChartDataLoaded(arrayList, arrayList2);
            getContractRespiratoryRateDashboard().updateRangeLevelData(dailyRespiratoryRateRangeBetweenDates);
            return;
        }
        getContractRespiratoryRateDashboard().onCandleChartDataLoaded(null, null);
        getContractRespiratoryRateDashboard().updateRangeLevelData(CollectionsKt__CollectionsKt.emptyList());
    }

    public final void setContractRespiratoryRateDashboard(@NotNull ContractRespiratoryRateDashBoard contractRespiratoryRateDashBoard) {
        Intrinsics.checkNotNullParameter(contractRespiratoryRateDashBoard, "<set-?>");
        this.contractRespiratoryRateDashboard = contractRespiratoryRateDashBoard;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }
}
