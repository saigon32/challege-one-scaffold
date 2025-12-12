package co.com.bancolombia.usecase.registeraccount;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RequiredArgsConstructor
public class RegisterAccountUseCase {

    private final AccountRepository accountRepository;


//    public Mono<Account> register(String name, String statusId) {
//        return Mono.empty();
//    }

    public Mono<Account> register(String name, String statusId) {
        //video 4, usando zip cuando hay latecia, para ejecutar procesos en paralelo
        return  getStatus(name)
                .flatMap(status -> Mono.zip(legalValidation(name), disponibilityValidation()))
                .flatMap(disponibility -> getStatus(name))
                .flatMap(status ->
                        finalValidation(Account.newAccount(0, name, statusId))
                )
                .flatMap(this::saveAccount);

        /*   video 3
        return legalValidation(name)
                .flatMap(legal -> disponibilityValidation())
                .flatMap(disponibility -> getStatus(name))
                .flatMap(status ->
                        finalValidation(Account.newAccount(0, name, statusId))
                )
                .flatMap(this::saveAccount); */
    }


//    public Mono<Account> generateAccount(String name, String statusId) {
//        return Mono.just(Account.newAccount(0, name, statusId));
//    }

    public Mono<Integer> disponibilityValidation() {
        return Mono.just(9);
    }

    public Mono<String> legalValidation(String accountName) {
        return Mono.just(accountName).delayElement(Duration.ofSeconds(2));
    }

    public Mono<String> getStatus(String accountName) {
        return Mono.just("OK");
    }

    public Mono<Account> finalValidation(Account account) {
        return Mono.just(account);
    }

    public Mono<Account> saveAccount(Account account) {
        return Mono.just(account);
    }

}
