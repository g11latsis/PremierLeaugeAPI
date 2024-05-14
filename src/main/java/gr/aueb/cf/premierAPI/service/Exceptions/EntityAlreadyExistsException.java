package gr.aueb.cf.premierAPI.service.Exceptions;

public class EntityAlreadyExistsException extends Exception{
    public EntityAlreadyExistsException(Class<?> entityClass, Long id){
        super("Entity of type " + entityClass.getSimpleName() + " with id " + id + " already exists.");
    }
}
