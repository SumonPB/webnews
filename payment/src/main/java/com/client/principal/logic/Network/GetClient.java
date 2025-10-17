package com.client.principal.logic.Network;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.UserEP;

@FeignClient(name = "client")
public interface GetClient {
    @GetMapping("/GetClientByEmail")
    public UserEP getClientByEmail(@RequestParam("email") String email);
}
