package com.clevertap.android.sdk.inapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.clevertap.android.sdk.AnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTPreferenceCache;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.InAppNotificationListener;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.PushPermissionManager;
import com.clevertap.android.sdk.PushPermissionResponseListener;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.MainLooperHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class InAppController implements CTInAppNotification.c, InAppListener, InAppNotificationActivity.PushPermissionResultCallback {
    public static final String DISPLAY_HARD_PERMISSION_BUNDLE_KEY = "displayHardPermissionDialog";
    public static final String IS_FIRST_TIME_PERMISSION_REQUEST = "firstTimeRequest";
    public static final String IS_HARD_PERMISSION_REQUEST = "isHardPermissionRequest";
    public static final String LOCAL_INAPP_COUNT = "local_in_app_count";
    public static final String SHOW_FALLBACK_SETTINGS_BUNDLE_KEY = "shouldShowFallbackSettings";
    public static CTInAppNotification s;
    public static final List<CTInAppNotification> t = Collections.synchronizedList(new ArrayList());
    public final AnalyticsManager h;
    public final BaseCallbackManager i;
    public final CleverTapInstanceConfig j;
    public final Context k;
    public final ControllerManager l;
    public final CoreMetaData m;
    public final DeviceInfo n;
    public final Logger q;
    public final MainLooperHandler r;
    public HashSet<String> p = null;
    public j o = j.RESUMED;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ CTInAppNotification i;

        public a(Context context, CTInAppNotification cTInAppNotification) {
            this.h = context;
            this.i = cTInAppNotification;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            InAppController.m(this.h, InAppController.this.j, this.i, InAppController.this);
            InAppController.this.a(this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public final /* synthetic */ CTInAppNotification h;

        public b(CTInAppNotification cTInAppNotification) {
            this.h = cTInAppNotification;
        }

        @Override // java.lang.Runnable
        public void run() {
            InAppController.this.notificationReady(this.h);
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Void> {
        public final /* synthetic */ Context h;

        public c(Context context) {
            this.h = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            InAppController.this.a(this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Runnable {
        public final /* synthetic */ CTInAppNotification h;

        public d(CTInAppNotification cTInAppNotification) {
            this.h = cTInAppNotification;
        }

        @Override // java.lang.Runnable
        public void run() {
            InAppController.this.l(this.h);
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Callable<Void> {
        public final /* synthetic */ JSONObject h;

        public e(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            InAppController inAppController = InAppController.this;
            new k(inAppController, this.h).run();
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Callable<Void> {
        public f() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            InAppController inAppController = InAppController.this;
            inAppController.a(inAppController.k);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class g implements Runnable {
        public final /* synthetic */ Context h;
        public final /* synthetic */ CTInAppNotification i;
        public final /* synthetic */ CleverTapInstanceConfig j;
        public final /* synthetic */ InAppController k;

        public g(Context context, CTInAppNotification cTInAppNotification, CleverTapInstanceConfig cleverTapInstanceConfig, InAppController inAppController) {
            this.h = context;
            this.i = cTInAppNotification;
            this.j = cleverTapInstanceConfig;
            this.k = inAppController;
        }

        @Override // java.lang.Runnable
        public void run() {
            InAppController.p(this.h, this.i, this.j, this.k);
        }
    }

    /* loaded from: classes2.dex */
    public class h implements Callable<Void> {
        public final /* synthetic */ Context h;

        public h(Context context) {
            this.h = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            StorageHelper.putIntImmediate(this.h, InAppController.LOCAL_INAPP_COUNT, InAppController.this.n.getLocalInAppCount());
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static /* synthetic */ class i {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2629a;

        static {
            int[] iArr = new int[CTInAppType.values().length];
            f2629a = iArr;
            try {
                iArr[CTInAppType.CTInAppTypeCoverHTML.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeInterstitialHTML.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeHalfInterstitialHTML.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeCover.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeHalfInterstitial.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeInterstitial.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeAlert.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeInterstitialImageOnly.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeHalfInterstitialImageOnly.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeCoverImageOnly.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeFooterHTML.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeHeaderHTML.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeFooter.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f2629a[CTInAppType.CTInAppTypeHeader.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum j {
        DISCARDED(-1),
        SUSPENDED(0),
        RESUMED(1);
        
        public final int state;

        j(int i) {
            this.state = i;
        }

        public int intValue() {
            return this.state;
        }
    }

    /* loaded from: classes2.dex */
    public final class k implements Runnable {
        public final WeakReference<InAppController> h;
        public JSONObject i;
        public final boolean j = Utils.haveVideoPlayerSupport;

        public k(InAppController inAppController, JSONObject jSONObject) {
            this.h = new WeakReference<>(inAppController);
            this.i = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            CTInAppNotification s = new CTInAppNotification().s(this.i, this.j);
            if (s.h() != null) {
                Logger logger = InAppController.this.q;
                String accountId = InAppController.this.j.getAccountId();
                logger.debug(accountId, "Unable to parse inapp notification " + s.h());
                return;
            }
            s.h = this.h.get();
            s.A();
        }
    }

    public InAppController(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, MainLooperHandler mainLooperHandler, ControllerManager controllerManager, BaseCallbackManager baseCallbackManager, AnalyticsManager analyticsManager, CoreMetaData coreMetaData, DeviceInfo deviceInfo) {
        this.k = context;
        this.j = cleverTapInstanceConfig;
        this.q = cleverTapInstanceConfig.getLogger();
        this.r = mainLooperHandler;
        this.l = controllerManager;
        this.i = baseCallbackManager;
        this.h = analyticsManager;
        this.m = coreMetaData;
        this.n = deviceInfo;
    }

    public static void k(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, InAppController inAppController) {
        Logger.v(cleverTapInstanceConfig.getAccountId(), "checking Pending Notifications");
        List<CTInAppNotification> list = t;
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            list.remove(0);
            new MainLooperHandler().post(new g(context, list.get(0), cleverTapInstanceConfig, inAppController));
        } catch (Throwable unused) {
        }
    }

    public static void m(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CTInAppNotification cTInAppNotification, InAppController inAppController) {
        Logger.v(cleverTapInstanceConfig.getAccountId(), "Running inAppDidDismiss");
        CTInAppNotification cTInAppNotification2 = s;
        if (cTInAppNotification2 == null || !cTInAppNotification2.getCampaignId().equals(cTInAppNotification.getCampaignId())) {
            return;
        }
        s = null;
        k(context, cleverTapInstanceConfig, inAppController);
    }

    public static void p(Context context, CTInAppNotification cTInAppNotification, CleverTapInstanceConfig cleverTapInstanceConfig, InAppController inAppController) {
        Logger.v(cleverTapInstanceConfig.getAccountId(), "Attempting to show next In-App");
        if (!CoreMetaData.isAppForeground()) {
            t.add(cTInAppNotification);
            Logger.v(cleverTapInstanceConfig.getAccountId(), "Not in foreground, queueing this In App");
        } else if (s != null) {
            t.add(cTInAppNotification);
            Logger.v(cleverTapInstanceConfig.getAccountId(), "In App already displaying, queueing this In App");
        } else if (!inAppController.j()) {
            t.add(cTInAppNotification);
            Logger.v(cleverTapInstanceConfig.getAccountId(), "Not showing In App on blacklisted activity, queuing this In App");
        } else if (System.currentTimeMillis() / 1000 > cTInAppNotification.getTimeToLive()) {
            Logger.d("InApp has elapsed its time to live, not showing the InApp");
        } else {
            s = cTInAppNotification;
            CTInAppType inAppType = cTInAppNotification.getInAppType();
            Fragment fragment = null;
            switch (i.f2629a[inAppType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    Intent intent = new Intent(context, InAppNotificationActivity.class);
                    intent.putExtra(Constants.INAPP_KEY, cTInAppNotification);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constants.KEY_CONFIG, cleverTapInstanceConfig);
                    intent.putExtra("configBundle", bundle);
                    try {
                        Activity currentActivity = CoreMetaData.getCurrentActivity();
                        if (currentActivity != null) {
                            Logger logger = cleverTapInstanceConfig.getLogger();
                            String accountId = cleverTapInstanceConfig.getAccountId();
                            logger.verbose(accountId, "calling InAppActivity for notification: " + cTInAppNotification.getJsonDescription());
                            currentActivity.startActivity(intent);
                            Logger.d("Displaying In-App: " + cTInAppNotification.getJsonDescription());
                            break;
                        } else {
                            throw new IllegalStateException("Current activity reference not found");
                        }
                    } catch (Throwable th) {
                        Logger.v("Please verify the integration of your app. It is not setup to support in-app notifications yet.", th);
                        break;
                    }
                case 11:
                    fragment = new CTInAppHtmlFooterFragment();
                    break;
                case 12:
                    fragment = new CTInAppHtmlHeaderFragment();
                    break;
                case 13:
                    fragment = new CTInAppNativeFooterFragment();
                    break;
                case 14:
                    fragment = new CTInAppNativeHeaderFragment();
                    break;
                default:
                    String accountId2 = cleverTapInstanceConfig.getAccountId();
                    Logger.d(accountId2, "Unknown InApp Type found: " + inAppType);
                    s = null;
                    return;
            }
            if (fragment != null) {
                Logger.d("Displaying In-App: " + cTInAppNotification.getJsonDescription());
                try {
                    FragmentTransaction beginTransaction = ((FragmentActivity) CoreMetaData.getCurrentActivity()).getSupportFragmentManager().beginTransaction();
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable(Constants.INAPP_KEY, cTInAppNotification);
                    bundle2.putParcelable(Constants.KEY_CONFIG, cleverTapInstanceConfig);
                    fragment.setArguments(bundle2);
                    beginTransaction.setCustomAnimations(17498112, 17498113);
                    beginTransaction.add(16908290, fragment, cTInAppNotification.getType());
                    String accountId3 = cleverTapInstanceConfig.getAccountId();
                    Logger.v(accountId3, "calling InAppFragment " + cTInAppNotification.getCampaignId());
                    beginTransaction.commit();
                } catch (ClassCastException e2) {
                    String accountId4 = cleverTapInstanceConfig.getAccountId();
                    Logger.v(accountId4, "Fragment not able to render, please ensure your Activity is an instance of AppCompatActivity" + e2.getMessage());
                } catch (Throwable th2) {
                    Logger.v(cleverTapInstanceConfig.getAccountId(), "Fragment not able to render", th2);
                }
            }
        }
    }

    public static void startPrompt(Activity activity, CleverTapInstanceConfig cleverTapInstanceConfig, boolean z) {
        if (activity.getClass().equals(InAppNotificationActivity.class)) {
            return;
        }
        Intent intent = new Intent(activity, InAppNotificationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.KEY_CONFIG, cleverTapInstanceConfig);
        intent.putExtra("configBundle", bundle);
        intent.putExtra(Constants.INAPP_KEY, s);
        intent.putExtra(DISPLAY_HARD_PERMISSION_BUNDLE_KEY, true);
        intent.putExtra(SHOW_FALLBACK_SETTINGS_BUNDLE_KEY, z);
        activity.startActivity(intent);
    }

    public final void a(Context context) {
        SharedPreferences preferences = StorageHelper.getPreferences(context);
        try {
            if (!j()) {
                Logger.v("Not showing notification on blacklisted activity");
            } else if (this.o == j.SUSPENDED) {
                this.q.debug(this.j.getAccountId(), "InApp Notifications are set to be suspended, not showing the InApp Notification");
            } else {
                k(context, this.j, this);
                JSONArray jSONArray = new JSONArray(StorageHelper.getStringFromPrefs(context, this.j, Constants.INAPP_KEY, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI));
                if (jSONArray.length() < 1) {
                    return;
                }
                if (this.o != j.DISCARDED) {
                    o(jSONArray.getJSONObject(0));
                } else {
                    this.q.debug(this.j.getAccountId(), "InApp Notifications are set to be discarded, dropping the InApp Notification");
                }
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (i2 != 0) {
                        jSONArray2.put(jSONArray.get(i2));
                    }
                }
                StorageHelper.persist(preferences.edit().putString(StorageHelper.storageKeyWithSuffix(this.j, Constants.INAPP_KEY), jSONArray2.toString()));
            }
        } catch (Throwable th) {
            this.q.verbose(this.j.getAccountId(), "InApp: Couldn't parse JSON array string from prefs", th);
        }
    }

    public void checkExistingInAppNotifications(Activity activity) {
        if (!j() || s == null || System.currentTimeMillis() / 1000 >= s.getTimeToLive()) {
            return;
        }
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        Fragment fragment = fragmentActivity.getSupportFragmentManager().getFragment(new Bundle(), s.getType());
        if (CoreMetaData.getCurrentActivity() == null || fragment == null) {
            return;
        }
        FragmentTransaction beginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.INAPP_KEY, s);
        bundle.putParcelable(Constants.KEY_CONFIG, this.j);
        fragment.setArguments(bundle);
        beginTransaction.setCustomAnimations(17498112, 17498113);
        beginTransaction.add(16908290, fragment, s.getType());
        String accountId = this.j.getAccountId();
        Logger.v(accountId, "calling InAppFragment " + s.getCampaignId());
        beginTransaction.commit();
    }

    public void checkPendingInAppNotifications(Activity activity) {
        if (j()) {
            if (this.r.getPendingRunnable() != null) {
                this.q.verbose(this.j.getAccountId(), "Found a pending inapp runnable. Scheduling it");
                MainLooperHandler mainLooperHandler = this.r;
                mainLooperHandler.postDelayed(mainLooperHandler.getPendingRunnable(), 200L);
                this.r.setPendingRunnable(null);
                return;
            }
            showNotificationIfAvailable(this.k);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("In-app notifications will not be shown for this activity (");
        sb.append(activity != null ? activity.getLocalClassName() : "");
        sb.append(")");
        Logger.d(sb.toString());
    }

    public void discardInApps() {
        this.o = j.DISCARDED;
        this.q.verbose(this.j.getAccountId(), "InAppState is DISCARDED");
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidClick(CTInAppNotification cTInAppNotification, Bundle bundle, HashMap<String, String> hashMap) {
        this.h.pushInAppNotificationStateEvent(true, cTInAppNotification, bundle);
        if (hashMap == null || hashMap.isEmpty() || this.i.getInAppNotificationButtonListener() == null) {
            return;
        }
        this.i.getInAppNotificationButtonListener().onInAppButtonClick(hashMap);
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidDismiss(Context context, CTInAppNotification cTInAppNotification, Bundle bundle) {
        HashMap<String, Object> hashMap;
        cTInAppNotification.b();
        if (this.l.getInAppFCManager() != null) {
            this.l.getInAppFCManager().didDismiss(cTInAppNotification);
            Logger logger = this.q;
            String accountId = this.j.getAccountId();
            logger.verbose(accountId, "InApp Dismissed: " + cTInAppNotification.getCampaignId());
        } else {
            Logger logger2 = this.q;
            String accountId2 = this.j.getAccountId();
            logger2.verbose(accountId2, "Not calling InApp Dismissed: " + cTInAppNotification.getCampaignId() + " because InAppFCManager is null");
        }
        try {
            InAppNotificationListener inAppNotificationListener = this.i.getInAppNotificationListener();
            if (inAppNotificationListener != null) {
                if (cTInAppNotification.f() != null) {
                    hashMap = Utils.convertJSONObjectToHashMap(cTInAppNotification.f());
                } else {
                    hashMap = new HashMap<>();
                }
                Logger.v("Calling the in-app listener on behalf of " + this.m.getSource());
                if (bundle != null) {
                    inAppNotificationListener.onDismissed(hashMap, Utils.convertBundleObjectToHashMap(bundle));
                } else {
                    inAppNotificationListener.onDismissed(hashMap, null);
                }
            }
        } catch (Throwable th) {
            this.q.verbose(this.j.getAccountId(), "Failed to call the in-app notification listener", th);
        }
        CTExecutorFactory.executors(this.j).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InappController#inAppNotificationDidDismiss", new a(context, cTInAppNotification));
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidShow(CTInAppNotification cTInAppNotification, Bundle bundle) {
        this.h.pushInAppNotificationStateEvent(false, cTInAppNotification, bundle);
        try {
            InAppNotificationListener inAppNotificationListener = this.i.getInAppNotificationListener();
            if (inAppNotificationListener != null) {
                inAppNotificationListener.onShow(cTInAppNotification);
            }
        } catch (Throwable th) {
            Logger.v(this.j.getAccountId(), "Failed to call the in-app notification listener", th);
        }
    }

    @RequiresApi(api = 33)
    public boolean isPushPermissionGranted() {
        return ContextCompat.checkSelfPermission(this.k, PushPermissionManager.ANDROID_PERMISSION_STRING) == 0;
    }

    public final boolean j() {
        s();
        Iterator<String> it = this.p.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String currentActivityName = CoreMetaData.getCurrentActivityName();
            if (currentActivityName != null && currentActivityName.contains(next)) {
                return false;
            }
        }
        return true;
    }

    public final void l(CTInAppNotification cTInAppNotification) {
        boolean z;
        HashMap<String, Object> hashMap;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.r.post(new d(cTInAppNotification));
        } else if (this.l.getInAppFCManager() != null) {
            if (!this.l.getInAppFCManager().canShow(cTInAppNotification)) {
                Logger logger = this.q;
                String accountId = this.j.getAccountId();
                logger.verbose(accountId, "InApp has been rejected by FC, not showing " + cTInAppNotification.getCampaignId());
                q();
                return;
            }
            this.l.getInAppFCManager().didShow(this.k, cTInAppNotification);
            InAppNotificationListener inAppNotificationListener = this.i.getInAppNotificationListener();
            if (inAppNotificationListener != null) {
                if (cTInAppNotification.f() != null) {
                    hashMap = Utils.convertJSONObjectToHashMap(cTInAppNotification.f());
                } else {
                    hashMap = new HashMap<>();
                }
                z = inAppNotificationListener.beforeShow(hashMap);
            } else {
                z = true;
            }
            if (!z) {
                Logger logger2 = this.q;
                String accountId2 = this.j.getAccountId();
                logger2.verbose(accountId2, "Application has decided to not show this in-app notification: " + cTInAppNotification.getCampaignId());
                q();
                return;
            }
            p(this.k, cTInAppNotification, this.j, this);
            n(this.k, cTInAppNotification);
        } else {
            Logger logger3 = this.q;
            String accountId3 = this.j.getAccountId();
            logger3.verbose(accountId3, "getCoreState().getInAppFCManager() is NULL, not showing " + cTInAppNotification.getCampaignId());
        }
    }

    public final void n(Context context, CTInAppNotification cTInAppNotification) {
        if (cTInAppNotification.isLocalInApp()) {
            this.n.incrementLocalInAppCount();
            CTExecutorFactory.executors(this.j).ioTask().execute("InAppController#incrementLocalInAppCountInPersistentStore", new h(context));
        }
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppNotification.c
    public void notificationReady(CTInAppNotification cTInAppNotification) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.r.post(new b(cTInAppNotification));
        } else if (cTInAppNotification.h() != null) {
            Logger logger = this.q;
            String accountId = this.j.getAccountId();
            logger.debug(accountId, "Unable to process inapp notification " + cTInAppNotification.h());
        } else {
            Logger logger2 = this.q;
            String accountId2 = this.j.getAccountId();
            logger2.debug(accountId2, "Notification ready: " + cTInAppNotification.getJsonDescription());
            l(cTInAppNotification);
        }
    }

    public void notifyPushPermissionResult(boolean z) {
        for (PushPermissionResponseListener pushPermissionResponseListener : this.i.getPushPermissionResponseListenerList()) {
            if (pushPermissionResponseListener != null) {
                pushPermissionResponseListener.onPushPermissionResponse(z);
            }
        }
    }

    public final void o(JSONObject jSONObject) {
        Logger logger = this.q;
        String accountId = this.j.getAccountId();
        logger.debug(accountId, "Preparing In-App for display: " + jSONObject.toString());
        CTExecutorFactory.executors(this.j).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InappController#prepareNotificationForDisplay", new e(jSONObject));
    }

    @Override // com.clevertap.android.sdk.InAppNotificationActivity.PushPermissionResultCallback
    public void onPushPermissionAccept() {
        notifyPushPermissionResult(true);
    }

    @Override // com.clevertap.android.sdk.InAppNotificationActivity.PushPermissionResultCallback
    public void onPushPermissionDeny() {
        notifyPushPermissionResult(false);
    }

    @RequiresApi(api = 33)
    public void promptPermission(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, z);
            jSONObject.put(IS_HARD_PERMISSION_REQUEST, true);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        promptPushPrimer(jSONObject);
    }

    @RequiresApi(api = 33)
    public void promptPushPrimer(JSONObject jSONObject) {
        if (ContextCompat.checkSelfPermission(this.k, PushPermissionManager.ANDROID_PERMISSION_STRING) == -1) {
            boolean isFirstTimeRequest = CTPreferenceCache.getInstance(this.k, this.j).isFirstTimeRequest();
            Activity currentActivity = CoreMetaData.getCurrentActivity();
            if (currentActivity == null) {
                Logger.d("CurrentActivity reference is null. SDK can't process the promptPushPrimer(jsonObject) method! Ensure the following things:\n1. Calling ActivityLifecycleCallback.register(this) in your custom application class before super.onCreate().\n   Alternatively, register CleverTap SDK's Application class in the manifest using com.clevertap.android.sdk.Application.\n2. Ensure that the promptPushPrimer() API is called from the onResume() lifecycle method, not onCreate().");
                return;
            }
            boolean shouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, PushPermissionManager.ANDROID_PERMISSION_STRING);
            if (!isFirstTimeRequest && shouldShowRequestPermissionRationale) {
                if (!jSONObject.optBoolean(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, false)) {
                    Logger.v("Notification permission is denied. Please grant notification permission access in your app's settings to send notifications");
                    notifyPushPermissionResult(false);
                    return;
                }
                r(jSONObject);
                return;
            }
            r(jSONObject);
            return;
        }
        notifyPushPermissionResult(true);
    }

    public final void q() {
        if (this.j.isAnalyticsOnly()) {
            return;
        }
        CTExecutorFactory.executors(this.j).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InAppController#showInAppNotificationIfAny", new f());
    }

    public final void r(JSONObject jSONObject) {
        if (jSONObject.optBoolean(IS_HARD_PERMISSION_REQUEST, false)) {
            Activity currentActivity = CoreMetaData.getCurrentActivity();
            Objects.requireNonNull(currentActivity);
            startPrompt(currentActivity, this.j, jSONObject.optBoolean(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, false));
            return;
        }
        o(jSONObject);
    }

    public void resumeInApps() {
        this.o = j.RESUMED;
        this.q.verbose(this.j.getAccountId(), "InAppState is RESUMED");
        this.q.verbose(this.j.getAccountId(), "Resuming InApps by calling showInAppNotificationIfAny()");
        q();
    }

    public final void s() {
        if (this.p == null) {
            this.p = new HashSet<>();
            try {
                String excludedActivities = ManifestInfo.getInstance(this.k).getExcludedActivities();
                if (excludedActivities != null) {
                    for (String str : excludedActivities.split(Constants.SEPARATOR_COMMA)) {
                        this.p.add(str.trim());
                    }
                }
            } catch (Throwable unused) {
            }
            this.q.debug(this.j.getAccountId(), "In-app notifications will not be shown on " + Arrays.toString(this.p.toArray()));
        }
    }

    public void showNotificationIfAvailable(Context context) {
        if (this.j.isAnalyticsOnly()) {
            return;
        }
        CTExecutorFactory.executors(this.j).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InappController#showNotificationIfAvailable", new c(context));
    }

    public void suspendInApps() {
        this.o = j.SUSPENDED;
        this.q.verbose(this.j.getAccountId(), "InAppState is SUSPENDED");
    }
}
