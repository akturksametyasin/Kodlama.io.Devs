package kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Kodlama.io.Devs.business.abtracts.ProgrammingLanguageService;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.CreateProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.DeleteProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.UpdateProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageResponses.GetAllProgrammingLanguagesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageResponses.GetProgrammingLanguageResponse;
import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {
	private ProgrammingLanguageRepository programmingLanguageRepository;
	
	@Autowired
	public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository) {
		this.programmingLanguageRepository = programmingLanguageRepository;
	}
	
	@Override
	public List<GetAllProgrammingLanguagesResponse> getAll() {
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		List<GetAllProgrammingLanguagesResponse> programmingLanguagesResponse = new ArrayList<GetAllProgrammingLanguagesResponse>();
		
		for(ProgrammingLanguage programmingLanguage : programmingLanguages) {
			GetAllProgrammingLanguagesResponse responseItem = new GetAllProgrammingLanguagesResponse();
			responseItem.setId(programmingLanguage.getId());
			responseItem.setName(programmingLanguage.getName());
			
			programmingLanguagesResponse.add(responseItem);
		}
		return programmingLanguagesResponse;
	}
	
	@Override
	public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
		if(isNameExistForCreate(createProgrammingLanguageRequest.getName())){
			throw new Exception("Programming languages' names cannot repeat.");
		}
		else {
			ProgrammingLanguage pLanguage = new ProgrammingLanguage();
			pLanguage.setName(createProgrammingLanguageRequest.getName());
				
			this.programmingLanguageRepository.save(pLanguage);
		}
	}

	@Override
	public void update(int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception{
		if(!isIdExist(id)) {
			throw new Exception("Id couldn't find.");
		}
		if(isNameExist(id, updateProgrammingLanguageRequest.getName())) {
			throw new Exception("Programming languages' names cannot repeat.");
		}
		if(!isAnyChange(id, updateProgrammingLanguageRequest.getName())) {
			throw new Exception("Id and name are the same. There isn't any change.");
		}
		else {
			ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository.findById(id).get();
			programmingLanguage.setName(updateProgrammingLanguageRequest.getName());
		
			this.programmingLanguageRepository.save(programmingLanguage);
		}
	}

	@Override
	public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) throws Exception {
		if(!isIdExist(deleteProgrammingLanguageRequest.getId())) {
			throw new Exception("Id couldn't find.");
		}
		else {
			ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository.findById(deleteProgrammingLanguageRequest.getId()).get();
		
			this.programmingLanguageRepository.delete(programmingLanguage);
		}
	}

	@Override
	public GetProgrammingLanguageResponse get(int id) throws Exception {
		if(!isIdExist(id)) {
			throw new Exception("Id couldn't find.");
		}
		else {
			ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id).get();
			GetProgrammingLanguageResponse responseItem = new GetProgrammingLanguageResponse();
			responseItem.setId(programmingLanguage.getId());
			responseItem.setName(programmingLanguage.getName());
		
			return responseItem;
		}
	}
	
	public boolean isIdExist(int id) { 
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		
		for(ProgrammingLanguage pLanguage : programmingLanguages) {
			if(pLanguage.getId() == id) {
			return true;
			} 
		} 
		return false;
	}
	
	public boolean isNameExistForCreate(String programmingLanguageName) {
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		 
		for(ProgrammingLanguage pLanguage : programmingLanguages) {
			if(pLanguage.getName().equalsIgnoreCase(programmingLanguageName)) { 
				return true;
			}
		} 
		return false; 
	}
	
	public boolean isNameExist(int id, String programmingLanguageName) {
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		 
		for(ProgrammingLanguage pLanguage : programmingLanguages) {
			if(pLanguage.getId() != id && pLanguage.getName().equalsIgnoreCase(programmingLanguageName)) { 
				return true;
			}
		} 
		return false; 
	}
	
	public boolean isAnyChange(int id, String programmingLanguageName) {
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		
		for(ProgrammingLanguage pLanguage : programmingLanguages) {
			if(pLanguage.getId() == id && !pLanguage.getName().equals(programmingLanguageName)) {
				return true;
			}
		}	
		return false;
	}
}
