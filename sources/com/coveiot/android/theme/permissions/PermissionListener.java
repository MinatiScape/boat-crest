package com.coveiot.android.theme.permissions;

import com.coveiot.utils.permissions.PermissionUtils;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public interface PermissionListener {
    void askPermission(@NotNull PermissionType permissionType);

    void checkPermssion(@NotNull String str, @NotNull PermissionUtils.PermissionAskListener permissionAskListener);

    void onPermissionDenied(@NotNull PermissionType permissionType);

    void onPermissionDisabled(@NotNull PermissionType permissionType);

    void onPermissionSuccess(@NotNull PermissionType permissionType);
}
