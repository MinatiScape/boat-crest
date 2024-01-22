package com.google.android.gms.internal.vision;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.L;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
/* loaded from: classes10.dex */
public final class zzr {
    public static boolean zza(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str) > DynamiteModule.getRemoteVersion(context, OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID);
    }

    @Nullable
    public static DynamiteModule zza(Context context, String str, boolean z) {
        DynamiteModule.VersionPolicy versionPolicy;
        String format = String.format("%s.%s", "com.google.android.gms.vision", str);
        if (!z) {
            format = OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID;
        }
        try {
            L.d("Loading module %s", format);
            if (z) {
                versionPolicy = DynamiteModule.PREFER_REMOTE;
            } else {
                versionPolicy = DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;
            }
            return DynamiteModule.load(context, versionPolicy, format);
        } catch (DynamiteModule.LoadingException e) {
            L.e(e, "Error loading module %s optional module %b", format, Boolean.valueOf(z));
            return null;
        }
    }
}
