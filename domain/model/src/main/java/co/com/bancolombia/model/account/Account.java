package co.com.bancolombia.model.account;
import co.com.bancolombia.model.exceptions.BusinessException;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import static co.com.bancolombia.model.exceptions.message.BusinessErrorMessage.ACCOUNT_VALIDATION_ERROR;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account {

    public final long id;
    public final String name;
    public final String status;

    public static Account newAccount(long id, String name, String status){
        if (name.equals("error"))
            throw new BusinessException(ACCOUNT_VALIDATION_ERROR);
        return new Account(id, name, status);
    }

}
