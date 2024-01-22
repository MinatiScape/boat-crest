package com.clevertap.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.events.EventDetail;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController;
import com.clevertap.android.sdk.inbox.CTInboxActivity;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.inbox.CTMessageDAO;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.interfaces.NotificationRenderedListener;
import com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener;
import com.clevertap.android.sdk.interfaces.SCDomainListener;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.product_config.CTProductConfigListener;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.CoreNotificationRenderer;
import com.clevertap.android.sdk.pushnotification.INotificationRenderer;
import com.clevertap.android.sdk.pushnotification.NotificationInfo;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.pushnotification.amp.CTPushAmpListener;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.validation.ManifestValidator;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.variables.CTVariables;
import com.clevertap.android.sdk.variables.Var;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.jose4j.jwk.RsaJsonWebKey;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CleverTapAPI implements CTInboxActivity.InboxActivityListener {
    public static final String NOTIFICATION_TAG = "wzrk_pn";
    public static CleverTapInstanceConfig f;
    public static HashMap<String, CleverTapAPI> g;
    public static NotificationHandler h;
    public static NotificationHandler i;

    /* renamed from: a  reason: collision with root package name */
    public final Context f2571a;
    public CoreState b;
    public WeakReference<InboxMessageButtonListener> c;
    public WeakReference<InboxMessageListener> d;
    public static int e = LogLevel.INFO.intValue();
    public static HashMap<String, NotificationRenderedListener> j = new HashMap<>();

    /* loaded from: classes2.dex */
    public interface DevicePushTokenRefreshListener {
        void devicePushTokenDidRefresh(String str, PushConstants.PushType pushType);
    }

    /* loaded from: classes2.dex */
    public enum LogLevel {
        OFF(-1),
        INFO(0),
        DEBUG(2),
        VERBOSE(3);
        
        private final int value;

        LogLevel(int i) {
            this.value = i;
        }

        public int intValue() {
            return this.value;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes2.dex */
    public interface RequestDevicePushTokenListener {
        void onDevicePushToken(String str, PushConstants.PushType pushType);
    }

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            CleverTapAPI.this.b.getSessionManager().c();
            CleverTapAPI.this.b.getDeviceInfo().x();
            CleverTapAPI.this.b.getDeviceInfo().setCurrentUserOptOutStateFromStorage();
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Callable<Void> {
        public final /* synthetic */ CleverTapInstanceConfig h;
        public final /* synthetic */ Context i;

        public b(CleverTapAPI cleverTapAPI, CleverTapInstanceConfig cleverTapInstanceConfig, Context context) {
            this.h = cleverTapInstanceConfig;
            this.i = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            String f = this.h.f();
            if (f == null) {
                Logger.v("Unable to save config to SharedPrefs, config Json is null");
                return null;
            }
            StorageHelper.putString(this.i, StorageHelper.storageKeyWithSuffix(this.h, "instance"), f);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class c implements OnCompleteListener<String> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ RequestDevicePushTokenListener f2572a;

        public c(CleverTapAPI cleverTapAPI, RequestDevicePushTokenListener requestDevicePushTokenListener) {
            this.f2572a = requestDevicePushTokenListener;
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(@NonNull Task<String> task) {
            if (!task.isSuccessful()) {
                Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json failed", task.getException());
                this.f2572a.onDevicePushToken(null, PushConstants.PushType.FCM);
                return;
            }
            String result = task.getResult() != null ? task.getResult() : null;
            Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json - " + result);
            this.f2572a.onDevicePushToken(result, PushConstants.PushType.FCM);
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Callable<Void> {
        public final /* synthetic */ CTInboxMessage h;
        public final /* synthetic */ Bundle i;

        public d(CTInboxMessage cTInboxMessage, Bundle bundle) {
            this.h = cTInboxMessage;
            this.i = bundle;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            Logger.d("CleverTapAPI:messageDidShow() called  in async with: messageId = [" + this.h.getMessageId() + "]");
            if (CleverTapAPI.this.getInboxMessageForId(this.h.getMessageId()).isRead()) {
                return null;
            }
            CleverTapAPI.this.markReadInboxMessage(this.h);
            CleverTapAPI.this.b.getAnalyticsManager().z(false, this.h, this.i);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Callable<Void> {
        public final /* synthetic */ boolean h;

        public e(boolean z) {
            this.h = z;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.CLEVERTAP_OPTOUT, Boolean.valueOf(this.h));
            if (!this.h) {
                CleverTapAPI.this.b.getCoreMetaData().setCurrentUserOptedOut(false);
                CleverTapAPI.this.pushProfile(hashMap);
            } else {
                CleverTapAPI.this.pushProfile(hashMap);
                CleverTapAPI.this.b.getCoreMetaData().setCurrentUserOptedOut(true);
            }
            String u = CleverTapAPI.this.b.getDeviceInfo().u();
            if (u != null) {
                StorageHelper.putBoolean(CleverTapAPI.this.f2571a, StorageHelper.storageKeyWithSuffix(CleverTapAPI.this.m(), u), this.h);
                Logger n = CleverTapAPI.this.n();
                String accountId = CleverTapAPI.this.getAccountId();
                n.verbose(accountId, "Set current user OptOut state to: " + this.h);
                return null;
            }
            CleverTapAPI.this.n().verbose(CleverTapAPI.this.getAccountId(), "Unable to persist user OptOut state, storage key is null");
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
            ManifestValidator.validate(CleverTapAPI.this.f2571a, CleverTapAPI.this.b.getDeviceInfo(), CleverTapAPI.this.b.getPushProviders());
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class g implements Callable<Void> {
        public final /* synthetic */ OnInitCleverTapIDListener h;

        public g(OnInitCleverTapIDListener onInitCleverTapIDListener) {
            this.h = onInitCleverTapIDListener;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            String deviceID = CleverTapAPI.this.b.getDeviceInfo().getDeviceID();
            if (deviceID == null) {
                CleverTapAPI.this.b.getCallbackManager().setOnInitCleverTapIDListener(this.h);
                return null;
            }
            this.h.onInitCleverTapID(deviceID);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class h implements Callable<Void> {
        public final /* synthetic */ INotificationRenderer h;
        public final /* synthetic */ Bundle i;
        public final /* synthetic */ Context j;

        public h(INotificationRenderer iNotificationRenderer, Bundle bundle, Context context) {
            this.h = iNotificationRenderer;
            this.i = bundle;
            this.j = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (CleverTapAPI.this.b.getPushProviders().getPushRenderingLock()) {
                CleverTapAPI.this.b.getPushProviders().setPushNotificationRenderer(this.h);
                Bundle bundle = this.i;
                if (bundle != null && bundle.containsKey(Constants.PT_NOTIF_ID)) {
                    PushProviders pushProviders = CleverTapAPI.this.b.getPushProviders();
                    Context context = this.j;
                    Bundle bundle2 = this.i;
                    pushProviders._createNotification(context, bundle2, bundle2.getInt(Constants.PT_NOTIF_ID));
                } else {
                    CleverTapAPI.this.b.getPushProviders()._createNotification(this.j, this.i, -1000);
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class i implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;
        public final /* synthetic */ CharSequence j;
        public final /* synthetic */ int k;
        public final /* synthetic */ String l;
        public final /* synthetic */ boolean m;
        public final /* synthetic */ CleverTapAPI n;

        public i(Context context, String str, CharSequence charSequence, int i, String str2, boolean z, CleverTapAPI cleverTapAPI) {
            this.h = context;
            this.i = str;
            this.j = charSequence;
            this.k = i;
            this.l = str2;
            this.m = z;
            this.n = cleverTapAPI;
        }

        @Override // java.util.concurrent.Callable
        @RequiresApi(api = 26)
        /* renamed from: a */
        public Void call() {
            NotificationManager notificationManager = (NotificationManager) this.h.getSystemService("notification");
            if (notificationManager == null) {
                return null;
            }
            NotificationChannel notificationChannel = new NotificationChannel(this.i, this.j, this.k);
            notificationChannel.setDescription(this.l);
            notificationChannel.setShowBadge(this.m);
            notificationManager.createNotificationChannel(notificationChannel);
            Logger n = this.n.n();
            String accountId = this.n.getAccountId();
            n.info(accountId, "Notification channel " + this.j.toString() + " has been created");
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class j implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;
        public final /* synthetic */ CharSequence j;
        public final /* synthetic */ int k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ boolean n;
        public final /* synthetic */ CleverTapAPI o;

        public j(Context context, String str, CharSequence charSequence, int i, String str2, String str3, boolean z, CleverTapAPI cleverTapAPI) {
            this.h = context;
            this.i = str;
            this.j = charSequence;
            this.k = i;
            this.l = str2;
            this.m = str3;
            this.n = z;
            this.o = cleverTapAPI;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            NotificationManager notificationManager = (NotificationManager) this.h.getSystemService("notification");
            if (notificationManager == null) {
                return null;
            }
            NotificationChannel notificationChannel = new NotificationChannel(this.i, this.j, this.k);
            notificationChannel.setDescription(this.l);
            notificationChannel.setGroup(this.m);
            notificationChannel.setShowBadge(this.n);
            notificationManager.createNotificationChannel(notificationChannel);
            Logger n = this.o.n();
            String accountId = this.o.getAccountId();
            n.info(accountId, "Notification channel " + this.j.toString() + " has been created");
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class k implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;
        public final /* synthetic */ CleverTapAPI j;
        public final /* synthetic */ String k;
        public final /* synthetic */ CharSequence l;
        public final /* synthetic */ int m;
        public final /* synthetic */ String n;
        public final /* synthetic */ boolean o;

        public k(Context context, String str, CleverTapAPI cleverTapAPI, String str2, CharSequence charSequence, int i, String str3, boolean z) {
            this.h = context;
            this.i = str;
            this.j = cleverTapAPI;
            this.k = str2;
            this.l = charSequence;
            this.m = i;
            this.n = str3;
            this.o = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0098  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00aa  */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void call() {
            /*
                r7 = this;
                android.content.Context r0 = r7.h
                java.lang.String r1 = "notification"
                java.lang.Object r0 = r0.getSystemService(r1)
                android.app.NotificationManager r0 = (android.app.NotificationManager) r0
                r1 = 0
                if (r0 != 0) goto Le
                return r1
            Le:
                java.lang.String r2 = r7.i
                boolean r2 = r2.isEmpty()
                if (r2 != 0) goto L80
                java.lang.String r2 = r7.i
                java.lang.String r3 = ".mp3"
                boolean r2 = r2.contains(r3)
                if (r2 != 0) goto L49
                java.lang.String r2 = r7.i
                java.lang.String r3 = ".ogg"
                boolean r2 = r2.contains(r3)
                if (r2 != 0) goto L49
                java.lang.String r2 = r7.i
                java.lang.String r3 = ".wav"
                boolean r2 = r2.contains(r3)
                if (r2 == 0) goto L35
                goto L49
            L35:
                com.clevertap.android.sdk.CleverTapAPI r2 = r7.j
                com.clevertap.android.sdk.Logger r2 = com.clevertap.android.sdk.CleverTapAPI.b(r2)
                com.clevertap.android.sdk.CleverTapAPI r3 = r7.j
                java.lang.String r3 = r3.getAccountId()
                java.lang.String r4 = "Sound file name not supported"
                r2.debug(r3, r4)
                java.lang.String r2 = ""
                goto L56
            L49:
                java.lang.String r2 = r7.i
                r3 = 0
                int r4 = r2.length()
                int r4 = r4 + (-4)
                java.lang.String r2 = r2.substring(r3, r4)
            L56:
                boolean r3 = r2.isEmpty()
                if (r3 != 0) goto L80
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "android.resource://"
                r3.append(r4)
                android.content.Context r4 = r7.h
                java.lang.String r4 = r4.getPackageName()
                r3.append(r4)
                java.lang.String r4 = "/raw/"
                r3.append(r4)
                r3.append(r2)
                java.lang.String r2 = r3.toString()
                android.net.Uri r2 = android.net.Uri.parse(r2)
                goto L81
            L80:
                r2 = r1
            L81:
                android.app.NotificationChannel r3 = new android.app.NotificationChannel
                java.lang.String r4 = r7.k
                java.lang.CharSequence r5 = r7.l
                int r6 = r7.m
                r3.<init>(r4, r5, r6)
                java.lang.String r4 = r7.n
                r3.setDescription(r4)
                boolean r4 = r7.o
                r3.setShowBadge(r4)
                if (r2 == 0) goto Laa
                android.media.AudioAttributes$Builder r4 = new android.media.AudioAttributes$Builder
                r4.<init>()
                r5 = 5
                android.media.AudioAttributes$Builder r4 = r4.setUsage(r5)
                android.media.AudioAttributes r4 = r4.build()
                r3.setSound(r2, r4)
                goto Lbb
            Laa:
                com.clevertap.android.sdk.CleverTapAPI r2 = r7.j
                com.clevertap.android.sdk.Logger r2 = com.clevertap.android.sdk.CleverTapAPI.b(r2)
                com.clevertap.android.sdk.CleverTapAPI r4 = r7.j
                java.lang.String r4 = r4.getAccountId()
                java.lang.String r5 = "Sound file not found, notification channel will be created without custom sound"
                r2.debug(r4, r5)
            Lbb:
                r0.createNotificationChannel(r3)
                com.clevertap.android.sdk.CleverTapAPI r0 = r7.j
                com.clevertap.android.sdk.Logger r0 = com.clevertap.android.sdk.CleverTapAPI.b(r0)
                com.clevertap.android.sdk.CleverTapAPI r2 = r7.j
                java.lang.String r2 = r2.getAccountId()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Notification channel "
                r3.append(r4)
                java.lang.CharSequence r4 = r7.l
                java.lang.String r4 = r4.toString()
                r3.append(r4)
                java.lang.String r4 = " has been created"
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                r0.info(r2, r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CleverTapAPI.k.call():java.lang.Void");
        }
    }

    /* loaded from: classes2.dex */
    public class l implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;
        public final /* synthetic */ CleverTapAPI j;
        public final /* synthetic */ String k;
        public final /* synthetic */ CharSequence l;
        public final /* synthetic */ int m;
        public final /* synthetic */ String n;
        public final /* synthetic */ String o;
        public final /* synthetic */ boolean p;

        public l(Context context, String str, CleverTapAPI cleverTapAPI, String str2, CharSequence charSequence, int i, String str3, String str4, boolean z) {
            this.h = context;
            this.i = str;
            this.j = cleverTapAPI;
            this.k = str2;
            this.l = charSequence;
            this.m = i;
            this.n = str3;
            this.o = str4;
            this.p = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00af  */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void call() {
            /*
                r7 = this;
                android.content.Context r0 = r7.h
                java.lang.String r1 = "notification"
                java.lang.Object r0 = r0.getSystemService(r1)
                android.app.NotificationManager r0 = (android.app.NotificationManager) r0
                r1 = 0
                if (r0 != 0) goto Le
                return r1
            Le:
                java.lang.String r2 = r7.i
                boolean r2 = r2.isEmpty()
                if (r2 != 0) goto L80
                java.lang.String r2 = r7.i
                java.lang.String r3 = ".mp3"
                boolean r2 = r2.contains(r3)
                if (r2 != 0) goto L49
                java.lang.String r2 = r7.i
                java.lang.String r3 = ".ogg"
                boolean r2 = r2.contains(r3)
                if (r2 != 0) goto L49
                java.lang.String r2 = r7.i
                java.lang.String r3 = ".wav"
                boolean r2 = r2.contains(r3)
                if (r2 == 0) goto L35
                goto L49
            L35:
                com.clevertap.android.sdk.CleverTapAPI r2 = r7.j
                com.clevertap.android.sdk.Logger r2 = com.clevertap.android.sdk.CleverTapAPI.b(r2)
                com.clevertap.android.sdk.CleverTapAPI r3 = r7.j
                java.lang.String r3 = r3.getAccountId()
                java.lang.String r4 = "Sound file name not supported"
                r2.debug(r3, r4)
                java.lang.String r2 = ""
                goto L56
            L49:
                java.lang.String r2 = r7.i
                r3 = 0
                int r4 = r2.length()
                int r4 = r4 + (-4)
                java.lang.String r2 = r2.substring(r3, r4)
            L56:
                boolean r3 = r2.isEmpty()
                if (r3 != 0) goto L80
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "android.resource://"
                r3.append(r4)
                android.content.Context r4 = r7.h
                java.lang.String r4 = r4.getPackageName()
                r3.append(r4)
                java.lang.String r4 = "/raw/"
                r3.append(r4)
                r3.append(r2)
                java.lang.String r2 = r3.toString()
                android.net.Uri r2 = android.net.Uri.parse(r2)
                goto L81
            L80:
                r2 = r1
            L81:
                android.app.NotificationChannel r3 = new android.app.NotificationChannel
                java.lang.String r4 = r7.k
                java.lang.CharSequence r5 = r7.l
                int r6 = r7.m
                r3.<init>(r4, r5, r6)
                java.lang.String r4 = r7.n
                r3.setDescription(r4)
                java.lang.String r4 = r7.o
                r3.setGroup(r4)
                boolean r4 = r7.p
                r3.setShowBadge(r4)
                if (r2 == 0) goto Laf
                android.media.AudioAttributes$Builder r4 = new android.media.AudioAttributes$Builder
                r4.<init>()
                r5 = 5
                android.media.AudioAttributes$Builder r4 = r4.setUsage(r5)
                android.media.AudioAttributes r4 = r4.build()
                r3.setSound(r2, r4)
                goto Lc0
            Laf:
                com.clevertap.android.sdk.CleverTapAPI r2 = r7.j
                com.clevertap.android.sdk.Logger r2 = com.clevertap.android.sdk.CleverTapAPI.b(r2)
                com.clevertap.android.sdk.CleverTapAPI r4 = r7.j
                java.lang.String r4 = r4.getAccountId()
                java.lang.String r5 = "Sound file not found, notification channel will be created without custom sound"
                r2.debug(r4, r5)
            Lc0:
                r0.createNotificationChannel(r3)
                com.clevertap.android.sdk.CleverTapAPI r0 = r7.j
                com.clevertap.android.sdk.Logger r0 = com.clevertap.android.sdk.CleverTapAPI.b(r0)
                com.clevertap.android.sdk.CleverTapAPI r2 = r7.j
                java.lang.String r2 = r2.getAccountId()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Notification channel "
                r3.append(r4)
                java.lang.CharSequence r4 = r7.l
                java.lang.String r4 = r4.toString()
                r3.append(r4)
                java.lang.String r4 = " has been created"
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                r0.info(r2, r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CleverTapAPI.l.call():java.lang.Void");
        }
    }

    /* loaded from: classes2.dex */
    public class m implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;
        public final /* synthetic */ CharSequence j;
        public final /* synthetic */ CleverTapAPI k;

        public m(Context context, String str, CharSequence charSequence, CleverTapAPI cleverTapAPI) {
            this.h = context;
            this.i = str;
            this.j = charSequence;
            this.k = cleverTapAPI;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            NotificationManager notificationManager = (NotificationManager) this.h.getSystemService("notification");
            if (notificationManager == null) {
                return null;
            }
            notificationManager.createNotificationChannelGroup(new NotificationChannelGroup(this.i, this.j));
            Logger n = this.k.n();
            String accountId = this.k.getAccountId();
            n.info(accountId, "Notification channel group " + this.j.toString() + " has been created");
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class n implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;
        public final /* synthetic */ CleverTapAPI j;

        public n(Context context, String str, CleverTapAPI cleverTapAPI) {
            this.h = context;
            this.i = str;
            this.j = cleverTapAPI;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            NotificationManager notificationManager = (NotificationManager) this.h.getSystemService("notification");
            if (notificationManager == null) {
                return null;
            }
            notificationManager.deleteNotificationChannel(this.i);
            Logger n = this.j.n();
            String accountId = this.j.getAccountId();
            n.info(accountId, "Notification channel " + this.i + " has been deleted");
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class o implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;
        public final /* synthetic */ CleverTapAPI j;

        public o(Context context, String str, CleverTapAPI cleverTapAPI) {
            this.h = context;
            this.i = str;
            this.j = cleverTapAPI;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            NotificationManager notificationManager = (NotificationManager) this.h.getSystemService("notification");
            if (notificationManager == null) {
                return null;
            }
            notificationManager.deleteNotificationChannelGroup(this.i);
            Logger n = this.j.n();
            String accountId = this.j.getAccountId();
            n.info(accountId, "Notification channel group " + this.i + " has been deleted");
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class p implements Callable<Void> {
        public p() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (CleverTapAPI.this.getCleverTapID() != null) {
                CleverTapAPI.this.b.getLoginController().recordDeviceIDErrors();
                return null;
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class q implements Callable<Void> {
        public final /* synthetic */ CleverTapInstanceConfig h;

        public q(CleverTapInstanceConfig cleverTapInstanceConfig) {
            this.h = cleverTapInstanceConfig;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (this.h.isDefaultInstance()) {
                CleverTapAPI.this.t();
                return null;
            }
            return null;
        }
    }

    public CleverTapAPI(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        this.f2571a = context;
        w(com.clevertap.android.sdk.k.b(context, cleverTapInstanceConfig, str));
        Logger n2 = n();
        n2.verbose(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "CoreState is set");
        CTExecutorFactory.executors(cleverTapInstanceConfig).postAsyncSafelyTask().execute("CleverTapAPI#initializeDeviceInfo", new q(cleverTapInstanceConfig));
        if (Utils.getNow() - CoreMetaData.e() > 5) {
            this.b.getConfig().e();
        }
        CTExecutorFactory.executors(cleverTapInstanceConfig).postAsyncSafelyTask().execute("setStatesAsync", new a());
        CTExecutorFactory.executors(cleverTapInstanceConfig).postAsyncSafelyTask().execute("saveConfigtoSharedPrefs", new b(this, cleverTapInstanceConfig, context));
        Logger.i("CleverTap SDK initialized with accountId: " + cleverTapInstanceConfig.getAccountId() + " accountToken: " + cleverTapInstanceConfig.getAccountToken() + " accountRegion: " + cleverTapInstanceConfig.getAccountRegion());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void addNotificationRenderedListener(String str, NotificationRenderedListener notificationRenderedListener) {
        j.put(str, notificationRenderedListener);
    }

    public static void changeCredentials(String str, String str2) {
        changeCredentials(str, str2, null);
    }

    public static void changeXiaomiCredentials(String str, String str2) {
        ManifestInfo.c(str, str2);
    }

    public static void createNotification(Context context, Bundle bundle, int i2) {
        CleverTapAPI l2 = l(context, bundle);
        if (l2 != null) {
            CoreState coreState = l2.b;
            CleverTapInstanceConfig config = coreState.getConfig();
            try {
                synchronized (coreState.getPushProviders().getPushRenderingLock()) {
                    coreState.getPushProviders().setPushNotificationRenderer(new CoreNotificationRenderer());
                    coreState.getPushProviders()._createNotification(context, bundle, i2);
                }
            } catch (Throwable th) {
                config.getLogger().debug(config.getAccountId(), "Failed to process createNotification()", th);
            }
        }
    }

    public static void createNotificationChannel(Context context, String str, CharSequence charSequence, String str2, int i2, boolean z) {
        CleverTapAPI p2 = p(context);
        if (p2 == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(p2.b.getConfig()).postAsyncSafelyTask().execute("createNotificationChannel", new i(context, str, charSequence, i2, str2, z, p2));
            }
        } catch (Throwable th) {
            p2.n().verbose(p2.getAccountId(), "Failure creating Notification Channel", th);
        }
    }

    @RequiresApi(api = 26)
    public static void createNotificationChannelGroup(Context context, String str, CharSequence charSequence) {
        CleverTapAPI p2 = p(context);
        if (p2 == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificationChannelGroup");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(p2.b.getConfig()).postAsyncSafelyTask().execute("creatingNotificationChannelGroup", new m(context, str, charSequence, p2));
            }
        } catch (Throwable th) {
            p2.n().verbose(p2.getAccountId(), "Failure creating Notification Channel Group", th);
        }
    }

    @RequiresApi(api = 26)
    public static void deleteNotificationChannel(Context context, String str) {
        CleverTapAPI p2 = p(context);
        if (p2 == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#deleteNotificationChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(p2.b.getConfig()).postAsyncSafelyTask().execute("deletingNotificationChannel", new n(context, str, p2));
            }
        } catch (Throwable th) {
            p2.n().verbose(p2.getAccountId(), "Failure deleting Notification Channel", th);
        }
    }

    @RequiresApi(api = 26)
    public static void deleteNotificationChannelGroup(Context context, String str) {
        CleverTapAPI p2 = p(context);
        if (p2 == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#deleteNotificationChannelGroup");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(p2.b.getConfig()).postAsyncSafelyTask().execute("deletingNotificationChannelGroup", new o(context, str, p2));
            }
        } catch (Throwable th) {
            p2.n().verbose(p2.getAccountId(), "Failure deleting Notification Channel Group", th);
        }
    }

    public static void enableXiaomiPushOn(int i2) {
        PushConstants.PushType.XPS.setRunningDevices(i2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Deprecated
    public static void fcmTokenRefresh(Context context, String str) {
        Iterator<CleverTapAPI> it = getAvailableInstances(context).iterator();
        while (it.hasNext()) {
            CleverTapAPI next = it.next();
            if (next != null && !next.getCoreState().getConfig().isAnalyticsOnly()) {
                next.getCoreState().getPushProviders().doTokenRefresh(str, PushConstants.PushType.FCM);
            } else {
                Logger.d("Instance is Analytics Only not processing device token");
            }
        }
    }

    public static boolean g(Context context, Bundle bundle, long j2) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Logger.v("Notification Bitmap Download is not allowed on main thread");
            return true;
        } else if (context == null) {
            Logger.v("Given Context is null. Not downloading bitmap!");
            return true;
        } else if (bundle == null) {
            Logger.v("Given Bundle is null. Not downloading bitmap!");
            return true;
        } else if (j2 < 1) {
            Logger.v("Given timeoutInMillis is less than 1 millis. Not downloading bitmap!");
            return true;
        } else if (j2 > 20000) {
            Logger.v("Given timeoutInMillis exceeds 20 secs limit. Not downloading bitmap!");
            return true;
        } else {
            return false;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static ArrayList<CleverTapAPI> getAvailableInstances(Context context) {
        ArrayList<CleverTapAPI> arrayList = new ArrayList<>();
        HashMap<String, CleverTapAPI> hashMap = g;
        if (hashMap != null && !hashMap.isEmpty()) {
            arrayList.addAll(g.values());
        } else {
            CleverTapAPI defaultInstance = getDefaultInstance(context);
            if (defaultInstance != null) {
                arrayList.add(defaultInstance);
            }
        }
        return arrayList;
    }

    public static int getDebugLevel() {
        return e;
    }

    public static CleverTapAPI getDefaultInstance(Context context, String str) {
        CleverTapInstanceConfig cleverTapInstanceConfig = f;
        if (cleverTapInstanceConfig != null) {
            return instanceWithConfig(context, cleverTapInstanceConfig, str);
        }
        CleverTapInstanceConfig o2 = o(context);
        f = o2;
        if (o2 != null) {
            return instanceWithConfig(context, o2, str);
        }
        return null;
    }

    public static int getEnableXiaomiPushOn() {
        return PushConstants.PushType.XPS.getRunningDevices();
    }

    @Nullable
    public static CleverTapAPI getGlobalInstance(Context context, String str) {
        return k(context, str);
    }

    public static HashMap<String, CleverTapAPI> getInstances() {
        return g;
    }

    @Nullable
    public static Bitmap getNotificationBitmapWithTimeout(Context context, Bundle bundle, String str, boolean z, long j2) {
        if (g(context, bundle, j2)) {
            return null;
        }
        CleverTapAPI l2 = l(context, bundle);
        if (l2 == null) {
            Logger.v("cleverTapAPI is null. Not downloading bitmap!");
            return null;
        }
        return Utils.getNotificationBitmapWithTimeout(str, z, context, l2.m(), j2).getBitmap();
    }

    @Nullable
    public static Bitmap getNotificationBitmapWithTimeoutAndSize(Context context, Bundle bundle, String str, boolean z, long j2, int i2) {
        if (g(context, bundle, j2)) {
            return null;
        }
        if (i2 < 1) {
            Logger.v("Given sizeInBytes is less than 1 bytes. Not downloading bitmap!");
            return null;
        }
        CleverTapAPI l2 = l(context, bundle);
        if (l2 == null) {
            Logger.v("cleverTapAPI is null. Not downloading bitmap!");
            return null;
        }
        return Utils.getNotificationBitmapWithTimeoutAndSize(str, z, context, l2.m(), j2, i2).getBitmap();
    }

    public static NotificationHandler getNotificationHandler() {
        return h;
    }

    public static NotificationInfo getNotificationInfo(Bundle bundle) {
        boolean z = false;
        if (bundle == null) {
            return new NotificationInfo(false, false);
        }
        boolean containsKey = bundle.containsKey("wzrk_pn");
        if (containsKey && bundle.containsKey(Constants.NOTIF_MSG)) {
            z = true;
        }
        return new NotificationInfo(containsKey, z);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static NotificationRenderedListener getNotificationRenderedListener(String str) {
        return j.get(str);
    }

    public static NotificationHandler getSignedCallNotificationHandler() {
        return i;
    }

    public static CleverTapAPI h(Context context, String str) {
        return i(context, str, null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void handleNotificationClicked(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        String str = null;
        try {
            str = bundle.getString(Constants.WZRK_ACCT_ID_KEY);
        } catch (Throwable unused) {
        }
        HashMap<String, CleverTapAPI> hashMap = g;
        if (hashMap == null) {
            CleverTapAPI h2 = h(context, str);
            if (h2 != null) {
                h2.pushNotificationClickedEvent(bundle);
                return;
            }
            return;
        }
        for (String str2 : hashMap.keySet()) {
            CleverTapAPI cleverTapAPI = g.get(str2);
            boolean z = false;
            if (cleverTapAPI != null && ((str == null && cleverTapAPI.b.getConfig().isDefaultInstance()) || cleverTapAPI.getAccountId().equals(str))) {
                z = true;
                continue;
            }
            if (z) {
                cleverTapAPI.pushNotificationClickedEvent(bundle);
                return;
            }
        }
    }

    @Nullable
    public static CleverTapAPI i(Context context, String str, String str2) {
        try {
            if (str == null) {
                return getDefaultInstance(context, str2);
            }
            String string = StorageHelper.getString(context, "instance:" + str, "");
            if (!string.isEmpty()) {
                CleverTapInstanceConfig createInstance = CleverTapInstanceConfig.createInstance(string);
                Logger.v("Inflated Instance Config: " + string);
                if (createInstance != null) {
                    return instanceWithConfig(context, createInstance, str2);
                }
                return null;
            }
            CleverTapAPI defaultInstance = getDefaultInstance(context);
            if (defaultInstance == null || !defaultInstance.b.getConfig().getAccountId().equals(str)) {
                return null;
            }
            return defaultInstance;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static CleverTapAPI instanceWithConfig(Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        return instanceWithConfig(context, cleverTapInstanceConfig, null);
    }

    public static boolean isAppForeground() {
        return CoreMetaData.isAppForeground();
    }

    public static CleverTapAPI k(Context context, String str) {
        HashMap<String, CleverTapAPI> hashMap = g;
        if (hashMap == null) {
            return h(context, str);
        }
        for (String str2 : hashMap.keySet()) {
            CleverTapAPI cleverTapAPI = g.get(str2);
            boolean z = false;
            if (cleverTapAPI != null && ((str == null && cleverTapAPI.b.getConfig().isDefaultInstance()) || cleverTapAPI.getAccountId().equals(str))) {
                z = true;
                continue;
            }
            if (z) {
                return cleverTapAPI;
            }
        }
        return null;
    }

    public static CleverTapAPI l(Context context, Bundle bundle) {
        return k(context, bundle.getString(Constants.WZRK_ACCT_ID_KEY));
    }

    public static CleverTapInstanceConfig o(Context context) {
        ManifestInfo manifestInfo = ManifestInfo.getInstance(context);
        String accountId = manifestInfo.getAccountId();
        String e2 = manifestInfo.e();
        String accountRegion = manifestInfo.getAccountRegion();
        if (accountId != null && e2 != null) {
            if (accountRegion == null) {
                Logger.i("Account Region not specified in the AndroidManifest - using default region");
            }
            return CleverTapInstanceConfig.createDefaultInstance(context, accountId, e2, accountRegion);
        }
        Logger.i("Account ID or Account token is missing from AndroidManifest.xml, unable to create default instance");
        return null;
    }

    public static void onActivityPaused() {
        HashMap<String, CleverTapAPI> hashMap = g;
        if (hashMap == null) {
            return;
        }
        for (String str : hashMap.keySet()) {
            CleverTapAPI cleverTapAPI = g.get(str);
            if (cleverTapAPI != null) {
                try {
                    cleverTapAPI.b.getActivityLifeCycleManager().f();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static void onActivityResumed(Activity activity) {
        onActivityResumed(activity, null);
    }

    @Nullable
    public static CleverTapAPI p(Context context) {
        HashMap<String, CleverTapAPI> hashMap;
        CleverTapAPI defaultInstance = getDefaultInstance(context);
        if (defaultInstance == null && (hashMap = g) != null && !hashMap.isEmpty()) {
            for (String str : g.keySet()) {
                defaultInstance = g.get(str);
                if (defaultInstance != null) {
                    break;
                }
            }
        }
        return defaultInstance;
    }

    public static void processPushNotification(Context context, Bundle bundle) {
        CleverTapAPI l2 = l(context, bundle);
        if (l2 != null) {
            l2.b.getPushProviders().processCustomPushNotification(bundle);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static NotificationRenderedListener removeNotificationRenderedListener(String str) {
        return j.remove(str);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void runBackgroundIntentService(Context context) {
        HashMap<String, CleverTapAPI> hashMap = g;
        if (hashMap == null) {
            CleverTapAPI defaultInstance = getDefaultInstance(context);
            if (defaultInstance != null) {
                if (defaultInstance.m().isBackgroundSync()) {
                    defaultInstance.b.getPushProviders().runInstanceJobWork(context, null);
                    return;
                } else {
                    Logger.d("Instance doesn't allow Background sync, not running the Job");
                    return;
                }
            }
            return;
        }
        for (String str : hashMap.keySet()) {
            CleverTapAPI cleverTapAPI = g.get(str);
            if (cleverTapAPI != null) {
                if (cleverTapAPI.m().isAnalyticsOnly()) {
                    Logger.d(str, "Instance is Analytics Only not processing device token");
                } else if (!cleverTapAPI.m().isBackgroundSync()) {
                    Logger.d(str, "Instance doesn't allow Background sync, not running the Job");
                } else {
                    cleverTapAPI.b.getPushProviders().runInstanceJobWork(context, null);
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void runJobWork(Context context, JobParameters jobParameters) {
        HashMap<String, CleverTapAPI> hashMap = g;
        if (hashMap == null) {
            CleverTapAPI defaultInstance = getDefaultInstance(context);
            if (defaultInstance != null) {
                if (defaultInstance.m().isBackgroundSync()) {
                    defaultInstance.b.getPushProviders().runInstanceJobWork(context, jobParameters);
                    return;
                } else {
                    Logger.d("Instance doesn't allow Background sync, not running the Job");
                    return;
                }
            }
            return;
        }
        for (String str : hashMap.keySet()) {
            CleverTapAPI cleverTapAPI = g.get(str);
            if (cleverTapAPI != null && cleverTapAPI.m().isAnalyticsOnly()) {
                Logger.d(str, "Instance is Analytics Only not running the Job");
            } else if (cleverTapAPI != null && cleverTapAPI.m().isBackgroundSync()) {
                cleverTapAPI.b.getPushProviders().runInstanceJobWork(context, jobParameters);
            } else {
                Logger.d(str, "Instance doesn't allow Background sync, not running the Job");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(String str) {
        JSONObject defineVarsData = this.b.getVarCache().getDefineVarsData();
        Logger.v("variables", "syncVariables: sending following vars to server:" + defineVarsData);
        this.b.getAnalyticsManager().pushDefineVarsEvent(defineVarsData);
    }

    public static void setAppForeground(boolean z) {
        CoreMetaData.setAppForeground(z);
    }

    public static void setDebugLevel(int i2) {
        e = i2;
    }

    public static void setInstances(HashMap<String, CleverTapAPI> hashMap) {
        g = hashMap;
    }

    public static void setNotificationHandler(NotificationHandler notificationHandler) {
        h = notificationHandler;
    }

    public static void setSignedCallNotificationHandler(NotificationHandler notificationHandler) {
        i = notificationHandler;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void tokenRefresh(Context context, String str, PushConstants.PushType pushType) {
        Iterator<CleverTapAPI> it = getAvailableInstances(context).iterator();
        while (it.hasNext()) {
            it.next().b.getPushProviders().doTokenRefresh(str, pushType);
        }
    }

    public static void u(Activity activity) {
        v(activity, null);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:9|10|11|(7:57|58|14|15|16|(7:20|(1:22)|35|(2:32|33)|25|(2:28|29)|27)|(5:39|40|(4:43|(3:45|46|47)(1:49)|48|41)|50|51)(1:38))|13|14|15|16|(8:18|20|(0)|35|(0)|25|(0)|27)|(0)|39|40|(1:41)|50|51) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0055, code lost:
        if (com.clevertap.android.sdk.Constants.WZRK_FROM.equals(r3.get(com.clevertap.android.sdk.Constants.WZRK_FROM_KEY)) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b A[Catch: all -> 0x0089, TRY_LEAVE, TryCatch #4 {all -> 0x0089, blocks: (B:17:0x0035, B:19:0x003f, B:21:0x0045, B:23:0x004b), top: B:58:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0081 A[Catch: all -> 0x0079, TRY_LEAVE, TryCatch #3 {all -> 0x0079, blocks: (B:28:0x005b, B:30:0x007b, B:32:0x0081), top: B:56:0x005b }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009e A[Catch: all -> 0x00b8, TryCatch #2 {all -> 0x00b8, blocks: (B:37:0x008e, B:38:0x0098, B:40:0x009e, B:42:0x00ae), top: B:54:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void v(android.app.Activity r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "wzrk_from"
            java.lang.String r1 = "wzrk_acct_id"
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r2 = com.clevertap.android.sdk.CleverTapAPI.g
            r3 = 0
            if (r2 != 0) goto L10
            android.content.Context r2 = r6.getApplicationContext()
            i(r2, r3, r7)
        L10:
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r7 = com.clevertap.android.sdk.CleverTapAPI.g
            if (r7 != 0) goto L1a
            java.lang.String r6 = "Instances is null in onActivityCreated!"
            com.clevertap.android.sdk.Logger.v(r6)
            return
        L1a:
            r7 = 1
            android.content.Intent r2 = r6.getIntent()     // Catch: java.lang.Throwable -> L32
            android.net.Uri r2 = r2.getData()     // Catch: java.lang.Throwable -> L32
            if (r2 == 0) goto L33
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L33
            android.os.Bundle r4 = com.clevertap.android.sdk.utils.UriHelper.getAllKeyValuePairs(r4, r7)     // Catch: java.lang.Throwable -> L33
            java.lang.String r4 = r4.getString(r1)     // Catch: java.lang.Throwable -> L33
            goto L34
        L32:
            r2 = r3
        L33:
            r4 = r3
        L34:
            r5 = 0
            android.content.Intent r6 = r6.getIntent()     // Catch: java.lang.Throwable -> L89
            android.os.Bundle r3 = r6.getExtras()     // Catch: java.lang.Throwable -> L89
            if (r3 == 0) goto L89
            boolean r6 = r3.isEmpty()     // Catch: java.lang.Throwable -> L89
            if (r6 != 0) goto L89
            boolean r6 = r3.containsKey(r0)     // Catch: java.lang.Throwable -> L89
            if (r6 == 0) goto L58
            java.lang.String r6 = "CTPushNotificationReceiver"
            java.lang.Object r0 = r3.get(r0)     // Catch: java.lang.Throwable -> L89
            boolean r6 = r6.equals(r0)     // Catch: java.lang.Throwable -> L89
            if (r6 == 0) goto L58
            goto L59
        L58:
            r7 = r5
        L59:
            if (r7 == 0) goto L7b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r6.<init>()     // Catch: java.lang.Throwable -> L79
            java.lang.String r0 = "ActivityLifecycleCallback: Notification Clicked already processed for "
            r6.append(r0)     // Catch: java.lang.Throwable -> L79
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L79
            r6.append(r0)     // Catch: java.lang.Throwable -> L79
            java.lang.String r0 = ", dropping duplicate."
            r6.append(r0)     // Catch: java.lang.Throwable -> L79
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L79
            com.clevertap.android.sdk.Logger.v(r6)     // Catch: java.lang.Throwable -> L79
            goto L7b
        L79:
            r5 = r7
            goto L89
        L7b:
            boolean r6 = r3.containsKey(r1)     // Catch: java.lang.Throwable -> L79
            if (r6 == 0) goto L79
            java.lang.Object r6 = r3.get(r1)     // Catch: java.lang.Throwable -> L79
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Throwable -> L79
            r4 = r6
            goto L79
        L89:
            if (r5 == 0) goto L8e
            if (r2 != 0) goto L8e
            return
        L8e:
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r6 = com.clevertap.android.sdk.CleverTapAPI.g     // Catch: java.lang.Throwable -> Lb8
            java.util.Set r6 = r6.keySet()     // Catch: java.lang.Throwable -> Lb8
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> Lb8
        L98:
            boolean r7 = r6.hasNext()     // Catch: java.lang.Throwable -> Lb8
            if (r7 == 0) goto Ld1
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> Lb8
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r0 = com.clevertap.android.sdk.CleverTapAPI.g     // Catch: java.lang.Throwable -> Lb8
            java.lang.Object r7 = r0.get(r7)     // Catch: java.lang.Throwable -> Lb8
            com.clevertap.android.sdk.CleverTapAPI r7 = (com.clevertap.android.sdk.CleverTapAPI) r7     // Catch: java.lang.Throwable -> Lb8
            if (r7 == 0) goto L98
            com.clevertap.android.sdk.CoreState r7 = r7.b     // Catch: java.lang.Throwable -> Lb8
            com.clevertap.android.sdk.a r7 = r7.getActivityLifeCycleManager()     // Catch: java.lang.Throwable -> Lb8
            r7.i(r3, r2, r4)     // Catch: java.lang.Throwable -> Lb8
            goto L98
        Lb8:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "Throwable - "
            r7.append(r0)
            java.lang.String r6 = r6.getLocalizedMessage()
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.clevertap.android.sdk.Logger.v(r6)
        Ld1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CleverTapAPI.v(android.app.Activity, java.lang.String):void");
    }

    public void addMultiValueForKey(String str, String str2) {
        if (str2 != null && !str2.isEmpty()) {
            addMultiValuesForKey(str, new ArrayList<>(Collections.singletonList(str2)));
        } else {
            this.b.getAnalyticsManager().d(str);
        }
    }

    public void addMultiValuesForKey(String str, ArrayList<String> arrayList) {
        this.b.getAnalyticsManager().addMultiValuesForKey(str, arrayList);
    }

    public void addOneTimeVariablesChangedCallback(@NonNull VariablesChangedCallback variablesChangedCallback) {
        this.b.getCTVariables().addOneTimeVariablesChangedCallback(variablesChangedCallback);
    }

    public void addVariablesChangedCallback(@NonNull VariablesChangedCallback variablesChangedCallback) {
        this.b.getCTVariables().addVariablesChangedCallback(variablesChangedCallback);
    }

    public void decrementValue(String str, Number number) {
        this.b.getAnalyticsManager().decrementValue(str, number);
    }

    public <T> Var<T> defineVariable(String str, T t) {
        return Var.define(str, t, this.b.getCTVariables());
    }

    public void deleteInboxMessage(CTInboxMessage cTInboxMessage) {
        if (this.b.getControllerManager().getCTInboxController() != null) {
            this.b.getControllerManager().getCTInboxController().deleteInboxMessage(cTInboxMessage);
        } else {
            n().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    public void deleteInboxMessagesForIDs(ArrayList<String> arrayList) {
        if (this.b.getControllerManager().getCTInboxController() != null) {
            this.b.getControllerManager().getCTInboxController().deleteInboxMessagesForIDs(arrayList);
        } else {
            n().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    public void disablePersonalization() {
        this.b.getConfig().enablePersonalization(false);
    }

    public void discardInAppNotifications() {
        if (!getCoreState().getConfig().isAnalyticsOnly()) {
            n().debug(getAccountId(), "Discarding InApp Notifications...");
            n().debug(getAccountId(), "Please Note - InApp Notifications will be dropped till resumeInAppNotifications() is not called again");
            getCoreState().getInAppController().discardInApps();
            return;
        }
        n().debug(getAccountId(), "CleverTap instance is set for Analytics only! Cannot discard InApp Notifications.");
    }

    public void dismissAppInbox() {
        try {
            Activity appInboxActivity = getCoreState().getCoreMetaData().getAppInboxActivity();
            if (appInboxActivity != null) {
                if (appInboxActivity.isFinishing()) {
                    return;
                }
                n().verbose(getAccountId(), "Finishing the App Inbox");
                appInboxActivity.finish();
                return;
            }
            throw new IllegalStateException("AppInboxActivity reference not found");
        } catch (Throwable th) {
            Logger n2 = n();
            String accountId = getAccountId();
            n2.verbose(accountId, "Can't dismiss AppInbox, please ensure to call this method after the usage of cleverTapApiInstance.showAppInbox(). \n" + th);
        }
    }

    public void enableDeviceNetworkInfoReporting(boolean z) {
        this.b.getDeviceInfo().h(z);
    }

    public void enablePersonalization() {
        this.b.getConfig().enablePersonalization(true);
    }

    @Deprecated
    public CTFeatureFlagsController featureFlag() {
        if (m().isAnalyticsOnly()) {
            m().getLogger().debug(getAccountId(), "Feature flag is not supported with analytics only configuration");
        }
        return this.b.getControllerManager().getCTFeatureFlagsController();
    }

    public void fetchVariables() {
        fetchVariables(null);
    }

    public void flush() {
        this.b.getBaseEventQueueManager().flush();
    }

    public String getAccountId() {
        return this.b.getConfig().getAccountId();
    }

    @Nullable
    public ArrayList<CleverTapDisplayUnit> getAllDisplayUnits() {
        if (this.b.getControllerManager().getCTDisplayUnitController() != null) {
            return this.b.getControllerManager().getCTDisplayUnitController().getAllDisplayUnits();
        }
        n().verbose(getAccountId(), "DisplayUnit : Failed to get all Display Units");
        return null;
    }

    public ArrayList<CTInboxMessage> getAllInboxMessages() {
        Logger.d("CleverTapAPI:getAllInboxMessages: called");
        ArrayList<CTInboxMessage> arrayList = new ArrayList<>();
        synchronized (this.b.getCTLockManager().getInboxControllerLock()) {
            if (this.b.getControllerManager().getCTInboxController() != null) {
                Iterator<CTMessageDAO> it = this.b.getControllerManager().getCTInboxController().getMessages().iterator();
                while (it.hasNext()) {
                    CTMessageDAO next = it.next();
                    Logger.v("CTMessage Dao - " + next.toJSON().toString());
                    arrayList.add(new CTInboxMessage(next.toJSON()));
                }
                return arrayList;
            }
            n().debug(getAccountId(), "Notification Inbox not initialized");
            return arrayList;
        }
    }

    public CTInboxListener getCTNotificationInboxListener() {
        return this.b.getCallbackManager().getInboxListener();
    }

    public CTPushAmpListener getCTPushAmpListener() {
        return this.b.getCallbackManager().getPushAmpListener();
    }

    public CTPushNotificationListener getCTPushNotificationListener() {
        return this.b.getCallbackManager().getPushNotificationListener();
    }

    @WorkerThread
    @Deprecated
    public String getCleverTapAttributionIdentifier() {
        return this.b.getDeviceInfo().l();
    }

    @WorkerThread
    public String getCleverTapID() {
        return this.b.getDeviceInfo().getDeviceID();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public CoreState getCoreState() {
        return this.b;
    }

    public int getCount(String str) {
        EventDetail s = this.b.getLocalDataStore().s(str);
        if (s != null) {
            return s.getCount();
        }
        return -1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getCustomSdkVersion(String str) {
        return this.b.getCoreMetaData().getCustomSdkVersion(str);
    }

    public EventDetail getDetails(String str) {
        return this.b.getLocalDataStore().s(str);
    }

    public String getDevicePushToken(PushConstants.PushType pushType) {
        return this.b.getPushProviders().getCachedToken(pushType);
    }

    public DevicePushTokenRefreshListener getDevicePushTokenRefreshListener() {
        return this.b.getPushProviders().getDevicePushTokenRefreshListener();
    }

    @Nullable
    public CleverTapDisplayUnit getDisplayUnitForId(String str) {
        if (this.b.getControllerManager().getCTDisplayUnitController() != null) {
            return this.b.getControllerManager().getCTDisplayUnitController().getDisplayUnitForID(str);
        }
        Logger n2 = n();
        String accountId = getAccountId();
        n2.verbose(accountId, "DisplayUnit : Failed to get Display Unit for id: " + str);
        return null;
    }

    public int getFirstTime(String str) {
        EventDetail s = this.b.getLocalDataStore().s(str);
        if (s != null) {
            return s.getFirstTime();
        }
        return -1;
    }

    public GeofenceCallback getGeofenceCallback() {
        return this.b.getCallbackManager().getGeofenceCallback();
    }

    public Map<String, EventDetail> getHistory() {
        return this.b.getLocalDataStore().t(this.f2571a);
    }

    public InAppNotificationListener getInAppNotificationListener() {
        return this.b.getCallbackManager().getInAppNotificationListener();
    }

    public int getInboxMessageCount() {
        synchronized (this.b.getCTLockManager().getInboxControllerLock()) {
            if (this.b.getControllerManager().getCTInboxController() != null) {
                return this.b.getControllerManager().getCTInboxController().count();
            }
            n().debug(getAccountId(), "Notification Inbox not initialized");
            return -1;
        }
    }

    public CTInboxMessage getInboxMessageForId(String str) {
        Logger.d("CleverTapAPI:getInboxMessageForId() called with: messageId = [" + str + "]");
        synchronized (this.b.getCTLockManager().getInboxControllerLock()) {
            if (this.b.getControllerManager().getCTInboxController() != null) {
                CTMessageDAO messageForId = this.b.getControllerManager().getCTInboxController().getMessageForId(str);
                return messageForId != null ? new CTInboxMessage(messageForId.toJSON()) : null;
            }
            n().debug(getAccountId(), "Notification Inbox not initialized");
            return null;
        }
    }

    public int getInboxMessageUnreadCount() {
        synchronized (this.b.getCTLockManager().getInboxControllerLock()) {
            if (this.b.getControllerManager().getCTInboxController() != null) {
                return this.b.getControllerManager().getCTInboxController().unreadCount();
            }
            n().debug(getAccountId(), "Notification Inbox not initialized");
            return -1;
        }
    }

    public int getLastTime(String str) {
        EventDetail s = this.b.getLocalDataStore().s(str);
        if (s != null) {
            return s.getLastTime();
        }
        return -1;
    }

    public Location getLocation() {
        return this.b.a().a();
    }

    public int getPreviousVisitTime() {
        return this.b.getSessionManager().getLastVisitTime();
    }

    public Object getProperty(String str) {
        if (this.b.getConfig().c()) {
            return this.b.getLocalDataStore().x(str);
        }
        return null;
    }

    public String getPushToken(@NonNull PushConstants.PushType pushType) {
        return this.b.getPushProviders().getCachedToken(pushType);
    }

    public int getScreenCount() {
        return CoreMetaData.getActivityCount();
    }

    public SyncListener getSyncListener() {
        return this.b.getCallbackManager().getSyncListener();
    }

    public int getTimeElapsed() {
        int currentSessionId = this.b.getCoreMetaData().getCurrentSessionId();
        if (currentSessionId == 0) {
            return -1;
        }
        return Utils.getNow() - currentSessionId;
    }

    public int getTotalVisits() {
        EventDetail s = this.b.getLocalDataStore().s(Constants.APP_LAUNCHED_EVENT);
        if (s != null) {
            return s.getCount();
        }
        return 0;
    }

    public UTMDetail getUTMDetails() {
        UTMDetail uTMDetail = new UTMDetail();
        uTMDetail.setSource(this.b.getCoreMetaData().getSource());
        uTMDetail.setMedium(this.b.getCoreMetaData().getMedium());
        uTMDetail.setCampaign(this.b.getCoreMetaData().getCampaign());
        return uTMDetail;
    }

    public ArrayList<CTInboxMessage> getUnreadInboxMessages() {
        ArrayList<CTInboxMessage> arrayList = new ArrayList<>();
        synchronized (this.b.getCTLockManager().getInboxControllerLock()) {
            if (this.b.getControllerManager().getCTInboxController() != null) {
                Iterator<CTMessageDAO> it = this.b.getControllerManager().getCTInboxController().getUnreadMessages().iterator();
                while (it.hasNext()) {
                    arrayList.add(new CTInboxMessage(it.next().toJSON()));
                }
                return arrayList;
            }
            n().debug(getAccountId(), "Notification Inbox not initialized");
            return arrayList;
        }
    }

    public <T> Var<T> getVariable(String str) {
        if (str == null) {
            return null;
        }
        return this.b.getVarCache().getVariable(str);
    }

    public Object getVariableValue(String str) {
        if (str == null) {
            return null;
        }
        return this.b.getVarCache().getMergedValue(str);
    }

    public void incrementValue(String str, Number number) {
        this.b.getAnalyticsManager().incrementValue(str, number);
    }

    public void initializeInbox() {
        this.b.getControllerManager().initializeInbox();
    }

    @SuppressLint({"NewApi"})
    public boolean isPushPermissionGranted() {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.f2571a, 32)) {
            return this.b.getInAppController().isPushPermissionGranted();
        }
        return false;
    }

    public void j(String str) {
        String accountId = this.b.getConfig().getAccountId();
        if (this.b.getControllerManager() == null) {
            n().verbose(accountId + ":async_deviceID", "ControllerManager not set yet! Returning from deviceIDCreated()");
            return;
        }
        if (this.b.getControllerManager().getInAppFCManager() == null) {
            n().verbose(accountId + ":async_deviceID", "Initializing InAppFC after Device ID Created = " + str);
            this.b.getControllerManager().setInAppFCManager(new InAppFCManager(this.f2571a, this.b.getConfig(), str));
        }
        CTFeatureFlagsController cTFeatureFlagsController = this.b.getControllerManager().getCTFeatureFlagsController();
        if (cTFeatureFlagsController != null && TextUtils.isEmpty(cTFeatureFlagsController.getGuid())) {
            n().verbose(accountId + ":async_deviceID", "Initializing Feature Flags after Device ID Created = " + str);
            cTFeatureFlagsController.setGuidAndInit(str);
        }
        CTProductConfigController cTProductConfigController = this.b.getControllerManager().getCTProductConfigController();
        if (cTProductConfigController != null && TextUtils.isEmpty(cTProductConfigController.getSettings().getGuid())) {
            n().verbose(accountId + ":async_deviceID", "Initializing Product Config after Device ID Created = " + str);
            cTProductConfigController.setGuidAndInit(str);
        }
        n().verbose(accountId + ":async_deviceID", "Got device id from DeviceInfo, notifying user profile initialized to SyncListener");
        this.b.getCallbackManager().notifyUserProfileInitialized(str);
        if (this.b.getCallbackManager().getOnInitCleverTapIDListener() != null) {
            this.b.getCallbackManager().getOnInitCleverTapIDListener().onInitCleverTapID(str);
        }
    }

    public final CleverTapInstanceConfig m() {
        return this.b.getConfig();
    }

    public void markReadInboxMessage(CTInboxMessage cTInboxMessage) {
        if (this.b.getControllerManager().getCTInboxController() != null) {
            this.b.getControllerManager().getCTInboxController().markReadInboxMessage(cTInboxMessage);
        } else {
            n().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    public void markReadInboxMessagesForIDs(ArrayList<String> arrayList) {
        if (this.b.getControllerManager().getCTInboxController() != null) {
            this.b.getControllerManager().getCTInboxController().markReadInboxMessagesForIDs(arrayList);
        } else {
            n().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxActivity.InboxActivityListener
    public void messageDidClick(CTInboxActivity cTInboxActivity, int i2, CTInboxMessage cTInboxMessage, Bundle bundle, HashMap<String, String> hashMap, int i3) {
        this.b.getAnalyticsManager().z(true, cTInboxMessage, bundle);
        Logger.v("clicked inbox notification.");
        WeakReference<InboxMessageListener> weakReference = this.d;
        if (weakReference != null && weakReference.get() != null) {
            this.d.get().onInboxItemClicked(cTInboxMessage, i2, i3);
        }
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        Logger.v("clicked button of an inbox notification.");
        WeakReference<InboxMessageButtonListener> weakReference2 = this.c;
        if (weakReference2 == null || weakReference2.get() == null) {
            return;
        }
        this.c.get().onInboxButtonClick(hashMap);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxActivity.InboxActivityListener
    public void messageDidShow(CTInboxActivity cTInboxActivity, CTInboxMessage cTInboxMessage, Bundle bundle) {
        CTExecutorFactory.executors(this.b.getConfig()).postAsyncSafelyTask().execute("handleMessageDidShow", new d(cTInboxMessage, bundle));
    }

    public final Logger n() {
        return m().getLogger();
    }

    public void onUserLogin(Map<String, Object> map, String str) {
        this.b.getLoginController().onUserLogin(map, str);
    }

    public void parseVariables(Object... objArr) {
        this.b.getParser().parseVariables(objArr);
    }

    public void parseVariablesForClasses(Class<?>... clsArr) {
        this.b.getParser().parseVariablesForClasses(clsArr);
    }

    @Deprecated
    public CTProductConfigController productConfig() {
        if (m().isAnalyticsOnly()) {
            m().getLogger().debug(getAccountId(), "Product config is not supported with analytics only configuration");
        }
        return this.b.getCtProductConfigController();
    }

    @SuppressLint({"NewApi"})
    public void promptForPushPermission(boolean z) {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.f2571a, 32)) {
            this.b.getInAppController().promptPermission(z);
        } else {
            Logger.v("Ensure your app supports Android 13 to verify permission access for notifications.");
        }
    }

    @SuppressLint({"NewApi"})
    public void promptPushPrimer(JSONObject jSONObject) {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.f2571a, 32)) {
            this.b.getInAppController().promptPushPrimer(jSONObject);
        } else {
            Logger.v("Ensure your app supports Android 13 to verify permission access for notifications.");
        }
    }

    public void pushBaiduRegistrationId(String str, boolean z) {
        this.b.getPushProviders().handleToken(str, PushConstants.PushType.BPS, z);
    }

    public void pushChargedEvent(HashMap<String, Object> hashMap, ArrayList<HashMap<String, Object>> arrayList) {
        this.b.getAnalyticsManager().x(hashMap, arrayList);
    }

    public void pushDeepLink(Uri uri) {
        this.b.getAnalyticsManager().y(uri, false);
    }

    public void pushDisplayUnitClickedEventForID(String str) {
        this.b.getAnalyticsManager().pushDisplayUnitClickedEventForID(str);
    }

    public void pushDisplayUnitViewedEventForID(String str) {
        this.b.getAnalyticsManager().pushDisplayUnitViewedEventForID(str);
    }

    public void pushError(String str, int i2) {
        this.b.getAnalyticsManager().pushError(str, i2);
    }

    public void pushEvent(String str) {
        if (str == null || str.trim().equals("")) {
            return;
        }
        pushEvent(str, null);
    }

    public void pushFcmRegistrationId(String str, boolean z) {
        this.b.getPushProviders().handleToken(str, PushConstants.PushType.FCM, z);
    }

    public void pushGeoFenceError(int i2, String str) {
        this.b.getValidationResultStack().pushValidationResult(new ValidationResult(i2, str));
    }

    public Future<?> pushGeoFenceExitedEvent(JSONObject jSONObject) {
        return this.b.getAnalyticsManager().A(Constants.GEOFENCE_EXITED_EVENT_NAME, jSONObject);
    }

    public Future<?> pushGeofenceEnteredEvent(JSONObject jSONObject) {
        return this.b.getAnalyticsManager().A(Constants.GEOFENCE_ENTERED_EVENT_NAME, jSONObject);
    }

    public void pushHuaweiRegistrationId(String str, boolean z) {
        this.b.getPushProviders().handleToken(str, PushConstants.PushType.HPS, z);
    }

    public void pushInboxNotificationClickedEvent(String str) {
        Logger.v("CleverTapAPI:pushInboxNotificationClickedEvent() called with: messageId = [" + str + "]");
        this.b.getAnalyticsManager().z(true, getInboxMessageForId(str), null);
    }

    public void pushInboxNotificationViewedEvent(String str) {
        Logger.v("CleverTapAPI:pushInboxNotificationViewedEvent() called with: messageId = [" + str + "]");
        this.b.getAnalyticsManager().z(false, getInboxMessageForId(str), null);
    }

    public void pushInstallReferrer(String str) {
        this.b.getAnalyticsManager().pushInstallReferrer(str);
    }

    public void pushNotificationClickedEvent(Bundle bundle) {
        this.b.getAnalyticsManager().pushNotificationClickedEvent(bundle);
    }

    public void pushNotificationViewedEvent(Bundle bundle) {
        this.b.getAnalyticsManager().pushNotificationViewedEvent(bundle);
    }

    public void pushProfile(Map<String, Object> map) {
        this.b.getAnalyticsManager().pushProfile(map);
    }

    public Future<?> pushSignedCallEvent(String str, JSONObject jSONObject) {
        return this.b.getAnalyticsManager().B(str, jSONObject);
    }

    public void pushXiaomiRegistrationId(String str, @NonNull String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            Logger.d("CleverTapApi : region must not be null or empty , use  MiPushClient.getAppRegion(context) to provide appropriate region");
            return;
        }
        Logger.d("CleverTapAPI: client called pushXiaomiRegistrationId called with region:" + str2);
        PushConstants.PushType pushType = PushConstants.PushType.XPS;
        pushType.setServerRegion(str2);
        this.b.getPushProviders().handleToken(str, pushType, z);
    }

    public boolean q() {
        return CTVariables.isDevelopmentMode(this.f2571a);
    }

    public final boolean r() {
        return this.b.getDeviceInfo().isErrorDeviceId();
    }

    public void recordScreen(String str) {
        String screenName = this.b.getCoreMetaData().getScreenName();
        if (str != null) {
            if (screenName == null || screenName.isEmpty() || !screenName.equals(str)) {
                Logger n2 = n();
                String accountId = getAccountId();
                n2.debug(accountId, "Screen changed to " + str);
                this.b.getCoreMetaData().setCurrentScreenName(str);
                this.b.getAnalyticsManager().C(null);
            }
        }
    }

    public void registerPushPermissionNotificationResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.b.getCallbackManager().registerPushPermissionResponseListener(pushPermissionResponseListener);
    }

    public void removeAllOneTimeVariablesChangedCallbacks() {
        this.b.getCTVariables().removeAllOneTimeVariablesChangedCallbacks();
    }

    public void removeAllVariablesChangedCallbacks() {
        this.b.getCTVariables().removeAllVariablesChangedCallbacks();
    }

    public void removeMultiValueForKey(String str, String str2) {
        if (str2 != null && !str2.isEmpty()) {
            removeMultiValuesForKey(str, new ArrayList<>(Collections.singletonList(str2)));
        } else {
            this.b.getAnalyticsManager().d(str);
        }
    }

    public void removeMultiValuesForKey(String str, ArrayList<String> arrayList) {
        this.b.getAnalyticsManager().removeMultiValuesForKey(str, arrayList);
    }

    public void removeOneTimeVariablesChangedCallback(@NonNull VariablesChangedCallback variablesChangedCallback) {
        this.b.getCTVariables().removeOneTimeVariablesChangedHandler(variablesChangedCallback);
    }

    public void removeValueForKey(String str) {
        this.b.getAnalyticsManager().removeValueForKey(str);
    }

    public void removeVariablesChangedCallback(@NonNull VariablesChangedCallback variablesChangedCallback) {
        this.b.getCTVariables().removeVariablesChangedCallback(variablesChangedCallback);
    }

    public Future<?> renderPushNotification(@NonNull INotificationRenderer iNotificationRenderer, Context context, Bundle bundle) {
        CleverTapInstanceConfig config = this.b.getConfig();
        try {
            return CTExecutorFactory.executors(config).postAsyncSafelyTask().submit("CleverTapAPI#renderPushNotification", new h(iNotificationRenderer, bundle, context));
        } catch (Throwable th) {
            config.getLogger().debug(config.getAccountId(), "Failed to process renderPushNotification()", th);
            return null;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void renderPushNotificationOnCallerThread(@NonNull INotificationRenderer iNotificationRenderer, Context context, Bundle bundle) {
        CleverTapInstanceConfig config = this.b.getConfig();
        try {
            synchronized (this.b.getPushProviders().getPushRenderingLock()) {
                Logger logger = config.getLogger();
                String accountId = config.getAccountId();
                logger.verbose(accountId, "rendering push on caller thread with id = " + Thread.currentThread().getId());
                this.b.getPushProviders().setPushNotificationRenderer(iNotificationRenderer);
                if (bundle != null && bundle.containsKey(Constants.PT_NOTIF_ID)) {
                    this.b.getPushProviders()._createNotification(context, bundle, bundle.getInt(Constants.PT_NOTIF_ID));
                } else {
                    this.b.getPushProviders()._createNotification(context, bundle, -1000);
                }
            }
        } catch (Throwable th) {
            config.getLogger().debug(config.getAccountId(), "Failed to process renderPushNotification()", th);
        }
    }

    public void resumeInAppNotifications() {
        if (!getCoreState().getConfig().isAnalyticsOnly()) {
            n().debug(getAccountId(), "Resuming InApp Notifications...");
            getCoreState().getInAppController().resumeInApps();
            return;
        }
        n().debug(getAccountId(), "CleverTap instance is set for Analytics only! Cannot resume InApp Notifications.");
    }

    @Deprecated
    public void setCTFeatureFlagsListener(CTFeatureFlagsListener cTFeatureFlagsListener) {
        this.b.getCallbackManager().setFeatureFlagListener(cTFeatureFlagsListener);
    }

    public void setCTInboxMessageListener(InboxMessageListener inboxMessageListener) {
        this.d = new WeakReference<>(inboxMessageListener);
    }

    public void setCTNotificationInboxListener(CTInboxListener cTInboxListener) {
        this.b.getCallbackManager().setInboxListener(cTInboxListener);
    }

    @Deprecated
    public void setCTProductConfigListener(CTProductConfigListener cTProductConfigListener) {
        this.b.getCallbackManager().setProductConfigListener(cTProductConfigListener);
    }

    public void setCTPushAmpListener(CTPushAmpListener cTPushAmpListener) {
        this.b.getCallbackManager().setPushAmpListener(cTPushAmpListener);
    }

    public void setCTPushNotificationListener(CTPushNotificationListener cTPushNotificationListener) {
        this.b.getCallbackManager().setPushNotificationListener(cTPushNotificationListener);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setCustomSdkVersion(String str, int i2) {
        this.b.getCoreMetaData().setCustomSdkVersion(str, i2);
    }

    public void setDevicePushTokenRefreshListener(DevicePushTokenRefreshListener devicePushTokenRefreshListener) {
        this.b.getPushProviders().setDevicePushTokenRefreshListener(devicePushTokenRefreshListener);
    }

    public void setDisplayUnitListener(DisplayUnitListener displayUnitListener) {
        this.b.getCallbackManager().setDisplayUnitListener(displayUnitListener);
    }

    public void setGeofenceCallback(GeofenceCallback geofenceCallback) {
        this.b.getCallbackManager().setGeofenceCallback(geofenceCallback);
    }

    public void setInAppNotificationButtonListener(InAppNotificationButtonListener inAppNotificationButtonListener) {
        this.b.getCallbackManager().setInAppNotificationButtonListener(inAppNotificationButtonListener);
    }

    public void setInAppNotificationListener(InAppNotificationListener inAppNotificationListener) {
        this.b.getCallbackManager().setInAppNotificationListener(inAppNotificationListener);
    }

    public void setInboxMessageButtonListener(InboxMessageButtonListener inboxMessageButtonListener) {
        this.c = new WeakReference<>(inboxMessageButtonListener);
    }

    public void setLibrary(String str) {
        if (this.b.getDeviceInfo() != null) {
            this.b.getDeviceInfo().y(str);
        }
    }

    public void setLocation(Location location) {
        this.b.a().b(location);
    }

    public Future<?> setLocationForGeofences(Location location, int i2) {
        this.b.getCoreMetaData().setLocationForGeofence(true);
        this.b.getCoreMetaData().setGeofenceSDKVersion(i2);
        return this.b.a().b(location);
    }

    public void setMultiValuesForKey(String str, ArrayList<String> arrayList) {
        this.b.getAnalyticsManager().D(str, arrayList);
    }

    public void setOffline(boolean z) {
        this.b.getCoreMetaData().o(z);
        if (z) {
            n().debug(getAccountId(), "CleverTap Instance has been set to offline, won't send events queue");
            return;
        }
        n().debug(getAccountId(), "CleverTap Instance has been set to online, sending events queue");
        flush();
    }

    public void setOptOut(boolean z) {
        CTExecutorFactory.executors(this.b.getConfig()).postAsyncSafelyTask().execute("setOptOut", new e(z));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequestDevicePushTokenListener(RequestDevicePushTokenListener requestDevicePushTokenListener) {
        try {
            Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Requesting FCM token using googleservices.json");
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new c(this, requestDevicePushTokenListener));
        } catch (Throwable th) {
            Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Error requesting FCM token", th);
            requestDevicePushTokenListener.onDevicePushToken(null, PushConstants.PushType.FCM);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSCDomainListener(SCDomainListener sCDomainListener) {
        String domainFromPrefsOrMetadata;
        this.b.getCallbackManager().setSCDomainListener(sCDomainListener);
        if (this.b.getNetworkManager() == null || (domainFromPrefsOrMetadata = ((NetworkManager) this.b.getNetworkManager()).getDomainFromPrefsOrMetadata(EventGroup.REGULAR)) == null) {
            return;
        }
        sCDomainListener.onSCDomainAvailable(Utils.getSCDomain(domainFromPrefsOrMetadata));
    }

    public void setSyncListener(SyncListener syncListener) {
        this.b.getCallbackManager().setSyncListener(syncListener);
    }

    public void showAppInbox(CTInboxStyleConfig cTInboxStyleConfig) {
        synchronized (this.b.getCTLockManager().getInboxControllerLock()) {
            if (this.b.getControllerManager().getCTInboxController() == null) {
                n().debug(getAccountId(), "Notification Inbox not initialized");
                return;
            }
            CTInboxStyleConfig cTInboxStyleConfig2 = new CTInboxStyleConfig(cTInboxStyleConfig);
            Intent intent = new Intent(this.f2571a, CTInboxActivity.class);
            intent.putExtra("styleConfig", cTInboxStyleConfig2);
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.KEY_CONFIG, m());
            intent.putExtra("configBundle", bundle);
            try {
                Activity currentActivity = CoreMetaData.getCurrentActivity();
                if (currentActivity != null) {
                    currentActivity.startActivity(intent);
                    Logger.d("Displaying Notification Inbox");
                    return;
                }
                throw new IllegalStateException("Current activity reference not found");
            } catch (Throwable th) {
                Logger.v("Please verify the integration of your app. It is not setup to support Notification Inbox yet.", th);
            }
        }
    }

    public void suspendInAppNotifications() {
        if (!getCoreState().getConfig().isAnalyticsOnly()) {
            n().debug(getAccountId(), "Suspending InApp Notifications...");
            n().debug(getAccountId(), "Please Note - InApp Notifications will be suspended till resumeInAppNotifications() is not called again");
            getCoreState().getInAppController().suspendInApps();
            return;
        }
        n().debug(getAccountId(), "CleverTap instance is set for Analytics only! Cannot suspend InApp Notifications.");
    }

    public void syncVariables() {
        if (q()) {
            Logger.v("variables", "syncVariables: waiting for id to be available");
            getCleverTapID(new OnInitCleverTapIDListener() { // from class: com.clevertap.android.sdk.i
                @Override // com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener
                public final void onInitCleverTapID(String str) {
                    CleverTapAPI.this.s(str);
                }
            });
            return;
        }
        Logger.v("variables", "Your app is NOT in development mode, variables data will not be sent to server");
    }

    public final void t() {
        CTExecutorFactory.executors(this.b.getConfig()).postAsyncSafelyTask().execute("Manifest Validation", new f());
    }

    public void unregisterPushPermissionNotificationResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.b.getCallbackManager().unregisterPushPermissionResponseListener(pushPermissionResponseListener);
    }

    public void w(CoreState coreState) {
        this.b = coreState;
    }

    public static void changeCredentials(String str, String str2, String str3) {
        if (f != null) {
            Logger.i("CleverTap SDK already initialized with accountID:" + f.getAccountId() + " and token:" + f.getAccountToken() + ". Cannot change credentials to " + str + " and " + str2);
            return;
        }
        ManifestInfo.b(str, str2, str3);
    }

    public static CleverTapAPI instanceWithConfig(Context context, @NonNull CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        if (cleverTapInstanceConfig == null) {
            Logger.v("CleverTapInstanceConfig cannot be null");
            return null;
        }
        if (g == null) {
            g = new HashMap<>();
        }
        CleverTapAPI cleverTapAPI = g.get(cleverTapInstanceConfig.getAccountId());
        if (cleverTapAPI == null) {
            cleverTapAPI = new CleverTapAPI(context, cleverTapInstanceConfig, str);
            g.put(cleverTapInstanceConfig.getAccountId(), cleverTapAPI);
            CTExecutorFactory.executors(cleverTapAPI.b.getConfig()).postAsyncSafelyTask().execute("recordDeviceIDErrors", new p());
        } else if (cleverTapAPI.r() && cleverTapAPI.m().getEnableCustomCleverTapId() && Utils.validateCTID(str)) {
            cleverTapAPI.b.getLoginController().asyncProfileSwitchUser(null, null, str);
        }
        Logger.v(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "CleverTapAPI instance = " + cleverTapAPI);
        return cleverTapAPI;
    }

    public static void onActivityResumed(Activity activity, String str) {
        if (g == null) {
            i(activity.getApplicationContext(), null, str);
        }
        CoreMetaData.setAppForeground(true);
        if (g == null) {
            Logger.v("Instances is null in onActivityResumed!");
            return;
        }
        String currentActivityName = CoreMetaData.getCurrentActivityName();
        CoreMetaData.setCurrentActivity(activity);
        if (currentActivityName == null || !currentActivityName.equals(activity.getLocalClassName())) {
            CoreMetaData.f();
        }
        if (CoreMetaData.e() <= 0) {
            CoreMetaData.k(Utils.getNow());
        }
        for (String str2 : g.keySet()) {
            CleverTapAPI cleverTapAPI = g.get(str2);
            if (cleverTapAPI != null) {
                try {
                    cleverTapAPI.b.getActivityLifeCycleManager().g(activity);
                } catch (Throwable th) {
                    Logger.v("Throwable - " + th.getLocalizedMessage());
                }
            }
        }
    }

    public static void setDebugLevel(LogLevel logLevel) {
        e = logLevel.intValue();
    }

    public void fetchVariables(FetchVariablesCallback fetchVariablesCallback) {
        if (this.b.getConfig().isAnalyticsOnly()) {
            return;
        }
        Logger.v("variables", "Fetching  variables");
        if (fetchVariablesCallback != null) {
            this.b.getCallbackManager().setFetchVariablesCallback(fetchVariablesCallback);
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, 4);
            jSONObject.put("evtName", Constants.WZRK_FETCH);
            jSONObject.put("evtData", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.b.getAnalyticsManager().sendFetchEvent(jSONObject);
    }

    public void getCleverTapID(@NonNull OnInitCleverTapIDListener onInitCleverTapIDListener) {
        CTExecutorFactory.executors(m()).ioTask().execute("getCleverTapID", new g(onInitCleverTapIDListener));
    }

    public void onUserLogin(Map<String, Object> map) {
        onUserLogin(map, null);
    }

    public synchronized void pushInstallReferrer(String str, String str2, String str3) {
        this.b.getAnalyticsManager().pushInstallReferrer(str, str2, str3);
    }

    public void pushEvent(String str, Map<String, Object> map) {
        this.b.getAnalyticsManager().pushEvent(str, map);
    }

    public void deleteInboxMessage(String str) {
        deleteInboxMessage(getInboxMessageForId(str));
    }

    public void markReadInboxMessage(String str) {
        markReadInboxMessage(getInboxMessageForId(str));
    }

    @Nullable
    public static CleverTapAPI getDefaultInstance(Context context) {
        return getDefaultInstance(context, null);
    }

    @RequiresApi(api = 26)
    public static void createNotificationChannel(Context context, String str, CharSequence charSequence, String str2, int i2, String str3, boolean z) {
        CleverTapAPI p2 = p(context);
        if (p2 == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(p2.b.getConfig()).postAsyncSafelyTask().execute("creatingNotificationChannel", new j(context, str, charSequence, i2, str2, str3, z, p2));
            }
        } catch (Throwable th) {
            p2.n().verbose(p2.getAccountId(), "Failure creating Notification Channel", th);
        }
    }

    public static void createNotification(Context context, Bundle bundle) {
        createNotification(context, bundle, -1000);
    }

    @RequiresApi(api = 26)
    public static void createNotificationChannel(Context context, String str, CharSequence charSequence, String str2, int i2, boolean z, String str3) {
        CleverTapAPI p2 = p(context);
        if (p2 == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(p2.b.getConfig()).postAsyncSafelyTask().execute("createNotificationChannel", new k(context, str3, p2, str, charSequence, i2, str2, z));
            }
        } catch (Throwable th) {
            p2.n().verbose(p2.getAccountId(), "Failure creating Notification Channel", th);
        }
    }

    public void showAppInbox() {
        showAppInbox(new CTInboxStyleConfig());
    }

    @RequiresApi(api = 26)
    public static void createNotificationChannel(Context context, String str, CharSequence charSequence, String str2, int i2, String str3, boolean z, String str4) {
        CleverTapAPI p2 = p(context);
        if (p2 == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(p2.b.getConfig()).postAsyncSafelyTask().execute("creatingNotificationChannel", new l(context, str4, p2, str, charSequence, i2, str2, str3, z));
            }
        } catch (Throwable th) {
            p2.n().verbose(p2.getAccountId(), "Failure creating Notification Channel", th);
        }
    }
}
