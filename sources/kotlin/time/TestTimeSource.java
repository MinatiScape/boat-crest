package kotlin.time;

import kotlin.SinceKotlin;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes12.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    public long b;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
    }

    public final void a(long j) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.b + DurationUnitKt__DurationUnitKt.shortName(getUnit()) + " is advanced by " + ((Object) Duration.m639toStringimpl(j)) + '.');
    }

    /* renamed from: plusAssign-LRDsOJo  reason: not valid java name */
    public final void m699plusAssignLRDsOJo(long j) {
        long j2;
        long m636toLongimpl = Duration.m636toLongimpl(j, getUnit());
        if (m636toLongimpl != Long.MIN_VALUE && m636toLongimpl != Long.MAX_VALUE) {
            long j3 = this.b;
            j2 = j3 + m636toLongimpl;
            if ((m636toLongimpl ^ j3) >= 0 && (j3 ^ j2) < 0) {
                a(j);
            }
        } else {
            double m633toDoubleimpl = this.b + Duration.m633toDoubleimpl(j, getUnit());
            if (m633toDoubleimpl > 9.223372036854776E18d || m633toDoubleimpl < -9.223372036854776E18d) {
                a(j);
            }
            j2 = (long) m633toDoubleimpl;
        }
        this.b = j2;
    }

    @Override // kotlin.time.AbstractLongTimeSource
    public long read() {
        return this.b;
    }
}
