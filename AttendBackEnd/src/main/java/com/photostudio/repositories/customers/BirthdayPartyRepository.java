package com.photostudio.repositories.customers;



import com.photostudio.domain.customers.BirthdayParty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/08/20.
 */
@Repository
public interface BirthdayPartyRepository extends CrudRepository<BirthdayParty,Long> {
}
