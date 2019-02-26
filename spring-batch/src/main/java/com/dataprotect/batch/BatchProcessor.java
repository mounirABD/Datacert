package com.dataprotect.batch;

import com.dataprotect.models.Author;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class BatchProcessor implements ItemProcessor<Author, Author> {

    @Override
    public Author process(Author author) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                author.getPassword().getBytes(StandardCharsets.UTF_8));

        author.setPassword(bytesToHex(encodedhash));

        return author;
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte hash1 : hash) {
            String hex = Integer.toHexString(0xff & hash1);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
