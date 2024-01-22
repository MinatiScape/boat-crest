package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class CompoundTrimPathContent {

    /* renamed from: a  reason: collision with root package name */
    public final List<TrimPathContent> f1993a = new ArrayList();

    public void a(TrimPathContent trimPathContent) {
        this.f1993a.add(trimPathContent);
    }

    public void apply(Path path) {
        for (int size = this.f1993a.size() - 1; size >= 0; size--) {
            Utils.applyTrimPathIfNeeded(path, this.f1993a.get(size));
        }
    }
}
