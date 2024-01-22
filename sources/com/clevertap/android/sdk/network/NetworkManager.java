package com.clevertap.android.sdk.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.android.volley.toolbox.JsonRequest;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.db.QueueCursor;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.interfaces.NotificationRenderedListener;
import com.clevertap.android.sdk.pushnotification.PushNotificationUtil;
import com.clevertap.android.sdk.response.ARPResponse;
import com.clevertap.android.sdk.response.BaseResponse;
import com.clevertap.android.sdk.response.CleverTapResponse;
import com.clevertap.android.sdk.response.CleverTapResponseHelper;
import com.clevertap.android.sdk.response.ConsoleResponse;
import com.clevertap.android.sdk.response.DisplayUnitResponse;
import com.clevertap.android.sdk.response.FeatureFlagResponse;
import com.clevertap.android.sdk.response.FetchVariablesResponse;
import com.clevertap.android.sdk.response.GeofenceResponse;
import com.clevertap.android.sdk.response.InAppResponse;
import com.clevertap.android.sdk.response.InboxResponse;
import com.clevertap.android.sdk.response.MetadataResponse;
import com.clevertap.android.sdk.response.ProductConfigResponse;
import com.clevertap.android.sdk.response.PushAmpResponse;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.validation.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class NetworkManager extends BaseNetworkManager {
    public static SSLSocketFactory q;
    public static SSLContext r;

    /* renamed from: a  reason: collision with root package name */
    public final BaseCallbackManager f2650a;
    public CleverTapResponse b;
    public final CleverTapInstanceConfig c;
    public final Context d;
    public final ControllerManager e;
    public final CoreMetaData f;
    public final BaseDatabaseManager h;
    public final DeviceInfo i;
    public final LocalDataStore j;
    public final Logger k;
    public final ValidationResultStack m;
    public final Validator o;
    public int g = 0;
    public int l = 0;
    public int n = 0;
    public int p = 0;

    public NetworkManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, CoreMetaData coreMetaData, ValidationResultStack validationResultStack, ControllerManager controllerManager, BaseDatabaseManager baseDatabaseManager, BaseCallbackManager baseCallbackManager, CTLockManager cTLockManager, Validator validator, LocalDataStore localDataStore) {
        this.d = context;
        this.c = cleverTapInstanceConfig;
        this.i = deviceInfo;
        this.f2650a = baseCallbackManager;
        this.o = validator;
        this.j = localDataStore;
        this.k = cleverTapInstanceConfig.getLogger();
        this.f = coreMetaData;
        this.m = validationResultStack;
        this.e = controllerManager;
        this.h = baseDatabaseManager;
        y(new BaseResponse(context, cleverTapInstanceConfig, deviceInfo, this, localDataStore, new InAppResponse(new MetadataResponse(new ARPResponse(new ConsoleResponse(new InboxResponse(new PushAmpResponse(new FetchVariablesResponse(new DisplayUnitResponse(new FeatureFlagResponse(new ProductConfigResponse(new GeofenceResponse(new CleverTapResponseHelper(), cleverTapInstanceConfig, baseCallbackManager), cleverTapInstanceConfig, coreMetaData, controllerManager), cleverTapInstanceConfig, controllerManager), cleverTapInstanceConfig, baseCallbackManager, controllerManager), cleverTapInstanceConfig, controllerManager, baseCallbackManager), context, cleverTapInstanceConfig, baseDatabaseManager, baseCallbackManager, controllerManager), cleverTapInstanceConfig, cTLockManager, baseCallbackManager, controllerManager), cleverTapInstanceConfig), cleverTapInstanceConfig, this, validator, controllerManager), cleverTapInstanceConfig, deviceInfo, this), cleverTapInstanceConfig, controllerManager, false)));
    }

    public static boolean isNetworkOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return true;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static SSLSocketFactory n(SSLContext sSLContext) {
        if (sSLContext == null) {
            return null;
        }
        if (q == null) {
            try {
                q = sSLContext.getSocketFactory();
                Logger.d("Pinning SSL session to DigiCertGlobalRoot CA certificate");
            } catch (Throwable th) {
                Logger.d("Issue in pinning SSL,", th);
            }
        }
        return q;
    }

    public static synchronized SSLContext o() {
        SSLContext sSLContext;
        synchronized (NetworkManager.class) {
            if (r == null) {
                r = new b().a();
            }
            sSLContext = r;
        }
        return sSLContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void s(Context context) throws Exception {
        this.h.clearQueues(context);
        return null;
    }

    public void A(int i) {
        if (i() > 0) {
            return;
        }
        StorageHelper.putInt(this.d, StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_FIRST_TS), i);
    }

    public void B(int i) {
        StorageHelper.putInt(this.d, StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_LAST_TS), i);
    }

    public final void C(final Context context, boolean z) {
        if (z) {
            StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_MUTED), (int) (System.currentTimeMillis() / 1000));
            z(context, null);
            CTExecutorFactory.executors(this.c).postAsyncSafelyTask().execute("CommsManager#setMuted", new Callable() { // from class: com.clevertap.android.sdk.network.a
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Void s;
                    s = NetworkManager.this.s(context);
                    return s;
                }
            });
            return;
        }
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_MUTED), 0);
    }

    public void D(int i) {
        this.n = i;
    }

    public void E(Context context, String str) {
        Logger logger = this.k;
        String accountId = this.c.getAccountId();
        logger.verbose(accountId, "Setting spiky domain to " + str);
        StorageHelper.putString(context, StorageHelper.storageKeyWithSuffix(this.c, Constants.SPIKY_KEY_DOMAIN_NAME), str);
    }

    public HttpsURLConnection b(String str) throws IOException {
        SSLContext o;
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
        httpsURLConnection.setConnectTimeout(10000);
        httpsURLConnection.setReadTimeout(10000);
        httpsURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpsURLConnection.setRequestProperty("X-CleverTap-Account-ID", this.c.getAccountId());
        httpsURLConnection.setRequestProperty("X-CleverTap-Token", this.c.getAccountToken());
        httpsURLConnection.setInstanceFollowRedirects(false);
        if (this.c.isSslPinningEnabled() && (o = o()) != null) {
            httpsURLConnection.setSSLSocketFactory(n(o));
        }
        return httpsURLConnection;
    }

    public final JSONObject c() {
        SharedPreferences t;
        try {
            String newNamespaceARPKey = getNewNamespaceARPKey();
            if (newNamespaceARPKey == null) {
                return null;
            }
            if (!StorageHelper.getPreferences(this.d, newNamespaceARPKey).getAll().isEmpty()) {
                t = StorageHelper.getPreferences(this.d, newNamespaceARPKey);
            } else {
                t = t(newNamespaceARPKey, m());
            }
            Map<String, ?> all = t.getAll();
            Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
            while (it.hasNext()) {
                Object value = it.next().getValue();
                if ((value instanceof Number) && ((Number) value).intValue() == -1) {
                    it.remove();
                }
            }
            JSONObject jSONObject = new JSONObject((Map<?, ?>) all);
            Logger logger = this.k;
            String accountId = this.c.getAccountId();
            logger.verbose(accountId, "Fetched ARP for namespace key: " + newNamespaceARPKey + " values: " + all);
            return jSONObject;
        } catch (Throwable th) {
            this.k.verbose(this.c.getAccountId(), "Failed to construct ARP object", th);
            return null;
        }
    }

    public CleverTapResponse d() {
        return this.b;
    }

    public int e() {
        return this.g;
    }

    public String f(boolean z, EventGroup eventGroup) {
        String domainFromPrefsOrMetadata = getDomainFromPrefsOrMetadata(eventGroup);
        boolean z2 = domainFromPrefsOrMetadata == null || domainFromPrefsOrMetadata.trim().length() == 0;
        if (!z2 || z) {
            if (z2) {
                return "clevertap-prod.com/hello";
            }
            if (eventGroup == EventGroup.VARIABLES) {
                return domainFromPrefsOrMetadata + eventGroup.additionalPath;
            }
            return domainFromPrefsOrMetadata + "/a1";
        }
        return null;
    }

    @Override // com.clevertap.android.sdk.network.BaseNetworkManager
    public void flushDBQueue(Context context, EventGroup eventGroup, @Nullable String str) {
        this.c.getLogger().verbose(this.c.getAccountId(), "Somebody has invoked me to send the queue to CleverTap servers");
        QueueCursor queueCursor = null;
        boolean z = true;
        while (z) {
            QueueCursor queuedEvents = this.h.getQueuedEvents(context, 50, queueCursor, eventGroup);
            if (queuedEvents != null && !queuedEvents.isEmpty().booleanValue()) {
                JSONArray data = queuedEvents.getData();
                if (data != null && data.length() > 0) {
                    boolean sendQueue = sendQueue(context, eventGroup, data, str);
                    if (!sendQueue) {
                        this.e.invokeCallbacksForNetworkError();
                    }
                    z = sendQueue;
                    queueCursor = queuedEvents;
                } else {
                    this.c.getLogger().verbose(this.c.getAccountId(), "No events in the queue, failing");
                    return;
                }
            } else {
                this.c.getLogger().verbose(this.c.getAccountId(), "No events in the queue, failing");
                if (eventGroup != EventGroup.PUSH_NOTIFICATION_VIEWED || queueCursor == null || queueCursor.getData() == null) {
                    return;
                }
                try {
                    v(queueCursor.getData());
                    return;
                } catch (Exception unused) {
                    this.c.getLogger().verbose(this.c.getAccountId(), "met with exception while notifying listeners for PushImpressionSentToServer event");
                    return;
                }
            }
        }
    }

    public String g(boolean z, EventGroup eventGroup) {
        String f = f(z, eventGroup);
        if (f == null) {
            this.k.verbose(this.c.getAccountId(), "Unable to configure endpoint, domain is null");
            return null;
        }
        String accountId = this.c.getAccountId();
        if (accountId == null) {
            this.k.verbose(this.c.getAccountId(), "Unable to configure endpoint, accountID is null");
            return null;
        }
        String str = ("https://" + f + "?os=Android&t=" + this.i.getSdkVersion()) + "&z=" + accountId;
        if (needsHandshakeForDomain(eventGroup)) {
            return str;
        }
        this.g = (int) (System.currentTimeMillis() / 1000);
        return str + "&ts=" + e();
    }

    @Override // com.clevertap.android.sdk.network.BaseNetworkManager
    public int getDelayFrequency() {
        Logger logger = this.k;
        String accountId = this.c.getAccountId();
        logger.debug(accountId, "Network retry #" + this.l);
        if (this.l < 10) {
            Logger logger2 = this.k;
            String accountId2 = this.c.getAccountId();
            logger2.debug(accountId2, "Failure count is " + this.l + ". Setting delay frequency to 1s");
            this.p = 1000;
            return 1000;
        } else if (this.c.getAccountRegion() == null) {
            this.k.debug(this.c.getAccountId(), "Setting delay frequency to 1s");
            return 1000;
        } else {
            int nextInt = this.p + ((new SecureRandom().nextInt(10) + 1) * 1000);
            this.p = nextInt;
            if (nextInt < 600000) {
                Logger logger3 = this.k;
                String accountId3 = this.c.getAccountId();
                logger3.debug(accountId3, "Setting delay frequency to " + this.p);
                return this.p;
            }
            this.p = 1000;
            Logger logger4 = this.k;
            String accountId4 = this.c.getAccountId();
            logger4.debug(accountId4, "Setting delay frequency to " + this.p);
            return this.p;
        }
    }

    public String getDomainFromPrefsOrMetadata(EventGroup eventGroup) {
        try {
            String accountRegion = this.c.getAccountRegion();
            if (accountRegion != null && accountRegion.trim().length() > 0) {
                D(0);
                if (eventGroup.equals(EventGroup.PUSH_NOTIFICATION_VIEWED)) {
                    return accountRegion.trim().toLowerCase() + eventGroup.httpResource + "." + Constants.PRIMARY_DOMAIN;
                }
                return accountRegion.trim().toLowerCase() + "." + Constants.PRIMARY_DOMAIN;
            }
        } catch (Throwable unused) {
        }
        if (eventGroup.equals(EventGroup.PUSH_NOTIFICATION_VIEWED)) {
            return StorageHelper.getStringFromPrefs(this.d, this.c, Constants.SPIKY_KEY_DOMAIN_NAME, null);
        }
        return StorageHelper.getStringFromPrefs(this.d, this.c, Constants.KEY_DOMAIN_NAME, null);
    }

    public String getNewNamespaceARPKey() {
        String accountId = this.c.getAccountId();
        if (accountId == null) {
            return null;
        }
        Logger logger = this.k;
        String accountId2 = this.c.getAccountId();
        logger.verbose(accountId2, "New ARP Key = ARP:" + accountId + ":" + this.i.getDeviceID());
        return "ARP:" + accountId + ":" + this.i.getDeviceID();
    }

    public final JSONObject h(HttpsURLConnection httpsURLConnection) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getErrorStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    return new JSONObject(sb.toString());
                }
            }
        } catch (IOException | JSONException unused) {
            return null;
        }
    }

    public int i() {
        return StorageHelper.getIntFromPrefs(this.d, this.c, Constants.KEY_FIRST_TS, 0);
    }

    public void incrementResponseFailureCount() {
        this.n++;
    }

    @Override // com.clevertap.android.sdk.network.BaseNetworkManager
    public void initHandshake(EventGroup eventGroup, Runnable runnable) {
        this.n = 0;
        w(this.d, eventGroup, runnable);
    }

    public final long j() {
        return StorageHelper.getLongFromPrefs(this.d, this.c, Constants.KEY_I, 0, Constants.NAMESPACE_IJ);
    }

    public final long k() {
        return StorageHelper.getLongFromPrefs(this.d, this.c, Constants.KEY_J, 0, Constants.NAMESPACE_IJ);
    }

    public int l() {
        return StorageHelper.getIntFromPrefs(this.d, this.c, Constants.KEY_LAST_TS, 0);
    }

    public final String m() {
        String accountId = this.c.getAccountId();
        if (accountId == null) {
            return null;
        }
        Logger logger = this.k;
        String accountId2 = this.c.getAccountId();
        logger.verbose(accountId2, "Old ARP Key = ARP:" + accountId);
        return "ARP:" + accountId;
    }

    @Override // com.clevertap.android.sdk.network.BaseNetworkManager
    public boolean needsHandshakeForDomain(EventGroup eventGroup) {
        String domainFromPrefsOrMetadata = getDomainFromPrefsOrMetadata(eventGroup);
        boolean z = this.n > 5;
        if (z) {
            z(this.d, null);
        }
        return domainFromPrefsOrMetadata == null || z;
    }

    public final boolean p(int i, HttpsURLConnection httpsURLConnection) {
        if (i == 200) {
            this.k.info("variables", "Vars synced successfully.");
            return false;
        } else if (i != 400) {
            if (i != 401) {
                Logger logger = this.k;
                logger.info("variables", "Response code " + i + " while syncing vars.");
                return true;
            }
            this.k.info("variables", "Unauthorized access from a non-test profile. Please mark this profile as a test profile from the CleverTap dashboard.");
            return true;
        } else {
            JSONObject h = h(httpsURLConnection);
            if (h != null && !TextUtils.isEmpty(h.optString("error"))) {
                String optString = h.optString("error");
                Logger logger2 = this.k;
                logger2.info("variables", "Error while syncing vars: " + optString);
            } else {
                this.k.info("variables", "Error while syncing vars.");
            }
            return true;
        }
    }

    public boolean q(String str) {
        return !str.equals(StorageHelper.getStringFromPrefs(this.d, this.c, Constants.KEY_DOMAIN_NAME, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00d8 A[Catch: all -> 0x01ef, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00fb A[Catch: all -> 0x01ef, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x014e A[Catch: all -> 0x0179, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x015b A[Catch: all -> 0x0179, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0168 A[Catch: all -> 0x0179, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0173 A[Catch: all -> 0x0179, TRY_LEAVE, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01a2 A[Catch: all -> 0x01ef, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b1 A[Catch: all -> 0x01ef, TryCatch #2 {all -> 0x01ef, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x000c, B:7:0x0014, B:9:0x001c, B:11:0x002f, B:13:0x0045, B:14:0x004a, B:16:0x0059, B:17:0x005e, B:19:0x0066, B:20:0x006b, B:24:0x007d, B:26:0x00bd, B:31:0x00cd, B:33:0x00d8, B:34:0x00e2, B:36:0x00fb, B:37:0x0111, B:46:0x0141, B:62:0x0187, B:64:0x018f, B:66:0x0195, B:67:0x019a, B:69:0x01a2, B:71:0x01be, B:70:0x01b1, B:73:0x01e0, B:10:0x0022, B:38:0x0121, B:40:0x0127, B:42:0x012d, B:47:0x0146, B:49:0x014e, B:50:0x0153, B:52:0x015b, B:53:0x0160, B:55:0x0168, B:56:0x016d, B:58:0x0173), top: B:79:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String r(android.content.Context r8, org.json.JSONArray r9, @androidx.annotation.Nullable java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.network.NetworkManager.r(android.content.Context, org.json.JSONArray, java.lang.String):java.lang.String");
    }

    @Override // com.clevertap.android.sdk.network.BaseNetworkManager
    public boolean sendQueue(Context context, EventGroup eventGroup, JSONArray jSONArray, @Nullable String str) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return false;
        }
        if (this.i.getDeviceID() == null) {
            this.k.debug(this.c.getAccountId(), "CleverTap Id not finalized, unable to send queue");
            return false;
        }
        HttpsURLConnection httpsURLConnection = null;
        try {
            String g = g(false, eventGroup);
            if (g == null) {
                this.k.debug(this.c.getAccountId(), "Problem configuring queue endpoint, unable to send queue");
                return false;
            }
            HttpsURLConnection b = b(g);
            try {
                String r2 = r(context, jSONArray, str);
                if (r2 == null) {
                    this.k.debug(this.c.getAccountId(), "Problem configuring queue request, unable to send queue");
                    if (b != null) {
                        try {
                            b.getInputStream().close();
                            b.disconnect();
                        } catch (Throwable unused) {
                        }
                    }
                    return false;
                }
                this.k.debug(this.c.getAccountId(), "Send queue contains " + jSONArray.length() + " items: " + r2);
                this.k.debug(this.c.getAccountId(), "Sending queue to: " + g);
                b.setDoOutput(true);
                b.getOutputStream().write(r2.getBytes("UTF-8"));
                int responseCode = b.getResponseCode();
                if (eventGroup == EventGroup.VARIABLES) {
                    if (p(responseCode, b)) {
                        try {
                            b.getInputStream().close();
                            b.disconnect();
                        } catch (Throwable unused2) {
                        }
                        return false;
                    }
                } else if (responseCode != 200) {
                    throw new IOException("Response code is not 200. It is " + responseCode);
                }
                String headerField = b.getHeaderField(Constants.HEADER_DOMAIN_NAME);
                if (headerField != null && headerField.trim().length() > 0 && q(headerField)) {
                    z(context, headerField);
                    this.k.debug(this.c.getAccountId(), "The domain has changed to " + headerField + ". The request will be retried shortly.");
                    try {
                        b.getInputStream().close();
                        b.disconnect();
                    } catch (Throwable unused3) {
                    }
                    return false;
                }
                if (x(context, b)) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(b.getInputStream(), JsonRequest.PROTOCOL_CHARSET));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    }
                    String sb2 = sb.toString();
                    if (eventGroup == EventGroup.VARIABLES) {
                        new BaseResponse(context, this.c, this.i, this, this.j, new ARPResponse(new CleverTapResponseHelper(), this.c, this, this.o, this.e)).processResponse(null, sb2, this.d);
                    } else {
                        d().processResponse(null, sb2, this.d);
                    }
                }
                B(e());
                A(e());
                this.k.debug(this.c.getAccountId(), "Queue sent successfully");
                this.n = 0;
                this.l = 0;
                try {
                    b.getInputStream().close();
                    b.disconnect();
                } catch (Throwable unused4) {
                }
                return true;
            } catch (Throwable th) {
                th = th;
                httpsURLConnection = b;
                try {
                    this.k.debug(this.c.getAccountId(), "An exception occurred while sending the queue, will retry: ", th);
                    this.n++;
                    this.l++;
                    this.f2650a.getFailureFlushListener().failureFlush(context);
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.getInputStream().close();
                            httpsURLConnection.disconnect();
                        } catch (Throwable unused5) {
                        }
                    }
                    return false;
                } catch (Throwable th2) {
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.getInputStream().close();
                            httpsURLConnection.disconnect();
                        } catch (Throwable unused6) {
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    public void setI(Context context, long j) {
        SharedPreferences.Editor edit = StorageHelper.getPreferences(context, Constants.NAMESPACE_IJ).edit();
        edit.putLong(StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_I), j);
        StorageHelper.persist(edit);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void setJ(Context context, long j) {
        SharedPreferences.Editor edit = StorageHelper.getPreferences(context, Constants.NAMESPACE_IJ).edit();
        edit.putLong(StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_J), j);
        StorageHelper.persist(edit);
    }

    public final SharedPreferences t(String str, String str2) {
        SharedPreferences preferences = StorageHelper.getPreferences(this.d, str2);
        SharedPreferences preferences2 = StorageHelper.getPreferences(this.d, str);
        SharedPreferences.Editor edit = preferences2.edit();
        for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Number) {
                edit.putInt(entry.getKey(), ((Number) value).intValue());
            } else if (value instanceof String) {
                String str3 = (String) value;
                if (str3.length() < 100) {
                    edit.putString(entry.getKey(), str3);
                } else {
                    Logger logger = this.k;
                    String accountId = this.c.getAccountId();
                    logger.verbose(accountId, "ARP update for key " + entry.getKey() + " rejected (string value too long)");
                }
            } else if (value instanceof Boolean) {
                edit.putBoolean(entry.getKey(), ((Boolean) value).booleanValue());
            } else {
                Logger logger2 = this.k;
                String accountId2 = this.c.getAccountId();
                logger2.verbose(accountId2, "ARP update for key " + entry.getKey() + " rejected (invalid data type)");
            }
        }
        Logger logger3 = this.k;
        String accountId3 = this.c.getAccountId();
        logger3.verbose(accountId3, "Completed ARP update for namespace key: " + str + "");
        StorageHelper.persist(edit);
        preferences.edit().clear().apply();
        return preferences2;
    }

    public final void u(@NonNull String str) {
        NotificationRenderedListener notificationRenderedListener = CleverTapAPI.getNotificationRenderedListener(str);
        if (notificationRenderedListener != null) {
            Logger logger = this.k;
            String accountId = this.c.getAccountId();
            logger.verbose(accountId, "notifying listener " + str + ", that push impression sent successfully");
            notificationRenderedListener.onNotificationRendered(true);
        }
    }

    public final void v(JSONArray jSONArray) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject optJSONObject = jSONArray.getJSONObject(i).optJSONObject("evtData");
                if (optJSONObject != null) {
                    u(PushNotificationUtil.buildPushNotificationRenderedListenerKey(optJSONObject.optString(Constants.WZRK_ACCT_ID_KEY), optJSONObject.optString(Constants.WZRK_PUSH_ID)));
                }
            } catch (JSONException unused) {
                this.k.verbose(this.c.getAccountId(), "Encountered an exception while parsing the push notification viewed event queue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.k.verbose(this.c.getAccountId(), "push notification viewed event sent successfully");
    }

    public void w(Context context, EventGroup eventGroup, Runnable runnable) {
        String g = g(true, eventGroup);
        if (g == null) {
            this.k.verbose(this.c.getAccountId(), "Unable to perform handshake, endpoint is null");
        }
        Logger logger = this.k;
        String accountId = this.c.getAccountId();
        logger.verbose(accountId, "Performing handshake with " + g);
        try {
            HttpsURLConnection b = b(g);
            int responseCode = b.getResponseCode();
            if (responseCode != 200) {
                Logger logger2 = this.k;
                String accountId2 = this.c.getAccountId();
                logger2.verbose(accountId2, "Invalid HTTP status code received for handshake - " + responseCode);
                try {
                    b.getInputStream().close();
                    b.disconnect();
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            this.k.verbose(this.c.getAccountId(), "Received success from handshake :)");
            if (x(context, b)) {
                this.k.verbose(this.c.getAccountId(), "We are not muted");
                runnable.run();
            }
            b.getInputStream().close();
            b.disconnect();
        } catch (Throwable unused2) {
        }
    }

    public boolean x(Context context, HttpsURLConnection httpsURLConnection) {
        String headerField = httpsURLConnection.getHeaderField(Constants.HEADER_MUTE);
        if (headerField != null && headerField.trim().length() > 0) {
            if (headerField.equals("true")) {
                C(context, true);
                return false;
            }
            C(context, false);
        }
        String headerField2 = httpsURLConnection.getHeaderField(Constants.HEADER_DOMAIN_NAME);
        Logger.v("Getting domain from header - " + headerField2);
        if (headerField2 != null && headerField2.trim().length() != 0) {
            String headerField3 = httpsURLConnection.getHeaderField(Constants.SPIKY_HEADER_DOMAIN_NAME);
            Logger.v("Getting spiky domain from header - " + headerField3);
            C(context, false);
            z(context, headerField2);
            Logger.v("Setting spiky domain from header as -" + headerField3);
            if (headerField3 == null) {
                E(context, headerField2);
            } else {
                E(context, headerField3);
            }
        }
        return true;
    }

    public void y(CleverTapResponse cleverTapResponse) {
        this.b = cleverTapResponse;
    }

    public void z(Context context, String str) {
        Logger logger = this.k;
        String accountId = this.c.getAccountId();
        logger.verbose(accountId, "Setting domain to " + str);
        StorageHelper.putString(context, StorageHelper.storageKeyWithSuffix(this.c, Constants.KEY_DOMAIN_NAME), str);
        if (this.f2650a.getSCDomainListener() != null) {
            if (str != null) {
                this.f2650a.getSCDomainListener().onSCDomainAvailable(Utils.getSCDomain(str));
            } else {
                this.f2650a.getSCDomainListener().onSCDomainUnavailable();
            }
        }
    }
}
