package com.coveiot.android.activitymodes.autodetection.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK;
import com.coveiot.android.activitymodes.databinding.ActivityAutoRecogSettingsWithOnekBinding;
import com.coveiot.android.activitymodes.utils.DialogListener;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityAutoRecognitionSettingsWithOneK extends BaseActivity implements DialogListener, View.OnClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityAutoRecogSettingsWithOnekBinding p;
    public ActivityAutoRecognitionViewModelWithOneK q;
    public boolean r;
    @Nullable
    public AutoActivityDetectionData s;
    @Nullable
    public AutoActivityDetectionData t;

    public static final void F(ActivityAutoRecognitionSettingsWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isBluetoothEnabled(this$0)) {
            if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (AppUtils.isNetConnected(this$0)) {
                    if (((SwitchCompat) this$0._$_findCachedViewById(R.id.sw_smart_mode)).isChecked() && !((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).isChecked() && !((CheckBox) this$0._$_findCachedViewById(R.id.cb_12pm_4pm)).isChecked() && !((CheckBox) this$0._$_findCachedViewById(R.id.cb_4pm_9pm)).isChecked() && !((CheckBox) this$0._$_findCachedViewById(R.id.cb_9pm_7am)).isChecked()) {
                        Toast.makeText(this$0, this$0.getString(R.string.please_select_at_least_one_of_the_time_slot), 0).show();
                        return;
                    }
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.CV_AUTO_ACTIVITY_SETTINGS.getValue());
                    analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.AUTO_ACTIVITY_DETECTION.getValue());
                    analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SETTINGS.getValue());
                    HashMap<String, String> hashMap = new HashMap<>();
                    AutoActivityDetectionData autoActivityDetectionData = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData);
                    Boolean smartModeEnabled = autoActivityDetectionData.getSmartModeEnabled();
                    Intrinsics.checkNotNullExpressionValue(smartModeEnabled, "autoActivityDetectionDataL!!.smartModeEnabled");
                    if (smartModeEnabled.booleanValue()) {
                        hashMap.put(FirebaseEventParams.MetaData.CV_SMART_MODE.getValue(), "enable");
                    } else {
                        hashMap.put(FirebaseEventParams.MetaData.CV_SMART_MODE.getValue(), "disable");
                    }
                    ArrayList arrayList = new ArrayList();
                    AutoActivityDetectionData autoActivityDetectionData2 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData2);
                    Boolean mondayEnabled = autoActivityDetectionData2.getMondayEnabled();
                    Intrinsics.checkNotNullExpressionValue(mondayEnabled, "autoActivityDetectionDataL!!.mondayEnabled");
                    if (mondayEnabled.booleanValue()) {
                        arrayList.add("monday");
                    }
                    AutoActivityDetectionData autoActivityDetectionData3 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData3);
                    Boolean tuesdayEnabled = autoActivityDetectionData3.getTuesdayEnabled();
                    Intrinsics.checkNotNullExpressionValue(tuesdayEnabled, "autoActivityDetectionDataL!!.tuesdayEnabled");
                    if (tuesdayEnabled.booleanValue()) {
                        arrayList.add("tuesday");
                    }
                    AutoActivityDetectionData autoActivityDetectionData4 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData4);
                    Boolean wednesdayEnabled = autoActivityDetectionData4.getWednesdayEnabled();
                    Intrinsics.checkNotNullExpressionValue(wednesdayEnabled, "autoActivityDetectionDataL!!.wednesdayEnabled");
                    if (wednesdayEnabled.booleanValue()) {
                        arrayList.add("wednesday");
                    }
                    AutoActivityDetectionData autoActivityDetectionData5 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData5);
                    Boolean thursdayEnabled = autoActivityDetectionData5.getThursdayEnabled();
                    Intrinsics.checkNotNullExpressionValue(thursdayEnabled, "autoActivityDetectionDataL!!.thursdayEnabled");
                    if (thursdayEnabled.booleanValue()) {
                        arrayList.add("thursday");
                    }
                    AutoActivityDetectionData autoActivityDetectionData6 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData6);
                    Boolean fridayEnabled = autoActivityDetectionData6.getFridayEnabled();
                    Intrinsics.checkNotNullExpressionValue(fridayEnabled, "autoActivityDetectionDataL!!.fridayEnabled");
                    if (fridayEnabled.booleanValue()) {
                        arrayList.add("friday");
                    }
                    AutoActivityDetectionData autoActivityDetectionData7 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData7);
                    Boolean saturdayEnabled = autoActivityDetectionData7.getSaturdayEnabled();
                    Intrinsics.checkNotNullExpressionValue(saturdayEnabled, "autoActivityDetectionDataL!!.saturdayEnabled");
                    if (saturdayEnabled.booleanValue()) {
                        arrayList.add("saturday");
                    }
                    AutoActivityDetectionData autoActivityDetectionData8 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData8);
                    Boolean sundayEnabled = autoActivityDetectionData8.getSundayEnabled();
                    Intrinsics.checkNotNullExpressionValue(sundayEnabled, "autoActivityDetectionDataL!!.sundayEnabled");
                    if (sundayEnabled.booleanValue()) {
                        arrayList.add("sunday");
                    }
                    String value = FirebaseEventParams.MetaData.CV_DETECT_DAYS.getValue();
                    String arrayList2 = arrayList.toString();
                    Intrinsics.checkNotNullExpressionValue(arrayList2, "list.toString()");
                    hashMap.put(value, kotlin.text.m.replace$default(kotlin.text.m.replace$default(arrayList2, "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null));
                    ArrayList arrayList3 = new ArrayList();
                    if (((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).isChecked()) {
                        String string = this$0.getString(R.string.mode_7am_12pm);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.mode_7am_12pm)");
                        String lowerCase = string.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        arrayList3.add(lowerCase);
                    }
                    if (((CheckBox) this$0._$_findCachedViewById(R.id.cb_12pm_4pm)).isChecked()) {
                        String string2 = this$0.getString(R.string.mode_12pm_4pm);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.mode_12pm_4pm)");
                        String lowerCase2 = string2.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        arrayList3.add(lowerCase2);
                    }
                    if (((CheckBox) this$0._$_findCachedViewById(R.id.cb_4pm_9pm)).isChecked()) {
                        String string3 = this$0.getString(R.string.mode_4pm_9pm);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.mode_4pm_9pm)");
                        String lowerCase3 = string3.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        arrayList3.add(lowerCase3);
                    }
                    if (((CheckBox) this$0._$_findCachedViewById(R.id.cb_9pm_7am)).isChecked()) {
                        String string4 = this$0.getString(R.string.mode_9pm_7am);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.mode_9pm_7am)");
                        String lowerCase4 = string4.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        arrayList3.add(lowerCase4);
                    }
                    String value2 = FirebaseEventParams.MetaData.CV_SMART_MODE_TIME.getValue();
                    String arrayList4 = arrayList3.toString();
                    Intrinsics.checkNotNullExpressionValue(arrayList4, "interval.toString()");
                    hashMap.put(value2, kotlin.text.m.replace$default(kotlin.text.m.replace$default(arrayList4, "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null));
                    analyticsLog.setMapData(hashMap);
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    this$0.showProgress();
                    ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = this$0.q;
                    if (activityAutoRecognitionViewModelWithOneK == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityAutoRecognitionViewModelWithOneK = null;
                    }
                    AutoActivityDetectionData autoActivityDetectionData9 = this$0.t;
                    Intrinsics.checkNotNull(autoActivityDetectionData9);
                    activityAutoRecognitionViewModelWithOneK.setAutoRecognitionToWatch(autoActivityDetectionData9);
                    return;
                }
                Toast.makeText(this$0, this$0.getString(R.string.please_enable_internet), 0).show();
                return;
            }
            Toast.makeText(this$0, this$0.getString(R.string.band_not_connected), 0).show();
            return;
        }
        Toast.makeText(this$0, this$0.getString(R.string.bluetooth_off_message), 0).show();
    }

    public static final void G(ActivityAutoRecognitionSettingsWithOneK this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            AutoActivityDetectionData autoActivityDetectionData = this$0.t;
            if (autoActivityDetectionData != null) {
                autoActivityDetectionData.setSundayEnabled(Boolean.valueOf(z));
            }
            AutoActivityDetectionData autoActivityDetectionData2 = this$0.t;
            if (autoActivityDetectionData2 != null) {
                autoActivityDetectionData2.setMondayEnabled(Boolean.valueOf(z));
            }
            AutoActivityDetectionData autoActivityDetectionData3 = this$0.t;
            if (autoActivityDetectionData3 != null) {
                autoActivityDetectionData3.setTuesdayEnabled(Boolean.valueOf(z));
            }
            AutoActivityDetectionData autoActivityDetectionData4 = this$0.t;
            if (autoActivityDetectionData4 != null) {
                autoActivityDetectionData4.setWednesdayEnabled(Boolean.valueOf(z));
            }
            AutoActivityDetectionData autoActivityDetectionData5 = this$0.t;
            if (autoActivityDetectionData5 != null) {
                autoActivityDetectionData5.setThursdayEnabled(Boolean.valueOf(z));
            }
            AutoActivityDetectionData autoActivityDetectionData6 = this$0.t;
            if (autoActivityDetectionData6 != null) {
                autoActivityDetectionData6.setFridayEnabled(Boolean.valueOf(z));
            }
            AutoActivityDetectionData autoActivityDetectionData7 = this$0.t;
            if (autoActivityDetectionData7 != null) {
                autoActivityDetectionData7.setSaturdayEnabled(Boolean.valueOf(z));
            }
        }
        this$0.updateRepeatLayout();
    }

    public static final void H(ActivityAutoRecognitionSettingsWithOneK this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoActivityDetectionData autoActivityDetectionData = this$0.t;
        Intrinsics.checkNotNull(autoActivityDetectionData);
        autoActivityDetectionData.setSmartModeEnabled(Boolean.valueOf(z));
        this$0.D();
    }

    public static final void I(ActivityAutoRecognitionSettingsWithOneK this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            int i = R.id.cb_7am_12pm;
            ((CheckBox) this$0._$_findCachedViewById(i)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
            ((CheckBox) this$0._$_findCachedViewById(i)).setTypeface(((CheckBox) this$0._$_findCachedViewById(i)).getTypeface(), 1);
            ((CheckBox) this$0._$_findCachedViewById(i)).setTextColor(this$0.getResources().getColor(R.color.main_text_color));
            AutoActivityDetectionData autoActivityDetectionData = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion = ActivityAutoRecognitionViewModelWithOneK.Companion;
            if (!this$0.Q(autoActivityDetectionData, companion.getST_7AM_12PM(), companion.getET_7AM_12PM())) {
                this$0.C(this$0.t, companion.getST_7AM_12PM(), companion.getET_7AM_12PM());
            }
        } else {
            int i2 = R.id.cb_7am_12pm;
            ((CheckBox) this$0._$_findCachedViewById(i2)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff);
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTypeface(((CheckBox) this$0._$_findCachedViewById(i2)).getTypeface(), 0);
            AutoActivityDetectionData autoActivityDetectionData2 = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion2 = ActivityAutoRecognitionViewModelWithOneK.Companion;
            this$0.T(autoActivityDetectionData2, companion2.getST_7AM_12PM(), companion2.getET_7AM_12PM());
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTextColor(this$0.getResources().getColor(R.color.secondary_text_color));
        }
        this$0.D();
    }

    public static final void J(ActivityAutoRecognitionSettingsWithOneK this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            int i = R.id.cb_12pm_4pm;
            ((CheckBox) this$0._$_findCachedViewById(i)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
            ((CheckBox) this$0._$_findCachedViewById(i)).setTypeface(((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).getTypeface(), 1);
            ((CheckBox) this$0._$_findCachedViewById(i)).setTextColor(this$0.getResources().getColor(R.color.main_text_color));
            AutoActivityDetectionData autoActivityDetectionData = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion = ActivityAutoRecognitionViewModelWithOneK.Companion;
            if (!this$0.Q(autoActivityDetectionData, companion.getST_12PM_4PM(), companion.getET_12PM_4PM())) {
                this$0.C(this$0.t, companion.getST_12PM_4PM(), companion.getET_12PM_4PM());
            }
        } else {
            int i2 = R.id.cb_12pm_4pm;
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTypeface(((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).getTypeface(), 0);
            ((CheckBox) this$0._$_findCachedViewById(i2)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff);
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTextColor(this$0.getResources().getColor(R.color.secondary_text_color));
            AutoActivityDetectionData autoActivityDetectionData2 = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion2 = ActivityAutoRecognitionViewModelWithOneK.Companion;
            this$0.T(autoActivityDetectionData2, companion2.getST_12PM_4PM(), companion2.getET_12PM_4PM());
        }
        this$0.D();
    }

    public static final void K(ActivityAutoRecognitionSettingsWithOneK this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            int i = R.id.cb_4pm_9pm;
            ((CheckBox) this$0._$_findCachedViewById(i)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
            ((CheckBox) this$0._$_findCachedViewById(i)).setTypeface(((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).getTypeface(), 1);
            ((CheckBox) this$0._$_findCachedViewById(i)).setTextColor(this$0.getResources().getColor(R.color.main_text_color));
            AutoActivityDetectionData autoActivityDetectionData = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion = ActivityAutoRecognitionViewModelWithOneK.Companion;
            if (!this$0.Q(autoActivityDetectionData, companion.getST_4PM_9PM(), companion.getET_4PM_9PM())) {
                this$0.C(this$0.t, companion.getST_4PM_9PM(), companion.getET_4PM_9PM());
            }
        } else {
            int i2 = R.id.cb_4pm_9pm;
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTypeface(((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).getTypeface(), 0);
            ((CheckBox) this$0._$_findCachedViewById(i2)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff);
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTextColor(this$0.getResources().getColor(R.color.secondary_text_color));
            AutoActivityDetectionData autoActivityDetectionData2 = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion2 = ActivityAutoRecognitionViewModelWithOneK.Companion;
            this$0.T(autoActivityDetectionData2, companion2.getST_4PM_9PM(), companion2.getET_4PM_9PM());
        }
        this$0.D();
    }

    public static final void L(ActivityAutoRecognitionSettingsWithOneK this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            int i = R.id.cb_9pm_7am;
            ((CheckBox) this$0._$_findCachedViewById(i)).setTypeface(((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).getTypeface(), 1);
            ((CheckBox) this$0._$_findCachedViewById(i)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
            ((CheckBox) this$0._$_findCachedViewById(i)).setTextColor(this$0.getResources().getColor(R.color.main_text_color));
            AutoActivityDetectionData autoActivityDetectionData = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion = ActivityAutoRecognitionViewModelWithOneK.Companion;
            if (!this$0.Q(autoActivityDetectionData, companion.getST_9PM_7AM(), companion.getET_9PM_7AM())) {
                this$0.C(this$0.t, companion.getST_9PM_7AM(), companion.getET_9PM_7AM());
            }
        } else {
            int i2 = R.id.cb_9pm_7am;
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTypeface(((CheckBox) this$0._$_findCachedViewById(R.id.cb_7am_12pm)).getTypeface(), 1);
            ((CheckBox) this$0._$_findCachedViewById(i2)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff);
            ((CheckBox) this$0._$_findCachedViewById(i2)).setTextColor(this$0.getResources().getColor(R.color.secondary_text_color));
            AutoActivityDetectionData autoActivityDetectionData2 = this$0.t;
            ActivityAutoRecognitionViewModelWithOneK.Companion companion2 = ActivityAutoRecognitionViewModelWithOneK.Companion;
            this$0.T(autoActivityDetectionData2, companion2.getST_9PM_7AM(), companion2.getET_9PM_7AM());
        }
        this$0.D();
    }

    public static final void M(ActivityAutoRecognitionSettingsWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void R(BottomSheetDialogTwoButtons dialog, ActivityAutoRecognitionSettingsWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        ((TextView) this$0.findViewById(R.id.save)).performClick();
    }

    public static final void S(BottomSheetDialogTwoButtons dialog, ActivityAutoRecognitionSettingsWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void U(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoRecognitionSettingsWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void V(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoRecognitionSettingsWithOneK this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final void C(AutoActivityDetectionData autoActivityDetectionData, int i, int i2) {
        if (autoActivityDetectionData == null || autoActivityDetectionData.getPeriods() == null) {
            return;
        }
        autoActivityDetectionData.getPeriods().add(new AutoActivityDetectionData.Period(Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public final void D() {
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding = null;
        if (!O() && !P()) {
            this.r = false;
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding2 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAutoRecogSettingsWithOnekBinding = activityAutoRecogSettingsWithOnekBinding2;
            }
            activityAutoRecogSettingsWithOnekBinding.btnSave.setEnabled(false);
            return;
        }
        this.r = true;
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding3 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAutoRecogSettingsWithOnekBinding = activityAutoRecogSettingsWithOnekBinding3;
        }
        activityAutoRecogSettingsWithOnekBinding.btnSave.setEnabled(true);
    }

    public final void E() {
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding = this.p;
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding2 = null;
        if (activityAutoRecogSettingsWithOnekBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding = null;
        }
        activityAutoRecogSettingsWithOnekBinding.btnSave.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionSettingsWithOneK.F(ActivityAutoRecognitionSettingsWithOneK.this, view);
            }
        });
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding3 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding3 = null;
        }
        activityAutoRecogSettingsWithOnekBinding3.repeatLayout.sunday.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding4 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding4 = null;
        }
        activityAutoRecogSettingsWithOnekBinding4.repeatLayout.clRepeat.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding5 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding5 = null;
        }
        activityAutoRecogSettingsWithOnekBinding5.repeatLayout.monday.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding6 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding6 = null;
        }
        activityAutoRecogSettingsWithOnekBinding6.repeatLayout.tuesday.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding7 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding7 = null;
        }
        activityAutoRecogSettingsWithOnekBinding7.repeatLayout.wednesday.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding8 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding8 = null;
        }
        activityAutoRecogSettingsWithOnekBinding8.repeatLayout.thursday.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding9 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding9 = null;
        }
        activityAutoRecogSettingsWithOnekBinding9.repeatLayout.friday.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding10 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoRecogSettingsWithOnekBinding10 = null;
        }
        activityAutoRecogSettingsWithOnekBinding10.repeatLayout.saturday.setOnClickListener(this);
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding11 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAutoRecogSettingsWithOnekBinding2 = activityAutoRecogSettingsWithOnekBinding11;
        }
        activityAutoRecogSettingsWithOnekBinding2.repeatLayout.cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.x
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoRecognitionSettingsWithOneK.G(ActivityAutoRecognitionSettingsWithOneK.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.sw_smart_mode)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.w
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoRecognitionSettingsWithOneK.H(ActivityAutoRecognitionSettingsWithOneK.this, compoundButton, z);
            }
        });
        ((CheckBox) _$_findCachedViewById(R.id.cb_7am_12pm)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.o
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoRecognitionSettingsWithOneK.I(ActivityAutoRecognitionSettingsWithOneK.this, compoundButton, z);
            }
        });
        ((CheckBox) _$_findCachedViewById(R.id.cb_12pm_4pm)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.v
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoRecognitionSettingsWithOneK.J(ActivityAutoRecognitionSettingsWithOneK.this, compoundButton, z);
            }
        });
        ((CheckBox) _$_findCachedViewById(R.id.cb_4pm_9pm)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.y
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoRecognitionSettingsWithOneK.K(ActivityAutoRecognitionSettingsWithOneK.this, compoundButton, z);
            }
        });
        ((CheckBox) _$_findCachedViewById(R.id.cb_9pm_7am)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.p
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoRecognitionSettingsWithOneK.L(ActivityAutoRecognitionSettingsWithOneK.this, compoundButton, z);
            }
        });
    }

    public final void N() {
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding = null;
        if (this.t == null) {
            ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = this.q;
            if (activityAutoRecognitionViewModelWithOneK == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityAutoRecognitionViewModelWithOneK = null;
            }
            this.t = activityAutoRecognitionViewModelWithOneK.getDefaultAutoActivityDetectionData();
        }
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding2 = this.p;
        if (activityAutoRecogSettingsWithOnekBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAutoRecogSettingsWithOnekBinding = activityAutoRecogSettingsWithOnekBinding2;
        }
        boolean z = false;
        activityAutoRecogSettingsWithOnekBinding.repeatLayout.tvSelectDaysInfo.setVisibility(0);
        updateRepeatLayout();
        int i = R.id.cb_7am_12pm;
        ((CheckBox) _$_findCachedViewById(i)).setChecked(false);
        int i2 = R.drawable.rounded_dark_grey_background_40dp_33ffffff;
        ((CheckBox) _$_findCachedViewById(i)).setBackgroundResource(i2);
        int i3 = R.id.cb_12pm_4pm;
        ((CheckBox) _$_findCachedViewById(i3)).setChecked(false);
        ((CheckBox) _$_findCachedViewById(i3)).setBackgroundResource(i2);
        int i4 = R.id.cb_4pm_9pm;
        ((CheckBox) _$_findCachedViewById(i4)).setChecked(false);
        ((CheckBox) _$_findCachedViewById(i4)).setBackgroundResource(i2);
        int i5 = R.id.cb_9pm_7am;
        ((CheckBox) _$_findCachedViewById(i5)).setChecked(false);
        ((CheckBox) _$_findCachedViewById(i5)).setBackgroundResource(i2);
        ((CheckBox) _$_findCachedViewById(i)).setTypeface(((CheckBox) _$_findCachedViewById(i)).getTypeface(), 0);
        ((CheckBox) _$_findCachedViewById(i3)).setTypeface(((CheckBox) _$_findCachedViewById(i)).getTypeface(), 0);
        ((CheckBox) _$_findCachedViewById(i4)).setTypeface(((CheckBox) _$_findCachedViewById(i)).getTypeface(), 0);
        ((CheckBox) _$_findCachedViewById(i5)).setTypeface(((CheckBox) _$_findCachedViewById(i)).getTypeface(), 0);
        AutoActivityDetectionData autoActivityDetectionData = this.t;
        Intrinsics.checkNotNull(autoActivityDetectionData);
        Boolean smartModeEnabled = autoActivityDetectionData.getSmartModeEnabled();
        Intrinsics.checkNotNullExpressionValue(smartModeEnabled, "autoActivityDetectionDataL!!.smartModeEnabled");
        ((SwitchCompat) _$_findCachedViewById(R.id.sw_smart_mode)).setChecked(smartModeEnabled.booleanValue());
        AutoActivityDetectionData autoActivityDetectionData2 = this.t;
        Intrinsics.checkNotNull(autoActivityDetectionData2);
        List<AutoActivityDetectionData.Period> periods = autoActivityDetectionData2.getPeriods();
        if ((periods == null || periods.isEmpty()) ? true : true) {
            return;
        }
        AutoActivityDetectionData autoActivityDetectionData3 = this.t;
        Intrinsics.checkNotNull(autoActivityDetectionData3);
        for (AutoActivityDetectionData.Period period : autoActivityDetectionData3.getPeriods()) {
            Integer startTime = period.getStartTime();
            ActivityAutoRecognitionViewModelWithOneK.Companion companion = ActivityAutoRecognitionViewModelWithOneK.Companion;
            int st_7am_12pm = companion.getST_7AM_12PM();
            if (startTime != null && startTime.intValue() == st_7am_12pm) {
                Integer endTime = period.getEndTime();
                int et_7am_12pm = companion.getET_7AM_12PM();
                if (endTime != null && endTime.intValue() == et_7am_12pm) {
                    int i6 = R.id.cb_7am_12pm;
                    ((CheckBox) _$_findCachedViewById(i6)).setChecked(true);
                    ((CheckBox) _$_findCachedViewById(i6)).setTypeface(((CheckBox) _$_findCachedViewById(i6)).getTypeface(), 1);
                    ((CheckBox) _$_findCachedViewById(i6)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
                    ((CheckBox) _$_findCachedViewById(i6)).setTextColor(getResources().getColor(R.color.main_text_color));
                }
            }
            Integer startTime2 = period.getStartTime();
            int st_12pm_4pm = companion.getST_12PM_4PM();
            if (startTime2 != null && startTime2.intValue() == st_12pm_4pm) {
                Integer endTime2 = period.getEndTime();
                int et_12pm_4pm = companion.getET_12PM_4PM();
                if (endTime2 != null && endTime2.intValue() == et_12pm_4pm) {
                    int i7 = R.id.cb_12pm_4pm;
                    ((CheckBox) _$_findCachedViewById(i7)).setChecked(true);
                    ((CheckBox) _$_findCachedViewById(i7)).setTypeface(((CheckBox) _$_findCachedViewById(i7)).getTypeface(), 1);
                    ((CheckBox) _$_findCachedViewById(i7)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
                    ((CheckBox) _$_findCachedViewById(i7)).setTextColor(getResources().getColor(R.color.main_text_color));
                }
            }
            Integer startTime3 = period.getStartTime();
            int st_4pm_9pm = companion.getST_4PM_9PM();
            if (startTime3 != null && startTime3.intValue() == st_4pm_9pm) {
                Integer endTime3 = period.getEndTime();
                int et_4pm_9pm = companion.getET_4PM_9PM();
                if (endTime3 != null && endTime3.intValue() == et_4pm_9pm) {
                    int i8 = R.id.cb_4pm_9pm;
                    ((CheckBox) _$_findCachedViewById(i8)).setChecked(true);
                    ((CheckBox) _$_findCachedViewById(i8)).setTypeface(((CheckBox) _$_findCachedViewById(i8)).getTypeface(), 1);
                    ((CheckBox) _$_findCachedViewById(i8)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
                    ((CheckBox) _$_findCachedViewById(i8)).setTextColor(getResources().getColor(R.color.main_text_color));
                }
            }
            Integer startTime4 = period.getStartTime();
            int st_9pm_7am = companion.getST_9PM_7AM();
            if (startTime4 != null && startTime4.intValue() == st_9pm_7am) {
                Integer endTime4 = period.getEndTime();
                int et_9pm_7am = companion.getET_9PM_7AM();
                if (endTime4 != null && endTime4.intValue() == et_9pm_7am) {
                    int i9 = R.id.cb_9pm_7am;
                    ((CheckBox) _$_findCachedViewById(i9)).setChecked(true);
                    ((CheckBox) _$_findCachedViewById(i9)).setTypeface(((CheckBox) _$_findCachedViewById(i9)).getTypeface(), 1);
                    ((CheckBox) _$_findCachedViewById(i9)).setBackgroundResource(R.drawable.rounded_dark_grey_background_40dp_33ffffff_selected);
                    ((CheckBox) _$_findCachedViewById(i9)).setTextColor(getResources().getColor(R.color.main_text_color));
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0089, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r3.getSaturdayEnabled(), r0.getSaturdayEnabled()) == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean O() {
        /*
            r5 = this;
            com.coveiot.covepreferences.data.AutoActivityDetectionData r0 = r5.s
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L8f
            com.coveiot.covepreferences.data.AutoActivityDetectionData r3 = r5.t
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Boolean r3 = r3.getSundayEnabled()
            java.lang.Boolean r4 = r0.getSundayEnabled()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L8b
            com.coveiot.covepreferences.data.AutoActivityDetectionData r3 = r5.t
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Boolean r3 = r3.getMondayEnabled()
            java.lang.Boolean r4 = r0.getMondayEnabled()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L8b
            com.coveiot.covepreferences.data.AutoActivityDetectionData r3 = r5.t
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Boolean r3 = r3.getTuesdayEnabled()
            java.lang.Boolean r4 = r0.getTuesdayEnabled()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L8b
            com.coveiot.covepreferences.data.AutoActivityDetectionData r3 = r5.t
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Boolean r3 = r3.getWednesdayEnabled()
            java.lang.Boolean r4 = r0.getWednesdayEnabled()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L8b
            com.coveiot.covepreferences.data.AutoActivityDetectionData r3 = r5.t
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Boolean r3 = r3.getThursdayEnabled()
            java.lang.Boolean r4 = r0.getThursdayEnabled()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L8b
            com.coveiot.covepreferences.data.AutoActivityDetectionData r3 = r5.t
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Boolean r3 = r3.getFridayEnabled()
            java.lang.Boolean r4 = r0.getFridayEnabled()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L8b
            com.coveiot.covepreferences.data.AutoActivityDetectionData r3 = r5.t
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Boolean r3 = r3.getSaturdayEnabled()
            java.lang.Boolean r0 = r0.getSaturdayEnabled()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            if (r0 != 0) goto L8c
        L8b:
            r2 = r1
        L8c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            goto L90
        L8f:
            r0 = 0
        L90:
            if (r0 != 0) goto L93
            goto L94
        L93:
            r1 = r2
        L94:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.autodetection.activities.ActivityAutoRecognitionSettingsWithOneK.O():boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0043, code lost:
        if ((r3 == null || r3.isEmpty()) != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006c, code lost:
        if ((r3 == null || r3.isEmpty()) == false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean P() {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.autodetection.activities.ActivityAutoRecognitionSettingsWithOneK.P():boolean");
    }

    public final boolean Q(AutoActivityDetectionData autoActivityDetectionData, int i, int i2) {
        Integer endTime;
        if (autoActivityDetectionData != null) {
            List<AutoActivityDetectionData.Period> periods = autoActivityDetectionData.getPeriods();
            if (!(periods == null || periods.isEmpty())) {
                for (AutoActivityDetectionData.Period period : autoActivityDetectionData.getPeriods()) {
                    Integer startTime = period.getStartTime();
                    if (startTime != null && i == startTime.intValue() && (endTime = period.getEndTime()) != null && i2 == endTime.intValue()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void T(AutoActivityDetectionData autoActivityDetectionData, int i, int i2) {
        Integer endTime;
        if (autoActivityDetectionData != null) {
            List<AutoActivityDetectionData.Period> periods = autoActivityDetectionData.getPeriods();
            if (periods == null || periods.isEmpty()) {
                return;
            }
            for (AutoActivityDetectionData.Period period : autoActivityDetectionData.getPeriods()) {
                Integer startTime = period.getStartTime();
                if (startTime != null && i == startTime.intValue() && (endTime = period.getEndTime()) != null && i2 == endTime.intValue()) {
                    autoActivityDetectionData.getPeriods().remove(period);
                    return;
                }
            }
        }
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

    @Nullable
    public final AutoActivityDetectionData getAutoActivityDetectionDataL() {
        return this.t;
    }

    @Nullable
    public final AutoActivityDetectionData getAutoActivityDetectionDataPref() {
        return this.s;
    }

    public final boolean getBoolSaveVisible() {
        return this.r;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.mange_activity_settings));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionSettingsWithOneK.M(ActivityAutoRecognitionSettingsWithOneK.this, view);
            }
        });
        int i = R.id.save;
        ((TextView) findViewById(i)).setVisibility(8);
        ((TextView) findViewById(i)).setText(getString(R.string.save_camel));
        ((TextView) findViewById(i)).setEnabled(false);
        ((TextView) findViewById(i)).setTextColor(getColor(R.color.secondary_text_color));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.r) {
            String string = getString(R.string.manage_activity);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.manage_activity)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.u
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoRecognitionSettingsWithOneK.R(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoRecognitionSettingsWithOneK.S(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            if (bottomSheetDialogTwoButtons.isShowing()) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        AutoActivityDetectionData autoActivityDetectionData;
        Boolean saturdayEnabled;
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        int i = R.id.sunday;
        if (valueOf != null && valueOf.intValue() == i) {
            AutoActivityDetectionData autoActivityDetectionData2 = this.t;
            if (autoActivityDetectionData2 != null) {
                saturdayEnabled = autoActivityDetectionData2 != null ? autoActivityDetectionData2.getSundayEnabled() : null;
                Intrinsics.checkNotNull(saturdayEnabled);
                autoActivityDetectionData2.setSundayEnabled(Boolean.valueOf(!saturdayEnabled.booleanValue()));
            }
        } else {
            int i2 = R.id.monday;
            if (valueOf != null && valueOf.intValue() == i2) {
                AutoActivityDetectionData autoActivityDetectionData3 = this.t;
                if (autoActivityDetectionData3 != null) {
                    saturdayEnabled = autoActivityDetectionData3 != null ? autoActivityDetectionData3.getMondayEnabled() : null;
                    Intrinsics.checkNotNull(saturdayEnabled);
                    autoActivityDetectionData3.setMondayEnabled(Boolean.valueOf(!saturdayEnabled.booleanValue()));
                }
            } else {
                int i3 = R.id.tuesday;
                if (valueOf != null && valueOf.intValue() == i3) {
                    AutoActivityDetectionData autoActivityDetectionData4 = this.t;
                    if (autoActivityDetectionData4 != null) {
                        saturdayEnabled = autoActivityDetectionData4 != null ? autoActivityDetectionData4.getTuesdayEnabled() : null;
                        Intrinsics.checkNotNull(saturdayEnabled);
                        autoActivityDetectionData4.setTuesdayEnabled(Boolean.valueOf(!saturdayEnabled.booleanValue()));
                    }
                } else {
                    int i4 = R.id.wednesday;
                    if (valueOf != null && valueOf.intValue() == i4) {
                        AutoActivityDetectionData autoActivityDetectionData5 = this.t;
                        if (autoActivityDetectionData5 != null) {
                            saturdayEnabled = autoActivityDetectionData5 != null ? autoActivityDetectionData5.getWednesdayEnabled() : null;
                            Intrinsics.checkNotNull(saturdayEnabled);
                            autoActivityDetectionData5.setWednesdayEnabled(Boolean.valueOf(!saturdayEnabled.booleanValue()));
                        }
                    } else {
                        int i5 = R.id.thursday;
                        if (valueOf != null && valueOf.intValue() == i5) {
                            AutoActivityDetectionData autoActivityDetectionData6 = this.t;
                            if (autoActivityDetectionData6 != null) {
                                saturdayEnabled = autoActivityDetectionData6 != null ? autoActivityDetectionData6.getThursdayEnabled() : null;
                                Intrinsics.checkNotNull(saturdayEnabled);
                                autoActivityDetectionData6.setThursdayEnabled(Boolean.valueOf(!saturdayEnabled.booleanValue()));
                            }
                        } else {
                            int i6 = R.id.friday;
                            if (valueOf != null && valueOf.intValue() == i6) {
                                AutoActivityDetectionData autoActivityDetectionData7 = this.t;
                                if (autoActivityDetectionData7 != null) {
                                    saturdayEnabled = autoActivityDetectionData7 != null ? autoActivityDetectionData7.getFridayEnabled() : null;
                                    Intrinsics.checkNotNull(saturdayEnabled);
                                    autoActivityDetectionData7.setFridayEnabled(Boolean.valueOf(!saturdayEnabled.booleanValue()));
                                }
                            } else {
                                int i7 = R.id.saturday;
                                if (valueOf != null && valueOf.intValue() == i7 && (autoActivityDetectionData = this.t) != null) {
                                    saturdayEnabled = autoActivityDetectionData != null ? autoActivityDetectionData.getSaturdayEnabled() : null;
                                    Intrinsics.checkNotNull(saturdayEnabled);
                                    autoActivityDetectionData.setSaturdayEnabled(Boolean.valueOf(!saturdayEnabled.booleanValue()));
                                }
                            }
                        }
                    }
                }
            }
        }
        updateRepeatLayout();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAutoRecogSettingsWithOnekBinding inflate = ActivityAutoRecogSettingsWithOnekBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ViewModel viewModel = ViewModelProviders.of(this).get(ActivityAutoRecognitionViewModelWithOneK.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(ActivityAut…odelWithOneK::class.java)");
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK2 = (ActivityAutoRecognitionViewModelWithOneK) viewModel;
        this.q = activityAutoRecognitionViewModelWithOneK2;
        if (activityAutoRecognitionViewModelWithOneK2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityAutoRecognitionViewModelWithOneK = activityAutoRecognitionViewModelWithOneK2;
        }
        activityAutoRecognitionViewModelWithOneK.setDialogListener(this);
        this.s = UserDataManager.getInstance(this).getAutoActivityDetectionData();
        this.t = UserDataManager.getInstance(this).getAutoActivityDetectionData();
        initToolbar();
        N();
        E();
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setAutoActivityDetectionDataL(@Nullable AutoActivityDetectionData autoActivityDetectionData) {
        this.t = autoActivityDetectionData;
    }

    public final void setAutoActivityDetectionDataPref(@Nullable AutoActivityDetectionData autoActivityDetectionData) {
        this.s = autoActivityDetectionData;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.r = z;
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void showErrorDialog() {
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionSettingsWithOneK.U(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.activitymodes.utils.DialogListener
    public void showSuccessDialog() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionSettingsWithOneK.V(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void updateRepeatLayout() {
        AutoActivityDetectionData autoActivityDetectionData = this.t;
        ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding = null;
        Boolean sundayEnabled = autoActivityDetectionData != null ? autoActivityDetectionData.getSundayEnabled() : null;
        Intrinsics.checkNotNull(sundayEnabled);
        if (sundayEnabled.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding2 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding2 = null;
            }
            activityAutoRecogSettingsWithOnekBinding2.repeatLayout.sunday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding3 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding3 = null;
            }
            activityAutoRecogSettingsWithOnekBinding3.repeatLayout.sunday.setTextColor(getResources().getColor(R.color.main_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding4 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding4 = null;
            }
            activityAutoRecogSettingsWithOnekBinding4.repeatLayout.sunday.setTextAppearance(R.style.semi_bold);
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding5 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding5 = null;
            }
            activityAutoRecogSettingsWithOnekBinding5.repeatLayout.sunday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding6 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding6 = null;
            }
            activityAutoRecogSettingsWithOnekBinding6.repeatLayout.sunday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding7 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding7 = null;
            }
            activityAutoRecogSettingsWithOnekBinding7.repeatLayout.sunday.setTextAppearance(R.style.regular);
        }
        AutoActivityDetectionData autoActivityDetectionData2 = this.t;
        Boolean mondayEnabled = autoActivityDetectionData2 != null ? autoActivityDetectionData2.getMondayEnabled() : null;
        Intrinsics.checkNotNull(mondayEnabled);
        if (mondayEnabled.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding8 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding8 = null;
            }
            activityAutoRecogSettingsWithOnekBinding8.repeatLayout.monday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding9 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding9 = null;
            }
            activityAutoRecogSettingsWithOnekBinding9.repeatLayout.monday.setTextColor(getResources().getColor(R.color.main_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding10 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding10 = null;
            }
            activityAutoRecogSettingsWithOnekBinding10.repeatLayout.monday.setTextAppearance(R.style.semi_bold);
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding11 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding11 = null;
            }
            activityAutoRecogSettingsWithOnekBinding11.repeatLayout.monday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding12 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding12 = null;
            }
            activityAutoRecogSettingsWithOnekBinding12.repeatLayout.monday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding13 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding13 = null;
            }
            activityAutoRecogSettingsWithOnekBinding13.repeatLayout.monday.setTextAppearance(R.style.regular);
        }
        AutoActivityDetectionData autoActivityDetectionData3 = this.t;
        Boolean tuesdayEnabled = autoActivityDetectionData3 != null ? autoActivityDetectionData3.getTuesdayEnabled() : null;
        Intrinsics.checkNotNull(tuesdayEnabled);
        if (tuesdayEnabled.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding14 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding14 = null;
            }
            activityAutoRecogSettingsWithOnekBinding14.repeatLayout.tuesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding15 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding15 = null;
            }
            activityAutoRecogSettingsWithOnekBinding15.repeatLayout.tuesday.setTextColor(getResources().getColor(R.color.main_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding16 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding16 = null;
            }
            activityAutoRecogSettingsWithOnekBinding16.repeatLayout.tuesday.setTextAppearance(R.style.semi_bold);
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding17 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding17 = null;
            }
            activityAutoRecogSettingsWithOnekBinding17.repeatLayout.tuesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding18 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding18 = null;
            }
            activityAutoRecogSettingsWithOnekBinding18.repeatLayout.tuesday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding19 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding19 = null;
            }
            activityAutoRecogSettingsWithOnekBinding19.repeatLayout.tuesday.setTextAppearance(R.style.regular);
        }
        AutoActivityDetectionData autoActivityDetectionData4 = this.t;
        Boolean wednesdayEnabled = autoActivityDetectionData4 != null ? autoActivityDetectionData4.getWednesdayEnabled() : null;
        Intrinsics.checkNotNull(wednesdayEnabled);
        if (wednesdayEnabled.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding20 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding20 = null;
            }
            activityAutoRecogSettingsWithOnekBinding20.repeatLayout.wednesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding21 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding21 = null;
            }
            activityAutoRecogSettingsWithOnekBinding21.repeatLayout.wednesday.setTextColor(getResources().getColor(R.color.main_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding22 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding22 = null;
            }
            activityAutoRecogSettingsWithOnekBinding22.repeatLayout.wednesday.setTextAppearance(R.style.semi_bold);
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding23 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding23 = null;
            }
            activityAutoRecogSettingsWithOnekBinding23.repeatLayout.wednesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding24 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding24 = null;
            }
            activityAutoRecogSettingsWithOnekBinding24.repeatLayout.wednesday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding25 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding25 = null;
            }
            activityAutoRecogSettingsWithOnekBinding25.repeatLayout.wednesday.setTextAppearance(R.style.regular);
        }
        AutoActivityDetectionData autoActivityDetectionData5 = this.t;
        Boolean thursdayEnabled = autoActivityDetectionData5 != null ? autoActivityDetectionData5.getThursdayEnabled() : null;
        Intrinsics.checkNotNull(thursdayEnabled);
        if (thursdayEnabled.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding26 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding26 = null;
            }
            activityAutoRecogSettingsWithOnekBinding26.repeatLayout.thursday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding27 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding27 = null;
            }
            activityAutoRecogSettingsWithOnekBinding27.repeatLayout.thursday.setTextColor(getResources().getColor(R.color.main_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding28 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding28 = null;
            }
            activityAutoRecogSettingsWithOnekBinding28.repeatLayout.thursday.setTextAppearance(R.style.semi_bold);
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding29 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding29 = null;
            }
            activityAutoRecogSettingsWithOnekBinding29.repeatLayout.thursday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding30 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding30 = null;
            }
            activityAutoRecogSettingsWithOnekBinding30.repeatLayout.thursday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding31 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding31 = null;
            }
            activityAutoRecogSettingsWithOnekBinding31.repeatLayout.thursday.setTextAppearance(R.style.regular);
        }
        AutoActivityDetectionData autoActivityDetectionData6 = this.t;
        Boolean fridayEnabled = autoActivityDetectionData6 != null ? autoActivityDetectionData6.getFridayEnabled() : null;
        Intrinsics.checkNotNull(fridayEnabled);
        if (fridayEnabled.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding32 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding32 = null;
            }
            activityAutoRecogSettingsWithOnekBinding32.repeatLayout.friday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding33 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding33 = null;
            }
            activityAutoRecogSettingsWithOnekBinding33.repeatLayout.friday.setTextColor(getResources().getColor(R.color.main_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding34 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding34 = null;
            }
            activityAutoRecogSettingsWithOnekBinding34.repeatLayout.friday.setTextAppearance(R.style.semi_bold);
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding35 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding35 = null;
            }
            activityAutoRecogSettingsWithOnekBinding35.repeatLayout.friday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding36 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding36 = null;
            }
            activityAutoRecogSettingsWithOnekBinding36.repeatLayout.friday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding37 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding37 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding37 = null;
            }
            activityAutoRecogSettingsWithOnekBinding37.repeatLayout.friday.setTextAppearance(R.style.regular);
        }
        AutoActivityDetectionData autoActivityDetectionData7 = this.t;
        Boolean saturdayEnabled = autoActivityDetectionData7 != null ? autoActivityDetectionData7.getSaturdayEnabled() : null;
        Intrinsics.checkNotNull(saturdayEnabled);
        if (saturdayEnabled.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding38 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding38 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding38 = null;
            }
            activityAutoRecogSettingsWithOnekBinding38.repeatLayout.saturday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding39 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding39 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding39 = null;
            }
            activityAutoRecogSettingsWithOnekBinding39.repeatLayout.saturday.setTextColor(getResources().getColor(R.color.main_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding40 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding40 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding40 = null;
            }
            activityAutoRecogSettingsWithOnekBinding40.repeatLayout.saturday.setTextAppearance(R.style.semi_bold);
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding41 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding41 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding41 = null;
            }
            activityAutoRecogSettingsWithOnekBinding41.repeatLayout.saturday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding42 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding42 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding42 = null;
            }
            activityAutoRecogSettingsWithOnekBinding42.repeatLayout.saturday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding43 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding43 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding43 = null;
            }
            activityAutoRecogSettingsWithOnekBinding43.repeatLayout.saturday.setTextAppearance(R.style.regular);
        }
        AutoActivityDetectionData autoActivityDetectionData8 = this.t;
        Boolean valueOf = autoActivityDetectionData8 != null ? Boolean.valueOf(autoActivityDetectionData8.isAllDaysEnabled()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding44 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding44 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding44 = null;
            }
            if (!activityAutoRecogSettingsWithOnekBinding44.repeatLayout.cbSelectAll.isChecked()) {
                ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding45 = this.p;
                if (activityAutoRecogSettingsWithOnekBinding45 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAutoRecogSettingsWithOnekBinding = activityAutoRecogSettingsWithOnekBinding45;
                }
                activityAutoRecogSettingsWithOnekBinding.repeatLayout.cbSelectAll.setChecked(true);
            }
        } else {
            ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding46 = this.p;
            if (activityAutoRecogSettingsWithOnekBinding46 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAutoRecogSettingsWithOnekBinding46 = null;
            }
            if (activityAutoRecogSettingsWithOnekBinding46.repeatLayout.cbSelectAll.isChecked()) {
                ActivityAutoRecogSettingsWithOnekBinding activityAutoRecogSettingsWithOnekBinding47 = this.p;
                if (activityAutoRecogSettingsWithOnekBinding47 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAutoRecogSettingsWithOnekBinding = activityAutoRecogSettingsWithOnekBinding47;
                }
                activityAutoRecogSettingsWithOnekBinding.repeatLayout.cbSelectAll.setChecked(false);
            }
        }
        D();
    }
}
