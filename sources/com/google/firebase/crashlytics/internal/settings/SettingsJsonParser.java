package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class SettingsJsonParser {

    /* renamed from: a  reason: collision with root package name */
    public final CurrentTimeProvider f11258a;

    public SettingsJsonParser(CurrentTimeProvider currentTimeProvider) {
        this.f11258a = currentTimeProvider;
    }

    public static b a(int i) {
        if (i != 3) {
            return new a();
        }
        return new c();
    }

    public SettingsData parseSettingsJson(JSONObject jSONObject) throws JSONException {
        return a(jSONObject.getInt("settings_version")).a(this.f11258a, jSONObject);
    }
}
