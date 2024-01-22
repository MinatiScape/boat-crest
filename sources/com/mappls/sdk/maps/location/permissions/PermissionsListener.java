package com.mappls.sdk.maps.location.permissions;

import java.util.List;
/* loaded from: classes11.dex */
public interface PermissionsListener {
    void onExplanationNeeded(List<String> list);

    void onPermissionResult(boolean z);
}
