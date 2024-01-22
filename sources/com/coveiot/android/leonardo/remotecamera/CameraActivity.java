package com.coveiot.android.leonardo.remotecamera;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.ExitRemoteCameraRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityRemoteCameraBinding;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.common.util.concurrent.ListenableFuture;
import com.squareup.otto.Subscribe;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class CameraActivity extends BaseActivity {
    public ActivityRemoteCameraBinding q;
    @Nullable
    public ImageCapture t;
    public long u;
    @NotNull
    public CameraSelector x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = CameraActivity.class.getSimpleName();
    public final int r = 123;
    public final int s = 124;
    public final long v = 1000;
    public boolean w = true;

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CameraState.values().length];
            try {
                iArr[CameraState.CAPTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CameraState.EXIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CameraActivity() {
        CameraSelector DEFAULT_BACK_CAMERA = CameraSelector.DEFAULT_BACK_CAMERA;
        Intrinsics.checkNotNullExpressionValue(DEFAULT_BACK_CAMERA, "DEFAULT_BACK_CAMERA");
        this.x = DEFAULT_BACK_CAMERA;
    }

    public static final void A(CameraActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.G();
    }

    public static final void D(CameraActivity this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void F(ListenableFuture cameraProviderFuture, CameraActivity this$0) {
        Intrinsics.checkNotNullParameter(cameraProviderFuture, "$cameraProviderFuture");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        V v = cameraProviderFuture.get();
        Intrinsics.checkNotNullExpressionValue(v, "cameraProviderFuture.get()");
        ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) v;
        Preview build = new Preview.Builder().build();
        ActivityRemoteCameraBinding activityRemoteCameraBinding = this$0.q;
        if (activityRemoteCameraBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRemoteCameraBinding = null;
        }
        build.setSurfaceProvider(activityRemoteCameraBinding.previewView.getSurfaceProvider());
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …ovider)\n                }");
        ImageCapture build2 = new ImageCapture.Builder().build();
        this$0.t = build2;
        Intrinsics.checkNotNull(build2);
        build2.setFlashMode(1);
        try {
            processCameraProvider.unbindAll();
            processCameraProvider.bindToLifecycle(this$0, this$0.x, build, this$0.t);
        } catch (Exception e) {
            Log.e(this$0.p, "Use case binding failed", e);
        }
    }

    public static final void x(CameraActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.takePhoto();
    }

    public static final void y(CameraActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.w();
    }

    public static final void z(CameraActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B();
    }

    public final void B() {
        Intent makeMainSelectorActivity = Intent.makeMainSelectorActivity("android.intent.action.MAIN", "android.intent.category.APP_GALLERY");
        makeMainSelectorActivity.setFlags(268435456);
        if (makeMainSelectorActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(makeMainSelectorActivity);
        } else {
            Toast.makeText(this, (int) R.string.no_app_found_to_open_gallery, 1).show();
        }
    }

    public final void C(String str) {
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.remotecamera.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraActivity.D(CameraActivity.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void E() {
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(this)");
        processCameraProvider.addListener(new Runnable() { // from class: com.coveiot.android.leonardo.remotecamera.f
            @Override // java.lang.Runnable
            public final void run() {
                CameraActivity.F(ListenableFuture.this, this);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    public final void G() {
        try {
            boolean z = !this.w;
            this.w = z;
            if (z) {
                ImageCapture imageCapture = this.t;
                if (imageCapture != null) {
                    imageCapture.setFlashMode(1);
                }
                ((ImageButton) _$_findCachedViewById(R.id.flash)).setImageResource(R.drawable.ic_flash);
                return;
            }
            ImageCapture imageCapture2 = this.t;
            if (imageCapture2 != null) {
                imageCapture2.setFlashMode(2);
            }
            ((ImageButton) _$_findCachedViewById(R.id.flash)).setImageResource(R.drawable.ic_flash_off);
        } catch (Exception e) {
            String str = this.p;
            LogHelper.e(str, "turnOnOffFlashError : " + e.getMessage());
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final long getDefaultInterval() {
        return this.v;
    }

    @Nullable
    public final ImageCapture getImageCapture() {
        return this.t;
    }

    public final long getLastTimeClicked() {
        return this.u;
    }

    public final String getTAG() {
        return this.p;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT == 29) {
            finishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"ServiceCast"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(16777216, 16777216);
        ActivityRemoteCameraBinding inflate = ActivityRemoteCameraBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.q = inflate;
        ActivityRemoteCameraBinding activityRemoteCameraBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.root);
        UserDataManager.getInstance(this).setIsCameraLaunched(Boolean.TRUE);
        ActivityRemoteCameraBinding activityRemoteCameraBinding2 = this.q;
        if (activityRemoteCameraBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRemoteCameraBinding = activityRemoteCameraBinding2;
        }
        activityRemoteCameraBinding.setIsFlashSupported(Boolean.valueOf(getPackageManager().hasSystemFeature("android.hardware.camera.flash")));
        activityRemoteCameraBinding.capturePhoto.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.remotecamera.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraActivity.x(CameraActivity.this, view);
            }
        });
        activityRemoteCameraBinding.toggleCamera.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.remotecamera.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraActivity.y(CameraActivity.this, view);
            }
        });
        activityRemoteCameraBinding.openGallery.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.remotecamera.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraActivity.z(CameraActivity.this, view);
            }
        });
        activityRemoteCameraBinding.flash.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.remotecamera.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraActivity.A(CameraActivity.this, view);
            }
        });
        if (Build.VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != 0) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                    requestPermissions(new String[]{"android.permission.CAMERA"}, this.s);
                } else {
                    String string = getString(R.string.camera_permission);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.camera_permission)");
                    C(string);
                }
            }
        } else if (PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}).length > 0) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA") && !ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}, this.r);
            } else {
                String string2 = getString(R.string.storage_camera_permission_required);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.stora…mera_permission_required)");
                C(string2);
            }
        }
        E();
    }

    @Subscribe
    public final void onRemoteCameraEvent(@NotNull CameraEventRes msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        String str = this.p;
        Log.d(str, "message " + msg);
        CameraState state = msg.getState();
        int i = state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            UserDataManager.getInstance(this).setIsCameraLaunched(Boolean.FALSE);
            finish();
        } else if (System.currentTimeMillis() - this.u < this.v) {
        } else {
            takePhoto();
            this.u = System.currentTimeMillis();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (i == this.r) {
            if (PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}).length > 0) {
                Toast.makeText(this, getString(R.string.storage_permission_required_remote_camera), 0).show();
            }
        } else if (i != this.s) {
            super.onRequestPermissionsResult(i, permissions, grantResults);
        } else {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                E();
            } else {
                Toast.makeText(this, getString(R.string.camera_permission_text), 0).show();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CloveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        CloveEventBusManager.getInstance().getEventBus().unregister(this);
        UserDataManager.getInstance(this).setIsCameraLaunched(Boolean.FALSE);
        sendToBle();
        if (isFinishing()) {
            return;
        }
        finish();
    }

    public final void sendToBle() {
        BleApiManager.getInstance(getApplicationContext()).getBleApi().setUserSettings(new ExitRemoteCameraRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.remotecamera.CameraActivity$sendToBle$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void setImageCapture(@Nullable ImageCapture imageCapture) {
        this.t = imageCapture;
    }

    public final void setLastTimeClicked(long j) {
        this.u = j;
    }

    public final void setTAG(String str) {
        this.p = str;
    }

    public final void takePhoto() {
        ImageCapture imageCapture = this.t;
        if (imageCapture == null) {
            return;
        }
        String format = new SimpleDateFormat("dd/mm/yyyy", Locale.US).format(Long.valueOf(System.currentTimeMillis()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", format);
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("relative_path", "Pictures");
        ImageCapture.OutputFileOptions build = new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …   )\n            .build()");
        imageCapture.a0(build, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() { // from class: com.coveiot.android.leonardo.remotecamera.CameraActivity$takePhoto$1
            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onError(@NotNull ImageCaptureException exc) {
                Intrinsics.checkNotNullParameter(exc, "exc");
                String tag = CameraActivity.this.getTAG();
                Log.e(tag, "Photo capture failed: " + exc.getMessage(), exc);
            }

            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onImageSaved(@NotNull ImageCapture.OutputFileResults output) {
                Intrinsics.checkNotNullParameter(output, "output");
                Toast.makeText(CameraActivity.this.getBaseContext(), "Image saved successfully", 0).show();
                Log.d(CameraActivity.this.getTAG(), "Photo capture succeeded: " + output.getSavedUri());
            }
        });
    }

    public final void w() {
        CameraSelector cameraSelector = this.x;
        CameraSelector DEFAULT_BACK_CAMERA = CameraSelector.DEFAULT_BACK_CAMERA;
        if (Intrinsics.areEqual(cameraSelector, DEFAULT_BACK_CAMERA)) {
            CameraSelector DEFAULT_FRONT_CAMERA = CameraSelector.DEFAULT_FRONT_CAMERA;
            Intrinsics.checkNotNullExpressionValue(DEFAULT_FRONT_CAMERA, "DEFAULT_FRONT_CAMERA");
            this.x = DEFAULT_FRONT_CAMERA;
        } else if (Intrinsics.areEqual(this.x, CameraSelector.DEFAULT_FRONT_CAMERA)) {
            Intrinsics.checkNotNullExpressionValue(DEFAULT_BACK_CAMERA, "DEFAULT_BACK_CAMERA");
            this.x = DEFAULT_BACK_CAMERA;
        }
        E();
    }
}
