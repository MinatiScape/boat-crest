package kotlin.io.path;

import java.nio.file.Path;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.8")
@ExperimentalPathApi
/* loaded from: classes12.dex */
public interface CopyActionContext {
    @NotNull
    CopyActionResult copyToIgnoringExistingDirectory(@NotNull Path path, @NotNull Path path2, boolean z);
}
