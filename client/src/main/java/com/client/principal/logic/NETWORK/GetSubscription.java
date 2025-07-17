package com.client.principal.logic.NETWORK;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.network.SubscriptionEP;

@FeignClient(name = "GetSubscription", url = "http://localhost:9092")
public interface GetSubscription {
    @GetMapping("/GetSubscriptionByName")
    public SubscriptionEP GetSubscriptionByName(@RequestParam String name);

    @GetMapping("/findbyid")
    public SubscriptionEP findSubscriptionById(@RequestParam("id") String idParam);
}
