package ncl.yujiaqi.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yujiaqi
 * @Since 04/02/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String account;
    private String password;
}
