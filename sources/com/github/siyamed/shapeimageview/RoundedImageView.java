package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.shader.RoundedShader;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
/* loaded from: classes9.dex */
public class RoundedImageView extends ShaderImageView {
    public RoundedShader i;

    public RoundedImageView(Context context) {
        super(context);
    }

    @Override // com.github.siyamed.shapeimageview.ShaderImageView
    public ShaderHelper createImageViewHelper() {
        RoundedShader roundedShader = new RoundedShader();
        this.i = roundedShader;
        return roundedShader;
    }

    public final int getRadius() {
        RoundedShader roundedShader = this.i;
        if (roundedShader != null) {
            return roundedShader.getRadius();
        }
        return 0;
    }

    public final void setRadius(int i) {
        RoundedShader roundedShader = this.i;
        if (roundedShader != null) {
            roundedShader.setRadius(i);
            invalidate();
        }
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
