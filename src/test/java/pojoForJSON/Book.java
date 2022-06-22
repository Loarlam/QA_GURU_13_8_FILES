package pojoForJSON;

import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private String genre;
    private Detail detail;
    private ArrayList<Price> price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public ArrayList<Price> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Price> price) {
        this.price = price;
    }

    static class Detail{
        private String publisher;
        private int publication_Year;
        private long isbn;
        private String language;
        private int pages;

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public int getPublication_Year() {
            return publication_Year;
        }

        public void setPublication_Year(int publication_Year) {
            this.publication_Year = publication_Year;
        }

        public long getIsbn() {
            return isbn;
        }

        public void setIsbn(long isbn) {
            this.isbn = isbn;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }

    static class Price{
        private String type;
        private double cost;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }
    }
}