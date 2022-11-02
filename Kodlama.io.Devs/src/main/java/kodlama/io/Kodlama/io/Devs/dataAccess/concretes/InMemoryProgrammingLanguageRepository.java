package kodlama.io.Kodlama.io.Devs.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

@Repository
public class InMemoryProgrammingLanguageRepository implements ProgrammingLanguageRepository {
	List<ProgrammingLanguage> programmingLanguages;

	public InMemoryProgrammingLanguageRepository() {
		programmingLanguages = new ArrayList<ProgrammingLanguage>();
		programmingLanguages.add(new ProgrammingLanguage(1, "C#"));
		programmingLanguages.add(new ProgrammingLanguage(2, "Java"));
		programmingLanguages.add(new ProgrammingLanguage(3, "Python"));
	}
	
	@Override
	public List<ProgrammingLanguage> getAll() {
		return programmingLanguages;
	}

	@Override
	public void add(ProgrammingLanguage programmingLanguage) {
		programmingLanguages.add(programmingLanguage);
	}
	
	@Override
	public void delete(int indexOfLanguage) {
		for(ProgrammingLanguage pLanguage : programmingLanguages) {
			if(pLanguage.getId() == indexOfLanguage) {
				programmingLanguages.remove(indexOfLanguage);
			}
		}
	}

	@Override
	public void update(ProgrammingLanguage programmingLanguage) {
		for(ProgrammingLanguage pLanguage : programmingLanguages) {
			if(pLanguage.getId() == programmingLanguage.getId()) {
				pLanguage.setName(programmingLanguage.getName());
			}
		}
	}

	@Override
	public ProgrammingLanguage get(int indexOfPLanguage) {
		return programmingLanguages.get(indexOfPLanguage);
	}
	
	
}
