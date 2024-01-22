package kotlin.io;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class FilePathComponents {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final File f14066a;
    @NotNull
    public final List<File> b;

    /* JADX WARN: Multi-variable type inference failed */
    public FilePathComponents(@NotNull File root, @NotNull List<? extends File> segments) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(segments, "segments");
        this.f14066a = root;
        this.b = segments;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FilePathComponents copy$default(FilePathComponents filePathComponents, File file, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            file = filePathComponents.f14066a;
        }
        if ((i & 2) != 0) {
            list = filePathComponents.b;
        }
        return filePathComponents.copy(file, list);
    }

    @NotNull
    public final File component1() {
        return this.f14066a;
    }

    @NotNull
    public final List<File> component2() {
        return this.b;
    }

    @NotNull
    public final FilePathComponents copy(@NotNull File root, @NotNull List<? extends File> segments) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(segments, "segments");
        return new FilePathComponents(root, segments);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FilePathComponents) {
            FilePathComponents filePathComponents = (FilePathComponents) obj;
            return Intrinsics.areEqual(this.f14066a, filePathComponents.f14066a) && Intrinsics.areEqual(this.b, filePathComponents.b);
        }
        return false;
    }

    @NotNull
    public final File getRoot() {
        return this.f14066a;
    }

    @NotNull
    public final String getRootName() {
        String path = this.f14066a.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "root.path");
        return path;
    }

    @NotNull
    public final List<File> getSegments() {
        return this.b;
    }

    public final int getSize() {
        return this.b.size();
    }

    public int hashCode() {
        return (this.f14066a.hashCode() * 31) + this.b.hashCode();
    }

    public final boolean isRooted() {
        String path = this.f14066a.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "root.path");
        return path.length() > 0;
    }

    @NotNull
    public final File subPath(int i, int i2) {
        if (i >= 0 && i <= i2 && i2 <= getSize()) {
            List<File> subList = this.b.subList(i, i2);
            String separator = File.separator;
            Intrinsics.checkNotNullExpressionValue(separator, "separator");
            return new File(CollectionsKt___CollectionsKt.joinToString$default(subList, separator, null, null, 0, null, null, 62, null));
        }
        throw new IllegalArgumentException();
    }

    @NotNull
    public String toString() {
        return "FilePathComponents(root=" + this.f14066a + ", segments=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
