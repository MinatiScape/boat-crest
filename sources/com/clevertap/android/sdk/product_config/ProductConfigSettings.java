package com.clevertap.android.sdk.product_config;

import android.text.TextUtils;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.utils.FileUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes2.dex */
public class ProductConfigSettings {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapInstanceConfig f2657a;
    public String b;
    public final FileUtils c;
    public final Map<String, String> d = Collections.synchronizedMap(new HashMap());

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ FileUtils h;

        public a(FileUtils fileUtils) {
            this.h = fileUtils;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (this) {
                try {
                    String f = ProductConfigSettings.this.f();
                    this.h.deleteFile(f);
                    Logger logger = ProductConfigSettings.this.f2657a.getLogger();
                    String a2 = com.clevertap.android.sdk.product_config.b.a(ProductConfigSettings.this.f2657a);
                    logger.verbose(a2, "Deleted settings file" + f);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger logger2 = ProductConfigSettings.this.f2657a.getLogger();
                    String a3 = com.clevertap.android.sdk.product_config.b.a(ProductConfigSettings.this.f2657a);
                    logger2.verbose(a3, "Error while resetting settings" + e.getLocalizedMessage());
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Callable<Boolean> {
        public b() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            try {
                HashMap hashMap = new HashMap(ProductConfigSettings.this.d);
                hashMap.remove(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS);
                ProductConfigSettings.this.c.writeJsonToFile(ProductConfigSettings.this.e(), CTProductConfigConstants.FILE_NAME_CONFIG_SETTINGS, new JSONObject((Map<?, ?>) hashMap));
                return Boolean.TRUE;
            } catch (Exception e) {
                e.printStackTrace();
                Logger logger = ProductConfigSettings.this.f2657a.getLogger();
                String a2 = com.clevertap.android.sdk.product_config.b.a(ProductConfigSettings.this.f2657a);
                logger.verbose(a2, "UpdateConfigToFile failed: " + e.getLocalizedMessage());
                return Boolean.FALSE;
            }
        }
    }

    /* loaded from: classes2.dex */
    public class c implements OnSuccessListener<Boolean> {
        public c() {
        }

        @Override // com.clevertap.android.sdk.task.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Boolean bool) {
            if (bool.booleanValue()) {
                Logger logger = ProductConfigSettings.this.f2657a.getLogger();
                String a2 = com.clevertap.android.sdk.product_config.b.a(ProductConfigSettings.this.f2657a);
                logger.verbose(a2, "Product Config settings: writing Success " + ProductConfigSettings.this.d);
                return;
            }
            ProductConfigSettings.this.f2657a.getLogger().verbose(com.clevertap.android.sdk.product_config.b.a(ProductConfigSettings.this.f2657a), "Product Config settings: writing Failed");
        }
    }

    @Deprecated
    public ProductConfigSettings(String str, CleverTapInstanceConfig cleverTapInstanceConfig, FileUtils fileUtils) {
        this.b = str;
        this.f2657a = cleverTapInstanceConfig;
        this.c = fileUtils;
        m();
    }

    public void d(FileUtils fileUtils) {
        if (fileUtils != null) {
            CTExecutorFactory.executors(this.f2657a).ioTask().execute("ProductConfigSettings#eraseStoredSettingsFile", new a(fileUtils));
            return;
        }
        throw new IllegalArgumentException("FileUtils can't be null");
    }

    public String e() {
        return "Product_Config_" + this.f2657a.getAccountId() + "_" + this.b;
    }

    public String f() {
        return e() + MqttTopic.TOPIC_LEVEL_SEPARATOR + CTProductConfigConstants.FILE_NAME_CONFIG_SETTINGS;
    }

    public JSONObject g(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            Logger logger = this.f2657a.getLogger();
            String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
            logger.verbose(a2, "LoadSettings failed: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Deprecated
    public String getGuid() {
        return this.b;
    }

    public synchronized long h() {
        long j;
        j = 0;
        String str = this.d.get(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP);
        try {
            if (!TextUtils.isEmpty(str)) {
                j = (long) Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = this.f2657a.getLogger();
            String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
            logger.verbose(a2, "GetLastFetchTimeStampInMillis failed: " + e.getLocalizedMessage());
        }
        return j;
    }

    public final long i() {
        long j = CTProductConfigConstants.DEFAULT_MIN_FETCH_INTERVAL_SECONDS;
        String str = this.d.get(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS);
        try {
            return !TextUtils.isEmpty(str) ? (long) Double.parseDouble(str) : j;
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = this.f2657a.getLogger();
            String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
            logger.verbose(a2, "GetMinFetchIntervalInSeconds failed: " + e.getLocalizedMessage());
            return j;
        }
    }

    public long j() {
        return Math.max(TimeUnit.MINUTES.toSeconds(l() / k()), i());
    }

    public final synchronized int k() {
        int i;
        i = 5;
        String str = this.d.get(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS);
        try {
            if (!TextUtils.isEmpty(str)) {
                i = (int) Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = this.f2657a.getLogger();
            String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
            logger.verbose(a2, "GetNoOfCallsInAllowedWindow failed: " + e.getLocalizedMessage());
        }
        return i;
    }

    public final synchronized int l() {
        int i;
        i = 60;
        String str = this.d.get(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS);
        try {
            if (!TextUtils.isEmpty(str)) {
                i = (int) Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = this.f2657a.getLogger();
            String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
            logger.verbose(a2, "GetWindowIntervalInMinutes failed: " + e.getLocalizedMessage());
        }
        return i;
    }

    public void m() {
        this.d.put(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS, String.valueOf(5));
        this.d.put(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS, String.valueOf(60));
        this.d.put(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP, String.valueOf(0));
        this.d.put(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS, String.valueOf(CTProductConfigConstants.DEFAULT_MIN_FETCH_INTERVAL_SECONDS));
        Logger logger = this.f2657a.getLogger();
        String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
        logger.verbose(a2, "Settings loaded with default values: " + this.d);
    }

    public synchronized void n(FileUtils fileUtils) {
        if (fileUtils != null) {
            try {
                o(g(fileUtils.readFromFile(f())));
            } catch (Exception e) {
                e.printStackTrace();
                Logger logger = this.f2657a.getLogger();
                String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
                logger.verbose(a2, "LoadSettings failed while reading file: " + e.getLocalizedMessage());
            }
        } else {
            throw new IllegalArgumentException("fileutils can't be null");
        }
    }

    public synchronized void o(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!TextUtils.isEmpty(next)) {
                try {
                    String valueOf = String.valueOf(jSONObject.get(next));
                    if (!TextUtils.isEmpty(valueOf)) {
                        this.d.put(next, valueOf);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger logger = this.f2657a.getLogger();
                    String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
                    logger.verbose(a2, "Failed loading setting for key " + next + " Error: " + e.getLocalizedMessage());
                }
            }
        }
        Logger logger2 = this.f2657a.getLogger();
        String a3 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
        logger2.verbose(a3, "LoadSettings completed with settings: " + this.d);
    }

    public void p(FileUtils fileUtils) {
        m();
        d(fileUtils);
    }

    public void q(JSONObject jSONObject) {
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    if (!TextUtils.isEmpty(next)) {
                        Object obj = jSONObject.get(next);
                        if (obj instanceof Number) {
                            int doubleValue = (int) ((Number) obj).doubleValue();
                            if (CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS.equalsIgnoreCase(next) || CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS.equalsIgnoreCase(next)) {
                                v(next, doubleValue);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger logger = this.f2657a.getLogger();
                    String a2 = com.clevertap.android.sdk.product_config.b.a(this.f2657a);
                    logger.verbose(a2, "Product Config setARPValue failed " + e.getLocalizedMessage());
                }
            }
        }
    }

    public void r(String str) {
        this.b = str;
    }

    public synchronized void s(long j) {
        long h = h();
        if (j >= 0 && h != j) {
            this.d.put(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP, String.valueOf(j));
            x();
        }
    }

    public synchronized void t(long j) {
        long i = i();
        if (j > 0 && i != j) {
            this.d.put(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS, String.valueOf(j));
        }
    }

    public final synchronized void u(int i) {
        long k = k();
        if (i > 0 && k != i) {
            this.d.put(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS, String.valueOf(i));
            x();
        }
    }

    public final void v(String str, int i) {
        str.hashCode();
        if (str.equals(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS)) {
            u(i);
        } else if (str.equals(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS)) {
            w(i);
        }
    }

    public final synchronized void w(int i) {
        int l = l();
        if (i > 0 && l != i) {
            this.d.put(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS, String.valueOf(i));
            x();
        }
    }

    public final synchronized void x() {
        CTExecutorFactory.executors(this.f2657a).ioTask().addOnSuccessListener(new c()).execute("ProductConfigSettings#updateConfigToFile", new b());
    }
}
