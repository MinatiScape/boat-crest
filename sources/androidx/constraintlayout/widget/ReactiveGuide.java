package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.SharedValues;
/* loaded from: classes.dex */
public class ReactiveGuide extends View implements SharedValues.SharedValuesListener {
    public int h;
    public boolean i;
    public int j;
    public boolean k;

    public ReactiveGuide(Context context) {
        super(context);
        this.h = -1;
        this.i = false;
        this.j = 0;
        this.k = true;
        super.setVisibility(8);
        b(null);
    }

    public final void a(int i, int i2, MotionLayout motionLayout, int i3) {
        ConstraintSet constraintSet = motionLayout.getConstraintSet(i3);
        constraintSet.setGuidelineEnd(i2, i);
        motionLayout.updateState(i3, constraintSet);
    }

    public final void b(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_ReactiveGuide);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_valueId) {
                    this.h = obtainStyledAttributes.getResourceId(index, this.h);
                } else if (index == R.styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_animateChange) {
                    this.i = obtainStyledAttributes.getBoolean(index, this.i);
                } else if (index == R.styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToConstraintSet) {
                    this.j = obtainStyledAttributes.getResourceId(index, this.j);
                } else if (index == R.styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToAllConstraintSets) {
                    this.k = obtainStyledAttributes.getBoolean(index, this.k);
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (this.h != -1) {
            ConstraintLayout.getSharedValues().addListener(this.h, this);
        }
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    public int getApplyToConstraintSetId() {
        return this.j;
    }

    public int getAttributeId() {
        return this.h;
    }

    public boolean isAnimatingChange() {
        return this.i;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    @Override // androidx.constraintlayout.widget.SharedValues.SharedValuesListener
    public void onNewValue(int i, int i2, int i3) {
        setGuidelineBegin(i2);
        int id = getId();
        if (id > 0 && (getParent() instanceof MotionLayout)) {
            MotionLayout motionLayout = (MotionLayout) getParent();
            int currentState = motionLayout.getCurrentState();
            int i4 = this.j;
            if (i4 != 0) {
                currentState = i4;
            }
            int i5 = 0;
            if (this.i) {
                if (this.k) {
                    int[] constraintSetIds = motionLayout.getConstraintSetIds();
                    while (i5 < constraintSetIds.length) {
                        int i6 = constraintSetIds[i5];
                        if (i6 != currentState) {
                            a(i2, id, motionLayout, i6);
                        }
                        i5++;
                    }
                }
                ConstraintSet cloneConstraintSet = motionLayout.cloneConstraintSet(currentState);
                cloneConstraintSet.setGuidelineEnd(id, i2);
                motionLayout.updateStateAnimate(currentState, cloneConstraintSet, 1000);
            } else if (this.k) {
                int[] constraintSetIds2 = motionLayout.getConstraintSetIds();
                while (i5 < constraintSetIds2.length) {
                    a(i2, id, motionLayout, constraintSetIds2[i5]);
                    i5++;
                }
            } else {
                a(i2, id, motionLayout, currentState);
            }
        }
    }

    public void setAnimateChange(boolean z) {
        this.i = z;
    }

    public void setApplyToConstraintSetId(int i) {
        this.j = i;
    }

    public void setAttributeId(int i) {
        SharedValues sharedValues = ConstraintLayout.getSharedValues();
        int i2 = this.h;
        if (i2 != -1) {
            sharedValues.removeListener(i2, this);
        }
        this.h = i;
        if (i != -1) {
            sharedValues.addListener(i, this);
        }
    }

    public void setGuidelineBegin(int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.guideBegin = i;
        setLayoutParams(layoutParams);
    }

    public void setGuidelineEnd(int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.guideEnd = i;
        setLayoutParams(layoutParams);
    }

    public void setGuidelinePercent(float f) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.guidePercent = f;
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = -1;
        this.i = false;
        this.j = 0;
        this.k = true;
        super.setVisibility(8);
        b(attributeSet);
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = -1;
        this.i = false;
        this.j = 0;
        this.k = true;
        super.setVisibility(8);
        b(attributeSet);
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.h = -1;
        this.i = false;
        this.j = 0;
        this.k = true;
        super.setVisibility(8);
        b(attributeSet);
    }
}
