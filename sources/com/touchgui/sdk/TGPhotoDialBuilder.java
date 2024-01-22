package com.touchgui.sdk;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import com.touchgui.sdk.internal.ea;
import java.util.List;
/* loaded from: classes12.dex */
public class TGPhotoDialBuilder {
    @Nullable
    private Bitmap background;
    private int color;
    private int dialId;
    private String filePath;
    private int replacedDialId = 0;
    @Nullable
    private List<Bitmap> slideImages;
    private String timePosition;
    private int timeStyle;

    public TGDial build() {
        ea eaVar = new ea(this.dialId, this.filePath);
        Bitmap bitmap = this.background;
        if (bitmap != null) {
            eaVar.h = 1;
            eaVar.f13760a.add(bitmap);
        } else {
            List<Bitmap> list = this.slideImages;
            if (list != null) {
                eaVar.h = 3;
                eaVar.f13760a.addAll(list);
            }
        }
        eaVar.b = this.color;
        eaVar.g = this.timePosition;
        eaVar.i = this.timeStyle;
        eaVar.setReplacedDialId(this.replacedDialId);
        return eaVar;
    }

    public TGPhotoDialBuilder setBackground(Bitmap bitmap) {
        this.background = bitmap;
        return this;
    }

    public TGPhotoDialBuilder setColor(int i) {
        this.color = i;
        return this;
    }

    public TGPhotoDialBuilder setDialId(int i) {
        this.dialId = i;
        return this;
    }

    public TGPhotoDialBuilder setFilePath(String str) {
        this.filePath = str;
        return this;
    }

    public TGPhotoDialBuilder setReplacedDialId(int i) {
        this.replacedDialId = i;
        return this;
    }

    public TGPhotoDialBuilder setSlideImages(@Nullable List<Bitmap> list) {
        this.slideImages = list;
        return this;
    }

    public TGPhotoDialBuilder setTimePosition(String str) {
        this.timePosition = str;
        return this;
    }

    public TGPhotoDialBuilder setTimeStyle(int i) {
        this.timeStyle = i;
        return this;
    }
}
