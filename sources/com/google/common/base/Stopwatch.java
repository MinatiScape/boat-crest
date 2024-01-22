package com.google.common.base;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.TimeUnit;
@GwtCompatible
/* loaded from: classes10.dex */
public final class Stopwatch {

    /* renamed from: a  reason: collision with root package name */
    public final Ticker f10529a;
    public boolean b;
    public long c;
    public long d;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10530a;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            f10530a = iArr;
            try {
                iArr[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10530a[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10530a[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10530a[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10530a[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10530a[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10530a[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public Stopwatch() {
        this.f10529a = Ticker.systemTicker();
    }

    public static String a(TimeUnit timeUnit) {
        switch (a.f10530a[timeUnit.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "μs";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return "min";
            case 6:
                return "h";
            case 7:
                return "d";
            default:
                throw new AssertionError();
        }
    }

    public static TimeUnit b(long j) {
        TimeUnit timeUnit = TimeUnit.DAYS;
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        if (timeUnit.convert(j, timeUnit2) > 0) {
            return timeUnit;
        }
        TimeUnit timeUnit3 = TimeUnit.HOURS;
        if (timeUnit3.convert(j, timeUnit2) > 0) {
            return timeUnit3;
        }
        TimeUnit timeUnit4 = TimeUnit.MINUTES;
        if (timeUnit4.convert(j, timeUnit2) > 0) {
            return timeUnit4;
        }
        TimeUnit timeUnit5 = TimeUnit.SECONDS;
        if (timeUnit5.convert(j, timeUnit2) > 0) {
            return timeUnit5;
        }
        TimeUnit timeUnit6 = TimeUnit.MILLISECONDS;
        if (timeUnit6.convert(j, timeUnit2) > 0) {
            return timeUnit6;
        }
        TimeUnit timeUnit7 = TimeUnit.MICROSECONDS;
        return timeUnit7.convert(j, timeUnit2) > 0 ? timeUnit7 : timeUnit2;
    }

    public static Stopwatch createStarted() {
        return new Stopwatch().start();
    }

    public static Stopwatch createUnstarted() {
        return new Stopwatch();
    }

    public final long c() {
        return this.b ? (this.f10529a.read() - this.d) + this.c : this.c;
    }

    public long elapsed(TimeUnit timeUnit) {
        return timeUnit.convert(c(), TimeUnit.NANOSECONDS);
    }

    public boolean isRunning() {
        return this.b;
    }

    @CanIgnoreReturnValue
    public Stopwatch reset() {
        this.c = 0L;
        this.b = false;
        return this;
    }

    @CanIgnoreReturnValue
    public Stopwatch start() {
        Preconditions.checkState(!this.b, "This stopwatch is already running.");
        this.b = true;
        this.d = this.f10529a.read();
        return this;
    }

    @CanIgnoreReturnValue
    public Stopwatch stop() {
        long read = this.f10529a.read();
        Preconditions.checkState(this.b, "This stopwatch is already stopped.");
        this.b = false;
        this.c += read - this.d;
        return this;
    }

    public String toString() {
        long c = c();
        TimeUnit b = b(c);
        String c2 = l.c(c / TimeUnit.NANOSECONDS.convert(1L, b));
        String a2 = a(b);
        StringBuilder sb = new StringBuilder(String.valueOf(c2).length() + 1 + String.valueOf(a2).length());
        sb.append(c2);
        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        sb.append(a2);
        return sb.toString();
    }

    public static Stopwatch createStarted(Ticker ticker) {
        return new Stopwatch(ticker).start();
    }

    public static Stopwatch createUnstarted(Ticker ticker) {
        return new Stopwatch(ticker);
    }

    public Stopwatch(Ticker ticker) {
        this.f10529a = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
    }
}
