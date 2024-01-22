package com.coveiot.android.dashboard2.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.dashboard2.BR;
import com.coveiot.android.dashboard2.R;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
/* loaded from: classes4.dex */
public class ItemTopFeaturesBindingImpl extends ItemTopFeaturesBinding {
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
        sparseIntArray.put(R.id.tvTopFeaturesTitle, 3);
    }

    public ItemTopFeaturesBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        String str2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        GetSmartGridRes.GridItem gridItem = this.mFeatureData;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        String str3 = null;
        if (i != 0) {
            if (gridItem != null) {
                String iconUrl = gridItem.getIconUrl();
                str3 = gridItem.getTag();
                str2 = iconUrl;
            } else {
                str2 = null;
            }
            boolean z = str3 != null;
            if (i != 0) {
                j2 |= z ? 8L : 4L;
            }
            r9 = z ? 0 : 8;
            String str4 = str3;
            str3 = str2;
            str = str4;
        } else {
            str = null;
        }
        if ((j2 & 3) != 0) {
            ViewUtilsKt.setImage(this.ivTopFeatureIcon, str3);
            TextViewBindingAdapter.setText(this.tvTag, str);
            this.tvTag.setVisibility(r9);
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

    @Override // com.coveiot.android.dashboard2.databinding.ItemTopFeaturesBinding
    public void setFeatureData(@Nullable GetSmartGridRes.GridItem gridItem) {
        this.mFeatureData = gridItem;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.featureData);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.featureData == i) {
            setFeatureData((GetSmartGridRes.GridItem) obj);
            return true;
        }
        return false;
    }

    public ItemTopFeaturesBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (TextView) objArr[2], (TextView) objArr[3]);
        this.i = -1L;
        this.ivTopFeatureIcon.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvTag.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
