package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzhn implements zzjm {
    public static final String MEDIA_TYPE = new zzgz("application/x-www-form-urlencoded").zza(zziw.UTF_8).zzft();

    public static Object a(Type type, List<Type> list, String str) {
        return zzix.zza(zzix.zza(list, type), str);
    }

    public static void zze(String str, Object obj) {
        if (str == null) {
            return;
        }
        try {
            StringReader stringReader = new StringReader(str);
            Class<?> cls = obj.getClass();
            zziv zzc = zziv.zzc(cls);
            List asList = Arrays.asList(cls);
            zzjf zzjfVar = zzjf.class.isAssignableFrom(cls) ? (zzjf) obj : null;
            Map map = Map.class.isAssignableFrom(cls) ? (Map) obj : null;
            zzis zzisVar = new zzis(obj);
            StringWriter stringWriter = new StringWriter();
            StringWriter stringWriter2 = new StringWriter();
            boolean z = true;
            while (true) {
                int read = stringReader.read();
                if (read == -1 || read == 38) {
                    String zzar = zzjw.zzar(stringWriter.toString());
                    if (zzar.length() != 0) {
                        String zzar2 = zzjw.zzar(stringWriter2.toString());
                        zzjd zzao = zzc.zzao(zzar);
                        if (zzao != null) {
                            Type zza = zzix.zza((List<Type>) asList, zzao.getGenericType());
                            if (zzjs.zzc(zza)) {
                                Class<?> zzb = zzjs.zzb(asList, zzjs.zzd(zza));
                                zzisVar.zza(zzao.zzia(), zzb, a(zzb, asList, zzar2));
                            } else if (zzjs.zza(zzjs.zzb(asList, zza), Iterable.class)) {
                                Collection<Object> collection = (Collection) zzao.zzh(obj);
                                if (collection == null) {
                                    collection = zzix.zzb(zza);
                                    zzao.zzb(obj, collection);
                                }
                                collection.add(a(zza == Object.class ? null : zzjs.zze(zza), asList, zzar2));
                            } else {
                                zzao.zzb(obj, a(zza, asList, zzar2));
                            }
                        } else if (map != null) {
                            ArrayList arrayList = (ArrayList) map.get(zzar);
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                if (zzjfVar != null) {
                                    zzjfVar.zzb(zzar, arrayList);
                                } else {
                                    map.put(zzar, arrayList);
                                }
                            }
                            arrayList.add(zzar2);
                        }
                    }
                    StringWriter stringWriter3 = new StringWriter();
                    StringWriter stringWriter4 = new StringWriter();
                    if (read == -1) {
                        zzisVar.zzhw();
                        return;
                    }
                    stringWriter2 = stringWriter4;
                    z = true;
                    stringWriter = stringWriter3;
                } else if (read != 61) {
                    if (z) {
                        stringWriter.write(read);
                    } else {
                        stringWriter2.write(read);
                    }
                } else if (z) {
                    z = false;
                } else {
                    stringWriter2.write(read);
                }
            }
        } catch (IOException e) {
            throw zzmv.zza(e);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjm
    public final <T> T zza(InputStream inputStream, Charset charset, Class<T> cls) throws IOException {
        throw new NoSuchMethodError();
    }
}
