package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class d extends SimpleFileVisitor<Path> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Function2<Path, BasicFileAttributes, FileVisitResult> f14077a;
    @Nullable
    public final Function2<Path, BasicFileAttributes, FileVisitResult> b;
    @Nullable
    public final Function2<Path, IOException, FileVisitResult> c;
    @Nullable
    public final Function2<Path, IOException, FileVisitResult> d;

    /* JADX WARN: Multi-variable type inference failed */
    public d(@Nullable Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function2, @Nullable Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function22, @Nullable Function2<? super Path, ? super IOException, ? extends FileVisitResult> function23, @Nullable Function2<? super Path, ? super IOException, ? extends FileVisitResult> function24) {
        this.f14077a = function2;
        this.b = function22;
        this.c = function23;
        this.d = function24;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    @NotNull
    /* renamed from: a */
    public FileVisitResult postVisitDirectory(@NotNull Path dir, @Nullable IOException iOException) {
        FileVisitResult invoke;
        Intrinsics.checkNotNullParameter(dir, "dir");
        Function2<Path, IOException, FileVisitResult> function2 = this.d;
        if (function2 == null || (invoke = function2.invoke(dir, iOException)) == null) {
            FileVisitResult postVisitDirectory = super.postVisitDirectory(dir, iOException);
            Intrinsics.checkNotNullExpressionValue(postVisitDirectory, "super.postVisitDirectory(dir, exc)");
            return postVisitDirectory;
        }
        return invoke;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    @NotNull
    /* renamed from: b */
    public FileVisitResult preVisitDirectory(@NotNull Path dir, @NotNull BasicFileAttributes attrs) {
        FileVisitResult invoke;
        Intrinsics.checkNotNullParameter(dir, "dir");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Function2<Path, BasicFileAttributes, FileVisitResult> function2 = this.f14077a;
        if (function2 == null || (invoke = function2.invoke(dir, attrs)) == null) {
            FileVisitResult preVisitDirectory = super.preVisitDirectory(dir, attrs);
            Intrinsics.checkNotNullExpressionValue(preVisitDirectory, "super.preVisitDirectory(dir, attrs)");
            return preVisitDirectory;
        }
        return invoke;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    @NotNull
    /* renamed from: c */
    public FileVisitResult visitFile(@NotNull Path file, @NotNull BasicFileAttributes attrs) {
        FileVisitResult invoke;
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Function2<Path, BasicFileAttributes, FileVisitResult> function2 = this.b;
        if (function2 == null || (invoke = function2.invoke(file, attrs)) == null) {
            FileVisitResult visitFile = super.visitFile(file, attrs);
            Intrinsics.checkNotNullExpressionValue(visitFile, "super.visitFile(file, attrs)");
            return visitFile;
        }
        return invoke;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    @NotNull
    /* renamed from: d */
    public FileVisitResult visitFileFailed(@NotNull Path file, @NotNull IOException exc) {
        FileVisitResult invoke;
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(exc, "exc");
        Function2<Path, IOException, FileVisitResult> function2 = this.c;
        if (function2 == null || (invoke = function2.invoke(file, exc)) == null) {
            FileVisitResult visitFileFailed = super.visitFileFailed(file, exc);
            Intrinsics.checkNotNullExpressionValue(visitFileFailed, "super.visitFileFailed(file, exc)");
            return visitFileFailed;
        }
        return invoke;
    }
}
