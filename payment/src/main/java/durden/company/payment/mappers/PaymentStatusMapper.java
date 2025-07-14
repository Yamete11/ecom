package durden.company.payment.mappers;

import durden.company.payment.DTOs.PaymentStatusDTO;
import durden.company.payment.entites.PaymentStatus;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentStatusMapper {

    public static PaymentStatusDTO toDTO(PaymentStatus entity) {
        PaymentStatusDTO dto = new PaymentStatusDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public static List<PaymentStatusDTO> toDTOList(List<PaymentStatus> entities) {
        return entities.stream()
                .map(PaymentStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static PaymentStatus toEntity(PaymentStatusDTO dto) {
        PaymentStatus entity = new PaymentStatus();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
