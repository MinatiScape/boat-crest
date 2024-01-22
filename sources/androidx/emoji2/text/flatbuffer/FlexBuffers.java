package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import kotlin.UShort;
import kotlin.text.Typography;
/* loaded from: classes.dex */
public class FlexBuffers {
    public static final int FBT_BLOB = 25;
    public static final int FBT_BOOL = 26;
    public static final int FBT_FLOAT = 3;
    public static final int FBT_INDIRECT_FLOAT = 8;
    public static final int FBT_INDIRECT_INT = 6;
    public static final int FBT_INDIRECT_UINT = 7;
    public static final int FBT_INT = 1;
    public static final int FBT_KEY = 4;
    public static final int FBT_MAP = 9;
    public static final int FBT_NULL = 0;
    public static final int FBT_STRING = 5;
    public static final int FBT_UINT = 2;
    public static final int FBT_VECTOR = 10;
    public static final int FBT_VECTOR_BOOL = 36;
    public static final int FBT_VECTOR_FLOAT = 13;
    public static final int FBT_VECTOR_FLOAT2 = 18;
    public static final int FBT_VECTOR_FLOAT3 = 21;
    public static final int FBT_VECTOR_FLOAT4 = 24;
    public static final int FBT_VECTOR_INT = 11;
    public static final int FBT_VECTOR_INT2 = 16;
    public static final int FBT_VECTOR_INT3 = 19;
    public static final int FBT_VECTOR_INT4 = 22;
    public static final int FBT_VECTOR_KEY = 14;
    public static final int FBT_VECTOR_STRING_DEPRECATED = 15;
    public static final int FBT_VECTOR_UINT = 12;
    public static final int FBT_VECTOR_UINT2 = 17;
    public static final int FBT_VECTOR_UINT3 = 20;
    public static final int FBT_VECTOR_UINT4 = 23;

    /* renamed from: a  reason: collision with root package name */
    public static final androidx.emoji2.text.flatbuffer.a f1270a = new ArrayReadWriteBuf(new byte[]{0}, 1);

    /* loaded from: classes.dex */
    public static class Blob extends b {
        public static final Blob d = new Blob(FlexBuffers.f1270a, 1, 1);

        public Blob(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
            super(aVar, i, i2);
        }

        public static Blob empty() {
            return d;
        }

        public ByteBuffer data() {
            ByteBuffer wrap = ByteBuffer.wrap(this.f1273a.data());
            wrap.position(this.b);
            wrap.limit(this.b + size());
            return wrap.asReadOnlyBuffer().slice();
        }

        public byte get(int i) {
            return this.f1273a.get(this.b + i);
        }

