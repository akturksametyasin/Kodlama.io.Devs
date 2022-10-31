package kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Kodlama.io.Devs.business.abtracts.ProgrammingLanguageService;
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
	public List<ProgrammingLanguage> getAll() {
		return programmingLanguageRepository.getAll();
	}

	@Override
	public void add(ProgrammingLanguage programmingLanguage) throws Exception {
		if(isNameExist(programmingLanguage)) throw new Exception("Programming languages' names cannot repeat.");
		if(isIdExist(programmingLanguage.getId())) throw new Exception("Ids cannot repeat.");
		if(programmingLanguage.getName().isEmpty()) throw new Exception("Programming language cannot be empty.");
		programmingLanguageRepository.add(programmingLanguage);
	}

	@Override
	public void delete(int id) throws Exception {
		if (!isIdExist(id)) throw new Exception("Id couldn't find.");
		for(ProgrammingLanguage pLanguage : programmingLanguageRepository.getAll()) {
			if(pLanguage.getId() == id) {
				int indexOfPLanguage = programmingLanguageRepository.getAll().indexOf(pLanguage);
				programmingLanguageRepository.delete(indexOfPLanguage);
			}
		}
		
	}
	
	@Override
	public void update(ProgrammingLanguage programmingLanguage) throws Exception {
		if(!isIdExist(programmingLanguage.getId())) throw new Exception("Id couldn't find.");
		if(isNameExist(programmingLanguage)) throw new Exception("Programming languages' names cannot repeat.");
		if(!isAnyChange(programmingLanguage)) throw new Exception("Id and name are the same. There isn't any change.");
		programmingLanguageRepository.update(programmingLanguage);
	}
	
	@Override
	public ProgrammingLanguage bring(int id) throws Exception {
		if(!isIdExist(id)) throw new Exception("Id couldn't find.");
		for(ProgrammingLanguage pLanguage : programmingLanguageRepository.getAll()) {
			if(pLanguage.getId() == id) {
				int indexOfPLanguage = programmingLanguageRepository.getAll().indexOf(pLanguage);
				return programmingLanguageRepository.bring(indexOfPLanguage);
			}
		}
		return null;
	}
	
	public boolean isNameExist(ProgrammingLanguage programmingLanguage) {
		for(ProgrammingLanguage pLanguage : programmingLanguageRepository.getAll()) {
			if(pLanguage.getId() != programmingLanguage.getId() && pLanguage.getName().equalsIgnoreCase(programmingLanguage.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAnyChange(ProgrammingLanguage programmingLanguage) {
		for(ProgrammingLanguage pLanguage : programmingLanguageRepository.getAll()) {
			if(pLanguage.getId() == programmingLanguage.getId() && !pLanguage.getName().equals(programmingLanguage.getName())) {
				return true;
			}
			else if(pLanguage.getId() == programmingLanguage.getId() && pLanguage.getName().equals(programmingLanguage.getName())) {
				return false;
			}
		}
		return false;
	}
	
	public boolean isIdExist(int id) {
		for(ProgrammingLanguage pLanguage : programmingLanguageRepository.getAll()) {
			if(pLanguage.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
}
