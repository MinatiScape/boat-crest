package com.coveiot.android.bleabstract.services;

import android.content.Context;
import android.os.Handler;
import com.coveiot.android.bleabstract.formatter.CRPFormatter;
import com.coveiot.android.bleabstract.models.ManualHrReading;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP;
import com.coveiot.android.crpsdk.BleConnectHelper;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPGetPastHeartRateDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayHeartRateDataReq;
import com.coveiot.android.crpsdk.api.CRPGetWorkoutDataReq;
import com.coveiot.android.crpsdk.events.CRPResponseEvent;
import com.coveiot.android.crpsdk.events.CRPResponseStatus;
import com.coveiot.android.crpsdk.events.CRPResponseType;
import com.coveiot.android.crpsdk.model.KhCRPHeartRateInfo;
import com.coveiot.android.crpsdk.model.KhCRPWorkoutInfo;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.CRPBleConnection;
import com.crrepa.ble.conn.bean.CRPHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPHistoryHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPMovementHeartRateInfo;
import com.crrepa.ble.conn.listener.CRPHeartRateChangeListener;
import com.crrepa.ble.conn.type.CRPHistoryDynamicRateType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpHeartRateChangeListener$1 implements CRPHeartRateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3703a;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CRPHistoryDynamicRateType.values().length];
            try {
                iArr[CRPHistoryDynamicRateType.FIRST_HEART_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CRPHistoryDynamicRateType.SECOND_HEART_RATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CRPBaseBleService$crpHeartRateChangeListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3703a = cRPBaseBleService;
    }

    public static final void a(final CRPBaseBleService this$0, CRPHistoryDynamicRateType cRPHistoryDynamicRateType, CRPHeartRateInfo cRPHeartRateInfo) {
        String str;
        Handler handler;
        String str2;
        Handler handler2;
        Runnable runnable;
        long j;
        String str3;
        Handler handler3;
        Runnable runnable2;
        long j2;
        String str4;
        Handler handler4;
        boolean z;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpHeartRateChangeListener->onMeasureComplete-> " + cRPHistoryDynamicRateType + " , khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (cRPHistoryDynamicRateType != null && cRPHeartRateInfo != null && cRPHeartRateInfo.getHeartRateType() == CRPHeartRateInfo.HeartRateType.PART_HEART_RATE) {
            List<KhCRPHeartRateInfo> partHeartRateData = PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).getPartHeartRateData();
            if (partHeartRateData != null && (!partHeartRateData.isEmpty())) {
                for (KhCRPHeartRateInfo khCRPHeartRateInfo : partHeartRateData) {
                    Long startTime = khCRPHeartRateInfo.getStartTime();
                    long startTime2 = cRPHeartRateInfo.getStartTime();
                    if (startTime != null && startTime.longValue() == startTime2) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
                if (partHeartRateData == null) {
                    partHeartRateData = new ArrayList<>();
                }
                if (partHeartRateData.size() > 1) {
                    kotlin.collections.h.sortWith(partHeartRateData, new Comparator() { // from class: com.coveiot.android.bleabstract.services.CRPBaseBleService$crpHeartRateChangeListener$1$onMeasureComplete$lambda$2$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return kotlin.comparisons.f.compareValues(((KhCRPHeartRateInfo) t).getStartTime(), ((KhCRPHeartRateInfo) t2).getStartTime());
                        }
                    });
                }
                KhCRPHeartRateInfo khCRPHeartRateInfo2 = new KhCRPHeartRateInfo();
                khCRPHeartRateInfo2.setHeartRateList(cRPHeartRateInfo.getHeartRateList());
                khCRPHeartRateInfo2.setHeartRateType(cRPHeartRateInfo.getHeartRateType());
                khCRPHeartRateInfo2.setStartTime(Long.valueOf(cRPHeartRateInfo.getStartTime()));
                khCRPHeartRateInfo2.setTimeInterval(Integer.valueOf(cRPHeartRateInfo.getTimeInterval()));
                if (partHeartRateData.size() >= 10) {
                    partHeartRateData.set(0, khCRPHeartRateInfo2);
                } else {
                    partHeartRateData.add(khCRPHeartRateInfo2);
                }
                PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).savePartHeartRateData(partHeartRateData);
            }
        }
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPGetWorkoutDataReq)) {
            return;
        }
        handler = this$0.h;
        handler.removeCallbacksAndMessages(null);
        int i = cRPHistoryDynamicRateType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[cRPHistoryDynamicRateType.ordinal()];
        if (i == 1) {
            str2 = this$0.m;
            LogHelper.d(str2, "First Workout Heart Rate At " + System.currentTimeMillis(), ModuleNames.BLEABSTRACT.getModuleName());
            CRPBleConnection bleConnection = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection();
            Intrinsics.checkNotNull(bleConnection);
            bleConnection.queryLastDynamicRate(CRPHistoryDynamicRateType.SECOND_HEART_RATE);
            handler2 = this$0.h;
            runnable = this$0.E;
            j = this$0.j;
            handler2.postDelayed(runnable, j);
        } else if (i != 2) {
            str4 = this$0.m;
            LogHelper.d(str4, "Third Workout Heart Rate At " + System.currentTimeMillis(), ModuleNames.BLEABSTRACT.getModuleName());
            handler4 = this$0.c;
            handler4.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.h0
                @Override // java.lang.Runnable
                public final void run() {
                    CRPBaseBleService$crpHeartRateChangeListener$1.a(CRPBaseBleService.this);
                }
            });
        } else {
            str3 = this$0.m;
            LogHelper.d(str3, "Second Workout Heart Rate At " + System.currentTimeMillis(), ModuleNames.BLEABSTRACT.getModuleName());
            CRPBleConnection bleConnection2 = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection();
            Intrinsics.checkNotNull(bleConnection2);
            bleConnection2.queryLastDynamicRate(CRPHistoryDynamicRateType.THIRD_HEART_RATE);
            handler3 = this$0.h;
            runnable2 = this$0.E;
            j2 = this$0.j;
            handler3.postDelayed(runnable2, j2);
        }
    }

    @Override // com.crrepa.ble.conn.listener.CRPHeartRateChangeListener
    public void on24HourMeasureResult(@Nullable final CRPHeartRateInfo cRPHeartRateInfo) {
        Handler handler;
        handler = this.f3703a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3703a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.i0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpHeartRateChangeListener$1.a(CRPBaseBleService.this, cRPHeartRateInfo);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPHeartRateChangeListener
    public void onHistoryHeartRate(@Nullable List<CRPHistoryHeartRateInfo> list) {
        String str;
        str = this.f3703a.m;
        LogHelper.d(str, "crpHeartRateChangeListener->onHistoryHeartRate-> " + list, ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.crrepa.ble.conn.listener.CRPHeartRateChangeListener
    public void onMeasureComplete(@Nullable final CRPHistoryDynamicRateType cRPHistoryDynamicRateType, @Nullable final CRPHeartRateInfo cRPHeartRateInfo) {
        Handler handler;
        handler = this.f3703a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3703a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.j0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpHeartRateChangeListener$1.a(CRPBaseBleService.this, cRPHistoryDynamicRateType, cRPHeartRateInfo);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPHeartRateChangeListener
    public void onMeasuring(final int i) {
        Handler handler;
        handler = this.f3703a.c;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.g0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpHeartRateChangeListener$1.a(i);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPHeartRateChangeListener
    public void onMovementMeasureResult(@Nullable final List<CRPMovementHeartRateInfo> list) {
        Handler handler;
        handler = this.f3703a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3703a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.k0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpHeartRateChangeListener$1.a(list, cRPBaseBleService);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPHeartRateChangeListener
    public void onOnceMeasureComplete(int i) {
        String str;
        str = this.f3703a.m;
        LogHelper.d(str, "crpHeartRateChangeListener->onOnceMeasureComplete-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
        if (i != 255) {
            ManualHrReading manualHrReading = new ManualHrReading(System.currentTimeMillis(), i, this.f3703a.getMacAddress());
            PreferenceManagerAbstract.getInstance(this.f3703a.getApplicationContext()).saveLastManualHrReading(manualHrReading);
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new CRPBaseBleService$crpHeartRateChangeListener$1$onOnceMeasureComplete$1(manualHrReading, null), 2, null);
        }
    }

    public static final void a(CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CRPBleConnection bleConnection = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection();
        Intrinsics.checkNotNull(bleConnection);
        bleConnection.queryMovementHeartRate();
    }

    public static final void a(CRPBaseBleService this$0, CRPHeartRateInfo cRPHeartRateInfo) {
        String str;
        List<Integer> heartRateList;
        boolean z;
        int i;
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpHeartRateChangeListener->on24HourMeasureResult-> " + cRPHeartRateInfo + " , khCurrentCommand-> " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() != null) {
            if ((this$0.getKhCurrentCommand() instanceof CRPGetTodayHeartRateDataReq) || (this$0.getKhCurrentCommand() instanceof CRPGetPastHeartRateDataReq)) {
                CRPBaseRes cRPBaseRes = new CRPBaseRes();
                CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                cRPBaseRes.setBaseReq(khCurrentCommand);
                if (cRPHeartRateInfo != null && (heartRateList = cRPHeartRateInfo.getHeartRateList()) != null) {
                    Intrinsics.checkNotNullExpressionValue(heartRateList, "heartRateList");
                    List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) heartRateList);
                    if (!mutableList.isEmpty()) {
                        Iterator it = mutableList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = true;
                                break;
                            }
                            Integer num = (Integer) it.next();
                            if (num != null && num.intValue() > 0) {
                                z = false;
                                break;
                            }
                        }
                        if (!z) {
                            if (this$0.getKhCurrentCommand() instanceof CRPGetTodayHeartRateDataReq) {
                                str2 = this$0.m;
                                LogHelper.d(str2, "crpHeartRateChangeListener->on24HourMeasureResult-> Sync Completed.", ModuleNames.BLEABSTRACT.getModuleName());
                                i = 1;
                            } else {
                                i = 0;
                            }
                            CRPHeartRateInfo cRPHeartRateInfo2 = new CRPHeartRateInfo(cRPHeartRateInfo.getStartTime(), CollectionsKt___CollectionsKt.toMutableList((Collection) heartRateList), cRPHeartRateInfo.getTimeInterval(), cRPHeartRateInfo.getHeartRateType());
                            CRPFormatter.Companion companion = CRPFormatter.Companion;
                            Context applicationContext = this$0.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                            cRPBaseRes.setObj(companion.getInstance(applicationContext).convertHrData(CollectionsKt__CollectionsKt.mutableListOf(cRPHeartRateInfo2), i));
                        }
                    }
                }
                CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(cRPBaseRes);
                this$0.setKhCurrentCommand(null);
            }
        }
    }

    public static final void a(int i) {
        BleEventBusManager.getInstance().getEventBus().post(new CRPResponseEvent(CRPResponseType.GET_LIVE_HEALTH_DATA, Integer.valueOf(i), CRPResponseStatus.RESPONSE_STATUS_SUCCESS));
    }

    public static final void a(List list, CRPBaseBleService this$0) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<CRPMovementHeartRateInfo> mutableList = list != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) list) : null;
        str = this$0.m;
        LogHelper.d(str, "crpHeartRateChangeListener->onMovementMeasureResult-> " + mutableList + " , khCurrentCommand-> " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (mutableList != null && (!mutableList.isEmpty())) {
            for (CRPMovementHeartRateInfo cRPMovementHeartRateInfo : mutableList) {
                if (cRPMovementHeartRateInfo.getEndTime() > cRPMovementHeartRateInfo.getStartTime() && PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).getKhCRPWorkoutInfo(Long.valueOf(cRPMovementHeartRateInfo.getStartTime()), Long.valueOf(cRPMovementHeartRateInfo.getEndTime())) == null) {
                    List<KhCRPWorkoutInfo> workoutData = PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).getWorkoutData();
                    if (workoutData == null) {
                        workoutData = new ArrayList<>();
                    }
                    if (workoutData.size() > 1) {
                        kotlin.collections.h.sortWith(workoutData, new Comparator() { // from class: com.coveiot.android.bleabstract.services.CRPBaseBleService$crpHeartRateChangeListener$1$onMovementMeasureResult$lambda$10$lambda$8$$inlined$sortBy$1
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                return kotlin.comparisons.f.compareValues(((KhCRPWorkoutInfo) t).getStartTime(), ((KhCRPWorkoutInfo) t2).getStartTime());
                            }
                        });
                    }
                    KhCRPWorkoutInfo khCRPWorkoutInfo = new KhCRPWorkoutInfo();
                    khCRPWorkoutInfo.setType(cRPMovementHeartRateInfo.getType());
                    khCRPWorkoutInfo.setStartTime(Long.valueOf(cRPMovementHeartRateInfo.getStartTime()));
                    khCRPWorkoutInfo.setEndTime(Long.valueOf(cRPMovementHeartRateInfo.getEndTime()));
                    khCRPWorkoutInfo.setSteps(Integer.valueOf(cRPMovementHeartRateInfo.getSteps()));
                    khCRPWorkoutInfo.setCalories(Integer.valueOf(cRPMovementHeartRateInfo.getCalories()));
                    khCRPWorkoutInfo.setDistance(Integer.valueOf(cRPMovementHeartRateInfo.getDistance()));
                    khCRPWorkoutInfo.setValidTime(Integer.valueOf(cRPMovementHeartRateInfo.getValidTime()));
                    if (workoutData.size() >= 10) {
                        workoutData.set(0, khCRPWorkoutInfo);
                    } else {
                        workoutData.add(khCRPWorkoutInfo);
                    }
                    PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).saveWorkoutData(workoutData);
                }
            }
        }
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPGetWorkoutDataReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        if (mutableList != null) {
            CRPFormatter.Companion companion = CRPFormatter.Companion;
            Context applicationContext = this$0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            cRPBaseRes.setObj(companion.getInstance(applicationContext).convertWorkoutData(this$0.getMacAddress(), mutableList));
        }
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
        str2 = this$0.m;
        LogHelper.d(str2, "Workout Data Fetch Ends At " + System.currentTimeMillis(), ModuleNames.BLEABSTRACT.getModuleName());
    }
}
