package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseAttachState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class UseCaseAttachState {

    /* renamed from: a  reason: collision with root package name */
    public final String f717a;
    public final Map<String, b> b = new HashMap();

    /* loaded from: classes.dex */
    public interface a {
        boolean a(b bVar);
    }

    /* loaded from: classes.dex */
    public static final class b {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final SessionConfig f718a;
        public boolean b = false;
        public boolean c = false;

        public b(@NonNull SessionConfig sessionConfig) {
            this.f718a = sessionConfig;
        }

        public boolean a() {
            return this.c;
        }

        public boolean b() {
            return this.b;
        }

        @NonNull
        public SessionConfig c() {
            return this.f718a;
        }

        public void d(boolean z) {
            this.c = z;
        }

        public void e(boolean z) {
            this.b = z;
        }
    }

    public UseCaseAttachState(@NonNull String str) {
        this.f717a = str;
    }

    public static /* synthetic */ boolean e(b bVar) {
        return bVar.a() && bVar.b();
    }

    public final b c(@NonNull String str, @NonNull SessionConfig sessionConfig) {
        b bVar = this.b.get(str);
        if (bVar == null) {
            b bVar2 = new b(sessionConfig);
            this.b.put(str, bVar2);
            return bVar2;
        }
        return bVar;
    }

    public final Collection<SessionConfig> d(a aVar) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, b> entry : this.b.entrySet()) {
            if (aVar == null || aVar.a(entry.getValue())) {
                arrayList.add(entry.getValue().c());
            }
        }
        return arrayList;
    }

    @NonNull
    public SessionConfig.ValidatingBuilder getActiveAndAttachedBuilder() {
        SessionConfig.ValidatingBuilder validatingBuilder = new SessionConfig.ValidatingBuilder();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, b> entry : this.b.entrySet()) {
            b value = entry.getValue();
            if (value.a() && value.b()) {
                validatingBuilder.add(value.c());
                arrayList.add(entry.getKey());
            }
        }
        Logger.d("UseCaseAttachState", "Active and attached use case: " + arrayList + " for camera: " + this.f717a);
        return validatingBuilder;
    }

    @NonNull
    public Collection<SessionConfig> getActiveAndAttachedSessionConfigs() {
        return Collections.unmodifiableCollection(d(new a() { // from class: androidx.camera.core.impl.s
            @Override // androidx.camera.core.impl.UseCaseAttachState.a
            public final boolean a(UseCaseAttachState.b bVar) {
                boolean e;
                e = UseCaseAttachState.e(bVar);
                return e;
            }
        }));
    }

    @NonNull
    public SessionConfig.ValidatingBuilder getAttachedBuilder() {
        SessionConfig.ValidatingBuilder validatingBuilder = new SessionConfig.ValidatingBuilder();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, b> entry : this.b.entrySet()) {
            b value = entry.getValue();
            if (value.b()) {
                validatingBuilder.add(value.c());
                arrayList.add(entry.getKey());
            }
        }
        Logger.d("UseCaseAttachState", "All use case: " + arrayList + " for camera: " + this.f717a);
        return validatingBuilder;
    }

    @NonNull
    public Collection<SessionConfig> getAttachedSessionConfigs() {
        return Collections.unmodifiableCollection(d(new a() { // from class: androidx.camera.core.impl.r
            @Override // androidx.camera.core.impl.UseCaseAttachState.a
            public final boolean a(UseCaseAttachState.b bVar) {
                boolean b2;
                b2 = bVar.b();
                return b2;
            }
        }));
    }

    public boolean isUseCaseAttached(@NonNull String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str).b();
        }
        return false;
    }

    public void removeUseCase(@NonNull String str) {
        this.b.remove(str);
    }

    public void setUseCaseActive(@NonNull String str, @NonNull SessionConfig sessionConfig) {
        c(str, sessionConfig).d(true);
    }

    public void setUseCaseAttached(@NonNull String str, @NonNull SessionConfig sessionConfig) {
        c(str, sessionConfig).e(true);
    }

    public void setUseCaseDetached(@NonNull String str) {
        if (this.b.containsKey(str)) {
            b bVar = this.b.get(str);
            bVar.e(false);
            if (bVar.a()) {
                return;
            }
            this.b.remove(str);
        }
    }

    public void setUseCaseInactive(@NonNull String str) {
        if (this.b.containsKey(str)) {
            b bVar = this.b.get(str);
            bVar.d(false);
            if (bVar.b()) {
                return;
            }
            this.b.remove(str);
        }
    }

    public void updateUseCase(@NonNull String str, @NonNull SessionConfig sessionConfig) {
        if (this.b.containsKey(str)) {
            b bVar = new b(sessionConfig);
            b bVar2 = this.b.get(str);
            bVar.e(bVar2.b());
            bVar.d(bVar2.a());
            this.b.put(str, bVar);
        }
    }
}
