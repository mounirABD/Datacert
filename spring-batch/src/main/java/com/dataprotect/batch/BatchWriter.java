package com.dataprotect.batch;

import com.dataprotect.models.Author;
import com.dataprotect.repository.AuthorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchWriter implements ItemWriter<Author> {

    private final AuthorRepository authorRepository;

    @Autowired
    public BatchWriter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void write(List<? extends Author> list) throws Exception {
        authorRepository.saveAll(list);
    }
}
