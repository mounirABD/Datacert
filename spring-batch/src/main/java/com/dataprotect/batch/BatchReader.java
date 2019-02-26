package com.dataprotect.batch;

import org.asynchttpclient.*;
import org.springframework.batch.item.ItemReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BatchReader implements ItemReader {

    private static final String FILE_NAME = "src/main/resources/nvd-data/nvdcve-1.0.json.zip";
    private static final String DIR_NAME = "src/main/resources/nvd-data/";
    //Url of the data to download
    private static final String URL = "https://nvd.nist.gov/feeds/json/cve/1.0/nvdcve-1.0-modified.json.zip";


    @Override
    public Object read() throws IOException {

        URL url = new URL("https://nvd.nist.gov/feeds/json/cve/1.0/nvdcve-1.0-modified.json.zip");

        downloadZipFile();



        return null;
    }

    private void downloadZipFile() throws IOException {
        AsyncHttpClient client = Dsl.asyncHttpClient();

        final FileOutputStream stream = new FileOutputStream(FILE_NAME);

        client.prepareGet(URL).execute(new AsyncCompletionHandler<FileOutputStream>() {

            @Override
            public State onBodyPartReceived(HttpResponseBodyPart bodyPart)
                    throws Exception {
                stream.getChannel().write(bodyPart.getBodyByteBuffer());
                System.out.println("Download file ...");
                return State.CONTINUE;
            }

            @Override
            public FileOutputStream onCompleted(Response response) throws Exception {
                // TODO Auto-generated method stub
                System.out.println("File downloaded !");
                unzipFile();
                return stream;
            }
        });
    }

    private void unzipFile() throws IOException {
        String fileZip = FILE_NAME;
        File destDir = new File(DIR_NAME);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        System.out.println("File Unziped");
        zis.closeEntry();
        zis.close();
    }

    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }

}
