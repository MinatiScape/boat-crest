package com.coveiot.android.leonardo.more.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.listeners.SuccessNotificationTroubleshoot;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.utils.utility.AppUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentTroubleshootNotification extends BaseFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    public SuccessNotificationTroubleshoot m;
    public long n;
    public boolean o;
    public boolean p;
    @Nullable
    public BottomSheetDialogTwoButtons q;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage r;

    public FragmentTroubleshootNotification(@NotNull SuccessNotificationTroubleshoot listenr) {
        Intrinsics.checkNotNullParameter(listenr, "listenr");
        this._$_findViewCache = new LinkedHashMap();
        this.m = listenr;
        this.n = -1L;
    }

    public static /* synthetic */ void F(FragmentTroubleshootNotification fragmentTroubleshootNotification, boolean z, BleBaseError bleBaseError, int i, Object obj) {
        if ((i & 2) != 0) {
            bleBaseError = null;
        }
        fragmentTroubleshootNotification.E(z, bleBaseError);
    }

    public static final void s(FragmentTroubleshootNotification this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), ActivityBandSettings.class));
        this$0.D();
    }

    public static final void t(FragmentTroubleshootNotification this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), ActivityNotifications.class));
        this$0.D();
    }

    public static final void u(FragmentTroubleshootNotification this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), ActivityNotifications.class));
        this$0.D();
    }

    public static final void v(FragmentTroubleshootNotification this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), ActivityNotifications.class));
        this$0.D();
    }

    public static final void w(FragmentTroubleshootNotification this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            this$0.H();
        } else {
            this$0.I();
        }
    }

    public final void A() {
        String string = getString(R.string.if_notfication_is_toggled_on);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.if_notfication_is_toggled_on)");
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(requireContext().getColor(R.color.colorAccent)), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Settings", 0, false, 6, (Object) null), string.length(), 34);
        spannableString.setSpan(new UnderlineSpan(), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Settings", 0, false, 6, (Object) null), string.length(), 34);
        spannableString.setSpan(new ClickableSpan() { // from class: com.coveiot.android.leonardo.more.activities.FragmentTroubleshootNotification$loadSettingsNavigationTextView$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                FragmentTroubleshootNotification.this.startActivity(new Intent(AppConstants.NOTIFICATION_SETTING.getValue()));
            }
        }, StringsKt__StringsKt.indexOf$default((CharSequence) string, "Settings", 0, false, 6, (Object) null), string.length(), 34);
        int i = R.id.tv_notice_2;
        ((TextView) _$_findCachedViewById(i)).setText(spannableString, TextView.BufferType.SPANNABLE);
        ((TextView) _$_findCachedViewById(i)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    public final void B() {
        z();
        y();
        C();
        x();
        loadSendButtonStatus();
    }

    public final void C() {
        UserDataManager userDataManager = UserDataManager.getInstance(requireContext());
        if (!(userDataManager.getNotificationsData() != null && userDataManager.getNotificationsData().isSms_notifications())) {
            ((TextView) _$_findCachedViewById(R.id.tv_sms_notification_status)).setText(R.string.disabled);
            _$_findCachedViewById(R.id.divider3).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.sms_noti_desc)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.sms_notification_allowBtn)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.cl_sms_notification)).setBackgroundResource(R.drawable.bg_notification_config_0);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_sms_notification_status)).setText(R.string.enabled);
        _$_findCachedViewById(R.id.divider3).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.sms_noti_desc)).setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.sms_notification_allowBtn)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.cl_sms_notification)).setBackgroundResource(R.drawable.bg_notification_config_1);
    }

    public final void D() {
        if (this.o) {
            return;
        }
        this.o = true;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_NOTIF_TROUBLESHOOT_START.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOTING_NOTIFICATIONS.getValue());
        if (this.p) {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOT_AND_FAQS.getValue());
        } else {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.HELP_FEEDBACK_SCREEN.getValue());
        }
        HashMap<String, String> hashMap = new HashMap<>();
        UserDataManager userDataManager = UserDataManager.getInstance(requireContext());
        DoNotDisturbData doNotDisturbData = userDataManager.getDoNotDisturbData();
        boolean z = false;
        boolean z2 = doNotDisturbData != null && (doNotDisturbData.isDnd_on_off() || doNotDisturbData.isSchedule_on_off());
        boolean z3 = userDataManager.getNotificationsData() != null && userDataManager.getNotificationsData().isCall_notifications();
        boolean z4 = userDataManager.getNotificationsData() != null && userDataManager.getNotificationsData().isSms_notifications();
        if (userDataManager.getNotificationsData() != null && userDataManager.getNotificationsData().isApp_notifications() && !AppUtils.isEmpty(userDataManager.getAppNotificationsData())) {
            for (AppNotificationData appNotificationData : userDataManager.getAppNotificationsData()) {
                if (appNotificationData.getChecked()) {
                    z = true;
                }
            }
        }
        hashMap.put(FirebaseEventParams.MetaData.CV_DND_ACTIVE.getValue(), String.valueOf(z2));
        hashMap.put(FirebaseEventParams.MetaData.CV_NOTIF_SMS_ACTIVE.getValue(), String.valueOf(z4));
        hashMap.put(FirebaseEventParams.MetaData.CV_NOTIF_CALL_ACTIVE.getValue(), String.valueOf(z3));
        hashMap.put(FirebaseEventParams.MetaData.CV_NOTIF_APP_ACTIVE.getValue(), String.valueOf(z));
        hashMap.put(FirebaseEventParams.MetaData.CV_PERMIS_NOTIF_ACCESS.getValue(), q().toString());
        hashMap.put(FirebaseEventParams.MetaData.CV_PERMIS_CALL.getValue(), p().toString());
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void E(boolean z, BleBaseError bleBaseError) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_NOTIF_SEND_TEST_MSG.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOTING_NOTIFICATIONS.getValue());
        if (this.p) {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOT_AND_FAQS.getValue());
        } else {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.HELP_FEEDBACK_SCREEN.getValue());
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(FirebaseEventParams.MetaData.CV_DVC_CONN_STATUS.getValue(), BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED ? "connected" : "disconnected");
        hashMap.put(FirebaseEventParams.MetaData.CV_TIME_SPENT_MILLS.getValue(), String.valueOf(System.currentTimeMillis() - this.n));
        int i = UserDataManager.getInstance(requireContext()).getBatteryLevelData().batteryLevel;
        String value = FirebaseEventParams.MetaData.CV_DVC_BATTERY_LEVEL.getValue();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.02f", Arrays.copyOf(new Object[]{Float.valueOf(i / 100.0f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        hashMap.put(value, format);
        String value2 = FirebaseEventParams.MetaData.CV_PHONE_BATTERY_LEVEL.getValue();
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.put(value2, payUtils.getPhoneBatteryLevel(requireContext));
        if (z) {
            hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "ok");
        } else {
            hashMap.put(FirebaseEventParams.MetaData.CV_STATUS.getValue(), "error");
            Integer errorCode = bleBaseError != null ? bleBaseError.getErrorCode() : null;
            Intrinsics.checkNotNull(errorCode);
            if (payUtils.getSyncErrorType(errorCode) != null) {
                String value3 = FirebaseEventParams.MetaData.CV_ERROR.getValue();
                Integer errorCode2 = bleBaseError != null ? bleBaseError.getErrorCode() : null;
                Intrinsics.checkNotNull(errorCode2);
                String syncErrorType = payUtils.getSyncErrorType(errorCode2);
                Intrinsics.checkNotNull(syncErrorType);
                hashMap.put(value3, syncErrorType);
            }
        }
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void G(boolean z) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_NOTIF_TEST_MSG_RECEIVED.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOTING_NOTIFICATIONS.getValue());
        if (this.p) {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOT_AND_FAQS.getValue());
        } else {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.HELP_FEEDBACK_SCREEN.getValue());
        }
        analyticsLog.setCVValue(z ? "yes" : "no");
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void H() {
        BaseFragment.showProgress$default(this, false, 1, null);
        this.n = System.currentTimeMillis();
        r(new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.activities.FragmentTroubleshootNotification$sendTestMsgNotification$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                FragmentTroubleshootNotification.this.E(false, error);
                if (FragmentTroubleshootNotification.this.isAdded()) {
                    FragmentTroubleshootNotification.this.dismissProgress();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                SetMessageContentRequest setMessageContentReq = new SetMessageContentRequest.Builder().setNotificationType(NotificationType.SMS, AppConstants.TEST_SMS_CONTENT.getValue(), AppConstants.TEST_SMS_TITLE.getValue()).build();
                BleApi bleApi = BleApiManager.getInstance(FragmentTroubleshootNotification.this.requireContext()).getBleApi();
                Intrinsics.checkNotNullExpressionValue(setMessageContentReq, "setMessageContentReq");
                final FragmentTroubleshootNotification fragmentTroubleshootNotification = FragmentTroubleshootNotification.this;
                bleApi.setUserSettings(setMessageContentReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.activities.FragmentTroubleshootNotification$sendTestMsgNotification$1$onSettingsResponse$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        FragmentTroubleshootNotification.this.E(false, error);
                        if (FragmentTroubleshootNotification.this.isAdded()) {
                            Toast.makeText(FragmentTroubleshootNotification.this.requireContext(), FragmentTroubleshootNotification.this.getString(R.string.error_there_seems_something_went_wrong), 0).show();
                            FragmentTroubleshootNotification.this.dismissProgress();
                        }
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                        Intrinsics.checkNotNullParameter(response2, "response");
                        if (FragmentTroubleshootNotification.this.isAdded()) {
                            FragmentTroubleshootNotification.this.dismissProgress();
                            FragmentTroubleshootNotification.this.J();
                            FragmentTroubleshootNotification.F(FragmentTroubleshootNotification.this, true, null, 2, null);
                        }
                    }
                });
            }
        });
    }

    public final void I() {
        if (this.r == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.watch_connection);
            Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …nection\n                )");
            String string2 = getString(R.string.make_sure_watch_connected);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …nnected\n                )");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
            this.r = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.FragmentTroubleshootNotification$showBandDisconnectedDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonOneTitle = FragmentTroubleshootNotification.this.getBottomSheetDialogOneButtonOneTitle();
                    Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle);
                    bottomSheetDialogOneButtonOneTitle.dismiss();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    public final void J() {
        if (this.q == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.troubleshoot);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshoot)");
            String string2 = getString(R.string.did_you_receive_test_notification);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.did_y…eceive_test_notification)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
            this.q = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.FragmentTroubleshootNotification$showConfirmationDialogToTheUser$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    FragmentTroubleshootNotification.this.G(true);
                    BottomSheetDialogTwoButtons bottomSheetDialogImageTitleMessageTwoBtns = FragmentTroubleshootNotification.this.getBottomSheetDialogImageTitleMessageTwoBtns();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
                    bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
                    FragmentTroubleshootNotification.this.getListenr().successTroubleshoot(true);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.FragmentTroubleshootNotification$showConfirmationDialogToTheUser$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogTwoButtons bottomSheetDialogImageTitleMessageTwoBtns = FragmentTroubleshootNotification.this.getBottomSheetDialogImageTitleMessageTwoBtns();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
                    bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
                    FragmentTroubleshootNotification.this.G(false);
                    AppNavigator.Companion companion = AppNavigator.Companion;
                    Context requireContext2 = FragmentTroubleshootNotification.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    companion.navigationToNotificationFeedbackScreen(requireContext2);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
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
    public final BottomSheetDialogTwoButtons getBottomSheetDialogImageTitleMessageTwoBtns() {
        return this.q;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonOneTitle() {
        return this.r;
    }

    @NotNull
    public final SuccessNotificationTroubleshoot getListenr() {
        return this.m;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.dnd_allowBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.qk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentTroubleshootNotification.s(FragmentTroubleshootNotification.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.call_notification_allowBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.nk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentTroubleshootNotification.t(FragmentTroubleshootNotification.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.sms_notification_allowBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ok
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentTroubleshootNotification.u(FragmentTroubleshootNotification.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.app_notification_allowBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.rk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentTroubleshootNotification.v(FragmentTroubleshootNotification.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_send_test_notification)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.pk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentTroubleshootNotification.w(FragmentTroubleshootNotification.this, view);
            }
        });
    }

    public final void loadSendButtonStatus() {
        boolean z;
        UserDataManager userDataManager = UserDataManager.getInstance(requireContext());
        DoNotDisturbData doNotDisturbData = userDataManager.getDoNotDisturbData();
        boolean z2 = true;
        boolean z3 = doNotDisturbData != null && (doNotDisturbData.isDnd_on_off() || doNotDisturbData.isSchedule_on_off());
        boolean z4 = userDataManager.getNotificationsData() != null && userDataManager.getNotificationsData().isCall_notifications();
        boolean z5 = userDataManager.getNotificationsData() != null && userDataManager.getNotificationsData().isSms_notifications();
        if (userDataManager.getNotificationsData() == null || !userDataManager.getNotificationsData().isApp_notifications() || AppUtils.isEmpty(userDataManager.getAppNotificationsData())) {
            z = false;
        } else {
            z = false;
            for (AppNotificationData appNotificationData : userDataManager.getAppNotificationsData()) {
                if (appNotificationData.getChecked()) {
                    z = true;
                }
            }
        }
        ((Button) _$_findCachedViewById(R.id.btn_send_test_notification)).setEnabled((!z3 && z4 && z5 && z) ? false : false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_troubleshoot_notification, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        B();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        A();
        initClickListeners();
    }

    public final String p() {
        int checkCallingOrSelfPermission = requireActivity().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") & requireActivity().checkCallingOrSelfPermission("android.permission.READ_CONTACTS") & requireActivity().checkCallingOrSelfPermission("android.permission.READ_CALL_LOG") & requireActivity().checkCallingOrSelfPermission("android.permission.CALL_PHONE");
        if (Build.VERSION.SDK_INT >= 28) {
            checkCallingOrSelfPermission = requireActivity().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") & requireActivity().checkCallingOrSelfPermission("android.permission.READ_CONTACTS") & requireActivity().checkCallingOrSelfPermission("android.permission.READ_CALL_LOG") & requireActivity().checkCallingOrSelfPermission("android.permission.ANSWER_PHONE_CALLS");
        }
        return checkCallingOrSelfPermission == 0 ? "allowed" : "denied";
    }

    public final String q() {
        String string = Settings.Secure.getString(requireActivity().getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
        if (string != null) {
            String packageName = requireContext().getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "requireContext().packageName");
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                return "allowed";
            }
        }
        return "denied";
    }

    public final void r(final SettingsResultListener settingsResultListener) {
        BleApiManager.getInstance(requireContext()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.FragmentTroubleshootNotification$fetchBatteryLevel$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SettingsResultListener.this.onSettingsError(error);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof BatteryLevelResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                    UserDataManager userDataManager = UserDataManager.getInstance(this.requireContext());
                    Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                    Intrinsics.checkNotNull(batteryLevel);
                    userDataManager.saveBatteryLevelData(new BatteryLevelData(batteryLevel.intValue(), System.currentTimeMillis()));
                    SettingsResultListener.this.onSettingsResponse(response);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void setBottomSheetDialogImageTitleMessageTwoBtns(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.q = bottomSheetDialogTwoButtons;
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.r = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setListenr(@NotNull SuccessNotificationTroubleshoot successNotificationTroubleshoot) {
        Intrinsics.checkNotNullParameter(successNotificationTroubleshoot, "<set-?>");
        this.m = successNotificationTroubleshoot;
    }

    public final void x() {
        boolean z;
        UserDataManager userDataManager = UserDataManager.getInstance(requireContext());
        if (userDataManager.getNotificationsData() == null || !userDataManager.getNotificationsData().isApp_notifications() || AppUtils.isEmpty(userDataManager.getAppNotificationsData())) {
            z = false;
        } else {
            z = false;
            for (AppNotificationData appNotificationData : userDataManager.getAppNotificationsData()) {
                if (appNotificationData.getChecked()) {
                    z = true;
                }
            }
        }
        if (!z) {
            ((TextView) _$_findCachedViewById(R.id.tv_app_notification_status)).setText(R.string.disabled);
            _$_findCachedViewById(R.id.divider5).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.app_noti_desc)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.app_notification_allowBtn)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.cl_app_notification)).setBackgroundResource(R.drawable.bg_notification_config_0);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_app_notification_status)).setText(R.string.enabled);
        _$_findCachedViewById(R.id.divider5).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.app_noti_desc)).setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.app_notification_allowBtn)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.cl_app_notification)).setBackgroundResource(R.drawable.bg_notification_config_1);
    }

    public final void y() {
        UserDataManager userDataManager = UserDataManager.getInstance(requireContext());
        if (!(userDataManager.getNotificationsData() != null && userDataManager.getNotificationsData().isCall_notifications())) {
            ((TextView) _$_findCachedViewById(R.id.tv_call_notification_status)).setText(R.string.disabled);
            _$_findCachedViewById(R.id.divider2).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.call_noti_desc)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.call_notification_allowBtn)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.cl_call_notification)).setBackgroundResource(R.drawable.bg_notification_config_0);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_call_notification_status)).setText(R.string.enabled);
        _$_findCachedViewById(R.id.divider2).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.call_noti_desc)).setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.call_notification_allowBtn)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.cl_call_notification)).setBackgroundResource(R.drawable.bg_notification_config_1);
    }

    public final void z() {
        DoNotDisturbData doNotDisturbData = UserDataManager.getInstance(requireContext()).getDoNotDisturbData();
        if (doNotDisturbData != null && (doNotDisturbData.isDnd_on_off() || doNotDisturbData.isSchedule_on_off())) {
            ((TextView) _$_findCachedViewById(R.id.tv_dnd_status)).setText(R.string.enabled);
            _$_findCachedViewById(R.id.divider).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.dnd_desc_txt)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.dnd_allowBtn)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.cl_dnd)).setBackgroundResource(R.drawable.bg_notification_config_0);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_dnd_status)).setText(R.string.disabled);
        _$_findCachedViewById(R.id.divider).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.dnd_desc_txt)).setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.dnd_allowBtn)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.cl_dnd)).setBackgroundResource(R.drawable.bg_notification_config_1);
    }
}
