package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
/* loaded from: classes7.dex */
public abstract class zzia {
    public final void a(boolean z, Object obj) throws IOException {
        boolean z2;
        if (obj == null) {
            return;
        }
        Class<?> cls = obj.getClass();
        if (zzix.isNull(obj)) {
            zzgz();
        } else if (obj instanceof String) {
            writeString((String) obj);
        } else {
            boolean z3 = true;
            if (obj instanceof Number) {
                if (z) {
                    writeString(obj.toString());
                } else if (obj instanceof BigDecimal) {
                    zza((BigDecimal) obj);
                } else if (obj instanceof BigInteger) {
                    zza((BigInteger) obj);
                } else if (obj instanceof Long) {
                    zze(((Long) obj).longValue());
                } else if (obj instanceof Float) {
                    float floatValue = ((Number) obj).floatValue();
                    zzml.checkArgument((Float.isInfinite(floatValue) || Float.isNaN(floatValue)) ? false : false);
                    zzj(floatValue);
                } else if (!(obj instanceof Integer) && !(obj instanceof Short) && !(obj instanceof Byte)) {
                    double doubleValue = ((Number) obj).doubleValue();
                    zzml.checkArgument((Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) ? false : false);
                    zza(doubleValue);
                } else {
                    zzah(((Number) obj).intValue());
                }
            } else if (obj instanceof Boolean) {
                writeBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof zzje) {
                writeString(((zzje) obj).zzid());
            } else if (!(obj instanceof Iterable) && !cls.isArray()) {
                if (cls.isEnum()) {
                    String name = zzjd.zza((Enum) obj).getName();
                    if (name == null) {
                        zzgz();
                        return;
                    } else {
                        writeString(name);
                        return;
                    }
                }
                zzgx();
                boolean z4 = (obj instanceof Map) && !(obj instanceof zzjf);
                zziv zzc = z4 ? null : zziv.zzc(cls);
                for (Map.Entry<String, Object> entry : zzix.zzf(obj).entrySet()) {
                    Object value = entry.getValue();
                    if (value != null) {
                        String key = entry.getKey();
                        if (z4) {
                            z2 = z;
                        } else {
                            zzjd zzao = zzc.zzao(key);
                            Field zzia = zzao == null ? null : zzao.zzia();
                            z2 = (zzia == null || zzia.getAnnotation(zzif.class) == null) ? false : true;
                        }
                        zzan(key);
                        a(z2, value);
                    }
                }
                zzgy();
            } else {
                zzgv();
                for (Object obj2 : zzjs.zzi(obj)) {
                    a(z, obj2);
                }
                zzgw();
            }
        }
    }

    public abstract void flush() throws IOException;

    public abstract void writeBoolean(boolean z) throws IOException;

    public abstract void writeString(String str) throws IOException;

    public abstract void zza(double d) throws IOException;

    public abstract void zza(BigDecimal bigDecimal) throws IOException;

    public abstract void zza(BigInteger bigInteger) throws IOException;

    public abstract void zzah(int i) throws IOException;

    public abstract void zzan(String str) throws IOException;

    public final void zzd(Object obj) throws IOException {
        a(false, obj);
    }

    public abstract void zze(long j) throws IOException;

    public abstract void zzgv() throws IOException;

    public abstract void zzgw() throws IOException;

    public abstract void zzgx() throws IOException;

    public abstract void zzgy() throws IOException;

    public abstract void zzgz() throws IOException;

    public void zzha() throws IOException {
    }

    public abstract void zzj(float f) throws IOException;
}
