package okio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;
import okio.internal.FixedLengthSource;
import okio.internal.ZipEntry;
import okio.internal.ZipKt;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ZipFileSystem extends FileSystem {
    @Deprecated
    @NotNull
    public static final Path d;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Path f14319a;
    @NotNull
    public final FileSystem b;
    @NotNull
    public final Map<Path, ZipEntry> c;

    /* loaded from: classes12.dex */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
        d = Path.Companion.get$default(Path.Companion, MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 1, (Object) null);
    }

    public ZipFileSystem(@NotNull Path zipPath, @NotNull FileSystem fileSystem, @NotNull Map<Path, ZipEntry> entries, @Nullable String str) {
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(entries, "entries");
        this.f14319a = zipPath;
        this.b = fileSystem;
        this.c = entries;
    }

    public final Path a(Path path) {
        return d.resolve(path, true);
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink appendingSink(@NotNull Path file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void atomicMove(@NotNull Path source, @NotNull Path target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException("zip file systems are read-only");
    }

    public final List<Path> b(Path path, boolean z) {
        ZipEntry zipEntry = this.c.get(a(path));
        if (zipEntry == null) {
            if (z) {
                throw new IOException("not a directory: " + path);
            }
            return null;
        }
        return CollectionsKt___CollectionsKt.toList(zipEntry.getChildren());
    }

    @Override // okio.FileSystem
    @NotNull
    public Path canonicalize(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Path a2 = a(path);
        if (this.c.containsKey(a2)) {
            return a2;
        }
        throw new FileNotFoundException(String.valueOf(path));
    }

    @Override // okio.FileSystem
    public void createDirectory(@NotNull Path dir, boolean z) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void createSymlink(@NotNull Path source, @NotNull Path target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void delete(@NotNull Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "path");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    @NotNull
    public List<Path> list(@NotNull Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> b = b(dir, true);
        Intrinsics.checkNotNull(b);
        return b;
    }

    @Override // okio.FileSystem
    @Nullable
    public List<Path> listOrNull(@NotNull Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        return b(dir, false);
    }

    @Override // okio.FileSystem
    @Nullable
    public FileMetadata metadataOrNull(@NotNull Path path) {
        BufferedSource bufferedSource;
        Intrinsics.checkNotNullParameter(path, "path");
        ZipEntry zipEntry = this.c.get(a(path));
        Throwable th = null;
        if (zipEntry == null) {
            return null;
        }
        FileMetadata fileMetadata = new FileMetadata(!zipEntry.isDirectory(), zipEntry.isDirectory(), null, zipEntry.isDirectory() ? null : Long.valueOf(zipEntry.getSize()), null, zipEntry.getLastModifiedAtMillis(), null, null, 128, null);
        if (zipEntry.getOffset() == -1) {
            return fileMetadata;
        }
        FileHandle openReadOnly = this.b.openReadOnly(this.f14319a);
        try {
            bufferedSource = Okio.buffer(openReadOnly.source(zipEntry.getOffset()));
        } catch (Throwable th2) {
            th = th2;
            bufferedSource = null;
        }
        if (openReadOnly != null) {
            try {
                openReadOnly.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                } else {
                    kotlin.a.addSuppressed(th, th3);
                }
            }
        }
        if (th == null) {
            Intrinsics.checkNotNull(bufferedSource);
            return ZipKt.readLocalHeader(bufferedSource, fileMetadata);
        }
        throw th;
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadOnly(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadWrite(@NotNull Path file, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException("zip entries are not writable");
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink sink(@NotNull Path file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    @NotNull
    public Source source(@NotNull Path file) throws IOException {
        BufferedSource bufferedSource;
        Intrinsics.checkNotNullParameter(file, "file");
        ZipEntry zipEntry = this.c.get(a(file));
        if (zipEntry != null) {
            FileHandle openReadOnly = this.b.openReadOnly(this.f14319a);
            Throwable th = null;
            try {
                bufferedSource = Okio.buffer(openReadOnly.source(zipEntry.getOffset()));
            } catch (Throwable th2) {
                bufferedSource = null;
                th = th2;
            }
            if (openReadOnly != null) {
                try {
                    openReadOnly.close();
                } catch (Throwable th3) {
                    if (th == null) {
                        th = th3;
                    } else {
                        kotlin.a.addSuppressed(th, th3);
                    }
                }
            }
            if (th == null) {
                Intrinsics.checkNotNull(bufferedSource);
                ZipKt.skipLocalHeader(bufferedSource);
                if (zipEntry.getCompressionMethod() == 0) {
                    return new FixedLengthSource(bufferedSource, zipEntry.getSize(), true);
                }
                return new FixedLengthSource(new InflaterSource(new FixedLengthSource(bufferedSource, zipEntry.getCompressedSize(), true), new Inflater(true)), zipEntry.getSize(), false);
            }
            throw th;
        }
        throw new FileNotFoundException("no such file: " + file);
    }
}
