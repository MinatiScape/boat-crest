package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.covepreferences.data.FirmwareCapabilityData;
import com.coveiot.covepreferences.data.ProfileData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SupportedDeviceFeatureUtils {
    @NotNull
    public static final SupportedDeviceFeatureUtils INSTANCE = new SupportedDeviceFeatureUtils();

    /* loaded from: classes5.dex */
    public static final class Feature {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f5435a;
        @NotNull
        public final Drawable b;

        public Feature(@NotNull String name, @NotNull Drawable drawable) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(drawable, "drawable");
            this.f5435a = name;
            this.b = drawable;
        }

        public static /* synthetic */ Feature copy$default(Feature feature, String str, Drawable drawable, int i, Object obj) {
            if ((i & 1) != 0) {
                str = feature.f5435a;
            }
            if ((i & 2) != 0) {
                drawable = feature.b;
            }
            return feature.copy(str, drawable);
        }

        @NotNull
        public final String component1() {
            return this.f5435a;
        }

        @NotNull
        public final Drawable component2() {
            return this.b;
        }

        @NotNull
        public final Feature copy(@NotNull String name, @NotNull Drawable drawable) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(drawable, "drawable");
            return new Feature(name, drawable);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Feature) {
                Feature feature = (Feature) obj;
                return Intrinsics.areEqual(this.f5435a, feature.f5435a) && Intrinsics.areEqual(this.b, feature.b);
            }
            return false;
        }

        @NotNull
        public final Drawable getDrawable() {
            return this.b;
        }

        @NotNull
        public final String getName() {
            return this.f5435a;
        }

        public int hashCode() {
            return (this.f5435a.hashCode() * 31) + this.b.hashCode();
        }

        @NotNull
        public String toString() {
            return "Feature(name=" + this.f5435a + ", drawable=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    /* loaded from: classes5.dex */
    public enum Gender {
        FEMALE(1),
        MALE(0);

        Gender(int i) {
        }
    }

    public final Gender a(Context context) {
        Gender gender = Gender.MALE;
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        return userDetails != null ? (userDetails.getGender() == null || !kotlin.text.m.equals(userDetails.getGender(), "MALE", true)) ? Gender.FEMALE : gender : gender;
    }

    @NotNull
    public final List<Feature> get(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Control featureControl = setFeatureControl(context);
        Gender a2 = a(context);
        ArrayList arrayList = new ArrayList();
        if (featureControl.isWatchFaceSupported()) {
            String string = context.getString(R.string.watch_face);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.watch_face)");
            Drawable drawable = context.getDrawable(R.drawable.ic_watch_more);
            Intrinsics.checkNotNull(drawable);
            arrayList.add(new Feature(string, drawable));
        }
        if (featureControl.isCameraSupported()) {
            String string2 = context.getString(R.string.camera_control);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.camera_control)");
            Drawable drawable2 = context.getDrawable(R.drawable.ic_more_camera);
            Intrinsics.checkNotNull(drawable2);
            arrayList.add(new Feature(string2, drawable2));
        }
        if (featureControl.isCameraControlSettingsSupported()) {
            String string3 = context.getString(R.string.remote_camera_control);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.remote_camera_control)");
            Drawable drawable3 = context.getDrawable(R.drawable.ic_more_camera);
            Intrinsics.checkNotNull(drawable3);
            arrayList.add(new Feature(string3, drawable3));
        }
        if (featureControl.isSedentaryReminderSupported()) {
            String string4 = context.getString(R.string.sedentary_alarm);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.sedentary_alarm)");
            Drawable drawable4 = context.getDrawable(2131232410);
            Intrinsics.checkNotNull(drawable4);
            arrayList.add(new Feature(string4, drawable4));
        }
        if (featureControl.isVibrationAlarmSupported()) {
            String string5 = context.getString(R.string.vibration_alarm);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.vibration_alarm)");
            Drawable drawable5 = context.getDrawable(2131231874);
            Intrinsics.checkNotNull(drawable5);
            arrayList.add(new Feature(string5, drawable5));
        }
        if (featureControl.isAlexaFeatureSupported()) {
            String string6 = context.getString(R.string.link_to_alexa);
            Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.link_to_alexa)");
            Drawable drawable6 = context.getDrawable(R.drawable.ic_alexa_connect);
            Intrinsics.checkNotNull(drawable6);
            arrayList.add(new Feature(string6, drawable6));
        }
        if (featureControl.isRespiratoryRateByPPGSupported()) {
            String string7 = context.getString(R.string.nightly_breathing_rate);
            Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.nightly_breathing_rate)");
            Drawable drawable7 = context.getDrawable(2131232421);
            Intrinsics.checkNotNull(drawable7);
            arrayList.add(new Feature(string7, drawable7));
        }
        if (featureControl.isDrinkReminderSupported()) {
            String string8 = context.getString(R.string.drink_reminder);
            Intrinsics.checkNotNullExpressionValue(string8, "context.getString(R.string.drink_reminder)");
            Drawable drawable8 = context.getDrawable(R.drawable.water_active);
            Intrinsics.checkNotNull(drawable8);
            arrayList.add(new Feature(string8, drawable8));
        }
        if (featureControl.isNudgeSupported()) {
            String string9 = context.getString(R.string.nudges);
            Intrinsics.checkNotNullExpressionValue(string9, "context.getString(R.string.nudges)");
            Drawable drawable9 = context.getDrawable(R.drawable.ic_nudges);
            Intrinsics.checkNotNull(drawable9);
            arrayList.add(new Feature(string9, drawable9));
        }
        if (featureControl.isWeatherSupported()) {
            String string10 = context.getString(R.string.weather);
            Intrinsics.checkNotNullExpressionValue(string10, "context.getString(R.string.weather)");
            Drawable drawable10 = context.getDrawable(R.drawable.ic_weather_red);
            Intrinsics.checkNotNull(drawable10);
            arrayList.add(new Feature(string10, drawable10));
        }
        if (a2 == Gender.FEMALE && featureControl.isFemaleWellnessTrackerSupported()) {
            String string11 = context.getString(R.string.female_wellness_tracker);
            Intrinsics.checkNotNullExpressionValue(string11, "context.getString(R.stri….female_wellness_tracker)");
            Drawable drawable11 = context.getDrawable(2131231766);
            Intrinsics.checkNotNull(drawable11);
            arrayList.add(new Feature(string11, drawable11));
        }
        if (featureControl.isCustomRemindersSupported()) {
            String string12 = context.getString(R.string.reminders);
            Intrinsics.checkNotNullExpressionValue(string12, "context.getString(R.string.reminders)");
            Drawable drawable12 = context.getDrawable(R.drawable.ic_reminders_icon);
            Intrinsics.checkNotNull(drawable12);
            arrayList.add(new Feature(string12, drawable12));
        }
        if (featureControl.isScheduleReminderSupported()) {
            String string13 = context.getString(R.string.schedule_reminder);
            Intrinsics.checkNotNullExpressionValue(string13, "context.getString(R.string.schedule_reminder)");
            Drawable drawable13 = context.getDrawable(R.drawable.ic_schedule_cal);
            Intrinsics.checkNotNull(drawable13);
            arrayList.add(new Feature(string13, drawable13));
        }
        if (featureControl.isNotificationSupported()) {
            String string14 = context.getString(R.string.notifications);
            Intrinsics.checkNotNullExpressionValue(string14, "context.getString(R.string.notifications)");
            Drawable drawable14 = context.getDrawable(R.drawable.ic_notification_more);
            Intrinsics.checkNotNull(drawable14);
            arrayList.add(new Feature(string14, drawable14));
        }
        if (featureControl.isGenericEventReminderSupported()) {
            String string15 = context.getString(R.string.event_reminder);
            Intrinsics.checkNotNullExpressionValue(string15, "context.getString(R.string.event_reminder)");
            Drawable drawable15 = context.getDrawable(R.drawable.ic_reminders_icon);
            Intrinsics.checkNotNull(drawable15);
            arrayList.add(new Feature(string15, drawable15));
        }
        if (featureControl.isShortcutsSupported()) {
            String string16 = context.getString(R.string.shortcuts);
            Intrinsics.checkNotNullExpressionValue(string16, "context.getString(R.string.shortcuts)");
            Drawable drawable16 = context.getDrawable(R.drawable.ic_shortcut);
            Intrinsics.checkNotNull(drawable16);
            arrayList.add(new Feature(string16, drawable16));
        }
        if (featureControl.isSportsTypeSupported()) {
            String string17 = context.getString(R.string.sports_type);
            Intrinsics.checkNotNullExpressionValue(string17, "context.getString(R.string.sports_type)");
            Drawable drawable17 = context.getDrawable(R.drawable.ic_sports_type);
            Intrinsics.checkNotNull(drawable17);
            arrayList.add(new Feature(string17, drawable17));
        }
        if (featureControl.isAmbientSoundLevelSupported()) {
            String string18 = context.getString(R.string.ambient_sound_level_txt);
            Intrinsics.checkNotNullExpressionValue(string18, "context.getString(R.stri….ambient_sound_level_txt)");
            Drawable drawable18 = context.getDrawable(R.drawable.ic_more_ambient_sound);
            Intrinsics.checkNotNull(drawable18);
            arrayList.add(new Feature(string18, drawable18));
        }
        if (featureControl.isWorldClockSupported()) {
            String string19 = context.getString(R.string.world_clock);
            Intrinsics.checkNotNullExpressionValue(string19, "context.getString(R.string.world_clock)");
            Drawable drawable19 = context.getDrawable(R.drawable.ic_world_clock);
            Intrinsics.checkNotNull(drawable19);
            arrayList.add(new Feature(string19, drawable19));
        }
        if (featureControl.isAutoHrSupported()) {
            String string20 = context.getString(R.string.auto_hr_tracker);
            Intrinsics.checkNotNullExpressionValue(string20, "context.getString(R.string.auto_hr_tracker)");
            Drawable drawable20 = context.getDrawable(R.drawable.ic_auto_hr_monitor);
            Intrinsics.checkNotNull(drawable20);
            arrayList.add(new Feature(string20, drawable20));
        }
        if (featureControl.isAutoTemperatureSupported()) {
            String string21 = context.getString(R.string.auto_temperature_tracker);
            Intrinsics.checkNotNullExpressionValue(string21, "context.getString(R.stri…auto_temperature_tracker)");
            Drawable drawable21 = context.getDrawable(R.drawable.ic_auto_temp_monitor);
            Intrinsics.checkNotNull(drawable21);
            arrayList.add(new Feature(string21, drawable21));
        }
        if (featureControl.isAutoStressHrvSupported()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isIDODevice(context) && !companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context)) {
                String string22 = context.getString(R.string.auto_stress_hrv);
                Intrinsics.checkNotNullExpressionValue(string22, "context.getString(R.string.auto_stress_hrv)");
                Drawable drawable22 = context.getDrawable(R.drawable.ic_stress_device_features);
                Intrinsics.checkNotNull(drawable22);
                arrayList.add(new Feature(string22, drawable22));
            } else {
                String string23 = context.getString(R.string.auto_stress_measurements);
                Intrinsics.checkNotNullExpressionValue(string23, "context.getString(R.stri…auto_stress_measurements)");
                Drawable drawable23 = context.getDrawable(R.drawable.ic_stress_device_features);
                Intrinsics.checkNotNull(drawable23);
                arrayList.add(new Feature(string23, drawable23));
            }
        }
        if (featureControl.isAutoSpo2Supported()) {
            String string24 = context.getString(R.string.auto_spo2_measurements);
            Intrinsics.checkNotNullExpressionValue(string24, "context.getString(R.string.auto_spo2_measurements)");
            Drawable drawable24 = context.getDrawable(R.drawable.ic_auto_spo2_monitor);
            Intrinsics.checkNotNull(drawable24);
            arrayList.add(new Feature(string24, drawable24));
        }
        if (featureControl.isBTCallingSupported()) {
            String string25 = context.getString(R.string.bluetooth_calling);
            Intrinsics.checkNotNullExpressionValue(string25, "context.getString(R.string.bluetooth_calling)");
            Drawable drawable25 = context.getDrawable(R.drawable.ic_bt_calling);
            Intrinsics.checkNotNull(drawable25);
            arrayList.add(new Feature(string25, drawable25));
        }
        if (featureControl.isFindMyBandSupported()) {
            String string26 = context.getString(R.string.find_my_band);
            Intrinsics.checkNotNullExpressionValue(string26, "context.getString(R.string.find_my_band)");
            Drawable drawable26 = context.getDrawable(R.drawable.ic_find_my_device);
            Intrinsics.checkNotNull(drawable26);
            arrayList.add(new Feature(string26, drawable26));
        }
        if (featureControl.isActivityAutoRecognitionSupported()) {
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            if (!companion2.isCADevice(context) && !companion2.isCZDevice(context) && !companion2.isCYDevice(context) && !companion2.isPS1Device(context) && !companion2.isBESDevice(context)) {
                String string27 = context.getString(R.string.intelligent_recognition_activity);
                Intrinsics.checkNotNullExpressionValue(string27, "context.getString(R.stri…ent_recognition_activity)");
                Drawable drawable27 = context.getDrawable(R.drawable.ic_intelligent_activities);
                Intrinsics.checkNotNull(drawable27);
                arrayList.add(new Feature(string27, drawable27));
            } else {
                String string28 = context.getString(R.string.auto_activity_detection);
                Intrinsics.checkNotNullExpressionValue(string28, "context.getString(R.stri….auto_activity_detection)");
                Drawable drawable28 = context.getDrawable(R.drawable.ic_auto_activity_detection);
                Intrinsics.checkNotNull(drawable28);
                arrayList.add(new Feature(string28, drawable28));
            }
        }
        if (featureControl.isGoogleFeatSupported()) {
            String string29 = context.getString(R.string.google_fit);
            Intrinsics.checkNotNullExpressionValue(string29, "context.getString(R.string.google_fit)");
            Drawable drawable29 = context.getDrawable(2131231830);
            Intrinsics.checkNotNull(drawable29);
            arrayList.add(new Feature(string29, drawable29));
        }
        if (SessionManager.getInstance(context).getDeviceModelBean() != null && SessionManager.getInstance(context).getDeviceModelBean().isTapAndPaySupported() != null && Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceModelBean().isTapAndPaySupported(), Boolean.TRUE)) {
            String string30 = context.getString(R.string.tap_pay);
            Intrinsics.checkNotNullExpressionValue(string30, "context.getString(R.string.tap_pay)");
            Drawable drawable30 = context.getDrawable(2131231874);
            Intrinsics.checkNotNull(drawable30);
            arrayList.add(new Feature(string30, drawable30));
        }
        if (featureControl.isSenseAiSupported()) {
            String string31 = context.getString(R.string.sens_ai);
            Intrinsics.checkNotNullExpressionValue(string31, "context.getString(R.string.sens_ai)");
            Drawable drawable31 = context.getDrawable(2131232410);
            Intrinsics.checkNotNull(drawable31);
            arrayList.add(new Feature(string31, drawable31));
        }
        if (featureControl.isSportBinFilePushSupported()) {
            String string32 = context.getString(R.string.additional_activities);
            Intrinsics.checkNotNullExpressionValue(string32, "context.getString(R.string.additional_activities)");
            Drawable drawable32 = context.getDrawable(R.drawable.ic_auto_spo2_monitor);
            Intrinsics.checkNotNull(drawable32);
            arrayList.add(new Feature(string32, drawable32));
        }
        return arrayList;
    }

    @NotNull
    public final Control setFeatureControl(@NotNull Context context) {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        Control control;
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceModelBean deviceModelBean = SessionManager.getInstance(context).getDeviceModelBean();
        Control control2 = new Control(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 536870911, null);
        BleApiManager bleApiManager = BleApiManager.getInstance(context);
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
        if (deviceSupportedFeatures.getAutoTemperatureSettingsSupported() && !DeviceUtils.Companion.isCZDevice(context)) {
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
            if (!companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                control.setActivityAutoRecognitionSupported(true);
            } else {
                FirmwareCapabilityData firmwareCapability = SessionManager.getInstance(context).getFirmwareCapability(BleApiManager.getInstance(context).getBleApi().getMacAddress());
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
        }
        if (deviceSupportedFeatures.isSensAISupported()) {
            control.setSenseAiSupported(true);
        }
        if (deviceSupportedFeatures.isSportBinFilePushFromAppSupported()) {
            control.setSportBinFilePushSupported(true);
            return control;
        }
        return control;
    }

    /* loaded from: classes5.dex */
    public static final class Control {
        public boolean A;
        public boolean B;
        public boolean C;

        /* renamed from: a  reason: collision with root package name */
        public boolean f5434a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public boolean q;
        public boolean r;
        public boolean s;
        public boolean t;
        public boolean u;
        public boolean v;
        public boolean w;
        public boolean x;
        public boolean y;
        public boolean z;

        public Control() {
            this(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 536870911, null);
        }

        public Control(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23, boolean z24, boolean z25, boolean z26, boolean z27, boolean z28, boolean z29) {
            this.f5434a = z;
            this.b = z2;
            this.c = z3;
            this.d = z4;
            this.e = z5;
            this.f = z6;
            this.g = z7;
            this.h = z8;
            this.i = z9;
            this.j = z10;
            this.k = z11;
            this.l = z12;
            this.m = z13;
            this.n = z14;
            this.o = z15;
            this.p = z16;
            this.q = z17;
            this.r = z18;
            this.s = z19;
            this.t = z20;
            this.u = z21;
            this.v = z22;
            this.w = z23;
            this.x = z24;
            this.y = z25;
            this.z = z26;
            this.A = z27;
            this.B = z28;
            this.C = z29;
        }

        public final boolean component1() {
            return this.f5434a;
        }

        public final boolean component10() {
            return this.j;
        }

        public final boolean component11() {
            return this.k;
        }

        public final boolean component12() {
            return this.l;
        }

        public final boolean component13() {
            return this.m;
        }

        public final boolean component14() {
            return this.n;
        }

        public final boolean component15() {
            return this.o;
        }

        public final boolean component16() {
            return this.p;
        }

        public final boolean component17() {
            return this.q;
        }

        public final boolean component18() {
            return this.r;
        }

        public final boolean component19() {
            return this.s;
        }

        public final boolean component2() {
            return this.b;
        }

        public final boolean component20() {
            return this.t;
        }

        public final boolean component21() {
            return this.u;
        }

        public final boolean component22() {
            return this.v;
        }

        public final boolean component23() {
            return this.w;
        }

        public final boolean component24() {
            return this.x;
        }

        public final boolean component25() {
            return this.y;
        }

        public final boolean component26() {
            return this.z;
        }

        public final boolean component27() {
            return this.A;
        }

        public final boolean component28() {
            return this.B;
        }

        public final boolean component29() {
            return this.C;
        }

        public final boolean component3() {
            return this.c;
        }

        public final boolean component4() {
            return this.d;
        }

        public final boolean component5() {
            return this.e;
        }

        public final boolean component6() {
            return this.f;
        }

        public final boolean component7() {
            return this.g;
        }

        public final boolean component8() {
            return this.h;
        }

        public final boolean component9() {
            return this.i;
        }

        @NotNull
        public final Control copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23, boolean z24, boolean z25, boolean z26, boolean z27, boolean z28, boolean z29) {
            return new Control(z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, z13, z14, z15, z16, z17, z18, z19, z20, z21, z22, z23, z24, z25, z26, z27, z28, z29);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Control) {
                Control control = (Control) obj;
                return this.f5434a == control.f5434a && this.b == control.b && this.c == control.c && this.d == control.d && this.e == control.e && this.f == control.f && this.g == control.g && this.h == control.h && this.i == control.i && this.j == control.j && this.k == control.k && this.l == control.l && this.m == control.m && this.n == control.n && this.o == control.o && this.p == control.p && this.q == control.q && this.r == control.r && this.s == control.s && this.t == control.t && this.u == control.u && this.v == control.v && this.w == control.w && this.x == control.x && this.y == control.y && this.z == control.z && this.A == control.A && this.B == control.B && this.C == control.C;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v58 */
        /* JADX WARN: Type inference failed for: r0v59 */
        /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v10, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v12, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v14, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v16, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v18, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v2, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v20, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v22, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v24, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v26, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v28, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v30, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v32, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v34, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v36, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v38, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v40, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v42, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v44, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v46, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v48, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v50, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v52, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v6, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v8, types: [boolean] */
        public int hashCode() {
            boolean z = this.f5434a;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            ?? r2 = this.b;
            int i2 = r2;
            if (r2 != 0) {
                i2 = 1;
            }
            int i3 = (i + i2) * 31;
            ?? r22 = this.c;
            int i4 = r22;
            if (r22 != 0) {
                i4 = 1;
            }
            int i5 = (i3 + i4) * 31;
            ?? r23 = this.d;
            int i6 = r23;
            if (r23 != 0) {
                i6 = 1;
            }
            int i7 = (i5 + i6) * 31;
            ?? r24 = this.e;
            int i8 = r24;
            if (r24 != 0) {
                i8 = 1;
            }
            int i9 = (i7 + i8) * 31;
            ?? r25 = this.f;
            int i10 = r25;
            if (r25 != 0) {
                i10 = 1;
            }
            int i11 = (i9 + i10) * 31;
            ?? r26 = this.g;
            int i12 = r26;
            if (r26 != 0) {
                i12 = 1;
            }
            int i13 = (i11 + i12) * 31;
            ?? r27 = this.h;
            int i14 = r27;
            if (r27 != 0) {
                i14 = 1;
            }
            int i15 = (i13 + i14) * 31;
            ?? r28 = this.i;
            int i16 = r28;
            if (r28 != 0) {
                i16 = 1;
            }
            int i17 = (i15 + i16) * 31;
            ?? r29 = this.j;
            int i18 = r29;
            if (r29 != 0) {
                i18 = 1;
            }
            int i19 = (i17 + i18) * 31;
            ?? r210 = this.k;
            int i20 = r210;
            if (r210 != 0) {
                i20 = 1;
            }
            int i21 = (i19 + i20) * 31;
            ?? r211 = this.l;
            int i22 = r211;
            if (r211 != 0) {
                i22 = 1;
            }
            int i23 = (i21 + i22) * 31;
            ?? r212 = this.m;
            int i24 = r212;
            if (r212 != 0) {
                i24 = 1;
            }
            int i25 = (i23 + i24) * 31;
            ?? r213 = this.n;
            int i26 = r213;
            if (r213 != 0) {
                i26 = 1;
            }
            int i27 = (i25 + i26) * 31;
            ?? r214 = this.o;
            int i28 = r214;
            if (r214 != 0) {
                i28 = 1;
            }
            int i29 = (i27 + i28) * 31;
            ?? r215 = this.p;
            int i30 = r215;
            if (r215 != 0) {
                i30 = 1;
            }
            int i31 = (i29 + i30) * 31;
            ?? r216 = this.q;
            int i32 = r216;
            if (r216 != 0) {
                i32 = 1;
            }
            int i33 = (i31 + i32) * 31;
            ?? r217 = this.r;
            int i34 = r217;
            if (r217 != 0) {
                i34 = 1;
            }
            int i35 = (i33 + i34) * 31;
            ?? r218 = this.s;
            int i36 = r218;
            if (r218 != 0) {
                i36 = 1;
            }
            int i37 = (i35 + i36) * 31;
            ?? r219 = this.t;
            int i38 = r219;
            if (r219 != 0) {
                i38 = 1;
            }
            int i39 = (i37 + i38) * 31;
            ?? r220 = this.u;
            int i40 = r220;
            if (r220 != 0) {
                i40 = 1;
            }
            int i41 = (i39 + i40) * 31;
            ?? r221 = this.v;
            int i42 = r221;
            if (r221 != 0) {
                i42 = 1;
            }
            int i43 = (i41 + i42) * 31;
            ?? r222 = this.w;
            int i44 = r222;
            if (r222 != 0) {
                i44 = 1;
            }
            int i45 = (i43 + i44) * 31;
            ?? r223 = this.x;
            int i46 = r223;
            if (r223 != 0) {
                i46 = 1;
            }
            int i47 = (i45 + i46) * 31;
            ?? r224 = this.y;
            int i48 = r224;
            if (r224 != 0) {
                i48 = 1;
            }
            int i49 = (i47 + i48) * 31;
            ?? r225 = this.z;
            int i50 = r225;
            if (r225 != 0) {
                i50 = 1;
            }
            int i51 = (i49 + i50) * 31;
            ?? r226 = this.A;
            int i52 = r226;
            if (r226 != 0) {
                i52 = 1;
            }
            int i53 = (i51 + i52) * 31;
            ?? r227 = this.B;
            int i54 = r227;
            if (r227 != 0) {
                i54 = 1;
            }
            int i55 = (i53 + i54) * 31;
            boolean z2 = this.C;
            return i55 + (z2 ? 1 : z2 ? 1 : 0);
        }

        public final boolean isActivityAutoRecognitionSupported() {
            return this.o;
        }

        public final boolean isAlexaFeatureSupported() {
            return this.z;
        }

        public final boolean isAmbientSoundLevelSupported() {
            return this.u;
        }

        public final boolean isAutoHrSupported() {
            return this.i;
        }

        public final boolean isAutoSpo2Supported() {
            return this.x;
        }

        public final boolean isAutoStressHrvSupported() {
            return this.k;
        }

        public final boolean isAutoTemperatureSupported() {
            return this.j;
        }

        public final boolean isBTCallingSupported() {
            return this.s;
        }

        public final boolean isCameraControlSettingsSupported() {
            return this.A;
        }

        public final boolean isCameraSupported() {
            return this.t;
        }

        public final boolean isCustomRemindersSupported() {
            return this.v;
        }

        public final boolean isDrinkReminderSupported() {
            return this.n;
        }

        public final boolean isFemaleWellnessTrackerSupported() {
            return this.g;
        }

        public final boolean isFindMyBandSupported() {
            return this.l;
        }

        public final boolean isGenericEventReminderSupported() {
            return this.y;
        }

        public final boolean isGoogleFeatSupported() {
            return this.m;
        }

        public final boolean isNotificationSupported() {
            return this.h;
        }

        public final boolean isNudgeSupported() {
            return this.d;
        }

        public final boolean isRespiratoryRateByPPGSupported() {
            return this.w;
        }

        public final boolean isScheduleReminderSupported() {
            return this.e;
        }

        public final boolean isSedentaryReminderSupported() {
            return this.b;
        }

        public final boolean isSenseAiSupported() {
            return this.B;
        }

        public final boolean isShortcutsSupported() {
            return this.p;
        }

        public final boolean isSportBinFilePushSupported() {
            return this.C;
        }

        public final boolean isSportsTypeSupported() {
            return this.q;
        }

        public final boolean isVibrationAlarmSupported() {
            return this.c;
        }

        public final boolean isWatchFaceSupported() {
            return this.f5434a;
        }

        public final boolean isWeatherSupported() {
            return this.f;
        }

        public final boolean isWorldClockSupported() {
            return this.r;
        }

        public final void setActivityAutoRecognitionSupported(boolean z) {
            this.o = z;
        }

        public final void setAlexaFeatureSupported(boolean z) {
            this.z = z;
        }

        public final void setAmbientSoundLevelSupported(boolean z) {
            this.u = z;
        }

        public final void setAutoHrSupported(boolean z) {
            this.i = z;
        }

        public final void setAutoSpo2Supported(boolean z) {
            this.x = z;
        }

        public final void setAutoStressHrvSupported(boolean z) {
            this.k = z;
        }

        public final void setAutoTemperatureSupported(boolean z) {
            this.j = z;
        }

        public final void setBTCallingSupported(boolean z) {
            this.s = z;
        }

        public final void setCameraControlSettingsSupported(boolean z) {
            this.A = z;
        }

        public final void setCameraSupported(boolean z) {
            this.t = z;
        }

        public final void setCustomRemindersSupported(boolean z) {
            this.v = z;
        }

        public final void setDrinkReminderSupported(boolean z) {
            this.n = z;
        }

        public final void setFemaleWellnessTrackerSupported(boolean z) {
            this.g = z;
        }

        public final void setFindMyBandSupported(boolean z) {
            this.l = z;
        }

        public final void setGenericEventReminderSupported(boolean z) {
            this.y = z;
        }

        public final void setGoogleFeatSupported(boolean z) {
            this.m = z;
        }

        public final void setNotificationSupported(boolean z) {
            this.h = z;
        }

        public final void setNudgeSupported(boolean z) {
            this.d = z;
        }

        public final void setRespiratoryRateByPPGSupported(boolean z) {
            this.w = z;
        }

        public final void setScheduleReminderSupported(boolean z) {
            this.e = z;
        }

        public final void setSedentaryReminderSupported(boolean z) {
            this.b = z;
        }

        public final void setSenseAiSupported(boolean z) {
            this.B = z;
        }

        public final void setShortcutsSupported(boolean z) {
            this.p = z;
        }

        public final void setSportBinFilePushSupported(boolean z) {
            this.C = z;
        }

        public final void setSportsTypeSupported(boolean z) {
            this.q = z;
        }

        public final void setVibrationAlarmSupported(boolean z) {
            this.c = z;
        }

        public final void setWatchFaceSupported(boolean z) {
            this.f5434a = z;
        }

        public final void setWeatherSupported(boolean z) {
            this.f = z;
        }

        public final void setWorldClockSupported(boolean z) {
            this.r = z;
        }

        @NotNull
        public String toString() {
            return "Control(isWatchFaceSupported=" + this.f5434a + ", isSedentaryReminderSupported=" + this.b + ", isVibrationAlarmSupported=" + this.c + ", isNudgeSupported=" + this.d + ", isScheduleReminderSupported=" + this.e + ", isWeatherSupported=" + this.f + ", isFemaleWellnessTrackerSupported=" + this.g + ", isNotificationSupported=" + this.h + ", isAutoHrSupported=" + this.i + ", isAutoTemperatureSupported=" + this.j + ", isAutoStressHrvSupported=" + this.k + ", isFindMyBandSupported=" + this.l + ", isGoogleFeatSupported=" + this.m + ", isDrinkReminderSupported=" + this.n + ", isActivityAutoRecognitionSupported=" + this.o + ", isShortcutsSupported=" + this.p + ", isSportsTypeSupported=" + this.q + ", isWorldClockSupported=" + this.r + ", isBTCallingSupported=" + this.s + ", isCameraSupported=" + this.t + ", isAmbientSoundLevelSupported=" + this.u + ", isCustomRemindersSupported=" + this.v + ", isRespiratoryRateByPPGSupported=" + this.w + ", isAutoSpo2Supported=" + this.x + ", isGenericEventReminderSupported=" + this.y + ", isAlexaFeatureSupported=" + this.z + ", isCameraControlSettingsSupported=" + this.A + ", isSenseAiSupported=" + this.B + ", isSportBinFilePushSupported=" + this.C + HexStringBuilder.COMMENT_END_CHAR;
        }

        public /* synthetic */ Control(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23, boolean z24, boolean z25, boolean z26, boolean z27, boolean z28, boolean z29, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? false : z6, (i & 64) != 0 ? false : z7, (i & 128) != 0 ? true : z8, (i & 256) != 0 ? true : z9, (i & 512) != 0 ? false : z10, (i & 1024) != 0 ? false : z11, (i & 2048) != 0 ? false : z12, (i & 4096) == 0 ? z13 : true, (i & 8192) != 0 ? false : z14, (i & 16384) != 0 ? false : z15, (i & 32768) != 0 ? false : z16, (i & 65536) != 0 ? false : z17, (i & 131072) != 0 ? false : z18, (i & 262144) != 0 ? false : z19, (i & 524288) != 0 ? false : z20, (i & 1048576) != 0 ? false : z21, (i & 2097152) != 0 ? false : z22, (i & 4194304) != 0 ? false : z23, (i & 8388608) != 0 ? false : z24, (i & 16777216) != 0 ? false : z25, (i & 33554432) != 0 ? false : z26, (i & 67108864) != 0 ? false : z27, (i & 134217728) != 0 ? false : z28, (i & 268435456) != 0 ? false : z29);
        }
    }
}
