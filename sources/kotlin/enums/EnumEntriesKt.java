package kotlin.enums;

import kotlin.ExperimentalStdlibApi;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class EnumEntriesKt {

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX WARN: Incorrect field signature: [TE; */
    /* loaded from: classes12.dex */
    public static final class a<E> extends Lambda implements Function0<E[]> {
        public final /* synthetic */ Enum[] $entries;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Incorrect types in method signature: ([TE;)V */
        public a(Enum[] enumArr) {
            super(0);
            this.$entries = enumArr;
        }

        /* JADX WARN: Incorrect return type in method signature: ()[TE; */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Enum[] invoke() {
            return this.$entries;
        }
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalStdlibApi
    @NotNull
    @PublishedApi
    public static final <E extends Enum<E>> EnumEntries<E> enumEntries(@NotNull Function0<E[]> entriesProvider) {
        Intrinsics.checkNotNullParameter(entriesProvider, "entriesProvider");
        return new kotlin.enums.a(entriesProvider);
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalStdlibApi
    @NotNull
    @PublishedApi
    public static final <E extends Enum<E>> EnumEntries<E> enumEntries(@NotNull E[] entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        kotlin.enums.a aVar = new kotlin.enums.a(new a(entries));
        aVar.size();
        return aVar;
    }
}
