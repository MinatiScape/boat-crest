package com.google.firebase.remoteconfig.internal;

import java.util.Date;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class ConfigContainer {
    public static final Date f = new Date(0);

    /* renamed from: a  reason: collision with root package name */
    public JSONObject f11491a;
    public JSONObject b;
    public Date c;
    public JSONArray d;
    public JSONObject e;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public JSONObject f11492a;
        public Date b;
        public JSONArray c;
        public JSONObject d;

        public ConfigContainer build() throws JSONException {
            return new ConfigContainer(this.f11492a, this.b, this.c, this.d);
        }

        public Builder replaceConfigsWith(Map<String, String> map) {
            this.f11492a = new JSONObject((Map<?, ?>) map);
            return this;
        }

        public Builder withAbtExperiments(JSONArray jSONArray) {
            try {
                this.c = new JSONArray(jSONArray.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder withFetchTime(Date date) {
            this.b = date;
            return this;
        }

        public Builder withPersonalizationMetadata(JSONObject jSONObject) {
            try {
                this.d = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder() {
            this.f11492a = new JSONObject();
            this.b = ConfigContainer.f;
            this.c = new JSONArray();
            this.d = new JSONObject();
        }

        public Builder replaceConfigsWith(JSONObject jSONObject) {
            try {
                this.f11492a = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder(ConfigContainer configContainer) {
            this.f11492a = configContainer.getConfigs();
            this.b = configContainer.getFetchTime();
            this.c = configContainer.getAbtExperiments();
            this.d = configContainer.getPersonalizationMetadata();
        }
    }

    public static ConfigContainer b(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("personalization_metadata_key");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        return new ConfigContainer(jSONObject.getJSONObject("configs_key"), new Date(jSONObject.getLong("fetch_time_key")), jSONObject.getJSONArray("abt_experiments_key"), optJSONObject);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ConfigContainer) {
            return this.f11491a.toString().equals(((ConfigContainer) obj).toString());
        }
        return false;
    }

    public JSONArray getAbtExperiments() {
        return this.d;
    }

    public JSONObject getConfigs() {
        return this.b;
    }

    public Date getFetchTime() {
        return this.c;
    }

    public JSONObject getPersonalizationMetadata() {
        return this.e;
    }

    public int hashCode() {
        return this.f11491a.hashCode();
    }

    public String toString() {
        return this.f11491a.toString();
    }

    public ConfigContainer(JSONObject jSONObject, Date date, JSONArray jSONArray, JSONObject jSONObject2) throws JSONException {
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("configs_key", jSONObject);
        jSONObject3.put("fetch_time_key", date.getTime());
        jSONObject3.put("abt_experiments_key", jSONArray);
        jSONObject3.put("personalization_metadata_key", jSONObject2);
        this.b = jSONObject;
        this.c = date;
        this.d = jSONArray;
        this.e = jSONObject2;
        this.f11491a = jSONObject3;
    }

    public static Builder newBuilder(ConfigContainer configContainer) {
        return new Builder(configContainer);
    }
}
