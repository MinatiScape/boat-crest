package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import com.mappls.sdk.plugin.annotation.Annotation;
/* loaded from: classes11.dex */
public interface OnAnnotationLongClickListener<T extends Annotation> {
    @Keep
    boolean onAnnotationLongClick(T t);
}
