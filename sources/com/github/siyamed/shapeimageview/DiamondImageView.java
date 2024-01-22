package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
import com.github.siyamed.shapeimageview.shader.SvgShader;
/* loaded from: classes9.dex */
public class DiamondImageView extends ShaderImageView {
    public DiamondImageView(Context context) {
        super(context);
    }

    @Override // com.github.siyamed.shapeimageview.ShaderImageView
    public ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.imgview_diamond);
    }

    public DiamondImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DiamondImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
