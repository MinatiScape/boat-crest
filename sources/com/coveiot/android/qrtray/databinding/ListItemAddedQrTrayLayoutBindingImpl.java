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
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.model.QRCodeDataApp;
import com.coveiot.android.qrtray.utils.ViewUtilsKt;
/* loaded from: classes5.dex */
public class ListItemAddedQrTrayLayoutBindingImpl extends ListItemAddedQrTrayLayoutBinding {
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
        sparseIntArray.put(R.id.ivMenu, 5);
    }

    public ListItemAddedQrTrayLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 6, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        String str2;
        boolean z;
        String str3;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        QRCodeDataApp qRCodeDataApp = this.mQrCodeData;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        String str4 = null;
        if (i != 0) {
            if (qRCodeDataApp != null) {
                str4 = qRCodeDataApp.getImageTag();
                str3 = qRCodeDataApp.getImageUrl();
                str2 = qRCodeDataApp.getImageTitle();
                z = qRCodeDataApp.getAppliedToWatch();
            } else {
                z = false;
                str3 = null;
                str2 = null;
            }
            if (i != 0) {
                j2 |= z ? 8L : 4L;
            }
            r8 = z ? 0 : 8;
            str = str4;
            str4 = str3;
        } else {
            str = null;
            str2 = null;
        }
        if ((j2 & 3) != 0) {
            ViewUtilsKt.setImage(this.ivQRCode, str4);
            TextViewBindingAdapter.setText(this.tvName, str2);
            this.tvStatus.setVisibility(r8);
            TextViewBindingAdapter.setText(this.tvTag, str);
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

    @Override // com.coveiot.android.qrtray.databinding.ListItemAddedQrTrayLayoutBinding
    public void setQrCodeData(@Nullable QRCodeDataApp qRCodeDataApp) {
        this.mQrCodeData = qRCodeDataApp;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.qrCodeData);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.qrCodeData == i) {
            setQrCodeData((QRCodeDataApp) obj);
            return true;
        }
        return false;
    }

    public ListItemAddedQrTrayLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[5], (ImageView) objArr[1], (TextView) objArr[2], (TextView) objArr[4], (TextView) objArr[3]);
        this.i = -1L;
        this.ivQRCode.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvName.setTag(null);
        this.tvStatus.setTag(null);
        this.tvTag.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
