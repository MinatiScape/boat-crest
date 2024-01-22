package com.google.android.gms.internal.firebase_ml;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;
/* loaded from: classes7.dex */
public final class zzgu extends zzjf {
    public static final zzjz p = new zzjy("=&-_.!~*'()@:$,;/?:", false);
    public String j;
    public String k;
    public String l;
    public int m;
    public List<String> n;
    public String o;

    public zzgu() {
        this.m = -1;
    }

    public static void a(Set<Map.Entry<String, Object>> set, StringBuilder sb) {
        boolean z = true;
        for (Map.Entry<String, Object> entry : set) {
            Object value = entry.getValue();
            if (value != null) {
                String zzav = zzjw.zzav(entry.getKey());
                if (value instanceof Collection) {
                    for (Object obj : (Collection) value) {
                        z = b(z, sb, zzav, obj);
                    }
                } else {
                    z = b(z, sb, zzav, value);
                }
            }
        }
    }

    public static boolean b(boolean z, StringBuilder sb, String str, Object obj) {
        if (z) {
            z = false;
            sb.append(org.apache.commons.codec.net.a.SEP);
        } else {
            sb.append(Typography.amp);
        }
        sb.append(str);
        String zzav = zzjw.zzav(obj.toString());
        if (zzav.length() != 0) {
            sb.append('=');
            sb.append(zzav);
        }
        return z;
    }

    public static List<String> d(String str) {
        String substring;
        if (str == null || str.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = true;
        while (z) {
            int indexOf = str.indexOf(47, i);
            boolean z2 = indexOf != -1;
            if (z2) {
                substring = str.substring(i, indexOf);
            } else {
                substring = str.substring(i);
            }
            arrayList.add(zzjw.zzar(substring));
            i = indexOf + 1;
            z = z2;
        }
        return arrayList;
    }

    public static URL e(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzgu zzguVar = (zzgu) super.clone();
        if (this.n != null) {
            zzguVar.n = new ArrayList(this.n);
        }
        return zzguVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && (obj instanceof zzgu)) {
            return zzft().equals(((zzgu) obj).zzft());
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return zzft().hashCode();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return zzft();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzgu) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzgu) clone();
    }

    public final String zzft() {
        StringBuilder sb = new StringBuilder();
        sb.append((String) zzml.checkNotNull(this.j));
        sb.append("://");
        String str = this.l;
        if (str != null) {
            sb.append(zzjw.zzau(str));
            sb.append('@');
        }
        sb.append((String) zzml.checkNotNull(this.k));
        int i = this.m;
        if (i != -1) {
            sb.append(':');
            sb.append(i);
        }
        String valueOf = String.valueOf(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        List<String> list = this.n;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str2 = this.n.get(i2);
                if (i2 != 0) {
                    sb2.append('/');
                }
                if (str2.length() != 0) {
                    sb2.append(zzjw.zzas(str2));
                }
            }
        }
        a(entrySet(), sb2);
        String str3 = this.o;
        if (str3 != null) {
            sb2.append('#');
            sb2.append(p.zzaw(str3));
        }
        String valueOf2 = String.valueOf(sb2.toString());
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final URL zzt(String str) {
        try {
            return new URL(e(zzft()), str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final void zzu(String str) {
        this.n = d(null);
    }

    public zzgu(String str) {
        this(e(str));
    }

    public zzgu(URL url) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo());
    }

    public zzgu(String str, String str2, int i, String str3, String str4, String str5, String str6) {
        this.m = -1;
        this.j = str.toLowerCase(Locale.US);
        this.k = str2;
        this.m = i;
        this.n = d(str3);
        this.o = str4 != null ? zzjw.zzar(str4) : null;
        if (str5 != null) {
            zzhn.zze(str5, this);
        }
        this.l = str6 != null ? zzjw.zzar(str6) : null;
    }
}
