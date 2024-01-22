package com.elvishew.xlog.printer.file.naming;
/* loaded from: classes9.dex */
public class ChangelessFileNameGenerator implements FileNameGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final String f7887a;

    public ChangelessFileNameGenerator(String str) {
        this.f7887a = str;
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public String generateFileName(int i, long j) {
        return this.f7887a;
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public boolean isFileNameChangeable() {
        return false;
    }
}
