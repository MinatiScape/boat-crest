package com.clevertap.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.inapp.AlertDialogPromptForSettings;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* loaded from: classes2.dex */
public class PushPermissionManager {
    public static final String ANDROID_PERMISSION_STRING = "android.permission.POST_NOTIFICATIONS";

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapInstanceConfig f2584a;
    public boolean b;
    public final Activity c;
    public boolean d = false;

    public PushPermissionManager(Activity activity, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.c = activity;
        this.f2584a = cleverTapInstanceConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit c() {
        Utils.navigateToAndroidSettingsForNotifications(this.c);
        this.d = true;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit d() {
        Activity activity = this.c;
        if (activity instanceof InAppNotificationActivity) {
            ((InAppNotificationActivity) activity).f(null);
        }
        return Unit.INSTANCE;
    }

    public final boolean e() {
        return this.b;
    }

    public boolean isFromNotificationSettingsActivity() {
        return this.d;
    }

    @RequiresApi(api = 33)
    public void requestPermission(InAppNotificationActivity.PushPermissionResultCallback pushPermissionResultCallback) {
        if (ContextCompat.checkSelfPermission(this.c, ANDROID_PERMISSION_STRING) == -1) {
            boolean isFirstTimeRequest = CTPreferenceCache.getInstance(this.c, this.f2584a).isFirstTimeRequest();
            Activity currentActivity = CoreMetaData.getCurrentActivity();
            if (currentActivity == null) {
                Logger.d("CurrentActivity reference is null. SDK can't prompt the user with Notification Permission! Ensure the following things:\n1. Calling ActivityLifecycleCallback.register(this) in your custom application class before super.onCreate().\n   Alternatively, register CleverTap SDK's Application class in the manifest using com.clevertap.android.sdk.Application.\n2. Ensure that the promptPushPrimer() API is called from the onResume() lifecycle method, not onCreate().");
                return;
            }
            boolean shouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, ANDROID_PERMISSION_STRING);
            if (!isFirstTimeRequest && shouldShowRequestPermissionRationale && e()) {
                showFallbackAlertDialog();
                return;
            } else {
                ActivityCompat.requestPermissions(this.c, new String[]{ANDROID_PERMISSION_STRING}, 102);
                return;
            }
        }
        pushPermissionResultCallback.onPushPermissionAccept();
        Activity activity = this.c;
        if (activity instanceof InAppNotificationActivity) {
            ((InAppNotificationActivity) activity).f(null);
        }
    }

    public void showFallbackAlertDialog() {
        AlertDialogPromptForSettings.show(this.c, new Function0() { // from class: com.clevertap.android.sdk.p
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit c;
                c = PushPermissionManager.this.c();
                return c;
            }
        }, new Function0() { // from class: com.clevertap.android.sdk.o
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit d;
                d = PushPermissionManager.this.d();
                return d;
            }
        });
    }

    @SuppressLint({"NewApi"})
    public void showHardPermissionPrompt(boolean z, InAppNotificationActivity.PushPermissionResultCallback pushPermissionResultCallback) {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.c, 32)) {
            this.b = z;
            requestPermission(pushPermissionResultCallback);
        }
    }
}
