package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.EnterRemoteCameraRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.customreminders.activities.ActivityCustomReminders;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.SyncState;
import com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard;
import com.coveiot.android.leonardo.googlefit.GoogleFitActivity;
import com.coveiot.android.leonardo.more.activities.ActivityAboutUs;
import com.coveiot.android.leonardo.more.activities.ActivityAdditionalActivities;
import com.coveiot.android.leonardo.more.activities.ActivityAlarmNew;
import com.coveiot.android.leonardo.more.activities.ActivityBTCalling;
import com.coveiot.android.leonardo.more.activities.ActivityBandSettings;
import com.coveiot.android.leonardo.more.activities.ActivityBatterySaverMode;
import com.coveiot.android.leonardo.more.activities.ActivityContactTracingSync;
import com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb;
import com.coveiot.android.leonardo.more.activities.ActivityDrinkWaterReminder;
import com.coveiot.android.leonardo.more.activities.ActivityEventReminder;
import com.coveiot.android.leonardo.more.activities.ActivityHelpFeedback;
import com.coveiot.android.leonardo.more.activities.ActivityLiftWristToView;
import com.coveiot.android.leonardo.more.activities.ActivityLinkedApplications;
import com.coveiot.android.leonardo.more.activities.ActivityMoreDeviceFeaturesNew;
import com.coveiot.android.leonardo.more.activities.ActivityNotifications;
import com.coveiot.android.leonardo.more.activities.ActivityNudgesSettings;
import com.coveiot.android.leonardo.more.activities.ActivityScheduleReminder;
import com.coveiot.android.leonardo.more.activities.ActivitySedentaryReminder;
import com.coveiot.android.leonardo.more.adapters.SettingsSearchAdapterWithListner;
import com.coveiot.android.leonardo.more.adapters.SettingsTabAdapter;
import com.coveiot.android.leonardo.quickreply.activity.ActivityQuickReplySettings;
import com.coveiot.android.leonardo.remotecamera.CameraActivity;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.SupportedDeviceFeatureUtils;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.permissionsandsettings.AppConfigurationsActivity;
import com.coveiot.android.sos.ActivitySOS;
import com.coveiot.android.sos.ActivitySOSSettings;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.ItemClickListenerNew;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.watchfaceui.activities.ActivityWatchFace;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SettingsFragmentNew extends BaseFragment implements ItemClickListenerNew {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ViewModelActivityDashboard m;
    public SettingsTabAdapter settingsTabAdapter;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SettingsFragmentNew newInstance() {
            return new SettingsFragmentNew();
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
                SettingsFragmentNew.this.dismissProgress();
                if (z) {
                    AppNavigator.Companion companion = AppNavigator.Companion;
                    Context requireContext = SettingsFragmentNew.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    companion.navigateToNavigationScreen(requireContext, HexStringBuilder.DEFAULT_SEPARATOR, false);
                    return;
                }
                AppNavigator.Companion companion2 = AppNavigator.Companion;
                Context requireContext2 = SettingsFragmentNew.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                companion2.navigateToNavigationScreen(requireContext2, HexStringBuilder.DEFAULT_SEPARATOR, true);
                return;
            }
            SettingsFragmentNew.this.dismissProgress();
            SettingsFragmentNew settingsFragmentNew = SettingsFragmentNew.this;
            Context requireContext3 = settingsFragmentNew.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            settingsFragmentNew.toast(requireContext3, String.valueOf(str));
        }
    }

    @JvmStatic
    @NotNull
    public static final SettingsFragmentNew newInstance() {
        return Companion.newInstance();
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

    @NotNull
    public final SettingsTabAdapter getSettingsTabAdapter() {
        SettingsTabAdapter settingsTabAdapter = this.settingsTabAdapter;
        if (settingsTabAdapter != null) {
            return settingsTabAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settingsTabAdapter");
        return null;
    }

    public final boolean k() {
        ViewModelActivityDashboard viewModelActivityDashboard = this.m;
        if (viewModelActivityDashboard == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodelDashboard");
            viewModelActivityDashboard = null;
        }
        if (viewModelActivityDashboard.getSyncStateLiveData().getValue() == SyncState.SYNINCING) {
            Toast.makeText(requireContext(), getString(R.string.syncing_please_wait), 1).show();
            return true;
        }
        return false;
    }

    public final ArrayList<SupportedDeviceFeatureUtils.Feature> l(List<SupportedDeviceFeatureUtils.Feature> list, CharSequence charSequence) {
        ArrayList<SupportedDeviceFeatureUtils.Feature> arrayList = new ArrayList<>();
        for (SupportedDeviceFeatureUtils.Feature feature : list) {
            if (StringsKt__StringsKt.contains((CharSequence) feature.getName(), charSequence, true)) {
                arrayList.add(feature);
            }
        }
        return arrayList;
    }

    public final void m(String str) {
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

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_settings_new, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.ItemClickListenerNew
    public void onItemSelected(@NotNull String itemName) {
        BleApi bleApi;
        Intrinsics.checkNotNullParameter(itemName, "itemName");
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        firebaseConstants.setValue(FirebaseEventParams.ScreenName.WATCH_SETTINGS.getValue());
        ((EditText) _$_findCachedViewById(R.id.etSearchSettings)).setText("");
        ConnectionStatus connectionStatus = BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus();
        ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
        if (connectionStatus != connectionStatus2) {
            Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
        } else if (k()) {
            Toast.makeText(requireContext(), getString(R.string.syncing_please_wait), 0).show();
        } else if (Intrinsics.areEqual(itemName, getString(R.string.watch_face))) {
            if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                if (k()) {
                    return;
                }
                Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                companion.getInstance(requireActivity).saveIsUserCheckedWatchface(true);
                startActivity(new Intent(requireContext(), ActivityWatchFace.class));
                return;
            }
            Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
        } else if (Intrinsics.areEqual(itemName, getString(R.string.sedentary_alarm))) {
            if (k()) {
                return;
            }
            startActivity(new Intent(requireContext(), ActivitySedentaryReminder.class));
        } else if (Intrinsics.areEqual(itemName, getString(R.string.drink_reminder))) {
            if (k()) {
                return;
            }
            startActivity(new Intent(requireContext(), ActivityDrinkWaterReminder.class));
        } else if (Intrinsics.areEqual(itemName, getString(R.string.female_wellness_tracker))) {
            if (k()) {
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
        } else if (Intrinsics.areEqual(itemName, getString(R.string.schedule_reminder))) {
            if (k()) {
                return;
            }
            startActivity(new Intent(requireContext(), ActivityScheduleReminder.class));
        } else if (Intrinsics.areEqual(itemName, getString(R.string.reminders))) {
            if (k()) {
                return;
            }
            startActivity(new Intent(requireContext(), ActivityCustomReminders.class));
        } else if (Intrinsics.areEqual(itemName, getString(R.string.manual_sync))) {
            if (k()) {
                return;
            }
            if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                startActivity(new Intent(requireContext(), ActivityContactTracingSync.class));
            } else {
                Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
            }
        } else {
            ViewModelActivityDashboard viewModelActivityDashboard = null;
            r7 = null;
            ConnectionStatus connectionStatus3 = null;
            if (Intrinsics.areEqual(itemName, getString(R.string.vibration_alarm))) {
                if (k()) {
                    return;
                }
                BleApiManager bleApiManager = BleApiManager.getInstance(requireContext());
                if (bleApiManager != null && (bleApi = bleApiManager.getBleApi()) != null) {
                    connectionStatus3 = bleApi.getConnectionStatus();
                }
                if (connectionStatus3 == connectionStatus2) {
                    startActivity(new Intent(requireContext(), ActivityAlarmNew.class));
                } else {
                    Toast.makeText(requireContext(), getString(R.string.band_not_connected), 0).show();
                }
            } else if (Intrinsics.areEqual(itemName, getString(R.string.nudges))) {
                if (k()) {
                    return;
                }
                startActivity(new Intent(requireContext(), ActivityNudgesSettings.class));
            } else if (Intrinsics.areEqual(itemName, getString(R.string.tutorials))) {
                Toast.makeText(requireContext(), getResources().getString(R.string.coming_soon), 0).show();
            } else if (Intrinsics.areEqual(itemName, getString(R.string.google_fit))) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_MORE_SCREEN.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_GOOGLE_FIT_SCREEN.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.GOOGLE_FIT_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                startActivity(new Intent(requireContext(), GoogleFitActivity.class));
            } else if (Intrinsics.areEqual(itemName, getString(R.string.bluetooth_calling))) {
                if (k()) {
                    return;
                }
                startActivity(new Intent(requireContext(), ActivityBTCalling.class));
            } else if (Intrinsics.areEqual(itemName, getString(R.string.notifications))) {
                if (k()) {
                    return;
                }
                startActivity(new Intent(requireContext(), ActivityNotifications.class));
            } else if (Intrinsics.areEqual(itemName, getString(R.string.auto_hr_tracker))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion5 = AppNavigator.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                companion5.navigateToAutoHr(requireContext);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.auto_temperature_tracker))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion6 = AppNavigator.Companion;
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                companion6.navigateToAutoTemperature(requireContext2);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.find_my_band))) {
                startActivity(new Intent(requireContext(), ActivityMoreDeviceFeaturesNew.class));
            } else if (Intrinsics.areEqual(itemName, getString(R.string.camera_control))) {
                if (k() || BleApiManager.getInstance(requireContext()) == null) {
                    return;
                }
                BleApiManager.getInstance(requireContext()).getBleApi().setUserSettings(new EnterRemoteCameraRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.SettingsFragmentNew$onItemSelected$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        Intent intent = new Intent(SettingsFragmentNew.this.requireContext(), CameraActivity.class);
                        intent.addFlags(268435456);
                        SettingsFragmentNew.this.startActivity(intent);
                    }
                });
            } else if (Intrinsics.areEqual(itemName, getString(R.string.weather))) {
                AppNavigator.Companion companion7 = AppNavigator.Companion;
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                companion7.navigateToWeatherActivity(requireContext3, true);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.link_to_alexa))) {
                startActivity(new Intent(requireContext(), ActivityLinkedApplications.class));
            } else if (Intrinsics.areEqual(itemName, getString(R.string.auto_stress_measurements))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion8 = AppNavigator.Companion;
                Context requireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                companion8.navigateToAutoStressSettings(requireContext4);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.auto_spo2_measurements))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion9 = AppNavigator.Companion;
                Context requireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                companion9.navigateToAutoSPO2Settings(requireContext5);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.auto_stress_hrv))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion10 = AppNavigator.Companion;
                Context requireContext6 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                companion10.navigateToAutoStressHRVSettings(requireContext6);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.shortcuts))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion11 = AppNavigator.Companion;
                Context requireContext7 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
                companion11.navigateToShortcuts(requireContext7);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.sports_type))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion12 = AppNavigator.Companion;
                Context requireContext8 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext8, "requireContext()");
                companion12.navigateToSportsType(requireContext8);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.world_clock))) {
                AppNavigator.Companion companion13 = AppNavigator.Companion;
                Context requireContext9 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext9, "requireContext()");
                companion13.navigateToWorldClock(requireContext9);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.intelligent_recognition_activity))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion14 = AppNavigator.Companion;
                Context requireContext10 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext10, "requireContext()");
                companion14.navigateToAutoRecognition(requireContext10);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.auto_activity_detection))) {
                if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                    if (k()) {
                        return;
                    }
                    Boolean isAutoActivityDetectionDisclaimerSeen = UserDataManager.getInstance(requireContext()).isAutoActivityDetectionDisclaimerSeen();
                    Intrinsics.checkNotNullExpressionValue(isAutoActivityDetectionDisclaimerSeen, "getInstance(requireConte…tyDetectionDisclaimerSeen");
                    if (isAutoActivityDetectionDisclaimerSeen.booleanValue()) {
                        AppNavigator.Companion companion15 = AppNavigator.Companion;
                        Context requireContext11 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext11, "requireContext()");
                        companion15.navigateToAutoRecognitionActivityWithOneK(requireContext11);
                        return;
                    }
                    AppNavigator.Companion companion16 = AppNavigator.Companion;
                    Context requireContext12 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext12, "requireContext()");
                    companion16.navigateToAutoActivityDetectionWithOneKDisclaimer(requireContext12);
                    return;
                }
                Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
            } else if (Intrinsics.areEqual(itemName, getString(R.string.ambient_sound_level_txt))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion17 = AppNavigator.Companion;
                Context requireContext13 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext13, "requireContext()");
                companion17.navigateToAmbientSoundLevelSettings(requireContext13);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.nightly_breathing_rate))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion18 = AppNavigator.Companion;
                Context requireContext14 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext14, "requireContext()");
                companion18.navigateToRespiratoryRateSettings(requireContext14);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.event_reminder))) {
                if (k()) {
                    return;
                }
                startActivity(new Intent(requireContext(), ActivityEventReminder.class));
            } else if (Intrinsics.areEqual(itemName, getString(R.string.remote_camera_control))) {
                if (k()) {
                    return;
                }
                AppNavigator.Companion companion19 = AppNavigator.Companion;
                Context requireContext15 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext15, "requireContext()");
                companion19.navigateToCameraSettings(requireContext15);
            } else if (Intrinsics.areEqual(itemName, getString(R.string.dnd_name))) {
                if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                    startActivity(new Intent(requireContext(), ActivityDoNotDisturb.class));
                } else {
                    Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                }
            } else if (Intrinsics.areEqual(itemName, getString(R.string.quick_reply))) {
                if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                    startActivity(new Intent(requireContext(), ActivityQuickReplySettings.class));
                } else {
                    Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                }
            } else if (Intrinsics.areEqual(itemName, getString(R.string.battery_saver_mode))) {
                startActivity(new Intent(requireContext(), ActivityBatterySaverMode.class));
            } else {
                if (Intrinsics.areEqual(itemName, getString(R.string.distance_unit)) ? true : Intrinsics.areEqual(itemName, getString(R.string.wrist_worn)) ? true : Intrinsics.areEqual(itemName, getString(R.string.temperature_unit)) ? true : Intrinsics.areEqual(itemName, getString(R.string.time_format))) {
                    if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                        startActivity(new Intent(requireContext(), ActivityBandSettings.class));
                    } else {
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                    }
                } else if (Intrinsics.areEqual(itemName, getString(R.string.lift_wrist))) {
                    if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isScheduledLiftWristViewSettingsSupported()) {
                            startActivity(new Intent(requireContext(), ActivityLiftWristToView.class));
                            return;
                        } else {
                            startActivity(new Intent(requireContext(), ActivityBandSettings.class));
                            return;
                        }
                    }
                    Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                } else if (Intrinsics.areEqual(itemName, getString(R.string.help_support))) {
                    startActivity(new Intent(requireContext(), ActivityHelpFeedback.class));
                } else if (Intrinsics.areEqual(itemName, getString(R.string.about))) {
                    startActivity(new Intent(requireContext(), ActivityAboutUs.class));
                } else if (Intrinsics.areEqual(itemName, getString(R.string.app_configurations))) {
                    startActivity(new Intent(requireContext(), AppConfigurationsActivity.class));
                } else if (Intrinsics.areEqual(itemName, getString(R.string.linked_applications))) {
                    startActivity(new Intent(requireContext(), ActivityLinkedApplications.class));
                } else if (Intrinsics.areEqual(itemName, getString(R.string.additional_activities))) {
                    startActivity(new Intent(requireContext(), ActivityAdditionalActivities.class));
                } else if (Intrinsics.areEqual(itemName, getString(R.string.emergency_sos_settings))) {
                    if (AppUtils.isNetConnected(requireActivity())) {
                        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            if (k()) {
                                return;
                            }
                            m(SOSCleverTapConstants.SOS_WATCH_SETTINGS.getValue());
                            if (SessionManager.getInstance(requireContext()).getSOSConfig() != null) {
                                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                                analyticsLog2.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_CARD_CLICK.getValue());
                                analyticsLog2.setPreviousScreenName(firebaseConstants.getValue());
                                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue());
                                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                                startActivity(new Intent(requireContext(), ActivitySOS.class));
                                return;
                            }
                            AnalyticsLog analyticsLog3 = new AnalyticsLog();
                            analyticsLog3.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_CARD_CLICK.getValue());
                            analyticsLog3.setPreviousScreenName(firebaseConstants.getValue());
                            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.SOS_FTU.getValue());
                            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
                            startActivity(new Intent(requireContext(), ActivitySOSSettings.class));
                            return;
                        }
                        Toast.makeText(requireContext(), getString(R.string.band_not_connected), 1).show();
                        return;
                    }
                    Toast.makeText(getContext(), requireContext().getResources().getString(R.string.please_check_your_internet), 0).show();
                } else if (Intrinsics.areEqual(itemName, getString(R.string.turn_by_turn))) {
                    Object fromJson = new Gson().fromJson(SessionManager.getInstance(requireContext()).getNavigationDiscliamerData(), new TypeToken<NavigationDisclaimerData>() { // from class: com.coveiot.android.leonardo.more.fragments.SettingsFragmentNew$onItemSelected$navigationDisclaimerDataType$1
                    }.getType());
                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(navigati…gationDisclaimerDataType)");
                    NavigationDisclaimerData navigationDisclaimerData = (NavigationDisclaimerData) fromJson;
                    if (AppUtils.isNetConnected(requireContext())) {
                        BaseFragment.showProgress$default(this, false, 1, null);
                        ViewModelActivityDashboard viewModelActivityDashboard2 = this.m;
                        if (viewModelActivityDashboard2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewmodelDashboard");
                        } else {
                            viewModelActivityDashboard = viewModelActivityDashboard2;
                        }
                        viewModelActivityDashboard.checkNavigationDisclaimerAcceptance(navigationDisclaimerData.getVersion(), new a());
                        return;
                    }
                    Context requireContext16 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext16, "requireContext()");
                    String string = getString(R.string.no_internet);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ion.R.string.no_internet)");
                    toast(requireContext16, string);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.m = (ViewModelActivityDashboard) ViewModelProviders.of(this).get(ViewModelActivityDashboard.class);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        setSettingsTabAdapter(new SettingsTabAdapter(childFragmentManager));
        SettingsTabAdapter settingsTabAdapter = getSettingsTabAdapter();
        WatchSettingsFragmentNew newInstance = WatchSettingsFragmentNew.Companion.newInstance();
        String string = getString(R.string.watch_settings_small);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_settings_small)");
        settingsTabAdapter.addFragment(newInstance, string);
        SettingsTabAdapter settingsTabAdapter2 = getSettingsTabAdapter();
        SystemSettingsFragment newInstance2 = SystemSettingsFragment.Companion.newInstance();
        String string2 = getString(R.string.system_settings);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.system_settings)");
        settingsTabAdapter2.addFragment(newInstance2, string2);
        int i = R.id.viewPager;
        ((ViewPager) _$_findCachedViewById(i)).setAdapter(getSettingsTabAdapter());
        ((TabLayout) _$_findCachedViewById(R.id.tl_settings)).setupWithViewPager((ViewPager) _$_findCachedViewById(i));
        SupportedDeviceFeatureUtils supportedDeviceFeatureUtils = SupportedDeviceFeatureUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final List<SupportedDeviceFeatureUtils.Feature> list = supportedDeviceFeatureUtils.get(requireContext);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext2)) {
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext3)) {
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isSosSupported()) {
                    String string3 = getString(R.string.emergency_sos_settings);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.emergency_sos_settings)");
                    Drawable drawable = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string3, drawable));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isLiftWristToViewSettingsSupported()) {
                    String string4 = getString(R.string.lift_wrist);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.lift_wrist)");
                    Drawable drawable2 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable2);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string4, drawable2));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isHandPreferenceSettingsSupported()) {
                    String string5 = getString(R.string.wrist_worn);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.wrist_worn)");
                    Drawable drawable3 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable3);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string5, drawable3));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isDistanceUnitSettingsSupported()) {
                    String string6 = getString(R.string.distance_unit);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.distance_unit)");
                    Drawable drawable4 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable4);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string6, drawable4));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isTimeFormatSettingsSupported()) {
                    String string7 = getString(R.string.time_format);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.time_format)");
                    Drawable drawable5 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable5);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string7, drawable5));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isTemperatureUnitSettingsSupported()) {
                    String string8 = getString(R.string.temperature_unit);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.temperature_unit)");
                    Drawable drawable6 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable6);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string8, drawable6));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isDndSupported()) {
                    String string9 = getString(R.string.dnd_name);
                    Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.dnd_name)");
                    Drawable drawable7 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable7);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string9, drawable7));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isQuickReplySupported()) {
                    String string10 = getString(R.string.quick_reply);
                    Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.quick_reply)");
                    Drawable drawable8 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable8);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string10, drawable8));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
                    String string11 = getString(R.string.battery_saver_mode);
                    Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.battery_saver_mode)");
                    Drawable drawable9 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable9);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string11, drawable9));
                }
                if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isTurnByTurnNavigationSupported()) {
                    String string12 = getString(R.string.turn_by_turn);
                    Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.turn_by_turn)");
                    Drawable drawable10 = requireContext().getDrawable(R.drawable.ic_watch_more);
                    Intrinsics.checkNotNull(drawable10);
                    list.add(new SupportedDeviceFeatureUtils.Feature(string12, drawable10));
                }
                String string13 = getString(R.string.help_support);
                Intrinsics.checkNotNullExpressionValue(string13, "getString(R.string.help_support)");
                Drawable drawable11 = requireContext().getDrawable(R.drawable.ic_watch_more);
                Intrinsics.checkNotNull(drawable11);
                list.add(new SupportedDeviceFeatureUtils.Feature(string13, drawable11));
                String string14 = getString(R.string.about);
                Intrinsics.checkNotNullExpressionValue(string14, "getString(R.string.about)");
                Drawable drawable12 = requireContext().getDrawable(R.drawable.ic_watch_more);
                Intrinsics.checkNotNull(drawable12);
                list.add(new SupportedDeviceFeatureUtils.Feature(string14, drawable12));
                String string15 = getString(R.string.linked_applications);
                Intrinsics.checkNotNullExpressionValue(string15, "getString(R.string.linked_applications)");
                Drawable drawable13 = requireContext().getDrawable(R.drawable.ic_watch_more);
                Intrinsics.checkNotNull(drawable13);
                list.add(new SupportedDeviceFeatureUtils.Feature(string15, drawable13));
                String string16 = getString(R.string.app_configurations);
                Intrinsics.checkNotNullExpressionValue(string16, "getString(R.string.app_configurations)");
                Drawable drawable14 = requireContext().getDrawable(R.drawable.ic_watch_more);
                Intrinsics.checkNotNull(drawable14);
                list.add(new SupportedDeviceFeatureUtils.Feature(string16, drawable14));
                final SettingsSearchAdapterWithListner settingsSearchAdapterWithListner = new SettingsSearchAdapterWithListner(list);
                int i2 = R.id.rcv_setting_search;
                ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(requireContext()));
                ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(settingsSearchAdapterWithListner);
                settingsSearchAdapterWithListner.setListner(this);
                ((EditText) _$_findCachedViewById(R.id.etSearchSettings)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.fragments.SettingsFragmentNew$onViewCreated$1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(@Nullable Editable editable) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
                        ArrayList l;
                        ArrayList l2;
                        if (charSequence != null && !AppUtils.isEmpty(charSequence.toString())) {
                            ((ConstraintLayout) SettingsFragmentNew.this._$_findCachedViewById(R.id.layout_search_settings)).setVisibility(0);
                            l = SettingsFragmentNew.this.l(list, charSequence);
                            if (l.isEmpty()) {
                                SettingsFragmentNew settingsFragmentNew = SettingsFragmentNew.this;
                                TextView tvNoSearchFound = (TextView) settingsFragmentNew._$_findCachedViewById(R.id.tvNoSearchFound);
                                Intrinsics.checkNotNullExpressionValue(tvNoSearchFound, "tvNoSearchFound");
                                settingsFragmentNew.visible(tvNoSearchFound);
                                SettingsFragmentNew settingsFragmentNew2 = SettingsFragmentNew.this;
                                RecyclerView rcv_setting_search = (RecyclerView) settingsFragmentNew2._$_findCachedViewById(R.id.rcv_setting_search);
                                Intrinsics.checkNotNullExpressionValue(rcv_setting_search, "rcv_setting_search");
                                settingsFragmentNew2.gone(rcv_setting_search);
                                return;
                            }
                            SettingsSearchAdapterWithListner settingsSearchAdapterWithListner2 = settingsSearchAdapterWithListner;
                            l2 = SettingsFragmentNew.this.l(list, charSequence);
                            settingsSearchAdapterWithListner2.setData(l2, charSequence.toString());
                            SettingsFragmentNew settingsFragmentNew3 = SettingsFragmentNew.this;
                            TextView tvNoSearchFound2 = (TextView) settingsFragmentNew3._$_findCachedViewById(R.id.tvNoSearchFound);
                            Intrinsics.checkNotNullExpressionValue(tvNoSearchFound2, "tvNoSearchFound");
                            settingsFragmentNew3.gone(tvNoSearchFound2);
                            SettingsFragmentNew settingsFragmentNew4 = SettingsFragmentNew.this;
                            RecyclerView rcv_setting_search2 = (RecyclerView) settingsFragmentNew4._$_findCachedViewById(R.id.rcv_setting_search);
                            Intrinsics.checkNotNullExpressionValue(rcv_setting_search2, "rcv_setting_search");
                            settingsFragmentNew4.visible(rcv_setting_search2);
                            return;
                        }
                        ((ConstraintLayout) SettingsFragmentNew.this._$_findCachedViewById(R.id.layout_search_settings)).setVisibility(8);
                        settingsSearchAdapterWithListner.setData(null, "");
                    }
                });
                Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
                Context requireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                companion.getInstance(requireContext4).saveIsUserCheckedWatchSettings(true);
            }
        }
        EditText etSearchSettings = (EditText) _$_findCachedViewById(R.id.etSearchSettings);
        Intrinsics.checkNotNullExpressionValue(etSearchSettings, "etSearchSettings");
        gone(etSearchSettings);
        String string132 = getString(R.string.help_support);
        Intrinsics.checkNotNullExpressionValue(string132, "getString(R.string.help_support)");
        Drawable drawable112 = requireContext().getDrawable(R.drawable.ic_watch_more);
        Intrinsics.checkNotNull(drawable112);
        list.add(new SupportedDeviceFeatureUtils.Feature(string132, drawable112));
        String string142 = getString(R.string.about);
        Intrinsics.checkNotNullExpressionValue(string142, "getString(R.string.about)");
        Drawable drawable122 = requireContext().getDrawable(R.drawable.ic_watch_more);
        Intrinsics.checkNotNull(drawable122);
        list.add(new SupportedDeviceFeatureUtils.Feature(string142, drawable122));
        String string152 = getString(R.string.linked_applications);
        Intrinsics.checkNotNullExpressionValue(string152, "getString(R.string.linked_applications)");
        Drawable drawable132 = requireContext().getDrawable(R.drawable.ic_watch_more);
        Intrinsics.checkNotNull(drawable132);
        list.add(new SupportedDeviceFeatureUtils.Feature(string152, drawable132));
        String string162 = getString(R.string.app_configurations);
        Intrinsics.checkNotNullExpressionValue(string162, "getString(R.string.app_configurations)");
        Drawable drawable142 = requireContext().getDrawable(R.drawable.ic_watch_more);
        Intrinsics.checkNotNull(drawable142);
        list.add(new SupportedDeviceFeatureUtils.Feature(string162, drawable142));
        final SettingsSearchAdapterWithListner settingsSearchAdapterWithListner2 = new SettingsSearchAdapterWithListner(list);
        int i22 = R.id.rcv_setting_search;
        ((RecyclerView) _$_findCachedViewById(i22)).setLayoutManager(new LinearLayoutManager(requireContext()));
        ((RecyclerView) _$_findCachedViewById(i22)).setAdapter(settingsSearchAdapterWithListner2);
        settingsSearchAdapterWithListner2.setListner(this);
        ((EditText) _$_findCachedViewById(R.id.etSearchSettings)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.fragments.SettingsFragmentNew$onViewCreated$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
                ArrayList l;
                ArrayList l2;
                if (charSequence != null && !AppUtils.isEmpty(charSequence.toString())) {
                    ((ConstraintLayout) SettingsFragmentNew.this._$_findCachedViewById(R.id.layout_search_settings)).setVisibility(0);
                    l = SettingsFragmentNew.this.l(list, charSequence);
                    if (l.isEmpty()) {
                        SettingsFragmentNew settingsFragmentNew = SettingsFragmentNew.this;
                        TextView tvNoSearchFound = (TextView) settingsFragmentNew._$_findCachedViewById(R.id.tvNoSearchFound);
                        Intrinsics.checkNotNullExpressionValue(tvNoSearchFound, "tvNoSearchFound");
                        settingsFragmentNew.visible(tvNoSearchFound);
                        SettingsFragmentNew settingsFragmentNew2 = SettingsFragmentNew.this;
                        RecyclerView rcv_setting_search = (RecyclerView) settingsFragmentNew2._$_findCachedViewById(R.id.rcv_setting_search);
                        Intrinsics.checkNotNullExpressionValue(rcv_setting_search, "rcv_setting_search");
                        settingsFragmentNew2.gone(rcv_setting_search);
                        return;
                    }
                    SettingsSearchAdapterWithListner settingsSearchAdapterWithListner22 = settingsSearchAdapterWithListner2;
                    l2 = SettingsFragmentNew.this.l(list, charSequence);
                    settingsSearchAdapterWithListner22.setData(l2, charSequence.toString());
                    SettingsFragmentNew settingsFragmentNew3 = SettingsFragmentNew.this;
                    TextView tvNoSearchFound2 = (TextView) settingsFragmentNew3._$_findCachedViewById(R.id.tvNoSearchFound);
                    Intrinsics.checkNotNullExpressionValue(tvNoSearchFound2, "tvNoSearchFound");
                    settingsFragmentNew3.gone(tvNoSearchFound2);
                    SettingsFragmentNew settingsFragmentNew4 = SettingsFragmentNew.this;
                    RecyclerView rcv_setting_search2 = (RecyclerView) settingsFragmentNew4._$_findCachedViewById(R.id.rcv_setting_search);
                    Intrinsics.checkNotNullExpressionValue(rcv_setting_search2, "rcv_setting_search");
                    settingsFragmentNew4.visible(rcv_setting_search2);
                    return;
                }
                ((ConstraintLayout) SettingsFragmentNew.this._$_findCachedViewById(R.id.layout_search_settings)).setVisibility(8);
                settingsSearchAdapterWithListner2.setData(null, "");
            }
        });
        Dashboard2PreferenceManager.Companion companion2 = Dashboard2PreferenceManager.Companion;
        Context requireContext42 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext42, "requireContext()");
        companion2.getInstance(requireContext42).saveIsUserCheckedWatchSettings(true);
    }

    public final void setSettingsTabAdapter(@NotNull SettingsTabAdapter settingsTabAdapter) {
        Intrinsics.checkNotNullParameter(settingsTabAdapter, "<set-?>");
        this.settingsTabAdapter = settingsTabAdapter;
    }
}
