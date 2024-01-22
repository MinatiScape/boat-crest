package com.clevertap.android.sdk;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.clevertap.android.sdk.inapp.CTInAppBaseFullFragment;
import com.clevertap.android.sdk.inapp.CTInAppHtmlCoverFragment;
import com.clevertap.android.sdk.inapp.CTInAppHtmlHalfInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppHtmlInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeCoverFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeCoverImageFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialImageFragment;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inapp.CTInAppType;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.inapp.InAppListener;
import java.lang.ref.WeakReference;
import java.util.HashMap;
/* loaded from: classes2.dex */
public final class InAppNotificationActivity extends FragmentActivity implements InAppListener, DidClickForHardPermissionListener {
    public static boolean m = false;
    public CleverTapInstanceConfig h;
    public CTInAppNotification i;
    public WeakReference<InAppListener> j;
    public WeakReference<PushPermissionResultCallback> k;
    public PushPermissionManager l;

    /* loaded from: classes2.dex */
    public interface PushPermissionResultCallback {
        void onPushPermissionAccept();

        void onPushPermissionDeny();
    }

    /* loaded from: classes2.dex */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.NOTIFICATION_ID_TAG, InAppNotificationActivity.this.i.getCampaignId());
            bundle.putString(Constants.KEY_C2A, InAppNotificationActivity.this.i.getButtons().get(0).getText());
            InAppNotificationActivity.this.e(bundle, null);
            String actionUrl = InAppNotificationActivity.this.i.getButtons().get(0).getActionUrl();
            if (actionUrl == null) {
                if (!InAppNotificationActivity.this.i.isLocalInApp()) {
                    if (InAppNotificationActivity.this.i.getButtons().get(0).getType() != null && InAppNotificationActivity.this.i.getButtons().get(0).getType().equalsIgnoreCase(Constants.KEY_REQUEST_FOR_NOTIFICATION_PERMISSION)) {
                        InAppNotificationActivity inAppNotificationActivity = InAppNotificationActivity.this;
                        inAppNotificationActivity.showHardPermissionPrompt(inAppNotificationActivity.i.getButtons().get(0).isFallbackToSettings());
                        return;
                    }
                    InAppNotificationActivity.this.f(bundle);
                    return;
                }
                InAppNotificationActivity inAppNotificationActivity2 = InAppNotificationActivity.this;
                inAppNotificationActivity2.showHardPermissionPrompt(inAppNotificationActivity2.i.fallBackToNotificationSettings());
                return;
            }
            InAppNotificationActivity.this.h(actionUrl, bundle);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements DialogInterface.OnClickListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.NOTIFICATION_ID_TAG, InAppNotificationActivity.this.i.getCampaignId());
            bundle.putString(Constants.KEY_C2A, InAppNotificationActivity.this.i.getButtons().get(1).getText());
            InAppNotificationActivity.this.e(bundle, null);
            String actionUrl = InAppNotificationActivity.this.i.getButtons().get(1).getActionUrl();
            if (actionUrl == null) {
                if (InAppNotificationActivity.this.i.getButtons().get(1).getType() != null && InAppNotificationActivity.this.i.getButtons().get(1).getType().equalsIgnoreCase(Constants.KEY_REQUEST_FOR_NOTIFICATION_PERMISSION)) {
                    InAppNotificationActivity inAppNotificationActivity = InAppNotificationActivity.this;
                    inAppNotificationActivity.showHardPermissionPrompt(inAppNotificationActivity.i.getButtons().get(1).isFallbackToSettings());
                    return;
                }
                InAppNotificationActivity.this.f(bundle);
                return;
            }
            InAppNotificationActivity.this.h(actionUrl, bundle);
        }
    }

    /* loaded from: classes2.dex */
    public class c implements DialogInterface.OnClickListener {
        public c() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.NOTIFICATION_ID_TAG, InAppNotificationActivity.this.i.getCampaignId());
            bundle.putString(Constants.KEY_C2A, InAppNotificationActivity.this.i.getButtons().get(0).getText());
            InAppNotificationActivity.this.e(bundle, null);
            String actionUrl = InAppNotificationActivity.this.i.getButtons().get(0).getActionUrl();
            if (actionUrl != null) {
                InAppNotificationActivity.this.h(actionUrl, bundle);
            } else {
                InAppNotificationActivity.this.f(bundle);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class d implements DialogInterface.OnClickListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.NOTIFICATION_ID_TAG, InAppNotificationActivity.this.i.getCampaignId());
            bundle.putString(Constants.KEY_C2A, InAppNotificationActivity.this.i.getButtons().get(1).getText());
            InAppNotificationActivity.this.e(bundle, null);
            String actionUrl = InAppNotificationActivity.this.i.getButtons().get(1).getActionUrl();
            if (actionUrl != null) {
                InAppNotificationActivity.this.h(actionUrl, bundle);
            } else {
                InAppNotificationActivity.this.f(bundle);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class e implements DialogInterface.OnClickListener {
        public e() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.NOTIFICATION_ID_TAG, InAppNotificationActivity.this.i.getCampaignId());
            bundle.putString(Constants.KEY_C2A, InAppNotificationActivity.this.i.getButtons().get(2).getText());
            InAppNotificationActivity.this.e(bundle, null);
            String actionUrl = InAppNotificationActivity.this.i.getButtons().get(2).getActionUrl();
            if (actionUrl != null) {
                InAppNotificationActivity.this.h(actionUrl, bundle);
            } else {
                InAppNotificationActivity.this.f(bundle);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static /* synthetic */ class f {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2580a;

        static {
            int[] iArr = new int[CTInAppType.values().length];
            f2580a = iArr;
            try {
                iArr[CTInAppType.CTInAppTypeCoverHTML.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeInterstitialHTML.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeHalfInterstitialHTML.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeCover.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeInterstitial.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeHalfInterstitial.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeCoverImageOnly.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeInterstitialImageOnly.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeHalfInterstitialImageOnly.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2580a[CTInAppType.CTInAppTypeAlert.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public final CTInAppBaseFullFragment d() {
        AlertDialog alertDialog;
        CTInAppType inAppType = this.i.getInAppType();
        switch (f.f2580a[inAppType.ordinal()]) {
            case 1:
                return new CTInAppHtmlCoverFragment();
            case 2:
                return new CTInAppHtmlInterstitialFragment();
            case 3:
                return new CTInAppHtmlHalfInterstitialFragment();
            case 4:
                return new CTInAppNativeCoverFragment();
            case 5:
                return new CTInAppNativeInterstitialFragment();
            case 6:
                return new CTInAppNativeHalfInterstitialFragment();
            case 7:
                return new CTInAppNativeCoverImageFragment();
            case 8:
                return new CTInAppNativeInterstitialImageFragment();
            case 9:
                return new CTInAppNativeHalfInterstitialImageFragment();
            case 10:
                if (this.i.getButtons().size() > 0) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        alertDialog = new AlertDialog.Builder(this, 16974394).setCancelable(false).setTitle(this.i.getTitle()).setMessage(this.i.getMessage()).setPositiveButton(this.i.getButtons().get(0).getText(), new a()).create();
                        if (this.i.getButtons().size() == 2) {
                            alertDialog.setButton(-2, this.i.getButtons().get(1).getText(), new b());
                        }
                    } else {
                        alertDialog = new AlertDialog.Builder(this).setCancelable(false).setTitle(this.i.getTitle()).setMessage(this.i.getMessage()).setPositiveButton(this.i.getButtons().get(0).getText(), new c()).create();
                        if (this.i.getButtons().size() == 2) {
                            alertDialog.setButton(-2, this.i.getButtons().get(1).getText(), new d());
                        }
                    }
                    if (this.i.getButtons().size() > 2) {
                        alertDialog.setButton(-3, this.i.getButtons().get(2).getText(), new e());
                    }
                } else {
                    alertDialog = null;
                }
                if (alertDialog != null) {
                    alertDialog.show();
                    m = true;
                    g(null);
                    return null;
                }
                this.h.getLogger().debug("InAppNotificationActivity: Alert Dialog is null, not showing Alert InApp");
                return null;
            default:
                this.h.getLogger().verbose("InAppNotificationActivity: Unhandled InApp Type: " + inAppType);
                return null;
        }
    }

    @Override // com.clevertap.android.sdk.DidClickForHardPermissionListener
    public void didClickForHardPermissionWithFallbackSettings(boolean z) {
        showHardPermissionPrompt(z);
    }

    public void e(Bundle bundle, HashMap<String, String> hashMap) {
        InAppListener j = j();
        if (j != null) {
            j.inAppNotificationDidClick(this.i, bundle, hashMap);
        }
    }

    public void f(Bundle bundle) {
        if (m) {
            m = false;
        }
        finish();
        InAppListener j = j();
        if (j == null || getBaseContext() == null || this.i == null) {
            return;
        }
        j.inAppNotificationDidDismiss(getBaseContext(), this.i, bundle);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(17432576, 17432577);
    }

    public void g(Bundle bundle) {
        InAppListener j = j();
        if (j != null) {
            j.inAppNotificationDidShow(this.i, bundle);
        }
    }

    public void h(String str, Bundle bundle) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str.replace("\n", "").replace("\r", ""))));
        } catch (Throwable unused) {
        }
        f(bundle);
    }

    public final String i() {
        return this.h.getAccountId() + ":CT_INAPP_CONTENT_FRAGMENT";
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidClick(CTInAppNotification cTInAppNotification, Bundle bundle, HashMap<String, String> hashMap) {
        e(bundle, hashMap);
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidDismiss(Context context, CTInAppNotification cTInAppNotification, Bundle bundle) {
        f(bundle);
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidShow(CTInAppNotification cTInAppNotification, Bundle bundle) {
        g(bundle);
    }

    public InAppListener j() {
        InAppListener inAppListener;
        try {
            inAppListener = this.j.get();
        } catch (Throwable unused) {
            inAppListener = null;
        }
        if (inAppListener == null) {
            Logger logger = this.h.getLogger();
            String accountId = this.h.getAccountId();
            logger.verbose(accountId, "InAppActivityListener is null for notification: " + this.i.getJsonDescription());
        }
        return inAppListener;
    }

    public void k(InAppListener inAppListener) {
        this.j = new WeakReference<>(inAppListener);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(17432576, 17432577);
        finish();
        f(null);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = getResources().getConfiguration().orientation;
        if (i == 2) {
            getWindow().addFlags(1024);
        }
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.i = (CTInAppNotification) extras.getParcelable(Constants.INAPP_KEY);
                boolean z = extras.getBoolean(InAppController.DISPLAY_HARD_PERMISSION_BUNDLE_KEY, false);
                Bundle bundle2 = extras.getBundle("configBundle");
                if (bundle2 != null) {
                    this.h = (CleverTapInstanceConfig) bundle2.getParcelable(Constants.KEY_CONFIG);
                }
                k(CleverTapAPI.instanceWithConfig(this, this.h).getCoreState().getInAppController());
                setPermissionCallback(CleverTapAPI.instanceWithConfig(this, this.h).getCoreState().getInAppController());
                this.l = new PushPermissionManager(this, this.h);
                if (z) {
                    showHardPermissionPrompt(extras.getBoolean(InAppController.SHOW_FALLBACK_SETTINGS_BUNDLE_KEY, false));
                    return;
                }
                CTInAppNotification cTInAppNotification = this.i;
                if (cTInAppNotification == null) {
                    finish();
                    return;
                }
                if (cTInAppNotification.isPortrait() && !this.i.isLandscape()) {
                    if (i == 2) {
                        Logger.d("App in Landscape, dismissing portrait InApp Notification");
                        finish();
                        f(null);
                        return;
                    }
                    Logger.d("App in Portrait, displaying InApp Notification anyway");
                }
                if (!this.i.isPortrait() && this.i.isLandscape()) {
                    if (i == 1) {
                        Logger.d("App in Portrait, dismissing landscape InApp Notification");
                        finish();
                        f(null);
                        return;
                    }
                    Logger.d("App in Landscape, displaying InApp Notification anyway");
                }
                if (bundle == null) {
                    CTInAppBaseFullFragment d2 = d();
                    if (d2 != null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putParcelable(Constants.INAPP_KEY, this.i);
                        bundle3.putParcelable(Constants.KEY_CONFIG, this.h);
                        d2.setArguments(bundle3);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(17498112, 17498113).add(16908290, d2, i()).commit();
                        return;
                    }
                    return;
                } else if (m) {
                    d();
                    return;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException();
        } catch (Throwable th) {
            Logger.v("Cannot find a valid notification bundle to show!", th);
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = false;
        CTPreferenceCache.getInstance(this, this.h).setFirstTimeRequest(false);
        CTPreferenceCache.updateCacheToDisk(this, this.h);
        if (i == 102) {
            if (iArr.length > 0 && iArr[0] == 0) {
                z = true;
            }
            if (z) {
                this.k.get().onPushPermissionAccept();
            } else {
                this.k.get().onPushPermissionDeny();
            }
            f(null);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!this.l.isFromNotificationSettingsActivity() || Build.VERSION.SDK_INT < 33) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, PushPermissionManager.ANDROID_PERMISSION_STRING) == 0) {
            this.k.get().onPushPermissionAccept();
        } else {
            this.k.get().onPushPermissionDeny();
        }
        f(null);
    }

    public void setPermissionCallback(PushPermissionResultCallback pushPermissionResultCallback) {
        this.k = new WeakReference<>(pushPermissionResultCallback);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(16973840);
    }

    @SuppressLint({"NewApi"})
    public void showHardPermissionPrompt(boolean z) {
        this.l.showHardPermissionPrompt(z, this.k.get());
    }
}
