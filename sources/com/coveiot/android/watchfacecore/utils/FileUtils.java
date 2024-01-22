package com.coveiot.android.watchfacecore.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FileUtils {
    @NotNull
    public static final FileUtils INSTANCE = new FileUtils();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f6114a = "FileUtils";

    public final String a(File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            Intrinsics.checkNotNullExpressionValue(messageDigest, "getInstance(\"MD5\")");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            messageDigest.update(bArr, 0, read);
                        } catch (IOException e) {
                            throw new RuntimeException("Unable to process file for MD5", e);
                        }
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            Log.e(f6114a, "Exception on closing MD5 input stream", e2);
                        }
                        throw th;
                    }
                }
                String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ENGLISH, "%32s", Arrays.copyOf(new Object[]{bigInteger}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                String replace$default = m.replace$default(format, ' ', '0', false, 4, (Object) null);
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    Log.e(f6114a, "Exception on closing MD5 input stream", e3);
                }
                return replace$default;
            } catch (FileNotFoundException e4) {
                Log.e(f6114a, "Exception while getting FileInputStream", e4);
                return null;
            }
        } catch (NoSuchAlgorithmException e5) {
            Log.e(f6114a, "Exception while getting digest", e5);
            return null;
        }
    }

    @Nullable
    public final String getRootPath(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File filesDir = context.getFilesDir();
        Intrinsics.checkNotNull(filesDir);
        return filesDir.getAbsolutePath();
    }

    @NotNull
    public final String getSaveFilePath(@NotNull Context context, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        File file = new File(getRootPath(context), fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
        return absolutePath;
    }

    public final boolean isAuthenticFile(@NotNull String md5, @Nullable File file) {
        Intrinsics.checkNotNullParameter(md5, "md5");
        if (!TextUtils.isEmpty(md5) && file != null) {
            String a2 = a(file);
            if (a2 == null) {
                Log.e(f6114a, "calculatedDigest null");
                return false;
            }
            String str = f6114a;
            Log.v(str, "Calculated digest: " + a2);
            Log.v(str, "Provided digest: " + md5);
            return m.equals(a2, md5, true);
        }
        Log.e(f6114a, "MD5 string empty or updateFile null");
        return false;
    }

    @Nullable
    public final Bitmap resizeBitmap(@NotNull Bitmap bitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }
}
