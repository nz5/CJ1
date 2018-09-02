package hawservice.repository;

import hawservice.model.UserDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long>
{
    Optional<UserDTO> findByIdAndEnabledIsTrue(Long id);

    List<UserDTO> findAllByEnabledIsTrue();

    Optional<UserDTO> findByEmail(String email);

    Boolean existsByEmail(String email);


}


