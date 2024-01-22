package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.s;
import kotlin.comparisons.f;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.ZipFileSystem;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ZipKt {
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1<ZipEntry, Boolean> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Boolean invoke(@NotNull ZipEntry it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Boolean.TRUE;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function2<Integer, Long, Unit> {
        public final /* synthetic */ Ref.LongRef $compressedSize;
        public final /* synthetic */ Ref.BooleanRef $hasZip64Extra;
        public final /* synthetic */ Ref.LongRef $offset;
        public final /* synthetic */ long $requiredZip64ExtraSize;
        public final /* synthetic */ Ref.LongRef $size;
        public final /* synthetic */ BufferedSource $this_readEntry;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Ref.BooleanRef booleanRef, long j, Ref.LongRef longRef, BufferedSource bufferedSource, Ref.LongRef longRef2, Ref.LongRef longRef3) {
            super(2);
            this.$hasZip64Extra = booleanRef;
            this.$requiredZip64ExtraSize = j;
            this.$size = longRef;
            this.$this_readEntry = bufferedSource;
            this.$compressedSize = longRef2;
            this.$offset = longRef3;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) {
            invoke(num.intValue(), l.longValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i, long j) {
            if (i == 1) {
                Ref.BooleanRef booleanRef = this.$hasZip64Extra;
                if (!booleanRef.element) {
                    booleanRef.element = true;
                    if (j >= this.$requiredZip64ExtraSize) {
                        Ref.LongRef longRef = this.$size;
                        long j2 = longRef.element;
                        if (j2 == 4294967295L) {
                            j2 = this.$this_readEntry.readLongLe();
                        }
                        longRef.element = j2;
                        Ref.LongRef longRef2 = this.$compressedSize;
                        longRef2.element = longRef2.element == 4294967295L ? this.$this_readEntry.readLongLe() : 0L;
                        Ref.LongRef longRef3 = this.$offset;
                        longRef3.element = longRef3.element == 4294967295L ? this.$this_readEntry.readLongLe() : 0L;
                        return;
                    }
                    throw new IOException("bad zip: zip64 extra too short");
                }
                throw new IOException("bad zip: zip64 extra repeated");
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Lambda implements Function2<Integer, Long, Unit> {
        public final /* synthetic */ Ref.ObjectRef<Long> $createdAtMillis;
        public final /* synthetic */ Ref.ObjectRef<Long> $lastAccessedAtMillis;
        public final /* synthetic */ Ref.ObjectRef<Long> $lastModifiedAtMillis;
        public final /* synthetic */ BufferedSource $this_readOrSkipLocalHeader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(BufferedSource bufferedSource, Ref.ObjectRef<Long> objectRef, Ref.ObjectRef<Long> objectRef2, Ref.ObjectRef<Long> objectRef3) {
            super(2);
            this.$this_readOrSkipLocalHeader = bufferedSource;
            this.$lastModifiedAtMillis = objectRef;
            this.$lastAccessedAtMillis = objectRef2;
            this.$createdAtMillis = objectRef3;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) {
            invoke(num.intValue(), l.longValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference failed for: r0v13, types: [T, java.lang.Long] */
        /* JADX WARN: Type inference failed for: r10v12, types: [T, java.lang.Long] */
        /* JADX WARN: Type inference failed for: r11v3, types: [T, java.lang.Long] */
        public final void invoke(int i, long j) {
            if (i == 21589) {
                if (j >= 1) {
                    int readByte = this.$this_readOrSkipLocalHeader.readByte() & 255;
                    boolean z = (readByte & 1) == 1;
                    boolean z2 = (readByte & 2) == 2;
                    boolean z3 = (readByte & 4) == 4;
                    BufferedSource bufferedSource = this.$this_readOrSkipLocalHeader;
                    long j2 = z ? 5L : 1L;
                    if (z2) {
                        j2 += 4;
                    }
                    if (z3) {
                        j2 += 4;
                    }
                    if (j < j2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        this.$lastModifiedAtMillis.element = Long.valueOf(bufferedSource.readIntLe() * 1000);
                    }
                    if (z2) {
                        this.$lastAccessedAtMillis.element = Long.valueOf(this.$this_readOrSkipLocalHeader.readIntLe() * 1000);
                    }
                    if (z3) {
                        this.$createdAtMillis.element = Long.valueOf(this.$this_readOrSkipLocalHeader.readIntLe() * 1000);
                        return;
                    }
                    return;
                }
                throw new IOException("bad zip: extended timestamp extra too short");
            }
        }
    }

    public static final Map<Path, ZipEntry> a(List<ZipEntry> list) {
        Path path = Path.Companion.get$default(Path.Companion, MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 1, (Object) null);
        Map<Path, ZipEntry> mutableMapOf = s.mutableMapOf(TuplesKt.to(path, new ZipEntry(path, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null)));
        for (ZipEntry zipEntry : CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: okio.internal.ZipKt$buildIndex$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(((ZipEntry) t).getCanonicalPath(), ((ZipEntry) t2).getCanonicalPath());
            }
        })) {
            if (mutableMapOf.put(zipEntry.getCanonicalPath(), zipEntry) == null) {
                while (true) {
                    Path parent = zipEntry.getCanonicalPath().parent();
                    if (parent != null) {
                        ZipEntry zipEntry2 = mutableMapOf.get(parent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(parent, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null);
                        mutableMapOf.put(parent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                    }
                }
            }
        }
        return mutableMapOf;
    }

    public static final Long b(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        gregorianCalendar.set(((i >> 9) & 127) + 1980, ((i >> 5) & 15) - 1, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    public static final String c(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(HexStringBuilder.DEFAULT_PREFIX);
        String num = Integer.toString(i, kotlin.text.a.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        sb.append(num);
        return sb.toString();
    }

    public static final okio.internal.a d(BufferedSource bufferedSource) throws IOException {
        int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        long readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if (readShortLe3 == (bufferedSource.readShortLe() & UShort.MAX_VALUE) && readShortLe == 0 && readShortLe2 == 0) {
            bufferedSource.skip(4L);
            return new okio.internal.a(readShortLe3, 4294967295L & bufferedSource.readIntLe(), bufferedSource.readShortLe() & UShort.MAX_VALUE);
        }
        throw new IOException("unsupported zip: spanned");
    }

    public static final void e(BufferedSource bufferedSource, int i, Function2<? super Integer, ? super Long, Unit> function2) {
        long j = i;
        while (j != 0) {
            if (j >= 4) {
                int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                long readShortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                long j2 = j - 4;
                if (j2 >= readShortLe2) {
                    bufferedSource.require(readShortLe2);
                    long size = bufferedSource.getBuffer().size();
                    function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
                    long size2 = (bufferedSource.getBuffer().size() + readShortLe2) - size;
                    int i2 = (size2 > 0L ? 1 : (size2 == 0L ? 0 : -1));
                    if (i2 < 0) {
                        throw new IOException("unsupported zip: too many bytes processed for " + readShortLe);
                    }
                    if (i2 > 0) {
                        bufferedSource.getBuffer().skip(size2);
                    }
                    j = j2 - readShortLe2;
                } else {
                    throw new IOException("bad zip: truncated value in extra field");
                }
            } else {
                throw new IOException("bad zip: truncated header in extra field");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FileMetadata f(BufferedSource bufferedSource, FileMetadata fileMetadata) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = fileMetadata != null ? fileMetadata.getLastModifiedAtMillis() : 0;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe == 67324752) {
            bufferedSource.skip(2L);
            int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            if ((readShortLe & 1) == 0) {
                bufferedSource.skip(18L);
                long readShortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                int readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                bufferedSource.skip(readShortLe2);
                if (fileMetadata == null) {
                    bufferedSource.skip(readShortLe3);
                    return null;
                }
                e(bufferedSource, readShortLe3, new c(bufferedSource, objectRef, objectRef2, objectRef3));
                return new FileMetadata(fileMetadata.isRegularFile(), fileMetadata.isDirectory(), null, fileMetadata.getSize(), (Long) objectRef3.element, (Long) objectRef.element, (Long) objectRef2.element, null, 128, null);
            }
            throw new IOException("unsupported zip: general purpose bit flag=" + c(readShortLe));
        }
        throw new IOException("bad zip: expected " + c(67324752) + " but was " + c(readIntLe));
    }

    public static final okio.internal.a g(BufferedSource bufferedSource, okio.internal.a aVar) throws IOException {
        bufferedSource.skip(12L);
        int readIntLe = bufferedSource.readIntLe();
        int readIntLe2 = bufferedSource.readIntLe();
        long readLongLe = bufferedSource.readLongLe();
        if (readLongLe == bufferedSource.readLongLe() && readIntLe == 0 && readIntLe2 == 0) {
            bufferedSource.skip(8L);
            return new okio.internal.a(readLongLe, bufferedSource.readLongLe(), aVar.b());
        }
        throw new IOException("unsupported zip: spanned");
    }

    @NotNull
    public static final ZipFileSystem openZip(@NotNull Path zipPath, @NotNull FileSystem fileSystem, @NotNull Function1<? super ZipEntry, Boolean> predicate) throws IOException {
        int readIntLe;
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        FileHandle openReadOnly = fileSystem.openReadOnly(zipPath);
        try {
            long size = openReadOnly.size() - 22;
            if (size >= 0) {
                long max = Math.max(size - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, 0L);
                while (true) {
                    BufferedSource buffer = Okio.buffer(openReadOnly.source(size));
                    if (buffer.readIntLe() == 101010256) {
                        okio.internal.a d = d(buffer);
                        String readUtf8 = buffer.readUtf8(d.b());
                        buffer.close();
                        long j = size - 20;
                        if (j > 0) {
                            BufferedSource buffer2 = Okio.buffer(openReadOnly.source(j));
                            if (buffer2.readIntLe() == 117853008) {
                                int readIntLe2 = buffer2.readIntLe();
                                long readLongLe = buffer2.readLongLe();
                                if (buffer2.readIntLe() == 1 && readIntLe2 == 0) {
                                    BufferedSource buffer3 = Okio.buffer(openReadOnly.source(readLongLe));
                                    try {
                                        if (buffer3.readIntLe() == 101075792) {
                                            d = g(buffer3, d);
                                            Unit unit = Unit.INSTANCE;
                                            CloseableKt.closeFinally(buffer3, null);
                                        } else {
                                            throw new IOException("bad zip: expected " + c(101075792) + " but was " + c(readIntLe));
                                        }
                                    } finally {
                                    }
                                } else {
                                    throw new IOException("unsupported zip: spanned");
                                }
                            }
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer2, null);
                        }
                        ArrayList arrayList = new ArrayList();
                        BufferedSource buffer4 = Okio.buffer(openReadOnly.source(d.a()));
                        long c2 = d.c();
                        for (long j2 = 0; j2 < c2; j2++) {
                            ZipEntry readEntry = readEntry(buffer4);
                            if (readEntry.getOffset() < d.a()) {
                                if (predicate.invoke(readEntry).booleanValue()) {
                                    arrayList.add(readEntry);
                                }
                            } else {
                                throw new IOException("bad zip: local file header offset >= central directory offset");
                            }
                        }
                        Unit unit3 = Unit.INSTANCE;
                        CloseableKt.closeFinally(buffer4, null);
                        ZipFileSystem zipFileSystem = new ZipFileSystem(zipPath, fileSystem, a(arrayList), readUtf8);
                        CloseableKt.closeFinally(openReadOnly, null);
                        return zipFileSystem;
                    }
                    buffer.close();
                    size--;
                    if (size < max) {
                        throw new IOException("not a zip: end of central directory signature not found");
                    }
                }
            } else {
                throw new IOException("not a zip: size=" + openReadOnly.size());
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(openReadOnly, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = a.INSTANCE;
        }
        return openZip(path, fileSystem, function1);
    }

    @NotNull
    public static final ZipEntry readEntry(@NotNull BufferedSource bufferedSource) throws IOException {
        int readIntLe;
        int readShortLe;
        Ref.LongRef longRef;
        long j;
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        if (bufferedSource.readIntLe() == 33639248) {
            bufferedSource.skip(4L);
            if ((bufferedSource.readShortLe() & UShort.MAX_VALUE & 1) == 0) {
                int readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                Long b2 = b(bufferedSource.readShortLe() & UShort.MAX_VALUE, bufferedSource.readShortLe() & UShort.MAX_VALUE);
                long readIntLe2 = bufferedSource.readIntLe() & 4294967295L;
                Ref.LongRef longRef2 = new Ref.LongRef();
                longRef2.element = bufferedSource.readIntLe() & 4294967295L;
                Ref.LongRef longRef3 = new Ref.LongRef();
                longRef3.element = bufferedSource.readIntLe() & 4294967295L;
                int readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                int readShortLe4 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                int readShortLe5 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                bufferedSource.skip(8L);
                Ref.LongRef longRef4 = new Ref.LongRef();
                longRef4.element = bufferedSource.readIntLe() & 4294967295L;
                String readUtf8 = bufferedSource.readUtf8(readShortLe3);
                if (!StringsKt__StringsKt.contains$default((CharSequence) readUtf8, (char) 0, false, 2, (Object) null)) {
                    if (longRef3.element == 4294967295L) {
                        j = 8 + 0;
                        longRef = longRef4;
                    } else {
                        longRef = longRef4;
                        j = 0;
                    }
                    if (longRef2.element == 4294967295L) {
                        j += 8;
                    }
                    Ref.LongRef longRef5 = longRef;
                    if (longRef5.element == 4294967295L) {
                        j += 8;
                    }
                    long j2 = j;
                    Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    e(bufferedSource, readShortLe4, new b(booleanRef, j2, longRef3, bufferedSource, longRef2, longRef5));
                    if (j2 > 0 && !booleanRef.element) {
                        throw new IOException("bad zip: zip64 extra required but absent");
                    }
                    return new ZipEntry(Path.Companion.get$default(Path.Companion, MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 1, (Object) null).resolve(readUtf8), m.endsWith$default(readUtf8, MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 2, null), bufferedSource.readUtf8(readShortLe5), readIntLe2, longRef2.element, longRef3.element, readShortLe2, b2, longRef5.element);
                }
                throw new IOException("bad zip: filename contains 0x00");
            }
            throw new IOException("unsupported zip: general purpose bit flag=" + c(readShortLe));
        }
        throw new IOException("bad zip: expected " + c(33639248) + " but was " + c(readIntLe));
    }

    @NotNull
    public static final FileMetadata readLocalHeader(@NotNull BufferedSource bufferedSource, @NotNull FileMetadata basicMetadata) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
        FileMetadata f = f(bufferedSource, basicMetadata);
        Intrinsics.checkNotNull(f);
        return f;
    }

    public static final void skipLocalHeader(@NotNull BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        f(bufferedSource, null);
    }
}
