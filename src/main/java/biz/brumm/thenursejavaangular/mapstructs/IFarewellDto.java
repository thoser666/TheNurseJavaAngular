package biz.brumm.thenursejavaangular.mapstructs;

import biz.brumm.thenursejavaangular.dto.Farewell;
import biz.brumm.thenursejavaangular.mapstructs.dto.FarewellDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IFarewellDto {

    public Farewell toFarewell(FarewellDto farewellDto);
}
