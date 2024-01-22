package androidx.core.view;

import android.view.Menu;
import android.view.MenuItem;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class MenuKt$iterator$1 implements Iterator<MenuItem>, KMutableIterator {
    public int h;
    public final /* synthetic */ Menu i;

    public MenuKt$iterator$1(Menu menu) {
        this.i = menu;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h < this.i.size();
    }

    @Override // java.util.Iterator
    public void remove() {
        Unit unit;
        Menu menu = this.i;
        int i = this.h - 1;
        this.h = i;
        MenuItem item = menu.getItem(i);
        if (item != null) {
            Intrinsics.checkNotNullExpressionValue(item, "getItem(index)");
            menu.removeItem(item.getItemId());
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IndexOutOfBoundsException();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    @NotNull
    public MenuItem next() {
        Menu menu = this.i;
        int i = this.h;
        this.h = i + 1;
        MenuItem item = menu.getItem(i);
        if (item != null) {
            return item;
        }
        throw new IndexOutOfBoundsException();
    }
}
