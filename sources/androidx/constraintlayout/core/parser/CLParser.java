package androidx.constraintlayout.core.parser;
/* loaded from: classes.dex */
public class CLParser {
    public static boolean d = false;

    /* renamed from: a  reason: collision with root package name */
    public String f891a;
    public boolean b = false;
    public int c;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f892a;

        static {
            int[] iArr = new int[b.values().length];
            f892a = iArr;
            try {
                iArr[b.OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f892a[b.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f892a[b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f892a[b.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f892a[b.KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f892a[b.TOKEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        UNKNOWN,
        OBJECT,
        ARRAY,
        NUMBER,
        STRING,
        KEY,
        TOKEN
    }

    public CLParser(String str) {
        this.f891a = str;
    }

    public static CLObject parse(String str) throws CLParsingException {
        return new CLParser(str).parse();
    }

    public final CLElement a(CLElement cLElement, int i, b bVar, boolean z, char[] cArr) {
        CLElement allocate;
        if (d) {
            System.out.println("CREATE " + bVar + " at " + cArr[i]);
        }
        switch (a.f892a[bVar.ordinal()]) {
            case 1:
                allocate = CLObject.allocate(cArr);
                i++;
                break;
            case 2:
                allocate = CLArray.allocate(cArr);
                i++;
                break;
            case 3:
                allocate = CLString.allocate(cArr);
                break;
            case 4:
                allocate = CLNumber.allocate(cArr);
                break;
            case 5:
                allocate = CLKey.allocate(cArr);
                break;
            case 6:
                allocate = CLToken.allocate(cArr);
                break;
            default:
                allocate = null;
                break;
        }
        if (allocate == null) {
            return null;
        }
        allocate.setLine(this.c);
        if (z) {
            allocate.setStart(i);
        }
        if (cLElement instanceof CLContainer) {
            allocate.setContainer((CLContainer) cLElement);
        }
        return allocate;
    }

    public final CLElement b(int i, char c, CLElement cLElement, char[] cArr) throws CLParsingException {
        if (c == '\t' || c == '\n' || c == '\r' || c == ' ') {
            return cLElement;
        }
        if (c == '\"' || c == '\'') {
            if (cLElement instanceof CLObject) {
                return a(cLElement, i, b.KEY, true, cArr);
            }
            return a(cLElement, i, b.STRING, true, cArr);
        } else if (c != '[') {
            if (c != ']') {
                if (c == '{') {
                    return a(cLElement, i, b.OBJECT, true, cArr);
                }
                if (c != '}') {
                    switch (c) {
                        case '+':
                        case '-':
                        case '.':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            return a(cLElement, i, b.NUMBER, true, cArr);
                        case ',':
                        case ':':
                            return cLElement;
                        case '/':
                            int i2 = i + 1;
                            if (i2 >= cArr.length || cArr[i2] != '/') {
                                return cLElement;
                            }
                            this.b = true;
                            return cLElement;
                        default:
                            if ((cLElement instanceof CLContainer) && !(cLElement instanceof CLObject)) {
                                CLElement a2 = a(cLElement, i, b.TOKEN, true, cArr);
                                CLToken cLToken = (CLToken) a2;
                                if (cLToken.validate(c, i)) {
                                    return a2;
                                }
                                throw new CLParsingException("incorrect token <" + c + "> at line " + this.c, cLToken);
                            }
                            return a(cLElement, i, b.KEY, true, cArr);
                    }
                }
            }
            cLElement.setEnd(i - 1);
            CLElement container = cLElement.getContainer();
            container.setEnd(i);
            return container;
        } else {
            return a(cLElement, i, b.ARRAY, true, cArr);
        }
    }

    public CLObject parse() throws CLParsingException {
        char[] charArray = this.f891a.toCharArray();
        int length = charArray.length;
        int i = 1;
        this.c = 1;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            char c = charArray[i2];
            if (c == '{') {
                break;
            }
            if (c == '\n') {
                this.c++;
            }
            i2++;
        }
        if (i2 != -1) {
            CLObject allocate = CLObject.allocate(charArray);
            allocate.setLine(this.c);
            allocate.setStart(i2);
            int i3 = i2 + 1;
            CLObject cLObject = allocate;
            while (i3 < length) {
                char c2 = charArray[i3];
                if (c2 == '\n') {
                    this.c += i;
                }
                if (this.b) {
                    if (c2 == '\n') {
                        this.b = z;
                    } else {
                        continue;
                        i3++;
                        i = 1;
                        z = false;
                    }
                }
                if (cLObject == null) {
                    break;
                }
                if (cLObject.isDone()) {
                    cLObject = b(i3, c2, cLObject, charArray);
                } else if (cLObject instanceof CLObject) {
                    if (c2 == '}') {
                        cLObject.setEnd(i3 - 1);
                    } else {
                        cLObject = b(i3, c2, cLObject, charArray);
                    }
                } else if (!(cLObject instanceof CLArray)) {
                    boolean z2 = cLObject instanceof CLString;
                    if (z2) {
                        long j = cLObject.start;
                        if (charArray[(int) j] == c2) {
                            cLObject.setStart(j + 1);
                            cLObject.setEnd(i3 - 1);
                        }
                    } else {
                        if (cLObject instanceof CLToken) {
                            CLToken cLToken = (CLToken) cLObject;
                            if (!cLToken.validate(c2, i3)) {
                                throw new CLParsingException("parsing incorrect token " + cLToken.content() + " at line " + this.c, cLToken);
                            }
                        }
                        if ((cLObject instanceof CLKey) || z2) {
                            long j2 = cLObject.start;
                            char c3 = charArray[(int) j2];
                            if ((c3 == '\'' || c3 == '\"') && c3 == c2) {
                                cLObject.setStart(j2 + 1);
                                cLObject.setEnd(i3 - 1);
                            }
                        }
                        if (!cLObject.isDone() && (c2 == '}' || c2 == ']' || c2 == ',' || c2 == ' ' || c2 == '\t' || c2 == '\r' || c2 == '\n' || c2 == ':')) {
                            long j3 = i3 - 1;
                            cLObject.setEnd(j3);
                            if (c2 == '}' || c2 == ']') {
                                cLObject = cLObject.getContainer();
                                cLObject.setEnd(j3);
                                if (cLObject instanceof CLKey) {
                                    cLObject = cLObject.getContainer();
                                    cLObject.setEnd(j3);
                                }
                            }
                        }
                    }
                } else if (c2 == ']') {
                    cLObject.setEnd(i3 - 1);
                } else {
                    cLObject = b(i3, c2, cLObject, charArray);
                }
                if (cLObject.isDone() && (!(cLObject instanceof CLKey) || ((CLKey) cLObject).j.size() > 0)) {
                    cLObject = cLObject.getContainer();
                }
                i3++;
                i = 1;
                z = false;
            }
            while (cLObject != null && !cLObject.isDone()) {
                if (cLObject instanceof CLString) {
                    cLObject.setStart(((int) cLObject.start) + 1);
                }
                cLObject.setEnd(length - 1);
                cLObject = cLObject.getContainer();
            }
            if (d) {
                System.out.println("Root: " + allocate.toJSON());
            }
            return allocate;
        }
        throw new CLParsingException("invalid json content", null);
    }
}
