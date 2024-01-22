package androidx.constraintlayout.core.parser;
/* loaded from: classes.dex */
public class CLToken extends CLElement {
    public int j;
    public b k;
    public char[] l;
    public char[] m;
    public char[] n;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f893a;

        static {
            int[] iArr = new int[b.values().length];
            f893a = iArr;
            try {
                iArr[b.TRUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f893a[b.FALSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f893a[b.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f893a[b.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        UNKNOWN,
        TRUE,
        FALSE,
        NULL
    }

    public CLToken(char[] cArr) {
        super(cArr);
        this.j = 0;
        this.k = b.UNKNOWN;
        this.l = "true".toCharArray();
        this.m = "false".toCharArray();
        this.n = "null".toCharArray();
    }

    public static CLElement allocate(char[] cArr) {
        return new CLToken(cArr);
    }

    public boolean getBoolean() throws CLParsingException {
        b bVar = this.k;
        if (bVar == b.TRUE) {
            return true;
        }
        if (bVar == b.FALSE) {
            return false;
        }
        throw new CLParsingException("this token is not a boolean: <" + content() + ">", this);
    }

    public b getType() {
        return this.k;
    }

    public boolean isNull() throws CLParsingException {
        if (this.k == b.NULL) {
            return true;
        }
        throw new CLParsingException("this token is not a null: <" + content() + ">", this);
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        addIndent(sb, i);
        sb.append(content());
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        if (CLParser.d) {
            return "<" + content() + ">";
        }
        return content();
    }

    public boolean validate(char c, long j) {
        int i = a.f893a[this.k.ordinal()];
        if (i == 1) {
            char[] cArr = this.l;
            int i2 = this.j;
            r1 = cArr[i2] == c;
            if (r1 && i2 + 1 == cArr.length) {
                setEnd(j);
            }
        } else if (i == 2) {
            char[] cArr2 = this.m;
            int i3 = this.j;
            r1 = cArr2[i3] == c;
            if (r1 && i3 + 1 == cArr2.length) {
                setEnd(j);
            }
        } else if (i == 3) {
            char[] cArr3 = this.n;
            int i4 = this.j;
            r1 = cArr3[i4] == c;
            if (r1 && i4 + 1 == cArr3.length) {
                setEnd(j);
            }
        } else if (i == 4) {
            char[] cArr4 = this.l;
            int i5 = this.j;
            if (cArr4[i5] == c) {
                this.k = b.TRUE;
            } else if (this.m[i5] == c) {
                this.k = b.FALSE;
            } else if (this.n[i5] == c) {
                this.k = b.NULL;
            }
            r1 = true;
        }
        this.j++;
        return r1;
    }
}
