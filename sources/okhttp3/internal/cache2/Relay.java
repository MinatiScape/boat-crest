package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Relay {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final ByteString PREFIX_CLEAN;
    @JvmField
    @NotNull
    public static final ByteString PREFIX_DIRTY;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public RandomAccessFile f14255a;
    @Nullable
    public Source b;
    public long c;
    @NotNull
    public final ByteString d;
    public final long e;
    @Nullable
    public Thread f;
    @NotNull
    public final Buffer g;
    public boolean h;
    @NotNull
    public final Buffer i;
    public int j;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Relay edit(@NotNull File file, @NotNull Source upstream, @NotNull ByteString metadata, long j) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(upstream, "upstream");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            Relay relay = new Relay(randomAccessFile, upstream, 0L, metadata, j, null);
            randomAccessFile.setLength(0L);
            relay.a(Relay.PREFIX_DIRTY, -1L, -1L);
            return relay;
        }

        @NotNull
        public final Relay read(@NotNull File file) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "randomAccessFile.channel");
            FileOperator fileOperator = new FileOperator(channel);
            Buffer buffer = new Buffer();
            fileOperator.read(0L, buffer, 32L);
            ByteString byteString = Relay.PREFIX_CLEAN;
            if (Intrinsics.areEqual(buffer.readByteString(byteString.size()), byteString)) {
                long readLong = buffer.readLong();
                long readLong2 = buffer.readLong();
                Buffer buffer2 = new Buffer();
                fileOperator.read(readLong + 32, buffer2, readLong2);
                return new Relay(randomAccessFile, null, readLong, buffer2.readByteString(), 0L, null);
            }
            throw new IOException("unreadable cache file");
        }
    }

    /* loaded from: classes12.dex */
    public final class RelaySource implements Source {
        @NotNull
        public final Timeout h;
        @Nullable
        public FileOperator i;
        public long j;
        public final /* synthetic */ Relay k;

        public RelaySource(Relay this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.k = this$0;
            this.h = new Timeout();
            RandomAccessFile file = this$0.getFile();
            Intrinsics.checkNotNull(file);
            FileChannel channel = file.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
            this.i = new FileOperator(channel);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.i == null) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            this.i = null;
            Relay relay = this.k;
            synchronized (relay) {
                relay.setSourceCount(relay.getSourceCount() - 1);
                if (relay.getSourceCount() == 0) {
                    RandomAccessFile file = relay.getFile();
                    relay.setFile(null);
                    randomAccessFile = file;
                }
                Unit unit = Unit.INSTANCE;
            }
            if (randomAccessFile == null) {
                return;
            }
            Util.closeQuietly(randomAccessFile);
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
            if (r4 != true) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x007b, code lost:
            r8 = java.lang.Math.min(r21, r19.k.getUpstreamPos() - r19.j);
            r2 = r19.i;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
            r2.read(r19.j + 32, r20, r8);
            r19.j += r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x009b, code lost:
            return r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x009d, code lost:
            r0 = r19.k.getUpstream();
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
            r14 = r0.read(r19.k.getUpstreamBuffer(), r19.k.getBufferMaxSize());
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x00b8, code lost:
            if (r14 != (-1)) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00ba, code lost:
            r0 = r19.k;
            r0.commit(r0.getUpstreamPos());
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00c3, code lost:
            r2 = r19.k;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00c5, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00c6, code lost:
            r2.setUpstreamReader(null);
            r2.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00ce, code lost:
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00cf, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00d3, code lost:
            r9 = java.lang.Math.min(r14, r21);
            r19.k.getUpstreamBuffer().copyTo(r20, 0, r9);
            r19.j += r9;
            r13 = r19.i;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
            r13.write(r19.k.getUpstreamPos() + 32, r19.k.getUpstreamBuffer().clone(), r14);
            r2 = r19.k;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x010a, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x010b, code lost:
            r2.getBuffer().write(r2.getUpstreamBuffer(), r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0124, code lost:
            if (r2.getBuffer().size() <= r2.getBufferMaxSize()) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0126, code lost:
            r2.getBuffer().skip(r2.getBuffer().size() - r2.getBufferMaxSize());
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x013a, code lost:
            r2.setUpstreamPos(r2.getUpstreamPos() + r14);
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0144, code lost:
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0145, code lost:
            r2 = r19.k;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0147, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0148, code lost:
            r2.setUpstreamReader(null);
            r2.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x014e, code lost:
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x014f, code lost:
            return r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0156, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0157, code lost:
            r2 = r19.k;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x0159, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x015a, code lost:
            r2.setUpstreamReader(null);
            r2.notifyAll();
            r3 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0163, code lost:
            throw r0;
         */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long read(@org.jetbrains.annotations.NotNull okio.Buffer r20, long r21) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 374
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        @Override // okio.Source
        @NotNull
        public Timeout timeout() {
            return this.h;
        }
    }

    static {
        ByteString.Companion companion = ByteString.Companion;
        PREFIX_CLEAN = companion.encodeUtf8("OkHttp cache v1\n");
        PREFIX_DIRTY = companion.encodeUtf8("OkHttp DIRTY :(\n");
    }

    public Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2) {
        this.f14255a = randomAccessFile;
        this.b = source;
        this.c = j;
        this.d = byteString;
        this.e = j2;
        this.g = new Buffer();
        this.h = this.b == null;
        this.i = new Buffer();
    }

    public /* synthetic */ Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(randomAccessFile, source, j, byteString, j2);
    }

    public final void a(ByteString byteString, long j, long j2) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(byteString);
        buffer.writeLong(j);
        buffer.writeLong(j2);
        if (buffer.size() == 32) {
            RandomAccessFile randomAccessFile = this.f14255a;
            Intrinsics.checkNotNull(randomAccessFile);
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
            new FileOperator(channel).write(0L, buffer, 32L);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final void b(long j) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(this.d);
        RandomAccessFile randomAccessFile = this.f14255a;
        Intrinsics.checkNotNull(randomAccessFile);
        FileChannel channel = randomAccessFile.getChannel();
        Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
        new FileOperator(channel).write(32 + j, buffer, this.d.size());
    }

    public final void commit(long j) throws IOException {
        b(j);
        RandomAccessFile randomAccessFile = this.f14255a;
        Intrinsics.checkNotNull(randomAccessFile);
        randomAccessFile.getChannel().force(false);
        a(PREFIX_CLEAN, j, this.d.size());
        RandomAccessFile randomAccessFile2 = this.f14255a;
        Intrinsics.checkNotNull(randomAccessFile2);
        randomAccessFile2.getChannel().force(false);
        synchronized (this) {
            setComplete(true);
            Unit unit = Unit.INSTANCE;
        }
        Source source = this.b;
        if (source != null) {
            Util.closeQuietly(source);
        }
        this.b = null;
    }

    @NotNull
    public final Buffer getBuffer() {
        return this.i;
    }

    public final long getBufferMaxSize() {
        return this.e;
    }

    public final boolean getComplete() {
        return this.h;
    }

    @Nullable
    public final RandomAccessFile getFile() {
        return this.f14255a;
    }

    public final int getSourceCount() {
        return this.j;
    }

    @Nullable
    public final Source getUpstream() {
        return this.b;
    }

    @NotNull
    public final Buffer getUpstreamBuffer() {
        return this.g;
    }

    public final long getUpstreamPos() {
        return this.c;
    }

    @Nullable
    public final Thread getUpstreamReader() {
        return this.f;
    }

    public final boolean isClosed() {
        return this.f14255a == null;
    }

    @NotNull
    public final ByteString metadata() {
        return this.d;
    }

    @Nullable
    public final Source newSource() {
        synchronized (this) {
            if (getFile() == null) {
                return null;
            }
            setSourceCount(getSourceCount() + 1);
            return new RelaySource(this);
        }
    }

    public final void setComplete(boolean z) {
        this.h = z;
    }

    public final void setFile(@Nullable RandomAccessFile randomAccessFile) {
        this.f14255a = randomAccessFile;
    }

    public final void setSourceCount(int i) {
        this.j = i;
    }

    public final void setUpstream(@Nullable Source source) {
        this.b = source;
    }

    public final void setUpstreamPos(long j) {
        this.c = j;
    }

    public final void setUpstreamReader(@Nullable Thread thread) {
        this.f = thread;
    }
}
