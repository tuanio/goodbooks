package iuh.fivet.app_dev.goodbooks;

public class BookModel {

    private String authors;
    private String book_url;
    private String desc;
    private String genres;
    private int id;
    private String image_url;
    private String isbn;
    private String isbn13;
    private int pages;
    private float rating;
    private int reviews;
    private String title;
    private int total_ratings;

    public BookModel(String authors, String book_url, String desc, String genres, int id, String image_url, String isbn, String isbn13, int pages, float rating, int reviews, String title, int total_ratings) {
        this.authors = authors;
        this.book_url = book_url;
        this.desc = desc;
        this.genres = genres;
        this.id = id;
        this.image_url = image_url;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.pages = pages;
        this.rating = rating;
        this.reviews = reviews;
        this.title = title;
        this.total_ratings = total_ratings;
    }

    public BookModel() {
    }

    @Override
    public String toString() {
        return  "Authors \uD83D\uDC49 " + authors + "\n" +
                "\nBook's link \uD83D\uDC49 " + book_url + "\n" +
                "\nGenres \uD83D\uDC49 " + genres + "\n" +
                "\nisbn \uD83D\uDC49 " + isbn + "\n" +
                "\nPages \uD83D\uDC49 " + pages + "\n" +
                "\nRating \uD83D\uDC49 " + rating + "\n" +
                "\nReviews \uD83D\uDC49 " + reviews + "\n" +
                "\nTotal_ratings \uD83D\uDC49 " + total_ratings;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal_ratings() {
        return total_ratings;
    }

    public void setTotal_ratings(int total_ratings) {
        this.total_ratings = total_ratings;
    }

}
