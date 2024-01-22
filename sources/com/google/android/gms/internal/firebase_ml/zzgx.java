package com.google.android.gms.internal.firebase_ml;

import com.facebook.stetho.inspector.network.DecompressionHelper;
import com.google.android.gms.internal.firebase_ml.zzjf;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes7.dex */
public final class zzgx extends zzjf {
    @zzjg(HttpHeaders.ACCEPT)
    private List<String> accept;
    @zzjg(HttpHeaders.ACCEPT_ENCODING)
    private List<String> acceptEncoding;
    @zzjg("Age")
    private List<Long> age;
    @zzjg(HttpHeaders.WWW_AUTHENTICATE)
    private List<String> authenticate;
    @zzjg("Authorization")
    private List<String> authorization;
    @zzjg(HttpHeaders.CACHE_CONTROL)
    private List<String> cacheControl;
    @zzjg(HttpHeaders.CONTENT_ENCODING)
    private List<String> contentEncoding;
    @zzjg("Content-Length")
    private List<Long> contentLength;
    @zzjg(HttpHeaders.CONTENT_MD5)
    private List<String> contentMD5;
    @zzjg(HttpHeaders.CONTENT_RANGE)
    private List<String> contentRange;
    @zzjg("Content-Type")
    private List<String> contentType;
    @zzjg(HttpHeaders.COOKIE)
    private List<String> cookie;
    @zzjg(HttpHeaders.DATE)
    private List<String> date;
    @zzjg(HttpHeaders.ETAG)
    private List<String> etag;
    @zzjg(HttpHeaders.EXPIRES)
    private List<String> expires;
    @zzjg(HttpHeaders.IF_MATCH)
    private List<String> ifMatch;
    @zzjg(HttpHeaders.IF_MODIFIED_SINCE)
    private List<String> ifModifiedSince;
    @zzjg(HttpHeaders.IF_NONE_MATCH)
    private List<String> ifNoneMatch;
    @zzjg(HttpHeaders.IF_RANGE)
    private List<String> ifRange;
    @zzjg(HttpHeaders.IF_UNMODIFIED_SINCE)
    private List<String> ifUnmodifiedSince;
    @zzjg(HttpHeaders.LAST_MODIFIED)
    private List<String> lastModified;
    @zzjg(HttpHeaders.LOCATION)
    private List<String> location;
    @zzjg("MIME-Version")
    private List<String> mimeVersion;
    @zzjg(HttpHeaders.RANGE)
    private List<String> range;
    @zzjg(HttpHeaders.RETRY_AFTER)
    private List<String> retryAfter;
    @zzjg(HttpHeaders.USER_AGENT)
    private List<String> userAgent;

    public zzgx() {
        super(EnumSet.of(zzjf.zzb.IGNORE_CASE));
        this.acceptEncoding = new ArrayList(Collections.singleton(DecompressionHelper.GZIP_ENCODING));
    }

    public static Object a(Type type, List<Type> list, String str) {
        return zzix.zza(zzix.zza(list, type), str);
    }

