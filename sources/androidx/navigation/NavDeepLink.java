package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public final class NavDeepLink {
    public static final Pattern j = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<String> f1440a;
    public final Map<String, b> b;
    public Pattern c;
    public boolean d;
    public boolean e;
    public final String f;
    public final String g;
    public Pattern h;
    public final String i;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f1441a;
        public String b;
        public String c;

        @NonNull
        public static Builder fromAction(@NonNull String str) {
            if (!str.isEmpty()) {
                Builder builder = new Builder();
                builder.setAction(str);
                return builder;
            }
            throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.");
        }

        @NonNull
        public static Builder fromMimeType(@NonNull String str) {
            Builder builder = new Builder();
            builder.setMimeType(str);
            return builder;
        }

        @NonNull
        public static Builder fromUriPattern(@NonNull String str) {
            Builder builder = new Builder();
            builder.setUriPattern(str);
            return builder;
        }

        @NonNull
        public NavDeepLink build() {
            return new NavDeepLink(this.f1441a, this.b, this.c);
        }

        @NonNull
        public Builder setAction(@NonNull String str) {
            if (!str.isEmpty()) {
                this.b = str;
                return this;
            }
            throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.");
        }

        @NonNull
        public Builder setMimeType(@NonNull String str) {
            this.c = str;
            return this;
        }

        @NonNull
        public Builder setUriPattern(@NonNull String str) {
            this.f1441a = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class a implements Comparable<a> {
        public String h;
        public String i;

        public a(@NonNull String str) {
            String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR, -1);
            this.h = split[0];
            this.i = split[1];
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(@NonNull a aVar) {
            int i = this.h.equals(aVar.h) ? 2 : 0;
            return this.i.equals(aVar.i) ? i + 1 : i;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public String f1442a;
        public ArrayList<String> b = new ArrayList<>();

        public void a(String str) {
            this.b.add(str);
        }

        public String b(int i) {
            return this.b.get(i);
        }

        public String c() {
            return this.f1442a;
        }

        public void d(String str) {
            this.f1442a = str;
        }

        public int e() {
            return this.b.size();
        }
    }

    public NavDeepLink(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.f1440a = new ArrayList<>();
        this.b = new HashMap();
        this.c = null;
        int i = 0;
        this.d = false;
        this.e = false;
        this.h = null;
        this.f = str;
        this.g = str2;
        this.i = str3;
        if (str != null) {
            Uri parse = Uri.parse(str);
            this.e = parse.getQuery() != null;
            StringBuilder sb = new StringBuilder("^");
            if (!j.matcher(str).find()) {
                sb.append("http[s]?://");
            }
            Pattern compile = Pattern.compile("\\{(.+?)\\}");
            if (this.e) {
                Matcher matcher = Pattern.compile("(\\?)").matcher(str);
                if (matcher.find()) {
                    a(str.substring(0, matcher.start()), sb, compile);
                }
                this.d = false;
                for (String str4 : parse.getQueryParameterNames()) {
                    StringBuilder sb2 = new StringBuilder();
                    String queryParameter = parse.getQueryParameter(str4);
                    Matcher matcher2 = compile.matcher(queryParameter);
                    b bVar = new b();
                    int i2 = i;
                    while (matcher2.find()) {
                        bVar.a(matcher2.group(1));
                        sb2.append(Pattern.quote(queryParameter.substring(i2, matcher2.start())));
                        sb2.append("(.+?)?");
                        i2 = matcher2.end();
                    }
                    if (i2 < queryParameter.length()) {
                        sb2.append(Pattern.quote(queryParameter.substring(i2)));
                    }
                    bVar.d(sb2.toString().replace(".*", "\\E.*\\Q"));
                    this.b.put(str4, bVar);
                    i = 0;
                }
            } else {
                this.d = a(str, sb, compile);
            }
            this.c = Pattern.compile(sb.toString().replace(".*", "\\E.*\\Q"), 2);
        }
        if (str3 != null) {
            if (Pattern.compile("^[\\s\\S]+/[\\s\\S]+$").matcher(str3).matches()) {
                a aVar = new a(str3);
                this.h = Pattern.compile(("^(" + aVar.h + "|[*]+)/(" + aVar.i + "|[*]+)$").replace("*|[*]", "[\\s\\S]"));
                return;
            }
            throw new IllegalArgumentException("The given mimeType " + str3 + " does not match to required \"type/subtype\" format");
        }
    }

    public final boolean a(@NonNull String str, StringBuilder sb, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        boolean z = !str.contains(".*");
        int i = 0;
        while (matcher.find()) {
            this.f1440a.add(matcher.group(1));
            sb.append(Pattern.quote(str.substring(i, matcher.start())));
            sb.append("(.+?)");
            i = matcher.end();
            z = false;
        }
        if (i < str.length()) {
            sb.append(Pattern.quote(str.substring(i)));
        }
        sb.append("($|(\\?(.)*))");
        return z;
    }

    @Nullable
    public Bundle b(@NonNull Uri uri, @NonNull Map<String, NavArgument> map) {
        Matcher matcher;
        Matcher matcher2 = this.c.matcher(uri.toString());
        if (matcher2.matches()) {
            Bundle bundle = new Bundle();
            int size = this.f1440a.size();
            int i = 0;
            while (i < size) {
                String str = this.f1440a.get(i);
                i++;
                if (e(bundle, str, Uri.decode(matcher2.group(i)), map.get(str))) {
                    return null;
                }
            }
            if (this.e) {
                for (String str2 : this.b.keySet()) {
                    b bVar = this.b.get(str2);
                    String queryParameter = uri.getQueryParameter(str2);
                    if (queryParameter != null) {
                        matcher = Pattern.compile(bVar.c()).matcher(queryParameter);
                        if (!matcher.matches()) {
                            return null;
                        }
                    } else {
                        matcher = null;
                    }
                    for (int i2 = 0; i2 < bVar.e(); i2++) {
                        String decode = matcher != null ? Uri.decode(matcher.group(i2 + 1)) : null;
                        String b2 = bVar.b(i2);
                        NavArgument navArgument = map.get(b2);
                        if (decode != null && !decode.replaceAll("[{}]", "").equals(b2) && e(bundle, b2, decode, navArgument)) {
                            return null;
                        }
                    }
                }
            }
            return bundle;
        }
        return null;
    }

    public int c(@NonNull String str) {
        if (this.i == null || !this.h.matcher(str).matches()) {
            return -1;
        }
        return new a(this.i).compareTo(new a(str));
    }

    public boolean d() {
        return this.d;
    }

    public final boolean e(Bundle bundle, String str, String str2, NavArgument navArgument) {
        if (navArgument != null) {
            try {
                navArgument.getType().c(bundle, str, str2);
                return false;
            } catch (IllegalArgumentException unused) {
                return true;
            }
        }
        bundle.putString(str, str2);
        return false;
    }

    @Nullable
    public String getAction() {
        return this.g;
    }

    @Nullable
    public String getMimeType() {
        return this.i;
    }

    @Nullable
    public String getUriPattern() {
        return this.f;
    }

    public NavDeepLink(@NonNull String str) {
        this(str, null, null);
    }
}
