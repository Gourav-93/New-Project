package auth.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auth.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
