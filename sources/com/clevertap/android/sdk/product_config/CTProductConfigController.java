package com.clevertap.android.sdk.product_config;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.BaseAnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.utils.FileUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.RsaJsonWebKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes2.dex */
public class CTProductConfigController {
    public final FileUtils d;
    public final CleverTapInstanceConfig e;
    public final Context f;
    public final BaseAnalyticsManager h;
    public final BaseCallbackManager i;
    public final CoreMetaData j;
    @Deprecated
    public final ProductConfigSettings k;
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f2651a = Collections.synchronizedMap(new HashMap());
    @Deprecated
    public final Map<String, String> b = Collections.synchronizedMap(new HashMap());
    public AtomicBoolean c = new AtomicBoolean(false);
    public final AtomicBoolean g = new AtomicBoolean(false);
    public final Map<String, String> l = Collections.synchronizedMap(new HashMap());

    /* loaded from: classes2.dex */
    public class a implements OnSuccessListener<Void> {
        public a() {
        }

        @Override // com.clevertap.android.sdk.task.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Void r1) {
            CTProductConfigController.this.m();
        }
    }

    /* loaded from: classes2.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2653a;

        static {
            int[] iArr = new int[l.values().length];
            f2653a = iArr;
            try {
                iArr[l.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2653a[l.FETCHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2653a[l.ACTIVATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Void> {
        public c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (this) {
                try {
                    HashMap hashMap = new HashMap();
                    if (!CTProductConfigController.this.l.isEmpty()) {
                        hashMap.putAll(CTProductConfigController.this.l);
                        CTProductConfigController.this.l.clear();
                    } else {
                        CTProductConfigController cTProductConfigController = CTProductConfigController.this;
                        hashMap = cTProductConfigController.l(cTProductConfigController.j());
                    }
                    CTProductConfigController.this.f2651a.clear();
                    if (!CTProductConfigController.this.b.isEmpty()) {
                        CTProductConfigController cTProductConfigController2 = CTProductConfigController.this;
                        cTProductConfigController2.f2651a.putAll(cTProductConfigController2.b);
                    }
                    CTProductConfigController.this.f2651a.putAll(hashMap);
                    Logger logger = CTProductConfigController.this.e.getLogger();
                    String a2 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                    logger.verbose(a2, "Activated successfully with configs: " + CTProductConfigController.this.f2651a);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger logger2 = CTProductConfigController.this.e.getLogger();
                    String a3 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                    logger2.verbose(a3, "Activate failed: " + e.getLocalizedMessage());
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class d implements OnSuccessListener<Void> {
        public d() {
        }

        @Override // com.clevertap.android.sdk.task.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Void r2) {
            CTProductConfigController.this.r(l.ACTIVATED);
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Callable<Void> {
        public e() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            CTProductConfigController.this.e.getLogger().verbose(com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e), "Product Config: fetch Success");
            CTProductConfigController.this.r(l.FETCHED);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Callable<Void> {
        public final /* synthetic */ HashMap h;

        public f(HashMap hashMap) {
            this.h = hashMap;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (this) {
                HashMap hashMap = this.h;
                if (hashMap != null && !hashMap.isEmpty()) {
                    for (Map.Entry entry : this.h.entrySet()) {
                        if (entry != null) {
                            String str = (String) entry.getKey();
                            Object value = entry.getValue();
                            try {
                                if (!TextUtils.isEmpty(str) && com.clevertap.android.sdk.product_config.b.b(value)) {
                                    CTProductConfigController.this.b.put(str, String.valueOf(value));
                                }
                            } catch (Exception e) {
                                Logger logger = CTProductConfigController.this.e.getLogger();
                                String a2 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                                logger.verbose(a2, "Product Config: setDefaults Failed for Key: " + str + " with Error: " + e.getLocalizedMessage());
                            }
                        }
                    }
                }
                Logger logger2 = CTProductConfigController.this.e.getLogger();
                String a3 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                logger2.verbose(a3, "Product Config: setDefaults Completed with: " + CTProductConfigController.this.b);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class g implements OnSuccessListener<Void> {
        public g() {
        }

        @Override // com.clevertap.android.sdk.task.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Void r1) {
            CTProductConfigController.this.m();
        }
    }

    /* loaded from: classes2.dex */
    public class h implements Callable<Void> {
        public h() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (this) {
                try {
                    String k = CTProductConfigController.this.k();
                    CTProductConfigController.this.d.deleteDirectory(k);
                    Logger logger = CTProductConfigController.this.e.getLogger();
                    String a2 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                    logger.verbose(a2, "Reset Deleted Dir: " + k);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger logger2 = CTProductConfigController.this.e.getLogger();
                    String a3 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                    logger2.verbose(a3, "Reset failed: " + e.getLocalizedMessage());
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class i implements Callable<Boolean> {
        public i() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            Boolean bool;
            synchronized (this) {
                try {
                    try {
                        if (!CTProductConfigController.this.b.isEmpty()) {
                            CTProductConfigController cTProductConfigController = CTProductConfigController.this;
                            cTProductConfigController.f2651a.putAll(cTProductConfigController.b);
                        }
                        CTProductConfigController cTProductConfigController2 = CTProductConfigController.this;
                        HashMap l = cTProductConfigController2.l(cTProductConfigController2.j());
                        if (!l.isEmpty()) {
                            CTProductConfigController.this.l.putAll(l);
                        }
                        Logger logger = CTProductConfigController.this.e.getLogger();
                        String a2 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                        logger.verbose(a2, "Loaded configs ready to be applied: " + CTProductConfigController.this.l);
                        CTProductConfigController.this.k.n(CTProductConfigController.this.d);
                        CTProductConfigController.this.c.set(true);
                        bool = Boolean.TRUE;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger logger2 = CTProductConfigController.this.e.getLogger();
                        String a3 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                        logger2.verbose(a3, "InitAsync failed - " + e.getLocalizedMessage());
                        return Boolean.FALSE;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return bool;
        }
    }

    /* loaded from: classes2.dex */
    public class j implements OnSuccessListener<Boolean> {
        public j() {
        }

        @Override // com.clevertap.android.sdk.task.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Boolean bool) {
            CTProductConfigController.this.r(l.INIT);
        }
    }

    /* loaded from: classes2.dex */
    public class k implements Callable<Void> {
        public final /* synthetic */ com.clevertap.android.sdk.product_config.a h;
        public final /* synthetic */ int i;

        public k(com.clevertap.android.sdk.product_config.a aVar, int i) {
            this.h = aVar;
            this.i = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (this) {
                CTProductConfigController cTProductConfigController = CTProductConfigController.this;
                cTProductConfigController.b.putAll(this.h.a(cTProductConfigController.f, this.i));
                Logger logger = CTProductConfigController.this.e.getLogger();
                String a2 = com.clevertap.android.sdk.product_config.b.a(CTProductConfigController.this.e);
                logger.verbose(a2, "Product Config: setDefaults Completed with: " + CTProductConfigController.this.b);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public enum l {
        INIT,
        FETCHED,
        ACTIVATED
    }

    @Deprecated
    public CTProductConfigController(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseAnalyticsManager baseAnalyticsManager, CoreMetaData coreMetaData, BaseCallbackManager baseCallbackManager, ProductConfigSettings productConfigSettings, FileUtils fileUtils) {
        this.f = context;
        this.e = cleverTapInstanceConfig;
        this.j = coreMetaData;
        this.i = baseCallbackManager;
        this.h = baseAnalyticsManager;
        this.k = productConfigSettings;
        this.d = fileUtils;
        m();
    }

    @Deprecated
    public void activate() {
        if (TextUtils.isEmpty(this.k.getGuid())) {
            return;
        }
        CTExecutorFactory.executors(this.e).ioTask().addOnSuccessListener(new d()).execute("activateProductConfigs", new c());
    }

    @Deprecated
    public void fetch() {
        fetch(this.k.j());
    }

    @Deprecated
    public void fetchAndActivate() {
        fetch();
        this.g.set(true);
    }

    @Deprecated
    public void fetchProductConfig() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, 0);
            jSONObject.put("evtName", Constants.WZRK_FETCH);
            jSONObject.put("evtData", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.h.sendFetchEvent(jSONObject);
        this.j.setProductConfigRequested(true);
        this.e.getLogger().verbose(this.e.getAccountId(), "Product Config : Fetching product config");
    }

    public final boolean g(long j2) {
        if (!(!TextUtils.isEmpty(this.k.getGuid()))) {
            this.e.getLogger().verbose(com.clevertap.android.sdk.product_config.b.a(this.e), "Product Config: Throttled due to empty Guid");
            return false;
        }
        long h2 = this.k.h();
        long currentTimeMillis = (System.currentTimeMillis() - h2) - TimeUnit.SECONDS.toMillis(j2);
        if (currentTimeMillis > 0) {
            return true;
        }
        Logger logger = this.e.getLogger();
        String a2 = com.clevertap.android.sdk.product_config.b.a(this.e);
        logger.verbose(a2, "Throttled since you made frequent request- [Last Request Time-" + new Date(h2) + "], Try again in " + ((-currentTimeMillis) / 1000) + " seconds");
        return false;
    }

    @Deprecated
    public Boolean getBoolean(String str) {
        if (this.c.get() && !TextUtils.isEmpty(str)) {
            String str2 = this.f2651a.get(str);
            if (!TextUtils.isEmpty(str2)) {
                return Boolean.valueOf(Boolean.parseBoolean(str2));
            }
        }
        return CTProductConfigConstants.DEFAULT_VALUE_FOR_BOOLEAN;
    }

    @Deprecated
    public Double getDouble(String str) {
        if (this.c.get() && !TextUtils.isEmpty(str)) {
            try {
                String str2 = this.f2651a.get(str);
                if (!TextUtils.isEmpty(str2)) {
                    return Double.valueOf(Double.parseDouble(str2));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Logger logger = this.e.getLogger();
                String a2 = com.clevertap.android.sdk.product_config.b.a(this.e);
                logger.verbose(a2, "Error getting Double for Key-" + str + HexStringBuilder.DEFAULT_SEPARATOR + e2.getLocalizedMessage());
            }
        }
        return CTProductConfigConstants.DEFAULT_VALUE_FOR_DOUBLE;
    }

    @Deprecated
    public long getLastFetchTimeStampInMillis() {
        return this.k.h();
    }

    @Deprecated
    public Long getLong(String str) {
        if (this.c.get() && !TextUtils.isEmpty(str)) {
            try {
                String str2 = this.f2651a.get(str);
                if (!TextUtils.isEmpty(str2)) {
                    return Long.valueOf(Long.parseLong(str2));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Logger logger = this.e.getLogger();
                String a2 = com.clevertap.android.sdk.product_config.b.a(this.e);
                logger.verbose(a2, "Error getting Long for Key-" + str + HexStringBuilder.DEFAULT_SEPARATOR + e2.getLocalizedMessage());
            }
        }
        return CTProductConfigConstants.DEFAULT_VALUE_FOR_LONG;
    }

    @Deprecated
    public ProductConfigSettings getSettings() {
        return this.k;
    }

    @Deprecated
    public String getString(String str) {
        if (!this.c.get() || TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = this.f2651a.get(str);
        return !TextUtils.isEmpty(str2) ? str2 : "";
    }

    public final HashMap<String, String> h(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.KEY_KV);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                        if (jSONObject2 != null) {
                            String string = jSONObject2.getString("n");
                            String string2 = jSONObject2.getString(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE);
                            if (!TextUtils.isEmpty(string)) {
                                hashMap.put(string, string2);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Logger logger = this.e.getLogger();
                        String a2 = com.clevertap.android.sdk.product_config.b.a(this.e);
                        logger.verbose(a2, "ConvertServerJsonToMap failed: " + e2.getLocalizedMessage());
                    }
                }
            }
            return hashMap;
        } catch (JSONException e3) {
            e3.printStackTrace();
            Logger logger2 = this.e.getLogger();
            String a3 = com.clevertap.android.sdk.product_config.b.a(this.e);
            logger2.verbose(a3, "ConvertServerJsonToMap failed - " + e3.getLocalizedMessage());
            return hashMap;
        }
    }

    @Deprecated
    public void i() {
        CTExecutorFactory.executors(this.e).ioTask().execute("eraseStoredConfigs", new h());
    }

    @Deprecated
    public boolean isInitialized() {
        return this.c.get();
    }

    public String j() {
        return k() + MqttTopic.TOPIC_LEVEL_SEPARATOR + CTProductConfigConstants.FILE_NAME_ACTIVATED;
    }

    public String k() {
        return "Product_Config_" + this.e.getAccountId() + "_" + this.k.getGuid();
    }

    public final HashMap<String, String> l(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String readFromFile = this.d.readFromFile(str);
            Logger logger = this.e.getLogger();
            String a2 = com.clevertap.android.sdk.product_config.b.a(this.e);
            logger.verbose(a2, "GetStoredValues reading file success:[ " + str + "]--[Content]" + readFromFile);
            if (!TextUtils.isEmpty(readFromFile)) {
                try {
                    JSONObject jSONObject = new JSONObject(readFromFile);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!TextUtils.isEmpty(next)) {
                            try {
                                String valueOf = String.valueOf(jSONObject.get(next));
                                if (!TextUtils.isEmpty(valueOf)) {
                                    hashMap.put(next, valueOf);
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                Logger logger2 = this.e.getLogger();
                                String a3 = com.clevertap.android.sdk.product_config.b.a(this.e);
                                logger2.verbose(a3, "GetStoredValues for key " + next + " while parsing json: " + e2.getLocalizedMessage());
                            }
                        }
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    Logger logger3 = this.e.getLogger();
                    String a4 = com.clevertap.android.sdk.product_config.b.a(this.e);
                    logger3.verbose(a4, "GetStoredValues failed due to malformed json: " + e3.getLocalizedMessage());
                }
            }
            return hashMap;
        } catch (Exception e4) {
            e4.printStackTrace();
            Logger logger4 = this.e.getLogger();
            String a5 = com.clevertap.android.sdk.product_config.b.a(this.e);
            logger4.verbose(a5, "GetStoredValues reading file failed: " + e4.getLocalizedMessage());
            return hashMap;
        }
    }

    public void m() {
        if (TextUtils.isEmpty(this.k.getGuid())) {
            return;
        }
        CTExecutorFactory.executors(this.e).ioTask().addOnSuccessListener(new j()).execute("ProductConfig#initAsync", new i());
    }

    public final void n() {
        if (this.i.getProductConfigListener() != null) {
            this.i.getProductConfigListener().onActivated();
        }
    }

    public final void o() {
        if (this.i.getProductConfigListener() != null) {
            this.i.getProductConfigListener().onFetched();
        }
    }

    @Deprecated
    public void onFetchFailed() {
        this.g.compareAndSet(true, false);
        this.e.getLogger().verbose(com.clevertap.android.sdk.product_config.b.a(this.e), "Fetch Failed");
    }

    @Deprecated
    public void onFetchSuccess(JSONObject jSONObject) {
        if (TextUtils.isEmpty(this.k.getGuid())) {
            return;
        }
        synchronized (this) {
            if (jSONObject != null) {
                try {
                    q(jSONObject);
                    this.d.writeJsonToFile(k(), CTProductConfigConstants.FILE_NAME_ACTIVATED, new JSONObject((Map<?, ?>) this.l));
                    Logger logger = this.e.getLogger();
                    String a2 = com.clevertap.android.sdk.product_config.b.a(this.e);
                    logger.verbose(a2, "Fetch file-[" + j() + "] write success: " + this.l);
                    CTExecutorFactory.executors(this.e).mainTask().execute("sendPCFetchSuccessCallback", new e());
                    if (this.g.getAndSet(false)) {
                        activate();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.e.getLogger().verbose(com.clevertap.android.sdk.product_config.b.a(this.e), "Product Config: fetch Failed");
                    r(l.FETCHED);
                    this.g.compareAndSet(true, false);
                }
            }
        }
    }

    public final void p() {
        if (this.i.getProductConfigListener() != null) {
            this.e.getLogger().verbose(this.e.getAccountId(), "Product Config initialized");
            this.i.getProductConfigListener().onInit();
        }
    }

    public final synchronized void q(JSONObject jSONObject) {
        HashMap<String, String> h2 = h(jSONObject);
        this.l.clear();
        this.l.putAll(h2);
        Logger logger = this.e.getLogger();
        String a2 = com.clevertap.android.sdk.product_config.b.a(this.e);
        logger.verbose(a2, "Product Config: Fetched response:" + jSONObject);
        Integer num = null;
        try {
            num = (Integer) jSONObject.get(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP);
        } catch (Exception e2) {
            e2.printStackTrace();
            Logger logger2 = this.e.getLogger();
            String a3 = com.clevertap.android.sdk.product_config.b.a(this.e);
            logger2.verbose(a3, "ParseFetchedResponse failed: " + e2.getLocalizedMessage());
        }
        if (num != null) {
            this.k.s(num.intValue() * 1000);
        }
    }

    public final void r(l lVar) {
        if (lVar != null) {
            int i2 = b.f2653a[lVar.ordinal()];
            if (i2 == 1) {
                p();
            } else if (i2 == 2) {
                o();
            } else if (i2 != 3) {
            } else {
                n();
            }
        }
    }

    @Deprecated
    public void reset() {
        this.b.clear();
        this.f2651a.clear();
        this.k.m();
        i();
    }

    @Deprecated
    public void resetSettings() {
        this.k.p(this.d);
    }

    public void s(int i2, @NonNull com.clevertap.android.sdk.product_config.a aVar) {
        CTExecutorFactory.executors(this.e).ioTask().addOnSuccessListener(new a()).execute("PCController#setDefaultsWithXmlParser", new k(aVar, i2));
    }

    @Deprecated
    public void setArpValue(JSONObject jSONObject) {
        this.k.q(jSONObject);
    }

    @Deprecated
    public void setDefaults(int i2) {
        s(i2, new com.clevertap.android.sdk.product_config.a());
    }

    @Deprecated
    public void setGuidAndInit(String str) {
        if (isInitialized() || TextUtils.isEmpty(str)) {
            return;
        }
        this.k.r(str);
        m();
    }

    @Deprecated
    public void setMinimumFetchIntervalInSeconds(long j2) {
        this.k.t(j2);
    }

    @Deprecated
    public void fetch(long j2) {
        if (g(j2)) {
            fetchProductConfig();
        }
    }

    @Deprecated
    public void setDefaults(HashMap<String, Object> hashMap) {
        CTExecutorFactory.executors(this.e).ioTask().addOnSuccessListener(new g()).execute("ProductConfig#setDefaultsUsingHashMap", new f(hashMap));
    }
}
