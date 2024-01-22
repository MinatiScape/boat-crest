package androidx.camera.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.camera.core.Camera;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.lifecycle.LifecycleOwner;
/* loaded from: classes.dex */
public final class LifecycleCameraController extends CameraController {
    @Nullable
    public LifecycleOwner E;

    public LifecycleCameraController(@NonNull Context context) {
        super(context);
    }

    @SuppressLint({"MissingPermission"})
    @MainThread
    public void bindToLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        Threads.checkMainThread();
        this.E = lifecycleOwner;
        w();
    }

    @MainThread
    public void unbind() {
        Threads.checkMainThread();
        this.E = null;
        this.p = null;
        ProcessCameraProvider processCameraProvider = this.q;
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
        }
    }

    @Override // androidx.camera.view.CameraController
    @Nullable
    @RequiresPermission("android.permission.CAMERA")
    @SuppressLint({"UnsafeOptInUsageError"})
    public Camera v() {
        if (this.E == null) {
            Log.d("CamLifecycleController", "Lifecycle is not set.");
            return null;
        } else if (this.q == null) {
            Log.d("CamLifecycleController", "CameraProvider is not ready.");
            return null;
        } else {
            UseCaseGroup createUseCaseGroup = createUseCaseGroup();
            if (createUseCaseGroup == null) {
                return null;
            }
            return this.q.bindToLifecycle(this.E, this.f798a, createUseCaseGroup);
        }
    }
}
