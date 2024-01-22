package com.coveiot.android.sos;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.sos.databinding.ActivitySosBinding;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ActivitySOS extends BaseActivity implements SuccessResultListener {
    public ActivitySosBinding p;
    public SOSSettingsViewmodel q;
    public boolean r;
    @Nullable
    public SOSData s;
    @Nullable
    public CoveContact t;
    public boolean u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int v = 1001;
    public final int w = 1;

    public static final void H(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySosBinding activitySosBinding = null;
        if (this$0.r) {
            this$0.r = false;
            ActivitySosBinding activitySosBinding2 = this$0.p;
            if (activitySosBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySosBinding = activitySosBinding2;
            }
            View root = activitySosBinding.itemSos.editSos.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.itemSos.editSos.root");
            this$0.gone(root);
            return;
        }
        this$0.r = true;
        ActivitySosBinding activitySosBinding3 = this$0.p;
        if (activitySosBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosBinding = activitySosBinding3;
        }
        View root2 = activitySosBinding.itemSos.editSos.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "binding.itemSos.editSos.root");
        this$0.visible(root2);
    }

    public static final void I(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySosBinding activitySosBinding = this$0.p;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        View root = activitySosBinding.itemSos.editSos.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.itemSos.editSos.root");
        this$0.gone(root);
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this$0)) {
                this$0.Q(SOSCleverTapConstants.BC_SOS_DELETECONTACT_REQUEST.getValue());
                this$0.N("delete_contact");
                this$0.b0();
                return;
            }
            this$0.showNoInternetMessage();
            return;
        }
        this$0.showWatchDisconnectedDialog(this$0);
    }

    public static final void J(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySosBinding activitySosBinding = this$0.p;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        View root = activitySosBinding.itemSos.editSos.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.itemSos.editSos.root");
        this$0.gone(root);
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this$0)) {
                this$0.Q(SOSCleverTapConstants.BC_SOS_CHANGECONTACT_REQUEST.getValue());
                this$0.N("change_contact");
                this$0.W();
                return;
            }
            this$0.showNoInternetMessage();
            return;
        }
        this$0.showWatchDisconnectedDialog(this$0);
    }

    public static final void K(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.N("notify");
        ActivitySosBinding activitySosBinding = this$0.p;
        ActivitySosBinding activitySosBinding2 = null;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        View root = activitySosBinding.itemSos.editSos.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.itemSos.editSos.root");
        this$0.gone(root);
        ActivitySosBinding activitySosBinding3 = this$0.p;
        if (activitySosBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosBinding2 = activitySosBinding3;
        }
        View root2 = activitySosBinding2.tooltip.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "binding.tooltip.root");
        this$0.visible(root2);
    }

    public static final void L(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void M(ActivitySOS this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.u = true;
        if (this$0.s != null) {
            if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (!AppUtils.isNetConnected(this$0)) {
                    this$0.showNoInternetMessage();
                    return;
                }
                SOSSettingsViewmodel sOSSettingsViewmodel = null;
                if (z) {
                    this$0.P(true);
                    this$0.Q(SOSCleverTapConstants.BC_SOS_ENABLE_REQUEST.getValue());
                    this$0.showProgress();
                    SOSSettingsViewmodel sOSSettingsViewmodel2 = this$0.q;
                    if (sOSSettingsViewmodel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        sOSSettingsViewmodel = sOSSettingsViewmodel2;
                    }
                    SOSData sOSData = this$0.s;
                    Intrinsics.checkNotNull(sOSData);
                    String contactName = sOSData.getContactName();
                    Intrinsics.checkNotNull(contactName);
                    SOSData sOSData2 = this$0.s;
                    Intrinsics.checkNotNull(sOSData2);
                    String contactNumber = sOSData2.getContactNumber();
                    Intrinsics.checkNotNull(contactNumber);
                    sOSSettingsViewmodel.setSOSConfigSettings(true, contactName, contactNumber, false);
                    return;
                }
                this$0.P(false);
                this$0.Q(SOSCleverTapConstants.BC_SOS_DISABLE_REQUEST.getValue());
                this$0.showProgress();
                SOSSettingsViewmodel sOSSettingsViewmodel3 = this$0.q;
                if (sOSSettingsViewmodel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    sOSSettingsViewmodel = sOSSettingsViewmodel3;
                }
                SOSData sOSData3 = this$0.s;
                Intrinsics.checkNotNull(sOSData3);
                String contactName2 = sOSData3.getContactName();
                Intrinsics.checkNotNull(contactName2);
                SOSData sOSData4 = this$0.s;
                Intrinsics.checkNotNull(sOSData4);
                String contactNumber2 = sOSData4.getContactNumber();
                Intrinsics.checkNotNull(contactNumber2);
                sOSSettingsViewmodel.setSOSConfigSettings(false, contactName2, contactNumber2, false);
                return;
            }
            this$0.showWatchDisconnectedDialog(this$0);
        }
    }

    public static final void S(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.Q(SOSCleverTapConstants.BC_SOS_TNC_VIEWED.getValue());
            SessionManager sessionManager = SessionManager.getInstance(this$0);
            Intent intent = new Intent();
            intent.setClassName(this$0, "com.coveiot.android.leonardo.websupport.ActivityInAppWebViewer");
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_URL, sessionManager.getSOSDisclaimerURL() != null ? sessionManager.getSOSDisclaimerURL() : "https://docs.coveiot.com/kaha/eula.html");
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_TITLE, this$0.getString(R.string.terms_conditions_title));
            this$0.startActivity(intent);
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void T(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySosBinding activitySosBinding = this$0.p;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        View root = activitySosBinding.tooltip.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.tooltip.root");
        this$0.gone(root);
    }

    public static final void U(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySosBinding activitySosBinding = this$0.p;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        View root = activitySosBinding.tooltip.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.tooltip.root");
        this$0.gone(root);
    }

    public static final void V(ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Q(SOSCleverTapConstants.BC_SOS_NOTIFY_CONTACT_TAPPED.getValue());
        ActivitySosBinding activitySosBinding = this$0.p;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        View root = activitySosBinding.tooltip.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.tooltip.root");
        this$0.gone(root);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", this$0.getString(R.string.notify_ememrgency_contact_message));
        intent.addFlags(1073741824);
        this$0.startActivity(Intent.createChooser(intent, "Share using"));
    }

    public static final void X(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this$0)) {
                this$0.Q(SOSCleverTapConstants.BC_SOS_CHANGECONTACT_SUCCESS.getValue());
                String value = FirebaseEventParams.EventName.CV_EMERGENCY_CONTACT_CHANGE.getValue();
                FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.EMERGENCY_SOS;
                this$0.O(value, screenName.getValue(), "proceed");
                FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(screenName.getValue());
                this$0.startActivity(new Intent(this$0, ActivitySOSSettings.class));
                this$0.finish();
                return;
            }
            this$0.showNoInternetMessage();
            return;
        }
        this$0.showWatchDisconnectedDialog(this$0);
    }

    public static final void Y(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        this$0.O(FirebaseEventParams.EventName.CV_EMERGENCY_CONTACT_CHANGE.getValue(), FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue(), "cancel");
    }

    public static final void a0(CommonMessageDialog commonMessageDialog, boolean z, ActivitySOS this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        commonMessageDialog.dismiss();
        if (z) {
            return;
        }
        FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue());
        this$0.finish();
    }

    public static final void c0(ActivitySOS this$0, BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        this$0.O(FirebaseEventParams.EventName.CV_EMERGENCY_CONTACT_DELETE.getValue(), FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue(), "no");
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void d0(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, ActivitySOS this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this$0)) {
                this$0.showProgress();
                this$0.Q(SOSCleverTapConstants.BC_SOS_DELETECONTACT_SUCCESS.getValue());
                this$0.O(FirebaseEventParams.EventName.CV_EMERGENCY_CONTACT_DELETE.getValue(), FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue(), "yes");
                this$0.u = false;
                SOSSettingsViewmodel sOSSettingsViewmodel = this$0.q;
                if (sOSSettingsViewmodel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    sOSSettingsViewmodel = null;
                }
                sOSSettingsViewmodel.setSOSConfigSettings(false, "", "", true);
                return;
            }
            this$0.showNoInternetMessage();
            return;
        }
        this$0.showWatchDisconnectedDialog(this$0);
    }

    public final void F() {
        PermissionUtils.checkPermission(this, "android.permission.READ_CONTACTS", new ActivitySOS$getContactsPermission$1(this));
    }

    public final void G() {
        PermissionUtils.checkPermission(this, "android.permission.SEND_SMS", new ActivitySOS$getSMSPermission$1(this));
    }

    public final void N(String str) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_CONTACT_EDIT.getValue());
        analyticsLog.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(FirebaseEventParams.Description.CV_value.getValue(), str);
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void O(String str, String str2, String str3) {
        String contactNumber;
        String contactName;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(str);
        analyticsLog.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
        analyticsLog.setScreenName(str2);
        HashMap<String, String> hashMap = new HashMap<>();
        SOSData sOSData = this.s;
        if (sOSData != null && (contactName = sOSData.getContactName()) != null) {
            hashMap.put(FirebaseEventParams.Description.CV_CONTACT_SELECTED_NAME.getValue(), contactName);
        }
        SOSData sOSData2 = this.s;
        if (sOSData2 != null && (contactNumber = sOSData2.getContactNumber()) != null) {
            hashMap.put(FirebaseEventParams.Description.CV_CONTACT_SELECTED_NUMBER.getValue(), contactNumber);
        }
        hashMap.put(FirebaseEventParams.Description.CV_value.getValue(), str3);
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void P(boolean z) {
        String value;
        String string;
        String str;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_EMERGENCY_TOGGLE.getValue());
        analyticsLog.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EMERGENCY_SOS.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        if (z) {
            value = FirebaseEventParams.Description.CV_EMERGENCY_STATUS.getValue();
            string = getString(R.string.enabled);
            str = "getString(R.string.enabled)";
        } else {
            value = FirebaseEventParams.Description.CV_EMERGENCY_STATUS.getValue();
            string = getString(R.string.disabled);
            str = "getString(R.string.disabled)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        hashMap.put(value, lowerCase);
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void Q(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getWatchDetails(this));
        hashMap.putAll(companion.getDeviceId(this));
        companion.logAnalyticsEvent(str, hashMap);
    }

    public final void R() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), SOSCleverTapConstants.SOS_APP_PUSH.getValue());
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getWatchDetails(this));
        hashMap.putAll(companion.getDeviceId(this));
        companion.logAnalyticsEvent(SOSCleverTapConstants.BC_SOS_LANDINGPAGE_VIEWED.getValue(), hashMap);
    }

    public final void W() {
        String string = getString(R.string.change_contact);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.change_contact)");
        String string2 = getString(R.string.change_contact_desc);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.change_contact_desc)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.proceed);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.sos.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.X(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        String string4 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.sos.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.Y(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void Z(final boolean z, String str) {
        Window window;
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, str, false, true);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.sos.f
            @Override // java.lang.Runnable
            public final void run() {
                ActivitySOS.a0(CommonMessageDialog.this, z, this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
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

    public final void b0() {
        String string = getString(R.string.delete_emergency_contact);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_emergency_contact)");
        String string2 = getString(R.string.delete_contact_desc);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.delete_contact_desc)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.f5776no);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.no)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.sos.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.c0(ActivitySOS.this, bottomSheetDialogTwoButtons, view);
            }
        });
        String string4 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.yes)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.sos.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.d0(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void init() {
        SOSData sOSConfig = SessionManager.getInstance(this).getSOSConfig();
        this.s = sOSConfig;
        ActivitySosBinding activitySosBinding = null;
        if (sOSConfig != null) {
            ActivitySosBinding activitySosBinding2 = this.p;
            if (activitySosBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosBinding2 = null;
            }
            TextView textView = activitySosBinding2.itemSos.displayName;
            SOSData sOSData = this.s;
            Intrinsics.checkNotNull(sOSData);
            textView.setText(sOSData.getContactName());
            ActivitySosBinding activitySosBinding3 = this.p;
            if (activitySosBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosBinding3 = null;
            }
            TextView textView2 = activitySosBinding3.itemSos.displayNumber;
            SOSData sOSData2 = this.s;
            Intrinsics.checkNotNull(sOSData2);
            textView2.setText(sOSData2.getContactNumber());
        } else {
            startActivity(new Intent(this, ActivitySOSSettings.class));
            finish();
        }
        ActivitySosBinding activitySosBinding4 = this.p;
        if (activitySosBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding4 = null;
        }
        activitySosBinding4.itemSos.editContact.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.H(ActivitySOS.this, view);
            }
        });
        ActivitySosBinding activitySosBinding5 = this.p;
        if (activitySosBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding5 = null;
        }
        activitySosBinding5.itemSos.editSos.tvIcon1.setText(getString(R.string.delete_contact));
        ActivitySosBinding activitySosBinding6 = this.p;
        if (activitySosBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding6 = null;
        }
        activitySosBinding6.itemSos.editSos.tvIcon1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delete_sos, 0, 0, 0);
        ActivitySosBinding activitySosBinding7 = this.p;
        if (activitySosBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding7 = null;
        }
        activitySosBinding7.itemSos.editSos.tvIcon2.setText(getString(R.string.change_contact));
        ActivitySosBinding activitySosBinding8 = this.p;
        if (activitySosBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding8 = null;
        }
        activitySosBinding8.itemSos.editSos.tvIcon2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit_sos, 0, 0, 0);
        ActivitySosBinding activitySosBinding9 = this.p;
        if (activitySosBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding9 = null;
        }
        activitySosBinding9.itemSos.editSos.tvIcon3.setText(getString(R.string.notify));
        ActivitySosBinding activitySosBinding10 = this.p;
        if (activitySosBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding10 = null;
        }
        activitySosBinding10.itemSos.editSos.tvIcon3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notify, 0, 0, 0);
        ActivitySosBinding activitySosBinding11 = this.p;
        if (activitySosBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding11 = null;
        }
        activitySosBinding11.itemSos.editSos.tvIcon1.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.I(ActivitySOS.this, view);
            }
        });
        ActivitySosBinding activitySosBinding12 = this.p;
        if (activitySosBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding12 = null;
        }
        activitySosBinding12.itemSos.editSos.tvIcon2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.J(ActivitySOS.this, view);
            }
        });
        ActivitySosBinding activitySosBinding13 = this.p;
        if (activitySosBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosBinding = activitySosBinding13;
        }
        activitySosBinding.itemSos.editSos.tvIcon3.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.K(ActivitySOS.this, view);
            }
        });
    }

    public final void initToolbar() {
        ActivitySosBinding activitySosBinding = this.p;
        ActivitySosBinding activitySosBinding2 = null;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        TextView textView = (TextView) activitySosBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivitySosBinding activitySosBinding3 = this.p;
        if (activitySosBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding3 = null;
        }
        textView.setText(getString(R.string.emergency_sos));
        ((TextView) activitySosBinding3.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.L(ActivitySOS.this, view);
            }
        });
        ActivitySosBinding activitySosBinding4 = this.p;
        if (activitySosBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding4 = null;
        }
        View view = activitySosBinding4.toolbar;
        int i = R.id.toggle_btn;
        View findViewById = view.findViewById(i);
        Intrinsics.checkNotNullExpressionValue(findViewById, "binding.toolbar.findView…<Switch>(R.id.toggle_btn)");
        visible(findViewById);
        ActivitySosBinding activitySosBinding5 = this.p;
        if (activitySosBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding5 = null;
        }
        ((Switch) activitySosBinding5.toolbar.findViewById(i)).setButtonDrawable(getDrawable(R.drawable.switch_selector));
        ActivitySosBinding activitySosBinding6 = this.p;
        if (activitySosBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding6 = null;
        }
        Switch r0 = (Switch) activitySosBinding6.toolbar.findViewById(i);
        SOSData sOSData = this.s;
        r0.setChecked(sOSData != null ? Intrinsics.areEqual(sOSData.isSOSEnabled(), Boolean.TRUE) : false);
        if (this.s != null) {
            ActivitySosBinding activitySosBinding7 = this.p;
            if (activitySosBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosBinding7 = null;
            }
            TextView textView2 = activitySosBinding7.itemSos.displayName;
            SOSData sOSData2 = this.s;
            Intrinsics.checkNotNull(sOSData2);
            textView2.setText(sOSData2.getContactName());
            ActivitySosBinding activitySosBinding8 = this.p;
            if (activitySosBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosBinding8 = null;
            }
            TextView textView3 = activitySosBinding8.itemSos.displayNumber;
            SOSData sOSData3 = this.s;
            Intrinsics.checkNotNull(sOSData3);
            textView3.setText(sOSData3.getContactNumber());
        }
        ActivitySosBinding activitySosBinding9 = this.p;
        if (activitySosBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosBinding2 = activitySosBinding9;
        }
        ((Switch) activitySosBinding2.toolbar.findViewById(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.sos.e
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivitySOS.M(ActivitySOS.this, compoundButton, z);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySosBinding inflate = ActivitySosBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        SOSSettingsViewmodel sOSSettingsViewmodel = new SOSSettingsViewmodel(this);
        this.q = sOSSettingsViewmodel;
        sOSSettingsViewmodel.setMListener(this);
        ActivitySosBinding activitySosBinding = this.p;
        ActivitySosBinding activitySosBinding2 = null;
        if (activitySosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding = null;
        }
        setContentView(activitySosBinding.getRoot());
        init();
        initToolbar();
        G();
        Intent intent = getIntent();
        if ((intent != null ? intent.getData() : null) != null) {
            FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(FirebaseEventParams.ScreenName.SYSTEM_NOTIFICATION.getValue());
            R();
        }
        this.t = SessionManager.getInstance(this).getSOSContact();
        SpannableString spannableString = new SpannableString(getString(R.string.terms_and_conditions));
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        ActivitySosBinding activitySosBinding3 = this.p;
        if (activitySosBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding3 = null;
        }
        activitySosBinding3.tvSosTermsCondition.setText(spannableString);
        ActivitySosBinding activitySosBinding4 = this.p;
        if (activitySosBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding4 = null;
        }
        activitySosBinding4.tvSosTermsCondition.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.S(ActivitySOS.this, view);
            }
        });
        if (SessionManager.getInstance(this).shouldShowSOSTooltip()) {
            SessionManager.getInstance(this).saveShowSOSTooltip(false);
            ActivitySosBinding activitySosBinding5 = this.p;
            if (activitySosBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosBinding5 = null;
            }
            View root = activitySosBinding5.tooltip.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.tooltip.root");
            visible(root);
        }
        ActivitySosBinding activitySosBinding6 = this.p;
        if (activitySosBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding6 = null;
        }
        activitySosBinding6.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.T(ActivitySOS.this, view);
            }
        });
        ActivitySosBinding activitySosBinding7 = this.p;
        if (activitySosBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding7 = null;
        }
        activitySosBinding7.tooltip.title.setText(getString(R.string.notify_emergency_contact));
        ActivitySosBinding activitySosBinding8 = this.p;
        if (activitySosBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding8 = null;
        }
        activitySosBinding8.tooltip.message.setText(getString(R.string.notify_emergency_contact_desc));
        ActivitySosBinding activitySosBinding9 = this.p;
        if (activitySosBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding9 = null;
        }
        activitySosBinding9.tooltip.negativeBtn.setText(getString(R.string.f5776no));
        ActivitySosBinding activitySosBinding10 = this.p;
        if (activitySosBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding10 = null;
        }
        activitySosBinding10.tooltip.positiveBtn.setText(getString(R.string.yes));
        ActivitySosBinding activitySosBinding11 = this.p;
        if (activitySosBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosBinding11 = null;
        }
        activitySosBinding11.tooltip.negativeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.U(ActivitySOS.this, view);
            }
        });
        ActivitySosBinding activitySosBinding12 = this.p;
        if (activitySosBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosBinding2 = activitySosBinding12;
        }
        activitySosBinding2.tooltip.positiveBtn.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS.V(ActivitySOS.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        dismissProgress();
        if (str != null) {
            Z(true, str);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        SOSSettingsViewmodel sOSSettingsViewmodel = null;
        if (i == this.v) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0 && this.t == null) {
                SOSData sOSData = this.s;
                if ((sOSData != null ? sOSData.getContactName() : null) != null) {
                    F();
                }
            }
        }
        if (i == this.w) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0 && this.t == null) {
                SOSData sOSData2 = this.s;
                if ((sOSData2 != null ? sOSData2.getContactName() : null) != null) {
                    SOSSettingsViewmodel sOSSettingsViewmodel2 = this.q;
                    if (sOSSettingsViewmodel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        sOSSettingsViewmodel = sOSSettingsViewmodel2;
                    }
                    sOSSettingsViewmodel.getSOSConfigSettings();
                }
            }
        }
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        dismissProgress();
        if (!this.u) {
            String string = getString(R.string.sos_contact_deleted_successfully);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sos_c…act_deleted_successfully)");
            Z(false, string);
            return;
        }
        String string2 = getString(R.string.sos_settings_saved_successfully);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sos_s…tings_saved_successfully)");
        Z(true, string2);
    }
}
