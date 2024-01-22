package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class MaskKeyframeAnimation {

    /* renamed from: a  reason: collision with root package name */
    public final List<BaseKeyframeAnimation<ShapeData, Path>> f2011a;
    public final List<BaseKeyframeAnimation<Integer, Integer>> b;
    public final List<Mask> c;

    public MaskKeyframeAnimation(List<Mask> list) {
        this.c = list;
        this.f2011a = new ArrayList(list.size());
        this.b = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.f2011a.add(list.get(i).getMaskPath().createAnimation());
            this.b.add(list.get(i).getOpacity().createAnimation());
        }
    }

    public List<BaseKeyframeAnimation<ShapeData, Path>> getMaskAnimations() {
        return this.f2011a;
    }

    public List<Mask> getMasks() {
        return this.c;
    }

    public List<BaseKeyframeAnimation<Integer, Integer>> getOpacityAnimations() {
        return this.b;
    }
}
