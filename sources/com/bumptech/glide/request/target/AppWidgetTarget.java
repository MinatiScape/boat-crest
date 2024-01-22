package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes2.dex */
public class AppWidgetTarget extends CustomTarget<Bitmap> {
    public final int[] k;
    public final ComponentName l;
    public final RemoteViews m;
    public final Context n;
    public final int o;

    public AppWidgetTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, int... iArr) {
        super(i, i2);
        if (iArr.length != 0) {
            this.n = (Context) Preconditions.checkNotNull(context, "Context can not be null!");
            this.m = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
            this.k = (int[]) Preconditions.checkNotNull(iArr, "WidgetIds can not be null!");
            this.o = i3;
            this.l = null;
            return;
        }
        throw new IllegalArgumentException("WidgetIds must have length > 0");
    }

    public final void a(@Nullable Bitmap bitmap) {
        this.m.setImageViewBitmap(this.o, bitmap);
        b();
    }

    public final void b() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.n);
        ComponentName componentName = this.l;
        if (componentName != null) {
            appWidgetManager.updateAppWidget(componentName, this.m);
        } else {
            appWidgetManager.updateAppWidget(this.k, this.m);
        }
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(@Nullable Drawable drawable) {
        a(null);
    }

    @Override // com.bumptech.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
        a(bitmap);
    }

    public AppWidgetTarget(Context context, int i, RemoteViews remoteViews, int... iArr) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews, iArr);
    }

    public AppWidgetTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, ComponentName componentName) {
        super(i, i2);
        this.n = (Context) Preconditions.checkNotNull(context, "Context can not be null!");
        this.m = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.l = (ComponentName) Preconditions.checkNotNull(componentName, "ComponentName can not be null!");
        this.o = i3;
        this.k = null;
    }

    public AppWidgetTarget(Context context, int i, RemoteViews remoteViews, ComponentName componentName) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews, componentName);
    }
}
