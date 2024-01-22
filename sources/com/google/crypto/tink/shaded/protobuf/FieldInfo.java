package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.lang.reflect.Field;
/* loaded from: classes10.dex */
public final class FieldInfo implements Comparable<FieldInfo> {
    public final Field h;
    public final FieldType i;
    public final Class<?> j;
    public final int k;
    public final Field l;
    public final int m;
    public final boolean n;
    public final boolean o;
    public final g0 p;
    public final Field q;
    public final Class<?> r;
    public final Object s;
    public final Internal.EnumVerifier t;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Field f10949a;
        public FieldType b;
        public int c;
        public Field d;
        public int e;
        public boolean f;
        public boolean g;
        public Class<?> h;
        public Object i;
        public Internal.EnumVerifier j;
        public Field k;

        public FieldInfo build() {
            Object obj = this.i;
            if (obj != null) {
                return FieldInfo.e(this.f10949a, this.c, obj, this.j);
            }
            Field field = this.d;
            if (field != null) {
                if (this.f) {
                    return FieldInfo.i(this.f10949a, this.c, this.b, field, this.e, this.g, this.j);
                }
                return FieldInfo.h(this.f10949a, this.c, this.b, field, this.e, this.g, this.j);
            }
            Internal.EnumVerifier enumVerifier = this.j;
            if (enumVerifier != null) {
                Field field2 = this.k;
                if (field2 == null) {
                    return FieldInfo.d(this.f10949a, this.c, this.b, enumVerifier);
                }
                return FieldInfo.g(this.f10949a, this.c, this.b, enumVerifier, field2);
            }
            Field field3 = this.k;
            if (field3 == null) {
                return FieldInfo.c(this.f10949a, this.c, this.b, this.g);
            }
            return FieldInfo.f(this.f10949a, this.c, this.b, field3);
        }

        public Builder withCachedSizeField(Field field) {
            this.k = field;
            return this;
        }

        public Builder withEnforceUtf8(boolean z) {
            this.g = z;
            return this;
        }

        public Builder withEnumVerifier(Internal.EnumVerifier enumVerifier) {
            this.j = enumVerifier;
            return this;
        }

        public Builder withField(Field field) {
            this.f10949a = field;
            return this;
        }

        public Builder withFieldNumber(int i) {
            this.c = i;
            return this;
        }

        public Builder withMapDefaultEntry(Object obj) {
            this.i = obj;
            return this;
        }

        public Builder withOneof(g0 g0Var, Class<?> cls) {
            if (this.f10949a == null && this.d == null) {
                this.h = cls;
                return this;
            }
            throw new IllegalStateException("Cannot set oneof when field or presenceField have been provided");
        }

        public Builder withPresence(Field field, int i) {
            this.d = (Field) Internal.b(field, "presenceField");
            this.e = i;
            return this;
        }

        public Builder withRequired(boolean z) {
            this.f = z;
            return this;
        }

        public Builder withType(FieldType fieldType) {
            this.b = fieldType;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10950a;

        static {
            int[] iArr = new int[FieldType.values().length];
            f10950a = iArr;
            try {
                iArr[FieldType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10950a[FieldType.GROUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10950a[FieldType.MESSAGE_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10950a[FieldType.GROUP_LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public FieldInfo(Field field, int i, FieldType fieldType, Class<?> cls, Field field2, int i2, boolean z, boolean z2, g0 g0Var, Class<?> cls2, Object obj, Internal.EnumVerifier enumVerifier, Field field3) {
        this.h = field;
        this.i = fieldType;
        this.j = cls;
        this.k = i;
        this.l = field2;
        this.m = i2;
        this.n = z;
        this.o = z2;
        this.r = cls2;
        this.s = obj;
        this.t = enumVerifier;
        this.q = field3;
    }

    public static void a(int i) {
        if (i > 0) {
            return;
        }
        throw new IllegalArgumentException("fieldNumber must be positive: " + i);
    }

    public static FieldInfo c(Field field, int i, FieldType fieldType, boolean z) {
        a(i);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        if (fieldType != FieldType.MESSAGE_LIST && fieldType != FieldType.GROUP_LIST) {
            return new FieldInfo(field, i, fieldType, null, null, 0, false, z, null, null, null, null, null);
        }
        throw new IllegalStateException("Shouldn't be called for repeated message fields.");
    }

    public static FieldInfo d(Field field, int i, FieldType fieldType, Internal.EnumVerifier enumVerifier) {
        a(i);
        Internal.b(field, "field");
        return new FieldInfo(field, i, fieldType, null, null, 0, false, false, null, null, null, enumVerifier, null);
    }

    public static FieldInfo e(Field field, int i, Object obj, Internal.EnumVerifier enumVerifier) {
        Internal.b(obj, "mapDefaultEntry");
        a(i);
        Internal.b(field, "field");
        return new FieldInfo(field, i, FieldType.MAP, null, null, 0, false, true, null, null, obj, enumVerifier, null);
    }

    public static FieldInfo f(Field field, int i, FieldType fieldType, Field field2) {
        a(i);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        if (fieldType != FieldType.MESSAGE_LIST && fieldType != FieldType.GROUP_LIST) {
            return new FieldInfo(field, i, fieldType, null, null, 0, false, false, null, null, null, null, field2);
        }
        throw new IllegalStateException("Shouldn't be called for repeated message fields.");
    }

    public static FieldInfo g(Field field, int i, FieldType fieldType, Internal.EnumVerifier enumVerifier, Field field2) {
        a(i);
        Internal.b(field, "field");
        return new FieldInfo(field, i, fieldType, null, null, 0, false, false, null, null, null, enumVerifier, field2);
    }

    public static FieldInfo h(Field field, int i, FieldType fieldType, Field field2, int i2, boolean z, Internal.EnumVerifier enumVerifier) {
        a(i);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        Internal.b(field2, "presenceField");
        if (field2 != null && !u(i2)) {
            throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + i2);
        }
        return new FieldInfo(field, i, fieldType, null, field2, i2, false, z, null, null, null, enumVerifier, null);
    }

    public static FieldInfo i(Field field, int i, FieldType fieldType, Field field2, int i2, boolean z, Internal.EnumVerifier enumVerifier) {
        a(i);
        Internal.b(field, "field");
        Internal.b(fieldType, "fieldType");
        Internal.b(field2, "presenceField");
        if (field2 != null && !u(i2)) {
            throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + i2);
        }
        return new FieldInfo(field, i, fieldType, null, field2, i2, true, z, null, null, null, enumVerifier, null);
    }

    public static boolean u(int i) {
        return i != 0 && (i & (i + (-1))) == 0;
    }

    @Override // java.lang.Comparable
    /* renamed from: b */
    public int compareTo(FieldInfo fieldInfo) {
        return this.k - fieldInfo.k;
    }

    public Field j() {
        return this.q;
    }

    public Internal.EnumVerifier k() {
        return this.t;
    }

    public Field l() {
        return this.h;
    }

    public int m() {
        return this.k;
    }

    public Object n() {
        return this.s;
    }

    public Class<?> o() {
        int i = a.f10950a[this.i.ordinal()];
        if (i == 1 || i == 2) {
            Field field = this.h;
            return field != null ? field.getType() : this.r;
        } else if (i == 3 || i == 4) {
            return this.j;
        } else {
            return null;
        }
    }

    public g0 p() {
        return this.p;
    }

    public Field q() {
        return this.l;
    }

    public int r() {
        return this.m;
    }

    public FieldType s() {
        return this.i;
    }

    public boolean t() {
        return this.o;
    }

    public boolean v() {
        return this.n;
    }
}
