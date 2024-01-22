package com.mappls.sdk.maps;
/* loaded from: classes11.dex */
public class n extends RuntimeException {
    public n(String str) {
        super(String.format("Map detected an error that would fail silently otherwise: %s", str));
    }
}
