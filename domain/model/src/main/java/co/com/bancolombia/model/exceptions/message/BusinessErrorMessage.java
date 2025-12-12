package co.com.bancolombia.model.exceptions.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessErrorMessage {

    INVALID_REQUEST("SF00000", "Invalid Request"),
    ACCOUNT_VALIDATION_ERROR("SF00001", "Account validation error"),
    ACCOUNT_FIND_ERROR("SF00002", "Account find error"),
    CHANNEL_TRANSACTION_NOT_FOUND("SF00003", "Channel transaction not found");

    private final String code;
    private final String message;
}
