package com.coveiot.android.watchfaceui.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.FileUtil;
import com.coveiot.utils.utility.LogHelper;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.jieli.bmp_convert.BmpConvert;
import com.realsil.sdk.dfu.DfuException;
import com.szabh.smable3.component.BleCache;
import com.szabh.smable3.watchface.Element;
import com.touchgui.sdk.TGEventListener;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceBackgroundSMA extends BaseFragment implements OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public float A;
    public float B;
    public float C;
    public float D;
    public float E;
    public float F;
    public float G;
    public float H;
    public float I;
    public float J;
    public float K;
    public float L;
    public int M;
    public float O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int a0;
    public int b0;
    public String c0;
    public String d0;
    public String e0;
    public String mCurrentPhotoPath;
    public WatchFaceBackgroundViewModel n;
    public ActivityWatchFaceViewModel o;
    public int p;
    @Nullable
    public String[] q;
    @Nullable
    public Uri t;
    @Nullable
    public Uri u;
    public float v;
    public float w;
    public float x;
    public float y;
    public float z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentWatchFaceBackgroundSMA";
    public final int r = 1;
    public final int s = 2;
    public boolean N = true;
    public int V = 9;
    @NotNull
    public String W = FileUtil.Format.PNG;
    public int X = 1;
    public int Y = 4;
    public int Z = 32;
    @NotNull
    public final String f0 = "am_pm";
    @NotNull
    public final String g0 = "date";
    @NotNull
    public final String h0 = "hour_minute";
    @NotNull
    public final String i0 = "week";
    public final int j0 = 4;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundSMA newInstance() {
            return new FragmentWatchFaceBackgroundSMA();
        }
    }

    public static /* synthetic */ byte[] C(FragmentWatchFaceBackgroundSMA fragmentWatchFaceBackgroundSMA, File file, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return fragmentWatchFaceBackgroundSMA.B(file, z);
    }

    public static /* synthetic */ Triple I(FragmentWatchFaceBackgroundSMA fragmentWatchFaceBackgroundSMA, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 9;
        }
        return fragmentWatchFaceBackgroundSMA.H(str, i);
    }

    public static /* synthetic */ Triple K(FragmentWatchFaceBackgroundSMA fragmentWatchFaceBackgroundSMA, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 9;
        }
        return fragmentWatchFaceBackgroundSMA.J(str, i);
    }

    public static final void S(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 351;
        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this$0.requireActivity(), new String[]{"android.permission.CAMERA"});
        this$0.q = checkPermissionsHasGranted;
        Intrinsics.checkNotNull(checkPermissionsHasGranted);
        if (!(checkPermissionsHasGranted.length == 0)) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this$0.requireActivity(), "android.permission.CAMERA")) {
                FragmentActivity requireActivity = this$0.requireActivity();
                String[] strArr = this$0.q;
                Intrinsics.checkNotNull(strArr);
                ActivityCompat.requestPermissions(requireActivity, strArr, this$0.p);
                return;
            }
            String string = this$0.getString(R.string.storage_camera_permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.stora…mera_permission_required)");
            this$0.j0(string);
            return;
        }
        this$0.l0();
    }

    public static final void T(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((HorizontalScrollView) this$0._$_findCachedViewById(R.id.horizontal_scroll_view)).setVisibility(8);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(8);
    }

    public static final void U(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((HorizontalScrollView) this$0._$_findCachedViewById(R.id.horizontal_scroll_view)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(8);
    }

    public static final void V(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 350;
        this$0.A();
    }

    public static final void X(FragmentWatchFaceBackgroundSMA this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                if (radioButton.isChecked()) {
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_top))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this$0.n;
                        if (watchFaceBackgroundViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel2 = null;
                        }
                        watchFaceBackgroundViewModel2.setMTimePosition(0);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_top)).setVisibility(0);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_bottom)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_left)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_right)).setVisibility(8);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_bottom))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this$0.n;
                        if (watchFaceBackgroundViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel3 = null;
                        }
                        watchFaceBackgroundViewModel3.setMTimePosition(1);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_top)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_bottom)).setVisibility(0);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_left)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_right)).setVisibility(8);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_left))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this$0.n;
                        if (watchFaceBackgroundViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel4 = null;
                        }
                        watchFaceBackgroundViewModel4.setMTimePosition(2);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_top)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_bottom)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_left)).setVisibility(0);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_right)).setVisibility(8);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_right))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this$0.n;
                        if (watchFaceBackgroundViewModel5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel5 = null;
                        }
                        watchFaceBackgroundViewModel5.setMTimePosition(3);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_top)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_bottom)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_left)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV_right)).setVisibility(0);
                    }
                }
                ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.o;
                if (activityWatchFaceViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                    activityWatchFaceViewModel = null;
                }
                activityWatchFaceViewModel.setWatchFacePushType(2);
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this$0.n;
                if (watchFaceBackgroundViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel6;
                }
                watchFaceBackgroundViewModel.showSaveBtn(true);
                this$0.h0(radioButton);
                this$0.i0(0);
            }
        }
    }

    public static final void Y(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setColor(0);
    }

    public static final void Z(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setColor(1);
    }

    public static final void a0(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setColor(2);
    }

    public static final void b0(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setColor(3);
    }

    public static final void c0(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setColor(4);
    }

    public static final void d0(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setColor(5);
    }

    public static /* synthetic */ byte[] defaultConversion$default(FragmentWatchFaceBackgroundSMA fragmentWatchFaceBackgroundSMA, String str, byte[] bArr, int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            i2 = 16;
        }
        int i5 = i2;
        if ((i4 & 16) != 0) {
            i3 = 70;
        }
        int i6 = i3;
        if ((i4 & 32) != 0) {
            z = true;
        }
        return fragmentWatchFaceBackgroundSMA.defaultConversion(str, bArr, i, i5, i6, z);
    }

    public static final void e0(FragmentWatchFaceBackgroundSMA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setColor(6);
    }

    public static final void k0(FragmentWatchFaceBackgroundSMA this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundSMA newInstance() {
        return Companion.newInstance();
    }

    public final void A() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), this.r);
    }

    public final byte[] B(File file, boolean z) {
        String str = file.getPath() + ".bin";
        int i = z ? 4 : 3;
        LogHelper.d(this.m, "convertPng type=" + i + ", pngFile=" + file + ", outFilePath=" + str);
        int bitmapConvertBlock = new BmpConvert().bitmapConvertBlock(i, file.getPath(), str);
        if (bitmapConvertBlock <= 0) {
            LogHelper.d(this.m, "convertPng error = " + bitmapConvertBlock);
            return null;
        }
        byte[] readFile2BytesByChannel = FileIOUtils.readFile2BytesByChannel(str);
        int length = readFile2BytesByChannel.length;
        int i2 = this.j0;
        int i3 = (((length + i2) - 1) / i2) * i2;
        byte[] bArr = new byte[i3];
        System.arraycopy(readFile2BytesByChannel, 0, bArr, 0, readFile2BytesByChannel.length);
        LogHelper.d(this.m, "convertPng outFileBytes=" + readFile2BytesByChannel.length + ", bytes=" + i3);
        return bArr;
    }

    public final File D() throws IOException {
        String formatDate = AppUtils.formatDate(new Date(), "yyyyMMdd_HHmmss");
        File image = File.createTempFile("JPEG_" + formatDate + '_', ".jpg", requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        String absolutePath = image.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "image.absolutePath");
        setMCurrentPhotoPath(absolutePath);
        Intrinsics.checkNotNullExpressionValue(image, "image");
        return image;
    }

    public final byte[] E() {
        byte[] B;
        Bitmap F = F(false);
        File file = new File(PathUtils.getExternalAppDataPath(), "dial_bg_file.png");
        ImageUtils.save(F, file, Bitmap.CompressFormat.PNG);
        return (!isSupport2DAcceleration() || (B = B(file, false)) == null) ? z(F) : B;
    }

    public final Bitmap F(boolean z) {
        Bitmap bitmap$default;
        Bitmap bgBitMap;
        if (this.N) {
            bitmap$default = ImageUtils.view2Bitmap((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2));
        } else {
            Drawable drawable = ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).getDrawable();
            Intrinsics.checkNotNullExpressionValue(drawable, "selected_watch_face.drawable");
            bitmap$default = DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null);
        }
        float width = this.P / bitmap$default.getWidth();
        float height = (this.Q - this.T) / bitmap$default.getHeight();
        String str = this.m;
        Log.d(str, "test getBgBitmap = " + bitmap$default.getWidth() + "- " + bitmap$default.getHeight() + " ; " + width + " - " + height + ' ');
        Bitmap scale = ImageUtils.scale(bitmap$default, width, height);
        if (this.N) {
            bgBitMap = ImageUtils.toRound(scale, this.a0, getResources().getColor(R.color.black_color));
        } else {
            bgBitMap = ImageUtils.toRoundCorner(scale, this.O, 0.0f, getResources().getColor(R.color.black_color));
        }
        if (!this.N) {
            int i = R.id.selected_watch_face;
            width = this.P / ((RoundedImageView) _$_findCachedViewById(i)).getWidth();
            height = (this.Q - this.T) / ((RoundedImageView) _$_findCachedViewById(i)).getHeight();
        }
        float f = width;
        float f2 = height;
        Canvas canvas = new Canvas(bgBitMap);
        StringBuilder sb = new StringBuilder();
        String str2 = this.e0;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
            str2 = null;
        }
        sb.append(str2);
        sb.append('/');
        sb.append(this.M);
        sb.append('/');
        x(sb.toString(), f, f2, canvas, z);
        Intrinsics.checkNotNullExpressionValue(bgBitMap, "bgBitMap");
        return G(bgBitMap);
    }

    public final Bitmap G(Bitmap bitmap) {
        Bitmap finalBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() + 1, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(finalBitmap);
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        Intrinsics.checkNotNullExpressionValue(finalBitmap, "finalBitmap");
        return finalBitmap;
    }

    public final Triple<Integer, Integer, ArrayList<byte[]>> H(String str, int i) {
        int i2;
        int i3;
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        if (i >= 0) {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                if (i4 == 0) {
                    Bitmap bitmap = ImageUtils.getBitmap(getResources().getAssets().open(str + i6 + '.' + this.W));
                    int width = bitmap.getWidth();
                    i2 = bitmap.getHeight();
                    i3 = width;
                } else {
                    i3 = i4;
                    i2 = i5;
                }
                InputStream it = getResources().getAssets().open(str + i6 + '.' + this.W);
                try {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    byte[] readBytes = ByteStreamsKt.readBytes(it);
                    CloseableKt.closeFinally(it, null);
                    arrayList.add(defaultConversion$default(this, this.W, readBytes, i3, 0, 0, false, 56, null));
                    if (i6 == i) {
                        break;
                    }
                    i6++;
                    i4 = i3;
                    i5 = i2;
                } finally {
                }
            }
            i4 = i3;
        } else {
            i2 = 0;
        }
        return new Triple<>(Integer.valueOf(i4), Integer.valueOf(i2), arrayList);
    }

    public final Triple<Integer, Integer, ArrayList<byte[]>> J(String str, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        if (i >= 0) {
            int i4 = 0;
            int i5 = 0;
            i2 = 0;
            while (true) {
                String str2 = str + i4 + '.' + this.W;
                if (i5 == 0) {
                    Bitmap bitmap = ImageUtils.getBitmap(getResources().getAssets().open(str2));
                    int width = bitmap.getWidth();
                    i2 = bitmap.getHeight();
                    i5 = width;
                }
                InputStream it = getResources().getAssets().open(str2);
                try {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    byte[] readBytes = ByteStreamsKt.readBytes(it);
                    CloseableKt.closeFinally(it, null);
                    File file = new File(PathUtils.getExternalAppDataPath(), str2);
                    FileIOUtils.writeFileFromBytesByStream(file, readBytes);
                    byte[] C = C(this, file, false, 2, null);
                    if (C != null) {
                        arrayList.add(C);
                    }
                    if (i4 == i) {
                        break;
                    }
                    i4++;
                } finally {
                }
            }
            i3 = i5;
        } else {
            i2 = 0;
        }
        return new Triple<>(Integer.valueOf(i3), Integer.valueOf(i2), arrayList);
    }

    public final byte[] L() {
        Bitmap finalPreviewBitMap;
        byte[] B;
        Bitmap F = F(true);
        float width = this.R / F.getWidth();
        Bitmap scale = ImageUtils.scale(F, width, this.S / F.getHeight());
        if (this.N) {
            finalPreviewBitMap = ImageUtils.toRound(scale, this.a0, getResources().getColor(R.color.black_color));
        } else {
            finalPreviewBitMap = ImageUtils.toRoundCorner(scale, this.O * width, this.a0, getResources().getColor(R.color.black_color));
        }
        File file = new File(PathUtils.getExternalAppDataPath(), "dial_bg_preview_file.png");
        ImageUtils.save(finalPreviewBitMap, file, Bitmap.CompressFormat.PNG);
        if (!isSupport2DAcceleration() || (B = B(file, false)) == null) {
            Intrinsics.checkNotNullExpressionValue(finalPreviewBitMap, "finalPreviewBitMap");
            return z(finalPreviewBitMap);
        }
        return B;
    }

    public final void M(String str, int i, int i2, int i3, ArrayList<Element> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        AssetManager assets = getResources().getAssets();
        Bitmap bitmap = ImageUtils.getBitmap(assets.open(str + "/symbol." + this.W));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        String str2 = this.W;
        AssetManager assets2 = getResources().getAssets();
        InputStream it = assets2.open(str + "/symbol." + this.W);
        try {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            byte[] readBytes = ByteStreamsKt.readBytes(it);
            CloseableKt.closeFinally(it, null);
            arrayList2.add(defaultConversion$default(this, str2, readBytes, width, 0, 0, false, 56, null));
            arrayList.add(new Element(i, 0, width, height, 9, this.b0, i2, i3, 0, 0, (byte[][]) arrayList2.toArray(new byte[0]), TGEventListener.REQUEST_UPDATE_WEATHER, null));
        } finally {
        }
    }

    public final void N(String str, int i, int i2, int i3, ArrayList<Element> arrayList) {
        String str2 = str + "/symbol." + this.W;
        ArrayList arrayList2 = new ArrayList();
        Bitmap bitmap = ImageUtils.getBitmap(getResources().getAssets().open(str2));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        InputStream it = getResources().getAssets().open(str2);
        try {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            byte[] readBytes = ByteStreamsKt.readBytes(it);
            CloseableKt.closeFinally(it, null);
            File file = new File(PathUtils.getExternalAppDataPath(), str2);
            FileIOUtils.writeFileFromBytesByStream(file, readBytes);
            byte[] C = C(this, file, false, 2, null);
            if (C != null) {
                arrayList2.add(C);
            }
            arrayList.add(new Element(i | 128, 0, width, height, 9, this.b0, i2, i3, 0, 0, (byte[][]) arrayList2.toArray(new byte[0]), TGEventListener.REQUEST_UPDATE_WEATHER, null));
        } finally {
        }
    }

    public final void O(ArrayList<Element> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        AssetManager assets = getResources().getAssets();
        StringBuilder sb = new StringBuilder();
        String str = this.e0;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
            str = null;
        }
        sb.append(str);
        sb.append('/');
        sb.append(this.M);
        sb.append('/');
        sb.append(this.f0);
        sb.append("/am.");
        sb.append(this.W);
        Bitmap bitmap = ImageUtils.getBitmap(assets.open(sb.toString()));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        AssetManager assets2 = getResources().getAssets();
        StringBuilder sb2 = new StringBuilder();
        String str3 = this.e0;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
            str3 = null;
        }
        sb2.append(str3);
        sb2.append('/');
        sb2.append(this.M);
        sb2.append('/');
        sb2.append(this.f0);
        sb2.append("/am.");
        sb2.append(this.W);
        InputStream it = assets2.open(sb2.toString());
        try {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            byte[] readBytes = ByteStreamsKt.readBytes(it);
            CloseableKt.closeFinally(it, null);
            AssetManager assets3 = getResources().getAssets();
            StringBuilder sb3 = new StringBuilder();
            String str4 = this.e0;
            if (str4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                str4 = null;
            }
            sb3.append(str4);
            sb3.append('/');
            sb3.append(this.M);
            sb3.append('/');
            sb3.append(this.f0);
            sb3.append("/pm.");
            sb3.append(this.W);
            it = assets3.open(sb3.toString());
            try {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                byte[] readBytes2 = ByteStreamsKt.readBytes(it);
                CloseableKt.closeFinally(it, null);
                arrayList2.add(defaultConversion$default(this, this.W, readBytes, width, 0, 0, false, 56, null));
                arrayList2.add(defaultConversion$default(this, this.W, readBytes2, width, 0, 0, false, 56, null));
                arrayList.add(new Element(12, 0, width, height, 9, this.b0, (int) this.v, (int) this.w, 0, 0, (byte[][]) arrayList2.toArray(new byte[0]), TGEventListener.REQUEST_UPDATE_WEATHER, null));
                StringBuilder sb4 = new StringBuilder();
                String str5 = this.e0;
                if (str5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                    str5 = null;
                }
                sb4.append(str5);
                sb4.append('/');
                sb4.append(this.M);
                sb4.append('/');
                sb4.append(this.h0);
                sb4.append('/');
                Triple I = I(this, sb4.toString(), 0, 2, null);
                int intValue = ((Number) I.getFirst()).intValue();
                int intValue2 = ((Number) I.getSecond()).intValue();
                byte[][] bArr = (byte[][]) ((Collection) I.getThird()).toArray(new byte[0]);
                arrayList.add(new Element(9, 0, intValue, intValue2, 9, this.b0, (int) this.x, (int) this.y, 0, 0, bArr, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                arrayList.add(new Element(10, 0, intValue, intValue2, 9, this.b0, (int) this.z, (int) this.B, 0, 0, bArr, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                if (this.T != 0) {
                    StringBuilder sb5 = new StringBuilder();
                    String str6 = this.e0;
                    if (str6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                        str6 = null;
                    }
                    sb5.append(str6);
                    sb5.append('/');
                    sb5.append(this.M);
                    sb5.append('/');
                    sb5.append(this.h0);
                    M(sb5.toString(), 20, (int) this.C, (int) this.D, arrayList);
                }
                StringBuilder sb6 = new StringBuilder();
                String str7 = this.e0;
                if (str7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                    str7 = null;
                }
                sb6.append(str7);
                sb6.append('/');
                sb6.append(this.M);
                sb6.append('/');
                sb6.append(this.g0);
                sb6.append('/');
                Triple I2 = I(this, sb6.toString(), 0, 2, null);
                int intValue3 = ((Number) I2.getFirst()).intValue();
                int intValue4 = ((Number) I2.getSecond()).intValue();
                byte[][] bArr2 = (byte[][]) ((Collection) I2.getThird()).toArray(new byte[0]);
                arrayList.add(new Element(7, 0, intValue3, intValue4, 9, this.b0, (int) this.E, (int) this.F, 0, 0, bArr2, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                arrayList.add(new Element(8, 0, intValue3, intValue4, 9, this.b0, (int) this.G, (int) this.H, 0, 0, bArr2, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                if (this.T != 0) {
                    StringBuilder sb7 = new StringBuilder();
                    String str8 = this.e0;
                    if (str8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                        str8 = null;
                    }
                    sb7.append(str8);
                    sb7.append('/');
                    sb7.append(this.M);
                    sb7.append('/');
                    sb7.append(this.g0);
                    M(sb7.toString(), 21, (int) this.I, (int) this.J, arrayList);
                }
                StringBuilder sb8 = new StringBuilder();
                String str9 = this.e0;
                if (str9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                } else {
                    str2 = str9;
                }
                sb8.append(str2);
                sb8.append('/');
                sb8.append(this.M);
                sb8.append('/');
                sb8.append(this.i0);
                sb8.append('/');
                Triple<Integer, Integer, ArrayList<byte[]>> H = H(sb8.toString(), 6);
                arrayList.add(new Element(13, 0, H.getFirst().intValue(), H.getSecond().intValue(), 9, this.b0, (int) this.K, (int) this.L, 0, 0, (byte[][]) H.getThird().toArray(new byte[0]), TGEventListener.REQUEST_UPDATE_WEATHER, null));
            } finally {
            }
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    public final void P(ArrayList<Element> arrayList) {
        int i;
        int i2;
        StringBuilder sb = new StringBuilder();
        String str = this.e0;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
            str = null;
        }
        sb.append(str);
        sb.append('/');
        sb.append(this.M);
        sb.append('/');
        sb.append(this.f0);
        sb.append("/am.");
        sb.append(this.W);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        String str3 = this.e0;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
            str3 = null;
        }
        sb3.append(str3);
        sb3.append('/');
        sb3.append(this.M);
        sb3.append('/');
        sb3.append(this.f0);
        sb3.append("/pm.");
        sb3.append(this.W);
        String sb4 = sb3.toString();
        ArrayList arrayList2 = new ArrayList();
        Bitmap bitmap = ImageUtils.getBitmap(getResources().getAssets().open(sb2));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        InputStream it = getResources().getAssets().open(sb2);
        try {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            byte[] readBytes = ByteStreamsKt.readBytes(it);
            CloseableKt.closeFinally(it, null);
            it = getResources().getAssets().open(sb4);
            try {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                byte[] readBytes2 = ByteStreamsKt.readBytes(it);
                CloseableKt.closeFinally(it, null);
                File file = new File(PathUtils.getExternalAppDataPath(), sb2);
                FileIOUtils.writeFileFromBytesByStream(file, readBytes);
                File file2 = new File(PathUtils.getExternalAppDataPath(), sb4);
                FileIOUtils.writeFileFromBytesByStream(file2, readBytes2);
                byte[] C = C(this, file, false, 2, null);
                if (C != null) {
                    arrayList2.add(C);
                }
                byte[] C2 = C(this, file2, false, 2, null);
                if (C2 != null) {
                    arrayList2.add(C2);
                }
                arrayList.add(new Element(140, 0, width, height, 9, this.b0, (int) this.v, (int) this.w, 0, 0, (byte[][]) arrayList2.toArray(new byte[0]), TGEventListener.REQUEST_UPDATE_WEATHER, null));
                StringBuilder sb5 = new StringBuilder();
                String str4 = this.e0;
                if (str4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                    str4 = null;
                }
                sb5.append(str4);
                sb5.append('/');
                sb5.append(this.M);
                sb5.append('/');
                sb5.append(this.h0);
                sb5.append('/');
                Triple K = K(this, sb5.toString(), 0, 2, null);
                int intValue = ((Number) K.getFirst()).intValue();
                int intValue2 = ((Number) K.getSecond()).intValue();
                byte[][] bArr = (byte[][]) ((Collection) K.getThird()).toArray(new byte[0]);
                arrayList.add(new Element(137, 0, intValue, intValue2, 9, this.b0, (int) this.x, (int) this.y, 0, 0, bArr, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                arrayList.add(new Element(138, 0, intValue, intValue2, 9, this.b0, (int) this.z, (int) this.B, 0, 0, bArr, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                if (this.T != 0) {
                    StringBuilder sb6 = new StringBuilder();
                    String str5 = this.e0;
                    if (str5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                        str5 = null;
                    }
                    sb6.append(str5);
                    sb6.append('/');
                    sb6.append(this.M);
                    sb6.append('/');
                    sb6.append(this.h0);
                    i = 2;
                    i2 = 0;
                    N(sb6.toString(), 20, (int) this.C, (int) this.D, arrayList);
                } else {
                    i = 2;
                    i2 = 0;
                }
                StringBuilder sb7 = new StringBuilder();
                String str6 = this.e0;
                if (str6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                    str6 = null;
                }
                sb7.append(str6);
                sb7.append('/');
                sb7.append(this.M);
                sb7.append('/');
                sb7.append(this.g0);
                sb7.append('/');
                Triple K2 = K(this, sb7.toString(), i2, i, null);
                int intValue3 = ((Number) K2.getFirst()).intValue();
                int intValue4 = ((Number) K2.getSecond()).intValue();
                byte[][] bArr2 = (byte[][]) ((Collection) K2.getThird()).toArray(new byte[i2]);
                arrayList.add(new Element(135, 0, intValue3, intValue4, 9, this.b0, (int) this.E, (int) this.F, 0, 0, bArr2, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                arrayList.add(new Element(136, 0, intValue3, intValue4, 9, this.b0, (int) this.G, (int) this.H, 0, 0, bArr2, TGEventListener.REQUEST_UPDATE_WEATHER, null));
                if (this.T != 0) {
                    StringBuilder sb8 = new StringBuilder();
                    String str7 = this.e0;
                    if (str7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                        str7 = null;
                    }
                    sb8.append(str7);
                    sb8.append('/');
                    sb8.append(this.M);
                    sb8.append('/');
                    sb8.append(this.g0);
                    N(sb8.toString(), 21, (int) this.I, (int) this.J, arrayList);
                }
                StringBuilder sb9 = new StringBuilder();
                String str8 = this.e0;
                if (str8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DIGITAL_DIR");
                } else {
                    str2 = str8;
                }
                sb9.append(str2);
                sb9.append('/');
                sb9.append(this.M);
                sb9.append('/');
                sb9.append(this.i0);
                sb9.append('/');
                Triple<Integer, Integer, ArrayList<byte[]>> J = J(sb9.toString(), 6);
                arrayList.add(new Element(141, 0, J.getFirst().intValue(), J.getSecond().intValue(), 9, this.b0, (int) this.K, (int) this.L, 0, 0, (byte[][]) J.getThird().toArray(new byte[i2]), TGEventListener.REQUEST_UPDATE_WEATHER, null));
            } finally {
            }
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    public final ArrayList<Element> Q() {
        ArrayList<Element> arrayList = new ArrayList<>();
        if (isSupport2DAcceleration()) {
            arrayList.add(new Element(1, 0, this.R, this.S, 0, this.b0, 0, 0, 0, 0, new byte[][]{L()}, TGEventListener.REQUEST_UPDATE_WEATHER, null));
            arrayList.add(new Element(2, 0, this.P, this.Q + 1, 9, this.b0, 0, 0, 0, 0, new byte[][]{E()}, TGEventListener.REQUEST_UPDATE_WEATHER, null));
            P(arrayList);
        } else {
            arrayList.add(new Element(1, 0, this.R, this.S, this.Z | this.Y, 0, this.P / 2, (this.Q / 2) + 2, 0, 0, new byte[][]{L()}, 802, null));
            byte[] E = E();
            int i = this.P;
            int i2 = this.Q;
            arrayList.add(new Element(2, 0, i, i2, this.Z | this.Y, 0, i / 2, i2 / 2, 0, 0, new byte[][]{E}, 802, null));
            O(arrayList);
        }
        return arrayList;
    }

    public final void R() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.V(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.j1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.S(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownUpLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.f1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.T(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownDownLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.n1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.U(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
    }

    public final void W() {
        ((RadioGroup) _$_findCachedViewById(R.id.rg_time_position)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.h1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundSMA.X(FragmentWatchFaceBackgroundSMA.this, radioGroup, i);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color1)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.k1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.Y(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.p1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.Z(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.i1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.a0(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color4)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.b0(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color5)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.o1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.c0(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color7)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.d0(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color8)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.q1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.e0(FragmentWatchFaceBackgroundSMA.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.lang.Iterable, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r11v9, types: [java.util.List] */
    @NotNull
    public final byte[] defaultConversion(@NotNull String fileFormat, @NotNull byte[] data, int i, int i2, int i3, boolean z) {
        Intrinsics.checkNotNullParameter(fileFormat, "fileFormat");
        Intrinsics.checkNotNullParameter(data, "data");
        if (Intrinsics.areEqual(fileFormat, "bmp")) {
            byte b = i3 == 0 ? (byte) 0 : data[10];
            Log.d(this.m, "headerInfoSize " + ((int) b));
            List<Byte> takeLast = ArraysKt___ArraysKt.takeLast(data, data.length - b);
            int i4 = (((i2 * i) + 31) / 32) * 4;
            ?? arrayList = new ArrayList();
            int i5 = i % 2 == 0 ? 0 : 2;
            int size = takeLast.size() / i4;
            for (int i6 = 0; i6 < size; i6++) {
                int i7 = i4 - i5;
                byte[] bArr = new byte[i7];
                for (int i8 = 0; i8 < i7; i8++) {
                    bArr[i8] = takeLast.get((i6 * i4) + i8).byteValue();
                }
                arrayList.add(bArr);
            }
            if (z) {
                arrayList = CollectionsKt___CollectionsKt.reversed(arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            int size2 = arrayList.size();
            for (int i9 = 0; i9 < size2; i9++) {
                for (int i10 = 0; i10 < ((byte[]) arrayList.get(i9)).length; i10 += 2) {
                    arrayList2.add(Byte.valueOf(((byte[]) arrayList.get(i9))[i10 + 1]));
                    arrayList2.add(Byte.valueOf(((byte[]) arrayList.get(i9))[i10]));
                }
            }
            int size3 = arrayList2.size();
            byte[] bArr2 = new byte[size3];
            for (int i11 = 0; i11 < size3; i11++) {
                Object obj = arrayList2.get(i11);
                Intrinsics.checkNotNullExpressionValue(obj, "test3[index]");
                bArr2[i11] = ((Number) obj).byteValue();
            }
            return bArr2;
        }
        return data;
    }

    public final void f0() {
        if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.smaR9)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(8);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(0);
            this.c0 = "dial_customize_454";
            this.P = 454;
            this.Q = 454;
            this.R = DfuException.ERROR_ENTER_OTA_MODE_FAILED;
            this.S = DfuException.ERROR_ENTER_OTA_MODE_FAILED;
            this.N = true;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.smaF2)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(0);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(8);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            this.c0 = "dial_customize_240";
            this.P = 240;
            this.Q = 240;
            this.R = 150;
            this.S = 150;
            this.N = false;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.SMA_WAVE_GENESIS_PRO)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(0);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(8);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            this.c0 = "dial_customize_454";
            this.P = 454;
            this.Q = 502;
            this.R = 260;
            this.S = 300;
            this.N = false;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.SMA_WAVE_ELEVATE_PRO)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(0);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(8);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            this.c0 = "dial_customize_454";
            this.P = 454;
            this.Q = 502;
            this.R = 260;
            this.S = 300;
            this.N = false;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.SMA_WAVE_GLORY_PRO)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(0);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(8);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            this.c0 = "dial_customize_454";
            this.P = 454;
            this.Q = 502;
            this.R = 260;
            this.S = 300;
            this.N = false;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.SMA_ULTIMA_VOGUE)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(0);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(8);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            this.c0 = "dial_customize_454";
            this.P = 454;
            this.Q = 502;
            this.R = 260;
            this.S = 300;
            this.N = false;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.SMA_LUNAR_SEEK)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(8);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
            this.c0 = "dial_customize_240";
            this.P = 240;
            this.Q = 240;
            this.R = 150;
            this.S = 150;
            this.N = true;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.SMA_LUNAR_COMET)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(8);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
            this.c0 = "dial_customize_240";
            this.P = 240;
            this.Q = 240;
            this.R = 150;
            this.S = 150;
            this.N = true;
        } else if (BleApiManager.getInstance(requireContext()) != null && BleApiManager.getInstance(requireContext()).getDeviceType().equals(DeviceType.SMA_LUNAR_VELOCITY)) {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setVisibility(8);
            ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
            this.c0 = "dial_customize_240";
            this.P = 240;
            this.Q = 240;
            this.R = 150;
            this.S = 150;
            this.N = true;
        }
        this.U = 1;
        this.b0 = isSupport2DAcceleration() ? 4 : 1;
        this.V = 10;
        this.W = isSupport2DAcceleration() ? FileUtil.Format.PNG : "bmp";
        this.X = 2;
        this.Y = 2;
        this.Z = 16;
        StringBuilder sb = new StringBuilder();
        String str = this.c0;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("DIAL_CUSTOMIZE_DIR");
            str = null;
        }
        sb.append(str);
        sb.append("/value");
        StringBuilder sb2 = new StringBuilder();
        String str3 = this.c0;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("DIAL_CUSTOMIZE_DIR");
            str3 = null;
        }
        sb2.append(str3);
        sb2.append("/time");
        this.d0 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        String str4 = this.d0;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("TIME_DIR");
        } else {
            str2 = str4;
        }
        sb3.append(str2);
        sb3.append("/digital");
        this.e0 = sb3.toString();
    }

    public final void g0() {
        String modelNumber;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
        DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
            DeviceModelBean deviceModelBean2 = SessionManager.getInstance(getContext()).getDeviceModelBean();
            modelNumber = deviceModelBean2 != null ? deviceModelBean2.getName() : null;
        } else {
            Utils utils = Utils.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            modelNumber = utils.getModelNumber(requireActivity);
        }
        analyticsLog.setCVPrevScreenName(modelNumber + "_features");
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.APPLY_WATCHFACE.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(FirebaseEventParams.MetaData.CV_WATCHFACE_CATEGORY.getValue(), "background");
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.n;
        if (watchFaceBackgroundViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel2;
        }
        watchFaceBackgroundViewModel.sendSMAWatchFaceBackgroundToWatch(Q());
    }

    public final float getAmLeftX() {
        return this.v;
    }

    public final float getAmTopY() {
        return this.w;
    }

    public final int getBorderSize() {
        return this.a0;
    }

    public final int getControlValueInterval() {
        return this.U;
    }

    public final int getControlValueRange() {
        return this.V;
    }

    @NotNull
    public final String getDIGITAL_AM_DIR() {
        return this.f0;
    }

    @NotNull
    public final String getDIGITAL_DATE_DIR() {
        return this.g0;
    }

    @NotNull
    public final String getDIGITAL_HOUR_MINUTE_DIR() {
        return this.h0;
    }

    @NotNull
    public final String getDIGITAL_WEEK_DIR() {
        return this.i0;
    }

    public final float getDigitalDateDayLeftX() {
        return this.G;
    }

    public final float getDigitalDateDayTopY() {
        return this.H;
    }

    public final float getDigitalDateMonthLeftX() {
        return this.E;
    }

    public final float getDigitalDateMonthTopY() {
        return this.F;
    }

    public final float getDigitalDateSymbolLeftX() {
        return this.I;
    }

    public final float getDigitalDateSymbolTopY() {
        return this.J;
    }

    public final float getDigitalTimeHourLeftX() {
        return this.x;
    }

    public final float getDigitalTimeHourTopY() {
        return this.y;
    }

    public final float getDigitalTimeMinuteLeftX() {
        return this.z;
    }

    public final float getDigitalTimeMinuteRightX() {
        return this.A;
    }

    public final float getDigitalTimeMinuteTopY() {
        return this.B;
    }

    public final float getDigitalTimeSymbolLeftX() {
        return this.C;
    }

    public final float getDigitalTimeSymbolTopY() {
        return this.D;
    }

    public final int getDigitalValueColor() {
        return this.M;
    }

    public final float getDigitalWeekLeftX() {
        return this.K;
    }

    public final float getDigitalWeekTopY() {
        return this.L;
    }

    @NotNull
    public final String getFileFormat() {
        return this.W;
    }

    public final int getIgnoreBlack() {
        return this.b0;
    }

    public final int getImageFormat() {
        return this.X;
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

    public final float getRoundCornerRadius() {
        return this.O;
    }

    public final int getSIZE_4() {
        return this.j0;
    }

    public final int getScreenHeight() {
        return this.Q;
    }

    public final int getScreenPreviewHeight() {
        return this.S;
    }

    public final int getScreenPreviewWidth() {
        return this.R;
    }

    public final int getScreenReservedBoundary() {
        return this.T;
    }

    public final int getScreenWidth() {
        return this.P;
    }

    public final int getY_CENTER() {
        return this.Z;
    }

    public final void h0(RadioButton radioButton) {
        if (radioButton.isChecked()) {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_selected), (Drawable) null, (Drawable) null);
        } else {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_unselected), (Drawable) null, (Drawable) null);
        }
    }

    public final void i0(int i) {
        Resources resources = getResources();
        int identifier = resources.getIdentifier("digital_time_" + i, "drawable", requireContext().getPackageName());
        ((ImageView) _$_findCachedViewById(R.id.timeTextImgV_top)).setImageResource(identifier);
        ((ImageView) _$_findCachedViewById(R.id.timeTextImgV_bottom)).setImageResource(identifier);
        ((ImageView) _$_findCachedViewById(R.id.timeTextImgV_left)).setImageResource(identifier);
        ((ImageView) _$_findCachedViewById(R.id.timeTextImgV_right)).setImageResource(identifier);
    }

    public final boolean isRound() {
        return this.N;
    }

    public final boolean isSupport2DAcceleration() {
        return BleCache.INSTANCE.getMSupport2DAcceleration() == 1;
    }

    public final void j0(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundSMA.k0(FragmentWatchFaceBackgroundSMA.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void l0() {
        File file = null;
        this.t = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            try {
                file = D();
            } catch (IOException unused) {
            }
            if (file != null) {
                FragmentActivity requireActivity = requireActivity();
                Uri uriForFile = FileProvider.getUriForFile(requireActivity, requireActivity().getPackageName() + ".provider", file);
                this.t = uriForFile;
                intent.putExtra("output", uriForFile);
                startActivityForResult(intent, this.s);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i == this.r) {
            if (intent != null) {
                try {
                    Uri data = intent.getData();
                    int i3 = !this.N ? 1 : 0;
                    Utils utils = Utils.INSTANCE;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    utils.startUCrop(requireActivity, this, data, i3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (i == this.s) {
            Uri uri = this.t;
            if (uri != null) {
                int i4 = !this.N ? 1 : 0;
                Utils utils2 = Utils.INSTANCE;
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                utils2.startUCrop(requireActivity2, this, uri, i4);
            }
        } else if (i != 69) {
            super.onActivityResult(i, i2, intent);
        } else if (intent != null) {
            Uri output = UCrop.getOutput(intent);
            this.u = output;
            if (output != null) {
                try {
                    int i5 = R.id.selected_watch_face;
                    if (((RoundedImageView) _$_findCachedViewById(i5)) != null) {
                        int i6 = R.id.selected_watch_face2;
                        if (((CircularImageView) _$_findCachedViewById(i6)) != null) {
                            ContentResolver contentResolver = requireActivity().getContentResolver();
                            Uri uri2 = this.u;
                            Intrinsics.checkNotNull(uri2);
                            ((RoundedImageView) _$_findCachedViewById(i5)).setImageBitmap(BitmapFactory.decodeStream(contentResolver.openInputStream(uri2)));
                            ContentResolver contentResolver2 = requireActivity().getContentResolver();
                            Uri uri3 = this.u;
                            Intrinsics.checkNotNull(uri3);
                            ((CircularImageView) _$_findCachedViewById(i6)).setImageBitmap(BitmapFactory.decodeStream(contentResolver2.openInputStream(uri3)));
                            ActivityWatchFaceViewModel activityWatchFaceViewModel = this.o;
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                            if (activityWatchFaceViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                                activityWatchFaceViewModel = null;
                            }
                            activityWatchFaceViewModel.setWatchFacePushType(2);
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.n;
                            if (watchFaceBackgroundViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel2 = null;
                            }
                            watchFaceBackgroundViewModel2.setSelectedBackgroundImageUri(this.u);
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.n;
                            if (watchFaceBackgroundViewModel3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            } else {
                                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
                            }
                            watchFaceBackgroundViewModel.showSaveBtn(true);
                        }
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_face_background_sma, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        int i2 = this.p;
        if (i2 == 350) {
            if (!(grantResults.length == 0)) {
                A();
            }
        } else if (i2 != 351) {
            super.onRequestPermissionsResult(i, permissions, grantResults);
        } else {
            if (!(grantResults.length == 0)) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(requireActivity(), new String[]{"android.permission.CAMERA"});
                Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
                if (!(checkPermissionsHasGranted.length == 0)) {
                    return;
                }
                l0();
            }
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onSaveClicked() {
        if (this.o == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.o;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 2) {
            if (this.n == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            }
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.n;
            if (watchFaceBackgroundViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                watchFaceBackgroundViewModel2 = null;
            }
            if (watchFaceBackgroundViewModel2.getSelectedBackgroundImageUri() != null) {
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.n;
                if (watchFaceBackgroundViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel3 = null;
                }
                if (watchFaceBackgroundViewModel3.getMTimePosition() != -1) {
                    if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundSMA$onSaveClicked$1
                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                LogHelper.i("FragmentWatchFaceCloud", "BatteryLevelRequest -- onDataError ");
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                if (response.getResponseData() instanceof BatteryLevelResponse) {
                                    Object responseData = response.getResponseData();
                                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                                    Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                                    Intrinsics.checkNotNull(batteryLevel);
                                    int intValue = batteryLevel.intValue();
                                    Utils utils = Utils.INSTANCE;
                                    Context requireContext = FragmentWatchFaceBackgroundSMA.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    if (utils.isRuggedDevice(requireContext)) {
                                        intValue = utils.getBatteryPercentageForMatrix(intValue);
                                    }
                                    LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                                    Context requireContext2 = FragmentWatchFaceBackgroundSMA.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                    if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                        FragmentWatchFaceBackgroundSMA.this.g0();
                                        return;
                                    }
                                    FragmentActivity requireActivity = FragmentWatchFaceBackgroundSMA.this.requireActivity();
                                    FragmentWatchFaceBackgroundSMA fragmentWatchFaceBackgroundSMA = FragmentWatchFaceBackgroundSMA.this;
                                    int i = R.string.make_sure_battery;
                                    StringBuilder sb = new StringBuilder();
                                    Context requireContext3 = FragmentWatchFaceBackgroundSMA.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                    sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext3));
                                    sb.append(" %");
                                    Toast.makeText(requireActivity, fragmentWatchFaceBackgroundSMA.getString(i, sb.toString()), 1).show();
                                }
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onProgressUpdate(@NotNull ProgressData progress) {
                                Intrinsics.checkNotNullParameter(progress, "progress");
                            }
                        });
                        return;
                    } else {
                        Toast.makeText(requireActivity(), getString(R.string.band_not_connected), 1).show();
                        return;
                    }
                }
                Toast.makeText(requireActivity(), getString(R.string.please_choose_time_position), 1).show();
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this.n;
                if (watchFaceBackgroundViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel4;
                }
                watchFaceBackgroundViewModel.onFail();
                return;
            }
            Toast.makeText(requireActivity(), getString(R.string.please_chooose_background), 1).show();
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this.n;
            if (watchFaceBackgroundViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel5;
            }
            watchFaceBackgroundViewModel.onFail();
            return;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this.n;
        if (watchFaceBackgroundViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel6;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(WatchFaceBackgroundViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…undViewModel::class.java)");
        this.n = (WatchFaceBackgroundViewModel) viewModel;
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity3, new ViewModelFactory(requireActivity4)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…aceViewModel::class.java)");
        this.o = (ActivityWatchFaceViewModel) viewModel2;
        R();
        W();
        f0();
        try {
            String lastWatchFaceBackgroundUrl = UserDataManager.getInstance(getContext()).getLastWatchFaceBackgroundUrl();
            if (lastWatchFaceBackgroundUrl != null) {
                Uri fromFile = Uri.fromFile(new File(lastWatchFaceBackgroundUrl));
                RoundedImageView roundedImageView = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                Intrinsics.checkNotNull(roundedImageView);
                roundedImageView.setImageBitmap(BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(fromFile.toString()))));
                CircularImageView circularImageView = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                Intrinsics.checkNotNull(circularImageView);
                circularImageView.setImageBitmap(BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(fromFile.toString()))));
                this.u = fromFile;
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.n;
                if (watchFaceBackgroundViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel = null;
                }
                watchFaceBackgroundViewModel.setSelectedBackgroundImageUri(this.u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setAmLeftX(float f) {
        this.v = f;
    }

    public final void setAmTopY(float f) {
        this.w = f;
    }

    public final void setBorderSize(int i) {
        this.a0 = i;
    }

    public final void setColor(int i) {
        this.M = i;
        i0(i);
    }

    public final void setControlValueInterval(int i) {
        this.U = i;
    }

    public final void setControlValueRange(int i) {
        this.V = i;
    }

    public final void setDigitalDateDayLeftX(float f) {
        this.G = f;
    }

    public final void setDigitalDateDayTopY(float f) {
        this.H = f;
    }

    public final void setDigitalDateMonthLeftX(float f) {
        this.E = f;
    }

    public final void setDigitalDateMonthTopY(float f) {
        this.F = f;
    }

    public final void setDigitalDateSymbolLeftX(float f) {
        this.I = f;
    }

    public final void setDigitalDateSymbolTopY(float f) {
        this.J = f;
    }

    public final void setDigitalTimeHourLeftX(float f) {
        this.x = f;
    }

    public final void setDigitalTimeHourTopY(float f) {
        this.y = f;
    }

    public final void setDigitalTimeMinuteLeftX(float f) {
        this.z = f;
    }

    public final void setDigitalTimeMinuteRightX(float f) {
        this.A = f;
    }

    public final void setDigitalTimeMinuteTopY(float f) {
        this.B = f;
    }

    public final void setDigitalTimeSymbolLeftX(float f) {
        this.C = f;
    }

    public final void setDigitalTimeSymbolTopY(float f) {
        this.D = f;
    }

    public final void setDigitalValueColor(int i) {
        this.M = i;
    }

    public final void setDigitalWeekLeftX(float f) {
        this.K = f;
    }

    public final void setDigitalWeekTopY(float f) {
        this.L = f;
    }

    public final void setFileFormat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.W = str;
    }

    public final void setIgnoreBlack(int i) {
        this.b0 = i;
    }

    public final void setImageFormat(int i) {
        this.X = i;
    }

    public final void setMCurrentPhotoPath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCurrentPhotoPath = str;
    }

    public final void setRound(boolean z) {
        this.N = z;
    }

    public final void setRoundCornerRadius(float f) {
        this.O = f;
    }

    public final void setScreenHeight(int i) {
        this.Q = i;
    }

    public final void setScreenPreviewHeight(int i) {
        this.S = i;
    }

    public final void setScreenPreviewWidth(int i) {
        this.R = i;
    }

    public final void setScreenReservedBoundary(int i) {
        this.T = i;
    }

    public final void setScreenWidth(int i) {
        this.P = i;
    }

    public final void setY_CENTER(int i) {
        this.Z = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0192  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void x(java.lang.String r16, float r17, float r18, android.graphics.Canvas r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundSMA.x(java.lang.String, float, float, android.graphics.Canvas, boolean):void");
    }

    public final Pair<Bitmap, Float> y(String str, String str2, String str3, float f, int i, Canvas canvas, float f2, boolean z) {
        int i2;
        int i3;
        Bitmap bitmap = ImageUtils.getBitmap(getResources().getAssets().open(str + str2 + "/symbol.png"));
        AssetManager assets = getResources().getAssets();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append('/');
        int i4 = 0;
        sb.append(str3.charAt(0));
        sb.append(".png");
        Bitmap bitmap2 = ImageUtils.getBitmap(assets.open(sb.toString()));
        float f3 = 6;
        float f4 = f + i + f3;
        int i5 = 2;
        float height = ((bitmap2.getHeight() - bitmap.getHeight()) / 2) + f4;
        if (StringsKt__StringsKt.contains$default((CharSequence) str2, (CharSequence) this.h0, false, 2, (Object) null)) {
            this.x = f2;
            this.y = f4;
            this.C = (bitmap2.getWidth() * 2) + f2;
            this.D = height;
            float width = (bitmap2.getWidth() * 2) + f2 + bitmap.getWidth();
            this.z = width;
            this.B = f4;
            this.A = (width + (bitmap2.getWidth() * 2)) - f3;
        } else if (StringsKt__StringsKt.contains$default((CharSequence) str2, (CharSequence) this.g0, false, 2, (Object) null)) {
            this.E = f2;
            this.F = f4;
            this.I = (bitmap2.getWidth() * 2) + f2;
            this.J = height;
            this.G = (bitmap2.getWidth() * 2) + f2 + bitmap.getWidth();
            this.H = f4;
        }
        if (this.T == 0) {
            int length = str3.length();
            int i6 = 0;
            int i7 = 0;
            while (i4 < length) {
                if (i4 == i5) {
                    canvas.drawBitmap(bitmap, (bitmap2.getWidth() * i6) + f2, height, (Paint) null);
                    i3 = length;
                    i7 = bitmap.getWidth();
                } else {
                    if (z) {
                        i3 = length;
                        canvas.drawBitmap(ImageUtils.getBitmap(getResources().getAssets().open(str + str2 + '/' + str3.charAt(i4) + ".png")), (bitmap2.getWidth() * i6) + f2 + i7, f4, (Paint) null);
                    } else {
                        i3 = length;
                    }
                    i6++;
                }
                i4++;
                i5 = 2;
                length = i3;
            }
        } else if (z) {
            int length2 = str3.length();
            int i8 = 0;
            int i9 = 0;
            while (i4 < length2) {
                if (i4 == 2) {
                    canvas.drawBitmap(bitmap, (bitmap2.getWidth() * i8) + f2, height, (Paint) null);
                    i9 = bitmap.getWidth();
                    i2 = length2;
                } else {
                    AssetManager assets2 = getResources().getAssets();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(str2);
                    sb2.append('/');
                    sb2.append(str3.charAt(i4));
                    sb2.append('.');
                    i2 = length2;
                    sb2.append(this.W);
                    canvas.drawBitmap(ImageUtils.getBitmap(assets2.open(sb2.toString())), (bitmap2.getWidth() * i8) + f2 + i9, f4, (Paint) null);
                    i8++;
                }
                i4++;
                length2 = i2;
            }
        }
        return new Pair<>(bitmap2, Float.valueOf(f4));
    }

    public final byte[] z(Bitmap bitmap) {
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        byte[] array = allocate.array();
        String str = this.W;
        Intrinsics.checkNotNullExpressionValue(array, "array");
        return defaultConversion(str, array, bitmap.getWidth(), 16, 0, false);
    }
}
