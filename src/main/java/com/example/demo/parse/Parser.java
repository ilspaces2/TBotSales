package com.example.demo.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Parser {


    public Set<String> getSales(int numsPages, int sizeOfSale) throws IOException {
        Set<String> links = new HashSet<>();
        String sales, url;

        for (int i = 0; i < numsPages; i++) {
            Document doc2 = Jsoup.connect("https://www.wildberries.ru/catalog/zhenshchinam/odezhda/bluzki-i-rubashki?sort=popular&page=" + i).userAgent("Chrome/95.0.4638.69 Safari/537.36").timeout(10000).get();
            Elements listNews = doc2.select("div.product-card-list");
            for (Element element : listNews.select("a")) {
                sales = element.select("span.product-card__sale").text();
                url = element.absUrl("href");
                if (url.equals("https://www.wildberries.ru/lk/basket") || sales.equals("")) continue;
                if (Integer.parseInt(sales.substring(1, sales.length() - 1)) > sizeOfSale) links.add(url + " Скидка " + sales);
            }
        }
        return links;
    }
}





