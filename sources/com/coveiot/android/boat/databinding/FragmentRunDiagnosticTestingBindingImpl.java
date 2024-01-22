package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentRunDiagnosticTestingBindingImpl extends FragmentRunDiagnosticTestingBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.clHeader, 7);
        sparseIntArray.put(R.id.rvDiagnosticTesting, 8);
        sparseIntArray.put(R.id.tvInfo, 9);
        sparseIntArray.put(R.id.btnNo, 10);
        sparseIntArray.put(R.id.btnYes, 11);
    }

    public FragmentRunDiagnosticTestingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, j, k));
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ae, code lost:
        if (r11 != false) goto L79;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBindingImpl.executeBindings():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 128L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding
    public void setHeaderData(@Nullable String str) {
        this.mHeaderData = str;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(43);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding
    public void setShowButtonProceed(@Nullable Boolean bool) {
        this.mShowButtonProceed = bool;
        synchronized (this) {
            this.i |= 16;
        }
        notifyPropertyChanged(94);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding
    public void setShowButtonRepeat(@Nullable Boolean bool) {
        this.mShowButtonRepeat = bool;
        synchronized (this) {
            this.i |= 4;
        }
        notifyPropertyChanged(95);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding
    public void setShowCharging(@Nullable Boolean bool) {
        this.mShowCharging = bool;
        synchronized (this) {
            this.i |= 8;
        }
        notifyPropertyChanged(96);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding
    public void setShowClBottomButton(@Nullable Boolean bool) {
        this.mShowClBottomButton = bool;
        synchronized (this) {
            this.i |= 64;
        }
        notifyPropertyChanged(97);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding
    public void setShowTimer(@Nullable Boolean bool) {
        this.mShowTimer = bool;
        synchronized (this) {
            this.i |= 32;
        }
        notifyPropertyChanged(109);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBinding
    public void setTimerData(@Nullable String str) {
        this.mTimerData = str;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(121);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (43 == i) {
            setHeaderData((String) obj);
        } else if (121 == i) {
            setTimerData((String) obj);
        } else if (95 == i) {
            setShowButtonRepeat((Boolean) obj);
        } else if (96 == i) {
            setShowCharging((Boolean) obj);
        } else if (94 == i) {
            setShowButtonProceed((Boolean) obj);
        } else if (109 == i) {
            setShowTimer((Boolean) obj);
        } else if (97 != i) {
            return false;
        } else {
            setShowClBottomButton((Boolean) obj);
        }
        return true;
    }

    public FragmentRunDiagnosticTestingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[10], (Button) objArr[4], (Button) objArr[6], (Button) objArr[11], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[7], (ImageView) objArr[2], (RecyclerView) objArr[8], (TextView) objArr[9], (TextView) objArr[1], (TextView) objArr[3]);
        this.i = -1L;
        this.btnProceed.setTag(null);
        this.btnRepeat.setTag(null);
        this.clBottomButton.setTag(null);
        this.ivCharging.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvTestHeader.setTag(null);
        this.tvTimer.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
