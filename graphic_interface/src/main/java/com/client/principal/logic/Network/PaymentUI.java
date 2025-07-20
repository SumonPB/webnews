package com.client.principal.logic.Network;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.PaymentEP;

@FeignClient(name = "PaymentConsole", url = "http://localhost:9093")
public interface PaymentUI {
    @GetMapping("GetBillById")
    public PaymentEP getBillById(
            @RequestParam("billId") String billId);

}
