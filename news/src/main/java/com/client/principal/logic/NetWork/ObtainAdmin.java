package com.client.principal.logic.NetWork;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.client.principal.logic.data.NetWork.AdminEP;

@FeignClient(name = "ObtainAdmin", url = "http://localhost:9090")
public interface ObtainAdmin {

    @GetMapping("/GetAdminByName")
    AdminEP getAdminByName(@PathVariable("name") String name);

}
