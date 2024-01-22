package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@ExperimentalPathApi
/* loaded from: classes12.dex */
public final class FileVisitorBuilderImpl implements FileVisitorBuilder {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> f14071a;
    @Nullable
    public Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> b;
    @Nullable
    public Function2<? super Path, ? super IOException, ? extends FileVisitResult> c;
    @Nullable
    public Function2<? super Path, ? super IOException, ? extends FileVisitResult> d;
    public boolean e;

    public final void a() {
        if (this.e) {
            throw new IllegalStateException("This builder was already built");
        }
    }

    public final void b(Object obj, String str) {
        if (obj == null) {
            return;
        }
        throw new IllegalStateException(str + " was already defined");
    }

    @NotNull
    public final FileVisitor<Path> build() {
        a();
        this.e = true;
        return new d(this.f14071a, this.b, this.c, this.d);
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onPostVisitDirectory(@NotNull Function2<? super Path, ? super IOException, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        a();
        b(this.d, "onPostVisitDirectory");
        this.d = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onPreVisitDirectory(@NotNull Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        a();
        b(this.f14071a, "onPreVisitDirectory");
        this.f14071a = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onVisitFile(@NotNull Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        a();
        b(this.b, "onVisitFile");
        this.b = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onVisitFileFailed(@NotNull Function2<? super Path, ? super IOException, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        a();
        b(this.c, "onVisitFileFailed");
        this.c = function;
    }
}
