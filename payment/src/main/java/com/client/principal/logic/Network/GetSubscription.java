package com.client.principal.logic.Network;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.SubscriptionEP;

@FeignClient(name = "GetSubscription", url = "http://localhost:9092")
public interface GetSubscription {
    @GetMapping("/GetSubscriptionByName")
    public SubscriptionEP GetSubscriptionByName(@RequestParam String name);
}
