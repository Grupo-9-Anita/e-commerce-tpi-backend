package tpi.backend.e_commerce.services.JwtService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;
import tpi.backend.e_commerce.dto.auth.response.JwtAuthenticationResponse;
import tpi.backend.e_commerce.enums.Role;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.interfaces.ISaveUserService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class SaveUserService implements ISaveUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Validation validation;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseEntity<?> signUpAdmin(SignUpRequest userDto, BindingResult result) {
        // Si hay algun error de validacion, retornara un 400 con los errores
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        // Chequea que el email no exista en la BD
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return validation.validate(
                    "email",
                    "Ya existe un usuario con ese email",
                    409);
        }

        // Si no hay errores, guarda al usuario en la BD y retorna el JWT
        var user = User.builder().firstName(userDto.getFirstName()).lastName(userDto.getLastName())
                .email(userDto.getEmail()).password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.ADMIN).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return ResponseEntity
                .ok(JwtAuthenticationResponse.builder().token(jwt).firstName(user.getFirstName())
                        .lastName(user.getLastName()).email(user.getEmail()).role(user.getRole()).build());
    }
}
    
