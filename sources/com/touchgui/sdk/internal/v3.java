package com.touchgui.sdk.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.touchgui.sdk.TGLogger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
/* loaded from: classes12.dex */
public abstract class v3 {
    public static boolean a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            if (!file2.delete()) {
                                return false;
                            }
                        } else if (file2.isDirectory() && !a(file2)) {
                            return false;
                        }
                    }
                }
                return file.delete();
            }
            return false;
        }
        return true;
    }

    public static boolean b(File file) {
        return a(file, new FileFilter() { // from class: com.touchgui.sdk.internal.uc
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return file2.isFile();
            }
        });
    }

    public static long c(File file) {
        long c;
        long j = 0;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        c = file2.length();
                    } else if (file2.isDirectory()) {
                        c = c(file2);
                    }
                    j += c;
                }
            }
            return j;
        }
        return 0L;
    }

    public static boolean a(File file, FileFilter fileFilter) {
        if (fileFilter == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (fileFilter.accept(file2)) {
                            if (file2.isFile()) {
                                if (!file2.delete()) {
                                    return false;
                                }
                            } else if (file2.isDirectory() && !a(file2)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(android.graphics.Bitmap r5, java.io.File r6, android.graphics.Bitmap.CompressFormat r7, boolean r8) {
        /*
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L13
            int r2 = r5.getWidth()
            if (r2 == 0) goto L13
            int r2 = r5.getHeight()
            if (r2 != 0) goto L11
            goto L13
        L11:
            r2 = r1
            goto L14
        L13:
            r2 = r0
        L14:
            java.lang.String r3 = "ImageUtils"
            if (r2 == 0) goto L1e
            java.lang.String r5 = "bitmap is empty."
            android.util.Log.e(r3, r5)
            return r1
        L1e:
            boolean r2 = r5.isRecycled()
            if (r2 == 0) goto L2a
            java.lang.String r5 = "bitmap is recycled."
            android.util.Log.e(r3, r5)
            return r1
        L2a:
            boolean r2 = r6.exists()
            if (r2 == 0) goto L37
            boolean r2 = r6.delete()
            if (r2 != 0) goto L37
            goto L54
        L37:
            java.io.File r2 = r6.getParentFile()
            if (r2 == 0) goto L51
            boolean r4 = r2.exists()
            if (r4 == 0) goto L4a
            boolean r2 = r2.isDirectory()
            if (r2 == 0) goto L51
            goto L52
        L4a:
            boolean r2 = r2.mkdirs()
            if (r2 == 0) goto L51
            goto L52
        L51:
            r0 = r1
        L52:
            if (r0 != 0) goto L56
        L54:
            r0 = r1
            goto L60
        L56:
            boolean r0 = r6.createNewFile()     // Catch: java.io.IOException -> L5b
            goto L60
        L5b:
            r0 = move-exception
            r0.printStackTrace()
            goto L54
        L60:
            if (r0 != 0) goto L79
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "create or delete file <"
            r5.<init>(r7)
            r5.append(r6)
            java.lang.String r6 = "> failed."
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r3, r5)
            return r1
        L79:
            r0 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> La4 java.io.IOException -> La6
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> La4 java.io.IOException -> La6
            r3.<init>(r6)     // Catch: java.lang.Throwable -> La4 java.io.IOException -> La6
            r2.<init>(r3)     // Catch: java.lang.Throwable -> La4 java.io.IOException -> La6
            r6 = 100
            boolean r6 = r5.compress(r7, r6, r2)     // Catch: java.lang.Throwable -> L9f java.io.IOException -> La1
            if (r8 == 0) goto L99
            boolean r7 = r5.isRecycled()     // Catch: java.io.IOException -> L96 java.lang.Throwable -> L9f
            if (r7 != 0) goto L99
            r5.recycle()     // Catch: java.io.IOException -> L96 java.lang.Throwable -> L9f
            goto L99
        L96:
            r5 = move-exception
            r1 = r6
            goto La2
        L99:
            r2.close()     // Catch: java.io.IOException -> L9d
            goto Lb7
        L9d:
            r5 = move-exception
            goto Lb2
        L9f:
            r5 = move-exception
            goto Lb9
        La1:
            r5 = move-exception
        La2:
            r0 = r2
            goto La7
        La4:
            r5 = move-exception
            goto Lb8
        La6:
            r5 = move-exception
        La7:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> La4
            if (r0 == 0) goto Lb6
            r0.close()     // Catch: java.io.IOException -> Lb0
            goto Lb6
        Lb0:
            r5 = move-exception
            r6 = r1
        Lb2:
            r5.printStackTrace()
            goto Lb7
        Lb6:
            r6 = r1
        Lb7:
            return r6
        Lb8:
            r2 = r0
        Lb9:
            if (r2 == 0) goto Lc3
            r2.close()     // Catch: java.io.IOException -> Lbf
            goto Lc3
        Lbf:
            r6 = move-exception
            r6.printStackTrace()
        Lc3:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.touchgui.sdk.internal.v3.a(android.graphics.Bitmap, java.io.File, android.graphics.Bitmap$CompressFormat, boolean):boolean");
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        float[] fArr = {f, f, f, f, f, f, f, f};
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.drawPath(path, paint);
        return createBitmap;
    }

    public static void a(File file, String str) {
        BufferedWriter bufferedWriter;
        if (str == null) {
            return;
        }
        boolean z = false;
        if (file.exists()) {
            z = file.isFile();
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null && (!parentFile.exists() ? !parentFile.mkdirs() : !parentFile.isDirectory())) {
                try {
                    z = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!z) {
            TGLogger.e("create file <" + file + "> failed.");
            return;
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            bufferedWriter = bufferedWriter2;
        }
        try {
            bufferedWriter.write(str);
            try {
                bufferedWriter.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (IOException e4) {
            e = e4;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }
}
