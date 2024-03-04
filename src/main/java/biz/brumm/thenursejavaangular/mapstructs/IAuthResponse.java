package biz.brumm.thenursejavaangular.mapstructs;

import biz.brumm.thenursejavaangular.dto.AuthResponse;
import biz.brumm.thenursejavaangular.mapstructs.dto.AuthResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IAuthResponse {

    public AuthResponse toAuthResponse(AuthResponseDTO authResponseDTO);
}
