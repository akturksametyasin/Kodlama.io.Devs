package kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageSubTechnologyResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByProgrammingLanguageIdSubTechnologiesResponse {

	private int programmingLanguageId;
	private String programmingLanguageName;
	private int id;
	private String name;
}
