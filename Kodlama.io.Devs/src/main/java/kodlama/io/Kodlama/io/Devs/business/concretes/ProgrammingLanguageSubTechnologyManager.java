package kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Kodlama.io.Devs.business.abtracts.ProgrammingLanguageSubTechnologyService;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageSubTechnologyRequests.CreateProgrammingLanguageSubTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageSubTechnologyRequests.DeleteProgrammingLanguageSubTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageSubTechnologyRequests.UpdateProgrammingLanguageSubTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageSubTechnologyResponses.GetAllProgrammingLanguageSubTechnologiesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageSubTechnologyResponses.GetByProgrammingLanguageIdSubTechnologiesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageSubTechnologyResponses.GetProgrammingLanguageSubTechnologyResponse;
import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageSubTechnologyRepository;
import kodlama.io.Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;
import kodlama.io.Kodlama.io.Devs.entities.concretes.ProgrammingLanguageSubTechnology;

@Service
public class ProgrammingLanguageSubTechnologyManager implements ProgrammingLanguageSubTechnologyService{
	private ProgrammingLanguageRepository programmingLanguageRepository;
	private ProgrammingLanguageSubTechnologyRepository programmingLanguageSubTechnologyRepository;
	
	@Autowired
	public ProgrammingLanguageSubTechnologyManager(ProgrammingLanguageRepository programmingLanguageRepository, ProgrammingLanguageSubTechnologyRepository programmingLanguageSubTechnologyRepository) {
		this.programmingLanguageRepository = programmingLanguageRepository;
		this.programmingLanguageSubTechnologyRepository = programmingLanguageSubTechnologyRepository;
	}


	@Override
	public List<GetAllProgrammingLanguageSubTechnologiesResponse> getall() {
		List<ProgrammingLanguageSubTechnology> programmingLanguageSubTechnologies = programmingLanguageSubTechnologyRepository.findAll();
		List<GetAllProgrammingLanguageSubTechnologiesResponse> programmingLanguageSubTechnologiesResponse = new ArrayList<GetAllProgrammingLanguageSubTechnologiesResponse>();
		
		for (ProgrammingLanguageSubTechnology programmingLanguageSubTechnology : programmingLanguageSubTechnologies) {
			GetAllProgrammingLanguageSubTechnologiesResponse responseItem = new GetAllProgrammingLanguageSubTechnologiesResponse();
			responseItem.setProgrammingLanguageId(programmingLanguageSubTechnology.getProgrammingLanguage().getId());
			responseItem.setProgrammingLanguageName(programmingLanguageSubTechnology.getProgrammingLanguage().getName());
			responseItem.setId(programmingLanguageSubTechnology.getId());
			responseItem.setName(programmingLanguageSubTechnology.getName());
			
			
			programmingLanguageSubTechnologiesResponse.add(responseItem);
		}
		return programmingLanguageSubTechnologiesResponse;
	}


	@Override
	public void add(int programmingLanguageId,CreateProgrammingLanguageSubTechnologyRequest createProgrammingLanguageSubTechnologyRequest) throws Exception {
		if(!isProgrammingLanguageIdExist(programmingLanguageId)) {
			throw new Exception("Programming language's id couldn't find.");
		}
		if(isNameExistForCreate(createProgrammingLanguageSubTechnologyRequest.getName())) {
			throw new Exception("Programming language sub-technologies' names cannot repeat.");
		}
		else {
			ProgrammingLanguageSubTechnology programmingLanguageSubTechnology = new ProgrammingLanguageSubTechnology();
			ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(programmingLanguageId).get();
		
			programmingLanguageSubTechnology.setName(createProgrammingLanguageSubTechnologyRequest.getName());
			programmingLanguageSubTechnology.setProgrammingLanguage(programmingLanguage);
		
			programmingLanguageSubTechnologyRepository.save(programmingLanguageSubTechnology);
		}
	}
	
	@Override
	public void update(int id, UpdateProgrammingLanguageSubTechnologyRequest updateProgrammingLanguageSubTechnologyRequest) throws Exception {
		if(!isIdExist(id)) {
			throw new Exception("Id couldn't find.");
		}
		if(isNameExist(id, updateProgrammingLanguageSubTechnologyRequest.getName())) {
			throw new Exception("Programming language sub-technologies' names cannot repeat.");
		}
		if(!isAnyChange(id, updateProgrammingLanguageSubTechnologyRequest.getName())) {
			throw new Exception("Id and name are the same. There isn't any change.");
		}
		else {
			ProgrammingLanguageSubTechnology programmingLanguageSubTechnology = programmingLanguageSubTechnologyRepository.findById(id).get();
			programmingLanguageSubTechnology.setName(updateProgrammingLanguageSubTechnologyRequest.getName());
		
			programmingLanguageSubTechnologyRepository.save(programmingLanguageSubTechnology);
		}
	}

	@Override
	public void delete(DeleteProgrammingLanguageSubTechnologyRequest deleteProgrammingLanguageSubTechnologyRequest) throws Exception {
		if(!isIdExist(deleteProgrammingLanguageSubTechnologyRequest.getId())) {
			throw new Exception("Id couldn't find.");
		}
		else {
			ProgrammingLanguageSubTechnology programmingLanguageSubTechnology = this.programmingLanguageSubTechnologyRepository.findById(deleteProgrammingLanguageSubTechnologyRequest.getId()).get();
		
			programmingLanguageSubTechnologyRepository.delete(programmingLanguageSubTechnology);
		}
	}

