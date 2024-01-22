package org.jose4j.jwt.consumer;

import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
import org.jose4j.lang.UncheckedJoseException;
import org.slf4j.Marker;
/* loaded from: classes13.dex */
public class TypeValidator implements ErrorCodeValidator {

    /* renamed from: a  reason: collision with root package name */
    public b f15546a;
    public boolean b;

    /* loaded from: classes13.dex */
    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    public TypeValidator(boolean z, String str) {
        try {
            b a2 = a(str);
            this.f15546a = a2;
            if (!a2.d().equals(Marker.ANY_MARKER)) {
                this.b = z;
                return;
            }
            throw new UncheckedJoseException("cannot use wildcard in subtype of expected type");
        } catch (a e) {
            throw new UncheckedJoseException("The given expected type '" + str + "' isn't a valid media type in this context.", e);
        }
    }

    public final b a(String str) throws a {
        return str.contains(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? new b(str) : new b("application", str);
    }

    public ErrorCodeValidator.Error b(String str) {
        if (str == null) {
            if (this.b) {
                return new ErrorCodeValidator.Error(21, "No typ header parameter present in the innermost JWS/JWE");
            }
            return null;
        } else if (this.f15546a != null) {
            try {
                b a2 = a(str);
                if (!this.f15546a.f(a2) || a2.d().equals(Marker.ANY_MARKER)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid typ header parameter value '");
                    sb.append(str);
                    sb.append("'. Expecting '");
                    sb.append(this.f15546a);
                    sb.append("'");
                    if (this.f15546a.c().equals("application")) {
                        sb.append(" or just '");
                        sb.append(this.f15546a.d());
                        sb.append("'");
                    }
                    sb.append(".");
                    return new ErrorCodeValidator.Error(22, sb.toString());
                }
                return null;
            } catch (a e) {
                return new ErrorCodeValidator.Error(22, "typ header parameter value '" + str + "' not parsable as a media type " + e);
            }
        } else {
            return null;
        }
    }

    @Override // org.jose4j.jwt.consumer.ErrorCodeValidator
    public ErrorCodeValidator.Error validate(JwtContext jwtContext) {
        return b(jwtContext.getJoseObjects().get(0).getHeader("typ"));
    }

    /* loaded from: classes13.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public String f15547a;
        public String b;

        public b(String str) throws a {
            g(str);
        }

        public static void a(String str) throws a {
            if (str != null && str.length() != 0) {
                for (int i = 0; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    if (!e(charAt)) {
                        throw new a("Invalid token char " + charAt);
                    }
                }
                return;
            }
            throw new a("cannot have empty part");
        }

        public static boolean e(char c) {
            return c > ' ' && c <= '~' && "()<>@,;:/[]?=\\\"".indexOf(c) < 0;
        }

        public String b() {
            return this.f15547a + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.b;
        }

        public String c() {
            return this.f15547a;
        }

        public String d() {
            return this.b;
        }

        public boolean f(b bVar) {
            return this.f15547a.equals(bVar.c()) && (this.b.equals(bVar.d()) || this.b.equals(Marker.ANY_MARKER) || bVar.d().equals(Marker.ANY_MARKER));
        }

        public final void g(String str) throws a {
            int indexOf = str.indexOf(47);
            if (indexOf >= 0) {
                int indexOf2 = str.indexOf(59);
                if (indexOf2 < 0) {
                    String trim = str.substring(0, indexOf).trim();
                    Locale locale = Locale.ENGLISH;
                    this.f15547a = trim.toLowerCase(locale);
                    this.b = str.substring(indexOf + 1).trim().toLowerCase(locale);
                } else if (indexOf < indexOf2) {
                    String trim2 = str.substring(0, indexOf).trim();
                    Locale locale2 = Locale.ENGLISH;
                    this.f15547a = trim2.toLowerCase(locale2);
                    this.b = str.substring(indexOf + 1, indexOf2).trim().toLowerCase(locale2);
                } else {
                    throw new a("Cannot find sub type.");
                }
                a(this.f15547a);
                a(this.b);
                return;
            }
            throw new a("Cannot find sub type.");
        }

        public String toString() {
            return b();
        }

        public b(String str, String str2) throws a {
            Locale locale = Locale.ENGLISH;
            String lowerCase = str.toLowerCase(locale);
            this.f15547a = lowerCase;
            a(lowerCase);
            String lowerCase2 = str2.toLowerCase(locale);
            this.b = lowerCase2;
            a(lowerCase2);
        }
    }
}
