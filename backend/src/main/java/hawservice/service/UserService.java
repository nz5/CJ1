package hawservice.service;

import hawservice.exception.AppException;
import hawservice.exception.UserNotFoundException;
import hawservice.model.Payload.SignUpRequest;
import hawservice.model.Role;
import hawservice.model.RoleName;
import hawservice.model.UserDTO;
import hawservice.repository.RoleRepository;
import hawservice.repository.UserRepository;
import hawservice.security.UserPrincipal;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class UserService implements UserDetailsService
{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @NonNull
    @Transactional
    public UserDTO createUser(@NonNull SignUpRequest signUpRequest)
    {

        UserDTO user = UserDTO.builder()
            .firstName(signUpRequest.getFirstName())
            .lastName(signUpRequest.getLastName())
            .email(signUpRequest.getEmail())
            .gradYear(signUpRequest.getGradYear())
            .registrationCode(signUpRequest.getRegistrationCode())
            .password(signUpRequest.getPassword())
            .build();

        user.setDateCreated(DateTime.now());
        user.setEnabled(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.USER)
            .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }


    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException
    {
        return userRepository.findByIdAndEnabledIsTrue(id)
            .map(oldUser ->
            {
                oldUser.setEnabled(userDTO.isEnabled());
                oldUser.setRegistrationCode(userDTO.getRegistrationCode());
                oldUser.setEmail(userDTO.getEmail());
                oldUser.setFirstName(userDTO.getFirstName());
                oldUser.setLastName(userDTO.getLastName());
                oldUser.setGradYear(userDTO.getGradYear());
                oldUser.setGeoLocationNew(userDTO.getGeoLocationNew());
                oldUser.setGeoLocationOld(userDTO.getGeoLocationOld());
                oldUser.setRoles(userDTO.getRoles());

                return userRepository.save(oldUser);
            })
            .orElseThrow(() -> {
                log.info("Cannot delete user with id {} because it does not exist. Ignoring request", id);
                return new UserNotFoundException("No user available with id: " + id);
            });
    }


    @Transactional
    public UserDTO deleteUser(Long id) throws UserNotFoundException
    {
        return userRepository.findByIdAndEnabledIsTrue(id)
            .map(user ->
            {
                user.setEnabled(false);
                user = userRepository.save(user);
                log.debug("Deleted user: {}", id);
                return user;
            })
            .orElseThrow(() -> {
                log.info("Cannot delete user with id {} because it does not exist. Ignoring request", id);
                return new UserNotFoundException("No user available with id: " + id);
            });


    }


    @NonNull
    @Transactional
    public UserDTO getUser(Long id) throws UserNotFoundException
    {
        return userRepository.findByIdAndEnabledIsTrue(id)
            .orElseThrow(() -> new UserNotFoundException("No user available with id: " + id));
    }


    public List<UserDTO> getUsers()
    {
        return userRepository.findAllByEnabledIsTrue();
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDTO user = userRepository.findByEmail(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username)
            );

        return UserPrincipal.create(user);
    }


    @Transactional
    public UserDetails loadUserById(Long id)
    {
        UserDTO user = userRepository.findByIdAndEnabledIsTrue(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}
