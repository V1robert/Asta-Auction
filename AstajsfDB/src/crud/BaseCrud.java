package crud;

import java.util.List;




public abstract class BaseCrud<T,P> {

    public abstract T insert(T model,P model1);
	
	public abstract T update(T model,P modeli);
	
	public abstract void delete(Integer id);
	
	public abstract T findById(Integer id);
	
	public abstract List<T> findAll(P mapper);



	

		
	
	
}
