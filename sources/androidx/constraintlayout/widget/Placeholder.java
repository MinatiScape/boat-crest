package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jieli.jl_rcsp.constant.Command;
/* loaded from: classes.dex */
public class Placeholder extends View {
    public int h;
    public View i;
    public int j;

    public Placeholder(Context context) {
        super(context);
        this.h = -1;
        this.i = null;
        this.j = 4;
        a(null);
    }

    public final void a(AttributeSet attributeSet) {
        super.setVisibility(this.j);
        this.h = -1;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_placeholder);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_placeholder_content) {
                    this.h = obtainStyledAttributes.getResourceId(index, this.h);
                } else if (index == R.styleable.ConstraintLayout_placeholder_placeholder_emptyVisibility) {
                    this.j = obtainStyledAttributes.getInt(index, this.j);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public View getContent() {
        return this.i;
    }

    public int getEmptyVisibility() {
        return this.j;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            canvas.drawRGB(223, 223, 223);
            Paint paint = new Paint();
            paint.setARGB(255, Command.CMD_RECEIVE_SPEECH_CANCEL, Command.CMD_RECEIVE_SPEECH_CANCEL, Command.CMD_RECEIVE_SPEECH_CANCEL);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            paint.setTextSize(rect.height());
            int height = rect.height();
            int width = rect.width();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.getTextBounds("?", 0, 1, rect);
            canvas.drawText("?", ((width / 2.0f) - (rect.width() / 2.0f)) - rect.left, ((height / 2.0f) + (rect.height() / 2.0f)) - rect.bottom, paint);
        }
    }

    public void setContentId(int i) {
        View findViewById;
        if (this.h == i) {
            return;
        }
        View view = this.i;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.LayoutParams) this.i.getLayoutParams()).j = false;
            this.i = null;
        }
        this.h = i;
        if (i == -1 || (findViewById = ((View) getParent()).findViewById(i)) == null) {
            return;
        }
        findViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i) {
        this.j = i;
    }

    public void updatePostMeasure(ConstraintLayout constraintLayout) {
        if (this.i == null) {
            return;
        }
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams2.v.setVisibility(0);
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = layoutParams.v.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        if (horizontalDimensionBehaviour != dimensionBehaviour) {
            layoutParams.v.setWidth(layoutParams2.v.getWidth());
        }
        if (layoutParams.v.getVerticalDimensionBehaviour() != dimensionBehaviour) {
            layoutParams.v.setHeight(layoutParams2.v.getHeight());
        }
        layoutParams2.v.setVisibility(8);
    }

    public void updatePreLayout(ConstraintLayout constraintLayout) {
        if (this.h == -1 && !isInEditMode()) {
            setVisibility(this.j);
        }
        View findViewById = constraintLayout.findViewById(this.h);
        this.i = findViewById;
        if (findViewById != null) {
            ((ConstraintLayout.LayoutParams) findViewById.getLayoutParams()).j = true;
            this.i.setVisibility(0);
            setVisibility(0);
        }
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = -1;
        this.i = null;
        this.j = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = -1;
        this.i = null;
        this.j = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.h = -1;
        this.i = null;
        this.j = 4;
        a(attributeSet);
    }
}
