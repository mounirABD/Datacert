package com.dataprotect.repository;

import com.dataprotect.models.Author;
import org.springframework.data.repository.CrudRepository;


public interface AuthorRepository extends CrudRepository<Author, Long> {
}
