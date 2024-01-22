package com.realsil.sdk.core.utility;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes12.dex */
public final class FileUtils {
    public static String a(Context context, Uri uri) {
        if (isGooglePhotosUri(uri)) {
            ZLogger.v("isGooglePhotosUri");
            return uri.getLastPathSegment();
        } else if (isOppoUri(uri)) {
            ZLogger.v("isOppoUri");
            String path = uri.getPath();
            File file = new File(path);
            if (!file.exists()) {
                return path.contains("/file_share/") ? path.replace("/file_share", "") : path;
            }
            ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file.getPath(), file.getName(), Long.valueOf(file.length())));
            return path;
        } else if (!isHuaweiUri(uri) && !isHuaweiShareUri(uri)) {
            if (isNokia(uri)) {
                ZLogger.v("isNokia");
                String path2 = uri.getPath();
                File file2 = new File(path2);
                if (!file2.exists()) {
                    return path2.contains("/shared_files/") ? path2.replace("/shared_files", "") : path2;
                }
                ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file2.getPath(), file2.getName(), Long.valueOf(file2.length())));
                return path2;
            } else if (isTencentProvider(uri)) {
                ZLogger.v("TencentProvider");
                String path3 = uri.getPath();
                File file3 = new File(path3);
                if (file3.exists()) {
                    ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file3.getPath(), file3.getName(), Long.valueOf(file3.length())));
                    return path3;
                } else if (path3.contains("/QQBrowser/")) {
                    String replace = path3.replace("/QQBrowser", "");
                    return Environment.getExternalStorageDirectory() + replace;
                } else {
                    return path3;
                }
            } else {
                String dataColumn = getDataColumn(context, uri, null, null);
                ZLogger.v("getDataColumn:" + dataColumn);
                return !TextUtils.isEmpty(dataColumn) ? dataColumn : uri.getPath();
            }
        } else {
            ZLogger.v("isHuaweiUri");
            String path4 = uri.getPath();
            File file4 = new File(path4);
            if (!file4.exists()) {
                return path4.contains("/root/") ? path4.replace("/root", "") : path4;
            }
            ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file4.getPath(), file4.getName(), Long.valueOf(file4.length())));
            return path4;
        }
    }

    public static String b(Context context, Uri uri) {
        int i;
        String authority = uri.getAuthority();
        if ("com.android.externalstorage.documents".equals(authority)) {
            i = 1;
        } else if ("com.android.providers.downloads.documents".equals(authority)) {
            i = 2;
        } else if ("com.android.providers.media.documents".equals(authority)) {
            i = 3;
        } else {
            i = "com.google.android.apps.docs.storage".equals(authority) ? 4 : 0;
        }
        String documentId = DocumentsContract.getDocumentId(uri);
        String[] split = documentId.split(":");
        ZLogger.v(String.format("isDocumentUri, documentType=0x%02X, docId=%s", Integer.valueOf(i), documentId));
        if (i == 1) {
            if (split.length <= 0) {
                return documentId;
            }
            String str = split[0];
            ZLogger.v(String.format("type=%s", str));
            if ("primary".equalsIgnoreCase(str)) {
                return Environment.getExternalStorageDirectory() + MqttTopic.TOPIC_LEVEL_SEPARATOR + split[1];
            }
            return Environment.getExternalStorageDirectory() + MqttTopic.TOPIC_LEVEL_SEPARATOR + split[1];
        }
        Uri uri2 = null;
        if (i == 2) {
            int length = split.length;
            if (length >= 2) {
                String str2 = split[0];
                ZLogger.v("type=" + str2);
                if ("raw".equalsIgnoreCase(str2)) {
                    return split[1];
                }
                if ("msf".equalsIgnoreCase(str2)) {
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(split[1])), null, null);
                }
                getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), ContentUris.parseId(uri)), null, null);
            } else if (length == 1) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(documentId)), null, null);
            }
        } else if (i == 3) {
            String str3 = split[0];
            if ("image".equalsIgnoreCase(str3)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equalsIgnoreCase(str3)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equalsIgnoreCase(str3)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(context, uri2, "_id=?", new String[]{split[1]});
        } else if (i == 4) {
            Cursor query = context.getContentResolver().query(uri, null, null, null, null);
            int columnIndex = query.getColumnIndex("_display_name");
            int columnIndex2 = query.getColumnIndex("_size");
            query.moveToFirst();
            String string = query.getString(columnIndex);
            ZLogger.e("name:" + string);
            ZLogger.e("size:" + Long.toString(query.getLong(columnIndex2)));
            File file = new File(context.getCacheDir(), string);
            try {
                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[Math.min(openInputStream.available(), 1048576)];
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                openInputStream.close();
                fileOutputStream.close();
                ZLogger.d("File Path: " + file.getPath());
                ZLogger.d("File Size: " + file.length());
            } catch (Exception e) {
                ZLogger.e(e.getMessage());
            }
            return file.getPath();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyFile(java.lang.String r5, java.lang.String r6) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L20
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r5 = " not exist"
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.realsil.sdk.core.logger.ZLogger.w(r5)
            return
        L20:
            java.io.File r1 = new java.io.File
            r1.<init>(r6)
            boolean r2 = r1.exists()
            if (r2 == 0) goto L61
            boolean r2 = r1.createNewFile()     // Catch: java.io.IOException -> L48
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L48
            r3.<init>()     // Catch: java.io.IOException -> L48
            java.lang.String r4 = "createNewFile: "
            r3.append(r4)     // Catch: java.io.IOException -> L48
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch: java.io.IOException -> L48
            r3.append(r2)     // Catch: java.io.IOException -> L48
            java.lang.String r2 = r3.toString()     // Catch: java.io.IOException -> L48
            com.realsil.sdk.core.logger.ZLogger.e(r2)     // Catch: java.io.IOException -> L48
            goto L61
        L48:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "createFile failed: "
            r3.append(r4)
            java.lang.String r2 = r2.toString()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.realsil.sdk.core.logger.ZLogger.e(r2)
        L61:
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L70
            r3.<init>(r0)     // Catch: java.io.FileNotFoundException -> L70
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.io.FileNotFoundException -> L6e
            r0.<init>(r1)     // Catch: java.io.FileNotFoundException -> L6e
            r2 = r0
            goto L79
        L6e:
            r0 = move-exception
            goto L72
        L70:
            r0 = move-exception
            r3 = r2
        L72:
            java.lang.String r0 = r0.toString()
            com.realsil.sdk.core.logger.ZLogger.e(r0)
        L79:
            java.lang.String r0 = "copyFile from %s to %s"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L96 java.io.IOException -> L98
            r4 = 0
            r1[r4] = r5     // Catch: java.lang.Throwable -> L96 java.io.IOException -> L98
            r5 = 1
            r1[r5] = r6     // Catch: java.lang.Throwable -> L96 java.io.IOException -> L98
            java.lang.String r5 = java.lang.String.format(r0, r1)     // Catch: java.lang.Throwable -> L96 java.io.IOException -> L98
            com.realsil.sdk.core.logger.ZLogger.d(r5)     // Catch: java.lang.Throwable -> L96 java.io.IOException -> L98
            copyFile(r3, r2)     // Catch: java.lang.Throwable -> L96 java.io.IOException -> L98
            if (r3 == 0) goto L93
            r3.close()     // Catch: java.io.IOException -> L93
        L93:
            if (r2 == 0) goto Laa
            goto La7
        L96:
            r5 = move-exception
            goto Lab
        L98:
            r5 = move-exception
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L96
            com.realsil.sdk.core.logger.ZLogger.e(r5)     // Catch: java.lang.Throwable -> L96
            if (r3 == 0) goto La5
            r3.close()     // Catch: java.io.IOException -> La5
        La5:
            if (r2 == 0) goto Laa
        La7:
            r2.close()     // Catch: java.io.IOException -> Laa
        Laa:
            return
        Lab:
            if (r3 == 0) goto Lb0
            r3.close()     // Catch: java.io.IOException -> Lb0
        Lb0:
            if (r2 == 0) goto Lb5
            r2.close()     // Catch: java.io.IOException -> Lb5
        Lb5:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.copyFile(java.lang.String, java.lang.String):void");
    }

    public static void copyFileStream(File file, Uri uri, Context context) throws IOException {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    if (inputStream != null) {
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream2 = fileOutputStream;
                            e.printStackTrace();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream = fileOutputStream2;
                                fileOutputStream.close();
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream2 = fileOutputStream;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw th;
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
        fileOutputStream.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0075 A[Catch: IOException -> 0x0091, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x0091, blocks: (B:19:0x004f, B:43:0x0075, B:55:0x008d), top: B:74:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008d A[Catch: IOException -> 0x0091, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x0091, blocks: (B:19:0x004f, B:43:0x0075, B:55:0x008d), top: B:74:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0092 -> B:73:0x0095). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyFromAssetsToSdcard(android.content.Context r4, boolean r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r7)
            boolean r0 = r0.exists()
            if (r0 == 0) goto Le
            if (r5 != 0) goto Le
            return
        Le:
            r5 = 0
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L61 java.io.FileNotFoundException -> L79
            java.io.InputStream r4 = r4.open(r6)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L61 java.io.FileNotFoundException -> L79
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L57 java.io.IOException -> L59 java.io.FileNotFoundException -> L5b
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L57 java.io.IOException -> L59 java.io.FileNotFoundException -> L5b
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r5]     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
        L20:
            r2 = 0
            int r3 = r4.read(r1, r2, r5)     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            if (r3 < 0) goto L2b
            r0.write(r1, r2, r3)     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            goto L20
        L2b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            r5.<init>()     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            java.lang.String r1 = "source = "
            r5.append(r1)     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            r5.append(r6)     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            java.lang.String r6 = ", dest = "
            r5.append(r6)     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            r5.append(r7)     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            java.lang.String r5 = r5.toString()     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            com.realsil.sdk.core.logger.ZLogger.v(r5)     // Catch: java.io.IOException -> L53 java.io.FileNotFoundException -> L55 java.lang.Throwable -> L96
            r0.close()     // Catch: java.io.IOException -> L4b
            goto L4f
        L4b:
            r5 = move-exception
            r5.printStackTrace()
        L4f:
            r4.close()     // Catch: java.io.IOException -> L91
            goto L95
        L53:
            r5 = move-exception
            goto L66
        L55:
            r5 = move-exception
            goto L7e
        L57:
            r6 = move-exception
            goto L99
        L59:
            r6 = move-exception
            goto L64
        L5b:
            r6 = move-exception
            goto L7c
        L5d:
            r4 = move-exception
            r6 = r4
            r4 = r5
            goto L99
        L61:
            r4 = move-exception
            r6 = r4
            r4 = r5
        L64:
            r0 = r5
            r5 = r6
        L66:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L96
            if (r0 == 0) goto L73
            r0.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r5 = move-exception
            r5.printStackTrace()
        L73:
            if (r4 == 0) goto L95
            r4.close()     // Catch: java.io.IOException -> L91
            goto L95
        L79:
            r4 = move-exception
            r6 = r4
            r4 = r5
        L7c:
            r0 = r5
            r5 = r6
        L7e:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L96
            if (r0 == 0) goto L8b
            r0.close()     // Catch: java.io.IOException -> L87
            goto L8b
        L87:
            r5 = move-exception
            r5.printStackTrace()
        L8b:
            if (r4 == 0) goto L95
            r4.close()     // Catch: java.io.IOException -> L91
            goto L95
        L91:
            r4 = move-exception
            r4.printStackTrace()
        L95:
            return
        L96:
            r5 = move-exception
            r6 = r5
            r5 = r0
        L99:
            if (r5 == 0) goto La3
            r5.close()     // Catch: java.io.IOException -> L9f
            goto La3
        L9f:
            r5 = move-exception
            r5.printStackTrace()
        La3:
            if (r4 == 0) goto Lad
            r4.close()     // Catch: java.io.IOException -> La9
            goto Lad
        La9:
            r4 = move-exception
            r4.printStackTrace()
        Lad:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.copyFromAssetsToSdcard(android.content.Context, boolean, java.lang.String, java.lang.String):void");
    }

    public static File createDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static boolean exists(String str) {
        try {
            return new File(str).exists();
        } catch (Exception e) {
            ZLogger.e(e.getMessage());
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r8 == 0) goto L31
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4b
            if (r9 == 0) goto L31
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4b
            if (r9 < 0) goto L29
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4b
            r8.close()
            return r9
        L29:
            java.lang.String r9 = "column '_data' does not exist. "
            com.realsil.sdk.core.logger.ZLogger.w(r9)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L4b
            goto L31
        L2f:
            r9 = move-exception
            goto L3b
        L31:
            if (r8 == 0) goto L36
            r8.close()
        L36:
            return r7
        L37:
            r9 = move-exception
            goto L4d
        L39:
            r9 = move-exception
            r8 = r7
        L3b:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L4b
            com.realsil.sdk.core.logger.ZLogger.w(r9)     // Catch: java.lang.Throwable -> L4b
            if (r8 == 0) goto L4a
            r8.close()
        L4a:
            return r7
        L4b:
            r9 = move-exception
            r7 = r8
        L4d:
            if (r7 == 0) goto L52
            r7.close()
        L52:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getFileExtensionFromUrl(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf2 = str.lastIndexOf(35);
        if (lastIndexOf2 > 0) {
            str = str.substring(0, lastIndexOf2);
        }
        int lastIndexOf3 = str.lastIndexOf(63);
        if (lastIndexOf3 > 0) {
            str = str.substring(0, lastIndexOf3);
        }
        int lastIndexOf4 = str.lastIndexOf(47);
        if (lastIndexOf4 >= 0) {
            str = str.substring(lastIndexOf4 + 1);
        }
        return (str.isEmpty() || (lastIndexOf = str.lastIndexOf(46)) < 0) ? "" : str.substring(lastIndexOf + 1);
    }

    public static String getFileExtensionFromUrl2(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf2 = str.lastIndexOf(35);
        ZLogger.d("fragment=" + lastIndexOf2);
        if (lastIndexOf2 > 0) {
            str = str.substring(0, lastIndexOf2);
        }
        int lastIndexOf3 = str.lastIndexOf(63);
        ZLogger.d("query=" + lastIndexOf3);
        if (lastIndexOf3 > 0) {
            str = str.substring(0, lastIndexOf3);
        }
        int lastIndexOf4 = str.lastIndexOf(47);
        if (lastIndexOf4 >= 0) {
            str = str.substring(lastIndexOf4 + 1);
        }
        return (str.isEmpty() || !Pattern.matches("[a-zA-Z_0-9\\+\\.\\-\\(\\)\\%]+", str) || (lastIndexOf = str.lastIndexOf(46)) < 0) ? "" : str.substring(lastIndexOf + 1);
    }

    public static long getFileSize(String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            long j = 0;
            for (File file2 : file.listFiles()) {
                j += getFileSize(file2.getAbsolutePath());
            }
            return j;
        }
        return file.length();
    }

    public static String getPath(Context context, Uri uri) {
        try {
            if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
                return b(context, uri);
            }
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                String a2 = a(context, uri);
                if (TextUtils.isEmpty(a2)) {
                    String lastPathSegment = uri.getLastPathSegment();
                    File file = new File(lastPathSegment);
                    if (file.exists()) {
                        ZLogger.v(String.format(Locale.US, "> %s\n%s\n%d", file.getPath(), file.getName(), Long.valueOf(file.length())));
                        return lastPathSegment;
                    }
                    String path = uri.getPath();
                    if (TextUtils.isEmpty(path)) {
                        String path2 = uri.getPath();
                        File file2 = new File(path2);
                        if (file2.exists()) {
                            ZLogger.v(String.format(Locale.US, ">>> %s\n%s\n%d", file2.getPath(), file2.getName(), Long.valueOf(file2.length())));
                            return path2;
                        }
                        ZLogger.d(">>>file not exist");
                        return path2;
                    }
                    File file3 = new File(path);
                    if (file3.exists()) {
                        ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file3.getPath(), file3.getName(), Long.valueOf(file3.length())));
                        return path;
                    }
                    String replace = path.replace("/file_share", "");
                    File file4 = new File(replace);
                    if (file4.exists()) {
                        ZLogger.v(String.format(Locale.US, ">>> %s\n%s\n%d", file4.getPath(), file4.getName(), Long.valueOf(file4.length())));
                        return replace;
                    }
                    return replace.replace("/file_share", "");
                }
                File file5 = new File(a2);
                if (file5.exists()) {
                    ZLogger.v(String.format(Locale.US, ">> %s\n%s\n%d", file5.getPath(), file5.getName(), Long.valueOf(file5.length())));
                    return a2;
                }
                ZLogger.d(">> file not exist");
                return a2;
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                ZLogger.v("file:" + uri.getPath());
                return uri.getPath();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ZLogger.w(e.toString());
            return null;
        }
    }

    public static String getSDCardAbsPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static File getSaveFile(String str, String str2) {
        return getSaveFile(str, str2, true);
    }

    public static File getSaveFolder(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getSDCardAbsPath());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        File file = new File(sb.toString());
        file.mkdirs();
        return file;
    }

    public static String getSavePath(String str) {
        return getSaveFolder(str).getAbsolutePath();
    }

    public static String getSuffix(File file) {
        return (file == null || !file.exists() || file.isDirectory()) ? "" : getSuffix(file.getName());
    }

    public static int getUrlFileSize(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);
            int contentLength = httpURLConnection.getContentLength();
            httpURLConnection.disconnect();
            return contentLength;
        } catch (Exception e) {
            ZLogger.e(e.toString());
            return 0;
        }
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isGoogleCloudDocument(Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isHuaweiShareUri(Uri uri) {
        return "com.huawei.filemanager.share.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isHuaweiUri(Uri uri) {
        return "com.huawei.hidisk.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isNokia(Uri uri) {
        return "com.fihtdc.filemanager.provider".equals(uri.getAuthority());
    }

    public static boolean isOppoUri(Uri uri) {
        return "com.coloros.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isTencentProvider(Uri uri) {
        return "com.tencent.mtt.fileprovider".equals(uri.getAuthority());
    }

    public static boolean makeDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static File getSaveFile(String str, String str2, boolean z) {
        return getSaveFile(getSavePath(str) + File.separator + str2, z);
    }

    public static String getSuffix(String str) {
        if (TextUtils.isEmpty(str) || str.endsWith(".")) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1).toLowerCase(Locale.US) : "";
    }

    public static File getSaveFile(String str, boolean z) {
        File file = new File(str);
        try {
            if (!file.exists() && z) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0071 A[Catch: IOException -> 0x008d, TRY_ENTER, TRY_LEAVE, TryCatch #3 {IOException -> 0x008d, blocks: (B:19:0x004b, B:43:0x0071, B:55:0x0089), top: B:74:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0089 A[Catch: IOException -> 0x008d, TRY_ENTER, TRY_LEAVE, TryCatch #3 {IOException -> 0x008d, blocks: (B:19:0x004b, B:43:0x0071, B:55:0x0089), top: B:74:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.res.AssetManager] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x008e -> B:73:0x0091). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyFromAssetsToSdcard(android.content.res.AssetManager r4, boolean r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r7)
            boolean r0 = r0.exists()
            if (r0 == 0) goto Le
            if (r5 != 0) goto Le
            return
        Le:
            r5 = 0
            java.io.InputStream r4 = r4.open(r6)     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5d java.io.FileNotFoundException -> L75
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L55 java.io.FileNotFoundException -> L57
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L55 java.io.FileNotFoundException -> L57
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r5]     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
        L1c:
            r2 = 0
            int r3 = r4.read(r1, r2, r5)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            if (r3 < 0) goto L27
            r0.write(r1, r2, r3)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            goto L1c
        L27:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            r5.<init>()     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            java.lang.String r1 = "source = "
            r5.append(r1)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            r5.append(r6)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            java.lang.String r6 = ", dest = "
            r5.append(r6)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            r5.append(r7)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            java.lang.String r5 = r5.toString()     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            com.realsil.sdk.core.logger.ZLogger.v(r5)     // Catch: java.io.IOException -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L92
            r0.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r5 = move-exception
            r5.printStackTrace()
        L4b:
            r4.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L4f:
            r5 = move-exception
            goto L62
        L51:
            r5 = move-exception
            goto L7a
        L53:
            r6 = move-exception
            goto L95
        L55:
            r6 = move-exception
            goto L60
        L57:
            r6 = move-exception
            goto L78
        L59:
            r4 = move-exception
            r6 = r4
            r4 = r5
            goto L95
        L5d:
            r4 = move-exception
            r6 = r4
            r4 = r5
        L60:
            r0 = r5
            r5 = r6
        L62:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L6f
            r0.close()     // Catch: java.io.IOException -> L6b
            goto L6f
        L6b:
            r5 = move-exception
            r5.printStackTrace()
        L6f:
            if (r4 == 0) goto L91
            r4.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L75:
            r4 = move-exception
            r6 = r4
            r4 = r5
        L78:
            r0 = r5
            r5 = r6
        L7a:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L87
            r0.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r5 = move-exception
            r5.printStackTrace()
        L87:
            if (r4 == 0) goto L91
            r4.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L8d:
            r4 = move-exception
            r4.printStackTrace()
        L91:
            return
        L92:
            r5 = move-exception
            r6 = r5
            r5 = r0
        L95:
            if (r5 == 0) goto L9f
            r5.close()     // Catch: java.io.IOException -> L9b
            goto L9f
        L9b:
            r5 = move-exception
            r5.printStackTrace()
        L9f:
            if (r4 == 0) goto La9
            r4.close()     // Catch: java.io.IOException -> La5
            goto La9
        La5:
            r4 = move-exception
            r4.printStackTrace()
        La9:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.copyFromAssetsToSdcard(android.content.res.AssetManager, boolean, java.lang.String, java.lang.String):void");
    }
}
