package androidx.core.text;

import java.nio.CharBuffer;
import java.util.Locale;
/* loaded from: classes.dex */
public final class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE;
    public static final TextDirectionHeuristicCompat LTR = new e(null, false);
    public static final TextDirectionHeuristicCompat RTL = new e(null, true);

    /* loaded from: classes.dex */
    public static class a implements c {
        public static final a b = new a(true);

        /* renamed from: a  reason: collision with root package name */
        public final boolean f1097a;

        public a(boolean z) {
            this.f1097a = z;
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            boolean z = false;
            while (i < i3) {
                int a2 = TextDirectionHeuristicsCompat.a(Character.getDirectionality(charSequence.charAt(i)));
                if (a2 != 0) {
                    if (a2 != 1) {
                        continue;
                        i++;
                        z = z;
                    } else if (!this.f1097a) {
                        return 1;
                    }
                } else if (this.f1097a) {
                    return 0;
                }
                z = true;
                i++;
                z = z;
            }
            if (z) {
                return this.f1097a ? 1 : 0;
            }
            return 2;
        }
    }

    /* loaded from: classes.dex */
    public static class b implements c {

        /* renamed from: a  reason: collision with root package name */
        public static final b f1098a = new b();

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 2;
            while (i < i3 && i4 == 2) {
                i4 = TextDirectionHeuristicsCompat.b(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return i4;
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        int a(CharSequence charSequence, int i, int i2);
    }

    /* loaded from: classes.dex */
    public static abstract class d implements TextDirectionHeuristicCompat {

        /* renamed from: a  reason: collision with root package name */
        public final c f1099a;

        public d(c cVar) {
            this.f1099a = cVar;
        }

        public abstract boolean a();

        public final boolean b(CharSequence charSequence, int i, int i2) {
            int a2 = this.f1099a.a(charSequence, i, i2);
            if (a2 != 0) {
                if (a2 != 1) {
                    return a();
                }
                return false;
            }
            return true;
        }

        @Override // androidx.core.text.TextDirectionHeuristicCompat
        public boolean isRtl(char[] cArr, int i, int i2) {
            return isRtl(CharBuffer.wrap(cArr), i, i2);
        }

        @Override // androidx.core.text.TextDirectionHeuristicCompat
        public boolean isRtl(CharSequence charSequence, int i, int i2) {
            if (charSequence != null && i >= 0 && i2 >= 0 && charSequence.length() - i2 >= i) {
                if (this.f1099a == null) {
                    return a();
                }
                return b(charSequence, i, i2);
            }
            throw new IllegalArgumentException();
        }
    }

    /* loaded from: classes.dex */
    public static class e extends d {
        public final boolean b;

        public e(c cVar, boolean z) {
            super(cVar);
            this.b = z;
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.d
        public boolean a() {
            return this.b;
        }
    }

    /* loaded from: classes.dex */
    public static class f extends d {
        public static final f b = new f();

        public f() {
            super(null);
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.d
        public boolean a() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f1098a;
        FIRSTSTRONG_LTR = new e(bVar, false);
        FIRSTSTRONG_RTL = new e(bVar, true);
        ANYRTL_LTR = new e(a.b, false);
        LOCALE = f.b;
    }

    public static int a(int i) {
        if (i != 0) {
            return (i == 1 || i == 2) ? 0 : 2;
        }
        return 1;
    }

    public static int b(int i) {
        if (i != 0) {
            if (i == 1 || i == 2) {
                return 0;
            }
            switch (i) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
