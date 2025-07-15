package com.client.principal.logic.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsUI {

    private String id;
    private String title;
    private String content;
    private String author;
    private CategoryNews category;
    private String subscriptionId;

}
