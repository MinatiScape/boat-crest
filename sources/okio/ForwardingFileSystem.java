package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.h;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
/* loaded from: classes12.dex */
public abstract class ForwardingFileSystem extends FileSystem {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final FileSystem f14314a;

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1<Path, Path> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Path invoke(@NotNull Path it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return ForwardingFileSystem.this.onPathResult(it, "listRecursively");
        }
    }

    public ForwardingFileSystem(@NotNull FileSystem delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.f14314a = delegate;
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink appendingSink(@NotNull Path file, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        return this.f14314a.appendingSink(onPathParameter(file, "appendingSink", "file"), z);
    }

    @Override // okio.FileSystem
    public void atomicMove(@NotNull Path source, @NotNull Path target) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        this.f14314a.atomicMove(onPathParameter(source, "atomicMove", "source"), onPathParameter(target, "atomicMove", TypedValues.AttributesType.S_TARGET));
    }

    @Override // okio.FileSystem
    @NotNull
    public Path canonicalize(@NotNull Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        return onPathResult(this.f14314a.canonicalize(onPathParameter(path, "canonicalize", "path")), "canonicalize");
    }

    @Override // okio.FileSystem
    public void createDirectory(@NotNull Path dir, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        this.f14314a.createDirectory(onPathParameter(dir, "createDirectory", KeyManagementAlgorithmIdentifiers.DIRECT), z);
    }

    @Override // okio.FileSystem
    public void createSymlink(@NotNull Path source, @NotNull Path target) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        this.f14314a.createSymlink(onPathParameter(source, "createSymlink", "source"), onPathParameter(target, "createSymlink", TypedValues.AttributesType.S_TARGET));
    }

    @JvmName(name = "delegate")
    @NotNull
    public final FileSystem delegate() {
        return this.f14314a;
    }

    @Override // okio.FileSystem
    public void delete(@NotNull Path path, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        this.f14314a.delete(onPathParameter(path, "delete", "path"), z);
    }

    @Override // okio.FileSystem
    @NotNull
    public List<Path> list(@NotNull Path dir) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> list = this.f14314a.list(onPathParameter(dir, "list", KeyManagementAlgorithmIdentifiers.DIRECT));
        ArrayList arrayList = new ArrayList();
        for (Path path : list) {
            arrayList.add(onPathResult(path, "list"));
        }
        h.sort(arrayList);
        return arrayList;
    }

    @Override // okio.FileSystem
    @Nullable
    public List<Path> listOrNull(@NotNull Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> listOrNull = this.f14314a.listOrNull(onPathParameter(dir, "listOrNull", KeyManagementAlgorithmIdentifiers.DIRECT));
        if (listOrNull == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Path path : listOrNull) {
            arrayList.add(onPathResult(path, "listOrNull"));
        }
        h.sort(arrayList);
        return arrayList;
    }

    @Override // okio.FileSystem
    @NotNull
    public Sequence<Path> listRecursively(@NotNull Path dir, boolean z) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        return SequencesKt___SequencesKt.map(this.f14314a.listRecursively(onPathParameter(dir, "listRecursively", KeyManagementAlgorithmIdentifiers.DIRECT), z), new a());
    }

    @Override // okio.FileSystem
    @Nullable
    public FileMetadata metadataOrNull(@NotNull Path path) throws IOException {
        FileMetadata copy;
        Intrinsics.checkNotNullParameter(path, "path");
        FileMetadata metadataOrNull = this.f14314a.metadataOrNull(onPathParameter(path, "metadataOrNull", "path"));
        if (metadataOrNull == null) {
            return null;
        }
        if (metadataOrNull.getSymlinkTarget() == null) {
            return metadataOrNull;
        }
        copy = metadataOrNull.copy((r18 & 1) != 0 ? metadataOrNull.f14313a : false, (r18 & 2) != 0 ? metadataOrNull.b : false, (r18 & 4) != 0 ? metadataOrNull.c : onPathResult(metadataOrNull.getSymlinkTarget(), "metadataOrNull"), (r18 & 8) != 0 ? metadataOrNull.d : null, (r18 & 16) != 0 ? metadataOrNull.e : null, (r18 & 32) != 0 ? metadataOrNull.f : null, (r18 & 64) != 0 ? metadataOrNull.g : null, (r18 & 128) != 0 ? metadataOrNull.h : null);
        return copy;
    }

    @NotNull
    public Path onPathParameter(@NotNull Path path, @NotNull String functionName, @NotNull String parameterName) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(functionName, "functionName");
        Intrinsics.checkNotNullParameter(parameterName, "parameterName");
        return path;
    }

    @NotNull
    public Path onPathResult(@NotNull Path path, @NotNull String functionName) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(functionName, "functionName");
        return path;
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadOnly(@NotNull Path file) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        return this.f14314a.openReadOnly(onPathParameter(file, "openReadOnly", "file"));
    }

    @Override // okio.FileSystem
    @NotNull
    public FileHandle openReadWrite(@NotNull Path file, boolean z, boolean z2) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        return this.f14314a.openReadWrite(onPathParameter(file, "openReadWrite", "file"), z, z2);
    }

    @Override // okio.FileSystem
    @NotNull
    public Sink sink(@NotNull Path file, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        return this.f14314a.sink(onPathParameter(file, "sink", "file"), z);
    }

    @Override // okio.FileSystem
    @NotNull
    public Source source(@NotNull Path file) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        return this.f14314a.source(onPathParameter(file, "source", "file"));
    }

    @NotNull
    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + HexStringBuilder.COMMENT_BEGIN_CHAR + this.f14314a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
