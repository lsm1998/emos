package com.lsm1998.api.mongo.repository;

import com.lsm1998.api.mongo.model.Temp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepository extends MongoRepository<Temp, String>
{

}
