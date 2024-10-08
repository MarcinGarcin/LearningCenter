package MarcinGarcin.ToDoApp.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public record user(
        @Id
        Integer id,
        String login,
        String password
) {

}
