package com.google.firebase.abt;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.coveiot.utils.utility.UtilConstants;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
public class AbtExperimentInfo {
    public static final String[] g = {RemoteConfigConstants.ExperimentDescriptionFieldKey.EXPERIMENT_ID, "experimentStartTime", "timeToLiveMillis", "triggerTimeoutMillis", RemoteConfigConstants.ExperimentDescriptionFieldKey.VARIANT_ID};
    @VisibleForTesting
    public static final DateFormat h = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT, Locale.US);

    /* renamed from: a  reason: collision with root package name */
    public final String f11071a;
    public final String b;
    public final String c;
    public final Date d;
    public final long e;
    public final long f;

    public AbtExperimentInfo(String str, String str2, String str3, Date date, long j, long j2) {
        this.f11071a = str;
        this.b = str2;
        this.c = str3;
        this.d = date;
        this.e = j;
        this.f = j2;
    }

    public static AbtExperimentInfo a(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        String str = conditionalUserProperty.triggerEventName;
        if (str == null) {
            str = "";
        }
        return new AbtExperimentInfo(conditionalUserProperty.name, String.valueOf(conditionalUserProperty.value), str, new Date(conditionalUserProperty.creationTimestamp), conditionalUserProperty.triggerTimeout, conditionalUserProperty.timeToLive);
    }

    public static AbtExperimentInfo b(Map<String, String> map) throws AbtException {
        h(map);
        try {
            return new AbtExperimentInfo(map.get(RemoteConfigConstants.ExperimentDescriptionFieldKey.EXPERIMENT_ID), map.get(RemoteConfigConstants.ExperimentDescriptionFieldKey.VARIANT_ID), map.containsKey("triggerEvent") ? map.get("triggerEvent") : "", h.parse(map.get("experimentStartTime")), Long.parseLong(map.get("triggerTimeoutMillis")), Long.parseLong(map.get("timeToLiveMillis")));
        } catch (NumberFormatException e) {
            throw new AbtException("Could not process experiment: one of the durations could not be converted into a long.", e);
        } catch (ParseException e2) {
            throw new AbtException("Could not process experiment: parsing experiment start time failed.", e2);
        }
    }

    public static void g(AbtExperimentInfo abtExperimentInfo) throws AbtException {
        h(abtExperimentInfo.f());
    }

    public static void h(Map<String, String> map) throws AbtException {
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        for (String str : g) {
            if (!map.containsKey(str)) {
                arrayList.add(str);
            }
        }
        if (!arrayList.isEmpty()) {
            throw new AbtException(String.format("The following keys are missing from the experiment info map: %s", arrayList));
        }
    }

    public String c() {
        return this.f11071a;
    }

    public long d() {
        return this.d.getTime();
    }

    public AnalyticsConnector.ConditionalUserProperty e(String str) {
        AnalyticsConnector.ConditionalUserProperty conditionalUserProperty = new AnalyticsConnector.ConditionalUserProperty();
        conditionalUserProperty.origin = str;
        conditionalUserProperty.creationTimestamp = d();
        conditionalUserProperty.name = this.f11071a;
        conditionalUserProperty.value = this.b;
        conditionalUserProperty.triggerEventName = TextUtils.isEmpty(this.c) ? null : this.c;
        conditionalUserProperty.triggerTimeout = this.e;
        conditionalUserProperty.timeToLive = this.f;
        return conditionalUserProperty;
    }

    @VisibleForTesting
    public Map<String, String> f() {
        HashMap hashMap = new HashMap();
        hashMap.put(RemoteConfigConstants.ExperimentDescriptionFieldKey.EXPERIMENT_ID, this.f11071a);
        hashMap.put(RemoteConfigConstants.ExperimentDescriptionFieldKey.VARIANT_ID, this.b);
        hashMap.put("triggerEvent", this.c);
        hashMap.put("experimentStartTime", h.format(this.d));
        hashMap.put("triggerTimeoutMillis", Long.toString(this.e));
        hashMap.put("timeToLiveMillis", Long.toString(this.f));
        return hashMap;
    }
}
