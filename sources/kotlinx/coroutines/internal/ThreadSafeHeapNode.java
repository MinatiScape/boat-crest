package kotlinx.coroutines.internal;

import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.Nullable;
@InternalCoroutinesApi
/* loaded from: classes12.dex */
public interface ThreadSafeHeapNode {
    @Nullable
    ThreadSafeHeap<?> getHeap();

    int getIndex();

    void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap);

    void setIndex(int i);
}
