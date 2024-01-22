package com.coveiot.repository.datasync;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.formatter.SMAActivityFormatter;
import com.coveiot.android.bleabstract.formatter.SMAHRFormatter;
import com.coveiot.android.bleabstract.formatter.SMAStressFormatter;
import com.coveiot.android.bleabstract.formatter.SMATemperatureFormatter;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.TemperatureHourData;
import com.coveiot.coveaccess.CoveActivityTracker;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.ecgsession.ECGSessionApi;
import com.coveiot.coveaccess.ecgsession.model.PostECGSessionData;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.fitness.model.GetGoalHistoryResponse;
import com.coveiot.coveaccess.manualdata.ManualDataApiManager;
import com.coveiot.coveaccess.manualdata.SaveManualSPO2DataRes;
import com.coveiot.coveaccess.mediauplaod.MediaUpload;
import com.coveiot.coveaccess.mediauplaod.model.MediaClassType;
import com.coveiot.coveaccess.mediauplaod.model.MediaUploadReq;
import com.coveiot.coveaccess.mediauplaod.model.MediaUploadRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sedentaryalerts.SedentaryAlertsApiManager;
import com.coveiot.coveaccess.sedentaryalerts.model.GetSedentaryAlertsDataRes;
import com.coveiot.coveaccess.sedentaryalerts.model.SedentaryAlertsDataBean;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.repository.Error;
import com.coveiot.repository.RepositoryModuleNames;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.SyncErrorTypes;
import com.coveiot.repository.ambientsound.datasources.db.write.AmbientSoundDBWrite;
import com.coveiot.repository.ambientsound.datasources.server.AmbientSoundApiHandler;
import com.coveiot.repository.bp.datasources.db.write.BpDBWrite;
import com.coveiot.repository.bp.datasources.server.BpApiHandler;
import com.coveiot.repository.datasync.domainlogic.APIResponseListner;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.repository.ecg.ECGRepository;
import com.coveiot.repository.ecg.db.write.ECGDBWrite;
import com.coveiot.repository.heartrate.datasources.db.read.HeartRateDBRead;
import com.coveiot.repository.heartrate.datasources.db.write.HeartRateDBWrite;
import com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler;
import com.coveiot.repository.hrv.datasource.db.write.HRVDBWrite;
import com.coveiot.repository.hrv.datasource.server.HRVApiHandler;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler;
import com.coveiot.repository.manualdata.datasources.server.ServerFormator;
import com.coveiot.repository.periodicspo2.PeriodicSpo2DBWrite;
import com.coveiot.repository.periodicspo2.server.PeriodicSpo2ApiHandler;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.repository.sedentary.datasource.db.SedentaryDBRepo;
import com.coveiot.repository.sedentary.datasource.server.FormatorServerToEntity;
import com.coveiot.repository.sleep.datasources.db.write.SleepDBWrite;
import com.coveiot.repository.sleep.datasources.server.SleepApiHandler;
import com.coveiot.repository.stress.datasources.db.read.StressDBRead;
import com.coveiot.repository.stress.datasources.db.write.StressDBWrite;
import com.coveiot.repository.stress.datasources.server.StressApiHandler;
import com.coveiot.repository.temperature.datasources.db.read.TemperatureDBRead;
import com.coveiot.repository.temperature.datasources.db.write.TemperatureDBWrite;
import com.coveiot.repository.temperature.datasources.server.TemperatureApiHandler;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.repository.walk.datasources.db.write.WalkDBWrite;
import com.coveiot.repository.walk.datasources.server.WalkApiHandler;
import com.coveiot.utils.utility.LogHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ServerSyncUtils {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public Context f7359a;
    @NotNull
    public final String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public String e;
    @Nullable
    public String f;
    @Nullable
    public String g;
    @Nullable
    public String h;
    @Nullable
    public String i;
    @Nullable
    public String j;
    @Nullable
    public String k;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<ServerSyncUtils, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, ServerSyncUtils> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, ServerSyncUtils.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final ServerSyncUtils invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new ServerSyncUtils(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$saveDataToServer$1", f = "ServerSyncUtils.kt", i = {}, l = {1535}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes9.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $serial_no;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$serial_no = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$serial_no, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ServerSyncUtils serverSyncUtils = ServerSyncUtils.this;
                String str = this.$serial_no;
                this.label = 1;
                if (serverSyncUtils.a(str, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public ServerSyncUtils(Context context) {
        this.b = "ServerSyncUtils";
        this.f7359a = context;
    }

    public /* synthetic */ ServerSyncUtils(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final int a(GetGoalHistoryResponse.DataBean.Item item, GetGoalHistoryResponse.DataBean.Item item2) {
        String startDate = item.getStartDate();
        Intrinsics.checkNotNull(startDate);
        String startDate2 = item2.getStartDate();
        Intrinsics.checkNotNull(startDate2);
        return startDate.compareTo(startDate2);
    }

    public static final void access$fetachBPDataFromServer(final ServerSyncUtils serverSyncUtils, final Calendar calendar, final Calendar calendar2, final String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        BpApiHandler.Companion.fetchDatafor(calendar, calendar2, new BpApiHandler.BPDataApiResultListner() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetachBPDataFromServer$1
            @Override // com.coveiot.repository.bp.datasources.server.BpApiHandler.BPDataApiResultListner
            public void onBPDataReceived(@NotNull EntityDailyBp dailyBpData, @NotNull ArrayList<EntityHourlyBp> hourlyBpData, boolean z) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Context context6;
                Context context7;
                Context context8;
                Context context9;
                Intrinsics.checkNotNullParameter(dailyBpData, "dailyBpData");
                Intrinsics.checkNotNullParameter(hourlyBpData, "hourlyBpData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onHeartRateDataReceived sussess :");
                dailyBpData.serial_no = str;
                int size = hourlyBpData.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        hourlyBpData.get(i).serial_no = str;
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context = ServerSyncUtils.this.f7359a;
                Context context10 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                BpDBWrite.getInstance(context).insert(dailyBpData);
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                BpDBWrite.getInstance(context2).insert(hourlyBpData);
                if (ServerSyncUtils.this.getBpLastSyncDate() == null) {
                    ServerSyncUtils.this.setBpLastSyncDate(dailyBpData.date);
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getBpLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyBpData.date, "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setBpLastSyncDate(dailyBpData.date);
                }
                if (z) {
                    context3 = ServerSyncUtils.this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context3 = null;
                    }
                    EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context3).getDeviceInfoBy(str);
                    Intrinsics.checkNotNull(deviceInfoBy, "null cannot be cast to non-null type com.coveiot.covedb.deviceinfo.EntityDeviceInfo");
                    deviceInfoBy.setLastSyncDateBp(ServerSyncUtils.this.getBpLastSyncDate());
                    context4 = ServerSyncUtils.this.f7359a;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context4 = null;
                    }
                    DeviceInfoRepository.getInstance(context4).insertDeviceInfo(deviceInfoBy);
                    context5 = ServerSyncUtils.this.f7359a;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context5 = null;
                    }
                    if (!BleApiManager.getInstance(context5).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                        context6 = ServerSyncUtils.this.f7359a;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context6 = null;
                        }
                        if (!BleApiManager.getInstance(context6).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                            context7 = ServerSyncUtils.this.f7359a;
                            if (context7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context7 = null;
                            }
                            if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                                context8 = ServerSyncUtils.this.f7359a;
                                if (context8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                    context8 = null;
                                }
                                if (!BleApiManager.getInstance(context8).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                    context9 = ServerSyncUtils.this.f7359a;
                                    if (context9 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("context");
                                    } else {
                                        context10 = context9;
                                    }
                                    if (!BleApiManager.getInstance(context10).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                        ServerSyncUtils.this.a(syncResultListner);
                                        return;
                                    } else {
                                        ServerSyncUtils.access$fetchAmbientSoundDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                        return;
                                    }
                                }
                                ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                return;
                            }
                            ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                            return;
                        }
                        ServerSyncUtils.access$fetchStressDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetchTemperatureDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                }
            }

            @Override // com.coveiot.repository.bp.datasources.server.BpApiHandler.BPDataApiResultListner
            public void onError(@NotNull String message) {
                String str2;
                String str3;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onHeartRateDataReceived error :" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    context = ServerSyncUtils.this.f7359a;
                    Context context6 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                        context2 = ServerSyncUtils.this.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        if (!BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                            context3 = ServerSyncUtils.this.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context3 = null;
                            }
                            if (!BleApiManager.getInstance(context3).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                                context4 = ServerSyncUtils.this.f7359a;
                                if (context4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                    context4 = null;
                                }
                                if (!BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                    context5 = ServerSyncUtils.this.f7359a;
                                    if (context5 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("context");
                                    } else {
                                        context6 = context5;
                                    }
                                    if (!BleApiManager.getInstance(context6).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                        ServerSyncUtils.this.a(syncResultListner);
                                        return;
                                    } else {
                                        ServerSyncUtils.access$fetchAmbientSoundDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                        return;
                                    }
                                }
                                ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                return;
                            }
                            ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                            return;
                        }
                        ServerSyncUtils.access$fetchStressDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetchTemperatureDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                    return;
                }
                str3 = ServerSyncUtils.this.b;
                LogHelper.d(str3, "onHeartRateDataReceived error :" + message);
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }
        });
    }

    public static final void access$fetchAmbientSoundDataFromServer(final ServerSyncUtils serverSyncUtils, Calendar calendar, Calendar calendar2, final String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        AmbientSoundApiHandler.Companion.fetchDataFor(calendar, calendar2, new AmbientSoundApiHandler.AmbientSoundDataApiResultListener() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchAmbientSoundDataFromServer$1
            @Override // com.coveiot.repository.ambientsound.datasources.server.AmbientSoundApiHandler.AmbientSoundDataApiResultListener
            public void onAmbientSoundDataReceived(@NotNull EntityDailyAmbientSoundData dailyAmbientSoundData, @NotNull ArrayList<EntityHourlyAmbientSoundData> hourlyAmbientSoundData, boolean z) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Intrinsics.checkNotNullParameter(dailyAmbientSoundData, "dailyAmbientSoundData");
                Intrinsics.checkNotNullParameter(hourlyAmbientSoundData, "hourlyAmbientSoundData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onHeartRateDataReceived success :");
                dailyAmbientSoundData.setSerialNo(str);
                int size = hourlyAmbientSoundData.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        hourlyAmbientSoundData.get(i).serial_no = str;
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context = ServerSyncUtils.this.f7359a;
                Context context5 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                AmbientSoundDBWrite.getInstance(context).insertDailyAmbientSound(dailyAmbientSoundData);
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                AmbientSoundDBWrite.getInstance(context2).insertHourlyAmbientSoundDataList(hourlyAmbientSoundData);
                if (ServerSyncUtils.this.getAmbientSoundLastSyncDate() == null) {
                    ServerSyncUtils.this.setAmbientSoundLastSyncDate(dailyAmbientSoundData.getDate());
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getAmbientSoundLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyAmbientSoundData.getDate(), "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setAmbientSoundLastSyncDate(dailyAmbientSoundData.getDate());
                }
                if (z) {
                    context3 = ServerSyncUtils.this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context3 = null;
                    }
                    EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context3).getDeviceInfoBy(str);
                    Intrinsics.checkNotNull(deviceInfoBy, "null cannot be cast to non-null type com.coveiot.covedb.deviceinfo.EntityDeviceInfo");
                    deviceInfoBy.setLastSyncDateAmbientSound(ServerSyncUtils.this.getAmbientSoundLastSyncDate());
                    context4 = ServerSyncUtils.this.f7359a;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    } else {
                        context5 = context4;
                    }
                    DeviceInfoRepository.getInstance(context5).insertDeviceInfo(deviceInfoBy);
                    ServerSyncUtils.this.a(syncResultListner);
                }
            }

            @Override // com.coveiot.repository.ambientsound.datasources.server.AmbientSoundApiHandler.AmbientSoundDataApiResultListener
            public void onError(@NotNull String message) {
                String str2;
                String str3;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onAmbientSoundDataReceived error :" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    ServerSyncUtils.this.a(syncResultListner);
                    return;
                }
                str3 = ServerSyncUtils.this.b;
                LogHelper.d(str3, " onAmbientSoundDataReceived error :" + message);
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }
        });
    }

    public static final void access$fetchHeartRateDataFromServer(final ServerSyncUtils serverSyncUtils, final Calendar calendar, final Calendar calendar2, final String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        HeartRateApiHandler.Companion.fetchDatafor(calendar, calendar2, new HeartRateApiHandler.HeartRateDataApiResultListner() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchHeartRateDataFromServer$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$fetchHeartRateDataFromServer$1$onHeartRateDataReceived$1", f = "ServerSyncUtils.kt", i = {}, l = {985, 1001}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ String $serial_no;
                public int label;
                public final /* synthetic */ ServerSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ServerSyncUtils serverSyncUtils, String str, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = serverSyncUtils;
                    this.$serial_no = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$serial_no, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Context context;
                    Context context2;
                    Context context3;
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (DelayKt.delay(500L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    context = this.this$0.f7359a;
                    Context context4 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (RepositoryUtils.isSmaDevice(context)) {
                        LogHelper.d("SMAHRFormatter", "heartRateLastSyncDate -> " + this.this$0.getHeartRateLastSyncDate());
                        context2 = this.this$0.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        List<EntityHourlyHeartRateData> hourlyHeartRateData = HeartRateDBRead.getInstance(context2).getHourlyHeartRateData(this.this$0.getHeartRateLastSyncDate(), this.$serial_no);
                        StringBuilder sb = new StringBuilder();
                        sb.append("hourlyHeartRates.size -> ");
                        sb.append(hourlyHeartRateData != null ? Boxing.boxInt(hourlyHeartRateData.size()) : null);
                        LogHelper.d("SMAHRFormatter", sb.toString());
                        if (hourlyHeartRateData != null) {
                            ServerSyncUtils serverSyncUtils = this.this$0;
                            SMAHRFormatter.Companion companion = SMAHRFormatter.Companion;
                            context3 = serverSyncUtils.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            } else {
                                context4 = context3;
                            }
                            List<HeartRateHourData> responseHeartRateHourData = RepositoryUtils.getResponseHeartRateHourData(hourlyHeartRateData);
                            Intrinsics.checkNotNullExpressionValue(responseHeartRateHourData, "getResponseHeartRateHour…                        )");
                            this.label = 2;
                            if (companion.getInstance(context4).checkAndUpdateHRDataFromServerToDb(responseHeartRateHourData, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler.HeartRateDataApiResultListner
            public void onError(@NotNull String message) {
                String str2;
                String str3;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Context context6;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onHeartRateDataReceived error :" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    context = ServerSyncUtils.this.f7359a;
                    Context context7 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isBpSupported()) {
                        context2 = ServerSyncUtils.this.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        if (!BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                            context3 = ServerSyncUtils.this.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context3 = null;
                            }
                            if (!BleApiManager.getInstance(context3).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                                context4 = ServerSyncUtils.this.f7359a;
                                if (context4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                    context4 = null;
                                }
                                if (!BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                                    context5 = ServerSyncUtils.this.f7359a;
                                    if (context5 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("context");
                                        context5 = null;
                                    }
                                    if (!BleApiManager.getInstance(context5).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                        context6 = ServerSyncUtils.this.f7359a;
                                        if (context6 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("context");
                                        } else {
                                            context7 = context6;
                                        }
                                        if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                            ServerSyncUtils.this.a(syncResultListner);
                                            return;
                                        } else {
                                            ServerSyncUtils.access$fetchAmbientSoundDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                            return;
                                        }
                                    }
                                    ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                    return;
                                }
                                ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                return;
                            }
                            ServerSyncUtils.access$fetchStressDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                            return;
                        }
                        ServerSyncUtils.access$fetchTemperatureDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetachBPDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                    return;
                }
                str3 = ServerSyncUtils.this.b;
                LogHelper.d(str3, " onHeartRateDataReceived error :" + message);
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }

            @Override // com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler.HeartRateDataApiResultListner
            public void onHeartRateDataReceived(@NotNull EntityDailyHeartRateData dailyHeartRateData, @NotNull ArrayList<EntityHourlyHeartRateData> hourlyHeartRateData, boolean z) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Context context6;
                Context context7;
                Context context8;
                Context context9;
                Context context10;
                Intrinsics.checkNotNullParameter(dailyHeartRateData, "dailyHeartRateData");
                Intrinsics.checkNotNullParameter(hourlyHeartRateData, "hourlyHeartRateData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onHeartRateDataReceived success :");
                dailyHeartRateData.setSerialNo(str);
                int size = hourlyHeartRateData.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        hourlyHeartRateData.get(i).serial_no = str;
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context = ServerSyncUtils.this.f7359a;
                Context context11 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                HeartRateDBWrite.getInstance(context).insertDailyHeartRate(dailyHeartRateData);
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                HeartRateDBWrite.getInstance(context2).inserHeartRateDataList(hourlyHeartRateData);
                if (ServerSyncUtils.this.getHeartRateLastSyncDate() == null) {
                    ServerSyncUtils.this.setHeartRateLastSyncDate(dailyHeartRateData.getDate());
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getHeartRateLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyHeartRateData.getDate(), "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setHeartRateLastSyncDate(dailyHeartRateData.getDate());
                }
                if (z) {
                    context3 = ServerSyncUtils.this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context3 = null;
                    }
                    EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context3).getDeviceInfoBy(str);
                    Intrinsics.checkNotNull(deviceInfoBy, "null cannot be cast to non-null type com.coveiot.covedb.deviceinfo.EntityDeviceInfo");
                    deviceInfoBy.setLastSyncDateHr(ServerSyncUtils.this.getHeartRateLastSyncDate());
                    context4 = ServerSyncUtils.this.f7359a;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context4 = null;
                    }
                    DeviceInfoRepository.getInstance(context4).insertDeviceInfo(deviceInfoBy);
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(ServerSyncUtils.this, str, null), 2, null);
                    context5 = ServerSyncUtils.this.f7359a;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context5 = null;
                    }
                    if (!BleApiManager.getInstance(context5).getBleApi().getDeviceSupportedFeatures().isBpSupported()) {
                        context6 = ServerSyncUtils.this.f7359a;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context6 = null;
                        }
                        if (!BleApiManager.getInstance(context6).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                            context7 = ServerSyncUtils.this.f7359a;
                            if (context7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context7 = null;
                            }
                            if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                                context8 = ServerSyncUtils.this.f7359a;
                                if (context8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                    context8 = null;
                                }
                                if (!BleApiManager.getInstance(context8).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                                    context9 = ServerSyncUtils.this.f7359a;
                                    if (context9 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("context");
                                        context9 = null;
                                    }
                                    if (!BleApiManager.getInstance(context9).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                        context10 = ServerSyncUtils.this.f7359a;
                                        if (context10 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("context");
                                        } else {
                                            context11 = context10;
                                        }
                                        if (!BleApiManager.getInstance(context11).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                            ServerSyncUtils.this.a(syncResultListner);
                                            return;
                                        } else {
                                            ServerSyncUtils.access$fetchAmbientSoundDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                            return;
                                        }
                                    }
                                    ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                    return;
                                }
                                ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                return;
                            }
                            ServerSyncUtils.access$fetchStressDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                            return;
                        }
                        ServerSyncUtils.access$fetchTemperatureDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetachBPDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                }
            }
        });
    }

    public static final void access$fetchNewHRVDataFromServer(final ServerSyncUtils serverSyncUtils, final Calendar calendar, final Calendar calendar2, String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        HRVApiHandler.Companion.fetchDatafor(calendar, calendar2, new HRVApiHandler.StressApiResultListener() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchNewHRVDataFromServer$1
            @Override // com.coveiot.repository.hrv.datasource.server.HRVApiHandler.StressApiResultListener
            public void onError(@NotNull String message) {
                String str2;
                Context context;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "nStressDataReceived error :" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    context = ServerSyncUtils.this.f7359a;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                        ServerSyncUtils.this.a(syncResultListner);
                        return;
                    }
                    ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                    Calendar calendar3 = calendar;
                    Calendar calendar4 = calendar2;
                    String serial_no = serverSyncUtils2.getSerial_no();
                    Intrinsics.checkNotNull(serial_no);
                    ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar3, calendar4, serial_no, syncResultListner);
                    return;
                }
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }

            @Override // com.coveiot.repository.hrv.datasource.server.HRVApiHandler.StressApiResultListener
            public void onStressDataReceived(@NotNull DailyHRV dailyHRV, @NotNull ArrayList<HourlyHRV> hourlyHRVData, boolean z) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Intrinsics.checkNotNullParameter(dailyHRV, "dailyHRV");
                Intrinsics.checkNotNullParameter(hourlyHRVData, "hourlyHRVData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onHRVDataRecieved success :");
                String serial_no = ServerSyncUtils.this.getSerial_no();
                Intrinsics.checkNotNull(serial_no);
                dailyHRV.mac_address = serial_no;
                int size = hourlyHRVData.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        hourlyHRVData.get(i).setMacAddress(ServerSyncUtils.this.getSerial_no());
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context = ServerSyncUtils.this.f7359a;
                Context context4 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                HRVDBWrite.getInstance(context).insert(dailyHRV);
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                HRVDBWrite.getInstance(context2).insertHourlyList(hourlyHRVData);
                if (ServerSyncUtils.this.getStressLastSyncDate() == null) {
                    ServerSyncUtils.this.setStressLastSyncDate(dailyHRV.mDate);
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getStressLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyHRV.mDate, "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setStressLastSyncDate(dailyHRV.mDate);
                }
                if (z) {
                    context3 = ServerSyncUtils.this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    } else {
                        context4 = context3;
                    }
                    if (!BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                        ServerSyncUtils.this.a(syncResultListner);
                        return;
                    }
                    ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                    Calendar calendar3 = calendar;
                    Calendar calendar4 = calendar2;
                    String serial_no2 = serverSyncUtils2.getSerial_no();
                    Intrinsics.checkNotNull(serial_no2);
                    ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar3, calendar4, serial_no2, syncResultListner);
                }
            }
        });
    }

    public static final void access$fetchPeriodicSpo2DataFromServer(final ServerSyncUtils serverSyncUtils, final Calendar calendar, final Calendar calendar2, final String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        LogHelper.d("periodicspo2", "fetchPeriodicSpo2DataFromServer");
        PeriodicSpo2ApiHandler.Companion.fetchDatafor(calendar, calendar2, new PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchPeriodicSpo2DataFromServer$1
            @Override // com.coveiot.repository.periodicspo2.server.PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner
            public void onError(@NotNull String message) {
                Context context;
                Context context2;
                Context context3;
                Intrinsics.checkNotNullParameter(message, "message");
                LogHelper.d("periodicspo2", "fetchPeriodicSpo2DataFromServer error:" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    context = ServerSyncUtils.this.f7359a;
                    Context context4 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                        context2 = ServerSyncUtils.this.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        if (!BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                            context3 = ServerSyncUtils.this.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            } else {
                                context4 = context3;
                            }
                            if (!BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                ServerSyncUtils.this.a(syncResultListner);
                                return;
                            }
                            ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                            Calendar calendar3 = calendar;
                            Calendar calendar4 = calendar2;
                            String serial_no = serverSyncUtils2.getSerial_no();
                            Intrinsics.checkNotNull(serial_no);
                            ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar3, calendar4, serial_no, syncResultListner);
                            return;
                        }
                        ServerSyncUtils serverSyncUtils3 = ServerSyncUtils.this;
                        Calendar calendar5 = calendar;
                        Calendar calendar6 = calendar2;
                        String serial_no2 = serverSyncUtils3.getSerial_no();
                        Intrinsics.checkNotNull(serial_no2);
                        ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils3, calendar5, calendar6, serial_no2, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                    return;
                }
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }

            @Override // com.coveiot.repository.periodicspo2.server.PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner
            public void onPeriodicSpo2DataReceived(@NotNull DailyPeriodicSpo2 dailyPeriodicSpo2, @NotNull ArrayList<EntityHourlySpo2> hourlyPeriodicSpo2, boolean z) {
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Context context6;
                Intrinsics.checkNotNullParameter(dailyPeriodicSpo2, "dailyPeriodicSpo2");
                Intrinsics.checkNotNullParameter(hourlyPeriodicSpo2, "hourlyPeriodicSpo2");
                String serial_no = ServerSyncUtils.this.getSerial_no();
                Intrinsics.checkNotNull(serial_no);
                dailyPeriodicSpo2.mac_address = serial_no;
                int size = hourlyPeriodicSpo2.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        hourlyPeriodicSpo2.get(i).mac_address = str;
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context = ServerSyncUtils.this.f7359a;
                Context context7 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                PeriodicSpo2DBWrite.getInstance(context).insertPeriodicSpo2(dailyPeriodicSpo2);
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                PeriodicSpo2DBWrite.getInstance(context2).insertSpo2HourlyList(hourlyPeriodicSpo2);
                if (ServerSyncUtils.this.getPeriodicSpo2LastSyncDate() == null) {
                    ServerSyncUtils.this.setPeriodicSpo2LastSyncDate(dailyPeriodicSpo2.mDate);
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getPeriodicSpo2LastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyPeriodicSpo2.mDate, "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setPeriodicSpo2LastSyncDate(dailyPeriodicSpo2.mDate);
                }
                if (z) {
                    LogHelper.d("periodicspo2", "complete fetch data");
                    context3 = ServerSyncUtils.this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context3 = null;
                    }
                    EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context3).getDeviceInfoBy(ServerSyncUtils.this.getSerial_no());
                    if (deviceInfoBy == null) {
                        deviceInfoBy = new EntityDeviceInfo();
                        String serial_no2 = ServerSyncUtils.this.getSerial_no();
                        Intrinsics.checkNotNull(serial_no2);
                        deviceInfoBy.setMacAddress(serial_no2);
                    }
                    deviceInfoBy.setLastSyncDatePeriodicSpo2(ServerSyncUtils.this.getPeriodicSpo2LastSyncDate());
                    context4 = ServerSyncUtils.this.f7359a;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context4 = null;
                    }
                    DeviceInfoRepository.getInstance(context4).insertDeviceInfo(deviceInfoBy);
                    context5 = ServerSyncUtils.this.f7359a;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context5 = null;
                    }
                    if (!BleApiManager.getInstance(context5).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                        context6 = ServerSyncUtils.this.f7359a;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                        } else {
                            context7 = context6;
                        }
                        if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                            ServerSyncUtils.this.a(syncResultListner);
                            return;
                        }
                        ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                        Calendar calendar3 = calendar;
                        Calendar calendar4 = calendar2;
                        String serial_no3 = serverSyncUtils2.getSerial_no();
                        Intrinsics.checkNotNull(serial_no3);
                        ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar3, calendar4, serial_no3, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                }
            }
        });
    }

    public static final void access$fetchSleepDataFromServer(final ServerSyncUtils serverSyncUtils, final Calendar calendar, final Calendar calendar2, final String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        SleepApiHandler.Companion.fetchDatafor(calendar, calendar2, new SleepApiHandler.SleepDataApiResultListner() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchSleepDataFromServer$1
            @Override // com.coveiot.repository.sleep.datasources.server.SleepApiHandler.SleepDataApiResultListner
            public void onError(@NotNull String message) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Context context6;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onSleepDataReceived error :" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    context = ServerSyncUtils.this.f7359a;
                    Context context7 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported()) {
                        context2 = ServerSyncUtils.this.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        if (!BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isBpSupported()) {
                            context3 = ServerSyncUtils.this.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context3 = null;
                            }
                            if (!BleApiManager.getInstance(context3).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                                context4 = ServerSyncUtils.this.f7359a;
                                if (context4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                    context4 = null;
                                }
                                if (!BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                                    context5 = ServerSyncUtils.this.f7359a;
                                    if (context5 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("context");
                                        context5 = null;
                                    }
                                    if (!BleApiManager.getInstance(context5).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                                        context6 = ServerSyncUtils.this.f7359a;
                                        if (context6 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("context");
                                        } else {
                                            context7 = context6;
                                        }
                                        if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                            ServerSyncUtils.this.a(syncResultListner);
                                            return;
                                        } else {
                                            ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                            return;
                                        }
                                    }
                                    ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                    return;
                                }
                                ServerSyncUtils.access$fetchStressDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                return;
                            }
                            ServerSyncUtils.access$fetchTemperatureDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                            return;
                        }
                        ServerSyncUtils.access$fetachBPDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetchHeartRateDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                    return;
                }
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }

            @Override // com.coveiot.repository.sleep.datasources.server.SleepApiHandler.SleepDataApiResultListner
            public void onSleepDataReceived(@NotNull DailySleepData dailySleepData, @NotNull ArrayList<HourlySleepData> hourlySleepData, boolean z) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Context context6;
                Context context7;
                Context context8;
                Context context9;
                Context context10;
                Context context11;
                Context context12;
                Intrinsics.checkNotNullParameter(dailySleepData, "dailySleepData");
                Intrinsics.checkNotNullParameter(hourlySleepData, "hourlySleepData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onSleepDataReceived :");
                dailySleepData.mac_address = str;
                context = ServerSyncUtils.this.f7359a;
                Context context13 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                dailySleepData.setSleepTarget(ProfileDBRead.getInstance(context).getLatestProfileData().sleepTarget);
                int i = 0;
                int size = hourlySleepData.size() - 1;
                if (size >= 0) {
                    while (true) {
                        hourlySleepData.get(i).mac_address = str;
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                SleepDBWrite.getInstance(context2).insertDailySleepData(dailySleepData);
                context3 = ServerSyncUtils.this.f7359a;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context3 = null;
                }
                SleepDBWrite.getInstance(context3).insertHourlySleepData(hourlySleepData);
                if (ServerSyncUtils.this.getSleepLastSyncDate() == null) {
                    ServerSyncUtils.this.setSleepLastSyncDate(dailySleepData.getDate());
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getSleepLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailySleepData.getDate(), "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setSleepLastSyncDate(dailySleepData.getDate());
                }
                if (z) {
                    context4 = ServerSyncUtils.this.f7359a;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context4 = null;
                    }
                    EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context4).getDeviceInfoBy(str);
                    Intrinsics.checkNotNull(deviceInfoBy, "null cannot be cast to non-null type com.coveiot.covedb.deviceinfo.EntityDeviceInfo");
                    deviceInfoBy.setLastSyncDateSleep(ServerSyncUtils.this.getSleepLastSyncDate());
                    context5 = ServerSyncUtils.this.f7359a;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context5 = null;
                    }
                    DeviceInfoRepository.getInstance(context5).insertDeviceInfo(deviceInfoBy);
                    context6 = ServerSyncUtils.this.f7359a;
                    if (context6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context6 = null;
                    }
                    if (!BleApiManager.getInstance(context6).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported()) {
                        context7 = ServerSyncUtils.this.f7359a;
                        if (context7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context7 = null;
                        }
                        if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isBpSupported()) {
                            context8 = ServerSyncUtils.this.f7359a;
                            if (context8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context8 = null;
                            }
                            if (!BleApiManager.getInstance(context8).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                                context9 = ServerSyncUtils.this.f7359a;
                                if (context9 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                    context9 = null;
                                }
                                if (!BleApiManager.getInstance(context9).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                                    context10 = ServerSyncUtils.this.f7359a;
                                    if (context10 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("context");
                                        context10 = null;
                                    }
                                    if (!BleApiManager.getInstance(context10).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                                        context11 = ServerSyncUtils.this.f7359a;
                                        if (context11 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("context");
                                            context11 = null;
                                        }
                                        if (!BleApiManager.getInstance(context11).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                            context12 = ServerSyncUtils.this.f7359a;
                                            if (context12 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                            } else {
                                                context13 = context12;
                                            }
                                            if (!BleApiManager.getInstance(context13).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                                ServerSyncUtils.this.a(syncResultListner);
                                                return;
                                            } else {
                                                ServerSyncUtils.access$fetchAmbientSoundDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                                return;
                                            }
                                        }
                                        ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                        return;
                                    }
                                    ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                    return;
                                }
                                ServerSyncUtils.access$fetchStressDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                return;
                            }
                            ServerSyncUtils.access$fetchTemperatureDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                            return;
                        }
                        ServerSyncUtils.access$fetachBPDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetchHeartRateDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                }
            }
        });
    }

    public static final void access$fetchStressDataFromServer(final ServerSyncUtils serverSyncUtils, final Calendar calendar, final Calendar calendar2, String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        StressApiHandler.Companion companion = StressApiHandler.Companion;
        Calendar calendar3 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar3, "getInstance()");
        companion.fetchDatafor(calendar, calendar3, new StressApiHandler.StressApiResultListener() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchStressDataFromServer$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$fetchStressDataFromServer$1$onStressDataReceived$1", f = "ServerSyncUtils.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.Y2, 666}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ServerSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ServerSyncUtils serverSyncUtils, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = serverSyncUtils;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Context context;
                    Context context2;
                    Context context3;
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (DelayKt.delay(500L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    context = this.this$0.f7359a;
                    Context context4 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (RepositoryUtils.isSmaDevice(context)) {
                        context2 = this.this$0.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        List<HourlyStress> hourlyStressData = StressDBRead.getInstance(context2).getHourlyStressData(this.this$0.getStressLastSyncDate(), this.this$0.getSerial_no());
                        StringBuilder sb = new StringBuilder();
                        sb.append("hourlyTemperatures.size -> ");
                        sb.append(hourlyStressData != null ? Boxing.boxInt(hourlyStressData.size()) : null);
                        LogHelper.d("SMATemperatureFormatter", sb.toString());
                        if (hourlyStressData != null) {
                            ServerSyncUtils serverSyncUtils = this.this$0;
                            SMAStressFormatter.Companion companion = SMAStressFormatter.Companion;
                            context3 = serverSyncUtils.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            } else {
                                context4 = context3;
                            }
                            List<StressHourData> responseStressHourData = RepositoryUtils.getResponseStressHourData(hourlyStressData);
                            Intrinsics.checkNotNullExpressionValue(responseStressHourData, "getResponseStressHourDat…                        )");
                            this.label = 2;
                            if (companion.getInstance(context4).checkAndUpdateStressDataFromServerToDb(responseStressHourData, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.repository.stress.datasources.server.StressApiHandler.StressApiResultListener
            public void onError(@NotNull String message) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "nStressDataReceived error :" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    context = ServerSyncUtils.this.f7359a;
                    Context context4 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                        context2 = ServerSyncUtils.this.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        if (!BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                            context3 = ServerSyncUtils.this.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            } else {
                                context4 = context3;
                            }
                            if (!BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                ServerSyncUtils.this.a(syncResultListner);
                                return;
                            }
                            ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                            Calendar calendar4 = calendar;
                            Calendar calendar5 = calendar2;
                            String serial_no = serverSyncUtils2.getSerial_no();
                            Intrinsics.checkNotNull(serial_no);
                            ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar4, calendar5, serial_no, syncResultListner);
                            return;
                        }
                        ServerSyncUtils serverSyncUtils3 = ServerSyncUtils.this;
                        Calendar calendar6 = calendar;
                        Calendar calendar7 = calendar2;
                        String serial_no2 = serverSyncUtils3.getSerial_no();
                        Intrinsics.checkNotNull(serial_no2);
                        ServerSyncUtils.access$fetchNewHRVDataFromServer(serverSyncUtils3, calendar6, calendar7, serial_no2, syncResultListner);
                        return;
                    }
                    ServerSyncUtils serverSyncUtils4 = ServerSyncUtils.this;
                    Calendar calendar8 = calendar;
                    Calendar calendar9 = calendar2;
                    String serial_no3 = serverSyncUtils4.getSerial_no();
                    Intrinsics.checkNotNull(serial_no3);
                    ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(serverSyncUtils4, calendar8, calendar9, serial_no3, syncResultListner);
                    return;
                }
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }

            @Override // com.coveiot.repository.stress.datasources.server.StressApiHandler.StressApiResultListener
            public void onStressDataReceived(@NotNull DailyStress dailyStress, @NotNull ArrayList<HourlyStress> hourlyStressData, boolean z) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Intrinsics.checkNotNullParameter(dailyStress, "dailyStress");
                Intrinsics.checkNotNullParameter(hourlyStressData, "hourlyStressData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onStressDataReceived success :");
                String serial_no = ServerSyncUtils.this.getSerial_no();
                Intrinsics.checkNotNull(serial_no);
                dailyStress.mac_address = serial_no;
                int size = hourlyStressData.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        hourlyStressData.get(i).setMacAddress(ServerSyncUtils.this.getSerial_no());
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context = ServerSyncUtils.this.f7359a;
                Context context6 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                StressDBWrite.getInstance(context).insertStress(dailyStress);
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                StressDBWrite.getInstance(context2).insertStressHourlyList(hourlyStressData);
                if (ServerSyncUtils.this.getStressLastSyncDate() == null) {
                    ServerSyncUtils.this.setStressLastSyncDate(dailyStress.mDate);
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getStressLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyStress.mDate, "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setStressLastSyncDate(dailyStress.mDate);
                }
                if (z) {
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(ServerSyncUtils.this, null), 2, null);
                    context3 = ServerSyncUtils.this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context3 = null;
                    }
                    if (!BleApiManager.getInstance(context3).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                        context4 = ServerSyncUtils.this.f7359a;
                        if (context4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context4 = null;
                        }
                        if (!BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                            context5 = ServerSyncUtils.this.f7359a;
                            if (context5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            } else {
                                context6 = context5;
                            }
                            if (!BleApiManager.getInstance(context6).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                ServerSyncUtils.this.a(syncResultListner);
                                return;
                            }
                            ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                            Calendar calendar4 = calendar;
                            Calendar calendar5 = calendar2;
                            String serial_no2 = serverSyncUtils2.getSerial_no();
                            Intrinsics.checkNotNull(serial_no2);
                            ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar4, calendar5, serial_no2, syncResultListner);
                            return;
                        }
                        ServerSyncUtils serverSyncUtils3 = ServerSyncUtils.this;
                        Calendar calendar6 = calendar;
                        Calendar calendar7 = calendar2;
                        String serial_no3 = serverSyncUtils3.getSerial_no();
                        Intrinsics.checkNotNull(serial_no3);
                        ServerSyncUtils.access$fetchNewHRVDataFromServer(serverSyncUtils3, calendar6, calendar7, serial_no3, syncResultListner);
                        return;
                    }
                    ServerSyncUtils serverSyncUtils4 = ServerSyncUtils.this;
                    Calendar calendar8 = calendar;
                    Calendar calendar9 = calendar2;
                    String serial_no4 = serverSyncUtils4.getSerial_no();
                    Intrinsics.checkNotNull(serial_no4);
                    ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(serverSyncUtils4, calendar8, calendar9, serial_no4, syncResultListner);
                }
            }
        });
    }

    public static final void access$fetchTemperatureDataFromServer(final ServerSyncUtils serverSyncUtils, final Calendar calendar, final Calendar calendar2, String str, final SyncResultListner syncResultListner) {
        serverSyncUtils.getClass();
        TemperatureApiHandler.Companion.fetchDatafor(calendar, calendar2, new TemperatureApiHandler.TemperatureApiResultListner() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchTemperatureDataFromServer$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$fetchTemperatureDataFromServer$1$onTemperatureDataReceived$1", f = "ServerSyncUtils.kt", i = {}, l = {500, 517}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ServerSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ServerSyncUtils serverSyncUtils, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = serverSyncUtils;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Context context;
                    Context context2;
                    Context context3;
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (DelayKt.delay(500L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    context = this.this$0.f7359a;
                    Context context4 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (RepositoryUtils.isSmaDevice(context)) {
                        LogHelper.d("SMATemperatureFormatter", "temperatureLastSyncDate -> " + this.this$0.getTemperatureLastSyncDate());
                        context2 = this.this$0.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        List<HourlyTemperature> hourlyTemperatureData = TemperatureDBRead.getInstance(context2).getHourlyTemperatureData(this.this$0.getTemperatureLastSyncDate(), this.this$0.getSerial_no());
                        StringBuilder sb = new StringBuilder();
                        sb.append("hourlyTemperatures.size -> ");
                        sb.append(hourlyTemperatureData != null ? Boxing.boxInt(hourlyTemperatureData.size()) : null);
                        LogHelper.d("SMATemperatureFormatter", sb.toString());
                        if (hourlyTemperatureData != null) {
                            ServerSyncUtils serverSyncUtils = this.this$0;
                            SMATemperatureFormatter.Companion companion = SMATemperatureFormatter.Companion;
                            context3 = serverSyncUtils.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            } else {
                                context4 = context3;
                            }
                            List<TemperatureHourData> responseTemperatureHourData = RepositoryUtils.getResponseTemperatureHourData(hourlyTemperatureData);
                            Intrinsics.checkNotNullExpressionValue(responseTemperatureHourData, "getResponseTemperatureHo…                        )");
                            this.label = 2;
                            if (companion.getInstance(context4).checkAndUpdateTemperatureDataFromServerToDb(responseTemperatureHourData, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.repository.temperature.datasources.server.TemperatureApiHandler.TemperatureApiResultListner
            public void onError(@NotNull String message) {
                String str2;
                String str3;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, " onTemperatureDataReceived error :" + message);
                if (Intrinsics.areEqual(message, "No data found")) {
                    context = ServerSyncUtils.this.f7359a;
                    Context context5 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                        context2 = ServerSyncUtils.this.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        if (!BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                            context3 = ServerSyncUtils.this.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context3 = null;
                            }
                            if (!BleApiManager.getInstance(context3).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                context4 = ServerSyncUtils.this.f7359a;
                                if (context4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                } else {
                                    context5 = context4;
                                }
                                if (!BleApiManager.getInstance(context5).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                    ServerSyncUtils.this.a(syncResultListner);
                                } else {
                                    ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                                    Calendar calendar3 = calendar;
                                    Calendar calendar4 = calendar2;
                                    String serial_no = serverSyncUtils2.getSerial_no();
                                    Intrinsics.checkNotNull(serial_no);
                                    ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar3, calendar4, serial_no, syncResultListner);
                                }
                            } else {
                                ServerSyncUtils serverSyncUtils3 = ServerSyncUtils.this;
                                Calendar calendar5 = calendar;
                                Calendar calendar6 = calendar2;
                                String serial_no2 = serverSyncUtils3.getSerial_no();
                                Intrinsics.checkNotNull(serial_no2);
                                ServerSyncUtils.access$fetchNewHRVDataFromServer(serverSyncUtils3, calendar5, calendar6, serial_no2, syncResultListner);
                            }
                        } else {
                            ServerSyncUtils serverSyncUtils4 = ServerSyncUtils.this;
                            Calendar calendar7 = calendar;
                            Calendar calendar8 = calendar2;
                            String serial_no3 = serverSyncUtils4.getSerial_no();
                            Intrinsics.checkNotNull(serial_no3);
                            ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(serverSyncUtils4, calendar7, calendar8, serial_no3, syncResultListner);
                        }
                    } else {
                        ServerSyncUtils serverSyncUtils5 = ServerSyncUtils.this;
                        Calendar calendar9 = calendar;
                        Calendar calendar10 = calendar2;
                        String serial_no4 = serverSyncUtils5.getSerial_no();
                        Intrinsics.checkNotNull(serial_no4);
                        ServerSyncUtils.access$fetchStressDataFromServer(serverSyncUtils5, calendar9, calendar10, serial_no4, syncResultListner);
                    }
                }
                str3 = ServerSyncUtils.this.b;
                LogHelper.d(str3, " onTemperatureDataReceived success :");
            }

            @Override // com.coveiot.repository.temperature.datasources.server.TemperatureApiHandler.TemperatureApiResultListner
            public void onTemperatureDataReceived(@NotNull DailyTemperature dailyTemperatureData, @NotNull ArrayList<HourlyTemperature> hourlyTemperatureData, boolean z) {
                String str2;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Context context6;
                Context context7;
                Context context8;
                Intrinsics.checkNotNullParameter(dailyTemperatureData, "dailyTemperatureData");
                Intrinsics.checkNotNullParameter(hourlyTemperatureData, "hourlyTemperatureData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onTemperatureDataReceived success :");
                String serial_no = ServerSyncUtils.this.getSerial_no();
                Intrinsics.checkNotNull(serial_no);
                dailyTemperatureData.mac_address = serial_no;
                int size = hourlyTemperatureData.size() - 1;
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        hourlyTemperatureData.get(i).setMacAddress(ServerSyncUtils.this.getSerial_no());
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context = ServerSyncUtils.this.f7359a;
                Context context9 = null;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                TemperatureDBWrite.getInstance(context).insertTemperature(dailyTemperatureData);
                context2 = ServerSyncUtils.this.f7359a;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context2 = null;
                }
                TemperatureDBWrite.getInstance(context2).insertTemperatureHourlyList(hourlyTemperatureData);
                if (ServerSyncUtils.this.getTemperatureLastSyncDate() == null) {
                    ServerSyncUtils.this.setTemperatureLastSyncDate(dailyTemperatureData.mDate);
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getTemperatureLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyTemperatureData.mDate, "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setTemperatureLastSyncDate(dailyTemperatureData.mDate);
                }
                if (z) {
                    context3 = ServerSyncUtils.this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context3 = null;
                    }
                    EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context3).getDeviceInfoBy(ServerSyncUtils.this.getSerial_no());
                    Intrinsics.checkNotNull(deviceInfoBy, "null cannot be cast to non-null type com.coveiot.covedb.deviceinfo.EntityDeviceInfo");
                    deviceInfoBy.setLastSyncDateTemperature(ServerSyncUtils.this.getTemperatureLastSyncDate());
                    context4 = ServerSyncUtils.this.f7359a;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context4 = null;
                    }
                    DeviceInfoRepository.getInstance(context4).insertDeviceInfo(deviceInfoBy);
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(ServerSyncUtils.this, null), 2, null);
                    context5 = ServerSyncUtils.this.f7359a;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context5 = null;
                    }
                    if (!BleApiManager.getInstance(context5).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                        context6 = ServerSyncUtils.this.f7359a;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context6 = null;
                        }
                        if (!BleApiManager.getInstance(context6).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                            context7 = ServerSyncUtils.this.f7359a;
                            if (context7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context7 = null;
                            }
                            if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                context8 = ServerSyncUtils.this.f7359a;
                                if (context8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                } else {
                                    context9 = context8;
                                }
                                if (!BleApiManager.getInstance(context9).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                    ServerSyncUtils.this.a(syncResultListner);
                                    return;
                                }
                                ServerSyncUtils serverSyncUtils2 = ServerSyncUtils.this;
                                Calendar calendar3 = calendar;
                                Calendar calendar4 = calendar2;
                                String serial_no2 = serverSyncUtils2.getSerial_no();
                                Intrinsics.checkNotNull(serial_no2);
                                ServerSyncUtils.access$fetchAmbientSoundDataFromServer(serverSyncUtils2, calendar3, calendar4, serial_no2, syncResultListner);
                                return;
                            }
                            ServerSyncUtils serverSyncUtils3 = ServerSyncUtils.this;
                            Calendar calendar5 = calendar;
                            Calendar calendar6 = calendar2;
                            String serial_no3 = serverSyncUtils3.getSerial_no();
                            Intrinsics.checkNotNull(serial_no3);
                            ServerSyncUtils.access$fetchNewHRVDataFromServer(serverSyncUtils3, calendar5, calendar6, serial_no3, syncResultListner);
                            return;
                        }
                        ServerSyncUtils serverSyncUtils4 = ServerSyncUtils.this;
                        Calendar calendar7 = calendar;
                        Calendar calendar8 = calendar2;
                        String serial_no4 = serverSyncUtils4.getSerial_no();
                        Intrinsics.checkNotNull(serial_no4);
                        ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(serverSyncUtils4, calendar7, calendar8, serial_no4, syncResultListner);
                        return;
                    }
                    ServerSyncUtils serverSyncUtils5 = ServerSyncUtils.this;
                    Calendar calendar9 = calendar;
                    Calendar calendar10 = calendar2;
                    String serial_no5 = serverSyncUtils5.getSerial_no();
                    Intrinsics.checkNotNull(serial_no5);
                    ServerSyncUtils.access$fetchStressDataFromServer(serverSyncUtils5, calendar9, calendar10, serial_no5, syncResultListner);
                }
            }
        });
        serverSyncUtils.a(syncResultListner);
    }

    public static final void access$logAnalyticsErrorEvent(ServerSyncUtils serverSyncUtils, String str, ArrayList arrayList) {
        serverSyncUtils.getClass();
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(str);
            analyticsLog.setData(arrayList);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void access$syncFailure(ServerSyncUtils serverSyncUtils, SyncResultListner syncResultListner, String str) {
        serverSyncUtils.getClass();
        syncResultListner.onFailure(new Error(str, Integer.valueOf(SyncErrorTypes.ERR_API_FAILURE.value), "api"));
    }

    public final void fetchDataFromServerWith(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Context context = this.f7359a;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        a(fromDate, BleApiManager.getInstance(context).getBleApi().getMacAddress(), toDate, syncResultListner);
    }

    public final void fetchSedentaryAlertsData(@NotNull Calendar fromDate, @NotNull Calendar todate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(todate, "todate");
        SedentaryAlertsApiManager.getSedentaryAlertsDataFromServer(RepositoryUtils.convertCalendarToString(fromDate), RepositoryUtils.convertCalendarToString(todate), true, new CoveApiListener<GetSedentaryAlertsDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchSedentaryAlertsData$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetSedentaryAlertsDataRes getSedentaryAlertsDataRes) {
                Context context;
                Context context2;
                if (getSedentaryAlertsDataRes == null || getSedentaryAlertsDataRes.getSedentaryAlertsDataBeanList() == null) {
                    return;
                }
                for (SedentaryAlertsDataBean sedentaryAlertData : getSedentaryAlertsDataRes.getSedentaryAlertsDataBeanList()) {
                    int i = 0;
                    for (SedentaryAlertsDataBean.TimeLog.Log timeLog : sedentaryAlertData.getTimeLog().getLogs()) {
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        Intrinsics.checkNotNullExpressionValue(timeLog, "timeLog");
                        Intrinsics.checkNotNullExpressionValue(sedentaryAlertData, "sedentaryAlertData");
                        context = ServerSyncUtils.this.f7359a;
                        Context context3 = null;
                        if (context == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context = null;
                        }
                        i++;
                        EntitySedentary sedentaryAlertsData = companion.getSedentaryAlertsData(timeLog, sedentaryAlertData, BleApiManager.getInstance(context).getBleApi().getMacAddress(), i);
                        SedentaryDBRepo.Companion companion2 = SedentaryDBRepo.Companion;
                        context2 = ServerSyncUtils.this.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                        } else {
                            context3 = context2;
                        }
                        SedentaryDBRepo companion3 = companion2.getInstance(context3);
                        Intrinsics.checkNotNull(companion3);
                        companion3.insert(sedentaryAlertsData);
                    }
                }
            }
        });
    }

    public final void fetchThreeMonthsDataFromServer(@NotNull String serial_no, @NotNull SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Calendar toDate = Calendar.getInstance();
        Object clone = toDate.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.add(2, -3);
        Intrinsics.checkNotNullExpressionValue(toDate, "toDate");
        a(calendar, serial_no, toDate, syncResultListner);
    }

    public final void fetchWalkTargetData(@NotNull final Calendar fromDate, @NotNull final String serial_no, @NotNull final Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        CoveActivityTracker.getGoalHistory(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), ActivityType.WALK.getActivityType(), ActivityBaseUnit.STEPS.getActivityBaseUnit(), new CoveApiListener<GetGoalHistoryResponse, CoveApiErrorModel>() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchWalkTargetData$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetGoalHistoryResponse getGoalHistoryResponse) {
                if (getGoalHistoryResponse != null) {
                    List<GetGoalHistoryResponse.DataBean.Item> items = getGoalHistoryResponse.getData().getItems();
                    if (items == null || items.isEmpty()) {
                        return;
                    }
                    ServerSyncUtils serverSyncUtils = ServerSyncUtils.this;
                    Calendar calendar = fromDate;
                    String str = serial_no;
                    Calendar calendar2 = toDate;
                    List<GetGoalHistoryResponse.DataBean.Item> items2 = getGoalHistoryResponse.getData().getItems();
                    Intrinsics.checkNotNullExpressionValue(items2, "p0.data.items");
                    serverSyncUtils.updateWalkTargetToDB(calendar, str, calendar2, items2);
                }
            }
        });
    }

    @Nullable
    public final String getAmbientSoundLastSyncDate() {
        return this.k;
    }

    @Nullable
    public final String getBpLastSyncDate() {
        return this.h;
    }

    @Nullable
    public final String getHeartRateLastSyncDate() {
        return this.e;
    }

    @Nullable
    public final String getPeriodicSpo2LastSyncDate() {
        return this.j;
    }

    @Nullable
    public final String getSerial_no() {
        return this.i;
    }

    @Nullable
    public final String getSleepLastSyncDate() {
        return this.d;
    }

    @Nullable
    public final String getStressLastSyncDate() {
        return this.g;
    }

    @Nullable
    public final String getTemperatureLastSyncDate() {
        return this.f;
    }

    @Nullable
    public final String getWalkLastSyncDate() {
        return this.c;
    }

    public final void saveDataToServer(@NotNull String serial_no, @NotNull SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        e.e(GlobalScope.INSTANCE, null, null, new a(serial_no, null), 3, null);
        syncResultListner.onSuccess();
    }

    public final void saveECGSummaryToServer(int i, @NotNull final SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        ECGRepository.Companion companion = ECGRepository.Companion;
        Context context = this.f7359a;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        final List<EntityECGSummaryData> eCGSummaryDataList = companion.getInstance(context).getECGSummaryDataList();
        ArrayList arrayList = new ArrayList();
        int size = eCGSummaryDataList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(com.coveiot.repository.ecg.server.FormatorServerToEntity.Companion.getSummaryEcgData(eCGSummaryDataList.get(i2), i));
        }
        PostECGSessionData postECGSessionData = new PostECGSessionData();
        postECGSessionData.setFitnessSessionData(arrayList);
        ECGSessionApi.saveECGSessionData(postECGSessionData, new CoveApiListener<FitnessConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$saveECGSummaryToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                SyncResultListner syncResultListner2 = syncResultListner;
                String msg = coveApiErrorModel.getMsg();
                Intrinsics.checkNotNullExpressionValue(msg, "coveApiErrorModel.msg");
                syncResultListner2.onFailure(new Error(msg, Integer.valueOf(SyncErrorTypes.ERR_API_FAILURE.value), "api"));
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull FitnessConfigResponse fitnessConfigResponse) {
                Context context2;
                Intrinsics.checkNotNullParameter(fitnessConfigResponse, "fitnessConfigResponse");
                LogHelper.d("ecg onSuccess", fitnessConfigResponse.getMessage(), RepositoryModuleNames.REPOSITORY.getModuleName());
                for (EntityECGSummaryData entityECGSummaryData : eCGSummaryDataList) {
                    entityECGSummaryData.is_sync_server = true;
                    context2 = this.f7359a;
                    if (context2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context2 = null;
                    }
                    ECGDBWrite.getInstance(context2).insertECGResultData(entityECGSummaryData);
                }
                syncResultListner.onSuccess();
            }
        });
    }

    public final void saveHRVStressDataToServer(@NotNull Context mContext, @NotNull SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        List<EntityManualData> unSyncedData = ManualDataRepository.Companion.getInstance(mContext).getUnSyncedData();
        if (unSyncedData != null && (!unSyncedData.isEmpty())) {
            ManualDataApiHandler.Companion.saveManualHRVStressDataToServer(unSyncedData, syncResultListner);
        }
        syncResultListner.onSuccess();
    }

    public final void saveSPO2DataToServer(@NotNull final Context mContext, @NotNull SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        final List<EntityManualData> unSyncedData = ManualDataRepository.Companion.getInstance(mContext).getUnSyncedData();
        if (unSyncedData != null && (!unSyncedData.isEmpty())) {
            LogHelper.d("manualspo2", "saveSPO2DataToServer");
            ManualDataApiManager.saveManualSPO2Data(ServerFormator.Companion.getSaveManualSPO2DataReq(unSyncedData), new CoveApiListener<SaveManualSPO2DataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$saveSPO2DataToServer$1

                @DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$saveSPO2DataToServer$1$onSuccess$1", f = "ServerSyncUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes9.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Context $mContext;
                    public final /* synthetic */ List<EntityManualData> $spo2List;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public a(List<? extends EntityManualData> list, Context context, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$spo2List = list;
                        this.$mContext = context;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$spo2List, this.$mContext, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            int size = this.$spo2List.size();
                            for (int i = 0; i < size; i++) {
                                EntityManualData entityManualData = this.$spo2List.get(i);
                                entityManualData.setSyncedWithServer(true);
                                LogHelper.d("manualspo2", "spo2Record");
                                ManualDataDBWrite.getInstance(this.$mContext).insert(entityManualData);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                    Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull SaveManualSPO2DataRes saveManualSPO2DataRes) {
                    Intrinsics.checkNotNullParameter(saveManualSPO2DataRes, "saveManualSPO2DataRes");
                    if (m.equals(saveManualSPO2DataRes.status, CoveApiConstants.RESPONSE_STATUS_VALUE_OK, true)) {
                        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(unSyncedData, mContext, null), 2, null);
                    }
                }
            });
        }
        syncResultListner.onSuccess();
    }

    public final void setAmbientSoundLastSyncDate(@Nullable String str) {
        this.k = str;
    }

    public final void setBpLastSyncDate(@Nullable String str) {
        this.h = str;
    }

    public final void setHeartRateLastSyncDate(@Nullable String str) {
        this.e = str;
    }

    public final void setPeriodicSpo2LastSyncDate(@Nullable String str) {
        this.j = str;
    }

    public final void setSerial_no(@Nullable String str) {
        this.i = str;
    }

    public final void setSleepLastSyncDate(@Nullable String str) {
        this.d = str;
    }

    public final void setStressLastSyncDate(@Nullable String str) {
        this.g = str;
    }

    public final void setTemperatureLastSyncDate(@Nullable String str) {
        this.f = str;
    }

    public final void setWalkLastSyncDate(@Nullable String str) {
        this.c = str;
    }

    public final void updateWalkTargetToDB(@NotNull Calendar fromDate, @NotNull String serial_no, @NotNull Calendar toDate, @NotNull List<? extends GetGoalHistoryResponse.DataBean.Item> walkTargetList) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(walkTargetList, "walkTargetList");
        Collections.sort(walkTargetList, new Comparator() { // from class: com.coveiot.repository.datasync.d
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ServerSyncUtils.a((GetGoalHistoryResponse.DataBean.Item) obj, (GetGoalHistoryResponse.DataBean.Item) obj2);
            }
        });
        String formatDate = RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(fromDate.time,\"yyyy-MM-dd\")");
        String toDate2 = RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd");
        int size = walkTargetList.size();
        int i = 0;
        while (i < size) {
            Context context = this.f7359a;
            Context context2 = null;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            }
            WalkDBWrite walkDBWrite = WalkDBWrite.getInstance(context);
            Integer target = (i == 0 ? walkTargetList.get(i) : walkTargetList.get(i - 1)).getTarget();
            Intrinsics.checkNotNullExpressionValue(target, "if(index == 0) walkTarge…argetList[index-1].target");
            walkDBWrite.updateDailyTarget(target.intValue(), formatDate, i == 0 ? walkTargetList.get(i).getStartDate() : RepositoryUtils.getPreviousDate(walkTargetList.get(i).getStartDate()), serial_no);
            formatDate = walkTargetList.get(i).getStartDate();
            Intrinsics.checkNotNullExpressionValue(formatDate, "walkTargetList[index].startDate");
            if (i == walkTargetList.size() - 1) {
                String startDate = walkTargetList.get(i).getStartDate();
                Intrinsics.checkNotNullExpressionValue(toDate2, "toDate");
                if (startDate.compareTo(toDate2) < 0) {
                    Context context3 = this.f7359a;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    } else {
                        context2 = context3;
                    }
                    WalkDBWrite walkDBWrite2 = WalkDBWrite.getInstance(context2);
                    Integer target2 = walkTargetList.get(i).getTarget();
                    Intrinsics.checkNotNullExpressionValue(target2, "walkTargetList[index].target");
                    walkDBWrite2.updateDailyTarget(target2.intValue(), formatDate, toDate2, serial_no);
                }
            }
            i++;
        }
    }

    public final void uploadECGFileToServer(@NotNull File imageFile, @NotNull final APIResponseListner apiListener) {
        Intrinsics.checkNotNullParameter(imageFile, "imageFile");
        Intrinsics.checkNotNullParameter(apiListener, "apiListener");
        MediaUpload.uploadMediaFile(new MediaUploadReq(MediaClassType.FIT_ECG_GRAPH_TYPE1, imageFile), new CoveApiListener<MediaUploadRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$uploadECGFileToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull MediaUploadRes mediaUploadRes) {
                Intrinsics.checkNotNullParameter(mediaUploadRes, "mediaUploadRes");
                APIResponseListner.this.onSuccess(mediaUploadRes);
            }
        });
    }

    public final void a(final Calendar calendar, final String str, final Calendar calendar2, final SyncResultListner syncResultListner) {
        Context context = this.f7359a;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        if (!RepositoryUtils.isSmaDevice(context)) {
            Context context2 = this.f7359a;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context2 = null;
            }
            if (!RepositoryUtils.isMatrixDevice(context2)) {
                Context context3 = this.f7359a;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context3 = null;
                }
                if (!RepositoryUtils.isIDODevice(context3)) {
                    Context context4 = this.f7359a;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context4 = null;
                    }
                    if (!RepositoryUtils.isTouchELXDevice(context4)) {
                        Context context5 = this.f7359a;
                        if (context5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context5 = null;
                        }
                        if (!RepositoryUtils.isEastApexDevice(context5)) {
                            calendar.add(6, -1);
                        }
                    }
                }
            }
        }
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = null;
        this.f = null;
        this.j = null;
        this.k = null;
        LogHelper.d(this.b, " fetchThreeMonthsDataFromServer date:" + calendar.getTime());
        this.i = str;
        WalkApiHandler.Companion.fetchDatafor(calendar, calendar2, new WalkApiHandler.WalkDataApiResultListner() { // from class: com.coveiot.repository.datasync.ServerSyncUtils$fetchDataFromServerWith$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$fetchDataFromServerWith$1$onWalkDataReceived$1", f = "ServerSyncUtils.kt", i = {}, l = {344, 359}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ Calendar $fromDate;
                public final /* synthetic */ String $serial_no;
                public final /* synthetic */ Calendar $toDate;
                public int label;
                public final /* synthetic */ ServerSyncUtils this$0;

                @DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$fetchDataFromServerWith$1$onWalkDataReceived$1$2", f = "ServerSyncUtils.kt", i = {}, l = {371}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.repository.datasync.ServerSyncUtils$fetchDataFromServerWith$1$a$a  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static final class C0333a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Calendar $fromDate;
                    public final /* synthetic */ String $serial_no;
                    public final /* synthetic */ Calendar $toDate;
                    public int label;
                    public final /* synthetic */ ServerSyncUtils this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0333a(ServerSyncUtils serverSyncUtils, Calendar calendar, String str, Calendar calendar2, Continuation<? super C0333a> continuation) {
                        super(2, continuation);
                        this.this$0 = serverSyncUtils;
                        this.$fromDate = calendar;
                        this.$serial_no = str;
                        this.$toDate = calendar2;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new C0333a(this.this$0, this.$fromDate, this.$serial_no, this.$toDate, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((C0333a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (DelayKt.delay(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        this.this$0.fetchWalkTargetData(this.$fromDate, this.$serial_no, this.$toDate);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ServerSyncUtils serverSyncUtils, String str, Calendar calendar, Calendar calendar2, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = serverSyncUtils;
                    this.$serial_no = str;
                    this.$fromDate = calendar;
                    this.$toDate = calendar2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$serial_no, this.$fromDate, this.$toDate, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Context context;
                    Context context2;
                    Context context3;
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (DelayKt.delay(500L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C0333a(this.this$0, this.$fromDate, this.$serial_no, this.$toDate, null), 2, null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    context = this.this$0.f7359a;
                    Context context4 = null;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    if (RepositoryUtils.isSmaDevice(context)) {
                        LogHelper.d("SMAActivityFormatter", "activityLastSyncDate -> " + this.this$0.getWalkLastSyncDate());
                        context2 = this.this$0.f7359a;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context2 = null;
                        }
                        List<HourlyWalkData> orderedHourlyWalkDataFor = WalkDBRead.getInstance(context2).getOrderedHourlyWalkDataFor(this.this$0.getWalkLastSyncDate(), this.$serial_no);
                        StringBuilder sb = new StringBuilder();
                        sb.append("hourlyWalks.size -> ");
                        sb.append(orderedHourlyWalkDataFor != null ? Boxing.boxInt(orderedHourlyWalkDataFor.size()) : null);
                        LogHelper.d("SMAActivityFormatter", sb.toString());
                        if (orderedHourlyWalkDataFor != null) {
                            ServerSyncUtils serverSyncUtils = this.this$0;
                            SMAActivityFormatter.Companion companion = SMAActivityFormatter.Companion;
                            context3 = serverSyncUtils.f7359a;
                            if (context3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            } else {
                                context4 = context3;
                            }
                            List<StepsHourData> responseStepsHourData = RepositoryUtils.getResponseStepsHourData(orderedHourlyWalkDataFor);
                            Intrinsics.checkNotNullExpressionValue(responseStepsHourData, "getResponseStepsHourData…                        )");
                            Date parseDate = RepositoryUtils.parseDate(serverSyncUtils.getWalkLastSyncDate(), "yyyy-MM-dd");
                            Intrinsics.checkNotNullExpressionValue(parseDate, "parseDate(\n             …                        )");
                            this.label = 2;
                            if (companion.getInstance(context4).checkAndUpdateActivityDataFromServerToDb(responseStepsHourData, parseDate, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C0333a(this.this$0, this.$fromDate, this.$serial_no, this.$toDate, null), 2, null);
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.repository.walk.datasources.server.WalkApiHandler.WalkDataApiResultListner
            public void onError(@NotNull String message) {
                String str2;
                Intrinsics.checkNotNullParameter(message, "message");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onWalkDataReceived error:" + message);
                ServerSyncUtils.access$syncFailure(ServerSyncUtils.this, syncResultListner, message);
            }

            @Override // com.coveiot.repository.walk.datasources.server.WalkApiHandler.WalkDataApiResultListner
            public void onWalkDataReceived(@NotNull DailyWalkData dailyWalkData, @NotNull ArrayList<HourlyWalkData> hourlyWalkData, boolean z) {
                String str2;
                Context context6;
                Context context7;
                Context context8;
                Context context9;
                Context context10;
                Context context11;
                Context context12;
                Context context13;
                Context context14;
                Context context15;
                Context context16;
                Context context17;
                Context context18;
                Intrinsics.checkNotNullParameter(dailyWalkData, "dailyWalkData");
                Intrinsics.checkNotNullParameter(hourlyWalkData, "hourlyWalkData");
                str2 = ServerSyncUtils.this.b;
                LogHelper.d(str2, "onWalkDataReceived :");
                dailyWalkData.setMacAddress(str);
                context6 = ServerSyncUtils.this.f7359a;
                Context context19 = null;
                if (context6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context6 = null;
                }
                dailyWalkData.setStepsTarget(ProfileDBRead.getInstance(context6).getLatestProfileData().stepsTarget);
                int i = 0;
                int size = hourlyWalkData.size() - 1;
                if (size >= 0) {
                    while (true) {
                        hourlyWalkData.get(i).setMacAddress(str);
                        if (i == size) {
                            break;
                        }
                        i++;
                    }
                }
                context7 = ServerSyncUtils.this.f7359a;
                if (context7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context7 = null;
                }
                WalkDBWrite.getInstance(context7).insertDailyData(dailyWalkData);
                context8 = ServerSyncUtils.this.f7359a;
                if (context8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context8 = null;
                }
                WalkDBWrite.getInstance(context8).insertHourlyStepsData(hourlyWalkData);
                if (ServerSyncUtils.this.getWalkLastSyncDate() == null) {
                    ServerSyncUtils.this.setWalkLastSyncDate(dailyWalkData.mDate);
                } else if (RepositoryUtils.parseDate(ServerSyncUtils.this.getWalkLastSyncDate(), "yyyy-MM-dd").getTime() < RepositoryUtils.parseDate(dailyWalkData.mDate, "yyyy-MM-dd").getTime()) {
                    ServerSyncUtils.this.setWalkLastSyncDate(dailyWalkData.mDate);
                }
                if (z) {
                    context9 = ServerSyncUtils.this.f7359a;
                    if (context9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context9 = null;
                    }
                    EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context9).getDeviceInfoBy(str);
                    if (deviceInfoBy == null) {
                        deviceInfoBy = new EntityDeviceInfo();
                        deviceInfoBy.setMacAddress(str);
                    }
                    deviceInfoBy.setLastSyncDateWalk(ServerSyncUtils.this.getWalkLastSyncDate());
                    context10 = ServerSyncUtils.this.f7359a;
                    if (context10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context10 = null;
                    }
                    DeviceInfoRepository.getInstance(context10).insertDeviceInfo(deviceInfoBy);
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(ServerSyncUtils.this, str, calendar, calendar2, null), 2, null);
                    context11 = ServerSyncUtils.this.f7359a;
                    if (context11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context11 = null;
                    }
                    if (!BleApiManager.getInstance(context11).getBleApi().getDeviceSupportedFeatures().isSleepSupported()) {
                        context12 = ServerSyncUtils.this.f7359a;
                        if (context12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("context");
                            context12 = null;
                        }
                        if (!BleApiManager.getInstance(context12).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported()) {
                            context13 = ServerSyncUtils.this.f7359a;
                            if (context13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                context13 = null;
                            }
                            if (!BleApiManager.getInstance(context13).getBleApi().getDeviceSupportedFeatures().isBpSupported()) {
                                context14 = ServerSyncUtils.this.f7359a;
                                if (context14 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                    context14 = null;
                                }
                                if (!BleApiManager.getInstance(context14).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                                    context15 = ServerSyncUtils.this.f7359a;
                                    if (context15 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("context");
                                        context15 = null;
                                    }
                                    if (!BleApiManager.getInstance(context15).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                                        context16 = ServerSyncUtils.this.f7359a;
                                        if (context16 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("context");
                                            context16 = null;
                                        }
                                        if (!BleApiManager.getInstance(context16).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                                            context17 = ServerSyncUtils.this.f7359a;
                                            if (context17 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("context");
                                                context17 = null;
                                            }
                                            if (!BleApiManager.getInstance(context17).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                                                context18 = ServerSyncUtils.this.f7359a;
                                                if (context18 == null) {
                                                    Intrinsics.throwUninitializedPropertyAccessException("context");
                                                } else {
                                                    context19 = context18;
                                                }
                                                if (!BleApiManager.getInstance(context19).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                                                    ServerSyncUtils.this.a(syncResultListner);
                                                    return;
                                                } else {
                                                    ServerSyncUtils.access$fetchAmbientSoundDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                                    return;
                                                }
                                            }
                                            ServerSyncUtils.access$fetchNewHRVDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                            return;
                                        }
                                        ServerSyncUtils.access$fetchPeriodicSpo2DataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                        return;
                                    }
                                    ServerSyncUtils.access$fetchStressDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                    return;
                                }
                                ServerSyncUtils.access$fetchTemperatureDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                                return;
                            }
                            ServerSyncUtils.access$fetachBPDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                            return;
                        }
                        ServerSyncUtils.access$fetchHeartRateDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                        return;
                    }
                    ServerSyncUtils.access$fetchSleepDataFromServer(ServerSyncUtils.this, calendar, calendar2, str, syncResultListner);
                }
            }
        });
    }

    public final void a(SyncResultListner syncResultListner) {
        Context context = this.f7359a;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context).getDeviceInfoBy(this.i);
        if (deviceInfoBy == null) {
            deviceInfoBy = new EntityDeviceInfo();
            String str = this.i;
            Intrinsics.checkNotNull(str);
            deviceInfoBy.setMacAddress(str);
        }
        deviceInfoBy.setLastSyncDateWalk(this.c);
        deviceInfoBy.setLastSyncDateSleep(this.d);
        deviceInfoBy.setLastSyncDateHr(this.e);
        deviceInfoBy.setLastSyncDateBp(this.h);
        deviceInfoBy.setLastSyncDateTemperature(this.f);
        deviceInfoBy.setLastSyncDatePeriodicSpo2(this.j);
        deviceInfoBy.setLastSyncDateAmbientSound(this.k);
        Context context3 = this.f7359a;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        DeviceInfoRepository.getInstance(context2).insertDeviceInfo(deviceInfoBy);
        syncResultListner.onSuccess();
    }

    public final Object a(String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new ServerSyncUtils$serverUpload$2(this, str, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
