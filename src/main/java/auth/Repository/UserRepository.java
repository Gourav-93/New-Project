package auth.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auth.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
}
