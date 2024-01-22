package kotlin.ranges;

import java.lang.Comparable;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.7")
@ExperimentalStdlibApi
/* loaded from: classes12.dex */
public interface OpenEndRange<T extends Comparable<? super T>> {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static <T extends Comparable<? super T>> boolean contains(@NotNull OpenEndRange<T> openEndRange, @NotNull T value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return value.compareTo(openEndRange.getStart()) >= 0 && value.compareTo(openEndRange.getEndExclusive()) < 0;
        }

        public static <T extends Comparable<? super T>> boolean isEmpty(@NotNull OpenEndRange<T> openEndRange) {
            return openEndRange.getStart().compareTo(openEndRange.getEndExclusive()) >= 0;
        }
    }

    boolean contains(@NotNull T t);

    @NotNull
    T getEndExclusive();

    @NotNull
    T getStart();

    boolean isEmpty();
}
