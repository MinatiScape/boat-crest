package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class MotionEffect extends MotionHelper {
    public static final int AUTO = -1;
    public static final int EAST = 2;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final String TAG = "FadeMove";
    public static final int WEST = 3;
    public float l;
    public int m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public int r;
    public int s;

    public MotionEffect(Context context) {
        super(context);
        this.l = 0.1f;
        this.m = 49;
        this.n = 50;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = -1;
        this.s = -1;
    }

    public final void g(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MotionEffect);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.MotionEffect_motionEffect_start) {
                    int i2 = obtainStyledAttributes.getInt(index, this.m);
                    this.m = i2;
                    this.m = Math.max(Math.min(i2, 99), 0);
                } else if (index == R.styleable.MotionEffect_motionEffect_end) {
                    int i3 = obtainStyledAttributes.getInt(index, this.n);
                    this.n = i3;
                    this.n = Math.max(Math.min(i3, 99), 0);
                } else if (index == R.styleable.MotionEffect_motionEffect_translationX) {
                    this.o = obtainStyledAttributes.getDimensionPixelOffset(index, this.o);
                } else if (index == R.styleable.MotionEffect_motionEffect_translationY) {
                    this.p = obtainStyledAttributes.getDimensionPixelOffset(index, this.p);
                } else if (index == R.styleable.MotionEffect_motionEffect_alpha) {
                    this.l = obtainStyledAttributes.getFloat(index, this.l);
                } else if (index == R.styleable.MotionEffect_motionEffect_move) {
                    this.s = obtainStyledAttributes.getInt(index, this.s);
                } else if (index == R.styleable.MotionEffect_motionEffect_strict) {
                    this.q = obtainStyledAttributes.getBoolean(index, this.q);
                } else if (index == R.styleable.MotionEffect_motionEffect_viewTransition) {
                    this.r = obtainStyledAttributes.getResourceId(index, this.r);
                }
            }
            int i4 = this.m;
            int i5 = this.n;
            if (i4 == i5) {
                if (i4 > 0) {
                    this.m = i4 - 1;
                } else {
                    this.n = i5 + 1;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    public boolean isDecorator() {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0188, code lost:
        if (r14 == 0.0f) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x019c, code lost:
        if (r14 == 0.0f) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ac, code lost:
        if (r15 == 0.0f) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01bc, code lost:
        if (r15 == 0.0f) goto L63;
     */
    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPreSetup(androidx.constraintlayout.motion.widget.MotionLayout r22, java.util.HashMap<android.view.View, androidx.constraintlayout.motion.widget.MotionController> r23) {
        /*
            Method dump skipped, instructions count: 502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.helper.widget.MotionEffect.onPreSetup(androidx.constraintlayout.motion.widget.MotionLayout, java.util.HashMap):void");
    }

    public MotionEffect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = 0.1f;
        this.m = 49;
        this.n = 50;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = -1;
        this.s = -1;
        g(context, attributeSet);
    }

    public MotionEffect(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = 0.1f;
        this.m = 49;
        this.n = 50;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = -1;
        this.s = -1;
        g(context, attributeSet);
    }
}
