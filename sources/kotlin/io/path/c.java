package kotlin.io.path;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final int f14076a;
    public int b;
    @NotNull
    public final List<Exception> c;
    @Nullable
    public Path d;

    public c(int i) {
        this.f14076a = i;
        this.c = new ArrayList();
    }

    public final void a(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.b++;
        if (this.c.size() < this.f14076a) {
            if (this.d != null) {
                Throwable initCause = new FileSystemException(String.valueOf(this.d)).initCause(exception);
                Intrinsics.checkNotNull(initCause, "null cannot be cast to non-null type java.nio.file.FileSystemException");
                exception = (FileSystemException) initCause;
            }
            this.c.add(exception);
        }
    }

    public final void b(@NotNull Path name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Path path = this.d;
        this.d = path != null ? path.resolve(name) : null;
    }

    public final void c(@NotNull Path name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Path path = this.d;
        if (Intrinsics.areEqual(name, path != null ? path.getFileName() : null)) {
            Path path2 = this.d;
            this.d = path2 != null ? path2.getParent() : null;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @NotNull
    public final List<Exception> d() {
        return this.c;
    }

    public final int e() {
        return this.b;
    }

    public final void f(@Nullable Path path) {
        this.d = path;
    }

    public /* synthetic */ c(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 64 : i);
    }
}
