package co.com.bancolombia.r2dbc.repository.account;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.r2dbc.repository.account.data.AccountData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Primary
@Repository
@RequiredArgsConstructor
public class AccountDataRepository implements AccountRepository {

    private final AcountDataDAO repository;   // ✔️ Spring inyecta
    private final ObjectMapper objectMapper;  // ✔️ Spring inyecta


    @Override
    public Mono<Account> findById(long id) {
        //asi llamariamos al query que definimos,
        // pero se comenta para seguir con el ejercicio
        //repository.findByStatus("ACTIVE");
        return repository.findById(id).map(this::toEntity);
    }

    //de objeto AccountData a entidad
    private Account toEntity(AccountData accountData) {
        return objectMapper.mapBuilder(accountData, Account.AccountBuilder.class).build();
    }

    //de entidad a objeto AccountData
    private AccountData toAccountData(Account account) {
        return objectMapper.mapBuilder(account, AccountData.class);
    }
}
