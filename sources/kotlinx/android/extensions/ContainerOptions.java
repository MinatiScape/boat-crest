package kotlinx.android.extensions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes12.dex */
public @interface ContainerOptions {
    CacheImplementation cache() default CacheImplementation.HASH_MAP;
}
