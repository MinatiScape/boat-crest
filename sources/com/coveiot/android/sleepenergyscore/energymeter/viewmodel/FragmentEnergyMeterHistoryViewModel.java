package com.coveiot.android.sleepenergyscore.energymeter.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.listener.ContractEnergyMeterHistory;
import com.coveiot.android.sleepenergyscore.sleepscore.SleepScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.coveaccess.energyscore.model.EnergyScoreData;
import com.coveiot.covedb.sleep.DailySleepDataAlias;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentEnergyMeterHistoryViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5728a;
    @NotNull
    public MutableLiveData<List<String>> b;
    @NotNull
    public ArrayList<String> c;
    public ContractEnergyMeterHistory contractEnergyMeterHistory;
    public LifecycleOwner mLifecycleOwner;

    public FragmentEnergyMeterHistoryViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5728a = context;
        this.b = new MutableLiveData<>();
        this.c = new ArrayList<>();
    }

    public final SleepScoreData c(String str) {
        return SleepScoreRepository.Companion.getInstance(this.f5728a).getSleepScoreData(str, BleApiManager.getInstance(this.f5728a).getBleApi().getMacAddress());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList<java.lang.String>, T] */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, com.coveiot.coveaccess.energyscore.model.EnergyScoreData] */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.util.ArrayList] */
    public final void callEnergyMeterApi(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this.c;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new EnergyScoreData();
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new ArrayList();
        e.e(GlobalScope.INSTANCE, null, null, new FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1(objectRef, context, objectRef3, objectRef2, this, null), 3, null);
    }

    public final void callSleepScoreApi(@NotNull ArrayList<String> listOfDates) {
        Intrinsics.checkNotNullParameter(listOfDates, "listOfDates");
        this.c.addAll(listOfDates);
        ArrayList arrayList = new ArrayList();
        if (listOfDates.size() > 0) {
            int size = listOfDates.size();
            for (int i = 0; i < size; i++) {
                String str = listOfDates.get(i);
                Intrinsics.checkNotNullExpressionValue(str, "listOfDates[i]");
                if (getEnergyScoreDataFortheDate(str) == null) {
                    arrayList.add(listOfDates.get(i));
                }
            }
        }
        if (arrayList.size() > 0) {
            h.sort(arrayList);
            Calendar calendarStart = Calendar.getInstance();
            SleepScoreApiCall sleepScoreApiCall = SleepScoreApiCall.INSTANCE;
            calendarStart.setTime(AppUtils.parseDate((String) arrayList.get(0), sleepScoreApiCall.getDateFormat()));
            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(AppUtils.parseDate(listOfDates.get(listOfDates.size() - 1), sleepScoreApiCall.getDateFormat()));
            Intrinsics.checkNotNullExpressionValue(calendarStart, "calendarStart");
            Intrinsics.checkNotNullExpressionValue(calendarEnd, "calendarEnd");
            Context context = this.f5728a;
            String string = context.getResources().getString(R.string.energy_meter_history);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ing.energy_meter_history)");
            sleepScoreApiCall.getSleepScoreBatchApiCall(calendarStart, calendarEnd, context, string);
            LogHelper.d("api call ** in sleep score ", " size of list " + arrayList.size());
            return;
        }
        if (UserDataManager.getInstance(this.f5728a).getLastUpdateEnergyMeterHistoryTimeStamp(BleApiManager.getInstance(this.f5728a).getBleApi().getMacAddress()) == 0) {
            UserDataManager.getInstance(this.f5728a).saveLastUpdateEnergyMeterHistoryTimeStamp(System.currentTimeMillis(), BleApiManager.getInstance(this.f5728a).getBleApi().getMacAddress());
        }
        LogHelper.d("loading graph in EM", "from db");
        loadGraph();
    }

    public final DailyWalkData d(String str) {
        return WalkDBRead.getInstance(this.f5728a).getDailyWalkDataWithDate(str, BleApiManager.getInstance(this.f5728a).getBleApi().getMacAddress());
    }

    @NotNull
    public final ArrayList<String> getCombinedListOfDates() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f5728a;
    }

    @NotNull
    public final ContractEnergyMeterHistory getContractEnergyMeterHistory$sleepenergyscore_prodRelease() {
        ContractEnergyMeterHistory contractEnergyMeterHistory = this.contractEnergyMeterHistory;
        if (contractEnergyMeterHistory != null) {
            return contractEnergyMeterHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractEnergyMeterHistory");
        return null;
    }

    @Nullable
    public final EnergyScoreDbData getEnergyScoreDataFortheDate(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return EnergyScoreRepository.Companion.getInstance(this.f5728a).getEnergyScoreData(date, BleApiManager.getInstance(this.f5728a).getBleApi().getMacAddress());
    }

    @Nullable
    public final List<String> getListOfDates(@Nullable List<String> list, @Nullable List<String> list2) {
        HashSet hashSet = new HashSet();
        if (list != null) {
            hashSet.addAll(list);
        }
        if (list2 != null) {
            hashSet.addAll(list2);
        }
        return CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(hashSet));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.lifecycle.LiveData, T] */
    public final void getListOfDatesFromSleepData() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        Calendar fromDate = Calendar.getInstance();
        fromDate.add(2, -3);
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Intrinsics.checkNotNullExpressionValue(fromDate, "fromDate");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f5728a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        ?? dayWiseList = SleepRepository.Companion.getInstance(this.f5728a).getDayWiseList(fromDate, calendar, connectedDeviceMacAddress);
        objectRef2.element = dayWiseList;
        ((LiveData) dayWiseList).observe(getMLifecycleOwner(), new Observer<List<? extends DailySleepDataAlias>>() { // from class: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterHistoryViewModel$getListOfDatesFromSleepData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@androidx.annotation.Nullable @Nullable List<? extends DailySleepDataAlias> list) {
                Intrinsics.checkNotNull(list);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (list.get(i).value > 0) {
                        objectRef.element.add(list.get(i).date);
                    }
                }
                this.getSleepDataDatesListLiveData().postValue(objectRef.element);
                objectRef2.element.removeObserver(this);
            }
        });
    }

    @NotNull
    public final List<String> getListOfDatesFromWalkData() {
        ArrayList arrayList = new ArrayList();
        for (DailyWalkData dailyWalkData : WalkDBRead.getInstance(this.f5728a).getTotalDailyWalkData(SessionManager.getInstance(this.f5728a).getConnectedDeviceMacAddress())) {
            String str = dailyWalkData.mDate;
            Intrinsics.checkNotNullExpressionValue(str, "walkData.mDate");
            DailyWalkData d = d(str);
            Integer valueOf = d != null ? Integer.valueOf(d.getValue()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 0) {
                arrayList.add(dailyWalkData.mDate);
            }
        }
        return arrayList;
    }

    @NotNull
    public final List<Date> getListOfNotSyncDates() {
        int findDateDifference = RepositoryUtils.findDateDifference(WorkoutUtils.INSTANCE.getDateFromTimeStamp(Long.valueOf(UserDataManager.getInstance(this.f5728a).getLastUpdateEnergyMeterHistoryTimeStamp(BleApiManager.getInstance(this.f5728a).getBleApi().getMacAddress())), "yyyy-MM-dd"));
        LogsHelper.d("maxdays history*** ", String.valueOf(findDateDifference));
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -findDateDifference);
        Date time = calendar.getTime();
        Date time2 = Calendar.getInstance().getTime();
        String formatDate = RepositoryUtils.formatDate(time, "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(startDateEM, \"yyyy-MM-dd\")");
        String formatDate2 = RepositoryUtils.formatDate(time2, "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(endDateEM, \"yyyy-MM-dd\")");
        return Utils.getDatesBetween(formatDate, formatDate2);
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
    public final MutableLiveData<List<String>> getSleepDataDatesListLiveData() {
        return this.b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0086, code lost:
        if (r8.intValue() > 0) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void loadGraph() {
        /*
            Method dump skipped, instructions count: 509
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterHistoryViewModel.loadGraph():void");
    }

    public final void setCombinedListOfDates(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.c = arrayList;
    }

    public final void setContractEnergyMeterHistory$sleepenergyscore_prodRelease(@NotNull ContractEnergyMeterHistory contractEnergyMeterHistory) {
        Intrinsics.checkNotNullParameter(contractEnergyMeterHistory, "<set-?>");
        this.contractEnergyMeterHistory = contractEnergyMeterHistory;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }

    public final void setSleepDataDatesListLiveData(@NotNull MutableLiveData<List<String>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }
}
