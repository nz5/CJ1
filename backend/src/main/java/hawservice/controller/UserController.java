package hawservice.controller;

import hawservice.exception.UserNotFoundException;
import hawservice.model.UserDTO;
import hawservice.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/v1/user")
public class UserController
{

    private UserService userService;


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws UserNotFoundException
    {
        UserDTO user = userService.deleteUser(id);

        if (user == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws UserNotFoundException
    {
        UserDTO result = userService.updateUser(id, userDTO);

        if (result == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<UserDTO> getUser(@RequestParam Long id) throws UserNotFoundException
    {
        UserDTO user = userService.getUser(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
