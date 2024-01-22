package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class MultiValueSet<C> {

    /* renamed from: a  reason: collision with root package name */
    public Set<C> f708a = new HashSet();

    public void addAll(@NonNull List<C> list) {
        this.f708a.addAll(list);
    }

    @Override // 
    @NonNull
    /* renamed from: clone */
    public abstract MultiValueSet<C> mo3clone();

    @NonNull
    public List<C> getAllItems() {
        return Collections.unmodifiableList(new ArrayList(this.f708a));
    }
}
