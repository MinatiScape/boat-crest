package com.bumptech.glide;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class GlideExperiments {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, b> f2306a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final Map<Class<?>, b> f2307a = new HashMap();

        public a b(b bVar) {
            this.f2307a.put(bVar.getClass(), bVar);
            return this;
        }

        public GlideExperiments c() {
            return new GlideExperiments(this);
        }

        public a d(b bVar, boolean z) {
            if (z) {
                b(bVar);
            } else {
                this.f2307a.remove(bVar.getClass());
            }
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    public GlideExperiments(a aVar) {
        this.f2306a = Collections.unmodifiableMap(new HashMap(aVar.f2307a));
    }

    public boolean isEnabled(Class<? extends b> cls) {
        return this.f2306a.containsKey(cls);
    }
}
