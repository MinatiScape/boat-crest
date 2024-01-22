package com.mappls.sdk.navigation.notifications;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationService;
import com.mappls.sdk.navigation.notifications.a;
import com.mappls.sdk.navigation.routing.d;
import com.mappls.sdk.navigation.t;
/* loaded from: classes11.dex */
public class NavigationNotification extends com.mappls.sdk.navigation.notifications.a {
    public static final String DEEP_LINK_ACTION_OPEN_ROOT_SCREEN = "com.mappls.app.navigation.car.OpenRootScreen";
    public static final String EXIT_NAVIGATION = "com.mmi.maps.navigation.EXIT";
    public static final String GROUP_NAME = "NAVIGATION";
    public static final String NAVIGATION_PAUSE_NAVIGATION_SERVICE_ACTION = "NAVIGATION_PAUSE_NAVIGATION_SERVICE_ACTION";
    public static final String NAVIGATION_RESUME_NAVIGATION_SERVICE_ACTION = "NAVIGATION_RESUME_NAVIGATION_SERVICE_ACTION";
    public static final String NAVIGATION_STOP_NAVIGATION_SERVICE_ACTION = "com.mmi.maps.navigation.EXIT";
    public Bitmap c;
    public boolean d;

    /* loaded from: classes11.dex */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            d h = NavigationNotification.this.app.h();
            h.b(true);
            h.a(false);
            h.w();
        }
    }

    /* loaded from: classes11.dex */
    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            d h = NavigationNotification.this.app.h();
            h.b(false);
            h.a(true);
        }
    }

    /* loaded from: classes11.dex */
    public class c extends BroadcastReceiver {
        public c() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            NavigationNotification.this.app.stopNavigation();
            NavigationNotification.this.app.getNotificationHelper().removeNotifications();
        }
    }

    public NavigationNotification(NavigationApplication navigationApplication) {
        super(navigationApplication, GROUP_NAME);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0128, code lost:
        if (r0 != null) goto L23;
     */
    @Override // com.mappls.sdk.navigation.notifications.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.core.app.NotificationCompat.Builder buildNotification(boolean r18) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.notifications.NavigationNotification.buildNotification(boolean):androidx.core.app.NotificationCompat$Builder");
    }

    public final NotificationCompat.Builder d(boolean z, String str, String str2, Bitmap bitmap, int i, int i2) {
        NotificationCompat.Builder largeIcon = createBuilder(z).setContentInfo(str2).setContentTitle(str).setCategory("navigation").setContentText(str2).setLargeIcon(bitmap);
        MapplsNavigationHelper.getInstance().extendNotification(largeIcon);
        return largeIcon;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap((int) this.app.getResources().getDimension(17104901), (int) this.app.getResources().getDimension(17104902), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        if (createBitmap != null) {
            Paint paint = new Paint();
            paint.setColorFilter((this.app.getResources().getConfiguration().uiMode & 48) == 32 ? new PorterDuffColorFilter(Color.parseColor(Constants.WHITE), PorterDuff.Mode.SRC_IN) : new PorterDuffColorFilter(Color.parseColor(Constants.BLACK), PorterDuff.Mode.SRC_IN));
            new Canvas(createBitmap).drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        }
        return createBitmap;
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public int getNavigationNotificationId() {
        return 5;
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public int getNavigationWearableNotificationId() {
        return 1005;
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public int getPriority() {
        return 1;
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public a.EnumC0640a getType() {
        return a.EnumC0640a.NAVIGATION;
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public void init() {
        this.d = ((t.l) this.app.k().x0.get()).f12958a;
        a aVar = new a();
        b bVar = new b();
        c cVar = new c();
        if (Build.VERSION.SDK_INT >= 33) {
            this.app.registerReceiver(aVar, new IntentFilter(NAVIGATION_PAUSE_NAVIGATION_SERVICE_ACTION), 4);
            this.app.registerReceiver(bVar, new IntentFilter(NAVIGATION_RESUME_NAVIGATION_SERVICE_ACTION), 4);
            this.app.registerReceiver(cVar, new IntentFilter("com.mmi.maps.navigation.EXIT"), 4);
            return;
        }
        this.app.registerReceiver(aVar, new IntentFilter(NAVIGATION_PAUSE_NAVIGATION_SERVICE_ACTION));
        this.app.registerReceiver(bVar, new IntentFilter(NAVIGATION_RESUME_NAVIGATION_SERVICE_ACTION));
        this.app.registerReceiver(cVar, new IntentFilter("com.mmi.maps.navigation.EXIT"));
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public boolean isActive() {
        return isEnabled();
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public boolean isEnabled() {
        NavigationService f = this.app.f();
        if (f != null) {
            int e = f.e();
            int i = NavigationService.p;
            if ((e & 1) != 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.mappls.sdk.navigation.notifications.a
    public void setupNotification(Notification notification) {
    }
}
