package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes7.dex */
public final class zzjd {
    public static final Map<Field, zzjd> d = new WeakHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8781a;
    public final Field b;
    public final String c;

    public zzjd(Field field, String str) {
        this.b = field;
        this.c = str == null ? null : str.intern();
        this.f8781a = zzix.zza(field.getType());
    }

    public static Object a(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static zzjd zza(Enum<?> r5) {
        try {
            zzjd zza = zza(r5.getClass().getField(r5.name()));
            Object[] objArr = {r5};
            if (zza != null) {
                return zza;
            }
            throw new IllegalArgumentException(zzms.zzb("enum constant missing @Value or @NullValue annotation: %s", objArr));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public final Type getGenericType() {
        return this.b.getGenericType();
    }

    public final String getName() {
        return this.c;
    }

    public final boolean isPrimitive() {
        return this.f8781a;
    }

    public final void zzb(Object obj, Object obj2) {
        zza(this.b, obj, obj2);
    }

    public final Object zzh(Object obj) {
        return a(this.b, obj);
    }

    public final Field zzia() {
        return this.b;
    }

    public final boolean zzib() {
        return Modifier.isFinal(this.b.getModifiers());
    }

    public final <T extends Enum<T>> T zzic() {
        return (T) Enum.valueOf(this.b.getDeclaringClass(), this.b.getName());
    }

    public static zzjd zza(Field field) {
        String str = null;
        if (field == null) {
            return null;
        }
        Map<Field, zzjd> map = d;
        synchronized (map) {
            zzjd zzjdVar = map.get(field);
            boolean isEnumConstant = field.isEnumConstant();
            if (zzjdVar == null && (isEnumConstant || !Modifier.isStatic(field.getModifiers()))) {
                if (isEnumConstant) {
                    zzjx zzjxVar = (zzjx) field.getAnnotation(zzjx.class);
                    if (zzjxVar != null) {
                        str = zzjxVar.value();
                    } else if (((zzjn) field.getAnnotation(zzjn.class)) == null) {
                        return null;
                    }
                } else {
                    zzjg zzjgVar = (zzjg) field.getAnnotation(zzjg.class);
                    if (zzjgVar == null) {
                        return null;
                    }
                    str = zzjgVar.value();
                    field.setAccessible(true);
                }
                if ("##default".equals(str)) {
                    str = field.getName();
                }
                zzjdVar = new zzjd(field, str);
                map.put(field, zzjdVar);
            }
            return zzjdVar;
        }
    }

    public static void zza(Field field, Object obj, Object obj2) {
        if (Modifier.isFinal(field.getModifiers())) {
            Object a2 = a(field, obj);
            if (obj2 == null) {
                if (a2 == null) {
                    return;
                }
            } else if (obj2.equals(a2)) {
                return;
            }
            String valueOf = String.valueOf(a2);
            String valueOf2 = String.valueOf(obj2);
            String name = field.getName();
            String name2 = obj.getClass().getName();
            StringBuilder sb = new StringBuilder(valueOf.length() + 48 + valueOf2.length() + String.valueOf(name).length() + name2.length());
            sb.append("expected final value <");
            sb.append(valueOf);
            sb.append("> but was <");
            sb.append(valueOf2);
            sb.append("> on ");
            sb.append(name);
            sb.append(" field in ");
            sb.append(name2);
            throw new IllegalArgumentException(sb.toString());
        }
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (SecurityException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
