package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
/* loaded from: classes10.dex */
public final class d implements ObjectEncoderContext, ValueEncoderContext {

    /* renamed from: a  reason: collision with root package name */
    public d f11276a = null;
    public boolean b = true;
    public final JsonWriter c;
    public final Map<Class<?>, ObjectEncoder<?>> d;
    public final Map<Class<?>, ValueEncoder<?>> e;
    public final ObjectEncoder<Object> f;
    public final boolean g;

    public d(@NonNull Writer writer, @NonNull Map<Class<?>, ObjectEncoder<?>> map, @NonNull Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder, boolean z) {
        this.c = new JsonWriter(writer);
        this.d = map;
        this.e = map2;
        this.f = objectEncoder;
        this.g = z;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: a */
    public d add(double d) throws IOException {
        s();
        this.c.value(d);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: b */
    public d add(float f) throws IOException {
        s();
        this.c.value(f);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: c */
    public d add(int i) throws IOException {
        s();
        this.c.value(i);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: d */
    public d add(long j) throws IOException {
        s();
        this.c.value(j);
        return this;
    }

    @NonNull
    public d e(@Nullable Object obj, boolean z) throws IOException {
        int[] iArr;
        int i = 0;
        if (z && n(obj)) {
            Object[] objArr = new Object[1];
            objArr[0] = obj == null ? null : obj.getClass();
            throw new EncodingException(String.format("%s cannot be encoded inline", objArr));
        } else if (obj == null) {
            this.c.nullValue();
            return this;
        } else if (obj instanceof Number) {
            this.c.value((Number) obj);
            return this;
        } else if (obj.getClass().isArray()) {
            if (obj instanceof byte[]) {
                return add((byte[]) obj);
            }
            this.c.beginArray();
            if (obj instanceof int[]) {
                int length = ((int[]) obj).length;
                while (i < length) {
                    this.c.value(iArr[i]);
                    i++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                int length2 = jArr.length;
                while (i < length2) {
                    add(jArr[i]);
                    i++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                int length3 = dArr.length;
                while (i < length3) {
                    this.c.value(dArr[i]);
                    i++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                int length4 = zArr.length;
                while (i < length4) {
                    this.c.value(zArr[i]);
                    i++;
                }
            } else if (obj instanceof Number[]) {
                for (Number number : (Number[]) obj) {
                    e(number, false);
                }
            } else {
                for (Object obj2 : (Object[]) obj) {
                    e(obj2, false);
                }
            }
            this.c.endArray();
            return this;
        } else if (obj instanceof Collection) {
            this.c.beginArray();
            for (Object obj3 : (Collection) obj) {
                e(obj3, false);
            }
            this.c.endArray();
            return this;
        } else if (obj instanceof Map) {
            this.c.beginObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                try {
                    add((String) key, entry.getValue());
                } catch (ClassCastException e) {
                    throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e);
                }
            }
            this.c.endObject();
            return this;
        } else {
            ObjectEncoder<?> objectEncoder = this.d.get(obj.getClass());
            if (objectEncoder != null) {
                return p(objectEncoder, obj, z);
            }
            ValueEncoder<?> valueEncoder = this.e.get(obj.getClass());
            if (valueEncoder != null) {
                valueEncoder.encode(obj, this);
                return this;
            } else if (obj instanceof Enum) {
                add(((Enum) obj).name());
                return this;
            } else {
                return p(this.f, obj, z);
            }
        }
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: f */
    public d add(@Nullable String str) throws IOException {
        s();
        this.c.value(str);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: g */
    public d add(@NonNull String str, double d) throws IOException {
        s();
        this.c.name(str);
        return add(d);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: h */
    public d add(@NonNull String str, int i) throws IOException {
        s();
        this.c.name(str);
        return add(i);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: i */
    public d add(@NonNull String str, long j) throws IOException {
        s();
        this.c.name(str);
        return add(j);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext inline(@Nullable Object obj) throws IOException {
        return e(obj, true);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: j */
    public d add(@NonNull String str, @Nullable Object obj) throws IOException {
        if (this.g) {
            return r(str, obj);
        }
        return q(str, obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    /* renamed from: k */
    public d add(@NonNull String str, boolean z) throws IOException {
        s();
        this.c.name(str);
        return add(z);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: l */
    public d add(boolean z) throws IOException {
        s();
        this.c.value(z);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    @NonNull
    /* renamed from: m */
    public d add(@Nullable byte[] bArr) throws IOException {
        s();
        if (bArr == null) {
            this.c.nullValue();
        } else {
            this.c.value(Base64.encodeToString(bArr, 2));
        }
        return this;
    }

    public final boolean n(Object obj) {
        return obj == null || obj.getClass().isArray() || (obj instanceof Collection) || (obj instanceof Date) || (obj instanceof Enum) || (obj instanceof Number);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext nested(@NonNull String str) throws IOException {
        s();
        this.f11276a = new d(this);
        this.c.name(str);
        this.c.beginObject();
        return this.f11276a;
    }

    public void o() throws IOException {
        s();
        this.c.flush();
    }

    public d p(ObjectEncoder<Object> objectEncoder, Object obj, boolean z) throws IOException {
        if (!z) {
            this.c.beginObject();
        }
        objectEncoder.encode(obj, this);
        if (!z) {
            this.c.endObject();
        }
        return this;
    }

    public final d q(@NonNull String str, @Nullable Object obj) throws IOException, EncodingException {
        s();
        this.c.name(str);
        if (obj == null) {
            this.c.nullValue();
            return this;
        }
        return e(obj, false);
    }

    public final d r(@NonNull String str, @Nullable Object obj) throws IOException, EncodingException {
        if (obj == null) {
            return this;
        }
        s();
        this.c.name(str);
        return e(obj, false);
    }

    public final void s() throws IOException {
        if (this.b) {
            d dVar = this.f11276a;
            if (dVar != null) {
                dVar.s();
                this.f11276a.b = false;
                this.f11276a = null;
                this.c.endObject();
                return;
            }
            return;
        }
        throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor) throws IOException {
        return nested(fieldDescriptor.getName());
    }

    public d(d dVar) {
        this.c = dVar.c;
        this.d = dVar.d;
        this.e = dVar.e;
        this.f = dVar.f;
        this.g = dVar.g;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException {
        return add(fieldDescriptor.getName(), obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f) throws IOException {
        return add(fieldDescriptor.getName(), f);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d) throws IOException {
        return add(fieldDescriptor.getName(), d);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i) throws IOException {
        return add(fieldDescriptor.getName(), i);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j) throws IOException {
        return add(fieldDescriptor.getName(), j);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    @NonNull
    public ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z) throws IOException {
        return add(fieldDescriptor.getName(), z);
    }
}
