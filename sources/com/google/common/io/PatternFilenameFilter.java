package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class PatternFilenameFilter implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final Pattern f10685a;

    public PatternFilenameFilter(String str) {
        this(Pattern.compile(str));
    }

    @Override // java.io.FilenameFilter
    public boolean accept(@NullableDecl File file, String str) {
        return this.f10685a.matcher(str).matches();
    }

    public PatternFilenameFilter(Pattern pattern) {
        this.f10685a = (Pattern) Preconditions.checkNotNull(pattern);
    }
}