	@Override
	public List<GetByProgrammingLanguageIdSubTechnologiesResponse> getByProgrammingLanguageId(int programmingLanguageId) throws Exception {
		List<ProgrammingLanguageSubTechnology> programmingLanguageSubTechnologies = programmingLanguageSubTechnologyRepository.findAll();
		List<GetByProgrammingLanguageIdSubTechnologiesResponse> programmingLanguageSubTechnologiesResponse = new ArrayList<GetByProgrammingLanguageIdSubTechnologiesResponse>();
		
		if(!isProgrammingLanguageIdExist(programmingLanguageId)) {
			throw new Exception("Programming language's id couldn't find.");
		}
		else {
			for (ProgrammingLanguageSubTechnology programmingLanguageSubTechnology : programmingLanguageSubTechnologies) {
				if(programmingLanguageId == programmingLanguageSubTechnology.getProgrammingLanguage().getId()) {
					GetByProgrammingLanguageIdSubTechnologiesResponse responseItem = new GetByProgrammingLanguageIdSubTechnologiesResponse();
					responseItem.setProgrammingLanguageId(programmingLanguageSubTechnology.getProgrammingLanguage().getId());
					responseItem.setProgrammingLanguageName(programmingLanguageSubTechnology.getProgrammingLanguage().getName());
					responseItem.setId(programmingLanguageSubTechnology.getId());
					responseItem.setName(programmingLanguageSubTechnology.getName());
					
					programmingLanguageSubTechnologiesResponse.add(responseItem);
				}
			}
			return programmingLanguageSubTechnologiesResponse;	
		}
		
	}

	@Override
	public List<GetProgrammingLanguageSubTechnologyResponse> getById(int id) throws Exception {
		List<ProgrammingLanguageSubTechnology> programmingLanguageSubTechnologies = programmingLanguageSubTechnologyRepository.findAll();
		List<GetProgrammingLanguageSubTechnologyResponse> programmingLanguageSubTechnologyResponse = new ArrayList<GetProgrammingLanguageSubTechnologyResponse>();
		
		if(!isIdExist(id)) {
			throw new Exception("Id couldn't find.");
		}
		else {
			for (ProgrammingLanguageSubTechnology programmingLanguageSubTechnology : programmingLanguageSubTechnologies) {
				if(id == programmingLanguageSubTechnology.getId()) {
					GetProgrammingLanguageSubTechnologyResponse responseItem = new GetProgrammingLanguageSubTechnologyResponse();
					responseItem.setProgrammingLanguageId(programmingLanguageSubTechnology.getProgrammingLanguage().getId());
					responseItem.setProgrammingLanguageName(programmingLanguageSubTechnology.getProgrammingLanguage().getName());
					responseItem.setId(programmingLanguageSubTechnology.getId());
					responseItem.setName(programmingLanguageSubTechnology.getName());
					
					programmingLanguageSubTechnologyResponse.add(responseItem);
				}
			}
			return programmingLanguageSubTechnologyResponse;	
		}
	}

	public boolean isIdExist(int id) {
		List<ProgrammingLanguageSubTechnology> programmingLanguageSubTechnologies = programmingLanguageSubTechnologyRepository.findAll();
		
		for(ProgrammingLanguageSubTechnology pLanguageSubTechnology : programmingLanguageSubTechnologies) {
			if(pLanguageSubTechnology.getId() == id) {
				return true;
			}	
		}
		return false;
	}
	
	public boolean isProgrammingLanguageIdExist(int programmingLanguageId) {
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		
		for(ProgrammingLanguage pLanguage : programmingLanguages) {
			if(pLanguage.getId() == programmingLanguageId) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNameExistForCreate(String programmingLanguageSubTechnologyName) {
		List<ProgrammingLanguageSubTechnology> programmingLanguageSubTechnologies = programmingLanguageSubTechnologyRepository.findAll();
		
		for(ProgrammingLanguageSubTechnology pLanguageSubTechnology : programmingLanguageSubTechnologies) {
			if(pLanguageSubTechnology.getName().equals(programmingLanguageSubTechnologyName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNameExist(int id, String programmingLanguageSubTechnologyName) {
		List<ProgrammingLanguageSubTechnology> programmingLanguageSubTechnologies = programmingLanguageSubTechnologyRepository.findAll();
		
		for(ProgrammingLanguageSubTechnology programmingLanguageSubTechnology : programmingLanguageSubTechnologies) {
			if(programmingLanguageSubTechnology.getId() != id && programmingLanguageSubTechnology.getName().equals(programmingLanguageSubTechnologyName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAnyChange(int id, String programminLanguageSubTechnologyName) {
		List<ProgrammingLanguageSubTechnology> programmingLanguageSubTechnologies = programmingLanguageSubTechnologyRepository.findAll();
		
		for(ProgrammingLanguageSubTechnology pLanguageSubTechnology : programmingLanguageSubTechnologies) {
			if(pLanguageSubTechnology.getId() == id && !pLanguageSubTechnology.getName().equals(programminLanguageSubTechnologyName)) {
				return true;
			}
		}
		return false;
	}
}
