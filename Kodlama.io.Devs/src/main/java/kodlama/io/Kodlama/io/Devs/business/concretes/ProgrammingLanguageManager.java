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
		isNameExist(programmingLanguage);
		if(isIdExist(programmingLanguage.getId())) throw new Exception("Ids cannot repeat.");
		if(programmingLanguage.getName().isEmpty()) throw new Exception("Programming language cannot be empty.");
		programmingLanguageRepository.add(programmingLanguage);
	}

	@Override
	public void delete(int id) throws Exception {
		if (!isIdExist(id)) throw new Exception("Id couldn't find.");
		programmingLanguageRepository.delete(id);
	}
	
	@Override
	public void update(ProgrammingLanguage programmingLanguage) throws Exception {
		if(!isIdExist(programmingLanguage.getId())) throw new Exception("Id couldn't find.");
		isNameExist(programmingLanguage);
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
	
	public void isNameExist(ProgrammingLanguage programmingLanguage) throws Exception {
		for(ProgrammingLanguage pLanguage : programmingLanguageRepository.getAll()) {
			if(pLanguage.getId() != programmingLanguage.getId() && pLanguage.getName().equalsIgnoreCase(programmingLanguage.getName())) {
				throw new Exception("Programming languages' names cannot repeat.");
			}
			else if(pLanguage.getId() == programmingLanguage.getId() && pLanguage.getName().equals(programmingLanguage.getName())) {
				throw new Exception("Id and name are the same. There isn't any change.");
			}
			else if(pLanguage.getId() == programmingLanguage.getId() && !pLanguage.getName().equals(programmingLanguage.getName())) {
				break;
			}
		}
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
