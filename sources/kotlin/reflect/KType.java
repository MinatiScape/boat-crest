package kotlin.reflect;

import java.util.List;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface KType extends KAnnotatedElement {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void getArguments$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void getClassifier$annotations() {
        }
    }

    @NotNull
    List<KTypeProjection> getArguments();

    @Nullable
    KClassifier getClassifier();

    boolean isMarkedNullable();
}
