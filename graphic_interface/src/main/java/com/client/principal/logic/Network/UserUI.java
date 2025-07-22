package com.client.principal.logic.Network;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.CategoryNews;
import com.client.principal.logic.data.newtwork.ClientDAOEP;
import com.client.principal.logic.data.newtwork.NewsEP;
import com.client.principal.logic.data.newtwork.PaymentEP;
import com.client.principal.logic.data.newtwork.UserEP;

@FeignClient(name = "validate", url = "http://localhost:9090")
public interface UserUI {

        @GetMapping("/validate")
        public Boolean validation(
                        @RequestParam("email") String email,
                        @RequestParam("password") String password);

        @GetMapping("/GetClientByEmail")
        public UserEP getClientByEmail(@RequestParam("email") String email);

        @GetMapping("/buySubscription")
        public UserEP buySubscription(
                        @RequestParam("email") String email,
                        @RequestParam("subscriptionName") String subscriptionName,
                        @RequestParam("methodPay") String methodPay);

        @GetMapping("/updateClient")
        public String updateClient(
                        @RequestParam("email") String email,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "nickname", required = false) String nickname,
                        @RequestParam(value = "password", required = false) String password);

        @GetMapping("/choseCategories")
        public ClientDAOEP choseCategories(
                        @RequestParam("email") String email,
                        @RequestParam("categories") List<CategoryNews> categoryNews);

        @GetMapping("/InsertClient")

        public String insertCustomer(
                        @RequestParam("name") String name,
                        @RequestParam("nickname") String nickname,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password);

        @GetMapping("/seeNewsNoLog")
        public List<NewsEP> seeNewsNoLog();

        @GetMapping("/seeNewsOnLog")
        public List<NewsEP> seeNewsOnLog(@RequestParam("email") String email);

        @GetMapping("/seeAllBills")
        public List<PaymentEP> seeAllBills(@RequestParam("email") String email);
}
