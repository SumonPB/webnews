package com.client.principal.logic.DTO;

import java.util.ArrayList;
import java.util.List;

import com.client.principal.logic.DAO.NewsDao;
import com.client.principal.logic.NETWORK.GetSubscription;
import com.client.principal.logic.data.network.NewsEP;

public class NewsDTO {

    public static List<NewsDao> toNewsDao(List<NewsEP> newsEPList, GetSubscription getSubscription) {
        List<NewsDao> newsDaoList = new ArrayList<>();

        for (NewsEP newsEP : newsEPList) {
            NewsDao newsDao = NewsDao.builder()
                    .id(newsEP.getId())
                    .title(newsEP.getTitle())
                    .content(newsEP.getContent())
                    .author(newsEP.getAuthor())
                    .category(newsEP.getCategory())
                    .subscriptionName(
                            getSubscription
                                    .findSubscriptionById(newsEP.getSubscriptionId())
                                    .getName())
                    .imgUrl(newsEP.getImgUrl())
                    .build();

            newsDaoList.add(newsDao);
        }

        return newsDaoList;
    }
}
