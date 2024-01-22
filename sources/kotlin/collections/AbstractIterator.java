package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class AbstractIterator<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    public c0 h = c0.NotReady;
    @Nullable
    public T i;

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[c0.values().length];
            try {
                iArr[c0.Done.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[c0.Ready.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final boolean a() {
        this.h = c0.Failed;
        computeNext();
        return this.h == c0.Ready;
    }

    public abstract void computeNext();

    public final void done() {
        this.h = c0.Done;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        c0 c0Var = this.h;
        if (c0Var != c0.Failed) {
            int i = WhenMappings.$EnumSwitchMapping$0[c0Var.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return a();
                }
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            this.h = c0.NotReady;
            return this.i;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setNext(T t) {
        this.i = t;
        this.h = c0.Ready;
    }
}
