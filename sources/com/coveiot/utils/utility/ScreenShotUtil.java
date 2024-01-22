package com.coveiot.utils.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import androidx.core.content.FileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes9.dex */
public class ScreenShotUtil {
    public static Uri getScreenShotUri(Context context, View view, String str) {
        try {
            String str2 = context.getFilesDir().getAbsolutePath().toString() + MqttTopic.TOPIC_LEVEL_SEPARATOR + AppUtils.getSimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Long.valueOf(System.currentTimeMillis())) + "_share_img.png";
            view.setDrawingCacheEnabled(true);
            if (view.getDrawingCache() == null) {
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                view.buildDrawingCache(true);
            }
            Bitmap createBitmap = view.getDrawingCache() != null ? Bitmap.createBitmap(view.getDrawingCache()) : null;
            view.setDrawingCacheEnabled(false);
            File file = new File(str2);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            createBitmap.compress(Bitmap.CompressFormat.PNG, 70, new ByteArrayOutputStream());
            createBitmap.compress(Bitmap.CompressFormat.PNG, 70, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return FileProvider.getUriForFile(context, str, file);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String takeScreenshot(Activity activity, Class cls, View view) {
        try {
            String str = activity.getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + AppUtils.getSimpleDateFormat("yyyy_MM_dd_hh").format(Long.valueOf(System.currentTimeMillis())) + "_share_img.jpg";
            view.setDrawingCacheEnabled(true);
            Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            new File(str).createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            createBitmap.compress(Bitmap.CompressFormat.PNG, 70, new ByteArrayOutputStream());
            createBitmap.compress(Bitmap.CompressFormat.PNG, 70, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return str;
        } catch (Throwable th) {
            th.printStackTrace();
            activity.getFilesDir().getAbsolutePath().toString();
            return null;
        }
    }

    public static void takeScreenshot(Context context, View view, String str) {
        try {
            String str2 = context.getFilesDir().getAbsolutePath().toString() + MqttTopic.TOPIC_LEVEL_SEPARATOR + AppUtils.getSimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Long.valueOf(System.currentTimeMillis())) + "_share_img.png";
            view.setDrawingCacheEnabled(true);
            Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            File file = new File(str2);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            createBitmap.compress(Bitmap.CompressFormat.PNG, 70, new ByteArrayOutputStream());
            createBitmap.compress(Bitmap.CompressFormat.PNG, 70, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Uri uriForFile = FileProvider.getUriForFile(context, str, file);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.setType("image/jpeg");
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.addFlags(1);
            intent.putExtra("android.intent.extra.EMAIL", new String[]{""});
            context.startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
