package com.google.android.gms.internal.firebase_ml;

import com.jstyle.blesdk1860.constant.BleConst;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public final class zzix {

    /* renamed from: a  reason: collision with root package name */
    public static final Boolean f8780a;
    public static final String b;
    public static final Character c;
    public static final Byte d;
    public static final Short e;
    public static final Integer f;
    public static final Float g;
    public static final Long h;
    public static final Double i;
    public static final BigInteger j;
    public static final BigDecimal k;
    public static final zzje l;
    public static final ConcurrentHashMap<Class<?>, Object> m;

    static {
        Boolean bool = new Boolean(true);
        f8780a = bool;
        String str = new String();
        b = str;
        Character ch = new Character((char) 0);
        c = ch;
        Byte b2 = new Byte((byte) 0);
        d = b2;
        Short sh = new Short((short) 0);
        e = sh;
        Integer num = new Integer(0);
        f = num;
        Float f2 = new Float(0.0f);
        g = f2;
        Long l2 = new Long(0L);
        h = l2;
        Double d2 = new Double(0.0d);
        i = d2;
        BigInteger bigInteger = new BigInteger(BleConst.GetDeviceTime);
        j = bigInteger;
        BigDecimal bigDecimal = new BigDecimal(BleConst.GetDeviceTime);
        k = bigDecimal;
        zzje zzjeVar = new zzje(0L);
        l = zzjeVar;
        ConcurrentHashMap<Class<?>, Object> concurrentHashMap = new ConcurrentHashMap<>();
        m = concurrentHashMap;
        concurrentHashMap.put(Boolean.class, bool);
        concurrentHashMap.put(String.class, str);
        concurrentHashMap.put(Character.class, ch);
        concurrentHashMap.put(Byte.class, b2);
        concurrentHashMap.put(Short.class, sh);
        concurrentHashMap.put(Integer.class, num);
        concurrentHashMap.put(Float.class, f2);
        concurrentHashMap.put(Long.class, l2);
        concurrentHashMap.put(Double.class, d2);
        concurrentHashMap.put(BigInteger.class, bigInteger);
        concurrentHashMap.put(BigDecimal.class, bigDecimal);
        concurrentHashMap.put(zzje.class, zzjeVar);
    }

    public static <T> T clone(T t) {
        T t2;
        if (t == null || zza(t.getClass())) {
            return t;
        }
        if (t instanceof zzjf) {
            return (T) ((zzjf) ((zzjf) t).clone());
        }
        Class<?> cls = t.getClass();
        if (cls.isArray()) {
            t2 = (T) Array.newInstance(cls.getComponentType(), Array.getLength(t));
        } else if (t instanceof zziq) {
            t2 = (T) ((zziq) ((zziq) t).clone());
        } else if ("java.util.Arrays$ArrayList".equals(cls.getName())) {
            Object[] array = ((List) t).toArray();
            zza(array, array);
            return (T) Arrays.asList(array);
        } else {
            t2 = (T) zzjs.zzf((Class<Object>) cls);
        }
        zza(t, t2);
        return t2;
    }

    public static boolean isNull(Object obj) {
        return obj != null && obj == m.get(obj.getClass());
    }

    public static void zza(Object obj, Object obj2) {
        Class<?> cls = obj.getClass();
        int i2 = 0;
        zzml.checkArgument(cls == obj2.getClass());
        if (cls.isArray()) {
            zzml.checkArgument(Array.getLength(obj) == Array.getLength(obj2));
            for (Object obj3 : zzjs.zzi(obj)) {
                Array.set(obj2, i2, clone(obj3));
                i2++;
            }
        } else if (Collection.class.isAssignableFrom(cls)) {
            Collection<Object> collection = (Collection) obj;
            if (ArrayList.class.isAssignableFrom(cls)) {
                ((ArrayList) obj2).ensureCapacity(collection.size());
            }
            Collection collection2 = (Collection) obj2;
            for (Object obj4 : collection) {
                collection2.add(clone(obj4));
            }
        } else {
            boolean isAssignableFrom = zzjf.class.isAssignableFrom(cls);
            if (!isAssignableFrom && Map.class.isAssignableFrom(cls)) {
                if (zziq.class.isAssignableFrom(cls)) {
                    zziq zziqVar = (zziq) obj2;
                    zziq zziqVar2 = (zziq) obj;
                    int size = zziqVar2.size();
                    while (i2 < size) {
                        zziqVar.set(i2, clone(zziqVar2.zzaj(i2)));
                        i2++;
                    }
                    return;
                }
                Map map = (Map) obj2;
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    map.put((String) entry.getKey(), clone(entry.getValue()));
                }
                return;
            }
            zziv zzc = isAssignableFrom ? ((zzjf) obj).i : zziv.zzc(cls);
            for (String str : zzc.d) {
                zzjd zzao = zzc.zzao(str);
                if (!zzao.zzib() && (!isAssignableFrom || !zzao.isPrimitive())) {
                    Object zzh = zzao.zzh(obj);
                    if (zzh != null) {
                        zzao.zzb(obj2, clone(zzh));
                    }
                }
            }
        }
    }

    public static Collection<Object> zzb(Type type) {
        if (type instanceof WildcardType) {
            type = zzjs.zza((WildcardType) type);
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        Class cls = type instanceof Class ? (Class) type : null;
        if (type == null || (type instanceof GenericArrayType) || (cls != null && (cls.isArray() || cls.isAssignableFrom(ArrayList.class)))) {
            return new ArrayList();
        }
        if (cls != null) {
            if (cls.isAssignableFrom(HashSet.class)) {
                return new HashSet();
            }
            if (cls.isAssignableFrom(TreeSet.class)) {
                return new TreeSet();
            }
            return (Collection) zzjs.zzf((Class<Object>) cls);
        }
        String valueOf = String.valueOf(type);
        StringBuilder sb = new StringBuilder(valueOf.length() + 39);
        sb.append("unable to create new instance of type: ");
        sb.append(valueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T> T zzd(Class<?> cls) {
        T t;
        T t2 = (T) m.get(cls);
        if (t2 == null) {
            int i2 = 0;
            if (cls.isArray()) {
                Class<?> cls2 = cls;
                do {
                    cls2 = cls2.getComponentType();
                    i2++;
                } while (cls2.isArray());
                t = (T) Array.newInstance(cls2, new int[i2]);
            } else if (cls.isEnum()) {
                zzjd zzao = zziv.zzc(cls).zzao(null);
                Object[] objArr = {cls};
                if (zzao != null) {
                    t = (T) zzao.zzic();
                } else {
                    throw new NullPointerException(zzms.zzb("enum missing constant with @NullValue annotation: %s", objArr));
                }
            } else {
                t = (T) zzjs.zzf((Class<Object>) cls);
            }
            T t3 = (T) m.putIfAbsent(cls, t);
            return t3 == null ? t : t3;
        }
        return t2;
    }

    public static Map<String, Object> zze(Class<?> cls) {
        if (cls != null && !cls.isAssignableFrom(zziq.class)) {
            if (cls.isAssignableFrom(TreeMap.class)) {
                return new TreeMap();
            }
            return (Map) zzjs.zzf((Class<Object>) cls);
        }
        return new zziq();
    }

    public static Map<String, Object> zzf(Object obj) {
        if (obj != null && !isNull(obj)) {
            if (obj instanceof Map) {
                return (Map) obj;
            }
            return new c1(obj, false);
        }
        return Collections.emptyMap();
    }

    public static boolean zzg(Object obj) {
        return obj == null || zza(obj.getClass());
    }

    public static boolean zza(Type type) {
        if (type instanceof WildcardType) {
            type = zzjs.zza((WildcardType) type);
        }
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isPrimitive() || cls == Character.class || cls == String.class || cls == Integer.class || cls == Long.class || cls == Short.class || cls == Byte.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == zzje.class || cls == Boolean.class;
        }
        return false;
    }

    public static Object zza(Type type, String str) {
        Class cls = type instanceof Class ? (Class) type : null;
        if (type == null || cls != null) {
            if (cls == Void.class) {
                return null;
            }
            if (str == null || cls == null || cls.isAssignableFrom(String.class)) {
                return str;
            }
            if (cls != Character.class && cls != Character.TYPE) {
                if (cls != Boolean.class && cls != Boolean.TYPE) {
                    if (cls != Byte.class && cls != Byte.TYPE) {
                        if (cls != Short.class && cls != Short.TYPE) {
                            if (cls != Integer.class && cls != Integer.TYPE) {
                                if (cls != Long.class && cls != Long.TYPE) {
                                    if (cls != Float.class && cls != Float.TYPE) {
                                        if (cls != Double.class && cls != Double.TYPE) {
                                            if (cls == zzje.class) {
                                                return zzje.zzap(str);
                                            }
                                            if (cls == BigInteger.class) {
                                                return new BigInteger(str);
                                            }
                                            if (cls == BigDecimal.class) {
                                                return new BigDecimal(str);
                                            }
                                            if (cls.isEnum()) {
                                                if (zziv.zzc(cls).d.contains(str)) {
                                                    return zziv.zzc(cls).zzao(str).zzic();
                                                }
                                                throw new IllegalArgumentException(String.format("given enum name %s not part of enumeration", str));
                                            }
                                        } else {
                                            return Double.valueOf(str);
                                        }
                                    } else {
                                        return Float.valueOf(str);
                                    }
                                } else {
                                    return Long.valueOf(str);
                                }
                            } else {
                                return Integer.valueOf(str);
                            }
                        } else {
                            return Short.valueOf(str);
                        }
                    } else {
                        return Byte.valueOf(str);
                    }
                } else {
                    return Boolean.valueOf(str);
                }
            } else if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            } else {
                String valueOf = String.valueOf(cls);
                StringBuilder sb = new StringBuilder(valueOf.length() + 37);
                sb.append("expected type Character/char but got ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        String valueOf2 = String.valueOf(type);
        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 35);
        sb2.append("expected primitive class, but got: ");
        sb2.append(valueOf2);
        throw new IllegalArgumentException(sb2.toString());
    }

    public static Type zza(List<Type> list, Type type) {
        if (type instanceof WildcardType) {
            type = zzjs.zza((WildcardType) type);
        }
        while (type instanceof TypeVariable) {
            Type zza = zzjs.zza(list, (TypeVariable) type);
            if (zza != null) {
                type = zza;
            }
            if (type instanceof TypeVariable) {
                type = ((TypeVariable) type).getBounds()[0];
            }
        }
        return type;
    }
}
