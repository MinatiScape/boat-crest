package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.CommonNotificationBuilder;
import com.google.firebase.messaging.Constants;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes10.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f11336a;
    public final Context b;
    public final NotificationParams c;

    public a(Context context, NotificationParams notificationParams, Executor executor) {
        this.f11336a = executor;
        this.b = context;
        this.c = notificationParams;
    }

    public boolean a() {
        if (this.c.getBoolean(Constants.MessageNotificationKeys.NO_UI)) {
            return true;
        }
        if (b()) {
            return false;
        }
        f0 d = d();
        CommonNotificationBuilder.DisplayNotificationInfo d2 = CommonNotificationBuilder.d(this.b, this.c);
        e(d2.notificationBuilder, d);
        c(d2);
        return true;
    }

    public final boolean b() {
        if (((KeyguardManager) this.b.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        if (!PlatformVersion.isAtLeastLollipop()) {
            SystemClock.sleep(10L);
        }
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.b.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == myPid) {
                    if (next.importance == 100) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void c(CommonNotificationBuilder.DisplayNotificationInfo displayNotificationInfo) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Showing notification");
        }
        ((NotificationManager) this.b.getSystemService("notification")).notify(displayNotificationInfo.tag, displayNotificationInfo.id, displayNotificationInfo.notificationBuilder.build());
    }

    @Nullable
    public final f0 d() {
        f0 c = f0.c(this.c.getString(Constants.MessageNotificationKeys.IMAGE_URL));
        if (c != null) {
            c.e(this.f11336a);
        }
        return c;
    }

    public final void e(NotificationCompat.Builder builder, @Nullable f0 f0Var) {
        if (f0Var == null) {
            return;
        }
        try {
            Bitmap bitmap = (Bitmap) Tasks.await(f0Var.d(), 5L, TimeUnit.SECONDS);
            builder.setLargeIcon(bitmap);
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
        } catch (InterruptedException unused) {
            Log.w(Constants.TAG, "Interrupted while downloading image, showing notification without it");
            f0Var.close();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            String valueOf = String.valueOf(e.getCause());
            StringBuilder sb = new StringBuilder(valueOf.length() + 26);
            sb.append("Failed to download image: ");
            sb.append(valueOf);
            Log.w(Constants.TAG, sb.toString());
        } catch (TimeoutException unused2) {
            Log.w(Constants.TAG, "Failed to download image in time, showing notification without it");
            f0Var.close();
        }
    }
}
