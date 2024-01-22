package com.clevertap.android.sdk.events;

import android.content.Context;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.FailureFlushListener;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.SessionManager;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.login.IdentityRepo;
import com.clevertap.android.sdk.login.IdentityRepoFactory;
import com.clevertap.android.sdk.login.LoginInfoProvider;
import com.clevertap.android.sdk.network.BaseNetworkManager;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.MainLooperHandler;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.google.android.gms.common.Scopes;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class EventQueueManager extends BaseEventQueueManager implements FailureFlushListener {
    public final BaseDatabaseManager b;
    public final CoreMetaData c;
    public final CleverTapInstanceConfig d;
    public final Context e;
    public final CTLockManager f;
    public final DeviceInfo g;
    public final EventMediator h;
    public final LocalDataStore i;
    public final Logger j;
    public LoginInfoProvider k;
    public final MainLooperHandler l;
    public final BaseNetworkManager m;
    public final SessionManager n;
    public final ValidationResultStack o;
    public final ControllerManager q;
    public final CryptHandler r;

    /* renamed from: a  reason: collision with root package name */
    public Runnable f2610a = null;
    public Runnable p = null;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ EventGroup h;
        public final /* synthetic */ Context i;

        public a(EventGroup eventGroup, Context context) {
            this.h = eventGroup;
            this.i = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (this.h == EventGroup.PUSH_NOTIFICATION_VIEWED) {
                EventQueueManager.this.j.verbose(EventQueueManager.this.d.getAccountId(), "Pushing Notification Viewed event onto queue flush sync");
            } else {
                EventQueueManager.this.j.verbose(EventQueueManager.this.d.getAccountId(), "Pushing event onto queue flush sync");
            }
            EventQueueManager.this.flushQueueSync(this.i, this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public final /* synthetic */ Context h;
        public final /* synthetic */ EventGroup i;
        public final /* synthetic */ String j;

        public b(Context context, EventGroup eventGroup, String str) {
            this.h = context;
            this.i = eventGroup;
            this.j = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            EventQueueManager.this.m.flushDBQueue(this.h, this.i, this.j);
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Void> {
        public c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            try {
                EventQueueManager.this.d.getLogger().verbose(EventQueueManager.this.d.getAccountId(), "Queuing daily events");
                EventQueueManager.this.pushBasicProfile(null, false);
            } catch (Throwable th) {
                EventQueueManager.this.d.getLogger().verbose(EventQueueManager.this.d.getAccountId(), "Daily profile sync failed", th);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Callable<Void> {
        public final /* synthetic */ JSONObject h;
        public final /* synthetic */ int i;
        public final /* synthetic */ Context j;

        /* loaded from: classes2.dex */
        public class a implements Runnable {

            /* renamed from: com.clevertap.android.sdk.events.EventQueueManager$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public class CallableC0225a implements Callable<Void> {
                public CallableC0225a() {
                }

                @Override // java.util.concurrent.Callable
                /* renamed from: a */
                public Void call() {
                    EventQueueManager.this.n.lazyCreateSession(d.this.j);
                    EventQueueManager.this.pushInitialEventsAsync();
                    d dVar = d.this;
                    EventQueueManager.this.addToQueue(dVar.j, dVar.h, dVar.i);
                    return null;
                }
            }

            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                CTExecutorFactory.executors(EventQueueManager.this.d).postAsyncSafelyTask().execute("queueEventWithDelay", new CallableC0225a());
            }
        }

        public d(JSONObject jSONObject, int i, Context context) {
            this.h = jSONObject;
            this.i = i;
            this.j = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (EventQueueManager.this.h.shouldDropEvent(this.h, this.i)) {
                return null;
            }
            if (EventQueueManager.this.h.shouldDeferProcessingEvent(this.h, this.i)) {
                Logger logger = EventQueueManager.this.d.getLogger();
                String accountId = EventQueueManager.this.d.getAccountId();
                logger.debug(accountId, "App Launched not yet processed, re-queuing event " + this.h + "after 2s");
                EventQueueManager.this.l.postDelayed(new a(), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
            } else {
                int i = this.i;
                if (i != 7) {
                    EventQueueManager.this.n.lazyCreateSession(this.j);
                    EventQueueManager.this.pushInitialEventsAsync();
                    EventQueueManager.this.addToQueue(this.j, this.h, this.i);
                } else {
                    EventQueueManager.this.addToQueue(this.j, this.h, i);
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Runnable {
        public final /* synthetic */ Context h;

        public e(Context context) {
            this.h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            EventQueueManager.this.flushQueueAsync(this.h, EventGroup.REGULAR);
            EventQueueManager.this.flushQueueAsync(this.h, EventGroup.PUSH_NOTIFICATION_VIEWED);
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Runnable {
        public final /* synthetic */ Context h;

        public f(Context context) {
            this.h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            EventQueueManager.this.d.getLogger().verbose(EventQueueManager.this.d.getAccountId(), "Pushing Notification Viewed event onto queue flush async");
            EventQueueManager.this.flushQueueAsync(this.h, EventGroup.PUSH_NOTIFICATION_VIEWED);
        }
    }

    public EventQueueManager(BaseDatabaseManager baseDatabaseManager, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, EventMediator eventMediator, SessionManager sessionManager, BaseCallbackManager baseCallbackManager, MainLooperHandler mainLooperHandler, DeviceInfo deviceInfo, ValidationResultStack validationResultStack, NetworkManager networkManager, CoreMetaData coreMetaData, CTLockManager cTLockManager, LocalDataStore localDataStore, ControllerManager controllerManager, CryptHandler cryptHandler) {
        this.b = baseDatabaseManager;
        this.e = context;
        this.d = cleverTapInstanceConfig;
        this.h = eventMediator;
        this.n = sessionManager;
        this.l = mainLooperHandler;
        this.g = deviceInfo;
        this.o = validationResultStack;
        this.m = networkManager;
        this.i = localDataStore;
        this.j = cleverTapInstanceConfig.getLogger();
        this.c = coreMetaData;
        this.f = cTLockManager;
        this.q = controllerManager;
        this.r = cryptHandler;
        baseCallbackManager.setFailureFlushListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(Context context, EventGroup eventGroup, JSONArray jSONArray) {
        this.m.sendQueue(context, eventGroup, jSONArray, null);
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void addToQueue(Context context, JSONObject jSONObject, int i) {
        if (i == 6) {
            this.d.getLogger().verbose(this.d.getAccountId(), "Pushing Notification Viewed event onto separate queue");
            processPushNotificationViewedEvent(context, jSONObject);
        } else if (i == 8) {
            l(context, jSONObject);
        } else {
            processEvent(context, jSONObject, i);
        }
    }

    @Override // com.clevertap.android.sdk.FailureFlushListener
    public void failureFlush(Context context) {
        scheduleQueueFlush(context);
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flush() {
        flushQueueAsync(this.e, EventGroup.REGULAR);
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flushQueueAsync(Context context, EventGroup eventGroup) {
        CTExecutorFactory.executors(this.d).postAsyncSafelyTask().execute("CommsManager#flushQueueAsync", new a(eventGroup, context));
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flushQueueSync(Context context, EventGroup eventGroup) {
        flushQueueSync(context, eventGroup, null);
    }

    public LoginInfoProvider getLoginInfoProvider() {
        return this.k;
    }

    public int getNow() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public final void h(JSONObject jSONObject, Context context) {
        try {
            jSONObject.put("mc", Utils.getMemoryConsumption());
        } catch (Throwable unused) {
        }
        try {
            jSONObject.put(Constants.NOTIF_TITLE, Utils.getCurrentNetworkType(context));
        } catch (Throwable unused2) {
        }
    }

    public final void i(Context context, JSONObject jSONObject) {
        try {
            if ("event".equals(jSONObject.getString("type")) && Constants.APP_LAUNCHED_EVENT.equals(jSONObject.getString("evtName"))) {
                jSONObject.put("pai", context.getPackageName());
            }
        } catch (Throwable unused) {
        }
    }

    public final String j() {
        return this.g.getDeviceID();
    }

    public final void l(Context context, JSONObject jSONObject) {
        sendImmediately(context, EventGroup.VARIABLES, jSONObject);
    }

    public final void m(Context context) {
        if (this.p == null) {
            this.p = new f(context);
        }
        this.l.removeCallbacks(this.p);
        this.l.post(this.p);
    }

    public final void n(Context context, JSONObject jSONObject, int i) {
        if (i == 4) {
            this.i.persistEvent(context, jSONObject, i);
        }
    }

    public void processEvent(Context context, JSONObject jSONObject, int i) {
        String str;
        synchronized (this.f.getEventLock()) {
            try {
                if (CoreMetaData.getActivityCount() == 0) {
                    CoreMetaData.setActivityCount(1);
                }
                if (i == 1) {
                    str = "page";
                } else if (i == 2) {
                    str = "ping";
                    h(jSONObject, context);
                    if (jSONObject.has("bk")) {
                        this.c.setBgPing(true);
                        jSONObject.remove("bk");
                    }
                    if (this.c.isLocationForGeofence()) {
                        jSONObject.put("gf", true);
                        this.c.setLocationForGeofence(false);
                        jSONObject.put("gfSDKVersion", this.c.getGeofenceSDKVersion());
                        this.c.setGeofenceSDKVersion(0);
                    }
                } else {
                    str = i == 3 ? Scopes.PROFILE : i == 5 ? "data" : "event";
                }
                String screenName = this.c.getScreenName();
                if (screenName != null) {
                    jSONObject.put("n", screenName);
                }
                jSONObject.put("s", this.c.getCurrentSessionId());
                jSONObject.put("pg", CoreMetaData.getActivityCount());
                jSONObject.put("type", str);
                jSONObject.put("ep", getNow());
                jSONObject.put("f", this.c.isFirstSession());
                jSONObject.put("lsl", this.c.getLastSessionLength());
                i(context, jSONObject);
                ValidationResult popValidationResult = this.o.popValidationResult();
                if (popValidationResult != null) {
                    jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(popValidationResult));
                }
                this.i.setDataSyncFlag(jSONObject);
                this.b.queueEventToDB(context, jSONObject, i);
                n(context, jSONObject, i);
                scheduleQueueFlush(context);
            }
        }
    }

    public void processPushNotificationViewedEvent(Context context, JSONObject jSONObject) {
        synchronized (this.f.getEventLock()) {
            try {
                jSONObject.put("s", this.c.getCurrentSessionId());
                jSONObject.put("type", "event");
                jSONObject.put("ep", getNow());
                ValidationResult popValidationResult = this.o.popValidationResult();
                if (popValidationResult != null) {
                    jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(popValidationResult));
                }
                this.d.getLogger().verbose(this.d.getAccountId(), "Pushing Notification Viewed event onto DB");
                this.b.queuePushNotificationViewedEventToDB(context, jSONObject);
                this.d.getLogger().verbose(this.d.getAccountId(), "Pushing Notification Viewed event onto queue flush");
                m(context);
            }
        }
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void pushBasicProfile(JSONObject jSONObject, boolean z) {
        try {
            String j = j();
            JSONObject jSONObject2 = new JSONObject();
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> keys = jSONObject.keys();
                IdentityRepo repo = IdentityRepoFactory.getRepo(this.e, this.d, this.g, this.o);
                setLoginInfoProvider(new LoginInfoProvider(this.e, this.d, this.g, this.r));
                while (keys.hasNext()) {
                    String next = keys.next();
                    Object obj = null;
                    try {
                        try {
                            obj = jSONObject.getJSONObject(next);
                        } catch (Throwable unused) {
                            obj = jSONObject.get(next);
                        }
                    } catch (JSONException unused2) {
                    }
                    if (obj != null) {
                        jSONObject2.put(next, obj);
                        boolean hasIdentity = repo.hasIdentity(next);
                        if (hasIdentity && z) {
                            try {
                                getLoginInfoProvider().removeValueFromCachedGUIDForIdentifier(j, next);
                            } catch (Throwable unused3) {
                            }
                        } else if (hasIdentity) {
                            getLoginInfoProvider().cacheGUIDForIdentifier(j, next, obj.toString());
                        }
                    }
                }
            }
            try {
                String carrier = this.g.getCarrier();
                if (carrier != null && !carrier.equals("")) {
                    jSONObject2.put("Carrier", carrier);
                }
                String countryCode = this.g.getCountryCode();
                if (countryCode != null && !countryCode.equals("")) {
                    jSONObject2.put("cc", countryCode);
                }
                jSONObject2.put("tz", TimeZone.getDefault().getID());
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(Scopes.PROFILE, jSONObject2);
                queueEvent(this.e, jSONObject3, 3);
            } catch (JSONException unused4) {
                this.d.getLogger().verbose(this.d.getAccountId(), "FATAL: Creating basic profile update event failed!");
            }
        } catch (Throwable th) {
            this.d.getLogger().verbose(this.d.getAccountId(), "Basic profile sync", th);
        }
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void pushInitialEventsAsync() {
        if (this.c.inCurrentSession()) {
            return;
        }
        CTExecutorFactory.executors(this.d).postAsyncSafelyTask().execute("CleverTapAPI#pushInitialEventsAsync", new c());
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public Future<?> queueEvent(Context context, JSONObject jSONObject, int i) {
        return CTExecutorFactory.executors(this.d).postAsyncSafelyTask().submit("queueEvent", new d(jSONObject, i, context));
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void scheduleQueueFlush(Context context) {
        if (this.f2610a == null) {
            this.f2610a = new e(context);
        }
        this.l.removeCallbacks(this.f2610a);
        this.l.postDelayed(this.f2610a, this.m.getDelayFrequency());
        this.j.verbose(this.d.getAccountId(), "Scheduling delayed queue flush on main event loop");
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void sendImmediately(final Context context, final EventGroup eventGroup, JSONObject jSONObject) {
        if (!NetworkManager.isNetworkOnline(context)) {
            this.j.verbose(this.d.getAccountId(), "Network connectivity unavailable. Event won't be sent.");
        } else if (this.c.isOffline()) {
            this.j.debug(this.d.getAccountId(), "CleverTap Instance has been set to offline, won't send event");
        } else {
            final JSONArray put = new JSONArray().put(jSONObject);
            if (this.m.needsHandshakeForDomain(eventGroup)) {
                this.m.initHandshake(eventGroup, new Runnable() { // from class: com.clevertap.android.sdk.events.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        EventQueueManager.this.k(context, eventGroup, put);
                    }
                });
            } else {
                this.m.sendQueue(context, eventGroup, put, null);
            }
        }
    }

    public void setLoginInfoProvider(LoginInfoProvider loginInfoProvider) {
        this.k = loginInfoProvider;
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flushQueueSync(Context context, EventGroup eventGroup, @Nullable String str) {
        if (!NetworkManager.isNetworkOnline(context)) {
            this.j.verbose(this.d.getAccountId(), "Network connectivity unavailable. Will retry later");
            this.q.invokeCallbacksForNetworkError();
        } else if (this.c.isOffline()) {
            this.j.debug(this.d.getAccountId(), "CleverTap Instance has been set to offline, won't send events queue");
            this.q.invokeCallbacksForNetworkError();
        } else if (this.m.needsHandshakeForDomain(eventGroup)) {
            this.m.initHandshake(eventGroup, new b(context, eventGroup, str));
        } else {
            this.j.verbose(this.d.getAccountId(), "Pushing Notification Viewed event onto queue DB flush");
            this.m.flushDBQueue(context, eventGroup, str);
        }
    }
}
