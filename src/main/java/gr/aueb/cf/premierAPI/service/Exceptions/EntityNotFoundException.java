package gr.aueb.cf.premierAPI.service.Exceptions;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(Class<?> entityClass, Long id){
        super("Entity of type " + entityClass.getSimpleName() + " with id " + id + " not found.");
    }
}
