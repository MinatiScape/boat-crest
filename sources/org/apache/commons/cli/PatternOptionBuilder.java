package org.apache.commons.cli;
/* loaded from: classes12.dex */
public class PatternOptionBuilder {
    public static final Class CLASS_VALUE;
    public static final Class DATE_VALUE;
    public static final Class EXISTING_FILE_VALUE;
    public static final Class FILES_VALUE;
    public static final Class FILE_VALUE;
    public static final Class NUMBER_VALUE;
    public static final Class OBJECT_VALUE;
    public static final Class STRING_VALUE;
    public static final Class URL_VALUE;

    /* renamed from: a  reason: collision with root package name */
    public static /* synthetic */ Class f14331a;
    public static /* synthetic */ Class b;
    public static /* synthetic */ Class c;
    public static /* synthetic */ Class d;
    public static /* synthetic */ Class e;
    public static /* synthetic */ Class f;
    public static /* synthetic */ Class g;
    public static /* synthetic */ Class h;
    public static /* synthetic */ Class i;

    static {
        Class cls = f14331a;
        if (cls == null) {
            cls = class$("java.lang.String");
            f14331a = cls;
        }
        STRING_VALUE = cls;
        Class cls2 = b;
        if (cls2 == null) {
            cls2 = class$("java.lang.Object");
            b = cls2;
        }
        OBJECT_VALUE = cls2;
        Class cls3 = c;
        if (cls3 == null) {
            cls3 = class$("java.lang.Number");
            c = cls3;
        }
        NUMBER_VALUE = cls3;
        Class cls4 = d;
        if (cls4 == null) {
            cls4 = class$("java.util.Date");
            d = cls4;
        }
        DATE_VALUE = cls4;
        Class cls5 = e;
        if (cls5 == null) {
            cls5 = class$("java.lang.Class");
            e = cls5;
        }
        CLASS_VALUE = cls5;
        Class cls6 = f;
        if (cls6 == null) {
            cls6 = class$("java.io.FileInputStream");
            f = cls6;
        }
        EXISTING_FILE_VALUE = cls6;
        Class cls7 = g;
        if (cls7 == null) {
            cls7 = class$("java.io.File");
            g = cls7;
        }
        FILE_VALUE = cls7;
        Class cls8 = h;
        if (cls8 == null) {
            cls8 = class$("[Ljava.io.File;");
            h = cls8;
        }
        FILES_VALUE = cls8;
        Class cls9 = i;
        if (cls9 == null) {
            cls9 = class$("java.net.URL");
            i = cls9;
        }
        URL_VALUE = cls9;
    }

    public static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError().initCause(e2);
        }
    }

    public static Object getValueClass(char c2) {
        if (c2 != '#') {
            if (c2 != '%') {
                if (c2 != '/') {
                    if (c2 != ':') {
                        if (c2 != '<') {
                            if (c2 != '>') {
                                if (c2 != '@') {
                                    if (c2 != '*') {
                                        if (c2 != '+') {
                                            return null;
                                        }
                                        return CLASS_VALUE;
                                    }
                                    return FILES_VALUE;
                                }
                                return OBJECT_VALUE;
                            }
                            return FILE_VALUE;
                        }
                        return EXISTING_FILE_VALUE;
                    }
                    return STRING_VALUE;
                }
                return URL_VALUE;
            }
            return NUMBER_VALUE;
        }
        return DATE_VALUE;
    }

    public static boolean isValueCode(char c2) {
        return c2 == '@' || c2 == ':' || c2 == '%' || c2 == '+' || c2 == '#' || c2 == '<' || c2 == '>' || c2 == '*' || c2 == '/' || c2 == '!';
    }

    public static Options parsePattern(String str) {
        Options options = new Options();
        Object obj = null;
        char c2 = ' ';
        int i2 = 0;
        boolean z = false;
        while (true) {
            if (i2 >= str.length()) {
                break;
            }
            char charAt = str.charAt(i2);
            if (!isValueCode(charAt)) {
                if (c2 != ' ') {
                    OptionBuilder.hasArg(obj != null);
                    OptionBuilder.isRequired(z);
                    OptionBuilder.withType(obj);
                    options.addOption(OptionBuilder.create(c2));
                    obj = null;
                    z = false;
                }
                c2 = charAt;
            } else if (charAt == '!') {
                z = true;
            } else {
                obj = getValueClass(charAt);
            }
            i2++;
        }
        if (c2 != ' ') {
            OptionBuilder.hasArg(obj != null);
            OptionBuilder.isRequired(z);
            OptionBuilder.withType(obj);
            options.addOption(OptionBuilder.create(c2));
        }
        return options;
    }
}
