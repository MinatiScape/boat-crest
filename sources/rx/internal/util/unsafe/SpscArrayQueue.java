package rx.internal.util.unsafe;

import java.util.Objects;
import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public final class SpscArrayQueue<E> extends v<E> {
    public SpscArrayQueue(int i) {
        super(i);
    }

    public final void a(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, s.C_INDEX_OFFSET, j);
    }

    public final void b(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, w.P_INDEX_OFFSET, j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, rx.internal.util.unsafe.MessagePassingQueue
    public boolean isEmpty() {
        return lvProducerIndex() == lvConsumerIndex();
    }

    public final long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, s.C_INDEX_OFFSET);
    }

    public final long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, w.P_INDEX_OFFSET);
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public boolean offer(E e) {
        Objects.requireNonNull(e, "null elements not allowed");
        E[] eArr = this.buffer;
        long j = this.producerIndex;
        long calcElementOffset = calcElementOffset(j);
        if (lvElement(eArr, calcElementOffset) != null) {
            return false;
        }
        soElement(eArr, calcElementOffset, e);
        b(j + 1);
        return true;
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E peek() {
        return lvElement(calcElementOffset(this.consumerIndex));
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E poll() {
        long j = this.consumerIndex;
        long calcElementOffset = calcElementOffset(j);
        E[] eArr = this.buffer;
        E lvElement = lvElement(eArr, calcElementOffset);
        if (lvElement == null) {
            return null;
        }
        soElement(eArr, calcElementOffset, null);
        a(j + 1);
        return lvElement;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, rx.internal.util.unsafe.MessagePassingQueue
    public int size() {
        long lvConsumerIndex = lvConsumerIndex();
        while (true) {
            long lvProducerIndex = lvProducerIndex();
            long lvConsumerIndex2 = lvConsumerIndex();
            if (lvConsumerIndex == lvConsumerIndex2) {
                return (int) (lvProducerIndex - lvConsumerIndex2);
            }
            lvConsumerIndex = lvConsumerIndex2;
        }
    }
}
