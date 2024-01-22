package com.google.android.gms.internal.mlkit_code_scanner;

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
/* loaded from: classes8.dex */
public final class d implements ObjectEncoderContext {
    public static final Charset f = Charset.forName("UTF-8");
    public static final FieldDescriptor g;
    public static final FieldDescriptor h;
    public static final ObjectEncoder i;

    /* renamed from: a  reason: collision with root package name */
    public OutputStream f8986a;
    public final Map b;
    public final Map c;
    public final ObjectEncoder d;
    public final e e = new e(this);

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(Constants.KEY_KEY);
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        g = builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("value");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        h = builder2.withProperty(zzadVar2.zzb()).build();
        i = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzai
            @Override // com.google.firebase.encoders.ObjectEncoder
            public final void encode(Object obj, Object obj2) {
                d.g((Map.Entry) obj, (ObjectEncoderContext) obj2);
            }
        };
    }

    public d(OutputStream outputStream, Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f8986a = outputStream;
        this.b = map;
        this.c = map2;
        this.d = objectEncoder;
    }

    public static /* synthetic */ void g(Map.Entry entry, ObjectEncoderContext objectEncoderContext) throws IOException {
        objectEncoderContext.add(g, entry.getKey());
        objectEncoderContext.add(h, entry.getValue());
    }

    public static int h(FieldDescriptor fieldDescriptor) {
        zzah zzahVar = (zzah) fieldDescriptor.getProperty(zzah.class);
        if (zzahVar != null) {
            return zzahVar.zza();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    public static zzah j(FieldDescriptor fieldDescriptor) {
        zzah zzahVar = (zzah) fieldDescriptor.getProperty(zzah.class);
        if (zzahVar != null) {
            return zzahVar;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    public static ByteBuffer m(int i2) {
        return ByteBuffer.allocate(i2).order(ByteOrder.LITTLE_ENDIAN);
    }

    public final ObjectEncoderContext a(@NonNull FieldDescriptor fieldDescriptor, double d, boolean z) throws IOException {
        if (z && d == 0.0d) {
            return this;
        }
        n((h(fieldDescriptor) << 3) | 1);
        this.f8986a.write(m(8).putDouble(d).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d) throws IOException {
        a(fieldDescriptor, d, true);
        return this;
    }

    public final ObjectEncoderContext b(@NonNull FieldDescriptor fieldDescriptor, float f2, boolean z) throws IOException {
        if (z && f2 == 0.0f) {
            return this;
        }
        n((h(fieldDescriptor) << 3) | 5);
        this.f8986a.write(m(4).putFloat(f2).array());
        return this;
    }

    public final ObjectEncoderContext c(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj, boolean z) throws IOException {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z && charSequence.length() == 0) {
                return this;
            }
            n((h(fieldDescriptor) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(f);
            n(bytes.length);
            this.f8986a.write(bytes);
            return this;
        } else if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                c(fieldDescriptor, obj2, false);
            }
            return this;
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                k(i, fieldDescriptor, entry, false);
            }
            return this;
        } else if (obj instanceof Double) {
            a(fieldDescriptor, ((Double) obj).doubleValue(), z);
            return this;
        } else if (obj instanceof Float) {
            b(fieldDescriptor, ((Float) obj).floatValue(), z);
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
            n((h(fieldDescriptor) << 3) | 2);
            n(bArr.length);
            this.f8986a.write(bArr);
            return this;
        } else {
            ObjectEncoder objectEncoder = (ObjectEncoder) this.b.get(obj.getClass());
            if (objectEncoder != null) {
                k(objectEncoder, fieldDescriptor, obj, z);
                return this;
            }
            ValueEncoder valueEncoder = (ValueEncoder) this.c.get(obj.getClass());
            if (valueEncoder != null) {
                l(valueEncoder, fieldDescriptor, obj, z);
                return this;
            } else if (obj instanceof zzaf) {
                d(fieldDescriptor, ((zzaf) obj).zza(), true);
                return this;
            } else if (obj instanceof Enum) {
                d(fieldDescriptor, ((Enum) obj).ordinal(), true);
                return this;
            } else {
                k(this.d, fieldDescriptor, obj, z);
                return this;
            }
        }
    }

    public final d d(@NonNull FieldDescriptor fieldDescriptor, int i2, boolean z) throws IOException {
        if (z && i2 == 0) {
            return this;
        }
        zzah j = j(fieldDescriptor);
        zzag zzagVar = zzag.DEFAULT;
        int ordinal = j.zzb().ordinal();
        if (ordinal == 0) {
            n(j.zza() << 3);
            n(i2);
        } else if (ordinal == 1) {
            n(j.zza() << 3);
            n((i2 + i2) ^ (i2 >> 31));
        } else if (ordinal == 2) {
            n((j.zza() << 3) | 5);
            this.f8986a.write(m(4).putInt(i2).array());
        }
        return this;
    }

    public final d e(@NonNull FieldDescriptor fieldDescriptor, long j, boolean z) throws IOException {
        if (z && j == 0) {
            return this;
        }
        zzah j2 = j(fieldDescriptor);
        zzag zzagVar = zzag.DEFAULT;
        int ordinal = j2.zzb().ordinal();
        if (ordinal == 0) {
            n(j2.zza() << 3);
            o(j);
        } else if (ordinal == 1) {
            n(j2.zza() << 3);
            o((j >> 63) ^ (j + j));
        } else if (ordinal == 2) {
            n((j2.zza() << 3) | 1);
            this.f8986a.write(m(8).putLong(j).array());
        }
        return this;
    }

    public final d f(@Nullable Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.b.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for ".concat(String.valueOf(obj.getClass())));
    }

    public final long i(ObjectEncoder objectEncoder, Object obj) throws IOException {
        c cVar = new c();
        try {
            OutputStream outputStream = this.f8986a;
            this.f8986a = cVar;
            objectEncoder.encode(obj, this);
            this.f8986a = outputStream;
            long a2 = cVar.a();
            cVar.close();
            return a2;
        } catch (Throwable th) {
            try {
                cVar.close();
            } catch (Throwable th2) {
                try {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                } catch (Exception unused) {
                }
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

    public final d k(ObjectEncoder objectEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) throws IOException {
        long i2 = i(objectEncoder, obj);
        if (z && i2 == 0) {
            return this;
        }
        n((h(fieldDescriptor) << 3) | 2);
        o(i2);
        objectEncoder.encode(obj, this);
        return this;
    }

    public final d l(ValueEncoder valueEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) throws IOException {
        this.e.a(fieldDescriptor, z);
        valueEncoder.encode(obj, this.e);
        return this;
    }

    public final void n(int i2) throws IOException {
        while ((i2 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) != 0) {
            this.f8986a.write((i2 & 127) | 128);
            i2 >>>= 7;
        }
        this.f8986a.write(i2 & 127);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    public final void o(long j) throws IOException {
        while (((-128) & j) != 0) {
            this.f8986a.write((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.f8986a.write(((int) j) & 127);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f2) throws IOException {
        b(fieldDescriptor, f2, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext nested(@NonNull String str) throws IOException {
        return nested(FieldDescriptor.of(str));
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i2) throws IOException {
        d(fieldDescriptor, i2, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j) throws IOException {
        e(fieldDescriptor, j, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException {
        c(fieldDescriptor, obj, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final /* synthetic */ ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z) throws IOException {
        d(fieldDescriptor, z ? 1 : 0, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, double d) throws IOException {
        a(FieldDescriptor.of(str), d, true);
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
        c(FieldDescriptor.of(str), obj, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public final ObjectEncoderContext add(@NonNull String str, boolean z) throws IOException {
        d(FieldDescriptor.of(str), z ? 1 : 0, true);
        return this;
    }
}
