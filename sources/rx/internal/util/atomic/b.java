package rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes13.dex */
public abstract class b<E> extends AbstractQueue<E> {
    public final AtomicReference<LinkedQueueNode<E>> h = new AtomicReference<>();
    public final AtomicReference<LinkedQueueNode<E>> i = new AtomicReference<>();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return lvConsumerNode() == lvProducerNode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final LinkedQueueNode<E> lpConsumerNode() {
        return this.i.get();
    }

    public final LinkedQueueNode<E> lpProducerNode() {
        return this.h.get();
    }

    public final LinkedQueueNode<E> lvConsumerNode() {
        return this.i.get();
    }

    public final LinkedQueueNode<E> lvProducerNode() {
        return this.h.get();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        LinkedQueueNode<E> lvNext;
        LinkedQueueNode<E> lvConsumerNode = lvConsumerNode();
        LinkedQueueNode<E> lvProducerNode = lvProducerNode();
        int i = 0;
        while (lvConsumerNode != lvProducerNode && i < Integer.MAX_VALUE) {
            do {
                lvNext = lvConsumerNode.lvNext();
            } while (lvNext == null);
            i++;
            lvConsumerNode = lvNext;
        }
        return i;
    }

    public final void spConsumerNode(LinkedQueueNode<E> linkedQueueNode) {
        this.i.lazySet(linkedQueueNode);
    }

    public final void spProducerNode(LinkedQueueNode<E> linkedQueueNode) {
        this.h.lazySet(linkedQueueNode);
    }

    public final LinkedQueueNode<E> xchgProducerNode(LinkedQueueNode<E> linkedQueueNode) {
        return this.h.getAndSet(linkedQueueNode);
    }
}
