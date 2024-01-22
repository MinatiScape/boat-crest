package com.coveiot.utils.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes9.dex */
public class CircularImageView {
    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        int width;
        Bitmap bitmap2 = null;
        try {
            if (bitmap.getWidth() > bitmap.getHeight()) {
                bitmap2 = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            } else {
                bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(bitmap2);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            if (bitmap.getWidth() > bitmap.getHeight()) {
                width = bitmap.getHeight() / 2;
            } else {
                width = bitmap.getWidth() / 2;
            }
            float f = width;
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawCircle(f, f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap2;
    }

    public static Uri getImageUri(Context context, Bitmap bitmap) {
        return Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", (String) null));
    }

    public static String saveImage(Bitmap bitmap, Context context, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        File file = new File(Environment.getExternalStorageDirectory() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            File file2 = new File(file, Calendar.getInstance().getTimeInMillis() + ".jpeg");
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            MediaScannerConnection.scanFile(context, new String[]{file2.getPath()}, new String[]{"image/jpeg"}, null);
            fileOutputStream.close();
            return file2.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String saveImageToInternal(Bitmap bitmap, Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream);
        try {
            FileOutputStream openFileOutput = context.openFileOutput(UtilConstants.PROFILE_PIC_PATH, 0);
            openFileOutput.write(byteArrayOutputStream.toByteArray());
            openFileOutput.close();
            String absolutePath = context.getFilesDir().getAbsolutePath();
            File file = new File(absolutePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + UtilConstants.PROFILE_PIC_PATH);
            MediaScannerConnection.scanFile(context, new String[]{file.getPath()}, new String[]{"image/jpeg"}, null);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
