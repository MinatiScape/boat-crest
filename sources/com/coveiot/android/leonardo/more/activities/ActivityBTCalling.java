package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.BT3CallUtils;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.dashboard.health.activities.ActivityBtCallingInfo;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.model.OnWatchBT3EnabledEvent;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AclBT3LastReceivedConnectionStatus;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.squareup.otto.Subscribe;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBTCalling extends BaseActivity implements IBT3ConnectionChangeListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public BT3CallViewModel p;
    public DataSyncViewModel q;
    @Nullable
    public BottomSheetDialogImageTitleMessage r;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle s;

    public static final void A(ActivityBTCalling this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, ActivitySyncContacts.class));
    }

    public static final void B(ActivityBTCalling this$0, View view) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleApi bleApi = BleApiManager.getInstance(this$0).getBleApi();
        boolean z = true;
        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
            z = false;
        }
        BT3CallViewModel bT3CallViewModel = null;
        if (z) {
            BleApi bleApi2 = BleApiManager.getInstance(this$0).getBleApi();
            if ((bleApi2 != null ? bleApi2.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(this$0).getConnectedBTCallDeviceMac(), this$0);
                if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 10) {
                    if (!this$0.y()) {
                        BT3CallViewModel bT3CallViewModel2 = this$0.p;
                        if (bT3CallViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
                        } else {
                            bT3CallViewModel = bT3CallViewModel2;
                        }
                        bT3CallViewModel.connectToBT3Watch(false, false, BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isOneClickToConnectSupported());
                        return;
                    }
                    Toast.makeText(this$0, this$0.getString(R.string.syncing_please_wait), 0).show();
                    return;
                }
                Integer bTDeviceBondedState2 = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(this$0).getConnectedBTCallDeviceMac(), this$0);
                if (bTDeviceBondedState2 != null && bTDeviceBondedState2.intValue() == 11) {
                    Toast.makeText(this$0, this$0.getString(R.string.please_wait_pairing_is_already_in_progress), 0).show();
                    return;
                }
                Integer bTDeviceBondedState3 = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(this$0).getConnectedBTCallDeviceMac(), this$0);
                if (bTDeviceBondedState3 != null && bTDeviceBondedState3.intValue() == 12) {
                    AppUtils.openBluetoothSettings(this$0);
                    return;
                }
                return;
            }
            Toast.makeText(this$0, this$0.getString(R.string.band_disconnected), 0).show();
        } else if (DeviceUtils.Companion.isSmaJieieDevice(this$0)) {
            BleApi bleApi3 = BleApiManager.getInstance(this$0).getBleApi();
            if ((bleApi3 != null ? bleApi3.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                Integer bTDeviceBondedState4 = BT3Utils.getBTDeviceBondedState(BleApiManager.getInstance(this$0).getBleApi().getMacAddress(), this$0);
                if (bTDeviceBondedState4 != null && bTDeviceBondedState4.intValue() == 10) {
                    AppUtils.openBluetoothSettings(this$0);
                    return;
                }
                return;
            }
            Toast.makeText(this$0, this$0.getString(R.string.band_disconnected), 0).show();
        }
    }

    public static final void C(ActivityBTCalling this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.y()) {
            this$0.startActivity(new Intent(this$0, ActivityNotifications.class));
        } else {
            Toast.makeText(this$0, this$0.getString(R.string.syncing_please_wait), 0).show();
        }
    }

    public static final void D(ActivityBTCalling this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void E(ActivityBTCalling this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, ActivityBtCallingInfo.class));
    }

    public static final void G(ActivityBTCalling this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion.navigateToTroubleshootNotification$default(AppNavigator.Companion, this$0, false, 2, null);
    }

    public static final void H(ActivityBTCalling this$0) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing() || (bottomSheetDialogOneButtonOneTitle = this$0.s) == null) {
            return;
        }
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle == null || !bottomSheetDialogOneButtonOneTitle.isShowing()) {
            z = false;
        }
        if (z && (bottomSheetDialogOneButtonOneTitle2 = this$0.s) != null) {
            bottomSheetDialogOneButtonOneTitle2.dismiss();
        }
        this$0.s = null;
    }

    public static /* synthetic */ void N(ActivityBTCalling activityBTCalling, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        activityBTCalling.M(z);
    }

    public static final void Q(BottomSheetDialogImageTitleMessage dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final void F() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        if (!((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isKahaBTCallSupported()) ? false : true) && !DeviceUtils.Companion.isSmaJieieDevice(this)) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.bt_call_troubleshoot_cv)).setVisibility(8);
            ((LinearLayout) _$_findCachedViewById(R.id.cl_bt_calling_status)).setVisibility(8);
        } else {
            String macAddress = BleApiManager.getInstance(this).getBleApi().getMacAddress();
            BleApi bleApi2 = BleApiManager.getInstance(this).getBleApi();
            if ((bleApi2 == null || (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) ? false : true) {
                macAddress = UserDataManager.getInstance(this).getConnectedBTCallDeviceMac();
                Intrinsics.checkNotNullExpressionValue(macAddress, "getInstance(this@Activit….connectedBTCallDeviceMac");
            }
            ((LinearLayout) _$_findCachedViewById(R.id.cl_bt_calling_status)).setVisibility(0);
            int i = R.id.bt_call_troubleshoot_cv;
            ((ConstraintLayout) _$_findCachedViewById(i)).setVisibility(0);
            Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(macAddress, this);
            if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12) {
                BleApi bleApi3 = BleApiManager.getInstance(this).getBleApi();
                ConnectionStatus connectionStatus = bleApi3 != null ? bleApi3.getConnectionStatus() : null;
                ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
                if (connectionStatus == connectionStatus2) {
                    if (DeviceUtils.Companion.isOPPDevice(this)) {
                        AclBT3LastReceivedConnectionStatus aclBt3LastReceivedConnectionStatus = SessionManager.getInstance(this).getAclBt3LastReceivedConnectionStatus();
                        if (aclBt3LastReceivedConnectionStatus != null && (aclBt3LastReceivedConnectionStatus.getConnectionStatus() != connectionStatus2 || !Intrinsics.areEqual(aclBt3LastReceivedConnectionStatus.getMacAddress(), UserDataManager.getInstance(this).getConnectedBTCallDeviceMac()))) {
                            M(true);
                        } else {
                            L();
                        }
                    } else {
                        L();
                    }
                    String string = getString(R.string.during_an_incoming_if_watch_doesnot);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.durin…ncoming_if_watch_doesnot)");
                    SpannableString spannableString = new SpannableString(string);
                    ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBTCalling$initViews$1
                        @Override // android.text.style.ClickableSpan
                        public void onClick(@NotNull View view) {
                            Intrinsics.checkNotNullParameter(view, "view");
                        }

                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(@NotNull TextPaint ds) {
                            Intrinsics.checkNotNullParameter(ds, "ds");
                            super.updateDrawState(ds);
                            ds.setColor(ActivityBTCalling.this.getResources().getColor(R.color.color_d0021b));
                        }
                    };
                    String string2 = getResources().getString(R.string.troubleshoot_notification_with_quotes);
                    Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…notification_with_quotes)");
                    int indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) string, string2, 0, false, 6, (Object) null);
                    String string3 = getResources().getString(R.string.troubleshoot_notification_with_quotes);
                    Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…notification_with_quotes)");
                    spannableString.setSpan(clickableSpan, indexOf$default, StringsKt__StringsKt.indexOf$default((CharSequence) string, string3, 0, false, 6, (Object) null) + getResources().getString(R.string.troubleshoot_notification_with_quotes).length(), 33);
                    ((TextView) _$_findCachedViewById(R.id.bt_call_troubleshoot_tv1)).setText(spannableString);
                    ((ConstraintLayout) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityBTCalling.G(ActivityBTCalling.this, view);
                        }
                    });
                }
            }
            N(this, false, 1, null);
            String string4 = getString(R.string.during_an_incoming_if_watch_doesnot);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.durin…ncoming_if_watch_doesnot)");
            SpannableString spannableString2 = new SpannableString(string4);
            ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBTCalling$initViews$1
                @Override // android.text.style.ClickableSpan
                public void onClick(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(@NotNull TextPaint ds) {
                    Intrinsics.checkNotNullParameter(ds, "ds");
                    super.updateDrawState(ds);
                    ds.setColor(ActivityBTCalling.this.getResources().getColor(R.color.color_d0021b));
                }
            };
            String string22 = getResources().getString(R.string.troubleshoot_notification_with_quotes);
            Intrinsics.checkNotNullExpressionValue(string22, "resources.getString(R.st…notification_with_quotes)");
            int indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) string4, string22, 0, false, 6, (Object) null);
            String string32 = getResources().getString(R.string.troubleshoot_notification_with_quotes);
            Intrinsics.checkNotNullExpressionValue(string32, "resources.getString(R.st…notification_with_quotes)");
            spannableString2.setSpan(clickableSpan2, indexOf$default2, StringsKt__StringsKt.indexOf$default((CharSequence) string4, string32, 0, false, 6, (Object) null) + getResources().getString(R.string.troubleshoot_notification_with_quotes).length(), 33);
            ((TextView) _$_findCachedViewById(R.id.bt_call_troubleshoot_tv1)).setText(spannableString2);
            ((ConstraintLayout) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityBTCalling.G(ActivityBTCalling.this, view);
                }
            });
        }
        int maxContactsInOneRequest = BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().getMaxContactsInOneRequest();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string5 = getString(R.string.add_max_20_contacts_to_your_watch_contact_directory);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.add_m…_watch_contact_directory)");
        String format = String.format(string5, Arrays.copyOf(new Object[]{Integer.valueOf(maxContactsInOneRequest)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.max_allowed_txt)).setText(format);
    }

    public final void I() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.r;
        boolean z = true;
        if (bottomSheetDialogImageTitleMessage2 != null) {
            if ((bottomSheetDialogImageTitleMessage2 != null && bottomSheetDialogImageTitleMessage2.isShowing()) && (bottomSheetDialogImageTitleMessage = this.r) != null) {
                bottomSheetDialogImageTitleMessage.dismiss();
            }
            this.r = null;
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.s;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            if (bottomSheetDialogOneButtonOneTitle2 == null || !bottomSheetDialogOneButtonOneTitle2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetDialogOneButtonOneTitle = this.s) != null) {
                bottomSheetDialogOneButtonOneTitle.dismiss();
            }
            this.s = null;
        }
    }

    public final void J() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_call_notification_info)).setBackground(getDrawable(2131232696));
        int i = R.id.btn_allow;
        ((Button) _$_findCachedViewById(i)).setEnabled(true);
        ((Button) _$_findCachedViewById(i)).setVisibility(8);
        int i2 = R.id.tv_enabled;
        ((TextView) _$_findCachedViewById(i2)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i2)).setText(getString(R.string.disabled));
        ((TextView) _$_findCachedViewById(i2)).setTextColor(getColor(R.color.color_ff3038));
        ((TextView) _$_findCachedViewById(R.id.tv_bt3_call_notification_info)).setText(getString(R.string.call_notification));
    }

    public final void K() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_call_notification_info)).setBackground(getDrawable(2131232695));
        int i = R.id.btn_allow;
        ((Button) _$_findCachedViewById(i)).setVisibility(8);
        ((Button) _$_findCachedViewById(i)).setEnabled(false);
        int i2 = R.id.tv_enabled;
        ((TextView) _$_findCachedViewById(i2)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i2)).setText(getString(R.string.enabled));
        ((TextView) _$_findCachedViewById(i2)).setTextColor(getColor(R.color.color_03f2ac));
        ((TextView) _$_findCachedViewById(R.id.tv_bt3_call_notification_info)).setText(getString(R.string.call_notification));
    }

    public final void L() {
        ((ImageView) _$_findCachedViewById(R.id.img_bt_connection_status)).setImageResource(2131231948);
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_bt_pair)).setBackground(getDrawable(2131232695));
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_pairing_details)).setVisibility(0);
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_connection_details)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.rightConnectArrowImgv)).setVisibility(8);
        int i = R.id.btn_pair_now;
        ((Button) _$_findCachedViewById(i)).setVisibility(8);
        ((Button) _$_findCachedViewById(i)).setEnabled(false);
        int i2 = R.id.tv_connected;
        ((TextView) _$_findCachedViewById(i2)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.tv_bt3_pair_info)).setText(getString(R.string.watch_connected));
        ((TextView) _$_findCachedViewById(i2)).setTextColor(getColor(R.color.color_b3b3b3));
        String watchName = DeviceUtils.Companion.getWatchName(this);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.connected_to_);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connected_to_)");
        String format = String.format(string, Arrays.copyOf(new Object[]{watchName}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new ForegroundColorSpan(getColor(R.color.color_ff3038)), StringsKt__StringsKt.indexOf$default((CharSequence) format, watchName, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, watchName, 0, false, 6, (Object) null) + watchName.length(), 33);
        ((TextView) _$_findCachedViewById(i2)).setText(spannableString);
        ((TextView) _$_findCachedViewById(i2)).setMovementMethod(new LinkMovementMethod());
        BT3CallUtils bT3CallUtils = BT3CallUtils.INSTANCE;
        if (bT3CallUtils.isCallNotificationFeatureEnabled(this) && bT3CallUtils.isCallPermissionAvailable(this)) {
            K();
        } else {
            J();
        }
    }

    public final void M(boolean z) {
        ((ImageView) _$_findCachedViewById(R.id.img_bt_connection_status)).setImageResource(2131231950);
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_bt_pair)).setBackground(getDrawable(2131232696));
        int i = R.id.btn_pair_now;
        ((Button) _$_findCachedViewById(i)).setEnabled(true);
        ((Button) _$_findCachedViewById(i)).setVisibility(8);
        int i2 = R.id.tv_connected;
        ((TextView) _$_findCachedViewById(i2)).setVisibility(0);
        if (z) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_pairing_details)).setVisibility(8);
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_connection_details)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.rightConnectArrowImgv)).setVisibility(0);
            String pairedRemoteDeviceName = BT3Utils.getPairedRemoteDeviceName(UserDataManager.getInstance(this).getConnectedBTCallDeviceMac(), this);
            if (pairedRemoteDeviceName == null) {
                pairedRemoteDeviceName = DeviceUtils.Companion.getModelNumber(this);
            }
            ((TextView) _$_findCachedViewById(R.id.toReconnectTapOnTv)).setText(getString(R.string.to_reconnect_tap_on_, new Object[]{pairedRemoteDeviceName}));
            ((TextView) _$_findCachedViewById(R.id.tv_bt3_pair_info)).setText(getString(R.string.connect_your_watch));
        } else {
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_pairing_details)).setVisibility(0);
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_connection_details)).setVisibility(8);
            ((ImageView) _$_findCachedViewById(R.id.rightConnectArrowImgv)).setVisibility(8);
            ((Button) _$_findCachedViewById(i)).setText(getString(R.string.pair_now));
            ((TextView) _$_findCachedViewById(i2)).setText(getString(R.string.pair_now));
            ((TextView) _$_findCachedViewById(R.id.tv_bt3_pair_info)).setText(getString(R.string.pair_your_watch));
        }
        ((TextView) _$_findCachedViewById(i2)).setTextColor(getColor(R.color.color_ff3038));
        BT3CallUtils bT3CallUtils = BT3CallUtils.INSTANCE;
        if (bT3CallUtils.isCallNotificationFeatureEnabled(this) && bT3CallUtils.isCallPermissionAvailable(this)) {
            K();
        } else {
            J();
        }
    }

    public final void O(String str) {
        if (this.s == null) {
            this.s = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.s;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.setCancelable(false);
            Button yesButton = bottomSheetDialogOneButtonOneTitle.getYesButton();
            if (yesButton != null) {
                yesButton.setVisibility(8);
            }
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void P(String str, boolean z) {
        if (this.r == null) {
            Drawable drawable = getResources().getDrawable(z ? R.drawable.success_fw_icon : R.drawable.failure_round_icon);
            Intrinsics.checkNotNull(drawable);
            this.r = new BottomSheetDialogImageTitleMessage(this, drawable, str, "", true);
        }
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.r;
        if (bottomSheetDialogImageTitleMessage != null) {
            bottomSheetDialogImageTitleMessage.setCancelable(false);
            String string = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.r2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityBTCalling.Q(BottomSheetDialogImageTitleMessage.this, view);
                }
            });
            bottomSheetDialogImageTitleMessage.show();
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

    public final void initToolbar() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        View findViewById = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNull(findViewById);
        ((TextView) findViewById).setText(getString(R.string.bluetooth_calling));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBTCalling.D(ActivityBTCalling.this, view);
            }
        });
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        boolean z = true;
        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
            z = false;
        }
        if (z) {
            ((ImageView) findViewById(R.id.share_iv)).setVisibility(8);
            return;
        }
        ((ImageView) findViewById(R.id.share_iv)).setVisibility(0);
        ((ImageView) findViewById(R.id.share_iv)).setImageResource(R.drawable.info_icon_new);
        ((ImageView) findViewById(R.id.share_iv)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBTCalling.E(ActivityBTCalling.this, view);
            }
        });
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isIDODevice(this) || companion.isTouchELXDevice(this) || companion.isMatrixDevice(this) || companion.isEastApexDevice(this) || companion.isSmaJieieDevice(this)) {
            ((ImageView) findViewById(R.id.share_iv)).setVisibility(8);
        }
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Connecting() {
        if (isFinishing()) {
            return;
        }
        I();
        String modelNumber = DeviceUtils.Companion.getModelNumber(this);
        if (modelNumber == null) {
            modelNumber = "BT";
        }
        String string = getString(R.string.xtendpro3_connecting, new Object[]{modelNumber});
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.xtend…3_connecting, deviceName)");
        O(string);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.s2
            @Override // java.lang.Runnable
            public final void run() {
                ActivityBTCalling.H(ActivityBTCalling.this);
            }
        }, 45000L);
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3ConnectionFailure(@Nullable String str) {
        if (isFinishing()) {
            return;
        }
        if (str == null || str.length() == 0) {
            return;
        }
        I();
        P(str, false);
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Disconnected() {
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        BT3CallViewModel bT3CallViewModel = (BT3CallViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(BT3CallViewModel.class);
        this.p = bT3CallViewModel;
        if (bT3CallViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
            bT3CallViewModel = null;
        }
        bT3CallViewModel.setBT3ConnectionChangeListener(this);
        this.q = (DataSyncViewModel) ViewModelProviders.of(this, new com.coveiot.android.dashboard2.ViewModelFactory(this)).get(DataSyncViewModel.class);
        setContentView(R.layout.activity_bt_calling);
        initToolbar();
        F();
        z();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onInitialCheckFailed(@Nullable String str) {
        if (isFinishing()) {
            return;
        }
        if (str == null || str.length() == 0) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        super.onResume();
        CoveEventBusManager.getInstance().getEventBus().register(this);
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        if ((bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) ? false : true) {
            Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(this).getConnectedBTCallDeviceMac(), this);
            if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12) {
                BleApi bleApi2 = BleApiManager.getInstance(this).getBleApi();
                ConnectionStatus connectionStatus = bleApi2 != null ? bleApi2.getConnectionStatus() : null;
                ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
                if (connectionStatus == connectionStatus2) {
                    if (DeviceUtils.Companion.isOPPDevice(this)) {
                        AclBT3LastReceivedConnectionStatus aclBt3LastReceivedConnectionStatus = SessionManager.getInstance(this).getAclBt3LastReceivedConnectionStatus();
                        if (aclBt3LastReceivedConnectionStatus != null && (aclBt3LastReceivedConnectionStatus.getConnectionStatus() != connectionStatus2 || !Intrinsics.areEqual(aclBt3LastReceivedConnectionStatus.getMacAddress(), UserDataManager.getInstance(this).getConnectedBTCallDeviceMac()))) {
                            M(true);
                            return;
                        } else {
                            L();
                            return;
                        }
                    }
                    L();
                    return;
                }
            }
            N(this, false, 1, null);
        } else if (DeviceUtils.Companion.isSmaJieieDevice(this)) {
            Integer bTDeviceBondedState2 = BT3Utils.getBTDeviceBondedState(BleApiManager.getInstance(this).getBleApi().getMacAddress(), this);
            if (bTDeviceBondedState2 != null && bTDeviceBondedState2.intValue() == 12) {
                BleApi bleApi3 = BleApiManager.getInstance(this).getBleApi();
                if ((bleApi3 != null ? bleApi3.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    L();
                    return;
                }
            }
            N(this, false, 1, null);
        }
    }

    @Subscribe
    public final void onWatchBTEnableEventReceived(@NotNull OnWatchBT3EnabledEvent onWatchBT3EnabledEvent) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(onWatchBT3EnabledEvent, "onWatchBT3EnabledEvent");
        if (isFinishing()) {
            return;
        }
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        if ((bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) ? false : true) {
            if (onWatchBT3EnabledEvent.isEnabled()) {
                BleApi bleApi2 = BleApiManager.getInstance(this).getBleApi();
                if ((bleApi2 != null ? bleApi2.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    L();
                    I();
                }
            }
            N(this, false, 1, null);
            I();
        }
    }

    public final boolean y() {
        DataSyncViewModel dataSyncViewModel = this.q;
        if (dataSyncViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataSyncViewModel");
            dataSyncViewModel = null;
        }
        if (dataSyncViewModel.checkIsSyncing()) {
            Toast.makeText(this, getString(R.string.syncing_please_wait), 1).show();
            return true;
        }
        return false;
    }

    public final void z() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_sync_contacts)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBTCalling.A(ActivityBTCalling.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.cl_bt_calling_status)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.n2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBTCalling.B(ActivityBTCalling.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_call_notification_info)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBTCalling.C(ActivityBTCalling.this, view);
            }
        });
    }
}
