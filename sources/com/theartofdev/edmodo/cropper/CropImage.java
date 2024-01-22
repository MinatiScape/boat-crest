package com.theartofdev.edmodo.cropper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public final class CropImage {
    public static final int CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE = 2011;
    public static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 203;
    public static final int CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE = 204;
    public static final String CROP_IMAGE_EXTRA_BUNDLE = "CROP_IMAGE_EXTRA_BUNDLE";
    public static final String CROP_IMAGE_EXTRA_OPTIONS = "CROP_IMAGE_EXTRA_OPTIONS";
    public static final String CROP_IMAGE_EXTRA_RESULT = "CROP_IMAGE_EXTRA_RESULT";
    public static final String CROP_IMAGE_EXTRA_SOURCE = "CROP_IMAGE_EXTRA_SOURCE";
    public static final int PICK_IMAGE_CHOOSER_REQUEST_CODE = 200;
    public static final int PICK_IMAGE_PERMISSIONS_REQUEST_CODE = 201;

    /* loaded from: classes12.dex */
    public static final class ActivityBuilder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final Uri f13720a;
        public final CropImageOptions b;

        public Intent getIntent(@NonNull Context context) {
            return getIntent(context, CropImageActivity.class);
        }

        public ActivityBuilder setActivityMenuIconColor(int i) {
            this.b.activityMenuIconColor = i;
            return this;
        }

        public ActivityBuilder setActivityTitle(CharSequence charSequence) {
            this.b.activityTitle = charSequence;
            return this;
        }

        public ActivityBuilder setAllowCounterRotation(boolean z) {
            this.b.allowCounterRotation = z;
            return this;
        }

        public ActivityBuilder setAllowFlipping(boolean z) {
            this.b.allowFlipping = z;
            return this;
        }

        public ActivityBuilder setAllowRotation(boolean z) {
            this.b.allowRotation = z;
            return this;
        }

        public ActivityBuilder setAspectRatio(int i, int i2) {
            CropImageOptions cropImageOptions = this.b;
            cropImageOptions.aspectRatioX = i;
            cropImageOptions.aspectRatioY = i2;
            cropImageOptions.fixAspectRatio = true;
            return this;
        }

        public ActivityBuilder setAutoZoomEnabled(boolean z) {
            this.b.autoZoomEnabled = z;
            return this;
        }

        public ActivityBuilder setBackgroundColor(int i) {
            this.b.backgroundColor = i;
            return this;
        }

        public ActivityBuilder setBorderCornerColor(int i) {
            this.b.borderCornerColor = i;
            return this;
        }

        public ActivityBuilder setBorderCornerLength(float f) {
            this.b.borderCornerLength = f;
            return this;
        }

        public ActivityBuilder setBorderCornerOffset(float f) {
            this.b.borderCornerOffset = f;
            return this;
        }

        public ActivityBuilder setBorderCornerThickness(float f) {
            this.b.borderCornerThickness = f;
            return this;
        }

        public ActivityBuilder setBorderLineColor(int i) {
            this.b.borderLineColor = i;
            return this;
        }

        public ActivityBuilder setBorderLineThickness(float f) {
            this.b.borderLineThickness = f;
            return this;
        }

        public ActivityBuilder setCropMenuCropButtonIcon(@DrawableRes int i) {
            this.b.cropMenuCropButtonIcon = i;
            return this;
        }

        public ActivityBuilder setCropMenuCropButtonTitle(CharSequence charSequence) {
            this.b.cropMenuCropButtonTitle = charSequence;
            return this;
        }

        public ActivityBuilder setCropShape(@NonNull CropImageView.CropShape cropShape) {
            this.b.cropShape = cropShape;
            return this;
        }

        public ActivityBuilder setFixAspectRatio(boolean z) {
            this.b.fixAspectRatio = z;
            return this;
        }

        public ActivityBuilder setFlipHorizontally(boolean z) {
            this.b.flipHorizontally = z;
            return this;
        }

        public ActivityBuilder setFlipVertically(boolean z) {
            this.b.flipVertically = z;
            return this;
        }

        public ActivityBuilder setGuidelines(@NonNull CropImageView.Guidelines guidelines) {
            this.b.guidelines = guidelines;
            return this;
        }

        public ActivityBuilder setGuidelinesColor(int i) {
            this.b.guidelinesColor = i;
            return this;
        }

        public ActivityBuilder setGuidelinesThickness(float f) {
            this.b.guidelinesThickness = f;
            return this;
        }

        public ActivityBuilder setInitialCropWindowPaddingRatio(float f) {
            this.b.initialCropWindowPaddingRatio = f;
            return this;
        }

        public ActivityBuilder setInitialCropWindowRectangle(Rect rect) {
            this.b.initialCropWindowRectangle = rect;
            return this;
        }

        public ActivityBuilder setInitialRotation(int i) {
            this.b.initialRotation = (i + 360) % 360;
            return this;
        }

        public ActivityBuilder setMaxCropResultSize(int i, int i2) {
            CropImageOptions cropImageOptions = this.b;
            cropImageOptions.maxCropResultWidth = i;
            cropImageOptions.maxCropResultHeight = i2;
            return this;
        }

        public ActivityBuilder setMaxZoom(int i) {
            this.b.maxZoom = i;
            return this;
        }

        public ActivityBuilder setMinCropResultSize(int i, int i2) {
            CropImageOptions cropImageOptions = this.b;
            cropImageOptions.minCropResultWidth = i;
            cropImageOptions.minCropResultHeight = i2;
            return this;
        }

        public ActivityBuilder setMinCropWindowSize(int i, int i2) {
            CropImageOptions cropImageOptions = this.b;
            cropImageOptions.minCropWindowWidth = i;
            cropImageOptions.minCropWindowHeight = i2;
            return this;
        }

        public ActivityBuilder setMultiTouchEnabled(boolean z) {
            this.b.multiTouchEnabled = z;
            return this;
        }

        public ActivityBuilder setNoOutputImage(boolean z) {
            this.b.noOutputImage = z;
            return this;
        }

        public ActivityBuilder setOutputCompressFormat(Bitmap.CompressFormat compressFormat) {
            this.b.outputCompressFormat = compressFormat;
            return this;
        }

        public ActivityBuilder setOutputCompressQuality(int i) {
            this.b.outputCompressQuality = i;
            return this;
        }

        public ActivityBuilder setOutputUri(Uri uri) {
            this.b.outputUri = uri;
            return this;
        }

        public ActivityBuilder setRequestedSize(int i, int i2) {
            return setRequestedSize(i, i2, CropImageView.RequestSizeOptions.RESIZE_INSIDE);
        }

        public ActivityBuilder setRotationDegrees(int i) {
            this.b.rotationDegrees = (i + 360) % 360;
            return this;
        }

        public ActivityBuilder setScaleType(@NonNull CropImageView.ScaleType scaleType) {
            this.b.scaleType = scaleType;
            return this;
        }

        public ActivityBuilder setShowCropOverlay(boolean z) {
            this.b.showCropOverlay = z;
            return this;
        }

        public ActivityBuilder setSnapRadius(float f) {
            this.b.snapRadius = f;
            return this;
        }

        public ActivityBuilder setTouchRadius(float f) {
            this.b.touchRadius = f;
            return this;
        }

        public void start(@NonNull Activity activity) {
            this.b.validate();
            activity.startActivityForResult(getIntent(activity), 203);
        }

        public ActivityBuilder(@Nullable Uri uri) {
            this.f13720a = uri;
            this.b = new CropImageOptions();
        }

        public Intent getIntent(@NonNull Context context, @Nullable Class<?> cls) {
            this.b.validate();
            Intent intent = new Intent();
            intent.setClass(context, cls);
            Bundle bundle = new Bundle();
            bundle.putParcelable(CropImage.CROP_IMAGE_EXTRA_SOURCE, this.f13720a);
            bundle.putParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS, this.b);
            intent.putExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE, bundle);
            return intent;
        }

        public ActivityBuilder setRequestedSize(int i, int i2, CropImageView.RequestSizeOptions requestSizeOptions) {
            CropImageOptions cropImageOptions = this.b;
            cropImageOptions.outputRequestWidth = i;
            cropImageOptions.outputRequestHeight = i2;
            cropImageOptions.outputRequestSizeOptions = requestSizeOptions;
            return this;
        }

        public void start(@NonNull Activity activity, @Nullable Class<?> cls) {
            this.b.validate();
            activity.startActivityForResult(getIntent(activity, cls), 203);
        }

        public void start(@NonNull Context context, @NonNull Fragment fragment) {
            fragment.startActivityForResult(getIntent(context), 203);
        }

        @RequiresApi(api = 11)
        public void start(@NonNull Context context, @NonNull android.app.Fragment fragment) {
            fragment.startActivityForResult(getIntent(context), 203);
        }

        public void start(@NonNull Context context, @NonNull Fragment fragment, @Nullable Class<?> cls) {
            fragment.startActivityForResult(getIntent(context, cls), 203);
        }

        @RequiresApi(api = 11)
        public void start(@NonNull Context context, @NonNull android.app.Fragment fragment, @Nullable Class<?> cls) {
            fragment.startActivityForResult(getIntent(context, cls), 203);
        }
    }

    /* loaded from: classes12.dex */
    public static final class ActivityResult extends CropImageView.CropResult implements Parcelable {
        public static final Parcelable.Creator<ActivityResult> CREATOR = new a();

        /* loaded from: classes12.dex */
        public static class a implements Parcelable.Creator<ActivityResult> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public ActivityResult createFromParcel(Parcel parcel) {
                return new ActivityResult(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public ActivityResult[] newArray(int i) {
                return new ActivityResult[i];
            }
        }

        public ActivityResult(Uri uri, Uri uri2, Exception exc, float[] fArr, Rect rect, int i, Rect rect2, int i2) {
            super(null, uri, null, uri2, exc, fArr, rect, rect2, i, i2);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(getOriginalUri(), i);
            parcel.writeParcelable(getUri(), i);
            parcel.writeSerializable(getError());
            parcel.writeFloatArray(getCropPoints());
            parcel.writeParcelable(getCropRect(), i);
            parcel.writeParcelable(getWholeImageRect(), i);
            parcel.writeInt(getRotation());
            parcel.writeInt(getSampleSize());
        }

        public ActivityResult(Parcel parcel) {
            super(null, (Uri) parcel.readParcelable(Uri.class.getClassLoader()), null, (Uri) parcel.readParcelable(Uri.class.getClassLoader()), (Exception) parcel.readSerializable(), parcel.createFloatArray(), (Rect) parcel.readParcelable(Rect.class.getClassLoader()), (Rect) parcel.readParcelable(Rect.class.getClassLoader()), parcel.readInt(), parcel.readInt());
        }
    }

    public static ActivityBuilder activity() {
        return new ActivityBuilder(null);
    }

    public static ActivityResult getActivityResult(@Nullable Intent intent) {
        if (intent != null) {
            return (ActivityResult) intent.getParcelableExtra(CROP_IMAGE_EXTRA_RESULT);
        }
        return null;
    }

    public static Intent getCameraIntent(@NonNull Context context, Uri uri) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (uri == null) {
            uri = getCaptureImageOutputUri(context);
        }
        intent.putExtra("output", uri);
        return intent;
    }

    public static List<Intent> getCameraIntents(@NonNull Context context, @NonNull PackageManager packageManager) {
        ArrayList arrayList = new ArrayList();
        Uri captureImageOutputUri = getCaptureImageOutputUri(context);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            Intent intent2 = new Intent(intent);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (captureImageOutputUri != null) {
                intent2.putExtra("output", captureImageOutputUri);
            }
            arrayList.add(intent2);
        }
        return arrayList;
    }

    public static Uri getCaptureImageOutputUri(@NonNull Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            return Uri.fromFile(new File(externalCacheDir.getPath(), "pickImageResult.jpeg"));
        }
        return null;
    }

    public static List<Intent> getGalleryIntents(@NonNull PackageManager packageManager, String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        Intent intent = str == "android.intent.action.GET_CONTENT" ? new Intent(str) : new Intent(str, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType(com.crrepa.r.a.d);
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            Intent intent2 = new Intent(intent);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            arrayList.add(intent2);
        }
        if (!z) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Intent intent3 = (Intent) it.next();
                if (intent3.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                    arrayList.remove(intent3);
                    break;
                }
            }
        }
        return arrayList;
    }

    public static Intent getPickImageChooserIntent(@NonNull Context context) {
        return getPickImageChooserIntent(context, context.getString(R.string.pick_image_intent_chooser_title), false, true);
    }

    public static Uri getPickImageResultUri(@NonNull Context context, @Nullable Intent intent) {
        String action;
        boolean z = true;
        if (intent != null && intent.getData() != null && ((action = intent.getAction()) == null || !action.equals("android.media.action.IMAGE_CAPTURE"))) {
            z = false;
        }
        return (z || intent.getData() == null) ? getCaptureImageOutputUri(context) : intent.getData();
    }

    public static boolean hasPermissionInManifest(@NonNull Context context, @NonNull String str) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null && strArr.length > 0) {
                for (String str2 : strArr) {
                    if (str2.equalsIgnoreCase(str)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static boolean isExplicitCameraPermissionRequired(@NonNull Context context) {
        return Build.VERSION.SDK_INT >= 23 && hasPermissionInManifest(context, "android.permission.CAMERA") && context.checkSelfPermission("android.permission.CAMERA") != 0;
    }

    public static boolean isReadExternalStoragePermissionsRequired(@NonNull Context context, @NonNull Uri uri) {
        return Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0 && isUriRequiresPermissions(context, uri);
    }

    public static boolean isUriRequiresPermissions(@NonNull Context context, @NonNull Uri uri) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                openInputStream.close();
                return false;
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static void startPickImageActivity(@NonNull Activity activity) {
        activity.startActivityForResult(getPickImageChooserIntent(activity), 200);
    }

    public static Bitmap toOvalBitmap(@NonNull Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawOval(new RectF(0.0f, 0.0f, width, height), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        bitmap.recycle();
        return createBitmap;
    }

    public static ActivityBuilder activity(@Nullable Uri uri) {
        return new ActivityBuilder(uri);
    }

    public static void startPickImageActivity(@NonNull Context context, @NonNull Fragment fragment) {
        fragment.startActivityForResult(getPickImageChooserIntent(context), 200);
    }

    public static Intent getPickImageChooserIntent(@NonNull Context context, CharSequence charSequence, boolean z, boolean z2) {
        Intent intent;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        if (!isExplicitCameraPermissionRequired(context) && z2) {
            arrayList.addAll(getCameraIntents(context, packageManager));
        }
        List<Intent> galleryIntents = getGalleryIntents(packageManager, "android.intent.action.GET_CONTENT", z);
        if (galleryIntents.size() == 0) {
            galleryIntents = getGalleryIntents(packageManager, "android.intent.action.PICK", z);
        }
        arrayList.addAll(galleryIntents);
        if (arrayList.isEmpty()) {
            intent = new Intent();
        } else {
            intent = (Intent) arrayList.get(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
        }
        Intent createChooser = Intent.createChooser(intent, charSequence);
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        return createChooser;
    }
}
