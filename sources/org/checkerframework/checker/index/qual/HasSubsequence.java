package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.JavaExpression;
@Target({ElementType.FIELD})
/* loaded from: classes13.dex */
public @interface HasSubsequence {
    @JavaExpression
    String from();

    @JavaExpression
    String to();

    @JavaExpression
    String value();
}
