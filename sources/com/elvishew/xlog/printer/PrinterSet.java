package com.elvishew.xlog.printer;
/* loaded from: classes9.dex */
public class PrinterSet implements Printer {

    /* renamed from: a  reason: collision with root package name */
    public Printer[] f7878a;

    public PrinterSet(Printer... printerArr) {
        this.f7878a = printerArr;
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        for (Printer printer : this.f7878a) {
            printer.println(i, str, str2);
        }
    }
}
