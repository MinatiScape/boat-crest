package okhttp3;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import okhttp3.internal.cache.DiskLruCache;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Cache$urls$1 implements Iterator<String>, KMutableIterator {
    @NotNull
    public final Iterator<DiskLruCache.Snapshot> h;
    @Nullable
    public String i;
    public boolean j;
    public final /* synthetic */ Cache k;

    public Cache$urls$1(Cache cache) {
        this.k = cache;
        this.h = cache.getCache$okhttp().snapshots();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.i != null) {
            return true;
        }
        this.j = false;
        while (this.h.hasNext()) {
            try {
                DiskLruCache.Snapshot next = this.h.next();
                this.i = Okio.buffer(next.getSource(0)).readUtf8LineStrict();
                CloseableKt.closeFinally(next, null);
                return true;
            } catch (IOException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.j) {
            this.h.remove();
            return;
        }
        throw new IllegalStateException("remove() before next()".toString());
    }

    @Override // java.util.Iterator
    @NotNull
    public String next() {
        if (hasNext()) {
            String str = this.i;
            Intrinsics.checkNotNull(str);
            this.i = null;
            this.j = true;
            return str;
        }
        throw new NoSuchElementException();
    }
}
