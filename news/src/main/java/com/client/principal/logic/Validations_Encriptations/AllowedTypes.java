package com.client.principal.logic.Validations_Encriptations;

import java.util.List;

import com.client.principal.logic.data.NetWork.SubscriptionEP;
import com.client.principal.logic.data.NetWork.subscriptionTypes;

public class AllowedTypes {

    public static List<subscriptionTypes> tiposPermitidos(SubscriptionEP userSub) {
        if (Boolean.TRUE.equals(userSub.getContAnt())) {
            return List.of(subscriptionTypes.values()); // Todas
        } else if (Boolean.TRUE.equals(userSub.getFullcat())) {
            return List.of(
                    subscriptionTypes.FREE,
                    subscriptionTypes.TITULAR,
                    subscriptionTypes.REDACCIONPlus);
        } else if (Boolean.TRUE.equals(userSub.getCat2())) {
            return List.of(
                    subscriptionTypes.FREE,
                    subscriptionTypes.TITULAR);
        } else {
            return List.of(subscriptionTypes.FREE);
        }
    }

}
