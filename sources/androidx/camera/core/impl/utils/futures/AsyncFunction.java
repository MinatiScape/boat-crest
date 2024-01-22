package androidx.camera.core.impl.utils.futures;

import androidx.annotation.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
@FunctionalInterface
/* loaded from: classes.dex */
public interface AsyncFunction<I, O> {
    ListenableFuture<O> apply(@Nullable I i) throws Exception;
}
