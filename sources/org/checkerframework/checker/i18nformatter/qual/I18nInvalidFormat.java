package org.checkerframework.checker.i18nformatter.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({I18nUnknownFormat.class})
/* loaded from: classes13.dex */
public @interface I18nInvalidFormat {
    String value();
}
