package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.googlefit.GoogleFitActivity;
import com.coveiot.android.leonardo.more.adapters.SettingsTitleSubTitleAdapterAdapter;
import com.coveiot.android.leonardo.utils.SupportedDeviceFeatureUtils;
import com.coveiot.android.remotecommandframework.ViewModelFactory;
import com.coveiot.android.remotecommandframework.alexa.activity.LinkToAlexaResultActivity;
import com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse;
import com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.model.SettingsListItemModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.covepreferences.data.FirmwareCapabilityData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityLinkedApplications extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AlexaApiCallHandlerViewModel p;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<AppToAppLinkingResponse, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(AppToAppLinkingResponse appToAppLinkingResponse) {
            invoke2(appToAppLinkingResponse);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0067, code lost:
            if (kotlin.text.m.equals(r4.getStatus(), "ENABLED", false) != false) goto L13;
         */
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke2(com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse r36) {
            /*
                Method dump skipped, instructions count: 556
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityLinkedApplications.a.invoke2(com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse):void");
        }
    }

    public static final void u(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void w(ActivityLinkedApplications this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.p = (AlexaApiCallHandlerViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AlexaApiCallHandlerViewModel.class);
        setContentView(R.layout.activity_linked_applications);
        ((RecyclerView) _$_findCachedViewById(R.id.rcv_linked_apps)).setLayoutManager(new LinearLayoutManager(this));
        v();
        if (s().isAlexaFeatureSupported()) {
            showProgress();
            t();
            AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = this.p;
            if (alexaApiCallHandlerViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodelAlexaApiCallHandler");
                alexaApiCallHandlerViewModel = null;
            }
            alexaApiCallHandlerViewModel.callGetAlexaAccountLinkingStatusApi();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ArrayList arrayList = new ArrayList();
        if (s().isAlexaFeatureSupported()) {
            String string = getString(R.string.alexa_connect);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.alexa_connect)");
            arrayList.add(new SettingsListItemModel(R.drawable.watch_features_icon, string, LinkToAlexaResultActivity.class, SessionManager.getInstance(this).getAlexaAccountLinkingStatus() ? getString(R.string.linked) : getString(R.string.link_now), Integer.valueOf(SessionManager.getInstance(this).getAlexaAccountLinkingStatus() ? getColor(R.color.color_state_connected) : getColor(R.color.color_state_dicconnected)), false, 32, null));
        }
        String string2 = getString(R.string.google_fit);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.google_fit)");
        arrayList.add(new SettingsListItemModel(R.drawable.watch_control_cion, string2, GoogleFitActivity.class, !UserDataManager.getInstance(this).isGoogleFitConnected(this) ? getString(R.string.link_now) : getString(R.string.linked), Integer.valueOf(!UserDataManager.getInstance(this).isGoogleFitConnected(this) ? getColor(R.color.color_state_dicconnected) : getColor(R.color.color_state_connected)), false, 32, null));
        ((RecyclerView) _$_findCachedViewById(R.id.rcv_linked_apps)).setAdapter(new SettingsTitleSubTitleAdapterAdapter(arrayList));
    }

    public final SupportedDeviceFeatureUtils.Control s() {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        SupportedDeviceFeatureUtils.Control control;
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this).getDeviceModelBean();
        SupportedDeviceFeatureUtils.Control control2 = new SupportedDeviceFeatureUtils.Control(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 536870911, null);
        BleApiManager bleApiManager = BleApiManager.getInstance(this);
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
        if (deviceSupportedFeatures.getAutoTemperatureSettingsSupported() && !DeviceUtils.Companion.isCZDevice(this)) {
            control.setAutoTemperatureSupported(true);
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
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                control.setActivityAutoRecognitionSupported(true);
            } else {
                FirmwareCapabilityData firmwareCapability = SessionManager.getInstance(this).getFirmwareCapability(BleApiManager.getInstance(this).getBleApi().getMacAddress());
                if (firmwareCapability != null && firmwareCapability.getCapabilities()[0] == 1) {
                    control.setActivityAutoRecognitionSupported(true);
                }
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
            return control;
        }
        return control;
    }

    public final void t() {
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = this.p;
        if (alexaApiCallHandlerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodelAlexaApiCallHandler");
            alexaApiCallHandlerViewModel = null;
        }
        MutableLiveData<AppToAppLinkingResponse> alexaAccountLinkingStatusLiveData = alexaApiCallHandlerViewModel.getAlexaAccountLinkingStatusLiveData();
        final a aVar = new a();
        alexaAccountLinkingStatusLiveData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.af
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityLinkedApplications.u(Function1.this, obj);
            }
        });
    }

    public final void v() {
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(R.string.linked_applications);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ze
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLinkedApplications.w(ActivityLinkedApplications.this, view);
            }
        });
    }
}
