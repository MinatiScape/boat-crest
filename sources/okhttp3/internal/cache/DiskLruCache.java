package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import kotlin.text.m;
import okhttp3.internal.Util;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class DiskLruCache implements Closeable, Flushable {
    @NotNull
    public final TaskQueue A;
    @NotNull
    public final DiskLruCache$cleanupTask$1 B;
    @NotNull
    public final FileSystem h;
    @NotNull
    public final File i;
    public final int j;
    public final int k;
    public long l;
    @NotNull
    public final File m;
    @NotNull
    public final File n;
    @NotNull
    public final File o;
    public long p;
    @Nullable
    public BufferedSink q;
    @NotNull
    public final LinkedHashMap<String, Entry> r;
    public int s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public long z;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE = "journal";
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_TEMP = "journal.tmp";
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    @JvmField
    @NotNull
    public static final String MAGIC = "libcore.io.DiskLruCache";
    @JvmField
    @NotNull
    public static final String VERSION_1 = "1";
    @JvmField
    public static final long ANY_SEQUENCE_NUMBER = -1;
    @JvmField
    @NotNull
    public static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
    @JvmField
    @NotNull
    public static final String CLEAN = "CLEAN";
    @JvmField
    @NotNull
    public static final String DIRTY = "DIRTY";
    @JvmField
    @NotNull
    public static final String REMOVE = "REMOVE";
    @JvmField
    @NotNull
    public static final String READ = "READ";

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public final class Editor {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final Entry f14252a;
        @Nullable
        public final boolean[] b;
        public boolean c;
        public final /* synthetic */ DiskLruCache d;

        /* loaded from: classes12.dex */
        public static final class a extends Lambda implements Function1<IOException, Unit> {
            public final /* synthetic */ DiskLruCache this$0;
            public final /* synthetic */ Editor this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(DiskLruCache diskLruCache, Editor editor) {
                super(1);
                this.this$0 = diskLruCache;
                this.this$1 = editor;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IOException iOException) {
                invoke2(iOException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull IOException it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DiskLruCache diskLruCache = this.this$0;
                Editor editor = this.this$1;
                synchronized (diskLruCache) {
                    editor.detach$okhttp();
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        public Editor(@NotNull DiskLruCache this$0, Entry entry) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(entry, "entry");
            this.d = this$0;
            this.f14252a = entry;
            this.b = entry.getReadable$okhttp() ? null : new boolean[this$0.getValueCount$okhttp()];
        }

        public final void abort() throws IOException {
            DiskLruCache diskLruCache = this.d;
            synchronized (diskLruCache) {
                if (!this.c) {
                    if (Intrinsics.areEqual(getEntry$okhttp().getCurrentEditor$okhttp(), this)) {
                        diskLruCache.completeEdit$okhttp(this, false);
                    }
                    this.c = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }

        public final void commit() throws IOException {
            DiskLruCache diskLruCache = this.d;
            synchronized (diskLruCache) {
                if (!this.c) {
                    if (Intrinsics.areEqual(getEntry$okhttp().getCurrentEditor$okhttp(), this)) {
                        diskLruCache.completeEdit$okhttp(this, true);
                    }
                    this.c = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }

        public final void detach$okhttp() {
            if (Intrinsics.areEqual(this.f14252a.getCurrentEditor$okhttp(), this)) {
                if (this.d.u) {
                    this.d.completeEdit$okhttp(this, false);
                } else {
                    this.f14252a.setZombie$okhttp(true);
                }
            }
        }

        @NotNull
        public final Entry getEntry$okhttp() {
            return this.f14252a;
        }

        @Nullable
        public final boolean[] getWritten$okhttp() {
            return this.b;
        }

        @NotNull
        public final Sink newSink(int i) {
            DiskLruCache diskLruCache = this.d;
            synchronized (diskLruCache) {
                if (!this.c) {
                    if (!Intrinsics.areEqual(getEntry$okhttp().getCurrentEditor$okhttp(), this)) {
                        return Okio.blackhole();
                    }
                    if (!getEntry$okhttp().getReadable$okhttp()) {
                        boolean[] written$okhttp = getWritten$okhttp();
                        Intrinsics.checkNotNull(written$okhttp);
                        written$okhttp[i] = true;
                    }
                    try {
                        return new FaultHidingSink(diskLruCache.getFileSystem$okhttp().sink(getEntry$okhttp().getDirtyFiles$okhttp().get(i)), new a(diskLruCache, this));
                    } catch (FileNotFoundException unused) {
                        return Okio.blackhole();
                    }
                }
                throw new IllegalStateException("Check failed.".toString());
            }
        }

        @Nullable
        public final Source newSource(int i) {
            DiskLruCache diskLruCache = this.d;
            synchronized (diskLruCache) {
                if (!this.c) {
                    Source source = null;
                    if (getEntry$okhttp().getReadable$okhttp() && Intrinsics.areEqual(getEntry$okhttp().getCurrentEditor$okhttp(), this) && !getEntry$okhttp().getZombie$okhttp()) {
                        try {
                            source = diskLruCache.getFileSystem$okhttp().source(getEntry$okhttp().getCleanFiles$okhttp().get(i));
                        } catch (FileNotFoundException unused) {
                        }
                        return source;
                    }
                    return null;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }

    /* loaded from: classes12.dex */
    public final class Entry {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f14253a;
        @NotNull
        public final long[] b;
        @NotNull
        public final List<File> c;
        @NotNull
        public final List<File> d;
        public boolean e;
        public boolean f;
        @Nullable
        public Editor g;
        public int h;
        public long i;
        public final /* synthetic */ DiskLruCache j;

        public Entry(@NotNull DiskLruCache this$0, String key) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(key, "key");
            this.j = this$0;
            this.f14253a = key;
            this.b = new long[this$0.getValueCount$okhttp()];
            this.c = new ArrayList();
            this.d = new ArrayList();
            StringBuilder sb = new StringBuilder(key);
            sb.append('.');
            int length = sb.length();
            int valueCount$okhttp = this$0.getValueCount$okhttp();
            for (int i = 0; i < valueCount$okhttp; i++) {
                sb.append(i);
                this.c.add(new File(this.j.getDirectory(), sb.toString()));
                sb.append(".tmp");
                this.d.add(new File(this.j.getDirectory(), sb.toString()));
                sb.setLength(length);
            }
        }

        public final Void a(List<String> list) throws IOException {
            throw new IOException(Intrinsics.stringPlus("unexpected journal line: ", list));
        }

        public final Source b(int i) {
            final Source source = this.j.getFileSystem$okhttp().source(this.c.get(i));
            if (this.j.u) {
                return source;
            }
            this.h++;
            final DiskLruCache diskLruCache = this.j;
            return new ForwardingSource(diskLruCache, this) { // from class: okhttp3.internal.cache.DiskLruCache$Entry$newSource$1
                public boolean i;
                public final /* synthetic */ DiskLruCache k;
                public final /* synthetic */ DiskLruCache.Entry l;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(Source.this);
                    this.k = diskLruCache;
                    this.l = this;
                }

                @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    super.close();
                    if (this.i) {
                        return;
                    }
                    this.i = true;
                    DiskLruCache diskLruCache2 = this.k;
                    DiskLruCache.Entry entry = this.l;
                    synchronized (diskLruCache2) {
                        entry.setLockingSourceCount$okhttp(entry.getLockingSourceCount$okhttp() - 1);
                        if (entry.getLockingSourceCount$okhttp() == 0 && entry.getZombie$okhttp()) {
                            diskLruCache2.removeEntry$okhttp(entry);
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
            };
        }

        @NotNull
        public final List<File> getCleanFiles$okhttp() {
            return this.c;
        }

        @Nullable
        public final Editor getCurrentEditor$okhttp() {
            return this.g;
        }

        @NotNull
        public final List<File> getDirtyFiles$okhttp() {
            return this.d;
        }

        @NotNull
        public final String getKey$okhttp() {
            return this.f14253a;
        }

        @NotNull
        public final long[] getLengths$okhttp() {
            return this.b;
        }

        public final int getLockingSourceCount$okhttp() {
            return this.h;
        }

        public final boolean getReadable$okhttp() {
            return this.e;
        }

        public final long getSequenceNumber$okhttp() {
            return this.i;
        }

        public final boolean getZombie$okhttp() {
            return this.f;
        }

        public final void setCurrentEditor$okhttp(@Nullable Editor editor) {
            this.g = editor;
        }

        public final void setLengths$okhttp(@NotNull List<String> strings) throws IOException {
            Intrinsics.checkNotNullParameter(strings, "strings");
            if (strings.size() == this.j.getValueCount$okhttp()) {
                int i = 0;
                try {
                    int size = strings.size();
                    while (i < size) {
                        int i2 = i + 1;
                        this.b[i] = Long.parseLong(strings.get(i));
                        i = i2;
                    }
                    return;
                } catch (NumberFormatException unused) {
                    a(strings);
                    throw new KotlinNothingValueException();
                }
            }
            a(strings);
            throw new KotlinNothingValueException();
        }

        public final void setLockingSourceCount$okhttp(int i) {
            this.h = i;
        }

        public final void setReadable$okhttp(boolean z) {
            this.e = z;
        }

        public final void setSequenceNumber$okhttp(long j) {
            this.i = j;
        }

        public final void setZombie$okhttp(boolean z) {
            this.f = z;
        }

        @Nullable
        public final Snapshot snapshot$okhttp() {
            DiskLruCache diskLruCache = this.j;
            if (Util.assertionsEnabled && !Thread.holdsLock(diskLruCache)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + diskLruCache);
            } else if (this.e) {
                if (this.j.u || (this.g == null && !this.f)) {
                    ArrayList<Source> arrayList = new ArrayList();
                    long[] jArr = (long[]) this.b.clone();
                    try {
                        int valueCount$okhttp = this.j.getValueCount$okhttp();
                        for (int i = 0; i < valueCount$okhttp; i++) {
                            arrayList.add(b(i));
                        }
                        return new Snapshot(this.j, this.f14253a, this.i, arrayList, jArr);
                    } catch (FileNotFoundException unused) {
                        for (Source source : arrayList) {
                            Util.closeQuietly(source);
                        }
                        try {
                            this.j.removeEntry$okhttp(this);
                        } catch (IOException unused2) {
                        }
                        return null;
                    }
                }
                return null;
            } else {
                return null;
            }
        }

        public final void writeLengths$okhttp(@NotNull BufferedSink writer) throws IOException {
            Intrinsics.checkNotNullParameter(writer, "writer");
            long[] jArr = this.b;
            int length = jArr.length;
            int i = 0;
            while (i < length) {
                long j = jArr[i];
                i++;
                writer.writeByte(32).writeDecimalLong(j);
            }
        }
    }

    /* loaded from: classes12.dex */
    public final class Snapshot implements Closeable {
        @NotNull
        public final String h;
        public final long i;
        @NotNull
        public final List<Source> j;
        @NotNull
        public final long[] k;
        public final /* synthetic */ DiskLruCache l;

        /* JADX WARN: Multi-variable type inference failed */
        public Snapshot(@NotNull DiskLruCache this$0, String key, @NotNull long j, @NotNull List<? extends Source> sources, long[] lengths) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(sources, "sources");
            Intrinsics.checkNotNullParameter(lengths, "lengths");
            this.l = this$0;
            this.h = key;
            this.i = j;
            this.j = sources;
            this.k = lengths;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (Source source : this.j) {
                Util.closeQuietly(source);
            }
        }

        @Nullable
        public final Editor edit() throws IOException {
            return this.l.edit(this.h, this.i);
        }

        public final long getLength(int i) {
            return this.k[i];
        }

        @NotNull
        public final Source getSource(int i) {
            return this.j.get(i);
        }

        @NotNull
        public final String key() {
            return this.h;
        }
    }

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1<IOException, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(IOException iOException) {
            invoke2(iOException);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull IOException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            DiskLruCache diskLruCache = DiskLruCache.this;
            if (!Util.assertionsEnabled || Thread.holdsLock(diskLruCache)) {
                DiskLruCache.this.t = true;
                return;
            }
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + diskLruCache);
        }
    }

    /* JADX WARN: Type inference failed for: r11v2, types: [okhttp3.internal.cache.DiskLruCache$cleanupTask$1] */
    public DiskLruCache(@NotNull FileSystem fileSystem, @NotNull File directory, int i, int i2, long j, @NotNull TaskRunner taskRunner) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        this.h = fileSystem;
        this.i = directory;
        this.j = i;
        this.k = i2;
        this.l = j;
        this.r = new LinkedHashMap<>(0, 0.75f, true);
        this.A = taskRunner.newQueue();
        final String stringPlus = Intrinsics.stringPlus(Util.okHttpName, " Cache");
        this.B = new Task(stringPlus) { // from class: okhttp3.internal.cache.DiskLruCache$cleanupTask$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                boolean z;
                boolean b;
                DiskLruCache diskLruCache = DiskLruCache.this;
                synchronized (diskLruCache) {
                    z = diskLruCache.v;
                    if (!z || diskLruCache.getClosed$okhttp()) {
                        return -1L;
                    }
                    try {
                        diskLruCache.trimToSize();
                    } catch (IOException unused) {
                        diskLruCache.x = true;
                    }
                    try {
                        b = diskLruCache.b();
                        if (b) {
                            diskLruCache.rebuildJournal$okhttp();
                            diskLruCache.s = 0;
                        }
                    } catch (IOException unused2) {
                        diskLruCache.y = true;
                        diskLruCache.q = Okio.buffer(Okio.blackhole());
                    }
                    return -1L;
                }
            }
        };
        if (!(j > 0)) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        if (i2 > 0) {
            this.m = new File(directory, JOURNAL_FILE);
            this.n = new File(directory, JOURNAL_FILE_TEMP);
            this.o = new File(directory, JOURNAL_FILE_BACKUP);
            return;
        }
        throw new IllegalArgumentException("valueCount <= 0".toString());
    }

    public static /* synthetic */ Editor edit$default(DiskLruCache diskLruCache, String str, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache.edit(str, j);
    }

    public final synchronized void a() {
        if (!(!this.w)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    public final boolean b() {
        int i = this.s;
        return i >= 2000 && i >= this.r.size();
    }

    public final BufferedSink c() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.h.appendingSink(this.m), new a()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        Editor currentEditor$okhttp;
        if (this.v && !this.w) {
            Collection<Entry> values = this.r.values();
            Intrinsics.checkNotNullExpressionValue(values, "lruEntries.values");
            int i = 0;
            Object[] array = values.toArray(new Entry[0]);
            if (array != null) {
                Entry[] entryArr = (Entry[]) array;
                int length = entryArr.length;
                while (i < length) {
                    Entry entry = entryArr[i];
                    i++;
                    if (entry.getCurrentEditor$okhttp() != null && (currentEditor$okhttp = entry.getCurrentEditor$okhttp()) != null) {
                        currentEditor$okhttp.detach$okhttp();
                    }
                }
                trimToSize();
                BufferedSink bufferedSink = this.q;
                Intrinsics.checkNotNull(bufferedSink);
                bufferedSink.close();
                this.q = null;
                this.w = true;
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        this.w = true;
    }

    public final synchronized void completeEdit$okhttp(@NotNull Editor editor, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(editor, "editor");
        Entry entry$okhttp = editor.getEntry$okhttp();
        if (Intrinsics.areEqual(entry$okhttp.getCurrentEditor$okhttp(), editor)) {
            int i = 0;
            if (z && !entry$okhttp.getReadable$okhttp()) {
                int i2 = this.k;
                int i3 = 0;
                while (i3 < i2) {
                    int i4 = i3 + 1;
                    boolean[] written$okhttp = editor.getWritten$okhttp();
                    Intrinsics.checkNotNull(written$okhttp);
                    if (written$okhttp[i3]) {
                        if (!this.h.exists(entry$okhttp.getDirtyFiles$okhttp().get(i3))) {
                            editor.abort();
                            return;
                        }
                        i3 = i4;
                    } else {
                        editor.abort();
                        throw new IllegalStateException(Intrinsics.stringPlus("Newly created entry didn't create value for index ", Integer.valueOf(i3)));
                    }
                }
            }
            int i5 = this.k;
            while (i < i5) {
                int i6 = i + 1;
                File file = entry$okhttp.getDirtyFiles$okhttp().get(i);
                if (z && !entry$okhttp.getZombie$okhttp()) {
                    if (this.h.exists(file)) {
                        File file2 = entry$okhttp.getCleanFiles$okhttp().get(i);
                        this.h.rename(file, file2);
                        long j = entry$okhttp.getLengths$okhttp()[i];
                        long size = this.h.size(file2);
                        entry$okhttp.getLengths$okhttp()[i] = size;
                        this.p = (this.p - j) + size;
                    }
                } else {
                    this.h.delete(file);
                }
                i = i6;
            }
            entry$okhttp.setCurrentEditor$okhttp(null);
            if (entry$okhttp.getZombie$okhttp()) {
                removeEntry$okhttp(entry$okhttp);
                return;
            }
            this.s++;
            BufferedSink bufferedSink = this.q;
            Intrinsics.checkNotNull(bufferedSink);
            if (!entry$okhttp.getReadable$okhttp() && !z) {
                getLruEntries$okhttp().remove(entry$okhttp.getKey$okhttp());
                bufferedSink.writeUtf8(REMOVE).writeByte(32);
                bufferedSink.writeUtf8(entry$okhttp.getKey$okhttp());
                bufferedSink.writeByte(10);
                bufferedSink.flush();
                if (this.p <= this.l || b()) {
                    TaskQueue.schedule$default(this.A, this.B, 0L, 2, null);
                }
                return;
            }
            entry$okhttp.setReadable$okhttp(true);
            bufferedSink.writeUtf8(CLEAN).writeByte(32);
            bufferedSink.writeUtf8(entry$okhttp.getKey$okhttp());
            entry$okhttp.writeLengths$okhttp(bufferedSink);
            bufferedSink.writeByte(10);
            if (z) {
                long j2 = this.z;
                this.z = 1 + j2;
                entry$okhttp.setSequenceNumber$okhttp(j2);
            }
            bufferedSink.flush();
            if (this.p <= this.l) {
            }
            TaskQueue.schedule$default(this.A, this.B, 0L, 2, null);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void d() throws IOException {
        this.h.delete(this.n);
        Iterator<Entry> it = this.r.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "i.next()");
            Entry entry = next;
            int i = 0;
            if (entry.getCurrentEditor$okhttp() == null) {
                int i2 = this.k;
                while (i < i2) {
                    this.p += entry.getLengths$okhttp()[i];
                    i++;
                }
            } else {
                entry.setCurrentEditor$okhttp(null);
                int i3 = this.k;
                while (i < i3) {
                    this.h.delete(entry.getCleanFiles$okhttp().get(i));
                    this.h.delete(entry.getDirtyFiles$okhttp().get(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    public final void delete() throws IOException {
        close();
        this.h.deleteContents(this.i);
    }

    public final void e() throws IOException {
        BufferedSource buffer = Okio.buffer(this.h.source(this.m));
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            if (Intrinsics.areEqual(MAGIC, readUtf8LineStrict) && Intrinsics.areEqual(VERSION_1, readUtf8LineStrict2) && Intrinsics.areEqual(String.valueOf(this.j), readUtf8LineStrict3) && Intrinsics.areEqual(String.valueOf(getValueCount$okhttp()), readUtf8LineStrict4)) {
                int i = 0;
                if (!(readUtf8LineStrict5.length() > 0)) {
                    while (true) {
                        try {
                            f(buffer.readUtf8LineStrict());
                            i++;
                        } catch (EOFException unused) {
                            this.s = i - getLruEntries$okhttp().size();
                            if (!buffer.exhausted()) {
                                rebuildJournal$okhttp();
                            } else {
                                this.q = c();
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer, null);
                            return;
                        }
                    }
                }
            }
            throw new IOException("unexpected journal header: [" + readUtf8LineStrict + ", " + readUtf8LineStrict2 + ", " + readUtf8LineStrict4 + ", " + readUtf8LineStrict5 + ']');
        } finally {
        }
    }

    @JvmOverloads
    @Nullable
    public final Editor edit(@NotNull String key) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        return edit$default(this, key, 0L, 2, null);
    }

    @JvmOverloads
    @Nullable
    public final synchronized Editor edit(@NotNull String key, long j) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        initialize();
        a();
        h(key);
        Entry entry = this.r.get(key);
        if (j == ANY_SEQUENCE_NUMBER || (entry != null && entry.getSequenceNumber$okhttp() == j)) {
            if ((entry == null ? null : entry.getCurrentEditor$okhttp()) != null) {
                return null;
            }
            if (entry == null || entry.getLockingSourceCount$okhttp() == 0) {
                if (!this.x && !this.y) {
                    BufferedSink bufferedSink = this.q;
                    Intrinsics.checkNotNull(bufferedSink);
                    bufferedSink.writeUtf8(DIRTY).writeByte(32).writeUtf8(key).writeByte(10);
                    bufferedSink.flush();
                    if (this.t) {
                        return null;
                    }
                    if (entry == null) {
                        entry = new Entry(this, key);
                        this.r.put(key, entry);
                    }
                    Editor editor = new Editor(this, entry);
                    entry.setCurrentEditor$okhttp(editor);
                    return editor;
                }
                TaskQueue.schedule$default(this.A, this.B, 0L, 2, null);
                return null;
            }
            return null;
        }
        return null;
    }

    public final synchronized void evictAll() throws IOException {
        initialize();
        Collection<Entry> values = this.r.values();
        Intrinsics.checkNotNullExpressionValue(values, "lruEntries.values");
        Object[] array = values.toArray(new Entry[0]);
        if (array != null) {
            Entry[] entryArr = (Entry[]) array;
            int length = entryArr.length;
            int i = 0;
            while (i < length) {
                Entry entry = entryArr[i];
                i++;
                Intrinsics.checkNotNullExpressionValue(entry, "entry");
                removeEntry$okhttp(entry);
            }
            this.x = false;
        } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
    }

    public final void f(String str) throws IOException {
        String substring;
        int indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, ' ', 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            int i = indexOf$default + 1;
            int indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, ' ', i, false, 4, (Object) null);
            if (indexOf$default2 == -1) {
                substring = str.substring(i);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                String str2 = REMOVE;
                if (indexOf$default == str2.length() && m.startsWith$default(str, str2, false, 2, null)) {
                    this.r.remove(substring);
                    return;
                }
            } else {
                substring = str.substring(i, indexOf$default2);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            Entry entry = this.r.get(substring);
            if (entry == null) {
                entry = new Entry(this, substring);
                this.r.put(substring, entry);
            }
            if (indexOf$default2 != -1) {
                String str3 = CLEAN;
                if (indexOf$default == str3.length() && m.startsWith$default(str, str3, false, 2, null)) {
                    String substring2 = str.substring(indexOf$default2 + 1);
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                    List<String> split$default = StringsKt__StringsKt.split$default((CharSequence) substring2, new char[]{' '}, false, 0, 6, (Object) null);
                    entry.setReadable$okhttp(true);
                    entry.setCurrentEditor$okhttp(null);
                    entry.setLengths$okhttp(split$default);
                    return;
                }
            }
            if (indexOf$default2 == -1) {
                String str4 = DIRTY;
                if (indexOf$default == str4.length() && m.startsWith$default(str, str4, false, 2, null)) {
                    entry.setCurrentEditor$okhttp(new Editor(this, entry));
                    return;
                }
            }
            if (indexOf$default2 == -1) {
                String str5 = READ;
                if (indexOf$default == str5.length() && m.startsWith$default(str, str5, false, 2, null)) {
                    return;
                }
            }
            throw new IOException(Intrinsics.stringPlus("unexpected journal line: ", str));
        }
        throw new IOException(Intrinsics.stringPlus("unexpected journal line: ", str));
    }

    @Override // java.io.Flushable
    public synchronized void flush() throws IOException {
        if (this.v) {
            a();
            trimToSize();
            BufferedSink bufferedSink = this.q;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.flush();
        }
    }

    public final boolean g() {
        for (Entry toEvict : this.r.values()) {
            if (!toEvict.getZombie$okhttp()) {
                Intrinsics.checkNotNullExpressionValue(toEvict, "toEvict");
                removeEntry$okhttp(toEvict);
                return true;
            }
        }
        return false;
    }

    @Nullable
    public final synchronized Snapshot get(@NotNull String key) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        initialize();
        a();
        h(key);
        Entry entry = this.r.get(key);
        if (entry == null) {
            return null;
        }
        Snapshot snapshot$okhttp = entry.snapshot$okhttp();
        if (snapshot$okhttp == null) {
            return null;
        }
        this.s++;
        BufferedSink bufferedSink = this.q;
        Intrinsics.checkNotNull(bufferedSink);
        bufferedSink.writeUtf8(READ).writeByte(32).writeUtf8(key).writeByte(10);
        if (b()) {
            TaskQueue.schedule$default(this.A, this.B, 0L, 2, null);
        }
        return snapshot$okhttp;
    }

    public final boolean getClosed$okhttp() {
        return this.w;
    }

    @NotNull
    public final File getDirectory() {
        return this.i;
    }

    @NotNull
    public final FileSystem getFileSystem$okhttp() {
        return this.h;
    }

    @NotNull
    public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
        return this.r;
    }

    public final synchronized long getMaxSize() {
        return this.l;
    }

    public final int getValueCount$okhttp() {
        return this.k;
    }

    public final void h(String str) {
        if (LEGAL_KEY_PATTERN.matches(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + Typography.quote).toString());
    }

    public final synchronized void initialize() throws IOException {
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + this);
        }
        if (this.v) {
            return;
        }
        if (this.h.exists(this.o)) {
            if (this.h.exists(this.m)) {
                this.h.delete(this.o);
            } else {
                this.h.rename(this.o, this.m);
            }
        }
        this.u = Util.isCivilized(this.h, this.o);
        if (this.h.exists(this.m)) {
            try {
                e();
                d();
                this.v = true;
                return;
            } catch (IOException e) {
                Platform platform = Platform.Companion.get();
                platform.log("DiskLruCache " + this.i + " is corrupt: " + ((Object) e.getMessage()) + ", removing", 5, e);
                delete();
                this.w = false;
            }
        }
        rebuildJournal$okhttp();
        this.v = true;
    }

    public final synchronized boolean isClosed() {
        return this.w;
    }

    public final synchronized void rebuildJournal$okhttp() throws IOException {
        BufferedSink bufferedSink = this.q;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        BufferedSink buffer = Okio.buffer(this.h.sink(this.n));
        buffer.writeUtf8(MAGIC).writeByte(10);
        buffer.writeUtf8(VERSION_1).writeByte(10);
        buffer.writeDecimalLong(this.j).writeByte(10);
        buffer.writeDecimalLong(getValueCount$okhttp()).writeByte(10);
        buffer.writeByte(10);
        for (Entry entry : getLruEntries$okhttp().values()) {
            if (entry.getCurrentEditor$okhttp() != null) {
                buffer.writeUtf8(DIRTY).writeByte(32);
                buffer.writeUtf8(entry.getKey$okhttp());
                buffer.writeByte(10);
            } else {
                buffer.writeUtf8(CLEAN).writeByte(32);
                buffer.writeUtf8(entry.getKey$okhttp());
                entry.writeLengths$okhttp(buffer);
                buffer.writeByte(10);
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(buffer, null);
        if (this.h.exists(this.m)) {
            this.h.rename(this.m, this.o);
        }
        this.h.rename(this.n, this.m);
        this.h.delete(this.o);
        this.q = c();
        this.t = false;
        this.y = false;
    }

    public final synchronized boolean remove(@NotNull String key) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        initialize();
        a();
        h(key);
        Entry entry = this.r.get(key);
        if (entry == null) {
            return false;
        }
        boolean removeEntry$okhttp = removeEntry$okhttp(entry);
        if (removeEntry$okhttp && this.p <= this.l) {
            this.x = false;
        }
        return removeEntry$okhttp;
    }

    public final boolean removeEntry$okhttp(@NotNull Entry entry) throws IOException {
        BufferedSink bufferedSink;
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (!this.u) {
            if (entry.getLockingSourceCount$okhttp() > 0 && (bufferedSink = this.q) != null) {
                bufferedSink.writeUtf8(DIRTY);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(entry.getKey$okhttp());
                bufferedSink.writeByte(10);
                bufferedSink.flush();
            }
            if (entry.getLockingSourceCount$okhttp() > 0 || entry.getCurrentEditor$okhttp() != null) {
                entry.setZombie$okhttp(true);
                return true;
            }
        }
        Editor currentEditor$okhttp = entry.getCurrentEditor$okhttp();
        if (currentEditor$okhttp != null) {
            currentEditor$okhttp.detach$okhttp();
        }
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            this.h.delete(entry.getCleanFiles$okhttp().get(i2));
            this.p -= entry.getLengths$okhttp()[i2];
            entry.getLengths$okhttp()[i2] = 0;
        }
        this.s++;
        BufferedSink bufferedSink2 = this.q;
        if (bufferedSink2 != null) {
            bufferedSink2.writeUtf8(REMOVE);
            bufferedSink2.writeByte(32);
            bufferedSink2.writeUtf8(entry.getKey$okhttp());
            bufferedSink2.writeByte(10);
        }
        this.r.remove(entry.getKey$okhttp());
        if (b()) {
            TaskQueue.schedule$default(this.A, this.B, 0L, 2, null);
        }
        return true;
    }

    public final void setClosed$okhttp(boolean z) {
        this.w = z;
    }

    public final synchronized void setMaxSize(long j) {
        this.l = j;
        if (this.v) {
            TaskQueue.schedule$default(this.A, this.B, 0L, 2, null);
        }
    }

    public final synchronized long size() throws IOException {
        initialize();
        return this.p;
    }

    @NotNull
    public final synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new DiskLruCache$snapshots$1(this);
    }

    public final void trimToSize() throws IOException {
        while (this.p > this.l) {
            if (!g()) {
                return;
            }
        }
        this.x = false;
    }
}
