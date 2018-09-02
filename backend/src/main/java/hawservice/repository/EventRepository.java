package hawservice.repository;

import hawservice.model.EventDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventDTO, Long>
{
    Optional<EventDTO> findById(Long id);

    List<EventDTO> findAll();


}
