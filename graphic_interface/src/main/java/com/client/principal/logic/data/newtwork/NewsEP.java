package com.client.principal.logic.data.newtwork;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsEP {
    private String id;
    private String title;
    private String content;
    private String author;
    private CategoryNews category;
    private String subscriptionId;
}
