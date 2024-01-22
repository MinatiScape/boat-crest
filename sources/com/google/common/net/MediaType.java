package com.google.common.net;

import com.clevertap.android.sdk.Constants;
import com.coveiot.utils.utility.FileUtil;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import kotlin.text.Typography;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.jose4j.jwx.HeaderParameterNames;
import org.slf4j.Marker;
@Immutable
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public final class MediaType {

    /* renamed from: a  reason: collision with root package name */
    public final String f10713a;
    public final String b;
    public final ImmutableListMultimap<String, String> c;
    @LazyInit
    public String d;
    @LazyInit
    public int e;
    @LazyInit
    public Optional<Charset> f;
    public static final ImmutableListMultimap<String, String> g = ImmutableListMultimap.of("charset", Ascii.toLowerCase(Charsets.UTF_8.name()));
    public static final CharMatcher h = CharMatcher.ascii().and(CharMatcher.javaIsoControl().negate()).and(CharMatcher.isNot(' ')).and(CharMatcher.noneOf("()<>@,;:\\\"/[]?="));
    public static final CharMatcher i = CharMatcher.ascii().and(CharMatcher.noneOf("\"\\\r"));
    public static final CharMatcher j = CharMatcher.anyOf(" \t\r\n");
    public static final Map<MediaType, MediaType> k = Maps.newHashMap();
    public static final MediaType ANY_TYPE = f(Marker.ANY_MARKER, Marker.ANY_MARKER);
    public static final MediaType ANY_TEXT_TYPE = f("text", Marker.ANY_MARKER);
    public static final MediaType ANY_IMAGE_TYPE = f("image", Marker.ANY_MARKER);
    public static final MediaType ANY_AUDIO_TYPE = f("audio", Marker.ANY_MARKER);
    public static final MediaType ANY_VIDEO_TYPE = f("video", Marker.ANY_MARKER);
    public static final MediaType ANY_APPLICATION_TYPE = f("application", Marker.ANY_MARKER);
    public static final MediaType ANY_FONT_TYPE = f("font", Marker.ANY_MARKER);
    public static final MediaType CACHE_MANIFEST_UTF_8 = g("text", "cache-manifest");
    public static final MediaType CSS_UTF_8 = g("text", "css");
    public static final MediaType CSV_UTF_8 = g("text", "csv");
    public static final MediaType HTML_UTF_8 = g("text", Constants.INAPP_HTML_TAG);
    public static final MediaType I_CALENDAR_UTF_8 = g("text", "calendar");
    public static final MediaType PLAIN_TEXT_UTF_8 = g("text", "plain");
    public static final MediaType TEXT_JAVASCRIPT_UTF_8 = g("text", "javascript");
    public static final MediaType TSV_UTF_8 = g("text", "tab-separated-values");
    public static final MediaType VCARD_UTF_8 = g("text", "vcard");
    public static final MediaType WML_UTF_8 = g("text", "vnd.wap.wml");
    public static final MediaType XML_UTF_8 = g("text", "xml");
    public static final MediaType VTT_UTF_8 = g("text", "vtt");
    public static final MediaType BMP = f("image", "bmp");
    public static final MediaType CRW = f("image", "x-canon-crw");
    public static final MediaType GIF = f("image", "gif");
    public static final MediaType ICO = f("image", "vnd.microsoft.icon");
    public static final MediaType JPEG = f("image", FileUtil.Format.JPEG);
    public static final MediaType PNG = f("image", FileUtil.Format.PNG);
    public static final MediaType PSD = f("image", "vnd.adobe.photoshop");
    public static final MediaType SVG_UTF_8 = g("image", "svg+xml");
    public static final MediaType TIFF = f("image", "tiff");
    public static final MediaType WEBP = f("image", "webp");
    public static final MediaType HEIF = f("image", "heif");
    public static final MediaType JP2K = f("image", "jp2");
    public static final MediaType MP4_AUDIO = f("audio", "mp4");
    public static final MediaType MPEG_AUDIO = f("audio", "mpeg");
    public static final MediaType OGG_AUDIO = f("audio", "ogg");
    public static final MediaType WEBM_AUDIO = f("audio", "webm");
    public static final MediaType L16_AUDIO = f("audio", "l16");
    public static final MediaType L24_AUDIO = f("audio", "l24");
    public static final MediaType BASIC_AUDIO = f("audio", "basic");
    public static final MediaType AAC_AUDIO = f("audio", "aac");
    public static final MediaType VORBIS_AUDIO = f("audio", "vorbis");
    public static final MediaType WMA_AUDIO = f("audio", "x-ms-wma");
    public static final MediaType WAX_AUDIO = f("audio", "x-ms-wax");
    public static final MediaType VND_REAL_AUDIO = f("audio", "vnd.rn-realaudio");
    public static final MediaType VND_WAVE_AUDIO = f("audio", "vnd.wave");
    public static final MediaType MP4_VIDEO = f("video", "mp4");
    public static final MediaType MPEG_VIDEO = f("video", "mpeg");
    public static final MediaType OGG_VIDEO = f("video", "ogg");
    public static final MediaType QUICKTIME = f("video", "quicktime");
    public static final MediaType WEBM_VIDEO = f("video", "webm");
    public static final MediaType WMV = f("video", "x-ms-wmv");
    public static final MediaType FLV_VIDEO = f("video", "x-flv");
    public static final MediaType THREE_GPP_VIDEO = f("video", "3gpp");
    public static final MediaType THREE_GPP2_VIDEO = f("video", "3gpp2");
    public static final MediaType APPLICATION_XML_UTF_8 = g("application", "xml");
    public static final MediaType ATOM_UTF_8 = g("application", "atom+xml");
    public static final MediaType BZIP2 = f("application", "x-bzip2");
    public static final MediaType DART_UTF_8 = g("application", "dart");
    public static final MediaType APPLE_PASSBOOK = f("application", "vnd.apple.pkpass");
    public static final MediaType EOT = f("application", "vnd.ms-fontobject");
    public static final MediaType EPUB = f("application", "epub+zip");
    public static final MediaType FORM_DATA = f("application", "x-www-form-urlencoded");
    public static final MediaType KEY_ARCHIVE = f("application", "pkcs12");
    public static final MediaType APPLICATION_BINARY = f("application", "binary");
    public static final MediaType GEO_JSON = f("application", "geo+json");
    public static final MediaType GZIP = f("application", "x-gzip");
    public static final MediaType HAL_JSON = f("application", "hal+json");
    public static final MediaType JAVASCRIPT_UTF_8 = g("application", "javascript");
    public static final MediaType JOSE = f("application", "jose");
    public static final MediaType JOSE_JSON = f("application", "jose+json");
    public static final MediaType JSON_UTF_8 = g("application", "json");
    public static final MediaType MANIFEST_JSON_UTF_8 = g("application", "manifest+json");
    public static final MediaType KML = f("application", "vnd.google-earth.kml+xml");
    public static final MediaType KMZ = f("application", "vnd.google-earth.kmz");
    public static final MediaType MBOX = f("application", "mbox");
    public static final MediaType APPLE_MOBILE_CONFIG = f("application", "x-apple-aspen-config");
    public static final MediaType MICROSOFT_EXCEL = f("application", "vnd.ms-excel");
    public static final MediaType MICROSOFT_OUTLOOK = f("application", "vnd.ms-outlook");
    public static final MediaType MICROSOFT_POWERPOINT = f("application", "vnd.ms-powerpoint");
    public static final MediaType MICROSOFT_WORD = f("application", "msword");
    public static final MediaType MEDIA_PRESENTATION_DESCRIPTION = f("application", "dash+xml");
    public static final MediaType WASM_APPLICATION = f("application", "wasm");
    public static final MediaType NACL_APPLICATION = f("application", "x-nacl");
    public static final MediaType NACL_PORTABLE_APPLICATION = f("application", "x-pnacl");
    public static final MediaType OCTET_STREAM = f("application", "octet-stream");
    public static final MediaType OGG_CONTAINER = f("application", "ogg");
    public static final MediaType OOXML_DOCUMENT = f("application", "vnd.openxmlformats-officedocument.wordprocessingml.document");
    public static final MediaType OOXML_PRESENTATION = f("application", "vnd.openxmlformats-officedocument.presentationml.presentation");
    public static final MediaType OOXML_SHEET = f("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    public static final MediaType OPENDOCUMENT_GRAPHICS = f("application", "vnd.oasis.opendocument.graphics");
    public static final MediaType OPENDOCUMENT_PRESENTATION = f("application", "vnd.oasis.opendocument.presentation");
    public static final MediaType OPENDOCUMENT_SPREADSHEET = f("application", "vnd.oasis.opendocument.spreadsheet");
    public static final MediaType OPENDOCUMENT_TEXT = f("application", "vnd.oasis.opendocument.text");
    public static final MediaType OPENSEARCH_DESCRIPTION_UTF_8 = g("application", "opensearchdescription+xml");
    public static final MediaType PDF = f("application", "pdf");
    public static final MediaType POSTSCRIPT = f("application", "postscript");
    public static final MediaType PROTOBUF = f("application", "protobuf");
    public static final MediaType RDF_XML_UTF_8 = g("application", "rdf+xml");
    public static final MediaType RTF_UTF_8 = g("application", "rtf");
    public static final MediaType SFNT = f("application", "font-sfnt");
    public static final MediaType SHOCKWAVE_FLASH = f("application", "x-shockwave-flash");
    public static final MediaType SKETCHUP = f("application", "vnd.sketchup.skp");
    public static final MediaType SOAP_XML_UTF_8 = g("application", "soap+xml");
    public static final MediaType TAR = f("application", "x-tar");
    public static final MediaType WOFF = f("application", "font-woff");
    public static final MediaType WOFF2 = f("application", "font-woff2");
    public static final MediaType XHTML_UTF_8 = g("application", "xhtml+xml");
    public static final MediaType XRD_UTF_8 = g("application", "xrd+xml");
    public static final MediaType ZIP = f("application", HeaderParameterNames.ZIP);
    public static final MediaType FONT_COLLECTION = f("font", "collection");
    public static final MediaType FONT_OTF = f("font", "otf");
    public static final MediaType FONT_SFNT = f("font", "sfnt");
    public static final MediaType FONT_TTF = f("font", "ttf");
    public static final MediaType FONT_WOFF = f("font", "woff");
    public static final MediaType FONT_WOFF2 = f("font", "woff2");
    public static final Joiner.MapJoiner l = Joiner.on("; ").withKeyValueSeparator("=");

    /* loaded from: classes10.dex */
    public class a implements Function<Collection<String>, ImmutableMultiset<String>> {
        public a(MediaType mediaType) {
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public ImmutableMultiset<String> apply(Collection<String> collection) {
            return ImmutableMultiset.copyOf(collection);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Function<String, String> {
        public b(MediaType mediaType) {
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public String apply(String str) {
            return (!MediaType.h.matchesAllOf(str) || str.isEmpty()) ? MediaType.h(str) : str;
        }
    }

    /* loaded from: classes10.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final String f10714a;
        public int b = 0;

        public c(String str) {
            this.f10714a = str;
        }

        public char a(char c) {
            Preconditions.checkState(e());
            Preconditions.checkState(f() == c);
            this.b++;
            return c;
        }

        public char b(CharMatcher charMatcher) {
            Preconditions.checkState(e());
            char f = f();
            Preconditions.checkState(charMatcher.matches(f));
            this.b++;
            return f;
        }

        public String c(CharMatcher charMatcher) {
            int i = this.b;
            String d = d(charMatcher);
            Preconditions.checkState(this.b != i);
            return d;
        }

        public String d(CharMatcher charMatcher) {
            Preconditions.checkState(e());
            int i = this.b;
            this.b = charMatcher.negate().indexIn(this.f10714a, i);
            return e() ? this.f10714a.substring(i, this.b) : this.f10714a.substring(i);
        }

        public boolean e() {
            int i = this.b;
            return i >= 0 && i < this.f10714a.length();
        }

        public char f() {
            Preconditions.checkState(e());
            return this.f10714a.charAt(this.b);
        }
    }

    public MediaType(String str, String str2, ImmutableListMultimap<String, String> immutableListMultimap) {
        this.f10713a = str;
        this.b = str2;
        this.c = immutableListMultimap;
    }

    public static MediaType c(MediaType mediaType) {
        k.put(mediaType, mediaType);
        return mediaType;
    }

    public static MediaType create(String str, String str2) {
        MediaType e = e(str, str2, ImmutableListMultimap.of());
        e.f = Optional.absent();
        return e;
    }

    public static MediaType e(String str, String str2, Multimap<String, String> multimap) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        Preconditions.checkNotNull(multimap);
        String j2 = j(str);
        String j3 = j(str2);
        Preconditions.checkArgument(!Marker.ANY_MARKER.equals(j2) || Marker.ANY_MARKER.equals(j3), "A wildcard type cannot be used with a non-wildcard subtype");
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        for (Map.Entry<String, String> entry : multimap.entries()) {
            String j4 = j(entry.getKey());
            builder.put((ImmutableListMultimap.Builder) j4, i(j4, entry.getValue()));
        }
        MediaType mediaType = new MediaType(j2, j3, builder.build());
        return (MediaType) MoreObjects.firstNonNull(k.get(mediaType), mediaType);
    }

    public static MediaType f(String str, String str2) {
        MediaType c2 = c(new MediaType(str, str2, ImmutableListMultimap.of()));
        c2.f = Optional.absent();
        return c2;
    }

    public static MediaType g(String str, String str2) {
        MediaType c2 = c(new MediaType(str, str2, g));
        c2.f = Optional.of(Charsets.UTF_8);
        return c2;
    }

    public static String h(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 16);
        sb.append(Typography.quote);
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\r' || charAt == '\\' || charAt == '\"') {
                sb.append('\\');
            }
            sb.append(charAt);
        }
        sb.append(Typography.quote);
        return sb.toString();
    }

    public static String i(String str, String str2) {
        Preconditions.checkNotNull(str2);
        Preconditions.checkArgument(CharMatcher.ascii().matchesAllOf(str2), "parameter values must be ASCII: %s", str2);
        return "charset".equals(str) ? Ascii.toLowerCase(str2) : str2;
    }

    public static String j(String str) {
        Preconditions.checkArgument(h.matchesAllOf(str));
        Preconditions.checkArgument(!str.isEmpty());
        return Ascii.toLowerCase(str);
    }

    public static MediaType parse(String str) {
        String c2;
        Preconditions.checkNotNull(str);
        c cVar = new c(str);
        try {
            CharMatcher charMatcher = h;
            String c3 = cVar.c(charMatcher);
            cVar.a('/');
            String c4 = cVar.c(charMatcher);
            ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
            while (cVar.e()) {
                CharMatcher charMatcher2 = j;
                cVar.d(charMatcher2);
                cVar.a(';');
                cVar.d(charMatcher2);
                CharMatcher charMatcher3 = h;
                String c5 = cVar.c(charMatcher3);
                cVar.a('=');
                if ('\"' == cVar.f()) {
                    cVar.a(Typography.quote);
                    StringBuilder sb = new StringBuilder();
                    while ('\"' != cVar.f()) {
                        if ('\\' == cVar.f()) {
                            cVar.a('\\');
                            sb.append(cVar.b(CharMatcher.ascii()));
                        } else {
                            sb.append(cVar.c(i));
                        }
                    }
                    c2 = sb.toString();
                    cVar.a(Typography.quote);
                } else {
                    c2 = cVar.c(charMatcher3);
                }
                builder.put((ImmutableListMultimap.Builder) c5, c2);
            }
            return e(c3, c4, builder.build());
        } catch (IllegalStateException e) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 18);
            sb2.append("Could not parse '");
            sb2.append(str);
            sb2.append("'");
            throw new IllegalArgumentException(sb2.toString(), e);
        }
    }

    public Optional<Charset> charset() {
        Optional<Charset> optional = this.f;
        if (optional == null) {
            Optional<Charset> absent = Optional.absent();
            UnmodifiableIterator<String> it = this.c.get((ImmutableListMultimap<String, String>) "charset").iterator();
            String str = null;
            optional = absent;
            while (it.hasNext()) {
                String next = it.next();
                if (str == null) {
                    optional = Optional.of(Charset.forName(next));
                    str = next;
                } else if (!str.equals(next)) {
                    StringBuilder sb = new StringBuilder(str.length() + 35 + String.valueOf(next).length());
                    sb.append("Multiple charset values defined: ");
                    sb.append(str);
                    sb.append(", ");
                    sb.append(next);
                    throw new IllegalStateException(sb.toString());
                }
            }
            this.f = optional;
        }
        return optional;
    }

    public final String d() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f10713a);
        sb.append('/');
        sb.append(this.b);
        if (!this.c.isEmpty()) {
            sb.append("; ");
            l.appendTo(sb, Multimaps.transformValues((ListMultimap) this.c, (Function) new b(this)).entries());
        }
        return sb.toString();
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MediaType) {
            MediaType mediaType = (MediaType) obj;
            return this.f10713a.equals(mediaType.f10713a) && this.b.equals(mediaType.b) && k().equals(mediaType.k());
        }
        return false;
    }

    public boolean hasWildcard() {
        return Marker.ANY_MARKER.equals(this.f10713a) || Marker.ANY_MARKER.equals(this.b);
    }

    public int hashCode() {
        int i2 = this.e;
        if (i2 == 0) {
            int hashCode = Objects.hashCode(this.f10713a, this.b, k());
            this.e = hashCode;
            return hashCode;
        }
        return i2;
    }

    public boolean is(MediaType mediaType) {
        return (mediaType.f10713a.equals(Marker.ANY_MARKER) || mediaType.f10713a.equals(this.f10713a)) && (mediaType.b.equals(Marker.ANY_MARKER) || mediaType.b.equals(this.b)) && this.c.entries().containsAll(mediaType.c.entries());
    }

    public final Map<String, ImmutableMultiset<String>> k() {
        return Maps.transformValues(this.c.asMap(), new a(this));
    }

    public ImmutableListMultimap<String, String> parameters() {
        return this.c;
    }

    public String subtype() {
        return this.b;
    }

    public String toString() {
        String str = this.d;
        if (str == null) {
            String d = d();
            this.d = d;
            return d;
        }
        return str;
    }

    public String type() {
        return this.f10713a;
    }

    public MediaType withCharset(Charset charset) {
        Preconditions.checkNotNull(charset);
        MediaType withParameter = withParameter("charset", charset.name());
        withParameter.f = Optional.of(charset);
        return withParameter;
    }

    public MediaType withParameter(String str, String str2) {
        return withParameters(str, ImmutableSet.of(str2));
    }

    public MediaType withParameters(Multimap<String, String> multimap) {
        return e(this.f10713a, this.b, multimap);
    }

    public MediaType withoutParameters() {
        return this.c.isEmpty() ? this : create(this.f10713a, this.b);
    }

    public MediaType withParameters(String str, Iterable<String> iterable) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(iterable);
        String j2 = j(str);
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        UnmodifiableIterator<Map.Entry<String, String>> it = this.c.entries().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String key = next.getKey();
            if (!j2.equals(key)) {
                builder.put((ImmutableListMultimap.Builder) key, next.getValue());
            }
        }
        for (String str2 : iterable) {
            builder.put((ImmutableListMultimap.Builder) j2, i(j2, str2));
        }
        MediaType mediaType = new MediaType(this.f10713a, this.b, builder.build());
        if (!j2.equals("charset")) {
            mediaType.f = this.f;
        }
        return (MediaType) MoreObjects.firstNonNull(k.get(mediaType), mediaType);
    }
}
