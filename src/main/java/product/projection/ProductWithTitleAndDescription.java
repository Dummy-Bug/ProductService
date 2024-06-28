package product.projection;

public interface ProductWithTitleAndDescription {
    String getTitle();

    String getDescription();

    default String toCustomString() {
        return "Title := " + getTitle() + ", Description := " + getDescription();
    }
}
