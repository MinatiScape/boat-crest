package com.google.android.material.internal;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import com.google.android.material.internal.MaterialCheckable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
@UiThread
/* loaded from: classes10.dex */
public class CheckableGroup<T extends MaterialCheckable<T>> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Integer, T> f10309a = new HashMap();
    public final Set<Integer> b = new HashSet();
    public OnCheckedStateChangeListener c;
    public boolean d;
    public boolean e;

    /* loaded from: classes10.dex */
    public interface OnCheckedStateChangeListener {
        void onCheckedStateChanged(@NonNull Set<Integer> set);
    }

    /* loaded from: classes10.dex */
    public class a implements MaterialCheckable.OnCheckedChangeListener<T> {
        public a() {
        }

        @Override // com.google.android.material.internal.MaterialCheckable.OnCheckedChangeListener
        /* renamed from: a */
        public void onCheckedChanged(T t, boolean z) {
            if (z) {
                if (!CheckableGroup.this.e(t)) {
                    return;
                }
            } else {
                CheckableGroup checkableGroup = CheckableGroup.this;
                if (!checkableGroup.g(t, checkableGroup.e)) {
                    return;
                }
            }
            CheckableGroup.this.f();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addCheckable(T t) {
        this.f10309a.put(Integer.valueOf(t.getId()), t);
        if (t.isChecked()) {
            e(t);
        }
        t.setInternalOnCheckedChangeListener(new a());
    }

    public void check(@IdRes int i) {
        T t = this.f10309a.get(Integer.valueOf(i));
        if (t != null && e(t)) {
            f();
        }
    }

    public void clearCheck() {
        boolean z = !this.b.isEmpty();
        for (T t : this.f10309a.values()) {
            g(t, false);
        }
        if (z) {
            f();
        }
    }

    public final boolean e(@NonNull MaterialCheckable<T> materialCheckable) {
        int id = materialCheckable.getId();
        if (this.b.contains(Integer.valueOf(id))) {
            return false;
        }
        T t = this.f10309a.get(Integer.valueOf(getSingleCheckedId()));
        if (t != null) {
            g(t, false);
        }
        boolean add = this.b.add(Integer.valueOf(id));
        if (!materialCheckable.isChecked()) {
            materialCheckable.setChecked(true);
        }
        return add;
    }

    public final void f() {
        OnCheckedStateChangeListener onCheckedStateChangeListener = this.c;
        if (onCheckedStateChangeListener != null) {
            onCheckedStateChangeListener.onCheckedStateChanged(getCheckedIds());
        }
    }

    public final boolean g(@NonNull MaterialCheckable<T> materialCheckable, boolean z) {
        int id = materialCheckable.getId();
        if (this.b.contains(Integer.valueOf(id))) {
            if (z && this.b.size() == 1 && this.b.contains(Integer.valueOf(id))) {
                materialCheckable.setChecked(true);
                return false;
            }
            boolean remove = this.b.remove(Integer.valueOf(id));
            if (materialCheckable.isChecked()) {
                materialCheckable.setChecked(false);
            }
            return remove;
        }
        return false;
    }

    @NonNull
    public Set<Integer> getCheckedIds() {
        return new HashSet(this.b);
    }

    @NonNull
    public List<Integer> getCheckedIdsSortedByChildOrder(@NonNull ViewGroup viewGroup) {
        Set<Integer> checkedIds = getCheckedIds();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof MaterialCheckable) && checkedIds.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    @IdRes
    public int getSingleCheckedId() {
        if (!this.d || this.b.isEmpty()) {
            return -1;
        }
        return this.b.iterator().next().intValue();
    }

    public boolean isSelectionRequired() {
        return this.e;
    }

    public boolean isSingleSelection() {
        return this.d;
    }

    public void removeCheckable(T t) {
        t.setInternalOnCheckedChangeListener(null);
        this.f10309a.remove(Integer.valueOf(t.getId()));
        this.b.remove(Integer.valueOf(t.getId()));
    }

    public void setOnCheckedStateChangeListener(@Nullable OnCheckedStateChangeListener onCheckedStateChangeListener) {
        this.c = onCheckedStateChangeListener;
    }

    public void setSelectionRequired(boolean z) {
        this.e = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.d != z) {
            this.d = z;
            clearCheck();
        }
    }

    public void uncheck(@IdRes int i) {
        T t = this.f10309a.get(Integer.valueOf(i));
        if (t != null && g(t, this.e)) {
            f();
        }
    }
}
