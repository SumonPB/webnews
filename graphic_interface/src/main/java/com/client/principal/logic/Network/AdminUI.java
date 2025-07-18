package com.client.principal.logic.Network;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.ClientDAOEP;

@FeignClient(name = "AdminConsole", url = "http://localhost:9090")
public interface AdminUI {

    @GetMapping("/InsertAdmin")
    public String insertAdmin(
            @RequestParam("name") String name,
            @RequestParam("nickname") String nickname,
            @RequestParam("email") String email,
            @RequestParam("password") String password);

    @GetMapping("/GetAllClients")
    public List<ClientDAOEP> getAllCustomers();

    @GetMapping("/updateCli")
    public String updateCli(
            @RequestParam("email") String email,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "subscriptionName", required = false) String subscriptionName);

    @GetMapping("/validateAdmin")
    public Boolean validationAdmin(
            @RequestParam("email") String email,
            @RequestParam("password") String password);

}
