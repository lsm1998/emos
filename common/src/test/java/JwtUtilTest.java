import com.auth0.jwt.interfaces.Claim;
import com.lsm1998.common.jwt.JwtUtil;
import org.junit.Test;

import java.util.Map;

public class JwtUtilTest
{
    @Test
    public void testSign()
    {
        String tokenSecret = "asdagfgdokadksaopd";
        String token = JwtUtil.signature(tokenSecret, Map.of("user_id", 100, "username", "lsm1998"));
        System.out.println(token);

        Map<String, Claim> claimMap = JwtUtil.parse(tokenSecret, token);
        System.out.println(claimMap);
    }
}
