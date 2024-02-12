package biz.brumm.thenursejavaangular.entity;

import biz.brumm.thenursejavaangular.entity.dto.MandantDTO;
import org.mapstruct.Mapper;

@Mapper
public interface IMandantMapper {

    public Mandant toMandant(MandantDTO mandantDTO);


}
