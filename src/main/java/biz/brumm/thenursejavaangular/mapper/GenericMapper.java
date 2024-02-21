package biz.brumm.thenursejavaangular.mapper;

import biz.brumm.thenursejavaangular.dto.Dto;
import biz.brumm.thenursejavaangular.model.MyEntity;

/**
 * @author UrosVesic
 */
public interface GenericMapper<D extends Dto, E extends MyEntity> {

  E toEntity(D dto);

  D toDto(E entity);
}
