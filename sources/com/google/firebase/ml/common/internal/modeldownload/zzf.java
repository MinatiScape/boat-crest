package com.google.firebase.ml.common.internal.modeldownload;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzne;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseLocalModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes10.dex */
public class zzf {

    /* renamed from: a  reason: collision with root package name */
    public MappedByteBuffer f11392a;
    public final Context b;
    public final FirebaseLocalModel c;

    public zzf(@NonNull Context context, @NonNull FirebaseLocalModel firebaseLocalModel) {
        this.b = context;
        this.c = firebaseLocalModel;
    }

    @NonNull
    @WorkerThread
    public MappedByteBuffer load() throws FirebaseMLException {
        Preconditions.checkNotNull(this.b, "Context can not be null");
        Preconditions.checkNotNull(this.c, "Model source can not be null");
        MappedByteBuffer mappedByteBuffer = this.f11392a;
        if (mappedByteBuffer != null) {
            return mappedByteBuffer;
        }
        if (this.c.getFilePath() != null) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(this.c.getFilePath(), RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
                FileChannel channel = randomAccessFile.getChannel();
                this.f11392a = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                channel.close();
                randomAccessFile.close();
                return this.f11392a;
            } catch (IOException e) {
                String valueOf = String.valueOf(this.c.getFilePath());
                throw new FirebaseMLException(valueOf.length() != 0 ? "Can not open the local file: ".concat(valueOf) : new String("Can not open the local file: "), 14, e);
            }
        } else if (this.c.getAssetFilePath() != null) {
            String assetFilePath = this.c.getAssetFilePath();
            try {
                AssetFileDescriptor openFd = this.b.getAssets().openFd(assetFilePath);
                FileInputStream fileInputStream = new FileInputStream(openFd.getFileDescriptor());
                try {
                    FileChannel channel2 = fileInputStream.getChannel();
                    this.f11392a = channel2.map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
                    channel2.close();
                    fileInputStream.close();
                    openFd.close();
                    return this.f11392a;
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        zzne.zza(th, th2);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                StringBuilder sb = new StringBuilder(String.valueOf(assetFilePath).length() + 186);
                sb.append("Can not load the file from asset: ");
                sb.append(assetFilePath);
                sb.append(". Please double check your asset file name and ensure it's not compressed. See documentation for details how to use aaptOptions to skip file compression");
                throw new FirebaseMLException(sb.toString(), 14, e2);
            }
        } else {
            throw new FirebaseMLException("Can not load the model. Either filePath or assetFilePath must be set for the model.", 14);
        }
    }

    public final FirebaseLocalModel zzot() {
        return this.c;
    }
}
