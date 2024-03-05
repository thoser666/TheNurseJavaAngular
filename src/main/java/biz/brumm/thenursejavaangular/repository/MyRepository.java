package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.MyEntity;
import org.springframework.stereotype.Repository;

/**
 * @author UrosVesic
 */
@Repository
public interface MyRepository {

  default void deleteByParent(MyEntity parent) {}
}
