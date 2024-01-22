package com.touchgui.sdk.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.touchgui.sdk.TGLogger;
import java.util.HashSet;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public abstract class m2 {

    /* renamed from: a  reason: collision with root package name */
    public final HashSet f13793a = new HashSet();
    public final JSONObject b = new JSONObject();

    public final JSONArray a(String str) {
        TGLogger.d(str);
        JSONArray jSONArray = new JSONArray();
        Matcher matcher = Pattern.compile("\"(.+?)\":(\\{.+?\\}),?").matcher(str);
        while (matcher.find()) {
            String group = matcher.group(2);
            Objects.requireNonNull(group);
            JSONObject jSONObject = new JSONObject(group);
            String group2 = matcher.group(1);
            jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, group2);
            this.f13793a.add(group2);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
