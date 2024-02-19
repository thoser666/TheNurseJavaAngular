package biz.brumm.thenursejavaangular.entity;

import biz.brumm.thenursejavaangular.entity.dto.MandantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMandantMapper {

  public Mandant toMandant(MandantDTO mandantDTO);
}
