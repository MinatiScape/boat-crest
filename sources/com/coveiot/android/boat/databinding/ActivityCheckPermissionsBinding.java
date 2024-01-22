package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityCheckPermissionsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnGrantpermissions;
    @NonNull
    public final ImageView imageViewHasAllPermissions;
    @NonNull
    public final RecyclerView recyclerViewPermissionsRequired;
    @NonNull
    public final TextView textviewPermissionCheckScreenTitle;

    public ActivityCheckPermissionsBinding(Object obj, View view, int i, Button button, ImageView imageView, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.btnGrantpermissions = button;
        this.imageViewHasAllPermissions = imageView;
        this.recyclerViewPermissionsRequired = recyclerView;
        this.textviewPermissionCheckScreenTitle = textView;
    }

    public static ActivityCheckPermissionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityCheckPermissionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCheckPermissionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityCheckPermissionsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_check_permissions);
    }

    @NonNull
    @Deprecated
    public static ActivityCheckPermissionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityCheckPermissionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_check_permissions, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityCheckPermissionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityCheckPermissionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityCheckPermissionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_check_permissions, null, false, obj);
    }
}
