package org.launchcode.givewise.request;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;
import org.launchcode.givewise.models.Product;
import org.launchcode.givewise.models.User;

@Getter
@Setter
public class FavoriteRequest {
    private Integer userid;
    private Integer productid;

}
