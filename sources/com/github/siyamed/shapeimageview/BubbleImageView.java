package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.shader.BubbleShader;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
/* loaded from: classes9.dex */
public class BubbleImageView extends ShaderImageView {
    public BubbleShader i;

    public BubbleImageView(Context context) {
        super(context);
    }

    @Override // com.github.siyamed.shapeimageview.ShaderImageView
    public ShaderHelper createImageViewHelper() {
        BubbleShader bubbleShader = new BubbleShader();
        this.i = bubbleShader;
        return bubbleShader;
    }

    public BubbleShader.ArrowPosition getArrowPosition() {
        BubbleShader bubbleShader = this.i;
        if (bubbleShader != null) {
            return bubbleShader.getArrowPosition();
        }
        return BubbleShader.ArrowPosition.LEFT;
    }

    public int getTriangleHeightPx() {
        BubbleShader bubbleShader = this.i;
        if (bubbleShader != null) {
            return bubbleShader.getTriangleHeightPx();
        }
        return 0;
    }

    public void setArrowPosition(BubbleShader.ArrowPosition arrowPosition) {
        BubbleShader bubbleShader = this.i;
        if (bubbleShader != null) {
            bubbleShader.setArrowPosition(arrowPosition);
            invalidate();
        }
    }

    public void setTriangleHeightPx(int i) {
        BubbleShader bubbleShader = this.i;
        if (bubbleShader != null) {
            bubbleShader.setTriangleHeightPx(i);
            invalidate();
        }
    }

    public BubbleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BubbleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
