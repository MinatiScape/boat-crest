package kotlin.reflect;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface KDeclarationContainer {
    @NotNull
    Collection<KCallable<?>> getMembers();
}
