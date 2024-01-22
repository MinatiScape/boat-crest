package defpackage;

import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraXConfig;
import com.mappls.sdk.navigation.NavigationApplication;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* renamed from: CameraXBasic  reason: default package */
/* loaded from: classes2.dex */
public class CameraXBasic extends NavigationApplication implements CameraXConfig.Provider {
    @Override // androidx.camera.core.CameraXConfig.Provider
    @NotNull
    public CameraXConfig getCameraXConfig() {
        CameraXConfig defaultConfig = Camera2Config.defaultConfig();
        Intrinsics.checkNotNullExpressionValue(defaultConfig, "defaultConfig()");
        return defaultConfig;
    }
}
