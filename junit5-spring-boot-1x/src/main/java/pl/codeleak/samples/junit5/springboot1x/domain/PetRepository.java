package pl.codeleak.samples.junit5.springboot1x.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, ObjectId> {
}
