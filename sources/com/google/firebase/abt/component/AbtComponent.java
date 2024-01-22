package com.google.firebase.abt.component;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class AbtComponent {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, FirebaseABTesting> f11073a = new HashMap();
    public final Context b;
    public final Provider<AnalyticsConnector> c;

    @VisibleForTesting(otherwise = 3)
    public AbtComponent(Context context, Provider<AnalyticsConnector> provider) {
        this.b = context;
        this.c = provider;
    }

    @VisibleForTesting
    public FirebaseABTesting createAbtInstance(String str) {
        return new FirebaseABTesting(this.b, this.c, str);
    }

    public synchronized FirebaseABTesting get(String str) {
        if (!this.f11073a.containsKey(str)) {
            this.f11073a.put(str, createAbtInstance(str));
        }
        return this.f11073a.get(str);
    }
}
