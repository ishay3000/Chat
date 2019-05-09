package SqlMappings;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "ishay_chat", catalog = "")
public class MySqlUsersEntity {
    private String username;
    private String password;

    public MySqlUsersEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MySqlUsersEntity() {
    }

    @Id
    @Column(name = "username", nullable = false, length = 16)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySqlUsersEntity that = (MySqlUsersEntity) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
