package kotlin.io.path;

import java.nio.file.Path;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class e {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Path f14078a;
    @Nullable
    public final Object b;
    @Nullable
    public final e c;
    @Nullable
    public Iterator<e> d;

    public e(@NotNull Path path, @Nullable Object obj, @Nullable e eVar) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.f14078a = path;
        this.b = obj;
        this.c = eVar;
    }

    @Nullable
    public final Iterator<e> a() {
        return this.d;
    }

    @Nullable
    public final Object b() {
        return this.b;
    }

    @Nullable
    public final e c() {
        return this.c;
    }

    @NotNull
    public final Path d() {
        return this.f14078a;
    }

    public final void e(@Nullable Iterator<e> it) {
        this.d = it;
    }
}
