package Jai.com.sentinela_AI.repository;

import Jai.com.sentinela_AI.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
