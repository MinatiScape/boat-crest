package com.google.protobuf;

import com.google.protobuf.Internal;
/* loaded from: classes11.dex */
public final class FieldInfo implements Comparable<FieldInfo> {
    public final java.lang.reflect.Field h;
    public final FieldType i;
    public final Class<?> j;
    public final int k;
    public final java.lang.reflect.Field l;
    public final int m;
    public final boolean n;
    public final boolean o;
    public final l0 p;
    public final java.lang.reflect.Field q;
    public final Class<?> r;
    public final Object s;
    public final Internal.EnumVerifier t;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private java.lang.reflect.Field cachedSizeField;
        private boolean enforceUtf8;
        private Internal.EnumVerifier enumVerifier;
        private java.lang.reflect.Field field;
        private int fieldNumber;
        private Object mapDefaultEntry;
        private l0 oneof;
        private Class<?> oneofStoredType;
        private java.lang.reflect.Field presenceField;
        private int presenceMask;
        private boolean required;
        private FieldType type;

        public /* synthetic */ Builder(a aVar) {
            this();
        }

        public FieldInfo build() {
            l0 l0Var = this.oneof;
            if (l0Var != null) {
                return FieldInfo.f(this.fieldNumber, this.type, l0Var, this.oneofStoredType, this.enforceUtf8, this.enumVerifier);
            }
            Object obj = this.mapDefaultEntry;
            if (obj != null) {
                return FieldInfo.e(this.field, this.fieldNumber, obj, this.enumVerifier);
            }
            java.lang.reflect.Field field = this.presenceField;
            if (field != null) {
                if (this.required) {
                    return FieldInfo.j(this.field, this.fieldNumber, this.type, field, this.presenceMask, this.enforceUtf8, this.enumVerifier);
                }
                return FieldInfo.i(this.field, this.fieldNumber, this.type, field, this.presenceMask, this.enforceUtf8, this.enumVerifier);
            }
            Internal.EnumVerifier enumVerifier = this.enumVerifier;
            if (enumVerifier != null) {
                java.lang.reflect.Field field2 = this.cachedSizeField;
                if (field2 == null) {
                    return FieldInfo.d(this.field, this.fieldNumber, this.type, enumVerifier);
                }
                return FieldInfo.h(this.field, this.fieldNumber, this.type, enumVerifier, field2);
            }
            java.lang.reflect.Field field3 = this.cachedSizeField;
            if (field3 == null) {
                return FieldInfo.c(this.field, this.fieldNumber, this.type, this.enforceUtf8);
            }
            return FieldInfo.g(this.field, this.fieldNumber, this.type, field3);
        }

        public Builder withCachedSizeField(java.lang.reflect.Field field) {
            this.cachedSizeField = field;
            return this;
        }

        public Builder withEnforceUtf8(boolean z) {
            this.enforceUtf8 = z;
            return this;
        }

        public Builder withEnumVerifier(Internal.EnumVerifier enumVerifier) {
            this.enumVerifier = enumVerifier;
            return this;
        }

        public Builder withField(java.lang.reflect.Field field) {
            if (this.oneof == null) {
                this.field = field;
                return this;
            }
            throw new IllegalStateException("Cannot set field when building a oneof.");
        }

        public Builder withFieldNumber(int i) {
            this.fieldNumber = i;
            return this;
        }

        public Builder withMapDefaultEntry(Object obj) {
            this.mapDefaultEntry = obj;
            return this;
        }

        public Builder withOneof(l0 l0Var, Class<?> cls) {
            if (this.field == null && this.presenceField == null) {
                this.oneof = l0Var;
                this.oneofStoredType = cls;
                return this;
            }
            throw new IllegalStateException("Cannot set oneof when field or presenceField have been provided");
        }

        public Builder withPresence(java.lang.reflect.Field field, int i) {
            this.presenceField = (java.lang.reflect.Field) Internal.checkNotNull(field, "presenceField");
            this.presenceMask = i;
            return this;
        }

        public Builder withRequired(boolean z) {
            this.required = z;
            return this;
        }

        public Builder withType(FieldType fieldType) {
            this.type = fieldType;
            return this;
        }

