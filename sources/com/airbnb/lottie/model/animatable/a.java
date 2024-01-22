package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public abstract class a<V, O> implements AnimatableValue<V, O> {

    /* renamed from: a  reason: collision with root package name */
    public final List<Keyframe<V>> f2033a;

    public a(V v) {
        this(Collections.singletonList(new Keyframe(v)));
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public List<Keyframe<V>> getKeyframes() {
        return this.f2033a;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public boolean isStatic() {
        return this.f2033a.isEmpty() || (this.f2033a.size() == 1 && this.f2033a.get(0).isStatic());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f2033a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f2033a.toArray()));
        }
        return sb.toString();
    }

    public a(List<Keyframe<V>> list) {
        this.f2033a = list;
    }
}
