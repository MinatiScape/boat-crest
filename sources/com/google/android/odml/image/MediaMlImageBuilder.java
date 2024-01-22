package com.google.android.odml.image;

import android.graphics.Rect;
import android.media.Image;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(19)
/* loaded from: classes10.dex */
public class MediaMlImageBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final Image f10441a;
    public int b = 0;
    public Rect c;

    public MediaMlImageBuilder(@NonNull Image image) {
        this.f10441a = image;
        this.c = new Rect(0, 0, image.getWidth(), image.getHeight());
    }

    @NonNull
    public MlImage build() {
        return new MlImage(new i(this.f10441a), this.b, this.c, 0L, this.f10441a.getWidth(), this.f10441a.getHeight());
    }

    @NonNull
    public MediaMlImageBuilder setRotation(int i) {
        MlImage.c(i);
        this.b = i;
        return this;
    }
}
