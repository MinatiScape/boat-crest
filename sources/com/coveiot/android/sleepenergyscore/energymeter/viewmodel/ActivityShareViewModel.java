package com.coveiot.android.sleepenergyscore.energymeter.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.theme.permissions.PermissionListener;
import com.coveiot.android.theme.permissions.PermissionType;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class ActivityShareViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5727a;
    public PermissionListener mListener;

    public ActivityShareViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5727a = context;
    }

    public final void checkStoragePermission() {
        getMListener().onPermissionSuccess(PermissionType.STORAGE);
    }

    @NotNull
    public final Context getContext() {
        return this.f5727a;
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
