package com.test.privat.currency.models.repositories;

import com.test.privat.currency.models.entities.Operation;
import com.test.privat.currency.models.entities.Wallet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationsRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT operation from Operation operation " +
            "where operation.destinationWallet = :wallet " +
            "or operation.sourceWallet = :wallet " +
            "ORDER BY operation.transferDate DESC")
    List<Operation> getAllByWallet(@Param("wallet") Wallet wallet, Pageable pageable);

    @Query("SELECT count(operation) from Operation operation " +
            "where operation.destinationWallet = :wallet " +
            "or operation.sourceWallet = :wallet")
    long countByWallet(@Param("wallet") Wallet wallet);

    Operation getByKey(String key);
}
