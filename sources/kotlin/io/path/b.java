package kotlin.io.path;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class b extends SimpleFileVisitor<Path> {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f14075a;
    @Nullable
    public e b;
    @NotNull
    public ArrayDeque<e> c = new ArrayDeque<>();

    public b(boolean z) {
        this.f14075a = z;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    @NotNull
    /* renamed from: a */
    public FileVisitResult preVisitDirectory(@NotNull Path dir, @NotNull BasicFileAttributes attrs) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.c.add(new e(dir, attrs.fileKey(), this.b));
        FileVisitResult preVisitDirectory = super.preVisitDirectory(dir, attrs);
        Intrinsics.checkNotNullExpressionValue(preVisitDirectory, "super.preVisitDirectory(dir, attrs)");
        return preVisitDirectory;
    }

    @NotNull
    public final List<e> b(@NotNull e directoryNode) {
        Intrinsics.checkNotNullParameter(directoryNode, "directoryNode");
        this.b = directoryNode;
        Files.walkFileTree(directoryNode.d(), LinkFollowing.INSTANCE.toVisitOptions(this.f14075a), 1, this);
        this.c.removeFirst();
        ArrayDeque<e> arrayDeque = this.c;
        this.c = new ArrayDeque<>();
        return arrayDeque;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    @NotNull
    /* renamed from: c */
    public FileVisitResult visitFile(@NotNull Path file, @NotNull BasicFileAttributes attrs) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.c.add(new e(file, null, this.b));
        FileVisitResult visitFile = super.visitFile(file, attrs);
        Intrinsics.checkNotNullExpressionValue(visitFile, "super.visitFile(file, attrs)");
        return visitFile;
    }
}
