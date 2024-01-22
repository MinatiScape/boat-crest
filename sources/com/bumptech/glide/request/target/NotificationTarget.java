package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes2.dex */
public class NotificationTarget extends CustomTarget<Bitmap> {
    public final RemoteViews k;
    public final Context l;
    public final int m;
    public final String n;
    public final Notification o;
    public final int p;

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2) {
        this(context, i, remoteViews, notification, i2, null);
    }

    public final void a(@Nullable Bitmap bitmap) {
        this.k.setImageViewBitmap(this.p, bitmap);
        b();
    }

    public final void b() {
        ((NotificationManager) Preconditions.checkNotNull((NotificationManager) this.l.getSystemService("notification"))).notify(this.n, this.m, this.o);
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(@Nullable Drawable drawable) {
        a(null);
    }

    @Override // com.bumptech.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2, String str) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews, notification, i2, str);
    }

    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
        a(bitmap);
    }

    public NotificationTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, Notification notification, int i4, String str) {
        super(i, i2);
        this.l = (Context) Preconditions.checkNotNull(context, "Context must not be null!");
        this.o = (Notification) Preconditions.checkNotNull(notification, "Notification object can not be null!");
        this.k = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.p = i3;
        this.m = i4;
        this.n = str;
    }
}
