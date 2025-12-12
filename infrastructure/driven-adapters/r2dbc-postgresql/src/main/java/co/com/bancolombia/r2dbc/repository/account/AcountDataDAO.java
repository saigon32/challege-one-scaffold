package co.com.bancolombia.r2dbc.repository.account;

import co.com.bancolombia.r2dbc.repository.account.data.AccountData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

// TODO: This file is just an example, you should delete or modify it
public interface AcountDataDAO extends ReactiveCrudRepository<AccountData, Long>, ReactiveQueryByExampleExecutor<AccountData> {

    //si quisieramos hacer un query asi lo nombramos
//    @Query("SELECT * FROM account WHERE status = :status")
//    Flux<AccountData> findByStatus(String status);
}
