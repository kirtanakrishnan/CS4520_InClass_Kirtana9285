package com.example.inclass_krishnan9285.InClass06;

import java.util.ArrayList;

public class Articles {
    private ArrayList<Article> articles;


    public Articles() {
    }


    public ArrayList<Article> getArticles() {
        return articles;
    }

    public ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Article article : articles) {
            titles.add("\n" + "\n" + "Title: " + article.getTitle() + "\n" + "\n" +
                            "\n" + "\n" + "Author: " + article.getAuthor() + "\n" + "\n" +
                            "\n" + "\n" + "Published At: "+ article.getPublishedAt() + "\n" + "\n" +
                            "\n" + "\n" + "Description: " + article.getDescription() + "\n" + "\n" +
                            "\n" + "\n" + "Url: " + article.getUrlToImage() + "\n" + "\n"

                    );
        }
        return titles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "articles=" + articles +
                '}';
    }


}
