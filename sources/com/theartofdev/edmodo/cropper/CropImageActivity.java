package com.theartofdev.edmodo.cropper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.IOException;
/* loaded from: classes12.dex */
public class CropImageActivity extends AppCompatActivity implements CropImageView.OnSetImageUriCompleteListener, CropImageView.OnCropImageCompleteListener {
    public CropImageView h;
    public Uri i;
    public CropImageOptions j;

    public final void c(Menu menu, int i, int i2) {
        Drawable icon;
        MenuItem findItem = menu.findItem(i);
        if (findItem == null || (icon = findItem.getIcon()) == null) {
            return;
        }
        try {
            icon.mutate();
            icon.setColorFilter(i2, PorterDuff.Mode.SRC_ATOP);
            findItem.setIcon(icon);
        } catch (Exception e) {
            Log.w("AIC", "Failed to update menu item color", e);
        }
    }

    public void cropImage() {
        if (this.j.noOutputImage) {
            setResult(null, null, 1);
            return;
        }
        Uri outputUri = getOutputUri();
        CropImageView cropImageView = this.h;
        CropImageOptions cropImageOptions = this.j;
        cropImageView.saveCroppedImageAsync(outputUri, cropImageOptions.outputCompressFormat, cropImageOptions.outputCompressQuality, cropImageOptions.outputRequestWidth, cropImageOptions.outputRequestHeight, cropImageOptions.outputRequestSizeOptions);
    }

    public Uri getOutputUri() {
        Uri uri = this.j.outputUri;
        if (uri == null || uri.equals(Uri.EMPTY)) {
            try {
                Bitmap.CompressFormat compressFormat = this.j.outputCompressFormat;
                return Uri.fromFile(File.createTempFile("cropped", compressFormat == Bitmap.CompressFormat.JPEG ? ".jpg" : compressFormat == Bitmap.CompressFormat.PNG ? ".png" : ".webp", getCacheDir()));
            } catch (IOException e) {
                throw new RuntimeException("Failed to create temp file for output image", e);
            }
        }
        return uri;
    }

