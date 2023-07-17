package com.lab.sso.security;

import com.lab.sso.repository.jpql.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import javax.validation.constraints.AssertTrue;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(FinalClassToMock.class)
@RunWith( MockitoJUnitRunner.class)
public class JWTServiceTest {

    private Integer JWT_TOKEN_VALIDITY;

    private String secretKey;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JWTService jwtService;

    @Before
    public void setUp() throws Exception {
        JWT_TOKEN_VALIDITY = 5 * 60 * 60;
        secretKey = "2prPhsLs8I8Dd/8D10SH9tN/j66nJGFaLoCHijNNV7W+uv/cwSrzGUV7sRDHzXsvhW7P7J1X1wvQnjxrJWVu3g==";
        MockitoAnnotations.openMocks(this);
        jwtService = new JWTService(secretKey, userRepository);
    }

    @Test
    public void generateToken() {
        Map<String, String> claims = new HashMap<>();
        claims.put("id", UUID.randomUUID().toString());

        String token = jwtService.generateToken(claims);
        assertNotNull( token );
    }

    @Test
    public void testGenerateToken() {
    }

    @Test
    public void validateToken() {
    }
}