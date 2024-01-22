package com.clevertap.android.sdk.login;

import android.content.Context;
import com.clevertap.android.sdk.AnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.SessionManager;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.db.DBManager;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController;
import com.clevertap.android.sdk.product_config.CTProductConfigFactory;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
public class LoginController {
    public static final Object r = new Object();
    public final AnalyticsManager b;
    public final BaseEventQueueManager c;
    public final CTLockManager d;
    public final BaseCallbackManager e;
    public final CleverTapInstanceConfig f;
    public final Context g;
    public final ControllerManager h;
    public final CoreMetaData i;
    public final BaseDatabaseManager j;
    public final DeviceInfo k;
    public final LocalDataStore l;
    public final PushProviders m;
    public final SessionManager n;
    public final ValidationResultStack o;
    public final CryptHandler q;

    /* renamed from: a  reason: collision with root package name */
    public String f2646a = null;
    public String p = null;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ Map h;
        public final /* synthetic */ String i;
        public final /* synthetic */ String j;

        public a(Map map, String str, String str2) {
            this.h = map;
            this.i = str;
            this.j = str2;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            String str;
            try {
                Logger logger = LoginController.this.f.getLogger();
                String accountId = LoginController.this.f.getAccountId();
                StringBuilder sb = new StringBuilder();
                sb.append("asyncProfileSwitchUser:[profile ");
                sb.append(this.h);
                sb.append(" with Cached GUID ");
                if (this.i != null) {
                    str = LoginController.this.f2646a;
                } else {
                    str = "NULL and cleverTapID " + this.j;
                }
                sb.append(str);
                logger.verbose(accountId, sb.toString());
                LoginController.this.i.setCurrentUserOptedOut(false);
                LoginController.this.m.forcePushDeviceToken(false);
                LoginController.this.c.flushQueueSync(LoginController.this.g, EventGroup.REGULAR);
                LoginController.this.c.flushQueueSync(LoginController.this.g, EventGroup.PUSH_NOTIFICATION_VIEWED);
                LoginController.this.j.clearQueues(LoginController.this.g);
                LoginController.this.l.changeUser();
                CoreMetaData.setActivityCount(1);
                LoginController.this.n.destroySession();
                if (this.i != null) {
                    LoginController.this.k.forceUpdateDeviceId(this.i);
                    LoginController.this.e.notifyUserProfileInitialized(this.i);
                } else if (LoginController.this.f.getEnableCustomCleverTapId()) {
                    LoginController.this.k.forceUpdateCustomCleverTapID(this.j);
                } else {
                    LoginController.this.k.forceNewDeviceID();
                }
                LoginController.this.e.notifyUserProfileInitialized(LoginController.this.k.getDeviceID());
                LoginController.this.k.setCurrentUserOptOutStateFromStorage();
                LoginController.this.A();
                LoginController.this.b.forcePushAppLaunchedEvent();
                if (this.h != null) {
                    LoginController.this.b.pushProfile(this.h);
                }
                LoginController.this.m.forcePushDeviceToken(true);
                synchronized (LoginController.r) {
                    LoginController.this.p = null;
                }
                LoginController.this.y();
                LoginController.this.x();
                LoginController.this.z();
                LoginController.this.recordDeviceIDErrors();
                LoginController.this.w();
                LoginController.this.h.getInAppFCManager().changeUser(LoginController.this.k.getDeviceID());
            } catch (Throwable th) {
                LoginController.this.f.getLogger().verbose(LoginController.this.f.getAccountId(), "Reset Profile error", th);
            }
            return null;
        }
    }

    public LoginController(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, ValidationResultStack validationResultStack, BaseEventQueueManager baseEventQueueManager, AnalyticsManager analyticsManager, CoreMetaData coreMetaData, ControllerManager controllerManager, SessionManager sessionManager, LocalDataStore localDataStore, BaseCallbackManager baseCallbackManager, DBManager dBManager, CTLockManager cTLockManager, CryptHandler cryptHandler) {
        this.f = cleverTapInstanceConfig;
        this.g = context;
        this.k = deviceInfo;
        this.o = validationResultStack;
        this.c = baseEventQueueManager;
        this.b = analyticsManager;
        this.i = coreMetaData;
        this.m = controllerManager.getPushProviders();
        this.n = sessionManager;
        this.l = localDataStore;
        this.e = baseCallbackManager;
        this.j = dBManager;
        this.h = controllerManager;
        this.d = cTLockManager;
        this.q = cryptHandler;
    }

    public final void A() {
        if (this.h.getCtVariables() != null) {
            this.h.getCtVariables().clearUserContent();
        }
    }

    public final void a(Map<String, Object> map, String str) {
        if (map == null) {
            return;
        }
        try {
            String deviceID = this.k.getDeviceID();
            if (deviceID == null) {
                return;
            }
            boolean z = false;
            LoginInfoProvider loginInfoProvider = new LoginInfoProvider(this.g, this.f, this.k, this.q);
            IdentityRepo repo = IdentityRepoFactory.getRepo(this.g, this.f, this.k, this.o);
            for (String str2 : map.keySet()) {
                Object obj = map.get(str2);
                if (repo.hasIdentity(str2)) {
                    String str3 = null;
                    if (obj != null) {
                        try {
                            str3 = obj.toString();
                        } catch (Throwable unused) {
                            continue;
                        }
                    }
                    if (str3 != null && str3.length() > 0) {
                        z = true;
                        String gUIDForIdentifier = loginInfoProvider.getGUIDForIdentifier(str2, str3);
                        this.f2646a = gUIDForIdentifier;
                        if (gUIDForIdentifier != null) {
                            break;
                        }
                    }
                }
            }
            if (!this.k.isErrorDeviceId() && (!z || loginInfoProvider.isAnonymousDevice())) {
                this.f.getLogger().debug(this.f.getAccountId(), "onUserLogin: no identifier provided or device is anonymous, pushing on current user profile");
                this.b.pushProfile(map);
                return;
            }
            String str4 = this.f2646a;
            if (str4 != null && str4.equals(deviceID)) {
                Logger logger = this.f.getLogger();
                String accountId = this.f.getAccountId();
                logger.debug(accountId, "onUserLogin: " + map.toString() + " maps to current device id " + deviceID + " pushing on current profile");
                this.b.pushProfile(map);
                return;
            }
            String obj2 = map.toString();
            if (v(obj2)) {
                Logger logger2 = this.f.getLogger();
                String accountId2 = this.f.getAccountId();
                logger2.debug(accountId2, "Already processing onUserLogin for " + obj2);
                return;
            }
            synchronized (r) {
                this.p = obj2;
            }
            Logger logger3 = this.f.getLogger();
            String accountId3 = this.f.getAccountId();
            StringBuilder sb = new StringBuilder();
            sb.append("onUserLogin: queuing reset profile for ");
            sb.append(obj2);
            sb.append(" with Cached GUID ");
            String str5 = this.f2646a;
            if (str5 == null) {
                str5 = "NULL";
            }
            sb.append(str5);
            logger3.verbose(accountId3, sb.toString());
            asyncProfileSwitchUser(map, this.f2646a, str);
        } catch (Throwable th) {
            this.f.getLogger().verbose(this.f.getAccountId(), "onUserLogin failed", th);
        }
    }

    public void asyncProfileSwitchUser(Map<String, Object> map, String str, String str2) {
        CTExecutorFactory.executors(this.f).postAsyncSafelyTask().execute("resetProfile", new a(map, str, str2));
    }

    public void onUserLogin(Map<String, Object> map, String str) {
        if (this.f.getEnableCustomCleverTapId()) {
            if (str == null) {
                Logger.i("CLEVERTAP_USE_CUSTOM_ID has been specified in the AndroidManifest.xml Please call onUserlogin() and pass a custom CleverTap ID");
            }
        } else if (str != null) {
            Logger.i("CLEVERTAP_USE_CUSTOM_ID has not been specified in the AndroidManifest.xml Please call CleverTapAPI.defaultInstance() without a custom CleverTap ID");
        }
        a(map, str);
    }

    public void recordDeviceIDErrors() {
        Iterator<ValidationResult> it = this.k.getValidationResults().iterator();
        while (it.hasNext()) {
            this.o.pushValidationResult(it.next());
        }
    }

    public final boolean v(String str) {
        boolean z;
        synchronized (r) {
            String str2 = this.p;
            z = str2 != null && str2.equals(str);
        }
        return z;
    }

    public final void w() {
        if (this.h.getCTDisplayUnitController() != null) {
            this.h.getCTDisplayUnitController().reset();
        } else {
            this.f.getLogger().verbose(this.f.getAccountId(), "DisplayUnit : Can't reset Display Units, DisplayUnitcontroller is null");
        }
    }

    public final void x() {
        CTFeatureFlagsController cTFeatureFlagsController = this.h.getCTFeatureFlagsController();
        if (cTFeatureFlagsController != null && cTFeatureFlagsController.isInitialized()) {
            cTFeatureFlagsController.resetWithGuid(this.k.getDeviceID());
            cTFeatureFlagsController.fetchFeatureFlags();
            return;
        }
        this.f.getLogger().verbose(this.f.getAccountId(), "DisplayUnit : Can't reset Display Units, CTFeatureFlagsController is null");
    }

    public final void y() {
        synchronized (this.d.getInboxControllerLock()) {
            this.h.setCTInboxController(null);
        }
        this.h.initializeInbox();
    }

    public final void z() {
        if (this.f.isAnalyticsOnly()) {
            this.f.getLogger().debug(this.f.getAccountId(), "Product Config is not enabled for this instance");
            return;
        }
        if (this.h.getCTProductConfigController() != null) {
            this.h.getCTProductConfigController().resetSettings();
        }
        this.h.setCTProductConfigController(CTProductConfigFactory.getInstance(this.g, this.k, this.f, this.b, this.i, this.e));
        this.f.getLogger().verbose(this.f.getAccountId(), "Product Config reset");
    }
}
