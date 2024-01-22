package com.google.android.gms.internal.firebase_ml;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzho extends zzgq {
    public Object c;

    public zzho(Object obj) {
        super(zzhn.MEDIA_TYPE);
        this.c = zzml.checkNotNull(obj);
    }

    public static boolean a(boolean z, Writer writer, String str, Object obj) throws IOException {
        if (obj != null && !zzix.isNull(obj)) {
            if (z) {
                z = false;
            } else {
                writer.write("&");
            }
            writer.write(str);
            String zzaq = zzjw.zzaq(obj instanceof Enum ? zzjd.zza((Enum) obj).getName() : obj.toString());
            if (zzaq.length() != 0) {
                writer.write("=");
                writer.write(zzaq);
            }
        }
        return z;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjq
    public final void writeTo(OutputStream outputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, zzfs()));
        boolean z = true;
        for (Map.Entry<String, Object> entry : zzix.zzf(this.c).entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                String zzaq = zzjw.zzaq(entry.getKey());
                Class<?> cls = value.getClass();
                if (!(value instanceof Iterable) && !cls.isArray()) {
                    z = a(z, bufferedWriter, zzaq, value);
                } else {
                    for (Object obj : zzjs.zzi(value)) {
                        z = a(z, bufferedWriter, zzaq, obj);
                    }
                }
            }
        }
        bufferedWriter.flush();
    }
}
