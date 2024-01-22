package com.coveiot.utils.utility;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
/* loaded from: classes9.dex */
public class ShareScreen {
    public static Bitmap saveScreen(View view, Context context) {
        view.setDrawingCacheEnabled(true);
        Bitmap drawingCache = view.getDrawingCache();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getFilesDir().getAbsolutePath().toString(), "my_screen.jpg"));
            drawingCache.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return drawingCache;
    }

    public static void shareScreen(String str, Bitmap bitmap, Context context) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/jpeg");
        intent.setPackage(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "title");
        contentValues.put("mime_type", "image/jpeg");
        Uri insert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        try {
            OutputStream openOutputStream = context.getContentResolver().openOutputStream(insert);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, openOutputStream);
            openOutputStream.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        intent.putExtra("android.intent.extra.STREAM", insert);
        context.startActivity(Intent.createChooser(intent, ""));
    }

    public static void shareScreenCommom(Bitmap bitmap, Context context) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/jpeg");
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "title");
        contentValues.put("mime_type", "image/jpeg");
        File file = new File(context.getFilesDir().getAbsolutePath().toString(), "my_screen.jpg");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file));
        Intent createChooser = Intent.createChooser(intent, "");
        createChooser.addFlags(1);
        context.startActivity(createChooser);
    }
}
