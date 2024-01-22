package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.coveiot.utils.utility.LogHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.ResponseBody;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes5.dex */
public final class FileUtils {
    @NotNull
    public static final FileUtils INSTANCE = new FileUtils();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f5421a = "FileUtils";

    public static /* synthetic */ void unZip$default(FileUtils fileUtils, File file, File file2, File file3, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            file3 = null;
        }
        fileUtils.unZip(file, file2, file3);
    }

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
                            Log.e(f5421a, "Exception on closing MD5 input stream", e2);
                        }
                        throw th;
                    }
                }
                String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ENGLISH, "%32s", Arrays.copyOf(new Object[]{bigInteger}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                String replace$default = kotlin.text.m.replace$default(format, ' ', '0', false, 4, (Object) null);
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    Log.e(f5421a, "Exception on closing MD5 input stream", e3);
                }
                return replace$default;
            } catch (FileNotFoundException e4) {
                Log.e(f5421a, "Exception while getting FileInputStream", e4);
                return null;
            }
        } catch (NoSuchAlgorithmException e5) {
            Log.e(f5421a, "Exception while getting digest", e5);
            return null;
        }
    }

    public final void b(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        int read = inputStream.read(bArr);
        while (read > 0) {
            outputStream.write(bArr, 0, read);
            read = inputStream.read(bArr);
        }
    }

    public final boolean c(File file) {
        if (file != null) {
            return file.delete();
        }
        return false;
    }

    @Nullable
    public final File copyAssets(@NotNull String filename, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(context, "context");
        AssetManager assets = context.getAssets();
        try {
            assets.list("");
        } catch (IOException unused) {
        }
        try {
            InputStream open = assets.open(filename);
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "", filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            b(open, fileOutputStream);
            open.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            LogHelper.e(HeaderParameterNames.AUTHENTICATION_TAG, "Failed to copy asset file: " + filename, e);
            return null;
        }
    }

    public final String d(Context context, String str) {
        File file = new File(getRootPath(context), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "agpsFile.absolutePath");
        return absolutePath;
    }

    public final void deleteRecursive(@Nullable File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        Intrinsics.checkNotNullExpressionValue(listFiles, "fileOrDirectory.listFiles()");
                        for (File file2 : listFiles) {
                            deleteRecursive(file2);
                        }
                    }
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    public final File getFirmwareFile(@NotNull File directory, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        File[] files = directory.listFiles();
        Log.d("Files", "Size: " + files.length);
        Intrinsics.checkNotNullExpressionValue(files, "files");
        int length = files.length;
        for (int i = 0; i < length; i++) {
            File file = files[i];
            String name = file.getName();
            Intrinsics.checkNotNullExpressionValue(name, "file.name");
            if (StringsKt__StringsKt.contains$default((CharSequence) name, (CharSequence) fileName, false, 2, (Object) null)) {
                return file;
            }
            String str = f5421a;
            Log.d(str, "FileName:" + file.getName());
        }
        return null;
    }

    @Nullable
    public final File getFirmwareFileFromAssets(@NotNull Context context, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return copyAssets(fileName, context);
    }

    @Nullable
    public final String getRootPath(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File filesDir = context.getFilesDir();
        Intrinsics.checkNotNull(filesDir);
        return filesDir.getAbsolutePath();
    }

    public final boolean isAuthenticFile(@NotNull String md5, @Nullable File file) {
        Intrinsics.checkNotNullParameter(md5, "md5");
        if (!TextUtils.isEmpty(md5) && file != null) {
            String a2 = a(file);
            if (a2 == null) {
                Log.e(f5421a, "calculatedDigest null");
                return false;
            }
            String str = f5421a;
            Log.v(str, "Calculated digest: " + a2);
            Log.v(str, "Provided digest: " + md5);
            return kotlin.text.m.equals(a2, md5, true);
        }
        Log.e(f5421a, "MD5 string empty or updateFile null");
        return false;
    }

    public final void saveFile(@NotNull Context context, @NotNull String fileName, @NotNull ResponseBody responseBody) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        File file = new File(d(context, fileName));
        byte[] bArr = new byte[8192];
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        InputStream byteStream = responseBody.byteStream();
        while (true) {
            int read = byteStream.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                byteStream.close();
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x008f, code lost:
        throw new java.io.FileNotFoundException("Failed to ensure directory: " + r13.getAbsolutePath());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void unZip(@org.jetbrains.annotations.NotNull java.io.File r19, @org.jetbrains.annotations.NotNull java.io.File r20, @org.jetbrains.annotations.Nullable java.io.File r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.utils.FileUtils.unZip(java.io.File, java.io.File, java.io.File):void");
    }
}
