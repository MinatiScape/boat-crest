package com.clevertap.android.sdk.pushnotification;

import android.content.Context;
import android.os.Bundle;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.interfaces.ActionButtonClickHandler;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes2.dex */
public class PushNotificationHandler implements ActionButtonClickHandler {

    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final PushNotificationHandler f2660a = new PushNotificationHandler();
    }

    public static NotificationHandler getPushNotificationHandler() {
        return b.f2660a;
    }

    public static boolean isForPushTemplates(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("pt_id");
        return (BleConst.GetDeviceTime.equals(string) || string == null || string.isEmpty()) ? false : true;
    }

    public final boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "signedcall".equals(bundle.getString("source"));
    }

    @Override // com.clevertap.android.sdk.interfaces.ActionButtonClickHandler
    public boolean onActionButtonClick(Context context, Bundle bundle, int i) {
        return false;
    }

    @Override // com.clevertap.android.sdk.interfaces.NotificationHandler
    public synchronized boolean onMessageReceived(Context context, Bundle bundle, String str) {
        bundle.putLong(Constants.OMR_INVOKE_TIME_IN_MILLIS, System.currentTimeMillis());
        CleverTapAPI globalInstance = CleverTapAPI.getGlobalInstance(context, PushNotificationUtil.getAccountIdFromNotificationBundle(bundle));
        if (CleverTapAPI.getNotificationInfo(bundle).fromCleverTap) {
            if (globalInstance != null) {
                CleverTapInstanceConfig config = globalInstance.getCoreState().getConfig();
                config.log(PushConstants.LOG_TAG, str + "received notification from CleverTap: " + bundle.toString());
                if (isForPushTemplates(bundle) && CleverTapAPI.getNotificationHandler() != null) {
                    CleverTapAPI.getNotificationHandler().onMessageReceived(context, bundle, str);
                } else if (a(bundle) && CleverTapAPI.getSignedCallNotificationHandler() != null) {
                    CleverTapAPI.getSignedCallNotificationHandler().onMessageReceived(context, bundle, str);
                } else {
                    globalInstance.renderPushNotificationOnCallerThread(new CoreNotificationRenderer(), context, bundle);
                }
            } else {
                Logger.d(PushConstants.LOG_TAG, str + "received notification from CleverTap: " + bundle.toString());
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" not renderning since cleverTapAPI is null");
                Logger.d(PushConstants.LOG_TAG, sb.toString());
            }
            return true;
        }
        return false;
    }

    @Override // com.clevertap.android.sdk.interfaces.NotificationHandler
    public boolean onNewToken(Context context, String str, String str2) {
        PushConstants.PushType pushType = PushConstants.PushType.FCM;
        if (str2.equals(pushType.getType())) {
            CleverTapAPI.tokenRefresh(context, str, pushType);
            return true;
        }
        PushConstants.PushType pushType2 = PushConstants.PushType.HPS;
        if (str2.equals(pushType2.getType())) {
            CleverTapAPI.tokenRefresh(context, str, pushType2);
            return true;
        }
        PushConstants.PushType pushType3 = PushConstants.PushType.XPS;
        if (str2.equals(pushType3.getType())) {
            CleverTapAPI.tokenRefresh(context, str, pushType3);
            return true;
        }
        return true;
    }

    public PushNotificationHandler() {
    }
}
