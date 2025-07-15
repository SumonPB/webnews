package com.client.principal.logic.data.NetWork;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminEP {
    private String id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private Boolean role;
}
