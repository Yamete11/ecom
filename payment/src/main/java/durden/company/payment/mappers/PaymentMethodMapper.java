package durden.company.payment.mappers;

import durden.company.payment.DTOs.PaymentMethodDTO;
import durden.company.payment.entites.PaymentMethod;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodMapper {

    public static PaymentMethodDTO toDTO(PaymentMethod entity) {
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public static List<PaymentMethodDTO> toDTOList(List<PaymentMethod> entities) {
        return entities.stream().map(PaymentMethodMapper::toDTO).collect(Collectors.toList());
    }

    public static PaymentMethod toEntity(PaymentMethodDTO dto) {
        PaymentMethod entity = new PaymentMethod();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
