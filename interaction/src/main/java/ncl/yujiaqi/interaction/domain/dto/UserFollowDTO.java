package ncl.yujiaqi.interaction.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ncl.yujiaqi.system.domain.dto.UserDTO;

import java.util.List;

/**
 * @Author yujiaqi
 * @Since 11/02/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserFollowDTO {
    private Long userId;
    private List<Long> followerIds;
    private List<UserDTO> followUsers;
    private boolean isFollowed;
}
