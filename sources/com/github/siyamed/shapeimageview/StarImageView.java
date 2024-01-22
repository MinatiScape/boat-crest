package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
import com.github.siyamed.shapeimageview.shader.SvgShader;
/* loaded from: classes9.dex */
public class StarImageView extends ShaderImageView {
    public StarImageView(Context context) {
        super(context);
    }

    @Override // com.github.siyamed.shapeimageview.ShaderImageView
    public ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.imgview_star);
    }

    public StarImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StarImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
