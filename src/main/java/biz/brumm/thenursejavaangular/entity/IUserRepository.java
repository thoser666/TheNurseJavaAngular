package biz.brumm.thenursejavaangular.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<Mandant, Long>
{

}

