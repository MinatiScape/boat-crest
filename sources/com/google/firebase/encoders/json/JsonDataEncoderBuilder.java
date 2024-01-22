package com.google.firebase.encoders.json;

import androidx.annotation.NonNull;
import com.coveiot.android.tappy.utils.Constants;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
/* loaded from: classes10.dex */
public final class JsonDataEncoderBuilder implements EncoderConfig<JsonDataEncoderBuilder> {
    public static final ObjectEncoder<Object> e = new ObjectEncoder() { // from class: com.google.firebase.encoders.json.a
        @Override // com.google.firebase.encoders.ObjectEncoder
        public final void encode(Object obj, Object obj2) {
            JsonDataEncoderBuilder.h(obj, (ObjectEncoderContext) obj2);
        }
    };
    public static final ValueEncoder<String> f = new ValueEncoder() { // from class: com.google.firebase.encoders.json.c
        @Override // com.google.firebase.encoders.ValueEncoder
        public final void encode(Object obj, Object obj2) {
            ((ValueEncoderContext) obj2).add((String) obj);
        }
    };
    public static final ValueEncoder<Boolean> g = new ValueEncoder() { // from class: com.google.firebase.encoders.json.b
        @Override // com.google.firebase.encoders.ValueEncoder
        public final void encode(Object obj, Object obj2) {
            JsonDataEncoderBuilder.j((Boolean) obj, (ValueEncoderContext) obj2);
        }
    };
    public static final b h = new b(null);

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, ObjectEncoder<?>> f11270a = new HashMap();
    public final Map<Class<?>, ValueEncoder<?>> b = new HashMap();
    public ObjectEncoder<Object> c = e;
    public boolean d = false;

    /* loaded from: classes10.dex */
    public static final class b implements ValueEncoder<Date> {

        /* renamed from: a  reason: collision with root package name */
        public static final DateFormat f11272a;

        static {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);
            f11272a = simpleDateFormat;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        public b() {
        }

        @Override // com.google.firebase.encoders.ValueEncoder
        /* renamed from: a */
        public void encode(@NonNull Date date, @NonNull ValueEncoderContext valueEncoderContext) throws IOException {
            valueEncoderContext.add(f11272a.format(date));
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    public JsonDataEncoderBuilder() {
        registerEncoder(String.class, (ValueEncoder) f);
        registerEncoder(Boolean.class, (ValueEncoder) g);
        registerEncoder(Date.class, (ValueEncoder) h);
    }

    public static /* synthetic */ void h(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
    }

    public static /* synthetic */ void j(Boolean bool, ValueEncoderContext valueEncoderContext) throws IOException {
        valueEncoderContext.add(bool.booleanValue());
    }

    @NonNull
    public DataEncoder build() {
        return new a();
    }

    @NonNull
    public JsonDataEncoderBuilder configureWith(@NonNull Configurator configurator) {
        configurator.configure(this);
        return this;
    }

    @NonNull
    public JsonDataEncoderBuilder ignoreNullValues(boolean z) {
        this.d = z;
        return this;
    }

    @NonNull
    public JsonDataEncoderBuilder registerFallbackEncoder(@NonNull ObjectEncoder<Object> objectEncoder) {
        this.c = objectEncoder;
        return this;
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public <T> JsonDataEncoderBuilder registerEncoder(@NonNull Class<T> cls, @NonNull ObjectEncoder<? super T> objectEncoder) {
        this.f11270a.put(cls, objectEncoder);
        this.b.remove(cls);
        return this;
    }

    /* loaded from: classes10.dex */
    public class a implements DataEncoder {
        public a() {
        }

        @Override // com.google.firebase.encoders.DataEncoder
        public void encode(@NonNull Object obj, @NonNull Writer writer) throws IOException {
            d dVar = new d(writer, JsonDataEncoderBuilder.this.f11270a, JsonDataEncoderBuilder.this.b, JsonDataEncoderBuilder.this.c, JsonDataEncoderBuilder.this.d);
            dVar.e(obj, false);
            dVar.o();
        }

        @Override // com.google.firebase.encoders.DataEncoder
        public String encode(@NonNull Object obj) {
            StringWriter stringWriter = new StringWriter();
            try {
                encode(obj, stringWriter);
            } catch (IOException unused) {
            }
            return stringWriter.toString();
        }
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public <T> JsonDataEncoderBuilder registerEncoder(@NonNull Class<T> cls, @NonNull ValueEncoder<? super T> valueEncoder) {
        this.b.put(cls, valueEncoder);
        this.f11270a.remove(cls);
        return this;
    }
}
