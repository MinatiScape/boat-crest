package com.google.mlkit.common.sdkinternal.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzj;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.LocalModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.jose4j.jwk.RsaJsonWebKey;
@KeepForSdk
/* loaded from: classes10.dex */
public class LocalModelLoader {

    /* renamed from: a  reason: collision with root package name */
    public MappedByteBuffer f11597a;
    public final Context b;
    public final LocalModel c;

    @KeepForSdk
    public LocalModelLoader(@NonNull Context context, @NonNull LocalModel localModel) {
        this.b = context;
        this.c = localModel;
    }

    @NonNull
    @KeepForSdk
    public LocalModel getLocalModel() {
        return this.c;
    }

    @NonNull
    @KeepForSdk
    @WorkerThread
    public MappedByteBuffer load() throws MlKitException {
        FileChannel channel;
        Preconditions.checkNotNull(this.b, "Context can not be null");
        Preconditions.checkNotNull(this.c, "Model source can not be null");
        MappedByteBuffer mappedByteBuffer = this.f11597a;
        if (mappedByteBuffer != null) {
            return mappedByteBuffer;
        }
        String absoluteFilePath = this.c.getAbsoluteFilePath();
        String assetFilePath = this.c.getAssetFilePath();
        Uri uri = this.c.getUri();
        if (absoluteFilePath != null) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(absoluteFilePath, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
                FileChannel channel2 = randomAccessFile.getChannel();
                this.f11597a = channel2.map(FileChannel.MapMode.READ_ONLY, 0L, channel2.size());
                channel2.close();
                randomAccessFile.close();
                return this.f11597a;
            } catch (IOException e) {
                throw new MlKitException("Can not open the local file: ".concat(String.valueOf(this.c.getAbsoluteFilePath())), 14, e);
            }
        } else if (assetFilePath == null) {
            if (uri != null) {
                try {
                    AssetFileDescriptor zza = zzj.zza(this.b, uri, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
                    channel = zza.createInputStream().getChannel();
                    try {
                        this.f11597a = channel.map(FileChannel.MapMode.READ_ONLY, zza.getStartOffset(), zza.getLength());
                        channel.close();
                        zza.close();
                        return this.f11597a;
                    } finally {
                    }
                } catch (IOException e2) {
                    throw new MlKitException("Can not load the file from URI: ".concat(uri.toString()), 14, e2);
                }
            }
            throw new MlKitException("Can not load the model. One of filePath, assetFilePath or URI must be set for the model.", 14);
        } else {
            try {
                AssetFileDescriptor openFd = this.b.getAssets().openFd(assetFilePath);
                channel = new FileInputStream(openFd.getFileDescriptor()).getChannel();
                try {
                    this.f11597a = channel.map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
                    channel.close();
                    openFd.close();
                    return this.f11597a;
                } finally {
                }
            } catch (IOException e3) {
                throw new MlKitException("Can not load the file from asset: " + assetFilePath + ". Please double check your asset file name and ensure it's not compressed. See documentation for details how to use aaptOptions to skip file compression", 14, e3);
            }
        }
    }
}
