package com.batuhan.simsek.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.batuhan.simsek.jpa.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("select new com.batuhan.simsek.jpa.repository.Balance(a.balance) from Account a where a.customerIdentityNo = ?1")
	Optional<Balance> findBalance(Integer customerIdentityNo);
	
	
	Optional<Account> findByCustomerIdentityNo(Integer id); 
	Optional<Account> findByIban(String iban);

}


