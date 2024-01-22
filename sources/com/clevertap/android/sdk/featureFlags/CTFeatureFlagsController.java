package com.clevertap.android.sdk.featureFlags;

import android.text.TextUtils;
import com.clevertap.android.sdk.BaseAnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import com.clevertap.android.sdk.utils.FileUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes2.dex */
public class CTFeatureFlagsController {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapInstanceConfig f2611a;
    public String b;
    public final BaseAnalyticsManager d;
    public final BaseCallbackManager e;
    public FileUtils f;
    public boolean c = false;
    public final Map<String, Boolean> g = Collections.synchronizedMap(new HashMap());

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            try {
                CTFeatureFlagsController.this.d.fetchFeatureFlags();
                return null;
            } catch (Exception e) {
                CTFeatureFlagsController.this.h().verbose(CTFeatureFlagsController.this.i(), e.getLocalizedMessage());
                return null;
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b implements OnSuccessListener<Boolean> {
        public b() {
        }

        @Override // com.clevertap.android.sdk.task.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Boolean bool) {
            CTFeatureFlagsController.this.c = bool.booleanValue();
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Boolean> {
        public c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            Boolean bool;
            synchronized (this) {
                CTFeatureFlagsController.this.h().verbose(CTFeatureFlagsController.this.i(), "Feature flags init is called");
                String g = CTFeatureFlagsController.this.g();
                try {
                    CTFeatureFlagsController.this.g.clear();
                    String readFromFile = CTFeatureFlagsController.this.f.readFromFile(g);
                    if (TextUtils.isEmpty(readFromFile)) {
                        Logger h = CTFeatureFlagsController.this.h();
                        String i = CTFeatureFlagsController.this.i();
                        h.verbose(i, "Feature flags file is empty-" + g);
                    } else {
                        JSONArray jSONArray = new JSONObject(readFromFile).getJSONArray(Constants.KEY_KV);
                        if (jSONArray != null && jSONArray.length() > 0) {
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                                if (jSONObject != null) {
                                    String string = jSONObject.getString("n");
                                    String string2 = jSONObject.getString(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE);
                                    if (!TextUtils.isEmpty(string)) {
                                        CTFeatureFlagsController.this.g.put(string, Boolean.valueOf(Boolean.parseBoolean(string2)));
                                    }
                                }
                            }
                        }
                        Logger h2 = CTFeatureFlagsController.this.h();
                        String i3 = CTFeatureFlagsController.this.i();
                        h2.verbose(i3, "Feature flags initialized from file " + g + " with configs  " + CTFeatureFlagsController.this.g);
                    }
                    bool = Boolean.TRUE;
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger h3 = CTFeatureFlagsController.this.h();
                    String i4 = CTFeatureFlagsController.this.i();
                    h3.verbose(i4, "UnArchiveData failed file- " + g + HexStringBuilder.DEFAULT_SEPARATOR + e.getLocalizedMessage());
                    return Boolean.FALSE;
                }
            }
            return bool;
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Callable<Void> {
        public d() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            try {
                if (CTFeatureFlagsController.this.e.getFeatureFlagListener() != null) {
                    CTFeatureFlagsController.this.e.getFeatureFlagListener().featureFlagsUpdated();
                    return null;
                }
                return null;
            } catch (Exception e) {
                CTFeatureFlagsController.this.h().verbose(CTFeatureFlagsController.this.i(), e.getLocalizedMessage());
                return null;
            }
        }
    }

    @Deprecated
    public CTFeatureFlagsController(String str, CleverTapInstanceConfig cleverTapInstanceConfig, BaseCallbackManager baseCallbackManager, BaseAnalyticsManager baseAnalyticsManager, FileUtils fileUtils) {
        this.b = str;
        this.f2611a = cleverTapInstanceConfig;
        this.e = baseCallbackManager;
        this.d = baseAnalyticsManager;
        this.f = fileUtils;
        j();
    }

    public final synchronized void d(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.f.writeJsonToFile(e(), f(), jSONObject);
                Logger h = h();
                String i = i();
                h.verbose(i, "Feature flags saved into file-[" + g() + "]" + this.g);
            } catch (Exception e) {
                e.printStackTrace();
                Logger h2 = h();
                String i2 = i();
                h2.verbose(i2, "ArchiveData failed - " + e.getLocalizedMessage());
            }
        }
    }

    public String e() {
        return "Feature_Flag_" + this.f2611a.getAccountId() + "_" + this.b;
    }

    public String f() {
        return CTFeatureFlagConstants.CACHED_FILE_NAME;
    }

    @Deprecated
    public void fetchFeatureFlags() {
        CTExecutorFactory.executors(this.f2611a).mainTask().execute("fetchFeatureFlags", new a());
    }

    public String g() {
        return e() + MqttTopic.TOPIC_LEVEL_SEPARATOR + f();
    }

    @Deprecated
    public Boolean get(String str, boolean z) {
        if (!this.c) {
            Logger h = h();
            String i = i();
            h.verbose(i, "Controller not initialized, returning default value - " + z);
            return Boolean.valueOf(z);
        }
        Logger h2 = h();
        String i2 = i();
        h2.verbose(i2, "Getting feature flag with key - " + str + " and default value - " + z);
        Boolean bool = this.g.get(str);
        if (bool != null) {
            return bool;
        }
        Logger h3 = h();
        String i3 = i();
        h3.verbose(i3, "Feature flag not found, returning default value - " + z);
        return Boolean.valueOf(z);
    }

    @Deprecated
    public String getGuid() {
        return this.b;
    }

    public final Logger h() {
        return this.f2611a.getLogger();
    }

    public final String i() {
        return this.f2611a.getAccountId() + "[Feature Flag]";
    }

    @Deprecated
    public boolean isInitialized() {
        return this.c;
    }

    public void j() {
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        Task ioTask = CTExecutorFactory.executors(this.f2611a).ioTask();
        ioTask.addOnSuccessListener(new b());
        ioTask.execute("initFeatureFlags", new c());
    }

    public final void k() {
        if (this.e.getFeatureFlagListener() != null) {
            CTExecutorFactory.executors(this.f2611a).mainTask().execute("notifyFeatureFlagUpdate", new d());
        }
    }

    @Deprecated
    public void resetWithGuid(String str) {
        this.b = str;
        j();
    }

    @Deprecated
    public void setGuidAndInit(String str) {
        if (this.c) {
            return;
        }
        this.b = str;
        j();
    }

    @Deprecated
    public synchronized void updateFeatureFlags(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.KEY_KV);
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                this.g.put(jSONObject2.getString("n"), Boolean.valueOf(jSONObject2.getBoolean(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE)));
            } catch (JSONException e) {
                Logger h = h();
                String i2 = i();
                h.verbose(i2, "Error parsing Feature Flag array " + e.getLocalizedMessage());
            }
        }
        Logger h2 = h();
        String i3 = i();
        h2.verbose(i3, "Updating feature flags..." + this.g);
        d(jSONObject);
        k();
    }
}
