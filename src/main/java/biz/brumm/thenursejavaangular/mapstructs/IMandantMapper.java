package biz.brumm.thenursejavaangular.mapstructs;

import biz.brumm.thenursejavaangular.mapstructs.dto.MandantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMandantMapper {

  Mandant toMandant(MandantDTO mandantDTO);
}
