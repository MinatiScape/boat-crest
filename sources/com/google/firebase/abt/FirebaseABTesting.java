package com.google.firebase.abt;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public class FirebaseABTesting {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<AnalyticsConnector> f11072a;
    public final String b;
    @Nullable
    public Integer c = null;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface OriginService {
        public static final String INAPP_MESSAGING = "fiam";
        public static final String REMOTE_CONFIG = "frc";
    }

    public FirebaseABTesting(Context context, Provider<AnalyticsConnector> provider, String str) {
        this.f11072a = provider;
        this.b = str;
    }

    public static List<AbtExperimentInfo> c(List<Map<String, String>> list) throws AbtException {
        ArrayList arrayList = new ArrayList();
        for (Map<String, String> map : list) {
            arrayList.add(AbtExperimentInfo.b(map));
        }
        return arrayList;
    }

    public final void a(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        this.f11072a.get().setConditionalUserProperty(conditionalUserProperty);
    }

    public final void b(List<AbtExperimentInfo> list) {
        ArrayDeque arrayDeque = new ArrayDeque(d());
        int g = g();
        for (AbtExperimentInfo abtExperimentInfo : list) {
            while (arrayDeque.size() >= g) {
                h(((AnalyticsConnector.ConditionalUserProperty) arrayDeque.pollFirst()).name);
            }
            AnalyticsConnector.ConditionalUserProperty e = abtExperimentInfo.e(this.b);
            a(e);
            arrayDeque.offer(e);
        }
    }

    @WorkerThread
    public final List<AnalyticsConnector.ConditionalUserProperty> d() {
        return this.f11072a.get().getConditionalUserProperties(this.b, "");
    }

    public final ArrayList<AbtExperimentInfo> e(List<AbtExperimentInfo> list, Set<String> set) {
        ArrayList<AbtExperimentInfo> arrayList = new ArrayList<>();
        for (AbtExperimentInfo abtExperimentInfo : list) {
            if (!set.contains(abtExperimentInfo.c())) {
                arrayList.add(abtExperimentInfo);
            }
        }
        return arrayList;
    }

    public final ArrayList<AnalyticsConnector.ConditionalUserProperty> f(List<AnalyticsConnector.ConditionalUserProperty> list, Set<String> set) {
        ArrayList<AnalyticsConnector.ConditionalUserProperty> arrayList = new ArrayList<>();
        for (AnalyticsConnector.ConditionalUserProperty conditionalUserProperty : list) {
            if (!set.contains(conditionalUserProperty.name)) {
                arrayList.add(conditionalUserProperty);
            }
        }
        return arrayList;
    }

    @WorkerThread
    public final int g() {
        if (this.c == null) {
            this.c = Integer.valueOf(this.f11072a.get().getMaxUserProperties(this.b));
        }
        return this.c.intValue();
    }

    @WorkerThread
    public List<AbtExperimentInfo> getAllExperiments() throws AbtException {
        k();
        List<AnalyticsConnector.ConditionalUserProperty> d = d();
        ArrayList arrayList = new ArrayList();
        for (AnalyticsConnector.ConditionalUserProperty conditionalUserProperty : d) {
            arrayList.add(AbtExperimentInfo.a(conditionalUserProperty));
        }
        return arrayList;
    }

    public final void h(String str) {
        this.f11072a.get().clearConditionalUserProperty(str, null, null);
    }

    public final void i(Collection<AnalyticsConnector.ConditionalUserProperty> collection) {
        for (AnalyticsConnector.ConditionalUserProperty conditionalUserProperty : collection) {
            h(conditionalUserProperty.name);
        }
    }

    public final void j(List<AbtExperimentInfo> list) throws AbtException {
        if (list.isEmpty()) {
            removeAllExperiments();
            return;
        }
        HashSet hashSet = new HashSet();
        for (AbtExperimentInfo abtExperimentInfo : list) {
            hashSet.add(abtExperimentInfo.c());
        }
        List<AnalyticsConnector.ConditionalUserProperty> d = d();
        HashSet hashSet2 = new HashSet();
        for (AnalyticsConnector.ConditionalUserProperty conditionalUserProperty : d) {
            hashSet2.add(conditionalUserProperty.name);
        }
        i(f(d, hashSet));
        b(e(list, hashSet2));
    }

    public final void k() throws AbtException {
        if (this.f11072a.get() == null) {
            throw new AbtException("The Analytics SDK is not available. Please check that the Analytics SDK is included in your app dependencies.");
        }
    }

    @WorkerThread
    public void removeAllExperiments() throws AbtException {
        k();
        i(d());
    }

    @WorkerThread
    public void replaceAllExperiments(List<Map<String, String>> list) throws AbtException {
        k();
        if (list != null) {
            j(c(list));
            return;
        }
        throw new IllegalArgumentException("The replacementExperiments list is null.");
    }

    @WorkerThread
    public void reportActiveExperiment(AbtExperimentInfo abtExperimentInfo) throws AbtException {
        k();
        AbtExperimentInfo.g(abtExperimentInfo);
        ArrayList arrayList = new ArrayList();
        Map<String, String> f = abtExperimentInfo.f();
        f.remove("triggerEvent");
        arrayList.add(AbtExperimentInfo.b(f));
        b(arrayList);
    }

    @WorkerThread
    public void validateRunningExperiments(List<AbtExperimentInfo> list) throws AbtException {
        k();
        HashSet hashSet = new HashSet();
        for (AbtExperimentInfo abtExperimentInfo : list) {
            hashSet.add(abtExperimentInfo.c());
        }
        i(f(d(), hashSet));
    }
}
