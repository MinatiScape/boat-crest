package kotlin.text;

import java.util.List;
import java.util.regex.Matcher;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.MatchResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class MatcherMatchResult implements MatchResult {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Matcher f14123a;
    @NotNull
    public final CharSequence b;
    @NotNull
    public final MatchGroupCollection c;
    @Nullable
    public List<String> d;

    public MatcherMatchResult(@NotNull Matcher matcher, @NotNull CharSequence input) {
        Intrinsics.checkNotNullParameter(matcher, "matcher");
        Intrinsics.checkNotNullParameter(input, "input");
        this.f14123a = matcher;
        this.b = input;
        this.c = new MatcherMatchResult$groups$1(this);
    }

    public final java.util.regex.MatchResult b() {
        return this.f14123a;
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public MatchResult.Destructured getDestructured() {
        return MatchResult.DefaultImpls.getDestructured(this);
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public List<String> getGroupValues() {
        if (this.d == null) {
            this.d = new AbstractList<String>() { // from class: kotlin.text.MatcherMatchResult$groupValues$1
                @Override // kotlin.collections.AbstractCollection, java.util.Collection
                public final /* bridge */ boolean contains(Object obj) {
                    if (obj instanceof String) {
                        return contains((String) obj);
                    }
                    return false;
                }

                @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
                public int getSize() {
                    java.util.regex.MatchResult b;
                    b = MatcherMatchResult.this.b();
                    return b.groupCount() + 1;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public final /* bridge */ int indexOf(Object obj) {
                    if (obj instanceof String) {
                        return indexOf((String) obj);
                    }
                    return -1;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public final /* bridge */ int lastIndexOf(Object obj) {
                    if (obj instanceof String) {
                        return lastIndexOf((String) obj);
                    }
                    return -1;
                }

                public /* bridge */ boolean contains(String str) {
                    return super.contains((MatcherMatchResult$groupValues$1) str);
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                @NotNull
                public String get(int i) {
                    java.util.regex.MatchResult b;
                    b = MatcherMatchResult.this.b();
                    String group = b.group(i);
                    return group == null ? "" : group;
                }

                public /* bridge */ int indexOf(String str) {
                    return super.indexOf((MatcherMatchResult$groupValues$1) str);
                }

                public /* bridge */ int lastIndexOf(String str) {
                    return super.lastIndexOf((MatcherMatchResult$groupValues$1) str);
                }
            };
        }
        List<String> list = this.d;
        Intrinsics.checkNotNull(list);
        return list;
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public MatchGroupCollection getGroups() {
        return this.c;
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public IntRange getRange() {
        IntRange c;
        c = RegexKt.c(b());
        return c;
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public String getValue() {
        String group = b().group();
        Intrinsics.checkNotNullExpressionValue(group, "matchResult.group()");
        return group;
    }

    @Override // kotlin.text.MatchResult
    @Nullable
    public MatchResult next() {
        MatchResult a2;
        int end = b().end() + (b().end() == b().start() ? 1 : 0);
        if (end <= this.b.length()) {
            Matcher matcher = this.f14123a.pattern().matcher(this.b);
            Intrinsics.checkNotNullExpressionValue(matcher, "matcher.pattern().matcher(input)");
            a2 = RegexKt.a(matcher, end, this.b);
            return a2;
        }
        return null;
    }
}
