package biz.brumm.thenursejavaangular.entity;

import biz.brumm.thenursejavaangular.entity.dto.MandantDTO;
import org.mapstruct.Mapper;

@Mapper
public class IMandantMapper {

    public Mandant toMandant(MandantDTO mandantDTO) {
        Mandant mandant = new Mandant();
        mandant.setName(mandantDTO.getName());
        mandant.setEmail(mandantDTO.getEmail());
        return mandant;
    }

}
