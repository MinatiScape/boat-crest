package org.apache.commons.cli;
/* loaded from: classes12.dex */
public final class OptionBuilder {

    /* renamed from: a  reason: collision with root package name */
    public static String f14329a = null;
    public static String b = null;
    public static String c = null;
    public static boolean d = false;
    public static int e = -1;
    public static Object f;
    public static boolean g;
    public static char h;
    public static OptionBuilder i = new OptionBuilder();

    public static void a() {
        b = null;
        c = HelpFormatter.DEFAULT_ARG_NAME;
        f14329a = null;
        f = null;
        d = false;
        e = -1;
        g = false;
        h = (char) 0;
    }

    public static Option create(char c2) throws IllegalArgumentException {
        return create(String.valueOf(c2));
    }

    public static OptionBuilder hasArg() {
        e = 1;
        return i;
    }

    public static OptionBuilder hasArgs() {
        e = -2;
        return i;
    }

    public static OptionBuilder hasOptionalArg() {
        e = 1;
        g = true;
        return i;
    }

    public static OptionBuilder hasOptionalArgs() {
        e = -2;
        g = true;
        return i;
    }

    public static OptionBuilder isRequired() {
        d = true;
        return i;
    }

    public static OptionBuilder withArgName(String str) {
        c = str;
        return i;
    }

    public static OptionBuilder withDescription(String str) {
        b = str;
        return i;
    }

    public static OptionBuilder withLongOpt(String str) {
        f14329a = str;
        return i;
    }

    public static OptionBuilder withType(Object obj) {
        f = obj;
        return i;
    }

    public static OptionBuilder withValueSeparator(char c2) {
        h = c2;
        return i;
    }

    public static Option create() throws IllegalArgumentException {
        if (f14329a != null) {
            return create((String) null);
        }
        a();
        throw new IllegalArgumentException("must specify longopt");
    }

    public static OptionBuilder hasArg(boolean z) {
        e = z ? 1 : -1;
        return i;
    }

    public static OptionBuilder hasArgs(int i2) {
        e = i2;
        return i;
    }

    public static OptionBuilder isRequired(boolean z) {
        d = z;
        return i;
    }

    public static OptionBuilder withValueSeparator() {
        h = '=';
        return i;
    }

    public static OptionBuilder hasOptionalArgs(int i2) {
        e = i2;
        g = true;
        return i;
    }

    public static Option create(String str) throws IllegalArgumentException {
        try {
            Option option = new Option(str, b);
            option.setLongOpt(f14329a);
            option.setRequired(d);
            option.setOptionalArg(g);
            option.setArgs(e);
            option.setType(f);
            option.setValueSeparator(h);
            option.setArgName(c);
            return option;
        } finally {
            a();
        }
    }
}