        public byte[] getBytes() {
            int size = size();
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = this.f1273a.get(this.b + i);
            }
            return bArr;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.b
        public /* bridge */ /* synthetic */ int size() {
            return super.size();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.a
        public String toString() {
            return this.f1273a.getString(this.b, size());
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.a
        public StringBuilder toString(StringBuilder sb) {
            sb.append(Typography.quote);
            sb.append(this.f1273a.getString(this.b, size()));
            sb.append(Typography.quote);
            return sb;
        }
    }

    /* loaded from: classes.dex */
    public static class FlexBufferException extends RuntimeException {
        public FlexBufferException(String str) {
            super(str);
        }
    }

    /* loaded from: classes.dex */
    public static class Key extends a {
        public static final Key d = new Key(FlexBuffers.f1270a, 0, 0);

        public Key(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
            super(aVar, i, i2);
        }

        public static Key empty() {
            return d;
        }

        public int b(byte[] bArr) {
            byte b;
            byte b2;
            int i = this.b;
            int i2 = 0;
            do {
                b = this.f1273a.get(i);
                b2 = bArr[i2];
                if (b == 0) {
                    return b - b2;
                }
                i++;
                i2++;
                if (i2 == bArr.length) {
                    return b - b2;
                }
            } while (b == b2);
            return b - b2;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                Key key = (Key) obj;
                return key.b == this.b && key.c == this.c;
            }
            return false;
        }

        public int hashCode() {
            return this.b ^ this.c;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.a
        public StringBuilder toString(StringBuilder sb) {
            sb.append(toString());
            return sb;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.a
        public String toString() {
            int i = this.b;
            while (this.f1273a.get(i) != 0) {
                i++;
            }
            int i2 = this.b;
            return this.f1273a.getString(i2, i - i2);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyVector {

        /* renamed from: a  reason: collision with root package name */
        public final TypedVector f1271a;

        public KeyVector(TypedVector typedVector) {
            this.f1271a = typedVector;
        }

        public Key get(int i) {
            if (i >= size()) {
                return Key.d;
            }
            TypedVector typedVector = this.f1271a;
            TypedVector typedVector2 = this.f1271a;
            androidx.emoji2.text.flatbuffer.a aVar = typedVector2.f1273a;
            return new Key(aVar, FlexBuffers.g(aVar, typedVector.b + (i * typedVector.c), typedVector2.c), 1);
        }

        public int size() {
            return this.f1271a.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 0; i < this.f1271a.size(); i++) {
                this.f1271a.get(i).b(sb);
                if (i != this.f1271a.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class Map extends Vector {
        public static final Map e = new Map(FlexBuffers.f1270a, 1, 1);

        public Map(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
            super(aVar, i, i2);
        }

        public static Map empty() {
            return e;
        }

        public final int a(KeyVector keyVector, byte[] bArr) {
            int size = keyVector.size() - 1;
            int i = 0;
            while (i <= size) {
                int i2 = (i + size) >>> 1;
                int b = keyVector.get(i2).b(bArr);
                if (b < 0) {
                    i = i2 + 1;
                } else if (b <= 0) {
                    return i2;
                } else {
                    size = i2 - 1;
                }
            }
            return -(i + 1);
        }

        public Reference get(String str) {
            return get(str.getBytes(StandardCharsets.UTF_8));
        }

        public KeyVector keys() {
            int i = this.b - (this.c * 3);
            androidx.emoji2.text.flatbuffer.a aVar = this.f1273a;
            int g = FlexBuffers.g(aVar, i, this.c);
            androidx.emoji2.text.flatbuffer.a aVar2 = this.f1273a;
            int i2 = this.c;
            return new KeyVector(new TypedVector(aVar, g, FlexBuffers.l(aVar2, i + i2, i2), 4));
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector, androidx.emoji2.text.flatbuffer.FlexBuffers.a
        public StringBuilder toString(StringBuilder sb) {
            sb.append("{ ");
            KeyVector keys = keys();
            int size = size();
            Vector values = values();
            for (int i = 0; i < size; i++) {
                sb.append(Typography.quote);
                sb.append(keys.get(i).toString());
                sb.append("\" : ");
                sb.append(values.get(i).toString());
                if (i != size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" }");
            return sb;
        }

        public Vector values() {
            return new Vector(this.f1273a, this.b, this.c);
        }

        public Reference get(byte[] bArr) {
            KeyVector keys = keys();
            int size = keys.size();
            int a2 = a(keys, bArr);
            if (a2 < 0 || a2 >= size) {
                return Reference.f;
            }
            return get(a2);
        }
    }

    /* loaded from: classes.dex */
    public static class Reference {
        public static final Reference f = new Reference(FlexBuffers.f1270a, 0, 1, 0);

        /* renamed from: a  reason: collision with root package name */
        public androidx.emoji2.text.flatbuffer.a f1272a;
        public int b;
        public int c;
        public int d;
        public int e;

        public Reference(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2, int i3) {
            this(aVar, i, i2, 1 << (i3 & 3), i3 >> 2);
        }

        public Blob asBlob() {
            if (!isBlob() && !isString()) {
                return Blob.empty();
            }
            androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
            return new Blob(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
        }

        public boolean asBoolean() {
            return isBoolean() ? this.f1272a.get(this.b) != 0 : asUInt() != 0;
        }

        public double asFloat() {
            int i = this.e;
            if (i == 3) {
                return FlexBuffers.k(this.f1272a, this.b, this.c);
            }
            if (i != 1) {
                if (i != 2) {
                    if (i == 5) {
                        return Double.parseDouble(asString());
                    }
                    if (i == 6) {
                        androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                        return FlexBuffers.l(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
                    } else if (i == 7) {
                        androidx.emoji2.text.flatbuffer.a aVar2 = this.f1272a;
                        return FlexBuffers.n(aVar2, FlexBuffers.g(aVar2, this.b, this.c), this.d);
                    } else if (i == 8) {
                        androidx.emoji2.text.flatbuffer.a aVar3 = this.f1272a;
                        return FlexBuffers.k(aVar3, FlexBuffers.g(aVar3, this.b, this.c), this.d);
                    } else if (i == 10) {
                        return asVector().size();
                    } else {
                        if (i != 26) {
                            return 0.0d;
                        }
                    }
                }
                return FlexBuffers.n(this.f1272a, this.b, this.c);
            }
            return FlexBuffers.l(this.f1272a, this.b, this.c);
        }

        public int asInt() {
            int i = this.e;
            if (i == 1) {
                return FlexBuffers.l(this.f1272a, this.b, this.c);
            }
            if (i != 2) {
                if (i != 3) {
                    if (i != 5) {
                        if (i == 6) {
                            androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                            return FlexBuffers.l(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
                        } else if (i == 7) {
                            androidx.emoji2.text.flatbuffer.a aVar2 = this.f1272a;
                            return (int) FlexBuffers.n(aVar2, FlexBuffers.g(aVar2, this.b, this.c), this.c);
                        } else if (i == 8) {
                            androidx.emoji2.text.flatbuffer.a aVar3 = this.f1272a;
                            return (int) FlexBuffers.k(aVar3, FlexBuffers.g(aVar3, this.b, this.c), this.d);
                        } else if (i != 10) {
                            if (i != 26) {
                                return 0;
                            }
                            return FlexBuffers.l(this.f1272a, this.b, this.c);
                        } else {
                            return asVector().size();
                        }
                    }
                    return Integer.parseInt(asString());
                }
                return (int) FlexBuffers.k(this.f1272a, this.b, this.c);
            }
            return (int) FlexBuffers.n(this.f1272a, this.b, this.c);
        }

        public Key asKey() {
            if (isKey()) {
                androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                return new Key(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
            }
            return Key.empty();
        }

        public long asLong() {
            int i = this.e;
            if (i == 1) {
                return FlexBuffers.m(this.f1272a, this.b, this.c);
            }
            if (i != 2) {
                if (i != 3) {
                    if (i == 5) {
                        try {
                            return Long.parseLong(asString());
                        } catch (NumberFormatException unused) {
                            return 0L;
                        }
                    } else if (i == 6) {
                        androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                        return FlexBuffers.m(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
                    } else if (i == 7) {
                        androidx.emoji2.text.flatbuffer.a aVar2 = this.f1272a;
                        return FlexBuffers.n(aVar2, FlexBuffers.g(aVar2, this.b, this.c), this.c);
                    } else if (i == 8) {
                        androidx.emoji2.text.flatbuffer.a aVar3 = this.f1272a;
                        return (long) FlexBuffers.k(aVar3, FlexBuffers.g(aVar3, this.b, this.c), this.d);
                    } else if (i != 10) {
                        if (i != 26) {
                            return 0L;
                        }
                        return FlexBuffers.l(this.f1272a, this.b, this.c);
                    } else {
                        return asVector().size();
                    }
                }
                return (long) FlexBuffers.k(this.f1272a, this.b, this.c);
            }
            return FlexBuffers.n(this.f1272a, this.b, this.c);
        }

        public Map asMap() {
            if (isMap()) {
                androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                return new Map(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
            }
            return Map.empty();
        }

        public String asString() {
            if (isString()) {
                int g = FlexBuffers.g(this.f1272a, this.b, this.c);
                androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                int i = this.d;
                return this.f1272a.getString(g, (int) FlexBuffers.n(aVar, g - i, i));
            } else if (isKey()) {
                int g2 = FlexBuffers.g(this.f1272a, this.b, this.d);
                int i2 = g2;
                while (this.f1272a.get(i2) != 0) {
                    i2++;
                }
                return this.f1272a.getString(g2, i2 - g2);
            } else {
                return "";
            }
        }

        public long asUInt() {
            int i = this.e;
            if (i == 2) {
                return FlexBuffers.n(this.f1272a, this.b, this.c);
            }
            if (i != 1) {
                if (i != 3) {
                    if (i != 10) {
                        if (i != 26) {
                            if (i != 5) {
                                if (i == 6) {
                                    androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                                    return FlexBuffers.m(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
                                } else if (i == 7) {
                                    androidx.emoji2.text.flatbuffer.a aVar2 = this.f1272a;
                                    return FlexBuffers.n(aVar2, FlexBuffers.g(aVar2, this.b, this.c), this.d);
                                } else if (i != 8) {
                                    return 0L;
                                } else {
                                    androidx.emoji2.text.flatbuffer.a aVar3 = this.f1272a;
                                    return (long) FlexBuffers.k(aVar3, FlexBuffers.g(aVar3, this.b, this.c), this.c);
                                }
                            }
                            return Long.parseLong(asString());
                        }
                        return FlexBuffers.l(this.f1272a, this.b, this.c);
                    }
                    return asVector().size();
                }
                return (long) FlexBuffers.k(this.f1272a, this.b, this.c);
            }
            return FlexBuffers.m(this.f1272a, this.b, this.c);
        }

        public Vector asVector() {
            if (isVector()) {
                androidx.emoji2.text.flatbuffer.a aVar = this.f1272a;
                return new Vector(aVar, FlexBuffers.g(aVar, this.b, this.c), this.d);
            }
            int i = this.e;
            if (i == 15) {
                androidx.emoji2.text.flatbuffer.a aVar2 = this.f1272a;
                return new TypedVector(aVar2, FlexBuffers.g(aVar2, this.b, this.c), this.d, 4);
            } else if (FlexBuffers.i(i)) {
                androidx.emoji2.text.flatbuffer.a aVar3 = this.f1272a;
                return new TypedVector(aVar3, FlexBuffers.g(aVar3, this.b, this.c), this.d, FlexBuffers.p(this.e));
            } else {
                return Vector.empty();
            }
        }

        public StringBuilder b(StringBuilder sb) {
            int i = this.e;
            if (i != 36) {
                switch (i) {
                    case 0:
                        sb.append("null");
                        return sb;
                    case 1:
                    case 6:
                        sb.append(asLong());
                        return sb;
                    case 2:
                    case 7:
                        sb.append(asUInt());
                        return sb;
                    case 3:
                    case 8:
                        sb.append(asFloat());
                        return sb;
                    case 4:
                        Key asKey = asKey();
                        sb.append(Typography.quote);
                        StringBuilder key = asKey.toString(sb);
                        key.append(Typography.quote);
                        return key;
                    case 5:
                        sb.append(Typography.quote);
                        sb.append(asString());
                        sb.append(Typography.quote);
                        return sb;
                    case 9:
                        return asMap().toString(sb);
                    case 10:
                        return asVector().toString(sb);
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        break;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        throw new FlexBufferException("not_implemented:" + this.e);
                    case 25:
                        return asBlob().toString(sb);
                    case 26:
                        sb.append(asBoolean());
                        return sb;
                    default:
                        return sb;
                }
            }
            sb.append(asVector());
            return sb;
        }

        public int getType() {
            return this.e;
        }

        public boolean isBlob() {
            return this.e == 25;
        }

        public boolean isBoolean() {
            return this.e == 26;
        }

        public boolean isFloat() {
            int i = this.e;
            return i == 3 || i == 8;
        }

        public boolean isInt() {
            int i = this.e;
            return i == 1 || i == 6;
        }

        public boolean isIntOrUInt() {
            return isInt() || isUInt();
        }

        public boolean isKey() {
            return this.e == 4;
        }

        public boolean isMap() {
            return this.e == 9;
        }

        public boolean isNull() {
            return this.e == 0;
        }

        public boolean isNumeric() {
            return isIntOrUInt() || isFloat();
        }

        public boolean isString() {
            return this.e == 5;
        }

        public boolean isTypedVector() {
            return FlexBuffers.i(this.e);
        }

        public boolean isUInt() {
            int i = this.e;
            return i == 2 || i == 7;
        }

        public boolean isVector() {
            int i = this.e;
            return i == 10 || i == 9;
        }

        public String toString() {
            return b(new StringBuilder(128)).toString();
        }

        public Reference(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2, int i3, int i4) {
            this.f1272a = aVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    /* loaded from: classes.dex */
    public static class TypedVector extends Vector {
        public static final TypedVector f = new TypedVector(FlexBuffers.f1270a, 1, 1, 1);
        public final int e;

        public TypedVector(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2, int i3) {
            super(aVar, i, i2);
            this.e = i3;
        }

        public static TypedVector empty() {
            return f;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector
        public Reference get(int i) {
            if (i >= size()) {
                return Reference.f;
            }
            return new Reference(this.f1273a, this.b + (i * this.c), this.c, 1, this.e);
        }

        public int getElemType() {
            return this.e;
        }

        public boolean isEmptyVector() {
            return this == f;
        }
    }

    /* loaded from: classes.dex */
    public static class Vector extends b {
        public static final Vector d = new Vector(FlexBuffers.f1270a, 1, 1);

        public Vector(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
            super(aVar, i, i2);
        }

        public static Vector empty() {
            return d;
        }

        public Reference get(int i) {
            long size = size();
            long j = i;
            if (j >= size) {
                return Reference.f;
            }
            return new Reference(this.f1273a, this.b + (i * this.c), this.c, c.a(this.f1273a.get((int) (this.b + (size * this.c) + j))));
        }

        public boolean isEmpty() {
            return this == d;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.b
        public /* bridge */ /* synthetic */ int size() {
            return super.size();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.a
        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.a
        public StringBuilder toString(StringBuilder sb) {
            sb.append("[ ");
            int size = size();
            for (int i = 0; i < size; i++) {
                get(i).b(sb);
                if (i != size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            return sb;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class a {

        /* renamed from: a  reason: collision with root package name */
        public androidx.emoji2.text.flatbuffer.a f1273a;
        public int b;
        public int c;

        public a(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
            this.f1273a = aVar;
            this.b = i;
            this.c = i2;
        }

        public String toString() {
            return toString(new StringBuilder(128)).toString();
        }

        public abstract StringBuilder toString(StringBuilder sb);
    }

    /* loaded from: classes.dex */
    public static abstract class b extends a {
        public final int size;

        public b(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
            super(aVar, i, i2);
            this.size = FlexBuffers.l(this.f1273a, i - i2, i2);
        }

        public int size() {
            return this.size;
        }
    }

    /* loaded from: classes.dex */
    public static class c {
        public static int a(byte b) {
            return b & 255;
        }

        public static long b(int i) {
            return i & 4294967295L;
        }

        public static int c(short s) {
            return s & UShort.MAX_VALUE;
        }
    }

    public static int g(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
        return (int) (i - n(aVar, i, i2));
    }

    @Deprecated
    public static Reference getRoot(ByteBuffer byteBuffer) {
        return getRoot(byteBuffer.hasArray() ? new ArrayReadWriteBuf(byteBuffer.array(), byteBuffer.limit()) : new ByteBufferReadWriteBuf(byteBuffer));
    }

    public static boolean h(int i) {
        return i <= 3 || i == 26;
    }

    public static boolean i(int i) {
        return (i >= 11 && i <= 15) || i == 36;
    }

    public static boolean j(int i) {
        return (i >= 1 && i <= 4) || i == 26;
    }

    public static double k(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
        if (i2 != 4) {
            if (i2 != 8) {
                return -1.0d;
            }
            return aVar.getDouble(i);
        }
        return aVar.getFloat(i);
    }

    public static int l(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
        return (int) m(aVar, i, i2);
    }

    public static long m(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
        int i3;
        if (i2 == 1) {
            i3 = aVar.get(i);
        } else if (i2 == 2) {
            i3 = aVar.getShort(i);
        } else if (i2 != 4) {
            if (i2 != 8) {
                return -1L;
            }
            return aVar.getLong(i);
        } else {
            i3 = aVar.getInt(i);
        }
        return i3;
    }

    public static long n(androidx.emoji2.text.flatbuffer.a aVar, int i, int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 4) {
                    if (i2 != 8) {
                        return -1L;
                    }
                    return aVar.getLong(i);
                }
                return c.b(aVar.getInt(i));
            }
            return c.c(aVar.getShort(i));
        }
        return c.a(aVar.get(i));
    }

    public static int o(int i, int i2) {
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return 0;
                    }
                    return (i - 1) + 22;
                }
                return (i - 1) + 19;
            }
            return (i - 1) + 16;
        }
        return (i - 1) + 11;
    }

    public static int p(int i) {
        return (i - 11) + 1;
    }

    public static Reference getRoot(androidx.emoji2.text.flatbuffer.a aVar) {
        int limit = aVar.limit() - 1;
        byte b2 = aVar.get(limit);
        int i = limit - 1;
        return new Reference(aVar, i - b2, b2, c.a(aVar.get(i)));
    }
}
