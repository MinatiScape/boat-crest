package kotlinx.coroutines.selects;

import com.clevertap.android.sdk.variables.CTVariableUtils;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class SeqNumber {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f14196a = AtomicLongFieldUpdater.newUpdater(SeqNumber.class, CTVariableUtils.NUMBER);
    @NotNull
    private volatile /* synthetic */ long number = 1;

    public final long next() {
        return f14196a.incrementAndGet(this);
    }
}
