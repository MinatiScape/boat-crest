package okhttp3.internal.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import okhttp3.internal.cache.DiskLruCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class DiskLruCache$snapshots$1 implements Iterator<DiskLruCache.Snapshot>, KMutableIterator {
    @NotNull
    public final Iterator<DiskLruCache.Entry> h;
    @Nullable
    public DiskLruCache.Snapshot i;
    @Nullable
    public DiskLruCache.Snapshot j;
    public final /* synthetic */ DiskLruCache k;

    public DiskLruCache$snapshots$1(DiskLruCache diskLruCache) {
        this.k = diskLruCache;
        Iterator<DiskLruCache.Entry> it = new ArrayList(diskLruCache.getLruEntries$okhttp().values()).iterator();
        Intrinsics.checkNotNullExpressionValue(it, "ArrayList(lruEntries.values).iterator()");
        this.h = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.i != null) {
            return true;
        }
        DiskLruCache diskLruCache = this.k;
        synchronized (diskLruCache) {
            if (diskLruCache.getClosed$okhttp()) {
                return false;
            }
            while (this.h.hasNext()) {
                DiskLruCache.Entry next = this.h.next();
                DiskLruCache.Snapshot snapshot$okhttp = next == null ? null : next.snapshot$okhttp();
                if (snapshot$okhttp != null) {
                    this.i = snapshot$okhttp;
                    return true;
                }
            }
            Unit unit = Unit.INSTANCE;
            return false;
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        DiskLruCache.Snapshot snapshot = this.j;
        if (snapshot != null) {
            try {
                this.k.remove(snapshot.key());
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.j = null;
                throw th;
            }
            this.j = null;
            return;
        }
        throw new IllegalStateException("remove() before next()".toString());
    }

    @Override // java.util.Iterator
    @NotNull
    public DiskLruCache.Snapshot next() {
        if (hasNext()) {
            DiskLruCache.Snapshot snapshot = this.i;
            this.j = snapshot;
            this.i = null;
            Intrinsics.checkNotNull(snapshot);
            return snapshot;
        }
        throw new NoSuchElementException();
    }
}
