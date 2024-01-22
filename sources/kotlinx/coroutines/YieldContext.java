package kotlinx.coroutines;

import kotlin.PublishedApi;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
@PublishedApi
/* loaded from: classes12.dex */
public final class YieldContext extends AbstractCoroutineContextElement {
    @NotNull
    public static final Key Key = new Key(null);
    @JvmField
    public boolean dispatcherWasUnconfined;

    /* loaded from: classes12.dex */
    public static final class Key implements CoroutineContext.Key<YieldContext> {
        public Key() {
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public YieldContext() {
        super(Key);
    }
}
