package kotlin.text;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: classes12.dex */
public enum RegexOption implements c {
    IGNORE_CASE(2, 0, 2, null),
    MULTILINE(8, 0, 2, null),
    LITERAL(16, 0, 2, null),
    UNIX_LINES(1, 0, 2, null),
    COMMENTS(4, 0, 2, null),
    DOT_MATCHES_ALL(32, 0, 2, null),
    CANON_EQ(128, 0, 2, null);
    
    private final int mask;
    private final int value;

    RegexOption(int i, int i2) {
        this.value = i;
        this.mask = i2;
    }

    @Override // kotlin.text.c
    public int getMask() {
        return this.mask;
    }

    @Override // kotlin.text.c
    public int getValue() {
        return this.value;
    }

    /* synthetic */ RegexOption(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? i : i2);
    }
}
