package com.dchalbyshev.webPage.repo;

import com.dchalbyshev.webPage.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface Postrepository extends CrudRepository<Post,Long> {
}
