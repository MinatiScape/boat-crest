package com.h6ah4i.android.widget.advrecyclerview.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class AdapterPath {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final List<AdapterPathSegment> f11889a = new ArrayList();

    @NonNull
    public AdapterPath append(@NonNull UnwrapPositionResult unwrapPositionResult) {
        return append(unwrapPositionResult.adapter, unwrapPositionResult.tag);
    }

    @NonNull
    public AdapterPath clear() {
        this.f11889a.clear();
        return this;
    }

    @Nullable
    public AdapterPathSegment firstSegment() {
        if (this.f11889a.isEmpty()) {
            return null;
        }
        return this.f11889a.get(0);
    }

    public boolean isEmpty() {
        return this.f11889a.isEmpty();
    }

    @Nullable
    public AdapterPathSegment lastSegment() {
        if (this.f11889a.isEmpty()) {
            return null;
        }
        List<AdapterPathSegment> list = this.f11889a;
        return list.get(list.size() - 1);
    }

    @NonNull
    public List<AdapterPathSegment> segments() {
        return this.f11889a;
    }

    @NonNull
    public AdapterPath append(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj) {
        return append(new AdapterPathSegment(adapter, obj));
    }

    @NonNull
    public AdapterPath append(@NonNull AdapterPathSegment adapterPathSegment) {
        this.f11889a.add(adapterPathSegment);
        return this;
    }
}
