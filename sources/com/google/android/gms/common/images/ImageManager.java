package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zam;
import com.google.android.gms.internal.base.zat;
import com.google.android.gms.internal.base.zau;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
/* loaded from: classes6.dex */
public final class ImageManager {
    public static final Object h = new Object();
    public static HashSet i = new HashSet();
    public static ImageManager j;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8319a;
    public final Handler b = new zau(Looper.getMainLooper());
    public final ExecutorService c = zat.zaa().zab(4, 2);
    public final zam d = new zam();
    public final Map e = new HashMap();
    public final Map f = new HashMap();
    public final Map g = new HashMap();

    @KeepName
    /* loaded from: classes6.dex */
    public final class ImageReceiver extends ResultReceiver {
        public final Uri h;
        public final ArrayList i;

        public ImageReceiver(Uri uri) {
            super(new zau(Looper.getMainLooper()));
            this.h = uri;
            this.i = new ArrayList();
        }

        public final void b(zag zagVar) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.i.add(zagVar);
        }

        public final void c(zag zagVar) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.i.remove(zagVar);
        }

        public final void d() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.setPackage("com.google.android.gms");
            intent.putExtra(Constants.EXTRA_URI, this.h);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            ImageManager.this.f8319a.sendBroadcast(intent);
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i, Bundle bundle) {
            ImageManager imageManager = ImageManager.this;
            imageManager.c.execute(new a(imageManager, this.h, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    /* loaded from: classes6.dex */
    public interface OnImageLoadedListener {
        void onImageLoaded(@NonNull Uri uri, @Nullable Drawable drawable, boolean z);
    }

    public ImageManager(Context context, boolean z) {
        this.f8319a = context.getApplicationContext();
    }

    @NonNull
    public static ImageManager create(@NonNull Context context) {
        if (j == null) {
            j = new ImageManager(context, false);
        }
        return j;
    }

    public void loadImage(@NonNull ImageView imageView, int i2) {
        zaj(new zae(imageView, i2));
    }

    public final void zaj(zag zagVar) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new b(this, zagVar).run();
    }

    public void loadImage(@NonNull ImageView imageView, @NonNull Uri uri) {
        zaj(new zae(imageView, uri));
    }

    public void loadImage(@NonNull ImageView imageView, @NonNull Uri uri, int i2) {
        zae zaeVar = new zae(imageView, uri);
        zaeVar.zab = i2;
        zaj(zaeVar);
    }

    public void loadImage(@NonNull OnImageLoadedListener onImageLoadedListener, @NonNull Uri uri) {
        zaj(new zaf(onImageLoadedListener, uri));
    }

    public void loadImage(@NonNull OnImageLoadedListener onImageLoadedListener, @NonNull Uri uri, int i2) {
        zaf zafVar = new zaf(onImageLoadedListener, uri);
        zafVar.zab = i2;
        zaj(zafVar);
    }
}
