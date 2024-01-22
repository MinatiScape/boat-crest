package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class ActivityStrideLengthAnimationBindingImpl extends ActivityStrideLengthAnimationBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts l = null;
    @Nullable
    public static final SparseIntArray m;
    @NonNull
    public final ConstraintLayout h;
    public InverseBindingListener i;
    public InverseBindingListener j;
    public long k;

    /* loaded from: classes7.dex */
    public class a implements InverseBindingListener {
        public a() {
        }

        @Override // androidx.databinding.InverseBindingListener
        public void onChange() {
            synchronized (ActivityStrideLengthAnimationBindingImpl.this) {
                ActivityStrideLengthAnimationBindingImpl.a(ActivityStrideLengthAnimationBindingImpl.this, 1L);
            }
            ActivityStrideLengthAnimationBindingImpl.this.requestRebind();
        }
    }

    /* loaded from: classes7.dex */
    public class b implements InverseBindingListener {
        public b() {
        }

        @Override // androidx.databinding.InverseBindingListener
        public void onChange() {
            synchronized (ActivityStrideLengthAnimationBindingImpl.this) {
                ActivityStrideLengthAnimationBindingImpl.a(ActivityStrideLengthAnimationBindingImpl.this, 2L);
            }
            ActivityStrideLengthAnimationBindingImpl.this.requestRebind();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        m = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.nestedScrollView, 3);
        sparseIntArray.put(R.id.calculate, 4);
        sparseIntArray.put(R.id.tvCalculateInfo, 5);
        sparseIntArray.put(R.id.ivVideo, 6);
        sparseIntArray.put(R.id.step1, 7);
        sparseIntArray.put(R.id.step1Info, 8);
        sparseIntArray.put(R.id.ivStep1, 9);
        sparseIntArray.put(R.id.distanceBetweenAB, 10);
        sparseIntArray.put(R.id.clDistance, 11);
        sparseIntArray.put(R.id.etDistance, 12);
        sparseIntArray.put(R.id.cm, 13);
        sparseIntArray.put(R.id.step2, 14);
        sparseIntArray.put(R.id.step2Info, 15);
        sparseIntArray.put(R.id.ivStep2, 16);
        sparseIntArray.put(R.id.noSteps, 17);
        sparseIntArray.put(R.id.clSteps, 18);
        sparseIntArray.put(R.id.etSteps, 19);
        sparseIntArray.put(R.id.clLottie, 20);
        sparseIntArray.put(R.id.ivClose, 21);
        sparseIntArray.put(R.id.animationView, 22);
    }

    public ActivityStrideLengthAnimationBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 23, l, m));
    }

    public static /* synthetic */ long a(ActivityStrideLengthAnimationBindingImpl activityStrideLengthAnimationBindingImpl, long j) {
        long j2 = j | activityStrideLengthAnimationBindingImpl.k;
        activityStrideLengthAnimationBindingImpl.k = j2;
        return j2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0045, code lost:
        if ((r6 != null ? r6.length() : 0) > 0) goto L22;
     */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r14 = this;
            monitor-enter(r14)
            long r0 = r14.k     // Catch: java.lang.Throwable -> L6f
            r2 = 0
            r14.k = r2     // Catch: java.lang.Throwable -> L6f
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L6f
            r4 = 7
            long r6 = r0 & r4
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            r7 = 1
            r8 = 16
            r10 = 0
            if (r6 == 0) goto L31
            android.widget.EditText r11 = r14.etSteps
            android.text.Editable r11 = r11.getText()
            if (r11 == 0) goto L21
            int r11 = r11.length()
            goto L22
        L21:
            r11 = r10
        L22:
            if (r11 <= 0) goto L26
            r11 = r7
            goto L27
        L26:
            r11 = r10
        L27:
            if (r6 == 0) goto L32
            if (r11 == 0) goto L2d
            long r0 = r0 | r8
            goto L32
        L2d:
            r12 = 8
            long r0 = r0 | r12
            goto L32
        L31:
            r11 = r10
        L32:
            long r8 = r8 & r0
            int r6 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r6 == 0) goto L48
            android.widget.EditText r6 = r14.etDistance
            android.text.Editable r6 = r6.getText()
            if (r6 == 0) goto L44
            int r6 = r6.length()
            goto L45
        L44:
            r6 = r10
        L45:
            if (r6 <= 0) goto L48
            goto L49
        L48:
            r7 = r10
        L49:
            long r4 = r4 & r0
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L51
            if (r11 == 0) goto L51
            r10 = r7
        L51:
            if (r4 == 0) goto L58
            android.widget.Button r4 = r14.btnCalculate
            r4.setEnabled(r10)
        L58:
            r4 = 4
            long r0 = r0 & r4
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L6e
            android.widget.EditText r0 = r14.etDistance
            androidx.databinding.InverseBindingListener r1 = r14.i
            r2 = 0
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r2, r2, r2, r1)
            android.widget.EditText r0 = r14.etSteps
            androidx.databinding.InverseBindingListener r1 = r14.j
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r2, r2, r2, r1)
        L6e:
            return
        L6f:
            r0 = move-exception
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L6f
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.theme.databinding.ActivityStrideLengthAnimationBindingImpl.executeBindings():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.k != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.k = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivityStrideLengthAnimationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LottieAnimationView) objArr[22], (Button) objArr[1], (TextView) objArr[4], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[18], (TextView) objArr[13], (TextView) objArr[10], (EditText) objArr[12], (EditText) objArr[19], (ImageView) objArr[21], (ImageView) objArr[9], (ImageView) objArr[16], (ImageView) objArr[6], (NestedScrollView) objArr[3], (TextView) objArr[17], (TextView) objArr[7], (TextView) objArr[8], (TextView) objArr[14], (TextView) objArr[15], (View) objArr[2], (TextView) objArr[5]);
        this.i = new a();
        this.j = new b();
        this.k = -1L;
        this.btnCalculate.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
