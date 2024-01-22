package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class FileOperator {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final FileChannel f14254a;

    public FileOperator(@NotNull FileChannel fileChannel) {
        Intrinsics.checkNotNullParameter(fileChannel, "fileChannel");
        this.f14254a = fileChannel;
    }

    public final void read(long j, @NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long transferTo = this.f14254a.transferTo(j, j2, sink);
            j += transferTo;
            j2 -= transferTo;
        }
    }

    public final void write(long j, @NotNull Buffer source, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (j2 < 0 || j2 > source.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long transferFrom = this.f14254a.transferFrom(source, j, j2);
            j += transferFrom;
            j2 -= transferFrom;
        }
    }
}
