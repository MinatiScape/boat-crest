package com.coveiot.android.boat;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.coveiot.analytics.AnalyticsType;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.androidappkillmanager.AndroidAutoKillManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.SetSocialDistanceScanSettingsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.dashboard2.StatusApiDataHelper;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.PersonalizationEventReceiver;
import com.coveiot.android.leonardo.RebootReceiver;
import com.coveiot.android.leonardo.SyncCompleteReciever;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.locationbreach.LocationBreachReceiver;
import com.coveiot.android.leonardo.model.MuteReceived;
import com.coveiot.android.leonardo.more.AppListLoaderReceiver;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.android.leonardo.phonelocator.FindMyPhoneReceiver;
import com.coveiot.android.leonardo.probe.ProbeBroadcastReceiver;
import com.coveiot.android.leonardo.quickreply.SendQuickReplyEventReceiver;
import com.coveiot.android.leonardo.utils.AgpsSuccessReceiver;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import com.coveiot.android.leonardo.utils.CallMuteReceiver;
import com.coveiot.android.leonardo.utils.CallNotificationHelper;
import com.coveiot.android.leonardo.utils.CallRejectReceiver;
import com.coveiot.android.leonardo.utils.CameraEventReceiver;
import com.coveiot.android.leonardo.utils.DeviceSettingsInfoReceiver;
import com.coveiot.android.leonardo.utils.FactoryResetReceiver;
import com.coveiot.android.leonardo.utils.LowBatteryReceiver;
import com.coveiot.android.leonardo.utils.MusicControlReceiver;
import com.coveiot.android.leonardo.utils.MusicMetaDataReceiver;
import com.coveiot.android.leonardo.utils.NavigationStatusReceiver;
import com.coveiot.android.leonardo.utils.NotifyNavigationEventReceiver;
import com.coveiot.android.leonardo.utils.RequestNavigationEventReceiver;
import com.coveiot.android.leonardo.utils.SetNavigationInstructionsReceiver;
import com.coveiot.android.leonardo.utils.TroubleshootNotificationClickReceiver;
import com.coveiot.android.leonardo.utils.VolumeControlListener;
import com.coveiot.android.navigation.activities.ActivityDirections;
import com.coveiot.android.sos.SendSOSEventReceiver;
import com.coveiot.android.sportsnotification.SoccerSportsServiceNew;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsType;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotificationsdk.network.SportType;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiClient;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.BaseUnitType;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.datasync.DataSyncImpl;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.eventmodels.OnDeviceConnected;
import com.coveiot.utils.eventmodels.OnDeviceDisconnected;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.RemoteConfig;
import com.coveiot.utils.utility.UNIT_TYPE;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.Recaptcha;
import com.google.android.recaptcha.RecaptchaClient;
import com.google.android.recaptcha.RecaptchaException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.htsmart.wristband2.WristbandApplication;
import com.ido.ble.BLEManager;
import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.ui.navigation.MapplsNavigationViewHelper;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.squareup.otto.Subscribe;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.shipbook.shipbooksdk.ShipBook;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GenericBandApplication extends CameraXBasic implements Application.ActivityLifecycleCallbacks {
    public static boolean o0;
    @Nullable
    public RecaptchaClient B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    @Nullable
    public ContentObserver H;
    public boolean I;
    @Nullable
    public TelephonyManager J;
    public boolean K;
    @Nullable
    public Observer<ConnectionStatus> M;
    public boolean l0;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String n0 = Dashboard2Constants.ACTION_SYNC_COMPLETE;
    @NotNull
    public static final CoroutineExceptionHandler p0 = new GenericBandApplication$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key);
    public final int G = Constants.ACTIVITY_INSIGHT_NOTIFICATION_ID;
    @NotNull
    public final String L = "GenericBandApplication";
    @NotNull
    public final FindMyPhoneReceiver N = new FindMyPhoneReceiver();
    @NotNull
    public final ProbeBroadcastReceiver O = new ProbeBroadcastReceiver();
    @NotNull
    public final SyncCompleteReciever P = new SyncCompleteReciever();
    @NotNull
    public final LocationBreachReceiver Q = new LocationBreachReceiver();
    @NotNull
    public final Handler R = new Handler(Looper.getMainLooper());
    @NotNull
    public final DeviceSettingsInfoReceiver S = new DeviceSettingsInfoReceiver();
    @NotNull
    public final CallRejectReceiver T = new CallRejectReceiver();
    @NotNull
    public final CallMuteReceiver U = new CallMuteReceiver();
    @NotNull
    public final CameraEventReceiver V = new CameraEventReceiver();
    @NotNull
    public final MusicControlReceiver W = new MusicControlReceiver();
    @NotNull
    public final MusicMetaDataReceiver X = new MusicMetaDataReceiver();
    @NotNull
    public final FactoryResetReceiver Y = new FactoryResetReceiver();
    @NotNull
    public final AppListLoaderReceiver Z = new AppListLoaderReceiver();
    @NotNull
    public final SendQuickReplyEventReceiver a0 = new SendQuickReplyEventReceiver();
    @NotNull
    public final RebootReceiver b0 = new RebootReceiver();
    @NotNull
    public final AgpsSuccessReceiver c0 = new AgpsSuccessReceiver();
    @NotNull
    public final LowBatteryReceiver d0 = new LowBatteryReceiver();
    @NotNull
    public final TroubleshootNotificationClickReceiver e0 = new TroubleshootNotificationClickReceiver();
    @NotNull
    public final NotifyNavigationEventReceiver f0 = new NotifyNavigationEventReceiver();
    @NotNull
    public final RequestNavigationEventReceiver g0 = new RequestNavigationEventReceiver();
    @NotNull
    public final SetNavigationInstructionsReceiver h0 = new SetNavigationInstructionsReceiver();
    @NotNull
    public final NavigationStatusReceiver i0 = new NavigationStatusReceiver();
    @NotNull
    public final SendSOSEventReceiver j0 = new SendSOSEventReceiver();
    @NotNull
    public final PersonalizationEventReceiver k0 = new PersonalizationEventReceiver();
    @NotNull
    public final GenericBandApplication$phoneStateChangeListener$1 m0 = new PhoneStateListener() { // from class: com.coveiot.android.boat.GenericBandApplication$phoneStateChangeListener$1
        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, @Nullable String str) {
            LogHelper.d(GenericBandApplication.this.getTAG(), "onCallStateChanged called");
            CallNotificationHelper callNotificationHelper = CallNotificationHelper.INSTANCE;
            Context applicationContext = GenericBandApplication.this.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            callNotificationHelper.onCallStateChangedL(applicationContext, GenericBandApplication.this, i, str);
        }
    };

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getACTION_SYNC_COMPLETE() {
            return GenericBandApplication.n0;
        }

        @NotNull
        public final CoroutineExceptionHandler getHandler() {
            return GenericBandApplication.p0;
        }

        public final boolean isVolumeChangedByWatch() {
            return GenericBandApplication.o0;
        }

        public final void setVolumeChangedByWatch(boolean z) {
            GenericBandApplication.o0 = z;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.boat.GenericBandApplication$initializeRecaptchaClient$1", f = "GenericBandApplication.kt", i = {}, l = {220}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes3.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Application $application;
        public int label;
        public final /* synthetic */ GenericBandApplication this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Application application, GenericBandApplication genericBandApplication, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$application = application;
            this.this$0 = genericBandApplication;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$application, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object m110getClientBWLJW6A$default;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Recaptcha recaptcha = Recaptcha.INSTANCE;
                Application application = this.$application;
                this.label = 1;
                m110getClientBWLJW6A$default = Recaptcha.m110getClientBWLJW6A$default(recaptcha, application, BuildConfig.RECAPTCH_SITE_KEY, 0L, this, 4, null);
                if (m110getClientBWLJW6A$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
                m110getClientBWLJW6A$default = ((Result) obj).m132unboximpl();
            }
            GenericBandApplication genericBandApplication = this.this$0;
            if (Result.m130isSuccessimpl(m110getClientBWLJW6A$default)) {
                genericBandApplication.setRecaptchaClient((RecaptchaClient) m110getClientBWLJW6A$default);
            }
            GenericBandApplication genericBandApplication2 = this.this$0;
            Throwable m126exceptionOrNullimpl = Result.m126exceptionOrNullimpl(m110getClientBWLJW6A$default);
            if (m126exceptionOrNullimpl != null) {
                if (m126exceptionOrNullimpl instanceof RecaptchaException) {
                    ((RecaptchaException) m126exceptionOrNullimpl).getErrorCode();
                }
                LogHelper.e(genericBandApplication2.getTAG() + " recaptcha", m126exceptionOrNullimpl.getLocalizedMessage());
            }
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes3.dex */
    public static final class b extends Lambda implements Function1<Throwable, Unit> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable th) {
            if (th instanceof UndeliverableException) {
                boolean z = th.getCause() instanceof BleException;
            }
        }
    }

    public static final void A(FirebaseRemoteConfig remoteConfig, GenericBandApplication this$0, Void r3, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(AppConstants.SHIPBOOK_STATE.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig\n           …nts.SHIPBOOK_STATE.value)");
        if (string.equals(AppConstants.ALL_ON.getValue())) {
            LogHelper.initialize(true);
            LogsHelper.init(true);
        } else {
            LogHelper.initialize(false);
            LogsHelper.init(false);
        }
        String str = this$0.L;
        LogHelper.d(str, "Config params updated: " + r3 + string);
    }

    public static final void B(GenericBandApplication this$0, ConnectionStatus connectionStatus) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.L, connectionStatus != null ? connectionStatus.toString() : null);
        LocalBroadcastManager.getInstance(this$0).sendBroadcast(new Intent(ThemeConstants.ACTION_CONNECTION_STATE_CHANGED.getValue()));
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            if (this$0.l0) {
                CoveEventBusManager.getInstance().getEventBus().post(new OnDeviceConnected());
                this$0.l0 = false;
                SessionManager.getInstance(this$0).saveIsWatchReconnected(Boolean.TRUE);
                SoccerSportsServiceNew.Companion.setWasWatchReconnected(true);
                AppSessionManager.getInstance(this$0).setIsDfuFailed(false);
                if (BleApiManager.getInstance(this$0).getDeviceType() == DeviceType.v7) {
                    this$0.disableSocialDistancingInBand();
                }
                StatusApiDataHelper.Companion.getInstance(this$0).saveStatusDataToServer(true);
            }
        } else if (this$0.l0) {
        } else {
            CoveEventBusManager.getInstance().getEventBus().post(new OnDeviceDisconnected());
            this$0.l0 = true;
            StatusApiDataHelper.Companion.getInstance(this$0).saveStatusDataToServer(true);
            SyncManager.getInstance().resetSyncProgress();
        }
    }

    public static /* synthetic */ void registerPhoneStateChangeListener$default(GenericBandApplication genericBandApplication, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        genericBandApplication.registerPhoneStateChangeListener(z);
    }

    public static /* synthetic */ void unregisterPhoneStateChangeListener$default(GenericBandApplication genericBandApplication, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        genericBandApplication.unregisterPhoneStateChangeListener(z);
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(final FirebaseRemoteConfig remoteConfig, final GenericBandApplication this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r4 = (Void) task.getResult();
            new RemoteConfig();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.boat.c
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    GenericBandApplication.A(FirebaseRemoteConfig.this, this$0, r4, task2);
                }
            });
            return;
        }
        LogHelper.e(this$0.L, "Remote Config Failed");
    }

    public final void C() {
        IntentFilter intentFilter = new IntentFilter(com.coveiot.android.bleabstract.Constants.FIND_MY_PHONE_BROADCAST_INTENT);
        IntentFilter intentFilter2 = new IntentFilter(com.coveiot.android.bleabstract.Constants.PROBE_DATA_BROADCAST_INTENT);
        IntentFilter intentFilter3 = new IntentFilter(n0);
        IntentFilter intentFilter4 = new IntentFilter(com.coveiot.android.bleabstract.Constants.DEVICE_SETTINGS_BROADCAST_INTENT);
        IntentFilter intentFilter5 = new IntentFilter(com.coveiot.android.bleabstract.Constants.CALL_REJECT_BROADCAST_INTENT);
        IntentFilter intentFilter6 = new IntentFilter(com.coveiot.android.bleabstract.Constants.CALL_MUTE_BROADCAST_INTENT);
        IntentFilter intentFilter7 = new IntentFilter(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT);
        IntentFilter intentFilter8 = new IntentFilter(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT);
        IntentFilter intentFilter9 = new IntentFilter(com.coveiot.android.bleabstract.Constants.SEND_QUICK_REPLY_BROADCAST_INTENT);
        IntentFilter intentFilter10 = new IntentFilter(com.coveiot.android.bleabstract.Constants.NOTIFY_SOS_BROADCAST_INTENT_EXTRA);
        IntentFilter intentFilter11 = new IntentFilter();
        intentFilter11.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter11.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter11.addDataScheme("package");
        intentFilter11.setPriority(999);
        IntentFilter intentFilter12 = new IntentFilter();
        intentFilter12.addAction(AppConstants.MUSIC_METADATA_BROADCAST_INTENT.getValue());
        intentFilter12.addAction(AppConstants.MUSIC_PLAYBACK_STATE_CHANGE_BROADCAST_INTENT.getValue());
        IntentFilter intentFilter13 = new IntentFilter(com.coveiot.android.bleabstract.Constants.FACTORY_RESET_BROADCAST_INTENT);
        IntentFilter intentFilter14 = new IntentFilter(com.coveiot.android.bleabstract.Constants.AGPS_UPDATED_BROADCAST_INTENT);
        IntentFilter intentFilter15 = new IntentFilter();
        Dashboard2Constants.Companion companion = Dashboard2Constants.Companion;
        intentFilter15.addAction(companion.getACTION_LOW_BATTERY_NOTIFICATION_SHOW());
        intentFilter15.addAction(companion.getACTION_LOW_BATTERY_NOTIFICATION_DISMISS());
        IntentFilter intentFilter16 = new IntentFilter();
        intentFilter16.addAction(companion.getTROUBLESHOOT_NOTIFICATION_CLICKED());
        IntentFilter intentFilter17 = new IntentFilter();
        intentFilter17.addAction(AppConstants.NOTIFY_NAVIGATION_EVENT_BROADCAST_INTENT.getValue());
        IntentFilter intentFilter18 = new IntentFilter();
        intentFilter18.addAction(AppConstants.REQUEST_NAVIGATION_EVENT_BROADCAST_INTENT.getValue());
        IntentFilter intentFilter19 = new IntentFilter();
        intentFilter19.addAction(AppConstants.REQUEST_NAVIGATION_FEATURE_CONTENT_BROADCAST_INTENT.getValue());
        IntentFilter intentFilter20 = new IntentFilter();
        intentFilter20.addAction(AppConstants.REQUEST_NAVIGATION_STATUS_BROADCAST_INTENT.getValue());
        IntentFilter intentFilter21 = new IntentFilter(com.coveiot.android.bleabstract.Constants.PERSONALIZED_MESSAGE_BROADCAST_INTENT);
        CoveEventBusManager.getInstance().getEventBus().register(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.N, intentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.O, intentFilter2);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.P, intentFilter3);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.S, intentFilter4);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.T, intentFilter5);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.U, intentFilter6);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.V, intentFilter7);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.W, intentFilter8);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.X, intentFilter12);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.Y, intentFilter13);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.a0, intentFilter9);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.c0, intentFilter14);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.d0, intentFilter15);
        registerReceiver(this.Z, intentFilter11);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.e0, intentFilter16);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.j0, intentFilter10);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f0, intentFilter17);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.g0, intentFilter18);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.h0, intentFilter19);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.h0, intentFilter19);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.i0, intentFilter20);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.k0, intentFilter21);
        IntentFilter intentFilter22 = new IntentFilter();
        intentFilter22.addAction("android.intent.action.BOOT_COMPLETED");
        registerReceiver(this.b0, intentFilter22);
        this.H = new VolumeControlListener(this, new Handler());
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Settings.System.CONTENT_URI;
        ContentObserver contentObserver = this.H;
        Intrinsics.checkNotNull(contentObserver);
        contentResolver.registerContentObserver(uri, true, contentObserver);
        registerPhoneStateChangeListener(true);
    }

    public final void D() {
        registerConnectionListener();
        if (SessionManager.getInstance(this).isLoggedIn() && BleApiManager.getInstance(this).getBleApi() == null) {
            v();
        }
    }

    public final void E() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.N);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.O);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.P);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.Q);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.S);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.T);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.U);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.V);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.W);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.X);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.Y);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.a0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.c0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.d0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.e0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.j0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.g0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.h0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.i0);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.k0);
        unregisterReceiver(this.Z);
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        unregisterReceiver(this.b0);
        if (this.H != null) {
            ContentResolver contentResolver = getContentResolver();
            ContentObserver contentObserver = this.H;
            Intrinsics.checkNotNull(contentObserver);
            contentResolver.unregisterContentObserver(contentObserver);
        }
        unregisterPhoneStateChangeListener$default(this, false, 1, null);
    }

    public final void clearContactTracingSyncTimeOutNotification() {
        NotificationManager.getInstance().cancleNotification(this, this.G);
    }

    public final void disableSocialDistancingInBand() {
        SetSocialDistanceScanSettingsRequest setSocialDistanceScanSettingsRequest = new SetSocialDistanceScanSettingsRequest.Builder().setScanPeriod(0).setAddressFilter("").build();
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        Intrinsics.checkNotNullExpressionValue(setSocialDistanceScanSettingsRequest, "setSocialDistanceScanSettingsRequest");
        bleApi.setUserSettings(setSocialDistanceScanSettingsRequest, new SettingsResultListener() { // from class: com.coveiot.android.boat.GenericBandApplication$disableSocialDistancingInBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(GenericBandApplication.this.getTAG(), error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(GenericBandApplication.this.getTAG(), response.toString());
            }
        });
    }

    @NotNull
    public final AgpsSuccessReceiver getAgpsSuccessReceiver() {
        return this.c0;
    }

    @NotNull
    public final AppListLoaderReceiver getAppListLoadReceiver() {
        return this.Z;
    }

    public final int getCONTACT_TRACING_NOTIFICATION_ID() {
        return this.G;
    }

    @NotNull
    public final CallMuteReceiver getCallMuteReceiver() {
        return this.U;
    }

    @NotNull
    public final CallRejectReceiver getCallRejectReceiver() {
        return this.T;
    }

    @NotNull
    public final CameraEventReceiver getCameraEventReceiver() {
        return this.V;
    }

    @NotNull
    public final DeviceSettingsInfoReceiver getDeviceSettingsInfoReceiver() {
        return this.S;
    }

    @NotNull
    public final FactoryResetReceiver getFactoryResetReceiver() {
        return this.Y;
    }

    @NotNull
    public final FindMyPhoneReceiver getFindMyPhoneReceiver() {
        return this.N;
    }

    @NotNull
    public final Handler getHandler() {
        return this.R;
    }

    public final boolean getHasDisconnected() {
        return this.l0;
    }

    @NotNull
    public final LocationBreachReceiver getLocationBreachRecevier() {
        return this.Q;
    }

    @NotNull
    public final LowBatteryReceiver getLowBatteryReceiver() {
        return this.d0;
    }

    @Nullable
    public final ContentObserver getMContentObserver() {
        return this.H;
    }

    @NotNull
    public final MusicControlReceiver getMusicControlReceiver() {
        return this.W;
    }

    @NotNull
    public final MusicMetaDataReceiver getMusicMetaDataReceiver() {
        return this.X;
    }

    @NotNull
    public final NavigationStatusReceiver getNavigationStatusReceiver() {
        return this.i0;
    }

    @NotNull
    public final NotifyNavigationEventReceiver getNotifyNavigationEventReceiver() {
        return this.f0;
    }

    @NotNull
    public final PersonalizationEventReceiver getPersonalizationEventReceiver() {
        return this.k0;
    }

    @NotNull
    public final ProbeBroadcastReceiver getProbeReciever() {
        return this.O;
    }

    @NotNull
    public final RebootReceiver getRebootReceiver() {
        return this.b0;
    }

    @Nullable
    public final RecaptchaClient getRecaptchaClient() {
        return this.B;
    }

    @NotNull
    public final RequestNavigationEventReceiver getRequestNavigationEventReceiver() {
        return this.g0;
    }

    @NotNull
    public final SendQuickReplyEventReceiver getSendQuickReplyReceiver() {
        return this.a0;
    }

    @NotNull
    public final SendSOSEventReceiver getSendSOSEventReceiver() {
        return this.j0;
    }

    @NotNull
    public final SetNavigationInstructionsReceiver getSetNavigationInstructionsReceiver() {
        return this.h0;
    }

    @NotNull
    public final SyncCompleteReciever getSyncCompleteReciever() {
        return this.P;
    }

    @NotNull
    public final String getTAG() {
        return this.L;
    }

    @Nullable
    public final TelephonyManager getTelephonyManager() {
        return this.J;
    }

    @NotNull
    public final TroubleshootNotificationClickReceiver getTroubleshootNotificationClickReceiver() {
        return this.e0;
    }

    public final void initializeRecaptchaClient(@NotNull Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        e.e(GlobalScope.INSTANCE, null, null, new a(application, this, null), 3, null);
    }

    public final boolean isAppTerminated() {
        return this.C;
    }

    public final boolean isBleSetupCompleted() {
        return this.I;
    }

    public final boolean isHomeFragmentVisible() {
        return this.D;
    }

    public final boolean isMuteCommandReceived() {
        return this.F;
    }

    public final boolean isPhoneStateChangeListenerRegistered() {
        return this.K;
    }

    public final boolean isStringsMatchesWithList(@NotNull String string, @NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(list, "list");
        boolean z = false;
        for (String str : list) {
            String lowerCase = string.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            String lowerCase2 = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
            if (m.startsWith$default(lowerCase, lowerCase2, false, 2, null)) {
                z = true;
            }
        }
        return z;
    }

    public final boolean isWatchReconnected() {
        return this.E;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NotNull Activity p02, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(p02, "p0");
        this.C = false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NotNull Activity p02) {
        Intrinsics.checkNotNullParameter(p02, "p0");
        if (this.C && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this).getBleApi().cleanUpCommands();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NotNull Activity p02) {
        Intrinsics.checkNotNullParameter(p02, "p0");
        this.C = true;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NotNull Activity p02) {
        Intrinsics.checkNotNullParameter(p02, "p0");
        this.C = false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NotNull Activity p02, @NotNull Bundle p1) {
        Intrinsics.checkNotNullParameter(p02, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NotNull Activity p02) {
        Intrinsics.checkNotNullParameter(p02, "p0");
        LogHelper.d(this.L, "onActivityStarted");
        this.C = false;
        if (this.I) {
            return;
        }
        this.I = true;
        D();
        LogHelper.d(this.L, "setupBle done");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NotNull Activity p02) {
        Intrinsics.checkNotNullParameter(p02, "p0");
    }

    @Override // com.mappls.sdk.navigation.NavigationApplication, android.app.Application
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
        x();
        LogHelper.d(this.L, "onCreate called");
        Object systemService = getSystemService("phone");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
        this.J = (TelephonyManager) systemService;
        this.I = false;
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        PreferenceManager.initPreferenceMgr(this);
        CoveApi.initCoveApi(this, null);
        registerActivityLifecycleCallbacks(this);
        initializeRecaptchaClient(this);
        try {
            BLEManager.onApplicationCreate(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WristbandApplication.init(this);
        try {
            final b bVar = b.INSTANCE;
            RxJavaPlugins.setErrorHandler(new Consumer() { // from class: com.coveiot.android.boat.d
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    GenericBandApplication.y(Function1.this, obj);
                }
            });
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("error while handling rxjava error ");
            e2.printStackTrace();
            sb.append(Unit.INSTANCE);
            LogHelper.i("rxjava", sb.toString());
        }
        LogHelper.initialize(true);
        LogsHelper.init(true);
        CoveAnalyticsManager.Companion companion = CoveAnalyticsManager.Companion;
        AnalyticsType analyticsType = AnalyticsType.CLEVER_TAP;
        companion.initialize(this, CollectionsKt__CollectionsKt.arrayListOf(AnalyticsType.FIREBASE, analyticsType));
        companion.initAnalyticsType(analyticsType);
        CleverTapAPI.createNotificationChannel(getApplicationContext(), "boatcrestpush", "boatcrestpush", "notification", 5, true);
        SyncManager.initialze(new DataSyncImpl(this), this);
        C();
        SportsPreferenceModel sportsNotification = SportsPreference.Companion.getSportsNotification(this);
        if (sportsNotification != null) {
            if (m.equals$default(sportsNotification.getSport(), SportsType.Football.name(), false, 2, null)) {
                SportsApiClient.Companion.initSportsApi(getApplicationContext(), SportType.SOCCER);
            } else {
                SportsApiClient.Companion.initSportsApi(getApplicationContext(), SportType.CRICKET);
            }
        } else {
            SportsApiClient.Companion.initSportsApi(getApplicationContext(), SportType.CRICKET);
        }
        AndroidAutoKillManager.getInstance(getApplicationContext()).init();
        if (SessionManager.getInstance(this).isLoggedIn()) {
            Dashboard2Utils.Companion.scheduleJob(this);
        }
        if (SessionManager.getInstance(this).isLoggedIn() && m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.j1790_device), false)) {
            Dashboard2Utils.Companion.scheduleJob(this);
        }
        ShipBook.Companion.start$default(ShipBook.Companion, this, AppConstants.SHIPBOOK_APP_ID.getValue(), AppConstants.SHIPBOOK_APP_KEY.getValue(), null, null, 24, null);
        String weightUnit = UserDataManager.getInstance(this).getWeightUnit();
        UNIT_TYPE unit_type = UNIT_TYPE.IMPERIAL;
        if (weightUnit.equals(unit_type.toString())) {
            BaseUnitType.INSTANCE.setUnitTypeWeight(unit_type);
        } else {
            BaseUnitType.INSTANCE.setUnitTypeWeight(UNIT_TYPE.METRIC);
        }
        if (UserDataManager.getInstance(this).getHeightUnit().equals(unit_type.toString())) {
            BaseUnitType.INSTANCE.setUnitTypeHeight(unit_type);
        } else {
            BaseUnitType.INSTANCE.setUnitTypeHeight(UNIT_TYPE.METRIC);
        }
        if (UserDataManager.getInstance(this).getStrideLengthUnit().equals(unit_type.toString())) {
            BaseUnitType.INSTANCE.setUnitTypeStrideLength(unit_type);
        } else {
            BaseUnitType.INSTANCE.setUnitTypeStrideLength(UNIT_TYPE.METRIC);
        }
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(firebaseAnalytics, "getInstance(this)");
        firebaseAnalytics.setUserProperty(AppConstants.BUILD_FLAVOUR.getValue(), "prod");
        firebaseAnalytics.setUserProperty(AppConstants.BUILD_TYPE.getValue(), "release");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        remoteConfig.fetch(1000L).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.boat.b
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                GenericBandApplication.z(FirebaseRemoteConfig.this, this, task);
            }
        });
        Dashboard2Utils.Companion.loadScanDeviceOnDisconnectConfiguration(this);
        w();
    }

    @Subscribe
    public final void onMuteReceived(@NotNull MuteReceived muteReceived) {
        Intrinsics.checkNotNullParameter(muteReceived, "muteReceived");
        this.F = true;
    }

    @Override // com.mappls.sdk.navigation.NavigationApplication, android.app.Application
    public void onTerminate() {
        super.onTerminate();
        E();
        if (BleApiManager.getInstance(this).getBleApi() == null || this.M == null) {
            return;
        }
        LiveData<ConnectionStatus> registerConnectionStatus = BleApiManager.getInstance(this).getBleApi().registerConnectionStatus();
        Observer<ConnectionStatus> observer = this.M;
        Intrinsics.checkNotNull(observer);
        registerConnectionStatus.removeObserver(observer);
    }

    public final void registerConnectionListener() {
        if (BleApiManager.getInstance(this).getBleApi() != null) {
            this.M = new Observer() { // from class: com.coveiot.android.boat.a
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    GenericBandApplication.B(GenericBandApplication.this, (ConnectionStatus) obj);
                }
            };
            LiveData<ConnectionStatus> registerConnectionStatus = BleApiManager.getInstance(this).getBleApi().registerConnectionStatus();
            Observer<ConnectionStatus> observer = this.M;
            Intrinsics.checkNotNull(observer);
            registerConnectionStatus.observeForever(observer);
        }
    }

    public final void registerPhoneStateChangeListener(boolean z) {
        if (!this.K || z) {
            boolean z2 = true;
            if (z) {
                unregisterPhoneStateChangeListener(true);
            }
            try {
                TelephonyManager telephonyManager = this.J;
                Intrinsics.checkNotNull(telephonyManager);
                telephonyManager.listen(this.m0, 32);
            } catch (IllegalStateException e) {
                unregisterPhoneStateChangeListener(true);
                e.printStackTrace();
                z2 = false;
                this.K = z2;
            } catch (SecurityException e2) {
                e2.printStackTrace();
                z2 = false;
                this.K = z2;
            } catch (Exception e3) {
                e3.printStackTrace();
                TelephonyManager telephonyManager2 = this.J;
                Intrinsics.checkNotNull(telephonyManager2);
                telephonyManager2.listen(this.m0, 0);
                z2 = false;
                this.K = z2;
            }
            this.K = z2;
        }
    }

    public final void setAppTerminated(boolean z) {
        this.C = z;
    }

    public final void setBleSetupCompleted(boolean z) {
        this.I = z;
    }

    public final void setHasDisconnected(boolean z) {
        this.l0 = z;
    }

    public final void setHomeFragmentVisible(boolean z) {
        this.D = z;
    }

    public final void setMContentObserver(@Nullable ContentObserver contentObserver) {
        this.H = contentObserver;
    }

    public final void setMuteCommandReceived(boolean z) {
        this.F = z;
    }

    public final void setPhoneStateChangeListenerRegistered(boolean z) {
        this.K = z;
    }

    public final void setRecaptchaClient(@Nullable RecaptchaClient recaptchaClient) {
        this.B = recaptchaClient;
    }

    public final void setTelephonyManager(@Nullable TelephonyManager telephonyManager) {
        this.J = telephonyManager;
    }

    public final void setWatchReconnected(boolean z) {
        this.E = z;
    }

    public final void unregisterPhoneStateChangeListener(boolean z) {
        try {
            if (this.K || z) {
                TelephonyManager telephonyManager = this.J;
                Intrinsics.checkNotNull(telephonyManager);
                telephonyManager.listen(this.m0, 0);
                this.K = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void v() {
        DeviceType bleDeviceType = DeviceUtils.Companion.getBleDeviceType(this);
        if (bleDeviceType != null) {
            BleApiManager.getInstance(this).init(bleDeviceType);
        }
    }

    public final void w() {
        MapplsNavigationHelper.getInstance().init(this);
        MapplsNavigationViewHelper.getInstance().init(this);
        MapplsAccountManager.getInstance().setRestAPIKey("f5503cdb43647efc06bd9bacede3b814");
        MapplsAccountManager.getInstance().setMapSDKKey("f5503cdb43647efc06bd9bacede3b814");
        MapplsAccountManager.getInstance().setAtlasClientId(BuildConfig.MAPPLS_MAP_ATLAS_CLIENT_ID);
        MapplsAccountManager.getInstance().setAtlasClientSecret(BuildConfig.MAPPLS_MAP_ATLAS_CLIENT_SECRET);
        MapplsNavigationHelper.getInstance().setNavigationActivityClass(ActivityDirections.class);
        MapplsNavigationHelper.getInstance().setJunctionViewEnabled(true);
        MapplsNavigationHelper.getInstance().setNavigationEventEnabled(true);
        Mappls.getInstance(this);
    }

    public final void x() {
    }
}
