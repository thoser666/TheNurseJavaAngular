package biz.brumm.thenursejavaangular.model;

import biz.brumm.thenursejavaangular.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import biz.brumm.thenursejavaangular.repository.MyRepository;

import java.util.List;

/**
 * @author UrosVesic
 */
public interface MyEntity {

    public default List<MyRepository> returnChildRepositories(@Autowired ApplicationContext context){
        throw new UnsupportedOperationException();
    }
}
