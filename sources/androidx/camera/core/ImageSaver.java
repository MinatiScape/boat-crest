package androidx.camera.core;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public final class ImageSaver implements Runnable {
    public final ImageProxy h;
    public final int i;
    @NonNull
    public final ImageCapture.OutputFileOptions j;
    public final Executor k;
    public final OnImageSavedCallback l;
    public final Executor m;

    /* loaded from: classes.dex */
    public interface OnImageSavedCallback {
        void onError(@NonNull SaveError saveError, @NonNull String str, @Nullable Throwable th);

        void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults);
    }

    /* loaded from: classes.dex */
    public enum SaveError {
        FILE_IO_FAILED,
        ENCODE_FAILED,
        CROP_FAILED,
        UNKNOWN
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f645a;

        static {
            int[] iArr = new int[ImageUtil.CodecFailedException.FailureType.values().length];
            f645a = iArr;
            try {
                iArr[ImageUtil.CodecFailedException.FailureType.ENCODE_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f645a[ImageUtil.CodecFailedException.FailureType.DECODE_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f645a[ImageUtil.CodecFailedException.FailureType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ImageSaver(ImageProxy imageProxy, @NonNull ImageCapture.OutputFileOptions outputFileOptions, int i, Executor executor, Executor executor2, OnImageSavedCallback onImageSavedCallback) {
        this.h = imageProxy;
        this.j = outputFileOptions;
        this.i = i;
        this.l = onImageSavedCallback;
        this.k = executor;
        this.m = executor2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(SaveError saveError, String str, Throwable th) {
        this.l.onError(saveError, str, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(Uri uri) {
        this.l.onImageSaved(new ImageCapture.OutputFileResults(uri));
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x009e  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void l(@androidx.annotation.NonNull java.io.File r6) {
        /*
            r5 = this;
            androidx.core.util.Preconditions.checkNotNull(r6)
            r0 = 0
            boolean r1 = r5.h()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L54
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.j     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.content.ContentValues r1 = r1.b()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L1e
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            androidx.camera.core.ImageCapture$OutputFileOptions r2 = r5.j     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.content.ContentValues r2 = r2.b()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            goto L23
        L1e:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            r1.<init>()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
        L23:
            r2 = 1
            r5.p(r1, r2)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            androidx.camera.core.ImageCapture$OutputFileOptions r2 = r5.j     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.content.ContentResolver r2 = r2.a()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            androidx.camera.core.ImageCapture$OutputFileOptions r3 = r5.j     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.net.Uri r3 = r3.e()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.net.Uri r1 = r2.insert(r3, r1)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 != 0) goto L3f
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            java.lang.String r3 = "Failed to insert URI."
            goto L95
        L3f:
            boolean r2 = r5.f(r6, r1)     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            if (r2 != 0) goto L4a
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            java.lang.String r3 = "Failed to save to URI."
            goto L4c
        L4a:
            r2 = r0
            r3 = r2
        L4c:
            r5.q(r1)     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            goto L95
        L50:
            r0 = move-exception
            goto L91
        L52:
            r0 = move-exception
            goto L91
        L54:
            boolean r1 = r5.i()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L64
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.j     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            java.io.OutputStream r1 = r1.d()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            r5.e(r6, r1)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            goto L85
        L64:
            boolean r1 = r5.g()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L85
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.j     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            java.io.File r1 = r1.c()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r2 == 0) goto L79
            r1.delete()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
        L79:
            boolean r1 = r6.renameTo(r1)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 != 0) goto L85
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            java.lang.String r3 = "Failed to rename file."
            r1 = r0
            goto L95
        L85:
            r1 = r0
            r2 = r1
            r3 = r2
            goto L95
        L89:
            r0 = move-exception
            goto La2
        L8b:
            r1 = move-exception
            goto L8e
        L8d:
            r1 = move-exception
        L8e:
            r4 = r1
            r1 = r0
            r0 = r4
        L91:
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.Throwable -> L89
            java.lang.String r3 = "Failed to write destination file."
        L95:
            r6.delete()
            if (r2 == 0) goto L9e
            r5.m(r2, r3, r0)
            goto La1
        L9e:
            r5.n(r1)
        La1:
            return
        La2:
            r6.delete()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageSaver.l(java.io.File):void");
    }

    public final void e(@NonNull File file, @NonNull OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    outputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean f(@NonNull File file, @NonNull Uri uri) throws IOException {
        OutputStream openOutputStream = this.j.a().openOutputStream(uri);
        if (openOutputStream == null) {
            if (openOutputStream != null) {
                openOutputStream.close();
            }
            return false;
        }
        try {
            e(file, openOutputStream);
            openOutputStream.close();
            return true;
        } catch (Throwable th) {
            try {
                openOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean g() {
        return this.j.c() != null;
    }

    public final boolean h() {
        return (this.j.e() == null || this.j.a() == null || this.j.b() == null) ? false : true;
    }

    public final boolean i() {
        return this.j.d() != null;
    }

    public final void m(final SaveError saveError, final String str, @Nullable final Throwable th) {
        try {
            this.k.execute(new Runnable() { // from class: androidx.camera.core.i1
                @Override // java.lang.Runnable
                public final void run() {
                    ImageSaver.this.j(saveError, str, th);
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.e("ImageSaver", "Application executor rejected executing OnImageSavedCallback.onError callback. Skipping.");
        }
    }

    public final void n(@Nullable final Uri uri) {
        try {
            this.k.execute(new Runnable() { // from class: androidx.camera.core.h1
                @Override // java.lang.Runnable
                public final void run() {
                    ImageSaver.this.k(uri);
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.e("ImageSaver", "Application executor rejected executing OnImageSavedCallback.onImageSaved callback. Skipping.");
        }
    }

    @Nullable
    public final File o() {
        File createTempFile;
        SaveError saveError;
        String str;
        ImageUtil.CodecFailedException codecFailedException;
        try {
            if (g()) {
                createTempFile = new File(this.j.c().getParent(), "CameraX" + UUID.randomUUID().toString() + ".tmp");
            } else {
                createTempFile = File.createTempFile("CameraX", ".tmp");
            }
            try {
                ImageProxy imageProxy = this.h;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                    fileOutputStream.write(ImageUtil.imageToJpegByteArray(this.h));
                    Exif createFromFile = Exif.createFromFile(createTempFile);
                    createFromFile.attachTimestamp();
                    if (new ExifRotationAvailability().shouldUseExifOrientation(this.h)) {
                        ByteBuffer buffer = this.h.getPlanes()[0].getBuffer();
                        buffer.rewind();
                        byte[] bArr = new byte[buffer.capacity()];
                        buffer.get(bArr);
                        createFromFile.setOrientation(Exif.createFromInputStream(new ByteArrayInputStream(bArr)).getOrientation());
                    } else {
                        createFromFile.rotate(this.i);
                    }
                    ImageCapture.Metadata metadata = this.j.getMetadata();
                    if (metadata.isReversedHorizontal()) {
                        createFromFile.flipHorizontally();
                    }
                    if (metadata.isReversedVertical()) {
                        createFromFile.flipVertically();
                    }
                    if (metadata.getLocation() != null) {
                        createFromFile.attachLocation(this.j.getMetadata().getLocation());
                    }
                    createFromFile.save();
                    fileOutputStream.close();
                    if (imageProxy != null) {
                        imageProxy.close();
                    }
                    codecFailedException = null;
                    saveError = null;
                    str = null;
                } catch (Throwable th) {
                    if (imageProxy != null) {
                        try {
                            imageProxy.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (ImageUtil.CodecFailedException e) {
                int i = a.f645a[e.getFailureType().ordinal()];
                if (i == 1) {
                    saveError = SaveError.ENCODE_FAILED;
                    str = "Failed to encode mImage";
                    codecFailedException = e;
                } else if (i != 2) {
                    saveError = SaveError.UNKNOWN;
                    str = "Failed to transcode mImage";
                    codecFailedException = e;
                } else {
                    saveError = SaveError.CROP_FAILED;
                    str = "Failed to crop mImage";
                    codecFailedException = e;
                }
            } catch (IOException e2) {
                e = e2;
                saveError = SaveError.FILE_IO_FAILED;
                str = "Failed to write temp file";
                codecFailedException = e;
            } catch (IllegalArgumentException e3) {
                e = e3;
                saveError = SaveError.FILE_IO_FAILED;
                str = "Failed to write temp file";
                codecFailedException = e;
            }
            if (saveError != null) {
                m(saveError, str, codecFailedException);
                createTempFile.delete();
                return null;
            }
            return createTempFile;
        } catch (IOException e4) {
            m(SaveError.FILE_IO_FAILED, "Failed to create temp file", e4);
            return null;
        }
    }

    public final void p(@NonNull ContentValues contentValues, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("is_pending", Integer.valueOf(i));
        }
    }

    public final void q(@NonNull Uri uri) {
        if (Build.VERSION.SDK_INT >= 29) {
            ContentValues contentValues = new ContentValues();
            p(contentValues, 0);
            this.j.a().update(uri, contentValues, null, null);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        final File o = o();
        if (o != null) {
            this.m.execute(new Runnable() { // from class: androidx.camera.core.j1
                @Override // java.lang.Runnable
                public final void run() {
                    ImageSaver.this.l(o);
                }
            });
        }
    }
}
