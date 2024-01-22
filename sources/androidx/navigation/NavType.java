package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public abstract class NavType<T> {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f1453a;
    @NonNull
    public static final NavType<Integer> IntType = new c(false);
    @NonNull
    public static final NavType<Integer> ReferenceType = new d(false);
    @NonNull
    public static final NavType<int[]> IntArrayType = new e(true);
    @NonNull
    public static final NavType<Long> LongType = new f(false);
    @NonNull
    public static final NavType<long[]> LongArrayType = new g(true);
    @NonNull
    public static final NavType<Float> FloatType = new h(false);
    @NonNull
    public static final NavType<float[]> FloatArrayType = new i(true);
    @NonNull
    public static final NavType<Boolean> BoolType = new j(false);
    @NonNull
    public static final NavType<boolean[]> BoolArrayType = new k(true);
    @NonNull
    public static final NavType<String> StringType = new a(true);
    @NonNull
    public static final NavType<String[]> StringArrayType = new b(true);

    /* loaded from: classes.dex */
    public static final class ParcelableArrayType<D extends Parcelable> extends NavType<D[]> {
        @NonNull
        public final Class<D[]> b;

        public ParcelableArrayType(@NonNull Class<D> cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls)) {
                try {
                    this.b = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
                    return;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            throw new IllegalArgumentException(cls + " does not implement Parcelable.");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ParcelableArrayType.class != obj.getClass()) {
                return false;
            }
            return this.b.equals(((ParcelableArrayType) obj).b);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.b.getName();
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (D[]) ((Parcelable[]) bundle.get(str));
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D[] dArr) {
            this.b.cast(dArr);
            bundle.putParcelableArray(str, dArr);
        }
    }

    /* loaded from: classes.dex */
    public static final class ParcelableType<D> extends NavType<D> {
        @NonNull
        public final Class<D> b;

        public ParcelableType(@NonNull Class<D> cls) {
            super(true);
            if (!Parcelable.class.isAssignableFrom(cls) && !Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Parcelable or Serializable.");
            }
            this.b = cls;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ParcelableType.class != obj.getClass()) {
                return false;
            }
            return this.b.equals(((ParcelableType) obj).b);
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D get(@NonNull Bundle bundle, @NonNull String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.b.getName();
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D d) {
            this.b.cast(d);
            if (d != null && !(d instanceof Parcelable)) {
                if (d instanceof Serializable) {
                    bundle.putSerializable(str, (Serializable) d);
                    return;
                }
                return;
            }
            bundle.putParcelable(str, (Parcelable) d);
        }
    }

    /* loaded from: classes.dex */
    public static final class SerializableArrayType<D extends Serializable> extends NavType<D[]> {
        @NonNull
        public final Class<D[]> b;

        public SerializableArrayType(@NonNull Class<D> cls) {
            super(true);
            if (Serializable.class.isAssignableFrom(cls)) {
                try {
                    this.b = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
                    return;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            throw new IllegalArgumentException(cls + " does not implement Serializable.");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || SerializableArrayType.class != obj.getClass()) {
                return false;
            }
            return this.b.equals(((SerializableArrayType) obj).b);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.b.getName();
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (D[]) ((Serializable[]) bundle.get(str));
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.navigation.NavType
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D[] dArr) {
            this.b.cast(dArr);
            bundle.putSerializable(str, dArr);
        }
    }

    /* loaded from: classes.dex */
    public static class SerializableType<D extends Serializable> extends NavType<D> {
        @NonNull
        public final Class<D> b;

        public SerializableType(@NonNull Class<D> cls) {
            super(true);
            if (Serializable.class.isAssignableFrom(cls)) {
                if (!cls.isEnum()) {
                    this.b = cls;
                    return;
                }
                throw new IllegalArgumentException(cls + " is an Enum. You should use EnumType instead.");
            }
            throw new IllegalArgumentException(cls + " does not implement Serializable.");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SerializableType) {
                return this.b.equals(((SerializableType) obj).b);
            }
            return false;
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.b.getName();
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(@NonNull Bundle bundle, @NonNull String str, @Nullable Object obj) {
            put(bundle, str, (String) obj);
        }

        @Override // androidx.navigation.NavType
        @Nullable
        public D get(@NonNull Bundle bundle, @NonNull String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public D parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D d) {
            this.b.cast(d);
            bundle.putSerializable(str, d);
        }

        public SerializableType(boolean z, @NonNull Class<D> cls) {
            super(z);
            if (Serializable.class.isAssignableFrom(cls)) {
                this.b = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Serializable.");
        }
    }

    /* loaded from: classes.dex */
    public class a extends NavType<String> {
        public a(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public String get(@NonNull Bundle bundle, @NonNull String str) {
            return (String) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public String parseValue(@NonNull String str) {
            return str;
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable String str2) {
            bundle.putString(str, str2);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "string";
        }
    }

    /* loaded from: classes.dex */
    public class b extends NavType<String[]> {
        public b(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public String[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (String[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public String[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable String[] strArr) {
            bundle.putStringArray(str, strArr);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "string[]";
        }
    }

    /* loaded from: classes.dex */
    public class c extends NavType<Integer> {
        public c(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public Integer get(@NonNull Bundle bundle, @NonNull String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public Integer parseValue(@NonNull String str) {
            if (str.startsWith(HexStringBuilder.DEFAULT_PREFIX)) {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            }
            return Integer.valueOf(Integer.parseInt(str));
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Integer num) {
            bundle.putInt(str, num.intValue());
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return TypedValues.Custom.S_INT;
        }
    }

    /* loaded from: classes.dex */
    public class d extends NavType<Integer> {
        public d(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        @AnyRes
        /* renamed from: d */
        public Integer get(@NonNull Bundle bundle, @NonNull String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public Integer parseValue(@NonNull String str) {
            if (str.startsWith(HexStringBuilder.DEFAULT_PREFIX)) {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            }
            return Integer.valueOf(Integer.parseInt(str));
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull @AnyRes Integer num) {
            bundle.putInt(str, num.intValue());
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "reference";
        }
    }

    /* loaded from: classes.dex */
    public class e extends NavType<int[]> {
        public e(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public int[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (int[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public int[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable int[] iArr) {
            bundle.putIntArray(str, iArr);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "integer[]";
        }
    }

    /* loaded from: classes.dex */
    public class f extends NavType<Long> {
        public f(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public Long get(@NonNull Bundle bundle, @NonNull String str) {
            return (Long) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public Long parseValue(@NonNull String str) {
            if (str.endsWith("L")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.startsWith(HexStringBuilder.DEFAULT_PREFIX)) {
                return Long.valueOf(Long.parseLong(str.substring(2), 16));
            }
            return Long.valueOf(Long.parseLong(str));
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Long l) {
            bundle.putLong(str, l.longValue());
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "long";
        }
    }

    /* loaded from: classes.dex */
    public class g extends NavType<long[]> {
        public g(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public long[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (long[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public long[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable long[] jArr) {
            bundle.putLongArray(str, jArr);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "long[]";
        }
    }

    /* loaded from: classes.dex */
    public class h extends NavType<Float> {
        public h(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public Float get(@NonNull Bundle bundle, @NonNull String str) {
            return (Float) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public Float parseValue(@NonNull String str) {
            return Float.valueOf(Float.parseFloat(str));
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Float f) {
            bundle.putFloat(str, f.floatValue());
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return TypedValues.Custom.S_FLOAT;
        }
    }

    /* loaded from: classes.dex */
    public class i extends NavType<float[]> {
        public i(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public float[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (float[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public float[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable float[] fArr) {
            bundle.putFloatArray(str, fArr);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "float[]";
        }
    }

    /* loaded from: classes.dex */
    public class j extends NavType<Boolean> {
        public j(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public Boolean get(@NonNull Bundle bundle, @NonNull String str) {
            return (Boolean) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public Boolean parseValue(@NonNull String str) {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Boolean bool) {
            bundle.putBoolean(str, bool.booleanValue());
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "boolean";
        }
    }

    /* loaded from: classes.dex */
    public class k extends NavType<boolean[]> {
        public k(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.NavType
        /* renamed from: d */
        public boolean[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (boolean[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        /* renamed from: e */
        public boolean[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        /* renamed from: f */
        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable boolean[] zArr) {
            bundle.putBooleanArray(str, zArr);
        }

        @Override // androidx.navigation.NavType
        @NonNull
        public String getName() {
            return "boolean[]";
        }
    }

    public NavType(boolean z) {
        this.f1453a = z;
    }

    @NonNull
    public static NavType a(@NonNull String str) {
        try {
            try {
                try {
                    try {
                        NavType<Integer> navType = IntType;
                        navType.parseValue(str);
                        return navType;
                    } catch (IllegalArgumentException unused) {
                        NavType<Boolean> navType2 = BoolType;
                        navType2.parseValue(str);
                        return navType2;
                    }
                } catch (IllegalArgumentException unused2) {
                    NavType<Float> navType3 = FloatType;
                    navType3.parseValue(str);
                    return navType3;
                }
            } catch (IllegalArgumentException unused3) {
                NavType<Long> navType4 = LongType;
                navType4.parseValue(str);
                return navType4;
            }
        } catch (IllegalArgumentException unused4) {
            return StringType;
        }
    }

    @NonNull
    public static NavType b(@Nullable Object obj) {
        if (obj instanceof Integer) {
            return IntType;
        }
        if (obj instanceof int[]) {
            return IntArrayType;
        }
        if (obj instanceof Long) {
            return LongType;
        }
        if (obj instanceof long[]) {
            return LongArrayType;
        }
        if (obj instanceof Float) {
            return FloatType;
        }
        if (obj instanceof float[]) {
            return FloatArrayType;
        }
        if (obj instanceof Boolean) {
            return BoolType;
        }
        if (obj instanceof boolean[]) {
            return BoolArrayType;
        }
        if (!(obj instanceof String) && obj != null) {
            if (obj instanceof String[]) {
                return StringArrayType;
            }
            if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
                return new ParcelableArrayType(obj.getClass().getComponentType());
            }
            if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
                return new SerializableArrayType(obj.getClass().getComponentType());
            }
            if (obj instanceof Parcelable) {
                return new ParcelableType(obj.getClass());
            }
            if (obj instanceof Enum) {
                return new EnumType(obj.getClass());
            }
            if (obj instanceof Serializable) {
                return new SerializableType(obj.getClass());
            }
            throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
        }
        return StringType;
    }

    @NonNull
    public static NavType<?> fromArgType(@Nullable String str, @Nullable String str2) {
        String str3;
        NavType<Integer> navType = IntType;
        if (navType.getName().equals(str)) {
            return navType;
        }
        NavType navType2 = IntArrayType;
        if (navType2.getName().equals(str)) {
            return navType2;
        }
        NavType<Long> navType3 = LongType;
        if (navType3.getName().equals(str)) {
            return navType3;
        }
        NavType navType4 = LongArrayType;
        if (navType4.getName().equals(str)) {
            return navType4;
        }
        NavType<Boolean> navType5 = BoolType;
        if (navType5.getName().equals(str)) {
            return navType5;
        }
        NavType navType6 = BoolArrayType;
        if (navType6.getName().equals(str)) {
            return navType6;
        }
        NavType<String> navType7 = StringType;
        if (navType7.getName().equals(str)) {
            return navType7;
        }
        NavType navType8 = StringArrayType;
        if (navType8.getName().equals(str)) {
            return navType8;
        }
        NavType<Float> navType9 = FloatType;
        if (navType9.getName().equals(str)) {
            return navType9;
        }
        NavType navType10 = FloatArrayType;
        if (navType10.getName().equals(str)) {
            return navType10;
        }
        NavType<Integer> navType11 = ReferenceType;
        if (navType11.getName().equals(str)) {
            return navType11;
        }
        if (str == null || str.isEmpty()) {
            return navType7;
        }
        try {
            if (!str.startsWith(".") || str2 == null) {
                str3 = str;
            } else {
                str3 = str2 + str;
            }
            if (str.endsWith(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
                str3 = str3.substring(0, str3.length() - 2);
                Class<?> cls = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls)) {
                    return new ParcelableArrayType(cls);
                }
                if (Serializable.class.isAssignableFrom(cls)) {
                    return new SerializableArrayType(cls);
                }
            } else {
                Class<?> cls2 = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls2)) {
                    return new ParcelableType(cls2);
                }
                if (Enum.class.isAssignableFrom(cls2)) {
                    return new EnumType(cls2);
                }
                if (Serializable.class.isAssignableFrom(cls2)) {
                    return new SerializableType(cls2);
                }
            }
            throw new IllegalArgumentException(str3 + " is not Serializable or Parcelable.");
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(e2);
        }
    }

    @NonNull
    public T c(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2) {
        T parseValue = parseValue(str2);
        put(bundle, str, parseValue);
        return parseValue;
    }

    @Nullable
    public abstract T get(@NonNull Bundle bundle, @NonNull String str);

    @NonNull
    public abstract String getName();

    public boolean isNullableAllowed() {
        return this.f1453a;
    }

    @NonNull
    public abstract T parseValue(@NonNull String str);

    public abstract void put(@NonNull Bundle bundle, @NonNull String str, @Nullable T t);

    @NonNull
    public String toString() {
        return getName();
    }

    /* loaded from: classes.dex */
    public static final class EnumType<D extends Enum> extends SerializableType<D> {
        @NonNull
        public final Class<D> c;

        public EnumType(@NonNull Class<D> cls) {
            super(false, cls);
            if (cls.isEnum()) {
                this.c = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " is not an Enum type.");
        }

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        @NonNull
        public String getName() {
            return this.c.getName();
        }

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        @NonNull
        public D parseValue(@NonNull String str) {
            D[] enumConstants;
            for (D d : this.c.getEnumConstants()) {
                if (d.name().equals(str)) {
                    return d;
                }
            }
            throw new IllegalArgumentException("Enum value " + str + " not found for type " + this.c.getName() + ".");
        }
    }
}
