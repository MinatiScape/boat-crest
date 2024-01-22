package kotlin.text;

import java.util.Iterator;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.PlatformImplementations;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection<MatchGroup> implements MatchNamedGroupCollection {
    public final /* synthetic */ MatcherMatchResult h;

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1<Integer, MatchGroup> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ MatchGroup invoke(Integer num) {
            return invoke(num.intValue());
        }

        @Nullable
        public final MatchGroup invoke(int i) {
            return MatcherMatchResult$groups$1.this.get(i);
        }
    }

    public MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.h = matcherMatchResult;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null ? true : obj instanceof MatchGroup) {
            return contains((MatchGroup) obj);
        }
        return false;
    }

    @Override // kotlin.text.MatchGroupCollection
    @Nullable
    public MatchGroup get(int i) {
        java.util.regex.MatchResult b;
        IntRange d;
        java.util.regex.MatchResult b2;
        b = this.h.b();
        d = RegexKt.d(b, i);
        if (d.getStart().intValue() >= 0) {
            b2 = this.h.b();
            String group = b2.group(i);
            Intrinsics.checkNotNullExpressionValue(group, "matchResult.group(index)");
            return new MatchGroup(group, d);
        }
        return null;
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        java.util.regex.MatchResult b;
        b = this.h.b();
        return b.groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<MatchGroup> iterator() {
        return SequencesKt___SequencesKt.map(CollectionsKt___CollectionsKt.asSequence(CollectionsKt__CollectionsKt.getIndices(this)), new a()).iterator();
    }

    public /* bridge */ boolean contains(MatchGroup matchGroup) {
        return super.contains((MatcherMatchResult$groups$1) matchGroup);
    }

    @Override // kotlin.text.MatchNamedGroupCollection
    @Nullable
    public MatchGroup get(@NotNull String name) {
        java.util.regex.MatchResult b;
        Intrinsics.checkNotNullParameter(name, "name");
        PlatformImplementations platformImplementations = PlatformImplementationsKt.IMPLEMENTATIONS;
        b = this.h.b();
        return platformImplementations.getMatchResultNamedGroup(b, name);
    }
}
