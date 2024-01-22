package okio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.h;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes12.dex */
public class JvmSystemFileSystem extends FileSystem {
    public final List<Path> a(Path path, boolean z) {
        File file = path.toFile();
        String[] list = file.list();
        if (list == null) {
            if (z) {
                if (file.exists()) {
                    throw new IOException("failed to list " + path);
                }
                throw new FileNotFoundException("no such file: " + path);
            } else {
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        for (String it : list) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            arrayList.add(path.resolve(it));
        }
        h.sort(arrayList);
        return arrayList;
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink appendingSink(@NotNull Path file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (z) {
            c(file);
        }
        return Okio.sink(file.toFile(), true);
    }

    @Override // okio.FileSystem
    public void atomicMove(@NotNull Path source, @NotNull Path target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        if (source.toFile().renameTo(target.toFile())) {
            return;
        }
        throw new IOException("failed to move " + source + " to " + target);
    }

    public final void b(Path path) {
        if (exists(path)) {
            throw new IOException(path + " already exists.");
        }
    }

    public final void c(Path path) {
        if (exists(path)) {
            return;
        }
        throw new IOException(path + " doesn't exist.");
    }

    @Override // okio.FileSystem
    @NotNull
    public Path canonicalize(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        File canonicalFile = path.toFile().getCanonicalFile();
        if (canonicalFile.exists()) {
            Path.Companion companion = Path.Companion;
            Intrinsics.checkNotNullExpressionValue(canonicalFile, "canonicalFile");
            return Path.Companion.get$default(companion, canonicalFile, false, 1, (Object) null);
        }
        throw new FileNotFoundException("no such file");
    }

    @Override // okio.FileSystem
    public void createDirectory(@NotNull Path dir, boolean z) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        if (dir.toFile().mkdir()) {
            return;
        }
        FileMetadata metadataOrNull = metadataOrNull(dir);
        boolean z2 = true;
        if (metadataOrNull == null || !metadataOrNull.isDirectory()) {
            z2 = false;
        }
        if (!z2) {
            throw new IOException("failed to create directory: " + dir);
        } else if (z) {
            throw new IOException(dir + " already exist.");
        }
    }

    @Override // okio.FileSystem
    public void createSymlink(@NotNull Path source, @NotNull Path target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException("unsupported");
    }

    @Override // okio.FileSystem
    public void delete(@NotNull Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "path");
        File file = path.toFile();
        if (file.delete()) {
            return;
        }
        if (file.exists()) {
            throw new IOException("failed to delete " + path);
        } else if (z) {
            throw new FileNotFoundException("no such file: " + path);
        }
    }

    @Override // okio.FileSystem
    @NotNull
    public List<Path> list(@NotNull Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> a2 = a(dir, true);
        Intrinsics.checkNotNull(a2);
        return a2;
    }

    @Override // okio.FileSystem
    @Nullable
    public List<Path> listOrNull(@NotNull Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        return a(dir, false);
    }

    @Override // okio.FileSystem
    @Nullable
    public FileMetadata metadataOrNull(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        File file = path.toFile();
        boolean isFile = file.isFile();
        boolean isDirectory = file.isDirectory();
        long lastModified = file.lastModified();
        long length = file.length();
        if (isFile || isDirectory || lastModified != 0 || length != 0 || file.exists()) {
            return new FileMetadata(isFile, isDirectory, null, Long.valueOf(length), null, Long.valueOf(lastModified), null, null, 128, null);
        }
        return null;
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadOnly(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return new JvmFileHandle(false, new RandomAccessFile(file.toFile(), RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME));
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadWrite(@NotNull Path file, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(file, "file");
        if ((z && z2) ? false : true) {
            if (z) {
                b(file);
            }
            if (z2) {
                c(file);
            }
            return new JvmFileHandle(true, new RandomAccessFile(file.toFile(), "rw"));
        }
        throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.".toString());
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink sink(@NotNull Path file, boolean z) {
        Sink q;
        Intrinsics.checkNotNullParameter(file, "file");
        if (z) {
            b(file);
        }
        q = c.q(file.toFile(), false, 1, null);
        return q;
    }

    @Override // okio.FileSystem
    @NotNull
    public Source source(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return Okio.source(file.toFile());
    }

    @NotNull
    public String toString() {
        return "JvmSystemFileSystem";
    }
}
