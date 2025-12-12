package co.com.bancolombia.r2dbc.repository.account.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class AccountData {
    @Id
    private long id;
    private String name;
    private String status;

}
