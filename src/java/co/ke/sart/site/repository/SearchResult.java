 package co.ke.sart.site.repository;

public class SearchResult<T>
{
    private final T entity;

    private final double relevance;

    public SearchResult(T entity, double relevance)
    {
        this.entity = entity;
        this.relevance = relevance;
    }

    public double getRelevance()
    {
        return relevance;
    }

    public T getEntity()
    {
        return entity;
    }
}
