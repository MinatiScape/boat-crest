package com.coveiot.android.boat.databinding;

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
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.generated.callback.OnClickListener;
import com.coveiot.android.leonardo.model.SelectedAppDataForQrCodePush;
import com.coveiot.android.leonardo.more.fragments.AppClickListener;
/* loaded from: classes3.dex */
public class LayoutItemQrCodeAppListBindingImpl extends LayoutItemQrCodeAppListBinding implements OnClickListener.Listener {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k = null;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @Nullable
    public final View.OnClickListener i;
    public long j;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.imageView22, 3);
    }

    public LayoutItemQrCodeAppListBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, k, l));
    }

    @Override // com.coveiot.android.boat.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        AppClickListener appClickListener = this.mClickListener;
        SelectedAppDataForQrCodePush selectedAppDataForQrCodePush = this.mAppData;
        if (appClickListener != null) {
            appClickListener.onClick(selectedAppDataForQrCodePush);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.j;
            this.j = 0L;
        }
        String str = null;
        SelectedAppDataForQrCodePush selectedAppDataForQrCodePush = this.mAppData;
        int i = 0;
        int i2 = ((6 & j) > 0L ? 1 : ((6 & j) == 0L ? 0 : -1));
        if (i2 != 0 && selectedAppDataForQrCodePush != null) {
            str = selectedAppDataForQrCodePush.getAppName();
            i = selectedAppDataForQrCodePush.getAppIcon();
        }
        if (i2 != 0) {
            this.ivAppIcon.setImageResource(i);
            TextViewBindingAdapter.setText(this.tvAppName, str);
        }
        if ((j & 4) != 0) {
            this.h.setOnClickListener(this.i);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.j != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.LayoutItemQrCodeAppListBinding
    public void setAppData(@Nullable SelectedAppDataForQrCodePush selectedAppDataForQrCodePush) {
        this.mAppData = selectedAppDataForQrCodePush;
        synchronized (this) {
            this.j |= 2;
        }
        notifyPropertyChanged(5);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.LayoutItemQrCodeAppListBinding
    public void setClickListener(@Nullable AppClickListener appClickListener) {
        this.mClickListener = appClickListener;
        synchronized (this) {
            this.j |= 1;
        }
        notifyPropertyChanged(15);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (15 == i) {
            setClickListener((AppClickListener) obj);
        } else if (5 != i) {
            return false;
        } else {
            setAppData((SelectedAppDataForQrCodePush) obj);
        }
        return true;
    }

    public LayoutItemQrCodeAppListBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[3], (ImageView) objArr[1], (TextView) objArr[2]);
        this.j = -1L;
        this.ivAppIcon.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvAppName.setTag(null);
        setRootTag(view);
        this.i = new OnClickListener(this, 1);
        invalidateAll();
    }
}
