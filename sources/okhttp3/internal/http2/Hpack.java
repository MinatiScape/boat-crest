package okhttp3.internal.http2;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Hpack {
    @NotNull
    public static final Hpack INSTANCE;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Header[] f14278a;
    @NotNull
    public static final Map<ByteString, Integer> b;

    static {
        Hpack hpack = new Hpack();
        INSTANCE = hpack;
        ByteString byteString = Header.TARGET_METHOD;
        ByteString byteString2 = Header.TARGET_PATH;
        ByteString byteString3 = Header.TARGET_SCHEME;
        ByteString byteString4 = Header.RESPONSE_STATUS;
        f14278a = new Header[]{new Header(Header.TARGET_AUTHORITY, ""), new Header(byteString, "GET"), new Header(byteString, "POST"), new Header(byteString2, MqttTopic.TOPIC_LEVEL_SEPARATOR), new Header(byteString2, "/index.html"), new Header(byteString3, "http"), new Header(byteString3, "https"), new Header(byteString4, "200"), new Header(byteString4, "204"), new Header(byteString4, "206"), new Header(byteString4, "304"), new Header(byteString4, "400"), new Header(byteString4, "404"), new Header(byteString4, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header(FirebaseAnalytics.Param.LOCATION, ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
        b = hpack.a();
    }

    public final Map<ByteString, Integer> a() {
        Header[] headerArr = f14278a;
        LinkedHashMap linkedHashMap = new LinkedHashMap(headerArr.length);
        int length = headerArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            Header[] headerArr2 = f14278a;
            if (!linkedHashMap.containsKey(headerArr2[i].name)) {
                linkedHashMap.put(headerArr2[i].name, Integer.valueOf(i));
            }
            i = i2;
        }
        Map<ByteString, Integer> unmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        Intrinsics.checkNotNullExpressionValue(unmodifiableMap, "unmodifiableMap(result)");
        return unmodifiableMap;
    }

    @NotNull
    public final ByteString checkLowercase(@NotNull ByteString name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        int size = name.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            byte b2 = name.getByte(i);
            if (65 <= b2 && b2 <= 90) {
                throw new IOException(Intrinsics.stringPlus("PROTOCOL_ERROR response malformed: mixed case name: ", name.utf8()));
            }
            i = i2;
        }
        return name;
    }

    @NotNull
    public final Map<ByteString, Integer> getNAME_TO_FIRST_INDEX() {
        return b;
    }

    @NotNull
    public final Header[] getSTATIC_HEADER_TABLE() {
        return f14278a;
    }

    /* loaded from: classes12.dex */
    public static final class Reader {

        /* renamed from: a  reason: collision with root package name */
        public final int f14279a;
        public int b;
        @NotNull
        public final List<Header> c;
        @NotNull
        public final BufferedSource d;
        @JvmField
        @NotNull
        public Header[] dynamicTable;
        @JvmField
        public int dynamicTableByteCount;
        public int e;
        @JvmField
        public int headerCount;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Reader(@NotNull Source source, int i) {
            this(source, i, 0, 4, null);
            Intrinsics.checkNotNullParameter(source, "source");
        }

        @JvmOverloads
        public Reader(@NotNull Source source, int i, int i2) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.f14279a = i;
            this.b = i2;
            this.c = new ArrayList();
            this.d = Okio.buffer(source);
            Header[] headerArr = new Header[8];
            this.dynamicTable = headerArr;
            this.e = headerArr.length - 1;
        }

        public final void a() {
            int i = this.b;
            int i2 = this.dynamicTableByteCount;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    d(i2 - i);
                }
            }
        }

        public final void b() {
            ArraysKt___ArraysJvmKt.fill$default(this.dynamicTable, (Object) null, 0, 0, 6, (Object) null);
            this.e = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        public final int c(int i) {
            return this.e + 1 + i;
        }

        public final int d(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    i2 = this.e;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    Header header = this.dynamicTable[length];
                    Intrinsics.checkNotNull(header);
                    int i4 = header.hpackSize;
                    i -= i4;
                    this.dynamicTableByteCount -= i4;
                    this.headerCount--;
                    i3++;
                }
                Header[] headerArr = this.dynamicTable;
                System.arraycopy(headerArr, i2 + 1, headerArr, i2 + 1 + i3, this.headerCount);
                this.e += i3;
            }
            return i3;
        }

        public final ByteString e(int i) throws IOException {
            if (g(i)) {
                return Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[i].name;
            }
            int c = c(i - Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (c >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (c < headerArr.length) {
                    Header header = headerArr[c];
                    Intrinsics.checkNotNull(header);
                    return header.name;
                }
            }
            throw new IOException(Intrinsics.stringPlus("Header index too large ", Integer.valueOf(i + 1)));
        }

        public final void f(int i, Header header) {
            this.c.add(header);
            int i2 = header.hpackSize;
            if (i != -1) {
                Header header2 = this.dynamicTable[c(i)];
                Intrinsics.checkNotNull(header2);
                i2 -= header2.hpackSize;
            }
            int i3 = this.b;
            if (i2 > i3) {
                b();
                return;
            }
            int d = d((this.dynamicTableByteCount + i2) - i3);
            if (i == -1) {
                int i4 = this.headerCount + 1;
                Header[] headerArr = this.dynamicTable;
                if (i4 > headerArr.length) {
                    Header[] headerArr2 = new Header[headerArr.length * 2];
                    System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                    this.e = this.dynamicTable.length - 1;
                    this.dynamicTable = headerArr2;
                }
                int i5 = this.e;
                this.e = i5 - 1;
                this.dynamicTable[i5] = header;
                this.headerCount++;
            } else {
                this.dynamicTable[i + c(i) + d] = header;
            }
            this.dynamicTableByteCount += i2;
        }

        public final boolean g(int i) {
            return i >= 0 && i <= Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length - 1;
        }

        @NotNull
        public final List<Header> getAndResetHeaderList() {
            List<Header> list = CollectionsKt___CollectionsKt.toList(this.c);
            this.c.clear();
            return list;
        }

        public final int h() throws IOException {
            return Util.and(this.d.readByte(), 255);
        }

        public final void i(int i) throws IOException {
            if (g(i)) {
                this.c.add(Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[i]);
                return;
            }
            int c = c(i - Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (c >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (c < headerArr.length) {
                    List<Header> list = this.c;
                    Header header = headerArr[c];
                    Intrinsics.checkNotNull(header);
                    list.add(header);
                    return;
                }
            }
            throw new IOException(Intrinsics.stringPlus("Header index too large ", Integer.valueOf(i + 1)));
        }

        public final void j(int i) throws IOException {
            f(-1, new Header(e(i), readByteString()));
        }

        public final void k() throws IOException {
            f(-1, new Header(Hpack.INSTANCE.checkLowercase(readByteString()), readByteString()));
        }

        public final void l(int i) throws IOException {
            this.c.add(new Header(e(i), readByteString()));
        }

        public final void m() throws IOException {
            this.c.add(new Header(Hpack.INSTANCE.checkLowercase(readByteString()), readByteString()));
        }

        public final int maxDynamicTableByteCount() {
            return this.b;
        }

        @NotNull
        public final ByteString readByteString() throws IOException {
            int h = h();
            boolean z = (h & 128) == 128;
            long readInt = readInt(h, 127);
            if (z) {
                Buffer buffer = new Buffer();
                Huffman.INSTANCE.decode(this.d, readInt, buffer);
                return buffer.readByteString();
            }
            return this.d.readByteString(readInt);
        }

        public final void readHeaders() throws IOException {
            while (!this.d.exhausted()) {
                int and = Util.and(this.d.readByte(), 255);
                if (and == 128) {
                    throw new IOException("index == 0");
                }
                if ((and & 128) == 128) {
                    i(readInt(and, 127) - 1);
                } else if (and == 64) {
                    k();
                } else if ((and & 64) == 64) {
                    j(readInt(and, 63) - 1);
                } else if ((and & 32) == 32) {
                    int readInt = readInt(and, 31);
                    this.b = readInt;
                    if (readInt >= 0 && readInt <= this.f14279a) {
                        a();
                    } else {
                        throw new IOException(Intrinsics.stringPlus("Invalid dynamic table size update ", Integer.valueOf(this.b)));
                    }
                } else if (and != 16 && and != 0) {
                    l(readInt(and, 15) - 1);
                } else {
                    m();
                }
            }
        }

        public final int readInt(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int h = h();
                if ((h & 128) == 0) {
                    return i2 + (h << i4);
                }
                i2 += (h & 127) << i4;
                i4 += 7;
            }
        }

        public /* synthetic */ Reader(Source source, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(source, i, (i3 & 4) != 0 ? i : i2);
        }
    }

    /* loaded from: classes12.dex */
    public static final class Writer {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f14280a;
        @NotNull
        public final Buffer b;
        public int c;
        public boolean d;
        @JvmField
        @NotNull
        public Header[] dynamicTable;
        @JvmField
        public int dynamicTableByteCount;
        public int e;
        @JvmField
        public int headerCount;
        @JvmField
        public int headerTableSizeSetting;
        @JvmField
        public int maxDynamicTableByteCount;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Writer(int i, @NotNull Buffer out) {
            this(i, false, out, 2, null);
            Intrinsics.checkNotNullParameter(out, "out");
        }

        @JvmOverloads
        public Writer(int i, boolean z, @NotNull Buffer out) {
            Intrinsics.checkNotNullParameter(out, "out");
            this.headerTableSizeSetting = i;
            this.f14280a = z;
            this.b = out;
            this.c = Integer.MAX_VALUE;
            this.maxDynamicTableByteCount = i;
            Header[] headerArr = new Header[8];
            this.dynamicTable = headerArr;
            this.e = headerArr.length - 1;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Writer(@NotNull Buffer out) {
            this(0, false, out, 3, null);
            Intrinsics.checkNotNullParameter(out, "out");
        }

        public final void a() {
            int i = this.maxDynamicTableByteCount;
            int i2 = this.dynamicTableByteCount;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    c(i2 - i);
                }
            }
        }

        public final void b() {
            ArraysKt___ArraysJvmKt.fill$default(this.dynamicTable, (Object) null, 0, 0, 6, (Object) null);
            this.e = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        public final int c(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    i2 = this.e;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    Header header = this.dynamicTable[length];
                    Intrinsics.checkNotNull(header);
                    i -= header.hpackSize;
                    int i4 = this.dynamicTableByteCount;
                    Header header2 = this.dynamicTable[length];
                    Intrinsics.checkNotNull(header2);
                    this.dynamicTableByteCount = i4 - header2.hpackSize;
                    this.headerCount--;
                    i3++;
                }
                Header[] headerArr = this.dynamicTable;
                System.arraycopy(headerArr, i2 + 1, headerArr, i2 + 1 + i3, this.headerCount);
                Header[] headerArr2 = this.dynamicTable;
                int i5 = this.e;
                Arrays.fill(headerArr2, i5 + 1, i5 + 1 + i3, (Object) null);
                this.e += i3;
            }
            return i3;
        }

        public final void d(Header header) {
            int i = header.hpackSize;
            int i2 = this.maxDynamicTableByteCount;
            if (i > i2) {
                b();
                return;
            }
            c((this.dynamicTableByteCount + i) - i2);
            int i3 = this.headerCount + 1;
            Header[] headerArr = this.dynamicTable;
            if (i3 > headerArr.length) {
                Header[] headerArr2 = new Header[headerArr.length * 2];
                System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                this.e = this.dynamicTable.length - 1;
                this.dynamicTable = headerArr2;
            }
            int i4 = this.e;
            this.e = i4 - 1;
            this.dynamicTable[i4] = header;
            this.headerCount++;
            this.dynamicTableByteCount += i;
        }

        public final void resizeHeaderTable(int i) {
            this.headerTableSizeSetting = i;
            int min = Math.min(i, 16384);
            int i2 = this.maxDynamicTableByteCount;
            if (i2 == min) {
                return;
            }
            if (min < i2) {
                this.c = Math.min(this.c, min);
            }
            this.d = true;
            this.maxDynamicTableByteCount = min;
            a();
        }

        public final void writeByteString(@NotNull ByteString data) throws IOException {
            Intrinsics.checkNotNullParameter(data, "data");
            if (this.f14280a) {
                Huffman huffman = Huffman.INSTANCE;
                if (huffman.encodedLength(data) < data.size()) {
                    Buffer buffer = new Buffer();
                    huffman.encode(data, buffer);
                    ByteString readByteString = buffer.readByteString();
                    writeInt(readByteString.size(), 127, 128);
                    this.b.write(readByteString);
                    return;
                }
            }
            writeInt(data.size(), 127, 0);
            this.b.write(data);
        }

        public final void writeHeaders(@NotNull List<Header> headerBlock) throws IOException {
            int i;
            int i2;
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            if (this.d) {
                int i3 = this.c;
                if (i3 < this.maxDynamicTableByteCount) {
                    writeInt(i3, 31, 32);
                }
                this.d = false;
                this.c = Integer.MAX_VALUE;
                writeInt(this.maxDynamicTableByteCount, 31, 32);
            }
            int size = headerBlock.size();
            int i4 = 0;
            while (i4 < size) {
                int i5 = i4 + 1;
                Header header = headerBlock.get(i4);
                ByteString asciiLowercase = header.name.toAsciiLowercase();
                ByteString byteString = header.value;
                Hpack hpack = Hpack.INSTANCE;
                Integer num = hpack.getNAME_TO_FIRST_INDEX().get(asciiLowercase);
                if (num != null) {
                    i2 = num.intValue() + 1;
                    if (2 <= i2 && i2 < 8) {
                        if (Intrinsics.areEqual(hpack.getSTATIC_HEADER_TABLE()[i2 - 1].value, byteString)) {
                            i = i2;
                        } else if (Intrinsics.areEqual(hpack.getSTATIC_HEADER_TABLE()[i2].value, byteString)) {
                            i2++;
                            i = i2;
                        }
                    }
                    i = i2;
                    i2 = -1;
                } else {
                    i = -1;
                    i2 = -1;
                }
                if (i2 == -1) {
                    int i6 = this.e + 1;
                    int length = this.dynamicTable.length;
                    while (true) {
                        if (i6 >= length) {
                            break;
                        }
                        int i7 = i6 + 1;
                        Header header2 = this.dynamicTable[i6];
                        Intrinsics.checkNotNull(header2);
                        if (Intrinsics.areEqual(header2.name, asciiLowercase)) {
                            Header header3 = this.dynamicTable[i6];
                            Intrinsics.checkNotNull(header3);
                            if (Intrinsics.areEqual(header3.value, byteString)) {
                                i2 = Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length + (i6 - this.e);
                                break;
                            } else if (i == -1) {
                                i = Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length + (i6 - this.e);
                            }
                        }
                        i6 = i7;
                    }
                }
                if (i2 != -1) {
                    writeInt(i2, 127, 128);
                } else if (i == -1) {
                    this.b.writeByte(64);
                    writeByteString(asciiLowercase);
                    writeByteString(byteString);
                    d(header);
                } else if (asciiLowercase.startsWith(Header.PSEUDO_PREFIX) && !Intrinsics.areEqual(Header.TARGET_AUTHORITY, asciiLowercase)) {
                    writeInt(i, 15, 0);
                    writeByteString(byteString);
                } else {
                    writeInt(i, 63, 64);
                    writeByteString(byteString);
                    d(header);
                }
                i4 = i5;
            }
        }

        public final void writeInt(int i, int i2, int i3) {
            if (i < i2) {
                this.b.writeByte(i | i3);
                return;
            }
            this.b.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.b.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.b.writeByte(i4);
        }

        public /* synthetic */ Writer(int i, boolean z, Buffer buffer, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 4096 : i, (i2 & 2) != 0 ? true : z, buffer);
        }
    }
}
