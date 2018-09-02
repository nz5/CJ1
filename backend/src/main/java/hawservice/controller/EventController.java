package hawservice.controller;

import hawservice.exception.EventNotFoundException;
import hawservice.model.EventDTO;
import hawservice.service.EventService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/v1/event")
public class EventController
{
    private EventService eventService;


    @PostMapping(produces = "application/json")
    public ResponseEntity<EventDTO> createEvent(@RequestBody @Valid EventDTO eventDTO)
    {
        EventDTO event = eventService.createEvent(eventDTO);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(@PathVariable Long id) throws EventNotFoundException
    {
        EventDTO event = eventService.deleteEvent(id);

        if (event == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody @Valid EventDTO eventDTO)
    {
        EventDTO result = eventService.updateEvent(id, eventDTO);

        if (result == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<EventDTO> getEvent(@RequestParam Long id) throws EventNotFoundException
    {
        EventDTO event = eventService.getEvent(id);

        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getAllEvents()
    {
        List<EventDTO> events = eventService.getEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
