package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
/* loaded from: classes7.dex */
public class zzqf {
    public static final Component<zzqf> zzbja = Component.builder(zzqf.class).add(Dependency.required(FirebaseApp.class)).factory(o4.f8709a).build();

    /* renamed from: a  reason: collision with root package name */
    public final FirebaseApp f8794a;

    public zzqf(FirebaseApp firebaseApp) {
        this.f8794a = firebaseApp;
    }

    public static final /* synthetic */ zzqf a(ComponentContainer componentContainer) {
        return new zzqf((FirebaseApp) componentContainer.get(FirebaseApp.class));
    }

    public static zzqf zzog() {
        return (zzqf) FirebaseApp.getInstance().get(zzqf.class);
    }

    public final <T> T get(Class<T> cls) {
        return (T) this.f8794a.get(cls);
    }

    public final Context getApplicationContext() {
        return this.f8794a.getApplicationContext();
    }

    public final String getPersistenceKey() {
        return this.f8794a.getPersistenceKey();
    }

    public final FirebaseApp zzoh() {
        return this.f8794a;
    }
}
