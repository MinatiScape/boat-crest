package androidx.collection;

import com.coveiot.android.leonardo.utils.MusicConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010(\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0003\u001a\u00020\u0002H\u0096\u0002J\u0018\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00018\u00008\u0000H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\"\u0010\u000e\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"androidx/collection/LongSparseArrayKt$valueIterator$1", "", "", "hasNext", "kotlin.jvm.PlatformType", MusicConstants.CMDNEXT, "()Ljava/lang/Object;", "", "h", "I", "getIndex", "()I", "setIndex", "(I)V", FirebaseAnalytics.Param.INDEX, "collection-ktx"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class LongSparseArrayKt$valueIterator$1<T> implements Iterator<T>, KMappedMarker {
    public int h;
    public final /* synthetic */ LongSparseArray i;

    public LongSparseArrayKt$valueIterator$1(LongSparseArray<T> longSparseArray) {
        this.i = longSparseArray;
    }

    public final int getIndex() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h < this.i.size();
    }

    @Override // java.util.Iterator
    public T next() {
        LongSparseArray longSparseArray = this.i;
        int i = this.h;
        this.h = i + 1;
        return (T) longSparseArray.valueAt(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setIndex(int i) {
        this.h = i;
    }
}
