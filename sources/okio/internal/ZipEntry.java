package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ZipEntry {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Path f14324a;
    public final boolean b;
    @NotNull
    public final String c;
    public final long d;
    public final long e;
    public final long f;
    public final int g;
    @Nullable
    public final Long h;
    public final long i;
    @NotNull
    public final List<Path> j;

    public ZipEntry(@NotNull Path canonicalPath, boolean z, @NotNull String comment, long j, long j2, long j3, int i, @Nullable Long l, long j4) {
        Intrinsics.checkNotNullParameter(canonicalPath, "canonicalPath");
        Intrinsics.checkNotNullParameter(comment, "comment");
        this.f14324a = canonicalPath;
        this.b = z;
        this.c = comment;
        this.d = j;
        this.e = j2;
        this.f = j3;
        this.g = i;
        this.h = l;
        this.i = j4;
        this.j = new ArrayList();
    }

    @NotNull
    public final Path getCanonicalPath() {
        return this.f14324a;
    }

    @NotNull
    public final List<Path> getChildren() {
        return this.j;
    }

    @NotNull
    public final String getComment() {
        return this.c;
    }

    public final long getCompressedSize() {
        return this.e;
    }

    public final int getCompressionMethod() {
        return this.g;
    }

    public final long getCrc() {
        return this.d;
    }

    @Nullable
    public final Long getLastModifiedAtMillis() {
        return this.h;
    }

    public final long getOffset() {
        return this.i;
    }

    public final long getSize() {
        return this.f;
    }

    public final boolean isDirectory() {
        return this.b;
    }

    public /* synthetic */ ZipEntry(Path path, boolean z, String str, long j, long j2, long j3, int i, Long l, long j4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(path, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? "" : str, (i2 & 8) != 0 ? -1L : j, (i2 & 16) != 0 ? -1L : j2, (i2 & 32) != 0 ? -1L : j3, (i2 & 64) != 0 ? -1 : i, (i2 & 128) != 0 ? null : l, (i2 & 256) == 0 ? j4 : -1L);
    }
}
