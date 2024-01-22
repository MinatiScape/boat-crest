package com.coveiot.android.theme.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.model.SmallHealthCardInfo;
/* loaded from: classes7.dex */
public class SmallHealthCardInfoWithProgressBindingImpl extends SmallHealthCardInfoWithProgressBinding {
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
        sparseIntArray.put(R.id.llData, 8);
    }

    public SmallHealthCardInfoWithProgressBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        SmallHealthCardInfo smallHealthCardInfo = this.mHealthInfo;
        Integer num = this.mProgress;
        int i = ((5 & j2) > 0L ? 1 : ((5 & j2) == 0L ? 0 : -1));
        Drawable drawable = null;
        if (i == 0 || smallHealthCardInfo == null) {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        } else {
            String value5 = smallHealthCardInfo.getValue5();
            String value4 = smallHealthCardInfo.getValue4();
            str3 = smallHealthCardInfo.getValue3();
            Drawable drawable2 = smallHealthCardInfo.getDrawable();
            str5 = smallHealthCardInfo.getValue2();
            str2 = smallHealthCardInfo.getValue1();
            str = value5;
            drawable = drawable2;
            str4 = value4;
        }
        int i2 = ((j2 & 6) > 0L ? 1 : ((j2 & 6) == 0L ? 0 : -1));
        int safeUnbox = i2 != 0 ? ViewDataBinding.safeUnbox(num) : 0;
        if (i != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ivProfile, drawable);
            TextViewBindingAdapter.setText(this.tvBottom, str);
            TextViewBindingAdapter.setText(this.tvValue1, str2);
            TextViewBindingAdapter.setText(this.tvValue2, str5);
            TextViewBindingAdapter.setText(this.tvValue3, str3);
            TextViewBindingAdapter.setText(this.tvValue4, str4);
        }
        if (i2 != 0) {
            this.progressBar1.setProgress(safeUnbox);
        }
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
            this.i = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.theme.databinding.SmallHealthCardInfoWithProgressBinding
    public void setHealthInfo(@Nullable SmallHealthCardInfo smallHealthCardInfo) {
        this.mHealthInfo = smallHealthCardInfo;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.healthInfo);
        super.requestRebind();
    }

    @Override // com.coveiot.android.theme.databinding.SmallHealthCardInfoWithProgressBinding
    public void setProgress(@Nullable Integer num) {
        this.mProgress = num;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.healthInfo == i) {
            setHealthInfo((SmallHealthCardInfo) obj);
        } else if (BR.progress != i) {
            return false;
        } else {
            setProgress((Integer) obj);
        }
        return true;
    }

    public SmallHealthCardInfoWithProgressBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[2], (LinearLayout) objArr[8], (ProgressBar) objArr[1], (TextView) objArr[7], (TextView) objArr[3], (TextView) objArr[4], (TextView) objArr[5], (TextView) objArr[6]);
        this.i = -1L;
        this.ivProfile.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.progressBar1.setTag(null);
        this.tvBottom.setTag(null);
        this.tvValue1.setTag(null);
        this.tvValue2.setTag(null);
        this.tvValue3.setTag(null);
        this.tvValue4.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
