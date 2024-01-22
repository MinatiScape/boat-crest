package com.clevertap.android.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.clevertap.android.sdk.a;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final AnalyticsManager f2587a;
    public final BaseEventQueueManager b;
    public final BaseCallbackManager c;
    public final CleverTapInstanceConfig d;
    public final Context e;
    public final CoreMetaData f;
    public final InAppController g;
    public final PushProviders h;
    public final SessionManager i;

    /* renamed from: com.clevertap.android.sdk.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class CallableC0224a implements Callable<Void> {
        public CallableC0224a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            if (a.this.f.inCurrentSession()) {
                try {
                    StorageHelper.putInt(a.this.e, StorageHelper.storageKeyWithSuffix(a.this.d, Constants.LAST_SESSION_EPOCH), currentTimeMillis);
                    Logger logger = a.this.d.getLogger();
                    String accountId = a.this.d.getAccountId();
                    logger.verbose(accountId, "Updated session time: " + currentTimeMillis);
                    return null;
                } catch (Throwable th) {
                    Logger logger2 = a.this.d.getLogger();
                    String accountId2 = a.this.d.getAccountId();
                    logger2.verbose(accountId2, "Failed to update session time time: " + th.getMessage());
                    return null;
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Callable<Void> {
        public b() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (a.this.f.isInstallReferrerDataSent() || !a.this.f.isFirstSession()) {
                return null;
            }
            a.this.h();
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class c implements InstallReferrerStateListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ InstallReferrerClient f2588a;

        public c(InstallReferrerClient installReferrerClient) {
            this.f2588a = installReferrerClient;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(InstallReferrerClient installReferrerClient, ReferrerDetails referrerDetails) {
            try {
                String installReferrer = referrerDetails.getInstallReferrer();
                a.this.f.p(referrerDetails.getReferrerClickTimestampSeconds());
                a.this.f.setAppInstallTime(referrerDetails.getInstallBeginTimestampSeconds());
                a.this.f2587a.pushInstallReferrer(installReferrer);
                a.this.f.l(true);
                Logger logger = a.this.d.getLogger();
                String accountId = a.this.d.getAccountId();
                logger.debug(accountId, "Install Referrer data set [Referrer URL-" + installReferrer + "]");
            } catch (NullPointerException e) {
                Logger logger2 = a.this.d.getLogger();
                String accountId2 = a.this.d.getAccountId();
                logger2.debug(accountId2, "Install referrer client null pointer exception caused by Google Play Install Referrer library - " + e.getMessage());
                installReferrerClient.endConnection();
                a.this.f.l(false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ ReferrerDetails d(InstallReferrerClient installReferrerClient) throws Exception {
            try {
                return installReferrerClient.getInstallReferrer();
            } catch (RemoteException e) {
                Logger logger = a.this.d.getLogger();
                String accountId = a.this.d.getAccountId();
                logger.debug(accountId, "Remote exception caused by Google Play Install Referrer library - " + e.getMessage());
                installReferrerClient.endConnection();
                a.this.f.l(false);
                return null;
            }
        }

        @Override // com.android.installreferrer.api.InstallReferrerStateListener
        public void onInstallReferrerServiceDisconnected() {
            if (a.this.f.isInstallReferrerDataSent()) {
                return;
            }
            a.this.h();
        }

        @Override // com.android.installreferrer.api.InstallReferrerStateListener
        public void onInstallReferrerSetupFinished(int i) {
            if (i == 0) {
                Task postAsyncSafelyTask = CTExecutorFactory.executors(a.this.d).postAsyncSafelyTask();
                final InstallReferrerClient installReferrerClient = this.f2588a;
                postAsyncSafelyTask.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.b
                    @Override // com.clevertap.android.sdk.task.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        a.c.this.c(installReferrerClient, (ReferrerDetails) obj);
                    }
                });
                final InstallReferrerClient installReferrerClient2 = this.f2588a;
                postAsyncSafelyTask.execute("ActivityLifeCycleManager#getInstallReferrer", new Callable() { // from class: com.clevertap.android.sdk.c
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        ReferrerDetails d;
                        d = a.c.this.d(installReferrerClient2);
                        return d;
                    }
                });
            } else if (i == 1) {
                a.this.d.getLogger().debug(a.this.d.getAccountId(), "Install Referrer data not set, connection to Play Store unavailable");
            } else if (i != 2) {
            } else {
                a.this.d.getLogger().debug(a.this.d.getAccountId(), "Install Referrer data not set, API not supported by Play Store on device");
            }
        }
    }

    public a(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, AnalyticsManager analyticsManager, CoreMetaData coreMetaData, SessionManager sessionManager, PushProviders pushProviders, BaseCallbackManager baseCallbackManager, InAppController inAppController, BaseEventQueueManager baseEventQueueManager) {
        this.e = context;
        this.d = cleverTapInstanceConfig;
        this.f2587a = analyticsManager;
        this.f = coreMetaData;
        this.i = sessionManager;
        this.h = pushProviders;
        this.c = baseCallbackManager;
        this.g = inAppController;
        this.b = baseEventQueueManager;
    }

    public void f() {
        CoreMetaData.setAppForeground(false);
        this.i.setAppLastSeen(System.currentTimeMillis());
        this.d.getLogger().verbose(this.d.getAccountId(), "App in background");
        CTExecutorFactory.executors(this.d).postAsyncSafelyTask().execute("activityPaused", new CallableC0224a());
    }

    public void g(Activity activity) {
        this.d.getLogger().verbose(this.d.getAccountId(), "App in foreground");
        this.i.checkTimeoutSession();
        if (!this.f.isAppLaunchPushed()) {
            this.f2587a.pushAppLaunchedEvent();
            this.f2587a.fetchFeatureFlags();
            this.h.onTokenRefresh();
            CTExecutorFactory.executors(this.d).postAsyncSafelyTask().execute("HandlingInstallReferrer", new b());
            try {
                if (this.c.getGeofenceCallback() != null) {
                    this.c.getGeofenceCallback().triggerLocation();
                }
            } catch (IllegalStateException e) {
                this.d.getLogger().verbose(this.d.getAccountId(), e.getLocalizedMessage());
            } catch (Exception unused) {
                this.d.getLogger().verbose(this.d.getAccountId(), "Failed to trigger location");
            }
        }
        this.b.pushInitialEventsAsync();
        this.g.checkExistingInAppNotifications(activity);
        this.g.checkPendingInAppNotifications(activity);
    }

    public final void h() {
        this.d.getLogger().verbose(this.d.getAccountId(), "Starting to handle install referrer");
        try {
            InstallReferrerClient build = InstallReferrerClient.newBuilder(this.e).build();
            build.startConnection(new c(build));
        } catch (Throwable th) {
            Logger logger = this.d.getLogger();
            String accountId = this.d.getAccountId();
            logger.verbose(accountId, "Google Play Install Referrer's InstallReferrerClient Class not found - " + th.getLocalizedMessage() + " \n Please add implementation 'com.android.installreferrer:installreferrer:2.1' to your build.gradle");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0009, code lost:
        if (r2.d.isDefaultInstance() == false) goto L3;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void i(android.os.Bundle r3, android.net.Uri r4, java.lang.String r5) {
        /*
            r2 = this;
            r0 = 0
            if (r5 != 0) goto Lb
            com.clevertap.android.sdk.CleverTapInstanceConfig r1 = r2.d     // Catch: java.lang.Throwable -> L39
            boolean r1 = r1.isDefaultInstance()     // Catch: java.lang.Throwable -> L39
            if (r1 != 0) goto L17
        Lb:
            com.clevertap.android.sdk.CleverTapInstanceConfig r1 = r2.d     // Catch: java.lang.Throwable -> L39
            java.lang.String r1 = r1.getAccountId()     // Catch: java.lang.Throwable -> L39
            boolean r5 = r1.equals(r5)     // Catch: java.lang.Throwable -> L39
            if (r5 == 0) goto L19
        L17:
            r5 = 1
            goto L1a
        L19:
            r5 = r0
        L1a:
            if (r5 == 0) goto L52
            if (r3 == 0) goto L31
            boolean r5 = r3.isEmpty()     // Catch: java.lang.Throwable -> L39
            if (r5 != 0) goto L31
            java.lang.String r5 = "wzrk_pn"
            boolean r5 = r3.containsKey(r5)     // Catch: java.lang.Throwable -> L39
            if (r5 == 0) goto L31
            com.clevertap.android.sdk.AnalyticsManager r5 = r2.f2587a     // Catch: java.lang.Throwable -> L39
            r5.pushNotificationClickedEvent(r3)     // Catch: java.lang.Throwable -> L39
        L31:
            if (r4 == 0) goto L52
            com.clevertap.android.sdk.AnalyticsManager r3 = r2.f2587a     // Catch: java.lang.Throwable -> L52
            r3.y(r4, r0)     // Catch: java.lang.Throwable -> L52
            goto L52
        L39:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Throwable - "
            r4.append(r5)
            java.lang.String r3 = r3.getLocalizedMessage()
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.clevertap.android.sdk.Logger.v(r3)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.a.i(android.os.Bundle, android.net.Uri, java.lang.String):void");
    }
}
