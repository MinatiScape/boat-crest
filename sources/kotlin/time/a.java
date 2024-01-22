package kotlin.time;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.time.TimeMark;
import org.jetbrains.annotations.NotNull;
@ExperimentalTime
/* loaded from: classes12.dex */
public final class a implements TimeMark {
    @NotNull
    public final TimeMark h;
    public final long i;

    public a(TimeMark timeMark, long j) {
        this.h = timeMark;
        this.i = j;
    }

    public /* synthetic */ a(TimeMark timeMark, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(timeMark, j);
    }

    @Override // kotlin.time.TimeMark
    /* renamed from: elapsedNow-UwyO8pc */
    public long mo588elapsedNowUwyO8pc() {
        return Duration.m625minusLRDsOJo(this.h.mo588elapsedNowUwyO8pc(), this.i);
    }

    @Override // kotlin.time.TimeMark
    public boolean hasNotPassedNow() {
        return TimeMark.DefaultImpls.hasNotPassedNow(this);
    }

    @Override // kotlin.time.TimeMark
    public boolean hasPassedNow() {
        return TimeMark.DefaultImpls.hasPassedNow(this);
    }

    @Override // kotlin.time.TimeMark
    @NotNull
    /* renamed from: minus-LRDsOJo */
    public TimeMark mo589minusLRDsOJo(long j) {
        return TimeMark.DefaultImpls.m700minusLRDsOJo(this, j);
    }

    @Override // kotlin.time.TimeMark
    @NotNull
    /* renamed from: plus-LRDsOJo */
    public TimeMark mo591plusLRDsOJo(long j) {
        return new a(this.h, Duration.m626plusLRDsOJo(this.i, j), null);
    }
}
