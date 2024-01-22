package com.coveiot.android.leonardo.dashboard;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.PointerIconCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.activities.ActivityInfo;
import com.coveiot.android.activitymodes.activities.ActivityPlanDetails;
import com.coveiot.android.activitymodes.activities.ActivityPreparationPlan;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutSession;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.eventmodels.OnWorkoutDeviceDisconnected;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.services.WorkoutSessionService;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.IndoorOutdoor;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetLatestHealthDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.ReminderType;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.TemperatureCalibrationDataRequest;
import com.coveiot.android.bleabstract.request.TodaysFitnessDataRequest;
import com.coveiot.android.bleabstract.request.TodaysStepsDataRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse;
import com.coveiot.android.bleabstract.response.HealthData;
import com.coveiot.android.bleabstract.response.TodaysStepsData;
import com.coveiot.android.bleabstract.response.TodaysStepsResponse;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.dashboard2.StatusApiDataHelper;
import com.coveiot.android.dashboard2.listener.ConnectionStatusUpdateListener;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.dashboard2.listener.OnDynamicTabDataChangedListener;
import com.coveiot.android.dashboard2.listener.SyncInterruptionListener;
import com.coveiot.android.dashboard2.listener.SyncTroubleShoutListener;
import com.coveiot.android.dashboard2.listener.WeatherResultListener;
import com.coveiot.android.dashboard2.model.FitnessVitalsType;
import com.coveiot.android.dashboard2.model.ServerDataPullConfigModel;
import com.coveiot.android.dashboard2.model.SyncInterruptionType;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.dashboard2.viewmodel.FitnessChallengeSyncViewModel;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.devicemodels.RSAKeysRemoteConfig;
import com.coveiot.android.dynamictab.sports.model.DashboardModel;
import com.coveiot.android.fitnessbuddies.activities.ActivityManageRequests;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinsWebViewer;
import com.coveiot.android.leonardo.boatcoin.ftu.activities.ActivityBoatCoinsFTU;
import com.coveiot.android.leonardo.dashboard.home.listeners.KeepStateNavigator;
import com.coveiot.android.leonardo.dashboard.utility.ActivityDashboardNewHelper;
import com.coveiot.android.leonardo.dashboard.utility.fragments.SyncTroubleshootFragment;
import com.coveiot.android.leonardo.firebaseservices.model.CustomNotifiacationModel;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.listener.DeviceCapabilityConfigFetchListener;
import com.coveiot.android.leonardo.listener.DeviceRemoteConfigFetchListener;
import com.coveiot.android.leonardo.listener.DeviceRsaKeysFetchListener;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.model.HRLastMeasuredData;
import com.coveiot.android.leonardo.model.HRVLastMeasuredData;
import com.coveiot.android.leonardo.model.Spo2LastMeasuredData;
import com.coveiot.android.leonardo.model.StressLastMeasuredData;
import com.coveiot.android.leonardo.more.AppListHelper;
import com.coveiot.android.leonardo.more.activities.ActivityProfileLanding;
import com.coveiot.android.leonardo.more.viewmodel.AutoHrViewModel;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentScanningDeviceKt;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import com.coveiot.android.leonardo.service.SocialNotificationListenerService;
import com.coveiot.android.leonardo.threshold.model.AlertWebDataModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.FirmwareUpdateActivityFactory;
import com.coveiot.android.leonardo.utils.FirmwareUtils;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.PreferenceNames;
import com.coveiot.android.leonardo.utils.SmaMigrationUtils;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.remotecommandframework.ViewModelFactory;
import com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse;
import com.coveiot.android.remotecommandframework.alexa.model.RcfSyncComplete;
import com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService;
import com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel;
import com.coveiot.android.sos.ActivitySOS;
import com.coveiot.android.sos.ActivitySOSSettings;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel;
import com.coveiot.android.sportsnotification.SoccerSportsServiceNew;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsService;
import com.coveiot.android.sportsnotification.SportsType;
import com.coveiot.android.sportsnotification.model.SpSyncComplete;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogDashboardFirmwareUpdated;
import com.coveiot.android.theme.BottomSheetDialogImageTitleAndMessageWatchFace;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleCustomRedMessageWithClose;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTransparentFullScreen;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.android.theme.LocationPermissionActivity;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.android.theme.model.WatchFactoryReset;
import com.coveiot.android.theme.model.WatchfaceScreenCaller;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.watchfaceui.activities.ActivityWatchFace;
import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.coveiot.android.weeklyreport.utils.SubscriptionDialog;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveHealthStatusApi;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.DeviceConfigurationRes;
import com.coveiot.coveaccess.model.server.SGetHealthStatusRes;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.model.server.UserRemoteConfigResponse;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AlexaDetails;
import com.coveiot.covepreferences.data.AlexaLocale;
import com.coveiot.covepreferences.data.BatteryOptimizationConfig;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.QRTrayCodeData;
import com.coveiot.covepreferences.data.SedentaryReminderData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.eventmodels.CurrentStepsValue;
import com.coveiot.utils.eventmodels.GetCurrentSteps;
import com.coveiot.utils.eventmodels.OnBleCommandError;
import com.coveiot.utils.eventmodels.OnDeviceDisconnected;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Subscribe;
import io.shipbook.shipbooksdk.ShipBook;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityDashboardNew extends BaseActivity implements PermissionListener, Observer<ConnectionStatus>, WorkoutSessionRepository.ActivityDataSyncListner, SyncTroubleShoutListener, FragmentHomeCallBackInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int g0 = 1230;
    public static final int h0 = 1231;
    public static final int i0 = 1232;
    public static final int j0 = 123;
    @NotNull
    public static final String k0 = "EXTRA_IS_USER_FLAGGED";
    @NotNull
    public static String l0 = "EXTRA_API_RESPONSE";
    @Nullable
    public static HRVLastMeasuredData m0;
    @Nullable
    public static StressLastMeasuredData n0;
    @Nullable
    public static HRLastMeasuredData o0;
    @Nullable
    public static Spo2LastMeasuredData p0;
    @Nullable
    public BottomSheetDialogTransparentFullScreen A;
    @Nullable
    public Snackbar B;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle C;
    public AlexaApiCallHandlerViewModel D;
    public AutoHrViewModel E;
    @Nullable
    public SyncInterruptionListener F;
    @Nullable
    public ServerDataPullConfigModel H;
    @Nullable
    public SubscriptionDialog I;
    public int K;
    public boolean L;
    @Nullable
    public LocationManager P;
    public boolean Q;
    @Nullable
    public Snackbar R;
    @Nullable
    public BottomSheetDialogImageTitleMessage S;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage T;
    @Nullable
    public BottomSheetDialogTwoButtons U;
    @Nullable
    public BottomSheetDialogDashboardFirmwareUpdated V;
    public FitnessChallengeSyncViewModel W;
    public SOSSettingsViewmodel X;
    @Nullable
    public SyncTroubleshootFragment Y;
    public Bitmap bitmapCustom;
    public BluetoothAdapter bluetoothAdapter;
    @Nullable
    public AlertDialog c0;
    @Nullable
    public View d0;
    public boolean f0;
    public File imagePath;
    public DataSyncViewModel mDataSyncViewModel;
    public boolean p;
    public boolean q;
    public int t;
    public long u;
    @Nullable
    public BottomSheetDialogTwoButtons v;
    public ViewModelActivityDashboardNew viewmodel;
    public boolean w;
    public boolean x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int r = PointerIconCompat.TYPE_ALIAS;
    @NotNull
    public Handler s = new Handler();
    @NotNull
    public final Handler y = new Handler(Looper.getMainLooper());
    @NotNull
    public final CoroutineScope z = CoroutineScopeKt.MainScope();
    @NotNull
    public List<ConnectionStatusUpdateListener> G = new ArrayList();
    public final String J = ActivityDashboardNew.class.getSimpleName();
    @NotNull
    public final Handler M = new Handler(Looper.getMainLooper());
    public final int N = 125;
    public boolean O = true;
    @NotNull
    public final ActivityDashboardNew$deviceRemoteConfigFetchListener$1 Z = new DeviceRemoteConfigFetchListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$deviceRemoteConfigFetchListener$1

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$deviceRemoteConfigFetchListener$1$onSuccess$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DeviceRemoteConfig $deviceRemoteConfig;
            public int label;
            public final /* synthetic */ ActivityDashboardNew this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(DeviceRemoteConfig deviceRemoteConfig, ActivityDashboardNew activityDashboardNew, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$deviceRemoteConfig = deviceRemoteConfig;
                this.this$0 = activityDashboardNew;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$deviceRemoteConfig, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                DeviceModelBean deviceModelBean;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    DeviceRemoteConfig deviceRemoteConfig = this.$deviceRemoteConfig;
                    if (deviceRemoteConfig != null) {
                        ActivityDashboardNew activityDashboardNew = this.this$0;
                        List<DeviceRemoteConfig.DeviceModelsBean> deviceList = deviceRemoteConfig.getDeviceList();
                        if (!(deviceList == null || deviceList.isEmpty()) && !activityDashboardNew.isFinishing() && (deviceModelBean = SessionManager.getInstance(activityDashboardNew).getDeviceModelBean()) != null) {
                            Iterator<DeviceRemoteConfig.DeviceModelsBean> it = deviceRemoteConfig.getDeviceList().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                DeviceRemoteConfig.DeviceModelsBean next = it.next();
                                if (Intrinsics.areEqual(next.getType(), deviceModelBean.getType())) {
                                    if (next.getIsWatchDiagnosticsSupported() != null) {
                                        deviceModelBean.setIsWatchDiagnosticsSupported(next.getIsWatchDiagnosticsSupported());
                                    }
                                    if (next.getIsDiyWatchFaceSupported() != null) {
                                        deviceModelBean.setIsDiyWatchFaceSupported(next.getIsDiyWatchFaceSupported());
                                    }
                                    if (next.getTapAndPaySupported() != null) {
                                        deviceModelBean.setTapAndPaySupported(next.getTapAndPaySupported());
                                    }
                                    if (next.getRemoteCommandFrameWork() != null && next.getRemoteCommandFrameWork().getEnable() != null) {
                                        deviceModelBean.setRemoteFrameworkSupported(next.getRemoteCommandFrameWork().getEnable());
                                    }
                                    if (next.getMinBatteryPerForWatchFaceUpload() != null) {
                                        deviceModelBean.setMinBatteryPerForWatchFaceUpload(next.getMinBatteryPerForWatchFaceUpload());
                                    } else {
                                        deviceModelBean.setMinBatteryPerForWatchFaceUpload(Boxing.boxInt(5));
                                    }
                                    if (next.getMinTimeGapForShowingNextAutoHRPopup() != null) {
                                        deviceModelBean.setMinTimeGapForShowingNextAutoHRPopup(next.getMinTimeGapForShowingNextAutoHRPopup());
                                    } else {
                                        deviceModelBean.setMinTimeGapForShowingNextAutoHRPopup(Boxing.boxInt(1440));
                                    }
                                    if (next.getDisableAutoHRFor() != null) {
                                        deviceModelBean.setDisableAutoHRFor(next.getDisableAutoHRFor());
                                    } else {
                                        deviceModelBean.setDisableAutoHRFor(CollectionsKt__CollectionsKt.emptyList());
                                    }
                                    if (next.getIsQRCodeOnboardingSupported() != null && Intrinsics.areEqual(next.getIsQRCodeOnboardingSupported(), Boxing.boxBoolean(true))) {
                                        deviceModelBean.setIsQRCodeOnboardingSupported(next.getIsQRCodeOnboardingSupported());
                                    }
                                    if (next.getQrCode() != null) {
                                        SessionManager.getInstance(activityDashboardNew).saveQRTrayCodeDetails(new QRTrayCodeData(next.getQrCode().getMax_allowed(), next.getQrCode().getMax_char_limit(), next.getQrCode().getResolution()));
                                    }
                                    if (next.getIs1kActivitySupported() != null) {
                                        deviceModelBean.setIs1kActivitySupported(next.getIs1kActivitySupported());
                                        Boolean is1kActivitySupported = next.getIs1kActivitySupported();
                                        Intrinsics.checkNotNullExpressionValue(is1kActivitySupported, "dm.is1kActivitySupported");
                                        if (is1kActivitySupported.booleanValue()) {
                                            UserDataManager.getInstance(activityDashboardNew).saveOneKSupported(true);
                                        } else {
                                            UserDataManager.getInstance(activityDashboardNew).saveOneKSupported(false);
                                        }
                                    } else {
                                        UserDataManager.getInstance(activityDashboardNew).saveOneKSupported(false);
                                    }
                                    if (next.getSleepAndEnergyScoreSupported() != null) {
                                        deviceModelBean.setSleepAndEnergyScoreSupported(next.getSleepAndEnergyScoreSupported());
                                        Boolean sleepAndEnergyScoreSupported = next.getSleepAndEnergyScoreSupported();
                                        Intrinsics.checkNotNullExpressionValue(sleepAndEnergyScoreSupported, "dm.sleepAndEnergyScoreSupported");
                                        if (sleepAndEnergyScoreSupported.booleanValue()) {
                                            UserDataManager.getInstance(activityDashboardNew).saveSleepEnergyScoreFeature(true);
                                        } else {
                                            UserDataManager.getInstance(activityDashboardNew).saveSleepEnergyScoreFeature(false);
                                        }
                                    } else {
                                        UserDataManager.getInstance(activityDashboardNew).saveSleepEnergyScoreFeature(false);
                                    }
                                    if (next.getLogoType() != null) {
                                        deviceModelBean.setLogoType(next.getLogoType());
                                    } else {
                                        deviceModelBean.setLogoType(null);
                                    }
                                    SessionManager.getInstance(activityDashboardNew).saveDeviceModelBean(deviceModelBean);
                                    if (next.getRemoteFrameworkSupported() != null) {
                                        Intrinsics.areEqual(next.getRemoteFrameworkSupported(), Boxing.boxBoolean(false));
                                    }
                                }
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @Override // com.coveiot.android.leonardo.listener.DeviceRemoteConfigFetchListener
        public void onFailure(@Nullable String str) {
        }

        @Override // com.coveiot.android.leonardo.listener.DeviceRemoteConfigFetchListener
        public void onSuccess(@Nullable DeviceRemoteConfig deviceRemoteConfig) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityDashboardNew.this), Dispatchers.getIO(), null, new a(deviceRemoteConfig, ActivityDashboardNew.this, null), 2, null);
        }
    };
    @NotNull
    public final ActivityDashboardNew$deviceRsaKeysFetchListener$1 a0 = new DeviceRsaKeysFetchListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$deviceRsaKeysFetchListener$1

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$deviceRsaKeysFetchListener$1$onSuccess$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ RSAKeysRemoteConfig $rsaKeysRemoteConfig;
            public int label;
            public final /* synthetic */ ActivityDashboardNew this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(RSAKeysRemoteConfig rSAKeysRemoteConfig, ActivityDashboardNew activityDashboardNew, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$rsaKeysRemoteConfig = rSAKeysRemoteConfig;
                this.this$0 = activityDashboardNew;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$rsaKeysRemoteConfig, this.this$0, continuation);
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
                    RSAKeysRemoteConfig rSAKeysRemoteConfig = this.$rsaKeysRemoteConfig;
                    if (rSAKeysRemoteConfig != null) {
                        SessionManager sessionManager = SessionManager.getInstance(this.this$0);
                        RSAKeysRemoteConfig.RSAEncryptDecryptKeys rsa_encryptdecrypt_keys = rSAKeysRemoteConfig.getRsa_encryptdecrypt_keys();
                        sessionManager.setRsaPrivateKey(rsa_encryptdecrypt_keys != null ? rsa_encryptdecrypt_keys.getPrivate_key() : null);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @Override // com.coveiot.android.leonardo.listener.DeviceRsaKeysFetchListener
        public void onFailure(@Nullable String str) {
        }

        @Override // com.coveiot.android.leonardo.listener.DeviceRsaKeysFetchListener
        public void onSuccess(@Nullable RSAKeysRemoteConfig rSAKeysRemoteConfig) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityDashboardNew.this), Dispatchers.getIO(), null, new a(rSAKeysRemoteConfig, ActivityDashboardNew.this, null), 2, null);
        }
    };
    @NotNull
    public final ActivityDashboardNew$deviceCapabilityConfigFetchListener$1 b0 = new DeviceCapabilityConfigFetchListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$deviceCapabilityConfigFetchListener$1
        @Override // com.coveiot.android.leonardo.listener.DeviceCapabilityConfigFetchListener
        public void onFailure(@Nullable String str) {
        }

        @Override // com.coveiot.android.leonardo.listener.DeviceCapabilityConfigFetchListener
        public void onSuccess(@Nullable DeviceRemoteConfig deviceRemoteConfig) {
            ViewModelActivityDashboardNew viewmodel;
            if (deviceRemoteConfig == null || (viewmodel = ActivityDashboardNew.this.getViewmodel()) == null) {
                return;
            }
            viewmodel.populateDeviceCapabilityConfig(deviceRemoteConfig);
        }
    };
    @NotNull
    public final ArrayList<DashboardModel> e0 = new ArrayList<>();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getEXTRA_API_RESPONSE() {
            return ActivityDashboardNew.l0;
        }

        @NotNull
        public final String getEXTRA_IS_USER_FLAGGED() {
            return ActivityDashboardNew.k0;
        }

        @Nullable
        public final HRLastMeasuredData getLastMeasuredHR() {
            return ActivityDashboardNew.o0;
        }

        @Nullable
        public final HRVLastMeasuredData getLastMeasuredHrv() {
            return ActivityDashboardNew.m0;
        }

        @Nullable
        public final Spo2LastMeasuredData getLastMeasuredSPO2() {
            return ActivityDashboardNew.p0;
        }

        @Nullable
        public final StressLastMeasuredData getLastMeasuredStress() {
            return ActivityDashboardNew.n0;
        }

        public final int getREQUEST_CODE_ALERT_WEBVIEW() {
            return ActivityDashboardNew.g0;
        }

        public final int getREQUEST_CODE_FROM_DASHBOARD() {
            return ActivityDashboardNew.j0;
        }

        public final int getREQUEST_CODE_FW_UPDATE() {
            return ActivityDashboardNew.i0;
        }

        public final int getREQUEST_CODE_LOCATION_PERMISSION_SCREEN() {
            return ActivityDashboardNew.h0;
        }

        public final void setEXTRA_API_RESPONSE(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            ActivityDashboardNew.l0 = str;
        }

        public final void setLastMeasuredHR(@Nullable HRLastMeasuredData hRLastMeasuredData) {
            ActivityDashboardNew.o0 = hRLastMeasuredData;
        }

        public final void setLastMeasuredHrv(@Nullable HRVLastMeasuredData hRVLastMeasuredData) {
            ActivityDashboardNew.m0 = hRVLastMeasuredData;
        }

        public final void setLastMeasuredSPO2(@Nullable Spo2LastMeasuredData spo2LastMeasuredData) {
            ActivityDashboardNew.p0 = spo2LastMeasuredData;
        }

        public final void setLastMeasuredStress(@Nullable StressLastMeasuredData stressLastMeasuredData) {
            ActivityDashboardNew.n0 = stressLastMeasuredData;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PermissionType.values().length];
            try {
                iArr[PermissionType.STORAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ConnectionStatus.values().length];
            try {
                iArr2[ConnectionStatus.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[ConnectionStatus.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<AppToAppLinkingResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$initObserver$1$1$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0273a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ AppToAppLinkingResponse $linkedDataResponse;
            public final /* synthetic */ ActivityDashboardNew $this_run;
            private /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ ActivityDashboardNew this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0273a(ActivityDashboardNew activityDashboardNew, AppToAppLinkingResponse appToAppLinkingResponse, ActivityDashboardNew activityDashboardNew2, Continuation<? super C0273a> continuation) {
                super(2, continuation);
                this.$this_run = activityDashboardNew;
                this.$linkedDataResponse = appToAppLinkingResponse;
                this.this$0 = activityDashboardNew2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                C0273a c0273a = new C0273a(this.$this_run, this.$linkedDataResponse, this.this$0, continuation);
                c0273a.L$0 = obj;
                return c0273a;
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0273a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x0065, code lost:
                if (kotlin.text.m.equals(r6.getStatus(), "ENABLED", false) != false) goto L15;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Class<com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaActivity> r0 = com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaActivity.class
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    if (r1 != 0) goto Le3
                    kotlin.ResultKt.throwOnFailure(r9)
                    java.lang.Object r9 = r8.L$0
                    kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r9 = r8.$this_run
                    r9.dismissProgress()
                    com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse r9 = r8.$linkedDataResponse
                    boolean r9 = r9.isSuccess()
                    r1 = 2131954922(0x7f130cea, float:1.9546357E38)
                    r2 = 1
                    r3 = 0
                    if (r9 == 0) goto La9
                    com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse r9 = r8.$linkedDataResponse
                    com.coveiot.android.remotecommandframework.alexa.model.EnablementData r9 = r9.getData()
                    if (r9 == 0) goto L96
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r4 = r8.this$0
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r5 = r8.$this_run
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r6 = r9.getAccountLink()
                    if (r6 == 0) goto L79
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r6 = r9.getAccountLink()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                    java.lang.String r6 = r6.getStatus()
                    if (r6 == 0) goto L79
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r6 = r9.getAccountLink()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                    java.lang.String r6 = r6.getStatus()
                    java.lang.String r7 = "LINKED"
                    boolean r6 = kotlin.text.m.equals(r6, r7, r3)
                    if (r6 != 0) goto L67
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r6 = r9.getAccountLink()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                    java.lang.String r6 = r6.getStatus()
                    java.lang.String r7 = "ENABLED"
                    boolean r6 = kotlin.text.m.equals(r6, r7, r3)
                    if (r6 == 0) goto L79
                L67:
                    com.coveiot.covepreferences.SessionManager r0 = com.coveiot.covepreferences.SessionManager.getInstance(r4)
                    r0.setAlexaAccountLinkingStatus(r2)
                    android.content.Intent r0 = new android.content.Intent
                    java.lang.Class<com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaResultActivity> r2 = com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaResultActivity.class
                    r0.<init>(r4, r2)
                    r5.startActivity(r0)
                    goto L88
                L79:
                    com.coveiot.covepreferences.SessionManager r2 = com.coveiot.covepreferences.SessionManager.getInstance(r4)
                    r2.setAlexaAccountLinkingStatus(r3)
                    android.content.Intent r2 = new android.content.Intent
                    r2.<init>(r4, r0)
                    r5.startActivity(r2)
                L88:
                    com.coveiot.covepreferences.SessionManager r0 = com.coveiot.covepreferences.SessionManager.getInstance(r4)
                    java.lang.String r9 = r9.getSkillEnabledFrom()
                    r0.saveAlexaAccountLinkedFrom(r9)
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE
                    goto L97
                L96:
                    r9 = 0
                L97:
                    if (r9 != 0) goto Le0
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r9 = r8.this$0
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r0 = r8.$this_run
                    java.lang.String r0 = r0.getString(r1)
                    android.widget.Toast r9 = android.widget.Toast.makeText(r9, r0, r3)
                    r9.show()
                    goto Le0
                La9:
                    com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse r9 = r8.$linkedDataResponse
                    int r9 = r9.getErrorCode()
                    r4 = 400(0x190, float:5.6E-43)
                    if (r4 > r9) goto Lb8
                    r4 = 500(0x1f4, float:7.0E-43)
                    if (r9 >= r4) goto Lb8
                    goto Lb9
                Lb8:
                    r2 = r3
                Lb9:
                    if (r2 == 0) goto Ld1
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r9 = r8.this$0
                    com.coveiot.covepreferences.SessionManager r9 = com.coveiot.covepreferences.SessionManager.getInstance(r9)
                    r9.setAlexaAccountLinkingStatus(r3)
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r9 = r8.$this_run
                    android.content.Intent r1 = new android.content.Intent
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r2 = r8.this$0
                    r1.<init>(r2, r0)
                    r9.startActivity(r1)
                    goto Le0
                Ld1:
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r9 = r8.this$0
                    com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r0 = r8.$this_run
                    java.lang.String r0 = r0.getString(r1)
                    android.widget.Toast r9 = android.widget.Toast.makeText(r9, r0, r3)
                    r9.show()
                Le0:
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE
                    return r9
                Le3:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew.a.C0273a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(AppToAppLinkingResponse appToAppLinkingResponse) {
            invoke2(appToAppLinkingResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(AppToAppLinkingResponse appToAppLinkingResponse) {
            ActivityDashboardNew activityDashboardNew = ActivityDashboardNew.this;
            if (activityDashboardNew.isFinishing()) {
                return;
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(activityDashboardNew), Dispatchers.getMain(), null, new C0273a(activityDashboardNew, appToAppLinkingResponse, activityDashboardNew, null), 2, null);
        }
    }

    /* loaded from: classes2.dex */
    public static final class b extends Lambda implements Function3<String, Boolean, Boolean, Unit> {
        public b() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool, Boolean bool2) {
            invoke(str, bool.booleanValue(), bool2.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(@Nullable String str, boolean z, boolean z2) {
            if (z2) {
                ActivityDashboardNew.this.dismissProgress();
                if (z) {
                    AppNavigator.Companion.navigateToNavigationScreen(ActivityDashboardNew.this, HexStringBuilder.DEFAULT_SEPARATOR, false);
                    return;
                } else {
                    AppNavigator.Companion.navigateToNavigationScreen(ActivityDashboardNew.this, HexStringBuilder.DEFAULT_SEPARATOR, true);
                    return;
                }
            }
            ActivityDashboardNew.this.dismissProgress();
            Toast.makeText(ActivityDashboardNew.this, str, 0).show();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$updateLookupFromFirebaseRemoteConfig$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PayUtils payUtils = PayUtils.INSTANCE;
                payUtils.getBatteryOptimizationConfig(ActivityDashboardNew.this);
                payUtils.checkCustomMessageConfiguration(ActivityDashboardNew.this);
                DeviceUtils.Companion.getFeatureMapping(ActivityDashboardNew.this);
                ActivityDashboardNew activityDashboardNew = ActivityDashboardNew.this;
                payUtils.fetchDeviceCapabilityConfig(activityDashboardNew, activityDashboardNew.b0);
                payUtils.fetchGooglefitConfiguration(ActivityDashboardNew.this);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A1(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void B1(ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openBluetoothSettings(this$0);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.v;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void C0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void C1(ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.v;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void E0(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        startScanOnDisconnect$app_prodRelease$default(this$0, false, 1, null);
    }

    public static final void F0(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        startScanOnDisconnect$app_prodRelease$default(this$0, false, 1, null);
    }

    public static final void F1(ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.N0();
    }

    public static final void G0(ActivityDashboardNew this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (str == null || kotlin.text.m.isBlank(str)) {
            return;
        }
        Toast.makeText(this$0, this$0.getString(R.string.low_battery), 1).show();
    }

    public static final void G1(ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackbar = this$0.R;
        if (snackbar != null) {
            snackbar.dismiss();
        }
        this$0.A0();
    }

    public static final void H0(final ActivityDashboardNew this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            BleApiManager.getInstance(this$0).getBleApi().restartService();
            Dashboard2Utils.Companion.scheduleJob(this$0);
            SyncManager.getInstance().resetSyncProgress();
            if (PayUtils.INSTANCE.checkIfScanOnDisConnectIsNeeded(this$0)) {
                this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.g0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityDashboardNew.I0(ActivityDashboardNew.this);
                    }
                });
            }
        }
    }

    public static final void H1(BottomSheetDialog bottomSheetDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialog, "$bottomSheetDialog");
        bottomSheetDialog.dismiss();
    }

    public static final void I0(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        startScanOnDisconnect$app_prodRelease$default(this$0, false, 1, null);
    }

    public static final void J1(ActivityDashboardNew this$0, Ref.ObjectRef cb_temperature_dna, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cb_temperature_dna, "$cb_temperature_dna");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        PreferenceManager.saveTemperatureDisclaimerShown(this$0, true);
        if (((CheckBox) cb_temperature_dna.element).isChecked()) {
            PreferenceManager.saveTemperatureDisclaimerDoNotShowAgain(this$0, true);
        } else {
            PreferenceManager.saveTemperatureDisclaimerDoNotShowAgain(this$0, false);
        }
        ((TemperatureDisclaimerDialog) dialog.element).dismiss();
    }

    public static final void O0(ActivityDashboardNew this$0, String str, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (AppUtils.isNetConnected(this$0)) {
            Intent intent = new Intent(this$0, ActivityBoatCoinsWebViewer.class);
            intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), str);
            intent.setFlags(67108864);
            this$0.startActivity(intent);
            ((Dialog) dialog.element).dismiss();
            return;
        }
        Toast.makeText(this$0, "Please check your internet connection", 0).show();
    }

    public static final void P0(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Dialog) dialog.element).dismiss();
    }

    public static final void Q0(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Navigation.findNavController(this$0, R.id.mainNavigationFragment).navigate(R.id.fragmentFitness);
    }

    public static final void Q1(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startScanOnDisconnect$app_prodRelease(false);
    }

    public static final void R0(BottomSheetDialogOneButtonTitleCustomRedMessageWithClose dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void R1(ActivityDashboardNew this$0) {
        FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfig;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if ((!themesUtils.isGuestUser(this$0) || !themesUtils.isPairDeviceLater(this$0)) && (fitnessChallengeRemoteConfig = FitnessChallengeSessionManager.getInstance(this$0).getFitnessChallengeRemoteConfig()) != null && fitnessChallengeRemoteConfig.getFitness_challenges() != null) {
            FitnessChallengeSyncViewModel fitnessChallengeSyncViewModel = this$0.W;
            if (fitnessChallengeSyncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeSyncViewModel");
                fitnessChallengeSyncViewModel = null;
            }
            fitnessChallengeSyncViewModel.getFitnessChallengeActiveDateRange(this$0);
        }
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0) && BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isSosSupported()) {
            SOSSettingsViewmodel sOSSettingsViewmodel = this$0.X;
            if (sOSSettingsViewmodel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sosSettingsViewModel");
                sOSSettingsViewmodel = null;
            }
            sOSSettingsViewmodel.getSOSConfigSettings();
            SOSSettingsViewmodel sOSSettingsViewmodel2 = this$0.X;
            if (sOSSettingsViewmodel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sosSettingsViewModel");
                sOSSettingsViewmodel2 = null;
            }
            sOSSettingsViewmodel2.getSOSRecords();
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_WATCHSYNC_SUCCESS.getValue(), null);
        Toast.makeText(this$0, this$0.getString(R.string.sync_complete), 0).show();
        Snackbar snackbar = this$0.B;
        if (snackbar != null) {
            Intrinsics.checkNotNull(snackbar);
            if (snackbar.isShown()) {
                Snackbar snackbar2 = this$0.B;
                Intrinsics.checkNotNull(snackbar2);
                snackbar2.dismiss();
            }
        }
    }

    public static final void S0(View view) {
    }

    public static final void S1() {
        CoveEventBusManager.getInstance().getEventBus().post(new SpSyncComplete());
        CoveEventBusManager.getInstance().getEventBus().post(new RcfSyncComplete());
    }

    public static final void T0(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getMDataSyncViewModel().checkIsSyncing() || this$0.w || this$0.x || !this$0.O) {
            return;
        }
        this$0.syncData();
    }

    public static final void T1(PackageManager packageManager, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "$componentName");
        packageManager.setComponentEnabledSetting(componentName, 1, 1);
    }

    public static final void U0(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((GenericMessageDialog) dialog.element).dismiss();
    }

    public static final void V0(Ref.ObjectRef dialog, ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this$0.N);
    }

    public static final void W0(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void X0(GenericMessageDialog dialog, ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.N);
    }

    public static final void Y0(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y0();
    }

    public static final void c1(final ActivityDashboardNew this$0, boolean z, final SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(firmwareBean, "$firmwareBean");
        ConnectionStatus connectionStatus = BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus();
        ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
        if (connectionStatus == connectionStatus2) {
            if (z) {
                SessionManager.getInstance(this$0).setForceFirmwareUpdated(false);
            }
            String string = this$0.getString(R.string.please_wait);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
            this$0.showProgresswithMsg(string);
            BleApiManager.getInstance(this$0).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$setFirmwareUpdatePopup$1$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$setFirmwareUpdatePopup$1$1$onDataError$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ ActivityDashboardNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(ActivityDashboardNew activityDashboardNew, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = activityDashboardNew;
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
                            this.this$0.dismissProgress();
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$setFirmwareUpdatePopup$1$1$onDataResponse$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SoftwareUpdateRes.DataBean.FirmwareBean $firmwareBean;
                    public final /* synthetic */ BleBaseResponse $response;
                    public int label;
                    public final /* synthetic */ ActivityDashboardNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(ActivityDashboardNew activityDashboardNew, BleBaseResponse bleBaseResponse, SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = activityDashboardNew;
                        this.$response = bleBaseResponse;
                        this.$firmwareBean = firmwareBean;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$response, this.$firmwareBean, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.dismissProgress();
                            if (this.$response.getResponseData() instanceof BatteryLevelResponse) {
                                Object responseData = this.$response.getResponseData();
                                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                                Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                                Intrinsics.checkNotNull(batteryLevel);
                                int intValue = batteryLevel.intValue();
                                if (intValue >= Integer.parseInt(AppConstants.BATTERY_LEVEL_FOR_FW_UPDATE.getValue())) {
                                    BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated = this.this$0.getBottomSheetDialogDashboardFirmwareUpdated();
                                    Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated);
                                    bottomSheetDialogDashboardFirmwareUpdated.dismiss();
                                    FirmwareUpdateActivityFactory.Companion.navigateToFirmwareUpdateActivity(this.this$0, this.$firmwareBean);
                                } else if (intValue <= 0 && PayUtils.INSTANCE.isCurrentFirmwareHasIssueWithBatteryPercentage(this.this$0)) {
                                    BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated2 = this.this$0.getBottomSheetDialogDashboardFirmwareUpdated();
                                    Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated2);
                                    bottomSheetDialogDashboardFirmwareUpdated2.dismiss();
                                    FirmwareUpdateActivityFactory.Companion.navigateToFirmwareUpdateActivity(this.this$0, this.$firmwareBean);
                                } else {
                                    ActivityDashboardNew activityDashboardNew = this.this$0;
                                    String string = activityDashboardNew.getString(R.string.make_sure_battery_device);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.make_sure_battery_device)");
                                    ViewUtilsKt.toast(activityDashboardNew, string);
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    kotlinx.coroutines.e.e(ActivityDashboardNew.this.getScope(), Dispatchers.getMain(), null, new a(ActivityDashboardNew.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    kotlinx.coroutines.e.e(ActivityDashboardNew.this.getScope(), Dispatchers.getMain(), null, new b(ActivityDashboardNew.this, response, firmwareBean, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        } else if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, this$0.getResources().getString(R.string.bluetooth_off_message), 1).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != connectionStatus2) {
            Toast.makeText(this$0, this$0.getResources().getString(R.string.watch_disconnected_msg), 1).show();
        }
    }

    public static final void d1(ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SessionManager.getInstance(this$0).saveFirmwareUpdateShownTime(String.valueOf(new Date().getTime()));
        BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated = this$0.V;
        Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated);
        bottomSheetDialogDashboardFirmwareUpdated.dismiss();
        this$0.w = false;
        SyncInterruptionListener syncInterruptionListener = this$0.F;
        if (syncInterruptionListener != null) {
            syncInterruptionListener.onInterrupt(SyncInterruptionType.NONE);
        }
        this$0.syncData();
    }

    public static final void g1(Ref.IntRef tabPos, ActivityDashboardNew this$0, NavController navController, Bundle bundle) {
        Intrinsics.checkNotNullParameter(tabPos, "$tabPos");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(navController, "$navController");
        Intrinsics.checkNotNullParameter(bundle, "$bundle");
        if (tabPos.element == 2) {
            this$0.startActivity(new Intent(this$0, ActivityManageRequests.class));
        } else {
            navController.navigate(R.id.fragmentFitness, bundle);
        }
    }

    public static final void h1(ActivityDashboardNew this$0, NavController navController, NavDestination destination, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(navController, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (destination.getId() != R.id.fragmentHome && destination.getId() != R.id.fragmentSettings) {
            ((ImageView) this$0._$_findCachedViewById(R.id.poweredby)).setVisibility(8);
            ImageView ivIndusIndLogo = (ImageView) this$0._$_findCachedViewById(R.id.ivIndusIndLogo);
            Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo, "ivIndusIndLogo");
            this$0.gone(ivIndusIndLogo);
            if (destination.getId() == R.id.fragmentRank && !LeaderBoardDataUtiils.isFirstTimeLeaderBoardShown(this$0) && this$0.J0()) {
                LeaderBoardNavigator.navigateToLeaderBoardOnBoardScreen(this$0);
                return;
            }
            return;
        }
        if (destination.getId() == R.id.fragmentHome && this$0.f0) {
            ImageView ivIndusIndLogo2 = (ImageView) this$0._$_findCachedViewById(R.id.ivIndusIndLogo);
            Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo2, "ivIndusIndLogo");
            this$0.visible(ivIndusIndLogo2);
        } else {
            ImageView ivIndusIndLogo3 = (ImageView) this$0._$_findCachedViewById(R.id.ivIndusIndLogo);
            Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo3, "ivIndusIndLogo");
            this$0.gone(ivIndusIndLogo3);
        }
        ((ImageView) this$0._$_findCachedViewById(R.id.poweredby)).setVisibility(8);
    }

    public static final void i1(Intent intent, ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(intent, "$intent");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Constants constants = Constants.EXTRA_GUARDIAN_TAB_POSITION;
        int intExtra = intent.getIntExtra(constants.getValue(), 0);
        Intent intent2 = new Intent(this$0, ManageHealthBuddiesActivity.class);
        intent2.putExtra(constants.getValue(), intExtra);
        this$0.startActivity(intent2);
    }

    public static final void m0(SoftwareUpdateRes appUpdateApiResponse, ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(appUpdateApiResponse, "$appUpdateApiResponse");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Z0(appUpdateApiResponse.getData().getApp().getUpdateUrl());
    }

    public static final void m1(Dialog alertDialog, View view) {
        Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
        alertDialog.dismiss();
    }

    public static final void n0(SoftwareUpdateRes appUpdateApiResponse, ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(appUpdateApiResponse, "$appUpdateApiResponse");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Z0(appUpdateApiResponse.getData().getApp().getUpdateUrl());
    }

    public static final void n1(Dialog alertDialog, ActivityDashboardNew this$0, CheckBox checkBox, View view) {
        Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        alertDialog.dismiss();
        new com.coveiot.android.activitymodes.preference.PreferenceManager(this$0).setShouldShowActivitySyncDialog(!checkBox.isChecked());
        this$0.startActivity(new Intent(this$0, OneKActivity.class));
    }

    public static final void o0(ActivityDashboardNew this$0, BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, SoftwareUpdateRes appUpdateApiResponse, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(appUpdateApiResponse, "$appUpdateApiResponse");
        SessionManager.getInstance(this$0).saveAppUpdateShownTime(String.valueOf(new Date().getTime()));
        bottomSheetDialogTwoButtons.dismiss();
        this$0.u0(appUpdateApiResponse);
    }

    public static final void p1(ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlertDialog alertDialog = this$0.c0;
        Intrinsics.checkNotNull(alertDialog);
        if (alertDialog.isShowing()) {
            AlertDialog alertDialog2 = this$0.c0;
            Intrinsics.checkNotNull(alertDialog2);
            alertDialog2.dismiss();
        }
    }

    public static final void q1(Ref.ObjectRef bottomSheetDialogImageTitleAndMessageWatchFace, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleAndMessageWatchFace, "$bottomSheetDialogImageTitleAndMessageWatchFace");
        T t = bottomSheetDialogImageTitleAndMessageWatchFace.element;
        Intrinsics.checkNotNull(t);
        ((BottomSheetDialogImageTitleAndMessageWatchFace) t).dismiss();
    }

    public static final void r1(Ref.ObjectRef pd, Dialog dialog, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(pd, "$pd");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((ProgressDialog) pd.element).dismiss();
        ((VideoView) dialog.findViewById(R.id.video)).start();
    }

    public static final void s1(CustomNotifiacationModel customNotifiacationModel, ActivityDashboardNew this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        CustomNotifiacationModel.BannerNotificationMessageBean bannerNotificationMessage = customNotifiacationModel.getBannerNotificationMessage();
        Intrinsics.checkNotNull(bannerNotificationMessage);
        if (!kotlin.text.m.equals(bannerNotificationMessage.getCallToActionType(), this$0.getResources().getString(R.string.index), true)) {
            CustomNotifiacationModel.BannerNotificationMessageBean bannerNotificationMessage2 = customNotifiacationModel.getBannerNotificationMessage();
            Intrinsics.checkNotNull(bannerNotificationMessage2);
            if (kotlin.text.m.equals(bannerNotificationMessage2.getCallToActionType(), this$0.getResources().getString(R.string.url), true)) {
                CustomNotifiacationModel.BannerNotificationMessageBean bannerNotificationMessage3 = customNotifiacationModel.getBannerNotificationMessage();
                Intrinsics.checkNotNull(bannerNotificationMessage3);
                this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(bannerNotificationMessage3.getCallToActionAndroid())));
            }
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static /* synthetic */ void startScanOnDisconnect$app_prodRelease$default(ActivityDashboardNew activityDashboardNew, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        activityDashboardNew.startScanOnDisconnect$app_prodRelease(z);
    }

    public static final void t1(Dialog dialog, ActivityDashboardNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.share_iv;
        if (kotlin.text.m.equals(((Button) dialog.findViewById(i)).getText().toString(), this$0.getResources().getString(R.string.done), true)) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } else if (kotlin.text.m.equals(((Button) dialog.findViewById(i)).getText().toString(), this$0.getResources().getString(R.string.share), true)) {
            ((Button) dialog.findViewById(i)).setVisibility(8);
            ((Button) dialog.findViewById(R.id.dynamicBtn)).setVisibility(8);
            RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.root_layout);
            Intrinsics.checkNotNullExpressionValue(relativeLayout, "dialog.root_layout");
            this$0.setBitmapCustom(this$0.takeScreenshot(relativeLayout));
            this$0.K = 2;
            this$0.getViewmodel().checkStoragePermission();
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } else if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static /* synthetic */ void takeToHomeScreen$default(ActivityDashboardNew activityDashboardNew, HomeNavigationElement homeNavigationElement, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = new Bundle();
        }
        activityDashboardNew.takeToHomeScreen(homeNavigationElement, bundle);
    }

    public static final void u1(BottomSheetDialog bottomSheetDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialog, "$bottomSheetDialog");
        bottomSheetDialog.dismiss();
    }

    public static final void w1(Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void x1(final ActivityDashboardNew this$0, Dialog dialog, final CheckBox checkBox, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this$0)) {
                dialog.dismiss();
                AutoHrViewModel autoHrViewModel = this$0.E;
                AutoHrViewModel autoHrViewModel2 = null;
                if (autoHrViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewmodelAutoHR");
                    autoHrViewModel = null;
                }
                autoHrViewModel.setDialogListener(new DialogListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDisableAutoHRDialog$2$1
                    @Override // com.coveiot.android.leonardo.listener.DialogListener
                    public void onDismiss() {
                        this$0.dismissProgress();
                    }

                    @Override // com.coveiot.android.leonardo.listener.DialogListener
                    public void onShowProgressDialog() {
                        this$0.showProgress();
                    }

                    @Override // com.coveiot.android.leonardo.listener.DialogListener
                    public void showErrorDialog() {
                        ActivityDashboardNew activityDashboardNew = this$0;
                        String string = activityDashboardNew.getString(R.string.some_went_wrong_disable_autohr);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_went_wrong_disable_autohr)");
                        activityDashboardNew.showCommonMessageDialog(string, true);
                    }

                    @Override // com.coveiot.android.leonardo.listener.DialogListener
                    public void showSuccessDialog() {
                        if (checkBox.isChecked()) {
                            UserDataManager.getInstance(this$0).saveTimestampDisableAutoHRDialogShown(BleApiManager.getInstance(this$0).getBleApi().getMacAddress(), -1L);
                        }
                        ActivityDashboardNew activityDashboardNew = this$0;
                        String string = activityDashboardNew.getString(R.string.disabled_successfully);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.disabled_successfully)");
                        BaseActivity.showCommonMessageDialog$default(activityDashboardNew, string, false, 2, null);
                    }
                });
                AutoHrViewModel autoHrViewModel3 = this$0.E;
                if (autoHrViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewmodelAutoHR");
                } else {
                    autoHrViewModel2 = autoHrViewModel3;
                }
                autoHrViewModel2.callSaveAndBleApi(UserDataManager.getInstance(this$0).getAutoHrInterval(), false);
                return;
            }
            this$0.showNoInternetDialog();
            return;
        }
        this$0.o1();
    }

    public static final void y1(CheckBox checkBox, ActivityDashboardNew this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (checkBox.isChecked()) {
            UserDataManager.getInstance(this$0).saveTimestampDisableAutoHRDialogShown(BleApiManager.getInstance(this$0).getBleApi().getMacAddress(), -1L);
        }
        dialog.dismiss();
    }

    public final void A0() {
        Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        intent.setData(Uri.parse("package:" + getPackageName()));
        intent.setFlags(268435456);
        startActivity(intent);
    }

    public final void B0() {
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = this.D;
        if (alexaApiCallHandlerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodelAlexaApiCallHandler");
            alexaApiCallHandlerViewModel = null;
        }
        MutableLiveData<AppToAppLinkingResponse> alexaAccountLinkingStatusLiveData = alexaApiCallHandlerViewModel.getAlexaAccountLinkingStatusLiveData();
        final a aVar = new a();
        alexaAccountLinkingStatusLiveData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.a0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityDashboardNew.C0(Function1.this, obj);
            }
        });
    }

    public final void D0() {
        Bundle extras;
        int i = WhenMappings.$EnumSwitchMapping$1[BleApiManager.getInstance(this).getBleApi().getConnectionStatus().ordinal()];
        if (i == 1) {
            String str = this.J;
            LogHelper.d(str, "connect state " + ConnectionStatus.CONNECTED);
        } else if (i != 2) {
            if (Build.VERSION.SDK_INT >= 31) {
                if (Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                    BleApiManager.getInstance(this).getBleApi().restartService();
                }
            } else {
                BleApiManager.getInstance(this).getBleApi().restartService();
            }
            runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.f0
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityDashboardNew.F0(ActivityDashboardNew.this);
                }
            });
            String str2 = this.J;
            LogHelper.d(str2, "connect state " + BleApiManager.getInstance(this).getBleApi().getConnectionStatus());
            AppListHelper.Companion.saveAppListFromSystem(this, true);
        } else {
            String str3 = this.J;
            LogHelper.d(str3, "connect state " + ConnectionStatus.CONNECTING);
            runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.e0
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityDashboardNew.E0(ActivityDashboardNew.this);
                }
            });
        }
        getViewmodel().registerConnectionStatus(this, this);
        checkAppUpgradeApi();
        if (!getBluetoothAdapter().isEnabled()) {
            showOpenBluetoothSettingsDialog();
        }
        getViewmodel().getBatteryUpdate().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.z
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityDashboardNew.G0(ActivityDashboardNew.this, (String) obj);
            }
        });
        getViewmodel().getBatteryRequestFailure().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.y
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityDashboardNew.H0(ActivityDashboardNew.this, (Boolean) obj);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD;
        analyticsLog.setScreenName(screenName.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isAgpsFileUploadSupported();
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported() && !PreferenceManager.isTemperatureDisclaimerShown(this) && !PreferenceManager.isTemperatureDisclaimerDoNotShowAgainOn(this)) {
            I1();
        }
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            f1(intent);
            String string = extras.getString(AppConstants.IS_CUSTOMNOTIFICATION.getValue());
            if (string != null) {
                if (string.length() > 0) {
                    showCustomNotificationDialog(string);
                }
            }
        }
        V1();
        getViewmodel().isShowSensAI(this);
        B0();
    }

    public final void D1(String str) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        String title;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.C;
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
            Intrinsics.checkNotNull(valueOf);
            z = true ^ valueOf.booleanValue();
        }
        if (this.C == null || z) {
            this.C = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.C;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            String string = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
            bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showPermissionDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                    bottomSheetDialogOneButtonOneTitle4 = ActivityDashboardNew.this.C;
                    if (bottomSheetDialogOneButtonOneTitle4 != null) {
                        bottomSheetDialogOneButtonOneTitle4.dismiss();
                    }
                    ActivityDashboardNew.this.setFromAppSettings(true);
                    AppUtils.openAppSettings(ActivityDashboardNew.this, 121);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.C;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.C) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void E1() {
        Snackbar snackbar;
        if (this.R == null) {
            this.R = Snackbar.make((CoordinatorLayout) _$_findCachedViewById(R.id.root_coordinate_layout), "", -2);
        }
        Snackbar snackbar2 = this.R;
        View view = snackbar2 != null ? snackbar2.getView() : null;
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.google.android.material.snackbar.Snackbar.SnackbarLayout");
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) view;
        snackbarLayout.setPadding(0, 0, 0, 0);
        View inflate = getLayoutInflater().inflate(R.layout.battery_optimization_snackbar, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "layoutInflater.inflate(R…imization_snackbar, null)");
        ((ImageView) inflate.findViewById(R.id.iv_battery_optimize_info)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ActivityDashboardNew.F1(ActivityDashboardNew.this, view2);
            }
        });
        TextView textView = (TextView) inflate.findViewById(R.id.battery_optimization_text);
        BatteryOptimizationConfig batteryOptimizationConfig = UserDataManager.getInstance(this).getBatteryOptimizationConfig();
        if (batteryOptimizationConfig != null && !AppUtils.isEmpty(batteryOptimizationConfig.getConfigs())) {
            for (BatteryOptimizationConfig.Configs configs : batteryOptimizationConfig.getConfigs()) {
                if (kotlin.text.m.equals(configs.getManufacturer(), Build.MANUFACTURER, true) && configs.getAndroidVersion().contains(Integer.valueOf(Build.VERSION.SDK_INT))) {
                    if (!AppUtils.isEmpty(configs.getModel())) {
                        if (configs.getModel().contains(Build.MODEL)) {
                            textView.setText(configs.getSnakbarText());
                        }
                    } else {
                        textView.setText(configs.getSnakbarText());
                    }
                }
            }
        }
        View findViewById = snackbarLayout.findViewById(R.id.snackbar_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setVisibility(4);
        ((Button) inflate.findViewById(R.id.disable)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.s0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ActivityDashboardNew.G1(ActivityDashboardNew.this, view2);
            }
        });
        snackbarLayout.addView(inflate, 0);
        Snackbar snackbar3 = this.R;
        Boolean valueOf = snackbar3 != null ? Boolean.valueOf(snackbar3.isShown()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (snackbar = this.R) == null) {
            return;
        }
        snackbar.show();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.coveiot.android.leonardo.dashboard.TemperatureDisclaimerDialog, T] */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, android.view.View] */
    public final void I1() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new TemperatureDisclaimerDialog(this);
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = ((TemperatureDisclaimerDialog) objectRef.element).findViewById(R.id.cb_temperature_dna);
        ((TextView) ((TemperatureDisclaimerDialog) objectRef.element).findViewById(R.id.btDisclaimerOk)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDashboardNew.J1(ActivityDashboardNew.this, objectRef2, objectRef, view);
            }
        });
        if (isFinishing()) {
            return;
        }
        ((TemperatureDisclaimerDialog) objectRef.element).show();
    }

    public final boolean J0() {
        String myRank = LeaderBoardDataUtiils.getMyRank(this);
        if (myRank != null) {
            if (myRank.length() > 0) {
                MyRankModel myRankModel = (MyRankModel) new Gson().fromJson(myRank, (Class<Object>) MyRankModel.class);
                if (myRankModel.getData() != null && myRankModel.getData().getRank() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public final boolean K0() {
        Object systemService = getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(WorkoutSessionService.class.getName(), runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public final void K1(boolean z) {
        try {
            String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"});
            if (Build.VERSION.SDK_INT < 29) {
                PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"});
            }
            if (checkPermissionsHasGranted.length <= 0 || !z) {
                return;
            }
            startActivityForResult(new Intent(this, LocationPermissionActivity.class), h0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void L0(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.BUTTONTEXT.getValue(), str);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(this));
        hashMap.putAll(companion.getWatchDetails(this));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BTN_NAV_TAPPED.getValue(), hashMap);
    }

    public final void L1() {
        if ((Build.VERSION.SDK_INT < 31 || (AppUtils.checkIfLocationPermissionExists(this) && Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this))) && AppUtils.isBluetoothEnabled(this)) {
            if (DeviceScanManager.getInstance(this).isScanningInProgress()) {
                DeviceScanManager.getInstance(this).stopScan();
            }
            if (AppUtils.isBluetoothEnabled(this)) {
                this.L = false;
                DeviceScanManager.getInstance(this).scanAllDevices(this, TimeUnit.HOURS.toMillis(Long.parseLong(AppConstants.SCAN_TIME_ON_DISCONNECTION.getValue())), false, new ScanResult() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$startLeScan$1
                    @Override // com.coveiot.sdk.ble.scan.ScanResult
                    public void onDevicesFound(@Nullable List<BleDevice> list, boolean z) {
                        String macAddress = BleApiManager.getInstance(ActivityDashboardNew.this).getBleApi().getMacAddress();
                        if (list == null || !(!list.isEmpty())) {
                            return;
                        }
                        for (BleDevice bleDevice : list) {
                            if (kotlin.text.m.equals(bleDevice.getmDevice().getAddress(), macAddress, true)) {
                                ActivityDashboardNew.this.setDeviceFoundDuringScan(true);
                            }
                        }
                    }

                    @Override // com.coveiot.sdk.ble.scan.ScanResult
                    public void onScanFailed(int i) {
                    }
                });
            }
        }
    }

    public final void M0(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), str);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getWatchDetails(this));
        hashMap.putAll(companion.getDeviceId(this));
        companion.logAnalyticsEvent(SOSCleverTapConstants.BC_SOS_LANDINGPAGE_VIEWED.getValue(), hashMap);
    }

    public final void M1() {
        SettingsSyncHelper.Companion.getInstance(this).setCurrentVolumeToWatch();
        if (getSupportFragmentManager().findFragmentById(R.id.mainNavigationFragment) != null) {
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.mainNavigationFragment);
            Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
            NavHostFragment navHostFragment = (NavHostFragment) findFragmentById;
            if (navHostFragment.getNavController() == null || navHostFragment.getNavController().getCurrentDestination() == null) {
                return;
            }
            NavDestination currentDestination = navHostFragment.getNavController().getCurrentDestination();
            Intrinsics.checkNotNull(currentDestination);
            LogHelper.d("Navigation", String.valueOf(currentDestination.getId() == R.id.fragmentHome));
            if (this.O) {
                NavDestination currentDestination2 = navHostFragment.getNavController().getCurrentDestination();
                Intrinsics.checkNotNull(currentDestination2);
                currentDestination2.getId();
            }
        }
    }

    public final void N0() {
        startActivity(new Intent(this, BatteryOptimizationInfoActivity.class));
    }

    public final void N1() {
        Intent intent = new Intent(this, AlexaRemoteCommandFrameworkService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    public final void O1() {
        SportsPreferenceModel sportsNotification = SportsPreference.Companion.getSportsNotification(this);
        if (kotlin.text.m.equals$default(sportsNotification != null ? sportsNotification.getSport() : null, SportsType.Cricket.name(), false, 2, null)) {
            Intent intent = new Intent(this, SportsService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent);
                return;
            } else {
                startService(intent);
                return;
            }
        }
        Intent intent2 = new Intent(this, SoccerSportsServiceNew.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent2);
        } else {
            startService(intent2);
        }
    }

    public final void P1() {
        try {
            DeviceScanManager.getInstance(this).stopScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void U1() {
        ProfileData userDetails = SessionManager.getInstance(this).getUserDetails();
        String height = userDetails.getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
        int parseInt = Integer.parseInt(height);
        String weight = userDetails.getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
        SetFitnessInfoRequest.Builder builder = new SetFitnessInfoRequest.Builder(parseInt, Double.parseDouble(weight));
        builder.setStride(ProfileRepository.getInstance().getLatestProfileData(this).walkStrideLength);
        builder.setRunStride(ProfileRepository.getInstance().getLatestProfileData(this).runStrideLength);
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
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
        BleApiManager.getInstance(this).getBleApi().setUserSettings(builder2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$updateHeightAndWeightAfterReconnection$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                ServerDataPullConfigModel serverDataPullConfigModel;
                Intrinsics.checkNotNullParameter(response, "response");
                str = ActivityDashboardNew.this.J;
                LogHelper.d(str, "setFitnessInfoRequest Success");
                SessionManager.getInstance(ActivityDashboardNew.this).setWriteStepGoalAfterConnection(false);
                LogHelper.d("viewmodelLog Activity Sync Data", ActivityDashboardNew.this.getViewmodel().toString());
                ViewModelActivityDashboardNew viewmodel = ActivityDashboardNew.this.getViewmodel();
                ActivityDashboardNew activityDashboardNew = ActivityDashboardNew.this;
                synchronized (viewmodel) {
                    DataSyncViewModel mDataSyncViewModel = activityDashboardNew.getMDataSyncViewModel();
                    serverDataPullConfigModel = activityDashboardNew.H;
                    mDataSyncViewModel.syncData(false, serverDataPullConfigModel);
                    Unit unit = Unit.INSTANCE;
                }
            }
        });
    }

    public final void V1() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new c(null), 2, null);
    }

    public final void Z0(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        startActivity(intent);
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void a1() {
        NavController findNavController = Navigation.findNavController(this, R.id.mainNavigationFragment);
        Intrinsics.checkNotNullExpressionValue(findNavController, "findNavController(this, …d.mainNavigationFragment)");
        NavDestination currentDestination = findNavController.getCurrentDestination();
        Integer valueOf = currentDestination != null ? Integer.valueOf(currentDestination.getId()) : null;
        Intrinsics.checkNotNull(valueOf);
        findNavController.popBackStack(valueOf.intValue(), true);
        findNavController.navigate(valueOf.intValue());
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void askPermission(@NotNull PermissionType permissionUtils) {
        Intrinsics.checkNotNullParameter(permissionUtils, "permissionUtils");
        if (WhenMappings.$EnumSwitchMapping$0[permissionUtils.ordinal()] == 1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.N);
        }
    }

    public final void b1(final boolean z, final SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated = this.V;
        Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated);
        bottomSheetDialogDashboardFirmwareUpdated.setCancelable(false);
        BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated2 = this.V;
        Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated2);
        bottomSheetDialogDashboardFirmwareUpdated2.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDashboardNew.c1(ActivityDashboardNew.this, z, firmwareBean, view);
            }
        });
        BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated3 = this.V;
        Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated3);
        bottomSheetDialogDashboardFirmwareUpdated3.setNegativeButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDashboardNew.d1(ActivityDashboardNew.this, view);
            }
        });
        BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated4 = this.V;
        Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated4);
        if (!bottomSheetDialogDashboardFirmwareUpdated4.isShowing() && this.O && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated5 = this.V;
            Intrinsics.checkNotNull(bottomSheetDialogDashboardFirmwareUpdated5);
            bottomSheetDialogDashboardFirmwareUpdated5.show();
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void callRemoteConfigAPI(@Nullable WeatherResultListener weatherResultListener, @Nullable OnDynamicTabDataChangedListener onDynamicTabDataChangedListener) {
        p0(weatherResultListener, onDynamicTabDataChangedListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003a A[LOOP:0: B:5:0x0015->B:17:0x003a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003d A[EDGE_INSN: B:20:0x003d->B:18:0x003d ?: BREAK  , SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String camelCase(@org.jetbrains.annotations.NotNull java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "str"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r7)
            int r7 = r0.length()
            r1 = 1
            int r7 = r7 - r1
            if (r7 < 0) goto L3d
            r2 = 0
            r4 = r1
            r3 = r2
        L15:
            char r5 = r0.charAt(r3)
            if (r4 == 0) goto L33
            r4 = 97
            int r4 = kotlin.jvm.internal.Intrinsics.compare(r5, r4)
            if (r4 < 0) goto L33
            r4 = 122(0x7a, float:1.71E-43)
            int r4 = kotlin.jvm.internal.Intrinsics.compare(r5, r4)
            if (r4 > 0) goto L33
            int r5 = r5 + (-32)
            char r4 = (char) r5
            r0.setCharAt(r3, r4)
        L31:
            r4 = r2
            goto L38
        L33:
            r4 = 32
            if (r5 != r4) goto L31
            r4 = r1
        L38:
            if (r3 == r7) goto L3d
            int r3 = r3 + 1
            goto L15
        L3d:
            java.lang.String r7 = r0.toString()
            java.lang.String r0 = "builder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew.camelCase(java.lang.String):java.lang.String");
    }

    public final boolean canShowDisableAutoHRDialog() {
        Integer minTimeGapForShowingNextAutoHRPopup;
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this).getDeviceModelBean();
        if (deviceModelBean == null || (minTimeGapForShowingNextAutoHRPopup = deviceModelBean.getMinTimeGapForShowingNextAutoHRPopup()) == null) {
            return false;
        }
        int intValue = minTimeGapForShowingNextAutoHRPopup.intValue();
        long timestampDisableAutoHRDialogShown = UserDataManager.getInstance(this).getTimestampDisableAutoHRDialogShown(BleApiManager.getInstance(this).getBleApi().getMacAddress());
        return timestampDisableAutoHRDialogShown != -1 && System.currentTimeMillis() - timestampDisableAutoHRDialogShown > TimeUnit.MINUTES.toMillis((long) intValue);
    }

    public final void checkAndSendGoalCompleteNotificaton() {
        v0();
        PreferenceNames preferenceNames = PreferenceNames.IS_STEP_GOAL_CHANGED;
        Boolean bool = Boolean.FALSE;
        Object preference = PreferenceManager.getPreference(this, preferenceNames, bool);
        Intrinsics.checkNotNullExpressionValue(preference, "getPreference(\n         …NGED, false\n            )");
        boolean booleanValue = ((Boolean) preference).booleanValue();
        Object preference2 = PreferenceManager.getPreference(this, PreferenceNames.IS_GOAL_COMPLETE_NOTIFICATION_SHOWN, bool);
        Intrinsics.checkNotNullExpressionValue(preference2, "getPreference(\n         …HOWN, false\n            )");
        boolean booleanValue2 = ((Boolean) preference2).booleanValue();
        StepsDataModel liveStepsData = UserDataManager.getInstance(this).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(this).getBleApi().getMacAddress());
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this).getFitnessGoalStepsData();
        if (fitnessGoalStepsData == null || liveStepsData == null) {
            return;
        }
        int steps = liveStepsData.getSteps();
        Integer num = fitnessGoalStepsData.target;
        Intrinsics.checkNotNullExpressionValue(num, "mStepsGoalData.target");
        if (steps >= num.intValue()) {
            if (booleanValue) {
                z1();
            } else if (booleanValue2) {
            } else {
                z1();
            }
        }
    }

    public final void checkAppUpgradeApi() {
        FirmwareUtils.INSTANCE.checkAppUpgradeApi(this, new CoveApiListener<SoftwareUpdateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$checkAppUpgradeApi$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                String str;
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                str = ActivityDashboardNew.this.J;
                LogHelper.d(str, coveApiErrorModel.toString());
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SoftwareUpdateRes softwareUpdateRes) {
                Intrinsics.checkNotNullParameter(softwareUpdateRes, "softwareUpdateRes");
                ActivityDashboardNew.this.l0(softwareUpdateRes);
                SessionManager.getInstance(ActivityDashboardNew.this).saveSoftwareUpdateResponseString(new Gson().toJson(softwareUpdateRes));
            }
        });
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void checkPermssion(@NotNull String permission, @NotNull PermissionUtils.PermissionAskListener permissionListener) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(permissionListener, "permissionListener");
        PermissionUtils.checkPermission(this, permission, permissionListener);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void deRegisterForConnectionStatusUpdate(@Nullable ConnectionStatusUpdateListener connectionStatusUpdateListener) {
        if (connectionStatusUpdateListener == null || !this.G.contains(connectionStatusUpdateListener)) {
            return;
        }
        this.G.remove(connectionStatusUpdateListener);
    }

    @Override // com.coveiot.android.dashboard2.listener.SyncTroubleShoutListener
    public void dismissSyncTroubleShoot() {
        try {
            SyncTroubleshootFragment syncTroubleshootFragment = this.Y;
            if (syncTroubleshootFragment == null || syncTroubleshootFragment == null) {
                return;
            }
            syncTroubleshootFragment.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void e1(DeviceConfigurationRes.CalibrationBean.TemperatureBean temperatureBean) {
        final double value = temperatureBean.getValue();
        if (((Double) PreferenceManager.getPreference(this, PreferenceNames.TEMPERATURE_CALIBRATION_VAL, Double.valueOf(0.0d))).equals(Double.valueOf(value))) {
            return;
        }
        TemperatureCalibrationDataRequest temperatureCalibrationDataRequest = new TemperatureCalibrationDataRequest();
        if (value > 0.0d) {
            temperatureCalibrationDataRequest.setTempCalculatingSign(1);
        } else {
            temperatureCalibrationDataRequest.setTempCalculatingSign(2);
        }
        temperatureCalibrationDataRequest.setTemperature(Math.abs(value));
        String baseUnit = temperatureBean.getBaseUnit();
        Intrinsics.checkNotNullExpressionValue(baseUnit, "temperature.baseUnit");
        temperatureCalibrationDataRequest.setTemperatureUnit(baseUnit);
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this).getBleApi().setUserSettings(temperatureCalibrationDataRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$setTemperatureCalibration$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = ActivityDashboardNew.this.J;
                    LogHelper.d(str, "TemperatureCalibrationDataRequest failed");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = ActivityDashboardNew.this.J;
                    LogHelper.d(str, "TemperatureCalibrationDataRequest success");
                    PreferenceManager.savePreference(ActivityDashboardNew.this, PreferenceNames.TEMPERATURE_CALIBRATION_VAL, Double.valueOf(value));
                }
            });
        }
    }

    public final void f1(final Intent intent) {
        final NavController findNavController = Navigation.findNavController(this, R.id.mainNavigationFragment);
        Intrinsics.checkNotNullExpressionValue(findNavController, "findNavController(this, …d.mainNavigationFragment)");
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.mainNavigationFragment);
        Intrinsics.checkNotNull(findFragmentById);
        FragmentManager childFragmentManager = findFragmentById.getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "navHostFragment.childFragmentManager");
        KeepStateNavigator keepStateNavigator = new KeepStateNavigator(this, childFragmentManager, R.id.mainNavigationFragment);
        NavigatorProvider navigatorProvider = findNavController.getNavigatorProvider();
        Intrinsics.checkNotNullExpressionValue(navigatorProvider, "navController.navigatorProvider");
        navigatorProvider.addNavigator(keepStateNavigator);
        findNavController.setGraph(R.navigation.dashboard_nav_graph_modes);
        int i = R.id.bottomNavigationView;
        NavigationUI.setupWithNavController((BottomNavigationView) _$_findCachedViewById(i), findNavController);
        final Ref.IntRef intRef = new Ref.IntRef();
        if (intent.getExtras() != null && intent.hasExtra("NOTIFICATION_TYPE")) {
            if (intent.hasExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION)) {
                intRef.element = intent.getIntExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION, 0);
            }
            PreferenceManager.Companion companion = com.coveiot.android.fitnessbuddies.utils.PreferenceManager.Companion;
            companion.saveFitnessCheerCount(this, 0);
            companion.saveFitnessNotificationCount(this, 0);
            final Bundle bundle = new Bundle();
            bundle.putInt("tab_pos", intRef.element);
            this.M.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.n0
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityDashboardNew.g1(Ref.IntRef.this, this, findNavController, bundle);
                }
            }, 200L);
        } else {
            if (intent.getExtras() != null && intent.hasExtra("FCM_NOTIFICATION_TYPE")) {
                String stringExtra = intent.getStringExtra("FCM_NOTIFICATION_TYPE");
                HealthNavigationElement healthNavigationElement = HealthNavigationElement.ENERGY_METER;
                if (kotlin.text.m.equals(stringExtra, healthNavigationElement.name(), true) || kotlin.text.m.equals(intent.getStringExtra("FCM_NOTIFICATION_TYPE"), HealthNavigationElement.SLEEP.name(), true) || kotlin.text.m.equals(intent.getStringExtra("FCM_NOTIFICATION_TYPE"), HealthNavigationElement.STEPS.name(), true)) {
                    if (kotlin.text.m.equals(intent.getStringExtra("FCM_NOTIFICATION_TYPE"), healthNavigationElement.name(), true)) {
                        takeToScreen(healthNavigationElement);
                    } else {
                        String stringExtra2 = intent.getStringExtra("FCM_NOTIFICATION_TYPE");
                        HealthNavigationElement healthNavigationElement2 = HealthNavigationElement.SLEEP;
                        if (kotlin.text.m.equals(stringExtra2, healthNavigationElement2.name(), true)) {
                            takeToScreen(healthNavigationElement2);
                        } else {
                            String stringExtra3 = intent.getStringExtra("FCM_NOTIFICATION_TYPE");
                            HealthNavigationElement healthNavigationElement3 = HealthNavigationElement.STEPS;
                            if (kotlin.text.m.equals(stringExtra3, healthNavigationElement3.name(), true)) {
                                takeToScreen(healthNavigationElement3);
                            }
                        }
                    }
                }
            }
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            if (themesUtils.isGuestUser(this) || themesUtils.isPairDeviceLater(this)) {
                findNavController.navigate(R.id.fragmentHomeGuest);
            }
        }
        findNavController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() { // from class: com.coveiot.android.leonardo.dashboard.b0
            @Override // androidx.navigation.NavController.OnDestinationChangedListener
            public final void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle2) {
                ActivityDashboardNew.h1(ActivityDashboardNew.this, navController, navDestination, bundle2);
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new ActivityDashboardNew$setupNavigation$3(this, findNavController));
        ((BottomNavigationView) _$_findCachedViewById(i)).setItemIconTintList(null);
        ((BottomNavigationView) _$_findCachedViewById(i)).setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$setupNavigation$4
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public boolean onNavigationItemSelected(@NotNull MenuItem item) {
                Intrinsics.checkNotNullParameter(item, "item");
                switch (item.getItemId()) {
                    case R.id.fragmentFitness /* 2131363510 */:
                        ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
                        if (!themesUtils2.isGuestUser(ActivityDashboardNew.this) && !themesUtils2.isPairDeviceLater(ActivityDashboardNew.this)) {
                            findNavController.navigate(R.id.fragmentFitness);
                        } else {
                            findNavController.navigate(R.id.fragmentFitnessGuest);
                        }
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
                        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BOTTON_MENU_BUDDIES.getValue());
                        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BOTTOM_MENU_BUDDIES_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        ActivityDashboardNew.this.L0(CleverTapConstants.CustomEventValues.FITCREW.getValue());
                        return true;
                    case R.id.fragmentFitnessGuest /* 2131363511 */:
                    case R.id.fragmentHomeGuest /* 2131363514 */:
                    default:
                        return true;
                    case R.id.fragmentHealth /* 2131363512 */:
                        findNavController.navigate(R.id.fragmentHealth);
                        AnalyticsLog analyticsLog2 = new AnalyticsLog();
                        analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
                        analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_HEALTH.getValue());
                        analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.BOTTOM_MENU_HEALTH_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                        ActivityDashboardNew.this.L0(CleverTapConstants.CustomEventValues.FITNESS.getValue());
                        return true;
                    case R.id.fragmentHome /* 2131363513 */:
                        ThemesUtils themesUtils3 = ThemesUtils.INSTANCE;
                        if (!themesUtils3.isGuestUser(ActivityDashboardNew.this) && !themesUtils3.isPairDeviceLater(ActivityDashboardNew.this)) {
                            findNavController.navigate(R.id.fragmentHome);
                            ActivityDashboardNew.this.L0(CleverTapConstants.CustomEventValues.HOME.getValue());
                        } else {
                            findNavController.navigate(R.id.fragmentHomeGuest);
                            ActivityDashboardNew.this.L0(CleverTapConstants.CustomEventValues.HOME.getValue());
                        }
                        AnalyticsLog analyticsLog3 = new AnalyticsLog();
                        analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
                        analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_HOME.getValue());
                        analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.BOTTOM_MENU_HOME_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
                        return true;
                    case R.id.fragmentMyWatch /* 2131363515 */:
                        findNavController.navigate(R.id.fragmentMyWatch);
                        AnalyticsLog analyticsLog4 = new AnalyticsLog();
                        analyticsLog4.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog4.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
                        analyticsLog4.setDescription(FirebaseEventParams.Description.OPEN_BOTTON_MENU_BUDDIES.getValue());
                        analyticsLog4.setUiElementName(FirebaseEventParams.UiElementName.BOTTOM_MENU_BUDDIES_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog4);
                        ActivityDashboardNew.this.L0(CleverTapConstants.CustomEventValues.MYWATCH.getValue());
                        return true;
                    case R.id.fragmentRank /* 2131363516 */:
                        findNavController.navigate(R.id.fragmentRank);
                        AnalyticsLog analyticsLog5 = new AnalyticsLog();
                        analyticsLog5.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog5.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
                        analyticsLog5.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_RANK.getValue());
                        analyticsLog5.setUiElementName(FirebaseEventParams.UiElementName.BOTTOM_MENU_RANK_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog5);
                        return true;
                    case R.id.fragmentSettings /* 2131363517 */:
                        findNavController.navigate(R.id.fragmentSettings);
                        AnalyticsLog analyticsLog6 = new AnalyticsLog();
                        analyticsLog6.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog6.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
                        analyticsLog6.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_MORE.getValue());
                        analyticsLog6.setUiElementName(FirebaseEventParams.UiElementName.BOTTOM_MENU_MORE_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog6);
                        ActivityDashboardNew.this.L0(CleverTapConstants.CustomEventValues.SETTINGS.getValue());
                        return true;
                }
            }
        });
        if (intent.getExtras() != null && intent.hasExtra("NOTIFICATION_TYPE") && intent.hasExtra(Constants.EXTRA_GUARDIAN_TAB_POSITION.getValue())) {
            this.M.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.c0
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityDashboardNew.i1(intent, this);
                }
            }, 200L);
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void fetchRemoteConfig() {
        PayUtils payUtils = PayUtils.INSTANCE;
        payUtils.fetchDeviceRemoteConfig(ThemeConstants.REMOTE_CONFIG_DEVICE_LIST_NEW.getValue(), this, this.Z);
        PayUtils.isBoatCoinsEnabled(this, new BoatCoinsFirebaseConfigResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$fetchRemoteConfig$1
            @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
            public void onFailure(@Nullable String str) {
            }

            @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
            public void onResult(boolean z) {
                com.coveiot.android.leonardo.utils.PreferenceManager.saveBoatCoinesEnabled(ActivityDashboardNew.this, z);
            }
        });
        payUtils.fetchRsaKeysConfig(ThemeConstants.RSA_ENCRYPTDECRYPT_KEYS.getValue(), this, this.a0);
    }

    public final int getBLUETOOTH_PERMISSIONS_REQUEST_CODE() {
        return this.r;
    }

    public final int getBT3ConnectedDeviceIcon() {
        return 2131231948;
    }

    public final int getBT3DisconnectedDeviceIcon() {
        return 2131231950;
    }

    public final int getBackPressFlag$app_prodRelease() {
        return this.t;
    }

    @NotNull
    public final Handler getBackPressHqandler$app_prodRelease() {
        return this.s;
    }

    @NotNull
    public final Bitmap getBitmapCustom() {
        Bitmap bitmap = this.bitmapCustom;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmapCustom");
        return null;
    }

    @NotNull
    public final BluetoothAdapter getBluetoothAdapter() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bluetoothAdapter");
        return null;
    }

    @Nullable
    public final BottomSheetDialogDashboardFirmwareUpdated getBottomSheetDialogDashboardFirmwareUpdated() {
        return this.V;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        return this.T;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getBottomSheetDialogTwoButtons() {
        return this.U;
    }

    @Nullable
    public final BottomSheetDialogTransparentFullScreen getBottomSheetTransparentDialog() {
        return this.A;
    }

    public final int getConnectedDeviceIcon() {
        if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smaf2_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_genesis_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_elevate_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_glory_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_ultima_vogue), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_seek), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_comet), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_velocity), false)) {
            return R.drawable.ic_band_connected_smaf2;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        return (companion.isMoyangDevice(this) || companion.isCZDevice(this) || companion.isMatrixDevice(this) || companion.isCADevice(this)) ? R.drawable.ic_band_connected_smaf2 : (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smas12_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_primia_voice), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_call_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_connect_pro), false)) ? R.drawable.ic_band_connected_smas12 : (companion.isIDODevice(this) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_connect_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.xtend_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.storm_connect_plus), false)) ? R.drawable.ic_band_connected_ido : R.drawable.ic_band_connected;
    }

    @NotNull
    public final ArrayList<DashboardModel> getDashboardModelList() {
        return this.e0;
    }

    public final boolean getDeviceFoundDuringScan() {
        return this.L;
    }

    public final int getDisconnectedDeviceIcon() {
        if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smaf2_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_genesis_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_elevate_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_glory_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_ultima_vogue), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_seek), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_comet), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_velocity), false)) {
            return R.drawable.ic_band_disconnected_smaf2;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isMoyangDevice(this) || companion.isCZDevice(this) || companion.isMatrixDevice(this) || companion.isCADevice(this)) {
            return R.drawable.ic_band_disconnected_smaf2;
        }
        if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smas12_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_primia_voice), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_call_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_connect_pro), false)) {
            return 2131231919;
        }
        if (companion.isIDODevice(this) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_connect_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.xtend_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.storm_connect_plus), false)) {
            return R.drawable.ic_band_disconnected_ido;
        }
        return 2131231919;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getEnableBluetoothDialog() {
        return this.v;
    }

    @NotNull
    public final Handler getFitnessBuddiesHandler() {
        return this.M;
    }

    public final boolean getFromMyWatchScreen() {
        return this.q;
    }

    public final boolean getFromOnboarding() {
        return this.Q;
    }

    @NotNull
    public final Handler getHandler() {
        return this.y;
    }

    public final void getHealthStatus() {
        CoveHealthStatusApi.getHealthStatus(new CoveApiListener<SGetHealthStatusRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$getHealthStatus$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SGetHealthStatusRes sGetHealthStatusRes) {
                SGetHealthStatusRes.DataBean.TemperatureBean temperature;
                if (sGetHealthStatusRes != null) {
                    try {
                        SGetHealthStatusRes.DataBean data = sGetHealthStatusRes.getData();
                        Boolean valueOf = (data == null || (temperature = data.getTemperature()) == null) ? null : Boolean.valueOf(temperature.isIsFlagged());
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.booleanValue()) {
                            ActivityDashboardNew.this.K1(false);
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    @NotNull
    public final File getImagePath() {
        File file = this.imagePath;
        if (file != null) {
            return file;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imagePath");
        return null;
    }

    @Nullable
    public final LocationManager getLocationManager() {
        return this.P;
    }

    @NotNull
    public final DataSyncViewModel getMDataSyncViewModel() {
        DataSyncViewModel dataSyncViewModel = this.mDataSyncViewModel;
        if (dataSyncViewModel != null) {
            return dataSyncViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
        return null;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getRecoveryDialog() {
        return this.S;
    }

    @NotNull
    public final CoroutineScope getScope() {
        return this.z;
    }

    @NotNull
    public final AlertWebDataModel getSelAssessemntAlertBeanForWebView(long j, double d) {
        AlertWebDataModel alertWebDataModel = new AlertWebDataModel();
        AlertWebDataModel.DataBean dataBean = new AlertWebDataModel.DataBean();
        AlertWebDataModel.DataBean.TemperatureBean temperatureBean = new AlertWebDataModel.DataBean.TemperatureBean();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        temperatureBean.setTime(AppUtils.formatDate(calendar.getTime(), UtilConstants.SERVER_TIME_FORMAT));
        String str = WatchfaceConstants.CELSIUS;
        temperatureBean.setBaseUnit(WatchfaceConstants.CELSIUS);
        Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this).isTemperatureUnitInFahrenheit();
        Intrinsics.checkNotNullExpressionValue(isTemperatureUnitInFahrenheit, "getInstance(this).isTemperatureUnitInFahrenheit");
        if (isTemperatureUnitInFahrenheit.booleanValue()) {
            str = WatchfaceConstants.FAHRENHEIT;
        }
        temperatureBean.setCurrentBaseUnit(str);
        temperatureBean.setValue((float) d);
        dataBean.setTemperature(temperatureBean);
        if (this.P != null && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            LocationManager locationManager = this.P;
            Location lastKnownLocation = locationManager != null ? locationManager.getLastKnownLocation("gps") : null;
            if (lastKnownLocation != null) {
                dataBean.setCurrentLocation(CollectionsKt__CollectionsKt.arrayListOf(Double.valueOf(lastKnownLocation.getLongitude()), Double.valueOf(lastKnownLocation.getLatitude())));
            }
        }
        alertWebDataModel.setData(dataBean);
        return alertWebDataModel;
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    @Nullable
    public ServerDataPullConfigModel getServerSyncConfig() {
        return this.H;
    }

    @Nullable
    public final Snackbar getSnackbar() {
        return this.B;
    }

    @Nullable
    public final Snackbar getSnackbarBatteryOptimization() {
        return this.R;
    }

    public final int getStatusConnectedIcon() {
        if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smaf2_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_genesis_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_elevate_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_glory_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_ultima_vogue), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_seek), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_comet), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_velocity), false)) {
            return R.drawable.ic_band_connected_status_icon_smaf2;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        return (companion.isMoyangDevice(this) || companion.isCZDevice(this) || companion.isMatrixDevice(this) || companion.isCADevice(this)) ? R.drawable.ic_band_connected_status_icon_smaf2 : (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smas12_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_primia_voice), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_call_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_connect_pro), false)) ? R.drawable.ic_band_connected_status_icon_smas12 : (companion.isIDODevice(this) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_connect_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.xtend_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.storm_connect_plus), false)) ? R.drawable.ic_band_connected_status_icon_ido : R.drawable.ic_band_connected_status_icon_smas12;
    }

    public final int getStatusConnectingIcon() {
        if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smaf2_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_genesis_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_elevate_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_wave_glory_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_ultima_vogue), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_seek), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_comet), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.sma_lunar_velocity), false)) {
            return R.drawable.ic_band_connecting_status_icon_smaf2;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        return (companion.isMoyangDevice(this) || companion.isCZDevice(this) || companion.isMatrixDevice(this) || companion.isCADevice(this)) ? R.drawable.ic_band_connecting_status_icon_smaf2 : (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smas12_device), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_primia_voice), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_call_pro), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.cy1_loop_connect_pro), false)) ? R.drawable.ic_band_connecting_status_icon_smas12 : (companion.isIDODevice(this) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.wave_connect_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.xtend_call_plus), false) || kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.storm_connect_plus), false)) ? R.drawable.ic_band_connecting_status_icon_ido : R.drawable.ic_band_connecting_status_icon_smas12;
    }

    public final int getStoragePermission() {
        return this.K;
    }

    @Nullable
    public final SubscriptionDialog getSubscriptionDialog() {
        return this.I;
    }

    @Nullable
    public final SyncTroubleshootFragment getSyncTroubleshootFragment() {
        return this.Y;
    }

    public final int getTabPositionBasedOnDevice(@NotNull HealthNavigationElement healthNavigationElement) {
        Intrinsics.checkNotNullParameter(healthNavigationElement, "healthNavigationElement");
        return ActivityDashboardNewHelper.INSTANCE.getVitalPosition(healthNavigationElement, this);
    }

    public final long getTimeStampDeviceFound() {
        return this.u;
    }

    @NotNull
    public final ViewModelActivityDashboardNew getViewmodel() {
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = this.viewmodel;
        if (viewModelActivityDashboardNew != null) {
            return viewModelActivityDashboardNew;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
        return null;
    }

    public final boolean isActive() {
        return this.O;
    }

    public final boolean isAppUpdateDialogShown() {
        return this.x;
    }

    public final boolean isFWUpdateDialogNeedToShow() {
        return this.w;
    }

    public final boolean isFromAppSettings() {
        return this.p;
    }

    public final void j1() {
        Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".provider", getImagePath());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(getResources().getString(R.string.image_jpeg));
        AppConstants appConstants = AppConstants.EMPTY_STRING;
        intent.putExtra("android.intent.extra.SUBJECT", appConstants.getValue());
        intent.putExtra("android.intent.extra.TEXT", appConstants.getValue());
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_via)));
        }
    }

    public final void k1() {
        if (SessionManager.getInstance(this).shouldWriteRemoteConfigAfterConnection()) {
            SessionManager.getInstance(this).setWriteRemoteConfigAfterConnection(false);
            q0();
            M1();
            return;
        }
        q0();
        M1();
    }

    public final void l0(final SoftwareUpdateRes softwareUpdateRes) {
        boolean z = true;
        if (!kotlin.text.m.equals(softwareUpdateRes.getData().getPerformAction(), AppConstants.BLOCK.getValue(), true) && !kotlin.text.m.equals(softwareUpdateRes.getData().getPerformAction(), AppConstants.NOTIFY.getValue(), true)) {
            u0(softwareUpdateRes);
        } else if (kotlin.text.m.equals(softwareUpdateRes.getData().getApp().getUpdateStatus(), AppConstants.ENFORCE.getValue(), true)) {
            this.x = true;
            String string = getString(R.string.app_update_title);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.app_update_title)");
            String message = softwareUpdateRes.getMessage();
            Intrinsics.checkNotNullExpressionValue(message, "appUpdateApiResponse.message");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, message);
            bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
            String string2 = getString(R.string.proceed);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.proceed)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.p
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.m0(SoftwareUpdateRes.this, this, view);
                }
            });
            bottomSheetDialogOneButtonTitleMessage.show();
        } else if (kotlin.text.m.equals(softwareUpdateRes.getData().getApp().getUpdateStatus(), AppConstants.OPTIONAL.getValue(), true)) {
            if (!FirmwareUtils.INSTANCE.canShowAppUpdateDialog(this)) {
                if (getIntent().getExtras() == null) {
                    return;
                }
                String stringExtra = getIntent().getStringExtra("FCM_NOTIFICATION_TYPE");
                if (stringExtra != null && stringExtra.length() != 0) {
                    z = false;
                }
                if (z || !kotlin.text.m.equals$default(getIntent().getStringExtra("FCM_NOTIFICATION_TYPE"), "FIRMWARE_UPDATE", false, 2, null)) {
                    return;
                }
            }
            String string3 = getString(R.string.app_update_title);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.app_update_title)");
            String message2 = softwareUpdateRes.getMessage();
            Intrinsics.checkNotNullExpressionValue(message2, "appUpdateApiResponse.message");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string3, message2, false, 8, null);
            bottomSheetDialogTwoButtons.setCancelable(false);
            String string4 = getString(R.string.proceed);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.proceed)");
            bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.n0(SoftwareUpdateRes.this, this, view);
                }
            });
            String string5 = getString(R.string.later);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.later)");
            bottomSheetDialogTwoButtons.setNegativeButton(string5, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.o0(ActivityDashboardNew.this, bottomSheetDialogTwoButtons, softwareUpdateRes, view);
                }
            });
            SessionManager.getInstance(this).saveAppUpdateShownTime(String.valueOf(new Date().getTime()));
            bottomSheetDialogTwoButtons.show();
        } else {
            u0(softwareUpdateRes);
        }
    }

    public final void l1() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.requestWindowFeature(1);
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_sync_warning_dialog, (ViewGroup) null);
        dialog.setContentView(inflate);
        ((ImageView) inflate.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDashboardNew.m1(dialog, view);
            }
        });
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkBox);
        ((TextView) inflate.findViewById(R.id.btn_stop)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDashboardNew.n1(dialog, this, checkBox, view);
            }
        });
        dialog.show();
    }

    public final void logAnalytics() {
        BleApi bleApi;
        if (!SessionManager.getInstance(this).isGuestUser() && !SessionManager.getInstance(this).isPairDeviceLater()) {
            if (this.Q && SessionManager.getInstance(this).isFromOnboarding()) {
                try {
                    BT3Utils.checkBT3ConnectionStatusAndLogEvent(this, Boolean.valueOf(this.Q));
                } catch (Exception unused) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ONBOARDING.getValue());
                    hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_USER_STATUS.getValue(), CleverTapConstants.CustomEventValues.LOGGEDIN.getValue());
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    hashMap.putAll(companion.getDeviceId(this));
                    hashMap.putAll(companion.getWatchDetails(this));
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_HP_VIEWED.getValue(), hashMap);
                }
            } else {
                BleApiManager bleApiManager = BleApiManager.getInstance(this);
                if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) == ConnectionStatus.DISCONNECTED) {
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put(CleverTapConstants.CustomEventProperties.HP_LOAD_USER_STATUS.getValue(), CleverTapConstants.CustomEventValues.LOGGEDIN.getValue());
                    hashMap2.put(CleverTapConstants.CustomEventProperties.HP_PAIRED.getValue(), CleverTapConstants.CustomEventValues.NO.getValue());
                    hashMap2.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ORGANIC.getValue());
                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                    hashMap2.putAll(companion2.getDeviceId(this));
                    companion2.logAnalyticsEvent(CleverTapConstants.EventName.HP_VIEWED.getValue(), hashMap2);
                }
            }
        }
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (themesUtils.isGuestUser(this) && !SessionManager.getInstance(this).isPairDeviceLater()) {
            if (this.Q) {
                HashMap<String, Object> hashMap3 = new HashMap<>();
                hashMap3.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ONBOARDING.getValue());
                hashMap3.put(CleverTapConstants.CustomEventProperties.HP_LOAD_USER_STATUS.getValue(), CleverTapConstants.CustomEventValues.GUEST.getValue());
                DeviceUtils.Companion companion3 = DeviceUtils.Companion;
                hashMap3.putAll(companion3.getDeviceId(this));
                companion3.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_HP_VIEWED.getValue(), hashMap3);
            } else {
                HashMap<String, Object> hashMap4 = new HashMap<>();
                hashMap4.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ORGANIC.getValue());
                hashMap4.put(CleverTapConstants.CustomEventProperties.HP_LOAD_USER_STATUS.getValue(), CleverTapConstants.CustomEventValues.GUEST.getValue());
                DeviceUtils.Companion companion4 = DeviceUtils.Companion;
                hashMap4.putAll(companion4.getDeviceId(this));
                companion4.logAnalyticsEvent(CleverTapConstants.EventName.HP_VIEWED.getValue(), hashMap4);
            }
        }
        if (!themesUtils.isPairDeviceLater(this) || SessionManager.getInstance(this).isGuestUser()) {
            return;
        }
        if (this.Q) {
            HashMap<String, Object> hashMap5 = new HashMap<>();
            hashMap5.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ONBOARDING.getValue());
            hashMap5.put(CleverTapConstants.CustomEventProperties.HP_LOAD_USER_STATUS.getValue(), CleverTapConstants.CustomEventValues.LOGGEDIN.getValue());
            hashMap5.put(CleverTapConstants.CustomEventProperties.HP_PAIRED.getValue(), CleverTapConstants.CustomEventValues.NO.getValue());
            DeviceUtils.Companion companion5 = DeviceUtils.Companion;
            hashMap5.putAll(companion5.getDeviceId(this));
            companion5.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_HP_VIEWED.getValue(), hashMap5);
            return;
        }
        HashMap<String, Object> hashMap6 = new HashMap<>();
        hashMap6.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ORGANIC.getValue());
        hashMap6.put(CleverTapConstants.CustomEventProperties.HP_LOAD_USER_STATUS.getValue(), CleverTapConstants.CustomEventValues.LOGGEDIN.getValue());
        hashMap6.put(CleverTapConstants.CustomEventProperties.HP_PAIRED.getValue(), CleverTapConstants.CustomEventValues.NO.getValue());
        DeviceUtils.Companion companion6 = DeviceUtils.Companion;
        hashMap6.putAll(companion6.getDeviceId(this));
        companion6.logAnalyticsEvent(CleverTapConstants.EventName.HP_VIEWED.getValue(), hashMap6);
    }

    public final void navigateToActivity() {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (getMDataSyncViewModel().checkIsSyncing()) {
                return;
            }
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCYDevice(this) && !companion.isCADevice(this) && !companion.isCZDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                finish();
                return;
            }
            Intent intent = new Intent(this, ActivityWatchFace.class);
            intent.setFlags(67108864);
            startActivity(intent);
            return;
        }
        Toast.makeText(this, getString(R.string.band_not_connected), 0).show();
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToAlexaConnect() {
        if (AppUtils.isNetConnected(this)) {
            showProgress();
            AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = this.D;
            if (alexaApiCallHandlerViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodelAlexaApiCallHandler");
                alexaApiCallHandlerViewModel = null;
            }
            alexaApiCallHandlerViewModel.callGetAlexaAccountLinkingStatusApi();
            return;
        }
        Toast.makeText(this, getString(R.string.please_check_your_internet), 0).show();
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToBluetoothCallingActivity() {
        ActivityDashboardNewHelper.INSTANCE.navigateToBluetoothCallingActivity(this);
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [T, android.app.Dialog] */
    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToBoatCoinsWebViewer(@NotNull BoatCoinsScreenCaller boatCoinsScreenCaller, @Nullable final String str) {
        Intrinsics.checkNotNullParameter(boatCoinsScreenCaller, "boatCoinsScreenCaller");
        SessionManager sessionManager = SessionManager.getInstance(this);
        if (str == null || str.length() == 0) {
            str = sessionManager.getCoinsHomeUrl();
        }
        if (sessionManager.isBoatCoinsFTUShown()) {
            if (AppUtils.isNetConnected(this)) {
                AppNavigator.Companion companion = AppNavigator.Companion;
                Intrinsics.checkNotNull(str);
                companion.navigateToBoatCoinsWebViewer(this, str, boatCoinsScreenCaller);
                return;
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? dialog = new Dialog(this, 16973829);
            objectRef.element = dialog;
            ((Dialog) dialog).requestWindowFeature(1);
            ((Dialog) objectRef.element).setContentView(R.layout.no_internet_message);
            Button button = (Button) ((Dialog) objectRef.element).findViewById(R.id.btn_retry);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.f
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDashboardNew.O0(ActivityDashboardNew.this, str, objectRef, view);
                    }
                });
            }
            ((TextView) ((Dialog) objectRef.element).findViewById(R.id.btn_home)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.P0(Ref.ObjectRef.this, view);
                }
            });
            ((Dialog) objectRef.element).show();
            return;
        }
        Intent intent = new Intent(this, ActivityBoatCoinsFTU.class);
        intent.putExtra(AppConstants.SCREEN_NAME.getValue(), boatCoinsScreenCaller.name());
        startActivity(intent);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToBuildFitnessPlan() {
        startActivity(new Intent(this, ActivityPreparationPlan.class));
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToFitnessBuddies() {
        this.M.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.i0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDashboardNew.Q0(ActivityDashboardNew.this);
            }
        }, 200L);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToFitnessOverview() {
        takeToScreen(HealthNavigationElement.HEALTH);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToFitnessVital(@NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.HEART_RATE.name())) {
            takeToScreen(HealthNavigationElement.HEART_RATE);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.TEMPERATURE.name())) {
            takeToScreen(HealthNavigationElement.TEMPERATURE);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.ENERGY_METER.name())) {
            takeToScreen(HealthNavigationElement.ENERGY_METER);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.NBR.name())) {
            takeToScreen(HealthNavigationElement.RESPIRATORY_RATE);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.HRV.name())) {
            takeToScreen(HealthNavigationElement.HRV);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.STRESS.name())) {
            takeToScreen(HealthNavigationElement.STRESS);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.SPO2.name())) {
            takeToScreen(HealthNavigationElement.SPO2);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.AMBIENT_SOUND.name())) {
            takeToScreen(HealthNavigationElement.AMBIENT_SOUND);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.SLEEP.name())) {
            takeToScreen(HealthNavigationElement.SLEEP);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.STEPS.name())) {
            takeToScreen(HealthNavigationElement.STEPS);
        } else if (Intrinsics.areEqual(fitnessVitalsType, FitnessVitalsType.BP.name())) {
            takeToScreen(HealthNavigationElement.BP);
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToLogin() {
        AppNavigator.Companion.navigateToLogin(this);
        finish();
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToNavigationScreen() {
        Type type = new TypeToken<NavigationDisclaimerData>() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$navigateToNavigationScreen$navigationDisclaimerDataType$1
        }.getType();
        Object fromJson = new Gson().fromJson(SessionManager.getInstance(this).getNavigationDiscliamerData(), type);
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(navigati…gationDisclaimerDataType)");
        NavigationDisclaimerData navigationDisclaimerData = (NavigationDisclaimerData) fromJson;
        if (AppUtils.isNetConnected(this)) {
            showProgress();
            getViewmodel().checkNavigationDisclaimerAcceptance(navigationDisclaimerData.getVersion(), new b());
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet), 0).show();
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToNotificationActivity() {
        ActivityDashboardNewHelper.INSTANCE.navigateToNotificationActivity(this);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToOnGoingFitnessPlan() {
        if (!new com.coveiot.android.activitymodes.preference.PreferenceManager(this).isModeFromApp()) {
            startActivity(new Intent(this, ActivityInfo.class));
        } else {
            startActivity(new Intent(this, ActivityPlanDetails.class));
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToOneKActivity() {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this)) {
                if (!SyncManager.getInstance().isSyncInProgress()) {
                    if (new com.coveiot.android.activitymodes.preference.PreferenceManager(this).getShouldShowActivitySyncDialog()) {
                        l1();
                        return;
                    } else {
                        startActivity(new Intent(this, OneKActivity.class));
                        return;
                    }
                }
                Toast.makeText(this, (int) R.string.syncing_please_wait, 0).show();
                return;
            }
            showNoInternetDialog();
            return;
        }
        o1();
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToPairDevice() {
        AppNavigator.Companion.navigateToPairYourDevice(this);
        finish();
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToProfileActivity() {
        startActivity(new Intent(this, ActivityProfileLanding.class));
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToSOSSettings(@NotNull String ctSource) {
        Intrinsics.checkNotNullParameter(ctSource, "ctSource");
        LogHelper.d("CALL", "Navigate to SOS settings");
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            showWatchDisconnectedDialog(this);
        } else if (AppUtils.isNetConnected(this)) {
            M0(ctSource);
            if (SessionManager.getInstance(this).getSOSConfig() != null) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_CARD_CLICK.getValue());
                analyticsLog.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                startActivity(new Intent(this, ActivitySOS.class));
                return;
            }
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_CARD_CLICK.getValue());
            analyticsLog2.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.SOS_FTU.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            startActivity(new Intent(this, ActivitySOSSettings.class));
        } else {
            Toast.makeText(this, getString(R.string.please_check_your_internet), 0).show();
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToSportsActivity() {
        ActivityDashboardNewHelper.INSTANCE.navigateToSportsActivity(this);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToTapAndPay() {
        if (AppUtils.isNetConnected(this)) {
            startActivity(new Intent(this, ActivityRegisteredProductSummary.class));
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_check_your_internet), 0).show();
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToWatchSettingsActivity(boolean z) {
        this.q = z;
        Navigation.findNavController(this, R.id.mainNavigationFragment).navigate(R.id.fragmentSettings);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_MORE.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BOTTOM_MENU_MORE_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToWatchfaceScreen(@NotNull WatchfaceScreenCaller watchfaceScreenCaller) {
        Intrinsics.checkNotNullParameter(watchfaceScreenCaller, "watchfaceScreenCaller");
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (!getMDataSyncViewModel().checkIsSyncing()) {
                Intent intent = new Intent(this, ActivityWatchFace.class);
                intent.putExtra(com.coveiot.android.watchfaceui.constants.Constants.SCREEN_TYPE.getValue(), watchfaceScreenCaller.name());
                startActivity(intent);
                return;
            }
            Toast.makeText(this, getString(R.string.syncing_please_wait), 0).show();
            return;
        }
        Toast.makeText(this, getString(R.string.band_not_connected), 0).show();
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Integer] */
    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToWatchfaceStudioActivity() {
        if (AppUtils.isNetConnected(this)) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? watchFaceMaxAllowed = SessionManager.getInstance(this).getWatchFaceMaxAllowed();
            objectRef.element = watchFaceMaxAllowed;
            Integer num = (Integer) watchFaceMaxAllowed;
            if (num != null && num.intValue() == -1) {
                ActivityDashboardNewHelper.INSTANCE.navigateToWatchfaceStudioActivity(this);
                return;
            }
            showProgress();
            ActivityDashboardNewHelper.INSTANCE.getDiyWatchFaceLists(this, new SuccessResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$navigateToWatchfaceStudioActivity$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$navigateToWatchfaceStudioActivity$1$onError$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ ActivityDashboardNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(ActivityDashboardNew activityDashboardNew, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = activityDashboardNew;
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
                            if (!this.this$0.isFinishing()) {
                                this.this$0.dismissProgress();
                                ActivityDashboardNew activityDashboardNew = this.this$0;
                                Toast.makeText(activityDashboardNew, activityDashboardNew.getString(R.string.something_went_wrong), 0).show();
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$navigateToWatchfaceStudioActivity$1$onSuccess$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Ref.ObjectRef<Integer> $maxAllowed;
                    public int label;
                    public final /* synthetic */ ActivityDashboardNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(ActivityDashboardNew activityDashboardNew, Ref.ObjectRef<Integer> objectRef, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = activityDashboardNew;
                        this.$maxAllowed = objectRef;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$maxAllowed, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            if (!this.this$0.isFinishing()) {
                                this.this$0.dismissProgress();
                                Integer diyWatchFaceListItems = SessionManager.getInstance(this.this$0).getDiyWatchFaceListItems();
                                Intrinsics.checkNotNullExpressionValue(diyWatchFaceListItems, "getInstance(this@Activit…ew).diyWatchFaceListItems");
                                int intValue = diyWatchFaceListItems.intValue();
                                Integer maxAllowed = this.$maxAllowed.element;
                                Intrinsics.checkNotNullExpressionValue(maxAllowed, "maxAllowed");
                                if (intValue < maxAllowed.intValue()) {
                                    ActivityDashboardNewHelper.INSTANCE.navigateToWatchfaceStudioActivity(this.this$0);
                                } else {
                                    ActivityDashboardNew activityDashboardNew = this.this$0;
                                    String string = activityDashboardNew.getString(R.string.max_exceeded);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ui.R.string.max_exceeded)");
                                    String string2 = this.this$0.getString(R.string.max_info);
                                    Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…faceui.R.string.max_info)");
                                    activityDashboardNew.showBottomSheetDialog(activityDashboardNew, string, string2);
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onError(@Nullable String str) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityDashboardNew.this), Dispatchers.getMain(), null, new a(ActivityDashboardNew.this, null), 2, null);
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onSuccess() {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityDashboardNew.this), Dispatchers.getMain(), null, new b(ActivityDashboardNew.this, objectRef, null), 2, null);
                }
            });
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void navigateToWellnessCrew() {
        Intent intent = new Intent(this, ManageHealthBuddiesActivity.class);
        intent.putExtra(Constants.EXTRA_GUARDIAN_TAB_POSITION.getValue(), 0);
        startActivity(intent);
    }

    public final void o1() {
        AlertDialog alertDialog = this.c0;
        if (alertDialog != null) {
            Intrinsics.checkNotNull(alertDialog);
            if (alertDialog.isShowing()) {
                AlertDialog alertDialog2 = this.c0;
                Intrinsics.checkNotNull(alertDialog2);
                alertDialog2.dismiss();
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_phone_not_connected, (ViewGroup) null);
        this.d0 = inflate;
        builder.setView(inflate);
        View view = this.d0;
        Intrinsics.checkNotNull(view);
        AlertDialog create = builder.create();
        this.c0 = create;
        Intrinsics.checkNotNull(create);
        create.show();
        ((Button) view.findViewById(R.id.try_again_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.t0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ActivityDashboardNew.p1(ActivityDashboardNew.this, view2);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.ActivityDataSyncListner
    public void onActivityDataSyncComplete() {
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isDeleteGpsDataSupported()) {
            LogHelper.d(this.J, "deleteActivityData");
            getViewmodel().deleteGpsDataFromWatch();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            if (i == g0) {
                LogHelper.d("Alert onActivityResult", "Request Code " + i + " result Code" + i2);
                if (i2 != -1 || intent == null) {
                    return;
                }
                LogHelper.d("Alert onActivityResult", "Request Code " + i + " result Code" + i2 + " data " + intent.getData());
                if (intent.hasExtra(l0)) {
                    SGetHealthStatusRes sGetHealthStatusRes = (SGetHealthStatusRes) new Gson().fromJson(intent.getStringExtra(l0), (Class<Object>) SGetHealthStatusRes.class);
                }
                String str = k0;
                if (intent.hasExtra(str) && intent.getBooleanExtra(str, false)) {
                    K1(true);
                }
            } else if (i == h0) {
                StatusApiDataHelper.Companion.getInstance(this).saveStatusDataToServer(true);
                K1(false);
            } else if (i == i0) {
                a1();
                if (i2 == -1) {
                    syncData();
                }
            } else if (i == j0 && i2 == -1) {
                String string = getString(R.string.watchface_created);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watchface_created)");
                final BottomSheetDialogOneButtonTitleCustomRedMessageWithClose bottomSheetDialogOneButtonTitleCustomRedMessageWithClose = new BottomSheetDialogOneButtonTitleCustomRedMessageWithClose(this, string, "");
                bottomSheetDialogOneButtonTitleCustomRedMessageWithClose.setCancelable(false);
                bottomSheetDialogOneButtonTitleCustomRedMessageWithClose.setHyperLinkAction(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$onActivityResult$1
                    @Override // android.view.View.OnClickListener
                    public void onClick(@Nullable View view) {
                        BottomSheetDialogOneButtonTitleCustomRedMessageWithClose.this.dismiss();
                        this.navigateToActivity();
                    }
                });
                String string2 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleCustomRedMessageWithClose.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.j
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDashboardNew.R0(BottomSheetDialogOneButtonTitleCustomRedMessageWithClose.this, view);
                    }
                });
                bottomSheetDialogOneButtonTitleCustomRedMessageWithClose.getCloseIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.x
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDashboardNew.S0(view);
                    }
                });
                bottomSheetDialogOneButtonTitleCustomRedMessageWithClose.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        String str;
        String str2;
        String str3;
        super.onCreate(bundle);
        setContentView(R.layout.activity_dashboard);
        if (SessionManager.getInstance(this).getAxTrackerId() == null) {
            Dashboard2Utils.Companion.getUserProfile(this);
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isSmaDevice(this) && !companion.isSmaJieieDevice(this)) {
            SmaMigrationUtils.INSTANCE.setBleDeviceInfo(this);
        }
        ShipBook.Companion companion2 = ShipBook.Companion;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        ShipBook.Companion.start$default(companion2, application, AppConstants.SHIPBOOK_APP_ID.getValue(), AppConstants.SHIPBOOK_APP_KEY.getValue(), null, null, 24, null);
        if (getIntent().hasExtra("FROM_ONBOARDING")) {
            this.Q = getIntent().getBooleanExtra("FROM_ONBOARDING", false);
        }
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(firebaseAnalytics, "getInstance(this)");
        this.D = (AlexaApiCallHandlerViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AlexaApiCallHandlerViewModel.class);
        this.E = (AutoHrViewModel) ViewModelProviders.of(this).get(AutoHrViewModel.class);
        this.W = new FitnessChallengeSyncViewModel(this);
        this.X = new SOSSettingsViewmodel(this);
        String str4 = null;
        if (!SessionManager.getInstance(this).isAnalyticsUserPropertiesUpdated()) {
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            String userDeviceID = com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserDeviceID();
            firebaseAnalytics.setUserId(String.valueOf(com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserId()));
            firebaseAnalytics.setUserProperty(FirebaseEventParams.UserProperties.CUSTOMER_ID.getValue(), getString(R.string.cove_client_id));
            String value = FirebaseEventParams.UserProperties.USER_DEVICE_ID.getValue();
            String lowerCase = userDeviceID.toString().toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            firebaseAnalytics.setUserProperty(value, lowerCase);
            if (bleDeviceInfo != null && bleDeviceInfo.getmModelNumber() != null) {
                String value2 = FirebaseEventParams.UserProperties.DEVICE_MODEL.getValue();
                String str5 = bleDeviceInfo.getmModelNumber();
                Intrinsics.checkNotNullExpressionValue(str5, "deviceInfo.getmModelNumber()");
                String lowerCase2 = str5.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                firebaseAnalytics.setUserProperty(value2, lowerCase2);
                SessionManager.getInstance(this).setAnalyticsUserPropertiesUpdated(true);
            } else {
                String modelNumberActual = companion.getModelNumberActual(this);
                if (bleDeviceInfo == null) {
                    if (modelNumberActual != null) {
                        BleDeviceInfo bleDeviceInfo2 = BleDeviceInfo.getInstance();
                        Intrinsics.checkNotNull(bleDeviceInfo2);
                        bleDeviceInfo2.setmModelNumber(modelNumberActual);
                        SessionManager.getInstance(this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo2));
                        String value3 = FirebaseEventParams.UserProperties.DEVICE_MODEL.getValue();
                        String str6 = bleDeviceInfo2.getmModelNumber();
                        Intrinsics.checkNotNullExpressionValue(str6, "deviceInfo.getmModelNumber()");
                        String lowerCase3 = str6.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                        firebaseAnalytics.setUserProperty(value3, lowerCase3);
                        SessionManager.getInstance(this).setAnalyticsUserPropertiesUpdated(true);
                    }
                } else if (bleDeviceInfo.getmModelNumber() == null && modelNumberActual != null) {
                    bleDeviceInfo.setmModelNumber(modelNumberActual);
                    SessionManager.getInstance(this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    String value4 = FirebaseEventParams.UserProperties.DEVICE_MODEL.getValue();
                    String str7 = bleDeviceInfo.getmModelNumber();
                    if (str7 != null) {
                        str3 = str7.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).toLowerCase()");
                    } else {
                        str3 = null;
                    }
                    firebaseAnalytics.setUserProperty(value4, str3);
                    SessionManager.getInstance(this).setAnalyticsUserPropertiesUpdated(true);
                }
            }
        }
        BleDeviceInfo bleDeviceInfo3 = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        if (bleDeviceInfo3 != null) {
            if (bleDeviceInfo3.getFirmwareRevision() != null) {
                String value5 = FirebaseEventParams.UserProperties.CV_DVC_FW_VER.getValue();
                String firmwareRevision = bleDeviceInfo3.getFirmwareRevision();
                if (firmwareRevision != null) {
                    str2 = firmwareRevision.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase()");
                } else {
                    str2 = null;
                }
                firebaseAnalytics.setUserProperty(value5, str2);
            }
            if (bleDeviceInfo3.getmDeviceName() != null) {
                String value6 = FirebaseEventParams.UserProperties.CV_DVC_FW_VER.getValue();
                String str8 = bleDeviceInfo3.getmDeviceName();
                if (str8 != null) {
                    str = str8.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
                } else {
                    str = null;
                }
                firebaseAnalytics.setUserProperty(value6, str);
            }
        }
        String value7 = FirebaseEventParams.UserProperties.CV_PHN_CHIPSET.getValue();
        String str9 = Build.BOARD;
        if (str9 != null) {
            str4 = str9.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String).toLowerCase()");
        }
        firebaseAnalytics.setUserProperty(value7, str4);
        Dashboard2Utils.Companion.loadScanDeviceOnDisconnectConfiguration(this);
        WorkoutSessionRepository.Companion.getInstance(this).setActivityDataSyncListner(this);
        if (BleApiManager.getInstance(this).getBleApi() != null) {
            new com.coveiot.android.activitymodes.preference.PreferenceManager(this).setConnectedDeviceMacAddress(BleApiManager.getInstance(this).getBleApi().getMacAddress());
            new com.coveiot.android.activitymodes.preference.PreferenceManager(this).setSampleDataSupported(BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isSampleDataSupportedInSportMode());
            new com.coveiot.android.activitymodes.preference.PreferenceManager(this).setSportModeHistorySupported(BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isSportsModeHistorySupported());
        }
        Object systemService = getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.P = (LocationManager) systemService;
        CoveEventBusManager.getInstance().getEventBus().register(this);
        if (K0()) {
            Intent intent = new Intent(this, ActivityWorkoutSession.class);
            intent.putExtra(WorkoutConstants.ACTIVITY_MODE, ActivityMode.RUN);
            intent.putExtra(WorkoutConstants.INDOOR_OUTDOOR, IndoorOutdoor.OUTDOOR);
            startActivity(intent);
        }
        setViewmodel((ViewModelActivityDashboardNew) ViewModelProviders.of(this).get(ViewModelActivityDashboardNew.class));
        setMDataSyncViewModel((DataSyncViewModel) ViewModelProviders.of(this, new com.coveiot.android.dashboard2.ViewModelFactory(this)).get(DataSyncViewModel.class));
        getViewmodel().setMListener(this);
        getMDataSyncViewModel().setSyncTroubleShoutListener(this);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this)) {
            getHealthStatus();
            getViewmodel().getConfigUrlsFromServer();
            getViewmodel().updateRegistrationToServer();
            getViewmodel().saveSPO2DataToServer();
            getViewmodel().getSportsDisclaimerConsentApi();
        }
        Intent intent2 = getIntent();
        if (intent2 != null) {
            f1(intent2);
        }
        Object systemService2 = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothAdapter adapter = ((BluetoothManager) systemService2).getAdapter();
        Intrinsics.checkNotNullExpressionValue(adapter, "getSystemService(Context…BluetoothManager).adapter");
        setBluetoothAdapter(adapter);
        if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
            stopService(new Intent(this, SportsService.class));
            stopService(new Intent(this, SoccerSportsServiceNew.class));
        } else if (SportsPreference.Companion.isNotificationEnabled(this)) {
            t0();
        }
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this).getDeviceModelBean();
        if (deviceModelBean != null && deviceModelBean.getRemoteFrameworkSupported() != null) {
            Boolean remoteFrameworkSupported = deviceModelBean.getRemoteFrameworkSupported();
            Intrinsics.checkNotNullExpressionValue(remoteFrameworkSupported, "deviceModelBean.remoteFrameworkSupported");
            if (remoteFrameworkSupported.booleanValue()) {
                s0();
            }
        }
        if (!themesUtils.isGuestUser(this) && !themesUtils.isPairDeviceLater(this)) {
            D0();
        }
        logAnalytics();
        Application application2 = getApplication();
        Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
        ((GenericBandApplication) application2).registerPhoneStateChangeListener(true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CoroutineScopeKt.cancel$default(this.z, null, 1, null);
        this.G.clear();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Subscribe
    public final void onGetCurrentStepsCmdRecieved(@NotNull GetCurrentSteps GetCurrentSteps) {
        Intrinsics.checkNotNullParameter(GetCurrentSteps, "GetCurrentSteps");
        BleBaseRequest build = new TodaysStepsDataRequest.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().build()");
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isFitnessValueCommandSupported()) {
            build = new TodaysFitnessDataRequest.Builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().build()");
        }
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this).getBleApi().getData(build, new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$onGetCurrentStepsCmdRecieved$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    CoveEventBusManager.getInstance().getEventBus().post(new OnBleCommandError(ActivityDashboardNew.this.getString(R.string.daily_steps_failure_msg)));
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    DeviceSupportedFeatures deviceSupportedFeatures;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof TodaysStepsResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.TodaysStepsResponse");
                        TodaysStepsResponse todaysStepsResponse = (TodaysStepsResponse) responseData;
                        if (todaysStepsResponse.getTodaysStepsData() != null) {
                            TodaysStepsData todaysStepsData = todaysStepsResponse.getTodaysStepsData();
                            Intrinsics.checkNotNull(todaysStepsData);
                            int totalSteps = todaysStepsData.getTotalSteps();
                            int i = ProfileRepository.getInstance().getLatestProfileData(ActivityDashboardNew.this).walkStrideLength;
                            String height = SessionManager.getInstance(ActivityDashboardNew.this).getUserDetails().getHeight();
                            Intrinsics.checkNotNullExpressionValue(height, "getInstance(this@Activit…rdNew).userDetails.height");
                            int parseInt = Integer.parseInt(height);
                            String weight = SessionManager.getInstance(ActivityDashboardNew.this).getUserDetails().getWeight();
                            Intrinsics.checkNotNullExpressionValue(weight, "getInstance(this@Activit…rdNew).userDetails.weight");
                            float calculateCalories = (float) AppUtils.calculateCalories(totalSteps, parseInt, (int) Double.parseDouble(weight), i);
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            if (companion.isCZDevice(ActivityDashboardNew.this) || companion.isCADevice(ActivityDashboardNew.this)) {
                                PayUtils payUtils = PayUtils.INSTANCE;
                                String height2 = SessionManager.getInstance(ActivityDashboardNew.this).getUserDetails().getHeight();
                                Intrinsics.checkNotNullExpressionValue(height2, "getInstance(this@Activit…rdNew).userDetails.height");
                                int parseInt2 = Integer.parseInt(height2);
                                String weight2 = SessionManager.getInstance(ActivityDashboardNew.this).getUserDetails().getWeight();
                                Intrinsics.checkNotNullExpressionValue(weight2, "getInstance(this@Activit…rdNew).userDetails.weight");
                                calculateCalories = (float) payUtils.calculateCaloriesForCZ(totalSteps, parseInt2, (int) Double.parseDouble(weight2), i);
                            }
                            String height3 = SessionManager.getInstance(ActivityDashboardNew.this).getUserDetails().getHeight();
                            Intrinsics.checkNotNullExpressionValue(height3, "getInstance(this@Activit…rdNew).userDetails.height");
                            int calculateDistance = AppUtils.calculateDistance(totalSteps, Integer.parseInt(height3), i);
                            BleApi bleApi = BleApiManager.getInstance(ActivityDashboardNew.this).getBleApi();
                            Boolean valueOf = (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isFitnessValueCommandSupported());
                            Intrinsics.checkNotNull(valueOf);
                            if (valueOf.booleanValue()) {
                                TodaysStepsData todaysStepsData2 = todaysStepsResponse.getTodaysStepsData();
                                Float valueOf2 = todaysStepsData2 != null ? Float.valueOf((float) todaysStepsData2.getTotalCalories()) : null;
                                Intrinsics.checkNotNull(valueOf2);
                                calculateCalories = valueOf2.floatValue();
                                TodaysStepsData todaysStepsData3 = todaysStepsResponse.getTodaysStepsData();
                                Integer valueOf3 = todaysStepsData3 != null ? Integer.valueOf(todaysStepsData3.getTotalDistance()) : null;
                                Intrinsics.checkNotNull(valueOf3);
                                calculateDistance = valueOf3.intValue();
                            }
                            CoveEventBusManager.getInstance().getEventBus().post(new CurrentStepsValue(totalSteps, calculateDistance, calculateCalories));
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
        CoveEventBusManager.getInstance().getEventBus().post(new OnDeviceDisconnected());
        CoveEventBusManager.getInstance().getEventBus().post(new OnWorkoutDeviceDisconnected());
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(@Nullable Intent intent) {
        Bundle extras;
        if (K0()) {
            Intent intent2 = new Intent(this, ActivityWorkoutSession.class);
            intent2.putExtra(WorkoutConstants.ACTIVITY_MODE, ActivityMode.RUN);
            intent2.putExtra(WorkoutConstants.INDOOR_OUTDOOR, IndoorOutdoor.OUTDOOR);
            startActivity(intent2);
        } else if (intent == null || (extras = intent.getExtras()) == null) {
        } else {
            f1(intent);
            String string = extras.getString(AppConstants.IS_CUSTOMNOTIFICATION.getValue());
            if (string != null) {
                if (string.length() > 0) {
                    showCustomNotificationDialog(string);
                    super.onNewIntent(intent);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.coveiot.android.theme.GenericMessageDialog, T] */
    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionDenied(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            String string = getString(R.string.storage_permission);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.storage_permission)");
            String string2 = getString(R.string.storage_permission_required);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.storage_permission_required)");
            ?? genericMessageDialog = new GenericMessageDialog(this, string, string2);
            objectRef.element = genericMessageDialog;
            ((TextView) ((GenericMessageDialog) genericMessageDialog).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.u
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.U0(Ref.ObjectRef.this, view);
                }
            });
            ((TextView) ((GenericMessageDialog) objectRef.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.v
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.V0(Ref.ObjectRef.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            ((GenericMessageDialog) objectRef.element).show();
        }
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionDisabled(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            String string = getString(R.string.storage_permission);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.storage_permission)");
            String string2 = getString(R.string.storage_permission_required);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.storage_permission_required)");
            final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this, string, string2);
            ((TextView) genericMessageDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.m
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.W0(GenericMessageDialog.this, view);
                }
            });
            ((TextView) genericMessageDialog.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.n
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDashboardNew.X0(GenericMessageDialog.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            genericMessageDialog.show();
        }
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionSuccess(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            saveBitmap(getBitmapCustom());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        boolean z = false;
        if (i == this.r) {
            if (!(grantResults.length == 0)) {
                int length = grantResults.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = true;
                        break;
                    } else if (grantResults[i2] != 0) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    BleApiManager.getInstance(this).getBleApi().restartService();
                } else {
                    String string = getString(R.string.bluetooth_permission_required);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_permission_required)");
                    D1(string);
                }
            }
        } else if (i == FragmentScanningDeviceKt.getLOCATION_PERMISSION_REQUEST_CODE()) {
            if (!(grantResults.length == 0)) {
                int length2 = grantResults.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        z = true;
                        break;
                    } else if (grantResults[i3] != 0) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (z) {
                    if (Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                        BleApiManager.getInstance(this).getBleApi().restartService();
                    } else {
                        String string2 = getString(R.string.location_permission_required);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.location_permission_required)");
                        D1(string2);
                    }
                }
            }
        }
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.mainNavigationFragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        ((NavHostFragment) findFragmentById).onRequestPermissionsResult(i, permissions, grantResults);
    }

    @Override // android.app.Activity
    public void onRestart() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        super.onRestart();
        if (!AppUtils.checkIfLocationPermissionExists(this) || (bottomSheetDialogOneButtonOneTitle = this.C) == null) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        super.onResume();
        FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(FirebaseEventParams.ScreenName.MAIN_HOME_DASHBOARD.getValue());
        if (PayUtils.INSTANCE.isManufacturerNeedBatteryOptimizationDelay(this)) {
            this.y.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.m0
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityDashboardNew.Y0(ActivityDashboardNew.this);
                }
            }, 1000L);
        } else {
            y0();
        }
        if (this.p && Build.VERSION.SDK_INT >= 31) {
            if (AppUtils.checkIfLocationPermissionExists(this) && Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                BleApiManager.getInstance(this).getBleApi().restartService();
            } else if (!AppUtils.checkIfLocationPermissionExists(this) || !Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                if (!AppUtils.checkIfLocationPermissionExists(this)) {
                    String string = getString(R.string.location_permission_required);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                    D1(string);
                } else if (!Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                    String string2 = getString(R.string.bluetooth_permission_required);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_permission_required)");
                    D1(string2);
                }
            }
        }
        if (!getBluetoothAdapter().isEnabled() || (bottomSheetDialogTwoButtons = this.v) == null) {
            return;
        }
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        if (bottomSheetDialogTwoButtons.isShowing()) {
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.v;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            bottomSheetDialogTwoButtons2.dismiss();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.O = true;
        if (SessionManager.getInstance(this).isForceFirmwareUpdated()) {
            return;
        }
        checkAppUpgradeApi();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.O = false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.mainNavigationFragment).navigateUp();
    }

    @Subscribe
    public final void onWachFactoryReset(@NotNull WatchFactoryReset watchFactoryReset) {
        Intrinsics.checkNotNullParameter(watchFactoryReset, "watchFactoryReset");
        if (watchFactoryReset.isUnbindRequired()) {
            AppNavigator.Companion.navigateToBluetoothScanActivityAndClear(this);
        }
    }

    public final void p0(final WeatherResultListener weatherResultListener, final OnDynamicTabDataChangedListener onDynamicTabDataChangedListener) {
        LogHelper.i(this.J, "callRemoteConfigAPIForDynamicTabs start");
        CoveOnboarding.getV2RemoteConfiguration("1", new CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$callRemoteConfigAPIForDynamicTabs$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$callRemoteConfigAPIForDynamicTabs$1$onSuccess$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ OnDynamicTabDataChangedListener $dynamicTabDataChangeListener;
                public final /* synthetic */ WeatherResultListener $listner;
                public final /* synthetic */ SRemoteConfigResponse $p0;
                public int label;
                public final /* synthetic */ ActivityDashboardNew this$0;

                /* renamed from: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$callRemoteConfigAPIForDynamicTabs$1$a$a  reason: collision with other inner class name */
                /* loaded from: classes2.dex */
                public static final class C0274a extends Lambda implements Function1<UserRemoteConfigResponse, Unit> {
                    public final /* synthetic */ ActivityDashboardNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0274a(ActivityDashboardNew activityDashboardNew) {
                        super(1);
                        this.this$0 = activityDashboardNew;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(UserRemoteConfigResponse userRemoteConfigResponse) {
                        invoke2(userRemoteConfigResponse);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke2(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.model.server.UserRemoteConfigResponse r6) {
                        /*
                            r5 = this;
                            if (r6 == 0) goto La2
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean r0 = r6.getData()
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean$DataPull r0 = r0.getDataPull()
                            if (r0 == 0) goto La2
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean r0 = r6.getData()
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean$DataPull r0 = r0.getDataPull()
                            java.lang.String r0 = r0.getFromDate()
                            if (r0 == 0) goto L23
                            int r0 = r0.length()
                            if (r0 != 0) goto L21
                            goto L23
                        L21:
                            r0 = 0
                            goto L24
                        L23:
                            r0 = 1
                        L24:
                            java.lang.String r1 = "yyyy-MM-dd"
                            r2 = 0
                            if (r0 != 0) goto L45
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean r0 = r6.getData()     // Catch: java.lang.Exception -> L41
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean$DataPull r0 = r0.getDataPull()     // Catch: java.lang.Exception -> L41
                            java.lang.String r0 = r0.getFromDate()     // Catch: java.lang.Exception -> L41
                            java.util.Date r0 = com.coveiot.utils.utility.AppUtils.parseDate(r0, r1)     // Catch: java.lang.Exception -> L41
                            java.util.Calendar r3 = java.util.Calendar.getInstance()     // Catch: java.lang.Exception -> L41
                            r3.setTime(r0)     // Catch: java.lang.Exception -> L41
                            goto L46
                        L41:
                            r0 = move-exception
                            r0.printStackTrace()
                        L45:
                            r3 = r2
                        L46:
                            com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r0 = r5.this$0
                            com.coveiot.android.dashboard2.model.ServerDataPullConfigModel r4 = new com.coveiot.android.dashboard2.model.ServerDataPullConfigModel
                            if (r3 == 0) goto L54
                            java.util.Date r2 = r3.getTime()
                            java.lang.String r2 = com.coveiot.utils.utility.AppUtils.formatDate(r2, r1)
                        L54:
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean r6 = r6.getData()
                            com.coveiot.coveaccess.model.server.UserRemoteConfigResponse$DataBean$DataPull r6 = r6.getDataPull()
                            java.lang.String r6 = r6.getVersionTag()
                            r4.<init>(r2, r6)
                            com.coveiot.android.leonardo.dashboard.ActivityDashboardNew.access$setServerDataPullConfigModel$p(r0, r4)
                            com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r6 = r5.this$0
                            java.lang.String r6 = com.coveiot.android.leonardo.dashboard.ActivityDashboardNew.access$getTAG$p(r6)
                            java.lang.StringBuilder r0 = new java.lang.StringBuilder
                            r0.<init>()
                            java.lang.String r1 = "callRemoteConfigAPIForDynamicTabs success "
                            r0.append(r1)
                            com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r1 = r5.this$0
                            com.coveiot.android.dashboard2.model.ServerDataPullConfigModel r1 = com.coveiot.android.leonardo.dashboard.ActivityDashboardNew.access$getServerDataPullConfigModel$p(r1)
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                            java.lang.String r1 = r1.getFromDate()
                            r0.append(r1)
                            java.lang.String r1 = ", "
                            r0.append(r1)
                            com.coveiot.android.leonardo.dashboard.ActivityDashboardNew r1 = r5.this$0
                            com.coveiot.android.dashboard2.model.ServerDataPullConfigModel r1 = com.coveiot.android.leonardo.dashboard.ActivityDashboardNew.access$getServerDataPullConfigModel$p(r1)
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                            java.lang.String r1 = r1.getVersionTag()
                            r0.append(r1)
                            java.lang.String r0 = r0.toString()
                            com.coveiot.utils.utility.LogHelper.i(r6, r0)
                        La2:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$callRemoteConfigAPIForDynamicTabs$1.a.C0274a.invoke2(com.coveiot.coveaccess.model.server.UserRemoteConfigResponse):void");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(SRemoteConfigResponse sRemoteConfigResponse, ActivityDashboardNew activityDashboardNew, OnDynamicTabDataChangedListener onDynamicTabDataChangedListener, WeatherResultListener weatherResultListener, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$p0 = sRemoteConfigResponse;
                    this.this$0 = activityDashboardNew;
                    this.$dynamicTabDataChangeListener = onDynamicTabDataChangedListener;
                    this.$listner = weatherResultListener;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$p0, this.this$0, this.$dynamicTabDataChangeListener, this.$listner, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    SRemoteConfigResponse.SensAIBean sensAI;
                    SRemoteConfigResponse.SensAIBean.SensAICoach coach;
                    SRemoteConfigResponse.SensAIBean sensAI2;
                    SRemoteConfigResponse.SensAIBean.SensAICoach coach2;
                    SRemoteConfigResponse.DataBean.CoinsBean coins;
                    SRemoteConfigResponse.DataBean.CoinsBean coins2;
                    SRemoteConfigResponse.DataBean.CoinsBean.WebViewUrl webViewUrl;
                    SRemoteConfigResponse.DataBean.WatchFaceBean watchface;
                    SRemoteConfigResponse.DataBean.WatchFaceBean watchface2;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        SRemoteConfigResponse sRemoteConfigResponse = this.$p0;
                        if (sRemoteConfigResponse != null) {
                            if (!AppUtils.isEmpty(sRemoteConfigResponse.getData().getUi())) {
                                List<SRemoteConfigResponse.DataBean.UiBean> ui = this.$p0.getData().getUi();
                                ArrayList<DashboardModel> dashboardModelList = this.this$0.getDashboardModelList();
                                if (dashboardModelList != null) {
                                    dashboardModelList.clear();
                                }
                                for (SRemoteConfigResponse.DataBean.UiBean uiBean : ui) {
                                    if (uiBean.getPlaceholder() != null) {
                                        String placeholder = uiBean.getPlaceholder();
                                        Intrinsics.checkNotNullExpressionValue(placeholder, "uiBean.placeholder");
                                        if ((placeholder.length() > 0) && kotlin.text.m.equals(uiBean.getPlaceholder(), "main_menu", true) && uiBean.getScreenName() != null) {
                                            String screenName = uiBean.getScreenName();
                                            Intrinsics.checkNotNullExpressionValue(screenName, "uiBean.screenName");
                                            if ((screenName.length() > 0) && kotlin.text.m.equals(uiBean.getScreenName(), "main_home_dashboard", true) && uiBean.getElements() != null && uiBean.getElements().size() > 0) {
                                                for (SRemoteConfigResponse.DataBean.UiBean.ElementsBean elementsBean : uiBean.getElements()) {
                                                    DashboardModel dashboardModel = new DashboardModel();
                                                    dashboardModel.setTitle(elementsBean.getContent().getTitle().getText());
                                                    if (elementsBean.getContent().getSubtitle() != null) {
                                                        dashboardModel.setSubTitle(elementsBean.getContent().getSubtitle().getText());
                                                    }
                                                    dashboardModel.setIndex(elementsBean.getIndex());
                                                    dashboardModel.setElementsBean(elementsBean);
                                                    dashboardModel.setElementName(elementsBean.getElementName());
                                                    dashboardModel.setSportsResponseModel(this.$p0);
                                                    dashboardModel.setSportsIcon(elementsBean.getContent().getImage().getSrc());
                                                    if (!this.this$0.getDashboardModelList().contains(dashboardModel)) {
                                                        if (this.this$0.getDashboardModelList().size() > elementsBean.getIndex()) {
                                                            this.this$0.getDashboardModelList().add(elementsBean.getIndex(), dashboardModel);
                                                        } else {
                                                            this.this$0.getDashboardModelList().add(this.this$0.getDashboardModelList().size(), dashboardModel);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            OnDynamicTabDataChangedListener onDynamicTabDataChangedListener = this.$dynamicTabDataChangeListener;
                            ArrayList arrayList = null;
                            if (onDynamicTabDataChangedListener != null) {
                                ArrayList<DashboardModel> dashboardModelList2 = this.this$0.getDashboardModelList();
                                onDynamicTabDataChangedListener.onDynamicTabDataChanged(dashboardModelList2 != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) dashboardModelList2) : null);
                            }
                            if (PayUtils.INSTANCE.isWeatherFeatureSupported(this.this$0.getApplicationContext())) {
                                String apiKey = this.$p0.getData().getOpenWeatherMap().getApiKey();
                                Intrinsics.checkNotNullExpressionValue(apiKey, "p0.data.openWeatherMap.apiKey");
                                this.this$0.getViewmodel().callOpenWeatherApiKey(apiKey, this.$listner);
                            }
                            SRemoteConfigResponse.DataBean.WatchFaceBean watchface3 = this.$p0.getData().getWatchface();
                            String diyToolUrl = watchface3 != null ? watchface3.getDiyToolUrl() : null;
                            if (!AppUtils.isEmpty(diyToolUrl)) {
                                SessionManager.getInstance(this.this$0.getApplicationContext()).saveWatchFaceDiyUrl(diyToolUrl);
                            }
                            SRemoteConfigResponse.DataBean data = this.$p0.getData();
                            String diyToolCardImage = (data == null || (watchface2 = data.getWatchface()) == null) ? null : watchface2.getDiyToolCardImage();
                            if (!AppUtils.isEmpty(diyToolCardImage)) {
                                SessionManager.getInstance(this.this$0.getApplicationContext()).saveWatchFaceDiyToolCardImage(diyToolCardImage);
                            }
                            SRemoteConfigResponse.DataBean data2 = this.$p0.getData();
                            Integer diyMaxAllowed = (data2 == null || (watchface = data2.getWatchface()) == null) ? null : watchface.getDiyMaxAllowed();
                            if (diyMaxAllowed != null) {
                                SessionManager.getInstance(this.this$0.getApplicationContext()).saveWatchFaceMaxAllowed(diyMaxAllowed);
                            }
                            SRemoteConfigResponse.DataBean data3 = this.$p0.getData();
                            String home = (data3 == null || (coins2 = data3.getCoins()) == null || (webViewUrl = coins2.getWebViewUrl()) == null) ? null : webViewUrl.getHome();
                            if (!AppUtils.isEmpty(home)) {
                                SessionManager.getInstance(this.this$0.getApplicationContext()).saveCoinsHomeUrl(home);
                            }
                            SRemoteConfigResponse.DataBean data4 = this.$p0.getData();
                            String cardImage = (data4 == null || (coins = data4.getCoins()) == null) ? null : coins.getCardImage();
                            if (!AppUtils.isEmpty(cardImage)) {
                                SessionManager.getInstance(this.this$0.getApplicationContext()).saveCoinsCardImage(cardImage);
                            }
                            SRemoteConfigResponse.DataBean data5 = this.$p0.getData();
                            String cardImage2 = (data5 == null || (sensAI2 = data5.getSensAI()) == null || (coach2 = sensAI2.getCoach()) == null) ? null : coach2.getCardImage();
                            SRemoteConfigResponse.DataBean data6 = this.$p0.getData();
                            String bannerImage = (data6 == null || (sensAI = data6.getSensAI()) == null || (coach = sensAI.getCoach()) == null) ? null : coach.getBannerImage();
                            if (!AppUtils.isEmpty(cardImage2)) {
                                SessionManager.getInstance(this.this$0.getApplicationContext()).saveSensAICardImage(cardImage2);
                            }
                            if (!AppUtils.isEmpty(bannerImage)) {
                                SessionManager.getInstance(this.this$0.getApplicationContext()).saveSensAIBannerImage(bannerImage);
                            }
                            SRemoteConfigResponse.DataBean.AlexaBean alexaBean = this.$p0.getData().getAlexaBean();
                            if (alexaBean != null) {
                                ActivityDashboardNew activityDashboardNew = this.this$0;
                                List<SRemoteConfigResponse.DataBean.AlexaBean.Locale> locales = alexaBean.getLocales();
                                if (locales != null) {
                                    Intrinsics.checkNotNullExpressionValue(locales, "locales");
                                    if (!locales.isEmpty()) {
                                        arrayList = new ArrayList();
                                        for (SRemoteConfigResponse.DataBean.AlexaBean.Locale locale : locales) {
                                            AlexaLocale alexaLocale = new AlexaLocale(null, null, null, null, 15, null);
                                            alexaLocale.setLocale(locale.getLocale());
                                            alexaLocale.setLabel(locale.getLabel());
                                            alexaLocale.setHelpUrl(locale.getHelpUrl());
                                            alexaLocale.setExamplePhrases(locale.getExamplePhrases());
                                            arrayList.add(alexaLocale);
                                            if (SessionManager.getInstance(activityDashboardNew.getApplicationContext()).getSelectedAlexaLocale() == null && locale.isPrimary()) {
                                                SessionManager.getInstance(activityDashboardNew.getApplicationContext()).saveSelectedAlexaLocale(alexaLocale);
                                            }
                                        }
                                    }
                                }
                                SessionManager.getInstance(activityDashboardNew.getApplicationContext()).saveAlexaDetails(new AlexaDetails(alexaBean.getNativeAppUrl(), alexaBean.getLwaFallbackUrl(), arrayList));
                            }
                            if (this.$p0.getData().getLegalBean() != null) {
                                SessionManager sessionManager = SessionManager.getInstance(this.this$0);
                                List<SRemoteConfigResponse.DataBean.LegalBean.Doc> doc = this.$p0.getData().getLegalBean().getDoc();
                                Intrinsics.checkNotNullExpressionValue(doc, "p0.data.legalBean.doc");
                                for (SRemoteConfigResponse.DataBean.LegalBean.Doc doc2 : doc) {
                                    if (kotlin.text.m.equals(doc2.getType(), this.this$0.getResources().getString(R.string.eula), true)) {
                                        sessionManager.setLegalDocType(doc2.getType());
                                        sessionManager.setLegalDocUrl(doc2.getHtmlUrl());
                                        sessionManager.setLegalDocVersion(doc2.getVersion());
                                    }
                                    if (kotlin.text.m.equals(doc2.getType(), "PRIVACY_POLICY", true)) {
                                        sessionManager.setPrivacyPolicyDocUrl(doc2.getHtmlUrl());
                                    }
                                    if (kotlin.text.m.equals(doc2.getType(), "SPORTS_DISCLAIMER", true)) {
                                        sessionManager.setSportsDisclaimerDocUrl(doc2.getHtmlUrl());
                                    }
                                    if (kotlin.text.m.equals(doc2.getType(), "SOS_DISCLAIMER", true)) {
                                        sessionManager.setSOSDisclaimerURL(doc2.getHtmlUrl());
                                    }
                                }
                            }
                            if (DeviceUtils.Companion.isMigratedDevice(this.this$0)) {
                                this.this$0.getViewmodel().getDataPullFromUserRemoteConfigurationApi(new C0274a(this.this$0));
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = ActivityDashboardNew.this.J;
                LogHelper.i(str, "callRemoteConfigAPIForDynamicTabs failure");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SRemoteConfigResponse sRemoteConfigResponse) {
                String str;
                str = ActivityDashboardNew.this.J;
                LogHelper.i(str, "callRemoteConfigAPIForDynamicTabs success");
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(sRemoteConfigResponse, ActivityDashboardNew.this, onDynamicTabDataChangedListener, weatherResultListener, null), 2, null);
            }
        });
    }

    public final void q0() {
        if (UserDataManager.getInstance(this).isAutoHRIntervalSaved().booleanValue()) {
            return;
        }
        BleApiManager.getInstance(this).getBleApi().setUserSettings(new HeartRateTimeIntervalRequest.Builder(Integer.parseInt(AppConstants.DEFAULT_AUTO_HR_INTERVAL.getValue())).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$checkAndEnableAutoHRSettings$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(ActivityDashboardNew.this).setAutoHRIntervalSaved(Boolean.TRUE);
            }
        });
    }

    public final void r0() {
        if (UserDataManager.getInstance(this).isAutoHrEnabled() && PayUtils.INSTANCE.shouldDisableAutoHR(this) && canShowDisableAutoHRDialog()) {
            v1();
            UserDataManager.getInstance(this).saveTimestampDisableAutoHRDialogShown(BleApiManager.getInstance(this).getBleApi().getMacAddress(), System.currentTimeMillis());
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void registerForConnectionStatusUpdate(@Nullable ConnectionStatusUpdateListener connectionStatusUpdateListener) {
        if (connectionStatusUpdateListener == null || this.G.contains(connectionStatusUpdateListener)) {
            return;
        }
        this.G.add(connectionStatusUpdateListener);
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void registerForSyncInterruptionEvent(@Nullable SyncInterruptionListener syncInterruptionListener) {
        this.F = syncInterruptionListener;
    }

    public final void s0() {
        if (w0()) {
            return;
        }
        LogHelper.d(this.J, "checkAndStartRemoteCommandFrameworkService-> service is not running ++ ");
        N1();
    }

    public final void saveBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        setImagePath(new File(Environment.getExternalStorageDirectory().toString() + '/' + System.currentTimeMillis() + getResources().getString(R.string._share_image)));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getImagePath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            LogHelper.e(this.J, e.getMessage(), e);
        }
        j1();
    }

    public final void setActive(boolean z) {
        this.O = z;
    }

    public final void setAppUpdateDialogShown(boolean z) {
        this.x = z;
    }

    public final void setBackPressFlag$app_prodRelease(int i) {
        this.t = i;
    }

    public final void setBackPressHqandler$app_prodRelease(@NotNull Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        this.s = handler;
    }

    public final void setBitmapCustom(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmapCustom = bitmap;
    }

    public final void setBluetoothAdapter(@NotNull BluetoothAdapter bluetoothAdapter) {
        Intrinsics.checkNotNullParameter(bluetoothAdapter, "<set-?>");
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public final void setBottomSheetDialogDashboardFirmwareUpdated(@Nullable BottomSheetDialogDashboardFirmwareUpdated bottomSheetDialogDashboardFirmwareUpdated) {
        this.V = bottomSheetDialogDashboardFirmwareUpdated;
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.T = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setBottomSheetDialogTwoButtons(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.U = bottomSheetDialogTwoButtons;
    }

    public final void setBottomSheetTransparentDialog(@Nullable BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen) {
        this.A = bottomSheetDialogTransparentFullScreen;
    }

    public final void setDeviceFoundDuringScan(boolean z) {
        this.L = z;
    }

    public final void setEnableBluetoothDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.v = bottomSheetDialogTwoButtons;
    }

    public final void setFWUpdateDialogNeedToShow(boolean z) {
        this.w = z;
    }

    public final void setFromAppSettings(boolean z) {
        this.p = z;
    }

    public final void setFromMyWatchScreen(boolean z) {
        this.q = z;
    }

    public final void setFromOnboarding(boolean z) {
        this.Q = z;
    }

    public final void setImagePath(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<set-?>");
        this.imagePath = file;
    }

    public final void setLocationManager(@Nullable LocationManager locationManager) {
        this.P = locationManager;
    }

    public final void setMDataSyncViewModel(@NotNull DataSyncViewModel dataSyncViewModel) {
        Intrinsics.checkNotNullParameter(dataSyncViewModel, "<set-?>");
        this.mDataSyncViewModel = dataSyncViewModel;
    }

    public final void setRecoveryDialog(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.S = bottomSheetDialogImageTitleMessage;
    }

    public final void setSnackbar(@Nullable Snackbar snackbar) {
        this.B = snackbar;
    }

    public final void setSnackbarBatteryOptimization(@Nullable Snackbar snackbar) {
        this.R = snackbar;
    }

    public final void setStoragePermission(int i) {
        this.K = i;
    }

    public final void setSubscriptionDialog(@Nullable SubscriptionDialog subscriptionDialog) {
        this.I = subscriptionDialog;
    }

    public final void setSyncTroubleshootFragment(@Nullable SyncTroubleshootFragment syncTroubleshootFragment) {
        this.Y = syncTroubleshootFragment;
    }

    public final void setTimeStampDeviceFound(long j) {
        this.u = j;
    }

    public final void setViewmodel(@NotNull ViewModelActivityDashboardNew viewModelActivityDashboardNew) {
        Intrinsics.checkNotNullParameter(viewModelActivityDashboardNew, "<set-?>");
        this.viewmodel = viewModelActivityDashboardNew;
    }

    @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
    public void shouldShowIndusIndLogo(boolean z) {
        ImageView ivIndusIndLogo = (ImageView) _$_findCachedViewById(R.id.ivIndusIndLogo);
        Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo, "ivIndusIndLogo");
        if (z) {
            visible(ivIndusIndLogo);
        } else {
            gone(ivIndusIndLogo);
        }
        this.f0 = z;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, com.coveiot.android.theme.BottomSheetDialogImageTitleAndMessageWatchFace, java.lang.Object] */
    public final void showBottomSheetDialog(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? bottomSheetDialogImageTitleAndMessageWatchFace = new BottomSheetDialogImageTitleAndMessageWatchFace(this, title, message);
        objectRef.element = bottomSheetDialogImageTitleAndMessageWatchFace;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        String string = getString(R.string.done);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…atchfaceui.R.string.done)");
        ((BottomSheetDialogImageTitleAndMessageWatchFace) bottomSheetDialogImageTitleAndMessageWatchFace).setDoneButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDashboardNew.q1(Ref.ObjectRef.this, view);
            }
        });
        T t = objectRef.element;
        Intrinsics.checkNotNull(t);
        ((BottomSheetDialogImageTitleAndMessageWatchFace) t).show();
        T t2 = objectRef.element;
        Intrinsics.checkNotNull(t2);
        ((BottomSheetDialogImageTitleAndMessageWatchFace) t2).setCancelable(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, android.app.ProgressDialog] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void showCustomNotificationDialog(@org.jetbrains.annotations.Nullable java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 1368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew.showCustomNotificationDialog(java.lang.String):void");
    }

    public final void showDeviceConnectedDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
        BleApiManager.getInstance(this).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDeviceConnectedDialog$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDeviceConnectedDialog$1$onDataError$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityDashboardNew this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityDashboardNew activityDashboardNew, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityDashboardNew;
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

                /* JADX WARN: Type inference failed for: r1v10, types: [T, java.lang.Object, java.lang.String] */
                /* JADX WARN: Type inference failed for: r1v8, types: [T, java.lang.String] */
                /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.String] */
                /* JADX WARN: Type inference failed for: r5v12, types: [T, java.lang.String] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.this$0);
                        bottomSheetDialog.requestWindowFeature(1);
                        bottomSheetDialog.setContentView(R.layout.generic_dialog_img_connected_status);
                        int i = 100;
                        Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        objectRef.element = "100 %";
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (companion.isMatrixDevice(this.this$0)) {
                            i = this.this$0.getViewmodel().getBatteryPercentageForMatrix$app_prodRelease(100);
                            objectRef.element = i + " %";
                        }
                        if (companion.isMoyangDevice(this.this$0)) {
                            int batteryPercentageForVertex$app_prodRelease = this.this$0.getViewmodel().getBatteryPercentageForVertex$app_prodRelease(i);
                            if (batteryPercentageForVertex$app_prodRelease == 101) {
                                ?? string = this.this$0.getString(R.string.charging);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.charging)");
                                objectRef.element = string;
                            } else {
                                objectRef.element = batteryPercentageForVertex$app_prodRelease + " %";
                            }
                        }
                        View findViewById = bottomSheetDialog.findViewById(R.id.battery_status);
                        Intrinsics.checkNotNull(findViewById);
                        ((TextView) findViewById).setText((CharSequence) objectRef.element);
                        DeviceModelBean deviceModelBean = SessionManager.getInstance(this.this$0).getDeviceModelBean();
                        String str = null;
                        if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                            DeviceModelBean deviceModelBean2 = SessionManager.getInstance(this.this$0).getDeviceModelBean();
                            if (deviceModelBean2 != null) {
                                str = deviceModelBean2.getName();
                            }
                        } else {
                            str = companion.getModelNumber(this.this$0);
                        }
                        String string2 = this.this$0.getString(R.string.your_band_is_connected, new Object[]{str});
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.your_…is_connected, deviceName)");
                        View findViewById2 = bottomSheetDialog.findViewById(R.id.message_textView);
                        Intrinsics.checkNotNull(findViewById2);
                        ((TextView) findViewById2).setText(string2);
                        View findViewById3 = bottomSheetDialog.findViewById(R.id.battery_status_icon);
                        Intrinsics.checkNotNull(findViewById3);
                        ((ImageView) findViewById3).setImageResource(R.drawable.ic_generic_battery_icon);
                        View findViewById4 = bottomSheetDialog.findViewById(R.id.img_title);
                        Intrinsics.checkNotNull(findViewById4);
                        ((ImageView) findViewById4).setImageResource(this.this$0.getStatusConnectedIcon());
                        View findViewById5 = bottomSheetDialog.findViewById(R.id.positive_btn);
                        Intrinsics.checkNotNull(findViewById5);
                        ((Button) findViewById5).setOnClickListener(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0130: INVOKE  
                              (wrap: android.widget.Button : 0x0129: CHECK_CAST (r0v14 android.widget.Button A[REMOVE]) = (android.widget.Button) (r0v13 'findViewById5' android.view.View))
                              (wrap: android.view.View$OnClickListener : 0x012d: CONSTRUCTOR  (r1v6 android.view.View$OnClickListener A[REMOVE]) = (r7v3 'bottomSheetDialog' com.google.android.material.bottomsheet.BottomSheetDialog A[DONT_INLINE]) call: com.coveiot.android.leonardo.dashboard.w0.<init>(com.google.android.material.bottomsheet.BottomSheetDialog):void type: CONSTRUCTOR)
                             type: VIRTUAL call: android.widget.Button.setOnClickListener(android.view.View$OnClickListener):void in method: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDeviceConnectedDialog$1.a.invokeSuspend(java.lang.Object):java.lang.Object, file: classes2.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.leonardo.dashboard.w0, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                            	... 23 more
                            */
                        /*
                            Method dump skipped, instructions count: 321
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDeviceConnectedDialog$1.a.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDeviceConnectedDialog$1$onDataResponse$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ BleBaseResponse $response;
                    public int label;
                    public final /* synthetic */ ActivityDashboardNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(ActivityDashboardNew activityDashboardNew, BleBaseResponse bleBaseResponse, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = activityDashboardNew;
                        this.$response = bleBaseResponse;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$response, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.String] */
                    /* JADX WARN: Type inference failed for: r5v12, types: [T, java.lang.String] */
                    /* JADX WARN: Type inference failed for: r7v21, types: [T, java.lang.String] */
                    /* JADX WARN: Type inference failed for: r7v23, types: [T, java.lang.Object, java.lang.String] */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.dismissProgress();
                            if (this.$response.getResponseData() instanceof BatteryLevelResponse) {
                                Object responseData = this.$response.getResponseData();
                                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.this$0);
                                bottomSheetDialog.requestWindowFeature(1);
                                bottomSheetDialog.setContentView(R.layout.generic_dialog_img_connected_status);
                                Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                                Intrinsics.checkNotNull(batteryLevel);
                                int intValue = batteryLevel.intValue();
                                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                                objectRef.element = intValue + " %";
                                DeviceUtils.Companion companion = DeviceUtils.Companion;
                                if (companion.isMatrixDevice(this.this$0)) {
                                    intValue = this.this$0.getViewmodel().getBatteryPercentageForMatrix$app_prodRelease(intValue);
                                    objectRef.element = intValue + " %";
                                }
                                if (companion.isMoyangDevice(this.this$0)) {
                                    int batteryPercentageForVertex$app_prodRelease = this.this$0.getViewmodel().getBatteryPercentageForVertex$app_prodRelease(intValue);
                                    if (batteryPercentageForVertex$app_prodRelease == 101) {
                                        ?? string = this.this$0.getString(R.string.charging);
                                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.charging)");
                                        objectRef.element = string;
                                    } else {
                                        objectRef.element = batteryPercentageForVertex$app_prodRelease + " %";
                                    }
                                }
                                View findViewById = bottomSheetDialog.findViewById(R.id.battery_status);
                                Intrinsics.checkNotNull(findViewById);
                                ((TextView) findViewById).setText((CharSequence) objectRef.element);
                                DeviceModelBean deviceModelBean = SessionManager.getInstance(this.this$0).getDeviceModelBean();
                                String str = null;
                                if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                                    DeviceModelBean deviceModelBean2 = SessionManager.getInstance(this.this$0).getDeviceModelBean();
                                    if (deviceModelBean2 != null) {
                                        str = deviceModelBean2.getName();
                                    }
                                } else {
                                    str = companion.getModelNumber(this.this$0);
                                }
                                String string2 = this.this$0.getString(R.string.your_band_is_connected, new Object[]{str});
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.your_…is_connected, deviceName)");
                                View findViewById2 = bottomSheetDialog.findViewById(R.id.message_textView);
                                Intrinsics.checkNotNull(findViewById2);
                                ((TextView) findViewById2).setText(string2);
                                View findViewById3 = bottomSheetDialog.findViewById(R.id.battery_status_icon);
                                Intrinsics.checkNotNull(findViewById3);
                                ((ImageView) findViewById3).setImageResource(R.drawable.ic_generic_battery_icon);
                                View findViewById4 = bottomSheetDialog.findViewById(R.id.img_title);
                                Intrinsics.checkNotNull(findViewById4);
                                ((ImageView) findViewById4).setImageResource(this.this$0.getStatusConnectedIcon());
                                View findViewById5 = bottomSheetDialog.findViewById(R.id.positive_btn);
                                Intrinsics.checkNotNull(findViewById5);
                                ((Button) findViewById5).setOnClickListener(
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0150: INVOKE  
                                      (wrap: android.widget.Button : 0x0149: CHECK_CAST (r7v19 android.widget.Button A[REMOVE]) = (android.widget.Button) (r7v18 'findViewById5' android.view.View))
                                      (wrap: android.view.View$OnClickListener : 0x014d: CONSTRUCTOR  (r1v9 android.view.View$OnClickListener A[REMOVE]) = (r0v3 'bottomSheetDialog' com.google.android.material.bottomsheet.BottomSheetDialog A[DONT_INLINE]) call: com.coveiot.android.leonardo.dashboard.x0.<init>(com.google.android.material.bottomsheet.BottomSheetDialog):void type: CONSTRUCTOR)
                                     type: VIRTUAL call: android.widget.Button.setOnClickListener(android.view.View$OnClickListener):void in method: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDeviceConnectedDialog$1.b.invokeSuspend(java.lang.Object):java.lang.Object, file: classes2.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.leonardo.dashboard.x0, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                                    	... 29 more
                                    */
                                /*
                                    Method dump skipped, instructions count: 353
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$showDeviceConnectedDialog$1.b.invokeSuspend(java.lang.Object):java.lang.Object");
                            }
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        @SuppressLint({"StringFormatInvalid"})
                        public void onDataError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            kotlinx.coroutines.e.e(ActivityDashboardNew.this.getScope(), Dispatchers.getMain(), null, new a(ActivityDashboardNew.this, null), 2, null);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            kotlinx.coroutines.e.e(ActivityDashboardNew.this.getScope(), Dispatchers.getMain(), null, new b(ActivityDashboardNew.this, response, null), 2, null);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onProgressUpdate(@NotNull ProgressData progress) {
                            Intrinsics.checkNotNullParameter(progress, "progress");
                        }
                    });
                }

                public final void showDeviceConnectingDialog() {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                    bottomSheetDialog.requestWindowFeature(1);
                    bottomSheetDialog.setContentView(R.layout.generic_dialog_img_disconnected_status);
                    View findViewById = bottomSheetDialog.findViewById(R.id.img_title);
                    Intrinsics.checkNotNull(findViewById);
                    ((ImageView) findViewById).setImageResource(getStatusConnectingIcon());
                    View findViewById2 = bottomSheetDialog.findViewById(R.id.positive_btn);
                    Intrinsics.checkNotNull(findViewById2);
                    ((Button) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.q
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDashboardNew.u1(BottomSheetDialog.this, view);
                        }
                    });
                    bottomSheetDialog.show();
                }

                public final void showNoInternetDialog() {
                    if (isFinishing()) {
                        return;
                    }
                    String string = getString(R.string.no_internet_connection);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…g.no_internet_connection)");
                    String string2 = getString(R.string.please_check_network);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ing.please_check_network)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
                    bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
                    String string3 = getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.android.theme.R.string.ok)");
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.k
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDashboardNew.A1(BottomSheetDialogOneButtonTitleMessage.this, view);
                        }
                    });
                    if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                        return;
                    }
                    bottomSheetDialogOneButtonTitleMessage.show();
                }

                public final void showOpenBluetoothSettingsDialog() {
                    if (this.v == null) {
                        String string = getString(R.string.bluetooth_off);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
                        String string2 = getString(R.string.bluetooth_off_message);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
                        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
                        this.v = bottomSheetDialogTwoButtons;
                        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
                        String string3 = getString(R.string.proceed);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
                        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.r0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityDashboardNew.B1(ActivityDashboardNew.this, view);
                            }
                        });
                        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.v;
                        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                        String string4 = getString(R.string.cancel);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
                        bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.u0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                ActivityDashboardNew.C1(ActivityDashboardNew.this, view);
                            }
                        });
                    }
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.v;
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                    if (bottomSheetDialogTwoButtons3.isShowing()) {
                        return;
                    }
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.v;
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
                    bottomSheetDialogTwoButtons4.show();
                }

                public final void showScanningForDeviceDialog() {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                    bottomSheetDialog.requestWindowFeature(1);
                    bottomSheetDialog.setContentView(R.layout.generic_dialog_img_disconnected_status);
                    View findViewById = bottomSheetDialog.findViewById(R.id.img_title);
                    Intrinsics.checkNotNull(findViewById);
                    ((ImageView) findViewById).setImageResource(getStatusConnectingIcon());
                    View findViewById2 = bottomSheetDialog.findViewById(R.id.positive_btn);
                    Intrinsics.checkNotNull(findViewById2);
                    ((Button) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.r
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDashboardNew.H1(BottomSheetDialog.this, view);
                        }
                    });
                    bottomSheetDialog.show();
                }

                public final void showSuscriptionDialog() {
                    try {
                        SubscriptionDialog subscriptionDialog = new SubscriptionDialog();
                        this.I = subscriptionDialog;
                        Intrinsics.checkNotNull(subscriptionDialog);
                        subscriptionDialog.setCancelable(false);
                        SubscriptionDialog subscriptionDialog2 = this.I;
                        Intrinsics.checkNotNull(subscriptionDialog2);
                        subscriptionDialog2.show(getSupportFragmentManager(), "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.coveiot.android.dashboard2.listener.SyncTroubleShoutListener
                public void showSyncTroubleShoot() {
                    try {
                        SyncTroubleshootFragment syncTroubleshootFragment = new SyncTroubleshootFragment();
                        this.Y = syncTroubleshootFragment;
                        Intrinsics.checkNotNull(syncTroubleshootFragment);
                        if (!syncTroubleshootFragment.isAdded()) {
                            SyncTroubleshootFragment syncTroubleshootFragment2 = this.Y;
                            Intrinsics.checkNotNull(syncTroubleshootFragment2);
                            FragmentManager supportFragmentManager = getSupportFragmentManager();
                            SyncTroubleshootFragment syncTroubleshootFragment3 = this.Y;
                            Intrinsics.checkNotNull(syncTroubleshootFragment3);
                            syncTroubleshootFragment2.show(supportFragmentManager, syncTroubleshootFragment3.getTag());
                        } else {
                            SyncTroubleshootFragment syncTroubleshootFragment4 = this.Y;
                            Intrinsics.checkNotNull(syncTroubleshootFragment4);
                            syncTroubleshootFragment4.dismiss();
                            SyncTroubleshootFragment syncTroubleshootFragment5 = this.Y;
                            Intrinsics.checkNotNull(syncTroubleshootFragment5);
                            FragmentManager supportFragmentManager2 = getSupportFragmentManager();
                            SyncTroubleshootFragment syncTroubleshootFragment6 = this.Y;
                            Intrinsics.checkNotNull(syncTroubleshootFragment6);
                            syncTroubleshootFragment5.show(supportFragmentManager2, syncTroubleshootFragment6.getTag());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public final void startScanOnDisconnect$app_prodRelease(boolean z) {
                    if (PayUtils.INSTANCE.checkIfScanOnDisConnectIsNeeded(this) && getBluetoothAdapter().isEnabled()) {
                        if (Build.VERSION.SDK_INT >= 31) {
                            if (checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0 && checkSelfPermission("android.permission.BLUETOOTH_CONNECT") == 0) {
                                if (!AppUtils.checkIfLocationPermissionExists(this)) {
                                    String string = getString(R.string.location_permission_required);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                                    D1(string);
                                    return;
                                }
                                L1();
                            } else if (z) {
                                if (AppUtils.checkIfLocationPermissionExists(this) && Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                                    L1();
                                } else if (AppUtils.checkIfLocationPermissionExists(this) && Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                                } else {
                                    if (!AppUtils.checkIfLocationPermissionExists(this)) {
                                        String string2 = getString(R.string.location_permission_required);
                                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.location_permission_required)");
                                        D1(string2);
                                    } else if (Dashboard2Utils.Companion.checkIfBluetoothPermissionExists(this)) {
                                    } else {
                                        String string3 = getString(R.string.bluetooth_permission_required);
                                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.bluetooth_permission_required)");
                                        D1(string3);
                                    }
                                }
                            }
                        } else if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 && checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                            L1();
                        } else if (z) {
                            String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
                            if (checkPermissionsHasGranted.length > 0) {
                                ActivityCompat.requestPermissions(this, checkPermissionsHasGranted, FragmentScanningDeviceKt.getLOCATION_PERMISSION_REQUEST_CODE());
                            }
                        }
                    }
                }

                public final void syncData() {
                    LogHelper.i(this.J, "syncData start");
                    if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        if (SessionManager.getInstance(this).shouldWriteStepGoalAfterConnection()) {
                            int i = ProfileRepository.getInstance().getLatestProfileData(this).stepsTarget;
                            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isStepGoalSupported()) {
                                StepsTargetRequest setWalkTargetReq = new StepsTargetRequest.Builder().setTarget(i).build();
                                BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
                                Intrinsics.checkNotNullExpressionValue(setWalkTargetReq, "setWalkTargetReq");
                                bleApi.setUserSettings(setWalkTargetReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$syncData$1
                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                        Intrinsics.checkNotNullParameter(response, "response");
                                        ActivityDashboardNew.this.U1();
                                    }
                                });
                                return;
                            }
                            U1();
                            return;
                        }
                        getMDataSyncViewModel().syncData(false, this.H);
                    }
                }

                @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
                public void syncFailed(@Nullable String str) {
                    runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.l0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityDashboardNew.Q1(ActivityDashboardNew.this);
                        }
                    });
                }

                public final void syncSpo2Data() {
                    BleApiManager.getInstance(this).getBleApi().getData(new GetLatestHealthDataRequest(HealthVitalsType.SPO2), new DataResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$syncSpo2Data$1
                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataError(@NotNull BleBaseError error) {
                            String str;
                            Intrinsics.checkNotNullParameter(error, "error");
                            str = ActivityDashboardNew.this.J;
                            LogHelper.d(str, "error GetLatestHealthDataRequest: " + error.getErrorMsg());
                            ActivityDashboardNew.this.syncData();
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataResponse(@NotNull BleBaseResponse response) {
                            String str;
                            Intrinsics.checkNotNullParameter(response, "response");
                            Object responseData = response.getResponseData();
                            Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetLatestHealthDataResponse");
                            GetLatestHealthDataResponse getLatestHealthDataResponse = (GetLatestHealthDataResponse) responseData;
                            str = ActivityDashboardNew.this.J;
                            LogHelper.d(str, "GetLatestHealthDataRequest: " + getLatestHealthDataResponse);
                            HealthData healthData = getLatestHealthDataResponse.getHealthData();
                            if (healthData.getValue() > 0) {
                                LatestHealthDataModel latestHealthDataModel = new LatestHealthDataModel();
                                latestHealthDataModel.setTimestamp(healthData.getTimestamp());
                                latestHealthDataModel.setValue(healthData.getValue());
                                latestHealthDataModel.setHealthDataType(healthData.getHealthVitalsType());
                                UserDataManager.getInstance(ActivityDashboardNew.this).saveLatestSpo2Value(latestHealthDataModel);
                            }
                            ActivityDashboardNew.this.syncData();
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onProgressUpdate(@NotNull ProgressData progress) {
                            Intrinsics.checkNotNullParameter(progress, "progress");
                        }
                    });
                }

                @Override // com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface
                public void syncSuccess() {
                    if (isFinishing()) {
                        return;
                    }
                    runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.k0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityDashboardNew.R1(ActivityDashboardNew.this);
                        }
                    });
                    checkAndSendGoalCompleteNotificaton();
                    Object preference = BlePreferenceManager.getPreference(this, CommonPreference.SHOULD_UPDATE_SETTINGS_BAND, Boolean.FALSE);
                    Intrinsics.checkNotNullExpressionValue(preference, "getPreference(\n         …, false\n                )");
                    if (((Boolean) preference).booleanValue()) {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (!companion.isMatrixDevice(this) && !companion.isEastApexDevice(this) && !companion.isTouchELXDevice(this) && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                            if (this.A == null) {
                                Drawable drawable = getResources().getDrawable(R.drawable.ic_shape_info);
                                Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.ic_shape_info)");
                                String string = getString(R.string.factory_reset_sync_msg);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.factory_reset_sync_msg)");
                                this.A = new BottomSheetDialogTransparentFullScreen(this, drawable, string);
                            }
                            BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen = this.A;
                            Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen);
                            bottomSheetDialogTransparentFullScreen.setCancelable(false);
                            BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen2 = this.A;
                            Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen2);
                            bottomSheetDialogTransparentFullScreen2.setCancelableOutside(false);
                            BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen3 = this.A;
                            Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen3);
                            if (!bottomSheetDialogTransparentFullScreen3.isShowing()) {
                                BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen4 = this.A;
                                Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen4);
                                bottomSheetDialogTransparentFullScreen4.show();
                            }
                            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                                BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen5 = this.A;
                                Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen5);
                                bottomSheetDialogTransparentFullScreen5.showProgress(8);
                                BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen6 = this.A;
                                Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen6);
                                bottomSheetDialogTransparentFullScreen6.showLoader(0);
                            } else if (SportsPreference.Companion.isNotificationEnabled(this)) {
                                BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen7 = this.A;
                                Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen7);
                                bottomSheetDialogTransparentFullScreen7.showProgress(0);
                                BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen8 = this.A;
                                Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen8);
                                bottomSheetDialogTransparentFullScreen8.showLoader(8);
                            } else {
                                BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen9 = this.A;
                                Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen9);
                                bottomSheetDialogTransparentFullScreen9.showProgress(8);
                                BottomSheetDialogTransparentFullScreen bottomSheetDialogTransparentFullScreen10 = this.A;
                                Intrinsics.checkNotNull(bottomSheetDialogTransparentFullScreen10);
                                bottomSheetDialogTransparentFullScreen10.showLoader(0);
                            }
                            BleApiManager.getInstance(this).getBleApi().clearCommandQueue();
                            SettingsSyncHelper.Companion.getInstance(this).syncSettings(new ActivityDashboardNew$syncSuccess$2(this));
                            return;
                        }
                    } else {
                        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.o0
                            @Override // java.lang.Runnable
                            public final void run() {
                                ActivityDashboardNew.S1();
                            }
                        });
                        final SedentaryReminderData sedentaryReminderData = UserDataManager.getInstance(this).getSedentaryReminderData();
                        if (sedentaryReminderData != null) {
                            if (sedentaryReminderData.getRemind_in() < 60) {
                                SetReminderRequest sedentaryReminderReq = new SetReminderRequest.Builder().setStartHour1(sedentaryReminderData.getBeggining_time_hour()).setEndHour1(sedentaryReminderData.getEnd_time_hour()).setStartMin1(sedentaryReminderData.getBeggining_time_minutes()).setEndMin1(sedentaryReminderData.getEnd_time_minutes()).setReminderInterval(60).setEnabled(sedentaryReminderData.getAlarm_on_off()).setReminderType(ReminderType.SEDENTARY_REMINDER).build();
                                sedentaryReminderData.setRemind_in(60);
                                BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
                                Intrinsics.checkNotNullExpressionValue(sedentaryReminderReq, "sedentaryReminderReq");
                                bleApi.setUserSettings(sedentaryReminderReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$syncSuccess$4
                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                        ActivityDashboardNew.this.k1();
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                        Intrinsics.checkNotNullParameter(response, "response");
                                        ActivityDashboardNew.this.k1();
                                        UserDataManager.getInstance(ActivityDashboardNew.this).saveSedentaryReminderSettings(sedentaryReminderData);
                                    }
                                });
                            } else {
                                k1();
                            }
                        } else {
                            k1();
                        }
                        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(GenericBandApplication.Companion.getACTION_SYNC_COMPLETE()));
                    }
                    if (getViewmodel().isShowSubscribtionDialog(this)) {
                        r0();
                        showSuscriptionDialog();
                        return;
                    }
                    r0();
                }

                public final void t0() {
                    if (x0()) {
                        return;
                    }
                    LogHelper.d(this.J, "checkAndStartService-> service is not running ++ ");
                    O1();
                }

                @NotNull
                public final Bitmap takeScreenshot(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setDrawingCacheEnabled(true);
                    Bitmap drawingCache = view.getDrawingCache();
                    Intrinsics.checkNotNullExpressionValue(drawingCache, "view.drawingCache");
                    return drawingCache;
                }

                public final void takeToHomeScreen(@NotNull HomeNavigationElement homeNavigationElement, @NotNull Bundle bundle) {
                    Intrinsics.checkNotNullParameter(homeNavigationElement, "homeNavigationElement");
                    Intrinsics.checkNotNullParameter(bundle, "bundle");
                    bundle.putInt("tab_pos_child", homeNavigationElement.ordinal());
                    Navigation.findNavController(this, R.id.mainNavigationFragment).navigate(R.id.fragmentHome, bundle);
                }

                public final void takeToScreen(@NotNull HealthNavigationElement healthNavigationElement) {
                    Intrinsics.checkNotNullParameter(healthNavigationElement, "healthNavigationElement");
                    int tabPositionBasedOnDevice = getTabPositionBasedOnDevice(healthNavigationElement);
                    Bundle bundle = new Bundle();
                    bundle.putInt("tab_pos", tabPositionBasedOnDevice);
                    bundle.putInt("is_from_dashboard", healthNavigationElement == HealthNavigationElement.HEALTH ? 0 : 1);
                    Navigation.findNavController(this, R.id.mainNavigationFragment).navigate(R.id.fragmentHealth, bundle);
                }

                public final void toggleNotificationListenerService() {
                    final PackageManager packageManager = getApplicationContext().getPackageManager();
                    final ComponentName componentName = new ComponentName(getApplicationContext(), SocialNotificationListenerService.class);
                    packageManager.setComponentEnabledSetting(componentName, 2, 1);
                    new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.d0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityDashboardNew.T1(packageManager, componentName);
                        }
                    }, 1000L);
                }

                public final void tryReconnectService() {
                    toggleNotificationListenerService();
                    int i = Build.VERSION.SDK_INT;
                    if (i >= 24) {
                        ComponentName componentName = new ComponentName(getApplicationContext(), SocialNotificationListenerService.class);
                        if (i >= 26) {
                            NotificationListenerService.requestRebind(componentName);
                        }
                    }
                }

                public final void u0(SoftwareUpdateRes softwareUpdateRes) {
                    if (softwareUpdateRes.getData().getFirmwares() != null && softwareUpdateRes.getData().getFirmwares().size() > 0) {
                        String updateStatus = softwareUpdateRes.getData().getFirmwares().get(0).getUpdateStatus();
                        AppConstants appConstants = AppConstants.ENFORCE;
                        boolean equals = kotlin.text.m.equals(updateStatus, appConstants.getValue(), true);
                        if (kotlin.text.m.equals(softwareUpdateRes.getData().getFirmwares().get(0).getUpdateStatus(), appConstants.getValue(), true)) {
                            this.w = true;
                            SyncInterruptionListener syncInterruptionListener = this.F;
                            if (syncInterruptionListener != null) {
                                syncInterruptionListener.onInterrupt(SyncInterruptionType.FIRMWARE_UPDATE);
                            }
                            if (this.V == null) {
                                this.V = new BottomSheetDialogDashboardFirmwareUpdated(this, true);
                            }
                            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = softwareUpdateRes.getData().getFirmwares().get(0);
                            Intrinsics.checkNotNullExpressionValue(firmwareBean, "appUpdateApiResponse.data.firmwares[0]");
                            b1(equals, firmwareBean);
                        } else if (kotlin.text.m.equals(softwareUpdateRes.getData().getFirmwares().get(0).getUpdateStatus(), AppConstants.OPTIONAL.getValue(), true)) {
                            boolean canShowFirmwareUpdateDialog = FirmwareUtils.INSTANCE.canShowFirmwareUpdateDialog(this);
                            SessionManager.getInstance(this).saveOptionalFirmwareBeanString(new Gson().toJson(softwareUpdateRes.getData().getFirmwares().get(0)));
                            if (canShowFirmwareUpdateDialog) {
                                this.w = true;
                                SyncInterruptionListener syncInterruptionListener2 = this.F;
                                if (syncInterruptionListener2 != null) {
                                    syncInterruptionListener2.onInterrupt(SyncInterruptionType.FIRMWARE_UPDATE);
                                }
                                if (this.V == null) {
                                    this.V = new BottomSheetDialogDashboardFirmwareUpdated(this, false);
                                }
                                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = softwareUpdateRes.getData().getFirmwares().get(0);
                                Intrinsics.checkNotNullExpressionValue(firmwareBean2, "appUpdateApiResponse.data.firmwares[0]");
                                b1(equals, firmwareBean2);
                            } else {
                                SyncInterruptionListener syncInterruptionListener3 = this.F;
                                if (syncInterruptionListener3 != null) {
                                    syncInterruptionListener3.onInterrupt(SyncInterruptionType.NONE);
                                }
                                this.w = false;
                            }
                        } else {
                            this.w = false;
                            SyncInterruptionListener syncInterruptionListener4 = this.F;
                            if (syncInterruptionListener4 != null) {
                                syncInterruptionListener4.onInterrupt(SyncInterruptionType.NONE);
                            }
                        }
                    }
                    if (BleApiManager.getInstance(this).getDeviceType() == DeviceType.jstyle1810G) {
                        z0(softwareUpdateRes);
                    }
                }

                public final void v0() {
                    Long goalCompleteNotificationShownTime = (Long) com.coveiot.android.leonardo.utils.PreferenceManager.getPreference(this, PreferenceNames.GOAL_COMPLETION_NOTIFICATION_SHOWN_TIME, 0L);
                    Intrinsics.checkNotNullExpressionValue(goalCompleteNotificationShownTime, "goalCompleteNotificationShownTime");
                    if (DateUtils.isToday(goalCompleteNotificationShownTime.longValue())) {
                        return;
                    }
                    com.coveiot.android.leonardo.utils.PreferenceManager.savePreference(this, PreferenceNames.IS_STEP_GOAL_CHANGED, Boolean.TRUE);
                    com.coveiot.android.leonardo.utils.PreferenceManager.savePreference(this, PreferenceNames.IS_GOAL_COMPLETE_NOTIFICATION_SHOWN, Boolean.FALSE);
                }

                public final void v1() {
                    Intrinsics.checkNotNull(this);
                    final Dialog dialog = new Dialog(this, R.style.DialogTheme);
                    dialog.requestWindowFeature(1);
                    dialog.setContentView(R.layout.activity_heart_monitor_warning_dialog);
                    ((ImageView) dialog.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.l
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDashboardNew.w1(dialog, view);
                        }
                    });
                    final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.cb_dont_remind_again);
                    ((Button) dialog.findViewById(R.id.btnStop)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.d
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDashboardNew.x1(ActivityDashboardNew.this, dialog, checkBox, view);
                        }
                    });
                    ((Button) dialog.findViewById(R.id.btnSkip)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.q0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDashboardNew.y1(checkBox, this, dialog, view);
                        }
                    });
                    dialog.show();
                }

                public final boolean w0() {
                    Object systemService = getSystemService("activity");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
                    boolean z = false;
                    for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
                        if (Intrinsics.areEqual(AlexaRemoteCommandFrameworkService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(getPackageName(), runningServiceInfo.service.getPackageName())) {
                            z = true;
                        }
                    }
                    return z;
                }

                public final boolean x0() {
                    Object systemService = getSystemService("activity");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
                    boolean z = false;
                    for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
                        SportsPreferenceModel sportsNotification = SportsPreference.Companion.getSportsNotification(this);
                        if (kotlin.text.m.equals$default(sportsNotification != null ? sportsNotification.getSport() : null, SportsType.Cricket.name(), false, 2, null)) {
                            if (Intrinsics.areEqual(SportsService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(getPackageName(), runningServiceInfo.service.getPackageName())) {
                                z = true;
                            }
                        } else if (Intrinsics.areEqual(SoccerSportsServiceNew.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(getPackageName(), runningServiceInfo.service.getPackageName())) {
                            z = true;
                        }
                    }
                    return z;
                }

                public final void y0() {
                    ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                    if (themesUtils.isGuestUser(this) || themesUtils.isPairDeviceLater(this) || Build.VERSION.SDK_INT < 31) {
                        return;
                    }
                    Object systemService = getSystemService("power");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
                    if (!((PowerManager) systemService).isIgnoringBatteryOptimizations(getPackageName())) {
                        E1();
                        return;
                    }
                    Snackbar snackbar = this.R;
                    if (snackbar == null || snackbar == null) {
                        return;
                    }
                    try {
                        snackbar.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public final void z0(SoftwareUpdateRes softwareUpdateRes) {
                    if (softwareUpdateRes == null || softwareUpdateRes.getData() == null || softwareUpdateRes.getData().getFirmwares() == null || softwareUpdateRes.getData().getFirmwares().size() <= 0 || AppUtils.isEmpty(softwareUpdateRes.getData().getFirmwares().get(0).getConfigUrl())) {
                        return;
                    }
                    CoveUserAppSettings.getDeviceConfiguration(softwareUpdateRes.getData().getFirmwares().get(0).getConfigUrl(), new CoveApiListener<DeviceConfigurationRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$checkTemperatureCalibration$1
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                            String str;
                            str = ActivityDashboardNew.this.J;
                            LogHelper.d(str, String.valueOf(coveApiErrorModel));
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@Nullable DeviceConfigurationRes deviceConfigurationRes) {
                            if (deviceConfigurationRes == null || deviceConfigurationRes.getCalibration() == null || deviceConfigurationRes.getCalibration().getTemperature() == null) {
                                return;
                            }
                            ActivityDashboardNew activityDashboardNew = ActivityDashboardNew.this;
                            DeviceConfigurationRes.CalibrationBean.TemperatureBean temperature = deviceConfigurationRes.getCalibration().getTemperature();
                            Intrinsics.checkNotNullExpressionValue(temperature, "p0.calibration.temperature");
                            activityDashboardNew.e1(temperature);
                        }
                    });
                }

                public final void z1() {
                    NotificationManager.getInstance().notifyGoalCompletion(this, 111, getString(R.string.goal_completion_msg));
                    com.coveiot.android.leonardo.utils.PreferenceManager.savePreference(this, PreferenceNames.GOAL_COMPLETION_NOTIFICATION_SHOWN_TIME, Long.valueOf(System.currentTimeMillis()));
                    com.coveiot.android.leonardo.utils.PreferenceManager.savePreference(this, PreferenceNames.IS_STEP_GOAL_CHANGED, Boolean.FALSE);
                    com.coveiot.android.leonardo.utils.PreferenceManager.savePreference(this, PreferenceNames.IS_GOAL_COMPLETE_NOTIFICATION_SHOWN, Boolean.TRUE);
                }

                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable ConnectionStatus connectionStatus) {
                    String str = this.J;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Connection state :");
                    sb.append(connectionStatus != null ? connectionStatus.name() : null);
                    LogHelper.d(str, sb.toString());
                    for (ConnectionStatusUpdateListener connectionStatusUpdateListener : this.G) {
                        if (connectionStatusUpdateListener != null) {
                            connectionStatusUpdateListener.onConnectionUpdated(connectionStatus);
                        }
                    }
                    if (!this.G.isEmpty()) {
                        if (connectionStatus == ConnectionStatus.CONNECTING) {
                            getViewmodel().stopSyncTimer();
                            if (UserDataManager.getInstance(this).isDisconnectionAtAgpsFileUpdate()) {
                                return;
                            }
                            this.u = System.currentTimeMillis();
                            getViewmodel().startTimer();
                        } else if (connectionStatus != ConnectionStatus.SCANNING) {
                            if (connectionStatus == ConnectionStatus.CONNECTED) {
                                DataSyncViewModel mDataSyncViewModel = getMDataSyncViewModel();
                                if (mDataSyncViewModel != null) {
                                    mDataSyncViewModel.resetSyncState();
                                }
                                getViewmodel().stopTimer();
                                getViewmodel().stopSyncTimer();
                                DeviceUtils.Companion companion = DeviceUtils.Companion;
                                if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                                    AnalyticsLog analyticsLog = new AnalyticsLog();
                                    analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_CONNECT.getValue());
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "ok");
                                    hashMap.put(FirebaseEventParams.MetaData.CV_TIME_SPENT_MILLS.getValue(), String.valueOf(System.currentTimeMillis() - this.u));
                                    hashMap.put(FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue(), Dashboard2Utils.Companion.getPhoneBatteryLevel(this));
                                    analyticsLog.setMapData(hashMap);
                                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                                }
                                P1();
                                String softwareUpdateResponseBeanString = SessionManager.getInstance(this).getSoftwareUpdateResponseBeanString();
                                if (!AppUtils.isEmpty(softwareUpdateResponseBeanString)) {
                                    Object fromJson = new Gson().fromJson(softwareUpdateResponseBeanString, (Class<Object>) SoftwareUpdateRes.class);
                                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(\n       …                        )");
                                    u0((SoftwareUpdateRes) fromJson);
                                }
                                this.y.removeCallbacksAndMessages(null);
                                this.y.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.j0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        ActivityDashboardNew.T0(ActivityDashboardNew.this);
                                    }
                                }, com.clevertap.android.sdk.Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                                return;
                            }
                            DataSyncViewModel mDataSyncViewModel2 = getMDataSyncViewModel();
                            if (mDataSyncViewModel2 != null) {
                                mDataSyncViewModel2.resetSyncState();
                            }
                            getViewmodel().stopTimer();
                            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                            if (!companion2.isCZDevice(this) && !companion2.isCADevice(this) && !companion2.isCYDevice(this) && !companion2.isPS1Device(this) && !companion2.isBESDevice(this) && this.L) {
                                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                                analyticsLog2.setEventName(FirebaseEventParams.EventName.CV_BT_CONNECT.getValue());
                                HashMap<String, String> hashMap2 = new HashMap<>();
                                hashMap2.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "error");
                                hashMap2.put(FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue(), Dashboard2Utils.Companion.getPhoneBatteryLevel(this));
                                analyticsLog2.setMapData(hashMap2);
                                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                            }
                            if (getBluetoothAdapter().isEnabled()) {
                                if (Build.VERSION.SDK_INT >= 31) {
                                    if (checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0 || checkSelfPermission("android.permission.BLUETOOTH_CONNECT") == 0) {
                                        L1();
                                    }
                                } else if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                                    L1();
                                }
                            }
                            CoveEventBusManager.getInstance().getEventBus().post(new OnDeviceDisconnected());
                        }
                    }
                }

                public final void takeToScreen(@NotNull HealthNavigationElement healthNavigationElement, @Nullable Calendar calendar) {
                    Intrinsics.checkNotNullParameter(healthNavigationElement, "healthNavigationElement");
                    int tabPositionBasedOnDevice = getTabPositionBasedOnDevice(healthNavigationElement);
                    Bundle bundle = new Bundle();
                    bundle.putInt("tab_pos", tabPositionBasedOnDevice);
                    bundle.putInt("is_from_dashboard", 1);
                    bundle.putSerializable("calender", calendar);
                    Navigation.findNavController(this, R.id.mainNavigationFragment).navigate(R.id.fragmentHealth, bundle);
                }
            }
