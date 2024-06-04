package com.mobabuild.api_build.utils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
public class BlobUtils {

    public static byte[] blobToBytes(Blob blob) {
        if (blob == null) {
            return null;
        }

        try (InputStream inputStream = blob.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (SQLException | IOException e) {
            // Manejar la excepción apropiadamente según tus requerimientos
            e.printStackTrace();
            return null;
        }
    }

    public static Blob bytesToBlob(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return new SerialBlob(bytes);
        } catch (SQLException e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }
}
