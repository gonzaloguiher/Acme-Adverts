package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.RadioFileRepository;
import domain.RadioFile;

@Component
@Transactional
public class StringToRadioFileConverter implements Converter<String, RadioFile> {

	@Autowired
	RadioFileRepository repository;

	@Override
	public RadioFile convert(String s) {
		RadioFile res;
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