        private Builder() {
        }
    }

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11674a;

        static {
            int[] iArr = new int[FieldType.values().length];
            f11674a = iArr;
            try {
                iArr[FieldType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11674a[FieldType.GROUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11674a[FieldType.MESSAGE_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11674a[FieldType.GROUP_LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public FieldInfo(java.lang.reflect.Field field, int i, FieldType fieldType, Class<?> cls, java.lang.reflect.Field field2, int i2, boolean z, boolean z2, l0 l0Var, Class<?> cls2, Object obj, Internal.EnumVerifier enumVerifier, java.lang.reflect.Field field3) {
        this.h = field;
        this.i = fieldType;
        this.j = cls;
        this.k = i;
        this.l = field2;
        this.m = i2;
        this.n = z;
        this.o = z2;
        this.p = l0Var;
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

    public static FieldInfo c(java.lang.reflect.Field field, int i, FieldType fieldType, boolean z) {
        a(i);
        Internal.checkNotNull(field, "field");
        Internal.checkNotNull(fieldType, "fieldType");
        if (fieldType != FieldType.MESSAGE_LIST && fieldType != FieldType.GROUP_LIST) {
            return new FieldInfo(field, i, fieldType, null, null, 0, false, z, null, null, null, null, null);
        }
        throw new IllegalStateException("Shouldn't be called for repeated message fields.");
    }

    public static FieldInfo d(java.lang.reflect.Field field, int i, FieldType fieldType, Internal.EnumVerifier enumVerifier) {
        a(i);
        Internal.checkNotNull(field, "field");
        return new FieldInfo(field, i, fieldType, null, null, 0, false, false, null, null, null, enumVerifier, null);
    }

    public static FieldInfo e(java.lang.reflect.Field field, int i, Object obj, Internal.EnumVerifier enumVerifier) {
        Internal.checkNotNull(obj, "mapDefaultEntry");
        a(i);
        Internal.checkNotNull(field, "field");
        return new FieldInfo(field, i, FieldType.MAP, null, null, 0, false, true, null, null, obj, enumVerifier, null);
    }

    public static FieldInfo f(int i, FieldType fieldType, l0 l0Var, Class<?> cls, boolean z, Internal.EnumVerifier enumVerifier) {
        a(i);
        Internal.checkNotNull(fieldType, "fieldType");
        Internal.checkNotNull(l0Var, "oneof");
        Internal.checkNotNull(cls, "oneofStoredType");
        if (fieldType.isScalar()) {
            return new FieldInfo(null, i, fieldType, null, null, 0, false, z, l0Var, cls, null, enumVerifier, null);
        }
        throw new IllegalArgumentException("Oneof is only supported for scalar fields. Field " + i + " is of type " + fieldType);
    }

    public static FieldInfo g(java.lang.reflect.Field field, int i, FieldType fieldType, java.lang.reflect.Field field2) {
        a(i);
        Internal.checkNotNull(field, "field");
        Internal.checkNotNull(fieldType, "fieldType");
        if (fieldType != FieldType.MESSAGE_LIST && fieldType != FieldType.GROUP_LIST) {
            return new FieldInfo(field, i, fieldType, null, null, 0, false, false, null, null, null, null, field2);
        }
        throw new IllegalStateException("Shouldn't be called for repeated message fields.");
    }

    public static FieldInfo h(java.lang.reflect.Field field, int i, FieldType fieldType, Internal.EnumVerifier enumVerifier, java.lang.reflect.Field field2) {
        a(i);
        Internal.checkNotNull(field, "field");
        return new FieldInfo(field, i, fieldType, null, null, 0, false, false, null, null, null, enumVerifier, field2);
    }

    public static FieldInfo i(java.lang.reflect.Field field, int i, FieldType fieldType, java.lang.reflect.Field field2, int i2, boolean z, Internal.EnumVerifier enumVerifier) {
        a(i);
        Internal.checkNotNull(field, "field");
        Internal.checkNotNull(fieldType, "fieldType");
        Internal.checkNotNull(field2, "presenceField");
        if (field2 != null && !w(i2)) {
            throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + i2);
        }
        return new FieldInfo(field, i, fieldType, null, field2, i2, false, z, null, null, null, enumVerifier, null);
    }

    public static FieldInfo j(java.lang.reflect.Field field, int i, FieldType fieldType, java.lang.reflect.Field field2, int i2, boolean z, Internal.EnumVerifier enumVerifier) {
        a(i);
        Internal.checkNotNull(field, "field");
        Internal.checkNotNull(fieldType, "fieldType");
        Internal.checkNotNull(field2, "presenceField");
        if (field2 != null && !w(i2)) {
            throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + i2);
        }
        return new FieldInfo(field, i, fieldType, null, field2, i2, true, z, null, null, null, enumVerifier, null);
    }

    public static FieldInfo k(java.lang.reflect.Field field, int i, FieldType fieldType, Class<?> cls) {
        a(i);
        Internal.checkNotNull(field, "field");
        Internal.checkNotNull(fieldType, "fieldType");
        Internal.checkNotNull(cls, "messageClass");
        return new FieldInfo(field, i, fieldType, cls, null, 0, false, false, null, null, null, null, null);
    }

    public static boolean w(int i) {
        return i != 0 && (i & (i + (-1))) == 0;
    }

    @Override // java.lang.Comparable
    /* renamed from: b */
    public int compareTo(FieldInfo fieldInfo) {
        return this.k - fieldInfo.k;
    }

    public java.lang.reflect.Field l() {
        return this.q;
    }

    public Internal.EnumVerifier m() {
        return this.t;
    }

    public java.lang.reflect.Field n() {
        return this.h;
    }

    public int o() {
        return this.k;
    }

    public Object p() {
        return this.s;
    }

    public Class<?> q() {
        int i = a.f11674a[this.i.ordinal()];
        if (i == 1 || i == 2) {
            java.lang.reflect.Field field = this.h;
            return field != null ? field.getType() : this.r;
        } else if (i == 3 || i == 4) {
            return this.j;
        } else {
            return null;
        }
    }

    public l0 r() {
        return this.p;
    }

    public java.lang.reflect.Field s() {
        return this.l;
    }

    public int t() {
        return this.m;
    }

    public FieldType u() {
        return this.i;
    }

    public boolean v() {
        return this.o;
    }

    public boolean x() {
        return this.n;
    }
}
