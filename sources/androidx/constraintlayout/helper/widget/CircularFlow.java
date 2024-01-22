package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.VirtualLayout;
import java.util.Arrays;
/* loaded from: classes.dex */
public class CircularFlow extends VirtualLayout {
    public static int u;
    public static float v;
    public ConstraintLayout k;
    public int l;
    public float[] m;
    public int[] n;
    public int o;
    public int p;
    public String q;
    public String r;
    public Float s;
    public Integer t;

    public CircularFlow(Context context) {
        super(context);
    }

    public static int[] removeElementFromArray(int[] iArr, int i) {
        int[] iArr2 = new int[iArr.length - 1];
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (i3 != i) {
                iArr2[i2] = iArr[i3];
                i2++;
            }
        }
        return iArr2;
    }

    private void setAngles(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        this.p = 0;
        while (true) {
            int indexOf = str.indexOf(44, i);
            if (indexOf == -1) {
                g(str.substring(i).trim());
                return;
            } else {
                g(str.substring(i, indexOf).trim());
                i = indexOf + 1;
            }
        }
    }

    private void setRadius(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        this.o = 0;
        while (true) {
            int indexOf = str.indexOf(44, i);
            if (indexOf == -1) {
                h(str.substring(i).trim());
                return;
            } else {
                h(str.substring(i, indexOf).trim());
                i = indexOf + 1;
            }
        }
    }

    public void addViewToCircularFlow(View view, int i, float f) {
        if (containsId(view.getId())) {
            return;
        }
        addView(view);
        this.p++;
        float[] angles = getAngles();
        this.m = angles;
        angles[this.p - 1] = f;
        this.o++;
        int[] radius = getRadius();
        this.n = radius;
        radius[this.o - 1] = (int) (i * this.myContext.getResources().getDisplayMetrics().density);
        i();
    }

    public final void g(String str) {
        float[] fArr;
        if (str == null || str.length() == 0 || this.myContext == null || (fArr = this.m) == null) {
            return;
        }
        if (this.p + 1 > fArr.length) {
            this.m = Arrays.copyOf(fArr, fArr.length + 1);
        }
        this.m[this.p] = Integer.parseInt(str);
        this.p++;
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.m, this.p);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.n, this.o);
    }

    public final void h(String str) {
        int[] iArr;
        if (str == null || str.length() == 0 || this.myContext == null || (iArr = this.n) == null) {
            return;
        }
        if (this.o + 1 > iArr.length) {
            this.n = Arrays.copyOf(iArr, iArr.length + 1);
        }
        this.n[this.o] = (int) (Integer.parseInt(str) * this.myContext.getResources().getDisplayMetrics().density);
        this.o++;
    }

    public final void i() {
        this.k = (ConstraintLayout) getParent();
        for (int i = 0; i < this.mCount; i++) {
            View viewById = this.k.getViewById(this.mIds[i]);
            if (viewById != null) {
                int i2 = u;
                float f = v;
                int[] iArr = this.n;
                if (iArr != null && i < iArr.length) {
                    i2 = iArr[i];
                } else {
                    Integer num = this.t;
                    if (num == null || num.intValue() == -1) {
                        Log.e("CircularFlow", "Added radius to view with id: " + this.mMap.get(Integer.valueOf(viewById.getId())));
                    } else {
                        this.o++;
                        if (this.n == null) {
                            this.n = new int[1];
                        }
                        int[] radius = getRadius();
                        this.n = radius;
                        radius[this.o - 1] = i2;
                    }
                }
                float[] fArr = this.m;
                if (fArr != null && i < fArr.length) {
                    f = fArr[i];
                } else {
                    Float f2 = this.s;
                    if (f2 == null || f2.floatValue() == -1.0f) {
                        Log.e("CircularFlow", "Added angle to view with id: " + this.mMap.get(Integer.valueOf(viewById.getId())));
                    } else {
                        this.p++;
                        if (this.m == null) {
                            this.m = new float[1];
                        }
                        float[] angles = getAngles();
                        this.m = angles;
                        angles[this.p - 1] = f;
                    }
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewById.getLayoutParams();
                layoutParams.circleAngle = f;
                layoutParams.circleConstraint = this.l;
                layoutParams.circleRadius = i2;
                viewById.setLayoutParams(layoutParams);
            }
        }
        applyLayoutFeatures();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_circularflow_viewCenter) {
                    this.l = obtainStyledAttributes.getResourceId(index, 0);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_angles) {
                    String string = obtainStyledAttributes.getString(index);
                    this.q = string;
                    setAngles(string);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_radiusInDP) {
                    String string2 = obtainStyledAttributes.getString(index);
                    this.r = string2;
                    setRadius(string2);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_defaultAngle) {
                    Float valueOf = Float.valueOf(obtainStyledAttributes.getFloat(index, v));
                    this.s = valueOf;
                    setDefaultAngle(valueOf.floatValue());
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_defaultRadius) {
                    Integer valueOf2 = Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(index, u));
                    this.t = valueOf2;
                    setDefaultRadius(valueOf2.intValue());
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public boolean isUpdatable(View view) {
        return containsId(view.getId()) && indexFromId(view.getId()) != -1;
    }

    public final float[] j(float[] fArr, int i) {
        return (fArr == null || i < 0 || i >= this.p) ? fArr : removeElementFromArray(fArr, i);
    }

    public final int[] k(int[] iArr, int i) {
        return (iArr == null || i < 0 || i >= this.o) ? iArr : removeElementFromArray(iArr, i);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.q;
        if (str != null) {
            this.m = new float[1];
            setAngles(str);
        }
        String str2 = this.r;
        if (str2 != null) {
            this.n = new int[1];
            setRadius(str2);
        }
        Float f = this.s;
        if (f != null) {
            setDefaultAngle(f.floatValue());
        }
        Integer num = this.t;
        if (num != null) {
            setDefaultRadius(num.intValue());
        }
        i();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public int removeView(View view) {
        int removeView = super.removeView(view);
        if (removeView == -1) {
            return removeView;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.k);
        constraintSet.clear(view.getId(), 8);
        constraintSet.applyTo(this.k);
        float[] fArr = this.m;
        if (removeView < fArr.length) {
            this.m = j(fArr, removeView);
            this.p--;
        }
        int[] iArr = this.n;
        if (removeView < iArr.length) {
            this.n = k(iArr, removeView);
            this.o--;
        }
        i();
        return removeView;
    }

    public void setDefaultAngle(float f) {
        v = f;
    }

    public void setDefaultRadius(int i) {
        u = i;
    }

    public void updateAngle(View view, float f) {
        if (!isUpdatable(view)) {
            Log.e("CircularFlow", "It was not possible to update angle to view with id: " + view.getId());
            return;
        }
        int indexFromId = indexFromId(view.getId());
        if (indexFromId > this.m.length) {
            return;
        }
        float[] angles = getAngles();
        this.m = angles;
        angles[indexFromId] = f;
        i();
    }

    public void updateRadius(View view, int i) {
        if (!isUpdatable(view)) {
            Log.e("CircularFlow", "It was not possible to update radius to view with id: " + view.getId());
            return;
        }
        int indexFromId = indexFromId(view.getId());
        if (indexFromId > this.n.length) {
            return;
        }
        int[] radius = getRadius();
        this.n = radius;
        radius[indexFromId] = (int) (i * this.myContext.getResources().getDisplayMetrics().density);
        i();
    }

    public void updateReference(View view, int i, float f) {
        if (!isUpdatable(view)) {
            Log.e("CircularFlow", "It was not possible to update radius and angle to view with id: " + view.getId());
            return;
        }
        int indexFromId = indexFromId(view.getId());
        if (getAngles().length > indexFromId) {
            float[] angles = getAngles();
            this.m = angles;
            angles[indexFromId] = f;
        }
        if (getRadius().length > indexFromId) {
            int[] radius = getRadius();
            this.n = radius;
            radius[indexFromId] = (int) (i * this.myContext.getResources().getDisplayMetrics().density);
        }
        i();
    }

    public CircularFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularFlow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static float[] removeElementFromArray(float[] fArr, int i) {
        float[] fArr2 = new float[fArr.length - 1];
        int i2 = 0;
        for (int i3 = 0; i3 < fArr.length; i3++) {
            if (i3 != i) {
                fArr2[i2] = fArr[i3];
                i2++;
            }
        }
        return fArr2;
    }
}
