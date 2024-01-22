package com.coveiot.android.watchfaceui.databinding;

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
import com.coveiot.android.watchfaceui.BR;
import com.coveiot.android.watchfaceui.model.Categories;
/* loaded from: classes8.dex */
public class ListWfCategoryItemBindingImpl extends ListWfCategoryItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k = null;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    public ListWfCategoryItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        boolean z;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Categories categories = this.mCloudCategoryData;
        String str2 = null;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        if (i != 0) {
            if (categories != null) {
                z = categories.getNewCategory();
                str = categories.getTitle();
            } else {
                str = null;
                z = false;
            }
            if (i != 0) {
                j2 |= z ? 8L : 4L;
            }
            r9 = z ? 0 : 8;
            str2 = str;
        }
        if ((j2 & 3) != 0) {
            this.ivCategory.setVisibility(r9);
            TextViewBindingAdapter.setText(this.tvCategory, str2);
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

    @Override // com.coveiot.android.watchfaceui.databinding.ListWfCategoryItemBinding
    public void setCloudCategoryData(@Nullable Categories categories) {
        this.mCloudCategoryData = categories;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.cloudCategoryData);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.cloudCategoryData == i) {
            setCloudCategoryData((Categories) obj);
            return true;
        }
        return false;
    }

    public ListWfCategoryItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
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
