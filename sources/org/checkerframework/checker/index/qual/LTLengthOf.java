package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.JavaExpression;
import org.checkerframework.framework.qual.SubtypeOf;
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({LTEqLengthOf.class})
/* loaded from: classes13.dex */
public @interface LTLengthOf {
    @JavaExpression
    String[] offset() default {};

    @JavaExpression
    String[] value();
}