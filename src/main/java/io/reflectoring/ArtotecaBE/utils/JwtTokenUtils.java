package io.reflectoring.ArtotecaBE.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import io.reflectoring.ArtotecaBE.user.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JwtTokenUtils {

    public boolean validateJwtToken(String token) throws FirebaseAuthException {

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

            return true;
    }

    public UserDetailsImpl getUserDetails(String token) throws FirebaseAuthException {
        try {
            if (validateJwtToken(token)) {
                UserDetailsImpl user = new UserDetailsImpl();
                return user;
            }
        }
        catch(FirebaseAuthException e){
            return null;
        }
        return null;
    }
}
