package com.coveiot.android.qrtray.databinding;

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
import com.coveiot.android.qrtray.BR;
import com.coveiot.android.qrtray.utils.ViewUtilsKt;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
/* loaded from: classes5.dex */
public class ListItemQrTrayCategoryLayoutBindingImpl extends ListItemQrTrayCategoryLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k = null;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    public ListItemQrTrayCategoryLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        QRTrayCategoriesRes.QRItem qRItem = this.mQrCategoryData;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        String str2 = null;
        if (i == 0 || qRItem == null) {
            str = null;
        } else {
            str = qRItem.getTitle();
            str2 = qRItem.getIconUrl();
        }
        if (i != 0) {
            ViewUtilsKt.setImage(this.ivCategory, str2);
            TextViewBindingAdapter.setText(this.tvCategory, str);
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

    @Override // com.coveiot.android.qrtray.databinding.ListItemQrTrayCategoryLayoutBinding
    public void setQrCategoryData(@Nullable QRTrayCategoriesRes.QRItem qRItem) {
        this.mQrCategoryData = qRItem;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.qrCategoryData);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.qrCategoryData == i) {
            setQrCategoryData((QRTrayCategoriesRes.QRItem) obj);
            return true;
        }
        return false;
    }

    public ListItemQrTrayCategoryLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (TextView) objArr[2]);
        this.i = -1L;
        this.ivCategory.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvCategory.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
