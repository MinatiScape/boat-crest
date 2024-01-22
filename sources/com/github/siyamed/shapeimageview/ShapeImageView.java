package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
import com.github.siyamed.shapeimageview.shader.SvgShader;
/* loaded from: classes9.dex */
public class ShapeImageView extends ShaderImageView {
    public SvgShader i;

    public ShapeImageView(Context context) {
        super(context);
    }

    @Override // com.github.siyamed.shapeimageview.ShaderImageView
    public ShaderHelper createImageViewHelper() {
        SvgShader svgShader = new SvgShader();
        this.i = svgShader;
        return svgShader;
    }

    public void setBorderType(int i) {
        SvgShader svgShader = this.i;
        if (svgShader != null) {
            svgShader.setBorderType(i);
            invalidate();
        }
    }

    public void setShapeResId(int i) {
        SvgShader svgShader = this.i;
        if (svgShader != null) {
            svgShader.setShapeResId(getContext(), i);
            invalidate();
        }
    }

    public void setStrokeCap(int i) {
        SvgShader svgShader = this.i;
        if (svgShader != null) {
            svgShader.setStrokeCap(i);
            invalidate();
        }
    }

    public void setStrokeJoin(int i) {
        SvgShader svgShader = this.i;
        if (svgShader != null) {
            svgShader.setStrokeJoin(i);
            invalidate();
        }
    }

    public void setStrokeMiter(int i) {
        SvgShader svgShader = this.i;
        if (svgShader != null) {
            svgShader.setStrokeMiter(i);
            invalidate();
        }
    }

    public ShapeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ShapeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