    public Intent getResultIntent(Uri uri, Exception exc, int i) {
        CropImage.ActivityResult activityResult = new CropImage.ActivityResult(this.h.getImageUri(), uri, exc, this.h.getCropPoints(), this.h.getCropRect(), this.h.getRotatedDegrees(), this.h.getWholeImageRect(), i);
        Intent intent = new Intent();
        intent.putExtras(getIntent());
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_RESULT, activityResult);
        return intent;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 200) {
            if (i2 == 0) {
                setResultCancel();
            }
            if (i2 == -1) {
                Uri pickImageResultUri = CropImage.getPickImageResultUri(this, intent);
                this.i = pickImageResultUri;
                if (CropImage.isReadExternalStoragePermissionsRequired(this, pickImageResultUri)) {
                    requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 201);
                } else {
                    this.h.setImageUriAsync(this.i);
                }
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        setResultCancel();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onCreate(Bundle bundle) {
        CharSequence charSequence;
        super.onCreate(bundle);
        setContentView(R.layout.crop_image_activity);
        this.h = (CropImageView) findViewById(R.id.cropImageView);
        Bundle bundleExtra = getIntent().getBundleExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE);
        this.i = (Uri) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_SOURCE);
        this.j = (CropImageOptions) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
        if (bundle == null) {
            Uri uri = this.i;
            if (uri != null && !uri.equals(Uri.EMPTY)) {
                if (CropImage.isReadExternalStoragePermissionsRequired(this, this.i)) {
                    requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 201);
                } else {
                    this.h.setImageUriAsync(this.i);
                }
            } else if (CropImage.isExplicitCameraPermissionRequired(this)) {
                requestPermissions(new String[]{"android.permission.CAMERA"}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
            } else {
                CropImage.startPickImageActivity(this);
            }
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            CropImageOptions cropImageOptions = this.j;
            supportActionBar.setTitle((cropImageOptions == null || (charSequence = cropImageOptions.activityTitle) == null || charSequence.length() <= 0) ? getResources().getString(R.string.crop_image_activity_title) : this.j.activityTitle);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crop_image_menu, menu);
        CropImageOptions cropImageOptions = this.j;
        if (!cropImageOptions.allowRotation) {
            menu.removeItem(R.id.crop_image_menu_rotate_left);
            menu.removeItem(R.id.crop_image_menu_rotate_right);
        } else if (cropImageOptions.allowCounterRotation) {
            menu.findItem(R.id.crop_image_menu_rotate_left).setVisible(true);
        }
        if (!this.j.allowFlipping) {
            menu.removeItem(R.id.crop_image_menu_flip);
        }
        if (this.j.cropMenuCropButtonTitle != null) {
            menu.findItem(R.id.crop_image_menu_crop).setTitle(this.j.cropMenuCropButtonTitle);
        }
        Drawable drawable = null;
        try {
            int i = this.j.cropMenuCropButtonIcon;
            if (i != 0) {
                drawable = ContextCompat.getDrawable(this, i);
                menu.findItem(R.id.crop_image_menu_crop).setIcon(drawable);
            }
        } catch (Exception e) {
            Log.w("AIC", "Failed to read menu crop drawable", e);
        }
        int i2 = this.j.activityMenuIconColor;
        if (i2 != 0) {
            c(menu, R.id.crop_image_menu_rotate_left, i2);
            c(menu, R.id.crop_image_menu_rotate_right, this.j.activityMenuIconColor);
            c(menu, R.id.crop_image_menu_flip, this.j.activityMenuIconColor);
            if (drawable != null) {
                c(menu, R.id.crop_image_menu_crop, this.j.activityMenuIconColor);
            }
        }
        return true;
    }

    @Override // com.theartofdev.edmodo.cropper.CropImageView.OnCropImageCompleteListener
    public void onCropImageComplete(CropImageView cropImageView, CropImageView.CropResult cropResult) {
        setResult(cropResult.getUri(), cropResult.getError(), cropResult.getSampleSize());
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.crop_image_menu_crop) {
            cropImage();
            return true;
        } else if (menuItem.getItemId() == R.id.crop_image_menu_rotate_left) {
            rotateImage(-this.j.rotationDegrees);
            return true;
        } else if (menuItem.getItemId() == R.id.crop_image_menu_rotate_right) {
            rotateImage(this.j.rotationDegrees);
            return true;
        } else if (menuItem.getItemId() == R.id.crop_image_menu_flip_horizontally) {
            this.h.flipImageHorizontally();
            return true;
        } else if (menuItem.getItemId() == R.id.crop_image_menu_flip_vertically) {
            this.h.flipImageVertically();
            return true;
        } else if (menuItem.getItemId() == 16908332) {
            setResultCancel();
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 201) {
            Uri uri = this.i;
            if (uri != null && iArr.length > 0 && iArr[0] == 0) {
                this.h.setImageUriAsync(uri);
            } else {
                Toast.makeText(this, R.string.crop_image_activity_no_permissions, 1).show();
                setResultCancel();
            }
        }
        if (i == 2011) {
            CropImage.startPickImageActivity(this);
        }
    }

    @Override // com.theartofdev.edmodo.cropper.CropImageView.OnSetImageUriCompleteListener
    public void onSetImageUriComplete(CropImageView cropImageView, Uri uri, Exception exc) {
        if (exc == null) {
            Rect rect = this.j.initialCropWindowRectangle;
            if (rect != null) {
                this.h.setCropRect(rect);
            }
            int i = this.j.initialRotation;
            if (i > -1) {
                this.h.setRotatedDegrees(i);
                return;
            }
            return;
        }
        setResult(null, exc, 1);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.h.setOnSetImageUriCompleteListener(this);
        this.h.setOnCropImageCompleteListener(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.h.setOnSetImageUriCompleteListener(null);
        this.h.setOnCropImageCompleteListener(null);
    }

    public void rotateImage(int i) {
        this.h.rotateImage(i);
    }

    public void setResult(Uri uri, Exception exc, int i) {
        setResult(exc == null ? -1 : 204, getResultIntent(uri, exc, i));
        finish();
    }

    public void setResultCancel() {
        setResult(0);
        finish();
    }
}
