package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
import com.github.siyamed.shapeimageview.shader.SvgShader;
/* loaded from: classes9.dex */
public class HexagonImageView extends ShaderImageView {
    public HexagonImageView(Context context) {
        super(context);
    }

    @Override // com.github.siyamed.shapeimageview.ShaderImageView
    public ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.imgview_hexagon);
    }

    public HexagonImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HexagonImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
