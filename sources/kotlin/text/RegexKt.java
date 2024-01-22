package kotlin.text;

import java.util.regex.Matcher;
import kotlin.ranges.IntRange;
/* loaded from: classes12.dex */
public final class RegexKt {
    public static final MatchResult a(Matcher matcher, int i, CharSequence charSequence) {
        if (matcher.find(i)) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public static final /* synthetic */ MatchResult access$findNext(Matcher matcher, int i, CharSequence charSequence) {
        return a(matcher, i, charSequence);
    }

    public static final /* synthetic */ MatchResult access$matchEntire(Matcher matcher, CharSequence charSequence) {
        return b(matcher, charSequence);
    }

    public static final /* synthetic */ int access$toInt(Iterable iterable) {
        return e(iterable);
    }

    public static final MatchResult b(Matcher matcher, CharSequence charSequence) {
        if (matcher.matches()) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public static final IntRange c(java.util.regex.MatchResult matchResult) {
        return kotlin.ranges.h.until(matchResult.start(), matchResult.end());
    }

    public static final IntRange d(java.util.regex.MatchResult matchResult, int i) {
        return kotlin.ranges.h.until(matchResult.start(i), matchResult.end(i));
    }

    public static final int e(Iterable<? extends c> iterable) {
        int i = 0;
        for (c cVar : iterable) {
            i |= cVar.getValue();
        }
        return i;
    }
}
