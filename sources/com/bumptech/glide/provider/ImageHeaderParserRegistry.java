package com.bumptech.glide.provider;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public final class ImageHeaderParserRegistry {

    /* renamed from: a  reason: collision with root package name */
    public final List<ImageHeaderParser> f2518a = new ArrayList();

    public synchronized void add(@NonNull ImageHeaderParser imageHeaderParser) {
        this.f2518a.add(imageHeaderParser);
    }

    @NonNull
    public synchronized List<ImageHeaderParser> getParsers() {
        return this.f2518a;
    }
}
