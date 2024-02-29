//package com.dojo.youthbankserver.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Collection;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/auth")
//public class SecurityController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtEncoder jwtEncoder;
//
//    @PostMapping("/login")
//    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
//        // Authenticate user
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        // Retrieve user authorities
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        // Generate JWT claims
//        Instant now = Instant.now();
//        Instant expiry = now.plus(10, ChronoUnit.MINUTES);
//        String scopes = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                .issuedAt(now)
//                .expiresAt(expiry)
//                .subject(username)
//                .claim("scope", scopes)
//                .build();
//
//        // Encode JWT
//        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwtClaimsSet);
//        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
//
//        // Return JWT
//        return Map.of("access-token", jwt);
//    }
//}
