package com.coveiot.android.ocr.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.coveiot.utils.permissions.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class OcrUtilsKt {
    public static final boolean checkIfCameraPermissionExists(@Nullable Context context) {
        final boolean[] zArr = {false};
        PermissionUtils.checkPermission(context, "android.permission.CAMERA", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.ocr.utils.OcrUtilsKt$checkIfCameraPermissionExists$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                zArr[0] = false;
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                zArr[0] = false;
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                zArr[0] = true;
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                zArr[0] = false;
            }
        });
        return zArr[0];
    }

    public static final void openAppSettings(@NotNull Fragment fragment, int i) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        Context context = fragment.getContext();
        Intrinsics.checkNotNull(context);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        if (i > 0) {
            fragment.startActivityForResult(intent, i);
        } else {
            fragment.startActivity(intent);
        }
    }
}
