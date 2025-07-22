package com.client.principal.logic.Network;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.SubscriptionEP;

@FeignClient(name = "subscription", url = "http://localhost:9092")
public interface SubscriptionUI {
    @GetMapping("/findbyid")
    public SubscriptionEP findSubscriptionById(@RequestParam("id") String idParam);

}
