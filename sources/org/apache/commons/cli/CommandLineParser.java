package org.apache.commons.cli;
/* loaded from: classes12.dex */
public interface CommandLineParser {
    CommandLine parse(Options options, String[] strArr) throws ParseException;

    CommandLine parse(Options options, String[] strArr, boolean z) throws ParseException;
}
