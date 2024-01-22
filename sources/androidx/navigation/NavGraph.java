package androidx.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.SparseArrayCompat;
import androidx.navigation.NavDestination;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
public class NavGraph extends NavDestination implements Iterable<NavDestination> {
    public final SparseArrayCompat<NavDestination> q;
    public int r;
    public String s;

    /* loaded from: classes.dex */
    public class a implements Iterator<NavDestination> {
        public int h = -1;
        public boolean i = false;

        public a() {
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public NavDestination next() {
            if (hasNext()) {
                this.i = true;
                SparseArrayCompat<NavDestination> sparseArrayCompat = NavGraph.this.q;
                int i = this.h + 1;
                this.h = i;
                return sparseArrayCompat.valueAt(i);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h + 1 < NavGraph.this.q.size();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.i) {
                NavGraph.this.q.valueAt(this.h).e(null);
                NavGraph.this.q.removeAt(this.h);
                this.h--;
                this.i = false;
                return;
            }
            throw new IllegalStateException("You must call next() before you can remove an element");
        }
    }

    public NavGraph(@NonNull Navigator<? extends NavGraph> navigator) {
        super(navigator);
        this.q = new SparseArrayCompat<>();
    }

    public final void addAll(@NonNull NavGraph navGraph) {
        Iterator<NavDestination> it = navGraph.iterator();
        while (it.hasNext()) {
            it.remove();
            addDestination(it.next());
        }
    }

    public final void addDestination(@NonNull NavDestination navDestination) {
        int id = navDestination.getId();
        if (id != 0) {
            if (id != getId()) {
                NavDestination navDestination2 = this.q.get(id);
                if (navDestination2 == navDestination) {
                    return;
                }
                if (navDestination.getParent() == null) {
                    if (navDestination2 != null) {
                        navDestination2.e(null);
                    }
                    navDestination.e(this);
                    this.q.put(navDestination.getId(), navDestination);
                    return;
                }
                throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
            }
            throw new IllegalArgumentException("Destination " + navDestination + " cannot have the same id as graph " + this);
        }
        throw new IllegalArgumentException("Destinations must have an id. Call setId() or include an android:id in your navigation XML.");
    }

    public final void addDestinations(@NonNull Collection<NavDestination> collection) {
        for (NavDestination navDestination : collection) {
            if (navDestination != null) {
                addDestination(navDestination);
            }
        }
    }

    public final void clear() {
        Iterator<NavDestination> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    @Override // androidx.navigation.NavDestination
    @Nullable
    public NavDestination.a d(@NonNull NavDeepLinkRequest navDeepLinkRequest) {
        NavDestination.a d = super.d(navDeepLinkRequest);
        Iterator<NavDestination> it = iterator();
        while (it.hasNext()) {
            NavDestination.a d2 = it.next().d(navDeepLinkRequest);
            if (d2 != null && (d == null || d2.compareTo(d) > 0)) {
                d = d2;
            }
        }
        return d;
    }

    @Nullable
    public final NavDestination findNode(@IdRes int i) {
        return g(i, true);
    }

    @Nullable
    public final NavDestination g(@IdRes int i, boolean z) {
        NavDestination navDestination = this.q.get(i);
        if (navDestination != null) {
            return navDestination;
        }
        if (!z || getParent() == null) {
            return null;
        }
        return getParent().findNode(i);
    }

    @Override // androidx.navigation.NavDestination
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getDisplayName() {
        return getId() != 0 ? super.getDisplayName() : "the root navigation";
    }

    @IdRes
    public final int getStartDestination() {
        return this.r;
    }

    @NonNull
    public String h() {
        if (this.s == null) {
            this.s = Integer.toString(this.r);
        }
        return this.s;
    }

    @Override // java.lang.Iterable
    @NonNull
    public final Iterator<NavDestination> iterator() {
        return new a();
    }

    @Override // androidx.navigation.NavDestination
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super.onInflate(context, attributeSet);
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, androidx.navigation.common.R.styleable.NavGraphNavigator);
        setStartDestination(obtainAttributes.getResourceId(androidx.navigation.common.R.styleable.NavGraphNavigator_startDestination, 0));
        this.s = NavDestination.c(context, this.r);
        obtainAttributes.recycle();
    }

    public final void remove(@NonNull NavDestination navDestination) {
        int indexOfKey = this.q.indexOfKey(navDestination.getId());
        if (indexOfKey >= 0) {
            this.q.valueAt(indexOfKey).e(null);
            this.q.removeAt(indexOfKey);
        }
    }

    public final void setStartDestination(@IdRes int i) {
        if (i != getId()) {
            this.r = i;
            this.s = null;
            return;
        }
        throw new IllegalArgumentException("Start destination " + i + " cannot use the same id as the graph " + this);
    }

    @Override // androidx.navigation.NavDestination
    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" startDestination=");
        NavDestination findNode = findNode(getStartDestination());
        if (findNode == null) {
            String str = this.s;
            if (str == null) {
                sb.append(HexStringBuilder.DEFAULT_PREFIX);
                sb.append(Integer.toHexString(this.r));
            } else {
                sb.append(str);
            }
        } else {
            sb.append("{");
            sb.append(findNode.toString());
            sb.append("}");
        }
        return sb.toString();
    }

    public final void addDestinations(@NonNull NavDestination... navDestinationArr) {
        for (NavDestination navDestination : navDestinationArr) {
            if (navDestination != null) {
                addDestination(navDestination);
            }
        }
    }
}
