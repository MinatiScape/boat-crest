package com.squareup.otto;

import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public interface d {

    /* renamed from: a  reason: collision with root package name */
    public static final d f13713a = new a();

    /* loaded from: classes12.dex */
    public static class a implements d {
        @Override // com.squareup.otto.d
        public Map<Class<?>, Set<b>> a(Object obj) {
            return com.squareup.otto.a.b(obj);
        }

        @Override // com.squareup.otto.d
        public Map<Class<?>, c> b(Object obj) {
            return com.squareup.otto.a.a(obj);
        }
    }

    Map<Class<?>, Set<b>> a(Object obj);

    Map<Class<?>, c> b(Object obj);
}
