package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.shader.CircleShader;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
/* loaded from: classes9.dex */
public class CircularImageView extends ShaderImageView {
    public CircleShader i;

    public CircularImageView(Context context) {
        super(context);
    }

    @Override // com.github.siyamed.shapeimageview.ShaderImageView
    public ShaderHelper createImageViewHelper() {
        CircleShader circleShader = new CircleShader();
        this.i = circleShader;
        return circleShader;
    }

    public float getBorderRadius() {
        CircleShader circleShader = this.i;
        if (circleShader != null) {
            return circleShader.getBorderRadius();
        }
        return 0.0f;
    }

    public void setBorderRadius(float f) {
        CircleShader circleShader = this.i;
        if (circleShader != null) {
            circleShader.setBorderRadius(f);
            invalidate();
        }
    }

    public CircularImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
