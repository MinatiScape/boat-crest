package com.coveiot.android.fitnesschallenges;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessChallengeCardShareBinding;
import com.coveiot.android.fitnesschallenges.fragments.FragmentShareChallenge;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityFitnessChallengeCardShare extends BaseActivity {
    public Bitmap bitmap;
    public String mCurrentPhotoPath;
    public ActivityFitnessChallengeCardShareBinding p;
    @Nullable
    public Uri r;
    @Nullable
    public Uri s;
    @Nullable
    public String[] t;
    @Nullable
    public BottomSheetDialogTwoButtons u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ChallengeShareData q = new ChallengeShareData();

    public static final void A(ActivityFitnessChallengeCardShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        if (bottomSheetDialogTwoButtons != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons == null || !bottomSheetDialogTwoButtons.isShowing()) {
                z = false;
            }
            if (z) {
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this$0.u;
                if (bottomSheetDialogTwoButtons2 != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this$0.v();
            }
        }
    }

    public static final void x(ActivityFitnessChallengeCardShare this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void z(ActivityFitnessChallengeCardShare this$0, View view) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t();
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this$0.u;
        if (bottomSheetDialogTwoButtons2 != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons2 == null || !bottomSheetDialogTwoButtons2.isShowing()) {
                z = false;
            }
            if (!z || (bottomSheetDialogTwoButtons = this$0.u) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public final void B(Uri uri) {
        CropImage.activity(uri).setAspectRatio(1, 1).setOutputUri(Uri.fromFile(u())).start(this);
    }

    public final void C() {
        File file = null;
        this.r = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                file = u();
            } catch (IOException unused) {
            }
            if (file != null) {
                Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
                this.r = uriForFile;
                intent.putExtra("output", uriForFile);
                startActivityForResult(intent, 1);
            }
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

    @NotNull
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @Nullable
    public final Uri getCropedPhotoUri() {
        return this.s;
    }

    @NotNull
    public final String getMCurrentPhotoPath() {
        String str = this.mCurrentPhotoPath;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCurrentPhotoPath");
        return null;
    }

    @Nullable
    public final String[] getPermission_array() {
        return this.t;
    }

    @Nullable
    public final Uri getPhotoURI() {
        return this.r;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        Uri uri;
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            B(this.r);
        } else if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 == -1 && (uri = activityResult.getUri()) != null) {
                this.s = uri;
                System.out.println((Object) ("Cropped Image URI: " + uri));
                v();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityFitnessChallengeCardShareBinding inflate = ActivityFitnessChallengeCardShareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            FitnessChallengeConstants.Companion companion = FitnessChallengeConstants.Companion;
            if (intent.getSerializableExtra(companion.getSHARE_CHALLENGE_DATA()) != null && (getIntent().getSerializableExtra(companion.getSHARE_CHALLENGE_DATA()) instanceof ChallengeShareData)) {
                Serializable serializableExtra = getIntent().getSerializableExtra(companion.getSHARE_CHALLENGE_DATA());
                Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.ChallengeShareData");
                this.q = (ChallengeShareData) serializableExtra;
            }
        }
        if (this.q == null) {
            finish();
        } else {
            y();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (!(grantResults.length == 0)) {
            String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"});
            Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…      )\n                )");
            if (!(checkPermissionsHasGranted.length == 0)) {
                return;
            }
            C();
            return;
        }
        super.onRequestPermissionsResult(i, permissions, grantResults);
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setCropedPhotoUri(@Nullable Uri uri) {
        this.s = uri;
    }

    public final void setMCurrentPhotoPath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCurrentPhotoPath = str;
    }

    public final void setPermission_array(@Nullable String[] strArr) {
        this.t = strArr;
    }

    public final void setPhotoURI(@Nullable Uri uri) {
        this.r = uri;
    }

    public final void t() {
        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"});
        this.t = checkPermissionsHasGranted;
        Intrinsics.checkNotNull(checkPermissionsHasGranted);
        if (!(checkPermissionsHasGranted.length == 0)) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                String[] strArr = this.t;
                Intrinsics.checkNotNull(strArr);
                ActivityCompat.requestPermissions(this, strArr, 0);
                return;
            }
            String string = getString(R.string.camera_permission_text);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.camera_permission_text)");
            w(string);
            return;
        }
        C();
    }

    public final File u() throws IOException {
        String formatDate = AppUtils.formatDate(new Date(), "yyyyMMdd_HHmmss");
        File image = File.createTempFile("JPEG_" + formatDate + '_', ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        String absolutePath = image.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "image.absolutePath");
        setMCurrentPhotoPath(absolutePath);
        Intrinsics.checkNotNullExpressionValue(image, "image");
        return image;
    }

    public final void v() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentShareChallenge.Companion.newInstance(this.q, this.s)).commitAllowingStateLoss();
    }

    public final void w(String str) {
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFitnessChallengeCardShare.x(ActivityFitnessChallengeCardShare.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void y() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.u;
        if (bottomSheetDialogTwoButtons2 != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons2 == null || !bottomSheetDialogTwoButtons2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetDialogTwoButtons = this.u) != null) {
                bottomSheetDialogTwoButtons.dismiss();
            }
            this.u = null;
        }
        String string = getString(R.string.take_picture);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.take_picture)");
        String string2 = getString(R.string.capture_a_picture_before_sharing);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.captu…a_picture_before_sharing)");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        this.u = bottomSheetDialogTwoButtons3;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogTwoButtons3.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFitnessChallengeCardShare.z(ActivityFitnessChallengeCardShare.this, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        String string4 = getString(R.string.f4491no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogTwoButtons4.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFitnessChallengeCardShare.A(ActivityFitnessChallengeCardShare.this, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.u;
        if (bottomSheetDialogTwoButtons5 != null) {
            bottomSheetDialogTwoButtons5.show();
        }
    }
}
