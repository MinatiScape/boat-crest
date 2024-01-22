package com.elvishew.xlog.printer;

import com.elvishew.xlog.flattener.Flattener;
import com.elvishew.xlog.internal.DefaultsFactory;
/* loaded from: classes9.dex */
public class ConsolePrinter implements Printer {

    /* renamed from: a  reason: collision with root package name */
    public Flattener f7877a;

    public ConsolePrinter() {
        this.f7877a = DefaultsFactory.createFlattener();
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        System.out.println(this.f7877a.flatten(i, str, str2).toString());
    }

    public ConsolePrinter(Flattener flattener) {
        this.f7877a = flattener;
    }
}
