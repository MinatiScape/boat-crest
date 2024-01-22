package com.bumptech.glide.load.engine;
/* loaded from: classes2.dex */
public final class b extends RuntimeException {
    private static final long serialVersionUID = -7530898992688511851L;

    public b(Throwable th) {
        super("Unexpected exception thrown by non-Glide code", th);
    }
}
