package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author UrosVesic
 */
@Repository
public interface MyRepository{


    public default void deleteByParent(MyEntity parent){}


}
