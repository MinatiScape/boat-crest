package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import java.util.BitSet;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class CharMatcher implements Predicate<Character> {

    /* loaded from: classes10.dex */
    public class a extends x {
        public final /* synthetic */ String i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(CharMatcher charMatcher, CharMatcher charMatcher2, String str) {
            super(charMatcher2);
            this.i = str;
        }

        @Override // com.google.common.base.CharMatcher.w, com.google.common.base.CharMatcher
        public String toString() {
            return this.i;
        }
    }

    /* loaded from: classes10.dex */
    public static class a0 extends CharMatcher {
        public final String h;
        public final char[] i;
        public final char[] j;

        public a0(String str, char[] cArr, char[] cArr2) {
            this.h = str;
            this.i = cArr;
            this.j = cArr2;
            Preconditions.checkArgument(cArr.length == cArr2.length);
            int i = 0;
            while (i < cArr.length) {
                Preconditions.checkArgument(cArr[i] <= cArr2[i]);
                int i2 = i + 1;
                if (i2 < cArr.length) {
                    Preconditions.checkArgument(cArr2[i] < cArr[i2]);
                }
                i = i2;
            }
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            int binarySearch = Arrays.binarySearch(this.i, c);
            if (binarySearch >= 0) {
                return true;
            }
            int i = (~binarySearch) - 1;
            return i >= 0 && c <= this.j[i];
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.h;
        }
    }

    /* loaded from: classes10.dex */
    public static final class b extends CharMatcher {
        public final CharMatcher h;
        public final CharMatcher i;

        public b(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this.h = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.i = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.h.g(bitSet2);
            BitSet bitSet3 = new BitSet();
            this.i.g(bitSet3);
            bitSet2.and(bitSet3);
            bitSet.or(bitSet2);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.h.matches(c) && this.i.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String valueOf = String.valueOf(this.h);
            String valueOf2 = String.valueOf(this.i);
            StringBuilder sb = new StringBuilder(valueOf.length() + 19 + valueOf2.length());
            sb.append("CharMatcher.and(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class b0 extends a0 {
        public static final b0 k = new b0();

        public b0() {
            super("CharMatcher.singleWidth()", "\u0000־א׳\u0600ݐ\u0e00Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ\u0e7f₯℺\ufdff\ufeffￜ".toCharArray());
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends v {
        public static final c i = new c();

        public c() {
            super("CharMatcher.any()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence charSequence, char c) {
            return charSequence.length() == 0 ? "" : String.valueOf(c);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            return charSequence.length();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            return charSequence.length() == 0 ? -1 : 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence charSequence) {
            return charSequence.length() - 1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher.i, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.none();
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            Preconditions.checkNotNull(charMatcher);
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c) {
            char[] cArr = new char[charSequence.length()];
            Arrays.fill(cArr, c);
            return new String(cArr);
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i2) {
            int length = charSequence.length();
            Preconditions.checkPositionIndex(i2, length);
            if (i2 == length) {
                return -1;
            }
            return i2;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            StringBuilder sb = new StringBuilder(charSequence.length() * charSequence2.length());
            for (int i2 = 0; i2 < charSequence.length(); i2++) {
                sb.append(charSequence2);
            }
            return sb.toString();
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static final class c0 extends v {
        public static final int i = Integer.numberOfLeadingZeros(31);
        public static final c0 j = new c0();

        public c0() {
            super("CharMatcher.whitespace()");
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            for (int i2 = 0; i2 < 32; i2++) {
                bitSet.set("\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000".charAt(i2));
            }
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000".charAt((48906 * c) >>> i) == c;
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends CharMatcher {
        public final char[] h;

        public d(CharSequence charSequence) {
            char[] charArray = charSequence.toString().toCharArray();
            this.h = charArray;
            Arrays.sort(charArray);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            for (char c : this.h) {
                bitSet.set(c);
            }
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Arrays.binarySearch(this.h, c) >= 0;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
            for (char c : this.h) {
                sb.append(CharMatcher.h(c));
            }
            sb.append("\")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class e extends v {
        public static final e i = new e();

        public e() {
            super("CharMatcher.ascii()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c <= 127;
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static final class f extends v {
        public final BitSet i;

        public /* synthetic */ f(BitSet bitSet, String str, a aVar) {
            this(bitSet, str);
        }

        @Override // com.google.common.base.CharMatcher
        public void g(BitSet bitSet) {
            bitSet.or(this.i);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.i.get(c);
        }

        public f(BitSet bitSet, String str) {
            super(str);
            this.i = bitSet.length() + 64 < bitSet.size() ? (BitSet) bitSet.clone() : bitSet;
        }
    }

    /* loaded from: classes10.dex */
    public static final class g extends CharMatcher {
        public static final CharMatcher h = new g();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            if (c != ' ' && c != 133 && c != 5760) {
                if (c == 8199) {
                    return false;
                }
                if (c != 8287 && c != 12288 && c != 8232 && c != 8233) {
                    switch (c) {
                        case '\t':
                        case '\n':
                        case 11:
                        case '\f':
                        case '\r':
                            break;
                        default:
                            return c >= 8192 && c <= 8202;
                    }
                }
            }
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.breakingWhitespace()";
        }
    }

    /* loaded from: classes10.dex */
    public static final class h extends a0 {
        public static final h k = new h();

        public h() {
            super("CharMatcher.digit()", j(), i());
        }

        public static char[] i() {
            char[] cArr = new char[37];
            for (int i = 0; i < 37; i++) {
                cArr[i] = (char) ("0٠۰߀०০੦૦୦௦౦೦൦෦๐໐༠၀႐០᠐᥆᧐᪀᪐᭐᮰᱀᱐꘠꣐꤀꧐꧰꩐꯰０".charAt(i) + '\t');
            }
            return cArr;
        }

        public static char[] j() {
            return "0٠۰߀०০੦૦୦௦౦೦൦෦๐໐༠၀႐០᠐᥆᧐᪀᪐᭐᮰᱀᱐꘠꣐꤀꧐꧰꩐꯰０".toCharArray();
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class i extends CharMatcher {
        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new x(this);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static final class j extends CharMatcher {
        public final Predicate<? super Character> h;

        public j(Predicate<? super Character> predicate) {
            this.h = (Predicate) Preconditions.checkNotNull(predicate);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.h.apply(Character.valueOf(c));
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 26);
            sb.append("CharMatcher.forPredicate(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        public boolean apply(Character ch) {
            return this.h.apply(Preconditions.checkNotNull(ch));
        }
    }

    /* loaded from: classes10.dex */
    public static final class k extends i {
        public final char h;
        public final char i;

        public k(char c, char c2) {
            Preconditions.checkArgument(c2 >= c);
            this.h = c;
            this.i = c2;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            bitSet.set(this.h, this.i + 1);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.h <= c && c <= this.i;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String h = CharMatcher.h(this.h);
            String h2 = CharMatcher.h(this.i);
            StringBuilder sb = new StringBuilder(String.valueOf(h).length() + 27 + String.valueOf(h2).length());
            sb.append("CharMatcher.inRange('");
            sb.append(h);
            sb.append("', '");
            sb.append(h2);
            sb.append("')");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class l extends a0 {
        public static final l k = new l();

        public l() {
            super("CharMatcher.invisible()", "\u0000\u007f\u00ad\u0600\u061c\u06dd\u070f\u08e2\u1680\u180e\u2000\u2028\u205f\u2066\u3000\ud800\ufeff\ufff9".toCharArray(), "  \u00ad\u0605\u061c\u06dd\u070f\u08e2\u1680\u180e\u200f \u2064\u206f\u3000\uf8ff\ufeff\ufffb".toCharArray());
        }
    }

    /* loaded from: classes10.dex */
    public static final class m extends i {
        public final char h;

        public m(char c) {
            this.h = c;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.h) ? this : CharMatcher.none();
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            bitSet.set(this.h);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c == this.h;
        }

        @Override // com.google.common.base.CharMatcher.i, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.isNot(this.h);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.h) ? charMatcher : super.or(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c) {
            return charSequence.toString().replace(this.h, c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String h = CharMatcher.h(this.h);
            StringBuilder sb = new StringBuilder(String.valueOf(h).length() + 18);
            sb.append("CharMatcher.is('");
            sb.append(h);
            sb.append("')");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class n extends i {
        public final char h;
        public final char i;

        public n(char c, char c2) {
            this.h = c;
            this.i = c2;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            bitSet.set(this.h);
            bitSet.set(this.i);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c == this.h || c == this.i;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String h = CharMatcher.h(this.h);
            String h2 = CharMatcher.h(this.i);
            StringBuilder sb = new StringBuilder(String.valueOf(h).length() + 21 + String.valueOf(h2).length());
            sb.append("CharMatcher.anyOf(\"");
            sb.append(h);
            sb.append(h2);
            sb.append("\")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class o extends i {
        public final char h;

        public o(char c) {
            this.h = c;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.h) ? super.and(charMatcher) : charMatcher;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            bitSet.set(0, this.h);
            bitSet.set(this.h + 1, 65536);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c != this.h;
        }

        @Override // com.google.common.base.CharMatcher.i, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.is(this.h);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.h) ? CharMatcher.any() : this;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String h = CharMatcher.h(this.h);
            StringBuilder sb = new StringBuilder(String.valueOf(h).length() + 21);
            sb.append("CharMatcher.isNot('");
            sb.append(h);
            sb.append("')");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class p extends CharMatcher {
        public static final p h = new p();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isDigit(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaDigit()";
        }
    }

    /* loaded from: classes10.dex */
    public static final class q extends v {
        public static final q i = new q();

        public q() {
            super("CharMatcher.javaIsoControl()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c <= 31 || (c >= 127 && c <= 159);
        }
    }

    /* loaded from: classes10.dex */
    public static final class r extends CharMatcher {
        public static final r h = new r();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLetter(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetter()";
        }
    }

    /* loaded from: classes10.dex */
    public static final class s extends CharMatcher {
        public static final s h = new s();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLetterOrDigit(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetterOrDigit()";
        }
    }

    /* loaded from: classes10.dex */
    public static final class t extends CharMatcher {
        public static final t h = new t();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLowerCase(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLowerCase()";
        }
    }

    /* loaded from: classes10.dex */
    public static final class u extends CharMatcher {
        public static final u h = new u();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isUpperCase(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaUpperCase()";
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class v extends i {
        public final String h;

        public v(String str) {
            this.h = (String) Preconditions.checkNotNull(str);
        }

        @Override // com.google.common.base.CharMatcher
        public final String toString() {
            return this.h;
        }
    }

    /* loaded from: classes10.dex */
    public static class w extends CharMatcher {
        public final CharMatcher h;

        public w(CharMatcher charMatcher) {
            this.h = (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            return charSequence.length() - this.h.countIn(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.h.g(bitSet2);
            bitSet2.flip(0, 65536);
            bitSet.or(bitSet2);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return !this.h.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return this.h.matchesNoneOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return this.h.matchesAllOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return this.h;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 9);
            sb.append(valueOf);
            sb.append(".negate()");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static class x extends w {
        public x(CharMatcher charMatcher) {
            super(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static final class y extends v {
        public static final y i = new y();

        public y() {
            super("CharMatcher.none()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            Preconditions.checkNotNull(charMatcher);
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence charSequence, char c) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return false;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher.i, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.any();
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimLeadingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimTrailingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i2) {
            Preconditions.checkPositionIndex(i2, charSequence.length());
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            Preconditions.checkNotNull(charSequence2);
            return charSequence.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class z extends CharMatcher {
        public final CharMatcher h;
        public final CharMatcher i;

        public z(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this.h = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.i = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void g(BitSet bitSet) {
            this.h.g(bitSet);
            this.i.g(bitSet);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.h.matches(c) || this.i.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String valueOf = String.valueOf(this.h);
            String valueOf2 = String.valueOf(this.i);
            StringBuilder sb = new StringBuilder(valueOf.length() + 18 + valueOf2.length());
            sb.append("CharMatcher.or(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    public static CharMatcher any() {
        return c.i;
    }

    public static CharMatcher anyOf(CharSequence charSequence) {
        int length = charSequence.length();
        if (length != 0) {
            if (length != 1) {
                if (length != 2) {
                    return new d(charSequence);
                }
                return c(charSequence.charAt(0), charSequence.charAt(1));
            }
            return is(charSequence.charAt(0));
        }
        return none();
    }

    public static CharMatcher ascii() {
        return e.i;
    }

    public static CharMatcher breakingWhitespace() {
        return g.h;
    }

    public static n c(char c2, char c3) {
        return new n(c2, c3);
    }

    @GwtIncompatible
    public static boolean d(int i2, int i3) {
        return i2 <= 1023 && i3 > (i2 * 4) * 16;
    }

    @Deprecated
    public static CharMatcher digit() {
        return h.k;
    }

    @GwtIncompatible
    public static CharMatcher f(int i2, BitSet bitSet, String str) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (d(i2, bitSet.length())) {
                        return com.google.common.base.n.k(bitSet, str);
                    }
                    return new f(bitSet, str, null);
                }
                char nextSetBit = (char) bitSet.nextSetBit(0);
                return c(nextSetBit, (char) bitSet.nextSetBit(nextSetBit + 1));
            }
            return is((char) bitSet.nextSetBit(0));
        }
        return none();
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        return predicate instanceof CharMatcher ? (CharMatcher) predicate : new j(predicate);
    }

    public static String h(char c2) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[5 - i2] = "0123456789ABCDEF".charAt(c2 & 15);
            c2 = (char) (c2 >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static CharMatcher inRange(char c2, char c3) {
        return new k(c2, c3);
    }

    @Deprecated
    public static CharMatcher invisible() {
        return l.k;
    }

    public static CharMatcher is(char c2) {
        return new m(c2);
    }

    public static CharMatcher isNot(char c2) {
        return new o(c2);
    }

    @Deprecated
    public static CharMatcher javaDigit() {
        return p.h;
    }

    public static CharMatcher javaIsoControl() {
        return q.i;
    }

    @Deprecated
    public static CharMatcher javaLetter() {
        return r.h;
    }

    @Deprecated
    public static CharMatcher javaLetterOrDigit() {
        return s.h;
    }

    @Deprecated
    public static CharMatcher javaLowerCase() {
        return t.h;
    }

    @Deprecated
    public static CharMatcher javaUpperCase() {
        return u.h;
    }

    public static CharMatcher none() {
        return y.i;
    }

    public static CharMatcher noneOf(CharSequence charSequence) {
        return anyOf(charSequence).negate();
    }

    @Deprecated
    public static CharMatcher singleWidth() {
        return b0.k;
    }

    public static CharMatcher whitespace() {
        return c0.j;
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return new b(this, charMatcher);
    }

    public final String b(CharSequence charSequence, int i2, int i3, char c2, StringBuilder sb, boolean z2) {
        while (i2 < i3) {
            char charAt = charSequence.charAt(i2);
            if (!matches(charAt)) {
                sb.append(charAt);
                z2 = false;
            } else if (!z2) {
                sb.append(c2);
                z2 = true;
            }
            i2++;
        }
        return sb.toString();
    }

    public String collapseFrom(CharSequence charSequence, char c2) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (matches(charAt)) {
                if (charAt != c2 || (i2 != length - 1 && matches(charSequence.charAt(i2 + 1)))) {
                    StringBuilder sb = new StringBuilder(length);
                    sb.append(charSequence, 0, i2);
                    sb.append(c2);
                    return b(charSequence, i2 + 1, length, c2, sb, true);
                }
                i2++;
            }
            i2++;
        }
        return charSequence.toString();
    }

    public int countIn(CharSequence charSequence) {
        int i2 = 0;
        for (int i3 = 0; i3 < charSequence.length(); i3++) {
            if (matches(charSequence.charAt(i3))) {
                i2++;
            }
        }
        return i2;
    }

    @GwtIncompatible
    public CharMatcher e() {
        String concat;
        BitSet bitSet = new BitSet();
        g(bitSet);
        int cardinality = bitSet.cardinality();
        if (cardinality * 2 <= 65536) {
            return f(cardinality, bitSet, toString());
        }
        bitSet.flip(0, 65536);
        int i2 = 65536 - cardinality;
        String charMatcher = toString();
        if (charMatcher.endsWith(".negate()")) {
            concat = charMatcher.substring(0, charMatcher.length() - 9);
        } else {
            concat = ".negate()".length() != 0 ? charMatcher.concat(".negate()") : new String(charMatcher);
        }
        return new a(this, f(i2, bitSet, concat), charMatcher);
    }

    @GwtIncompatible
    public void g(BitSet bitSet) {
        for (int i2 = 65535; i2 >= 0; i2--) {
            if (matches((char) i2)) {
                bitSet.set(i2);
            }
        }
    }

    public int indexIn(CharSequence charSequence) {
        return indexIn(charSequence, 0);
    }

    public int lastIndexIn(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (matches(charSequence.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public abstract boolean matches(char c2);

    public boolean matchesAllOf(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesAnyOf(CharSequence charSequence) {
        return !matchesNoneOf(charSequence);
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return indexIn(charSequence) == -1;
    }

    public CharMatcher negate() {
        return new w(this);
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return new z(this, charMatcher);
    }

    public CharMatcher precomputed() {
        return com.google.common.base.l.h(this);
    }

    public String removeFrom(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] charArray = charSequence2.toCharArray();
        int i2 = 1;
        while (true) {
            indexIn++;
            while (indexIn != charArray.length) {
                if (matches(charArray[indexIn])) {
                    break;
                }
                charArray[indexIn - i2] = charArray[indexIn];
                indexIn++;
            }
            return new String(charArray, 0, indexIn - i2);
            i2++;
        }
    }

    public String replaceFrom(CharSequence charSequence, char c2) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] charArray = charSequence2.toCharArray();
        charArray[indexIn] = c2;
        while (true) {
            indexIn++;
            if (indexIn < charArray.length) {
                if (matches(charArray[indexIn])) {
                    charArray[indexIn] = c2;
                }
            } else {
                return new String(charArray);
            }
        }
    }

    public String retainFrom(CharSequence charSequence) {
        return negate().removeFrom(charSequence);
    }

    public String toString() {
        return super.toString();
    }

    public String trimAndCollapseFrom(CharSequence charSequence, char c2) {
        int length = charSequence.length();
        int i2 = length - 1;
        int i3 = 0;
        while (i3 < length && matches(charSequence.charAt(i3))) {
            i3++;
        }
        int i4 = i2;
        while (i4 > i3 && matches(charSequence.charAt(i4))) {
            i4--;
        }
        if (i3 == 0 && i4 == i2) {
            return collapseFrom(charSequence, c2);
        }
        int i5 = i4 + 1;
        return b(charSequence, i3, i5, c2, new StringBuilder(i5 - i3), false);
    }

    public String trimFrom(CharSequence charSequence) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && matches(charSequence.charAt(i2))) {
            i2++;
        }
        int i3 = length - 1;
        while (i3 > i2 && matches(charSequence.charAt(i3))) {
            i3--;
        }
        return charSequence.subSequence(i2, i3 + 1).toString();
    }

    public String trimLeadingFrom(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!matches(charSequence.charAt(i2))) {
                return charSequence.subSequence(i2, length).toString();
            }
        }
        return "";
    }

    public String trimTrailingFrom(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1).toString();
            }
        }
        return "";
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(Character ch) {
        return matches(ch.charValue());
    }

    public int indexIn(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i2, length);
        while (i2 < length) {
            if (matches(charSequence.charAt(i2))) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence2.length();
        if (length == 0) {
            return removeFrom(charSequence);
        }
        int i2 = 0;
        if (length == 1) {
            return replaceFrom(charSequence, charSequence2.charAt(0));
        }
        String charSequence3 = charSequence.toString();
        int indexIn = indexIn(charSequence3);
        if (indexIn == -1) {
            return charSequence3;
        }
        int length2 = charSequence3.length();
        StringBuilder sb = new StringBuilder(((length2 * 3) / 2) + 16);
        do {
            sb.append((CharSequence) charSequence3, i2, indexIn);
            sb.append(charSequence2);
            i2 = indexIn + 1;
            indexIn = indexIn(charSequence3, i2);
        } while (indexIn != -1);
        sb.append((CharSequence) charSequence3, i2, length2);
        return sb.toString();
    }
}
