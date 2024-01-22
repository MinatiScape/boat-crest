package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes7.dex */
public final class zziv {
    public static final ConcurrentMap<Class<?>, zziv> e = new ConcurrentHashMap();
    public static final ConcurrentMap<Class<?>, zziv> f = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final Class<?> f8779a;
    public final boolean b;
    public final IdentityHashMap<String, zzjd> c = new IdentityHashMap<>();
    public final List<String> d;

    public zziv(Class<?> cls, boolean z) {
        Field[] declaredFields;
        List<String> unmodifiableList;
        this.f8779a = cls;
        this.b = z;
        boolean z2 = (z && cls.isEnum()) ? false : true;
        String valueOf = String.valueOf(cls);
        StringBuilder sb = new StringBuilder(valueOf.length() + 31);
        sb.append("cannot ignore case on an enum: ");
        sb.append(valueOf);
        zzml.checkArgument(z2, sb.toString());
        TreeSet treeSet = new TreeSet(new a1(this));
        for (Field field : cls.getDeclaredFields()) {
            zzjd zza = zzjd.zza(field);
            if (zza != null) {
                String name = zza.getName();
                name = z ? name.toLowerCase(Locale.US).intern() : name;
                zzjd zzjdVar = this.c.get(name);
                boolean z3 = zzjdVar == null;
                Object[] objArr = new Object[4];
                objArr[0] = z ? "case-insensitive " : "";
                objArr[1] = name;
                objArr[2] = field;
                objArr[3] = zzjdVar == null ? null : zzjdVar.zzia();
                if (z3) {
                    this.c.put(name, zza);
                    treeSet.add(name);
                } else {
                    throw new IllegalArgumentException(zzms.zzb("two fields have the same %sname <%s>: %s and %s", objArr));
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            zziv zza2 = zza(superclass, z);
            treeSet.addAll(zza2.d);
            for (Map.Entry<String, zzjd> entry : zza2.c.entrySet()) {
                String key = entry.getKey();
                if (!this.c.containsKey(key)) {
                    this.c.put(key, entry.getValue());
                }
            }
        }
        if (treeSet.isEmpty()) {
            unmodifiableList = Collections.emptyList();
        } else {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(treeSet));
        }
        this.d = unmodifiableList;
    }

    public static zziv zza(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        ConcurrentMap<Class<?>, zziv> concurrentMap = z ? f : e;
        zziv zzivVar = concurrentMap.get(cls);
        if (zzivVar == null) {
            zziv zzivVar2 = new zziv(cls, z);
            zziv putIfAbsent = concurrentMap.putIfAbsent(cls, zzivVar2);
            return putIfAbsent == null ? zzivVar2 : putIfAbsent;
        }
        return zzivVar;
    }

    public static zziv zzc(Class<?> cls) {
        return zza(cls, false);
    }

    public final boolean isEnum() {
        return this.f8779a.isEnum();
    }

    public final zzjd zzao(String str) {
        if (str != null) {
            if (this.b) {
                str = str.toLowerCase(Locale.US);
            }
            str = str.intern();
        }
        return this.c.get(str);
    }

    public final boolean zzhy() {
        return this.b;
    }

    public final Collection<zzjd> zzhz() {
        return Collections.unmodifiableCollection(this.c.values());
    }
}
