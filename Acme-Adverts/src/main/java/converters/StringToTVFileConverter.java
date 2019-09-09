package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.TVFileRepository;
import domain.TVFile;

@Component
@Transactional
public class StringToTVFileConverter implements Converter<String, TVFile> {

	@Autowired
	TVFileRepository repository;

	@Override
	public TVFile convert(String s) {
		TVFile res;
		int id;

		try {
			if (StringUtils.isEmpty(s))
				res = null;
			else {
				id = Integer.valueOf(s);
				res = repository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}
}