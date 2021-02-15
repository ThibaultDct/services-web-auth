package fr.epsi.webserviceauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String clientId = "webserviceauth";
    private String clientSecret = "authenticator";

    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEoQIBAAKCAQEAsErOoagKbDxHiexcuT3w9hHH7odFj8LqyedzfX9jz8vUKmbH\n" +
            "6rstD2lnXIdwYDlzNIKuNOGKTal6BrYmHpmOz+oW+FmWqg4sLjbVo6Gnr+1BKS2f\n" +
            "e9ec2gHVABP2Lito/SNVlbQs2YZUk75TZzgJxZGskyFOlOTptv5BwQJfaR5/JgkB\n" +
            "eAymdVAmGS9yPzRWxrs9xMgSVMOKaudC0aTaPRnMHauzHGldPFng0ERH7D9fJwk0\n" +
            "XilZx2L1R6C+Qdr1qxLuuqkE3DFpGMCWJZXNnTis+y0MSV+nIQ4gndcV+meKlMpM\n" +
            "3BS0+qSSVAHq2Ir+1CY3Vzgy25Cj7tugklkGVQIBJQKCAQATDwGU75JskOUjqtmX\n" +
            "fFFmtdBs0FqMFRJ2sTzq+QPfHPRXnGijwTVNxjSwDqRdbf6d5J02Jjh3GT2f3Fcl\n" +
            "5xZbqpqzEJqqmb+WSx4D2h/3V+teZcwNY2rnIsrrQG2kIF5gjjLEE3p4YYyaWcPT\n" +
            "zrTyw6P0OvO9EdQTyHXPrTq/QWM/bvRqIZQV2aTyhNF5cZXweX0CGWFs4OMvdxU6\n" +
            "zguwdCdwU9ahT1PfEcQGKsuDkyQn0aov/MxCBmrYeOWrR+IjcBE/unLpZXN+Q9+u\n" +
            "gvECCpr6HxNAXLwmeRiDemcKwGXgQQTUNoZGit6mYOO+a6GVouEQyrGRftrFQvJp\n" +
            "J8SdAoGBANt83SRRKSzlNVpeWbC3D/72xgXuBMiEu58boSGOedkY1X9nRKx2GWKc\n" +
            "ORcBSbTBebx8zv7HgPPJBKrdhJgQAK3N/nNZdfz0QHRDmjFEW6jopDmmdcP2gtpx\n" +
            "z5KT8M+BBhJdidrroDKF+jw44sOvCcc88PQgy1NDNLJI15MQbDV/AoGBAM2eaYhR\n" +
            "LN/F/ZX3iS/kyazfkXODWISg57XYOJzjORUEEd/nX4VSMjLQvHSV7riptJQ55s9a\n" +
            "xgSbou4XvdnBgVwVR9vTtmD5BoiFMo/S/u9hslnLF9Y/gwBLXmtJug1V7cnc5wVF\n" +
            "fChoJcqYHuyIHaDvEqFbGfUWhooFWMs1PPYrAoGAWPtE5TWo6K/zCPXRVXqlnrcS\n" +
            "AmdpuRM3TldPKUeZLn+wf8IU6/iG1PM5vTffnE5vn3DeS5z87TXRdbPAIfimVEyX\n" +
            "lowNPQkTNg2RiZhA1cYZEHPqjbb2x0LlcsZatPz0n6lhZpbgFHuBH1VVAzkfo8yZ\n" +
            "CQZgRFmEEO0ZH/HmrecCgYBeeTdoJUv4HLLIXPnXvCVPbaOxnTaCIGp2JRMXpquG\n" +
            "Mk1m3+2eHtjLPU+rMCGTHYpR8RcFUzhi+2aJEdPE6jSEQSEEIwDExXG0WN/hN242\n" +
            "ooJgm5VUmbi68jJM+Fxm/feh79/EJtguPbB/ra1X64M8GtE1YTVwm6WZXGcRQflq\n" +
            "LwKBgQDFe/kD9XUdwK3KWmySgqUiumpMtWKVEAb5Xz5CND97qZCpQG4rQ60ntsk2\n" +
            "siysvF0k1AIGHVNDMutGD+COTwc2A7QOI4yuIcjTEjiVF5Jkyx9poaEh+N846ymx\n" +
            "aXG4ar8lS6+g0ZPlydqk7JVmH2zr1IaFayvfG2LFWnULh5F0rg==\n" +
            "-----END RSA PRIVATE KEY-----\n";

    private String publicKey = "---- BEGIN SSH2 PUBLIC KEY ----\n" +
            "Comment: \"rsa-key-20210214\"\n" +
            "AAAAB3NzaC1yc2EAAAABJQAAAQEAsErOoagKbDxHiexcuT3w9hHH7odFj8Lqyedz\n" +
            "fX9jz8vUKmbH6rstD2lnXIdwYDlzNIKuNOGKTal6BrYmHpmOz+oW+FmWqg4sLjbV\n" +
            "o6Gnr+1BKS2fe9ec2gHVABP2Lito/SNVlbQs2YZUk75TZzgJxZGskyFOlOTptv5B\n" +
            "wQJfaR5/JgkBeAymdVAmGS9yPzRWxrs9xMgSVMOKaudC0aTaPRnMHauzHGldPFng\n" +
            "0ERH7D9fJwk0XilZx2L1R6C+Qdr1qxLuuqkE3DFpGMCWJZXNnTis+y0MSV+nIQ4g\n" +
            "ndcV+meKlMpM3BS0+qSSVAHq2Ir+1CY3Vzgy25Cj7tugklkGVQ==\n" +
            "---- END SSH2 PUBLIC KEY ----\n";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read","write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }

}
