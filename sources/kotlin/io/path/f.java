package kotlin.io.path;

import java.nio.file.Path;
import java.nio.file.Paths;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class f {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final f f14079a = new f();
    public static final Path b = Paths.get("", new String[0]);
    public static final Path c = Paths.get("..", new String[0]);

    @NotNull
    public final Path a(@NotNull Path path, @NotNull Path base) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(base, "base");
        Path normalize = base.normalize();
        Path r = path.normalize();
        Path relativize = normalize.relativize(r);
        int min = Math.min(normalize.getNameCount(), r.getNameCount());
        for (int i = 0; i < min; i++) {
            Path name = normalize.getName(i);
            Path path2 = c;
            if (!Intrinsics.areEqual(name, path2)) {
                break;
            } else if (!Intrinsics.areEqual(r.getName(i), path2)) {
                throw new IllegalArgumentException("Unable to compute relative path");
            }
        }
        if (Intrinsics.areEqual(r, normalize) || !Intrinsics.areEqual(normalize, b)) {
            String obj = relativize.toString();
            String separator = relativize.getFileSystem().getSeparator();
            Intrinsics.checkNotNullExpressionValue(separator, "rn.fileSystem.separator");
            r = m.endsWith$default(obj, separator, false, 2, null) ? relativize.getFileSystem().getPath(StringsKt___StringsKt.dropLast(obj, relativize.getFileSystem().getSeparator().length()), new String[0]) : relativize;
        }
        Intrinsics.checkNotNullExpressionValue(r, "r");
        return r;
    }
}
