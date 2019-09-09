package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.InfoFileRepository;
import domain.InfoFile;

@Component
@Transactional
public class StringToInfoFileConverter implements Converter<String, InfoFile> {

	@Autowired
	InfoFileRepository repository;

	@Override
	public InfoFile convert(String s) {
		InfoFile res;
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