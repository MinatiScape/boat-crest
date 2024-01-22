package com.google.android.gms.internal.firebase_messaging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes7.dex */
public final class c implements ObjectEncoderContext {
    public static final Charset f = Charset.forName("UTF-8");
    public static final FieldDescriptor g;
    public static final FieldDescriptor h;
    public static final ObjectEncoder<Map.Entry<Object, Object>> i;

    /* renamed from: a  reason: collision with root package name */
    public OutputStream f8643a;
    public final Map<Class<?>, ObjectEncoder<?>> b;
    public final Map<Class<?>, ValueEncoder<?>> c;
    public final ObjectEncoder<Object> d;
    public final e e = new e(this);

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(Constants.KEY_KEY);
        zzv zzvVar = new zzv();
        zzvVar.zza(1);
        g = builder.withProperty(zzvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("value");
        zzv zzvVar2 = new zzv();
        zzvVar2.zza(2);
        h = builder2.withProperty(zzvVar2.zzb()).build();
        i = b.f8642a;
    }

    public c(OutputStream outputStream, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.f8643a = outputStream;
        this.b = map;
        this.c = map2;
        this.d = objectEncoder;
    }

    public static final /* synthetic */ void g(Map.Entry entry, ObjectEncoderContext objectEncoderContext) throws IOException {
        objectEncoderContext.add(g, entry.getKey());
        objectEncoderContext.add(h, entry.getValue());
    }

    public static ByteBuffer k(int i2) {
        return ByteBuffer.allocate(i2).order(ByteOrder.LITTLE_ENDIAN);
    }

