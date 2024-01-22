package kotlin.io.path;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class PathsKt__PathRecursiveFunctionsKt extends g {

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CopyActionResult.values().length];
            try {
                iArr[CopyActionResult.CONTINUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CopyActionResult.TERMINATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CopyActionResult.SKIP_SUBTREE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OnErrorResult.values().length];
            try {
                iArr2[OnErrorResult.TERMINATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[OnErrorResult.SKIP_SUBTREE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function3 {
        public static final a INSTANCE = new a();

        public a() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        @NotNull
        public final Void invoke(@NotNull Path path, @NotNull Path path2, @NotNull Exception exception) {
            Intrinsics.checkNotNullParameter(path, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(path2, "<anonymous parameter 1>");
            Intrinsics.checkNotNullParameter(exception, "exception");
            throw exception;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function3<CopyActionContext, Path, Path, CopyActionResult> {
        public final /* synthetic */ boolean $followLinks;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z) {
            super(3);
            this.$followLinks = z;
        }

        @Override // kotlin.jvm.functions.Function3
        @NotNull
        public final CopyActionResult invoke(@NotNull CopyActionContext copyToRecursively, @NotNull Path src, @NotNull Path dst) {
            Intrinsics.checkNotNullParameter(copyToRecursively, "$this$copyToRecursively");
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(dst, "dst");
            LinkOption[] linkOptions = LinkFollowing.INSTANCE.toLinkOptions(this.$followLinks);
            boolean isDirectory = Files.isDirectory(dst, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1));
            LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
            if (!Files.isDirectory(src, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length)) || !isDirectory) {
                if (isDirectory) {
                    PathsKt__PathRecursiveFunctionsKt.deleteRecursively(dst);
                }
                SpreadBuilder spreadBuilder = new SpreadBuilder(2);
                spreadBuilder.addSpread(linkOptions);
                spreadBuilder.add(StandardCopyOption.REPLACE_EXISTING);
                CopyOption[] copyOptionArr = (CopyOption[]) spreadBuilder.toArray(new CopyOption[spreadBuilder.size()]);
                Intrinsics.checkNotNullExpressionValue(Files.copy(src, dst, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length)), "copy(this, target, *options)");
            }
            return CopyActionResult.CONTINUE;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Lambda implements Function3 {
        public static final c INSTANCE = new c();

        public c() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        @NotNull
        public final Void invoke(@NotNull Path path, @NotNull Path path2, @NotNull Exception exception) {
            Intrinsics.checkNotNullParameter(path, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(path2, "<anonymous parameter 1>");
            Intrinsics.checkNotNullParameter(exception, "exception");
            throw exception;
        }
    }

    /* loaded from: classes12.dex */
    public static final class d extends Lambda implements Function3<CopyActionContext, Path, Path, CopyActionResult> {
        public final /* synthetic */ boolean $followLinks;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(boolean z) {
            super(3);
            this.$followLinks = z;
        }

        @Override // kotlin.jvm.functions.Function3
        @NotNull
        public final CopyActionResult invoke(@NotNull CopyActionContext copyActionContext, @NotNull Path src, @NotNull Path dst) {
            Intrinsics.checkNotNullParameter(copyActionContext, "$this$null");
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(dst, "dst");
            return copyActionContext.copyToIgnoringExistingDirectory(src, dst, this.$followLinks);
        }
    }

    /* loaded from: classes12.dex */
    public static final class e extends Lambda implements Function1<FileVisitorBuilder, Unit> {
        public final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
        public final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
        public final /* synthetic */ Path $target;
        public final /* synthetic */ Path $this_copyToRecursively;

        /* loaded from: classes12.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function2<Path, BasicFileAttributes, FileVisitResult> {
            public final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
            public final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
            public final /* synthetic */ Path $target;
            public final /* synthetic */ Path $this_copyToRecursively;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
                super(2, Intrinsics.Kotlin.class, Constants.COPY_TYPE, "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
                this.$copyAction = function3;
                this.$this_copyToRecursively = path;
                this.$target = path2;
                this.$onError = function32;
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final FileVisitResult invoke(@NotNull Path p0, @NotNull BasicFileAttributes p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                return PathsKt__PathRecursiveFunctionsKt.a(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError, p0, p1);
            }
        }

        /* loaded from: classes12.dex */
        public /* synthetic */ class b extends FunctionReferenceImpl implements Function2<Path, BasicFileAttributes, FileVisitResult> {
            public final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
            public final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
            public final /* synthetic */ Path $target;
            public final /* synthetic */ Path $this_copyToRecursively;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public b(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
                super(2, Intrinsics.Kotlin.class, Constants.COPY_TYPE, "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
                this.$copyAction = function3;
                this.$this_copyToRecursively = path;
                this.$target = path2;
                this.$onError = function32;
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final FileVisitResult invoke(@NotNull Path p0, @NotNull BasicFileAttributes p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                return PathsKt__PathRecursiveFunctionsKt.a(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError, p0, p1);
            }
        }

        /* loaded from: classes12.dex */
        public /* synthetic */ class c extends FunctionReferenceImpl implements Function2<Path, Exception, FileVisitResult> {
            public final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
            public final /* synthetic */ Path $target;
            public final /* synthetic */ Path $this_copyToRecursively;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public c(Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function3, Path path, Path path2) {
                super(2, Intrinsics.Kotlin.class, "error", "copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/lang/Exception;)Ljava/nio/file/FileVisitResult;", 0);
                this.$onError = function3;
                this.$this_copyToRecursively = path;
                this.$target = path2;
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final FileVisitResult invoke(@NotNull Path p0, @NotNull Exception p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                return PathsKt__PathRecursiveFunctionsKt.c(this.$onError, this.$this_copyToRecursively, this.$target, p0, p1);
            }
        }

        /* loaded from: classes12.dex */
        public static final class d extends Lambda implements Function2<Path, IOException, FileVisitResult> {
            public final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
            public final /* synthetic */ Path $target;
            public final /* synthetic */ Path $this_copyToRecursively;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public d(Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function3, Path path, Path path2) {
                super(2);
                this.$onError = function3;
                this.$this_copyToRecursively = path;
                this.$target = path2;
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final FileVisitResult invoke(@NotNull Path directory, @Nullable IOException iOException) {
                Intrinsics.checkNotNullParameter(directory, "directory");
                if (iOException != null) {
                    return PathsKt__PathRecursiveFunctionsKt.c(this.$onError, this.$this_copyToRecursively, this.$target, directory, iOException);
                }
                return FileVisitResult.CONTINUE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public e(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
            super(1);
            this.$copyAction = function3;
            this.$this_copyToRecursively = path;
            this.$target = path2;
            this.$onError = function32;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FileVisitorBuilder fileVisitorBuilder) {
            invoke2(fileVisitorBuilder);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull FileVisitorBuilder visitFileTree) {
            Intrinsics.checkNotNullParameter(visitFileTree, "$this$visitFileTree");
            visitFileTree.onPreVisitDirectory(new a(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError));
            visitFileTree.onVisitFile(new b(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError));
            visitFileTree.onVisitFileFailed(new c(this.$onError, this.$this_copyToRecursively, this.$target));
            visitFileTree.onPostVisitDirectory(new d(this.$onError, this.$this_copyToRecursively, this.$target));
        }
    }

    public static final FileVisitResult a(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32, Path path3, BasicFileAttributes basicFileAttributes) {
        try {
            return j(function3.invoke(kotlin.io.path.a.f14074a, path3, b(path, path2, path3)));
        } catch (Exception e2) {
            return c(function32, path, path2, path3, e2);
        }
    }

    public static final Path b(Path path, Path path2, Path path3) {
        Path resolve = path2.resolve(h.relativeTo(path3, path));
        Intrinsics.checkNotNullExpressionValue(resolve, "target.resolve(relativePath)");
        return resolve;
    }

    public static final FileVisitResult c(Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function3, Path path, Path path2, Path path3, Exception exc) {
        return k(function3.invoke(path3, b(path, path2, path3), exc));
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    @NotNull
    public static final Path copyToRecursively(@NotNull Path path, @NotNull Path target, @NotNull Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> onError, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (z2) {
            return copyToRecursively(path, target, onError, z, new b(z));
        }
        return copyToRecursively$default(path, target, onError, z, (Function3) null, 8, (Object) null);
    }

    public static /* synthetic */ Path copyToRecursively$default(Path path, Path path2, Function3 function3, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            function3 = a.INSTANCE;
        }
        return copyToRecursively(path, path2, function3, z, z2);
    }

    public static final List<Exception> d(Path path) {
        DirectoryStream<Path> directoryStream;
        boolean z = false;
        boolean z2 = true;
        kotlin.io.path.c cVar = new kotlin.io.path.c(0, 1, null);
        Path parent = path.getParent();
        if (parent != null) {
            try {
                directoryStream = Files.newDirectoryStream(parent);
            } catch (Throwable unused) {
                directoryStream = null;
            }
            if (directoryStream != null) {
                try {
                    if (directoryStream instanceof SecureDirectoryStream) {
                        cVar.f(parent);
                        Path fileName = path.getFileName();
                        Intrinsics.checkNotNullExpressionValue(fileName, "this.fileName");
                        f((SecureDirectoryStream) directoryStream, fileName, cVar);
                    } else {
                        z = true;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(directoryStream, null);
                    z2 = z;
                } finally {
                }
            }
        }
        if (z2) {
            h(path, cVar);
        }
        return cVar.d();
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    public static final void deleteRecursively(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        List<Exception> d2 = d(path);
        if (!d2.isEmpty()) {
            FileSystemException fileSystemException = new FileSystemException("Failed to delete one or more files. See suppressed exceptions for details.");
            for (Exception exc : d2) {
                kotlin.a.addSuppressed(fileSystemException, exc);
            }
            throw fileSystemException;
        }
    }

    public static final void e(SecureDirectoryStream<Path> secureDirectoryStream, Path path, kotlin.io.path.c cVar) {
        SecureDirectoryStream<Path> secureDirectoryStream2;
        try {
            try {
                secureDirectoryStream2 = secureDirectoryStream.newDirectoryStream(path, LinkOption.NOFOLLOW_LINKS);
            } catch (Exception e2) {
                cVar.a(e2);
                return;
            }
        } catch (NoSuchFileException unused) {
            secureDirectoryStream2 = null;
        }
        if (secureDirectoryStream2 != null) {
            for (Path path2 : secureDirectoryStream2) {
                Path fileName = path2.getFileName();
                Intrinsics.checkNotNullExpressionValue(fileName, "entry.fileName");
                f(secureDirectoryStream2, fileName, cVar);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(secureDirectoryStream2, null);
        }
    }

    public static final void f(SecureDirectoryStream<Path> secureDirectoryStream, Path path, kotlin.io.path.c cVar) {
        cVar.b(path);
        try {
        } catch (Exception e2) {
            cVar.a(e2);
        }
        if (i(secureDirectoryStream, path, LinkOption.NOFOLLOW_LINKS)) {
            int e3 = cVar.e();
            e(secureDirectoryStream, path, cVar);
            if (e3 == cVar.e()) {
                secureDirectoryStream.deleteDirectory(path);
                Unit unit = Unit.INSTANCE;
            }
            cVar.c(path);
        }
        secureDirectoryStream.deleteFile(path);
        Unit unit2 = Unit.INSTANCE;
        cVar.c(path);
    }

    public static final void g(Path path, kotlin.io.path.c cVar) {
        DirectoryStream<Path> directoryStream;
        try {
            try {
                directoryStream = Files.newDirectoryStream(path);
            } catch (NoSuchFileException unused) {
                directoryStream = null;
            }
            if (directoryStream != null) {
                for (Path entry : directoryStream) {
                    Intrinsics.checkNotNullExpressionValue(entry, "entry");
                    h(entry, cVar);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(directoryStream, null);
            }
        } catch (Exception e2) {
            cVar.a(e2);
        }
    }

    public static final void h(Path path, kotlin.io.path.c cVar) {
        try {
            if (Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
                int e2 = cVar.e();
                g(path, cVar);
                if (e2 == cVar.e()) {
                    Files.deleteIfExists(path);
                }
            } else {
                Files.deleteIfExists(path);
            }
        } catch (Exception e3) {
            cVar.a(e3);
        }
    }

    public static final boolean i(SecureDirectoryStream<Path> secureDirectoryStream, Path path, LinkOption... linkOptionArr) {
        Boolean bool;
        try {
            bool = Boolean.valueOf(((BasicFileAttributeView) secureDirectoryStream.getFileAttributeView(path, BasicFileAttributeView.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))).readAttributes().isDirectory());
        } catch (NoSuchFileException unused) {
            bool = null;
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @ExperimentalPathApi
    public static final FileVisitResult j(CopyActionResult copyActionResult) {
        int i = WhenMappings.$EnumSwitchMapping$0[copyActionResult.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                throw new NoWhenBranchMatchedException();
            }
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    @ExperimentalPathApi
    public static final FileVisitResult k(OnErrorResult onErrorResult) {
        int i = WhenMappings.$EnumSwitchMapping$1[onErrorResult.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            throw new NoWhenBranchMatchedException();
        }
        return FileVisitResult.TERMINATE;
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    @NotNull
    public static final Path copyToRecursively(@NotNull Path path, @NotNull Path target, @NotNull Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> onError, boolean z, @NotNull Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> copyAction) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(copyAction, "copyAction");
        LinkOption[] linkOptions = LinkFollowing.INSTANCE.toLinkOptions(z);
        LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
        if (Files.exists(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            boolean z2 = false;
            if (Files.exists(path, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) && (z || !Files.isSymbolicLink(path))) {
                boolean z3 = Files.exists(target, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) && !Files.isSymbolicLink(target);
                if (!z3 || !Files.isSameFile(path, target)) {
                    Path realPath = path.toRealPath(new LinkOption[0]);
                    if (z3) {
                        z2 = target.toRealPath(new LinkOption[0]).startsWith(realPath);
                    } else {
                        Path parent = target.getParent();
                        if (parent != null && Files.exists(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) && parent.toRealPath(new LinkOption[0]).startsWith(realPath)) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        throw new FileSystemException(path.toString(), target.toString(), "Recursively copying a directory into its subdirectory is prohibited.");
                    }
                }
            }
            h.visitFileTree$default(path, 0, z, new e(copyAction, path, target, onError), 1, (Object) null);
            return target;
        }
        throw new NoSuchFileException(path.toString(), target.toString(), "The source file doesn't exist.");
    }

    public static /* synthetic */ Path copyToRecursively$default(Path path, Path path2, Function3 function3, boolean z, Function3 function32, int i, Object obj) {
        if ((i & 2) != 0) {
            function3 = c.INSTANCE;
        }
        if ((i & 8) != 0) {
            function32 = new d(z);
        }
        return copyToRecursively(path, path2, function3, z, function32);
    }
}
