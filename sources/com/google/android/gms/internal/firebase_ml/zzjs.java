package com.google.android.gms.internal.firebase_ml;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzjs {
    public static IllegalArgumentException a(Exception exc, Class<?> cls) {
        StringBuilder sb = new StringBuilder("unable to create new instance of class ");
        sb.append(cls.getName());
        ArrayList arrayList = new ArrayList();
        int i = 0;
        if (cls.isArray()) {
            arrayList.add("because it is an array");
        } else if (cls.isPrimitive()) {
            arrayList.add("because it is primitive");
        } else if (cls == Void.class) {
            arrayList.add("because it is void");
        } else {
            if (Modifier.isInterface(cls.getModifiers())) {
                arrayList.add("because it is an interface");
            } else if (Modifier.isAbstract(cls.getModifiers())) {
                arrayList.add("because it is abstract");
            }
            if (cls.getEnclosingClass() != null && !Modifier.isStatic(cls.getModifiers())) {
                arrayList.add("because it is not static");
            }
            if (!Modifier.isPublic(cls.getModifiers())) {
                arrayList.add("possibly because it is not public");
            } else {
                try {
                    cls.getConstructor(new Class[0]);
                } catch (NoSuchMethodException unused) {
                    arrayList.add("because it has no accessible default constructor");
                }
            }
        }
        int size = arrayList.size();
        boolean z = false;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            String str = (String) obj;
            if (z) {
                sb.append(" and");
            } else {
                z = true;
            }
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(str);
        }
        return new IllegalArgumentException(sb.toString(), exc);
    }

    public static ParameterizedType b(Type type, Class<?> cls) {
        Class<?> cls2;
        Type[] genericInterfaces;
        Class<?> zza;
        if ((type instanceof Class) || (type instanceof ParameterizedType)) {
            while (type != null && type != Object.class) {
                if (type instanceof Class) {
                    cls2 = (Class) type;
                } else {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Class<?> zza2 = zza(parameterizedType);
                    if (zza2 == cls) {
                        return parameterizedType;
                    }
                    if (cls.isInterface()) {
                        for (Type type2 : zza2.getGenericInterfaces()) {
                            if (type2 instanceof Class) {
                                zza = (Class) type2;
                            } else {
                                zza = zza((ParameterizedType) type2);
                            }
                            if (cls.isAssignableFrom(zza)) {
                                type = type2;
                                break;
                            }
                        }
                    }
                    cls2 = zza2;
                }
                type = cls2.getGenericSuperclass();
            }
            return null;
        }
        return null;
    }

    public static Type c(Type type, Class<?> cls, int i) {
        Type zza;
        ParameterizedType b = b(type, cls);
        if (b == null) {
            return null;
        }
        Type type2 = b.getActualTypeArguments()[i];
        return (!(type2 instanceof TypeVariable) || (zza = zza(Arrays.asList(type), (TypeVariable) type2)) == null) ? type2 : zza;
    }

    public static boolean zza(Class<?> cls, Class<?> cls2) {
        return cls.isAssignableFrom(cls2) || cls2.isAssignableFrom(cls);
    }

    public static Class<?> zzb(List<Type> list, Type type) {
        if (type instanceof TypeVariable) {
            type = zza(list, (TypeVariable) type);
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(zzb(list, zzd(type)), 0).getClass();
        }
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return zza((ParameterizedType) type);
        }
        zzjp.checkArgument(type == null, "wildcard type is not supported: %s", type);
        return Object.class;
    }

    public static boolean zzc(Type type) {
        if (type instanceof GenericArrayType) {
            return true;
        }
        return (type instanceof Class) && ((Class) type).isArray();
    }

    public static Type zzd(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        return ((Class) type).getComponentType();
    }

    public static Type zze(Type type) {
        return c(type, Iterable.class, 0);
    }

    public static <T> T zzf(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            throw a(e, cls);
        } catch (InstantiationException e2) {
            throw a(e2, cls);
        }
    }

    public static <T> Iterable<T> zzi(Object obj) {
        if (obj instanceof Iterable) {
            return (Iterable) obj;
        }
        Class<?> cls = obj.getClass();
        zzjp.checkArgument(cls.isArray(), "not an array or Iterable: %s", cls);
        if (!cls.getComponentType().isPrimitive()) {
            return Arrays.asList((Object[]) obj);
        }
        return new h1(obj);
    }

    public static Class<?> zza(ParameterizedType parameterizedType) {
        return (Class) parameterizedType.getRawType();
    }

    public static Type zza(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length != 0) {
            return lowerBounds[0];
        }
        return wildcardType.getUpperBounds()[0];
    }

    public static Type zzf(Type type) {
        return c(type, Map.class, 1);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.reflect.GenericDeclaration] */
    public static Type zza(List<Type> list, TypeVariable<?> typeVariable) {
        Type zza;
        ?? genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            Class cls = (Class) genericDeclaration;
            int size = list.size();
            ParameterizedType parameterizedType = null;
            while (parameterizedType == null) {
                size--;
                if (size < 0) {
                    break;
                }
                parameterizedType = b(list.get(size), cls);
            }
            if (parameterizedType != null) {
                TypeVariable<?>[] typeParameters = genericDeclaration.getTypeParameters();
                int i = 0;
                while (i < typeParameters.length && !typeParameters[i].equals(typeVariable)) {
                    i++;
                }
                Type type = parameterizedType.getActualTypeArguments()[i];
                return (!(type instanceof TypeVariable) || (zza = zza(list, (TypeVariable) type)) == null) ? type : zza;
            }
        }
        return null;
    }

    public static Object zza(Collection<?> collection, Class<?> cls) {
        if (cls.isPrimitive()) {
            Object newInstance = Array.newInstance(cls, collection.size());
            int i = 0;
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                Array.set(newInstance, i, it.next());
                i++;
            }
            return newInstance;
        }
        return collection.toArray((Object[]) Array.newInstance(cls, collection.size()));
    }
}
