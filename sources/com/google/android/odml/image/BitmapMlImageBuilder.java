package com.google.android.odml.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import java.io.IOException;
/* loaded from: classes10.dex */
public class BitmapMlImageBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final Bitmap f10439a;
    public int b;
    public Rect c;

    public BitmapMlImageBuilder(@NonNull Context context, @NonNull Uri uri) throws IOException {
        this(MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri));
    }

    @NonNull
    public MlImage build() {
        return new MlImage(new e(this.f10439a), this.b, this.c, 0L, this.f10439a.getWidth(), this.f10439a.getHeight());
    }

    @NonNull
    public BitmapMlImageBuilder setRotation(int i) {
        MlImage.c(i);
        this.b = i;
        return this;
    }

    public BitmapMlImageBuilder(@NonNull Bitmap bitmap) {
        this.f10439a = bitmap;
        this.b = 0;
        this.c = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }
}
