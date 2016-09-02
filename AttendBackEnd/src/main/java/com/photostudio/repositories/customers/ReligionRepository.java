package com.photostudio.repositories.customers;

import com.photostudio.domain.customers.Religion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AP on 8/24/2016.
 */
@Repository
public interface ReligionRepository extends CrudRepository<Religion,Long> {

}
