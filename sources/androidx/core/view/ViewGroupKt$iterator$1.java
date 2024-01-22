package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class ViewGroupKt$iterator$1 implements Iterator<View>, KMutableIterator {
    public int h;
    public final /* synthetic */ ViewGroup i;

    public ViewGroupKt$iterator$1(ViewGroup viewGroup) {
        this.i = viewGroup;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h < this.i.getChildCount();
    }

    @Override // java.util.Iterator
    public void remove() {
        ViewGroup viewGroup = this.i;
        int i = this.h - 1;
        this.h = i;
        viewGroup.removeViewAt(i);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    @NotNull
    public View next() {
        ViewGroup viewGroup = this.i;
        int i = this.h;
        this.h = i + 1;
        View childAt = viewGroup.getChildAt(i);
        if (childAt != null) {
            return childAt;
        }
        throw new IndexOutOfBoundsException();
    }
}
