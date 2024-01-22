package com.coveiot.android.leonardo.dashboard;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelKt;
import androidx.work.PeriodicWorkRequest;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment;
import com.coveiot.android.activitymodes.database.entities.FootballSample;
import com.coveiot.android.activitymodes.database.entities.FreeExerciseSample;
import com.coveiot.android.activitymodes.database.entities.HikingSample;
import com.coveiot.android.activitymodes.database.entities.MeditationSample;
import com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample;
import com.coveiot.android.activitymodes.database.entities.RowingMachineSample;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.SampleData;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.database.models.HeartRateZoneRanges;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityGPSSample;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1860;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.ActivityPauseResumeRequest;
import com.coveiot.android.bleabstract.request.AgpsUpdateRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.DeleteActivityDataRequest;
import com.coveiot.android.bleabstract.request.DeleteGpsDataRequest;
import com.coveiot.android.bleabstract.request.GetLatestHealthDataRequest;
import com.coveiot.android.bleabstract.request.LiveHeartRateControlRequest;
import com.coveiot.android.bleabstract.request.LiveStepsControlRequest;
import com.coveiot.android.bleabstract.request.LiveTemperatureControlRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetSensAIActivityConfigRequest;
import com.coveiot.android.bleabstract.request.SportModeRequest;
import com.coveiot.android.bleabstract.request.TodaysFitnessDataRequest;
import com.coveiot.android.bleabstract.request.TodaysStepsDataRequest;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse;
import com.coveiot.android.bleabstract.response.HealthData;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.LastDataHelper;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.dashboard2.StatusApiDataHelper;
import com.coveiot.android.dashboard2.listener.SyncTroubleShoutListener;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.dashboard2.model.DeviceReconnected;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceConstants;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractUpdateHrBpHealthTextListener;
import com.coveiot.android.leonardo.dashboard.health.spo2.SPO2Level;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.android.leonardo.dashboard.home.fragments.WeatherResultListener;
import com.coveiot.android.leonardo.dashboard.model.MatchState;
import com.coveiot.android.leonardo.dashboard.model.ProgressUpdateData;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.model.UpdateHomeDashboard;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import com.coveiot.android.leonardo.sp02.SPO2Formator;
import com.coveiot.android.leonardo.sp02.SPO2Repository;
import com.coveiot.android.leonardo.sp02.database.entities.SPO2Record;
import com.coveiot.android.leonardo.sp02.preference.Spo2DataManager;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weathersdk.WeatherAPIType;
import com.coveiot.android.weathersdk.WeatherApiCallsManager;
import com.coveiot.android.weathersdk.WeatherApiErrorModel;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.WeatherUnit;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentForecastModel;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.config.FitnessConfigApi;
import com.coveiot.coveaccess.fitness.config.models.requestmodel.FitnessConfigRequest;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordApiManager;
import com.coveiot.coveaccess.fitnessrecord.SaveFitnessRecordsResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.onboarding.model.FCMRegistrationReq;
import com.coveiot.coveaccess.onboarding.model.FCMRegistrationRes;
import com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.manualdata.entities.Source;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import com.coveiot.covepreferences.data.StressConfiguration;
import com.coveiot.covepreferences.data.WebViewUrlBean;
import com.coveiot.repository.Error;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.bp.BPRepository;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.temperature.TemperatureRepository;
import com.coveiot.repository.walk.WalkRepository;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.eventmodels.LiveHeartRate;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelActivityDashboard extends AndroidViewModel {
    @NotNull
    public MutableLiveData<ProgressUpdateData> A;
    @NotNull
    public ProgressUpdateData B;
    public int C;
    @NotNull
    public final GenericBandApplication D;
    @Nullable
    public LiveData<DailyWalkData> E;
    @Nullable
    public LiveData<EntityManualData> F;
    @Nullable
    public LiveData<DailyRespiratoryRateEntity> G;
    @NotNull
    public final Observer<LiveStepsData> H;
    @NotNull
    public final Observer<LiveHealthData> I;
    @Nullable
    public LiveData<EntityDailyBp> J;
    @Nullable
    public LiveData<EntityDailyHeartRateData> K;
    @Nullable
    public LiveData<EntityHourlyHeartRateData> L;
    @Nullable
    public LiveData<List<HourlyTemperature>> M;
    @Nullable
    public LiveData<List<SleepDataModelForLastNight>> d;
    @NotNull
    public final String e;
    public final int f;
    @Nullable
    public BroadcastReceiver g;
    @NotNull
    public final Handler h;
    public boolean i;
    public boolean j;
    public long k;
    @NotNull
    public MutableLiveData<SyncState> l;
    @Nullable
    public ContractUpdateHrBpHealthTextListener m;
    public PermissionListener mListener;
    @Nullable
    public LifecycleOwner n;
    @Nullable
    public SyncManager o;
    public SessionManager p;
    public Context q;
    @NotNull
    public MutableLiveData<String> r;
    @NotNull
    public MutableLiveData<Boolean> s;
    public SyncTroubleShoutListener syncTroubleShoutListener;
    @Nullable
    public String t;
    public int u;
    public long v;
    public ViewModelListener viewModelListener;
    public int w;
    @Nullable
    public CountDownTimer x;
    @Nullable
    public CountDownTimer y;
    @NotNull
    public final List<MatchState> z;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Spo2DeviceType.values().length];
            try {
                iArr[Spo2DeviceType.PPG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Spo2DeviceType.BLUETOOTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Spo2DeviceType.OCR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Spo2DeviceType.MANUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ActivityMode.values().length];
            try {
                iArr2[ActivityMode.WALK.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ActivityMode.RUN.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ActivityMode.PHYSICAL_ACTIVITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ActivityMode.BADMINTON.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[ActivityMode.FREE_EXERCISE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[ActivityMode.BASKETBALL.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[ActivityMode.CYCLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[ActivityMode.MEDITATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[ActivityMode.TREADMILL.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[ActivityMode.CLIMBING.ordinal()] = 10;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[ActivityMode.DANCE.ordinal()] = 11;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[ActivityMode.HIKING.ordinal()] = 12;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[ActivityMode.FOOTBALL.ordinal()] = 13;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[ActivityMode.TENNIS.ordinal()] = 14;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr2[ActivityMode.WORKOUT.ordinal()] = 15;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr2[ActivityMode.YOGA.ordinal()] = 16;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr2[ActivityMode.SKIPPING.ordinal()] = 17;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr2[ActivityMode.ELLIPTICAL.ordinal()] = 18;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr2[ActivityMode.ROWING.ordinal()] = 19;
            } catch (NoSuchFieldError unused23) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard", f = "ViewModelActivityDashboard.kt", i = {0, 0, 0}, l = {2563}, m = "getEntityWorkoutSession", n = {"this", "sportsHistoryResponse", "entityWorkoutSession"}, s = {"L$0", "L$1", "L$2"})
    /* loaded from: classes2.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelActivityDashboard.this.w(null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$populateDeviceCapabilityConfig$1", f = "ViewModelActivityDashboard.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DeviceRemoteConfig $deviceRemoteConfig;
        public int label;
        public final /* synthetic */ ViewModelActivityDashboard this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(DeviceRemoteConfig deviceRemoteConfig, ViewModelActivityDashboard viewModelActivityDashboard, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$deviceRemoteConfig = deviceRemoteConfig;
            this.this$0 = viewModelActivityDashboard;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$deviceRemoteConfig, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:180:0x02e0, code lost:
            r0 = r5.getExtendedNotifications().getFwToExclude().iterator();
         */
        /* JADX WARN: Code restructure failed: missing block: B:182:0x02f0, code lost:
            if (r0.hasNext() == false) goto L187;
         */
        /* JADX WARN: Code restructure failed: missing block: B:183:0x02f2, code lost:
            r4 = r0.next();
            r5 = com.coveiot.android.leonardo.utils.PayUtils.INSTANCE;
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, "fTExclude");
         */
        /* JADX WARN: Code restructure failed: missing block: B:184:0x0305, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual(r5.getVersionCodeFromFirmware(r4), r6) == false) goto L181;
         */
        /* JADX WARN: Code restructure failed: missing block: B:185:0x0307, code lost:
            r7.setExtendedAppNotificationSupported(false);
         */
        /* JADX WARN: Removed duplicated region for block: B:110:0x01a8 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:123:0x01e0 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:139:0x0229 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:152:0x0261 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:168:0x02aa A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0091 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00c6 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x00d8 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0129 A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x015f A[Catch: Exception -> 0x0323, TryCatch #0 {Exception -> 0x0323, blocks: (B:30:0x006f, B:32:0x0077, B:33:0x007b, B:35:0x0085, B:41:0x0091, B:43:0x009e, B:44:0x00a2, B:46:0x00b4, B:48:0x00ba, B:54:0x00c6, B:56:0x00cc, B:62:0x00d8, B:64:0x00e9, B:66:0x00f1, B:67:0x00f5, B:69:0x0103, B:70:0x0108, B:73:0x0110, B:75:0x011d, B:81:0x0129, B:83:0x013c, B:85:0x0146, B:86:0x0149, B:88:0x0153, B:94:0x015f, B:95:0x016b, B:97:0x0171, B:99:0x0186, B:100:0x0189, B:102:0x018f, B:104:0x019c, B:110:0x01a8, B:112:0x01bd, B:114:0x01c7, B:115:0x01ca, B:117:0x01d4, B:123:0x01e0, B:124:0x01ec, B:126:0x01f2, B:128:0x0207, B:129:0x020a, B:131:0x0210, B:133:0x021d, B:139:0x0229, B:141:0x023e, B:143:0x0248, B:144:0x024b, B:146:0x0255, B:152:0x0261, B:153:0x026d, B:155:0x0273, B:157:0x0288, B:158:0x028b, B:160:0x0291, B:162:0x029e, B:168:0x02aa, B:170:0x02bf, B:172:0x02c9, B:173:0x02cc, B:175:0x02d6, B:180:0x02e0, B:181:0x02ec, B:183:0x02f2, B:185:0x0307, B:186:0x030a, B:188:0x0312, B:190:0x0317), top: B:198:0x006f }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 818
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$processBleResponse$1", f = "ViewModelActivityDashboard.kt", i = {0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5}, l = {2337, 2339, 2342, 2351, 2365, 2377, 2395}, m = "invokeSuspend", n = {"sportsHistoryResponse", "sportsHistoryResponse", "entityWorkoutSession", "sportsHistoryResponse", "entityWorkoutSession", "segmentID", "sportsHistoryResponse", "entityWorkoutSession", "segmentID", "sportsHistoryResponse", "sportsHistoryResponse"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$0"})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ BleBaseResponse $response;
        public final /* synthetic */ long $startTime;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public final /* synthetic */ ViewModelActivityDashboard this$0;

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$processBleResponse$1$1", f = "ViewModelActivityDashboard.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ ViewModelActivityDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ViewModelActivityDashboard viewModelActivityDashboard, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = viewModelActivityDashboard;
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
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.checkForAgpsFileUpdate();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$processBleResponse$1$2", f = "ViewModelActivityDashboard.kt", i = {}, l = {2401}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ long $startTime;
            public int label;
            public final /* synthetic */ ViewModelActivityDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(ViewModelActivityDashboard viewModelActivityDashboard, long j, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = viewModelActivityDashboard;
                this.$startTime = j;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$startTime, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
                    Context context = this.this$0.q;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context = null;
                    }
                    long j = this.$startTime;
                    this.label = 1;
                    if (companion.getInstance(context).saveUnSyncedSessionsToServer(j, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(BleBaseResponse bleBaseResponse, ViewModelActivityDashboard viewModelActivityDashboard, long j, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$response = bleBaseResponse;
            this.this$0 = viewModelActivityDashboard;
            this.$startTime = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$response, this.this$0, this.$startTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:17:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00d1 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00d2  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00ea  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0109 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x010a  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x011d  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0142 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0143  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x015c  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0164  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x017f  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x018f  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x028a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:69:0x028b  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0294  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x02c2  */
        /* JADX WARN: Type inference failed for: r12v4, types: [android.content.Context] */
        /* JADX WARN: Type inference failed for: r12v5 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r21) {
            /*
                Method dump skipped, instructions count: 766
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$processBleResponse$2", f = "ViewModelActivityDashboard.kt", i = {}, l = {2413}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ long $startTime;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(long j, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$startTime = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.$startTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
                Context context = ViewModelActivityDashboard.this.q;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context = null;
                }
                long j = this.$startTime;
                this.label = 1;
                if (companion.getInstance(context).saveUnSyncedSessionsToServer(j, this) == coroutine_suspended) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelActivityDashboard(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.e = "ViewModelDashboard";
        this.f = 60;
        this.h = new Handler(Looper.getMainLooper());
        this.k = -1L;
        this.l = new MutableLiveData<>();
        this.r = new MutableLiveData<>("");
        this.s = new MutableLiveData<>();
        this.u = -1;
        this.z = CollectionsKt__CollectionsKt.listOf((Object[]) new MatchState[]{new MatchState("Match yet to Start", "- -", "- -"), new MatchState("India won the toss", "- -", "- -"), new MatchState("Match ongoing", "3/0 (0.5) *", "Yet to bat"), new MatchState("Match ongoing", "11/0 (2.3) *", "Yet to bat"), new MatchState("Match ongoing", "14/1 (3.1) *", "Yet to bat"), new MatchState("Match ongoing", "26/2 (4.2) *", "Yet to bat"), new MatchState("Match ongoing", "32/2 (5.2) *", "Yet to bat"), new MatchState("Match ongoing", "33/3 (6.0) *", "Yet to bat"), new MatchState("Match ongoing", "46/3 (7.1) *", "Yet to bat"), new MatchState("Match ongoing", "58/3 (8.4) *", "Yet to bat"), new MatchState("Match ongoing", "60/4 (9.1) *", "Yet to bat"), new MatchState("Match ongoing", "64/4 (10) *", "Yet to bat"), new MatchState("Match ongoing", "72/4 (10.5) *", "Yet to bat"), new MatchState("Match ongoing", "87/4 (12.1) *", "Yet to bat"), new MatchState("Match ongoing", "103/4 (13.4) *", "Yet to bat"), new MatchState("Match ongoing", "121/4 (14.5) *", "Yet to bat"), new MatchState("Match ongoing", "138/4 (16.3) *", "Yet to bat"), new MatchState("Match ongoing", "152/4 (17.5) *", "Yet to bat"), new MatchState("Match ongoing", "167/4 (19.1) *", "Yet to bat"), new MatchState("Match ongoing", "178/4 (20) *", "Yet to bat"), new MatchState("Drinks break", "178/4 (20) ", "Yet to bat"), new MatchState("Match ongoing", "178/4 (20) ", "2/0 (0.4) *"), new MatchState("Match ongoing", "178/4 (20) ", "5/1 (1.6) *"), new MatchState("Match ongoing", "178/4 (20) ", "21/3 (2.5) *"), new MatchState("Match ongoing", "178/4 (20) ", "34/4 (5.4) *"), new MatchState("Match ongoing", "178/4 (20) ", "50/4 (6.3) *"), new MatchState("Match ongoing", "178/4 (20) ", "50/5 (6.5) *"), new MatchState("Match ongoing", "178/4 (20) ", "72/6 (7.4) *"), new MatchState("Match ongoing", "178/4 (20) ", "81/8 (9.2) *"), new MatchState("Match ongoing", "178/4 (20) ", "90/9 (9.4) *"), new MatchState("Match ongoing", "178/4 (20) ", "94/10 (10.4) *"), new MatchState("India won the match", "178/4 (20) ", "94/10 (10.4)")});
        this.A = new MutableLiveData<>();
        this.B = new ProgressUpdateData();
        this.q = application;
        this.l.setValue(SyncState.IDLE);
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        SessionManager sessionManager = SessionManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(sessionManager, "getInstance(mContext)");
        this.p = sessionManager;
        Application application2 = getApplication();
        Intrinsics.checkNotNullExpressionValue(application2, "getApplication<GenericBandApplication>()");
        this.D = (GenericBandApplication) application2;
        this.H = new Observer() { // from class: com.coveiot.android.leonardo.dashboard.c1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ViewModelActivityDashboard.i0(ViewModelActivityDashboard.this, (LiveStepsData) obj);
            }
        };
        this.I = new Observer() { // from class: com.coveiot.android.leonardo.dashboard.b1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ViewModelActivityDashboard.h0(ViewModelActivityDashboard.this, (LiveHealthData) obj);
            }
        };
    }

    public static final void V(ViewModelActivityDashboard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.e, "Sync completed twice");
        if (this$0.syncTroubleShoutListener != null) {
            this$0.getSyncTroubleShoutListener().dismissSyncTroubleShoot();
        }
        this$0.w = 0;
        if (this$0.D.isWatchReconnected()) {
            this$0.D.setWatchReconnected(false);
            CoveEventBusManager.getInstance().getEventBus().post(new DeviceReconnected());
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context context = this$0.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (companion.isSmaDevice(context)) {
            LastDataHelper.Companion companion2 = LastDataHelper.Companion;
            Context context3 = this$0.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context3 = null;
            }
            companion2.getInstance(context3).sendSleepDataToBand(new LastDataHelper.UploadCompletionListner() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$postDataSyncComplete$1$2
                @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                public void onDataUploadeComplete() {
                }

                @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
                public void onUploadFailed() {
                }
            });
        }
        LastDataHelper.Companion companion3 = LastDataHelper.Companion;
        Context context4 = this$0.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context4;
        }
        companion3.getInstance(context2).saveLastDataInfoToServer(new LastDataHelper.UploadCompletionListner() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$postDataSyncComplete$1$3
            @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
            public void onDataUploadeComplete() {
            }

            @Override // com.coveiot.android.dashboard2.LastDataHelper.UploadCompletionListner
            public void onUploadFailed() {
            }
        });
    }

    public static final void f0(ViewModelActivityDashboard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ProgressUpdateData progressUpdateData = this$0.B;
        Context context = this$0.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        progressUpdateData.setTextProgress(context.getResources().getString(R.string.updating_agps_file));
        this$0.A.postValue(this$0.B);
    }

    public static final void g0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void h0(ViewModelActivityDashboard this$0, LiveHealthData liveHealthData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (liveHealthData != null) {
            String str = this$0.e;
            LogHelper.d(str, "liveHeartRate: " + liveHealthData.getHeartRate());
            CoveEventBusManager.getInstance().getEventBus().post(new LiveHeartRate(liveHealthData.getHeartRate()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c0, code lost:
        if (r7.isBESDevice(r1) != false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void i0(com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard r9, com.coveiot.android.bleabstract.response.LiveStepsData r10) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            if (r10 == 0) goto Ldd
            java.lang.String r0 = r9.e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "liveSteps: "
            r1.append(r2)
            int r2 = r10.getLiveSteps()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.coveiot.utils.utility.LogHelper.d(r0, r1)
            int r10 = r10.getLiveSteps()
            android.content.Context r0 = r9.q
            r1 = 0
            java.lang.String r2 = "mContext"
            if (r0 != 0) goto L30
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L30:
            com.coveiot.covepreferences.SessionManager r0 = com.coveiot.covepreferences.SessionManager.getInstance(r0)
            com.coveiot.covepreferences.data.ProfileData r0 = r0.getUserDetails()
            java.lang.String r0 = r0.getHeight()
            java.lang.String r3 = "getInstance(mContext).userDetails.height"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            int r0 = java.lang.Integer.parseInt(r0)
            android.content.Context r3 = r9.q
            if (r3 != 0) goto L4d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r3 = r1
        L4d:
            com.coveiot.covepreferences.SessionManager r3 = com.coveiot.covepreferences.SessionManager.getInstance(r3)
            com.coveiot.covepreferences.data.ProfileData r3 = r3.getUserDetails()
            java.lang.String r3 = r3.getWeight()
            java.lang.String r4 = "getInstance(mContext).userDetails.weight"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            double r3 = java.lang.Double.parseDouble(r3)
            int r3 = (int) r3
            com.coveiot.repository.profile.ProfileRepository r4 = com.coveiot.repository.profile.ProfileRepository.getInstance()
            android.content.Context r5 = r9.q
            if (r5 != 0) goto L6f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r5 = r1
        L6f:
            com.coveiot.covedb.profile.entities.EntityProfile r4 = r4.getLatestProfileData(r5)
            int r4 = r4.walkStrideLength
            double r5 = com.coveiot.utils.utility.AppUtils.calculateCalories(r10, r0, r3, r4)
            com.coveiot.android.devicemodels.DeviceUtils$Companion r7 = com.coveiot.android.devicemodels.DeviceUtils.Companion
            android.content.Context r8 = r9.q
            if (r8 != 0) goto L83
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r1
        L83:
            boolean r8 = r7.isCZDevice(r8)
            if (r8 != 0) goto Lc2
            android.content.Context r8 = r9.q
            if (r8 != 0) goto L91
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r1
        L91:
            boolean r8 = r7.isCADevice(r8)
            if (r8 != 0) goto Lc2
            android.content.Context r8 = r9.q
            if (r8 != 0) goto L9f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r1
        L9f:
            boolean r8 = r7.isCYDevice(r8)
            if (r8 != 0) goto Lc2
            android.content.Context r8 = r9.q
            if (r8 != 0) goto Lad
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r1
        Lad:
            boolean r8 = r7.isPS1Device(r8)
            if (r8 != 0) goto Lc2
            android.content.Context r9 = r9.q
            if (r9 != 0) goto Lbb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto Lbc
        Lbb:
            r1 = r9
        Lbc:
            boolean r9 = r7.isBESDevice(r1)
            if (r9 == 0) goto Lc8
        Lc2:
            com.coveiot.android.leonardo.utils.PayUtils r9 = com.coveiot.android.leonardo.utils.PayUtils.INSTANCE
            double r5 = r9.calculateCaloriesForCZ(r10, r0, r3, r4)
        Lc8:
            int r9 = com.coveiot.utils.utility.AppUtils.calculateDistance(r10, r0, r4)
            com.coveiot.utils.CoveEventBusManager r0 = com.coveiot.utils.CoveEventBusManager.getInstance()
            com.squareup.otto.Bus r0 = r0.getEventBus()
            com.coveiot.utils.eventmodels.LiveSteps r1 = new com.coveiot.utils.eventmodels.LiveSteps
            float r2 = (float) r5
            r1.<init>(r10, r2, r9)
            r0.post(r1)
        Ldd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard.i0(com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard, com.coveiot.android.bleabstract.response.LiveStepsData):void");
    }

    public static final void k(ViewModelActivityDashboard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateRegistrationToServer();
    }

    public static /* synthetic */ String n(ViewModelActivityDashboard viewModelActivityDashboard, ActivityModeSummaryResponse activityModeSummaryResponse, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return viewModelActivityDashboard.m(activityModeSummaryResponse, z);
    }

    public static /* synthetic */ void saveSPO2Value$default(ViewModelActivityDashboard viewModelActivityDashboard, double d2, Spo2DeviceType spo2DeviceType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        viewModelActivityDashboard.saveSPO2Value(d2, spo2DeviceType, z);
    }

    public static /* synthetic */ void syncData$default(ViewModelActivityDashboard viewModelActivityDashboard, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        viewModelActivityDashboard.syncData(z);
    }

    public final ArrayList<HikingSample> A(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<HikingSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    HikingSample hikingSample = new HikingSample();
                    hikingSample.setSess_id(str);
                    hikingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        hikingSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    hikingSample.setSampleData(sampleData);
                    arrayList4.add(hikingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void B() {
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().getData(new GetLatestHealthDataRequest(HealthVitalsType.HEART_RATE), new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getLatestHeartRateData$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String str = ViewModelActivityDashboard.this.e;
                LogHelper.d(str, "error GetLatestHealthDataRequest: " + error.getErrorMsg());
                ViewModelActivityDashboard.this.D();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse");
                GetLatestHealthDataResponse getLatestHealthDataResponse = (GetLatestHealthDataResponse) responseData;
                String str = ViewModelActivityDashboard.this.e;
                LogHelper.d(str, "GetLatestHealthDataRequest: " + getLatestHealthDataResponse);
                HealthData healthData = getLatestHealthDataResponse.getHealthData();
                if (healthData.getValue() > 0) {
                    LatestHealthDataModel latestHealthDataModel = new LatestHealthDataModel();
                    latestHealthDataModel.setTimestamp(healthData.getTimestamp());
                    latestHealthDataModel.setValue(healthData.getValue());
                    latestHealthDataModel.setHealthDataType(healthData.getHealthVitalsType());
                    if (healthData.getTimestamp() > 946665000000L && !Dashboard2Utils.Companion.isFutureDate(new Date(healthData.getTimestamp()))) {
                        Context context2 = ViewModelActivityDashboard.this.q;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context2 = null;
                        }
                        UserDataManager.getInstance(context2).saveLatestHRValue(latestHealthDataModel);
                    }
                }
                ViewModelActivityDashboard.this.D();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void C() {
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().getData(new GetLatestHealthDataRequest(HealthVitalsType.SPO2), new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getLatestSPO2Data$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String str = ViewModelActivityDashboard.this.e;
                LogHelper.d(str, "error GetLatestHealthDataRequest: " + error.getErrorMsg());
                ViewModelActivityDashboard.this.B();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse");
                GetLatestHealthDataResponse getLatestHealthDataResponse = (GetLatestHealthDataResponse) responseData;
                String str = ViewModelActivityDashboard.this.e;
                LogHelper.d(str, "GetLatestHealthDataRequest: " + getLatestHealthDataResponse);
                HealthData healthData = getLatestHealthDataResponse.getHealthData();
                if (healthData.getValue() > 0) {
                    LatestHealthDataModel latestHealthDataModel = new LatestHealthDataModel();
                    latestHealthDataModel.setTimestamp(healthData.getTimestamp());
                    latestHealthDataModel.setValue(healthData.getValue());
                    latestHealthDataModel.setHealthDataType(healthData.getHealthVitalsType());
                    if (healthData.getTimestamp() > 946665000000L && !Dashboard2Utils.Companion.isFutureDate(new Date(healthData.getTimestamp()))) {
                        Context context2 = ViewModelActivityDashboard.this.q;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context2 = null;
                        }
                        UserDataManager.getInstance(context2).saveLatestSpo2Value(latestHealthDataModel);
                    }
                }
                ViewModelActivityDashboard.this.B();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void D() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            BleApiManager.getInstance(context2).getBleApi().getData(new GetLatestHealthDataRequest(HealthVitalsType.TEMPERATURE), new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getLatestTemperatureData$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String str = ViewModelActivityDashboard.this.e;
                    LogHelper.d(str, "error GetLatestHealthDataRequest: " + error.getErrorMsg());
                    ViewModelActivityDashboard.this.e0();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse");
                    GetLatestHealthDataResponse getLatestHealthDataResponse = (GetLatestHealthDataResponse) responseData;
                    String str = ViewModelActivityDashboard.this.e;
                    LogHelper.d(str, "GetLatestHealthDataRequest: " + getLatestHealthDataResponse);
                    HealthData healthData = getLatestHealthDataResponse.getHealthData();
                    if (healthData.getValue() > 0) {
                        LatestHealthDataModel latestHealthDataModel = new LatestHealthDataModel();
                        latestHealthDataModel.setTimestamp(healthData.getTimestamp());
                        latestHealthDataModel.setValue(healthData.getValue());
                        latestHealthDataModel.setHealthDataType(healthData.getHealthVitalsType());
                        if (healthData.getTimestamp() > 946665000000L && !Dashboard2Utils.Companion.isFutureDate(new Date(healthData.getTimestamp()))) {
                            Context context4 = ViewModelActivityDashboard.this.q;
                            if (context4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                context4 = null;
                            }
                            UserDataManager.getInstance(context4).saveLatestTemperatureValue(latestHealthDataModel);
                        }
                    }
                    ViewModelActivityDashboard.this.e0();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        e0();
    }

    public final ArrayList<MeditationSample> E(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<MeditationSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    MeditationSample meditationSample = new MeditationSample();
                    meditationSample.setSess_id(str);
                    meditationSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        meditationSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    meditationSample.setSampleData(sampleData);
                    arrayList4.add(meditationSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<PhysicalActivitySample> F(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<PhysicalActivitySample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    PhysicalActivitySample physicalActivitySample = new PhysicalActivitySample();
                    physicalActivitySample.setSess_id(str);
                    physicalActivitySample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        physicalActivitySample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    physicalActivitySample.setSampleData(sampleData);
                    arrayList4.add(physicalActivitySample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<RowingMachineSample> G(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<RowingMachineSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    RowingMachineSample rowingMachineSample = new RowingMachineSample();
                    rowingMachineSample.setSess_id(str);
                    rowingMachineSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        rowingMachineSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    rowingMachineSample.setSampleData(sampleData);
                    arrayList3.add(rowingMachineSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<RunSample> H(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<RunSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    RunSample runSample = new RunSample();
                    runSample.setSess_id(str);
                    runSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        runSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    runSample.setSampleData(sampleData);
                    arrayList4.add(runSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<SkippingSample> I(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<SkippingSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    SkippingSample skippingSample = new SkippingSample();
                    skippingSample.setSess_id(str);
                    skippingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        skippingSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    skippingSample.setSampleData(sampleData);
                    arrayList3.add(skippingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final String J(Spo2DeviceType spo2DeviceType) {
        int i = WhenMappings.$EnumSwitchMapping$0[spo2DeviceType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return Source.MANUAL.name();
                    }
                    throw new NoWhenBranchMatchedException();
                }
                return Source.OCR.name();
            }
            return Source.FROM_SECONDARY_DEVICE.name();
        }
        return Source.FROM_DEVICE.name();
    }

    public final ArrayList<TennisSample> K(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<TennisSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    TennisSample tennisSample = new TennisSample();
                    tennisSample.setSess_id(str);
                    tennisSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        tennisSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    tennisSample.setSampleData(sampleData);
                    arrayList3.add(tennisSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final Object L(List<ActivityHeartRateSample> list, Continuation<? super TimeSpentHeartRateZone> continuation) {
        TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        HeartRateZoneRanges geHeartRateZoneRanges = workoutUtils.geHeartRateZoneRanges(new PreferenceManager(context).getAge());
        Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        int sampleRateForSessionInSecs = companion.getSampleRateForSessionInSecs(context2);
        for (ActivityHeartRateSample activityHeartRateSample : list) {
            int hrValue = activityHeartRateSample.getHrValue();
            boolean z = true;
            if (hrValue <= geHeartRateZoneRanges.getZone2LowLimit() && geHeartRateZoneRanges.getZone1LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone1Time(timeSpentHeartRateZone.getZone1Time() + sampleRateForSessionInSecs);
            } else if (hrValue <= geHeartRateZoneRanges.getZone3LowLimit() && geHeartRateZoneRanges.getZone2LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone2Time(timeSpentHeartRateZone.getZone2Time() + sampleRateForSessionInSecs);
            } else if (hrValue <= geHeartRateZoneRanges.getZone4LowLimit() && geHeartRateZoneRanges.getZone3LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone3Time(timeSpentHeartRateZone.getZone3Time() + sampleRateForSessionInSecs);
            } else if (hrValue <= geHeartRateZoneRanges.getZone5LowLimit() && geHeartRateZoneRanges.getZone4LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone4Time(timeSpentHeartRateZone.getZone4Time() + sampleRateForSessionInSecs);
            } else {
                if ((hrValue > geHeartRateZoneRanges.getZone5HighLimit() || geHeartRateZoneRanges.getZone5LowLimit() > hrValue) ? false : false) {
                    timeSpentHeartRateZone.setZone5Time(timeSpentHeartRateZone.getZone5Time() + sampleRateForSessionInSecs);
                }
            }
        }
        return timeSpentHeartRateZone;
    }

    public final ArrayList<TreadmillSample> M(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<TreadmillSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    TreadmillSample treadmillSample = new TreadmillSample();
                    treadmillSample.setSess_id(str);
                    treadmillSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        treadmillSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    treadmillSample.setSampleData(sampleData);
                    arrayList4.add(treadmillSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<WalkSample> N(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<WalkSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    WalkSample walkSample = new WalkSample();
                    walkSample.setSess_id(str);
                    walkSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        walkSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    walkSample.setSampleData(sampleData);
                    arrayList4.add(walkSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.lang.String] */
    public final void O(final double d2, final double d3, final WeatherResultListener weatherResultListener) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = WeatherUnit.METRIC.weatherUnit();
        WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WeatherAppPreferenceManager companion2 = companion.getInstance(context);
        Intrinsics.checkNotNull(companion2);
        if (Intrinsics.areEqual(companion2.isMetricUnitEnabled(), Boolean.FALSE)) {
            objectRef.element = WeatherUnit.IMPERIAL.weatherUnit();
        }
        WeatherApiCallsManager.Companion companion3 = WeatherApiCallsManager.Companion;
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        WeatherApiCallsManager weatherApiCallsManager = companion3.getInstance(context2).getWeatherApiCallsManager();
        Intrinsics.checkNotNull(weatherApiCallsManager);
        T weatherUnit = objectRef.element;
        Intrinsics.checkNotNullExpressionValue(weatherUnit, "weatherUnit");
        weatherApiCallsManager.getOpenWeatherMapDailyForecastInfo(d2, d3, 7, (String) weatherUnit, new WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getWeatherForecastInfo$1
            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onError(@NotNull WeatherApiErrorModel obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                LogHelper.d("getWeatherForecastInfo", obj.toString());
                Integer code = obj.getCode();
                Context context4 = null;
                if (code == null || code.intValue() != 403) {
                    weatherResultListener.onFailure("Unable to fetch weather updates");
                    WeatherPreferenceManager.Companion companion4 = WeatherPreferenceManager.Companion;
                    Context context5 = ViewModelActivityDashboard.this.q;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context4 = context5;
                    }
                    WeatherPreferenceManager companion5 = companion4.getInstance(context4);
                    Intrinsics.checkNotNull(companion5);
                    companion5.saveWeatherForecastModel(new WeatherForecastModel(null, null, null, null, null, null, null, null, null, null, null, 2047, null));
                    return;
                }
                WeatherApiCallsManager.Companion companion6 = WeatherApiCallsManager.Companion;
                Context context6 = ViewModelActivityDashboard.this.q;
                if (context6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context4 = context6;
                }
                companion6.getInstance(context4).invalidate();
                ViewModelActivityDashboard.this.getConfigUrlsFromServer();
            }

            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onSuccess(@Nullable WeatherForecastModel weatherForecastModel) {
                LogHelper.d("getWeatherForecastInfo", FirebaseAnalytics.Param.SUCCESS);
                if (weatherForecastModel != null) {
                    WeatherPreferenceManager.Companion companion4 = WeatherPreferenceManager.Companion;
                    Context context4 = ViewModelActivityDashboard.this.q;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context4 = null;
                    }
                    WeatherPreferenceManager companion5 = companion4.getInstance(context4);
                    Intrinsics.checkNotNull(companion5);
                    companion5.saveWeatherForecastModel(weatherForecastModel);
                    WeatherAppPreferenceManager.Companion companion6 = WeatherAppPreferenceManager.Companion;
                    Context context5 = ViewModelActivityDashboard.this.q;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context5 = null;
                    }
                    WeatherAppPreferenceManager companion7 = companion6.getInstance(context5);
                    if (companion7 != null) {
                        companion7.saveOpenWeatherLatTimeStamp(System.currentTimeMillis());
                    }
                } else {
                    WeatherPreferenceManager.Companion companion8 = WeatherPreferenceManager.Companion;
                    Context context6 = ViewModelActivityDashboard.this.q;
                    if (context6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context6 = null;
                    }
                    WeatherPreferenceManager companion9 = companion8.getInstance(context6);
                    Intrinsics.checkNotNull(companion9);
                    companion9.saveWeatherForecastModel(new WeatherForecastModel(null, null, null, null, null, null, null, null, null, null, null, 2047, null));
                }
                WeatherApiCallsManager.Companion companion10 = WeatherApiCallsManager.Companion;
                Context context7 = ViewModelActivityDashboard.this.q;
                if (context7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context7 = null;
                }
                WeatherApiCallsManager singletonHolder = companion10.getInstance(context7);
                double d4 = d2;
                double d5 = d3;
                String str = objectRef.element;
                final WeatherResultListener weatherResultListener2 = weatherResultListener;
                singletonHolder.getOpenWeatherMapCurrentWeatherInfo(d4, d5, str, new WeatherApiResponseListener<CurrentForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getWeatherForecastInfo$1$onSuccess$1
                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                    public void onError(@NotNull WeatherApiErrorModel obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        WeatherResultListener.this.onFailure("Unable to fetch weather updates");
                    }

                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                    public void onSuccess(@Nullable CurrentForecastModel currentForecastModel) {
                        WeatherResultListener.this.onSuccess();
                    }
                });
            }
        });
    }

    public final ArrayList<WorkoutSample> P(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<WorkoutSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    WorkoutSample workoutSample = new WorkoutSample();
                    workoutSample.setSess_id(str);
                    workoutSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        workoutSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    workoutSample.setSampleData(sampleData);
                    arrayList4.add(workoutSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<YogaSample> Q(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<YogaSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    YogaSample yogaSample = new YogaSample();
                    yogaSample.setSess_id(str);
                    yogaSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        yogaSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    yogaSample.setSampleData(sampleData);
                    arrayList3.add(yogaSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void R() {
        if (U()) {
            j();
            return;
        }
        this.h.removeCallbacksAndMessages(null);
        Z();
    }

    public final Object S(ActivityMode activityMode, ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, String str, String str2, int i, Continuation<? super Unit> continuation) {
        int i2 = i;
        ArrayList<ActivityDataSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    ActivityDataSample activityDataSample = new ActivityDataSample();
                    activityDataSample.setSessionID(str);
                    activityDataSample.setSegmentID(str2);
                    activityDataSample.setActivityType(activityMode.name());
                    activityDataSample.setTimeStamp(j4);
                    ActivityStepsSample i3 = i(arrayList, j4, i2);
                    if (i3 != null) {
                        activityDataSample.setCalories((float) i3.getCalories());
                        activityDataSample.setDistance((int) i3.getDistance());
                        activityDataSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i2);
                    if (h != null) {
                        activityDataSample.setHrValue(h.getHrValue());
                    }
                    ActivityGPSSample g = g(arrayList3, j4, i2);
                    if (g != null) {
                        activityDataSample.setLatitude(g.getLocation().latitude);
                        activityDataSample.setLongitude(g.getLocation().longitude);
                    }
                    arrayList4.add(activityDataSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    i2 = i;
                }
            }
            if (arrayList4.size() > 0) {
                WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
                Context context = this.q;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context = null;
                }
                Object insertGenericActivitySampleList = companion.getInstance(context).insertGenericActivitySampleList(arrayList4, continuation);
                return insertGenericActivitySampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertGenericActivitySampleList : Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final Object T(ActivityMode activityMode, ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, String str, String str2, int i, Continuation<? super Unit> continuation) {
        WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WorkoutSessionRepository singletonHolder = companion.getInstance(context);
        switch (WhenMappings.$EnumSwitchMapping$1[activityMode.ordinal()]) {
            case 1:
                ArrayList<WalkSample> N = N(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((N != null ? N.size() : 0) > 0) {
                    Intrinsics.checkNotNull(N);
                    Object insertWalkSampleList = singletonHolder.insertWalkSampleList(N, continuation);
                    return insertWalkSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertWalkSampleList : Unit.INSTANCE;
                }
                break;
            case 2:
                ArrayList<RunSample> H = H(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((H != null ? H.size() : 0) > 0) {
                    Intrinsics.checkNotNull(H);
                    Object insertRunSampleList = singletonHolder.insertRunSampleList(H, continuation);
                    return insertRunSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertRunSampleList : Unit.INSTANCE;
                }
                break;
            case 3:
                ArrayList<PhysicalActivitySample> F = F(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((F != null ? F.size() : 0) > 0) {
                    Intrinsics.checkNotNull(F);
                    Object insertPhysicalActivitySamples = singletonHolder.insertPhysicalActivitySamples(F, continuation);
                    return insertPhysicalActivitySamples == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertPhysicalActivitySamples : Unit.INSTANCE;
                }
                break;
            case 4:
                ArrayList<BadmintonSample> o = o(arrayList, arrayList2, j, j2, i, str, str2);
                if ((o != null ? o.size() : 0) > 0) {
                    Intrinsics.checkNotNull(o);
                    Object insertBadmintonSampleList = singletonHolder.insertBadmintonSampleList(o, continuation);
                    return insertBadmintonSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertBadmintonSampleList : Unit.INSTANCE;
                }
                break;
            case 5:
                ArrayList<FreeExerciseSample> z = z(arrayList, arrayList2, j, j2, i, str, str2);
                if ((z != null ? z.size() : 0) > 0) {
                    Intrinsics.checkNotNull(z);
                    Object insertFreeExerciseSampleList = singletonHolder.insertFreeExerciseSampleList(z, continuation);
                    return insertFreeExerciseSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertFreeExerciseSampleList : Unit.INSTANCE;
                }
                break;
            case 6:
                ArrayList<BasketBallSample> p = p(arrayList, arrayList2, j, j2, i, str, str2);
                if ((p != null ? p.size() : 0) > 0) {
                    Intrinsics.checkNotNull(p);
                    Object insertBasketBallSampleList = singletonHolder.insertBasketBallSampleList(p, continuation);
                    return insertBasketBallSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertBasketBallSampleList : Unit.INSTANCE;
                }
                break;
            case 7:
                ArrayList<CyclingSample> t = t(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((t != null ? t.size() : 0) > 0) {
                    Intrinsics.checkNotNull(t);
                    Object insertCyclingSampleList = singletonHolder.insertCyclingSampleList(t, continuation);
                    return insertCyclingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertCyclingSampleList : Unit.INSTANCE;
                }
                break;
            case 8:
                ArrayList<MeditationSample> E = E(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((E != null ? E.size() : 0) > 0) {
                    Intrinsics.checkNotNull(E);
                    Object insertMeditationSampleList = singletonHolder.insertMeditationSampleList(E, continuation);
                    return insertMeditationSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertMeditationSampleList : Unit.INSTANCE;
                }
                break;
            case 9:
                ArrayList<TreadmillSample> M = M(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((M != null ? M.size() : 0) > 0) {
                    Intrinsics.checkNotNull(M);
                    Object insertTreadmillSampleList = singletonHolder.insertTreadmillSampleList(M, continuation);
                    return insertTreadmillSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertTreadmillSampleList : Unit.INSTANCE;
                }
                break;
            case 10:
                ArrayList<ClimbingSample> r = r(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((r != null ? r.size() : 0) > 0) {
                    Intrinsics.checkNotNull(r);
                    Object insertClimbingSampleList = singletonHolder.insertClimbingSampleList(r, continuation);
                    return insertClimbingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertClimbingSampleList : Unit.INSTANCE;
                }
                break;
            case 11:
                ArrayList<DanceSample> u = u(arrayList, arrayList2, j, j2, i, str, str2);
                if ((u != null ? u.size() : 0) > 0) {
                    Intrinsics.checkNotNull(u);
                    Object insertDanceSampleList = singletonHolder.insertDanceSampleList(u, continuation);
                    return insertDanceSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertDanceSampleList : Unit.INSTANCE;
                }
                break;
            case 12:
                ArrayList<HikingSample> A = A(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((A != null ? A.size() : 0) > 0) {
                    Intrinsics.checkNotNull(A);
                    Object insertHikingSampleList = singletonHolder.insertHikingSampleList(A, continuation);
                    return insertHikingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertHikingSampleList : Unit.INSTANCE;
                }
                break;
            case 13:
                ArrayList<FootballSample> y = y(arrayList, arrayList2, j, j2, i, str, str2);
                if ((y != null ? y.size() : 0) > 0) {
                    Intrinsics.checkNotNull(y);
                    Object insertFootBallSampleList = singletonHolder.insertFootBallSampleList(y, continuation);
                    return insertFootBallSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertFootBallSampleList : Unit.INSTANCE;
                }
                break;
            case 14:
                ArrayList<TennisSample> K = K(arrayList, arrayList2, j, j2, i, str, str2);
                if ((K != null ? K.size() : 0) > 0) {
                    Intrinsics.checkNotNull(K);
                    Object insertTennisSampleList = singletonHolder.insertTennisSampleList(K, continuation);
                    return insertTennisSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertTennisSampleList : Unit.INSTANCE;
                }
                break;
            case 15:
                ArrayList<WorkoutSample> P = P(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((P != null ? P.size() : 0) > 0) {
                    Intrinsics.checkNotNull(P);
                    Object insertWorkoutSampleList = singletonHolder.insertWorkoutSampleList(P, continuation);
                    return insertWorkoutSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertWorkoutSampleList : Unit.INSTANCE;
                }
                break;
            case 16:
                ArrayList<YogaSample> Q = Q(arrayList, arrayList2, j, j2, i, str, str2);
                if ((Q != null ? Q.size() : 0) > 0) {
                    Intrinsics.checkNotNull(Q);
                    Object insertYogaSampleList = singletonHolder.insertYogaSampleList(Q, continuation);
                    return insertYogaSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertYogaSampleList : Unit.INSTANCE;
                }
                break;
            case 17:
                ArrayList<SkippingSample> I = I(arrayList, arrayList2, j, j2, i, str, str2);
                if ((I != null ? I.size() : 0) > 0) {
                    Intrinsics.checkNotNull(I);
                    Object insertSkippingSampleList = singletonHolder.insertSkippingSampleList(I, continuation);
                    return insertSkippingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertSkippingSampleList : Unit.INSTANCE;
                }
                break;
            case 18:
                ArrayList<EllipticalSample> v = v(arrayList, arrayList2, j, j2, i, str, str2);
                if ((v != null ? v.size() : 0) > 0) {
                    Intrinsics.checkNotNull(v);
                    Object insertEllipticalSampleList = singletonHolder.insertEllipticalSampleList(v, continuation);
                    return insertEllipticalSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertEllipticalSampleList : Unit.INSTANCE;
                }
                break;
            case 19:
                ArrayList<RowingMachineSample> G = G(arrayList, arrayList2, j, j2, i, str, str2);
                if ((G != null ? G.size() : 0) > 0) {
                    Intrinsics.checkNotNull(G);
                    Object insertRowingMachineSampleList = singletonHolder.insertRowingMachineSampleList(G, continuation);
                    return insertRowingMachineSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertRowingMachineSampleList : Unit.INSTANCE;
                }
                break;
        }
        return Unit.INSTANCE;
    }

    public final boolean U() {
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public final void W(String str, Error error) {
        this.B.setPercentBySatge(0);
        this.B.setProgressPercent(0);
        ProgressUpdateData progressUpdateData = this.B;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        progressUpdateData.setTextProgress(context.getResources().getString(R.string.sync_falied));
        this.A.postValue(this.B);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_DATA_SYNC.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        String value = FirebaseEventParams.MetaData.CV_BT_STATE.getValue();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        hashMap.put(value, AppUtils.isBluetoothEnabled(context3) ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        hashMap.put(FirebaseEventParams.MetaData.CV_TIME_SPENT_MILLS.getValue(), String.valueOf(System.currentTimeMillis() - this.k));
        String value2 = FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue();
        Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        hashMap.put(value2, companion.getPhoneBatteryLevel(context4));
        Context context5 = this.q;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context5 = null;
        }
        BatteryLevelData batteryLevelData = UserDataManager.getInstance(context5).getBatteryLevelData();
        if (batteryLevelData != null) {
            int i = batteryLevelData.batteryLevel;
            String value3 = FirebaseEventParams.MetaData.CV_DVC_BATTERY_LEVEL.getValue();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.02f", Arrays.copyOf(new Object[]{Float.valueOf(i / 100.0f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            hashMap.put(value3, format);
        }
        hashMap.put(FirebaseEventParams.MetaData.CV_EVT_TRIGGER_TYPE.getValue(), this.j ? "manual" : "auto");
        hashMap.put(FirebaseEventParams.MetaData.CV_APP_PROCESS_STATUS.getValue(), "foreground");
        String value4 = FirebaseEventParams.MetaData.CV_DVC_CONN_STATUS.getValue();
        Context context6 = this.q;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context6 = null;
        }
        hashMap.put(value4, BleApiManager.getInstance(context6).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED ? "connected" : "disconnected");
        hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "error");
        if (error != null) {
            if (error.getDataType() != null) {
                String value5 = FirebaseEventParams.MetaData.CV_DATA_TYPE.getValue();
                String dataType = error.getDataType();
                Intrinsics.checkNotNull(dataType);
                hashMap.put(value5, dataType);
            }
            if (companion.getSyncErrorType(error.getCode()) != null) {
                String value6 = FirebaseEventParams.MetaData.CV_ERROR.getValue();
                String syncErrorType = companion.getSyncErrorType(error.getCode());
                Intrinsics.checkNotNull(syncErrorType);
                hashMap.put(value6, syncErrorType);
            }
        }
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        StatusApiDataHelper.Companion companion2 = StatusApiDataHelper.Companion;
        Context context7 = this.q;
        if (context7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context7 = null;
        }
        companion2.getInstance(context7).saveStatusDataToServer(true);
        if (!kotlin.text.m.equals(str, "Interrupted request", true)) {
            LogHelper.d(this.e, "Sync failed  Interrupted request");
            ViewModelListener viewModelListener = getViewModelListener();
            Context context8 = this.q;
            if (context8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context8 = null;
            }
            String string = context8.getString(R.string.sync_falied);
            Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.string.sync_falied)");
            viewModelListener.onDataFailure(string);
        }
        LogHelper.e(this.e, String.valueOf(str));
        this.l.setValue(SyncState.IDLE);
        MutableLiveData<SyncState> mutableLiveData = this.l;
        mutableLiveData.postValue(mutableLiveData.getValue());
        if (kotlin.text.m.equals(str, "PPG operation in progress", true)) {
            return;
        }
        this.w++;
        Context context9 = this.q;
        if (context9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context9;
        }
        companion.resetBluetoothServicesAndConfigurations(context2);
        if (this.w < Integer.parseInt(AppConstants.DATA_SYNC_FAILURE_THRESHOLD.getValue()) || this.syncTroubleShoutListener == null) {
            return;
        }
        getSyncTroubleShoutListener().showSyncTroubleShoot();
        this.w = 0;
    }

    public final void X(BleBaseResponse bleBaseResponse) {
        if (bleBaseResponse != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (bleBaseResponse.getResponseData() == null) {
                Context context = null;
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new d(currentTimeMillis, null), 3, null);
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                Context context2 = this.q;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context2 = null;
                }
                if (!companion.isCZDevice(context2)) {
                    Context context3 = this.q;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context3 = null;
                    }
                    if (!companion.isCADevice(context3)) {
                        Context context4 = this.q;
                        if (context4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context4 = null;
                        }
                        if (!companion.isCYDevice(context4)) {
                            Context context5 = this.q;
                            if (context5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                context5 = null;
                            }
                            if (!companion.isPS1Device(context5)) {
                                Context context6 = this.q;
                                if (context6 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                    context6 = null;
                                }
                                if (!companion.isBESDevice(context6)) {
                                    checkForAgpsFileUpdate();
                                    return;
                                }
                            }
                        }
                    }
                }
                if (bleBaseResponse.isCompleted()) {
                    Context context7 = this.q;
                    if (context7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context = context7;
                    }
                    UserDataManager.getInstance(context).saveLastActivitySyncedDate(Calendar.getInstance().getTime());
                    checkForAgpsFileUpdate();
                }
            } else if (bleBaseResponse.getResponseData() instanceof ActivityModeSummaryResponse) {
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new c(bleBaseResponse, this, currentTimeMillis, null), 2, null);
            }
        }
    }

    public final void Y() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApiManager.getInstance(context2).getBleApi().registerLiveStepsData().observeForever(this.H);
    }

    public final void Z() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            j();
            return;
        }
        this.g = new BroadcastReceiver() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$registerForNetworkConnectivityEvents$1
            @Override // android.content.BroadcastReceiver
            @SuppressLint({"MissingPermission"})
            public void onReceive(@NotNull Context context3, @NotNull Intent intent) {
                Intrinsics.checkNotNullParameter(context3, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                Object systemService2 = context3.getSystemService("connectivity");
                Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.net.ConnectivityManager");
                NetworkInfo activeNetworkInfo2 = ((ConnectivityManager) systemService2).getActiveNetworkInfo();
                if (activeNetworkInfo2 != null && activeNetworkInfo2.isAvailable() && activeNetworkInfo2.isConnected()) {
                    ViewModelActivityDashboard.this.updateRegistrationToServer();
                }
            }
        };
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        context2.registerReceiver(this.g, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void a0() {
        SettingsSyncHelper.Companion companion = SettingsSyncHelper.Companion;
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        companion.getInstance(context).syncBandSettings(new SettingsSyncHelper.SettingsSyncListner() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$syncBandSettings$1
            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
            public void onProgressUpdate(int i) {
            }

            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
            public void onSettingSyncError() {
                ViewModelActivityDashboard.this.c0();
            }

            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
            public void onSettingsSyncCompleted() {
                ViewModelActivityDashboard.this.c0();
            }
        });
    }

    public final void b0() {
        LogHelper.d(this.e, "syncDataFromRepo");
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        if (companion.isTouchELXDevice(context3)) {
            Thread.sleep(1000L);
        }
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        if (BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isBatteryLevelRequestSupported()) {
            Context context5 = this.q;
            if (context5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context5;
            }
            BleApiManager.getInstance(context2).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$syncDataFromRepo$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                    Context context6 = ViewModelActivityDashboard.this.q;
                    Context context7 = null;
                    if (context6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context6 = null;
                    }
                    if (companion2.isMatrixDevice(context6)) {
                        LogHelper.d(ViewModelActivityDashboard.this.e, "BatteryLevelRequest onDataError Matrix");
                        ViewModelActivityDashboard.this.c0();
                        return;
                    }
                    ViewModelActivityDashboard.this.getSyncStateLiveData().setValue(SyncState.IDLE);
                    ViewModelActivityDashboard.this.getSyncStateLiveData().postValue(ViewModelActivityDashboard.this.getSyncStateLiveData().getValue());
                    ViewModelActivityDashboard.this.getProgressUpdateData().setPercentBySatge(0);
                    ViewModelActivityDashboard.this.getProgressUpdateData().setProgressPercent(0);
                    ProgressUpdateData progressUpdateData = ViewModelActivityDashboard.this.getProgressUpdateData();
                    Context context8 = ViewModelActivityDashboard.this.q;
                    if (context8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context7 = context8;
                    }
                    progressUpdateData.setTextProgress(context7.getResources().getString(R.string.sync_falied));
                    ViewModelActivityDashboard.this.getProgressValueSync().postValue(ViewModelActivityDashboard.this.getProgressUpdateData());
                    LogHelper.d(ViewModelActivityDashboard.this.e, "Sync Failed syncDataFromRepo");
                    ViewModelActivityDashboard.this.W("Battery command failed", new Error("Battery command", 1, "Battery"));
                    ViewModelActivityDashboard.this.getBatteryRequestFailure().postValue(Boolean.TRUE);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof BatteryLevelResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                        BatteryLevelResponse batteryLevelResponse = (BatteryLevelResponse) responseData;
                        Context context6 = ViewModelActivityDashboard.this.q;
                        Context context7 = null;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context6 = null;
                        }
                        UserDataManager userDataManager = UserDataManager.getInstance(context6);
                        Integer batteryLevel = batteryLevelResponse.getBatteryLevel();
                        Intrinsics.checkNotNull(batteryLevel);
                        userDataManager.saveBatteryLevelData(new BatteryLevelData(batteryLevel.intValue(), System.currentTimeMillis()));
                        Integer batteryLevel2 = batteryLevelResponse.getBatteryLevel();
                        Intrinsics.checkNotNull(batteryLevel2);
                        if (batteryLevel2.intValue() <= 15) {
                            ViewModelActivityDashboard.this.getBatteryUpdate().postValue(AppConstants.LOW_BATTERY_BROADCAST.getValue());
                        }
                        Context context8 = ViewModelActivityDashboard.this.q;
                        if (context8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        } else {
                            context7 = context8;
                        }
                        if (BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isSyncBandSettingsSupported()) {
                            ViewModelActivityDashboard.this.a0();
                        } else {
                            ViewModelActivityDashboard.this.c0();
                        }
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        Context context6 = this.q;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context6;
        }
        if (BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isSyncBandSettingsSupported()) {
            a0();
        } else {
            c0();
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    public final void beginAgpsFileDownload(@NotNull String sUrl) {
        Intrinsics.checkNotNullParameter(sUrl, "sUrl");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = AppConstants.JSTYLE_AGPS_FILE_NAME.getValue() + ".bin";
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ViewModelActivityDashboard$beginAgpsFileDownload$1(this, objectRef, sUrl, null), 2, null);
    }

    public final boolean boolUpdateAgpsFile() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (PreferenceManager1860.getInstance(context).getAgpsFileLastUpdatedDate() != null) {
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context3 = null;
            }
            String agpsFileLastUpdatedDate = PreferenceManager1860.getInstance(context3).getAgpsFileLastUpdatedDate();
            Intrinsics.checkNotNullExpressionValue(agpsFileLastUpdatedDate, "getInstance(mContext).agpsFileLastUpdatedDate");
            int findDateDifference = AppUtils.findDateDifference(AppUtils.parseDate(agpsFileLastUpdatedDate, "yyyy-MM-dd"), AppUtils.parseDate(PayUtils.getDate(), "yyyy-MM-dd"));
            r3 = findDateDifference >= Integer.parseInt(AppConstants.AGPS_INTERVAL.getValue());
            LogHelper.d(this.e, "boolFileUpdate:", String.valueOf(findDateDifference));
        }
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        PreferenceManager1860 preferenceManager1860 = PreferenceManager1860.getInstance(context4);
        Context context5 = this.q;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context5;
        }
        preferenceManager1860.saveAgpsFileUpdated(PreferenceManager1860.getInstance(context2).getConnectedDeviceMacAddress(), !r3);
        return r3;
    }

    public final void c0() {
        LogHelper.d(this.e, "Sync started");
        this.i = true;
        this.l.setValue(SyncState.SYNINCING);
        MutableLiveData<SyncState> mutableLiveData = this.l;
        mutableLiveData.postValue(mutableLiveData.getValue());
        LogHelper.d(this.e, "syncDataFromRepository");
        SyncManager syncManager = this.o;
        Intrinsics.checkNotNull(syncManager);
        if (syncManager.isSyncInProgress()) {
            return;
        }
        SyncManager syncManager2 = this.o;
        Intrinsics.checkNotNull(syncManager2);
        syncManager2.syncData(new ViewModelActivityDashboard$syncDataFromRepository$1(this), true);
    }

    public final void callOpenWeatherApiKey(@Nullable String str, @NotNull WeatherResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WeatherAppPreferenceManager companion2 = companion.getInstance(context);
        Intrinsics.checkNotNull(companion2);
        Boolean isWeatherEnabled = companion2.isWeatherEnabled();
        WeatherApiCallsManager.Companion companion3 = WeatherApiCallsManager.Companion;
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        if (!companion3.getInstance(context3).isInitialized()) {
            Context context4 = this.q;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context4;
            }
            Intrinsics.checkNotNull(str);
            companion3.getInstance(context2).initWeatherApiClient(str, WeatherAPIType.OPEN_WEATHER_MAP);
        }
        Intrinsics.checkNotNull(isWeatherEnabled);
        if (isWeatherEnabled.booleanValue()) {
            s(listener);
        } else {
            listener.onFailure("Weather not enabled");
        }
    }

    public final void checkForAgpsFileUpdate() {
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isAgpsFileUploadSupported()) {
            if (boolUpdateAgpsFile()) {
                updateAgpsFile();
                return;
            } else {
                postDataSyncComplete();
                return;
            }
        }
        postDataSyncComplete();
    }

    public final boolean checkIsSyncing() {
        if (this.l.getValue() == SyncState.SYNINCING) {
            if (System.currentTimeMillis() - this.k > PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
                SyncManager.getInstance().resetSyncProgress();
                return false;
            }
            LogHelper.d(this.e, "Sync state is synting");
            return true;
        }
        return false;
    }

    public final void checkNavigationDisclaimerAcceptance(@NotNull final String version, @NotNull final Function3<? super String, ? super Boolean, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.getAcceptedLegalDoc(new CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$checkNavigationDisclaimerAcceptance$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Context context = null;
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function3<String, Boolean, Boolean, Unit> function3 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Boolean bool = Boolean.FALSE;
                        function3.invoke(msg, bool, bool);
                        return;
                    }
                    Function3<String, Boolean, Boolean, Unit> function32 = result;
                    Context context2 = this.q;
                    if (context2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context = context2;
                    }
                    String string = context.getString(R.string.some_thing_went_wrong);
                    Boolean bool2 = Boolean.FALSE;
                    function32.invoke(string, bool2, bool2);
                    return;
                }
                Function3<String, Boolean, Boolean, Unit> function33 = result;
                Context context3 = this.q;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context = context3;
                }
                String string2 = context.getString(R.string.some_thing_went_wrong);
                Boolean bool3 = Boolean.FALSE;
                function33.invoke(string2, bool3, bool3);
            }

            /* JADX WARN: Removed duplicated region for block: B:39:0x0067 A[EDGE_INSN: B:39:0x0067->B:30:0x0067 ?: BREAK  , SYNTHETIC] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes r8) {
                /*
                    r7 = this;
                    if (r8 == 0) goto L8f
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r0 = r8.getData()
                    if (r0 == 0) goto L8f
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r0 = r8.getData()
                    java.util.List r0 = r0.getItems()
                    java.lang.String r1 = "legalHistory"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    boolean r1 = r0.isEmpty()
                    r2 = 1
                    r1 = r1 ^ r2
                    if (r1 == 0) goto L81
                    java.lang.String r1 = r3
                    boolean r3 = r0 instanceof java.util.Collection
                    r4 = 0
                    if (r3 == 0) goto L2c
                    boolean r3 = r0.isEmpty()
                    if (r3 == 0) goto L2c
                L2a:
                    r2 = r4
                    goto L67
                L2c:
                    java.util.Iterator r0 = r0.iterator()
                L30:
                    boolean r3 = r0.hasNext()
                    if (r3 == 0) goto L2a
                    java.lang.Object r3 = r0.next()
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Items r3 = (com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes.Items) r3
                    java.lang.String r5 = r3.getType()
                    java.lang.String r6 = "MAP_NAV_DISCLAIMER"
                    boolean r5 = r5.equals(r6)
                    if (r5 == 0) goto L64
                    java.lang.String r5 = r3.medium
                    if (r5 == 0) goto L55
                    int r5 = r5.length()
                    if (r5 != 0) goto L53
                    goto L55
                L53:
                    r5 = r4
                    goto L56
                L55:
                    r5 = r2
                L56:
                    if (r5 == 0) goto L64
                    java.lang.String r3 = r3.getVersion()
                    boolean r3 = r3.equals(r1)
                    if (r3 == 0) goto L64
                    r3 = r2
                    goto L65
                L64:
                    r3 = r4
                L65:
                    if (r3 == 0) goto L30
                L67:
                    if (r2 == 0) goto L73
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r1
                    java.lang.Boolean r0 = java.lang.Boolean.TRUE
                    java.lang.String r1 = ""
                    r8.invoke(r1, r0, r0)
                    goto Lab
                L73:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r0 = r1
                    java.lang.String r8 = r8.getMessage()
                    java.lang.Boolean r1 = java.lang.Boolean.FALSE
                    java.lang.Boolean r2 = java.lang.Boolean.TRUE
                    r0.invoke(r8, r1, r2)
                    goto Lab
                L81:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r0 = r1
                    java.lang.String r8 = r8.getMessage()
                    java.lang.Boolean r1 = java.lang.Boolean.FALSE
                    java.lang.Boolean r2 = java.lang.Boolean.TRUE
                    r0.invoke(r8, r1, r2)
                    goto Lab
                L8f:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r1
                    com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard r0 = r2
                    android.content.Context r0 = com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard.access$getMContext$p(r0)
                    if (r0 != 0) goto L9f
                    java.lang.String r0 = "mContext"
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                    r0 = 0
                L9f:
                    r1 = 2131954922(0x7f130cea, float:1.9546357E38)
                    java.lang.String r0 = r0.getString(r1)
                    java.lang.Boolean r1 = java.lang.Boolean.FALSE
                    r8.invoke(r0, r1, r1)
                Lab:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$checkNavigationDisclaimerAcceptance$1.onSuccess(com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes):void");
            }
        }, NavigationConstants.MAP_NAV_DISCLAIMER);
    }

    public final void checkStoragePermission() {
        getMListener().onPermissionSuccess(PermissionType.STORAGE);
    }

    public final void d0() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (!companion.isCZDevice(context)) {
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context3 = null;
            }
            if (!companion.isCADevice(context3)) {
                Context context4 = this.q;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context4 = null;
                }
                if (!companion.isCYDevice(context4)) {
                    Context context5 = this.q;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context5 = null;
                    }
                    if (!companion.isPS1Device(context5)) {
                        Context context6 = this.q;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        } else {
                            context2 = context6;
                        }
                        if (!companion.isBESDevice(context2)) {
                            e0();
                            return;
                        }
                    }
                }
            }
        }
        C();
    }

    public final void deleteActivityDataFromWatch() {
        DeleteActivityDataRequest deleteActivityDataRequest = new DeleteActivityDataRequest();
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().getData(deleteActivityDataRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$deleteActivityDataFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ViewModelActivityDashboard.this.e, "activity data deletion failed");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ViewModelActivityDashboard.this.e, "activity data deleted");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void deleteGpsDataFromWatch() {
        DeleteGpsDataRequest deleteGpsDataRequest = new DeleteGpsDataRequest();
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().getData(deleteGpsDataRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$deleteGpsDataFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ViewModelActivityDashboard.this.e, "gps data deletion failed");
                Context context2 = ViewModelActivityDashboard.this.q;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context2 = null;
                }
                if (BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isDeleteActivityDataSupported()) {
                    ViewModelActivityDashboard.this.deleteActivityDataFromWatch();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ViewModelActivityDashboard.this.e, "gps data deleted");
                Context context2 = ViewModelActivityDashboard.this.q;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context2 = null;
                }
                if (BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isDeleteActivityDataSupported()) {
                    ViewModelActivityDashboard.this.deleteActivityDataFromWatch();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void e0() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (!BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isLiveHeartRateSupported()) {
            b0();
            return;
        }
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        if (BleApiManager.getInstance(context2).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        b0();
    }

    public final void fetchLiveDataInstance() {
        WalkRepository.Companion companion = WalkRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WalkRepository singletonHolder = companion.getInstance(context);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        this.E = singletonHolder.getDailyDataWithoutFlowValidator(calendar, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
    }

    public final ActivityGPSSample g(ArrayList<ActivityGPSSample> arrayList, long j, int i) {
        if (AppUtils.isEmpty(arrayList)) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        Iterator<ActivityGPSSample> it = arrayList.iterator();
        while (it.hasNext()) {
            ActivityGPSSample next = it.next();
            long j2 = i * 1000;
            if (next.getGpsTimeStamp() / j2 == j / j2) {
                return next;
            }
        }
        return null;
    }

    @Nullable
    public final String getAgpsFilePath() {
        return this.t;
    }

    @Nullable
    public final EntityHourlyBp getBPLastDataInstance() {
        BPRepository.Companion companion = BPRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BPRepository singletonHolder = companion.getInstance(context);
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        return singletonHolder.getLatestRecordHourly(BleApiManager.getInstance(context2).getBleApi().getMacAddress());
    }

    public final int getBatteryPercentageForMatrix$app_prodRelease(int i) {
        return (i / 10) * 10;
    }

    public final int getBatteryPercentageForVertex$app_prodRelease(int i) {
        return i;
    }

    @NotNull
    public final MutableLiveData<Boolean> getBatteryRequestFailure() {
        return this.s;
    }

    @NotNull
    public final MutableLiveData<String> getBatteryUpdate() {
        return this.r;
    }

    @Nullable
    public final LiveData<EntityDailyBp> getBpLiveDataInstance(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        BPRepository.Companion companion = BPRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BPRepository singletonHolder = companion.getInstance(context);
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        LiveData<EntityDailyBp> dailyDataWithoutFlowValidator = singletonHolder.getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
        this.J = dailyDataWithoutFlowValidator;
        return dailyDataWithoutFlowValidator;
    }

    public final void getConfigUrlsFromServer() {
        LogHelper.d(this.e, "getConfigUrlsFromServer: called");
        CoveOnboarding.getV2RemoteConfiguration("1", new CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getConfigUrlsFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SRemoteConfigResponse sRemoteConfigResponse) {
                Context context = null;
                LogHelper.d(ViewModelActivityDashboard.this.e, sRemoteConfigResponse != null ? sRemoteConfigResponse.toString() : null);
                if (sRemoteConfigResponse != null) {
                    SRemoteConfigResponse.DataBean data = sRemoteConfigResponse.getData();
                    if ((data != null ? data.getFitnessPlan() : null) != null) {
                        SRemoteConfigResponse.DataBean.FitnessPlanBean.WebViewUrlBean webViewUrl = sRemoteConfigResponse.getData().getFitnessPlan().getWebViewUrl();
                        UserDataManager.getInstance(ViewModelActivityDashboard.this.getApplication()).savePlanConfigUrlsToPref(new WebViewUrlBean(webViewUrl.getOnboarding(), webViewUrl.getBrowsePlan(), webViewUrl.getUserPlanHistory()));
                    }
                    SRemoteConfigResponse.DataBean.GuardianBean guardian = sRemoteConfigResponse.getData().getGuardian();
                    if (guardian != null) {
                        Context context2 = ViewModelActivityDashboard.this.q;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context2 = null;
                        }
                        UserDataManager.getInstance(context2).setEnableGuardianFeature(Boolean.valueOf(guardian.isEnable()));
                    }
                    if (sRemoteConfigResponse.getData().getSpo2Aiml() != null && sRemoteConfigResponse.getData().getSpo2Aiml().getRemeasure() != null) {
                        SRemoteConfigResponse.DataBean.Spo2AimlBean.RemeasureBean remeasure = sRemoteConfigResponse.getData().getSpo2Aiml().getRemeasure();
                        Context context3 = ViewModelActivityDashboard.this.q;
                        if (context3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context3 = null;
                        }
                        Spo2DataManager spo2DataManager = Spo2DataManager.getInstance(context3);
                        Integer maxRetry = remeasure.getMaxRetry();
                        Intrinsics.checkNotNullExpressionValue(maxRetry, "remeasure.maxRetry");
                        spo2DataManager.setConsecutiveLowCountLimit(maxRetry.intValue());
                        Context context4 = ViewModelActivityDashboard.this.q;
                        if (context4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context4 = null;
                        }
                        Spo2DataManager spo2DataManager2 = Spo2DataManager.getInstance(context4);
                        Integer retryTimeout = remeasure.getRetryTimeout();
                        Intrinsics.checkNotNullExpressionValue(retryTimeout, "remeasure.retryTimeout");
                        spo2DataManager2.setMinTimeOut(retryTimeout.intValue());
                        Context context5 = ViewModelActivityDashboard.this.q;
                        if (context5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context5 = null;
                        }
                        Spo2DataManager spo2DataManager3 = Spo2DataManager.getInstance(context5);
                        Integer remindIn = remeasure.getRemindIn();
                        Intrinsics.checkNotNullExpressionValue(remindIn, "remeasure.remindIn");
                        spo2DataManager3.setReminderInterval(remindIn.intValue());
                    }
                    if (sRemoteConfigResponse.getData().getApiFrequency() != null) {
                        Context context6 = ViewModelActivityDashboard.this.q;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context6 = null;
                        }
                        UserDataManager.getInstance(context6).saveIotUserHeartbeatApiFrequency(sRemoteConfigResponse.getData().getApiFrequency().getPostIotUserHeartbeat());
                    }
                    sRemoteConfigResponse.getData().getNearbyTracking();
                    if (!AppUtils.isEmpty(sRemoteConfigResponse.getData().getOpenWeatherMap().getApiKey())) {
                        String key = sRemoteConfigResponse.getData().getOpenWeatherMap().getApiKey();
                        WeatherApiCallsManager.Companion companion = WeatherApiCallsManager.Companion;
                        Context context7 = ViewModelActivityDashboard.this.q;
                        if (context7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context7 = null;
                        }
                        if (!companion.getInstance(context7).isInitialized()) {
                            Context context8 = ViewModelActivityDashboard.this.q;
                            if (context8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                context8 = null;
                            }
                            Intrinsics.checkNotNullExpressionValue(key, "key");
                            companion.getInstance(context8).initWeatherApiClient(key, WeatherAPIType.OPEN_WEATHER_MAP);
                        }
                    }
                    if (sRemoteConfigResponse.getData().getStress() != null) {
                        SRemoteConfigResponse.DataBean.Stress stress = sRemoteConfigResponse.getData().getStress();
                        StressConfiguration stressConfiguration = new StressConfiguration();
                        StressConfiguration.Stress stress2 = new StressConfiguration.Stress();
                        if (stress.getAlert() != null) {
                            StressConfiguration.Stress.Alert alert = new StressConfiguration.Stress.Alert();
                            alert.setThreshold(stress.getAlert().getThreshold());
                            alert.setMaxAllowed(stress.getAlert().getMaxAllowed());
                            stress2.setAlert(alert);
                        }
                        stress2.setBaselineTime(stress.getBaselineTime());
                        stress2.setReadinessTime(stress.getReadinessTime());
                        stressConfiguration.setStress(stress2);
                        Context context9 = ViewModelActivityDashboard.this.q;
                        if (context9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context9 = null;
                        }
                        SessionManager.getInstance(context9).saveAutoStressConfig(stressConfiguration);
                    }
                    if (sRemoteConfigResponse.getData().getRespiratoryRate() != null) {
                        SRemoteConfigResponse.RespiratoryRateBean respiratoryRate = sRemoteConfigResponse.getData().getRespiratoryRate();
                        RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfiguration = new RespiratoryRateRemoteConfiguration();
                        respiratoryRateRemoteConfiguration.setStartTime(respiratoryRate.getStartTime());
                        respiratoryRateRemoteConfiguration.setEndTime(respiratoryRate.getEndTime());
                        respiratoryRateRemoteConfiguration.setInterval(respiratoryRate.getInterval());
                        respiratoryRateRemoteConfiguration.setDuration(respiratoryRate.getDuration());
                        respiratoryRateRemoteConfiguration.setComputation(new RespiratoryRateRemoteConfiguration.Computation(new RespiratoryRateRemoteConfiguration()));
                        if (respiratoryRate.getComputation() != null) {
                            respiratoryRate.getComputation().getConfidenceLevelThreshold();
                            respiratoryRateRemoteConfiguration.getComputation().setConfidenceLevelThreshold(respiratoryRate.getComputation().getConfidenceLevelThreshold());
                        }
                        Context context10 = ViewModelActivityDashboard.this.q;
                        if (context10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        } else {
                            context = context10;
                        }
                        SessionManager.getInstance(context).saveRespiratoryRateRemoteConfig(respiratoryRateRemoteConfiguration);
                    }
                }
            }
        });
    }

    @Nullable
    public final ContractUpdateHrBpHealthTextListener getContractUpdateHrBpHealthTextListener() {
        return this.m;
    }

    public final void getCurrentStepsFromWatch(@NotNull final DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(dataResultListener, "dataResultListener");
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getDeviceType() != DeviceType.v2) {
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context3 = null;
            }
            BleApiManager bleApiManager = BleApiManager.getInstance(context3);
            if ((bleApiManager != null ? bleApiManager.getDeviceType() : null) != DeviceType.v7) {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                Context context4 = this.q;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context4 = null;
                }
                if (!companion.isCZDevice(context4)) {
                    Context context5 = this.q;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context5 = null;
                    }
                    if (!companion.isCADevice(context5)) {
                        Context context6 = this.q;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context6 = null;
                        }
                        if (!companion.isCYDevice(context6)) {
                            Context context7 = this.q;
                            if (context7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                context7 = null;
                            }
                            if (!companion.isPS1Device(context7)) {
                                Context context8 = this.q;
                                if (context8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                    context8 = null;
                                }
                                if (!companion.isBESDevice(context8)) {
                                    Context context9 = this.q;
                                    if (context9 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                        context9 = null;
                                    }
                                    if (BleApiManager.getInstance(context9).getDeviceType() != DeviceType.smartT) {
                                        TodaysStepsDataRequest build = new TodaysStepsDataRequest.Builder().build();
                                        Intrinsics.checkNotNullExpressionValue(build, "Builder().build()");
                                        dataResultListener.onDataResponse(new BleBaseResponse(build));
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        BleBaseRequest build2 = new TodaysStepsDataRequest.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder().build()");
        Context context10 = this.q;
        if (context10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context10 = null;
        }
        if (BleApiManager.getInstance(context10).getBleApi().getDeviceSupportedFeatures().isFitnessValueCommandSupported()) {
            build2 = new TodaysFitnessDataRequest.Builder().build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder().build()");
        }
        Context context11 = this.q;
        if (context11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context11;
        }
        BleApiManager.getInstance(context2).getBleApi().getData(build2, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getCurrentStepsFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DataResultListener.this.onDataError(error);
            }

            /* JADX WARN: Code restructure failed: missing block: B:77:0x018a, code lost:
                if (r7.isCADevice(r11) != false) goto L85;
             */
            /* JADX WARN: Removed duplicated region for block: B:89:0x01c5  */
            /* JADX WARN: Removed duplicated region for block: B:92:0x01d9  */
            /* JADX WARN: Removed duplicated region for block: B:93:0x01dd  */
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onDataResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.response.BleBaseResponse r13) {
                /*
                    Method dump skipped, instructions count: 499
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getCurrentStepsFromWatch$1.onDataResponse(com.coveiot.android.bleabstract.response.BleBaseResponse):void");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @Nullable
    public final LiveData<EntityDailyBp> getDailyBPLiveData() {
        return this.J;
    }

    @Nullable
    public final LiveData<EntityDailyHeartRateData> getDailyHRLiveData() {
        return this.K;
    }

    @Nullable
    public final LiveData<List<SleepDataModelForLastNight>> getDailySleepLiveData() {
        return this.d;
    }

    @Nullable
    public final LiveData<DailyWalkData> getDailyStepsLivedata() {
        return this.E;
    }

    public final int getDataSyncFailureCount() {
        return this.w;
    }

    @NotNull
    public final String getDeviceName(@NotNull String deviceType) {
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (Intrinsics.areEqual(deviceType, context.getResources().getString(R.string.smaf2_device))) {
            return DeviceConstants.Companion.getMERCURY();
        }
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        if (Intrinsics.areEqual(deviceType, context3.getResources().getString(R.string.moyangy20_device))) {
            return DeviceConstants.Companion.getVERTEX();
        }
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        if (Intrinsics.areEqual(deviceType, context4.getResources().getString(R.string.cove_cz1))) {
            return DeviceConstants.Companion.getWAVEPRO();
        }
        Context context5 = this.q;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context5 = null;
        }
        if (Intrinsics.areEqual(deviceType, context5.getResources().getString(R.string.moyangygpf5_device))) {
            return DeviceConstants.Companion.getWAVEFIT();
        }
        Context context6 = this.q;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context6 = null;
        }
        if (Intrinsics.areEqual(deviceType, context6.getResources().getString(R.string.j1860_device))) {
            return DeviceConstants.Companion.getWANDERER();
        }
        Context context7 = this.q;
        if (context7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context7 = null;
        }
        if (Intrinsics.areEqual(deviceType, context7.getResources().getString(R.string.smas12_device))) {
            return DeviceConstants.Companion.getPRIMIA();
        }
        Context context8 = this.q;
        if (context8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context8 = null;
        }
        if (Intrinsics.areEqual(deviceType, context8.getResources().getString(R.string.matrix_device))) {
            return DeviceConstants.Companion.getMATRIX();
        }
        Context context9 = this.q;
        if (context9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context9 = null;
        }
        if (Intrinsics.areEqual(deviceType, context9.getResources().getString(R.string.cove_cz3))) {
            return DeviceConstants.Companion.getXTEND_SPORT();
        }
        Context context10 = this.q;
        if (context10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context10 = null;
        }
        if (Intrinsics.areEqual(deviceType, context10.getResources().getString(R.string.cove_wave_prime))) {
            return DeviceConstants.Companion.getWAVEPRIME();
        }
        Context context11 = this.q;
        if (context11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context11 = null;
        }
        if (Intrinsics.areEqual(deviceType, context11.getResources().getString(R.string.cove_ca0))) {
            return DeviceConstants.Companion.getCA_0();
        }
        Context context12 = this.q;
        if (context12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context12 = null;
        }
        if (Intrinsics.areEqual(deviceType, context12.getResources().getString(R.string.cove_ca3))) {
            return DeviceConstants.Companion.getSTORMPRO();
        }
        Context context13 = this.q;
        if (context13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context13 = null;
        }
        if (Intrinsics.areEqual(deviceType, context13.getResources().getString(R.string.ido_select))) {
            return DeviceConstants.Companion.getIDO_SELECT();
        }
        Context context14 = this.q;
        if (context14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context14 = null;
        }
        if (Intrinsics.areEqual(deviceType, context14.getResources().getString(R.string.cove_ca3_bt))) {
            return DeviceConstants.Companion.getXTENDPROBT3();
        }
        Context context15 = this.q;
        if (context15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context15 = null;
        }
        if (Intrinsics.areEqual(deviceType, context15.getResources().getString(R.string.cy1_primia_voice))) {
            return DeviceConstants.Companion.getPRIMIA_VOICE();
        }
        Context context16 = this.q;
        if (context16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context16 = null;
        }
        if (Intrinsics.areEqual(deviceType, context16.getResources().getString(R.string.cy1_loop_call_pro))) {
            return DeviceConstants.Companion.getLOOP_CALL_PRO();
        }
        Context context17 = this.q;
        if (context17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context17 = null;
        }
        if (Intrinsics.areEqual(deviceType, context17.getResources().getString(R.string.cy1_loop_connect_pro))) {
            return DeviceConstants.Companion.getLOOP_CONNECT_PRO();
        }
        Context context18 = this.q;
        if (context18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context18 = null;
        }
        if (Intrinsics.areEqual(deviceType, context18.getResources().getString(R.string.cove_ca2))) {
            return DeviceConstants.Companion.getWAVEACTIVE();
        }
        Context context19 = this.q;
        if (context19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context19 = null;
        }
        if (Intrinsics.areEqual(deviceType, context19.getResources().getString(R.string.cove_ca5_wave_style))) {
            return DeviceConstants.Companion.getWAVESTYLE();
        }
        Context context20 = this.q;
        if (context20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context20 = null;
        }
        if (Intrinsics.areEqual(deviceType, context20.getResources().getString(R.string.cove_ca5_wave_play))) {
            return DeviceConstants.Companion.getWAVEPLAY();
        }
        Context context21 = this.q;
        if (context21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context21 = null;
        }
        if (Intrinsics.areEqual(deviceType, context21.getResources().getString(R.string.cove_ca5_wave_beat))) {
            return DeviceConstants.Companion.getWAVEBEAT();
        }
        Context context22 = this.q;
        if (context22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context22 = null;
        }
        if (Intrinsics.areEqual(deviceType, context22.getResources().getString(R.string.cove_ulc3_wave_smart))) {
            return DeviceConstants.Companion.getWAVESMART();
        }
        Context context23 = this.q;
        if (context23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context23 = null;
        }
        if (Intrinsics.areEqual(deviceType, context23.getResources().getString(R.string.cove_ulc2_wave_beat_plus))) {
            return DeviceConstants.Companion.getBEATPLUS();
        }
        Context context24 = this.q;
        if (context24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context24 = null;
        }
        if (Intrinsics.areEqual(deviceType, context24.getResources().getString(R.string.cove_ulc2_wave_style_plus))) {
            return DeviceConstants.Companion.getSTYLEPLUS();
        }
        Context context25 = this.q;
        if (context25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context25 = null;
        }
        if (Intrinsics.areEqual(deviceType, context25.getResources().getString(R.string.cove_ulc2_wave_smart_plus))) {
            return DeviceConstants.Companion.getSMARTPLUS();
        }
        Context context26 = this.q;
        if (context26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context26 = null;
        }
        if (Intrinsics.areEqual(deviceType, context26.getResources().getString(R.string.cove_ulc2_wave_lync))) {
            return DeviceConstants.Companion.getLYNC();
        }
        Context context27 = this.q;
        if (context27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context27 = null;
        }
        if (Intrinsics.areEqual(deviceType, context27.getResources().getString(R.string.cove_ca3_wave_cosmos))) {
            return DeviceConstants.Companion.getWAVECOSMOS();
        }
        Context context28 = this.q;
        if (context28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context28 = null;
        }
        if (Intrinsics.areEqual(deviceType, context28.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro))) {
            return DeviceConstants.Companion.getWAVECOSMOSPRO();
        }
        Context context29 = this.q;
        if (context29 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context29 = null;
        }
        if (Intrinsics.areEqual(deviceType, context29.getResources().getString(R.string.cove_ca3_bt_stormpro_call))) {
            return DeviceConstants.Companion.getSTORMPROCALL();
        }
        Context context30 = this.q;
        if (context30 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context30 = null;
        }
        if (Intrinsics.areEqual(deviceType, context30.getResources().getString(R.string.cove_wave_elite))) {
            return DeviceConstants.Companion.getWAVEELITE();
        }
        Context context31 = this.q;
        if (context31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context31 = null;
        }
        if (Intrinsics.areEqual(deviceType, context31.getResources().getString(R.string.ido_connect))) {
            return DeviceConstants.Companion.getIDO_CONNECT();
        }
        Context context32 = this.q;
        if (context32 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context32 = null;
        }
        if (Intrinsics.areEqual(deviceType, context32.getResources().getString(R.string.sma_wave_genesis_pro))) {
            return DeviceConstants.Companion.getWAVEGENESISPRO();
        }
        Context context33 = this.q;
        if (context33 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context33 = null;
        }
        if (Intrinsics.areEqual(deviceType, context33.getResources().getString(R.string.sma_wave_elevate_pro))) {
            return DeviceConstants.Companion.getWAVEELEVATEPRO();
        }
        Context context34 = this.q;
        if (context34 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context34 = null;
        }
        if (Intrinsics.areEqual(deviceType, context34.getResources().getString(R.string.sma_wave_glory_pro))) {
            return DeviceConstants.Companion.getWAVEGLORYPRO();
        }
        Context context35 = this.q;
        if (context35 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context35 = null;
        }
        if (Intrinsics.areEqual(deviceType, context35.getResources().getString(R.string.sma_ultima_vogue))) {
            return DeviceConstants.Companion.getULTIMAVOGUE();
        }
        Context context36 = this.q;
        if (context36 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context36 = null;
        }
        if (Intrinsics.areEqual(deviceType, context36.getResources().getString(R.string.sma_lunar_seek))) {
            return DeviceConstants.Companion.getLUNARSEEK();
        }
        Context context37 = this.q;
        if (context37 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context37 = null;
        }
        if (Intrinsics.areEqual(deviceType, context37.getResources().getString(R.string.sma_lunar_comet))) {
            return DeviceConstants.Companion.getLUNARCOMET();
        }
        Context context38 = this.q;
        if (context38 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context38;
        }
        return Intrinsics.areEqual(deviceType, context2.getResources().getString(R.string.sma_lunar_velocity)) ? DeviceConstants.Companion.getLUNARVELOCITY() : deviceType;
    }

    @NotNull
    public final EntityManualData getEntityManualData(@NotNull Spo2DeviceType spo2DeviceType, double d2, long j, boolean z) {
        Intrinsics.checkNotNullParameter(spo2DeviceType, "spo2DeviceType");
        String userDeviceID = com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserDeviceID();
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        String macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
        EntityManualData entityManualData = new EntityManualData(j, J(spo2DeviceType));
        entityManualData.setSerialNo(macAddress);
        entityManualData.setLevelInterpretation(z);
        entityManualData.setUserDeviceId(userDeviceID);
        entityManualData.setSpo2(d2);
        entityManualData.setSyncedWithServer(false);
        return entityManualData;
    }

    @Nullable
    public final LiveData<EntityHourlyHeartRateData> getHRLastDataInstance(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        HeartRateRepository.Companion companion = HeartRateRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        HeartRateRepository singletonHolder = companion.getInstance(context);
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        LiveData<EntityHourlyHeartRateData> lastReadHeartRate = singletonHolder.getLastReadHeartRate(BleApiManager.getInstance(context2).getBleApi().getMacAddress());
        this.L = lastReadHeartRate;
        return lastReadHeartRate;
    }

    @Nullable
    public final LiveData<EntityDailyHeartRateData> getHRLiveDataInstance(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        HeartRateRepository.Companion companion = HeartRateRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        HeartRateRepository singletonHolder = companion.getInstance(context);
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        LiveData<EntityDailyHeartRateData> dailyDataWithoutFlowValidator = singletonHolder.getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
        this.K = dailyDataWithoutFlowValidator;
        return dailyDataWithoutFlowValidator;
    }

    @Nullable
    public final LiveData<EntityHourlyHeartRateData> getHourHRLiveData() {
        return this.L;
    }

    @Nullable
    public final LiveData<List<HourlyTemperature>> getHourTemperatureLiveData() {
        return this.M;
    }

    public final long getLastCommanmdSentTime() {
        return this.v;
    }

    @Nullable
    public final LiveData<DailyRespiratoryRateEntity> getLatestRespiratoryRateLiveData() {
        return this.G;
    }

    @Nullable
    public final LifecycleOwner getMLifecycleOwner() {
        return this.n;
    }

    @NotNull
    public final PermissionListener getMListener() {
        PermissionListener permissionListener = this.mListener;
        if (permissionListener != null) {
            return permissionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    public final int getMatchStateIndex() {
        return this.u;
    }

    @NotNull
    public final List<MatchState> getMatchStesList() {
        return this.z;
    }

    @NotNull
    public final ProgressUpdateData getProgressUpdateData() {
        return this.B;
    }

    @NotNull
    public final MutableLiveData<ProgressUpdateData> getProgressValueSync() {
        return this.A;
    }

    @Nullable
    public final SleepScoreData getSleepScoreCurrentDate() {
        SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        SleepScoreRepository singletonHolder = companion.getInstance(context);
        String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …-MM-dd\"\n                )");
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        return singletonHolder.getSleepScoreData(formatDate, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
    }

    @Nullable
    public final LiveData<EntityManualData> getSpO2RecordLivedata() {
        return this.F;
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
            return kotlin.text.m.replace$default(SPO2Level.VERY_LOW.name(), "_", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null);
        }
        return String.valueOf(f);
    }

    public final int getStageValue() {
        return this.C;
    }

    @Nullable
    public final SyncManager getSyncManager() {
        return this.o;
    }

    @NotNull
    public final MutableLiveData<SyncState> getSyncStateLiveData() {
        return this.l;
    }

    @NotNull
    public final SyncTroubleShoutListener getSyncTroubleShoutListener() {
        SyncTroubleShoutListener syncTroubleShoutListener = this.syncTroubleShoutListener;
        if (syncTroubleShoutListener != null) {
            return syncTroubleShoutListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("syncTroubleShoutListener");
        return null;
    }

    @Nullable
    public final LiveData<List<HourlyTemperature>> getTemperatureHourlyDataInstance(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        TemperatureRepository.Companion companion = TemperatureRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        TemperatureRepository singletonHolder = companion.getInstance(context);
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        LiveData<List<HourlyTemperature>> hourlyData = singletonHolder.getHourlyData(date, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
        this.M = hourlyData;
        return hourlyData;
    }

    public final long getTimeStampSyncTigger() {
        return this.k;
    }

    @NotNull
    public final ViewModelListener getViewModelListener() {
        ViewModelListener viewModelListener = this.viewModelListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelListener");
        return null;
    }

    @NotNull
    public final Observer<LiveHealthData> getVirtualRunHeartRateLiveDataObserver() {
        return this.I;
    }

    @NotNull
    public final Observer<LiveStepsData> getVirtualRunStepsLiveDataObserve() {
        return this.H;
    }

    @Nullable
    public final DailyWalkData getWalkDataCurrentDate() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WalkDBRead walkDBRead = WalkDBRead.getInstance(context);
        String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        return walkDBRead.getDailyWalkDataWithDate(formatDate, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
    }

    public final ActivityHeartRateSample h(ArrayList<ActivityHeartRateSample> arrayList, long j, int i) {
        Iterator<ActivityHeartRateSample> it = arrayList.iterator();
        while (it.hasNext()) {
            ActivityHeartRateSample next = it.next();
            long j2 = i * 1000;
            if (next.getHrTimeStamp() / j2 == j / j2) {
                return next;
            }
        }
        return null;
    }

    public final ActivityStepsSample i(ArrayList<ActivityStepsSample> arrayList, long j, int i) {
        Iterator<ActivityStepsSample> it = arrayList.iterator();
        while (it.hasNext()) {
            ActivityStepsSample next = it.next();
            long j2 = i * 1000;
            if (next.getStepsTimeStamp() / j2 == j / j2) {
                return next;
            }
        }
        return null;
    }

    public final boolean isManualSyncAttept() {
        return this.j;
    }

    public final void isShowSensAI(@NotNull final Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        if (BleApiManager.getInstance(mContext).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED && BleApiManager.getInstance(mContext).getBleApi().getDeviceSupportedFeatures().isSensAISupported()) {
            BleApiManager.getInstance(mContext).getBleApi().setUserSettings(new SetSensAIActivityConfigRequest.Builder().setSensAiActivityConfigRequest(true).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$isShowSensAI$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Log.d(this.e, error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    com.coveiot.android.leonardo.utils.PreferenceManager.saveSensAIEnabled(mContext, true);
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0078, code lost:
        if (r3 >= 1) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00c3, code lost:
        if (com.coveiot.utils.utility.AppUtils.findDateDifference(r3, com.coveiot.utils.utility.AppUtils.parseDate(r4, "yyyy-MM-dd")) >= java.lang.Integer.parseInt(com.coveiot.android.sleepenergyscore.utils.Constants.SUBSCRIPTION_INTERVAL.getValue())) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r0.isSubscribed() != false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isShowSubscribtionDialog(@org.jetbrains.annotations.NotNull android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r0 = "mContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.coveiot.covepreferences.UserDataManager r0 = com.coveiot.covepreferences.UserDataManager.getInstance(r9)
            com.coveiot.covepreferences.data.WeeklyReportPrefData r0 = r0.getWeeklyReportData()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L1c
            r0.isSubscribed()
            boolean r3 = r0.isSubscribed()
            if (r3 == 0) goto L1c
            goto Lcb
        L1c:
            java.lang.String r3 = "prod"
            java.lang.String r4 = "qa"
            boolean r3 = kotlin.text.m.equals(r3, r4, r2)
            r4 = 0
            if (r3 == 0) goto L80
            if (r0 == 0) goto L7b
            r0.getLastShownTimestamp()
            long r6 = r0.getLastShownTimestamp()
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 <= 0) goto L7b
            long r3 = r0.getLastShownTimestamp()
            java.util.Date r5 = new java.util.Date
            r5.<init>(r3)
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat
            java.util.Locale r4 = java.util.Locale.ENGLISH
            java.lang.String r6 = "yyyy-MM-dd hh:mm a"
            r3.<init>(r6, r4)
            java.lang.String r4 = r3.format(r5)
            java.util.Date r5 = new java.util.Date
            r5.<init>()
            java.lang.String r3 = r3.format(r5)
            java.lang.String r5 = "tempDt"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            java.lang.String r5 = "currentDt"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            int r3 = com.coveiot.android.sleepenergyscore.utils.Utils.getTimeDifferenceInHours(r4, r3, r6)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "hoursDiff: "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            java.io.PrintStream r5 = java.lang.System.out
            r5.println(r4)
            if (r3 < r2) goto Lcb
            goto Lc5
        L7b:
            com.coveiot.covepreferences.data.WeeklyReportPrefData r0 = com.coveiot.covepreferences.data.WeeklyReportPrefData.getInstance()
            goto Lca
        L80:
            if (r0 == 0) goto Lc6
            r0.getLastShownTimestamp()
            long r6 = r0.getLastShownTimestamp()
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 <= 0) goto Lc6
            long r3 = r0.getLastShownTimestamp()
            java.util.Date r5 = new java.util.Date
            r5.<init>(r3)
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat
            java.util.Locale r4 = java.util.Locale.ENGLISH
            java.lang.String r6 = "yyyy-MM-dd"
            r3.<init>(r6, r4)
            java.lang.String r3 = r3.format(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.util.Date r3 = com.coveiot.utils.utility.AppUtils.parseDate(r3, r6)
            java.lang.String r4 = com.coveiot.android.sleepenergyscore.utils.Utils.getCurrentDate()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            java.util.Date r4 = com.coveiot.utils.utility.AppUtils.parseDate(r4, r6)
            int r3 = com.coveiot.utils.utility.AppUtils.findDateDifference(r3, r4)
            com.coveiot.android.sleepenergyscore.utils.Constants r4 = com.coveiot.android.sleepenergyscore.utils.Constants.SUBSCRIPTION_INTERVAL
            java.lang.String r4 = r4.getValue()
            int r4 = java.lang.Integer.parseInt(r4)
            if (r3 < r4) goto Lcb
        Lc5:
            goto Lca
        Lc6:
            com.coveiot.covepreferences.data.WeeklyReportPrefData r0 = com.coveiot.covepreferences.data.WeeklyReportPrefData.getInstance()
        Lca:
            r1 = r2
        Lcb:
            if (r1 == 0) goto Ldb
            long r2 = java.lang.System.currentTimeMillis()
            r0.setLastShownTimestamp(r2)
            com.coveiot.covepreferences.UserDataManager r9 = com.coveiot.covepreferences.UserDataManager.getInstance(r9)
            r9.saveWeeklyReport(r0)
        Ldb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard.isShowSubscribtionDialog(android.content.Context):boolean");
    }

    public final boolean isSyncing() {
        return this.i;
    }

    public final void j() {
        this.h.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.f1
            @Override // java.lang.Runnable
            public final void run() {
                ViewModelActivityDashboard.k(ViewModelActivityDashboard.this);
            }
        }, this.f * 1000);
    }

    public final void l() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (!companion.isTouchELXDevice(context)) {
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context3 = null;
            }
            if (!companion.isMatrixDevice(context3)) {
                Context context4 = this.q;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context4 = null;
                }
                if (!companion.isEastApexDevice(context4)) {
                    if (this.v == 0) {
                        this.v = System.currentTimeMillis();
                    } else if (System.currentTimeMillis() - this.v < 15000) {
                        return;
                    }
                }
            }
        }
        ActivityModeWithSamplesRequest activityModeWithSamplesRequest = new ActivityModeWithSamplesRequest();
        Calendar.getInstance().add(6, -4);
        Context context5 = this.q;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context5 = null;
        }
        activityModeWithSamplesRequest.setStartDate(UserDataManager.getInstance(context5).getLastActivitySyncDate());
        activityModeWithSamplesRequest.setEndDate(new Date());
        Context context6 = this.q;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context6 = null;
        }
        if (BleApiManager.getInstance(context6).getBleApi().getDeviceSupportedFeatures().isSportsModeHistorySupported()) {
            Context context7 = this.q;
            if (context7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context7 = null;
            }
            ActivityModeSummaryRequest sportsDataRequest = activityModeWithSamplesRequest;
            if (!BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isSampleDataSupportedInSportMode()) {
                sportsDataRequest = new ActivityModeSummaryRequest.Builder().build();
            }
            ProgressUpdateData progressUpdateData = this.B;
            Context context8 = this.q;
            if (context8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context8 = null;
            }
            progressUpdateData.setTextProgress(context8.getResources().getString(R.string.syncing_activity));
            this.A.postValue(this.B);
            this.v = System.currentTimeMillis();
            Context context9 = this.q;
            if (context9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context9;
            }
            BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
            Intrinsics.checkNotNullExpressionValue(sportsDataRequest, "sportsDataRequest");
            bleApi.getData(sportsDataRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getActivityModeDataFromDevice$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ViewModelActivityDashboard.this.W(error.getErrorMsg(), new Error(error.getErrorMsg(), error.getErrorCode(), "activity"));
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    ViewModelActivityDashboard.this.getProgressUpdateData().setProgressPercent(ViewModelActivityDashboard.this.getProgressUpdateData().getProgressPercent() + ViewModelActivityDashboard.this.getProgressUpdateData().getPercentBySatge());
                    ViewModelActivityDashboard.this.getProgressValueSync().postValue(ViewModelActivityDashboard.this.getProgressUpdateData());
                    ViewModelActivityDashboard.this.X(response);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        checkForAgpsFileUpdate();
    }

    public final void loadBpData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        if (this.n != null) {
            BPRepository.Companion companion = BPRepository.Companion;
            Context context = this.q;
            Context context2 = null;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            BPRepository singletonHolder = companion.getInstance(context);
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            LiveData<EntityDailyBp> dailyDataWithoutFlowValidator = singletonHolder.getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
            LifecycleOwner lifecycleOwner = this.n;
            Intrinsics.checkNotNull(lifecycleOwner);
            dailyDataWithoutFlowValidator.observe(lifecycleOwner, new Observer<EntityDailyBp>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$loadBpData$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@androidx.annotation.Nullable @Nullable EntityDailyBp entityDailyBp) {
                    if (ViewModelActivityDashboard.this.getContractUpdateHrBpHealthTextListener() != null) {
                        ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener = ViewModelActivityDashboard.this.getContractUpdateHrBpHealthTextListener();
                        Intrinsics.checkNotNull(contractUpdateHrBpHealthTextListener);
                        contractUpdateHrBpHealthTextListener.updateDailyLevelBpData(entityDailyBp);
                    }
                }
            });
        }
    }

    public final void loadHrData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        if (this.n != null) {
            HeartRateRepository.Companion companion = HeartRateRepository.Companion;
            Context context = this.q;
            Context context2 = null;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            HeartRateRepository singletonHolder = companion.getInstance(context);
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            LiveData<EntityDailyHeartRateData> dailyDataWithoutFlowValidator = singletonHolder.getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
            LifecycleOwner lifecycleOwner = this.n;
            Intrinsics.checkNotNull(lifecycleOwner);
            dailyDataWithoutFlowValidator.observe(lifecycleOwner, new Observer<EntityDailyHeartRateData>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$loadHrData$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@androidx.annotation.Nullable @Nullable EntityDailyHeartRateData entityDailyHeartRateData) {
                    if (ViewModelActivityDashboard.this.getContractUpdateHrBpHealthTextListener() != null) {
                        ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener = ViewModelActivityDashboard.this.getContractUpdateHrBpHealthTextListener();
                        Intrinsics.checkNotNull(contractUpdateHrBpHealthTextListener);
                        contractUpdateHrBpHealthTextListener.updateDailyLevelHrData(entityDailyHeartRateData);
                    }
                }
            });
        }
    }

    public final String m(ActivityModeSummaryResponse activityModeSummaryResponse, boolean z) {
        if (z) {
            if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "SPINNING")) {
                String lowerCase = CoveApiConstants.CYCLE.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                return lowerCase;
            } else if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "FREE_EXERCISE")) {
                String lowerCase2 = "free_exercise".toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                return lowerCase2;
            } else if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "ROWING_MACHINE")) {
                String lowerCase3 = "ROWING".toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                return lowerCase3;
            } else {
                String activityMode = activityModeSummaryResponse.getActivityMode();
                Intrinsics.checkNotNull(activityMode);
                String lowerCase4 = activityMode.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                return lowerCase4;
            }
        } else if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "SPINNING")) {
            return CoveApiConstants.CYCLE;
        } else {
            if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "ROWING_MACHINE")) {
                return "ROWING";
            }
            String activityMode2 = activityModeSummaryResponse.getActivityMode();
            Intrinsics.checkNotNull(activityMode2);
            return activityMode2;
        }
    }

    public final ArrayList<BadmintonSample> o(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<BadmintonSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    BadmintonSample badmintonSample = new BadmintonSample();
                    badmintonSample.setSess_id(str);
                    badmintonSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        badmintonSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    badmintonSample.setSampleData(sampleData);
                    arrayList3.add(badmintonSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void onResume() {
        LogHelper.d(this.e, "onResume");
        WalkRepository.Companion companion = WalkRepository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WalkRepository singletonHolder = companion.getInstance(context);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        this.E = singletonHolder.getDailyDataWithoutFlowValidator(calendar, BleApiManager.getInstance(context3).getBleApi().getMacAddress());
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        if (BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isSleepSupported()) {
            SleepRepository.Companion companion2 = SleepRepository.Companion;
            Context context5 = this.q;
            if (context5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context5 = null;
            }
            SleepRepository singletonHolder2 = companion2.getInstance(context5);
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
            Context context6 = this.q;
            if (context6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context6 = null;
            }
            this.d = singletonHolder2.getHourlyDataWithoutFlowValidator(calendar2, BleApiManager.getInstance(context6).getBleApi().getMacAddress());
        }
        Context context7 = this.q;
        if (context7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context7 = null;
        }
        if (BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
            ManualDataRepository.Companion companion3 = ManualDataRepository.Companion;
            Context context8 = this.q;
            if (context8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context8 = null;
            }
            ManualDataRepository singletonHolder3 = companion3.getInstance(context8);
            Context context9 = this.q;
            if (context9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context9 = null;
            }
            this.F = singletonHolder3.getLastMeasuredSpo2(BleApiManager.getInstance(context9).getBleApi().getMacAddress());
        }
        Context context10 = this.q;
        if (context10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context10 = null;
        }
        if (BleApiManager.getInstance(context10).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
            RespiratoryRateRepository.Companion companion4 = RespiratoryRateRepository.Companion;
            Context context11 = this.q;
            if (context11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context11 = null;
            }
            RespiratoryRateRepository singletonHolder4 = companion4.getInstance(context11);
            Context context12 = this.q;
            if (context12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context12;
            }
            this.G = singletonHolder4.getLatestRespiratoryRateData(BleApiManager.getInstance(context2).getBleApi().getMacAddress());
        }
    }

    public final void onStart() {
        LogHelper.d(this.e, "onStart");
    }

    public final ArrayList<BasketBallSample> p(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<BasketBallSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    BasketBallSample basketBallSample = new BasketBallSample();
                    basketBallSample.setSess_id(str);
                    basketBallSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        basketBallSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    basketBallSample.setSampleData(sampleData);
                    arrayList3.add(basketBallSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void pauseLiveStepsGpsRunWithActivityModeCommand$app_prodRelease() {
        ActivityPauseResumeRequest activityPauseResumeRequest = new ActivityPauseResumeRequest.Builder().pauseSession(true).build();
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Intrinsics.checkNotNullExpressionValue(activityPauseResumeRequest, "activityPauseResumeRequest");
        bleApi.setUserSettings(activityPauseResumeRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$pauseLiveStepsGpsRunWithActivityModeCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void populateDeviceCapabilityConfig(@NotNull DeviceRemoteConfig deviceRemoteConfig) {
        Intrinsics.checkNotNullParameter(deviceRemoteConfig, "deviceRemoteConfig");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new b(deviceRemoteConfig, this, null), 2, null);
    }

    public final void postDataSyncComplete() {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_DATA_SYNC.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
        String value = FirebaseEventParams.MetaData.CV_BT_STATE.getValue();
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        hashMap.put(value, AppUtils.isBluetoothEnabled(context) ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        hashMap.put(FirebaseEventParams.MetaData.CV_TIME_SPENT_MILLS.getValue(), String.valueOf(System.currentTimeMillis() - this.k));
        String value2 = FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue();
        Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        hashMap.put(value2, companion.getPhoneBatteryLevel(context3));
        String value3 = FirebaseEventParams.MetaData.CV_DVC_BATTERY_LEVEL.getValue();
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        hashMap.put(value3, String.valueOf(UserDataManager.getInstance(context4).getBatteryLevelData().batteryLevel));
        hashMap.put(FirebaseEventParams.MetaData.CV_EVT_TRIGGER_TYPE.getValue(), this.j ? "manual" : "auto");
        hashMap.put(FirebaseEventParams.MetaData.CV_APP_PROCESS_STATUS.getValue(), "foreground");
        String value4 = FirebaseEventParams.MetaData.CV_DVC_CONN_STATUS.getValue();
        Context context5 = this.q;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context5;
        }
        hashMap.put(value4, BleApiManager.getInstance(context2).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED ? "connected" : "disconnected");
        hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "ok");
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        getViewModelListener().onSuccess();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.g1
            @Override // java.lang.Runnable
            public final void run() {
                ViewModelActivityDashboard.V(ViewModelActivityDashboard.this);
            }
        });
    }

    public final String q(Integer num, Integer num2, String str, long j, long j2, int i) {
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        String userDeviceID = com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserDeviceID();
        if (kotlin.text.m.equals(str, "PHYSICAL_ACTIVITY", true)) {
            PhysicalActivityRepository.Companion companion = PhysicalActivityRepository.Companion;
            Context context2 = this.q;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context2 = null;
            }
            EntityPhysicalActivities physicalActivityByFwActivityId = companion.getInstance(context2).getPhysicalActivityByFwActivityId(num, num2);
            String activityCode = physicalActivityByFwActivityId != null ? physicalActivityByFwActivityId.getActivityCode() : null;
            if (activityCode != null) {
                str = activityCode;
            }
        }
        String str2 = userDetails.getUserId() + ';' + userDeviceID + ';' + str + ';' + AppUtils.formatDateUTC(new Date(j), UtilConstants.SERVER_TIME_FORMAT2) + ';' + AppUtils.formatDateUTC(new Date(j2), UtilConstants.SERVER_TIME_FORMAT2) + ';' + i;
        LogHelper.d(this.e, "clientRefString: " + str2);
        String convertStringToMD5 = AppUtils.convertStringToMD5(str2);
        Intrinsics.checkNotNullExpressionValue(convertStringToMD5, "convertStringToMD5(clientRefString)");
        return convertStringToMD5;
    }

    public final ArrayList<ClimbingSample> r(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<ClimbingSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    ClimbingSample climbingSample = new ClimbingSample();
                    climbingSample.setSess_id(str);
                    climbingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        climbingSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    climbingSample.setSampleData(sampleData);
                    arrayList4.add(climbingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void registerConnectionStatus(@NotNull LifecycleOwner lifecycleOwner, @NotNull Observer<ConnectionStatus> connectionStateListener) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(connectionStateListener, "connectionStateListener");
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().registerConnectionStatus().observe(lifecycleOwner, connectionStateListener);
    }

    public final void registerForLiveHeartRate() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApiManager.getInstance(context2).getBleApi().registerLiveHealthData().observeForever(this.I);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.coveiot.android.bleabstract.request.ActivityPauseResumeRequest, T] */
    public final void resumeLiveStepsGpsRunWithActivityModeCommand$app_prodRelease() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ActivityPauseResumeRequest.Builder().pauseSession(true).build();
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        T activityPauseResumeRequest = objectRef.element;
        Intrinsics.checkNotNullExpressionValue(activityPauseResumeRequest, "activityPauseResumeRequest");
        bleApi.setUserSettings((BleBaseRequest) activityPauseResumeRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$resumeLiveStepsGpsRunWithActivityModeCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [com.coveiot.android.bleabstract.request.ActivityPauseResumeRequest, T] */
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                objectRef.element = new ActivityPauseResumeRequest.Builder().pauseSession(false).build();
                Context context2 = this.q;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context2 = null;
                }
                BleApi bleApi2 = BleApiManager.getInstance(context2).getBleApi();
                ActivityPauseResumeRequest activityPauseResumeRequest2 = objectRef.element;
                Intrinsics.checkNotNullExpressionValue(activityPauseResumeRequest2, "activityPauseResumeRequest");
                bleApi2.setUserSettings(activityPauseResumeRequest2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$resumeLiveStepsGpsRunWithActivityModeCommand$1$onSettingsResponse$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                        Intrinsics.checkNotNullParameter(response2, "response");
                    }
                });
            }
        });
    }

    public final void s(final WeatherResultListener weatherResultListener) {
        final String[] strArr = new String[1];
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(mContext)");
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        if (ContextCompat.checkSelfPermission(context2, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getCurrentLocation$1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(@Nullable Location location) {
                    if (location != null) {
                        WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
                        Context context4 = ViewModelActivityDashboard.this.q;
                        if (context4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context4 = null;
                        }
                        WeatherPreferenceManager companion2 = companion.getInstance(context4);
                        Intrinsics.checkNotNull(companion2);
                        companion2.saveLastLocationLatLng(location);
                        String[] strArr2 = strArr;
                        StringBuilder sb = new StringBuilder();
                        sb.append(location.getLatitude());
                        sb.append(',');
                        sb.append(location.getLongitude());
                        strArr2[0] = sb.toString();
                        String str = ViewModelActivityDashboard.this.e;
                        LogHelper.d(str, "Last Location ==== " + location.getLatitude() + ", " + location.getLongitude());
                        ViewModelActivityDashboard.this.O(location.getLatitude(), location.getLongitude(), weatherResultListener);
                        return;
                    }
                    LogHelper.d(ViewModelActivityDashboard.this.e, "getLastLocationLatLng onSuccess() location is NULL====");
                    weatherResultListener.onFailure("Unable to fetch location");
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$getCurrentLocation$2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(@NotNull Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    LogHelper.d(ViewModelActivityDashboard.this.e, "onFailure() in getLastLocation() ====");
                    weatherResultListener.onFailure("Unable to fetch location");
                }
            });
        } else {
            weatherResultListener.onFailure("Location permission required");
        }
    }

    public final void saveFitnessConfig(@NotNull final String selectedWeight) {
        Intrinsics.checkNotNullParameter(selectedWeight, "selectedWeight");
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        ProfileRepository profileRepository = ProfileRepository.getInstance();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        int i = profileRepository.getLatestProfileData(context3).walkStrideLength;
        ProfileRepository profileRepository2 = ProfileRepository.getInstance();
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        int i2 = profileRepository2.getLatestProfileData(context4).runStrideLength;
        FitnessConfigRequest fitnessConfigRequest = new FitnessConfigRequest(userDetails.getHeight(), selectedWeight, i > 0 ? String.valueOf(i) : null, i2 > 0 ? String.valueOf(i2) : null);
        Context context5 = this.q;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context5 = null;
        }
        if (AppUtils.isNetConnected(context5)) {
            FitnessConfigApi.saveFitnessConfigData(fitnessConfigRequest, new CoveApiListener<FitnessConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$saveFitnessConfig$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable FitnessConfigResponse fitnessConfigResponse) {
                    Context context6 = ViewModelActivityDashboard.this.q;
                    Context context7 = null;
                    if (context6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context6 = null;
                    }
                    Context context8 = ViewModelActivityDashboard.this.q;
                    if (context8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context7 = context8;
                    }
                    Toast.makeText(context6, context7.getResources().getString(R.string.weight_updated_successfully), 0).show();
                    ViewModelActivityDashboard.this.updateLoginSession(selectedWeight);
                }
            });
            return;
        }
        String str = this.e;
        Context context6 = this.q;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context6;
        }
        LogHelper.e(str, context2.getString(R.string.noconnection));
    }

    public final void saveSPO2DataToServer() {
        SPO2Repository.Companion companion = SPO2Repository.Companion;
        Context context = this.q;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        final List<SPO2Record> unSyncedData = companion.getInstance(context).getUnSyncedData();
        if (AppUtils.isEmpty(unSyncedData)) {
            return;
        }
        FitnessRecordApiManager.saveFitnessRecords(SPO2Formator.getSPO2ServerModels(unSyncedData), new CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$saveSPO2DataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveFitnessRecordsResponse saveFitnessRecordsResponse) {
                Intrinsics.checkNotNullParameter(saveFitnessRecordsResponse, "saveFitnessRecordsResponse");
                if (kotlin.text.m.equals(saveFitnessRecordsResponse.status, CoveApiConstants.RESPONSE_STATUS_VALUE_OK, true)) {
                    List<SPO2Record> list = unSyncedData;
                    Intrinsics.checkNotNull(list);
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        SPO2Record sPO2Record = unSyncedData.get(i);
                        sPO2Record.isSyncedToServer = true;
                        SPO2Repository.Companion companion2 = SPO2Repository.Companion;
                        Context context2 = this.q;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context2 = null;
                        }
                        companion2.getInstance(context2).insert(sPO2Record);
                    }
                }
            }
        });
    }

    public final void saveSPO2Value(double d2, @NotNull Spo2DeviceType spo2DeviceType, boolean z) {
        Intrinsics.checkNotNullParameter(spo2DeviceType, "spo2DeviceType");
        SPO2Record sPO2Record = new SPO2Record();
        sPO2Record.timeStamp = System.currentTimeMillis();
        sPO2Record.value = d2;
        sPO2Record.isLevelIntepreTation = z;
        sPO2Record.date = AppUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        sPO2Record.timeZoneOffSet = PayUtils.INSTANCE.getTimeZoneOffset();
        sPO2Record.isSyncedToServer = false;
        SPO2Repository.Companion companion = SPO2Repository.Companion;
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        companion.getInstance(context).insert(sPO2Record);
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        ManualDataDBWrite.getInstance(context2).insert(getEntityManualData(spo2DeviceType, d2, System.currentTimeMillis(), z));
        saveSPO2DataToServer();
    }

    public final void setAgpsFilePath(@Nullable String str) {
        this.t = str;
    }

    public final void setBatteryRequestFailure(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.s = mutableLiveData;
    }

    public final void setBatteryUpdate(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.r = mutableLiveData;
    }

    public final void setContractUpdateHrBpHealthTextListener(@Nullable ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener) {
        this.m = contractUpdateHrBpHealthTextListener;
    }

    public final void setDailyBPLiveData(@Nullable LiveData<EntityDailyBp> liveData) {
        this.J = liveData;
    }

    public final void setDailyHRLiveData(@Nullable LiveData<EntityDailyHeartRateData> liveData) {
        this.K = liveData;
    }

    public final void setDailySleepLiveData(@Nullable LiveData<List<SleepDataModelForLastNight>> liveData) {
        this.d = liveData;
    }

    public final void setDailyStepsLivedata(@Nullable LiveData<DailyWalkData> liveData) {
        this.E = liveData;
    }

    public final void setDataSyncFailureCount(int i) {
        this.w = i;
    }

    public final void setHourHRLiveData(@Nullable LiveData<EntityHourlyHeartRateData> liveData) {
        this.L = liveData;
    }

    public final void setHourTemperatureLiveData(@Nullable LiveData<List<HourlyTemperature>> liveData) {
        this.M = liveData;
    }

    public final void setLastCommanmdSentTime(long j) {
        this.v = j;
    }

    public final void setLatestRespiratoryRateLiveData(@Nullable LiveData<DailyRespiratoryRateEntity> liveData) {
        this.G = liveData;
    }

    public final void setMLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        this.n = lifecycleOwner;
    }

    public final void setMListener(@NotNull PermissionListener permissionListener) {
        Intrinsics.checkNotNullParameter(permissionListener, "<set-?>");
        this.mListener = permissionListener;
    }

    public final void setManualSyncAttept(boolean z) {
        this.j = z;
    }

    public final void setMatchStateIndex(int i) {
        this.u = i;
    }

    public final void setProgressUpdateData(@NotNull ProgressUpdateData progressUpdateData) {
        Intrinsics.checkNotNullParameter(progressUpdateData, "<set-?>");
        this.B = progressUpdateData;
    }

    public final void setProgressValueSync(@NotNull MutableLiveData<ProgressUpdateData> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.A = mutableLiveData;
    }

    public final void setSpO2RecordLivedata(@Nullable LiveData<EntityManualData> liveData) {
        this.F = liveData;
    }

    public final void setStageValue(int i) {
        this.C = i;
    }

    public final void setSyncManager(@Nullable SyncManager syncManager) {
        this.o = syncManager;
    }

    public final void setSyncStateLiveData(@NotNull MutableLiveData<SyncState> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.l = mutableLiveData;
    }

    public final void setSyncTroubleShoutListener(@NotNull SyncTroubleShoutListener syncTroubleShoutListener) {
        Intrinsics.checkNotNullParameter(syncTroubleShoutListener, "<set-?>");
        this.syncTroubleShoutListener = syncTroubleShoutListener;
    }

    public final void setSyncing(boolean z) {
        this.i = z;
    }

    public final void setTimeStampSyncTigger(long j) {
        this.k = j;
    }

    public final void setViewModelListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.viewModelListener = viewModelListener;
    }

    public final void startLiveHeartRate$app_prodRelease() {
        LiveHeartRateControlRequest liveHeartRateControlRequest = new LiveHeartRateControlRequest.Builder(true).build();
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED && !checkIsSyncing()) {
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
            Intrinsics.checkNotNullExpressionValue(liveHeartRateControlRequest, "liveHeartRateControlRequest");
            bleApi.setUserSettings(liveHeartRateControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$startLiveHeartRate$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ViewModelActivityDashboard.this.e, "liveHeartRateControlRequest failure");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ViewModelActivityDashboard.this.e, "Start heart rate from On Resume");
                }
            });
        }
    }

    public final void startLiveSteps$app_prodRelease(final boolean z) {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED && !checkIsSyncing()) {
            LiveStepsControlRequest liveStepsControlRequest = new LiveStepsControlRequest.Builder(true).build();
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
            Intrinsics.checkNotNullExpressionValue(liveStepsControlRequest, "liveStepsControlRequest");
            bleApi.setUserSettings(liveStepsControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$startLiveSteps$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequest failure");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequest success");
                    if (z) {
                        Context context4 = ViewModelActivityDashboard.this.q;
                        if (context4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context4 = null;
                        }
                        BleApiManager.getInstance(context4).getBleApi().registerLiveStepsData().observeForever(ViewModelActivityDashboard.this.getVirtualRunStepsLiveDataObserve());
                        ViewModelActivityDashboard.this.startLiveHeartRate$app_prodRelease();
                        ViewModelActivityDashboard.this.registerForLiveHeartRate();
                    }
                }
            });
        }
    }

    public final void startLiveStepsGpsRun$app_prodRelease() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().registerLiveStepsData().observeForever(this.H);
        LiveStepsControlRequest liveStepsControlRequest = new LiveStepsControlRequest.Builder(true).build();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
        Intrinsics.checkNotNullExpressionValue(liveStepsControlRequest, "liveStepsControlRequest");
        bleApi.setUserSettings(liveStepsControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$startLiveStepsGpsRun$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequestGps failure");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequestGps success");
                ViewModelActivityDashboard.this.registerForLiveHeartRate();
                ViewModelActivityDashboard.this.startLiveHeartRate$app_prodRelease();
            }
        });
    }

    public final void startLiveStepsGpsRunWithActivityModeCommand$app_prodRelease(@NotNull String activityType) {
        SportModeRequest build;
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        if (Intrinsics.areEqual(activityType, ActivityMode.WALK.name())) {
            build = new SportModeRequest.Builder().setIsIndoor(false).setModes(false, false, false, true, false).build();
        } else if (Intrinsics.areEqual(activityType, ActivityMode.RUN.name())) {
            build = new SportModeRequest.Builder().setIsIndoor(false).setModes(true, false, false, false, false).build();
        } else {
            build = new SportModeRequest.Builder().setIsIndoor(false).setModes(false, false, false, true, false).build();
        }
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApiManager.getInstance(context2).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$startLiveStepsGpsRunWithActivityModeCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequestGps failure");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequestGps success");
                ViewModelActivityDashboard.this.registerForLiveHeartRate();
                ViewModelActivityDashboard.this.Y();
            }
        });
    }

    public final void startLiveTemperature$app_prodRelease() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED && !checkIsSyncing()) {
            LiveTemperatureControlRequest liveTemperatureControlRequest = new LiveTemperatureControlRequest.Builder(true).build();
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
            Intrinsics.checkNotNullExpressionValue(liveTemperatureControlRequest, "liveTemperatureControlRequest");
            bleApi.setUserSettings(liveTemperatureControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$startLiveTemperature$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                }
            });
        }
    }

    public final void startSyncCountdownTimer() {
        CountDownTimer countDownTimer = this.y;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.y = null;
        }
        if (this.y == null) {
            this.y = new CountDownTimer() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$startSyncCountdownTimer$1
                {
                    super(Constants.ONE_MIN_IN_MILLIS, 1000L);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    CountDownTimer countDownTimer2;
                    countDownTimer2 = ViewModelActivityDashboard.this.y;
                    Intrinsics.checkNotNull(countDownTimer2);
                    countDownTimer2.cancel();
                    Context context = null;
                    ViewModelActivityDashboard.this.y = null;
                    ProgressUpdateData progressUpdateData = new ProgressUpdateData();
                    Context context2 = ViewModelActivityDashboard.this.q;
                    if (context2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context = context2;
                    }
                    progressUpdateData.setTextProgress(context.getResources().getString(R.string.trying_to_connect));
                    progressUpdateData.setShouldShowSubText(true);
                    ViewModelActivityDashboard.this.getProgressValueSync().postValue(progressUpdateData);
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }
            };
        }
        CountDownTimer countDownTimer2 = this.y;
        Intrinsics.checkNotNull(countDownTimer2);
        countDownTimer2.start();
    }

    public final void startTimer() {
        CountDownTimer countDownTimer = this.x;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.x = null;
        }
        if (this.x == null) {
            this.x = new CountDownTimer() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$startTimer$1
                {
                    super(180000L, 1000L);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    CountDownTimer countDownTimer2;
                    countDownTimer2 = ViewModelActivityDashboard.this.x;
                    Intrinsics.checkNotNull(countDownTimer2);
                    countDownTimer2.cancel();
                    ViewModelActivityDashboard.this.x = null;
                    ViewModelActivityDashboard.this.stopTimer();
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }
            };
        }
        CountDownTimer countDownTimer2 = this.x;
        Intrinsics.checkNotNull(countDownTimer2);
        countDownTimer2.start();
    }

    public final void stopLiveHearRate() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        LiveHeartRateControlRequest liveStepsControlRequest = new LiveHeartRateControlRequest.Builder(false).build();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
        Intrinsics.checkNotNullExpressionValue(liveStepsControlRequest, "liveStepsControlRequest");
        bleApi.setUserSettings(liveStepsControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$stopLiveHearRate$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void stopLiveHrAndSteps() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        LiveHeartRateControlRequest liveHrControlRequest = new LiveHeartRateControlRequest.Builder(false).build();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
        Intrinsics.checkNotNullExpressionValue(liveHrControlRequest, "liveHrControlRequest");
        bleApi.setUserSettings(liveHrControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$stopLiveHrAndSteps$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LiveStepsControlRequest liveStepsControlRequest = new LiveStepsControlRequest.Builder(false).build();
                Context context4 = ViewModelActivityDashboard.this.q;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context4 = null;
                }
                BleApi bleApi2 = BleApiManager.getInstance(context4).getBleApi();
                Intrinsics.checkNotNullExpressionValue(liveStepsControlRequest, "liveStepsControlRequest");
                bleApi2.setUserSettings(liveStepsControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$stopLiveHrAndSteps$1$onSettingsResponse$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                        Intrinsics.checkNotNullParameter(response2, "response");
                    }
                });
            }
        });
    }

    public final void stopLiveStepsGpsRunWithActivityModeCommand$app_prodRelease() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        SportModeRequest sportModeRequest = new SportModeRequest.Builder().setIsIndoor(false).setModes(false, false, false, false, false).build();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
        Intrinsics.checkNotNullExpressionValue(sportModeRequest, "sportModeRequest");
        bleApi.setUserSettings(sportModeRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$stopLiveStepsGpsRunWithActivityModeCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequestGps failure");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ViewModelActivityDashboard.this.e, "liveStepsControlRequestGps success");
            }
        });
    }

    public final void stopLiveTemperature() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        LiveTemperatureControlRequest liveStepsControlRequest = new LiveTemperatureControlRequest.Builder(false).build();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApi bleApi = BleApiManager.getInstance(context2).getBleApi();
        Intrinsics.checkNotNullExpressionValue(liveStepsControlRequest, "liveStepsControlRequest");
        bleApi.setUserSettings(liveStepsControlRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$stopLiveTemperature$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void stopSyncTimer() {
        CountDownTimer countDownTimer = this.y;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.y = null;
        }
    }

    public final void stopTimer() {
        this.B.setPercentBySatge(0);
        this.B.setProgressPercent(0);
        this.B.setTextProgress("");
        this.A.postValue(this.B);
        CountDownTimer countDownTimer = this.x;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.x = null;
        }
    }

    public final void syncData(boolean z) {
        if (this.o == null) {
            this.o = SyncManager.getInstance();
        }
        if (checkIsSyncing()) {
            LogHelper.d(this.e, "Sync Skiped");
            return;
        }
        SyncManager syncManager = this.o;
        Intrinsics.checkNotNull(syncManager);
        Context context = null;
        if (syncManager.isSyncInProgress()) {
            this.l.setValue(SyncState.IDLE);
            MutableLiveData<SyncState> mutableLiveData = this.l;
            mutableLiveData.postValue(mutableLiveData.getValue());
            LogHelper.d(this.e, "Sync Skiped isSyncInProgress is true");
            Context context2 = this.q;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context2 = null;
            }
            Context context3 = this.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context = context3;
            }
            Toast.makeText(context2, context.getString(R.string.sync_is_in_progress), 1).show();
            return;
        }
        this.j = z;
        this.k = System.currentTimeMillis();
        this.i = true;
        this.l.setValue(SyncState.SYNINCING);
        MutableLiveData<SyncState> mutableLiveData2 = this.l;
        mutableLiveData2.postValue(mutableLiveData2.getValue());
        this.B.setPercentBySatge(0);
        this.B.setProgressPercent(0);
        ProgressUpdateData progressUpdateData = this.B;
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context = context4;
        }
        progressUpdateData.setTextProgress(context.getResources().getString(R.string.syncing_data));
        this.A.postValue(this.B);
        Log.d(this.e, "Sync manager ref" + this.o);
        getCurrentStepsFromWatch(new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$syncData$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ViewModelActivityDashboard.this.d0();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                CoveEventBusManager.getInstance().getEventBus().post(new UpdateHomeDashboard());
                ViewModelActivityDashboard.this.d0();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final ArrayList<CyclingSample> t(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboard viewModelActivityDashboard = this;
        int i2 = i;
        ArrayList<CyclingSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    CyclingSample cyclingSample = new CyclingSample();
                    cyclingSample.setSess_id(str);
                    cyclingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i3 = viewModelActivityDashboard.i(arrayList, j4, i2);
                    if (i3 != null) {
                        sampleData.setCalories((float) i3.getCalories());
                        sampleData.setDistance((int) i3.getDistance());
                        cyclingSample.setStepCount(i3.getStepsValue());
                    }
                    ActivityHeartRateSample h = viewModelActivityDashboard.h(arrayList2, j4, i2);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ActivityGPSSample g = viewModelActivityDashboard.g(arrayList3, j4, i2);
                    if (g != null) {
                        sampleData.setLatitude(g.getLocation().latitude);
                        sampleData.setLongitude(g.getLocation().longitude);
                    }
                    cyclingSample.setSampleData(sampleData);
                    arrayList4.add(cyclingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboard = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<DanceSample> u(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<DanceSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    DanceSample danceSample = new DanceSample();
                    danceSample.setSess_id(str);
                    danceSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        danceSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    danceSample.setSampleData(sampleData);
                    arrayList3.add(danceSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void updateAgpsFile() {
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        File file = new File(context.getFilesDir(), AppConstants.JSTYLE_AGPS_FILE_NAME.getValue() + ".bin");
        AgpsUpdateRequest agpsUpdateRequest = new AgpsUpdateRequest();
        agpsUpdateRequest.setAgpsFilePath(file.getAbsolutePath());
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        BleApiManager.getInstance(context2).getBleApi().getData(agpsUpdateRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$updateAgpsFile$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ViewModelActivityDashboard.this.e, "agps_upload_failed");
                String errorMsg = error.getErrorMsg();
                Context context4 = ViewModelActivityDashboard.this.q;
                Context context5 = null;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context4 = null;
                }
                if (Intrinsics.areEqual(errorMsg, context4.getResources().getString(R.string.error_device_connected_msg))) {
                    Context context6 = ViewModelActivityDashboard.this.q;
                    if (context6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context6 = null;
                    }
                    UserDataManager.getInstance(context6).saveDisconnectionAtAgpsFileUpdate(true);
                    Context context7 = ViewModelActivityDashboard.this.q;
                    if (context7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context7 = null;
                    }
                    Context context8 = ViewModelActivityDashboard.this.q;
                    if (context8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context5 = context8;
                    }
                    Toast.makeText(context7, context5.getString(R.string.agps_update_in_progress), 1).show();
                    ViewModelActivityDashboard.this.getSyncStateLiveData().setValue(SyncState.IDLE);
                    ViewModelActivityDashboard.this.getSyncStateLiveData().postValue(ViewModelActivityDashboard.this.getSyncStateLiveData().getValue());
                    return;
                }
                ViewModelActivityDashboard.this.getProgressUpdateData().setProgressPercent(ViewModelActivityDashboard.this.getProgressUpdateData().getProgressPercent() + ViewModelActivityDashboard.this.getProgressUpdateData().getPercentBySatge());
                ViewModelActivityDashboard.this.getProgressValueSync().postValue(ViewModelActivityDashboard.this.getProgressUpdateData());
                ViewModelActivityDashboard.this.postDataSyncComplete();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ViewModelActivityDashboard.this.getProgressUpdateData().setProgressPercent(ViewModelActivityDashboard.this.getProgressUpdateData().getProgressPercent() + ViewModelActivityDashboard.this.getProgressUpdateData().getPercentBySatge());
                ViewModelActivityDashboard.this.getProgressValueSync().postValue(ViewModelActivityDashboard.this.getProgressUpdateData());
                LogHelper.d(ViewModelActivityDashboard.this.e, "agps_uploaded_success");
                ViewModelActivityDashboard.this.postDataSyncComplete();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.e1
            @Override // java.lang.Runnable
            public final void run() {
                ViewModelActivityDashboard.f0(ViewModelActivityDashboard.this);
            }
        }, 5000L);
    }

    public final void updateHeightWeightToBand(@NotNull final String selectedWeight) {
        Intrinsics.checkNotNullParameter(selectedWeight, "selectedWeight");
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        String height = userDetails.getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
        SetFitnessInfoRequest.Builder builder = new SetFitnessInfoRequest.Builder(Integer.parseInt(height), Double.parseDouble(selectedWeight));
        ProfileRepository profileRepository = ProfileRepository.getInstance();
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        builder.setStride(profileRepository.getLatestProfileData(context3).walkStrideLength);
        ProfileRepository profileRepository2 = ProfileRepository.getInstance();
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        builder.setRunStride(profileRepository2.getLatestProfileData(context4).runStrideLength);
        Context context5 = this.q;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context5 = null;
        }
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context5).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(mContext).isDistanceUnitInMile");
        builder.setUnitType(isDistanceUnitInMile.booleanValue() ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC);
        String dob = userDetails.getDob();
        boolean z = false;
        if (!(dob == null || dob.length() == 0)) {
            builder.setAge(AppUtils.getAge(userDetails.getDob()));
        }
        String gender = userDetails.getGender();
        if (gender == null || gender.length() == 0) {
            z = true;
        }
        if (!z) {
            builder.setMale(kotlin.text.m.equals(userDetails.getGender(), AppConstants.MALE.getValue(), true));
        }
        SetFitnessInfoRequest builder2 = builder.builder();
        Intrinsics.checkNotNullExpressionValue(builder2, "fitnessInfoRequestBuilder.builder()");
        Context context6 = this.q;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context6;
        }
        BleApiManager.getInstance(context2).getBleApi().setUserSettings(builder2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$updateHeightWeightToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ViewModelActivityDashboard.this.e, "Failed to save Fitness Info");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ViewModelActivityDashboard.this.e, "Fitness Info saved successfully");
                ViewModelActivityDashboard.this.saveFitnessConfig(selectedWeight);
            }
        });
    }

    public final void updateLoginSession(@NotNull String weight) {
        Intrinsics.checkNotNullParameter(weight, "weight");
        Context context = this.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        userDetails.setWeight(weight);
        Context context3 = this.q;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        SessionManager.getInstance(context3).createLoginSession(userDetails);
        ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener = this.m;
        Intrinsics.checkNotNull(contractUpdateHrBpHealthTextListener);
        contractUpdateHrBpHealthTextListener.onFitnessHeightWeightUpdate(true);
        ProfileRepository profileRepository = ProfileRepository.getInstance();
        Context context4 = this.q;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context4;
        }
        String height = userDetails.getHeight();
        Intrinsics.checkNotNull(height);
        int parseInt = Integer.parseInt(height);
        String weight2 = userDetails.getWeight();
        Intrinsics.checkNotNull(weight2);
        profileRepository.updateHeightWeight(context2, parseInt, Double.parseDouble(weight2));
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.String] */
    public final void updateRegistrationToServer() {
        SessionManager sessionManager = null;
        this.h.removeCallbacksAndMessages(null);
        if (this.g != null) {
            Context context = this.q;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            context.unregisterReceiver(this.g);
            this.g = null;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        SessionManager sessionManager2 = this.p;
        if (sessionManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
            sessionManager2 = null;
        }
        ?? registrationToken = sessionManager2.getRegistrationToken();
        objectRef.element = registrationToken;
        if (AppUtils.isEmpty((String) registrationToken)) {
            Task<String> token = FirebaseMessaging.getInstance().getToken();
            final Function1<String, Unit> function1 = new Function1<String, Unit>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$updateRegistrationToServer$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String str) {
                    SessionManager sessionManager3;
                    SessionManager sessionManager4;
                    SessionManager sessionManager5;
                    objectRef.element = str;
                    sessionManager3 = this.p;
                    SessionManager sessionManager6 = null;
                    if (sessionManager3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
                        sessionManager3 = null;
                    }
                    sessionManager3.saveRegistrationToken(objectRef.element);
                    sessionManager4 = this.p;
                    if (sessionManager4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
                        sessionManager4 = null;
                    }
                    ProfileData userDetails = sessionManager4.getUserDetails();
                    if ((userDetails != null ? Integer.valueOf(userDetails.getUserId()) : null) != null) {
                        sessionManager5 = this.p;
                        if (sessionManager5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
                        } else {
                            sessionManager6 = sessionManager5;
                        }
                        FCMRegistrationReq fCMRegistrationReq = new FCMRegistrationReq(objectRef.element, String.valueOf(sessionManager6.getUserDetails().getUserId()));
                        final ViewModelActivityDashboard viewModelActivityDashboard = this;
                        CoveOnboarding.sendUserRegistrationTokenToServer(fCMRegistrationReq, new CoveApiListener<FCMRegistrationRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$updateRegistrationToServer$1.1
                            @Override // com.coveiot.coveaccess.CoveApiListener
                            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                                ViewModelActivityDashboard.this.R();
                            }

                            @Override // com.coveiot.coveaccess.CoveApiListener
                            public void onSuccess(@NotNull FCMRegistrationRes fcmRegistrationRes) {
                                Intrinsics.checkNotNullParameter(fcmRegistrationRes, "fcmRegistrationRes");
                                if (kotlin.text.m.equals(fcmRegistrationRes.getStatus(), CoveApiConstants.RESPONSE_STATUS_VALUE_ERROR, true)) {
                                    ViewModelActivityDashboard.this.R();
                                }
                            }
                        });
                    }
                }
            };
            token.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.dashboard.d1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ViewModelActivityDashboard.g0(Function1.this, obj);
                }
            });
            return;
        }
        SessionManager sessionManager3 = this.p;
        if (sessionManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
            sessionManager3 = null;
        }
        ProfileData userDetails = sessionManager3.getUserDetails();
        if ((userDetails != null ? Integer.valueOf(userDetails.getUserId()) : null) != null) {
            SessionManager sessionManager4 = this.p;
            if (sessionManager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
            } else {
                sessionManager = sessionManager4;
            }
            CoveOnboarding.sendUserRegistrationTokenToServer(new FCMRegistrationReq((String) objectRef.element, String.valueOf(sessionManager.getUserDetails().getUserId())), new CoveApiListener<FCMRegistrationRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$updateRegistrationToServer$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                    Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                    ViewModelActivityDashboard.this.R();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull FCMRegistrationRes fcmRegistrationRes) {
                    Intrinsics.checkNotNullParameter(fcmRegistrationRes, "fcmRegistrationRes");
                    if (kotlin.text.m.equals(fcmRegistrationRes.getStatus(), CoveApiConstants.RESPONSE_STATUS_VALUE_ERROR, true)) {
                        ViewModelActivityDashboard.this.R();
                    }
                }
            });
        }
    }

    public final ArrayList<EllipticalSample> v(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<EllipticalSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    EllipticalSample ellipticalSample = new EllipticalSample();
                    ellipticalSample.setSess_id(str);
                    ellipticalSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        ellipticalSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    ellipticalSample.setSampleData(sampleData);
                    arrayList3.add(ellipticalSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object w(com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse r24, kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession> r25) {
        /*
            Method dump skipped, instructions count: 1350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard.w(com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final EntityWorkoutSessionSegment x(EntityWorkoutSession entityWorkoutSession, String str) {
        EntityWorkoutSessionSegment entityWorkoutSessionSegment = new EntityWorkoutSessionSegment();
        entityWorkoutSessionSegment.setSess_id(entityWorkoutSession.getSession_id());
        entityWorkoutSessionSegment.setSegment_id(str);
        entityWorkoutSessionSegment.setSegment_duration((entityWorkoutSession.getSession_duration() / 60) + 1);
        entityWorkoutSessionSegment.setStart_time(entityWorkoutSession.getStart_time());
        entityWorkoutSessionSegment.setEnd_time(entityWorkoutSession.getEnd_time());
        return entityWorkoutSessionSegment;
    }

    public final ArrayList<FootballSample> y(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<FootballSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    FootballSample footballSample = new FootballSample();
                    footballSample.setSess_id(str);
                    footballSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        footballSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    footballSample.setSampleData(sampleData);
                    arrayList3.add(footballSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<FreeExerciseSample> z(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<FreeExerciseSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    FreeExerciseSample freeExerciseSample = new FreeExerciseSample();
                    freeExerciseSample.setSess_id(str);
                    freeExerciseSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample i2 = i(arrayList, j4, i);
                    if (i2 != null) {
                        sampleData.setCalories((float) i2.getCalories());
                        sampleData.setDistance((int) i2.getDistance());
                        freeExerciseSample.setStepCount(i2.getStepsValue());
                    }
                    ActivityHeartRateSample h = h(arrayList2, j4, i);
                    if (h != null) {
                        sampleData.setHr_value(h.getHrValue());
                    }
                    freeExerciseSample.setSampleData(sampleData);
                    arrayList3.add(freeExerciseSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }
}
