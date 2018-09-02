package hawservice.service;

import hawservice.exception.EventNotFoundException;
import hawservice.model.EventDTO;
import hawservice.repository.EventRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EventService
{
    private final EventRepository eventRepositoryRepository;


    @NonNull
    public EventDTO createEvent(@NonNull EventDTO eventDTO)
    {
        eventDTO.setDateCreated(DateTime.now());
        eventDTO.setEnabled(true);

        return eventRepositoryRepository.save(eventDTO);
    }


    @Transactional
    public EventDTO updateEvent(Long id, EventDTO eventDTO)
    {
        return eventRepositoryRepository.findById(id)
            .map(oldEvent ->
            {
                oldEvent.setEnabled(eventDTO.isEnabled());
                oldEvent.setDescription(eventDTO.getDescription());
                oldEvent.setDateOfEvent(eventDTO.getDateOfEvent());
                oldEvent.setLocation(eventDTO.getLocation());
                oldEvent.setImage(eventDTO.getImage());
                oldEvent.setTitle(eventDTO.getTitle());
                oldEvent.setImageName(eventDTO.getImageName());

                return eventRepositoryRepository.save(oldEvent);
            })
            .orElseGet(() ->
            {
                log.info("Cannot update event with id {} because it does not exist. Ignoring request", id);
                return null;
            });
    }


    @Transactional
    public EventDTO deleteEvent(Long id)
    {
        return eventRepositoryRepository.findById(id)
            .map(event ->
            {
                event.setEnabled(false);
                event = eventRepositoryRepository.save(event);
                log.debug("Deleted event: {}", id);
                return event;
            })
            .orElseGet(() ->
            {
                log.info("Cannot delete event with id {} because it does not exist. Ignoring request", id);
                return null;
            });
    }


    @NonNull
    public EventDTO getEvent(Long id) throws EventNotFoundException
    {
        return eventRepositoryRepository.findById(id)
            .orElseThrow(() -> new EventNotFoundException("No event available with id: " + id));
    }


    public List<EventDTO> getEvents()
    {
        return eventRepositoryRepository.findAll();
    }
}
