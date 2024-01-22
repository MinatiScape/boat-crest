package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import android.content.res.Resources;
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.remotecommandframework.alexa.handler.INotificationPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SNotificationInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.NotificationSettings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class NotificationPreferenceHandler implements INotificationPreferenceHandler {
    public final List<AppNotificationData> a(Context context) {
        String[] stringArray;
        String[] stringArray2;
        ArrayList arrayList = new ArrayList();
        Resources resources = context.getResources();
        int i = R.array.app_package_list_1860;
        Intrinsics.checkNotNullExpressionValue(resources.getStringArray(i), "context.resources.getStr…ay.app_package_list_1860)");
        Resources resources2 = context.getResources();
        int i2 = R.array.app_name_list_1860;
        Intrinsics.checkNotNullExpressionValue(resources2.getStringArray(i2), "context.resources.getStr…array.app_name_list_1860)");
        if (!m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1790_device), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1810g_device), false)) {
            if (!m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963d_device), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963yh_device), false)) {
                String deviceType = SessionManager.getInstance(context).getDeviceType();
                Resources resources3 = context.getResources();
                int i3 = R.string.j1860_device;
                if (!m.equals(deviceType, resources3.getString(i3), false)) {
                    if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(i3), false)) {
                        stringArray = context.getResources().getStringArray(i);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1860)");
                        stringArray2 = context.getResources().getStringArray(i2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1860)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smaf2_device), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smas12_device), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_genesis_pro), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_elevate_pro), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_glory_pro), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_ultima_vogue), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_seek), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_comet), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_velocity), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_sma_f2)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_sma_f2);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_sma_f2)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangy20_device), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_moyang);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_moyang)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_moyang);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_moyang)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.matrix_device), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_matrix);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….app_package_list_matrix)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_matrix);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ray.app_name_list_matrix)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangygpf5_device), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_moyang_gpf5);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…package_list_moyang_gpf5)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_moyang_gpf5);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…pp_name_list_moyang_gpf5)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca0), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_ca);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.app_package_list_ca)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_ca);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…R.array.app_name_list_ca)");
                    } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false)) {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_ca);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.app_package_list_ca)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_ca);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…R.array.app_name_list_ca)");
                    } else if (!m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_primia_voice), false)) {
                        if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca2), false)) {
                            stringArray = context.getResources().getStringArray(R.array.app_package_list_ca);
                            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.app_package_list_ca)");
                            stringArray2 = context.getResources().getStringArray(R.array.app_name_list_ca);
                            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…R.array.app_name_list_ca)");
                        } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_select), false)) {
                            stringArray = context.getResources().getStringArray(R.array.app_package_list_ido);
                            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ray.app_package_list_ido)");
                            stringArray2 = context.getResources().getStringArray(R.array.app_name_list_ido);
                            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr….array.app_name_list_ido)");
                        } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_connect), false)) {
                            stringArray = context.getResources().getStringArray(R.array.app_package_list_ido);
                            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ray.app_package_list_ido)");
                            stringArray2 = context.getResources().getStringArray(R.array.app_name_list_ido);
                            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr….array.app_name_list_ido)");
                        } else if (!m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_wave_cosmos), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_beat), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_play), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_style), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc3_wave_smart), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) && !m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false)) {
                            stringArray = context.getResources().getStringArray(R.array.app_package_list);
                            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…R.array.app_package_list)");
                            stringArray2 = context.getResources().getStringArray(R.array.app_name_list);
                            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ay(R.array.app_name_list)");
                        } else {
                            stringArray = context.getResources().getStringArray(R.array.app_package_list_ca);
                            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.app_package_list_ca)");
                            stringArray2 = context.getResources().getStringArray(R.array.app_name_list_ca);
                            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…R.array.app_name_list_ca)");
                        }
                    } else {
                        stringArray = context.getResources().getStringArray(R.array.app_package_list_ca);
                        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.app_package_list_ca)");
                        stringArray2 = context.getResources().getStringArray(R.array.app_name_list_ca);
                        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…R.array.app_name_list_ca)");
                    }
                }
            }
            stringArray = context.getResources().getStringArray(R.array.app_package_list_1963);
            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1963)");
            stringArray2 = context.getResources().getStringArray(R.array.app_name_list_1963);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1963)");
        } else {
            stringArray = context.getResources().getStringArray(R.array.app_package_list_1790);
            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.app_package_list_1790)");
            stringArray2 = context.getResources().getStringArray(R.array.app_name_list_1790);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…array.app_name_list_1790)");
        }
        int length = stringArray.length;
        for (int i4 = 0; i4 < length; i4++) {
            AppNotificationData appNotificationData = new AppNotificationData();
            appNotificationData.setPackageName(stringArray[i4]);
            appNotificationData.setChecked(false);
            appNotificationData.setAppName(stringArray2[i4]);
            arrayList.add(appNotificationData);
        }
        return arrayList;
    }

    public final void b(String str, Boolean bool, String str2, List<AppNotificationData> list) {
        if (bool != null) {
            bool.booleanValue();
            for (AppNotificationData appNotificationData : list) {
                if (Intrinsics.areEqual(appNotificationData.getPackageName(), str2)) {
                    appNotificationData.setChecked(bool.booleanValue());
                    return;
                }
            }
        }
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.INotificationPreferenceHandler
    public boolean isAlreadySet(@NotNull Context context, @NotNull SNotificationInfo sNotificationInfo) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sNotificationInfo, "sNotificationInfo");
        List<AppNotificationData> appNotificationsData = UserDataManager.getInstance(context).getAppNotificationsData();
        if ((appNotificationsData == null || appNotificationsData.isEmpty()) || sNotificationInfo.getOtherApps() == null) {
            return false;
        }
        SNotificationInfo.OtherApps otherApps = sNotificationInfo.getOtherApps();
        Intrinsics.checkNotNull(otherApps);
        List<SNotificationInfo.AppBean> apps = otherApps.getApps();
        if (apps == null || apps.isEmpty()) {
            return false;
        }
        SNotificationInfo.OtherApps otherApps2 = sNotificationInfo.getOtherApps();
        Intrinsics.checkNotNull(otherApps2);
        List<SNotificationInfo.AppBean> apps2 = otherApps2.getApps();
        Intrinsics.checkNotNull(apps2);
        boolean z2 = false;
        for (SNotificationInfo.AppBean appBean : apps2) {
            Iterator<AppNotificationData> it = appNotificationsData.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    continue;
                    break;
                }
                AppNotificationData next = it.next();
                String appId = appBean.getAppId();
                if (!(appId == null || appId.length() == 0) && appBean.getActive() != null) {
                    String appName = next.getAppName();
                    if (!(appName == null || appName.length() == 0)) {
                        next.getChecked();
                        String appId2 = appBean.getAppId();
                        Intrinsics.checkNotNull(appId2);
                        if (!m.equals(appId2, next.getAppName(), true)) {
                            continue;
                        } else if (!Intrinsics.areEqual(appBean.getActive(), Boolean.valueOf(next.getChecked()))) {
                            z = true;
                            z2 = false;
                            continue;
                            break;
                        } else {
                            z2 = true;
                        }
                    } else {
                        continue;
                    }
                }
            }
            if (z) {
                break;
            }
        }
        return z2;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.INotificationPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SNotificationInfo sNotificationInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sNotificationInfo, "sNotificationInfo");
        return true;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.INotificationPreferenceHandler
    public void update(@NotNull Context context, @NotNull SNotificationInfo sNotificationInfo, @NotNull SuccessListener successListener) {
        List<SNotificationInfo.AppBean> apps;
        String appId;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sNotificationInfo, "sNotificationInfo");
        Intrinsics.checkNotNullParameter(successListener, "successListener");
        NotificationSettings notificationsData = UserDataManager.getInstance(context).getNotificationsData();
        List<AppNotificationData> appNotificationsData = UserDataManager.getInstance(context).getAppNotificationsData();
        if (appNotificationsData == null) {
            appNotificationsData = a(context);
        }
        SNotificationInfo.CallBean call = sNotificationInfo.getCall();
        if (call != null) {
            notificationsData.setCall_notifications(Intrinsics.areEqual(call.getActive(), Boolean.TRUE));
        }
        SNotificationInfo.SmsBean sms = sNotificationInfo.getSms();
        if (sms != null) {
            notificationsData.setSms_notifications(Intrinsics.areEqual(sms.getActive(), Boolean.TRUE));
        }
        boolean z = true;
        if (sNotificationInfo.getOtherApps() != null) {
            notificationsData.setApp_notifications(true);
            SNotificationInfo.OtherApps otherApps = sNotificationInfo.getOtherApps();
            if (otherApps != null && (apps = otherApps.getApps()) != null && (!apps.isEmpty())) {
                for (SNotificationInfo.AppBean appBean : apps) {
                    if (appBean != null && (appId = appBean.getAppId()) != null) {
                        SNotificationInfo.Companion companion = SNotificationInfo.Companion;
                        if (Intrinsics.areEqual(appId, companion.getAPP_ID_TELEGRAM())) {
                            b(companion.getAPP_ID_TELEGRAM(), appBean.getActive(), companion.getPKG_ID_TELEGRAM(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_LINKEDIN())) {
                            b(companion.getAPP_ID_LINKEDIN(), appBean.getActive(), companion.getPKG_ID_LINKEDIN(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_FACEBOOK())) {
                            b(companion.getAPP_ID_FACEBOOK(), appBean.getActive(), companion.getPKG_ID_FACEBOOK(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_FACEBOOK_MESSENGER())) {
                            b(companion.getAPP_ID_FACEBOOK_MESSENGER(), appBean.getActive(), companion.getPKG_ID_FACEBOOK_MESSENGER(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_GMAIL())) {
                            b(companion.getAPP_ID_GMAIL(), appBean.getActive(), companion.getPKG_ID_GMAIL(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_INSTAGRAM())) {
                            b(companion.getAPP_ID_INSTAGRAM(), appBean.getActive(), companion.getPKG_ID_INSTAGRAM(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_QQ_MESSENGER())) {
                            b(companion.getAPP_ID_QQ_MESSENGER(), appBean.getActive(), companion.getPKG_ID_QQ_MESSENGER(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_SNAPCHAT())) {
                            b(companion.getAPP_ID_SNAPCHAT(), appBean.getActive(), companion.getPKG_ID_SNAPCHAT(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_TWITTER())) {
                            b(companion.getAPP_ID_TWITTER(), appBean.getActive(), companion.getPKG_ID_TWITTER(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_WECHAT())) {
                            b(companion.getAPP_ID_WECHAT(), appBean.getActive(), companion.getPKG_ID_WECHAT(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_WHATSAPP())) {
                            b(companion.getAPP_ID_WHATSAPP(), appBean.getActive(), companion.getPKG_ID_WHATSAPP(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_SKYPE())) {
                            b(companion.getAPP_ID_SKYPE(), appBean.getActive(), companion.getPKG_ID_SKYPE(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_QZONE())) {
                            b(companion.getAPP_ID_QZONE(), appBean.getActive(), companion.getPKG_ID_QZONE(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_VKCLIENT())) {
                            b(companion.getAPP_ID_VKCLIENT(), appBean.getActive(), companion.getPKG_ID_VKCLIENT(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_LINE_MESSENGER())) {
                            b(companion.getAPP_ID_LINE_MESSENGER(), appBean.getActive(), companion.getPKG_ID_LINE_MESSENGER(), appNotificationsData);
                        } else if (Intrinsics.areEqual(appId, companion.getAPP_ID_CALENDAR())) {
                            b(companion.getAPP_ID_CALENDAR(), appBean.getActive(), companion.getPKG_ID_CALENDAR(), appNotificationsData);
                        }
                    }
                }
            }
        }
        if (!(appNotificationsData == null || appNotificationsData.isEmpty())) {
            Iterator<AppNotificationData> it = appNotificationsData.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getChecked()) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (z) {
            notificationsData.setApp_notifications(false);
        }
        UserDataManager.getInstance(context).saveNotificationsSettings(notificationsData);
        UserDataManager.getInstance(context).saveAppNotificationsSettings(appNotificationsData);
        successListener.onSuccess();
    }
}
