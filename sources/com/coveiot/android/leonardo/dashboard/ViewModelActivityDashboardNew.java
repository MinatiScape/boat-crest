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
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
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
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.models.ActivityIcons;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityGPSSample;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeleteActivityDataRequest;
import com.coveiot.android.bleabstract.request.DeleteGpsDataRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetSensAIActivityConfigRequest;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.SyncTroubleShoutListener;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.dashboard2.listener.WeatherResultListener;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractUpdateHrBpHealthTextListener;
import com.coveiot.android.leonardo.dashboard.health.spo2.SPO2Level;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import com.coveiot.android.leonardo.sp02.SPO2Formator;
import com.coveiot.android.leonardo.sp02.SPO2Repository;
import com.coveiot.android.leonardo.sp02.database.entities.SPO2Record;
import com.coveiot.android.leonardo.sp02.preference.Spo2DataManager;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sportsnotification.SportsPreference;
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
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.config.FitnessConfigApi;
import com.coveiot.coveaccess.fitness.config.models.requestmodel.FitnessConfigRequest;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordApiManager;
import com.coveiot.coveaccess.fitnessrecord.SaveFitnessRecordsResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetConsentData;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.model.server.UserRemoteConfigResponse;
import com.coveiot.coveaccess.onboarding.model.FCMRegistrationReq;
import com.coveiot.coveaccess.onboarding.model.FCMRegistrationRes;
import com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.manualdata.entities.Source;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import com.coveiot.covepreferences.data.StressConfiguration;
import com.coveiot.covepreferences.data.WebViewUrlBean;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.bp.BPRepository;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.walk.WalkRepository;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;
import com.google.zxing.pdf417.PDF417Common;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelActivityDashboardNew extends AndroidViewModel {
    @Nullable
    public LiveData<List<SleepDataModelForLastNight>> d;
    @NotNull
    public final String e;
    public final int f;
    @Nullable
    public BroadcastReceiver g;
    @NotNull
    public final Handler h;
    @Nullable
    public ContractUpdateHrBpHealthTextListener i;
    @Nullable
    public LifecycleOwner j;
    public SessionManager k;
    public Context l;
    @NotNull
    public MutableLiveData<String> m;
    public PermissionListener mListener;
    @NotNull
    public MutableLiveData<Boolean> n;
    @Nullable
    public String o;
    public int p;
    public long q;
    public int r;
    @Nullable
    public CountDownTimer s;
    public SyncTroubleShoutListener syncTroubleShoutListener;
    @Nullable
    public CountDownTimer t;
    @Nullable
    public LiveData<DailyWalkData> u;
    @Nullable
    public LiveData<EntityManualData> v;
    public ViewModelListener viewModelListener;
    @Nullable
    public LiveData<DailyRespiratoryRateEntity> w;

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

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew", f = "ViewModelActivityDashboardNew.kt", i = {0, 0, 0}, l = {PDF417Common.NUMBER_OF_CODEWORDS}, m = "getEntityWorkoutSession", n = {"this", "sportsHistoryResponse", "entityWorkoutSession"}, s = {"L$0", "L$1", "L$2"})
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
            return ViewModelActivityDashboardNew.this.t(null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$populateDeviceCapabilityConfig$1", f = "ViewModelActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DeviceRemoteConfig $deviceRemoteConfig;
        public int label;
        public final /* synthetic */ ViewModelActivityDashboardNew this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(DeviceRemoteConfig deviceRemoteConfig, ViewModelActivityDashboardNew viewModelActivityDashboardNew, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$deviceRemoteConfig = deviceRemoteConfig;
            this.this$0 = viewModelActivityDashboardNew;
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
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelActivityDashboardNew(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.e = "ViewModelDashboard";
        this.f = 60;
        this.h = new Handler(Looper.getMainLooper());
        this.m = new MutableLiveData<>("");
        this.n = new MutableLiveData<>();
        this.p = -1;
        this.l = application;
        if (application == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            application = null;
        }
        SessionManager sessionManager = SessionManager.getInstance(application);
        Intrinsics.checkNotNullExpressionValue(sessionManager, "getInstance(mContext)");
        this.k = sessionManager;
        Application application2 = getApplication();
        Intrinsics.checkNotNullExpressionValue(application2, "getApplication<GenericBandApplication>()");
        GenericBandApplication genericBandApplication = (GenericBandApplication) application2;
    }

    public static final void R(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void i(ViewModelActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateRegistrationToServer();
    }

    public static /* synthetic */ String k(ViewModelActivityDashboardNew viewModelActivityDashboardNew, ActivityModeSummaryResponse activityModeSummaryResponse, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return viewModelActivityDashboardNew.j(activityModeSummaryResponse, z);
    }

    public static /* synthetic */ void saveSPO2Value$default(ViewModelActivityDashboardNew viewModelActivityDashboardNew, double d, Spo2DeviceType spo2DeviceType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        viewModelActivityDashboardNew.saveSPO2Value(d, spo2DeviceType, z);
    }

    public static final void v(ViewModelActivityDashboardNew this$0, Ref.ObjectRef mFirebaseRemoteConfig, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String string = ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getString("fitness_challenges");
            Intrinsics.checkNotNullExpressionValue(string, "mFirebaseRemoteConfig.ge…ing(\"fitness_challenges\")");
            this$0.u(string);
        }
    }

    public final ArrayList<PhysicalActivitySample> A(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        physicalActivitySample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    physicalActivitySample.setSampleData(sampleData);
                    arrayList4.add(physicalActivitySample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<RowingMachineSample> B(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        rowingMachineSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final ArrayList<RunSample> C(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        runSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    runSample.setSampleData(sampleData);
                    arrayList4.add(runSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<SkippingSample> D(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        skippingSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final String E(Spo2DeviceType spo2DeviceType) {
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

    public final ArrayList<TennisSample> F(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        tennisSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final Object G(List<ActivityHeartRateSample> list, Continuation<? super TimeSpentHeartRateZone> continuation) {
        TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        HeartRateZoneRanges geHeartRateZoneRanges = workoutUtils.geHeartRateZoneRanges(new PreferenceManager(context).getAge());
        Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
        Context context3 = this.l;
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

    public final ArrayList<TreadmillSample> H(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        treadmillSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    treadmillSample.setSampleData(sampleData);
                    arrayList4.add(treadmillSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<WalkSample> I(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        walkSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    walkSample.setSampleData(sampleData);
                    arrayList4.add(walkSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.lang.String] */
    public final void J(final double d, final double d2, final WeatherResultListener weatherResultListener) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = WeatherUnit.METRIC.weatherUnit();
        WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
        Context context = this.l;
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
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        WeatherApiCallsManager weatherApiCallsManager = companion3.getInstance(context2).getWeatherApiCallsManager();
        Intrinsics.checkNotNull(weatherApiCallsManager);
        T weatherUnit = objectRef.element;
        Intrinsics.checkNotNullExpressionValue(weatherUnit, "weatherUnit");
        weatherApiCallsManager.getOpenWeatherMapDailyForecastInfo(d, d2, 7, (String) weatherUnit, new WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getWeatherForecastInfo$1
            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onError(@NotNull WeatherApiErrorModel obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                LogHelper.d("getWeatherForecastInfo", obj.toString());
                Integer code = obj.getCode();
                Context context4 = null;
                if (code == null || code.intValue() != 403) {
                    WeatherResultListener weatherResultListener2 = weatherResultListener;
                    if (weatherResultListener2 != null) {
                        weatherResultListener2.onFailure("Unable to fetch weather updates");
                    }
                    WeatherPreferenceManager.Companion companion4 = WeatherPreferenceManager.Companion;
                    Context context5 = ViewModelActivityDashboardNew.this.l;
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
                Context context6 = ViewModelActivityDashboardNew.this.l;
                if (context6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context4 = context6;
                }
                companion6.getInstance(context4).invalidate();
                ViewModelActivityDashboardNew.this.getConfigUrlsFromServer();
            }

            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onSuccess(@Nullable WeatherForecastModel weatherForecastModel) {
                LogHelper.d("getWeatherForecastInfo", FirebaseAnalytics.Param.SUCCESS);
                if (weatherForecastModel != null) {
                    WeatherPreferenceManager.Companion companion4 = WeatherPreferenceManager.Companion;
                    Context context4 = ViewModelActivityDashboardNew.this.l;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context4 = null;
                    }
                    WeatherPreferenceManager companion5 = companion4.getInstance(context4);
                    Intrinsics.checkNotNull(companion5);
                    companion5.saveWeatherForecastModel(weatherForecastModel);
                    WeatherAppPreferenceManager.Companion companion6 = WeatherAppPreferenceManager.Companion;
                    Context context5 = ViewModelActivityDashboardNew.this.l;
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
                    Context context6 = ViewModelActivityDashboardNew.this.l;
                    if (context6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context6 = null;
                    }
                    WeatherPreferenceManager companion9 = companion8.getInstance(context6);
                    Intrinsics.checkNotNull(companion9);
                    companion9.saveWeatherForecastModel(new WeatherForecastModel(null, null, null, null, null, null, null, null, null, null, null, 2047, null));
                }
                WeatherApiCallsManager.Companion companion10 = WeatherApiCallsManager.Companion;
                Context context7 = ViewModelActivityDashboardNew.this.l;
                if (context7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context7 = null;
                }
                WeatherApiCallsManager singletonHolder = companion10.getInstance(context7);
                double d3 = d;
                double d4 = d2;
                String str = objectRef.element;
                final WeatherResultListener weatherResultListener2 = weatherResultListener;
                singletonHolder.getOpenWeatherMapCurrentWeatherInfo(d3, d4, str, new WeatherApiResponseListener<CurrentForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getWeatherForecastInfo$1$onSuccess$1
                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                    public void onError(@NotNull WeatherApiErrorModel obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        WeatherResultListener weatherResultListener3 = WeatherResultListener.this;
                        if (weatherResultListener3 != null) {
                            weatherResultListener3.onFailure("Unable to fetch weather updates");
                        }
                    }

                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                    public void onSuccess(@Nullable CurrentForecastModel currentForecastModel) {
                        WeatherResultListener weatherResultListener3 = WeatherResultListener.this;
                        if (weatherResultListener3 != null) {
                            weatherResultListener3.onSuccess();
                        }
                    }
                });
            }
        });
    }

    public final ArrayList<WorkoutSample> K(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        workoutSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    workoutSample.setSampleData(sampleData);
                    arrayList4.add(workoutSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<YogaSample> L(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        yogaSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final void M() {
        if (P()) {
            h();
            return;
        }
        this.h.removeCallbacksAndMessages(null);
        Q();
    }

    public final Object N(ActivityMode activityMode, ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, String str, String str2, int i, Continuation<? super Unit> continuation) {
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
                    ActivityStepsSample f = f(arrayList, j4, i2);
                    if (f != null) {
                        activityDataSample.setCalories((float) f.getCalories());
                        activityDataSample.setDistance((int) f.getDistance());
                        activityDataSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i2);
                    if (e != null) {
                        activityDataSample.setHrValue(e.getHrValue());
                    }
                    ActivityGPSSample d = d(arrayList3, j4, i2);
                    if (d != null) {
                        activityDataSample.setLatitude(d.getLocation().latitude);
                        activityDataSample.setLongitude(d.getLocation().longitude);
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
                Context context = this.l;
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

    public final Object O(ActivityMode activityMode, ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, String str, String str2, int i, Continuation<? super Unit> continuation) {
        WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WorkoutSessionRepository singletonHolder = companion.getInstance(context);
        switch (WhenMappings.$EnumSwitchMapping$1[activityMode.ordinal()]) {
            case 1:
                ArrayList<WalkSample> I = I(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((I != null ? I.size() : 0) > 0) {
                    Intrinsics.checkNotNull(I);
                    Object insertWalkSampleList = singletonHolder.insertWalkSampleList(I, continuation);
                    return insertWalkSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertWalkSampleList : Unit.INSTANCE;
                }
                break;
            case 2:
                ArrayList<RunSample> C = C(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((C != null ? C.size() : 0) > 0) {
                    Intrinsics.checkNotNull(C);
                    Object insertRunSampleList = singletonHolder.insertRunSampleList(C, continuation);
                    return insertRunSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertRunSampleList : Unit.INSTANCE;
                }
                break;
            case 3:
                ArrayList<PhysicalActivitySample> A = A(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((A != null ? A.size() : 0) > 0) {
                    Intrinsics.checkNotNull(A);
                    Object insertPhysicalActivitySamples = singletonHolder.insertPhysicalActivitySamples(A, continuation);
                    return insertPhysicalActivitySamples == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertPhysicalActivitySamples : Unit.INSTANCE;
                }
                break;
            case 4:
                ArrayList<BadmintonSample> l = l(arrayList, arrayList2, j, j2, i, str, str2);
                if ((l != null ? l.size() : 0) > 0) {
                    Intrinsics.checkNotNull(l);
                    Object insertBadmintonSampleList = singletonHolder.insertBadmintonSampleList(l, continuation);
                    return insertBadmintonSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertBadmintonSampleList : Unit.INSTANCE;
                }
                break;
            case 5:
                ArrayList<FreeExerciseSample> x = x(arrayList, arrayList2, j, j2, i, str, str2);
                if ((x != null ? x.size() : 0) > 0) {
                    Intrinsics.checkNotNull(x);
                    Object insertFreeExerciseSampleList = singletonHolder.insertFreeExerciseSampleList(x, continuation);
                    return insertFreeExerciseSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertFreeExerciseSampleList : Unit.INSTANCE;
                }
                break;
            case 6:
                ArrayList<BasketBallSample> m = m(arrayList, arrayList2, j, j2, i, str, str2);
                if ((m != null ? m.size() : 0) > 0) {
                    Intrinsics.checkNotNull(m);
                    Object insertBasketBallSampleList = singletonHolder.insertBasketBallSampleList(m, continuation);
                    return insertBasketBallSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertBasketBallSampleList : Unit.INSTANCE;
                }
                break;
            case 7:
                ArrayList<CyclingSample> q = q(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((q != null ? q.size() : 0) > 0) {
                    Intrinsics.checkNotNull(q);
                    Object insertCyclingSampleList = singletonHolder.insertCyclingSampleList(q, continuation);
                    return insertCyclingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertCyclingSampleList : Unit.INSTANCE;
                }
                break;
            case 8:
                ArrayList<MeditationSample> z = z(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((z != null ? z.size() : 0) > 0) {
                    Intrinsics.checkNotNull(z);
                    Object insertMeditationSampleList = singletonHolder.insertMeditationSampleList(z, continuation);
                    return insertMeditationSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertMeditationSampleList : Unit.INSTANCE;
                }
                break;
            case 9:
                ArrayList<TreadmillSample> H = H(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((H != null ? H.size() : 0) > 0) {
                    Intrinsics.checkNotNull(H);
                    Object insertTreadmillSampleList = singletonHolder.insertTreadmillSampleList(H, continuation);
                    return insertTreadmillSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertTreadmillSampleList : Unit.INSTANCE;
                }
                break;
            case 10:
                ArrayList<ClimbingSample> o = o(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((o != null ? o.size() : 0) > 0) {
                    Intrinsics.checkNotNull(o);
                    Object insertClimbingSampleList = singletonHolder.insertClimbingSampleList(o, continuation);
                    return insertClimbingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertClimbingSampleList : Unit.INSTANCE;
                }
                break;
            case 11:
                ArrayList<DanceSample> r = r(arrayList, arrayList2, j, j2, i, str, str2);
                if ((r != null ? r.size() : 0) > 0) {
                    Intrinsics.checkNotNull(r);
                    Object insertDanceSampleList = singletonHolder.insertDanceSampleList(r, continuation);
                    return insertDanceSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertDanceSampleList : Unit.INSTANCE;
                }
                break;
            case 12:
                ArrayList<HikingSample> y = y(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((y != null ? y.size() : 0) > 0) {
                    Intrinsics.checkNotNull(y);
                    Object insertHikingSampleList = singletonHolder.insertHikingSampleList(y, continuation);
                    return insertHikingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertHikingSampleList : Unit.INSTANCE;
                }
                break;
            case 13:
                ArrayList<FootballSample> w = w(arrayList, arrayList2, j, j2, i, str, str2);
                if ((w != null ? w.size() : 0) > 0) {
                    Intrinsics.checkNotNull(w);
                    Object insertFootBallSampleList = singletonHolder.insertFootBallSampleList(w, continuation);
                    return insertFootBallSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertFootBallSampleList : Unit.INSTANCE;
                }
                break;
            case 14:
                ArrayList<TennisSample> F = F(arrayList, arrayList2, j, j2, i, str, str2);
                if ((F != null ? F.size() : 0) > 0) {
                    Intrinsics.checkNotNull(F);
                    Object insertTennisSampleList = singletonHolder.insertTennisSampleList(F, continuation);
                    return insertTennisSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertTennisSampleList : Unit.INSTANCE;
                }
                break;
            case 15:
                ArrayList<WorkoutSample> K = K(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((K != null ? K.size() : 0) > 0) {
                    Intrinsics.checkNotNull(K);
                    Object insertWorkoutSampleList = singletonHolder.insertWorkoutSampleList(K, continuation);
                    return insertWorkoutSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertWorkoutSampleList : Unit.INSTANCE;
                }
                break;
            case 16:
                ArrayList<YogaSample> L = L(arrayList, arrayList2, j, j2, i, str, str2);
                if ((L != null ? L.size() : 0) > 0) {
                    Intrinsics.checkNotNull(L);
                    Object insertYogaSampleList = singletonHolder.insertYogaSampleList(L, continuation);
                    return insertYogaSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertYogaSampleList : Unit.INSTANCE;
                }
                break;
            case 17:
                ArrayList<SkippingSample> D = D(arrayList, arrayList2, j, j2, i, str, str2);
                if ((D != null ? D.size() : 0) > 0) {
                    Intrinsics.checkNotNull(D);
                    Object insertSkippingSampleList = singletonHolder.insertSkippingSampleList(D, continuation);
                    return insertSkippingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertSkippingSampleList : Unit.INSTANCE;
                }
                break;
            case 18:
                ArrayList<EllipticalSample> s = s(arrayList, arrayList2, j, j2, i, str, str2);
                if ((s != null ? s.size() : 0) > 0) {
                    Intrinsics.checkNotNull(s);
                    Object insertEllipticalSampleList = singletonHolder.insertEllipticalSampleList(s, continuation);
                    return insertEllipticalSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertEllipticalSampleList : Unit.INSTANCE;
                }
                break;
            case 19:
                ArrayList<RowingMachineSample> B = B(arrayList, arrayList2, j, j2, i, str, str2);
                if ((B != null ? B.size() : 0) > 0) {
                    Intrinsics.checkNotNull(B);
                    Object insertRowingMachineSampleList = singletonHolder.insertRowingMachineSampleList(B, continuation);
                    return insertRowingMachineSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertRowingMachineSampleList : Unit.INSTANCE;
                }
                break;
        }
        return Unit.INSTANCE;
    }

    public final boolean P() {
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public final void Q() {
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            h();
            return;
        }
        this.g = new BroadcastReceiver() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$registerForNetworkConnectivityEvents$1
            @Override // android.content.BroadcastReceiver
            @SuppressLint({"MissingPermission"})
            public void onReceive(@NotNull Context context3, @NotNull Intent intent) {
                Intrinsics.checkNotNullParameter(context3, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                Object systemService2 = context3.getSystemService("connectivity");
                Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.net.ConnectivityManager");
                NetworkInfo activeNetworkInfo2 = ((ConnectivityManager) systemService2).getActiveNetworkInfo();
                if (activeNetworkInfo2 != null && activeNetworkInfo2.isAvailable() && activeNetworkInfo2.isConnected()) {
                    ViewModelActivityDashboardNew.this.updateRegistrationToServer();
                }
            }
        };
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        context2.registerReceiver(this.g, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void callOpenWeatherApiKey(@Nullable String str, @Nullable WeatherResultListener weatherResultListener) {
        WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WeatherAppPreferenceManager companion2 = companion.getInstance(context);
        Intrinsics.checkNotNull(companion2);
        Boolean isWeatherEnabled = companion2.isWeatherEnabled();
        WeatherApiCallsManager.Companion companion3 = WeatherApiCallsManager.Companion;
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        if (!companion3.getInstance(context3).isInitialized()) {
            Context context4 = this.l;
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
            p(weatherResultListener);
        } else if (weatherResultListener != null) {
            weatherResultListener.onFailure("Weather not enabled");
        }
    }

    public final void checkNavigationDisclaimerAcceptance(@NotNull final String version, @NotNull final Function3<? super String, ? super Boolean, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.getAcceptedLegalDoc(new CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$checkNavigationDisclaimerAcceptance$1
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
                    Context context2 = this.l;
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
                Context context3 = this.l;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context = context3;
                }
                String string2 = context.getString(R.string.some_thing_went_wrong);
                Boolean bool3 = Boolean.FALSE;
                function33.invoke(string2, bool3, bool3);
            }

            /* JADX WARN: Removed duplicated region for block: B:35:0x0067 A[EDGE_INSN: B:35:0x0067->B:30:0x0067 ?: BREAK  , SYNTHETIC] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes r8) {
                /*
                    r7 = this;
                    if (r8 == 0) goto L8e
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r0 = r8.getData()
                    if (r0 == 0) goto L8e
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r0 = r8.getData()
                    java.util.List r0 = r0.getItems()
                    java.lang.String r1 = "legalHistory"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    boolean r1 = r0.isEmpty()
                    r2 = 1
                    r1 = r1 ^ r2
                    if (r1 == 0) goto L81
                    java.lang.String r1 = r2
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
                    goto L8e
                L73:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r0 = r1
                    java.lang.String r8 = r8.getMessage()
                    java.lang.Boolean r1 = java.lang.Boolean.FALSE
                    java.lang.Boolean r2 = java.lang.Boolean.TRUE
                    r0.invoke(r8, r1, r2)
                    goto L8e
                L81:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r0 = r1
                    java.lang.String r8 = r8.getMessage()
                    java.lang.Boolean r1 = java.lang.Boolean.FALSE
                    java.lang.Boolean r2 = java.lang.Boolean.TRUE
                    r0.invoke(r8, r1, r2)
                L8e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$checkNavigationDisclaimerAcceptance$1.onSuccess(com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes):void");
            }
        }, NavigationConstants.MAP_NAV_DISCLAIMER);
    }

    public final void checkStoragePermission() {
        getMListener().onPermissionSuccess(PermissionType.STORAGE);
    }

    public final ActivityGPSSample d(ArrayList<ActivityGPSSample> arrayList, long j, int i) {
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

    public final void deleteActivityDataFromWatch() {
        DeleteActivityDataRequest deleteActivityDataRequest = new DeleteActivityDataRequest();
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().getData(deleteActivityDataRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$deleteActivityDataFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = ViewModelActivityDashboardNew.this.e;
                LogHelper.d(str, "activity data deletion failed");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                str = ViewModelActivityDashboardNew.this.e;
                LogHelper.d(str, "activity data deleted");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void deleteGpsDataFromWatch() {
        DeleteGpsDataRequest deleteGpsDataRequest = new DeleteGpsDataRequest();
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().getData(deleteGpsDataRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$deleteGpsDataFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = ViewModelActivityDashboardNew.this.e;
                LogHelper.d(str, "gps data deletion failed");
                Context context2 = ViewModelActivityDashboardNew.this.l;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context2 = null;
                }
                if (BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isDeleteActivityDataSupported()) {
                    ViewModelActivityDashboardNew.this.deleteActivityDataFromWatch();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                str = ViewModelActivityDashboardNew.this.e;
                LogHelper.d(str, "gps data deleted");
                Context context2 = ViewModelActivityDashboardNew.this.l;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context2 = null;
                }
                if (BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isDeleteActivityDataSupported()) {
                    ViewModelActivityDashboardNew.this.deleteActivityDataFromWatch();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final ActivityHeartRateSample e(ArrayList<ActivityHeartRateSample> arrayList, long j, int i) {
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

    public final ActivityStepsSample f(ArrayList<ActivityStepsSample> arrayList, long j, int i) {
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

    @Nullable
    public final String getAgpsFilePath() {
        return this.o;
    }

    public final int getBatteryPercentageForMatrix$app_prodRelease(int i) {
        return (i / 10) * 10;
    }

    public final int getBatteryPercentageForVertex$app_prodRelease(int i) {
        return i;
    }

    @NotNull
    public final MutableLiveData<Boolean> getBatteryRequestFailure() {
        return this.n;
    }

    @NotNull
    public final MutableLiveData<String> getBatteryUpdate() {
        return this.m;
    }

    public final void getConfigUrlsFromServer() {
        LogHelper.d(this.e, "getConfigUrlsFromServer: called");
        CoveOnboarding.getV2RemoteConfiguration("1", new CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getConfigUrlsFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            /* JADX WARN: Type inference failed for: r3v17, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r3v2, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r3v20, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r3v5, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r3v8, types: [T, java.util.ArrayList] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SRemoteConfigResponse sRemoteConfigResponse) {
                String str;
                List<SRemoteConfigResponse.DataBean.LegalBean.Doc> doc;
                String apiKey;
                str = ViewModelActivityDashboardNew.this.e;
                Context context = null;
                LogHelper.d(str, sRemoteConfigResponse != null ? sRemoteConfigResponse.toString() : null);
                if (sRemoteConfigResponse != null) {
                    SRemoteConfigResponse.DataBean data = sRemoteConfigResponse.getData();
                    if ((data != null ? data.getFitnessPlan() : null) != null) {
                        SRemoteConfigResponse.DataBean.FitnessPlanBean.WebViewUrlBean webViewUrl = sRemoteConfigResponse.getData().getFitnessPlan().getWebViewUrl();
                        UserDataManager.getInstance(ViewModelActivityDashboardNew.this.getApplication()).savePlanConfigUrlsToPref(new WebViewUrlBean(webViewUrl.getOnboarding(), webViewUrl.getBrowsePlan(), webViewUrl.getUserPlanHistory()));
                    }
                    SRemoteConfigResponse.DataBean.GuardianBean guardian = sRemoteConfigResponse.getData().getGuardian();
                    if (guardian != null) {
                        Context context2 = ViewModelActivityDashboardNew.this.l;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context2 = null;
                        }
                        UserDataManager.getInstance(context2).setEnableGuardianFeature(Boolean.valueOf(guardian.isEnable()));
                    }
                    if (sRemoteConfigResponse.getData().getTrigger() != null) {
                        sRemoteConfigResponse.getData().getTrigger().getTemperature();
                    }
                    if (sRemoteConfigResponse.getData().getSpo2Aiml() != null && sRemoteConfigResponse.getData().getSpo2Aiml().getRemeasure() != null) {
                        SRemoteConfigResponse.DataBean.Spo2AimlBean.RemeasureBean remeasure = sRemoteConfigResponse.getData().getSpo2Aiml().getRemeasure();
                        Context context3 = ViewModelActivityDashboardNew.this.l;
                        if (context3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context3 = null;
                        }
                        Spo2DataManager spo2DataManager = Spo2DataManager.getInstance(context3);
                        Integer maxRetry = remeasure.getMaxRetry();
                        Intrinsics.checkNotNullExpressionValue(maxRetry, "remeasure.maxRetry");
                        spo2DataManager.setConsecutiveLowCountLimit(maxRetry.intValue());
                        Context context4 = ViewModelActivityDashboardNew.this.l;
                        if (context4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context4 = null;
                        }
                        Spo2DataManager spo2DataManager2 = Spo2DataManager.getInstance(context4);
                        Integer retryTimeout = remeasure.getRetryTimeout();
                        Intrinsics.checkNotNullExpressionValue(retryTimeout, "remeasure.retryTimeout");
                        spo2DataManager2.setMinTimeOut(retryTimeout.intValue());
                        Context context5 = ViewModelActivityDashboardNew.this.l;
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
                        Context context6 = ViewModelActivityDashboardNew.this.l;
                        if (context6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context6 = null;
                        }
                        UserDataManager.getInstance(context6).saveIotUserHeartbeatApiFrequency(sRemoteConfigResponse.getData().getApiFrequency().getPostIotUserHeartbeat());
                    }
                    if (sRemoteConfigResponse.getData().getOpenWeatherMap() != null && sRemoteConfigResponse.getData().getOpenWeatherMap().getApiKey() != null && !AppUtils.isEmpty(sRemoteConfigResponse.getData().getOpenWeatherMap().getApiKey()) && (apiKey = sRemoteConfigResponse.getData().getOpenWeatherMap().getApiKey()) != null) {
                        WeatherApiCallsManager.Companion companion = WeatherApiCallsManager.Companion;
                        Context context7 = ViewModelActivityDashboardNew.this.l;
                        if (context7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context7 = null;
                        }
                        if (!companion.getInstance(context7).isInitialized()) {
                            Context context8 = ViewModelActivityDashboardNew.this.l;
                            if (context8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                context8 = null;
                            }
                            companion.getInstance(context8).initWeatherApiClient(apiKey, WeatherAPIType.OPEN_WEATHER_MAP);
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
                        Context context9 = ViewModelActivityDashboardNew.this.l;
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
                        Context context10 = ViewModelActivityDashboardNew.this.l;
                        if (context10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context10 = null;
                        }
                        SessionManager.getInstance(context10).saveRespiratoryRateRemoteConfig(respiratoryRateRemoteConfiguration);
                    }
                    if (sRemoteConfigResponse.getData().getRefsBean() != null) {
                        if (sRemoteConfigResponse.getData().getRefsBean().getEastapexActivities() != null) {
                            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                            objectRef.element = new ArrayList();
                            OkHttpClient okHttpClient = new OkHttpClient();
                            Request.Builder builder = new Request.Builder();
                            String eastapexActivities = sRemoteConfigResponse.getData().getRefsBean().getEastapexActivities();
                            Intrinsics.checkNotNullExpressionValue(eastapexActivities, "`object`.data.refsBean.eastapexActivities");
                            Call newCall = okHttpClient.newCall(builder.url(eastapexActivities).build());
                            final ViewModelActivityDashboardNew viewModelActivityDashboardNew = ViewModelActivityDashboardNew.this;
                            newCall.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getConfigUrlsFromServer$1$onSuccess$1
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r2v8, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    if (response.body() != null) {
                                        ResponseBody body = response.body();
                                        Intrinsics.checkNotNull(body);
                                        Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                        Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef2 = objectRef;
                                        List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>");
                                        objectRef2.element = (ArrayList) activities;
                                        LogHelper.d("eastapexActivitiesList", objectRef.element.toString());
                                        Context context11 = viewModelActivityDashboardNew.l;
                                        if (context11 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                            context11 = null;
                                        }
                                        new PreferenceManager(context11).saveEastApexActivityIcons(objectRef.element);
                                    }
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getRuggedActivities() != null) {
                            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            objectRef2.element = new ArrayList();
                            OkHttpClient okHttpClient2 = new OkHttpClient();
                            Request.Builder builder2 = new Request.Builder();
                            String ruggedActivities = sRemoteConfigResponse.getData().getRefsBean().getRuggedActivities();
                            Intrinsics.checkNotNullExpressionValue(ruggedActivities, "`object`.data.refsBean.ruggedActivities");
                            Call newCall2 = okHttpClient2.newCall(builder2.url(ruggedActivities).build());
                            final ViewModelActivityDashboardNew viewModelActivityDashboardNew2 = ViewModelActivityDashboardNew.this;
                            newCall2.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getConfigUrlsFromServer$1$onSuccess$2
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r2v8, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    if (response.body() != null) {
                                        ResponseBody body = response.body();
                                        Intrinsics.checkNotNull(body);
                                        Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                        Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef3 = objectRef2;
                                        List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>");
                                        objectRef3.element = (ArrayList) activities;
                                        LogHelper.d("ruggedActivitiesList", objectRef2.element.toString());
                                        Context context11 = viewModelActivityDashboardNew2.l;
                                        if (context11 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                            context11 = null;
                                        }
                                        new PreferenceManager(context11).saveRuggedActivityIcons(objectRef2.element);
                                    }
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getSupportBean() != null) {
                            if (sRemoteConfigResponse.getData().getSupportBean().getTroubleshootUrl() != null) {
                                Context context11 = ViewModelActivityDashboardNew.this.l;
                                if (context11 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                    context11 = null;
                                }
                                SessionManager.getInstance(context11).setTroubleshootUrl(sRemoteConfigResponse.getData().getSupportBean().getTroubleshootUrl());
                            }
                            if (sRemoteConfigResponse.getData().getSupportBean().getFaqUrl() != null) {
                                Context context12 = ViewModelActivityDashboardNew.this.l;
                                if (context12 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                    context12 = null;
                                }
                                SessionManager.getInstance(context12).setFAQUrl(sRemoteConfigResponse.getData().getSupportBean().getFaqUrl());
                            }
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getTouchelxActivities() != null) {
                            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                            objectRef3.element = new ArrayList();
                            OkHttpClient okHttpClient3 = new OkHttpClient();
                            Request.Builder builder3 = new Request.Builder();
                            String touchelxActivities = sRemoteConfigResponse.getData().getRefsBean().getTouchelxActivities();
                            Intrinsics.checkNotNullExpressionValue(touchelxActivities, "`object`.data.refsBean.touchelxActivities");
                            Call newCall3 = okHttpClient3.newCall(builder3.url(touchelxActivities).build());
                            final ViewModelActivityDashboardNew viewModelActivityDashboardNew3 = ViewModelActivityDashboardNew.this;
                            newCall3.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getConfigUrlsFromServer$1$onSuccess$3
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r2v7, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    ResponseBody body = response.body();
                                    Intrinsics.checkNotNull(body);
                                    Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                    Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef4 = objectRef3;
                                    List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                    Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>");
                                    objectRef4.element = (ArrayList) activities;
                                    LogHelper.d("touchActivitiesList", objectRef3.element.toString());
                                    Context context13 = viewModelActivityDashboardNew3.l;
                                    if (context13 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                        context13 = null;
                                    }
                                    new PreferenceManager(context13).saveTouchActivityIcons(objectRef3.element);
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getIdoActivities() != null) {
                            final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                            objectRef4.element = new ArrayList();
                            OkHttpClient okHttpClient4 = new OkHttpClient();
                            Request.Builder builder4 = new Request.Builder();
                            String idoActivities = sRemoteConfigResponse.getData().getRefsBean().getIdoActivities();
                            Intrinsics.checkNotNullExpressionValue(idoActivities, "`object`.data.refsBean.idoActivities");
                            Call newCall4 = okHttpClient4.newCall(builder4.url(idoActivities).build());
                            final ViewModelActivityDashboardNew viewModelActivityDashboardNew4 = ViewModelActivityDashboardNew.this;
                            newCall4.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getConfigUrlsFromServer$1$onSuccess$4
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r2v7, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    ResponseBody body = response.body();
                                    Intrinsics.checkNotNull(body);
                                    Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                    Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef5 = objectRef4;
                                    List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                    Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>");
                                    objectRef5.element = (ArrayList) activities;
                                    LogHelper.d("idoActivitiesList", objectRef4.element.toString());
                                    Context context13 = viewModelActivityDashboardNew4.l;
                                    if (context13 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                        context13 = null;
                                    }
                                    new PreferenceManager(context13).saveIDOActivityIcons(objectRef4.element);
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getRefsBean().getSmaActivities() != null) {
                            final Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                            objectRef5.element = new ArrayList();
                            OkHttpClient okHttpClient5 = new OkHttpClient();
                            Request.Builder builder5 = new Request.Builder();
                            String smaActivities = sRemoteConfigResponse.getData().getRefsBean().getSmaActivities();
                            Intrinsics.checkNotNullExpressionValue(smaActivities, "`object`.data.refsBean.smaActivities");
                            Call newCall5 = okHttpClient5.newCall(builder5.url(smaActivities).build());
                            final ViewModelActivityDashboardNew viewModelActivityDashboardNew5 = ViewModelActivityDashboardNew.this;
                            newCall5.enqueue(new Callback() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getConfigUrlsFromServer$1$onSuccess$5
                                @Override // okhttp3.Callback
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                }

                                /* JADX WARN: Type inference failed for: r2v8, types: [T, java.util.ArrayList] */
                                @Override // okhttp3.Callback
                                public void onResponse(@NotNull Call call, @NotNull Response response) {
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    if (response.body() != null) {
                                        ResponseBody body = response.body();
                                        Intrinsics.checkNotNull(body);
                                        Object fromJson = new Gson().fromJson(body.string(), (Class<Object>) ActivityIcons.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ctivityIcons::class.java)");
                                        Ref.ObjectRef<ArrayList<ActivitiesItem>> objectRef6 = objectRef5;
                                        List<ActivitiesItem> activities = ((ActivityIcons) fromJson).getActivities();
                                        Intrinsics.checkNotNull(activities, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.models.ActivitiesItem>");
                                        objectRef6.element = (ArrayList) activities;
                                        LogHelper.d("smaActivitiesList", objectRef5.element.toString());
                                        Context context13 = viewModelActivityDashboardNew5.l;
                                        if (context13 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                            context13 = null;
                                        }
                                        new PreferenceManager(context13).saveSMAActivityIcons(objectRef5.element);
                                    }
                                }
                            });
                        }
                        if (sRemoteConfigResponse.getData().getLegalBean() == null || (doc = sRemoteConfigResponse.getData().getLegalBean().getDoc()) == null) {
                            return;
                        }
                        int size = doc.size();
                        for (int i = 0; i < size; i++) {
                            if (doc.get(i).getType().equals(NavigationConstants.MAP_NAV_DISCLAIMER)) {
                                String version = doc.get(i).getVersion();
                                Intrinsics.checkNotNullExpressionValue(version, "docBean[i].version");
                                String htmlUrl = doc.get(i).getHtmlUrl();
                                Intrinsics.checkNotNullExpressionValue(htmlUrl, "docBean[i].htmlUrl");
                                String dvcText = doc.get(i).getDvcText();
                                Intrinsics.checkNotNullExpressionValue(dvcText, "docBean[i].dvcText");
                                NavigationDisclaimerData navigationDisclaimerData = new NavigationDisclaimerData(version, htmlUrl, dvcText);
                                Context context13 = ViewModelActivityDashboardNew.this.l;
                                if (context13 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                                } else {
                                    context = context13;
                                }
                                SessionManager.getInstance(context).saveNavigationDiscliamerData(new Gson().toJson(navigationDisclaimerData));
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    @Nullable
    public final ContractUpdateHrBpHealthTextListener getContractUpdateHrBpHealthTextListener() {
        return this.i;
    }

    @Nullable
    public final LiveData<List<SleepDataModelForLastNight>> getDailySleepLiveData() {
        return this.d;
    }

    @Nullable
    public final LiveData<DailyWalkData> getDailyStepsLivedata() {
        return this.u;
    }

    public final void getDataPullFromUserRemoteConfigurationApi(@NotNull final Function1<? super UserRemoteConfigResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        CoveOnboarding.getUserRemoteConfiguration(new CoveApiListener<UserRemoteConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getDataPullFromUserRemoteConfigurationApi$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getDataPullFromUserRemoteConfigurationApi$1$onSuccess$1", f = "ViewModelActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ Function1<UserRemoteConfigResponse, Unit> $callback;
                public final /* synthetic */ UserRemoteConfigResponse $p0;
                public int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public a(UserRemoteConfigResponse userRemoteConfigResponse, Function1<? super UserRemoteConfigResponse, Unit> function1, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$p0 = userRemoteConfigResponse;
                    this.$callback = function1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$p0, this.$callback, continuation);
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
                        UserRemoteConfigResponse userRemoteConfigResponse = this.$p0;
                        if (userRemoteConfigResponse != null) {
                            this.$callback.invoke(userRemoteConfigResponse);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                callback.invoke(null);
                str = ViewModelActivityDashboardNew.this.e;
                LogHelper.i(str, "getDataPullFromUserRemoteConfigurationApi failure");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UserRemoteConfigResponse userRemoteConfigResponse) {
                kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(ViewModelActivityDashboardNew.this), Dispatchers.getIO(), null, new a(userRemoteConfigResponse, callback, null), 2, null);
            }
        });
    }

    public final int getDataSyncFailureCount() {
        return this.r;
    }

    @NotNull
    public final EntityManualData getEntityManualData(@NotNull Spo2DeviceType spo2DeviceType, double d, long j, boolean z) {
        Intrinsics.checkNotNullParameter(spo2DeviceType, "spo2DeviceType");
        String userDeviceID = com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserDeviceID();
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        String macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
        EntityManualData entityManualData = new EntityManualData(j, E(spo2DeviceType));
        entityManualData.setSerialNo(macAddress);
        entityManualData.setLevelInterpretation(z);
        entityManualData.setUserDeviceId(userDeviceID);
        entityManualData.setSpo2(d);
        entityManualData.setSyncedWithServer(false);
        return entityManualData;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.google.firebase.remoteconfig.FirebaseRemoteConfig, java.lang.Object] */
    public final void getFitnessChallengeRemoteConfig() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        objectRef.element = firebaseRemoteConfig;
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        ((FirebaseRemoteConfig) objectRef.element).setConfigSettingsAsync(build);
        ((FirebaseRemoteConfig) objectRef.element).fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.dashboard.l1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ViewModelActivityDashboardNew.v(ViewModelActivityDashboardNew.this, objectRef, task);
            }
        });
    }

    public final long getLastCommanmdSentTime() {
        return this.q;
    }

    @Nullable
    public final LiveData<DailyRespiratoryRateEntity> getLatestRespiratoryRateLiveData() {
        return this.w;
    }

    @Nullable
    public final LifecycleOwner getMLifecycleOwner() {
        return this.j;
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
        return this.p;
    }

    @Nullable
    public final SleepScoreData getSleepScoreCurrentDate() {
        SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        SleepScoreRepository singletonHolder = companion.getInstance(context);
        String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …-MM-dd\"\n                )");
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        return singletonHolder.getSleepScoreData(formatDate, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
    }

    @Nullable
    public final LiveData<EntityManualData> getSpO2RecordLivedata() {
        return this.v;
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

    public final void getSportsDisclaimerConsentApi() {
        CoveUserAppSettings.getUserConsent(new CoveApiListener<SGetConsentData, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getSportsDisclaimerConsentApi$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SGetConsentData sGetConsentData) {
                SGetConsentData.DataBean data;
                SGetConsentData.DataBean data2;
                Boolean bool = null;
                if ((sGetConsentData != null ? sGetConsentData.getData() : null) != null) {
                    if (((sGetConsentData == null || (data2 = sGetConsentData.getData()) == null) ? null : data2.getSportsConsent()) != null) {
                        SportsPreference.Companion companion = SportsPreference.Companion;
                        Context context = ViewModelActivityDashboardNew.this.l;
                        if (context == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context = null;
                        }
                        if (sGetConsentData != null && (data = sGetConsentData.getData()) != null) {
                            bool = data.getSportsConsent();
                        }
                        Intrinsics.checkNotNull(bool);
                        companion.saveDisclaimerAccepted(context, bool.booleanValue());
                    }
                }
            }
        });
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

    @NotNull
    public final ViewModelListener getViewModelListener() {
        ViewModelListener viewModelListener = this.viewModelListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelListener");
        return null;
    }

    @Nullable
    public final DailyWalkData getWalkDataCurrentDate() {
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WalkDBRead walkDBRead = WalkDBRead.getInstance(context);
        String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        return walkDBRead.getDailyWalkDataWithDate(formatDate, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
    }

    public final void h() {
        this.h.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.n1
            @Override // java.lang.Runnable
            public final void run() {
                ViewModelActivityDashboardNew.i(ViewModelActivityDashboardNew.this);
            }
        }, this.f * 1000);
    }

    public final void isShowSensAI(@NotNull final Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        if (BleApiManager.getInstance(mContext).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED && BleApiManager.getInstance(mContext).getBleApi().getDeviceSupportedFeatures().isSensAISupported()) {
            BleApiManager.getInstance(mContext).getBleApi().setUserSettings(new SetSensAIActivityConfigRequest.Builder().setSensAiActivityConfigRequest(true).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$isShowSensAI$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = this.e;
                    Log.d(str, error.toString());
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew.isShowSubscribtionDialog(android.content.Context):boolean");
    }

    public final String j(ActivityModeSummaryResponse activityModeSummaryResponse, boolean z) {
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

    public final ArrayList<BadmintonSample> l(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        badmintonSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final void loadBpData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        if (this.j != null) {
            BPRepository.Companion companion = BPRepository.Companion;
            Context context = this.l;
            Context context2 = null;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            BPRepository singletonHolder = companion.getInstance(context);
            Context context3 = this.l;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            LiveData<EntityDailyBp> dailyDataWithoutFlowValidator = singletonHolder.getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
            LifecycleOwner lifecycleOwner = this.j;
            Intrinsics.checkNotNull(lifecycleOwner);
            dailyDataWithoutFlowValidator.observe(lifecycleOwner, new Observer<EntityDailyBp>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$loadBpData$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@androidx.annotation.Nullable @Nullable EntityDailyBp entityDailyBp) {
                    if (ViewModelActivityDashboardNew.this.getContractUpdateHrBpHealthTextListener() != null) {
                        ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener = ViewModelActivityDashboardNew.this.getContractUpdateHrBpHealthTextListener();
                        Intrinsics.checkNotNull(contractUpdateHrBpHealthTextListener);
                        contractUpdateHrBpHealthTextListener.updateDailyLevelBpData(entityDailyBp);
                    }
                }
            });
        }
    }

    public final void loadHrData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        if (this.j != null) {
            HeartRateRepository.Companion companion = HeartRateRepository.Companion;
            Context context = this.l;
            Context context2 = null;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            HeartRateRepository singletonHolder = companion.getInstance(context);
            Context context3 = this.l;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            LiveData<EntityDailyHeartRateData> dailyDataWithoutFlowValidator = singletonHolder.getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(context2).getBleApi().getMacAddress());
            LifecycleOwner lifecycleOwner = this.j;
            Intrinsics.checkNotNull(lifecycleOwner);
            dailyDataWithoutFlowValidator.observe(lifecycleOwner, new Observer<EntityDailyHeartRateData>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$loadHrData$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(@androidx.annotation.Nullable @Nullable EntityDailyHeartRateData entityDailyHeartRateData) {
                    if (ViewModelActivityDashboardNew.this.getContractUpdateHrBpHealthTextListener() != null) {
                        ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener = ViewModelActivityDashboardNew.this.getContractUpdateHrBpHealthTextListener();
                        Intrinsics.checkNotNull(contractUpdateHrBpHealthTextListener);
                        contractUpdateHrBpHealthTextListener.updateDailyLevelHrData(entityDailyHeartRateData);
                    }
                }
            });
        }
    }

    public final ArrayList<BasketBallSample> m(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        basketBallSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final String n(Integer num, Integer num2, String str, long j, long j2, int i) {
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        String userDeviceID = com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserDeviceID();
        if (kotlin.text.m.equals(str, "PHYSICAL_ACTIVITY", true)) {
            PhysicalActivityRepository.Companion companion = PhysicalActivityRepository.Companion;
            Context context2 = this.l;
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

    public final ArrayList<ClimbingSample> o(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        climbingSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    climbingSample.setSampleData(sampleData);
                    arrayList4.add(climbingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void onResume() {
        LogHelper.d(this.e, "onResume");
        WalkRepository.Companion companion = WalkRepository.Companion;
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        WalkRepository singletonHolder = companion.getInstance(context);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        this.u = singletonHolder.getDailyDataWithoutFlowValidator(calendar, BleApiManager.getInstance(context3).getBleApi().getMacAddress());
        Context context4 = this.l;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        if (BleApiManager.getInstance(context4).getBleApi().getDeviceSupportedFeatures().isSleepSupported()) {
            SleepRepository.Companion companion2 = SleepRepository.Companion;
            Context context5 = this.l;
            if (context5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context5 = null;
            }
            SleepRepository singletonHolder2 = companion2.getInstance(context5);
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
            Context context6 = this.l;
            if (context6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context6 = null;
            }
            this.d = singletonHolder2.getHourlyDataWithoutFlowValidator(calendar2, BleApiManager.getInstance(context6).getBleApi().getMacAddress());
        }
        Context context7 = this.l;
        if (context7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context7 = null;
        }
        if (BleApiManager.getInstance(context7).getBleApi().getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
            ManualDataRepository.Companion companion3 = ManualDataRepository.Companion;
            Context context8 = this.l;
            if (context8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context8 = null;
            }
            ManualDataRepository singletonHolder3 = companion3.getInstance(context8);
            Context context9 = this.l;
            if (context9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context9 = null;
            }
            this.v = singletonHolder3.getLastMeasuredSpo2(BleApiManager.getInstance(context9).getBleApi().getMacAddress());
        }
        Context context10 = this.l;
        if (context10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context10 = null;
        }
        if (BleApiManager.getInstance(context10).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
            RespiratoryRateRepository.Companion companion4 = RespiratoryRateRepository.Companion;
            Context context11 = this.l;
            if (context11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context11 = null;
            }
            RespiratoryRateRepository singletonHolder4 = companion4.getInstance(context11);
            Context context12 = this.l;
            if (context12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context12;
            }
            this.w = singletonHolder4.getLatestRespiratoryRateData(BleApiManager.getInstance(context2).getBleApi().getMacAddress());
        }
    }

    public final void onStart() {
        LogHelper.d(this.e, "onStart");
    }

    public final void p(final WeatherResultListener weatherResultListener) {
        final String[] strArr = new String[1];
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(mContext)");
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        if (ContextCompat.checkSelfPermission(context2, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getCurrentLocation$1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(@Nullable Location location) {
                    String str;
                    String str2;
                    if (location == null) {
                        str = ViewModelActivityDashboardNew.this.e;
                        LogHelper.d(str, "getLastLocationLatLng onSuccess() location is NULL====");
                        WeatherResultListener weatherResultListener2 = weatherResultListener;
                        if (weatherResultListener2 != null) {
                            weatherResultListener2.onFailure("Unable to fetch location");
                            return;
                        }
                        return;
                    }
                    WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
                    Context context4 = ViewModelActivityDashboardNew.this.l;
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
                    str2 = ViewModelActivityDashboardNew.this.e;
                    LogHelper.d(str2, "Last Location ==== " + location.getLatitude() + ", " + location.getLongitude());
                    ViewModelActivityDashboardNew.this.J(location.getLatitude(), location.getLongitude(), weatherResultListener);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getCurrentLocation$2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(@NotNull Exception e) {
                    String str;
                    Intrinsics.checkNotNullParameter(e, "e");
                    str = ViewModelActivityDashboardNew.this.e;
                    LogHelper.d(str, "onFailure() in getLastLocation() ====");
                    WeatherResultListener weatherResultListener2 = weatherResultListener;
                    if (weatherResultListener2 != null) {
                        weatherResultListener2.onFailure("Unable to fetch location");
                    }
                }
            });
        } else if (weatherResultListener != null) {
            weatherResultListener.onFailure("Location permission required");
        }
    }

    public final void populateDeviceCapabilityConfig(@NotNull DeviceRemoteConfig deviceRemoteConfig) {
        Intrinsics.checkNotNullParameter(deviceRemoteConfig, "deviceRemoteConfig");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new b(deviceRemoteConfig, this, null), 2, null);
    }

    public final ArrayList<CyclingSample> q(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        cyclingSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    cyclingSample.setSampleData(sampleData);
                    arrayList4.add(cyclingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<DanceSample> r(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        danceSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final void registerConnectionStatus(@NotNull LifecycleOwner lifecycleOwner, @NotNull Observer<ConnectionStatus> connectionStateListener) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(connectionStateListener, "connectionStateListener");
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        BleApiManager.getInstance(context).getBleApi().registerConnectionStatus().observe(lifecycleOwner, connectionStateListener);
    }

    public final ArrayList<EllipticalSample> s(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        ellipticalSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final void saveFitnessConfig(@NotNull final String selectedWeight) {
        Intrinsics.checkNotNullParameter(selectedWeight, "selectedWeight");
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        ProfileRepository profileRepository = ProfileRepository.getInstance();
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        int i = profileRepository.getLatestProfileData(context3).walkStrideLength;
        ProfileRepository profileRepository2 = ProfileRepository.getInstance();
        Context context4 = this.l;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        int i2 = profileRepository2.getLatestProfileData(context4).runStrideLength;
        FitnessConfigRequest fitnessConfigRequest = new FitnessConfigRequest(userDetails.getHeight(), selectedWeight, i > 0 ? String.valueOf(i) : null, i2 > 0 ? String.valueOf(i2) : null);
        Context context5 = this.l;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context5 = null;
        }
        if (AppUtils.isNetConnected(context5)) {
            FitnessConfigApi.saveFitnessConfigData(fitnessConfigRequest, new CoveApiListener<FitnessConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$saveFitnessConfig$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable FitnessConfigResponse fitnessConfigResponse) {
                    Context context6 = ViewModelActivityDashboardNew.this.l;
                    Context context7 = null;
                    if (context6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        context6 = null;
                    }
                    Context context8 = ViewModelActivityDashboardNew.this.l;
                    if (context8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context7 = context8;
                    }
                    Toast.makeText(context6, context7.getResources().getString(R.string.weight_updated_successfully), 0).show();
                    ViewModelActivityDashboardNew.this.updateLoginSession(selectedWeight);
                }
            });
            return;
        }
        String str = this.e;
        Context context6 = this.l;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context6;
        }
        LogHelper.e(str, context2.getString(R.string.noconnection));
    }

    public final void saveSPO2DataToServer() {
        SPO2Repository.Companion companion = SPO2Repository.Companion;
        Context context = this.l;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        final List<SPO2Record> unSyncedData = companion.getInstance(context).getUnSyncedData();
        if (AppUtils.isEmpty(unSyncedData)) {
            return;
        }
        FitnessRecordApiManager.saveFitnessRecords(SPO2Formator.getSPO2ServerModels(unSyncedData), new CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$saveSPO2DataToServer$1
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
                        Context context2 = this.l;
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

    public final void saveSPO2Value(double d, @NotNull Spo2DeviceType spo2DeviceType, boolean z) {
        Intrinsics.checkNotNullParameter(spo2DeviceType, "spo2DeviceType");
        SPO2Record sPO2Record = new SPO2Record();
        sPO2Record.timeStamp = System.currentTimeMillis();
        sPO2Record.value = d;
        sPO2Record.isLevelIntepreTation = z;
        sPO2Record.date = AppUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        sPO2Record.timeZoneOffSet = PayUtils.INSTANCE.getTimeZoneOffset();
        sPO2Record.isSyncedToServer = false;
        SPO2Repository.Companion companion = SPO2Repository.Companion;
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        companion.getInstance(context).insert(sPO2Record);
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context3;
        }
        ManualDataDBWrite.getInstance(context2).insert(getEntityManualData(spo2DeviceType, d, System.currentTimeMillis(), z));
        saveSPO2DataToServer();
    }

    public final void setAgpsFilePath(@Nullable String str) {
        this.o = str;
    }

    public final void setBatteryRequestFailure(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.n = mutableLiveData;
    }

    public final void setBatteryUpdate(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.m = mutableLiveData;
    }

    public final void setContractUpdateHrBpHealthTextListener(@Nullable ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener) {
        this.i = contractUpdateHrBpHealthTextListener;
    }

    public final void setDailySleepLiveData(@Nullable LiveData<List<SleepDataModelForLastNight>> liveData) {
        this.d = liveData;
    }

    public final void setDailyStepsLivedata(@Nullable LiveData<DailyWalkData> liveData) {
        this.u = liveData;
    }

    public final void setDataSyncFailureCount(int i) {
        this.r = i;
    }

    public final void setLastCommanmdSentTime(long j) {
        this.q = j;
    }

    public final void setLatestRespiratoryRateLiveData(@Nullable LiveData<DailyRespiratoryRateEntity> liveData) {
        this.w = liveData;
    }

    public final void setMLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        this.j = lifecycleOwner;
    }

    public final void setMListener(@NotNull PermissionListener permissionListener) {
        Intrinsics.checkNotNullParameter(permissionListener, "<set-?>");
        this.mListener = permissionListener;
    }

    public final void setMatchStateIndex(int i) {
        this.p = i;
    }

    public final void setSpO2RecordLivedata(@Nullable LiveData<EntityManualData> liveData) {
        this.v = liveData;
    }

    public final void setSyncTroubleShoutListener(@NotNull SyncTroubleShoutListener syncTroubleShoutListener) {
        Intrinsics.checkNotNullParameter(syncTroubleShoutListener, "<set-?>");
        this.syncTroubleShoutListener = syncTroubleShoutListener;
    }

    public final void setViewModelListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.viewModelListener = viewModelListener;
    }

    public final void startTimer() {
        CountDownTimer countDownTimer = this.s;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.s = null;
        }
        if (this.s == null) {
            this.s = new CountDownTimer() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$startTimer$1
                {
                    super(180000L, 1000L);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    CountDownTimer countDownTimer2;
                    countDownTimer2 = ViewModelActivityDashboardNew.this.s;
                    Intrinsics.checkNotNull(countDownTimer2);
                    countDownTimer2.cancel();
                    ViewModelActivityDashboardNew.this.s = null;
                    ViewModelActivityDashboardNew.this.stopTimer();
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }
            };
        }
        CountDownTimer countDownTimer2 = this.s;
        Intrinsics.checkNotNull(countDownTimer2);
        countDownTimer2.start();
    }

    public final void stopSyncTimer() {
        CountDownTimer countDownTimer = this.t;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.t = null;
        }
    }

    public final void stopTimer() {
        CountDownTimer countDownTimer = this.s;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.s = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object t(com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse r24, kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession> r25) {
        /*
            Method dump skipped, instructions count: 1362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew.t(com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void u(String str) {
        new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$getFitnessChallengeFromFirebase$1
            @Override // okhttp3.Callback
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // okhttp3.Callback
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                new Gson();
                ResponseBody body = response.body();
                Intrinsics.checkNotNull(body);
                String string = body.string();
                Log.i("Test", "" + string);
            }
        });
    }

    public final void updateHeightWeightToBand(@NotNull final String selectedWeight) {
        Intrinsics.checkNotNullParameter(selectedWeight, "selectedWeight");
        Context context = this.l;
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
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        builder.setStride(profileRepository.getLatestProfileData(context3).walkStrideLength);
        ProfileRepository profileRepository2 = ProfileRepository.getInstance();
        Context context4 = this.l;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context4 = null;
        }
        builder.setRunStride(profileRepository2.getLatestProfileData(context4).runStrideLength);
        Context context5 = this.l;
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
        Context context6 = this.l;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context6;
        }
        BleApiManager.getInstance(context2).getBleApi().setUserSettings(builder2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$updateHeightWeightToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = ViewModelActivityDashboardNew.this.e;
                LogHelper.d(str, "Failed to save Fitness Info");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                str = ViewModelActivityDashboardNew.this.e;
                LogHelper.d(str, "Fitness Info saved successfully");
                ViewModelActivityDashboardNew.this.saveFitnessConfig(selectedWeight);
            }
        });
    }

    public final void updateLoginSession(@NotNull String weight) {
        Intrinsics.checkNotNullParameter(weight, "weight");
        Context context = this.l;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        userDetails.setWeight(weight);
        Context context3 = this.l;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        SessionManager.getInstance(context3).createLoginSession(userDetails);
        ContractUpdateHrBpHealthTextListener contractUpdateHrBpHealthTextListener = this.i;
        Intrinsics.checkNotNull(contractUpdateHrBpHealthTextListener);
        contractUpdateHrBpHealthTextListener.onFitnessHeightWeightUpdate(true);
        ProfileRepository profileRepository = ProfileRepository.getInstance();
        Context context4 = this.l;
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
            Context context = this.l;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            context.unregisterReceiver(this.g);
            this.g = null;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        SessionManager sessionManager2 = this.k;
        if (sessionManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
            sessionManager2 = null;
        }
        ?? registrationToken = sessionManager2.getRegistrationToken();
        objectRef.element = registrationToken;
        if (AppUtils.isEmpty((String) registrationToken)) {
            Task<String> token = FirebaseMessaging.getInstance().getToken();
            final Function1<String, Unit> function1 = new Function1<String, Unit>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$updateRegistrationToServer$1
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
                    sessionManager3 = this.k;
                    SessionManager sessionManager6 = null;
                    if (sessionManager3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
                        sessionManager3 = null;
                    }
                    sessionManager3.saveRegistrationToken(objectRef.element);
                    sessionManager4 = this.k;
                    if (sessionManager4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
                        sessionManager4 = null;
                    }
                    ProfileData userDetails = sessionManager4.getUserDetails();
                    if ((userDetails != null ? Integer.valueOf(userDetails.getUserId()) : null) != null) {
                        sessionManager5 = this.k;
                        if (sessionManager5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
                        } else {
                            sessionManager6 = sessionManager5;
                        }
                        FCMRegistrationReq fCMRegistrationReq = new FCMRegistrationReq(objectRef.element, String.valueOf(sessionManager6.getUserDetails().getUserId()));
                        final ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
                        CoveOnboarding.sendUserRegistrationTokenToServer(fCMRegistrationReq, new CoveApiListener<FCMRegistrationRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$updateRegistrationToServer$1.1
                            @Override // com.coveiot.coveaccess.CoveApiListener
                            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                                ViewModelActivityDashboardNew.this.M();
                            }

                            @Override // com.coveiot.coveaccess.CoveApiListener
                            public void onSuccess(@NotNull FCMRegistrationRes fcmRegistrationRes) {
                                Intrinsics.checkNotNullParameter(fcmRegistrationRes, "fcmRegistrationRes");
                                if (kotlin.text.m.equals(fcmRegistrationRes.getStatus(), CoveApiConstants.RESPONSE_STATUS_VALUE_ERROR, true)) {
                                    ViewModelActivityDashboardNew.this.M();
                                }
                            }
                        });
                    }
                }
            };
            token.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.dashboard.m1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ViewModelActivityDashboardNew.R(Function1.this, obj);
                }
            });
            return;
        }
        SessionManager sessionManager3 = this.k;
        if (sessionManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
            sessionManager3 = null;
        }
        ProfileData userDetails = sessionManager3.getUserDetails();
        if ((userDetails != null ? Integer.valueOf(userDetails.getUserId()) : null) != null) {
            SessionManager sessionManager4 = this.k;
            if (sessionManager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
            } else {
                sessionManager = sessionManager4;
            }
            CoveOnboarding.sendUserRegistrationTokenToServer(new FCMRegistrationReq((String) objectRef.element, String.valueOf(sessionManager.getUserDetails().getUserId())), new CoveApiListener<FCMRegistrationRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew$updateRegistrationToServer$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                    Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                    ViewModelActivityDashboardNew.this.M();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull FCMRegistrationRes fcmRegistrationRes) {
                    Intrinsics.checkNotNullParameter(fcmRegistrationRes, "fcmRegistrationRes");
                    if (kotlin.text.m.equals(fcmRegistrationRes.getStatus(), CoveApiConstants.RESPONSE_STATUS_VALUE_ERROR, true)) {
                        ViewModelActivityDashboardNew.this.M();
                    }
                }
            });
        }
    }

    public final ArrayList<FootballSample> w(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        footballSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final ArrayList<FreeExerciseSample> x(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
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
                    ActivityStepsSample f = f(arrayList, j4, i);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        freeExerciseSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = e(arrayList2, j4, i);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
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

    public final ArrayList<HikingSample> y(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        hikingSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    hikingSample.setSampleData(sampleData);
                    arrayList4.add(hikingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<MeditationSample> z(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this;
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
                    ActivityStepsSample f = viewModelActivityDashboardNew.f(arrayList, j4, i2);
                    if (f != null) {
                        sampleData.setCalories((float) f.getCalories());
                        sampleData.setDistance((int) f.getDistance());
                        meditationSample.setStepCount(f.getStepsValue());
                    }
                    ActivityHeartRateSample e = viewModelActivityDashboardNew.e(arrayList2, j4, i2);
                    if (e != null) {
                        sampleData.setHr_value(e.getHrValue());
                    }
                    ActivityGPSSample d = viewModelActivityDashboardNew.d(arrayList3, j4, i2);
                    if (d != null) {
                        sampleData.setLatitude(d.getLocation().latitude);
                        sampleData.setLongitude(d.getLocation().longitude);
                    }
                    meditationSample.setSampleData(sampleData);
                    arrayList4.add(meditationSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    viewModelActivityDashboardNew = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }
}
