package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public interface b {
    SettingsData a(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject) throws JSONException;
}
