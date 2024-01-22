package com.clevertap.android.sdk.variables;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class VarCache {
    public final Context f;
    public final CleverTapInstanceConfig g;

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Object> f2695a = new HashMap();
    public final Map<String, Var<?>> b = new ConcurrentHashMap();
    public final Map<String, String> c = new HashMap();
    public Runnable d = null;
    public Map<String, Object> e = new HashMap();
    public Object merged = null;

    public VarCache(CleverTapInstanceConfig cleverTapInstanceConfig, Context context) {
        this.f = context;
        this.g = cleverTapInstanceConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void c() throws Exception {
        h();
        return null;
    }

    public static void e(String str) {
        Logger.d("variables", str);
    }

    public static void f(String str, Throwable th) {
        Logger.d("variables", str, th);
    }

    public final void b(Map<String, Object> map) {
        e("applyVariableDiffs() called with: diffs = [" + map + "]");
        if (map != null) {
            this.e = map;
            this.merged = CTVariableUtils.mergeHelper(this.f2695a, map);
            e("applyVariableDiffs: updated value of merged=[" + this.merged + "]");
            for (String str : new HashMap(this.b).keySet()) {
                Var<?> var = this.b.get(str);
                if (var != null) {
                    var.update();
                }
            }
        }
    }

    public synchronized void clearUserContent() {
        e("Clear user content in VarCache");
        for (String str : new HashMap(this.b).keySet()) {
            Var<?> var = this.b.get(str);
            if (var != null) {
                var.b();
            }
        }
        b(new HashMap());
        i();
    }

    public final String d() {
        String string = StorageHelper.getString(this.f, StorageHelper.storageKeyWithSuffix(this.g, Constants.CACHED_VARIABLES_KEY), "{}");
        e("VarCache loaded cache data:\n" + string);
        return string;
    }

    @VisibleForTesting
    public void g(@NonNull Var<?> var) {
        Object obj = this.merged;
        if (obj == null) {
            e("mergeVariable() called, but `merged` member is null.");
        } else if (!(obj instanceof Map)) {
            e("mergeVariable() called, but `merged` member is not of Map type.");
        } else {
            boolean z = false;
            String str = var.nameComponents()[0];
            Object obj2 = this.f2695a.get(str);
            Map map = (Map) JsonUtil.uncheckedCast(this.merged);
            Object obj3 = map.get(str);
            if ((obj2 == null && obj3 != null) || (obj2 != null && !obj2.equals(obj3))) {
                z = true;
            }
            if (z) {
                map.put(str, CTVariableUtils.mergeHelper(obj2, obj3));
                StringBuilder sb = new StringBuilder(str);
                for (int i = 1; i < var.nameComponents().length; i++) {
                    Var<?> var2 = this.b.get(sb.toString());
                    if (var2 != null) {
                        var2.update();
                    }
                    sb.append('.');
                    sb.append(var.nameComponents()[i]);
                }
            }
        }
    }

    public JSONObject getDefineVarsData() {
        return CTVariableUtils.getFlatVarsJson(this.f2695a, this.c);
    }

    public synchronized Object getMergedValue(String str) {
        Object mergedValueFromComponentArray = getMergedValueFromComponentArray(CTVariableUtils.getNameComponents(str));
        if (mergedValueFromComponentArray instanceof Map) {
            return CTVariableUtils.deepCopyMap((Map) JsonUtil.uncheckedCast(mergedValueFromComponentArray));
        }
        return mergedValueFromComponentArray;
    }

    public synchronized <T> T getMergedValueFromComponentArray(Object[] objArr) {
        Object obj;
        obj = this.merged;
        if (obj == null) {
            obj = this.f2695a;
        }
        return (T) getMergedValueFromComponentArray(objArr, obj);
    }

    public synchronized <T> Var<T> getVariable(String str) {
        return (Var) JsonUtil.uncheckedCast(this.b.get(str));
    }

    @WorkerThread
    public final void h() {
        e("saveDiffs() called");
        j(JsonUtil.toJson(this.e));
    }

    public final void i() {
        CTExecutorFactory.executors(this.g).postAsyncSafelyTask().execute("VarCache#saveDiffsAsync", new Callable() { // from class: com.clevertap.android.sdk.variables.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void c;
                c = VarCache.this.c();
                return c;
            }
        });
    }

    public final void j(@NonNull String str) {
        e("storeDataInCache() called with: data = [" + str + "]");
        try {
            StorageHelper.putString(this.f, StorageHelper.storageKeyWithSuffix(this.g, Constants.CACHED_VARIABLES_KEY), str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final synchronized void k() {
        Runnable runnable = this.d;
        if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void loadDiffs() {
        try {
            b(JsonUtil.fromJson(d()));
        } catch (Exception e) {
            f("Could not load variable diffs.\n", e);
        }
    }

    public synchronized void loadDiffsAndTriggerHandlers() {
        loadDiffs();
        k();
    }

    public synchronized void registerVariable(@NonNull Var<?> var) {
        e("registerVariable() called with: var = [" + var + "]");
        this.b.put(var.name(), var);
        Object defaultValue = var.defaultValue();
        if (defaultValue instanceof Map) {
            defaultValue = CTVariableUtils.deepCopyMap((Map) JsonUtil.uncheckedCast(defaultValue));
        }
        CTVariableUtils.updateValuesAndKinds(var.name(), var.nameComponents(), defaultValue, var.kind(), this.f2695a, this.c);
        g(var);
    }

    public synchronized void setGlobalCallbacksRunnable(Runnable runnable) {
        this.d = runnable;
    }

    public synchronized void updateDiffsAndTriggerHandlers(Map<String, Object> map) {
        b(map);
        i();
        k();
    }

    public synchronized <T> T getMergedValueFromComponentArray(Object[] objArr, Object obj) {
        for (Object obj2 : objArr) {
            obj = CTVariableUtils.traverse(obj, obj2, false);
        }
        return (T) JsonUtil.uncheckedCast(obj);
    }
}
