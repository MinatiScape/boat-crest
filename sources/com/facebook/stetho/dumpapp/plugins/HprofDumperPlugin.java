package com.facebook.stetho.dumpapp.plugins;

import android.content.Context;
import android.os.Debug;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
/* loaded from: classes9.dex */
public class HprofDumperPlugin implements DumperPlugin {
    private static final String NAME = "hprof";
    private final Context mContext;

    public HprofDumperPlugin(Context context) {
        this.mContext = context;
    }

    private void handlePipeOutput(OutputStream outputStream) throws DumpException {
        File fileStreamPath = this.mContext.getFileStreamPath("hprof-dump.hprof");
        try {
            writeHprof(fileStreamPath);
            try {
                FileInputStream fileInputStream = new FileInputStream(fileStreamPath);
                try {
                    Util.copy(fileInputStream, outputStream, new byte[2048]);
                } finally {
                    fileInputStream.close();
                }
            } catch (IOException unused) {
                throw new DumpException("Failure copying " + fileStreamPath + " to dumper output");
            }
        } finally {
            if (fileStreamPath.exists()) {
                fileStreamPath.delete();
            }
        }
    }

    private static void truncateAndDeleteFile(File file) throws IOException {
        new FileOutputStream(file).close();
        if (file.delete()) {
            return;
        }
        throw new IOException("Failed to delete " + file);
    }

    private void usage(PrintStream printStream) throws DumpUsageException {
        printStream.println("Usage: dumpapp hprof [ path ]");
        printStream.println("Dump HPROF memory usage data from the running application.");
        printStream.println();
        printStream.println("Where path can be any of:");
        printStream.println("  -           Output directly to stdout");
        printStream.println("  <path>      Full path to a writable file on the device");
        printStream.println("  <filename>  Relative filename that will be stored in the app internal storage");
        throw new DumpUsageException("Missing path");
    }

    private void writeHprof(File file) throws DumpException {
        try {
            truncateAndDeleteFile(file);
            Debug.dumpHprofData(file.getAbsolutePath());
        } catch (IOException e) {
            throw new DumpException("Failure writing to " + file + ": " + e.getMessage());
        }
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public void dump(DumperContext dumperContext) throws DumpException {
        PrintStream stdout = dumperContext.getStdout();
        Iterator<String> it = dumperContext.getArgsAsList().iterator();
        String next = it.hasNext() ? it.next() : null;
        if (next == null) {
            usage(stdout);
        } else if ("-".equals(next)) {
            handlePipeOutput(stdout);
        } else {
            File file = new File(next);
            if (!file.isAbsolute()) {
                file = this.mContext.getFileStreamPath(next);
            }
            writeHprof(file);
            stdout.println("Wrote to " + file);
        }
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public String getName() {
        return NAME;
    }
}
