package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f11520a = Logger.getLogger(a.class.getName());
    public static final Map<Integer, Phonemetadata.PhoneMetadata> b = Collections.synchronizedMap(new HashMap());
    public static final Map<String, Phonemetadata.PhoneMetadata> c = Collections.synchronizedMap(new HashMap());
    public static final Set<Integer> d = AlternateFormatsCountryCodeSet.a();
    public static final Set<String> e = ShortNumbersRegionCodeSet.a();

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                f11520a.log(Level.WARNING, e2.toString());
            }
        }
    }

    public static Phonemetadata.PhoneMetadata b(int i) {
        if (d.contains(Integer.valueOf(i))) {
            Map<Integer, Phonemetadata.PhoneMetadata> map = b;
            synchronized (map) {
                if (!map.containsKey(Integer.valueOf(i))) {
                    e(i);
                }
            }
            return map.get(Integer.valueOf(i));
        }
        return null;
    }

    public static Phonemetadata.PhoneMetadata c(String str) {
        if (e.contains(str)) {
            Map<String, Phonemetadata.PhoneMetadata> map = c;
            synchronized (map) {
                if (!map.containsKey(str)) {
                    f(str);
                }
            }
            return map.get(str);
        }
        return null;
    }

    public static Set<String> d() {
        return e;
    }

    public static void e(int i) {
        ObjectInputStream objectInputStream;
        StringBuilder sb = new StringBuilder("/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto_".length() + 11);
        sb.append("/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto_");
        sb.append(i);
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(b.class.getResourceAsStream(sb.toString()));
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            Phonemetadata.PhoneMetadataCollection phoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
            phoneMetadataCollection.readExternal(objectInputStream);
            for (Phonemetadata.PhoneMetadata phoneMetadata : phoneMetadataCollection.getMetadataList()) {
                b.put(Integer.valueOf(phoneMetadata.getCountryCode()), phoneMetadata);
            }
            a(objectInputStream);
        } catch (IOException e3) {
            e = e3;
            objectInputStream2 = objectInputStream;
            f11520a.log(Level.WARNING, e.toString());
            a(objectInputStream2);
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            a(objectInputStream2);
            throw th;
        }
    }

    public static void f(String str) {
        ObjectInputStream objectInputStream;
        String valueOf = String.valueOf(str);
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(b.class.getResourceAsStream(valueOf.length() != 0 ? "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto_".concat(valueOf) : new String("/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto_")));
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            Phonemetadata.PhoneMetadataCollection phoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
            phoneMetadataCollection.readExternal(objectInputStream);
            for (Phonemetadata.PhoneMetadata phoneMetadata : phoneMetadataCollection.getMetadataList()) {
                c.put(str, phoneMetadata);
            }
            a(objectInputStream);
        } catch (IOException e3) {
            e = e3;
            objectInputStream2 = objectInputStream;
            f11520a.log(Level.WARNING, e.toString());
            a(objectInputStream2);
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            a(objectInputStream2);
            throw th;
        }
    }
}
