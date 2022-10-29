package kodlama.io.Kodlama.io.Devs.dataAccess.abstracts;

import java.util.List;

import kodlama.io.Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageRepository {
	List<ProgrammingLanguage> getAll();
	public void add(ProgrammingLanguage programmingLanguage);
	public void delete(int id);
	public void update(ProgrammingLanguage programmingLanguage);
	ProgrammingLanguage bring(int id);
}
