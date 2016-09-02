package com.photostudio.repositories.customers;



import com.photostudio.domain.customers.Funeral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AP on 8/21/2016.
 */
@Repository
public interface FuneralRepository extends CrudRepository<Funeral,Long> {
}
