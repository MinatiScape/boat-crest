package kotlin.reflect;

import java.lang.annotation.Annotation;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface KAnnotatedElement {
    @NotNull
    List<Annotation> getAnnotations();
}
