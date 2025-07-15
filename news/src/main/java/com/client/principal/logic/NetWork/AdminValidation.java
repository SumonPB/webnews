package com.client.principal.logic.NetWork;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "adminValidation", url = "http://localhost:9090")
public interface AdminValidation {
    @GetMapping("/validate")
    Boolean getAdminByName(
            @RequestParam("email") String email,
            @RequestParam("password") String password);

}
