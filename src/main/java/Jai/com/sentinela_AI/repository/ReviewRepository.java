package Jai.com.sentinela_AI.repository;

import Jai.com.sentinela_AI.model.Review;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends CosmosRepository<Review, String> {

}
