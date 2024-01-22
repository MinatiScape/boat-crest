package com.clevertap.android.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.cryption.CryptUtils;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.events.EventDetail;
import com.google.android.gms.common.Scopes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class LocalDataStore {
    public static long h;
    public final CleverTapInstanceConfig c;
    public final Context d;
    public final CryptHandler e;
    public DBAdapter f;

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, Integer> f2581a = new HashMap<>();
    public final HashMap<String, Object> b = new HashMap<>();
    public final ExecutorService g = Executors.newFixedThreadPool(1);

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ Context h;
        public final /* synthetic */ String i;

        public a(Context context, String str) {
            this.h = context;
            this.i = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject fetchUserProfileById;
            String decrypt;
            if (LocalDataStore.this.f == null) {
                LocalDataStore.this.f = new DBAdapter(this.h, LocalDataStore.this.c);
            }
            synchronized (LocalDataStore.this.b) {
                try {
                    fetchUserProfileById = LocalDataStore.this.f.fetchUserProfileById(this.i);
                } catch (Throwable unused) {
                }
                if (fetchUserProfileById == null) {
                    return;
                }
                Iterator<String> keys = fetchUserProfileById.keys();
                while (keys.hasNext()) {
                    try {
                        String next = keys.next();
                        Object obj = fetchUserProfileById.get(next);
                        if (obj instanceof JSONObject) {
                            LocalDataStore.this.b.put(next, fetchUserProfileById.getJSONObject(next));
                        } else if (obj instanceof JSONArray) {
                            LocalDataStore.this.b.put(next, fetchUserProfileById.getJSONArray(next));
                        } else {
                            if ((obj instanceof String) && (decrypt = LocalDataStore.this.e.decrypt((String) obj, next)) != null) {
                                obj = decrypt;
                            }
                            LocalDataStore.this.b.put(next, obj);
                        }
                    } catch (JSONException unused2) {
                    }
                }
                Logger r = LocalDataStore.this.r();
                String q = LocalDataStore.this.q();
                r.verbose(q, "Local Data Store - Inflated local profile " + LocalDataStore.this.b.toString());
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public final /* synthetic */ String h;

        public b(String str) {
            this.h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (LocalDataStore.this.b) {
                HashMap hashMap = LocalDataStore.this.b;
                boolean z = true;
                Iterator<String> it = Constants.piiDBKeys.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (hashMap.get(next) != null) {
                        Object obj = hashMap.get(next);
                        if (obj instanceof String) {
                            String encrypt = LocalDataStore.this.e.encrypt((String) obj, next);
                            if (encrypt == null) {
                                z = false;
                            } else {
                                hashMap.put(next, encrypt);
                            }
                        }
                    }
                }
                JSONObject jSONObject = new JSONObject((Map<?, ?>) hashMap);
                if (!z) {
                    CryptUtils.updateEncryptionFlagOnFailure(LocalDataStore.this.d, LocalDataStore.this.c, 2, LocalDataStore.this.e);
                }
                long storeUserProfile = LocalDataStore.this.f.storeUserProfile(this.h, jSONObject);
                Logger r = LocalDataStore.this.r();
                String q = LocalDataStore.this.q();
                r.verbose(q, "Persist Local Profile complete with status " + storeUserProfile + " for id " + this.h);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Runnable {
        public final /* synthetic */ String h;
        public final /* synthetic */ Runnable i;

        public c(String str, Runnable runnable) {
            this.h = str;
            this.i = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            long unused = LocalDataStore.h = Thread.currentThread().getId();
            try {
                Logger r = LocalDataStore.this.r();
                String q = LocalDataStore.this.q();
                r.verbose(q, "Local Data Store Executor service: Starting task - " + this.h);
                this.i.run();
            } catch (Throwable th) {
                LocalDataStore.this.r().verbose(LocalDataStore.this.q(), "Executor service: Failed to complete the scheduled task", th);
            }
        }
    }

    public LocalDataStore(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CryptHandler cryptHandler) {
        this.d = context;
        this.c = cleverTapInstanceConfig;
        this.e = cryptHandler;
        B(context);
    }

    public final String A() {
        return this.c.getAccountId();
    }

    public final void B(Context context) {
        F("LocalDataStore#inflateLocalProfileAsync", new a(context, this.c.getAccountId()));
    }

    public final boolean C() {
        return this.c.c();
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void D(Context context, JSONObject jSONObject) {
        String str;
        try {
            String string = jSONObject.getString("evtName");
            if (string == null) {
                return;
            }
            if (this.c.isDefaultInstance()) {
                str = "local_events";
            } else {
                str = "local_events:" + this.c.getAccountId();
            }
            SharedPreferences preferences = StorageHelper.getPreferences(context, str);
            int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            EventDetail o = o(string, z(string, p(currentTimeMillis, currentTimeMillis, 0), str));
            String p = p(o.getFirstTime(), currentTimeMillis, o.getCount() + 1);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString(R(string), p);
            StorageHelper.persist(edit);
        } catch (Throwable th) {
            r().verbose(q(), "Failed to persist event locally", th);
        }
    }

    public final void E() {
        F("LocalDataStore#persistLocalProfileAsync", new b(this.c.getAccountId()));
    }

    public final void F(String str, Runnable runnable) {
        try {
            if (Thread.currentThread().getId() == h) {
                runnable.run();
            } else {
                this.g.submit(new c(str, runnable));
            }
        } catch (Throwable th) {
            r().verbose(q(), "Failed to submit task to the executor service", th);
        }
    }

    public final boolean G(Object obj) {
        if (obj == null) {
            return true;
        }
        boolean z = (obj instanceof String) && ((String) obj).trim().length() == 0;
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).length() <= 0;
        }
        return z;
    }

    public final Boolean H(Object obj, Object obj2) {
        return Boolean.valueOf(S(obj).equals(S(obj2)));
    }

    @WorkerThread
    public void I(String str) {
        J(str, Boolean.FALSE, true);
    }

    public final void J(String str, Boolean bool, boolean z) {
        if (str == null) {
            return;
        }
        try {
            b(str);
            if (!bool.booleanValue()) {
                V(str);
            }
        } catch (Throwable unused) {
        }
        if (z) {
            E();
        }
    }

    public final void K() {
        synchronized (this.f2581a) {
            this.f2581a.clear();
        }
        synchronized (this.b) {
            this.b.clear();
        }
        this.f.removeUserProfile(A());
    }

    public final void L(Context context, int i) {
        StorageHelper.putInt(context, R("local_cache_expires_in"), i);
    }

    public void M(String str, Object obj) {
        N(str, obj, Boolean.FALSE, true);
    }

    public final void N(String str, Object obj, Boolean bool, boolean z) {
        if (str == null || obj == null) {
            return;
        }
        try {
            c(str, obj);
            if (!bool.booleanValue()) {
                V(str);
            }
        } catch (Throwable unused) {
        }
        if (z) {
            E();
        }
    }

    public void O(JSONObject jSONObject) {
        P(jSONObject, Boolean.FALSE);
    }

    public final void P(JSONObject jSONObject, Boolean bool) {
        if (jSONObject == null) {
            return;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                N(obj, jSONObject.get(obj), bool, false);
            }
            E();
        } catch (Throwable th) {
            r().verbose(q(), "Failed to set profile fields", th);
        }
    }

    public final Boolean Q(String str, int i) {
        if (i <= 0) {
            i = (int) (System.currentTimeMillis() / 1000);
        }
        Integer w = w(str);
        return Boolean.valueOf(w != null && w.intValue() > i);
    }

    public final String R(String str) {
        return str + ":" + this.c.getAccountId();
    }

    public final String S(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public final JSONObject T(Context context, JSONObject jSONObject) {
        String str;
        LocalDataStore localDataStore = this;
        try {
            if (localDataStore.c.isDefaultInstance()) {
                str = "local_events";
            } else {
                str = "local_events:" + localDataStore.c.getAccountId();
            }
            String str2 = str;
            SharedPreferences preferences = StorageHelper.getPreferences(context, str2);
            Iterator<String> keys = jSONObject.keys();
            SharedPreferences.Editor edit = preferences.edit();
            JSONObject jSONObject2 = null;
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                EventDetail o = localDataStore.o(obj, localDataStore.z(obj, localDataStore.p(0, 0, 0), str2));
                JSONArray jSONArray = jSONObject.getJSONArray(obj);
                if (jSONArray != null && jSONArray.length() >= 3) {
                    int i = jSONArray.getInt(0);
                    int i2 = jSONArray.getInt(1);
                    int i3 = jSONArray.getInt(2);
                    if (i > o.getCount()) {
                        edit.putString(localDataStore.R(obj), localDataStore.p(i2, i3, i));
                        r().verbose(q(), "Accepted update for event " + obj + " from upstream");
                        if (jSONObject2 == null) {
                            jSONObject2 = new JSONObject();
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("oldValue", o.getCount());
                        jSONObject4.put("newValue", i);
                        jSONObject3.put("count", jSONObject4);
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("oldValue", o.getFirstTime());
                        jSONObject5.put("newValue", jSONArray.getInt(1));
                        jSONObject3.put("firstTime", jSONObject5);
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put("oldValue", o.getLastTime());
                        jSONObject6.put("newValue", jSONArray.getInt(2));
                        jSONObject3.put("lastTime", jSONObject6);
                        jSONObject2.put(obj, jSONObject3);
                    } else {
                        r().verbose(q(), "Rejected update for event " + obj + " from upstream");
                    }
                } else {
                    r().verbose(q(), "Corrupted upstream event detail");
                }
                localDataStore = this;
                jSONObject2 = jSONObject2;
            }
            StorageHelper.persist(edit);
            return jSONObject2;
        } catch (Throwable th) {
            r().verbose(q(), "Couldn't sync events from upstream", th);
            return null;
        }
    }

    public final JSONObject U(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null || jSONObject.length() <= 0) {
            return jSONObject2;
        }
        try {
            JSONObject jSONObject3 = new JSONObject();
            int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                if (Q(obj, currentTimeMillis).booleanValue()) {
                    r().verbose(q(), "Rejecting upstream value for key " + obj + " because our local cache prohibits it");
                } else {
                    Object y = y(obj);
                    Object obj2 = jSONObject.get(obj);
                    if (G(obj2)) {
                        obj2 = null;
                    }
                    if (!H(obj2, y).booleanValue()) {
                        if (obj2 != null) {
                            try {
                                jSONObject3.put(obj, obj2);
                            } catch (Throwable th) {
                                r().verbose(q(), "Failed to set profile updates", th);
                            }
                        } else {
                            J(obj, Boolean.TRUE, true);
                        }
                        JSONObject m = m(y, obj2);
                        if (m != null) {
                            jSONObject2.put(obj, m);
                        }
                    }
                }
            }
            if (jSONObject3.length() > 0) {
                P(jSONObject3, Boolean.TRUE);
            }
            return jSONObject2;
        } catch (Throwable th2) {
            r().verbose(q(), "Failed to sync remote profile", th2);
            return null;
        }
    }

    public final void V(String str) {
        if (str == null) {
            return;
        }
        synchronized (this.f2581a) {
            this.f2581a.put(str, Integer.valueOf(n()));
        }
    }

    public final Object a(String str) {
        if (str == null) {
            return null;
        }
        synchronized (this.b) {
            Object obj = this.b.get(str);
            if ((obj instanceof String) && CryptHandler.isTextEncrypted((String) obj)) {
                r().verbose(q(), "Failed to retrieve local profile property because it wasn't decrypted");
                return null;
            }
            return this.b.get(str);
        }
    }

    public final void b(String str) {
        if (str == null) {
            return;
        }
        synchronized (this.b) {
            try {
                this.b.remove(str);
            }
        }
    }

    public final void c(String str, Object obj) {
        if (str == null || obj == null) {
            return;
        }
        synchronized (this.b) {
            this.b.put(str, obj);
        }
    }

    @WorkerThread
    public void changeUser() {
        K();
    }

    public final JSONObject m(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        if (obj2 == null) {
            try {
                obj2 = -1;
            } catch (Throwable th) {
                r().verbose(q(), "Failed to create profile changed values object", th);
                return null;
            }
        }
        jSONObject.put("newValue", obj2);
        if (obj != null) {
            jSONObject.put("oldValue", obj);
        }
        return jSONObject;
    }

    public final int n() {
        return ((int) (System.currentTimeMillis() / 1000)) + v(0);
    }

    public final EventDetail o(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        String[] split = str2.split("\\|");
        return new EventDetail(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), str);
    }

    public final String p(int i, int i2, int i3) {
        return i3 + "|" + i + "|" + i2;
    }

    @WorkerThread
    public void persistEvent(Context context, JSONObject jSONObject, int i) {
        if (jSONObject != null && i == 4) {
            try {
                D(context, jSONObject);
            } catch (Throwable th) {
                r().verbose(q(), "Failed to sync with upstream", th);
            }
        }
    }

    public final String q() {
        return this.c.getAccountId();
    }

    public final Logger r() {
        return this.c.getLogger();
    }

    public EventDetail s(String str) {
        String str2;
        try {
            if (C()) {
                if (this.c.isDefaultInstance()) {
                    str2 = "local_events";
                } else {
                    str2 = "local_events:" + this.c.getAccountId();
                }
                return o(str, z(str, null, str2));
            }
            return null;
        } catch (Throwable th) {
            r().verbose(q(), "Failed to retrieve local event detail", th);
            return null;
        }
    }

    @WorkerThread
    public void setDataSyncFlag(JSONObject jSONObject) {
        try {
            if (!this.c.c()) {
                jSONObject.put("dsync", false);
                return;
            }
            String string = jSONObject.getString("type");
            if ("event".equals(string) && Constants.APP_LAUNCHED_EVENT.equals(jSONObject.getString("evtName"))) {
                r().verbose(q(), "Local cache needs to be updated (triggered by App Launched)");
                jSONObject.put("dsync", true);
            } else if (Scopes.PROFILE.equals(string)) {
                jSONObject.put("dsync", true);
                r().verbose(q(), "Local cache needs to be updated (profile event)");
            } else {
                int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                if (u("local_cache_last_update", currentTimeMillis) + v(1200) < currentTimeMillis) {
                    jSONObject.put("dsync", true);
                    r().verbose(q(), "Local cache needs to be updated");
                    return;
                }
                jSONObject.put("dsync", false);
                r().verbose(q(), "Local cache doesn't need to be updated");
            }
        } catch (Throwable th) {
            r().verbose(q(), "Failed to sync with upstream", th);
        }
    }

    public void syncWithUpstream(Context context, JSONObject jSONObject) {
        JSONObject jSONObject2;
        Object obj;
        try {
            if (jSONObject.has("evpr")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("evpr");
                SyncListener syncListener = null;
                if (jSONObject3.has(Scopes.PROFILE)) {
                    JSONObject jSONObject4 = jSONObject3.getJSONObject(Scopes.PROFILE);
                    if (jSONObject4.has("_custom")) {
                        JSONObject jSONObject5 = jSONObject4.getJSONObject("_custom");
                        jSONObject4.remove("_custom");
                        Iterator<String> keys = jSONObject5.keys();
                        while (keys.hasNext()) {
                            String obj2 = keys.next().toString();
                            try {
                                try {
                                    obj = jSONObject5.getJSONArray(obj2);
                                } catch (Throwable unused) {
                                    obj = jSONObject5.get(obj2);
                                }
                            } catch (JSONException unused2) {
                                obj = null;
                            }
                            if (obj != null) {
                                jSONObject4.put(obj2, obj);
                            }
                        }
                    }
                    jSONObject2 = U(jSONObject4);
                } else {
                    jSONObject2 = null;
                }
                JSONObject T = jSONObject3.has("events") ? T(context, jSONObject3.getJSONObject("events")) : null;
                if (jSONObject3.has("expires_in")) {
                    L(context, jSONObject3.getInt("expires_in"));
                }
                StorageHelper.putInt(context, R("local_cache_last_update"), (int) (System.currentTimeMillis() / 1000));
                boolean z = true;
                Boolean valueOf = Boolean.valueOf(jSONObject2 != null && jSONObject2.length() > 0);
                if (T == null || T.length() <= 0) {
                    z = false;
                }
                Boolean valueOf2 = Boolean.valueOf(z);
                if (valueOf.booleanValue() || valueOf2.booleanValue()) {
                    JSONObject jSONObject6 = new JSONObject();
                    if (valueOf.booleanValue()) {
                        jSONObject6.put(Scopes.PROFILE, jSONObject2);
                    }
                    if (valueOf2.booleanValue()) {
                        jSONObject6.put("events", T);
                    }
                    try {
                        CleverTapAPI defaultInstance = CleverTapAPI.getDefaultInstance(context);
                        if (defaultInstance != null) {
                            syncListener = defaultInstance.getSyncListener();
                        }
                    } catch (Throwable unused3) {
                    }
                    if (syncListener != null) {
                        syncListener.profileDataUpdated(jSONObject6);
                    }
                }
            }
        } catch (Throwable th) {
            r().verbose(q(), "Failed to sync with upstream", th);
        }
    }

    public Map<String, EventDetail> t(Context context) {
        try {
            Map<String, ?> all = StorageHelper.getPreferences(context, this.c.isDefaultInstance() ? "local_events" : "local_events:" + this.c.getAccountId()).getAll();
            HashMap hashMap = new HashMap();
            for (String str : all.keySet()) {
                hashMap.put(str, o(str, all.get(str).toString()));
            }
            return hashMap;
        } catch (Throwable th) {
            r().verbose(q(), "Failed to retrieve local event history", th);
            return null;
        }
    }

    public final int u(String str, int i) {
        if (this.c.isDefaultInstance()) {
            int i2 = StorageHelper.getInt(this.d, R(str), -1000);
            return i2 != -1000 ? i2 : StorageHelper.getInt(this.d, str, i);
        }
        return StorageHelper.getInt(this.d, R(str), i);
    }

    public final int v(int i) {
        return u("local_cache_expires_in", i);
    }

    public final Integer w(String str) {
        Integer num;
        if (str == null) {
            return 0;
        }
        synchronized (this.f2581a) {
            num = this.f2581a.get(str);
        }
        return num;
    }

    public Object x(String str) {
        return y(str);
    }

    public Object y(String str) {
        return a(str);
    }

    public final String z(String str, String str2, String str3) {
        if (this.c.isDefaultInstance()) {
            String c2 = StorageHelper.c(this.d, str3, R(str), str2);
            return c2 != null ? c2 : StorageHelper.c(this.d, str3, str, str2);
        }
        return StorageHelper.c(this.d, str3, R(str), str2);
    }
}
