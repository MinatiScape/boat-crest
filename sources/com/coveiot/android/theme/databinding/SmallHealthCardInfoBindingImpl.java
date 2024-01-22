package com.coveiot.android.theme.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class SmallHealthCardInfoBindingImpl extends SmallHealthCardInfoBinding {
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
        sparseIntArray.put(R.id.llData, 5);
    }

    public SmallHealthCardInfoBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 6, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        String str2;
        String str3;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        SmallHealthCardInfo smallHealthCardInfo = this.mHealthInfo;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        Drawable drawable = null;
        if (i == 0 || smallHealthCardInfo == null) {
            str = null;
            str2 = null;
            str3 = null;
        } else {
            String value3 = smallHealthCardInfo.getValue3();
            Drawable drawable2 = smallHealthCardInfo.getDrawable();
            str2 = smallHealthCardInfo.getValue2();
            str3 = smallHealthCardInfo.getValue1();
            str = value3;
            drawable = drawable2;
        }
        if (i != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ivIcon, drawable);
            TextViewBindingAdapter.setText(this.tvBottom, str);
            TextViewBindingAdapter.setText(this.tvValue1, str3);
            TextViewBindingAdapter.setText(this.tvValue2, str2);
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
            this.i = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.theme.databinding.SmallHealthCardInfoBinding
    public void setHealthInfo(@Nullable SmallHealthCardInfo smallHealthCardInfo) {
        this.mHealthInfo = smallHealthCardInfo;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.healthInfo);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.healthInfo == i) {
            setHealthInfo((SmallHealthCardInfo) obj);
            return true;
        }
        return false;
    }

    public SmallHealthCardInfoBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (LinearLayout) objArr[5], (TextView) objArr[4], (TextView) objArr[2], (TextView) objArr[3]);
        this.i = -1L;
        this.ivIcon.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvBottom.setTag(null);
        this.tvValue1.setTag(null);
        this.tvValue2.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
