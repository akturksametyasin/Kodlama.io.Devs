package kodlama.io.Kodlama.io.Devs.business.abtracts;

import java.util.List;

import kodlama.io.Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageService {
	List<ProgrammingLanguage> getAll();
	public void add(ProgrammingLanguage programmingLanguage) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProgrammingLanguage programmingLanguage) throws Exception;
	public ProgrammingLanguage get(int id) throws Exception;
}
