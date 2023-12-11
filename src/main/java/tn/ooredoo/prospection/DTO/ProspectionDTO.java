package tn.ooredoo.prospection.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.ooredoo.prospection.entity.Prospection;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProspectionDTO {
	private long id;
	private String fullName;
	private Long numID;
	private String zone;
	private String access;
	
	public static ProspectionDTO toDto(Prospection p) {
		return ProspectionDTO
				.builder()
				.id(p.getId())
				.fullName(p.getFullName())
				.numID(p.getNumID())
				.zone(p.getZone())
				.access(p.getAccess())
				.build();
	}
}
