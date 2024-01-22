package com.clevertap.android.sdk.variables;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CTVariables {

    /* renamed from: a  reason: collision with root package name */
    public boolean f2692a = false;
    public final List<VariablesChangedCallback> b = new ArrayList();
    public final List<VariablesChangedCallback> c = new ArrayList();
    public final Runnable d;
    public final VarCache e;

    public CTVariables(VarCache varCache) {
        Runnable runnable = new Runnable() { // from class: com.clevertap.android.sdk.variables.a
            @Override // java.lang.Runnable
            public final void run() {
                CTVariables.this.d();
            }
        };
        this.d = runnable;
        this.e = varCache;
        varCache.setGlobalCallbacksRunnable(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        synchronized (this.b) {
            for (VariablesChangedCallback variablesChangedCallback : this.b) {
                Utils.runOnUiThread(variablesChangedCallback);
            }
        }
        synchronized (this.c) {
            for (VariablesChangedCallback variablesChangedCallback2 : this.c) {
                Utils.runOnUiThread(variablesChangedCallback2);
            }
            this.c.clear();
        }
    }

    public static void e(String str) {
        Logger.d("variables", str);
    }

    public static boolean isDevelopmentMode(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public void addOneTimeVariablesChangedCallback(@NonNull VariablesChangedCallback variablesChangedCallback) {
        if (this.f2692a) {
            variablesChangedCallback.variablesChanged();
            return;
        }
        synchronized (this.c) {
            this.c.add(variablesChangedCallback);
        }
    }

    public void addVariablesChangedCallback(@NonNull VariablesChangedCallback variablesChangedCallback) {
        synchronized (this.b) {
            this.b.add(variablesChangedCallback);
        }
        if (hasVarsRequestCompleted().booleanValue()) {
            variablesChangedCallback.variablesChanged();
        }
    }

    public VarCache b() {
        return this.e;
    }

    public final void c(@NonNull JSONObject jSONObject, @Nullable FetchVariablesCallback fetchVariablesCallback) {
        setHasVarsRequestCompleted(true);
        this.e.updateDiffsAndTriggerHandlers(CTVariableUtils.convertFlatMapToNestedMaps(JsonUtil.mapFromJson(jSONObject)));
        if (fetchVariablesCallback != null) {
            fetchVariablesCallback.onVariablesFetched(true);
        }
    }

    public void clearUserContent() {
        e("Clear user content in CTVariables");
        setHasVarsRequestCompleted(false);
        this.e.clearUserContent();
    }

    public void handleVariableResponse(@Nullable JSONObject jSONObject, @Nullable FetchVariablesCallback fetchVariablesCallback) {
        e("handleVariableResponse() called with: response = [" + jSONObject + "]");
        if (jSONObject == null) {
            handleVariableResponseError(fetchVariablesCallback);
        } else {
            c(jSONObject, fetchVariablesCallback);
        }
    }

    public void handleVariableResponseError(@Nullable FetchVariablesCallback fetchVariablesCallback) {
        if (!hasVarsRequestCompleted().booleanValue()) {
            setHasVarsRequestCompleted(true);
            this.e.loadDiffsAndTriggerHandlers();
        }
        if (fetchVariablesCallback != null) {
            fetchVariablesCallback.onVariablesFetched(false);
        }
    }

    public Boolean hasVarsRequestCompleted() {
        return Boolean.valueOf(this.f2692a);
    }

    public void init() {
        e("init() called");
        this.e.loadDiffs();
    }

    public void removeAllOneTimeVariablesChangedCallbacks() {
        synchronized (this.c) {
            this.c.clear();
        }
    }

    public void removeAllVariablesChangedCallbacks() {
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public void removeOneTimeVariablesChangedHandler(@NonNull VariablesChangedCallback variablesChangedCallback) {
        synchronized (this.c) {
            this.c.remove(variablesChangedCallback);
        }
    }

    public void removeVariablesChangedCallback(@NonNull VariablesChangedCallback variablesChangedCallback) {
        synchronized (this.b) {
            this.b.remove(variablesChangedCallback);
        }
    }

    public void setHasVarsRequestCompleted(boolean z) {
        this.f2692a = z;
    }
}
