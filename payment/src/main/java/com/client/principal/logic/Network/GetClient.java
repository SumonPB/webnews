package com.client.principal.logic.Network;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.UserEP;

@FeignClient(name = "GetClient", url = "http://localhost:9090")
public interface GetClient {
    @GetMapping("/GetClientByName")
    public UserEP getClientByName(@RequestParam("name") String name);
}
