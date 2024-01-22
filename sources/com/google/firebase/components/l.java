package com.google.firebase.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes10.dex */
public class l {

    /* loaded from: classes10.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final Component<?> f11105a;
        public final Set<b> b = new HashSet();
        public final Set<b> c = new HashSet();

        public b(Component<?> component) {
            this.f11105a = component;
        }

        public void a(b bVar) {
            this.b.add(bVar);
        }

        public void b(b bVar) {
            this.c.add(bVar);
        }

        public Component<?> c() {
            return this.f11105a;
        }

        public Set<b> d() {
            return this.b;
        }

        public boolean e() {
            return this.b.isEmpty();
        }

        public boolean f() {
            return this.c.isEmpty();
        }

        public void g(b bVar) {
            this.c.remove(bVar);
        }
    }

    /* loaded from: classes10.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final Class<?> f11106a;
        public final boolean b;

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                return cVar.f11106a.equals(this.f11106a) && cVar.b == this.b;
            }
            return false;
        }

        public int hashCode() {
            return ((this.f11106a.hashCode() ^ 1000003) * 1000003) ^ Boolean.valueOf(this.b).hashCode();
        }

        public c(Class<?> cls, boolean z) {
            this.f11106a = cls;
            this.b = z;
        }
    }

    public static void a(List<Component<?>> list) {
        Set<b> c2 = c(list);
        Set<b> b2 = b(c2);
        int i = 0;
        while (!b2.isEmpty()) {
            b next = b2.iterator().next();
            b2.remove(next);
            i++;
            for (b bVar : next.d()) {
                bVar.g(next);
                if (bVar.f()) {
                    b2.add(bVar);
                }
            }
        }
        if (i == list.size()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (b bVar2 : c2) {
            if (!bVar2.f() && !bVar2.e()) {
                arrayList.add(bVar2.c());
            }
        }
        throw new DependencyCycleException(arrayList);
    }

    public static Set<b> b(Set<b> set) {
        HashSet hashSet = new HashSet();
        for (b bVar : set) {
            if (bVar.f()) {
                hashSet.add(bVar);
            }
        }
        return hashSet;
    }

    public static Set<b> c(List<Component<?>> list) {
        Set<b> set;
        HashMap hashMap = new HashMap(list.size());
        for (Component<?> component : list) {
            b bVar = new b(component);
            for (Class<? super Object> cls : component.getProvidedInterfaces()) {
                c cVar = new c(cls, !component.isValue());
                if (!hashMap.containsKey(cVar)) {
                    hashMap.put(cVar, new HashSet());
                }
                Set set2 = (Set) hashMap.get(cVar);
                if (!set2.isEmpty() && !cVar.b) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", cls));
                }
                set2.add(bVar);
            }
        }
        for (Set<b> set3 : hashMap.values()) {
            for (b bVar2 : set3) {
                for (Dependency dependency : bVar2.c().getDependencies()) {
                    if (dependency.isDirectInjection() && (set = (Set) hashMap.get(new c(dependency.getInterface(), dependency.isSet()))) != null) {
                        for (b bVar3 : set) {
                            bVar2.a(bVar3);
                            bVar3.b(bVar2);
                        }
                    }
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Set set4 : hashMap.values()) {
            hashSet.addAll(set4);
        }
        return hashSet;
    }
}
