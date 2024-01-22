package org.checkerframework.checker.regex;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.EnsuresQualifierIf;
/* loaded from: classes13.dex */
public final class RegexUtil {
    public RegexUtil() {
        throw new Error("do not instantiate");
    }

    @Pure
    public static int a(Pattern pattern) {
        return pattern.matcher("").groupCount();
    }

    @SideEffectFree
    public static String asRegex(String str) {
        return asRegex(str, 0);
    }

    @SideEffectFree
    public static String b(String str, int i, int i2) {
        return "regex \"" + str + "\" has " + i2 + " groups, but " + i + " groups are needed.";
    }

    @EnsuresQualifierIf(expression = {"#1"}, qualifier = Regex.class, result = true)
    @Pure
    public static boolean isRegex(String str) {
        return isRegex(str, 0);
    }

    @SideEffectFree
    public static String regexError(String str) {
        return regexError(str, 0);
    }

    @SideEffectFree
    public static PatternSyntaxException regexException(String str) {
        return regexException(str, 0);
    }

    /* loaded from: classes13.dex */
    public static class CheckedPatternSyntaxException extends Exception {
        private static final long serialVersionUID = 6266881831979001480L;
        private final PatternSyntaxException pse;

        public CheckedPatternSyntaxException(PatternSyntaxException patternSyntaxException) {
            this.pse = patternSyntaxException;
        }

        public String getDescription() {
            return this.pse.getDescription();
        }

        public int getIndex() {
            return this.pse.getIndex();
        }

        @Override // java.lang.Throwable
        @Pure
        public String getMessage() {
            return this.pse.getMessage();
        }

        public String getPattern() {
            return this.pse.getPattern();
        }

        public CheckedPatternSyntaxException(String str, String str2, int i) {
            this(new PatternSyntaxException(str, str2, i));
        }
    }

    @SideEffectFree
    public static String asRegex(String str, int i) {
        try {
            int a2 = a(Pattern.compile(str));
            if (a2 >= i) {
                return str;
            }
            throw new Error(b(str, i, a2));
        } catch (PatternSyntaxException e) {
            throw new Error(e);
        }
    }

    @EnsuresQualifierIf(expression = {"#1"}, qualifier = Regex.class, result = true)
    @Pure
    public static boolean isRegex(String str, int i) {
        try {
            return a(Pattern.compile(str)) >= i;
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    @SideEffectFree
    public static String regexError(String str, int i) {
        try {
            int a2 = a(Pattern.compile(str));
            if (a2 < i) {
                return b(str, i, a2);
            }
            return null;
        } catch (PatternSyntaxException e) {
            return e.getMessage();
        }
    }

    @SideEffectFree
    public static PatternSyntaxException regexException(String str, int i) {
        try {
            int a2 = a(Pattern.compile(str));
            if (a2 < i) {
                return new PatternSyntaxException(b(str, i, a2), str, -1);
            }
            return null;
        } catch (PatternSyntaxException e) {
            return e;
        }
    }

    @EnsuresQualifierIf(expression = {"#1"}, qualifier = Regex.class, result = true)
    @Pure
    public static boolean isRegex(char c) {
        return isRegex(Character.toString(c));
    }
}