    public static <T> T b(List<T> list) {
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    public static <T> List<T> d(T t) {
        if (t == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        return arrayList;
    }

    public static void e(zzgx zzgxVar, StringBuilder sb, StringBuilder sb2, Logger logger, zzhk zzhkVar) throws IOException {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, Object> entry : zzgxVar.entrySet()) {
            String key = entry.getKey();
            Object[] objArr = {key};
            if (hashSet.add(key)) {
                Object value = entry.getValue();
                if (value != null) {
                    zzjd zzao = zzgxVar.zzie().zzao(key);
                    if (zzao != null) {
                        key = zzao.getName();
                    }
                    String str = key;
                    Class<?> cls = value.getClass();
                    if (!(value instanceof Iterable) && !cls.isArray()) {
                        f(logger, sb, sb2, zzhkVar, str, value, null);
                    } else {
                        for (Object obj : zzjs.zzi(value)) {
                            f(logger, sb, sb2, zzhkVar, str, obj, null);
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException(zzms.zzb("multiple headers of the same name (headers are case insensitive): %s", objArr));
            }
        }
    }

    public static void f(Logger logger, StringBuilder sb, StringBuilder sb2, zzhk zzhkVar, String str, Object obj, Writer writer) throws IOException {
        String obj2;
        if (obj == null || zzix.isNull(obj)) {
            return;
        }
        if (obj instanceof Enum) {
            obj2 = zzjd.zza((Enum) obj).getName();
        } else {
            obj2 = obj.toString();
        }
        String str2 = (("Authorization".equalsIgnoreCase(str) || HttpHeaders.COOKIE.equalsIgnoreCase(str)) && (logger == null || !logger.isLoggable(Level.ALL))) ? "<Not Logged>" : obj2;
        if (sb != null) {
            sb.append(str);
            sb.append(": ");
            sb.append(str2);
            sb.append(zzjt.zzaig);
        }
        if (sb2 != null) {
            sb2.append(" -H '");
            sb2.append(str);
            sb2.append(": ");
            sb2.append(str2);
            sb2.append("'");
        }
        if (zzhkVar != null) {
            zzhkVar.addHeader(str, obj2);
        }
        if (writer != null) {
            writer.write(str);
            writer.write(": ");
            writer.write(obj2);
            writer.write("\r\n");
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzgx) super.clone();
    }

    public final String getContentType() {
        return (String) b(this.contentType);
    }

    public final String getLocation() {
        return (String) b(this.location);
    }

    public final String getUserAgent() {
        return (String) b(this.userAgent);
    }

    public final void zza(zzhj zzhjVar, StringBuilder sb) throws IOException {
        clear();
        n0 n0Var = new n0(this, sb);
        int zzgk = zzhjVar.zzgk();
        for (int i = 0; i < zzgk; i++) {
            String zzaf = zzhjVar.zzaf(i);
            String zzag = zzhjVar.zzag(i);
            List<Type> list = n0Var.d;
            zziv zzivVar = n0Var.c;
            zzis zzisVar = n0Var.f8707a;
            StringBuilder sb2 = n0Var.b;
            if (sb2 != null) {
                StringBuilder sb3 = new StringBuilder(String.valueOf(zzaf).length() + 2 + String.valueOf(zzag).length());
                sb3.append(zzaf);
                sb3.append(": ");
                sb3.append(zzag);
                sb2.append(sb3.toString());
                sb2.append(zzjt.zzaig);
            }
            zzjd zzao = zzivVar.zzao(zzaf);
            if (zzao != null) {
                Type zza = zzix.zza(list, zzao.getGenericType());
                if (zzjs.zzc(zza)) {
                    Class<?> zzb = zzjs.zzb(list, zzjs.zzd(zza));
                    zzisVar.zza(zzao.zzia(), zzb, a(zzb, list, zzag));
                } else if (zzjs.zza(zzjs.zzb(list, zza), Iterable.class)) {
                    Collection<Object> collection = (Collection) zzao.zzh(this);
                    if (collection == null) {
                        collection = zzix.zzb(zza);
                        zzao.zzb(this, collection);
                    }
                    collection.add(a(zza == Object.class ? null : zzjs.zze(zza), list, zzag));
                } else {
                    zzao.zzb(this, a(zza, list, zzag));
                }
            } else {
                ArrayList arrayList = (ArrayList) get(zzaf);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    zzgx zzgxVar = (zzgx) zzb(zzaf, arrayList);
                }
                arrayList.add(zzag);
            }
        }
        n0Var.f8707a.zzhw();
    }

    public final zzgx zzaa(String str) {
        this.ifNoneMatch = d(null);
        return this;
    }

    public final zzgx zzab(String str) {
        this.ifUnmodifiedSince = d(null);
        return this;
    }

    public final zzgx zzac(String str) {
        this.ifRange = d(null);
        return this;
    }

    public final zzgx zzad(String str) {
        this.userAgent = d(str);
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzgx) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzgx) clone();
    }

    public final zzgx zzx(String str) {
        this.authorization = d(null);
        return this;
    }

    public final zzgx zzy(String str) {
        this.ifModifiedSince = d(null);
        return this;
    }

    public final zzgx zzz(String str) {
        this.ifMatch = d(null);
        return this;
    }
}
