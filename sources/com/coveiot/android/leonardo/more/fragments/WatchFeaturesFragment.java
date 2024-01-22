package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.EnterRemoteCameraRequest;
import com.coveiot.android.bleabstract.request.StopFindMyWatchRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.customreminders.activities.ActivityCustomReminders;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.googlefit.GoogleFitActivity;
import com.coveiot.android.leonardo.more.activities.ActivityAlarmNew;
import com.coveiot.android.leonardo.more.activities.ActivityBTCalling;
import com.coveiot.android.leonardo.more.activities.ActivityContactTracingSync;
import com.coveiot.android.leonardo.more.activities.ActivityDrinkWaterReminder;
import com.coveiot.android.leonardo.more.activities.ActivityEventReminder;
import com.coveiot.android.leonardo.more.activities.ActivityFindMyWatch;
import com.coveiot.android.leonardo.more.activities.ActivityNotifications;
import com.coveiot.android.leonardo.more.activities.ActivityNudgesSettings;
import com.coveiot.android.leonardo.more.activities.ActivityScheduleReminder;
import com.coveiot.android.leonardo.more.activities.ActivitySedentaryReminder;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation;
import com.coveiot.android.leonardo.remotecamera.CameraActivity;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.SupportedDeviceFeatureUtilsNew;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.remotecommandframework.ViewModelFactory;
import com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse;
import com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel;
import com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting;
import com.coveiot.android.sos.ActivitySOS;
import com.coveiot.android.sos.ActivitySOSSettings;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.watchfaceui.activities.ActivityWatchFace;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.covepreferences.data.FirmwareCapabilityData;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WatchFeaturesFragment extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean m;
    public DataSyncViewModel n;
    @Nullable
    public CountDownTimer o;
    public AlexaApiCallHandlerViewModel p;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final WatchFeaturesFragment newInstance() {
            return new WatchFeaturesFragment();
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function3<String, Boolean, Boolean, Unit> {
        public a() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool, Boolean bool2) {
            invoke(str, bool.booleanValue(), bool2.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(@Nullable String str, boolean z, boolean z2) {
            if (z2) {
                WatchFeaturesFragment.this.dismissProgress();
                if (z) {
                    AppNavigator.Companion companion = AppNavigator.Companion;
                    Context requireContext = WatchFeaturesFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    companion.navigateToNavigationScreen(requireContext, HexStringBuilder.DEFAULT_SEPARATOR, false);
                    return;
                }
                AppNavigator.Companion companion2 = AppNavigator.Companion;
                Context requireContext2 = WatchFeaturesFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                companion2.navigateToNavigationScreen(requireContext2, HexStringBuilder.DEFAULT_SEPARATOR, true);
                return;
            }
            WatchFeaturesFragment.this.dismissProgress();
            Toast.makeText(WatchFeaturesFragment.this.requireContext(), str, 0).show();
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<AppToAppLinkingResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment$initObserver$1$1$1", f = "WatchFeaturesFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ AppToAppLinkingResponse $linkedDataResponse;
            public final /* synthetic */ WatchFeaturesFragment $this_run;
            private /* synthetic */ Object L$0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(WatchFeaturesFragment watchFeaturesFragment, AppToAppLinkingResponse appToAppLinkingResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$this_run = watchFeaturesFragment;
                this.$linkedDataResponse = appToAppLinkingResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.$this_run, this.$linkedDataResponse, continuation);
                aVar.L$0 = obj;
                return aVar;
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x0063, code lost:
                if (kotlin.text.m.equals(r5.getStatus(), "ENABLED", false) != false) goto L15;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Class<com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaActivity> r0 = com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaActivity.class
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                    int r1 = r7.label
                    if (r1 != 0) goto L103
                    kotlin.ResultKt.throwOnFailure(r8)
                    java.lang.Object r8 = r7.L$0
                    kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r8 = r7.$this_run
                    r8.dismissProgress()
                    com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse r8 = r7.$linkedDataResponse
                    boolean r8 = r8.isSuccess()
                    r1 = 2131954922(0x7f130cea, float:1.9546357E38)
                    r2 = 1
                    r3 = 0
                    if (r8 == 0) goto Lbd
                    com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse r8 = r7.$linkedDataResponse
                    com.coveiot.android.remotecommandframework.alexa.model.EnablementData r8 = r8.getData()
                    if (r8 == 0) goto La8
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r4 = r7.$this_run
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r5 = r8.getAccountLink()
                    if (r5 == 0) goto L7f
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r5 = r8.getAccountLink()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                    java.lang.String r5 = r5.getStatus()
                    if (r5 == 0) goto L7f
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r5 = r8.getAccountLink()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                    java.lang.String r5 = r5.getStatus()
                    java.lang.String r6 = "LINKED"
                    boolean r5 = kotlin.text.m.equals(r5, r6, r3)
                    if (r5 != 0) goto L65
                    com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData r5 = r8.getAccountLink()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                    java.lang.String r5 = r5.getStatus()
                    java.lang.String r6 = "ENABLED"
                    boolean r5 = kotlin.text.m.equals(r5, r6, r3)
                    if (r5 == 0) goto L7f
                L65:
                    android.content.Context r0 = r4.requireContext()
                    com.coveiot.covepreferences.SessionManager r0 = com.coveiot.covepreferences.SessionManager.getInstance(r0)
                    r0.setAlexaAccountLinkingStatus(r2)
                    android.content.Intent r0 = new android.content.Intent
                    android.content.Context r2 = r4.requireContext()
                    java.lang.Class<com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaResultActivity> r5 = com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaResultActivity.class
                    r0.<init>(r2, r5)
                    r4.startActivity(r0)
                    goto L96
                L7f:
                    android.content.Context r2 = r4.requireContext()
                    com.coveiot.covepreferences.SessionManager r2 = com.coveiot.covepreferences.SessionManager.getInstance(r2)
                    r2.setAlexaAccountLinkingStatus(r3)
                    android.content.Intent r2 = new android.content.Intent
                    android.content.Context r5 = r4.requireContext()
                    r2.<init>(r5, r0)
                    r4.startActivity(r2)
                L96:
                    android.content.Context r0 = r4.requireContext()
                    com.coveiot.covepreferences.SessionManager r0 = com.coveiot.covepreferences.SessionManager.getInstance(r0)
                    java.lang.String r8 = r8.getSkillEnabledFrom()
                    r0.saveAlexaAccountLinkedFrom(r8)
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    goto La9
                La8:
                    r8 = 0
                La9:
                    if (r8 != 0) goto L100
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r8 = r7.$this_run
                    android.content.Context r0 = r8.requireContext()
                    java.lang.String r8 = r8.getString(r1)
                    android.widget.Toast r8 = android.widget.Toast.makeText(r0, r8, r3)
                    r8.show()
                    goto L100
                Lbd:
                    com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse r8 = r7.$linkedDataResponse
                    int r8 = r8.getErrorCode()
                    r4 = 400(0x190, float:5.6E-43)
                    if (r4 > r8) goto Lcc
                    r4 = 500(0x1f4, float:7.0E-43)
                    if (r8 >= r4) goto Lcc
                    goto Lcd
                Lcc:
                    r2 = r3
                Lcd:
                    if (r2 == 0) goto Led
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r8 = r7.$this_run
                    android.content.Context r8 = r8.requireContext()
                    com.coveiot.covepreferences.SessionManager r8 = com.coveiot.covepreferences.SessionManager.getInstance(r8)
                    r8.setAlexaAccountLinkingStatus(r3)
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r8 = r7.$this_run
                    android.content.Intent r1 = new android.content.Intent
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r2 = r7.$this_run
                    android.content.Context r2 = r2.requireContext()
                    r1.<init>(r2, r0)
                    r8.startActivity(r1)
                    goto L100
                Led:
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r8 = r7.$this_run
                    android.content.Context r8 = r8.requireContext()
                    com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment r0 = r7.$this_run
                    java.lang.String r0 = r0.getString(r1)
                    android.widget.Toast r8 = android.widget.Toast.makeText(r8, r0, r3)
                    r8.show()
                L100:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                L103:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment.b.a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(AppToAppLinkingResponse appToAppLinkingResponse) {
            invoke2(appToAppLinkingResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(AppToAppLinkingResponse appToAppLinkingResponse) {
            WatchFeaturesFragment watchFeaturesFragment = WatchFeaturesFragment.this;
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(watchFeaturesFragment), Dispatchers.getMain(), null, new a(watchFeaturesFragment, appToAppLinkingResponse, null), 2, null);
        }
    }

    @JvmStatic
    @NotNull
    public static final WatchFeaturesFragment newInstance() {
        return Companion.newInstance();
    }

    public static final void u(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void w(WatchFeaturesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.requireContext().getString(R.string.watch_face);
        Intrinsics.checkNotNullExpressionValue(string, "requireContext().getString(R.string.watch_face)");
        Drawable drawable = this$0.requireContext().getDrawable(R.drawable.ic_watch_more);
        Intrinsics.checkNotNull(drawable);
        this$0.t(new SupportedDeviceFeatureUtilsNew.Feature(string, drawable));
    }

    public static final void y(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void z(WatchFeaturesFragment this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            Intent intent = new Intent(this$0.getContext(), ActivityPhoneValidation.class);
            intent.addFlags(335577088);
            this$0.requireContext().startActivity(intent);
            SessionManager.getInstance(this$0.getContext()).setIsGuestUser(false);
            SessionManager.getInstance(this$0.getContext()).setOnBoardingComplete(false);
            this$0.requireActivity().finish();
        } else {
            AppNavigator.Companion companion = AppNavigator.Companion;
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion.navigateToPairYourDevice(requireContext2);
        }
        guestOrPairDevicePopup.dismiss();
    }

    public final void A() {
        BleApiManager.getInstance(requireContext()).getBleApi().setUserSettings(new StopFindMyWatchRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment$stopFindMyWatch$1
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

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Nullable
    public final CountDownTimer getCountDownTimer() {
        return this.o;
    }

    public final void initObserver() {
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = this.p;
        if (alexaApiCallHandlerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodelAlexaApiCallHandler");
            alexaApiCallHandlerViewModel = null;
        }
        MutableLiveData<AppToAppLinkingResponse> alexaAccountLinkingStatusLiveData = alexaApiCallHandlerViewModel.getAlexaAccountLinkingStatusLiveData();
        final b bVar = new b();
        alexaAccountLinkingStatusLiveData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.fragments.g3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFeaturesFragment.u(Function1.this, obj);
            }
        });
    }

    public final boolean o() {
        DataSyncViewModel dataSyncViewModel = this.n;
        if (dataSyncViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
            dataSyncViewModel = null;
        }
        if (dataSyncViewModel.checkIsSyncing()) {
            Toast.makeText(requireContext(), getString(R.string.syncing_please_wait), 1).show();
            return true;
        }
        return false;
    }

    public final void onBackPressed() {
        if (this.m) {
            A();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        this.p = (AlexaApiCallHandlerViewModel) ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(AlexaApiCallHandlerViewModel.class);
        initObserver();
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        this.n = (DataSyncViewModel) ViewModelProviders.of(requireActivity3, new com.coveiot.android.dashboard2.ViewModelFactory(requireActivity4)).get(DataSyncViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_features, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        CountDownTimer countDownTimer = this.o;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer, "null cannot be cast to non-null type android.os.CountDownTimer");
            countDownTimer.cancel();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0024, code lost:
        if (r11.isPairDeviceLater(r12) != false) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0242  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r11, @org.jetbrains.annotations.Nullable android.os.Bundle r12) {
        /*
            Method dump skipped, instructions count: 588
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void p() {
        Type type = new TypeToken<NavigationDisclaimerData>() { // from class: com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment$checkNavigationDisclaimerToProceed$navigationDisclaimerDataType$1
        }.getType();
        Object fromJson = new Gson().fromJson(SessionManager.getInstance(requireContext()).getNavigationDiscliamerData(), type);
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(navigati…gationDisclaimerDataType)");
        NavigationDisclaimerData navigationDisclaimerData = (NavigationDisclaimerData) fromJson;
        if (AppUtils.isNetConnected(requireContext())) {
            DataSyncViewModel dataSyncViewModel = null;
            BaseFragment.showProgress$default(this, false, 1, null);
            DataSyncViewModel dataSyncViewModel2 = this.n;
            if (dataSyncViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
            } else {
                dataSyncViewModel = dataSyncViewModel2;
            }
            dataSyncViewModel.checkNavigationDisclaimerAcceptance(navigationDisclaimerData.getVersion(), new a());
            return;
        }
        Toast.makeText(requireContext(), getString(R.string.no_internet), 0).show();
    }

    public final SupportedDeviceFeatureUtilsNew.Control q() {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        SupportedDeviceFeatureUtilsNew.Control control;
        DeviceModelBean deviceModelBean = SessionManager.getInstance(requireContext()).getDeviceModelBean();
        SupportedDeviceFeatureUtilsNew.Control control2 = new SupportedDeviceFeatureUtilsNew.Control(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, -1, 7, null);
        BleApiManager bleApiManager = BleApiManager.getInstance(requireContext());
        if (bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) {
            return control2;
        }
        if (deviceSupportedFeatures.isSedentaryReminderSupported()) {
            control = control2;
            control.setSedentaryReminderSupported(true);
        } else {
            control = control2;
        }
        if (deviceSupportedFeatures.isVibrationAlarmSupported()) {
            control.setVibrationAlarmSupported(true);
        }
        if (deviceSupportedFeatures.isNudgeSupported()) {
            control.setNudgeSupported(true);
        }
        if (deviceSupportedFeatures.isScheduleReminderSupported()) {
            control.setScheduleReminderSupported(true);
        }
        if (deviceSupportedFeatures.isWeatherSupportedInBand() || deviceSupportedFeatures.isWeatherEnableCommandSupported()) {
            control.setWeatherSupported(true);
        }
        if (deviceSupportedFeatures.isRespiratoryRateByPPGSupported()) {
            control.setRespiratoryRateByPPGSupported(true);
        }
        if (deviceSupportedFeatures.isFemaleWellnessSupported()) {
            control.setFemaleWellnessTrackerSupported(true);
        }
        if (deviceSupportedFeatures.getAutoHrSettingsSupported()) {
            control.setAutoHrSupported(true);
        }
        if (deviceSupportedFeatures.getAutoTemperatureSettingsSupported()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!companion.isCZDevice(requireContext)) {
                control.setAutoTemperatureSupported(true);
            }
        }
        if (deviceSupportedFeatures.isAutoStressSettingsSupported()) {
            control.setAutoStressHrvSupported(true);
        }
        if (deviceSupportedFeatures.isFindMyBandSupported()) {
            control.setFindMyBandSupported(true);
        }
        if (deviceSupportedFeatures.isDrinkingReminderSupported()) {
            control.setDrinkReminderSupported(true);
        }
        if (deviceSupportedFeatures.isCustomRemindersSupported()) {
            control.setCustomRemindersSupported(true);
        }
        if (deviceSupportedFeatures.isActivityAutoRecognitionSupported()) {
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!companion2.isCADevice(requireContext2)) {
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                if (!companion2.isCYDevice(requireContext3)) {
                    Context requireContext4 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    if (!companion2.isPS1Device(requireContext4)) {
                        Context requireContext5 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                        if (!companion2.isBESDevice(requireContext5)) {
                            control.setActivityAutoRecognitionSupported(true);
                        }
                    }
                }
            }
            FirmwareCapabilityData firmwareCapability = SessionManager.getInstance(requireContext()).getFirmwareCapability(BleApiManager.getInstance(requireContext()).getBleApi().getMacAddress());
            if (firmwareCapability != null && firmwareCapability.getCapabilities()[0] == 1) {
                control.setActivityAutoRecognitionSupported(true);
            }
        }
        if (deviceSupportedFeatures.isShortcutMenuShowHideCommandSupported()) {
            control.setShortcutsSupported(true);
        }
        if (deviceSupportedFeatures.isActivityShowHideCommandSupported()) {
            control.setSportsTypeSupported(true);
        }
        if (deviceSupportedFeatures.isWorldClockFeatureSupported()) {
            control.setWorldClockSupported(true);
        }
        if (deviceSupportedFeatures.isBTCallingSupported()) {
            control.setBTCallingSupported(true);
        }
        if (deviceSupportedFeatures.isCameraLaunchFromAppIsSupported()) {
            control.setCameraSupported(true);
        }
        if (deviceSupportedFeatures.isAmbientSoundLevelSupported()) {
            control.setAmbientSoundLevelSupported(true);
        }
        if (deviceSupportedFeatures.isAutoSPO2SettingsSupported()) {
            control.setAutoSpo2Supported(true);
        }
        if (deviceSupportedFeatures.isGenericEventReminderSupported()) {
            control.setGenericEventReminderSupported(true);
        }
        if (deviceModelBean != null && deviceModelBean.getRemoteFrameworkSupported() != null && Intrinsics.areEqual(deviceModelBean.getRemoteFrameworkSupported(), Boolean.TRUE)) {
            control.setAlexaFeatureSupported(true);
        }
        if (deviceSupportedFeatures.isCameraEnableSettingsSupported()) {
            control.setCameraControlSettingsSupported(true);
        }
        if (deviceSupportedFeatures.isSensAISupported()) {
            control.setSenseAiSupported(true);
        }
        if (deviceSupportedFeatures.isSportBinFilePushFromAppSupported()) {
            control.setSportBinFilePushSupported(true);
        }
        if (deviceSupportedFeatures.isWalletCardSupported()) {
            control.setWalletCardSupported(true);
        }
        if (deviceSupportedFeatures.isQRCodeSupported()) {
            control.setQRCodeSupported(true);
        }
        if (deviceSupportedFeatures.isBusinessCardSupported()) {
            control.setBusinessCardSupported(true);
        }
        if (deviceSupportedFeatures.isSosSupported()) {
            control.setSOSSupported(true);
        }
        if (deviceSupportedFeatures.isTurnByTurnNavigationSupported()) {
            control.setTbtNavigationSupported(true);
        }
        if (deviceSupportedFeatures.isSmartAlertsSupported()) {
            control.setSmartAlertSupported(true);
            return control;
        }
        return control;
    }

    public final SupportedDeviceFeatureUtilsNew.Control r() {
        SupportedDeviceFeatureUtilsNew.Control control = new SupportedDeviceFeatureUtilsNew.Control(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, -1, 7, null);
        control.setSedentaryReminderSupported(true);
        control.setVibrationAlarmSupported(true);
        control.setNudgeSupported(true);
        control.setScheduleReminderSupported(true);
        control.setWeatherSupported(true);
        control.setRespiratoryRateByPPGSupported(true);
        control.setFemaleWellnessTrackerSupported(true);
        control.setAutoHrSupported(true);
        control.setAutoTemperatureSupported(true);
        control.setAutoStressHrvSupported(true);
        control.setFindMyBandSupported(true);
        control.setDrinkReminderSupported(true);
        control.setCustomRemindersSupported(true);
        control.setBTCallingSupported(true);
        control.setCameraSupported(true);
        control.setAutoSpo2Supported(true);
        control.setGenericEventReminderSupported(true);
        control.setAlexaFeatureSupported(true);
        control.setSenseAiSupported(true);
        return control;
    }

    public final SupportedDeviceFeatureUtilsNew.Gender s() {
        SupportedDeviceFeatureUtilsNew.Gender gender = SupportedDeviceFeatureUtilsNew.Gender.MALE;
        ProfileData userDetails = SessionManager.getInstance(requireContext()).getUserDetails();
        return userDetails != null ? (userDetails.getGender() == null || !kotlin.text.m.equals(userDetails.getGender(), "MALE", true)) ? SupportedDeviceFeatureUtilsNew.Gender.FEMALE : gender : gender;
    }

    public final void setCountDownTimer(@Nullable CountDownTimer countDownTimer) {
        this.o = countDownTimer;
    }

    public final void t(SupportedDeviceFeatureUtilsNew.Feature feature) {
        BleApi bleApi;
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                ConnectionStatus connectionStatus = BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus();
                ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
                if (connectionStatus != connectionStatus2) {
                    Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
                    return;
                }
                String name = feature.getName();
                if (Intrinsics.areEqual(name, getString(R.string.watch_face))) {
                    if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                        if (o()) {
                            return;
                        }
                        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        companion.getInstance(requireActivity).saveIsUserCheckedWatchface(true);
                        Intent intent = new Intent(requireContext(), ActivityWatchFace.class);
                        intent.putExtra("fromSettings", true);
                        startActivity(intent);
                        return;
                    }
                    Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
                    return;
                } else if (Intrinsics.areEqual(name, getString(R.string.sedentary_alarm))) {
                    if (o()) {
                        return;
                    }
                    startActivity(new Intent(requireContext(), ActivitySedentaryReminder.class));
                    return;
                } else if (Intrinsics.areEqual(name, getString(R.string.drink_reminder))) {
                    if (o()) {
                        return;
                    }
                    startActivity(new Intent(requireContext(), ActivityDrinkWaterReminder.class));
                    return;
                } else if (Intrinsics.areEqual(name, getString(R.string.female_wellness_tracker))) {
                    if (o()) {
                        return;
                    }
                    if (UserDataManager.getInstance(requireActivity()).getWomenWellnessData() != null) {
                        if (UserDataManager.getInstance(requireActivity()).getWomenWellnessData().getLastPeriodDay() == 0) {
                            AppNavigator.Companion companion2 = AppNavigator.Companion;
                            FragmentActivity requireActivity2 = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                            companion2.navigateToWomenWellnessFTUActivity(requireActivity2);
                            return;
                        }
                        AppNavigator.Companion companion3 = AppNavigator.Companion;
                        FragmentActivity requireActivity3 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                        companion3.navigateToFemaleWellnessCalendarViewOrSettings(requireActivity3);
                        return;
                    }
                    AppNavigator.Companion companion4 = AppNavigator.Companion;
                    FragmentActivity requireActivity4 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
                    companion4.navigateToWomenWellnessFTUActivity(requireActivity4);
                    return;
                } else if (Intrinsics.areEqual(name, getString(R.string.schedule_reminder))) {
                    if (o()) {
                        return;
                    }
                    startActivity(new Intent(requireContext(), ActivityScheduleReminder.class));
                    return;
                } else if (Intrinsics.areEqual(name, getString(R.string.custome_reminder))) {
                    if (o()) {
                        return;
                    }
                    startActivity(new Intent(requireContext(), ActivityCustomReminders.class));
                    return;
                } else if (Intrinsics.areEqual(name, getString(R.string.manual_sync))) {
                    if (o()) {
                        return;
                    }
                    if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                        startActivity(new Intent(requireContext(), ActivityContactTracingSync.class));
                        return;
                    } else {
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
                        return;
                    }
                } else {
                    AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = null;
                    r5 = null;
                    ConnectionStatus connectionStatus3 = null;
                    if (Intrinsics.areEqual(name, getString(R.string.vibration_alarm))) {
                        if (o()) {
                            return;
                        }
                        BleApiManager bleApiManager = BleApiManager.getInstance(requireContext());
                        if (bleApiManager != null && (bleApi = bleApiManager.getBleApi()) != null) {
                            connectionStatus3 = bleApi.getConnectionStatus();
                        }
                        if (connectionStatus3 == connectionStatus2) {
                            startActivity(new Intent(requireContext(), ActivityAlarmNew.class));
                            return;
                        } else {
                            Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
                            return;
                        }
                    } else if (Intrinsics.areEqual(name, getString(R.string.nudges))) {
                        if (o()) {
                            return;
                        }
                        startActivity(new Intent(requireContext(), ActivityNudgesSettings.class));
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.tutorials))) {
                        Toast.makeText(requireContext(), getResources().getString(R.string.coming_soon), 0).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.google_fit))) {
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_MORE_SCREEN.getValue());
                        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_GOOGLE_FIT_SCREEN.getValue());
                        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.GOOGLE_FIT_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        startActivity(new Intent(requireContext(), GoogleFitActivity.class));
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.bluetooth_calling))) {
                        if (o()) {
                            return;
                        }
                        startActivity(new Intent(requireContext(), ActivityBTCalling.class));
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.notifications))) {
                        if (o()) {
                            return;
                        }
                        startActivity(new Intent(requireContext(), ActivityNotifications.class));
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.auto_hr_tracker))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion5 = AppNavigator.Companion;
                        Context requireContext3 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        companion5.navigateToAutoHr(requireContext3);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.auto_temperature_tracker))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion6 = AppNavigator.Companion;
                        Context requireContext4 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        companion6.navigateToAutoTemperature(requireContext4);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.find_my_band))) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (o()) {
                                return;
                            }
                            startActivity(new Intent(requireContext(), ActivityFindMyWatch.class));
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.camera_control))) {
                        if (o() || BleApiManager.getInstance(requireContext()) == null) {
                            return;
                        }
                        BleApiManager.getInstance(requireContext()).getBleApi().setUserSettings(new EnterRemoteCameraRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment$handleItemClick$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                Intent intent2 = new Intent(WatchFeaturesFragment.this.requireContext(), CameraActivity.class);
                                intent2.addFlags(268435456);
                                WatchFeaturesFragment.this.startActivity(intent2);
                            }
                        });
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.weather_setting))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion7 = AppNavigator.Companion;
                        Context requireContext5 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                        companion7.navigateToWeatherActivity(requireContext5, true);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.auto_stress_measurements))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion8 = AppNavigator.Companion;
                        Context requireContext6 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                        companion8.navigateToAutoStressSettings(requireContext6);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.auto_spo2_measurements))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion9 = AppNavigator.Companion;
                        Context requireContext7 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
                        companion9.navigateToAutoSPO2Settings(requireContext7);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.auto_stress_hrv))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion10 = AppNavigator.Companion;
                        Context requireContext8 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext8, "requireContext()");
                        companion10.navigateToAutoStressHRVSettings(requireContext8);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.shortcuts))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion11 = AppNavigator.Companion;
                        Context requireContext9 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext9, "requireContext()");
                        companion11.navigateToShortcuts(requireContext9);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.sports_type))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion12 = AppNavigator.Companion;
                        Context requireContext10 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext10, "requireContext()");
                        companion12.navigateToSportsType(requireContext10);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.world_clock))) {
                        AppNavigator.Companion companion13 = AppNavigator.Companion;
                        Context requireContext11 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext11, "requireContext()");
                        companion13.navigateToWorldClock(requireContext11);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.intelligent_recognition_activity))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion14 = AppNavigator.Companion;
                        Context requireContext12 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext12, "requireContext()");
                        companion14.navigateToAutoRecognition(requireContext12);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.auto_activity_detection))) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (o()) {
                                return;
                            }
                            Boolean isAutoActivityDetectionDisclaimerSeen = UserDataManager.getInstance(requireContext()).isAutoActivityDetectionDisclaimerSeen();
                            Intrinsics.checkNotNullExpressionValue(isAutoActivityDetectionDisclaimerSeen, "getInstance(requireConte…tyDetectionDisclaimerSeen");
                            if (isAutoActivityDetectionDisclaimerSeen.booleanValue()) {
                                AppNavigator.Companion companion15 = AppNavigator.Companion;
                                Context requireContext13 = requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext13, "requireContext()");
                                companion15.navigateToAutoRecognitionActivityWithOneK(requireContext13);
                                return;
                            }
                            AppNavigator.Companion companion16 = AppNavigator.Companion;
                            Context requireContext14 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext14, "requireContext()");
                            companion16.navigateToAutoActivityDetectionWithOneKDisclaimer(requireContext14);
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.ambient_sound_level_txt))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion17 = AppNavigator.Companion;
                        Context requireContext15 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext15, "requireContext()");
                        companion17.navigateToAmbientSoundLevelSettings(requireContext15);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.nightly_breathing_rate))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion18 = AppNavigator.Companion;
                        Context requireContext16 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext16, "requireContext()");
                        companion18.navigateToRespiratoryRateSettings(requireContext16);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.event_reminder))) {
                        if (o()) {
                            return;
                        }
                        startActivity(new Intent(requireContext(), ActivityEventReminder.class));
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.remote_camera_control))) {
                        if (o()) {
                            return;
                        }
                        AppNavigator.Companion companion19 = AppNavigator.Companion;
                        Context requireContext17 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext17, "requireContext()");
                        companion19.navigateToCameraSettings(requireContext17);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.sens_ai))) {
                        if (PreferenceManager.isSensAIFTUShown(getContext())) {
                            AppNavigator.Companion companion20 = AppNavigator.Companion;
                            Context requireContext18 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext18, "requireContext()");
                            companion20.navigateToSensAIHomeScreen(requireContext18);
                            return;
                        }
                        AppNavigator.Companion companion21 = AppNavigator.Companion;
                        Context requireContext19 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext19, "requireContext()");
                        companion21.navigateToSensAIFTUScreen(requireContext19);
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.tap_pay))) {
                        if (AppUtils.isNetConnected(requireActivity())) {
                            startActivity(new Intent(getActivity(), ActivityRegisteredProductSummary.class));
                            return;
                        } else {
                            Toast.makeText(getContext(), requireContext().getResources().getString(R.string.please_check_your_internet), 0).show();
                            return;
                        }
                    } else if (Intrinsics.areEqual(name, getString(R.string.additional_activities))) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (o()) {
                                return;
                            }
                            AppNavigator.Companion companion22 = AppNavigator.Companion;
                            Context requireContext20 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext20, "requireContext()");
                            companion22.navigateToAdditionalActivities(requireContext20);
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.alexa_connect))) {
                        if (AppUtils.isNetConnected(requireActivity())) {
                            BaseFragment.showProgress$default(this, false, 1, null);
                            AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel2 = this.p;
                            if (alexaApiCallHandlerViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewmodelAlexaApiCallHandler");
                            } else {
                                alexaApiCallHandlerViewModel = alexaApiCallHandlerViewModel2;
                            }
                            alexaApiCallHandlerViewModel.callGetAlexaAccountLinkingStatusApi();
                            return;
                        }
                        Toast.makeText(getContext(), requireContext().getResources().getString(R.string.please_check_your_internet), 0).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.wallet))) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (o()) {
                                return;
                            }
                            AppNavigator.Companion companion23 = AppNavigator.Companion;
                            Context requireContext21 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext21, "requireContext()");
                            String string = getString(R.string.wallet);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.wallet)");
                            companion23.navigateToAddQRCodeActivity(requireContext21, string);
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.qr_tray))) {
                        if (AppUtils.isNetConnected(requireActivity())) {
                            if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                                if (o()) {
                                    return;
                                }
                                AppNavigator.Companion companion24 = AppNavigator.Companion;
                                Context requireContext22 = requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext22, "requireContext()");
                                companion24.navigateToAddQRCodeTrayActivity(requireContext22);
                                return;
                            }
                            Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                            return;
                        }
                        Toast.makeText(getContext(), requireContext().getResources().getString(R.string.please_check_your_internet), 0).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.emergency_sos_settings))) {
                        if (AppUtils.isNetConnected(requireActivity())) {
                            if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                                if (o()) {
                                    return;
                                }
                                v(SOSCleverTapConstants.SOS_WATCH_FEATURES.getValue());
                                if (SessionManager.getInstance(requireContext()).getSOSConfig() != null) {
                                    AnalyticsLog analyticsLog2 = new AnalyticsLog();
                                    analyticsLog2.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_CARD_CLICK.getValue());
                                    analyticsLog2.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
                                    analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue());
                                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                                    startActivity(new Intent(requireContext(), ActivitySOS.class));
                                    return;
                                }
                                AnalyticsLog analyticsLog3 = new AnalyticsLog();
                                analyticsLog3.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_CARD_CLICK.getValue());
                                analyticsLog3.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
                                analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.SOS_FTU.getValue());
                                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
                                startActivity(new Intent(requireContext(), ActivitySOSSettings.class));
                                return;
                            }
                            Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                            return;
                        }
                        Toast.makeText(getContext(), requireContext().getResources().getString(R.string.please_check_your_internet), 0).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.business_card))) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (o()) {
                                return;
                            }
                            AppNavigator.Companion companion25 = AppNavigator.Companion;
                            Context requireContext23 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext23, "requireContext()");
                            String string2 = getString(R.string.business_card);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.business_card)");
                            companion25.navigateToAddQRCodeActivity(requireContext23, string2);
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.turn_by_turn))) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (o()) {
                                return;
                            }
                            p();
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    } else if (Intrinsics.areEqual(name, getString(R.string.smart_alert_testing_automation))) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (o()) {
                                return;
                            }
                            startActivity(new Intent(requireContext(), ActivitySmartAlertTesting.class));
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
        x();
    }

    public final void v(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), str);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext2));
        companion.logAnalyticsEvent(SOSCleverTapConstants.BC_SOS_LANDINGPAGE_VIEWED.getValue(), hashMap);
    }

    public final void x() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string = getString(themesUtils.isGuestUser(requireContext2) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if(requireContext().isGu…    R.string.pair_device)");
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String string2 = getString(themesUtils.isGuestUser(requireContext3) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if(requireContext().isGu…vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.f3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchFeaturesFragment.y(BottomSheetDialogTwoButtons.this, view);
            }
        });
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String string4 = getString(themesUtils.isGuestUser(requireContext4) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(requireContext().isGu…       R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.e3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchFeaturesFragment.z(WatchFeaturesFragment.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }
}
