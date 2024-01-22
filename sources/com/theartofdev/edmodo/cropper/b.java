package com.theartofdev.edmodo.cropper;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.realsil.sdk.dfu.DfuException;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
/* loaded from: classes12.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final Rect f13726a = new Rect();
    public static final RectF b = new RectF();
    public static final RectF c = new RectF();
    public static final float[] d = new float[6];
    public static final float[] e = new float[6];
    public static int f;
    public static Pair<String, WeakReference<Bitmap>> g;

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final Bitmap f13727a;
        public final int b;

        public a(Bitmap bitmap, int i) {
            this.f13727a = bitmap;
            this.b = i;
        }
    }

    /* renamed from: com.theartofdev.edmodo.cropper.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static final class C0734b {

        /* renamed from: a  reason: collision with root package name */
        public final Bitmap f13728a;
        public final int b;

        public C0734b(Bitmap bitmap, int i) {
            this.f13728a = bitmap;
            this.b = i;
        }
    }

    public static C0734b A(Bitmap bitmap, Context context, Uri uri) {
        ExifInterface exifInterface = null;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                ExifInterface exifInterface2 = new ExifInterface(openInputStream);
                try {
                    openInputStream.close();
                } catch (Exception unused) {
                }
                exifInterface = exifInterface2;
            }
        } catch (Exception unused2) {
        }
        return exifInterface != null ? B(bitmap, exifInterface) : new C0734b(bitmap, 0);
    }

    public static C0734b B(Bitmap bitmap, ExifInterface exifInterface) {
        int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        return new C0734b(bitmap, attributeInt != 3 ? attributeInt != 6 ? attributeInt != 8 ? 0 : DfuException.ERROR_READ_DEVICE_INFO_ERROR : 90 : 180);
    }

    public static void C(Context context, Bitmap bitmap, Uri uri, Bitmap.CompressFormat compressFormat, int i) throws FileNotFoundException {
        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(compressFormat, i, outputStream);
        } finally {
            c(outputStream);
        }
    }

    public static Uri D(Context context, Bitmap bitmap, Uri uri) {
        boolean z = true;
        try {
            if (uri == null) {
                uri = Uri.fromFile(File.createTempFile("aic_state_store_temp", ".jpg", context.getCacheDir()));
            } else if (new File(uri.getPath()).exists()) {
                z = false;
            }
            if (z) {
                C(context, bitmap, uri, Bitmap.CompressFormat.JPEG, 95);
            }
            return uri;
        } catch (Exception e2) {
            Log.w("AIC", "Failed to write bitmap to temp file for image-cropper save instance state", e2);
            return null;
        }
    }

    public static int a(int i, int i2) {
        if (f == 0) {
            f = o();
        }
        int i3 = 1;
        if (f > 0) {
            while (true) {
                int i4 = i2 / i3;
                int i5 = f;
                if (i4 <= i5 && i / i3 <= i5) {
                    break;
                }
                i3 *= 2;
            }
        }
        return i3;
    }

    public static int b(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i4 || i > i3) {
            while ((i2 / 2) / i5 > i4 && (i / 2) / i5 > i3) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static void c(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static a d(Context context, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3) {
        int i8 = 1;
        do {
            try {
                return e(context, uri, fArr, i, i2, i3, z, i4, i5, i6, i7, z2, z3, i8);
            } catch (OutOfMemoryError e2) {
                i8 *= 2;
                if (i8 > 16) {
                    throw new RuntimeException("Failed to handle OOM by sampling (" + i8 + "): " + uri + "\r\n" + e2.getMessage(), e2);
                }
            }
        } while (i8 > 16);
        throw new RuntimeException("Failed to handle OOM by sampling (" + i8 + "): " + uri + "\r\n" + e2.getMessage(), e2);
    }

    public static a e(Context context, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3, int i8) {
        Bitmap bitmap;
        Rect s = s(fArr, i2, i3, z, i4, i5);
        int width = i6 > 0 ? i6 : s.width();
        int height = i7 > 0 ? i7 : s.height();
        Bitmap bitmap2 = null;
        int i9 = 1;
        try {
            a m = m(context, uri, s, width, height, i8);
            bitmap2 = m.f13727a;
            i9 = m.b;
        } catch (Exception unused) {
        }
        if (bitmap2 != null) {
            try {
                Bitmap z4 = z(bitmap2, i, z2, z3);
                try {
                    if (i % 90 != 0) {
                        z4 = i(z4, fArr, s, i, z, i4, i5);
                    }
                    return new a(z4, i9);
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    bitmap2 = bitmap;
                    if (bitmap2 != null) {
                        bitmap2.recycle();
                    }
                    throw e;
                }
            } catch (OutOfMemoryError e3) {
                e = e3;
            }
        } else {
            return f(context, uri, fArr, i, z, i4, i5, i8, s, width, height, z2, z3);
        }
    }

    public static a f(Context context, Uri uri, float[] fArr, int i, boolean z, int i2, int i3, int i4, Rect rect, int i5, int i6, boolean z2, boolean z3) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int b2 = b(rect.width(), rect.height(), i5, i6) * i4;
            options.inSampleSize = b2;
            Bitmap j = j(context.getContentResolver(), uri, options);
            if (j != null) {
                try {
                    int length = fArr.length;
                    float[] fArr2 = new float[length];
                    System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                    for (int i7 = 0; i7 < length; i7++) {
                        fArr2[i7] = fArr2[i7] / options.inSampleSize;
                    }
                    bitmap = h(j, fArr2, i, z, i2, i3, 1.0f, z2, z3);
                    if (bitmap != j) {
                        j.recycle();
                    }
                } catch (Throwable th) {
                    j.recycle();
                    throw th;
                }
            }
            return new a(bitmap, b2);
        } catch (Exception e2) {
            throw new RuntimeException("Failed to load sampled bitmap: " + uri + "\r\n" + e2.getMessage(), e2);
        } catch (OutOfMemoryError e3) {
            if (0 != 0) {
                bitmap.recycle();
            }
            throw e3;
        }
    }

    public static a g(Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, boolean z2, boolean z3) {
        int i4 = 1;
        do {
            try {
                return new a(h(bitmap, fArr, i, z, i2, i3, 1.0f / i4, z2, z3), i4);
            } catch (OutOfMemoryError e2) {
                i4 *= 2;
                if (i4 > 8) {
                    throw e2;
                }
            }
        } while (i4 > 8);
        throw e2;
    }

    public static Bitmap h(Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, float f2, boolean z2, boolean z3) {
        float f3 = f2;
        Rect s = s(fArr, bitmap.getWidth(), bitmap.getHeight(), z, i2, i3);
        Matrix matrix = new Matrix();
        matrix.setRotate(i, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        float f4 = z2 ? -f3 : f3;
        if (z3) {
            f3 = -f3;
        }
        matrix.postScale(f4, f3);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, s.left, s.top, s.width(), s.height(), matrix, true);
        if (createBitmap == bitmap) {
            createBitmap = bitmap.copy(bitmap.getConfig(), false);
        }
        return i % 90 != 0 ? i(createBitmap, fArr, s, i, z, i2, i3) : createBitmap;
    }

    public static Bitmap i(Bitmap bitmap, float[] fArr, Rect rect, int i, boolean z, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        if (i % 90 != 0) {
            double radians = Math.toRadians(i);
            int i7 = (i < 90 || (i > 180 && i < 270)) ? rect.left : rect.right;
            int i8 = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= fArr.length) {
                    i4 = 0;
                    i5 = 0;
                    i6 = 0;
                    break;
                } else if (fArr[i9] >= i7 - 1 && fArr[i9] <= i7 + 1) {
                    int i10 = i9 + 1;
                    i8 = (int) Math.abs(Math.sin(radians) * (rect.bottom - fArr[i10]));
                    i5 = (int) Math.abs(Math.cos(radians) * (fArr[i10] - rect.top));
                    i6 = (int) Math.abs((fArr[i10] - rect.top) / Math.sin(radians));
                    i4 = (int) Math.abs((rect.bottom - fArr[i10]) / Math.cos(radians));
                    break;
                } else {
                    i9 += 2;
                }
            }
            rect.set(i8, i5, i6 + i8, i4 + i5);
            if (z) {
                n(rect, i2, i3);
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
            if (bitmap != createBitmap) {
                bitmap.recycle();
            }
            return createBitmap;
        }
        return bitmap;
    }

    public static Bitmap j(ContentResolver contentResolver, Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
        do {
            InputStream inputStream = null;
            try {
                try {
                    inputStream = contentResolver.openInputStream(uri);
                    return BitmapFactory.decodeStream(inputStream, f13726a, options);
                } catch (OutOfMemoryError unused) {
                    options.inSampleSize *= 2;
                    c(inputStream);
                    if (options.inSampleSize > 512) {
                        throw new RuntimeException("Failed to decode image: " + uri);
                    }
                }
            } finally {
                c(inputStream);
            }
        } while (options.inSampleSize > 512);
        throw new RuntimeException("Failed to decode image: " + uri);
    }

    public static BitmapFactory.Options k(ContentResolver contentResolver, Uri uri) throws FileNotFoundException {
        InputStream inputStream;
        try {
            inputStream = contentResolver.openInputStream(uri);
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, f13726a, options);
                options.inJustDecodeBounds = false;
                c(inputStream);
                return options;
            } catch (Throwable th) {
                th = th;
                c(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public static a l(Context context, Uri uri, int i, int i2) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            BitmapFactory.Options k = k(contentResolver, uri);
            int i3 = k.outWidth;
            if (i3 == -1 && k.outHeight == -1) {
                throw new RuntimeException("File is not a picture");
            }
            k.inSampleSize = Math.max(b(i3, k.outHeight, i, i2), a(k.outWidth, k.outHeight));
            return new a(j(contentResolver, uri, k), k.inSampleSize);
        } catch (Exception e2) {
            throw new RuntimeException("Failed to load sampled bitmap: " + uri + "\r\n" + e2.getMessage(), e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.theartofdev.edmodo.cropper.b.a m(android.content.Context r4, android.net.Uri r5, android.graphics.Rect r6, int r7, int r8, int r9) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            r1.<init>()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            int r2 = r6.width()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            int r3 = r6.height()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            int r7 = b(r2, r3, r7, r8)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            int r9 = r9 * r7
            r1.inSampleSize = r9     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            java.io.InputStream r4 = r4.openInputStream(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            r7 = 0
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r4, r7)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L55
        L22:
            com.theartofdev.edmodo.cropper.b$a r8 = new com.theartofdev.edmodo.cropper.b$a     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36 java.lang.OutOfMemoryError -> L38
            android.graphics.Bitmap r9 = r7.decodeRegion(r6, r1)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36 java.lang.OutOfMemoryError -> L38
            int r2 = r1.inSampleSize     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36 java.lang.OutOfMemoryError -> L38
            r8.<init>(r9, r2)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36 java.lang.OutOfMemoryError -> L38
            c(r4)
            r7.recycle()
            return r8
        L34:
            r5 = move-exception
            goto L53
        L36:
            r6 = move-exception
            goto L57
        L38:
            int r8 = r1.inSampleSize     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            int r8 = r8 * 2
            r1.inSampleSize = r8     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            r9 = 512(0x200, float:7.175E-43)
            if (r8 <= r9) goto L22
            c(r4)
            if (r7 == 0) goto L4a
            r7.recycle()
        L4a:
            com.theartofdev.edmodo.cropper.b$a r4 = new com.theartofdev.edmodo.cropper.b$a
            r5 = 1
            r4.<init>(r0, r5)
            return r4
        L51:
            r5 = move-exception
            r7 = r0
        L53:
            r0 = r4
            goto L82
        L55:
            r6 = move-exception
            r7 = r0
        L57:
            r0 = r4
            goto L5e
        L59:
            r5 = move-exception
            r7 = r0
            goto L82
        L5c:
            r6 = move-exception
            r7 = r0
        L5e:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L81
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L81
            r8.<init>()     // Catch: java.lang.Throwable -> L81
            java.lang.String r9 = "Failed to load sampled bitmap: "
            r8.append(r9)     // Catch: java.lang.Throwable -> L81
            r8.append(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = "\r\n"
            r8.append(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = r6.getMessage()     // Catch: java.lang.Throwable -> L81
            r8.append(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = r8.toString()     // Catch: java.lang.Throwable -> L81
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L81
            throw r4     // Catch: java.lang.Throwable -> L81
        L81:
            r5 = move-exception
        L82:
            c(r0)
            if (r7 == 0) goto L8a
            r7.recycle()
        L8a:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.b.m(android.content.Context, android.net.Uri, android.graphics.Rect, int, int, int):com.theartofdev.edmodo.cropper.b$a");
    }

    public static void n(Rect rect, int i, int i2) {
        if (i != i2 || rect.width() == rect.height()) {
            return;
        }
        if (rect.height() > rect.width()) {
            rect.bottom -= rect.height() - rect.width();
        } else {
            rect.right -= rect.width() - rect.height();
        }
    }

    public static int o() {
        try {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            egl10.eglInitialize(eglGetDisplay, new int[2]);
            int[] iArr = new int[1];
            egl10.eglGetConfigs(eglGetDisplay, null, 0, iArr);
            EGLConfig[] eGLConfigArr = new EGLConfig[iArr[0]];
            egl10.eglGetConfigs(eglGetDisplay, eGLConfigArr, iArr[0], iArr);
            int[] iArr2 = new int[1];
            int i = 0;
            for (int i2 = 0; i2 < iArr[0]; i2++) {
                egl10.eglGetConfigAttrib(eglGetDisplay, eGLConfigArr[i2], 12332, iArr2);
                if (i < iArr2[0]) {
                    i = iArr2[0];
                }
            }
            egl10.eglTerminate(eglGetDisplay);
            return Math.max(i, 2048);
        } catch (Exception unused) {
            return 2048;
        }
    }

    public static float p(float[] fArr) {
        return Math.max(Math.max(Math.max(fArr[1], fArr[3]), fArr[5]), fArr[7]);
    }

    public static float q(float[] fArr) {
        return (v(fArr) + u(fArr)) / 2.0f;
    }

    public static float r(float[] fArr) {
        return (p(fArr) + w(fArr)) / 2.0f;
    }

    public static Rect s(float[] fArr, int i, int i2, boolean z, int i3, int i4) {
        Rect rect = new Rect(Math.round(Math.max(0.0f, u(fArr))), Math.round(Math.max(0.0f, w(fArr))), Math.round(Math.min(i, v(fArr))), Math.round(Math.min(i2, p(fArr))));
        if (z) {
            n(rect, i3, i4);
        }
        return rect;
    }

    public static float t(float[] fArr) {
        return p(fArr) - w(fArr);
    }

    public static float u(float[] fArr) {
        return Math.min(Math.min(Math.min(fArr[0], fArr[2]), fArr[4]), fArr[6]);
    }

    public static float v(float[] fArr) {
        return Math.max(Math.max(Math.max(fArr[0], fArr[2]), fArr[4]), fArr[6]);
    }

    public static float w(float[] fArr) {
        return Math.min(Math.min(Math.min(fArr[1], fArr[3]), fArr[5]), fArr[7]);
    }

    public static float x(float[] fArr) {
        return v(fArr) - u(fArr);
    }

    public static Bitmap y(Bitmap bitmap, int i, int i2, CropImageView.RequestSizeOptions requestSizeOptions) {
        if (i > 0 && i2 > 0) {
            try {
                CropImageView.RequestSizeOptions requestSizeOptions2 = CropImageView.RequestSizeOptions.RESIZE_FIT;
                if (requestSizeOptions == requestSizeOptions2 || requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_INSIDE || requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                    Bitmap bitmap2 = null;
                    if (requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, i, i2, false);
                    } else {
                        float width = bitmap.getWidth();
                        float height = bitmap.getHeight();
                        float max = Math.max(width / i, height / i2);
                        if (max > 1.0f || requestSizeOptions == requestSizeOptions2) {
                            bitmap2 = Bitmap.createScaledBitmap(bitmap, (int) (width / max), (int) (height / max), false);
                        }
                    }
                    if (bitmap2 != null) {
                        if (bitmap2 != bitmap) {
                            bitmap.recycle();
                        }
                        return bitmap2;
                    }
                }
            } catch (Exception e2) {
                Log.w("AIC", "Failed to resize cropped image, return bitmap before resize", e2);
            }
        }
        return bitmap;
    }

    public static Bitmap z(Bitmap bitmap, int i, boolean z, boolean z2) {
        if (i > 0 || z || z2) {
            Matrix matrix = new Matrix();
            matrix.setRotate(i);
            matrix.postScale(z ? -1.0f : 1.0f, z2 ? -1.0f : 1.0f);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            if (createBitmap != bitmap) {
                bitmap.recycle();
            }
            return createBitmap;
        }
        return bitmap;
    }
}
