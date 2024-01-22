package okio;

import java.io.RandomAccessFile;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class JvmFileHandle extends FileHandle {
    @NotNull
    public final RandomAccessFile k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmFileHandle(boolean z, @NotNull RandomAccessFile randomAccessFile) {
        super(z);
        Intrinsics.checkNotNullParameter(randomAccessFile, "randomAccessFile");
        this.k = randomAccessFile;
    }

    @Override // okio.FileHandle
    public synchronized void protectedClose() {
        this.k.close();
    }

    @Override // okio.FileHandle
    public synchronized void protectedFlush() {
        this.k.getFD().sync();
    }

    @Override // okio.FileHandle
    public synchronized int protectedRead(long j, @NotNull byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.k.seek(j);
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            int read = this.k.read(array, i, i2 - i3);
            if (read != -1) {
                i3 += read;
            } else if (i3 == 0) {
                return -1;
            }
        }
        return i3;
    }

    @Override // okio.FileHandle
    public synchronized void protectedResize(long j) {
        long size = size();
        long j2 = j - size;
        if (j2 > 0) {
            int i = (int) j2;
            protectedWrite(size, new byte[i], 0, i);
        } else {
            this.k.setLength(j);
        }
    }

    @Override // okio.FileHandle
    public synchronized long protectedSize() {
        return this.k.length();
    }

    @Override // okio.FileHandle
    public synchronized void protectedWrite(long j, @NotNull byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.k.seek(j);
        this.k.write(array, i, i2);
    }
}
