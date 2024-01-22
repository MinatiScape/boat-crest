package com.elvishew.xlog.printer.file.naming;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes9.dex */
public class DateFileNameGenerator implements FileNameGenerator {

    /* renamed from: a  reason: collision with root package name */
    public ThreadLocal<SimpleDateFormat> f7888a = new a(this);

    /* loaded from: classes9.dex */
    public class a extends ThreadLocal<SimpleDateFormat> {
        public a(DateFileNameGenerator dateFileNameGenerator) {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        }
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public String generateFileName(int i, long j) {
        SimpleDateFormat simpleDateFormat = this.f7888a.get();
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(j));
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public boolean isFileNameChangeable() {
        return true;
    }
}
