package pl.smile.SmileApp.exception;

public class ResourceNotFound extends RuntimeException{
    private String resourceName;
    private Long id;

    public ResourceNotFound(String resourceName, Long id) {
        super(String.format("%s with id: %s not found.", resourceName, id));
        this.resourceName = resourceName;
        this.id = id;
    }
}
