package biz.brumm.thenursejavaangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import biz.brumm.thenursejavaangular.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByToken(String token);
}
