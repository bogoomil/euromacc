package hu.feladat.elastic.repository;

import hu.feladat.elastic.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String>{

    List<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

}
