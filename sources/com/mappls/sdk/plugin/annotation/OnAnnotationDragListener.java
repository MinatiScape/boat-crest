package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import com.mappls.sdk.plugin.annotation.Annotation;
/* loaded from: classes11.dex */
public interface OnAnnotationDragListener<T extends Annotation> {
    @Keep
    void onAnnotationDrag(T t);

    @Keep
    void onAnnotationDragFinished(T t);

    @Keep
    void onAnnotationDragStarted(T t);
}
