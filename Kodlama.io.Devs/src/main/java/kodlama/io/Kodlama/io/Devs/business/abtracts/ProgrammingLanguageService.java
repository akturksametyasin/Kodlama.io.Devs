package kodlama.io.Kodlama.io.Devs.business.abtracts;

import java.util.List;

import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.CreateProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.DeleteProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.UpdateProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageResponses.GetAllProgrammingLanguagesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageResponses.GetProgrammingLanguageResponse;

public interface ProgrammingLanguageService {
	List<GetAllProgrammingLanguagesResponse> getAll();
	void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception;
	void update(int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception;
	void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) throws Exception;
	GetProgrammingLanguageResponse get(int id) throws Exception;
	
	/*
	 * public void add(ProgrammingLanguage programmingLanguage) throws Exception;
	 * public void delete(int id) throws Exception; public void
	 * update(ProgrammingLanguage programmingLanguage) throws Exception; public
	 * ProgrammingLanguage get(int id) throws Exception;
	 */
	
}
