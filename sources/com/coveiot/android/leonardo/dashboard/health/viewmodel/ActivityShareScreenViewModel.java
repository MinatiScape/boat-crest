package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ActivityShareScreenViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4753a;
    public PermissionListener mListener;

    public ActivityShareScreenViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4753a = context;
    }

    public final void checkStoragePermission() {
        getMListener().onPermissionSuccess(PermissionType.STORAGE);
    }

    @NotNull
    public final Context getContext() {
        return this.f4753a;
    }

    @NotNull
    public final PermissionListener getMListener() {
        PermissionListener permissionListener = this.mListener;
        if (permissionListener != null) {
            return permissionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    public final void setMListener(@NotNull PermissionListener permissionListener) {
        Intrinsics.checkNotNullParameter(permissionListener, "<set-?>");
        this.mListener = permissionListener;
    }
}
