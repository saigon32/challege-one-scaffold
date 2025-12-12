package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.RegisterAccountRequest;
import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.exceptions.BusinessException;
import co.com.bancolombia.model.exceptions.message.BusinessErrorMessage;
import co.com.bancolombia.usecase.getstatus.GetStatusUseCase;
import co.com.bancolombia.usecase.registeraccount.RegisterAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;

    private final RegisterAccountUseCase registerAccountUseCase;
    private final GetStatusUseCase getStatusUseCase;
    private final AccountRepository accountRepository;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterAccountRequest.class)
                //asi se evaluaria siempre la excepcion incluso en los casos exitosos
                //.switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.INVALID_REQUEST)))
                //asi solo en los casos que sea requerido evaluarla! con la funcion supplier de error
                .switchIfEmpty(Mono.error(() -> new BusinessException(BusinessErrorMessage.INVALID_REQUEST)))
                .flatMap(request ->
                        registerAccountUseCase.register(
                                request.getName(), request.getStatusId()))
                .flatMap(account -> ServerResponse.ok().bodyValue(account))
                .onErrorResume(BusinessException.class, error -> ServerResponse.badRequest()
                        .bodyValue(error.getErrorMessage()));
    }

    //lamamos asi desde la url: http://localhost:8080/api/otherusercase/path?id=4000
    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        var id = serverRequest.queryParam("id").orElse("4000");
        return accountRepository.findById(Long.valueOf(id))
                .flatMap(account -> ServerResponse.ok().bodyValue(account))
                .switchIfEmpty(Mono.defer(() -> ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();

        return ServerResponse.ok().bodyValue("");
    }
}
