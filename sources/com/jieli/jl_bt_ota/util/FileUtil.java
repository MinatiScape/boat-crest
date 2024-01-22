package com.jieli.jl_bt_ota.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class FileUtil {
    public static final int FILE_TYPE_PIC = 1;
    public static final int FILE_TYPE_UNKNOWN = 0;
    public static final int FILE_TYPE_VIDEO = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f12403a = "FileUtil";

    private static String a(Context context) {
        File externalFilesDir;
        String path = Environment.getExternalStorageDirectory().getPath();
        return (Build.VERSION.SDK_INT < 29 || context == null || (externalFilesDir = context.getExternalFilesDir(null)) == null) ? path : externalFilesDir.getPath();
    }

    public static boolean bitmapToFile(Bitmap bitmap, String str, int i) {
        FileOutputStream fileOutputStream;
        if (bitmap != null && !TextUtils.isEmpty(str)) {
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(str);
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, i, fileOutputStream);
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return true;
                }
            } catch (IOException e3) {
                e = e3;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return false;
    }

    public static boolean bytesToFile(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        if (bArr == null || TextUtils.isEmpty(str)) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(str);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            fileOutputStream.write(bArr);
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return true;
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                    return false;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return false;
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean checkFileExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x006b A[Catch: IOException -> 0x0088, TRY_ENTER, TRY_LEAVE, TryCatch #7 {IOException -> 0x0088, blocks: (B:24:0x0044, B:48:0x006b, B:60:0x0084), top: B:82:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0084 A[Catch: IOException -> 0x0088, TRY_ENTER, TRY_LEAVE, TryCatch #7 {IOException -> 0x0088, blocks: (B:24:0x0044, B:48:0x006b, B:60:0x0084), top: B:82:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v15, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x0089 -> B:81:0x00a6). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyFromAssetsToSdcard(android.content.Context r3, boolean r4, java.lang.String r5, java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto La6
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto La6
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            if (r4 != 0) goto L1b
            if (r4 != 0) goto La6
            boolean r4 = r0.exists()
            if (r4 != 0) goto La6
        L1b:
            r4 = 0
            android.content.res.Resources r3 = r3.getResources()     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56 java.io.FileNotFoundException -> L6f
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56 java.io.FileNotFoundException -> L6f
            java.io.InputStream r3 = r3.open(r5)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56 java.io.FileNotFoundException -> L6f
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L51
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f java.io.FileNotFoundException -> L51
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r4]     // Catch: java.io.IOException -> L49 java.io.FileNotFoundException -> L4b java.lang.Throwable -> L8d
        L31:
            r0 = 0
            int r1 = r3.read(r6, r0, r4)     // Catch: java.io.IOException -> L49 java.io.FileNotFoundException -> L4b java.lang.Throwable -> L8d
            if (r1 < 0) goto L3c
            r5.write(r6, r0, r1)     // Catch: java.io.IOException -> L49 java.io.FileNotFoundException -> L4b java.lang.Throwable -> L8d
            goto L31
        L3c:
            r5.close()     // Catch: java.io.IOException -> L40
            goto L44
        L40:
            r4 = move-exception
            r4.printStackTrace()
        L44:
            r3.close()     // Catch: java.io.IOException -> L88
            goto La6
        L49:
            r4 = move-exception
            goto L5c
        L4b:
            r4 = move-exception
            goto L75
        L4d:
            r5 = move-exception
            goto L91
        L4f:
            r5 = move-exception
            goto L59
        L51:
            r5 = move-exception
            goto L72
        L53:
            r5 = move-exception
            r3 = r4
            goto L91
        L56:
            r3 = move-exception
            r5 = r3
            r3 = r4
        L59:
            r2 = r5
            r5 = r4
            r4 = r2
        L5c:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L8d
            if (r5 == 0) goto L69
            r5.close()     // Catch: java.io.IOException -> L65
            goto L69
        L65:
            r4 = move-exception
            r4.printStackTrace()
        L69:
            if (r3 == 0) goto La6
            r3.close()     // Catch: java.io.IOException -> L88
            goto La6
        L6f:
            r3 = move-exception
            r5 = r3
            r3 = r4
        L72:
            r2 = r5
            r5 = r4
            r4 = r2
        L75:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L8d
            if (r5 == 0) goto L82
            r5.close()     // Catch: java.io.IOException -> L7e
            goto L82
        L7e:
            r4 = move-exception
            r4.printStackTrace()
        L82:
            if (r3 == 0) goto La6
            r3.close()     // Catch: java.io.IOException -> L88
            goto La6
        L88:
            r3 = move-exception
            r3.printStackTrace()
            goto La6
        L8d:
            r4 = move-exception
            r2 = r5
            r5 = r4
            r4 = r2
        L91:
            if (r4 == 0) goto L9b
            r4.close()     // Catch: java.io.IOException -> L97
            goto L9b
        L97:
            r4 = move-exception
            r4.printStackTrace()
        L9b:
            if (r3 == 0) goto La5
            r3.close()     // Catch: java.io.IOException -> La1
            goto La5
        La1:
            r3 = move-exception
            r3.printStackTrace()
        La5:
            throw r5
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.util.FileUtil.copyFromAssetsToSdcard(android.content.Context, boolean, java.lang.String, java.lang.String):void");
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            if (file.delete()) {
                JL_Log.i(f12403a, "delete file success!");
            }
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    deleteFile(file2);
                }
                if (file.delete()) {
                    JL_Log.i(f12403a, "delete empty file success!");
                }
            } else if (file.delete()) {
                JL_Log.i(f12403a, "delete empty file success!");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.io.FileInputStream] */
    public static byte[] getBytes(String str) {
        Throwable th;
        IOException e;
        ByteArrayOutputStream byteArrayOutputStream;
        FileNotFoundException e2;
        byte[] bArr = null;
        r0 = 0;
        bArr = null;
        bArr = null;
        ?? r0 = 0;
        if (str == 0 || str.isEmpty()) {
            return null;
        }
        File file = new File((String) str);
        try {
            try {
                try {
                    str = new FileInputStream(file);
                } catch (Throwable th2) {
                    th = th2;
                    r0 = file;
                }
            } catch (FileNotFoundException e3) {
                e2 = e3;
                str = 0;
                byteArrayOutputStream = null;
            } catch (IOException e4) {
                e = e4;
                str = 0;
                byteArrayOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                str = 0;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = str.read(bArr2);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr2, 0, read);
                        } else {
                            try {
                                break;
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                    }
                    byteArrayOutputStream.close();
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        str.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                } catch (FileNotFoundException e7) {
                    e2 = e7;
                    e2.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                        bArr = byteArrayOutputStream.toByteArray();
                    }
                    if (str != 0) {
                        str.close();
                    }
                    return bArr;
                } catch (IOException e9) {
                    e = e9;
                    e.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                        bArr = byteArrayOutputStream.toByteArray();
                    }
                    if (str != 0) {
                        str.close();
                    }
                    return bArr;
                }
            } catch (FileNotFoundException e11) {
                e2 = e11;
                byteArrayOutputStream = null;
            } catch (IOException e12) {
                e = e12;
                byteArrayOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                if (r0 != 0) {
                    try {
                        r0.close();
                    } catch (IOException e13) {
                        e13.printStackTrace();
                    }
                    r0.toByteArray();
                }
                if (str != 0) {
                    try {
                        str.close();
                    } catch (IOException e14) {
                        e14.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e15) {
            e15.printStackTrace();
        }
        return bArr;
    }

    public static byte[] getFromRaw(Context context, int i) {
        byte[] bArr;
        IOException e;
        InputStream openRawResource;
        InputStream inputStream = null;
        r0 = null;
        byte[] bArr2 = null;
        inputStream = null;
        try {
            try {
                openRawResource = context.getResources().openRawResource(i);
            } catch (Exception e2) {
                e = e2;
                bArr = null;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            Runtime runtime = Runtime.getRuntime();
            int i2 = 512000;
            if (runtime != null && runtime.freeMemory() < 512000) {
                i2 = (int) runtime.freeMemory();
            }
            byte[] bArr3 = new byte[i2];
            byte[] bArr4 = new byte[1024];
            int i3 = 0;
            while (true) {
                int read = openRawResource.read(bArr4, 0, 1024);
                if (read < 0) {
                    break;
                }
                int i4 = i3 + read;
                if (i4 <= i2) {
                    System.arraycopy(bArr4, 0, bArr3, i3, read);
                    i3 = i4;
                }
            }
            if (i3 > 0) {
                bArr2 = new byte[i3];
                System.arraycopy(bArr3, 0, bArr2, 0, i3);
            }
            try {
                openRawResource.close();
                return bArr2;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                return bArr2;
            }
        } catch (Exception e4) {
            e = e4;
            byte[] bArr5 = bArr2;
            inputStream = openRawResource;
            bArr = bArr5;
            e.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    bArr2 = bArr;
                    e = e5;
                    e.printStackTrace();
                    return bArr2;
                }
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            inputStream = openRawResource;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static int judgeFileType(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str.endsWith(".png") || str.endsWith(".PNG") || str.endsWith(".JPEG") || str.endsWith(".jpeg") || str.endsWith(".jpg") || str.endsWith(".JPG")) {
            return 1;
        }
        return (str.endsWith(".mov") || str.endsWith(".MOV") || str.endsWith(".mp4") || str.endsWith(".MP4") || str.endsWith(".avi") || str.endsWith(".AVI")) ? 2 : 0;
    }

    public static String splicingFilePath(Context context, String str, String str2, String str3, String str4) {
        String[] split;
        String a2 = a(context);
        if (!TextUtils.isEmpty(str)) {
            String str5 = File.separator;
            if (str.contains(str5)) {
                for (String str6 : str.split(str5)) {
                    if (!TextUtils.isEmpty(str6)) {
                        a2 = a2 + File.separator + str6;
                        File file = new File(a2);
                        if (!file.exists() && file.mkdir()) {
                            JL_Log.w(f12403a, "create root dir success! path : " + a2);
                        }
                    }
                }
            } else {
                a2 = a2 + str5 + str;
                File file2 = new File(a2);
                if (!file2.exists() && file2.mkdir()) {
                    JL_Log.w(f12403a, "create root dir success! path : " + a2);
                }
            }
            if (TextUtils.isEmpty(str2)) {
                return a2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(a2);
            String str7 = File.separator;
            sb.append(str7);
            sb.append(str2);
            String sb2 = sb.toString();
            File file3 = new File(sb2);
            if (!file3.exists() && file3.mkdir()) {
                JL_Log.w(f12403a, "create one dir success!");
            }
            if (TextUtils.isEmpty(str3)) {
                return sb2;
            }
            String str8 = sb2 + str7 + str3;
            File file4 = new File(str8);
            if (!file4.exists() && file4.mkdir()) {
                JL_Log.w(f12403a, "create two dir success!");
            }
            if (TextUtils.isEmpty(str4)) {
                return str8;
            }
            a2 = str8 + str7 + str4;
            File file5 = new File(a2);
            if (!file5.exists() && file5.mkdir()) {
                JL_Log.w(f12403a, "create three sub dir success!");
            }
        }
        return a2;
    }
}
