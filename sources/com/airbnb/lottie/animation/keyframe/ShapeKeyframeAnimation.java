package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import androidx.annotation.Nullable;
import com.airbnb.lottie.animation.content.ShapeModifierContent;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class ShapeKeyframeAnimation extends BaseKeyframeAnimation<ShapeData, Path> {
    public final ShapeData g;
    public final Path h;
    public List<ShapeModifierContent> i;

    public ShapeKeyframeAnimation(List<Keyframe<ShapeData>> list) {
        super(list);
        this.g = new ShapeData();
        this.h = new Path();
    }

    public void setShapeModifiers(@Nullable List<ShapeModifierContent> list) {
        this.i = list;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Path getValue(Keyframe<ShapeData> keyframe, float f) {
        this.g.interpolateBetween(keyframe.startValue, keyframe.endValue, f);
        ShapeData shapeData = this.g;
        List<ShapeModifierContent> list = this.i;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                shapeData = this.i.get(size).modifyShape(shapeData);
            }
        }
        MiscUtils.getPathFromData(shapeData, this.h);
        return this.h;
    }
}
