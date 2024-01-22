package kotlin.io.path;

import java.nio.file.FileVisitOption;
import java.nio.file.LinkOption;
import java.util.Set;
import kotlin.collections.a0;
import kotlin.collections.z;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class LinkFollowing {
    @NotNull
    public static final LinkFollowing INSTANCE = new LinkFollowing();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final LinkOption[] f14072a = {LinkOption.NOFOLLOW_LINKS};
    @NotNull
    public static final LinkOption[] b = new LinkOption[0];
    @NotNull
    public static final Set<FileVisitOption> c = a0.emptySet();
    @NotNull
    public static final Set<FileVisitOption> d = z.setOf(FileVisitOption.FOLLOW_LINKS);

    @NotNull
    public final LinkOption[] toLinkOptions(boolean z) {
        return z ? b : f14072a;
    }

    @NotNull
    public final Set<FileVisitOption> toVisitOptions(boolean z) {
        return z ? d : c;
    }
}
