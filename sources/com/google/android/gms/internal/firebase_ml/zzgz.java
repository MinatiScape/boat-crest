package com.google.android.gms.internal.firebase_ml;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes7.dex */
public final class zzgz {
    public static final Pattern e = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
    public static final Pattern f = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
    public static final Pattern g;
    public static final Pattern h;

    /* renamed from: a  reason: collision with root package name */
    public String f8765a;
    public String b;
    public final SortedMap<String, String> c = new TreeMap();
    public String d;

    static {
        StringBuilder sb = new StringBuilder("[^\\s/=;\"]+".length() + 14 + "[^\\s/=;\"]+".length() + ";.*".length());
        sb.append("\\s*(");
        sb.append("[^\\s/=;\"]+");
        sb.append(")/(");
        sb.append("[^\\s/=;\"]+");
        sb.append(")\\s*(");
        sb.append(";.*");
        sb.append(")?");
        g = Pattern.compile(sb.toString(), 32);
        StringBuilder sb2 = new StringBuilder("\"([^\"]*)\"".length() + 1 + "[^\\s;\"]*".length());
        sb2.append("\"([^\"]*)\"");
        sb2.append("|");
        sb2.append("[^\\s;\"]*");
        String sb3 = sb2.toString();
        StringBuilder sb4 = new StringBuilder("[^\\s/=;\"]+".length() + 12 + String.valueOf(sb3).length());
        sb4.append("\\s*;\\s*(");
        sb4.append("[^\\s/=;\"]+");
        sb4.append(")=(");
        sb4.append(sb3);
        sb4.append(")");
        h = Pattern.compile(sb4.toString());
    }

    public zzgz(String str) {
        this.f8765a = "application";
        this.b = "octet-stream";
        Matcher matcher = g.matcher(str);
        zzml.checkArgument(matcher.matches(), "Type must be in the 'maintype/subtype; parameter=value' format");
        String group = matcher.group(1);
        Pattern pattern = e;
        zzml.checkArgument(pattern.matcher(group).matches(), "Type contains reserved characters");
        this.f8765a = group;
        this.d = null;
        String group2 = matcher.group(2);
        zzml.checkArgument(pattern.matcher(group2).matches(), "Subtype contains reserved characters");
        this.b = group2;
        this.d = null;
        String group3 = matcher.group(3);
        if (group3 != null) {
            Matcher matcher2 = h.matcher(group3);
            while (matcher2.find()) {
                String group4 = matcher2.group(1);
                String group5 = matcher2.group(3);
                if (group5 == null) {
                    group5 = matcher2.group(2);
                }
                a(group4, group5);
            }
        }
    }

    public static boolean c(String str) {
        return f.matcher(str).matches();
    }

    public static boolean zzb(String str, String str2) {
        return str2 != null && new zzgz(str).b(new zzgz(str2));
    }

    public final zzgz a(String str, String str2) {
        if (str2 == null) {
            this.d = null;
            this.c.remove(str.toLowerCase(Locale.US));
            return this;
        }
        zzml.checkArgument(f.matcher(str).matches(), "Name contains reserved characters");
        this.d = null;
        this.c.put(str.toLowerCase(Locale.US), str2);
        return this;
    }

    public final boolean b(zzgz zzgzVar) {
        return zzgzVar != null && this.f8765a.equalsIgnoreCase(zzgzVar.f8765a) && this.b.equalsIgnoreCase(zzgzVar.b);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzgz) {
            zzgz zzgzVar = (zzgz) obj;
            return b(zzgzVar) && this.c.equals(zzgzVar.c);
        }
        return false;
    }

    public final int hashCode() {
        return zzft().hashCode();
    }

    public final String toString() {
        return zzft();
    }

    public final zzgz zza(Charset charset) {
        a("charset", charset == null ? null : charset.name());
        return this;
    }

    public final String zzft() {
        String str = this.d;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8765a);
        sb.append('/');
        sb.append(this.b);
        SortedMap<String, String> sortedMap = this.c;
        if (sortedMap != null) {
            for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
                String value = entry.getValue();
                sb.append("; ");
                sb.append(entry.getKey());
                sb.append("=");
                if (!c(value)) {
                    String replace = value.replace("\\", "\\\\").replace("\"", "\\\"");
                    StringBuilder sb2 = new StringBuilder(String.valueOf(replace).length() + 2);
                    sb2.append("\"");
                    sb2.append(replace);
                    sb2.append("\"");
                    value = sb2.toString();
                }
                sb.append(value);
            }
        }
        String sb3 = sb.toString();
        this.d = sb3;
        return sb3;
    }

    public final Charset zzfu() {
        String str = this.c.get("charset".toLowerCase(Locale.US));
        if (str == null) {
            return null;
        }
        return Charset.forName(str);
    }
}