    public static int l(FieldDescriptor fieldDescriptor) {
        zzz zzzVar = (zzz) fieldDescriptor.getProperty(zzz.class);
        if (zzzVar != null) {
            return zzzVar.zza();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    public static zzz m(FieldDescriptor fieldDescriptor) {
        zzz zzzVar = (zzz) fieldDescriptor.getProperty(zzz.class);
        if (zzzVar != null) {
            return zzzVar;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    public final ObjectEncoderContext a(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj, boolean z) throws IOException {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z && charSequence.length() == 0) {
                return this;
            }
            n((l(fieldDescriptor) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(f);
            n(bytes.length);
            this.f8643a.write(bytes);
            return this;
        } else if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                a(fieldDescriptor, obj2, false);
            }
            return this;
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                h(i, fieldDescriptor, entry, false);
            }
            return this;
        } else if (obj instanceof Double) {
            b(fieldDescriptor, ((Double) obj).doubleValue(), z);
            return this;
        } else if (obj instanceof Float) {
            c(fieldDescriptor, ((Float) obj).floatValue(), z);
            return this;
        } else if (obj instanceof Number) {
            e(fieldDescriptor, ((Number) obj).longValue(), z);
            return this;
        } else if (obj instanceof Boolean) {
            d(fieldDescriptor, ((Boolean) obj).booleanValue() ? 1 : 0, z);
            return this;
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (z && bArr.length == 0) {
                return this;
            }
            n((l(fieldDescriptor) << 3) | 2);
            n(bArr.length);
            this.f8643a.write(bArr);
            return this;
        } else {
            ObjectEncoder<?> objectEncoder = this.b.get(obj.getClass());
            if (objectEncoder != null) {
                h(objectEncoder, fieldDescriptor, obj, z);
                return this;
            }
            ValueEncoder<?> valueEncoder = this.c.get(obj.getClass());
            if (valueEncoder != null) {
                j(valueEncoder, fieldDescriptor, obj, z);
                return this;
            } else if (obj instanceof zzx) {
                d(fieldDescriptor, ((zzx) obj).getNumber(), true);
                return this;
            } else if (obj instanceof Enum) {
                d(fieldDescriptor, ((Enum) obj).ordinal(), true);
                return this;
            } else {
                h(this.d, fieldDescriptor, obj, z);
                return this;
            }
        }
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d) throws IOException {
        b(fieldDescriptor, d, true);
        return this;
    }

    public final ObjectEncoderContext b(@NonNull FieldDescriptor fieldDescriptor, double d, boolean z) throws IOException {
        if (z && d == 0.0d) {
            return this;
        }
        n((l(fieldDescriptor) << 3) | 1);
        this.f8643a.write(k(8).putDouble(d).array());
        return this;
    }

    public final ObjectEncoderContext c(@NonNull FieldDescriptor fieldDescriptor, float f2, boolean z) throws IOException {
        if (z && f2 == 0.0f) {
            return this;
        }
        n((l(fieldDescriptor) << 3) | 5);
        this.f8643a.write(k(4).putFloat(f2).array());
        return this;
    }

    public final c d(@NonNull FieldDescriptor fieldDescriptor, int i2, boolean z) throws IOException {
        if (z && i2 == 0) {
            return this;
        }
        zzz m = m(fieldDescriptor);
        zzy zzyVar = zzy.DEFAULT;
        int ordinal = m.zzb().ordinal();
        if (ordinal == 0) {
            n(m.zza() << 3);
            n(i2);
        } else if (ordinal == 1) {
            n(m.zza() << 3);
            n((i2 + i2) ^ (i2 >> 31));
        } else if (ordinal == 2) {
            n((m.zza() << 3) | 5);
            this.f8643a.write(k(4).putInt(i2).array());
        }
        return this;
    }

    public final c e(@NonNull FieldDescriptor fieldDescriptor, long j, boolean z) throws IOException {
        if (z && j == 0) {
            return this;
        }
        zzz m = m(fieldDescriptor);
        zzy zzyVar = zzy.DEFAULT;
        int ordinal = m.zzb().ordinal();
        if (ordinal == 0) {
            n(m.zza() << 3);
            o(j);
        } else if (ordinal == 1) {
            n(m.zza() << 3);
            o((j >> 63) ^ (j + j));
        } else if (ordinal == 2) {
            n((m.zza() << 3) | 1);
            this.f8643a.write(k(8).putLong(j).array());
        }
        return this;
    }

    public final c f(@Nullable Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.b.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        String valueOf = String.valueOf(obj.getClass());
        StringBuilder sb = new StringBuilder(valueOf.length() + 15);
        sb.append("No encoder for ");
        sb.append(valueOf);
        throw new EncodingException(sb.toString());
    }

    public final <T> c h(ObjectEncoder<T> objectEncoder, FieldDescriptor fieldDescriptor, T t, boolean z) throws IOException {
        long i2 = i(objectEncoder, t);
        if (z && i2 == 0) {
            return this;
        }
        n((l(fieldDescriptor) << 3) | 2);
        o(i2);
        objectEncoder.encode(t, this);
        return this;
    }

    public final <T> long i(ObjectEncoder<T> objectEncoder, T t) throws IOException {
        s sVar = new s();
        try {
            OutputStream outputStream = this.f8643a;
            this.f8643a = sVar;
            objectEncoder.encode(t, this);
            this.f8643a = outputStream;
            long a2 = sVar.a();
            sVar.close();
            return a2;
        } catch (Throwable th) {
            try {
                sVar.close();
            } catch (Throwable th2) {
                zzt.zza(th, th2);
            }
            throw th;
        }
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext inline(@Nullable Object obj) throws IOException {
        f(obj);
        return this;
    }

    public final <T> c j(ValueEncoder<T> valueEncoder, FieldDescriptor fieldDescriptor, T t, boolean z) throws IOException {
        this.e.a(fieldDescriptor, z);
        valueEncoder.encode(t, this.e);
        return this;
    }

    public final void n(int i2) throws IOException {
        while ((i2 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) != 0) {
            this.f8643a.write((i2 & 127) | 128);
            i2 >>>= 7;
        }
        this.f8643a.write(i2 & 127);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    public final void o(long j) throws IOException {
        while (((-128) & j) != 0) {
            this.f8643a.write((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.f8643a.write(((int) j) & 127);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f2) throws IOException {
        c(fieldDescriptor, f2, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext nested(@NonNull String str) throws IOException {
        return nested(FieldDescriptor.of(str));
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final /* bridge */ /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i2) throws IOException {
        d(fieldDescriptor, i2, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final /* bridge */ /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j) throws IOException {
        e(fieldDescriptor, j, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException {
        a(fieldDescriptor, obj, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final /* bridge */ /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z) throws IOException {
        d(fieldDescriptor, z ? 1 : 0, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, double d) throws IOException {
        b(FieldDescriptor.of(str), d, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, int i2) throws IOException {
        d(FieldDescriptor.of(str), i2, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, long j) throws IOException {
        e(FieldDescriptor.of(str), j, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, @Nullable Object obj) throws IOException {
        a(FieldDescriptor.of(str), obj, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, boolean z) throws IOException {
        d(FieldDescriptor.of(str), z ? 1 : 0, true);
        return this;
    }
}
