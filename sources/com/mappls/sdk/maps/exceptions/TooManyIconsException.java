package com.mappls.sdk.maps.exceptions;
/* loaded from: classes11.dex */
public class TooManyIconsException extends RuntimeException {
    public TooManyIconsException() {
        super("Cannot create an Icon because there are already too many. Try reusing Icon objects whenever possible.");
    }
}
