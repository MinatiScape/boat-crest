package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public class PosixParser extends Parser {
    public List c = new ArrayList();
    public boolean d;
    public Option e;
    public Options f;

    public final void a(Iterator it) {
        if (this.d) {
            while (it.hasNext()) {
                this.c.add(it.next());
            }
        }
    }

    public final void b() {
        this.d = false;
        this.c.clear();
    }

    public void burstToken(String str, boolean z) {
        int i;
        for (int i2 = 1; i2 < str.length(); i2++) {
            String valueOf = String.valueOf(str.charAt(i2));
            if (!this.f.hasOption(valueOf)) {
                if (z) {
                    c(str.substring(i2), true);
                    return;
                } else {
                    this.c.add(str);
                    return;
                }
            }
            List list = this.c;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-");
            stringBuffer.append(valueOf);
            list.add(stringBuffer.toString());
            Option option = this.f.getOption(valueOf);
            this.e = option;
            if (option.hasArg() && str.length() != (i = i2 + 1)) {
                this.c.add(str.substring(i));
                return;
            }
        }
    }

    public final void c(String str, boolean z) {
        Option option;
        if (z && ((option = this.e) == null || !option.hasArg())) {
            this.d = true;
            this.c.add("--");
        }
        this.c.add(str);
    }

    public final void d(String str, boolean z) {
        if (z && !this.f.hasOption(str)) {
            this.d = true;
        }
        if (this.f.hasOption(str)) {
            this.e = this.f.getOption(str);
        }
        this.c.add(str);
    }

    @Override // org.apache.commons.cli.Parser
    public String[] flatten(Options options, String[] strArr, boolean z) {
        b();
        this.f = options;
        Iterator it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith("--")) {
                int indexOf = str.indexOf(61);
                String substring = indexOf == -1 ? str : str.substring(0, indexOf);
                if (!options.hasOption(substring)) {
                    c(str, z);
                } else {
                    this.e = options.getOption(substring);
                    this.c.add(substring);
                    if (indexOf != -1) {
                        this.c.add(str.substring(indexOf + 1));
                    }
                }
            } else if ("-".equals(str)) {
                this.c.add(str);
            } else if (str.startsWith("-")) {
                if (str.length() != 2 && !options.hasOption(str)) {
                    burstToken(str, z);
                } else {
                    d(str, z);
                }
            } else {
                c(str, z);
            }
            a(it);
        }
        List list = this.c;
        return (String[]) list.toArray(new String[list.size()]);
    }
}
