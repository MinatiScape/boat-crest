package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.BiConsumer;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import org.json.JSONException;
/* loaded from: classes10.dex */
public class ConfigGetParameterHandler {
    @VisibleForTesting(otherwise = 3)
    public static final Charset FRC_BYTE_ARRAY_ENCODING = Charset.forName("UTF-8");
    public static final Pattern e = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
    public static final Pattern f = Pattern.compile("^(0|false|f|no|n|off|)$", 2);

    /* renamed from: a  reason: collision with root package name */
    public final Set<BiConsumer<String, ConfigContainer>> f11496a = new HashSet();
    public final Executor b;
    public final ConfigCacheClient c;
    public final ConfigCacheClient d;

    public ConfigGetParameterHandler(Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        this.b = executor;
        this.c = configCacheClient;
        this.d = configCacheClient2;
    }

    @Nullable
    public static ConfigContainer c(ConfigCacheClient configCacheClient) {
        return configCacheClient.getBlocking();
    }

    @Nullable
    public static Double d(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer c = c(configCacheClient);
        if (c == null) {
            return null;
        }
        try {
            return Double.valueOf(c.getConfigs().getDouble(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Set<String> e(ConfigCacheClient configCacheClient) {
        HashSet hashSet = new HashSet();
        ConfigContainer c = c(configCacheClient);
        if (c == null) {
            return hashSet;
        }
        Iterator<String> keys = c.getConfigs().keys();
        while (keys.hasNext()) {
            hashSet.add(keys.next());
        }
        return hashSet;
    }

    public static TreeSet<String> f(String str, ConfigContainer configContainer) {
        TreeSet<String> treeSet = new TreeSet<>();
        Iterator<String> keys = configContainer.getConfigs().keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (next.startsWith(str)) {
                treeSet.add(next);
            }
        }
        return treeSet;
    }

    @Nullable
    public static Long g(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer c = c(configCacheClient);
        if (c == null) {
            return null;
        }
        try {
            return Long.valueOf(c.getConfigs().getLong(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    public static String h(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer c = c(configCacheClient);
        if (c == null) {
            return null;
        }
        try {
            return c.getConfigs().getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void j(String str, String str2) {
        Log.w(FirebaseRemoteConfig.TAG, String.format("No value of type '%s' exists for parameter key '%s'.", str2, str));
    }

    public void addListener(BiConsumer<String, ConfigContainer> biConsumer) {
        synchronized (this.f11496a) {
            this.f11496a.add(biConsumer);
        }
    }

    public final void b(final String str, final ConfigContainer configContainer) {
        if (configContainer == null) {
            return;
        }
        synchronized (this.f11496a) {
            for (final BiConsumer<String, ConfigContainer> biConsumer : this.f11496a) {
                this.b.execute(new Runnable() { // from class: com.google.firebase.remoteconfig.internal.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        BiConsumer.this.accept(str, configContainer);
                    }
                });
            }
        }
    }

    public Map<String, FirebaseRemoteConfigValue> getAll() {
        HashSet<String> hashSet = new HashSet();
        hashSet.addAll(e(this.c));
        hashSet.addAll(e(this.d));
        HashMap hashMap = new HashMap();
        for (String str : hashSet) {
            hashMap.put(str, getValue(str));
        }
        return hashMap;
    }

    public boolean getBoolean(String str) {
        String h = h(this.c, str);
        if (h != null) {
            if (e.matcher(h).matches()) {
                b(str, c(this.c));
                return true;
            } else if (f.matcher(h).matches()) {
                b(str, c(this.c));
                return false;
            }
        }
        String h2 = h(this.d, str);
        if (h2 != null) {
            if (e.matcher(h2).matches()) {
                return true;
            }
            if (f.matcher(h2).matches()) {
                return false;
            }
        }
        j(str, "Boolean");
        return false;
    }

    public byte[] getByteArray(String str) {
        String h = h(this.c, str);
        if (h != null) {
            b(str, c(this.c));
            return h.getBytes(FRC_BYTE_ARRAY_ENCODING);
        }
        String h2 = h(this.d, str);
        if (h2 != null) {
            return h2.getBytes(FRC_BYTE_ARRAY_ENCODING);
        }
        j(str, "ByteArray");
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY;
    }

    public double getDouble(String str) {
        Double d = d(this.c, str);
        if (d != null) {
            b(str, c(this.c));
            return d.doubleValue();
        }
        Double d2 = d(this.d, str);
        if (d2 != null) {
            return d2.doubleValue();
        }
        j(str, "Double");
        return 0.0d;
    }

    public Set<String> getKeysByPrefix(String str) {
        if (str == null) {
            str = "";
        }
        TreeSet treeSet = new TreeSet();
        ConfigContainer c = c(this.c);
        if (c != null) {
            treeSet.addAll(f(str, c));
        }
        ConfigContainer c2 = c(this.d);
        if (c2 != null) {
            treeSet.addAll(f(str, c2));
        }
        return treeSet;
    }

    public long getLong(String str) {
        Long g = g(this.c, str);
        if (g != null) {
            b(str, c(this.c));
            return g.longValue();
        }
        Long g2 = g(this.d, str);
        if (g2 != null) {
            return g2.longValue();
        }
        j(str, "Long");
        return 0L;
    }

    public String getString(String str) {
        String h = h(this.c, str);
        if (h != null) {
            b(str, c(this.c));
            return h;
        }
        String h2 = h(this.d, str);
        if (h2 != null) {
            return h2;
        }
        j(str, "String");
        return "";
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        String h = h(this.c, str);
        if (h != null) {
            b(str, c(this.c));
            return new FirebaseRemoteConfigValueImpl(h, 2);
        }
        String h2 = h(this.d, str);
        if (h2 != null) {
            return new FirebaseRemoteConfigValueImpl(h2, 1);
        }
        j(str, "FirebaseRemoteConfigValue");
        return new FirebaseRemoteConfigValueImpl("", 0);
    }
}
