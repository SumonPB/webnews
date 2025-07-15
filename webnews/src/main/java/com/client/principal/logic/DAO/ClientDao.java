package com.client.principal.logic.DAO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDao {
    private String name;
    private String nickname;
    private String email;
}
